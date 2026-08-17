package androidx.compose.foundation;

import androidx.compose.foundation.BasicTooltipKt;
import androidx.compose.foundation.gestures.ForEachGestureKt;
import androidx.compose.foundation.gestures.LongPressResult;
import androidx.compose.foundation.gestures.TapGestureDetectorKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerEvent;
import androidx.compose.ui.input.pointer.PointerEventPass;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.PointerType;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.semantics.LiveRegionMode;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.window.AndroidPopup_androidKt;
import androidx.compose.ui.window.PopupPositionProvider;
import androidx.compose.ui.window.PopupProperties;
import com.intellij.util.io.IOUtil;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000D\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aa\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\u0011\u0010\u000e\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u0006H\u0007¢\u0006\u0002\u0010\u000f\u001a:\u0010\u0010\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\u0011\u0010\u000e\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u0006H\u0003¢\u0006\u0002\u0010\u0011\u001a@\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u000b\u001a\u00020\f2\u0011\u0010\u000e\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u0006H\u0003¢\u0006\u0002\u0010\u0015\u001a\u001c\u0010\u0016\u001a\u00020\n*\u00020\n2\u0006\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\bH\u0002\u001a,\u0010\u0018\u001a\u00020\n*\u00020\n2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002\u001a+\u0010\u001b\u001a\u00020\b2\b\b\u0002\u0010\u001c\u001a\u00020\f2\b\b\u0002\u0010\u001d\u001a\u00020\f2\b\b\u0002\u0010\u001e\u001a\u00020\u001fH\u0007¢\u0006\u0002\u0010 \u001a&\u0010!\u001a\u00020\b2\b\b\u0002\u0010\u001c\u001a\u00020\f2\b\b\u0002\u0010\u001d\u001a\u00020\f2\b\b\u0002\u0010\u001e\u001a\u00020\u001fH\u0007¨\u0006\""}, d2 = {"BasicTooltipBox", "", "positionProvider", "Landroidx/compose/ui/window/PopupPositionProvider;", "tooltip", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "state", "Landroidx/compose/foundation/BasicTooltipState;", "modifier", "Landroidx/compose/ui/Modifier;", "focusable", "", "enableUserInput", "content", "(Landroidx/compose/ui/window/PopupPositionProvider;Lkotlin/jvm/functions/Function2;Landroidx/compose/foundation/BasicTooltipState;Landroidx/compose/ui/Modifier;ZZLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "WrappedAnchor", "(ZLandroidx/compose/foundation/BasicTooltipState;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "TooltipPopup", "scope", "Lkotlinx/coroutines/CoroutineScope;", "(Landroidx/compose/ui/window/PopupPositionProvider;Landroidx/compose/foundation/BasicTooltipState;Lkotlinx/coroutines/CoroutineScope;ZLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "handleGestures", "enabled", "anchorSemantics", "label", "", "rememberBasicTooltipState", "initialIsVisible", "isPersistent", "mutatorMutex", "Landroidx/compose/foundation/MutatorMutex;", "(ZZLandroidx/compose/foundation/MutatorMutex;Landroidx/compose/runtime/Composer;II)Landroidx/compose/foundation/BasicTooltipState;", "BasicTooltipState", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class BasicTooltipKt {
    /* JADX WARN: Code duplicated, block: B:100:0x01c3  */
    /* JADX WARN: Code duplicated, block: B:103:0x01cb  */
    /* JADX WARN: Code duplicated, block: B:105:0x01d1  */
    /* JADX WARN: Code duplicated, block: B:108:0x01e8  */
    /* JADX WARN: Code duplicated, block: B:111:0x01ef  */
    /* JADX WARN: Code duplicated, block: B:114:0x01fa  */
    /* JADX WARN: Code duplicated, block: B:116:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:38:0x0065  */
    /* JADX WARN: Code duplicated, block: B:40:0x006a  */
    /* JADX WARN: Code duplicated, block: B:42:0x006e  */
    /* JADX WARN: Code duplicated, block: B:44:0x0076  */
    /* JADX WARN: Code duplicated, block: B:45:0x0079  */
    /* JADX WARN: Code duplicated, block: B:49:0x0082  */
    /* JADX WARN: Code duplicated, block: B:51:0x0086  */
    /* JADX WARN: Code duplicated, block: B:53:0x0089  */
    /* JADX WARN: Code duplicated, block: B:55:0x0091  */
    /* JADX WARN: Code duplicated, block: B:56:0x0094  */
    /* JADX WARN: Code duplicated, block: B:60:0x009c  */
    /* JADX WARN: Code duplicated, block: B:62:0x00a4  */
    /* JADX WARN: Code duplicated, block: B:63:0x00a7  */
    /* JADX WARN: Code duplicated, block: B:66:0x00ac  */
    /* JADX WARN: Code duplicated, block: B:69:0x00bb  */
    /* JADX WARN: Code duplicated, block: B:70:0x00be  */
    /* JADX WARN: Code duplicated, block: B:73:0x00c7 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:74:0x00c9  */
    /* JADX WARN: Code duplicated, block: B:76:0x00ce  */
    /* JADX WARN: Code duplicated, block: B:77:0x00d1  */
    /* JADX WARN: Code duplicated, block: B:79:0x00d4  */
    /* JADX WARN: Code duplicated, block: B:82:0x00dc  */
    /* JADX WARN: Code duplicated, block: B:85:0x00f1  */
    /* JADX WARN: Code duplicated, block: B:88:0x0124  */
    /* JADX WARN: Code duplicated, block: B:91:0x0130  */
    /* JADX WARN: Code duplicated, block: B:92:0x0134  */
    /* JADX WARN: Code duplicated, block: B:95:0x016a  */
    /* JADX WARN: Code duplicated, block: B:97:0x0195  */
    public static final void BasicTooltipBox(final PopupPositionProvider popupPositionProvider, final Function2<? super Composer, ? super Integer, Unit> function2, BasicTooltipState basicTooltipState, Modifier modifier, boolean z, boolean z2, final Function2<? super Composer, ? super Integer, Unit> function3, Composer composer, final int i, final int i2) {
        int i3;
        Function2<? super Composer, ? super Integer, Unit> function4;
        Modifier modifier2;
        int i4;
        boolean z3;
        int i5;
        int i6;
        boolean z4;
        int i7;
        Function2<? super Composer, ? super Integer, Unit> function5;
        int i8;
        boolean z5;
        final boolean z6;
        final boolean z7;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Object objRememberedValue;
        Composer.Companion companion;
        CoroutineScope coroutineScope;
        Function0 constructor;
        boolean z8;
        Object objRememberedValue2;
        int i9;
        final BasicTooltipState basicTooltipState2 = basicTooltipState;
        Composer composerStartRestartGroup = composer.startRestartGroup(196062260);
        if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(popupPositionProvider) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i & 48) == 0) {
            function4 = function2;
            i3 |= composerStartRestartGroup.changedInstance(function4) ? 32 : 16;
        } else {
            function4 = function2;
        }
        if ((i & 384) == 0) {
            i3 |= composerStartRestartGroup.changed(basicTooltipState2) ? 256 : 128;
        }
        int i10 = i2 & 8;
        if (i10 == 0) {
            if ((i & 3072) == 0) {
                modifier2 = modifier;
                i3 |= composerStartRestartGroup.changed(modifier2) ? 2048 : 1024;
            }
            i4 = i2 & 16;
            if (i4 != 0) {
                if ((i & 24576) == 0) {
                    z3 = z;
                    if (composerStartRestartGroup.changed(z3)) {
                        i5 = 16384;
                    } else {
                        i5 = 8192;
                    }
                    i3 |= i5;
                }
                i6 = i2 & 32;
                if (i6 != 0) {
                    if ((196608 & i) == 0) {
                        z4 = z2;
                        if (composerStartRestartGroup.changed(z4)) {
                            i7 = 131072;
                        } else {
                            i7 = 65536;
                        }
                        i3 |= i7;
                    }
                    if ((1572864 & i) == 0) {
                        function5 = function3;
                        if (composerStartRestartGroup.changedInstance(function5)) {
                            i9 = IOUtil.MiB;
                        } else {
                            i9 = 524288;
                        }
                        i3 |= i9;
                    } else {
                        function5 = function3;
                    }
                    i8 = i3;
                    if ((599187 & i8) != 599186) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z5, i8 & 1)) {
                        if (i10 != 0) {
                            modifier2 = Modifier.Companion;
                        }
                        if (i4 != 0) {
                            z3 = true;
                        } else {
                            z3 = z3;
                        }
                        if (i6 != 0) {
                            z4 = true;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(196062260, i8, -1, "androidx.compose.foundation.BasicTooltipBox (BasicTooltip.kt:81)");
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        companion = Composer.Companion;
                        if (objRememberedValue == companion.getEmpty()) {
                            objRememberedValue = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        coroutineScope = (CoroutineScope) objRememberedValue;
                        Modifier.Companion companion2 = Modifier.Companion;
                        MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.getTopStart(), false);
                        int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                        CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion2);
                        ComposeUiNode.Companion companion3 = ComposeUiNode.Companion;
                        constructor = companion3.getConstructor();
                        if (composerStartRestartGroup.getApplier() == null) {
                            ComposablesKt.invalidApplier();
                        }
                        composerStartRestartGroup.startReusableNode();
                        if (composerStartRestartGroup.getInserting()) {
                            composerStartRestartGroup.createNode(constructor);
                        } else {
                            composerStartRestartGroup.useNode();
                        }
                        Composer composer2 = Updater.constructor-impl(composerStartRestartGroup);
                        Updater.set-impl(composer2, measurePolicyMaybeCachedBoxMeasurePolicy, companion3.getSetMeasurePolicy());
                        Updater.set-impl(composer2, currentCompositionLocalMap, companion3.getSetResolvedCompositionLocals());
                        Updater.init-impl(composer2, Integer.valueOf(iHashCode), companion3.getSetCompositeKeyHash());
                        Updater.reconcile-impl(composer2, companion3.getApplyOnDeactivatedNodeAssertion());
                        Updater.set-impl(composer2, modifierMaterializeModifier, companion3.getSetModifier());
                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                        if (basicTooltipState.isVisible()) {
                            composerStartRestartGroup.startReplaceGroup(1833353604);
                            int i11 = i8 >> 3;
                            z8 = false;
                            TooltipPopup(popupPositionProvider, basicTooltipState, coroutineScope, z3, function4, composerStartRestartGroup, (i8 & 14) | (i11 & 112) | (i11 & 7168) | ((i8 << 9) & 57344));
                            composerStartRestartGroup = composerStartRestartGroup;
                        } else {
                            z8 = false;
                            composerStartRestartGroup.startReplaceGroup(1829588468);
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        int i12 = i8 >> 3;
                        int i13 = ((i8 >> 15) & 14) | (i12 & 112) | (i12 & 896) | ((i8 >> 9) & 7168);
                        basicTooltipState2 = basicTooltipState;
                        Modifier modifier3 = modifier2;
                        boolean z9 = z4;
                        WrappedAnchor(z9, basicTooltipState2, modifier3, function5, composerStartRestartGroup, i13, 0);
                        composerStartRestartGroup.endNode();
                        if ((i8 & 896) == 256) {
                            z8 = true;
                        }
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (z8 || objRememberedValue2 == companion.getEmpty()) {
                            objRememberedValue2 = new Function1() { // from class: ct0
                                public final Object invoke(Object obj) {
                                    return BasicTooltipKt.BasicTooltipBox$lambda$1$0(basicTooltipState2, (DisposableEffectScope) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        EffectsKt.DisposableEffect(basicTooltipState2, (Function1) objRememberedValue2, composerStartRestartGroup, (i8 >> 6) & 14);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        z6 = z9;
                        modifier2 = modifier3;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        z6 = z4;
                    }
                    z7 = z3;
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        final BasicTooltipState basicTooltipState3 = basicTooltipState2;
                        final Modifier modifier4 = modifier2;
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: et0
                            public final Object invoke(Object obj, Object obj2) {
                                return BasicTooltipKt.i(popupPositionProvider, function2, basicTooltipState3, modifier4, z7, z6, function3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 196608;
                z4 = z2;
                if ((1572864 & i) == 0) {
                    function5 = function3;
                    if (composerStartRestartGroup.changedInstance(function5)) {
                        i9 = IOUtil.MiB;
                    } else {
                        i9 = 524288;
                    }
                    i3 |= i9;
                } else {
                    function5 = function3;
                }
                i8 = i3;
                if ((599187 & i8) != 599186) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z5, i8 & 1)) {
                    if (i10 != 0) {
                        modifier2 = Modifier.Companion;
                    }
                    if (i4 != 0) {
                        z3 = true;
                    } else {
                        z3 = z3;
                    }
                    if (i6 != 0) {
                        z4 = true;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(196062260, i8, -1, "androidx.compose.foundation.BasicTooltipBox (BasicTooltip.kt:81)");
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    companion = Composer.Companion;
                    if (objRememberedValue == companion.getEmpty()) {
                        objRememberedValue = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    coroutineScope = (CoroutineScope) objRememberedValue;
                    Modifier.Companion companion4 = Modifier.Companion;
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.getTopStart(), false);
                    int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                    CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion4);
                    ComposeUiNode.Companion companion5 = ComposeUiNode.Companion;
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
                    Composer composer3 = Updater.constructor-impl(composerStartRestartGroup);
                    Updater.set-impl(composer3, measurePolicyMaybeCachedBoxMeasurePolicy2, companion5.getSetMeasurePolicy());
                    Updater.set-impl(composer3, currentCompositionLocalMap2, companion5.getSetResolvedCompositionLocals());
                    Updater.init-impl(composer3, Integer.valueOf(iHashCode2), companion5.getSetCompositeKeyHash());
                    Updater.reconcile-impl(composer3, companion5.getApplyOnDeactivatedNodeAssertion());
                    Updater.set-impl(composer3, modifierMaterializeModifier2, companion5.getSetModifier());
                    BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                    if (basicTooltipState.isVisible()) {
                        composerStartRestartGroup.startReplaceGroup(1833353604);
                        int i14 = i8 >> 3;
                        z8 = false;
                        TooltipPopup(popupPositionProvider, basicTooltipState, coroutineScope, z3, function4, composerStartRestartGroup, (i8 & 14) | (i14 & 112) | (i14 & 7168) | ((i8 << 9) & 57344));
                        composerStartRestartGroup = composerStartRestartGroup;
                    } else {
                        z8 = false;
                        composerStartRestartGroup.startReplaceGroup(1829588468);
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    int i15 = i8 >> 3;
                    int i16 = ((i8 >> 15) & 14) | (i15 & 112) | (i15 & 896) | ((i8 >> 9) & 7168);
                    basicTooltipState2 = basicTooltipState;
                    Modifier modifier5 = modifier2;
                    boolean z10 = z4;
                    WrappedAnchor(z10, basicTooltipState2, modifier5, function5, composerStartRestartGroup, i16, 0);
                    composerStartRestartGroup.endNode();
                    if ((i8 & 896) == 256) {
                        z8 = true;
                    }
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (z8) {
                        objRememberedValue2 = new Function1() { // from class: ct0
                            public final Object invoke(Object obj) {
                                return BasicTooltipKt.BasicTooltipBox$lambda$1$0(basicTooltipState2, (DisposableEffectScope) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = new Function1() { // from class: ct0
                            public final Object invoke(Object obj) {
                                return BasicTooltipKt.BasicTooltipBox$lambda$1$0(basicTooltipState2, (DisposableEffectScope) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    EffectsKt.DisposableEffect(basicTooltipState2, (Function1) objRememberedValue2, composerStartRestartGroup, (i8 >> 6) & 14);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    z6 = z10;
                    modifier2 = modifier5;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    z6 = z4;
                }
                z7 = z3;
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    final BasicTooltipState basicTooltipState4 = basicTooltipState2;
                    final Modifier modifier6 = modifier2;
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: et0
                        public final Object invoke(Object obj, Object obj2) {
                            return BasicTooltipKt.i(popupPositionProvider, function2, basicTooltipState4, modifier6, z7, z6, function3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 24576;
            z3 = z;
            i6 = i2 & 32;
            if (i6 != 0) {
                if ((196608 & i) == 0) {
                    z4 = z2;
                    if (composerStartRestartGroup.changed(z4)) {
                        i7 = 131072;
                    } else {
                        i7 = 65536;
                    }
                    i3 |= i7;
                }
                if ((1572864 & i) == 0) {
                    function5 = function3;
                    if (composerStartRestartGroup.changedInstance(function5)) {
                        i9 = IOUtil.MiB;
                    } else {
                        i9 = 524288;
                    }
                    i3 |= i9;
                } else {
                    function5 = function3;
                }
                i8 = i3;
                if ((599187 & i8) != 599186) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z5, i8 & 1)) {
                    if (i10 != 0) {
                        modifier2 = Modifier.Companion;
                    }
                    if (i4 != 0) {
                        z3 = true;
                    } else {
                        z3 = z3;
                    }
                    if (i6 != 0) {
                        z4 = true;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(196062260, i8, -1, "androidx.compose.foundation.BasicTooltipBox (BasicTooltip.kt:81)");
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    companion = Composer.Companion;
                    if (objRememberedValue == companion.getEmpty()) {
                        objRememberedValue = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    coroutineScope = (CoroutineScope) objRememberedValue;
                    Modifier.Companion companion6 = Modifier.Companion;
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy3 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.getTopStart(), false);
                    int iHashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                    CompositionLocalMap currentCompositionLocalMap3 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion6);
                    ComposeUiNode.Companion companion7 = ComposeUiNode.Companion;
                    constructor = companion7.getConstructor();
                    if (composerStartRestartGroup.getApplier() == null) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    Composer composer4 = Updater.constructor-impl(composerStartRestartGroup);
                    Updater.set-impl(composer4, measurePolicyMaybeCachedBoxMeasurePolicy3, companion7.getSetMeasurePolicy());
                    Updater.set-impl(composer4, currentCompositionLocalMap3, companion7.getSetResolvedCompositionLocals());
                    Updater.init-impl(composer4, Integer.valueOf(iHashCode3), companion7.getSetCompositeKeyHash());
                    Updater.reconcile-impl(composer4, companion7.getApplyOnDeactivatedNodeAssertion());
                    Updater.set-impl(composer4, modifierMaterializeModifier3, companion7.getSetModifier());
                    BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
                    if (basicTooltipState.isVisible()) {
                        composerStartRestartGroup.startReplaceGroup(1833353604);
                        int i17 = i8 >> 3;
                        z8 = false;
                        TooltipPopup(popupPositionProvider, basicTooltipState, coroutineScope, z3, function4, composerStartRestartGroup, (i8 & 14) | (i17 & 112) | (i17 & 7168) | ((i8 << 9) & 57344));
                        composerStartRestartGroup = composerStartRestartGroup;
                    } else {
                        z8 = false;
                        composerStartRestartGroup.startReplaceGroup(1829588468);
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    int i18 = i8 >> 3;
                    int i19 = ((i8 >> 15) & 14) | (i18 & 112) | (i18 & 896) | ((i8 >> 9) & 7168);
                    basicTooltipState2 = basicTooltipState;
                    Modifier modifier7 = modifier2;
                    boolean z11 = z4;
                    WrappedAnchor(z11, basicTooltipState2, modifier7, function5, composerStartRestartGroup, i19, 0);
                    composerStartRestartGroup.endNode();
                    if ((i8 & 896) == 256) {
                        z8 = true;
                    }
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (z8) {
                        objRememberedValue2 = new Function1() { // from class: ct0
                            public final Object invoke(Object obj) {
                                return BasicTooltipKt.BasicTooltipBox$lambda$1$0(basicTooltipState2, (DisposableEffectScope) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = new Function1() { // from class: ct0
                            public final Object invoke(Object obj) {
                                return BasicTooltipKt.BasicTooltipBox$lambda$1$0(basicTooltipState2, (DisposableEffectScope) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    EffectsKt.DisposableEffect(basicTooltipState2, (Function1) objRememberedValue2, composerStartRestartGroup, (i8 >> 6) & 14);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    z6 = z11;
                    modifier2 = modifier7;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    z6 = z4;
                }
                z7 = z3;
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    final BasicTooltipState basicTooltipState5 = basicTooltipState2;
                    final Modifier modifier8 = modifier2;
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: et0
                        public final Object invoke(Object obj, Object obj2) {
                            return BasicTooltipKt.i(popupPositionProvider, function2, basicTooltipState5, modifier8, z7, z6, function3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 196608;
            z4 = z2;
            if ((1572864 & i) == 0) {
                function5 = function3;
                if (composerStartRestartGroup.changedInstance(function5)) {
                    i9 = IOUtil.MiB;
                } else {
                    i9 = 524288;
                }
                i3 |= i9;
            } else {
                function5 = function3;
            }
            i8 = i3;
            if ((599187 & i8) != 599186) {
                z5 = true;
            } else {
                z5 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z5, i8 & 1)) {
                if (i10 != 0) {
                    modifier2 = Modifier.Companion;
                }
                if (i4 != 0) {
                    z3 = true;
                } else {
                    z3 = z3;
                }
                if (i6 != 0) {
                    z4 = true;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(196062260, i8, -1, "androidx.compose.foundation.BasicTooltipBox (BasicTooltip.kt:81)");
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                companion = Composer.Companion;
                if (objRememberedValue == companion.getEmpty()) {
                    objRememberedValue = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                coroutineScope = (CoroutineScope) objRememberedValue;
                Modifier.Companion companion8 = Modifier.Companion;
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy4 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.getTopStart(), false);
                int iHashCode4 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                CompositionLocalMap currentCompositionLocalMap4 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion8);
                ComposeUiNode.Companion companion9 = ComposeUiNode.Companion;
                constructor = companion9.getConstructor();
                if (composerStartRestartGroup.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor);
                } else {
                    composerStartRestartGroup.useNode();
                }
                Composer composer5 = Updater.constructor-impl(composerStartRestartGroup);
                Updater.set-impl(composer5, measurePolicyMaybeCachedBoxMeasurePolicy4, companion9.getSetMeasurePolicy());
                Updater.set-impl(composer5, currentCompositionLocalMap4, companion9.getSetResolvedCompositionLocals());
                Updater.init-impl(composer5, Integer.valueOf(iHashCode4), companion9.getSetCompositeKeyHash());
                Updater.reconcile-impl(composer5, companion9.getApplyOnDeactivatedNodeAssertion());
                Updater.set-impl(composer5, modifierMaterializeModifier4, companion9.getSetModifier());
                BoxScopeInstance boxScopeInstance4 = BoxScopeInstance.INSTANCE;
                if (basicTooltipState.isVisible()) {
                    composerStartRestartGroup.startReplaceGroup(1833353604);
                    int i110 = i8 >> 3;
                    z8 = false;
                    TooltipPopup(popupPositionProvider, basicTooltipState, coroutineScope, z3, function4, composerStartRestartGroup, (i8 & 14) | (i110 & 112) | (i110 & 7168) | ((i8 << 9) & 57344));
                    composerStartRestartGroup = composerStartRestartGroup;
                } else {
                    z8 = false;
                    composerStartRestartGroup.startReplaceGroup(1829588468);
                }
                composerStartRestartGroup.endReplaceGroup();
                int i111 = i8 >> 3;
                int i112 = ((i8 >> 15) & 14) | (i111 & 112) | (i111 & 896) | ((i8 >> 9) & 7168);
                basicTooltipState2 = basicTooltipState;
                Modifier modifier9 = modifier2;
                boolean z12 = z4;
                WrappedAnchor(z12, basicTooltipState2, modifier9, function5, composerStartRestartGroup, i112, 0);
                composerStartRestartGroup.endNode();
                if ((i8 & 896) == 256) {
                    z8 = true;
                }
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (z8) {
                    objRememberedValue2 = new Function1() { // from class: ct0
                        public final Object invoke(Object obj) {
                            return BasicTooltipKt.BasicTooltipBox$lambda$1$0(basicTooltipState2, (DisposableEffectScope) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    objRememberedValue2 = new Function1() { // from class: ct0
                        public final Object invoke(Object obj) {
                            return BasicTooltipKt.BasicTooltipBox$lambda$1$0(basicTooltipState2, (DisposableEffectScope) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                EffectsKt.DisposableEffect(basicTooltipState2, (Function1) objRememberedValue2, composerStartRestartGroup, (i8 >> 6) & 14);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                z6 = z12;
                modifier2 = modifier9;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                z6 = z4;
            }
            z7 = z3;
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                final BasicTooltipState basicTooltipState6 = basicTooltipState2;
                final Modifier modifier10 = modifier2;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: et0
                    public final Object invoke(Object obj, Object obj2) {
                        return BasicTooltipKt.i(popupPositionProvider, function2, basicTooltipState6, modifier10, z7, z6, function3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 3072;
        modifier2 = modifier;
        i4 = i2 & 16;
        if (i4 != 0) {
            if ((i & 24576) == 0) {
                z3 = z;
                if (composerStartRestartGroup.changed(z3)) {
                    i5 = 16384;
                } else {
                    i5 = 8192;
                }
                i3 |= i5;
            }
            i6 = i2 & 32;
            if (i6 != 0) {
                if ((196608 & i) == 0) {
                    z4 = z2;
                    if (composerStartRestartGroup.changed(z4)) {
                        i7 = 131072;
                    } else {
                        i7 = 65536;
                    }
                    i3 |= i7;
                }
                if ((1572864 & i) == 0) {
                    function5 = function3;
                    if (composerStartRestartGroup.changedInstance(function5)) {
                        i9 = IOUtil.MiB;
                    } else {
                        i9 = 524288;
                    }
                    i3 |= i9;
                } else {
                    function5 = function3;
                }
                i8 = i3;
                if ((599187 & i8) != 599186) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z5, i8 & 1)) {
                    if (i10 != 0) {
                        modifier2 = Modifier.Companion;
                    }
                    if (i4 != 0) {
                        z3 = true;
                    } else {
                        z3 = z3;
                    }
                    if (i6 != 0) {
                        z4 = true;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(196062260, i8, -1, "androidx.compose.foundation.BasicTooltipBox (BasicTooltip.kt:81)");
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    companion = Composer.Companion;
                    if (objRememberedValue == companion.getEmpty()) {
                        objRememberedValue = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    coroutineScope = (CoroutineScope) objRememberedValue;
                    Modifier.Companion companion10 = Modifier.Companion;
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy5 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.getTopStart(), false);
                    int iHashCode5 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                    CompositionLocalMap currentCompositionLocalMap5 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier5 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion10);
                    ComposeUiNode.Companion companion11 = ComposeUiNode.Companion;
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
                    Composer composer6 = Updater.constructor-impl(composerStartRestartGroup);
                    Updater.set-impl(composer6, measurePolicyMaybeCachedBoxMeasurePolicy5, companion11.getSetMeasurePolicy());
                    Updater.set-impl(composer6, currentCompositionLocalMap5, companion11.getSetResolvedCompositionLocals());
                    Updater.init-impl(composer6, Integer.valueOf(iHashCode5), companion11.getSetCompositeKeyHash());
                    Updater.reconcile-impl(composer6, companion11.getApplyOnDeactivatedNodeAssertion());
                    Updater.set-impl(composer6, modifierMaterializeModifier5, companion11.getSetModifier());
                    BoxScopeInstance boxScopeInstance5 = BoxScopeInstance.INSTANCE;
                    if (basicTooltipState.isVisible()) {
                        composerStartRestartGroup.startReplaceGroup(1833353604);
                        int i113 = i8 >> 3;
                        z8 = false;
                        TooltipPopup(popupPositionProvider, basicTooltipState, coroutineScope, z3, function4, composerStartRestartGroup, (i8 & 14) | (i113 & 112) | (i113 & 7168) | ((i8 << 9) & 57344));
                        composerStartRestartGroup = composerStartRestartGroup;
                    } else {
                        z8 = false;
                        composerStartRestartGroup.startReplaceGroup(1829588468);
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    int i114 = i8 >> 3;
                    int i115 = ((i8 >> 15) & 14) | (i114 & 112) | (i114 & 896) | ((i8 >> 9) & 7168);
                    basicTooltipState2 = basicTooltipState;
                    Modifier modifier11 = modifier2;
                    boolean z13 = z4;
                    WrappedAnchor(z13, basicTooltipState2, modifier11, function5, composerStartRestartGroup, i115, 0);
                    composerStartRestartGroup.endNode();
                    if ((i8 & 896) == 256) {
                        z8 = true;
                    }
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (z8) {
                        objRememberedValue2 = new Function1() { // from class: ct0
                            public final Object invoke(Object obj) {
                                return BasicTooltipKt.BasicTooltipBox$lambda$1$0(basicTooltipState2, (DisposableEffectScope) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = new Function1() { // from class: ct0
                            public final Object invoke(Object obj) {
                                return BasicTooltipKt.BasicTooltipBox$lambda$1$0(basicTooltipState2, (DisposableEffectScope) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    EffectsKt.DisposableEffect(basicTooltipState2, (Function1) objRememberedValue2, composerStartRestartGroup, (i8 >> 6) & 14);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    z6 = z13;
                    modifier2 = modifier11;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    z6 = z4;
                }
                z7 = z3;
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    final BasicTooltipState basicTooltipState7 = basicTooltipState2;
                    final Modifier modifier12 = modifier2;
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: et0
                        public final Object invoke(Object obj, Object obj2) {
                            return BasicTooltipKt.i(popupPositionProvider, function2, basicTooltipState7, modifier12, z7, z6, function3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 196608;
            z4 = z2;
            if ((1572864 & i) == 0) {
                function5 = function3;
                if (composerStartRestartGroup.changedInstance(function5)) {
                    i9 = IOUtil.MiB;
                } else {
                    i9 = 524288;
                }
                i3 |= i9;
            } else {
                function5 = function3;
            }
            i8 = i3;
            if ((599187 & i8) != 599186) {
                z5 = true;
            } else {
                z5 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z5, i8 & 1)) {
                if (i10 != 0) {
                    modifier2 = Modifier.Companion;
                }
                if (i4 != 0) {
                    z3 = true;
                } else {
                    z3 = z3;
                }
                if (i6 != 0) {
                    z4 = true;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(196062260, i8, -1, "androidx.compose.foundation.BasicTooltipBox (BasicTooltip.kt:81)");
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                companion = Composer.Companion;
                if (objRememberedValue == companion.getEmpty()) {
                    objRememberedValue = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                coroutineScope = (CoroutineScope) objRememberedValue;
                Modifier.Companion companion12 = Modifier.Companion;
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy6 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.getTopStart(), false);
                int iHashCode6 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                CompositionLocalMap currentCompositionLocalMap6 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier6 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion12);
                ComposeUiNode.Companion companion13 = ComposeUiNode.Companion;
                constructor = companion13.getConstructor();
                if (composerStartRestartGroup.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor);
                } else {
                    composerStartRestartGroup.useNode();
                }
                Composer composer7 = Updater.constructor-impl(composerStartRestartGroup);
                Updater.set-impl(composer7, measurePolicyMaybeCachedBoxMeasurePolicy6, companion13.getSetMeasurePolicy());
                Updater.set-impl(composer7, currentCompositionLocalMap6, companion13.getSetResolvedCompositionLocals());
                Updater.init-impl(composer7, Integer.valueOf(iHashCode6), companion13.getSetCompositeKeyHash());
                Updater.reconcile-impl(composer7, companion13.getApplyOnDeactivatedNodeAssertion());
                Updater.set-impl(composer7, modifierMaterializeModifier6, companion13.getSetModifier());
                BoxScopeInstance boxScopeInstance6 = BoxScopeInstance.INSTANCE;
                if (basicTooltipState.isVisible()) {
                    composerStartRestartGroup.startReplaceGroup(1833353604);
                    int i116 = i8 >> 3;
                    z8 = false;
                    TooltipPopup(popupPositionProvider, basicTooltipState, coroutineScope, z3, function4, composerStartRestartGroup, (i8 & 14) | (i116 & 112) | (i116 & 7168) | ((i8 << 9) & 57344));
                    composerStartRestartGroup = composerStartRestartGroup;
                } else {
                    z8 = false;
                    composerStartRestartGroup.startReplaceGroup(1829588468);
                }
                composerStartRestartGroup.endReplaceGroup();
                int i117 = i8 >> 3;
                int i118 = ((i8 >> 15) & 14) | (i117 & 112) | (i117 & 896) | ((i8 >> 9) & 7168);
                basicTooltipState2 = basicTooltipState;
                Modifier modifier13 = modifier2;
                boolean z14 = z4;
                WrappedAnchor(z14, basicTooltipState2, modifier13, function5, composerStartRestartGroup, i118, 0);
                composerStartRestartGroup.endNode();
                if ((i8 & 896) == 256) {
                    z8 = true;
                }
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (z8) {
                    objRememberedValue2 = new Function1() { // from class: ct0
                        public final Object invoke(Object obj) {
                            return BasicTooltipKt.BasicTooltipBox$lambda$1$0(basicTooltipState2, (DisposableEffectScope) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    objRememberedValue2 = new Function1() { // from class: ct0
                        public final Object invoke(Object obj) {
                            return BasicTooltipKt.BasicTooltipBox$lambda$1$0(basicTooltipState2, (DisposableEffectScope) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                EffectsKt.DisposableEffect(basicTooltipState2, (Function1) objRememberedValue2, composerStartRestartGroup, (i8 >> 6) & 14);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                z6 = z14;
                modifier2 = modifier13;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                z6 = z4;
            }
            z7 = z3;
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                final BasicTooltipState basicTooltipState8 = basicTooltipState2;
                final Modifier modifier14 = modifier2;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: et0
                    public final Object invoke(Object obj, Object obj2) {
                        return BasicTooltipKt.i(popupPositionProvider, function2, basicTooltipState8, modifier14, z7, z6, function3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 24576;
        z3 = z;
        i6 = i2 & 32;
        if (i6 != 0) {
            if ((196608 & i) == 0) {
                z4 = z2;
                if (composerStartRestartGroup.changed(z4)) {
                    i7 = 131072;
                } else {
                    i7 = 65536;
                }
                i3 |= i7;
            }
            if ((1572864 & i) == 0) {
                function5 = function3;
                if (composerStartRestartGroup.changedInstance(function5)) {
                    i9 = IOUtil.MiB;
                } else {
                    i9 = 524288;
                }
                i3 |= i9;
            } else {
                function5 = function3;
            }
            i8 = i3;
            if ((599187 & i8) != 599186) {
                z5 = true;
            } else {
                z5 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z5, i8 & 1)) {
                if (i10 != 0) {
                    modifier2 = Modifier.Companion;
                }
                if (i4 != 0) {
                    z3 = true;
                } else {
                    z3 = z3;
                }
                if (i6 != 0) {
                    z4 = true;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(196062260, i8, -1, "androidx.compose.foundation.BasicTooltipBox (BasicTooltip.kt:81)");
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                companion = Composer.Companion;
                if (objRememberedValue == companion.getEmpty()) {
                    objRememberedValue = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                coroutineScope = (CoroutineScope) objRememberedValue;
                Modifier.Companion companion14 = Modifier.Companion;
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy7 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.getTopStart(), false);
                int iHashCode7 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                CompositionLocalMap currentCompositionLocalMap7 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier7 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion14);
                ComposeUiNode.Companion companion15 = ComposeUiNode.Companion;
                constructor = companion15.getConstructor();
                if (composerStartRestartGroup.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor);
                } else {
                    composerStartRestartGroup.useNode();
                }
                Composer composer8 = Updater.constructor-impl(composerStartRestartGroup);
                Updater.set-impl(composer8, measurePolicyMaybeCachedBoxMeasurePolicy7, companion15.getSetMeasurePolicy());
                Updater.set-impl(composer8, currentCompositionLocalMap7, companion15.getSetResolvedCompositionLocals());
                Updater.init-impl(composer8, Integer.valueOf(iHashCode7), companion15.getSetCompositeKeyHash());
                Updater.reconcile-impl(composer8, companion15.getApplyOnDeactivatedNodeAssertion());
                Updater.set-impl(composer8, modifierMaterializeModifier7, companion15.getSetModifier());
                BoxScopeInstance boxScopeInstance7 = BoxScopeInstance.INSTANCE;
                if (basicTooltipState.isVisible()) {
                    composerStartRestartGroup.startReplaceGroup(1833353604);
                    int i119 = i8 >> 3;
                    z8 = false;
                    TooltipPopup(popupPositionProvider, basicTooltipState, coroutineScope, z3, function4, composerStartRestartGroup, (i8 & 14) | (i119 & 112) | (i119 & 7168) | ((i8 << 9) & 57344));
                    composerStartRestartGroup = composerStartRestartGroup;
                } else {
                    z8 = false;
                    composerStartRestartGroup.startReplaceGroup(1829588468);
                }
                composerStartRestartGroup.endReplaceGroup();
                int i1110 = i8 >> 3;
                int i1111 = ((i8 >> 15) & 14) | (i1110 & 112) | (i1110 & 896) | ((i8 >> 9) & 7168);
                basicTooltipState2 = basicTooltipState;
                Modifier modifier15 = modifier2;
                boolean z15 = z4;
                WrappedAnchor(z15, basicTooltipState2, modifier15, function5, composerStartRestartGroup, i1111, 0);
                composerStartRestartGroup.endNode();
                if ((i8 & 896) == 256) {
                    z8 = true;
                }
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (z8) {
                    objRememberedValue2 = new Function1() { // from class: ct0
                        public final Object invoke(Object obj) {
                            return BasicTooltipKt.BasicTooltipBox$lambda$1$0(basicTooltipState2, (DisposableEffectScope) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    objRememberedValue2 = new Function1() { // from class: ct0
                        public final Object invoke(Object obj) {
                            return BasicTooltipKt.BasicTooltipBox$lambda$1$0(basicTooltipState2, (DisposableEffectScope) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                EffectsKt.DisposableEffect(basicTooltipState2, (Function1) objRememberedValue2, composerStartRestartGroup, (i8 >> 6) & 14);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                z6 = z15;
                modifier2 = modifier15;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                z6 = z4;
            }
            z7 = z3;
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                final BasicTooltipState basicTooltipState9 = basicTooltipState2;
                final Modifier modifier16 = modifier2;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: et0
                    public final Object invoke(Object obj, Object obj2) {
                        return BasicTooltipKt.i(popupPositionProvider, function2, basicTooltipState9, modifier16, z7, z6, function3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 196608;
        z4 = z2;
        if ((1572864 & i) == 0) {
            function5 = function3;
            if (composerStartRestartGroup.changedInstance(function5)) {
                i9 = IOUtil.MiB;
            } else {
                i9 = 524288;
            }
            i3 |= i9;
        } else {
            function5 = function3;
        }
        i8 = i3;
        if ((599187 & i8) != 599186) {
            z5 = true;
        } else {
            z5 = false;
        }
        if (composerStartRestartGroup.shouldExecute(z5, i8 & 1)) {
            if (i10 != 0) {
                modifier2 = Modifier.Companion;
            }
            if (i4 != 0) {
                z3 = true;
            } else {
                z3 = z3;
            }
            if (i6 != 0) {
                z4 = true;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(196062260, i8, -1, "androidx.compose.foundation.BasicTooltipBox (BasicTooltip.kt:81)");
            }
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            companion = Composer.Companion;
            if (objRememberedValue == companion.getEmpty()) {
                objRememberedValue = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            coroutineScope = (CoroutineScope) objRememberedValue;
            Modifier.Companion companion16 = Modifier.Companion;
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy8 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.getTopStart(), false);
            int iHashCode8 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
            CompositionLocalMap currentCompositionLocalMap8 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier8 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion16);
            ComposeUiNode.Companion companion17 = ComposeUiNode.Companion;
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
            Composer composer9 = Updater.constructor-impl(composerStartRestartGroup);
            Updater.set-impl(composer9, measurePolicyMaybeCachedBoxMeasurePolicy8, companion17.getSetMeasurePolicy());
            Updater.set-impl(composer9, currentCompositionLocalMap8, companion17.getSetResolvedCompositionLocals());
            Updater.init-impl(composer9, Integer.valueOf(iHashCode8), companion17.getSetCompositeKeyHash());
            Updater.reconcile-impl(composer9, companion17.getApplyOnDeactivatedNodeAssertion());
            Updater.set-impl(composer9, modifierMaterializeModifier8, companion17.getSetModifier());
            BoxScopeInstance boxScopeInstance8 = BoxScopeInstance.INSTANCE;
            if (basicTooltipState.isVisible()) {
                composerStartRestartGroup.startReplaceGroup(1833353604);
                int i1112 = i8 >> 3;
                z8 = false;
                TooltipPopup(popupPositionProvider, basicTooltipState, coroutineScope, z3, function4, composerStartRestartGroup, (i8 & 14) | (i1112 & 112) | (i1112 & 7168) | ((i8 << 9) & 57344));
                composerStartRestartGroup = composerStartRestartGroup;
            } else {
                z8 = false;
                composerStartRestartGroup.startReplaceGroup(1829588468);
            }
            composerStartRestartGroup.endReplaceGroup();
            int i1113 = i8 >> 3;
            int i1114 = ((i8 >> 15) & 14) | (i1113 & 112) | (i1113 & 896) | ((i8 >> 9) & 7168);
            basicTooltipState2 = basicTooltipState;
            Modifier modifier17 = modifier2;
            boolean z16 = z4;
            WrappedAnchor(z16, basicTooltipState2, modifier17, function5, composerStartRestartGroup, i1114, 0);
            composerStartRestartGroup.endNode();
            if ((i8 & 896) == 256) {
                z8 = true;
            }
            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (z8) {
                objRememberedValue2 = new Function1() { // from class: ct0
                    public final Object invoke(Object obj) {
                        return BasicTooltipKt.BasicTooltipBox$lambda$1$0(basicTooltipState2, (DisposableEffectScope) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            } else {
                objRememberedValue2 = new Function1() { // from class: ct0
                    public final Object invoke(Object obj) {
                        return BasicTooltipKt.BasicTooltipBox$lambda$1$0(basicTooltipState2, (DisposableEffectScope) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            EffectsKt.DisposableEffect(basicTooltipState2, (Function1) objRememberedValue2, composerStartRestartGroup, (i8 >> 6) & 14);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            z6 = z16;
            modifier2 = modifier17;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            z6 = z4;
        }
        z7 = z3;
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final BasicTooltipState basicTooltipState10 = basicTooltipState2;
            final Modifier modifier18 = modifier2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: et0
                public final Object invoke(Object obj, Object obj2) {
                    return BasicTooltipKt.i(popupPositionProvider, function2, basicTooltipState10, modifier18, z7, z6, function3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult BasicTooltipBox$lambda$1$0(final BasicTooltipState basicTooltipState, DisposableEffectScope disposableEffectScope) {
        return new DisposableEffectResult() { // from class: androidx.compose.foundation.BasicTooltipKt$BasicTooltipBox$lambda$1$0$$inlined$onDispose$1
            public void dispose() {
                basicTooltipState.onDispose();
            }
        };
    }

    public static final BasicTooltipState BasicTooltipState(boolean z, boolean z2, MutatorMutex mutatorMutex) {
        return new BasicTooltipStateImpl(z, z2, mutatorMutex);
    }

    public static /* synthetic */ BasicTooltipState BasicTooltipState$default(boolean z, boolean z2, MutatorMutex mutatorMutex, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        if ((i & 2) != 0) {
            z2 = true;
        }
        if ((i & 4) != 0) {
            mutatorMutex = BasicTooltipDefaults.INSTANCE.getGlobalMutatorMutex();
        }
        return BasicTooltipState(z, z2, mutatorMutex);
    }

    private static final void TooltipPopup(final PopupPositionProvider popupPositionProvider, final BasicTooltipState basicTooltipState, final CoroutineScope coroutineScope, final boolean z, final Function2<? super Composer, ? super Integer, Unit> function2, Composer composer, final int i) {
        PopupPositionProvider popupPositionProvider2;
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1882542163);
        if ((i & 6) == 0) {
            popupPositionProvider2 = popupPositionProvider;
            i2 = (composerStartRestartGroup.changed(popupPositionProvider2) ? 4 : 2) | i;
        } else {
            popupPositionProvider2 = popupPositionProvider;
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changed(basicTooltipState) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(coroutineScope) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i2 |= composerStartRestartGroup.changed(z) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function2) ? 16384 : 8192;
        }
        if (composerStartRestartGroup.shouldExecute((i2 & 9363) != 9362, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1882542163, i2, -1, "androidx.compose.foundation.TooltipPopup (BasicTooltip.kt:133)");
            }
            final String strDescription = BasicTooltipStrings.INSTANCE.description(composerStartRestartGroup, 6);
            boolean zChangedInstance = composerStartRestartGroup.changedInstance(coroutineScope) | ((i2 & 112) == 32);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.Companion.getEmpty()) {
                objRememberedValue = new Function0() { // from class: ss0
                    public final Object invoke() {
                        return BasicTooltipKt.TooltipPopup$lambda$0$0(basicTooltipState, coroutineScope);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            AndroidPopup_androidKt.Popup(popupPositionProvider2, (Function0) objRememberedValue, new PopupProperties(z, false, false, false, 14, (DefaultConstructorMarker) null), ComposableLambdaKt.rememberComposableLambda(916917707, true, new Function2() { // from class: us0
                public final Object invoke(Object obj, Object obj2) {
                    return BasicTooltipKt.g(strDescription, function2, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, composerStartRestartGroup, 54), composerStartRestartGroup, (i2 & 14) | 3072, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ws0
                public final Object invoke(Object obj, Object obj2) {
                    return BasicTooltipKt.f(popupPositionProvider, basicTooltipState, coroutineScope, z, function2, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit TooltipPopup$lambda$0$0(BasicTooltipState basicTooltipState, CoroutineScope coroutineScope) {
        if (basicTooltipState.isVisible()) {
            BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new BasicTooltipKt$TooltipPopup$1$1$1(basicTooltipState, null), 3, (Object) null);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit TooltipPopup$lambda$1$0$0(String str, SemanticsPropertyReceiver semanticsPropertyReceiver) {
        SemanticsPropertiesKt.setLiveRegion-hR3wRGc(semanticsPropertyReceiver, LiveRegionMode.Companion.getAssertive-0phEisY());
        SemanticsPropertiesKt.setPaneTitle(semanticsPropertyReceiver, str);
        return Unit.INSTANCE;
    }

    private static final void WrappedAnchor(final boolean z, final BasicTooltipState basicTooltipState, Modifier modifier, final Function2<? super Composer, ? super Integer, Unit> function2, Composer composer, final int i, final int i2) {
        int i3;
        Composer composerStartRestartGroup = composer.startRestartGroup(1381511093);
        if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(z) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i & 48) == 0) {
            i3 |= composerStartRestartGroup.changed(basicTooltipState) ? 32 : 16;
        }
        int i4 = i2 & 4;
        if (i4 != 0) {
            i3 |= 384;
        } else if ((i & 384) == 0) {
            i3 |= composerStartRestartGroup.changed(modifier) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function2) ? 2048 : 1024;
        }
        if (composerStartRestartGroup.shouldExecute((i3 & 1171) != 1170, i3 & 1)) {
            if (i4 != 0) {
                modifier = Modifier.Companion;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1381511093, i3, -1, "androidx.compose.foundation.WrappedAnchor (BasicTooltip.kt:112)");
            }
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.Companion.getEmpty()) {
                objRememberedValue = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            Modifier modifierAnchorSemantics = anchorSemantics(handleGestures(modifier, z, basicTooltipState), BasicTooltipStrings.INSTANCE.label(composerStartRestartGroup, 6), z, basicTooltipState, (CoroutineScope) objRememberedValue);
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.getTopStart(), false);
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierAnchorSemantics);
            ComposeUiNode.Companion companion = ComposeUiNode.Companion;
            Function0 constructor = companion.getConstructor();
            if (composerStartRestartGroup.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composer2 = Updater.constructor-impl(composerStartRestartGroup);
            Updater.set-impl(composer2, measurePolicyMaybeCachedBoxMeasurePolicy, companion.getSetMeasurePolicy());
            Updater.set-impl(composer2, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
            Updater.init-impl(composer2, Integer.valueOf(iHashCode), companion.getSetCompositeKeyHash());
            Updater.reconcile-impl(composer2, companion.getApplyOnDeactivatedNodeAssertion());
            Updater.set-impl(composer2, modifierMaterializeModifier, companion.getSetModifier());
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            function2.invoke(composerStartRestartGroup, Integer.valueOf((i3 >> 9) & 14));
            composerStartRestartGroup.endNode();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        final Modifier modifier2 = modifier;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ys0
                public final Object invoke(Object obj, Object obj2) {
                    return BasicTooltipKt.d(z, basicTooltipState, modifier2, function2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final Modifier anchorSemantics(Modifier modifier, final String str, boolean z, final BasicTooltipState basicTooltipState, final CoroutineScope coroutineScope) {
        return z ? SemanticsModifierKt.semantics(modifier, true, new Function1() { // from class: ft0
            public final Object invoke(Object obj) {
                return BasicTooltipKt.c(str, coroutineScope, basicTooltipState, (SemanticsPropertyReceiver) obj);
            }
        }) : modifier;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean anchorSemantics$lambda$0$0(CoroutineScope coroutineScope, BasicTooltipState basicTooltipState) {
        BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new BasicTooltipKt$anchorSemantics$1$1$1(basicTooltipState, null), 3, (Object) null);
        return true;
    }

    public static Unit c(String str, final CoroutineScope coroutineScope, final BasicTooltipState basicTooltipState, SemanticsPropertyReceiver semanticsPropertyReceiver) {
        SemanticsPropertiesKt.onLongClick(semanticsPropertyReceiver, str, new Function0() { // from class: at0
            public final Object invoke() {
                return Boolean.valueOf(BasicTooltipKt.anchorSemantics$lambda$0$0(coroutineScope, basicTooltipState));
            }
        });
        return Unit.INSTANCE;
    }

    public static Unit d(boolean z, BasicTooltipState basicTooltipState, Modifier modifier, Function2 function2, int i, int i2, Composer composer, int i3) {
        WrappedAnchor(z, basicTooltipState, modifier, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static Unit f(PopupPositionProvider popupPositionProvider, BasicTooltipState basicTooltipState, CoroutineScope coroutineScope, boolean z, Function2 function2, int i, Composer composer, int i2) {
        TooltipPopup(popupPositionProvider, basicTooltipState, coroutineScope, z, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static Unit g(final String str, Function2 function2, Composer composer, int i) {
        if (composer.shouldExecute((i & 3) != 2, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(916917707, i, -1, "androidx.compose.foundation.TooltipPopup.<anonymous> (BasicTooltip.kt:144)");
            }
            Modifier.Companion companion = Modifier.Companion;
            boolean zChanged = composer.changed(str);
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.Companion.getEmpty()) {
                objRememberedValue = new Function1() { // from class: qs0
                    public final Object invoke(Object obj) {
                        return BasicTooltipKt.TooltipPopup$lambda$1$0$0(str, (SemanticsPropertyReceiver) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(companion, false, (Function1) objRememberedValue, 1, (Object) null);
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.getTopStart(), false);
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer, modifierSemantics$default);
            ComposeUiNode.Companion companion2 = ComposeUiNode.Companion;
            Function0 constructor = companion2.getConstructor();
            if (composer.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
                composer.createNode(constructor);
            } else {
                composer.useNode();
            }
            Composer composer2 = Updater.constructor-impl(composer);
            Updater.set-impl(composer2, measurePolicyMaybeCachedBoxMeasurePolicy, companion2.getSetMeasurePolicy());
            Updater.set-impl(composer2, currentCompositionLocalMap, companion2.getSetResolvedCompositionLocals());
            Updater.init-impl(composer2, Integer.valueOf(iHashCode), companion2.getSetCompositeKeyHash());
            Updater.reconcile-impl(composer2, companion2.getApplyOnDeactivatedNodeAssertion());
            Updater.set-impl(composer2, modifierMaterializeModifier, companion2.getSetModifier());
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            function2.invoke(composer, 0);
            composer.endNode();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    private static final Modifier handleGestures(Modifier modifier, boolean z, final BasicTooltipState basicTooltipState) {
        return z ? SuspendingPointerInputFilterKt.pointerInput(SuspendingPointerInputFilterKt.pointerInput(modifier, basicTooltipState, new PointerInputEventHandler() { // from class: androidx.compose.foundation.BasicTooltipKt.handleGestures.1

            /* JADX INFO: renamed from: androidx.compose.foundation.BasicTooltipKt$handleGestures$1$1, reason: invalid class name and collision with other inner class name */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
            @DebugMetadata(c = "androidx.compose.foundation.BasicTooltipKt$handleGestures$1$1", f = "BasicTooltip.kt", i = {}, l = {162}, m = "invokeSuspend", n = {}, s = {}, v = 1)
            public static final class C00031 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ BasicTooltipState $state;
                final /* synthetic */ PointerInputScope $this_pointerInput;
                private /* synthetic */ Object L$0;
                int label;

                /* JADX INFO: renamed from: androidx.compose.foundation.BasicTooltipKt$handleGestures$1$1$1, reason: invalid class name and collision with other inner class name */
                @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
                @DebugMetadata(c = "androidx.compose.foundation.BasicTooltipKt$handleGestures$1$1$1", f = "BasicTooltip.kt", i = {0, 0, 1, 1}, l = {166, 169, 175}, m = "invokeSuspend", n = {"$this$awaitEachGesture", "pass", "$this$awaitEachGesture", "pass"}, s = {"L$0", "L$1", "L$0", "L$1"}, v = 1)
                public static final class C00041 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
                    final /* synthetic */ CoroutineScope $$this$coroutineScope;
                    final /* synthetic */ BasicTooltipState $state;
                    private /* synthetic */ Object L$0;
                    Object L$1;
                    int label;

                    /* JADX INFO: renamed from: androidx.compose.foundation.BasicTooltipKt$handleGestures$1$1$1$1, reason: invalid class name and collision with other inner class name */
                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
                    @DebugMetadata(c = "androidx.compose.foundation.BasicTooltipKt$handleGestures$1$1$1$1", f = "BasicTooltip.kt", i = {}, l = {172}, m = "invokeSuspend", n = {}, s = {}, v = 1)
                    public static final class C00051 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        final /* synthetic */ BasicTooltipState $state;
                        int label;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        public C00051(BasicTooltipState basicTooltipState, Continuation<? super C00051> continuation) {
                            super(2, continuation);
                            this.$state = basicTooltipState;
                        }

                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                            return new C00051(this.$state, continuation);
                        }

                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
                        }

                        public final Object invokeSuspend(Object obj) {
                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            int i = this.label;
                            if (i == 0) {
                                ResultKt.throwOnFailure(obj);
                                BasicTooltipState basicTooltipState = this.$state;
                                MutatePriority mutatePriority = MutatePriority.UserInput;
                                this.label = 1;
                                if (basicTooltipState.show(mutatePriority, this) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                            } else {
                                if (i != 1) {
                                    k2d.a("call to 'resume' before 'invoke' with coroutine");
                                    return null;
                                }
                                ResultKt.throwOnFailure(obj);
                            }
                            return Unit.INSTANCE;
                        }
                    }

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public C00041(CoroutineScope coroutineScope, BasicTooltipState basicTooltipState, Continuation<? super C00041> continuation) {
                        super(2, continuation);
                        this.$$this$coroutineScope = coroutineScope;
                        this.$state = basicTooltipState;
                    }

                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        C00041 c00041 = new C00041(this.$$this$coroutineScope, this.$state, continuation);
                        c00041.L$0 = obj;
                        return c00041;
                    }

                    public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
                        return create(awaitPointerEventScope, continuation).invokeSuspend(Unit.INSTANCE);
                    }

                    /* JADX WARN: Code duplicated, block: B:25:0x008c  */
                    /* JADX WARN: Code restructure failed: missing block: B:26:0x00a6, code lost:
                    
                        if (r0 == r6) goto L27;
                     */
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                    */
                    public final Object invokeSuspend(Object obj) {
                        Object objAwaitFirstDown$default;
                        AwaitPointerEventScope awaitPointerEventScope;
                        PointerEventPass pointerEventPass;
                        Object objWaitForLongPress;
                        Object objAwaitPointerEvent;
                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        int i = this.label;
                        if (i == 0) {
                            ResultKt.throwOnFailure(obj);
                            AwaitPointerEventScope awaitPointerEventScope2 = (AwaitPointerEventScope) this.L$0;
                            PointerEventPass pointerEventPass2 = PointerEventPass.Initial;
                            this.L$0 = awaitPointerEventScope2;
                            this.L$1 = pointerEventPass2;
                            this.label = 1;
                            objAwaitFirstDown$default = TapGestureDetectorKt.awaitFirstDown$default(awaitPointerEventScope2, false, pointerEventPass2, this, 1, null);
                            if (objAwaitFirstDown$default != coroutine_suspended) {
                                awaitPointerEventScope = awaitPointerEventScope2;
                                pointerEventPass = pointerEventPass2;
                            }
                            return coroutine_suspended;
                        }
                        if (i == 1) {
                            pointerEventPass = (PointerEventPass) this.L$1;
                            AwaitPointerEventScope awaitPointerEventScope3 = (AwaitPointerEventScope) this.L$0;
                            ResultKt.throwOnFailure(obj);
                            awaitPointerEventScope = awaitPointerEventScope3;
                            objAwaitFirstDown$default = obj;
                        } else {
                            if (i == 2) {
                                pointerEventPass = (PointerEventPass) this.L$1;
                                AwaitPointerEventScope awaitPointerEventScope4 = (AwaitPointerEventScope) this.L$0;
                                ResultKt.throwOnFailure(obj);
                                awaitPointerEventScope = awaitPointerEventScope4;
                                objWaitForLongPress = obj;
                                if (((LongPressResult) objWaitForLongPress) instanceof LongPressResult.Success) {
                                    BuildersKt.launch$default(this.$$this$coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new C00051(this.$state, null), 3, (Object) null);
                                    this.L$0 = null;
                                    this.L$1 = null;
                                    this.label = 3;
                                    objAwaitPointerEvent = awaitPointerEventScope.awaitPointerEvent(pointerEventPass, this);
                                }
                                return Unit.INSTANCE;
                            }
                            if (i != 3) {
                                k2d.a("call to 'resume' before 'invoke' with coroutine");
                                return null;
                            }
                            ResultKt.throwOnFailure(obj);
                            objAwaitPointerEvent = obj;
                        }
                        List changes = ((PointerEvent) objAwaitPointerEvent).getChanges();
                        int size = changes.size();
                        for (int i2 = 0; i2 < size; i2++) {
                            ((PointerInputChange) changes.get(i2)).consume();
                        }
                        return Unit.INSTANCE;
                        int i3 = ((PointerInputChange) objAwaitFirstDown$default).getType-T8wyACA();
                        PointerType.Companion companion = PointerType.Companion;
                        if (PointerType.equals-impl0(i3, companion.getTouch-T8wyACA()) || PointerType.equals-impl0(i3, companion.getStylus-T8wyACA())) {
                            this.L$0 = awaitPointerEventScope;
                            this.L$1 = pointerEventPass;
                            this.label = 2;
                            objWaitForLongPress = TapGestureDetectorKt.waitForLongPress(awaitPointerEventScope, pointerEventPass, this);
                            if (objWaitForLongPress != coroutine_suspended) {
                                if (((LongPressResult) objWaitForLongPress) instanceof LongPressResult.Success) {
                                    BuildersKt.launch$default(this.$$this$coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new C00051(this.$state, null), 3, (Object) null);
                                    this.L$0 = null;
                                    this.L$1 = null;
                                    this.label = 3;
                                    objAwaitPointerEvent = awaitPointerEventScope.awaitPointerEvent(pointerEventPass, this);
                                }
                            }
                            return coroutine_suspended;
                        }
                        return Unit.INSTANCE;
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C00031(PointerInputScope pointerInputScope, BasicTooltipState basicTooltipState, Continuation<? super C00031> continuation) {
                    super(2, continuation);
                    this.$this_pointerInput = pointerInputScope;
                    this.$state = basicTooltipState;
                }

                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    C00031 c00031 = new C00031(this.$this_pointerInput, this.$state, continuation);
                    c00031.L$0 = obj;
                    return c00031;
                }

                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                        PointerInputScope pointerInputScope = this.$this_pointerInput;
                        C00041 c00041 = new C00041(coroutineScope, this.$state, null);
                        this.label = 1;
                        if (ForEachGestureKt.awaitEachGesture(pointerInputScope, c00041, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            k2d.a("call to 'resume' before 'invoke' with coroutine");
                            return null;
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    return Unit.INSTANCE;
                }
            }

            public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
                Object objCoroutineScope = CoroutineScopeKt.coroutineScope(new C00031(pointerInputScope, basicTooltipState, null), continuation);
                return objCoroutineScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCoroutineScope : Unit.INSTANCE;
            }
        }), basicTooltipState, new PointerInputEventHandler() { // from class: androidx.compose.foundation.BasicTooltipKt.handleGestures.2

            /* JADX INFO: renamed from: androidx.compose.foundation.BasicTooltipKt$handleGestures$2$1, reason: invalid class name */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
            @DebugMetadata(c = "androidx.compose.foundation.BasicTooltipKt$handleGestures$2$1", f = "BasicTooltip.kt", i = {}, l = {186}, m = "invokeSuspend", n = {}, s = {}, v = 1)
            public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ BasicTooltipState $state;
                final /* synthetic */ PointerInputScope $this_pointerInput;
                private /* synthetic */ Object L$0;
                int label;

                /* JADX INFO: renamed from: androidx.compose.foundation.BasicTooltipKt$handleGestures$2$1$1, reason: invalid class name and collision with other inner class name */
                @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
                @DebugMetadata(c = "androidx.compose.foundation.BasicTooltipKt$handleGestures$2$1$1", f = "BasicTooltip.kt", i = {0, 0}, l = {190}, m = "invokeSuspend", n = {"$this$awaitPointerEventScope", "pass"}, s = {"L$0", "L$1"}, v = 1)
                public static final class C00061 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
                    final /* synthetic */ CoroutineScope $$this$coroutineScope;
                    final /* synthetic */ BasicTooltipState $state;
                    private /* synthetic */ Object L$0;
                    Object L$1;
                    int label;

                    /* JADX INFO: renamed from: androidx.compose.foundation.BasicTooltipKt$handleGestures$2$1$1$1, reason: invalid class name and collision with other inner class name */
                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
                    @DebugMetadata(c = "androidx.compose.foundation.BasicTooltipKt$handleGestures$2$1$1$1", f = "BasicTooltip.kt", i = {}, l = {195}, m = "invokeSuspend", n = {}, s = {}, v = 1)
                    public static final class C00071 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        final /* synthetic */ BasicTooltipState $state;
                        int label;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        public C00071(BasicTooltipState basicTooltipState, Continuation<? super C00071> continuation) {
                            super(2, continuation);
                            this.$state = basicTooltipState;
                        }

                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                            return new C00071(this.$state, continuation);
                        }

                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
                        }

                        public final Object invokeSuspend(Object obj) {
                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            int i = this.label;
                            if (i == 0) {
                                ResultKt.throwOnFailure(obj);
                                BasicTooltipState basicTooltipState = this.$state;
                                MutatePriority mutatePriority = MutatePriority.UserInput;
                                this.label = 1;
                                if (basicTooltipState.show(mutatePriority, this) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                            } else {
                                if (i != 1) {
                                    k2d.a("call to 'resume' before 'invoke' with coroutine");
                                    return null;
                                }
                                ResultKt.throwOnFailure(obj);
                            }
                            return Unit.INSTANCE;
                        }
                    }

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public C00061(CoroutineScope coroutineScope, BasicTooltipState basicTooltipState, Continuation<? super C00061> continuation) {
                        super(2, continuation);
                        this.$$this$coroutineScope = coroutineScope;
                        this.$state = basicTooltipState;
                    }

                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        C00061 c00061 = new C00061(this.$$this$coroutineScope, this.$state, continuation);
                        c00061.L$0 = obj;
                        return c00061;
                    }

                    public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
                        return create(awaitPointerEventScope, continuation).invokeSuspend(Unit.INSTANCE);
                    }

                    /* JADX WARN: Code duplicated, block: B:11:0x0034 A[RETURN] */
                    /* JADX WARN: Code duplicated, block: B:14:0x0052  */
                    /* JADX WARN: Code duplicated, block: B:16:0x0062  */
                    /* JADX WARN: Code duplicated, block: B:17:0x0073  */
                    /* JADX WARN: Code duplicated, block: B:19:0x007d  */
                    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:10:0x0032 -> B:12:0x0035). Please report as a decompilation issue!!! */
                    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
                        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached at block B:0:?
                        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
                        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
                        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
                        */
                    public final java.lang.Object invokeSuspend(java.lang.Object r14) {
                        /*
                            r13 = this;
                            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                            int r1 = r13.label
                            r2 = 0
                            r3 = 1
                            if (r1 == 0) goto L1e
                            if (r1 != r3) goto L18
                            java.lang.Object r1 = r13.L$1
                            androidx.compose.ui.input.pointer.PointerEventPass r1 = (androidx.compose.ui.input.pointer.PointerEventPass) r1
                            java.lang.Object r4 = r13.L$0
                            androidx.compose.ui.input.pointer.AwaitPointerEventScope r4 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r4
                            kotlin.ResultKt.throwOnFailure(r14)
                            goto L35
                        L18:
                            java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
                            k2d.a(r13)
                            return r2
                        L1e:
                            kotlin.ResultKt.throwOnFailure(r14)
                            java.lang.Object r14 = r13.L$0
                            androidx.compose.ui.input.pointer.AwaitPointerEventScope r14 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r14
                            androidx.compose.ui.input.pointer.PointerEventPass r1 = androidx.compose.ui.input.pointer.PointerEventPass.Main
                            r4 = r14
                        L28:
                            r13.L$0 = r4
                            r13.L$1 = r1
                            r13.label = r3
                            java.lang.Object r14 = r4.awaitPointerEvent(r1, r13)
                            if (r14 != r0) goto L35
                            return r0
                        L35:
                            androidx.compose.ui.input.pointer.PointerEvent r14 = (androidx.compose.ui.input.pointer.PointerEvent) r14
                            java.util.List r5 = r14.getChanges()
                            r6 = 0
                            java.lang.Object r5 = r5.get(r6)
                            androidx.compose.ui.input.pointer.PointerInputChange r5 = (androidx.compose.ui.input.pointer.PointerInputChange) r5
                            int r5 = r5.getType-T8wyACA()
                            androidx.compose.ui.input.pointer.PointerType$Companion r6 = androidx.compose.ui.input.pointer.PointerType.Companion
                            int r6 = r6.getMouse-T8wyACA()
                            boolean r5 = androidx.compose.ui.input.pointer.PointerType.equals-impl0(r5, r6)
                            if (r5 == 0) goto L28
                            int r14 = r14.getType-7fucELk()
                            androidx.compose.ui.input.pointer.PointerEventType$Companion r5 = androidx.compose.ui.input.pointer.PointerEventType.Companion
                            int r6 = r5.getEnter-7fucELk()
                            boolean r6 = androidx.compose.ui.input.pointer.PointerEventType.equals-impl0(r14, r6)
                            if (r6 == 0) goto L73
                            kotlinx.coroutines.CoroutineScope r7 = r13.$$this$coroutineScope
                            androidx.compose.foundation.BasicTooltipKt$handleGestures$2$1$1$1 r10 = new androidx.compose.foundation.BasicTooltipKt$handleGestures$2$1$1$1
                            androidx.compose.foundation.BasicTooltipState r14 = r13.$state
                            r10.<init>(r14, r2)
                            r11 = 3
                            r12 = 0
                            r8 = 0
                            r9 = 0
                            kotlinx.coroutines.BuildersKt.launch$default(r7, r8, r9, r10, r11, r12)
                            goto L28
                        L73:
                            int r5 = r5.getExit-7fucELk()
                            boolean r14 = androidx.compose.ui.input.pointer.PointerEventType.equals-impl0(r14, r5)
                            if (r14 == 0) goto L28
                            androidx.compose.foundation.BasicTooltipState r14 = r13.$state
                            r14.dismiss()
                            goto L28
                        */
                        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.BasicTooltipKt.AnonymousClass2.AnonymousClass1.C00061.invokeSuspend(java.lang.Object):java.lang.Object");
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass1(PointerInputScope pointerInputScope, BasicTooltipState basicTooltipState, Continuation<? super AnonymousClass1> continuation) {
                    super(2, continuation);
                    this.$this_pointerInput = pointerInputScope;
                    this.$state = basicTooltipState;
                }

                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$this_pointerInput, this.$state, continuation);
                    anonymousClass1.L$0 = obj;
                    return anonymousClass1;
                }

                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                        PointerInputScope pointerInputScope = this.$this_pointerInput;
                        C00061 c00061 = new C00061(coroutineScope, this.$state, null);
                        this.label = 1;
                        if (pointerInputScope.awaitPointerEventScope(c00061, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            k2d.a("call to 'resume' before 'invoke' with coroutine");
                            return null;
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    return Unit.INSTANCE;
                }
            }

            public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
                Object objCoroutineScope = CoroutineScopeKt.coroutineScope(new AnonymousClass1(pointerInputScope, basicTooltipState, null), continuation);
                return objCoroutineScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCoroutineScope : Unit.INSTANCE;
            }
        }) : modifier;
    }

    public static Unit i(PopupPositionProvider popupPositionProvider, Function2 function2, BasicTooltipState basicTooltipState, Modifier modifier, boolean z, boolean z2, Function2 function3, int i, int i2, Composer composer, int i3) {
        BasicTooltipBox(popupPositionProvider, function2, basicTooltipState, modifier, z, z2, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static final BasicTooltipState rememberBasicTooltipState(boolean z, boolean z2, MutatorMutex mutatorMutex, Composer composer, int i, int i2) {
        if ((i2 & 1) != 0) {
            z = false;
        }
        if ((i2 & 2) != 0) {
            z2 = true;
        }
        if ((i2 & 4) != 0) {
            mutatorMutex = BasicTooltipDefaults.INSTANCE.getGlobalMutatorMutex();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1123859613, i, -1, "androidx.compose.foundation.rememberBasicTooltipState (BasicTooltip.kt:245)");
        }
        boolean z3 = ((((i & 112) ^ 48) > 32 && composer.changed(z2)) || (i & 48) == 32) | ((((i & 896) ^ 384) > 256 && composer.changed(mutatorMutex)) || (i & 384) == 256);
        Object objRememberedValue = composer.rememberedValue();
        if (z3 || objRememberedValue == Composer.Companion.getEmpty()) {
            objRememberedValue = new BasicTooltipStateImpl(z, z2, mutatorMutex);
            composer.updateRememberedValue(objRememberedValue);
        }
        BasicTooltipStateImpl basicTooltipStateImpl = (BasicTooltipStateImpl) objRememberedValue;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return basicTooltipStateImpl;
    }
}
