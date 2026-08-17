package androidx.compose.material3;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimatableKt;
import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.layout.WindowInsetsPadding_androidKt;
import androidx.compose.material3.DefaultModalWideNavigationRailOverride;
import androidx.compose.material3.tokens.MotionSchemeKeyTokens;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MovableContentKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Density;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0011\u0010\u0004\u001a\u00020\u0005*\u00020\u0006H\u0017¢\u0006\u0002\u0010\u0007¨\u0006\b²\u0006\n\u0010\t\u001a\u00020\nX\u008a\u0084\u0002²\u0006\n\u0010\u000b\u001a\u00020\nX\u008a\u0084\u0002"}, d2 = {"Landroidx/compose/material3/DefaultModalWideNavigationRailOverride;", "Landroidx/compose/material3/ModalWideNavigationRailOverride;", "<init>", "()V", "ModalWideNavigationRail", "", "Landroidx/compose/material3/ModalWideNavigationRailOverrideScope;", "(Landroidx/compose/material3/ModalWideNavigationRailOverrideScope;Landroidx/compose/runtime/Composer;I)V", "material3", "isCollapsed", "", "modalExpanded"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class DefaultModalWideNavigationRailOverride implements ModalWideNavigationRailOverride {
    public static final int $stable = 0;
    public static final DefaultModalWideNavigationRailOverride INSTANCE = new DefaultModalWideNavigationRailOverride();

    private DefaultModalWideNavigationRailOverride() {
    }

    private static final boolean ModalWideNavigationRail$lambda$4(State<Boolean> state) {
        return state.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean ModalWideNavigationRail$lambda$7(State<Boolean> state) {
        return state.getValue().booleanValue();
    }

    public static boolean a(State state) {
        return ((Number) state.getValue()).floatValue() >= 0.3f;
    }

    public static Unit b(DefaultModalWideNavigationRailOverride defaultModalWideNavigationRailOverride, ModalWideNavigationRailOverrideScope modalWideNavigationRailOverrideScope, int i, Composer composer, int i2) {
        defaultModalWideNavigationRailOverride.ModalWideNavigationRail(modalWideNavigationRailOverrideScope, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static Unit c(CoroutineScope coroutineScope, Animatable animatable, float f) {
        BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new DefaultModalWideNavigationRailOverride$ModalWideNavigationRail$5$1$1(animatable, f, null), 3, (Object) null);
        return Unit.INSTANCE;
    }

    public static boolean d(State state) {
        return ((Number) state.getValue()).floatValue() == 0.0f;
    }

    public static Unit e(CoroutineScope coroutineScope, Animatable animatable) {
        BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new DefaultModalWideNavigationRailOverride$ModalWideNavigationRail$6$1$1(animatable, null), 3, (Object) null);
        return Unit.INSTANCE;
    }

    public static Unit f(Channel channel, ModalWideNavigationRailOverrideScope modalWideNavigationRailOverrideScope) {
        channel.trySend-JP2dKIU(Boolean.valueOf(WideNavigationRailStateKt.isExpanded(modalWideNavigationRailOverrideScope.getState().getTargetValue())));
        return Unit.INSTANCE;
    }

    public static Unit g(CoroutineScope coroutineScope, ModalWideNavigationRailOverrideScope modalWideNavigationRailOverrideScope) {
        BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new DefaultModalWideNavigationRailOverride$ModalWideNavigationRail$4$1$1(modalWideNavigationRailOverrideScope, null), 3, (Object) null);
        return Unit.INSTANCE;
    }

    @Override // androidx.compose.material3.ModalWideNavigationRailOverride
    public void ModalWideNavigationRail(final ModalWideNavigationRailOverrideScope modalWideNavigationRailOverrideScope, Composer composer, final int i) {
        int i2;
        final ModalWideNavigationRailOverrideScope modalWideNavigationRailOverrideScope2;
        Function2<Composer, Integer, Unit> content;
        int i3;
        BufferOverflow bufferOverflow;
        boolean z;
        Composer composerStartRestartGroup = composer.startRestartGroup(1751235721);
        if ((i & 6) == 0) {
            i2 = ((i & 8) == 0 ? composerStartRestartGroup.changed(modalWideNavigationRailOverrideScope) : composerStartRestartGroup.changedInstance(modalWideNavigationRailOverrideScope) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if (composerStartRestartGroup.shouldExecute((i2 & 3) != 2, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1751235721, i2, -1, "androidx.compose.material3.DefaultModalWideNavigationRailOverride.ModalWideNavigationRail (WideNavigationRail.kt:503)");
            }
            if (modalWideNavigationRailOverrideScope.getShouldHideOnCollapse()) {
                composerStartRestartGroup.startReplaceGroup(95781714);
                composerStartRestartGroup.endReplaceGroup();
                content = modalWideNavigationRailOverrideScope.getContent();
            } else {
                composerStartRestartGroup.startReplaceGroup(95826602);
                boolean zChanged = composerStartRestartGroup.changed(modalWideNavigationRailOverrideScope.getContent());
                Object objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = MovableContentKt.movableContentOf(modalWideNavigationRailOverrideScope.getContent());
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                content = (Function2) objRememberedValue;
                composerStartRestartGroup.endReplaceGroup();
            }
            final Function2<Composer, Integer, Unit> function2 = content;
            Density density = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
            FiniteAnimationSpec finiteAnimationSpecValue = MotionSchemeKt.value(MotionSchemeKeyTokens.DefaultSpatial, composerStartRestartGroup, 6);
            boolean zChanged2 = composerStartRestartGroup.changed(modalWideNavigationRailOverrideScope.getState());
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = new ModalWideNavigationRailState(modalWideNavigationRailOverrideScope.getState(), density, finiteAnimationSpecValue);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            final ModalWideNavigationRailState modalWideNavigationRailState = (ModalWideNavigationRailState) objRememberedValue2;
            final State stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(!WideNavigationRailStateKt.isExpanded(modalWideNavigationRailOverrideScope.getState().getTargetValue()) ? 0.0f : 1.0f, MotionSchemeKt.value(MotionSchemeKeyTokens.DefaultEffects, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            Composer.Companion companion = Composer.INSTANCE;
            if (objRememberedValue3 == companion.getEmpty()) {
                objRememberedValue3 = SnapshotStateKt.derivedStateOf(new Function0() { // from class: wh3
                    public final Object invoke() {
                        return Boolean.valueOf(DefaultModalWideNavigationRailOverride.d(stateAnimateFloatAsState));
                    }
                });
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            State state = (State) objRememberedValue3;
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue4 == companion.getEmpty()) {
                objRememberedValue4 = SnapshotStateKt.derivedStateOf(new Function0() { // from class: xh3
                    public final Object invoke() {
                        return Boolean.valueOf(DefaultModalWideNavigationRailOverride.a(stateAnimateFloatAsState));
                    }
                });
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            }
            final State state2 = (State) objRememberedValue4;
            int i4 = i2 & 14;
            boolean zChangedInstance = (i4 == 4 || ((i2 & 8) != 0 && composerStartRestartGroup.changedInstance(modalWideNavigationRailOverrideScope))) | composerStartRestartGroup.changedInstance(modalWideNavigationRailState);
            Object objRememberedValue5 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || objRememberedValue5 == companion.getEmpty()) {
                objRememberedValue5 = new DefaultModalWideNavigationRailOverride$ModalWideNavigationRail$animateToDismiss$1$1(modalWideNavigationRailOverrideScope, modalWideNavigationRailState, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
            }
            final Function1 function1 = (Function1) objRememberedValue5;
            boolean zChangedInstance2 = (i4 == 4 || ((i2 & 8) != 0 && composerStartRestartGroup.changedInstance(modalWideNavigationRailOverrideScope))) | composerStartRestartGroup.changedInstance(modalWideNavigationRailState);
            Object objRememberedValue6 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance2 || objRememberedValue6 == companion.getEmpty()) {
                objRememberedValue6 = new DefaultModalWideNavigationRailOverride$ModalWideNavigationRail$settleToDismiss$1$1(modalWideNavigationRailOverrideScope, modalWideNavigationRailState, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
            }
            final Function2 function3 = (Function2) objRememberedValue6;
            if (modalWideNavigationRailOverrideScope.getShouldHideOnCollapse() || !ModalWideNavigationRail$lambda$4(state)) {
                i3 = i4;
                bufferOverflow = null;
                composerStartRestartGroup.startReplaceGroup(97788313);
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(97400069);
                i3 = i4;
                bufferOverflow = null;
                WideNavigationRailKt.WideNavigationRailLayout(modalWideNavigationRailOverrideScope.getModifier(), false, false, modalWideNavigationRailOverrideScope.getColors(), modalWideNavigationRailOverrideScope.getCollapsedShape(), modalWideNavigationRailOverrideScope.getHeader(), modalWideNavigationRailOverrideScope.getWindowInsets(), modalWideNavigationRailOverrideScope.getArrangement(), function2, composerStartRestartGroup, 432);
                composerStartRestartGroup = composerStartRestartGroup;
                composerStartRestartGroup.endReplaceGroup();
            }
            Object objRememberedValue7 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue7 == companion.getEmpty()) {
                objRememberedValue7 = ChannelKt.Channel$default(-1, bufferOverflow, bufferOverflow, 6, bufferOverflow);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue7);
            }
            final Channel channel = (Channel) objRememberedValue7;
            if (modalWideNavigationRailOverrideScope.getShouldHideOnCollapse()) {
                composerStartRestartGroup.startReplaceGroup(97908438);
                boolean zChangedInstance3 = composerStartRestartGroup.changedInstance(channel) | composerStartRestartGroup.changedInstance(modalWideNavigationRailState);
                Object objRememberedValue8 = composerStartRestartGroup.rememberedValue();
                if (zChangedInstance3 || objRememberedValue8 == companion.getEmpty()) {
                    objRememberedValue8 = new DefaultModalWideNavigationRailOverride$ModalWideNavigationRail$1$1(channel, modalWideNavigationRailState, bufferOverflow);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue8);
                }
                EffectsKt.LaunchedEffect(channel, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue8, composerStartRestartGroup, 0);
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(98341849);
                composerStartRestartGroup.endReplaceGroup();
            }
            if (ModalWideNavigationRail$lambda$4(state)) {
                modalWideNavigationRailOverrideScope2 = modalWideNavigationRailOverrideScope;
                composerStartRestartGroup.startReplaceGroup(101334713);
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(98512256);
                if (modalWideNavigationRailOverrideScope.getShouldHideOnCollapse()) {
                    z = true;
                    composerStartRestartGroup.startReplaceGroup(98809081);
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(98472731);
                    Modifier modifier = BackgroundKt.background-bw27NRU(Modifier.INSTANCE, modalWideNavigationRailOverrideScope.getColors().getContainerColor(), modalWideNavigationRailOverrideScope.getCollapsedShape());
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                    int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier);
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
                    BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                    z = true;
                    SpacerKt.Spacer(SizeKt.fillMaxHeight$default(SizeKt.widthIn-VpY3zN4$default(modalWideNavigationRailOverrideScope.getModifier(), WideNavigationRailKt.CollapsedRailWidth, 0.0f, 2, bufferOverflow), 0.0f, 1, bufferOverflow), composerStartRestartGroup, 0);
                    composerStartRestartGroup.endNode();
                    composerStartRestartGroup.endReplaceGroup();
                }
                Object objRememberedValue9 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue9 == companion.getEmpty()) {
                    objRememberedValue9 = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue9);
                }
                final CoroutineScope coroutineScope = (CoroutineScope) objRememberedValue9;
                Object objRememberedValue10 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue10 == companion.getEmpty()) {
                    objRememberedValue10 = AnimatableKt.Animatable$default(0.0f, 0.0f, 2, bufferOverflow);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue10);
                }
                final Animatable animatable = (Animatable) objRememberedValue10;
                Object objRememberedValue11 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue11 == companion.getEmpty()) {
                    objRememberedValue11 = new RailPredictiveBackState();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue11);
                }
                final RailPredictiveBackState railPredictiveBackState = (RailPredictiveBackState) objRememberedValue11;
                int i5 = i3;
                boolean zChangedInstance4 = composerStartRestartGroup.changedInstance(channel) | ((i5 == 4 || ((i2 & 8) != 0 && composerStartRestartGroup.changedInstance(modalWideNavigationRailOverrideScope))) ? z : false);
                Object objRememberedValue12 = composerStartRestartGroup.rememberedValue();
                if (zChangedInstance4 || objRememberedValue12 == companion.getEmpty()) {
                    objRememberedValue12 = new Function0() { // from class: yh3
                        public final Object invoke() {
                            return DefaultModalWideNavigationRailOverride.f(channel, modalWideNavigationRailOverrideScope);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue12);
                }
                boolean z2 = false;
                EffectsKt.SideEffect((Function0) objRememberedValue12, composerStartRestartGroup, 0);
                ModalWideNavigationRailProperties expandedProperties = modalWideNavigationRailOverrideScope.getExpandedProperties();
                boolean zChangedInstance5 = composerStartRestartGroup.changedInstance(coroutineScope);
                if (i5 == 4 || ((i2 & 8) != 0 && composerStartRestartGroup.changedInstance(modalWideNavigationRailOverrideScope))) {
                    z2 = z;
                }
                boolean z3 = zChangedInstance5 | z2;
                Object objRememberedValue13 = composerStartRestartGroup.rememberedValue();
                if (z3 || objRememberedValue13 == companion.getEmpty()) {
                    objRememberedValue13 = new Function0() { // from class: zh3
                        public final Object invoke() {
                            return DefaultModalWideNavigationRailOverride.g(coroutineScope, modalWideNavigationRailOverrideScope);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue13);
                }
                Function0 function0 = (Function0) objRememberedValue13;
                boolean zChangedInstance6 = composerStartRestartGroup.changedInstance(coroutineScope) | composerStartRestartGroup.changedInstance(animatable);
                Object objRememberedValue14 = composerStartRestartGroup.rememberedValue();
                if (zChangedInstance6 || objRememberedValue14 == companion.getEmpty()) {
                    objRememberedValue14 = new Function1() { // from class: ai3
                        public final Object invoke(Object obj) {
                            return DefaultModalWideNavigationRailOverride.c(coroutineScope, animatable, ((Float) obj).floatValue());
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue14);
                }
                Function1 function4 = (Function1) objRememberedValue14;
                boolean zChangedInstance7 = composerStartRestartGroup.changedInstance(coroutineScope) | composerStartRestartGroup.changedInstance(animatable);
                Object objRememberedValue15 = composerStartRestartGroup.rememberedValue();
                if (zChangedInstance7 || objRememberedValue15 == companion.getEmpty()) {
                    objRememberedValue15 = new Function0() { // from class: bi3
                        public final Object invoke() {
                            return DefaultModalWideNavigationRailOverride.e(coroutineScope, animatable);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue15);
                }
                Function2<Composer, Integer, Unit> function5 = new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DefaultModalWideNavigationRailOverride.ModalWideNavigationRail.7
                    public final void invoke(Composer composer2, int i6) {
                        boolean zModalWideNavigationRail$lambda$7;
                        if (!composer2.shouldExecute((i6 & 3) != 2, i6 & 1)) {
                            composer2.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1345045690, i6, -1, "androidx.compose.material3.DefaultModalWideNavigationRailOverride.ModalWideNavigationRail.<anonymous> (WideNavigationRail.kt:600)");
                        }
                        Modifier modifierImePadding = WindowInsetsPadding_androidKt.imePadding(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, (Object) null));
                        final ModalWideNavigationRailOverrideScope modalWideNavigationRailOverrideScope3 = modalWideNavigationRailOverrideScope;
                        ModalWideNavigationRailState modalWideNavigationRailState2 = modalWideNavigationRailState;
                        Function1<Continuation<? super Unit>, Object> function6 = function1;
                        Animatable<Float, AnimationVector1D> animatable2 = animatable;
                        RailPredictiveBackState railPredictiveBackState2 = railPredictiveBackState;
                        Function2<Float, Continuation<? super Unit>, Object> function7 = function3;
                        Function2<Composer, Integer, Unit> function8 = function2;
                        State<Boolean> state3 = state2;
                        MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                        int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                        CompositionLocalMap currentCompositionLocalMap2 = composer2.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composer2, modifierImePadding);
                        ComposeUiNode.Companion companion3 = ComposeUiNode.INSTANCE;
                        Function0<ComposeUiNode> constructor2 = companion3.getConstructor();
                        if (composer2.getApplier() == null) {
                            ComposablesKt.invalidApplier();
                        }
                        composer2.startReusableNode();
                        if (composer2.getInserting()) {
                            composer2.createNode(constructor2);
                        } else {
                            composer2.useNode();
                        }
                        Composer composerM2388constructorimpl2 = Updater.m2388constructorimpl(composer2);
                        Updater.m2396setimpl(composerM2388constructorimpl2, measurePolicyMaybeCachedBoxMeasurePolicy2, companion3.getSetMeasurePolicy());
                        Updater.m2396setimpl(composerM2388constructorimpl2, currentCompositionLocalMap2, companion3.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = companion3.getSetCompositeKeyHash();
                        if (composerM2388constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                            composerM2388constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                            composerM2388constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                        }
                        Updater.m2396setimpl(composerM2388constructorimpl2, modifierMaterializeModifier2, companion3.getSetModifier());
                        BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                        if (modalWideNavigationRailOverrideScope3.getShouldHideOnCollapse()) {
                            zModalWideNavigationRail$lambda$7 = modalWideNavigationRailState2.getTargetValue() != WideNavigationRailValue.Collapsed;
                        } else {
                            zModalWideNavigationRail$lambda$7 = DefaultModalWideNavigationRailOverride.ModalWideNavigationRail$lambda$7(state3);
                        }
                        WideNavigationRailKt.m1338Scrim3JVO9M(modalWideNavigationRailOverrideScope3.getColors().getModalScrimColor(), function6, zModalWideNavigationRail$lambda$7, composer2, 0);
                        WideNavigationRailKt.m1337ModalWideNavigationRailContentpU6N4AM(modalWideNavigationRailOverrideScope3.getShouldHideOnCollapse() || DefaultModalWideNavigationRailOverride.ModalWideNavigationRail$lambda$7(state3), modalWideNavigationRailOverrideScope3.getShouldHideOnCollapse(), animatable2, railPredictiveBackState2, function7, modalWideNavigationRailOverrideScope3.getModifier(), modalWideNavigationRailState2, modalWideNavigationRailOverrideScope3.getColors(), modalWideNavigationRailOverrideScope3.getExpandedShape(), WideNavigationRailKt.ExpandedRailMaxWidth, ComposableLambdaKt.rememberComposableLambda(208840989, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DefaultModalWideNavigationRailOverride$ModalWideNavigationRail$7$1$1
                            public final void invoke(Composer composer3, int i7) {
                                if (!composer3.shouldExecute((i7 & 3) != 2, i7 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(208840989, i7, -1, "androidx.compose.material3.DefaultModalWideNavigationRailOverride.ModalWideNavigationRail.<anonymous>.<anonymous>.<anonymous> (WideNavigationRail.kt:626)");
                                }
                                Modifier modifier2 = PaddingKt.padding-qDBjuR0$default(Modifier.INSTANCE, 0.0f, modalWideNavigationRailOverrideScope3.getExpandedHeaderTopPadding(), 0.0f, 0.0f, 13, (Object) null);
                                ModalWideNavigationRailOverrideScope modalWideNavigationRailOverrideScope4 = modalWideNavigationRailOverrideScope3;
                                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy3 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                                int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                                CompositionLocalMap currentCompositionLocalMap3 = composer3.getCurrentCompositionLocalMap();
                                Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composer3, modifier2);
                                ComposeUiNode.Companion companion4 = ComposeUiNode.INSTANCE;
                                Function0<ComposeUiNode> constructor3 = companion4.getConstructor();
                                if (composer3.getApplier() == null) {
                                    ComposablesKt.invalidApplier();
                                }
                                composer3.startReusableNode();
                                if (composer3.getInserting()) {
                                    composer3.createNode(constructor3);
                                } else {
                                    composer3.useNode();
                                }
                                Composer composerM2388constructorimpl3 = Updater.m2388constructorimpl(composer3);
                                Updater.m2396setimpl(composerM2388constructorimpl3, measurePolicyMaybeCachedBoxMeasurePolicy3, companion4.getSetMeasurePolicy());
                                Updater.m2396setimpl(composerM2388constructorimpl3, currentCompositionLocalMap3, companion4.getSetResolvedCompositionLocals());
                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = companion4.getSetCompositeKeyHash();
                                if (composerM2388constructorimpl3.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                                    composerM2388constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                                    composerM2388constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
                                }
                                Updater.m2396setimpl(composerM2388constructorimpl3, modifierMaterializeModifier3, companion4.getSetModifier());
                                BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
                                Function2<Composer, Integer, Unit> header = modalWideNavigationRailOverrideScope4.getHeader();
                                if (header == null) {
                                    composer3.startReplaceGroup(-1627801290);
                                } else {
                                    composer3.startReplaceGroup(-2130719701);
                                    header.invoke(composer3, 0);
                                }
                                composer3.endReplaceGroup();
                                composer3.endNode();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composer2, 54), modalWideNavigationRailOverrideScope3.getWindowInsets(), modalWideNavigationRailOverrideScope3.getShouldHideOnCollapse(), modalWideNavigationRailOverrideScope3.getArrangement(), function8, composer2, (Animatable.$stable << 6) | 805309440, 6);
                        composer2.endNode();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                };
                modalWideNavigationRailOverrideScope2 = modalWideNavigationRailOverrideScope;
                WideNavigationRail_androidKt.ModalWideNavigationRailDialog(function0, expandedProperties, function4, (Function0) objRememberedValue15, railPredictiveBackState, ComposableLambdaKt.rememberComposableLambda(1345045690, z, function5, composerStartRestartGroup, 54), composerStartRestartGroup, 221184);
                composerStartRestartGroup.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            modalWideNavigationRailOverrideScope2 = modalWideNavigationRailOverrideScope;
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ci3
                public final Object invoke(Object obj, Object obj2) {
                    return DefaultModalWideNavigationRailOverride.b(this.b, modalWideNavigationRailOverrideScope2, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
