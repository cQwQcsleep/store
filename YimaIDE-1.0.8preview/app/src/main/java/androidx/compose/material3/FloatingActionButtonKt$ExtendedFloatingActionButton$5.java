package androidx.compose.material3;

import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.Transition;
import androidx.compose.animation.core.TransitionKt;
import androidx.compose.animation.core.TwoWayConverter;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.material3.tokens.MotionSchemeKeyTokens;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.layout.LayoutModifierKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.util.MathHelpersKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FloatCompanionObject;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class FloatingActionButtonKt$ExtendedFloatingActionButton$5 implements Function2<Composer, Integer, Unit> {
    final /* synthetic */ float $endPadding;
    final /* synthetic */ boolean $expanded;
    final /* synthetic */ Function2<Composer, Integer, Unit> $icon;
    final /* synthetic */ float $iconPadding;
    final /* synthetic */ float $minHeight;
    final /* synthetic */ float $minWidth;
    final /* synthetic */ float $startPadding;
    final /* synthetic */ Function2<Composer, Integer, Unit> $text;

    /* JADX WARN: Multi-variable type inference failed */
    public FloatingActionButtonKt$ExtendedFloatingActionButton$5(boolean z, float f, float f2, float f3, float f4, Function2<? super Composer, ? super Integer, Unit> function2, float f5, Function2<? super Composer, ? super Integer, Unit> function3) {
        this.$expanded = z;
        this.$minWidth = f;
        this.$minHeight = f2;
        this.$startPadding = f3;
        this.$endPadding = f4;
        this.$icon = function2;
        this.$iconPadding = f5;
        this.$text = function3;
    }

    public static Unit a(Placeable placeable, Placeable.PlacementScope placementScope) {
        Placeable.PlacementScope.place$default(placementScope, placeable, 0, 0, 0.0f, 4, null);
        return Unit.INSTANCE;
    }

    public static Unit b(State state, GraphicsLayerScope graphicsLayerScope) {
        graphicsLayerScope.setAlpha(((Number) state.getValue()).floatValue());
        return Unit.INSTANCE;
    }

    public static boolean c(Transition transition) {
        return ((Number) transition.getCurrentState()).floatValue() == 0.0f && !transition.isRunning();
    }

    public static Unit d(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        return Unit.INSTANCE;
    }

    public static MeasureResult e(float f, State state, MeasureScope measureScope, Measurable measurable, Constraints constraints) {
        int iLerp = MathHelpersKt.lerp(measureScope.mo4551roundToPx0680j_4(f), measurable.maxIntrinsicWidth(Constraints.m5974getMaxHeightimpl(constraints.getValue())), ((Number) state.getValue()).floatValue());
        final Placeable placeableMo4605measureBRTryo0 = measurable.mo4605measureBRTryo0(constraints.getValue());
        return MeasureScope.layout$default(measureScope, iLerp, placeableMo4605measureBRTryo0.getHeight(), null, new Function1() { // from class: androidx.compose.material3.z1
            public final Object invoke(Object obj) {
                return FloatingActionButtonKt$ExtendedFloatingActionButton$5.a(placeableMo4605measureBRTryo0, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    public final void invoke(Composer composer, int i) {
        if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
            composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-827388388, i, -1, "androidx.compose.material3.ExtendedFloatingActionButton.<anonymous> (FloatingActionButton.kt:464)");
        }
        final Transition transitionUpdateTransition = TransitionKt.updateTransition(Float.valueOf(this.$expanded ? 1.0f : 0.0f), "expanded state", composer, 48, 0);
        final FiniteAnimationSpec finiteAnimationSpecValue = MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composer, 6);
        final FiniteAnimationSpec finiteAnimationSpecValue2 = MotionSchemeKt.value(MotionSchemeKeyTokens.FastEffects, composer, 6);
        Function3<Transition.Segment<Float>, Composer, Integer, FiniteAnimationSpec<Float>> function3 = new Function3<Transition.Segment<Float>, Composer, Integer, FiniteAnimationSpec<Float>>() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$5$expandedWidthProgress$1
            public final FiniteAnimationSpec<Float> invoke(Transition.Segment<Float> segment, Composer composer2, int i2) {
                composer2.startReplaceGroup(-1114419602);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1114419602, i2, -1, "androidx.compose.material3.ExtendedFloatingActionButton.<anonymous>.<anonymous> (FloatingActionButton.kt:469)");
                }
                FiniteAnimationSpec<Float> finiteAnimationSpec = finiteAnimationSpecValue;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                composer2.endReplaceGroup();
                return finiteAnimationSpec;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ FiniteAnimationSpec<Float> invoke(Transition.Segment<Float> segment, Composer composer2, Integer num) {
                return invoke(segment, composer2, num.intValue());
            }
        };
        FloatCompanionObject floatCompanionObject = FloatCompanionObject.INSTANCE;
        TwoWayConverter vectorConverter = VectorConvertersKt.getVectorConverter(floatCompanionObject);
        float fFloatValue = ((Number) transitionUpdateTransition.getCurrentState()).floatValue();
        composer.startReplaceGroup(-157343033);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-157343033, 0, -1, "androidx.compose.material3.ExtendedFloatingActionButton.<anonymous>.<anonymous> (FloatingActionButton.kt:469)");
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceGroup();
        Float fValueOf = Float.valueOf(fFloatValue);
        float fFloatValue2 = ((Number) transitionUpdateTransition.getTargetState()).floatValue();
        composer.startReplaceGroup(-157343033);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-157343033, 0, -1, "androidx.compose.material3.ExtendedFloatingActionButton.<anonymous>.<anonymous> (FloatingActionButton.kt:469)");
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceGroup();
        final State stateCreateTransitionAnimation = TransitionKt.createTransitionAnimation(transitionUpdateTransition, fValueOf, Float.valueOf(fFloatValue2), function3.invoke(transitionUpdateTransition.getSegment(), composer, 0), vectorConverter, "FloatAnimation", composer, 0);
        Function3<Transition.Segment<Float>, Composer, Integer, FiniteAnimationSpec<Float>> function4 = new Function3<Transition.Segment<Float>, Composer, Integer, FiniteAnimationSpec<Float>>() { // from class: androidx.compose.material3.FloatingActionButtonKt$ExtendedFloatingActionButton$5$expandedAlphaProgress$1
            public final FiniteAnimationSpec<Float> invoke(Transition.Segment<Float> segment, Composer composer2, int i2) {
                composer2.startReplaceGroup(-781713402);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-781713402, i2, -1, "androidx.compose.material3.ExtendedFloatingActionButton.<anonymous>.<anonymous> (FloatingActionButton.kt:471)");
                }
                FiniteAnimationSpec<Float> finiteAnimationSpec = finiteAnimationSpecValue2;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                composer2.endReplaceGroup();
                return finiteAnimationSpec;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ FiniteAnimationSpec<Float> invoke(Transition.Segment<Float> segment, Composer composer2, Integer num) {
                return invoke(segment, composer2, num.intValue());
            }
        };
        TwoWayConverter vectorConverter2 = VectorConvertersKt.getVectorConverter(floatCompanionObject);
        float fFloatValue3 = ((Number) transitionUpdateTransition.getCurrentState()).floatValue();
        composer.startReplaceGroup(175363167);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(175363167, 0, -1, "androidx.compose.material3.ExtendedFloatingActionButton.<anonymous>.<anonymous> (FloatingActionButton.kt:471)");
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceGroup();
        Float fValueOf2 = Float.valueOf(fFloatValue3);
        float fFloatValue4 = ((Number) transitionUpdateTransition.getTargetState()).floatValue();
        composer.startReplaceGroup(175363167);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(175363167, 0, -1, "androidx.compose.material3.ExtendedFloatingActionButton.<anonymous>.<anonymous> (FloatingActionButton.kt:471)");
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceGroup();
        final State stateCreateTransitionAnimation2 = TransitionKt.createTransitionAnimation(transitionUpdateTransition, fValueOf2, Float.valueOf(fFloatValue4), function4.invoke(transitionUpdateTransition.getSegment(), composer, 0), vectorConverter2, "FloatAnimation", composer, 0);
        Modifier.Companion companion = Modifier.INSTANCE;
        boolean zChanged = composer.changed(this.$minWidth) | composer.changed(stateCreateTransitionAnimation);
        final float f = this.$minWidth;
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new Function3() { // from class: androidx.compose.material3.v1
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return FloatingActionButtonKt$ExtendedFloatingActionButton$5.e(f, stateCreateTransitionAnimation, (MeasureScope) obj, (Measurable) obj2, (Constraints) obj3);
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        Modifier modifier = PaddingKt.padding-qDBjuR0$default(SizeKt.sizeIn-qDBjuR0$default(LayoutModifierKt.layout(companion, (Function3) objRememberedValue), this.$minWidth, this.$minHeight, 0.0f, 0.0f, 12, (Object) null), this.$startPadding, 0.0f, this.$endPadding, 0.0f, 10, (Object) null);
        Alignment.Companion companion2 = Alignment.INSTANCE;
        Alignment.Vertical centerVertically = companion2.getCenterVertically();
        Function2<Composer, Integer, Unit> function2 = this.$icon;
        float f2 = this.$iconPadding;
        Function2<Composer, Integer, Unit> function5 = this.$text;
        Arrangement arrangement = Arrangement.INSTANCE;
        MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(arrangement.getStart(), centerVertically, composer, 48);
        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
        CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer, modifier);
        ComposeUiNode.Companion companion3 = ComposeUiNode.INSTANCE;
        Function0<ComposeUiNode> constructor = companion3.getConstructor();
        if (composer.getApplier() == null) {
            ComposablesKt.invalidApplier();
        }
        composer.startReusableNode();
        if (composer.getInserting()) {
            composer.createNode(constructor);
        } else {
            composer.useNode();
        }
        Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer);
        Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion3.getSetMeasurePolicy());
        Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion3.getSetResolvedCompositionLocals());
        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion3.getSetCompositeKeyHash();
        if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
        }
        Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion3.getSetModifier());
        RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
        function2.invoke(composer, 0);
        boolean zChanged2 = composer.changed(transitionUpdateTransition);
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue2 = SnapshotStateKt.derivedStateOf(new Function0() { // from class: androidx.compose.material3.w1
                public final Object invoke() {
                    return Boolean.valueOf(FloatingActionButtonKt$ExtendedFloatingActionButton$5.c(transitionUpdateTransition));
                }
            });
            composer.updateRememberedValue(objRememberedValue2);
        }
        if (((Boolean) ((State) objRememberedValue2).getValue()).booleanValue()) {
            composer.startReplaceGroup(65953058);
            composer.endReplaceGroup();
        } else {
            composer.startReplaceGroup(65675329);
            Object objRememberedValue3 = composer.rememberedValue();
            Composer.Companion companion4 = Composer.INSTANCE;
            if (objRememberedValue3 == companion4.getEmpty()) {
                objRememberedValue3 = new Function1() { // from class: androidx.compose.material3.x1
                    public final Object invoke(Object obj) {
                        return FloatingActionButtonKt$ExtendedFloatingActionButton$5.d((SemanticsPropertyReceiver) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue3);
            }
            Modifier modifierClearAndSetSemantics = SemanticsModifierKt.clearAndSetSemantics(companion, (Function1) objRememberedValue3);
            boolean zChanged3 = composer.changed(stateCreateTransitionAnimation2);
            Object objRememberedValue4 = composer.rememberedValue();
            if (zChanged3 || objRememberedValue4 == companion4.getEmpty()) {
                objRememberedValue4 = new Function1() { // from class: androidx.compose.material3.y1
                    public final Object invoke(Object obj) {
                        return FloatingActionButtonKt$ExtendedFloatingActionButton$5.b(stateCreateTransitionAnimation2, (GraphicsLayerScope) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue4);
            }
            Modifier modifierGraphicsLayer = GraphicsLayerModifierKt.graphicsLayer(modifierClearAndSetSemantics, (Function1) objRememberedValue4);
            MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(arrangement.getStart(), companion2.getTop(), composer, 0);
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composer, modifierGraphicsLayer);
            Function0<ComposeUiNode> constructor2 = companion3.getConstructor();
            if (composer.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
                composer.createNode(constructor2);
            } else {
                composer.useNode();
            }
            Composer composerM2388constructorimpl2 = Updater.m2388constructorimpl(composer);
            Updater.m2396setimpl(composerM2388constructorimpl2, measurePolicyRowMeasurePolicy2, companion3.getSetMeasurePolicy());
            Updater.m2396setimpl(composerM2388constructorimpl2, currentCompositionLocalMap2, companion3.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = companion3.getSetCompositeKeyHash();
            if (composerM2388constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                composerM2388constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                composerM2388constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
            }
            Updater.m2396setimpl(composerM2388constructorimpl2, modifierMaterializeModifier2, companion3.getSetModifier());
            SpacerKt.Spacer(SizeKt.width-3ABfNKs(companion, f2), composer, 0);
            function5.invoke(composer, 0);
            composer.endNode();
            composer.endReplaceGroup();
        }
        composer.endNode();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return Unit.INSTANCE;
    }
}
