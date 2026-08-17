package androidx.compose.animation.core;

import androidx.compose.animation.core.InfiniteTransition;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotMutationPolicy;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.collection.MutableVector;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u000b\b\u0007\u0018\u00002\u00020\u0001:\u0001(B\u0011\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J!\u0010\u001d\u001a\u00020\u001e2\u0012\u0010\u001f\u001a\u000e\u0012\u0002\b\u0003\u0012\u0002\b\u00030\nR\u00020\u0000H\u0000¢\u0006\u0002\b J!\u0010!\u001a\u00020\u001e2\u0012\u0010\u001f\u001a\u000e\u0012\u0002\b\u0003\u0012\u0002\b\u00030\nR\u00020\u0000H\u0000¢\u0006\u0002\b\"J\u000f\u0010#\u001a\u00020\u001eH\u0001¢\u0006\u0004\b$\u0010%J\u0010\u0010&\u001a\u00020\u001e2\u0006\u0010'\u001a\u00020\u0015H\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R \u0010\b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0002\b\u0003\u0012\u0002\b\u00030\nR\u00020\u00000\tX\u0082\u0004¢\u0006\u0002\n\u0000R+\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010\u0016\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0018\u0010\u0013\u001a\u0004\b\u0016\u0010\u000f\"\u0004\b\u0017\u0010\u0011R#\u0010\u0019\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0002\b\u0003\u0012\u0002\b\u00030\nR\u00020\u00000\u001a8F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001c¨\u0006)"}, d2 = {"Landroidx/compose/animation/core/InfiniteTransition;", "", "label", "", "<init>", "(Ljava/lang/String;)V", "getLabel", "()Ljava/lang/String;", "_animations", "Landroidx/compose/runtime/collection/MutableVector;", "Landroidx/compose/animation/core/InfiniteTransition$TransitionAnimationState;", "<set-?>", "", "refreshChildNeeded", "getRefreshChildNeeded", "()Z", "setRefreshChildNeeded", "(Z)V", "refreshChildNeeded$delegate", "Landroidx/compose/runtime/MutableState;", "startTimeNanos", "", "isRunning", "setRunning", "isRunning$delegate", "animations", "", "getAnimations", "()Ljava/util/List;", "addAnimation", "", "animation", "addAnimation$animation_core", "removeAnimation", "removeAnimation$animation_core", "run", "run$animation_core", "(Landroidx/compose/runtime/Composer;I)V", "onFrame", "playTimeNanos", "TransitionAnimationState", "animation-core"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class InfiniteTransition {
    public static final int $stable = 8;
    private final String label;
    private final MutableVector<TransitionAnimationState<?, ?>> _animations = new MutableVector<>(new TransitionAnimationState[16], 0);

    /* JADX INFO: renamed from: refreshChildNeeded$delegate, reason: from kotlin metadata */
    private final MutableState refreshChildNeeded = SnapshotStateKt.mutableStateOf$default(Boolean.FALSE, (SnapshotMutationPolicy) null, 2, (Object) null);
    private long startTimeNanos = Long.MIN_VALUE;

    /* JADX INFO: renamed from: isRunning$delegate, reason: from kotlin metadata */
    private final MutableState isRunning = SnapshotStateKt.mutableStateOf$default(Boolean.TRUE, (SnapshotMutationPolicy) null, 2, (Object) null);

    @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\n\b\u0086\u0004\u0018\u0000*\u0004\b\u0000\u0010\u0001*\b\b\u0001\u0010\u0002*\u00020\u00032\b\u0012\u0004\u0012\u0002H\u00010\u0004BC\b\u0000\u0012\u0006\u0010\u0005\u001a\u00028\u0000\u0012\u0006\u0010\u0006\u001a\u00028\u0000\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eJ-\u00101\u001a\u0002022\u0006\u0010\u0005\u001a\u00028\u00002\u0006\u0010\u0006\u001a\u00028\u00002\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\nH\u0000¢\u0006\u0004\b3\u00104J\u0015\u00105\u001a\u0002022\u0006\u00106\u001a\u000200H\u0000¢\u0006\u0002\b7J\r\u00108\u001a\u000202H\u0000¢\u0006\u0002\b9J\r\u0010:\u001a\u000202H\u0000¢\u0006\u0002\b;R\u001c\u0010\u0005\u001a\u00028\u0000X\u0080\u000e¢\u0006\u0010\n\u0002\u0010\u0013\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0006\u001a\u00028\u0000X\u0080\u000e¢\u0006\u0010\n\u0002\u0010\u0013\u001a\u0004\b\u0014\u0010\u0010\"\u0004\b\u0015\u0010\u0012R\u001d\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R+\u0010\u001b\u001a\u00028\u00002\u0006\u0010\u001a\u001a\u00028\u00008V@PX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b\u001c\u0010\u0010\"\u0004\b\u001d\u0010\u0012R*\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\n2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00028\u00000\n@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R<\u0010#\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\"2\u0012\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\"@@X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\u001a\u0010(\u001a\u00020)X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u000e\u0010.\u001a\u00020)X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u000200X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006<"}, d2 = {"Landroidx/compose/animation/core/InfiniteTransition$TransitionAnimationState;", "T", "V", "Landroidx/compose/animation/core/AnimationVector;", "Landroidx/compose/runtime/State;", "initialValue", "targetValue", "typeConverter", "Landroidx/compose/animation/core/TwoWayConverter;", "animationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "label", "", "<init>", "(Landroidx/compose/animation/core/InfiniteTransition;Ljava/lang/Object;Ljava/lang/Object;Landroidx/compose/animation/core/TwoWayConverter;Landroidx/compose/animation/core/AnimationSpec;Ljava/lang/String;)V", "getInitialValue$animation_core", "()Ljava/lang/Object;", "setInitialValue$animation_core", "(Ljava/lang/Object;)V", "Ljava/lang/Object;", "getTargetValue$animation_core", "setTargetValue$animation_core", "getTypeConverter", "()Landroidx/compose/animation/core/TwoWayConverter;", "getLabel", "()Ljava/lang/String;", "<set-?>", "value", "getValue", "setValue$animation_core", "value$delegate", "Landroidx/compose/runtime/MutableState;", "getAnimationSpec", "()Landroidx/compose/animation/core/AnimationSpec;", "Landroidx/compose/animation/core/TargetBasedAnimation;", "animation", "getAnimation", "()Landroidx/compose/animation/core/TargetBasedAnimation;", "setAnimation$animation_core", "(Landroidx/compose/animation/core/TargetBasedAnimation;)V", "isFinished", "", "isFinished$animation_core", "()Z", "setFinished$animation_core", "(Z)V", "startOnTheNextFrame", "playTimeNanosOffset", "", "updateValues", "", "updateValues$animation_core", "(Ljava/lang/Object;Ljava/lang/Object;Landroidx/compose/animation/core/AnimationSpec;)V", "onPlayTimeChanged", "playTimeNanos", "onPlayTimeChanged$animation_core", "skipToEnd", "skipToEnd$animation_core", "reset", "reset$animation_core", "animation-core"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public final class TransitionAnimationState<T, V extends AnimationVector> implements State<T> {
        private TargetBasedAnimation<T, V> animation;
        private AnimationSpec<T> animationSpec;
        private T initialValue;
        private boolean isFinished;
        private final String label;
        private long playTimeNanosOffset;
        private boolean startOnTheNextFrame;
        private T targetValue;
        private final TwoWayConverter<T, V> typeConverter;

        /* JADX INFO: renamed from: value$delegate, reason: from kotlin metadata */
        private final MutableState value;

        public TransitionAnimationState(T t, T t2, TwoWayConverter<T, V> twoWayConverter, AnimationSpec<T> animationSpec, String str) {
            this.initialValue = t;
            this.targetValue = t2;
            this.typeConverter = twoWayConverter;
            this.label = str;
            this.value = SnapshotStateKt.mutableStateOf$default(t, (SnapshotMutationPolicy) null, 2, (Object) null);
            this.animationSpec = animationSpec;
            this.animation = new TargetBasedAnimation<>(this.animationSpec, twoWayConverter, this.initialValue, this.targetValue, (AnimationVector) null, 16, (DefaultConstructorMarker) null);
        }

        public final TargetBasedAnimation<T, V> getAnimation() {
            return this.animation;
        }

        public final AnimationSpec<T> getAnimationSpec() {
            return this.animationSpec;
        }

        public final T getInitialValue$animation_core() {
            return this.initialValue;
        }

        public final String getLabel() {
            return this.label;
        }

        public final T getTargetValue$animation_core() {
            return this.targetValue;
        }

        public final TwoWayConverter<T, V> getTypeConverter() {
            return this.typeConverter;
        }

        public T getValue() {
            return (T) this.value.getValue();
        }

        /* JADX INFO: renamed from: isFinished$animation_core, reason: from getter */
        public final boolean getIsFinished() {
            return this.isFinished;
        }

        public final void onPlayTimeChanged$animation_core(long playTimeNanos) {
            InfiniteTransition.this.setRefreshChildNeeded(false);
            if (this.startOnTheNextFrame) {
                this.startOnTheNextFrame = false;
                this.playTimeNanosOffset = playTimeNanos;
            }
            long j = playTimeNanos - this.playTimeNanosOffset;
            setValue$animation_core(this.animation.getValueFromNanos(j));
            this.isFinished = this.animation.isFinishedFromNanos(j);
        }

        public final void reset$animation_core() {
            this.startOnTheNextFrame = true;
        }

        public final void setAnimation$animation_core(TargetBasedAnimation<T, V> targetBasedAnimation) {
            this.animation = targetBasedAnimation;
        }

        public final void setFinished$animation_core(boolean z) {
            this.isFinished = z;
        }

        public final void setInitialValue$animation_core(T t) {
            this.initialValue = t;
        }

        public final void setTargetValue$animation_core(T t) {
            this.targetValue = t;
        }

        public void setValue$animation_core(T t) {
            this.value.setValue(t);
        }

        public final void skipToEnd$animation_core() {
            setValue$animation_core(this.animation.getTargetValue());
            this.startOnTheNextFrame = true;
        }

        public final void updateValues$animation_core(T initialValue, T targetValue, AnimationSpec<T> animationSpec) {
            this.initialValue = initialValue;
            this.targetValue = targetValue;
            this.animationSpec = animationSpec;
            this.animation = new TargetBasedAnimation<>(animationSpec, this.typeConverter, initialValue, targetValue, (AnimationVector) null, 16, (DefaultConstructorMarker) null);
            InfiniteTransition.this.setRefreshChildNeeded(true);
            this.isFinished = false;
            this.startOnTheNextFrame = true;
        }
    }

    public InfiniteTransition(String str) {
        this.label = str;
    }

    public static Unit a(InfiniteTransition infiniteTransition, int i, Composer composer, int i2) {
        infiniteTransition.run$animation_core(composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    private final boolean getRefreshChildNeeded() {
        return ((Boolean) this.refreshChildNeeded.getValue()).booleanValue();
    }

    private final boolean isRunning() {
        return ((Boolean) this.isRunning.getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onFrame(long playTimeNanos) {
        MutableVector<TransitionAnimationState<?, ?>> mutableVector = this._animations;
        Object[] objArr = mutableVector.content;
        int size = mutableVector.getSize();
        boolean z = true;
        for (int i = 0; i < size; i++) {
            TransitionAnimationState transitionAnimationState = (TransitionAnimationState) objArr[i];
            if (!transitionAnimationState.getIsFinished()) {
                transitionAnimationState.onPlayTimeChanged$animation_core(playTimeNanos);
            }
            if (!transitionAnimationState.getIsFinished()) {
                z = false;
            }
        }
        setRunning(!z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setRefreshChildNeeded(boolean z) {
        this.refreshChildNeeded.setValue(Boolean.valueOf(z));
    }

    private final void setRunning(boolean z) {
        this.isRunning.setValue(Boolean.valueOf(z));
    }

    public final void addAnimation$animation_core(TransitionAnimationState<?, ?> animation) {
        this._animations.add(animation);
        setRefreshChildNeeded(true);
    }

    public final List<TransitionAnimationState<?, ?>> getAnimations() {
        return this._animations.asMutableList();
    }

    public final String getLabel() {
        return this.label;
    }

    public final void removeAnimation$animation_core(TransitionAnimationState<?, ?> animation) {
        this._animations.remove(animation);
    }

    public final void run$animation_core(Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-318043801);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(this) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if (composerStartRestartGroup.shouldExecute((i2 & 3) != 2, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-318043801, i2, -1, "androidx.compose.animation.core.InfiniteTransition.run (InfiniteTransition.kt:164)");
            }
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            Composer.Companion companion = Composer.Companion;
            if (objRememberedValue == companion.getEmpty()) {
                objRememberedValue = SnapshotStateKt.mutableStateOf$default((Object) null, (SnapshotMutationPolicy) null, 2, (Object) null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            MutableState mutableState = (MutableState) objRememberedValue;
            if (isRunning() || getRefreshChildNeeded()) {
                composerStartRestartGroup.startReplaceGroup(-144841960);
                boolean zChangedInstance = composerStartRestartGroup.changedInstance(this);
                Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (zChangedInstance || objRememberedValue2 == companion.getEmpty()) {
                    objRememberedValue2 = new InfiniteTransition$run$1$1(mutableState, this, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                EffectsKt.LaunchedEffect(this, (Function2) objRememberedValue2, composerStartRestartGroup, i2 & 14);
            } else {
                composerStartRestartGroup.startReplaceGroup(-151918981);
            }
            composerStartRestartGroup.endReplaceGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: so6
                public final Object invoke(Object obj, Object obj2) {
                    return InfiniteTransition.a(this.b, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
