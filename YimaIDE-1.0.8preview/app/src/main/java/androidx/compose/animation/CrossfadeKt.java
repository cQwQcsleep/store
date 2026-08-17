package androidx.compose.animation;

import androidx.collection.MutableScatterMap;
import androidx.collection.ScatterMapKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.Transition;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.snapshots.SnapshotStateList;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import java.util.Iterator;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000@\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\u001aX\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\u0017\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\f¢\u0006\u0002\b\rH\u0007¢\u0006\u0002\u0010\u000e\u001aN\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0017\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\f¢\u0006\u0002\b\rH\u0007¢\u0006\u0002\u0010\u000f\u001a\u0086\u0001\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00102\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072%\b\u0002\u0010\u0011\u001a\u001f\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00140\f2&\u0010\u000b\u001a\"\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0003\u0012\u0004\u0012\u00020\u00010\f¢\u0006\u0002\b\rH\u0007¢\u0006\u0002\u0010\u0015¨\u0006\u0016²\u0006\n\u0010\u0017\u001a\u00020\bX\u008a\u0084\u0002"}, d2 = {"Crossfade", "", "T", "targetState", "modifier", "Landroidx/compose/ui/Modifier;", "animationSpec", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "", "label", "", "content", "Lkotlin/Function1;", "Landroidx/compose/runtime/Composable;", "(Ljava/lang/Object;Landroidx/compose/ui/Modifier;Landroidx/compose/animation/core/FiniteAnimationSpec;Ljava/lang/String;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "(Ljava/lang/Object;Landroidx/compose/ui/Modifier;Landroidx/compose/animation/core/FiniteAnimationSpec;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "Landroidx/compose/animation/core/Transition;", "contentKey", "Lkotlin/ParameterName;", "name", "", "(Landroidx/compose/animation/core/Transition;Landroidx/compose/ui/Modifier;Landroidx/compose/animation/core/FiniteAnimationSpec;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "animation", "alpha"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class CrossfadeKt {
    /* JADX WARN: Code duplicated, block: B:100:0x0173  */
    /* JADX WARN: Code duplicated, block: B:103:0x018a A[LOOP:0: B:98:0x016d->B:103:0x018a, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:106:0x0190  */
    /* JADX WARN: Code duplicated, block: B:107:0x0198  */
    /* JADX WARN: Code duplicated, block: B:110:0x01a9 A[LOOP:1: B:109:0x01a7->B:110:0x01a9, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:112:0x01c5  */
    /* JADX WARN: Code duplicated, block: B:115:0x01f0  */
    /* JADX WARN: Code duplicated, block: B:118:0x01fc  */
    /* JADX WARN: Code duplicated, block: B:119:0x0200  */
    /* JADX WARN: Code duplicated, block: B:122:0x023d  */
    /* JADX WARN: Code duplicated, block: B:124:0x0253  */
    /* JADX WARN: Code duplicated, block: B:125:0x025f  */
    /* JADX WARN: Code duplicated, block: B:129:0x0283  */
    /* JADX WARN: Code duplicated, block: B:131:0x0289  */
    /* JADX WARN: Code duplicated, block: B:134:0x0293  */
    /* JADX WARN: Code duplicated, block: B:136:0x018d A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:137:0x018e A[EDGE_INSN: B:137:0x018e->B:105:0x018e BREAK  A[LOOP:0: B:98:0x016d->B:103:0x018a], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:142:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:23:0x003e  */
    /* JADX WARN: Code duplicated, block: B:25:0x0043  */
    /* JADX WARN: Code duplicated, block: B:27:0x0047  */
    /* JADX WARN: Code duplicated, block: B:29:0x004f  */
    /* JADX WARN: Code duplicated, block: B:30:0x0052  */
    /* JADX WARN: Code duplicated, block: B:34:0x0059  */
    /* JADX WARN: Code duplicated, block: B:36:0x005e  */
    /* JADX WARN: Code duplicated, block: B:38:0x0062  */
    /* JADX WARN: Code duplicated, block: B:40:0x006a  */
    /* JADX WARN: Code duplicated, block: B:41:0x006d  */
    /* JADX WARN: Code duplicated, block: B:45:0x0074  */
    /* JADX WARN: Code duplicated, block: B:47:0x007a  */
    /* JADX WARN: Code duplicated, block: B:48:0x007d  */
    /* JADX WARN: Code duplicated, block: B:52:0x0088  */
    /* JADX WARN: Code duplicated, block: B:53:0x008a  */
    /* JADX WARN: Code duplicated, block: B:56:0x0093 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:57:0x0095  */
    /* JADX WARN: Code duplicated, block: B:59:0x009a  */
    /* JADX WARN: Code duplicated, block: B:61:0x00a3  */
    /* JADX WARN: Code duplicated, block: B:63:0x00af  */
    /* JADX WARN: Code duplicated, block: B:67:0x00be  */
    /* JADX WARN: Code duplicated, block: B:70:0x00cf  */
    /* JADX WARN: Code duplicated, block: B:73:0x00e9  */
    /* JADX WARN: Code duplicated, block: B:76:0x0103  */
    /* JADX WARN: Code duplicated, block: B:78:0x010f  */
    /* JADX WARN: Code duplicated, block: B:83:0x0125  */
    /* JADX WARN: Code duplicated, block: B:85:0x0130  */
    /* JADX WARN: Code duplicated, block: B:86:0x0132  */
    /* JADX WARN: Code duplicated, block: B:89:0x0139  */
    /* JADX WARN: Code duplicated, block: B:91:0x013f  */
    /* JADX WARN: Code duplicated, block: B:94:0x0154  */
    /* JADX WARN: Code duplicated, block: B:97:0x0162  */
    public static final <T> void Crossfade(final Transition<T> transition, Modifier modifier, FiniteAnimationSpec<Float> finiteAnimationSpec, Function1<? super T, ? extends Object> function1, final Function3<? super T, ? super Composer, ? super Integer, Unit> function3, Composer composer, final int i, final int i2) {
        Modifier modifier2;
        int i3;
        FiniteAnimationSpec<Float> finiteAnimationSpecTween$default;
        int i4;
        int i5;
        Function1<? super T, ? extends Object> function2;
        int i6;
        boolean z;
        final FiniteAnimationSpec<Float> finiteAnimationSpec2;
        final Function1<? super T, ? extends Object> function4;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Object objRememberedValue;
        Composer.Companion companion;
        Object obj;
        SnapshotStateList snapshotStateList;
        Object objRememberedValue2;
        MutableScatterMap mutableScatterMap;
        Function0 constructor;
        int size;
        int i7;
        Function2 function5;
        Iterator it;
        int i8;
        int size2;
        int i9;
        boolean z2;
        Object objRememberedValue3;
        Object objRememberedValue4;
        int i10;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1877370462);
        int i11 = (i & 6) == 0 ? (composerStartRestartGroup.changed(transition) ? 4 : 2) | i : i;
        int i12 = i2 & 1;
        if (i12 == 0) {
            if ((i & 48) == 0) {
                modifier2 = modifier;
                i11 |= composerStartRestartGroup.changed(modifier2) ? 32 : 16;
            }
            i3 = i2 & 2;
            if (i3 != 0) {
                if ((i & 384) == 0) {
                    finiteAnimationSpecTween$default = finiteAnimationSpec;
                    if (composerStartRestartGroup.changedInstance(finiteAnimationSpecTween$default)) {
                        i4 = 256;
                    } else {
                        i4 = 128;
                    }
                    i11 |= i4;
                }
                i5 = i2 & 4;
                if (i5 != 0) {
                    if ((i & 3072) == 0) {
                        function2 = function1;
                        if (composerStartRestartGroup.changedInstance(function2)) {
                            i6 = 2048;
                        } else {
                            i6 = 1024;
                        }
                        i11 |= i6;
                    }
                    if ((i & 24576) == 0) {
                        if (composerStartRestartGroup.changedInstance(function3)) {
                            i10 = 16384;
                        } else {
                            i10 = 8192;
                        }
                        i11 |= i10;
                    }
                    if ((i11 & 9363) != 9362) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i11 & 1)) {
                        if (i12 != 0) {
                            modifier2 = Modifier.Companion;
                        }
                        if (i3 != 0) {
                            finiteAnimationSpecTween$default = AnimationSpecKt.tween$default(0, 0, null, 7, null);
                        }
                        if (i5 != 0) {
                            objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue4 == Composer.Companion.getEmpty()) {
                                objRememberedValue4 = new Function1<T, T>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$3$1
                                    public final T invoke(T t) {
                                        return t;
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                            }
                            function2 = (Function1) objRememberedValue4;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1877370462, i11, -1, "androidx.compose.animation.Crossfade (Crossfade.kt:102)");
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        companion = Composer.Companion;
                        obj = objRememberedValue;
                        if (objRememberedValue == companion.getEmpty()) {
                            SnapshotStateList snapshotStateListMutableStateListOf = SnapshotStateKt.mutableStateListOf();
                            snapshotStateListMutableStateListOf.add(transition.getCurrentState());
                            composerStartRestartGroup.updateRememberedValue(snapshotStateListMutableStateListOf);
                            obj = snapshotStateListMutableStateListOf;
                        }
                        snapshotStateList = (SnapshotStateList) obj;
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue2 == companion.getEmpty()) {
                            objRememberedValue2 = ScatterMapKt.mutableScatterMapOf();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        mutableScatterMap = (MutableScatterMap) objRememberedValue2;
                        if (Intrinsics.areEqual(transition.getCurrentState(), transition.getTargetState())) {
                            composerStartRestartGroup.startReplaceGroup(321145192);
                            if (snapshotStateList.size() == 1 || !Intrinsics.areEqual(snapshotStateList.get(0), transition.getTargetState())) {
                                composerStartRestartGroup.startReplaceGroup(321279546);
                                if ((i11 & 14) == 4) {
                                    z2 = true;
                                } else {
                                    z2 = false;
                                }
                                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                                if (z2 || objRememberedValue3 == companion.getEmpty()) {
                                    objRememberedValue3 = new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        {
                                            super(1);
                                        }

                                        /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
                                        public final Boolean m95invoke(T t) {
                                            return Boolean.valueOf(!Intrinsics.areEqual(t, transition.getTargetState()));
                                        }
                                    };
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                                }
                                CollectionsKt.removeAll(snapshotStateList, (Function1) objRememberedValue3);
                                mutableScatterMap.clear();
                            } else {
                                composerStartRestartGroup.startReplaceGroup(316295552);
                            }
                            composerStartRestartGroup.endReplaceGroup();
                        } else {
                            composerStartRestartGroup.startReplaceGroup(316295552);
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        if (mutableScatterMap.contains(transition.getTargetState())) {
                            composerStartRestartGroup.startReplaceGroup(316295552);
                        } else {
                            composerStartRestartGroup.startReplaceGroup(321536443);
                            it = snapshotStateList.iterator();
                            i8 = 0;
                            while (true) {
                                if (it.hasNext()) {
                                    i8 = -1;
                                    break;
                                } else if (Intrinsics.areEqual(function2.invoke(it.next()), function2.invoke(transition.getTargetState()))) {
                                    break;
                                } else {
                                    i8++;
                                }
                            }
                            if (i8 == -1) {
                                snapshotStateList.add(transition.getTargetState());
                            } else {
                                snapshotStateList.set(i8, transition.getTargetState());
                            }
                            mutableScatterMap.clear();
                            size2 = snapshotStateList.size();
                            for (i9 = 0; i9 < size2; i9++) {
                                Object obj2 = snapshotStateList.get(i9);
                                mutableScatterMap.set(obj2, ComposableLambdaKt.rememberComposableLambda(-934471669, true, new CrossfadeKt$Crossfade$5$1(transition, finiteAnimationSpecTween$default, obj2, function3), composerStartRestartGroup, 54));
                            }
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.getTopStart(), false);
                        int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                        CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier2);
                        ComposeUiNode.Companion companion2 = ComposeUiNode.Companion;
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
                        Composer composer2 = Updater.constructor-impl(composerStartRestartGroup);
                        Updater.set-impl(composer2, measurePolicyMaybeCachedBoxMeasurePolicy, companion2.getSetMeasurePolicy());
                        Updater.set-impl(composer2, currentCompositionLocalMap, companion2.getSetResolvedCompositionLocals());
                        Updater.init-impl(composer2, Integer.valueOf(iHashCode), companion2.getSetCompositeKeyHash());
                        Updater.reconcile-impl(composer2, companion2.getApplyOnDeactivatedNodeAssertion());
                        Updater.set-impl(composer2, modifierMaterializeModifier, companion2.getSetModifier());
                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                        composerStartRestartGroup.startReplaceGroup(-1312707512);
                        size = snapshotStateList.size();
                        for (i7 = 0; i7 < size; i7++) {
                            Object obj3 = snapshotStateList.get(i7);
                            composerStartRestartGroup.startMovableGroup(1171574969, function2.invoke(obj3));
                            function5 = (Function2) mutableScatterMap.get(obj3);
                            if (function5 == null) {
                                composerStartRestartGroup.startReplaceGroup(1959122128);
                                composerStartRestartGroup.endReplaceGroup();
                            } else {
                                composerStartRestartGroup.startReplaceGroup(1171576145);
                                function5.invoke(composerStartRestartGroup, 0);
                                composerStartRestartGroup.endReplaceGroup();
                            }
                            composerStartRestartGroup.endMovableGroup();
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        composerStartRestartGroup.endNode();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                    }
                    finiteAnimationSpec2 = finiteAnimationSpecTween$default;
                    function4 = function2;
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        final Modifier modifier3 = modifier2;
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.CrossfadeKt.Crossfade.7
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            public final void invoke(Composer composer3, int i13) {
                                CrossfadeKt.Crossfade(transition, modifier3, finiteAnimationSpec2, function4, function3, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj4, Object obj5) {
                                invoke((Composer) obj4, ((Number) obj5).intValue());
                                return Unit.INSTANCE;
                            }
                        });
                    }
                }
                i11 |= 3072;
                function2 = function1;
                if ((i & 24576) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i10 = 16384;
                    } else {
                        i10 = 8192;
                    }
                    i11 |= i10;
                }
                if ((i11 & 9363) != 9362) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i11 & 1)) {
                    if (i12 != 0) {
                        modifier2 = Modifier.Companion;
                    }
                    if (i3 != 0) {
                        finiteAnimationSpecTween$default = AnimationSpecKt.tween$default(0, 0, null, 7, null);
                    }
                    if (i5 != 0) {
                        objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue4 == Composer.Companion.getEmpty()) {
                            objRememberedValue4 = new Function1<T, T>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$3$1
                                public final T invoke(T t) {
                                    return t;
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                        }
                        function2 = (Function1) objRememberedValue4;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1877370462, i11, -1, "androidx.compose.animation.Crossfade (Crossfade.kt:102)");
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    companion = Composer.Companion;
                    obj = objRememberedValue;
                    if (objRememberedValue == companion.getEmpty()) {
                        SnapshotStateList snapshotStateListMutableStateListOf2 = SnapshotStateKt.mutableStateListOf();
                        snapshotStateListMutableStateListOf2.add(transition.getCurrentState());
                        composerStartRestartGroup.updateRememberedValue(snapshotStateListMutableStateListOf2);
                        obj = snapshotStateListMutableStateListOf2;
                    }
                    snapshotStateList = (SnapshotStateList) obj;
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue2 == companion.getEmpty()) {
                        objRememberedValue2 = ScatterMapKt.mutableScatterMapOf();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    mutableScatterMap = (MutableScatterMap) objRememberedValue2;
                    if (Intrinsics.areEqual(transition.getCurrentState(), transition.getTargetState())) {
                        composerStartRestartGroup.startReplaceGroup(321145192);
                        if (snapshotStateList.size() == 1) {
                            composerStartRestartGroup.startReplaceGroup(321279546);
                            if ((i11 & 14) == 4) {
                                z2 = true;
                            } else {
                                z2 = false;
                            }
                            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                            if (z2) {
                                objRememberedValue3 = new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
                                    public final Boolean m95invoke(T t) {
                                        return Boolean.valueOf(!Intrinsics.areEqual(t, transition.getTargetState()));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                            } else {
                                objRememberedValue3 = new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
                                    public final Boolean m95invoke(T t) {
                                        return Boolean.valueOf(!Intrinsics.areEqual(t, transition.getTargetState()));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                            }
                            CollectionsKt.removeAll(snapshotStateList, (Function1) objRememberedValue3);
                            mutableScatterMap.clear();
                        } else {
                            composerStartRestartGroup.startReplaceGroup(321279546);
                            if ((i11 & 14) == 4) {
                                z2 = true;
                            } else {
                                z2 = false;
                            }
                            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                            if (z2) {
                                objRememberedValue3 = new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
                                    public final Boolean m95invoke(T t) {
                                        return Boolean.valueOf(!Intrinsics.areEqual(t, transition.getTargetState()));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                            } else {
                                objRememberedValue3 = new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
                                    public final Boolean m95invoke(T t) {
                                        return Boolean.valueOf(!Intrinsics.areEqual(t, transition.getTargetState()));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                            }
                            CollectionsKt.removeAll(snapshotStateList, (Function1) objRememberedValue3);
                            mutableScatterMap.clear();
                        }
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(316295552);
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    if (mutableScatterMap.contains(transition.getTargetState())) {
                        composerStartRestartGroup.startReplaceGroup(321536443);
                        it = snapshotStateList.iterator();
                        i8 = 0;
                        while (true) {
                            if (it.hasNext()) {
                                i8 = -1;
                                break;
                            } else {
                                if (Intrinsics.areEqual(function2.invoke(it.next()), function2.invoke(transition.getTargetState()))) {
                                    break;
                                    break;
                                }
                                i8++;
                            }
                        }
                        if (i8 == -1) {
                            snapshotStateList.add(transition.getTargetState());
                        } else {
                            snapshotStateList.set(i8, transition.getTargetState());
                        }
                        mutableScatterMap.clear();
                        size2 = snapshotStateList.size();
                        while (i9 < size2) {
                            Object obj4 = snapshotStateList.get(i9);
                            mutableScatterMap.set(obj4, ComposableLambdaKt.rememberComposableLambda(-934471669, true, new CrossfadeKt$Crossfade$5$1(transition, finiteAnimationSpecTween$default, obj4, function3), composerStartRestartGroup, 54));
                        }
                    } else {
                        composerStartRestartGroup.startReplaceGroup(316295552);
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.getTopStart(), false);
                    int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                    CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier2);
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
                    Composer composer3 = Updater.constructor-impl(composerStartRestartGroup);
                    Updater.set-impl(composer3, measurePolicyMaybeCachedBoxMeasurePolicy2, companion3.getSetMeasurePolicy());
                    Updater.set-impl(composer3, currentCompositionLocalMap2, companion3.getSetResolvedCompositionLocals());
                    Updater.init-impl(composer3, Integer.valueOf(iHashCode2), companion3.getSetCompositeKeyHash());
                    Updater.reconcile-impl(composer3, companion3.getApplyOnDeactivatedNodeAssertion());
                    Updater.set-impl(composer3, modifierMaterializeModifier2, companion3.getSetModifier());
                    BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                    composerStartRestartGroup.startReplaceGroup(-1312707512);
                    size = snapshotStateList.size();
                    while (i7 < size) {
                        Object obj5 = snapshotStateList.get(i7);
                        composerStartRestartGroup.startMovableGroup(1171574969, function2.invoke(obj5));
                        function5 = (Function2) mutableScatterMap.get(obj5);
                        if (function5 == null) {
                            composerStartRestartGroup.startReplaceGroup(1959122128);
                            composerStartRestartGroup.endReplaceGroup();
                        } else {
                            composerStartRestartGroup.startReplaceGroup(1171576145);
                            function5.invoke(composerStartRestartGroup, 0);
                            composerStartRestartGroup.endReplaceGroup();
                        }
                        composerStartRestartGroup.endMovableGroup();
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    composerStartRestartGroup.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                }
                finiteAnimationSpec2 = finiteAnimationSpecTween$default;
                function4 = function2;
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    final Modifier modifier4 = modifier2;
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.CrossfadeKt.Crossfade.7
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        public final void invoke(Composer composer4, int i13) {
                            CrossfadeKt.Crossfade(transition, modifier4, finiteAnimationSpec2, function4, function3, composer4, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj6, Object obj7) {
                            invoke((Composer) obj6, ((Number) obj7).intValue());
                            return Unit.INSTANCE;
                        }
                    });
                }
            }
            i11 |= 384;
            finiteAnimationSpecTween$default = finiteAnimationSpec;
            i5 = i2 & 4;
            if (i5 != 0) {
                if ((i & 3072) == 0) {
                    function2 = function1;
                    if (composerStartRestartGroup.changedInstance(function2)) {
                        i6 = 2048;
                    } else {
                        i6 = 1024;
                    }
                    i11 |= i6;
                }
                if ((i & 24576) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i10 = 16384;
                    } else {
                        i10 = 8192;
                    }
                    i11 |= i10;
                }
                if ((i11 & 9363) != 9362) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i11 & 1)) {
                    if (i12 != 0) {
                        modifier2 = Modifier.Companion;
                    }
                    if (i3 != 0) {
                        finiteAnimationSpecTween$default = AnimationSpecKt.tween$default(0, 0, null, 7, null);
                    }
                    if (i5 != 0) {
                        objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue4 == Composer.Companion.getEmpty()) {
                            objRememberedValue4 = new Function1<T, T>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$3$1
                                public final T invoke(T t) {
                                    return t;
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                        }
                        function2 = (Function1) objRememberedValue4;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1877370462, i11, -1, "androidx.compose.animation.Crossfade (Crossfade.kt:102)");
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    companion = Composer.Companion;
                    obj = objRememberedValue;
                    if (objRememberedValue == companion.getEmpty()) {
                        SnapshotStateList snapshotStateListMutableStateListOf3 = SnapshotStateKt.mutableStateListOf();
                        snapshotStateListMutableStateListOf3.add(transition.getCurrentState());
                        composerStartRestartGroup.updateRememberedValue(snapshotStateListMutableStateListOf3);
                        obj = snapshotStateListMutableStateListOf3;
                    }
                    snapshotStateList = (SnapshotStateList) obj;
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue2 == companion.getEmpty()) {
                        objRememberedValue2 = ScatterMapKt.mutableScatterMapOf();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    mutableScatterMap = (MutableScatterMap) objRememberedValue2;
                    if (Intrinsics.areEqual(transition.getCurrentState(), transition.getTargetState())) {
                        composerStartRestartGroup.startReplaceGroup(321145192);
                        if (snapshotStateList.size() == 1) {
                            composerStartRestartGroup.startReplaceGroup(321279546);
                            if ((i11 & 14) == 4) {
                                z2 = true;
                            } else {
                                z2 = false;
                            }
                            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                            if (z2) {
                                objRememberedValue3 = new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
                                    public final Boolean m95invoke(T t) {
                                        return Boolean.valueOf(!Intrinsics.areEqual(t, transition.getTargetState()));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                            } else {
                                objRememberedValue3 = new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
                                    public final Boolean m95invoke(T t) {
                                        return Boolean.valueOf(!Intrinsics.areEqual(t, transition.getTargetState()));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                            }
                            CollectionsKt.removeAll(snapshotStateList, (Function1) objRememberedValue3);
                            mutableScatterMap.clear();
                        } else {
                            composerStartRestartGroup.startReplaceGroup(321279546);
                            if ((i11 & 14) == 4) {
                                z2 = true;
                            } else {
                                z2 = false;
                            }
                            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                            if (z2) {
                                objRememberedValue3 = new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
                                    public final Boolean m95invoke(T t) {
                                        return Boolean.valueOf(!Intrinsics.areEqual(t, transition.getTargetState()));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                            } else {
                                objRememberedValue3 = new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
                                    public final Boolean m95invoke(T t) {
                                        return Boolean.valueOf(!Intrinsics.areEqual(t, transition.getTargetState()));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                            }
                            CollectionsKt.removeAll(snapshotStateList, (Function1) objRememberedValue3);
                            mutableScatterMap.clear();
                        }
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(316295552);
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    if (mutableScatterMap.contains(transition.getTargetState())) {
                        composerStartRestartGroup.startReplaceGroup(321536443);
                        it = snapshotStateList.iterator();
                        i8 = 0;
                        while (true) {
                            if (it.hasNext()) {
                                i8 = -1;
                                break;
                            } else {
                                if (Intrinsics.areEqual(function2.invoke(it.next()), function2.invoke(transition.getTargetState()))) {
                                    break;
                                    break;
                                }
                                i8++;
                            }
                        }
                        if (i8 == -1) {
                            snapshotStateList.add(transition.getTargetState());
                        } else {
                            snapshotStateList.set(i8, transition.getTargetState());
                        }
                        mutableScatterMap.clear();
                        size2 = snapshotStateList.size();
                        while (i9 < size2) {
                            Object obj6 = snapshotStateList.get(i9);
                            mutableScatterMap.set(obj6, ComposableLambdaKt.rememberComposableLambda(-934471669, true, new CrossfadeKt$Crossfade$5$1(transition, finiteAnimationSpecTween$default, obj6, function3), composerStartRestartGroup, 54));
                        }
                    } else {
                        composerStartRestartGroup.startReplaceGroup(316295552);
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy3 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.getTopStart(), false);
                    int iHashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                    CompositionLocalMap currentCompositionLocalMap3 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier2);
                    ComposeUiNode.Companion companion4 = ComposeUiNode.Companion;
                    constructor = companion4.getConstructor();
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
                    Updater.set-impl(composer4, measurePolicyMaybeCachedBoxMeasurePolicy3, companion4.getSetMeasurePolicy());
                    Updater.set-impl(composer4, currentCompositionLocalMap3, companion4.getSetResolvedCompositionLocals());
                    Updater.init-impl(composer4, Integer.valueOf(iHashCode3), companion4.getSetCompositeKeyHash());
                    Updater.reconcile-impl(composer4, companion4.getApplyOnDeactivatedNodeAssertion());
                    Updater.set-impl(composer4, modifierMaterializeModifier3, companion4.getSetModifier());
                    BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
                    composerStartRestartGroup.startReplaceGroup(-1312707512);
                    size = snapshotStateList.size();
                    while (i7 < size) {
                        Object obj7 = snapshotStateList.get(i7);
                        composerStartRestartGroup.startMovableGroup(1171574969, function2.invoke(obj7));
                        function5 = (Function2) mutableScatterMap.get(obj7);
                        if (function5 == null) {
                            composerStartRestartGroup.startReplaceGroup(1959122128);
                            composerStartRestartGroup.endReplaceGroup();
                        } else {
                            composerStartRestartGroup.startReplaceGroup(1171576145);
                            function5.invoke(composerStartRestartGroup, 0);
                            composerStartRestartGroup.endReplaceGroup();
                        }
                        composerStartRestartGroup.endMovableGroup();
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    composerStartRestartGroup.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                }
                finiteAnimationSpec2 = finiteAnimationSpecTween$default;
                function4 = function2;
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    final Modifier modifier5 = modifier2;
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.CrossfadeKt.Crossfade.7
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        public final void invoke(Composer composer5, int i13) {
                            CrossfadeKt.Crossfade(transition, modifier5, finiteAnimationSpec2, function4, function3, composer5, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj8, Object obj9) {
                            invoke((Composer) obj8, ((Number) obj9).intValue());
                            return Unit.INSTANCE;
                        }
                    });
                }
            }
            i11 |= 3072;
            function2 = function1;
            if ((i & 24576) == 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i10 = 16384;
                } else {
                    i10 = 8192;
                }
                i11 |= i10;
            }
            if ((i11 & 9363) != 9362) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i11 & 1)) {
                if (i12 != 0) {
                    modifier2 = Modifier.Companion;
                }
                if (i3 != 0) {
                    finiteAnimationSpecTween$default = AnimationSpecKt.tween$default(0, 0, null, 7, null);
                }
                if (i5 != 0) {
                    objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue4 == Composer.Companion.getEmpty()) {
                        objRememberedValue4 = new Function1<T, T>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$3$1
                            public final T invoke(T t) {
                                return t;
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                    }
                    function2 = (Function1) objRememberedValue4;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1877370462, i11, -1, "androidx.compose.animation.Crossfade (Crossfade.kt:102)");
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                companion = Composer.Companion;
                obj = objRememberedValue;
                if (objRememberedValue == companion.getEmpty()) {
                    SnapshotStateList snapshotStateListMutableStateListOf4 = SnapshotStateKt.mutableStateListOf();
                    snapshotStateListMutableStateListOf4.add(transition.getCurrentState());
                    composerStartRestartGroup.updateRememberedValue(snapshotStateListMutableStateListOf4);
                    obj = snapshotStateListMutableStateListOf4;
                }
                snapshotStateList = (SnapshotStateList) obj;
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue2 == companion.getEmpty()) {
                    objRememberedValue2 = ScatterMapKt.mutableScatterMapOf();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                mutableScatterMap = (MutableScatterMap) objRememberedValue2;
                if (Intrinsics.areEqual(transition.getCurrentState(), transition.getTargetState())) {
                    composerStartRestartGroup.startReplaceGroup(321145192);
                    if (snapshotStateList.size() == 1) {
                        composerStartRestartGroup.startReplaceGroup(321279546);
                        if ((i11 & 14) == 4) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (z2) {
                            objRememberedValue3 = new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
                                public final Boolean m95invoke(T t) {
                                    return Boolean.valueOf(!Intrinsics.areEqual(t, transition.getTargetState()));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            objRememberedValue3 = new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
                                public final Boolean m95invoke(T t) {
                                    return Boolean.valueOf(!Intrinsics.areEqual(t, transition.getTargetState()));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        CollectionsKt.removeAll(snapshotStateList, (Function1) objRememberedValue3);
                        mutableScatterMap.clear();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(321279546);
                        if ((i11 & 14) == 4) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (z2) {
                            objRememberedValue3 = new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
                                public final Boolean m95invoke(T t) {
                                    return Boolean.valueOf(!Intrinsics.areEqual(t, transition.getTargetState()));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            objRememberedValue3 = new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
                                public final Boolean m95invoke(T t) {
                                    return Boolean.valueOf(!Intrinsics.areEqual(t, transition.getTargetState()));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        CollectionsKt.removeAll(snapshotStateList, (Function1) objRememberedValue3);
                        mutableScatterMap.clear();
                    }
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(316295552);
                }
                composerStartRestartGroup.endReplaceGroup();
                if (mutableScatterMap.contains(transition.getTargetState())) {
                    composerStartRestartGroup.startReplaceGroup(321536443);
                    it = snapshotStateList.iterator();
                    i8 = 0;
                    while (true) {
                        if (it.hasNext()) {
                            i8 = -1;
                            break;
                        } else {
                            if (Intrinsics.areEqual(function2.invoke(it.next()), function2.invoke(transition.getTargetState()))) {
                                break;
                                break;
                            }
                            i8++;
                        }
                    }
                    if (i8 == -1) {
                        snapshotStateList.add(transition.getTargetState());
                    } else {
                        snapshotStateList.set(i8, transition.getTargetState());
                    }
                    mutableScatterMap.clear();
                    size2 = snapshotStateList.size();
                    while (i9 < size2) {
                        Object obj8 = snapshotStateList.get(i9);
                        mutableScatterMap.set(obj8, ComposableLambdaKt.rememberComposableLambda(-934471669, true, new CrossfadeKt$Crossfade$5$1(transition, finiteAnimationSpecTween$default, obj8, function3), composerStartRestartGroup, 54));
                    }
                } else {
                    composerStartRestartGroup.startReplaceGroup(316295552);
                }
                composerStartRestartGroup.endReplaceGroup();
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy4 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.getTopStart(), false);
                int iHashCode4 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                CompositionLocalMap currentCompositionLocalMap4 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier2);
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
                Composer composer5 = Updater.constructor-impl(composerStartRestartGroup);
                Updater.set-impl(composer5, measurePolicyMaybeCachedBoxMeasurePolicy4, companion5.getSetMeasurePolicy());
                Updater.set-impl(composer5, currentCompositionLocalMap4, companion5.getSetResolvedCompositionLocals());
                Updater.init-impl(composer5, Integer.valueOf(iHashCode4), companion5.getSetCompositeKeyHash());
                Updater.reconcile-impl(composer5, companion5.getApplyOnDeactivatedNodeAssertion());
                Updater.set-impl(composer5, modifierMaterializeModifier4, companion5.getSetModifier());
                BoxScopeInstance boxScopeInstance4 = BoxScopeInstance.INSTANCE;
                composerStartRestartGroup.startReplaceGroup(-1312707512);
                size = snapshotStateList.size();
                while (i7 < size) {
                    Object obj9 = snapshotStateList.get(i7);
                    composerStartRestartGroup.startMovableGroup(1171574969, function2.invoke(obj9));
                    function5 = (Function2) mutableScatterMap.get(obj9);
                    if (function5 == null) {
                        composerStartRestartGroup.startReplaceGroup(1959122128);
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(1171576145);
                        function5.invoke(composerStartRestartGroup, 0);
                        composerStartRestartGroup.endReplaceGroup();
                    }
                    composerStartRestartGroup.endMovableGroup();
                }
                composerStartRestartGroup.endReplaceGroup();
                composerStartRestartGroup.endNode();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                composerStartRestartGroup.skipToGroupEnd();
            }
            finiteAnimationSpec2 = finiteAnimationSpecTween$default;
            function4 = function2;
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                final Modifier modifier6 = modifier2;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.CrossfadeKt.Crossfade.7
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    public final void invoke(Composer composer6, int i13) {
                        CrossfadeKt.Crossfade(transition, modifier6, finiteAnimationSpec2, function4, function3, composer6, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj10, Object obj11) {
                        invoke((Composer) obj10, ((Number) obj11).intValue());
                        return Unit.INSTANCE;
                    }
                });
            }
        }
        i11 |= 48;
        modifier2 = modifier;
        i3 = i2 & 2;
        if (i3 != 0) {
            if ((i & 384) == 0) {
                finiteAnimationSpecTween$default = finiteAnimationSpec;
                if (composerStartRestartGroup.changedInstance(finiteAnimationSpecTween$default)) {
                    i4 = 256;
                } else {
                    i4 = 128;
                }
                i11 |= i4;
            }
            i5 = i2 & 4;
            if (i5 != 0) {
                if ((i & 3072) == 0) {
                    function2 = function1;
                    if (composerStartRestartGroup.changedInstance(function2)) {
                        i6 = 2048;
                    } else {
                        i6 = 1024;
                    }
                    i11 |= i6;
                }
                if ((i & 24576) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i10 = 16384;
                    } else {
                        i10 = 8192;
                    }
                    i11 |= i10;
                }
                if ((i11 & 9363) != 9362) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i11 & 1)) {
                    if (i12 != 0) {
                        modifier2 = Modifier.Companion;
                    }
                    if (i3 != 0) {
                        finiteAnimationSpecTween$default = AnimationSpecKt.tween$default(0, 0, null, 7, null);
                    }
                    if (i5 != 0) {
                        objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue4 == Composer.Companion.getEmpty()) {
                            objRememberedValue4 = new Function1<T, T>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$3$1
                                public final T invoke(T t) {
                                    return t;
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                        }
                        function2 = (Function1) objRememberedValue4;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1877370462, i11, -1, "androidx.compose.animation.Crossfade (Crossfade.kt:102)");
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    companion = Composer.Companion;
                    obj = objRememberedValue;
                    if (objRememberedValue == companion.getEmpty()) {
                        SnapshotStateList snapshotStateListMutableStateListOf5 = SnapshotStateKt.mutableStateListOf();
                        snapshotStateListMutableStateListOf5.add(transition.getCurrentState());
                        composerStartRestartGroup.updateRememberedValue(snapshotStateListMutableStateListOf5);
                        obj = snapshotStateListMutableStateListOf5;
                    }
                    snapshotStateList = (SnapshotStateList) obj;
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue2 == companion.getEmpty()) {
                        objRememberedValue2 = ScatterMapKt.mutableScatterMapOf();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    mutableScatterMap = (MutableScatterMap) objRememberedValue2;
                    if (Intrinsics.areEqual(transition.getCurrentState(), transition.getTargetState())) {
                        composerStartRestartGroup.startReplaceGroup(321145192);
                        if (snapshotStateList.size() == 1) {
                            composerStartRestartGroup.startReplaceGroup(321279546);
                            if ((i11 & 14) == 4) {
                                z2 = true;
                            } else {
                                z2 = false;
                            }
                            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                            if (z2) {
                                objRememberedValue3 = new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
                                    public final Boolean m95invoke(T t) {
                                        return Boolean.valueOf(!Intrinsics.areEqual(t, transition.getTargetState()));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                            } else {
                                objRememberedValue3 = new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
                                    public final Boolean m95invoke(T t) {
                                        return Boolean.valueOf(!Intrinsics.areEqual(t, transition.getTargetState()));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                            }
                            CollectionsKt.removeAll(snapshotStateList, (Function1) objRememberedValue3);
                            mutableScatterMap.clear();
                        } else {
                            composerStartRestartGroup.startReplaceGroup(321279546);
                            if ((i11 & 14) == 4) {
                                z2 = true;
                            } else {
                                z2 = false;
                            }
                            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                            if (z2) {
                                objRememberedValue3 = new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
                                    public final Boolean m95invoke(T t) {
                                        return Boolean.valueOf(!Intrinsics.areEqual(t, transition.getTargetState()));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                            } else {
                                objRememberedValue3 = new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
                                    public final Boolean m95invoke(T t) {
                                        return Boolean.valueOf(!Intrinsics.areEqual(t, transition.getTargetState()));
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                            }
                            CollectionsKt.removeAll(snapshotStateList, (Function1) objRememberedValue3);
                            mutableScatterMap.clear();
                        }
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(316295552);
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    if (mutableScatterMap.contains(transition.getTargetState())) {
                        composerStartRestartGroup.startReplaceGroup(321536443);
                        it = snapshotStateList.iterator();
                        i8 = 0;
                        while (true) {
                            if (it.hasNext()) {
                                i8 = -1;
                                break;
                            } else {
                                if (Intrinsics.areEqual(function2.invoke(it.next()), function2.invoke(transition.getTargetState()))) {
                                    break;
                                    break;
                                }
                                i8++;
                            }
                        }
                        if (i8 == -1) {
                            snapshotStateList.add(transition.getTargetState());
                        } else {
                            snapshotStateList.set(i8, transition.getTargetState());
                        }
                        mutableScatterMap.clear();
                        size2 = snapshotStateList.size();
                        while (i9 < size2) {
                            Object obj10 = snapshotStateList.get(i9);
                            mutableScatterMap.set(obj10, ComposableLambdaKt.rememberComposableLambda(-934471669, true, new CrossfadeKt$Crossfade$5$1(transition, finiteAnimationSpecTween$default, obj10, function3), composerStartRestartGroup, 54));
                        }
                    } else {
                        composerStartRestartGroup.startReplaceGroup(316295552);
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy5 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.getTopStart(), false);
                    int iHashCode5 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                    CompositionLocalMap currentCompositionLocalMap5 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier5 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier2);
                    ComposeUiNode.Companion companion6 = ComposeUiNode.Companion;
                    constructor = companion6.getConstructor();
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
                    Updater.set-impl(composer6, measurePolicyMaybeCachedBoxMeasurePolicy5, companion6.getSetMeasurePolicy());
                    Updater.set-impl(composer6, currentCompositionLocalMap5, companion6.getSetResolvedCompositionLocals());
                    Updater.init-impl(composer6, Integer.valueOf(iHashCode5), companion6.getSetCompositeKeyHash());
                    Updater.reconcile-impl(composer6, companion6.getApplyOnDeactivatedNodeAssertion());
                    Updater.set-impl(composer6, modifierMaterializeModifier5, companion6.getSetModifier());
                    BoxScopeInstance boxScopeInstance5 = BoxScopeInstance.INSTANCE;
                    composerStartRestartGroup.startReplaceGroup(-1312707512);
                    size = snapshotStateList.size();
                    while (i7 < size) {
                        Object obj11 = snapshotStateList.get(i7);
                        composerStartRestartGroup.startMovableGroup(1171574969, function2.invoke(obj11));
                        function5 = (Function2) mutableScatterMap.get(obj11);
                        if (function5 == null) {
                            composerStartRestartGroup.startReplaceGroup(1959122128);
                            composerStartRestartGroup.endReplaceGroup();
                        } else {
                            composerStartRestartGroup.startReplaceGroup(1171576145);
                            function5.invoke(composerStartRestartGroup, 0);
                            composerStartRestartGroup.endReplaceGroup();
                        }
                        composerStartRestartGroup.endMovableGroup();
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    composerStartRestartGroup.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                }
                finiteAnimationSpec2 = finiteAnimationSpecTween$default;
                function4 = function2;
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    final Modifier modifier7 = modifier2;
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.CrossfadeKt.Crossfade.7
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        public final void invoke(Composer composer7, int i13) {
                            CrossfadeKt.Crossfade(transition, modifier7, finiteAnimationSpec2, function4, function3, composer7, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj12, Object obj13) {
                            invoke((Composer) obj12, ((Number) obj13).intValue());
                            return Unit.INSTANCE;
                        }
                    });
                }
            }
            i11 |= 3072;
            function2 = function1;
            if ((i & 24576) == 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i10 = 16384;
                } else {
                    i10 = 8192;
                }
                i11 |= i10;
            }
            if ((i11 & 9363) != 9362) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i11 & 1)) {
                if (i12 != 0) {
                    modifier2 = Modifier.Companion;
                }
                if (i3 != 0) {
                    finiteAnimationSpecTween$default = AnimationSpecKt.tween$default(0, 0, null, 7, null);
                }
                if (i5 != 0) {
                    objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue4 == Composer.Companion.getEmpty()) {
                        objRememberedValue4 = new Function1<T, T>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$3$1
                            public final T invoke(T t) {
                                return t;
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                    }
                    function2 = (Function1) objRememberedValue4;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1877370462, i11, -1, "androidx.compose.animation.Crossfade (Crossfade.kt:102)");
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                companion = Composer.Companion;
                obj = objRememberedValue;
                if (objRememberedValue == companion.getEmpty()) {
                    SnapshotStateList snapshotStateListMutableStateListOf6 = SnapshotStateKt.mutableStateListOf();
                    snapshotStateListMutableStateListOf6.add(transition.getCurrentState());
                    composerStartRestartGroup.updateRememberedValue(snapshotStateListMutableStateListOf6);
                    obj = snapshotStateListMutableStateListOf6;
                }
                snapshotStateList = (SnapshotStateList) obj;
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue2 == companion.getEmpty()) {
                    objRememberedValue2 = ScatterMapKt.mutableScatterMapOf();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                mutableScatterMap = (MutableScatterMap) objRememberedValue2;
                if (Intrinsics.areEqual(transition.getCurrentState(), transition.getTargetState())) {
                    composerStartRestartGroup.startReplaceGroup(321145192);
                    if (snapshotStateList.size() == 1) {
                        composerStartRestartGroup.startReplaceGroup(321279546);
                        if ((i11 & 14) == 4) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (z2) {
                            objRememberedValue3 = new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
                                public final Boolean m95invoke(T t) {
                                    return Boolean.valueOf(!Intrinsics.areEqual(t, transition.getTargetState()));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            objRememberedValue3 = new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
                                public final Boolean m95invoke(T t) {
                                    return Boolean.valueOf(!Intrinsics.areEqual(t, transition.getTargetState()));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        CollectionsKt.removeAll(snapshotStateList, (Function1) objRememberedValue3);
                        mutableScatterMap.clear();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(321279546);
                        if ((i11 & 14) == 4) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (z2) {
                            objRememberedValue3 = new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
                                public final Boolean m95invoke(T t) {
                                    return Boolean.valueOf(!Intrinsics.areEqual(t, transition.getTargetState()));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            objRememberedValue3 = new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
                                public final Boolean m95invoke(T t) {
                                    return Boolean.valueOf(!Intrinsics.areEqual(t, transition.getTargetState()));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        CollectionsKt.removeAll(snapshotStateList, (Function1) objRememberedValue3);
                        mutableScatterMap.clear();
                    }
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(316295552);
                }
                composerStartRestartGroup.endReplaceGroup();
                if (mutableScatterMap.contains(transition.getTargetState())) {
                    composerStartRestartGroup.startReplaceGroup(321536443);
                    it = snapshotStateList.iterator();
                    i8 = 0;
                    while (true) {
                        if (it.hasNext()) {
                            i8 = -1;
                            break;
                        } else {
                            if (Intrinsics.areEqual(function2.invoke(it.next()), function2.invoke(transition.getTargetState()))) {
                                break;
                                break;
                            }
                            i8++;
                        }
                    }
                    if (i8 == -1) {
                        snapshotStateList.add(transition.getTargetState());
                    } else {
                        snapshotStateList.set(i8, transition.getTargetState());
                    }
                    mutableScatterMap.clear();
                    size2 = snapshotStateList.size();
                    while (i9 < size2) {
                        Object obj12 = snapshotStateList.get(i9);
                        mutableScatterMap.set(obj12, ComposableLambdaKt.rememberComposableLambda(-934471669, true, new CrossfadeKt$Crossfade$5$1(transition, finiteAnimationSpecTween$default, obj12, function3), composerStartRestartGroup, 54));
                    }
                } else {
                    composerStartRestartGroup.startReplaceGroup(316295552);
                }
                composerStartRestartGroup.endReplaceGroup();
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy6 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.getTopStart(), false);
                int iHashCode6 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                CompositionLocalMap currentCompositionLocalMap6 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier6 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier2);
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
                Composer composer7 = Updater.constructor-impl(composerStartRestartGroup);
                Updater.set-impl(composer7, measurePolicyMaybeCachedBoxMeasurePolicy6, companion7.getSetMeasurePolicy());
                Updater.set-impl(composer7, currentCompositionLocalMap6, companion7.getSetResolvedCompositionLocals());
                Updater.init-impl(composer7, Integer.valueOf(iHashCode6), companion7.getSetCompositeKeyHash());
                Updater.reconcile-impl(composer7, companion7.getApplyOnDeactivatedNodeAssertion());
                Updater.set-impl(composer7, modifierMaterializeModifier6, companion7.getSetModifier());
                BoxScopeInstance boxScopeInstance6 = BoxScopeInstance.INSTANCE;
                composerStartRestartGroup.startReplaceGroup(-1312707512);
                size = snapshotStateList.size();
                while (i7 < size) {
                    Object obj13 = snapshotStateList.get(i7);
                    composerStartRestartGroup.startMovableGroup(1171574969, function2.invoke(obj13));
                    function5 = (Function2) mutableScatterMap.get(obj13);
                    if (function5 == null) {
                        composerStartRestartGroup.startReplaceGroup(1959122128);
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(1171576145);
                        function5.invoke(composerStartRestartGroup, 0);
                        composerStartRestartGroup.endReplaceGroup();
                    }
                    composerStartRestartGroup.endMovableGroup();
                }
                composerStartRestartGroup.endReplaceGroup();
                composerStartRestartGroup.endNode();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                composerStartRestartGroup.skipToGroupEnd();
            }
            finiteAnimationSpec2 = finiteAnimationSpecTween$default;
            function4 = function2;
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                final Modifier modifier8 = modifier2;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.CrossfadeKt.Crossfade.7
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    public final void invoke(Composer composer8, int i13) {
                        CrossfadeKt.Crossfade(transition, modifier8, finiteAnimationSpec2, function4, function3, composer8, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj14, Object obj15) {
                        invoke((Composer) obj14, ((Number) obj15).intValue());
                        return Unit.INSTANCE;
                    }
                });
            }
        }
        i11 |= 384;
        finiteAnimationSpecTween$default = finiteAnimationSpec;
        i5 = i2 & 4;
        if (i5 != 0) {
            if ((i & 3072) == 0) {
                function2 = function1;
                if (composerStartRestartGroup.changedInstance(function2)) {
                    i6 = 2048;
                } else {
                    i6 = 1024;
                }
                i11 |= i6;
            }
            if ((i & 24576) == 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i10 = 16384;
                } else {
                    i10 = 8192;
                }
                i11 |= i10;
            }
            if ((i11 & 9363) != 9362) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i11 & 1)) {
                if (i12 != 0) {
                    modifier2 = Modifier.Companion;
                }
                if (i3 != 0) {
                    finiteAnimationSpecTween$default = AnimationSpecKt.tween$default(0, 0, null, 7, null);
                }
                if (i5 != 0) {
                    objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue4 == Composer.Companion.getEmpty()) {
                        objRememberedValue4 = new Function1<T, T>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$3$1
                            public final T invoke(T t) {
                                return t;
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                    }
                    function2 = (Function1) objRememberedValue4;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1877370462, i11, -1, "androidx.compose.animation.Crossfade (Crossfade.kt:102)");
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                companion = Composer.Companion;
                obj = objRememberedValue;
                if (objRememberedValue == companion.getEmpty()) {
                    SnapshotStateList snapshotStateListMutableStateListOf7 = SnapshotStateKt.mutableStateListOf();
                    snapshotStateListMutableStateListOf7.add(transition.getCurrentState());
                    composerStartRestartGroup.updateRememberedValue(snapshotStateListMutableStateListOf7);
                    obj = snapshotStateListMutableStateListOf7;
                }
                snapshotStateList = (SnapshotStateList) obj;
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue2 == companion.getEmpty()) {
                    objRememberedValue2 = ScatterMapKt.mutableScatterMapOf();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                mutableScatterMap = (MutableScatterMap) objRememberedValue2;
                if (Intrinsics.areEqual(transition.getCurrentState(), transition.getTargetState())) {
                    composerStartRestartGroup.startReplaceGroup(321145192);
                    if (snapshotStateList.size() == 1) {
                        composerStartRestartGroup.startReplaceGroup(321279546);
                        if ((i11 & 14) == 4) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (z2) {
                            objRememberedValue3 = new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
                                public final Boolean m95invoke(T t) {
                                    return Boolean.valueOf(!Intrinsics.areEqual(t, transition.getTargetState()));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            objRememberedValue3 = new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
                                public final Boolean m95invoke(T t) {
                                    return Boolean.valueOf(!Intrinsics.areEqual(t, transition.getTargetState()));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        CollectionsKt.removeAll(snapshotStateList, (Function1) objRememberedValue3);
                        mutableScatterMap.clear();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(321279546);
                        if ((i11 & 14) == 4) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (z2) {
                            objRememberedValue3 = new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
                                public final Boolean m95invoke(T t) {
                                    return Boolean.valueOf(!Intrinsics.areEqual(t, transition.getTargetState()));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            objRememberedValue3 = new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
                                public final Boolean m95invoke(T t) {
                                    return Boolean.valueOf(!Intrinsics.areEqual(t, transition.getTargetState()));
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        CollectionsKt.removeAll(snapshotStateList, (Function1) objRememberedValue3);
                        mutableScatterMap.clear();
                    }
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(316295552);
                }
                composerStartRestartGroup.endReplaceGroup();
                if (mutableScatterMap.contains(transition.getTargetState())) {
                    composerStartRestartGroup.startReplaceGroup(321536443);
                    it = snapshotStateList.iterator();
                    i8 = 0;
                    while (true) {
                        if (it.hasNext()) {
                            i8 = -1;
                            break;
                        } else {
                            if (Intrinsics.areEqual(function2.invoke(it.next()), function2.invoke(transition.getTargetState()))) {
                                break;
                                break;
                            }
                            i8++;
                        }
                    }
                    if (i8 == -1) {
                        snapshotStateList.add(transition.getTargetState());
                    } else {
                        snapshotStateList.set(i8, transition.getTargetState());
                    }
                    mutableScatterMap.clear();
                    size2 = snapshotStateList.size();
                    while (i9 < size2) {
                        Object obj14 = snapshotStateList.get(i9);
                        mutableScatterMap.set(obj14, ComposableLambdaKt.rememberComposableLambda(-934471669, true, new CrossfadeKt$Crossfade$5$1(transition, finiteAnimationSpecTween$default, obj14, function3), composerStartRestartGroup, 54));
                    }
                } else {
                    composerStartRestartGroup.startReplaceGroup(316295552);
                }
                composerStartRestartGroup.endReplaceGroup();
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy7 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.getTopStart(), false);
                int iHashCode7 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                CompositionLocalMap currentCompositionLocalMap7 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier7 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier2);
                ComposeUiNode.Companion companion8 = ComposeUiNode.Companion;
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
                Composer composer8 = Updater.constructor-impl(composerStartRestartGroup);
                Updater.set-impl(composer8, measurePolicyMaybeCachedBoxMeasurePolicy7, companion8.getSetMeasurePolicy());
                Updater.set-impl(composer8, currentCompositionLocalMap7, companion8.getSetResolvedCompositionLocals());
                Updater.init-impl(composer8, Integer.valueOf(iHashCode7), companion8.getSetCompositeKeyHash());
                Updater.reconcile-impl(composer8, companion8.getApplyOnDeactivatedNodeAssertion());
                Updater.set-impl(composer8, modifierMaterializeModifier7, companion8.getSetModifier());
                BoxScopeInstance boxScopeInstance7 = BoxScopeInstance.INSTANCE;
                composerStartRestartGroup.startReplaceGroup(-1312707512);
                size = snapshotStateList.size();
                while (i7 < size) {
                    Object obj15 = snapshotStateList.get(i7);
                    composerStartRestartGroup.startMovableGroup(1171574969, function2.invoke(obj15));
                    function5 = (Function2) mutableScatterMap.get(obj15);
                    if (function5 == null) {
                        composerStartRestartGroup.startReplaceGroup(1959122128);
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        composerStartRestartGroup.startReplaceGroup(1171576145);
                        function5.invoke(composerStartRestartGroup, 0);
                        composerStartRestartGroup.endReplaceGroup();
                    }
                    composerStartRestartGroup.endMovableGroup();
                }
                composerStartRestartGroup.endReplaceGroup();
                composerStartRestartGroup.endNode();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                composerStartRestartGroup.skipToGroupEnd();
            }
            finiteAnimationSpec2 = finiteAnimationSpecTween$default;
            function4 = function2;
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                final Modifier modifier9 = modifier2;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.CrossfadeKt.Crossfade.7
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    public final void invoke(Composer composer9, int i13) {
                        CrossfadeKt.Crossfade(transition, modifier9, finiteAnimationSpec2, function4, function3, composer9, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj16, Object obj17) {
                        invoke((Composer) obj16, ((Number) obj17).intValue());
                        return Unit.INSTANCE;
                    }
                });
            }
        }
        i11 |= 3072;
        function2 = function1;
        if ((i & 24576) == 0) {
            if (composerStartRestartGroup.changedInstance(function3)) {
                i10 = 16384;
            } else {
                i10 = 8192;
            }
            i11 |= i10;
        }
        if ((i11 & 9363) != 9362) {
            z = true;
        } else {
            z = false;
        }
        if (composerStartRestartGroup.shouldExecute(z, i11 & 1)) {
            if (i12 != 0) {
                modifier2 = Modifier.Companion;
            }
            if (i3 != 0) {
                finiteAnimationSpecTween$default = AnimationSpecKt.tween$default(0, 0, null, 7, null);
            }
            if (i5 != 0) {
                objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue4 == Composer.Companion.getEmpty()) {
                    objRememberedValue4 = new Function1<T, T>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$3$1
                        public final T invoke(T t) {
                            return t;
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                }
                function2 = (Function1) objRememberedValue4;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1877370462, i11, -1, "androidx.compose.animation.Crossfade (Crossfade.kt:102)");
            }
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            companion = Composer.Companion;
            obj = objRememberedValue;
            if (objRememberedValue == companion.getEmpty()) {
                SnapshotStateList snapshotStateListMutableStateListOf8 = SnapshotStateKt.mutableStateListOf();
                snapshotStateListMutableStateListOf8.add(transition.getCurrentState());
                composerStartRestartGroup.updateRememberedValue(snapshotStateListMutableStateListOf8);
                obj = snapshotStateListMutableStateListOf8;
            }
            snapshotStateList = (SnapshotStateList) obj;
            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == companion.getEmpty()) {
                objRememberedValue2 = ScatterMapKt.mutableScatterMapOf();
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            mutableScatterMap = (MutableScatterMap) objRememberedValue2;
            if (Intrinsics.areEqual(transition.getCurrentState(), transition.getTargetState())) {
                composerStartRestartGroup.startReplaceGroup(321145192);
                if (snapshotStateList.size() == 1) {
                    composerStartRestartGroup.startReplaceGroup(321279546);
                    if ((i11 & 14) == 4) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (z2) {
                        objRememberedValue3 = new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
                            public final Boolean m95invoke(T t) {
                                return Boolean.valueOf(!Intrinsics.areEqual(t, transition.getTargetState()));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
                            public final Boolean m95invoke(T t) {
                                return Boolean.valueOf(!Intrinsics.areEqual(t, transition.getTargetState()));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    CollectionsKt.removeAll(snapshotStateList, (Function1) objRememberedValue3);
                    mutableScatterMap.clear();
                } else {
                    composerStartRestartGroup.startReplaceGroup(321279546);
                    if ((i11 & 14) == 4) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (z2) {
                        objRememberedValue3 = new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
                            public final Boolean m95invoke(T t) {
                                return Boolean.valueOf(!Intrinsics.areEqual(t, transition.getTargetState()));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = new Function1<T, Boolean>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$4$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
                            public final Boolean m95invoke(T t) {
                                return Boolean.valueOf(!Intrinsics.areEqual(t, transition.getTargetState()));
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    CollectionsKt.removeAll(snapshotStateList, (Function1) objRememberedValue3);
                    mutableScatterMap.clear();
                }
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(316295552);
            }
            composerStartRestartGroup.endReplaceGroup();
            if (mutableScatterMap.contains(transition.getTargetState())) {
                composerStartRestartGroup.startReplaceGroup(321536443);
                it = snapshotStateList.iterator();
                i8 = 0;
                while (true) {
                    if (it.hasNext()) {
                        i8 = -1;
                        break;
                    } else {
                        if (Intrinsics.areEqual(function2.invoke(it.next()), function2.invoke(transition.getTargetState()))) {
                            break;
                            break;
                        }
                        i8++;
                    }
                }
                if (i8 == -1) {
                    snapshotStateList.add(transition.getTargetState());
                } else {
                    snapshotStateList.set(i8, transition.getTargetState());
                }
                mutableScatterMap.clear();
                size2 = snapshotStateList.size();
                while (i9 < size2) {
                    Object obj16 = snapshotStateList.get(i9);
                    mutableScatterMap.set(obj16, ComposableLambdaKt.rememberComposableLambda(-934471669, true, new CrossfadeKt$Crossfade$5$1(transition, finiteAnimationSpecTween$default, obj16, function3), composerStartRestartGroup, 54));
                }
            } else {
                composerStartRestartGroup.startReplaceGroup(316295552);
            }
            composerStartRestartGroup.endReplaceGroup();
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy8 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.getTopStart(), false);
            int iHashCode8 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
            CompositionLocalMap currentCompositionLocalMap8 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier8 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier2);
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
            Composer composer9 = Updater.constructor-impl(composerStartRestartGroup);
            Updater.set-impl(composer9, measurePolicyMaybeCachedBoxMeasurePolicy8, companion9.getSetMeasurePolicy());
            Updater.set-impl(composer9, currentCompositionLocalMap8, companion9.getSetResolvedCompositionLocals());
            Updater.init-impl(composer9, Integer.valueOf(iHashCode8), companion9.getSetCompositeKeyHash());
            Updater.reconcile-impl(composer9, companion9.getApplyOnDeactivatedNodeAssertion());
            Updater.set-impl(composer9, modifierMaterializeModifier8, companion9.getSetModifier());
            BoxScopeInstance boxScopeInstance8 = BoxScopeInstance.INSTANCE;
            composerStartRestartGroup.startReplaceGroup(-1312707512);
            size = snapshotStateList.size();
            while (i7 < size) {
                Object obj17 = snapshotStateList.get(i7);
                composerStartRestartGroup.startMovableGroup(1171574969, function2.invoke(obj17));
                function5 = (Function2) mutableScatterMap.get(obj17);
                if (function5 == null) {
                    composerStartRestartGroup.startReplaceGroup(1959122128);
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(1171576145);
                    function5.invoke(composerStartRestartGroup, 0);
                    composerStartRestartGroup.endReplaceGroup();
                }
                composerStartRestartGroup.endMovableGroup();
            }
            composerStartRestartGroup.endReplaceGroup();
            composerStartRestartGroup.endNode();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        finiteAnimationSpec2 = finiteAnimationSpecTween$default;
        function4 = function2;
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier10 = modifier2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.CrossfadeKt.Crossfade.7
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                public final void invoke(Composer composer10, int i13) {
                    CrossfadeKt.Crossfade(transition, modifier10, finiteAnimationSpec2, function4, function3, composer10, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj18, Object obj19) {
                    invoke((Composer) obj18, ((Number) obj19).intValue());
                    return Unit.INSTANCE;
                }
            });
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Crossfade API now has a new label parameter added.")
    public static final /* synthetic */ void Crossfade(final Object obj, Modifier modifier, FiniteAnimationSpec finiteAnimationSpec, Function3 function3, Composer composer, final int i, final int i2) {
        int i3;
        final Function3 function4;
        final FiniteAnimationSpec finiteAnimationSpec2;
        final Modifier modifier2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-160948176);
        if ((i & 6) == 0) {
            i3 = ((i & 8) == 0 ? composerStartRestartGroup.changed(obj) : composerStartRestartGroup.changedInstance(obj) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        int i4 = i2 & 2;
        if (i4 != 0) {
            i3 |= 48;
        } else if ((i & 48) == 0) {
            i3 |= composerStartRestartGroup.changed(modifier) ? 32 : 16;
        }
        int i5 = i2 & 4;
        if (i5 != 0) {
            i3 |= 384;
        } else if ((i & 384) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(finiteAnimationSpec) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function3) ? 2048 : 1024;
        }
        if (composerStartRestartGroup.shouldExecute((i3 & 1171) != 1170, i3 & 1)) {
            if (i4 != 0) {
                modifier = Modifier.Companion;
            }
            Modifier modifier3 = modifier;
            if (i5 != 0) {
                finiteAnimationSpec = AnimationSpecKt.tween$default(0, 0, null, 7, null);
            }
            FiniteAnimationSpec finiteAnimationSpec3 = finiteAnimationSpec;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-160948176, i3, -1, "androidx.compose.animation.Crossfade (Crossfade.kt:68)");
            }
            Crossfade(androidx.compose.animation.core.TransitionKt.updateTransition(obj, (String) null, composerStartRestartGroup, i3 & 14, 2), modifier3, (FiniteAnimationSpec<Float>) finiteAnimationSpec3, (Function1) null, function3, composerStartRestartGroup, (i3 & 1008) | ((i3 << 3) & 57344), 4);
            function4 = function3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier3;
            finiteAnimationSpec2 = finiteAnimationSpec3;
        } else {
            function4 = function3;
            composerStartRestartGroup.skipToGroupEnd();
            finiteAnimationSpec2 = finiteAnimationSpec;
            modifier2 = modifier;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.CrossfadeKt.Crossfade.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                public final void invoke(Composer composer2, int i6) {
                    CrossfadeKt.Crossfade(obj, modifier2, finiteAnimationSpec2, function4, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj2, Object obj3) {
                    invoke((Composer) obj2, ((Number) obj3).intValue());
                    return Unit.INSTANCE;
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:26:0x0045  */
    /* JADX WARN: Code duplicated, block: B:28:0x004a  */
    /* JADX WARN: Code duplicated, block: B:30:0x004e  */
    /* JADX WARN: Code duplicated, block: B:32:0x0056  */
    /* JADX WARN: Code duplicated, block: B:33:0x0059  */
    /* JADX WARN: Code duplicated, block: B:37:0x0060  */
    /* JADX WARN: Code duplicated, block: B:39:0x0065  */
    /* JADX WARN: Code duplicated, block: B:41:0x0069  */
    /* JADX WARN: Code duplicated, block: B:43:0x0071  */
    /* JADX WARN: Code duplicated, block: B:44:0x0074  */
    /* JADX WARN: Code duplicated, block: B:48:0x007d  */
    /* JADX WARN: Code duplicated, block: B:50:0x0083  */
    /* JADX WARN: Code duplicated, block: B:51:0x0086  */
    /* JADX WARN: Code duplicated, block: B:55:0x0090  */
    /* JADX WARN: Code duplicated, block: B:56:0x0092  */
    /* JADX WARN: Code duplicated, block: B:59:0x009b A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:60:0x009d  */
    /* JADX WARN: Code duplicated, block: B:61:0x00a3  */
    /* JADX WARN: Code duplicated, block: B:63:0x00a7  */
    /* JADX WARN: Code duplicated, block: B:64:0x00af  */
    /* JADX WARN: Code duplicated, block: B:66:0x00b2  */
    /* JADX WARN: Code duplicated, block: B:67:0x00b5  */
    /* JADX WARN: Code duplicated, block: B:70:0x00bd  */
    /* JADX WARN: Code duplicated, block: B:73:0x00de  */
    /* JADX WARN: Code duplicated, block: B:75:0x00e5  */
    /* JADX WARN: Code duplicated, block: B:78:0x00f2  */
    /* JADX WARN: Code duplicated, block: B:80:? A[RETURN, SYNTHETIC] */
    public static final <T> void Crossfade(final T t, Modifier modifier, FiniteAnimationSpec<Float> finiteAnimationSpec, String str, final Function3<? super T, ? super Composer, ? super Integer, Unit> function3, Composer composer, final int i, final int i2) {
        int i3;
        Modifier modifier2;
        int i4;
        FiniteAnimationSpec<Float> finiteAnimationSpec2;
        int i5;
        int i6;
        int i7;
        boolean z;
        final Modifier modifier3;
        final FiniteAnimationSpec<Float> finiteAnimationSpec3;
        final String str2;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        int i8;
        Modifier modifier4;
        FiniteAnimationSpec<Float> finiteAnimationSpecTween$default;
        String str3;
        int i9;
        Composer composerStartRestartGroup = composer.startRestartGroup(-513216493);
        if ((i & 6) == 0) {
            i3 = ((i & 8) == 0 ? composerStartRestartGroup.changed(t) : composerStartRestartGroup.changedInstance(t) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        int i10 = i2 & 2;
        if (i10 == 0) {
            if ((i & 48) == 0) {
                modifier2 = modifier;
                i3 |= composerStartRestartGroup.changed(modifier2) ? 32 : 16;
            }
            i4 = i2 & 4;
            if (i4 != 0) {
                if ((i & 384) == 0) {
                    finiteAnimationSpec2 = finiteAnimationSpec;
                    if (composerStartRestartGroup.changedInstance(finiteAnimationSpec2)) {
                        i5 = 256;
                    } else {
                        i5 = 128;
                    }
                    i3 |= i5;
                }
                i6 = i2 & 8;
                if (i6 != 0) {
                    if ((i & 3072) == 0) {
                        if (composerStartRestartGroup.changed(str)) {
                            i7 = 2048;
                        } else {
                            i7 = 1024;
                        }
                        i3 |= i7;
                    }
                    if ((i & 24576) == 0) {
                        if (composerStartRestartGroup.changedInstance(function3)) {
                            i9 = 16384;
                        } else {
                            i9 = 8192;
                        }
                        i3 |= i9;
                    }
                    if ((i3 & 9363) != 9362) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                        if (i10 != 0) {
                            modifier4 = Modifier.Companion;
                            i8 = i6;
                        } else {
                            i8 = i6;
                            modifier4 = modifier2;
                        }
                        if (i4 != 0) {
                            finiteAnimationSpecTween$default = AnimationSpecKt.tween$default(0, 0, null, 7, null);
                        } else {
                            finiteAnimationSpecTween$default = finiteAnimationSpec2;
                        }
                        if (i8 != 0) {
                            str3 = "Crossfade";
                        } else {
                            str3 = str;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-513216493, i3, -1, "androidx.compose.animation.Crossfade (Crossfade.kt:55)");
                        }
                        Crossfade(androidx.compose.animation.core.TransitionKt.updateTransition(t, str3, composerStartRestartGroup, (i3 & 14) | ((i3 >> 6) & 112), 0), modifier4, finiteAnimationSpecTween$default, (Function1) null, function3, composerStartRestartGroup, i3 & 58352, 4);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        str2 = str3;
                        modifier3 = modifier4;
                        finiteAnimationSpec3 = finiteAnimationSpecTween$default;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        modifier3 = modifier2;
                        finiteAnimationSpec3 = finiteAnimationSpec2;
                        str2 = str;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.CrossfadeKt.Crossfade.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            public final void invoke(Composer composer2, int i11) {
                                CrossfadeKt.Crossfade(t, modifier3, finiteAnimationSpec3, str2, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        });
                    }
                }
                i3 |= 3072;
                if ((i & 24576) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i9 = 16384;
                    } else {
                        i9 = 8192;
                    }
                    i3 |= i9;
                }
                if ((i3 & 9363) != 9362) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                    if (i10 != 0) {
                        modifier4 = Modifier.Companion;
                        i8 = i6;
                    } else {
                        i8 = i6;
                        modifier4 = modifier2;
                    }
                    if (i4 != 0) {
                        finiteAnimationSpecTween$default = AnimationSpecKt.tween$default(0, 0, null, 7, null);
                    } else {
                        finiteAnimationSpecTween$default = finiteAnimationSpec2;
                    }
                    if (i8 != 0) {
                        str3 = "Crossfade";
                    } else {
                        str3 = str;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-513216493, i3, -1, "androidx.compose.animation.Crossfade (Crossfade.kt:55)");
                    }
                    Crossfade(androidx.compose.animation.core.TransitionKt.updateTransition(t, str3, composerStartRestartGroup, (i3 & 14) | ((i3 >> 6) & 112), 0), modifier4, finiteAnimationSpecTween$default, (Function1) null, function3, composerStartRestartGroup, i3 & 58352, 4);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    str2 = str3;
                    modifier3 = modifier4;
                    finiteAnimationSpec3 = finiteAnimationSpecTween$default;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    modifier3 = modifier2;
                    finiteAnimationSpec3 = finiteAnimationSpec2;
                    str2 = str;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.CrossfadeKt.Crossfade.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        public final void invoke(Composer composer2, int i11) {
                            CrossfadeKt.Crossfade(t, modifier3, finiteAnimationSpec3, str2, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    });
                }
            }
            i3 |= 384;
            finiteAnimationSpec2 = finiteAnimationSpec;
            i6 = i2 & 8;
            if (i6 != 0) {
                if ((i & 3072) == 0) {
                    if (composerStartRestartGroup.changed(str)) {
                        i7 = 2048;
                    } else {
                        i7 = 1024;
                    }
                    i3 |= i7;
                }
                if ((i & 24576) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i9 = 16384;
                    } else {
                        i9 = 8192;
                    }
                    i3 |= i9;
                }
                if ((i3 & 9363) != 9362) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                    if (i10 != 0) {
                        modifier4 = Modifier.Companion;
                        i8 = i6;
                    } else {
                        i8 = i6;
                        modifier4 = modifier2;
                    }
                    if (i4 != 0) {
                        finiteAnimationSpecTween$default = AnimationSpecKt.tween$default(0, 0, null, 7, null);
                    } else {
                        finiteAnimationSpecTween$default = finiteAnimationSpec2;
                    }
                    if (i8 != 0) {
                        str3 = "Crossfade";
                    } else {
                        str3 = str;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-513216493, i3, -1, "androidx.compose.animation.Crossfade (Crossfade.kt:55)");
                    }
                    Crossfade(androidx.compose.animation.core.TransitionKt.updateTransition(t, str3, composerStartRestartGroup, (i3 & 14) | ((i3 >> 6) & 112), 0), modifier4, finiteAnimationSpecTween$default, (Function1) null, function3, composerStartRestartGroup, i3 & 58352, 4);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    str2 = str3;
                    modifier3 = modifier4;
                    finiteAnimationSpec3 = finiteAnimationSpecTween$default;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    modifier3 = modifier2;
                    finiteAnimationSpec3 = finiteAnimationSpec2;
                    str2 = str;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.CrossfadeKt.Crossfade.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        public final void invoke(Composer composer2, int i11) {
                            CrossfadeKt.Crossfade(t, modifier3, finiteAnimationSpec3, str2, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    });
                }
            }
            i3 |= 3072;
            if ((i & 24576) == 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i9 = 16384;
                } else {
                    i9 = 8192;
                }
                i3 |= i9;
            }
            if ((i3 & 9363) != 9362) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                if (i10 != 0) {
                    modifier4 = Modifier.Companion;
                    i8 = i6;
                } else {
                    i8 = i6;
                    modifier4 = modifier2;
                }
                if (i4 != 0) {
                    finiteAnimationSpecTween$default = AnimationSpecKt.tween$default(0, 0, null, 7, null);
                } else {
                    finiteAnimationSpecTween$default = finiteAnimationSpec2;
                }
                if (i8 != 0) {
                    str3 = "Crossfade";
                } else {
                    str3 = str;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-513216493, i3, -1, "androidx.compose.animation.Crossfade (Crossfade.kt:55)");
                }
                Crossfade(androidx.compose.animation.core.TransitionKt.updateTransition(t, str3, composerStartRestartGroup, (i3 & 14) | ((i3 >> 6) & 112), 0), modifier4, finiteAnimationSpecTween$default, (Function1) null, function3, composerStartRestartGroup, i3 & 58352, 4);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                str2 = str3;
                modifier3 = modifier4;
                finiteAnimationSpec3 = finiteAnimationSpecTween$default;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
                finiteAnimationSpec3 = finiteAnimationSpec2;
                str2 = str;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.CrossfadeKt.Crossfade.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    public final void invoke(Composer composer2, int i11) {
                        CrossfadeKt.Crossfade(t, modifier3, finiteAnimationSpec3, str2, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                });
            }
        }
        i3 |= 48;
        modifier2 = modifier;
        i4 = i2 & 4;
        if (i4 != 0) {
            if ((i & 384) == 0) {
                finiteAnimationSpec2 = finiteAnimationSpec;
                if (composerStartRestartGroup.changedInstance(finiteAnimationSpec2)) {
                    i5 = 256;
                } else {
                    i5 = 128;
                }
                i3 |= i5;
            }
            i6 = i2 & 8;
            if (i6 != 0) {
                if ((i & 3072) == 0) {
                    if (composerStartRestartGroup.changed(str)) {
                        i7 = 2048;
                    } else {
                        i7 = 1024;
                    }
                    i3 |= i7;
                }
                if ((i & 24576) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i9 = 16384;
                    } else {
                        i9 = 8192;
                    }
                    i3 |= i9;
                }
                if ((i3 & 9363) != 9362) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                    if (i10 != 0) {
                        modifier4 = Modifier.Companion;
                        i8 = i6;
                    } else {
                        i8 = i6;
                        modifier4 = modifier2;
                    }
                    if (i4 != 0) {
                        finiteAnimationSpecTween$default = AnimationSpecKt.tween$default(0, 0, null, 7, null);
                    } else {
                        finiteAnimationSpecTween$default = finiteAnimationSpec2;
                    }
                    if (i8 != 0) {
                        str3 = "Crossfade";
                    } else {
                        str3 = str;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-513216493, i3, -1, "androidx.compose.animation.Crossfade (Crossfade.kt:55)");
                    }
                    Crossfade(androidx.compose.animation.core.TransitionKt.updateTransition(t, str3, composerStartRestartGroup, (i3 & 14) | ((i3 >> 6) & 112), 0), modifier4, finiteAnimationSpecTween$default, (Function1) null, function3, composerStartRestartGroup, i3 & 58352, 4);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    str2 = str3;
                    modifier3 = modifier4;
                    finiteAnimationSpec3 = finiteAnimationSpecTween$default;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    modifier3 = modifier2;
                    finiteAnimationSpec3 = finiteAnimationSpec2;
                    str2 = str;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.CrossfadeKt.Crossfade.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        public final void invoke(Composer composer2, int i11) {
                            CrossfadeKt.Crossfade(t, modifier3, finiteAnimationSpec3, str2, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    });
                }
            }
            i3 |= 3072;
            if ((i & 24576) == 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i9 = 16384;
                } else {
                    i9 = 8192;
                }
                i3 |= i9;
            }
            if ((i3 & 9363) != 9362) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                if (i10 != 0) {
                    modifier4 = Modifier.Companion;
                    i8 = i6;
                } else {
                    i8 = i6;
                    modifier4 = modifier2;
                }
                if (i4 != 0) {
                    finiteAnimationSpecTween$default = AnimationSpecKt.tween$default(0, 0, null, 7, null);
                } else {
                    finiteAnimationSpecTween$default = finiteAnimationSpec2;
                }
                if (i8 != 0) {
                    str3 = "Crossfade";
                } else {
                    str3 = str;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-513216493, i3, -1, "androidx.compose.animation.Crossfade (Crossfade.kt:55)");
                }
                Crossfade(androidx.compose.animation.core.TransitionKt.updateTransition(t, str3, composerStartRestartGroup, (i3 & 14) | ((i3 >> 6) & 112), 0), modifier4, finiteAnimationSpecTween$default, (Function1) null, function3, composerStartRestartGroup, i3 & 58352, 4);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                str2 = str3;
                modifier3 = modifier4;
                finiteAnimationSpec3 = finiteAnimationSpecTween$default;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
                finiteAnimationSpec3 = finiteAnimationSpec2;
                str2 = str;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.CrossfadeKt.Crossfade.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    public final void invoke(Composer composer2, int i11) {
                        CrossfadeKt.Crossfade(t, modifier3, finiteAnimationSpec3, str2, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                });
            }
        }
        i3 |= 384;
        finiteAnimationSpec2 = finiteAnimationSpec;
        i6 = i2 & 8;
        if (i6 != 0) {
            if ((i & 3072) == 0) {
                if (composerStartRestartGroup.changed(str)) {
                    i7 = 2048;
                } else {
                    i7 = 1024;
                }
                i3 |= i7;
            }
            if ((i & 24576) == 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i9 = 16384;
                } else {
                    i9 = 8192;
                }
                i3 |= i9;
            }
            if ((i3 & 9363) != 9362) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                if (i10 != 0) {
                    modifier4 = Modifier.Companion;
                    i8 = i6;
                } else {
                    i8 = i6;
                    modifier4 = modifier2;
                }
                if (i4 != 0) {
                    finiteAnimationSpecTween$default = AnimationSpecKt.tween$default(0, 0, null, 7, null);
                } else {
                    finiteAnimationSpecTween$default = finiteAnimationSpec2;
                }
                if (i8 != 0) {
                    str3 = "Crossfade";
                } else {
                    str3 = str;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-513216493, i3, -1, "androidx.compose.animation.Crossfade (Crossfade.kt:55)");
                }
                Crossfade(androidx.compose.animation.core.TransitionKt.updateTransition(t, str3, composerStartRestartGroup, (i3 & 14) | ((i3 >> 6) & 112), 0), modifier4, finiteAnimationSpecTween$default, (Function1) null, function3, composerStartRestartGroup, i3 & 58352, 4);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                str2 = str3;
                modifier3 = modifier4;
                finiteAnimationSpec3 = finiteAnimationSpecTween$default;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
                finiteAnimationSpec3 = finiteAnimationSpec2;
                str2 = str;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.CrossfadeKt.Crossfade.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    public final void invoke(Composer composer2, int i11) {
                        CrossfadeKt.Crossfade(t, modifier3, finiteAnimationSpec3, str2, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                });
            }
        }
        i3 |= 3072;
        if ((i & 24576) == 0) {
            if (composerStartRestartGroup.changedInstance(function3)) {
                i9 = 16384;
            } else {
                i9 = 8192;
            }
            i3 |= i9;
        }
        if ((i3 & 9363) != 9362) {
            z = true;
        } else {
            z = false;
        }
        if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
            if (i10 != 0) {
                modifier4 = Modifier.Companion;
                i8 = i6;
            } else {
                i8 = i6;
                modifier4 = modifier2;
            }
            if (i4 != 0) {
                finiteAnimationSpecTween$default = AnimationSpecKt.tween$default(0, 0, null, 7, null);
            } else {
                finiteAnimationSpecTween$default = finiteAnimationSpec2;
            }
            if (i8 != 0) {
                str3 = "Crossfade";
            } else {
                str3 = str;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-513216493, i3, -1, "androidx.compose.animation.Crossfade (Crossfade.kt:55)");
            }
            Crossfade(androidx.compose.animation.core.TransitionKt.updateTransition(t, str3, composerStartRestartGroup, (i3 & 14) | ((i3 >> 6) & 112), 0), modifier4, finiteAnimationSpecTween$default, (Function1) null, function3, composerStartRestartGroup, i3 & 58352, 4);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            str2 = str3;
            modifier3 = modifier4;
            finiteAnimationSpec3 = finiteAnimationSpecTween$default;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            modifier3 = modifier2;
            finiteAnimationSpec3 = finiteAnimationSpec2;
            str2 = str;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.CrossfadeKt.Crossfade.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                public final void invoke(Composer composer2, int i11) {
                    CrossfadeKt.Crossfade(t, modifier3, finiteAnimationSpec3, str2, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((Composer) obj, ((Number) obj2).intValue());
                    return Unit.INSTANCE;
                }
            });
        }
    }
}
