package androidx.compose.animation;

import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.Transition;
import androidx.compose.animation.core.TwoWayConverter;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FloatCompanionObject;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "(Landroidx/compose/runtime/Composer;I)V"}, k = 3, mv = {2, 0, 0}, xi = 48)
public final class CrossfadeKt$Crossfade$5$1 extends Lambda implements Function2<Composer, Integer, Unit> {
    final /* synthetic */ FiniteAnimationSpec<Float> $animationSpec;
    final /* synthetic */ Function3<T, Composer, Integer, Unit> $content;
    final /* synthetic */ T $stateForContent;
    final /* synthetic */ Transition<T> $this_Crossfade;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public CrossfadeKt$Crossfade$5$1(Transition<T> transition, FiniteAnimationSpec<Float> finiteAnimationSpec, T t, Function3<? super T, ? super Composer, ? super Integer, Unit> function3) {
        super(2);
        this.$this_Crossfade = transition;
        this.$animationSpec = finiteAnimationSpec;
        this.$stateForContent = t;
        this.$content = function3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float invoke$lambda$1(State<Float> state) {
        return ((Number) state.getValue()).floatValue();
    }

    public final void invoke(Composer composer, int i) throws Throwable {
        Object currentState;
        if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
            composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-934471669, i, -1, "androidx.compose.animation.Crossfade.<anonymous>.<anonymous> (Crossfade.kt:125)");
        }
        final Transition<T> transition = this.$this_Crossfade;
        final FiniteAnimationSpec<Float> finiteAnimationSpec = this.$animationSpec;
        Function3<Transition.Segment<T>, Composer, Integer, FiniteAnimationSpec<Float>> function3 = new Function3<Transition.Segment<T>, Composer, Integer, FiniteAnimationSpec<Float>>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$5$1$alpha$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            public final FiniteAnimationSpec<Float> invoke(Transition.Segment<T> segment, Composer composer2, int i2) {
                composer2.startReplaceGroup(955869654);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(955869654, i2, -1, "androidx.compose.animation.Crossfade.<anonymous>.<anonymous>.<anonymous> (Crossfade.kt:126)");
                }
                FiniteAnimationSpec<Float> finiteAnimationSpec2 = finiteAnimationSpec;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                composer2.endReplaceGroup();
                return finiteAnimationSpec2;
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
                return invoke((Transition.Segment) obj, (Composer) obj2, ((Number) obj3).intValue());
            }
        };
        T t = this.$stateForContent;
        TwoWayConverter<Float, AnimationVector1D> vectorConverter = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
        if (transition.isSeeking()) {
            composer.startReplaceGroup(1666827533);
            composer.endReplaceGroup();
            currentState = transition.getCurrentState();
        } else {
            composer.startReplaceGroup(1666573488);
            boolean zChanged = composer.changed(transition);
            currentState = composer.rememberedValue();
            if (zChanged || currentState == Composer.Companion.getEmpty()) {
                Snapshot.Companion companion = Snapshot.Companion;
                Snapshot currentThreadSnapshot = companion.getCurrentThreadSnapshot();
                Function1 readObserver = currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null;
                Snapshot snapshotMakeCurrentNonObservable = companion.makeCurrentNonObservable(currentThreadSnapshot);
                try {
                    Object currentState2 = transition.getCurrentState();
                    companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
                    composer.updateRememberedValue(currentState2);
                    currentState = currentState2;
                } catch (Throwable th) {
                    companion.restoreNonObservable(currentThreadSnapshot, snapshotMakeCurrentNonObservable, readObserver);
                    throw th;
                }
            }
            composer.endReplaceGroup();
        }
        composer.startReplaceGroup(1378811975);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1378811975, 0, -1, "androidx.compose.animation.Crossfade.<anonymous>.<anonymous>.<anonymous> (Crossfade.kt:127)");
        }
        float f = Intrinsics.areEqual(currentState, t) ? 1.0f : 0.0f;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceGroup();
        Float fValueOf = Float.valueOf(f);
        boolean zChanged2 = composer.changed(transition);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged2 || objRememberedValue == Composer.Companion.getEmpty()) {
            objRememberedValue = SnapshotStateKt.derivedStateOf(new Function0<T>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$5$1$invoke$$inlined$animateFloat$1
                public final T invoke() {
                    return (T) transition.getTargetState();
                }
            });
            composer.updateRememberedValue(objRememberedValue);
        }
        Object value = ((State) objRememberedValue).getValue();
        composer.startReplaceGroup(1378811975);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1378811975, 0, -1, "androidx.compose.animation.Crossfade.<anonymous>.<anonymous>.<anonymous> (Crossfade.kt:127)");
        }
        float f2 = Intrinsics.areEqual(value, t) ? 1.0f : 0.0f;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceGroup();
        Float fValueOf2 = Float.valueOf(f2);
        boolean zChanged3 = composer.changed(transition);
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChanged3 || objRememberedValue2 == Composer.Companion.getEmpty()) {
            objRememberedValue2 = SnapshotStateKt.derivedStateOf(new Function0<Transition.Segment<T>>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$5$1$invoke$$inlined$animateFloat$2
                /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
                public final Transition.Segment<T> m96invoke() {
                    return transition.getSegment();
                }
            });
            composer.updateRememberedValue(objRememberedValue2);
        }
        final State stateCreateTransitionAnimation = androidx.compose.animation.core.TransitionKt.createTransitionAnimation(transition, fValueOf, fValueOf2, (FiniteAnimationSpec) function3.invoke(((State) objRememberedValue2).getValue(), composer, 0), vectorConverter, "FloatAnimation", composer, 0);
        Modifier.Companion companion2 = Modifier.Companion;
        boolean zChanged4 = composer.changed(stateCreateTransitionAnimation);
        Object objRememberedValue3 = composer.rememberedValue();
        if (zChanged4 || objRememberedValue3 == Composer.Companion.getEmpty()) {
            objRememberedValue3 = new Function1<GraphicsLayerScope, Unit>() { // from class: androidx.compose.animation.CrossfadeKt$Crossfade$5$1$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                public final void invoke(GraphicsLayerScope graphicsLayerScope) {
                    graphicsLayerScope.setAlpha(CrossfadeKt$Crossfade$5$1.invoke$lambda$1(stateCreateTransitionAnimation));
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    invoke((GraphicsLayerScope) obj);
                    return Unit.INSTANCE;
                }
            };
            composer.updateRememberedValue(objRememberedValue3);
        }
        Modifier modifierGraphicsLayer = GraphicsLayerModifierKt.graphicsLayer(companion2, (Function1) objRememberedValue3);
        Function3<T, Composer, Integer, Unit> function4 = this.$content;
        T t2 = this.$stateForContent;
        MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.getTopStart(), false);
        int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
        CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer, modifierGraphicsLayer);
        ComposeUiNode.Companion companion3 = ComposeUiNode.Companion;
        Function0 constructor = companion3.getConstructor();
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
        Updater.set-impl(composer2, measurePolicyMaybeCachedBoxMeasurePolicy, companion3.getSetMeasurePolicy());
        Updater.set-impl(composer2, currentCompositionLocalMap, companion3.getSetResolvedCompositionLocals());
        Updater.init-impl(composer2, Integer.valueOf(iHashCode), companion3.getSetCompositeKeyHash());
        Updater.reconcile-impl(composer2, companion3.getApplyOnDeactivatedNodeAssertion());
        Updater.set-impl(composer2, modifierMaterializeModifier, companion3.getSetModifier());
        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
        function4.invoke(t2, composer, 0);
        composer.endNode();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) throws Throwable {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return Unit.INSTANCE;
    }
}
