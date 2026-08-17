package androidx.compose.animation;

import androidx.collection.MutableScatterMap;
import androidx.collection.ScatterMapKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.AnimationVector2D;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.Transition;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotMutationPolicy;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.ParentDataModifier;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\b\b\u0001\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0003VWXB'\b\u0000\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\u001a\u001a\u00020\u001b*\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0096\u0004JH\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020$0#2!\u0010%\u001a\u001d\u0012\u0013\u0012\u00110'¢\u0006\f\b(\u0012\b\b)\u0012\u0004\b\b(*\u0012\u0004\u0012\u00020'0&H\u0016¢\u0006\u0004\b+\u0010,J\u001f\u00103\u001a\u00020$2\u0006\u00104\u001a\u0002052\u0006\u00106\u001a\u000205H\u0002¢\u0006\u0004\b7\u00108JH\u00109\u001a\u00020:2\u0006\u0010 \u001a\u00020!2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020$0#2!\u0010;\u001a\u001d\u0012\u0013\u0012\u00110'¢\u0006\f\b(\u0012\b\b)\u0012\u0004\b\b(*\u0012\u0004\u0012\u00020'0&H\u0016¢\u0006\u0004\b<\u0010=J\u0017\u0010Q\u001a\u00020R2\u0006\u0010S\u001a\u00020\u001bH\u0001¢\u0006\u0004\bT\u0010UR\u001a\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\u0005\u001a\u00020\u0006X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0007\u001a\u00020\bX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\u00028\u00008VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0018\u001a\u00028\u00008VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0017R\u0018\u0010-\u001a\u00020.*\u00020!8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b/\u00100R\u0018\u00101\u001a\u00020.*\u00020!8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b2\u00100R+\u0010?\u001a\u0002052\u0006\u0010>\u001a\u0002058@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\bD\u0010E\u001a\u0004\b@\u0010A\"\u0004\bB\u0010CR&\u0010F\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u0002050H0GX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bI\u0010JR\"\u0010K\u001a\n\u0012\u0004\u0012\u000205\u0018\u00010HX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bL\u0010M\"\u0004\bN\u0010OR\u0014\u00106\u001a\u0002058BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bP\u0010A¨\u0006Y²\u0006\n\u0010Z\u001a\u00020.X\u008a\u008e\u0002"}, d2 = {"Landroidx/compose/animation/AnimatedContentTransitionScopeImpl;", "S", "Landroidx/compose/animation/AnimatedContentTransitionScope;", "transition", "Landroidx/compose/animation/core/Transition;", "contentAlignment", "Landroidx/compose/ui/Alignment;", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "<init>", "(Landroidx/compose/animation/core/Transition;Landroidx/compose/ui/Alignment;Landroidx/compose/ui/unit/LayoutDirection;)V", "getTransition$animation", "()Landroidx/compose/animation/core/Transition;", "getContentAlignment", "()Landroidx/compose/ui/Alignment;", "setContentAlignment", "(Landroidx/compose/ui/Alignment;)V", "getLayoutDirection$animation", "()Landroidx/compose/ui/unit/LayoutDirection;", "setLayoutDirection$animation", "(Landroidx/compose/ui/unit/LayoutDirection;)V", "initialState", "getInitialState", "()Ljava/lang/Object;", "targetState", "getTargetState", "using", "Landroidx/compose/animation/ContentTransform;", "sizeTransform", "Landroidx/compose/animation/SizeTransform;", "slideIntoContainer", "Landroidx/compose/animation/EnterTransition;", "towards", "Landroidx/compose/animation/AnimatedContentTransitionScope$SlideDirection;", "animationSpec", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "Landroidx/compose/ui/unit/IntOffset;", "initialOffset", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "offsetForFullSlide", "slideIntoContainer-mOhB8PU", "(ILandroidx/compose/animation/core/FiniteAnimationSpec;Lkotlin/jvm/functions/Function1;)Landroidx/compose/animation/EnterTransition;", "isLeft", "", "isLeft-gWo6LJ4", "(I)Z", "isRight", "isRight-gWo6LJ4", "calculateOffset", "fullSize", "Landroidx/compose/ui/unit/IntSize;", "currentSize", "calculateOffset-emnUabE", "(JJ)J", "slideOutOfContainer", "Landroidx/compose/animation/ExitTransition;", "targetOffset", "slideOutOfContainer-mOhB8PU", "(ILandroidx/compose/animation/core/FiniteAnimationSpec;Lkotlin/jvm/functions/Function1;)Landroidx/compose/animation/ExitTransition;", "<set-?>", "measuredSize", "getMeasuredSize-YbymL2g$animation", "()J", "setMeasuredSize-ozmzZPI$animation", "(J)V", "measuredSize$delegate", "Landroidx/compose/runtime/MutableState;", "targetSizeMap", "Landroidx/collection/MutableScatterMap;", "Landroidx/compose/runtime/State;", "getTargetSizeMap$animation", "()Landroidx/collection/MutableScatterMap;", "animatedSize", "getAnimatedSize$animation", "()Landroidx/compose/runtime/State;", "setAnimatedSize$animation", "(Landroidx/compose/runtime/State;)V", "getCurrentSize-YbymL2g", "createSizeAnimationModifier", "Landroidx/compose/ui/Modifier;", "contentTransform", "createSizeAnimationModifier$animation", "(Landroidx/compose/animation/ContentTransform;Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/Modifier;", "ChildData", "SizeModifierElement", "SizeModifierNode", "animation", "shouldAnimateSize"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class AnimatedContentTransitionScopeImpl<S> implements AnimatedContentTransitionScope<S> {
    public static final int $stable = 8;
    private State<IntSize> animatedSize;
    private Alignment contentAlignment;
    private LayoutDirection layoutDirection;

    /* JADX INFO: renamed from: measuredSize$delegate, reason: from kotlin metadata */
    private final MutableState measuredSize = SnapshotStateKt.mutableStateOf$default(IntSize.box-impl(IntSize.Companion.getZero-YbymL2g()), (SnapshotMutationPolicy) null, 2, (Object) null);
    private final MutableScatterMap<S, State<IntSize>> targetSizeMap = ScatterMapKt.mutableScatterMapOf();
    private final Transition<S> transition;

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0001\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\u000b\u001a\u00020\f*\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\fH\u0016R+\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00038F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\t\u0010\n\u001a\u0004\b\u0002\u0010\u0007\"\u0004\b\b\u0010\u0005¨\u0006\u000f"}, d2 = {"Landroidx/compose/animation/AnimatedContentTransitionScopeImpl$ChildData;", "Landroidx/compose/ui/layout/ParentDataModifier;", "isTarget", "", "<init>", "(Z)V", "<set-?>", "()Z", "setTarget", "isTarget$delegate", "Landroidx/compose/runtime/MutableState;", "modifyParentData", "", "Landroidx/compose/ui/unit/Density;", "parentData", "animation"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class ChildData implements ParentDataModifier {
        public static final int $stable = 0;

        /* JADX INFO: renamed from: isTarget$delegate, reason: from kotlin metadata */
        private final MutableState isTarget;

        public ChildData(boolean z) {
            this.isTarget = SnapshotStateKt.mutableStateOf$default(Boolean.valueOf(z), (SnapshotMutationPolicy) null, 2, (Object) null);
        }

        public final boolean isTarget() {
            return ((Boolean) this.isTarget.getValue()).booleanValue();
        }

        public Object modifyParentData(Density density, Object obj) {
            return this;
        }

        public final void setTarget(boolean z) {
            this.isTarget.setValue(Boolean.valueOf(z));
        }
    }

    @Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u0000*\u0004\b\u0001\u0010\u00012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00030\u0002BE\u0012\u001e\u0010\u0004\u001a\u001a\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0005R\b\u0012\u0004\u0012\u00028\u00010\b\u0012\u000e\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\n\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00010\r¢\u0006\u0004\b\u000e\u0010\u000fJ\u000e\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00010\u0003H\u0016J\b\u0010\u0017\u001a\u00020\u0018H\u0016J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0096\u0002J\u0016\u0010\u001d\u001a\u00020\u001e2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00028\u00010\u0003H\u0016J\f\u0010 \u001a\u00020\u001e*\u00020!H\u0016R)\u0010\u0004\u001a\u001a\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0005R\b\u0012\u0004\u0012\u00028\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0019\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006\""}, d2 = {"Landroidx/compose/animation/AnimatedContentTransitionScopeImpl$SizeModifierElement;", "S", "Landroidx/compose/ui/node/ModifierNodeElement;", "Landroidx/compose/animation/AnimatedContentTransitionScopeImpl$SizeModifierNode;", "sizeAnimation", "Landroidx/compose/animation/core/Transition$DeferredAnimation;", "Landroidx/compose/ui/unit/IntSize;", "Landroidx/compose/animation/core/AnimationVector2D;", "Landroidx/compose/animation/core/Transition;", "sizeTransform", "Landroidx/compose/runtime/State;", "Landroidx/compose/animation/SizeTransform;", "scope", "Landroidx/compose/animation/AnimatedContentTransitionScopeImpl;", "<init>", "(Landroidx/compose/animation/core/Transition$DeferredAnimation;Landroidx/compose/runtime/State;Landroidx/compose/animation/AnimatedContentTransitionScopeImpl;)V", "getSizeAnimation", "()Landroidx/compose/animation/core/Transition$DeferredAnimation;", "getSizeTransform", "()Landroidx/compose/runtime/State;", "getScope", "()Landroidx/compose/animation/AnimatedContentTransitionScopeImpl;", "create", "hashCode", "", "equals", "", "other", "", "update", "", "node", "inspectableProperties", "Landroidx/compose/ui/platform/InspectorInfo;", "animation"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class SizeModifierElement<S> extends ModifierNodeElement<SizeModifierNode<S>> {
        private final AnimatedContentTransitionScopeImpl<S> scope;
        private final Transition<S>.DeferredAnimation<IntSize, AnimationVector2D> sizeAnimation;
        private final State<SizeTransform> sizeTransform;

        /* JADX WARN: Multi-variable type inference failed */
        public SizeModifierElement(Transition<S>.DeferredAnimation<IntSize, AnimationVector2D> deferredAnimation, State<? extends SizeTransform> state, AnimatedContentTransitionScopeImpl<S> animatedContentTransitionScopeImpl) {
            this.sizeAnimation = deferredAnimation;
            this.sizeTransform = state;
            this.scope = animatedContentTransitionScopeImpl;
        }

        /* JADX INFO: renamed from: create, reason: merged with bridge method [inline-methods] */
        public SizeModifierNode<S> m74create() {
            return new SizeModifierNode<>(this.sizeAnimation, this.sizeTransform, this.scope);
        }

        public boolean equals(Object other) {
            if (!(other instanceof SizeModifierElement)) {
                return false;
            }
            SizeModifierElement sizeModifierElement = (SizeModifierElement) other;
            return Intrinsics.areEqual(sizeModifierElement.sizeAnimation, this.sizeAnimation) && Intrinsics.areEqual(sizeModifierElement.sizeTransform, this.sizeTransform);
        }

        public final AnimatedContentTransitionScopeImpl<S> getScope() {
            return this.scope;
        }

        public final Transition<S>.DeferredAnimation<IntSize, AnimationVector2D> getSizeAnimation() {
            return this.sizeAnimation;
        }

        public final State<SizeTransform> getSizeTransform() {
            return this.sizeTransform;
        }

        public int hashCode() {
            int iHashCode = this.scope.hashCode() * 31;
            Transition<S>.DeferredAnimation<IntSize, AnimationVector2D> deferredAnimation = this.sizeAnimation;
            return ((iHashCode + (deferredAnimation != null ? deferredAnimation.hashCode() : 0)) * 31) + this.sizeTransform.hashCode();
        }

        public void inspectableProperties(InspectorInfo inspectorInfo) {
            inspectorInfo.setName("sizeTransform");
            inspectorInfo.getProperties().set("sizeAnimation", this.sizeAnimation);
            inspectorInfo.getProperties().set("sizeTransform", this.sizeTransform);
            inspectorInfo.getProperties().set("scope", this.scope);
        }

        public void update(SizeModifierNode<S> node) {
            node.setSizeAnimation(this.sizeAnimation);
            node.setSizeTransform(this.sizeTransform);
            node.setScope(this.scope);
        }
    }

    @Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u0000*\u0004\b\u0001\u0010\u00012\u00020\u0002BE\u0012\u001e\u0010\u0003\u001a\u001a\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0004R\b\u0012\u0004\u0012\u00028\u00010\u0007\u0012\u000e\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\t\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00010\f¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u001d\u001a\u00020\u00052\u0006\u0010\u001e\u001a\u00020\u0005H\u0002¢\u0006\u0004\b\u001f\u0010 J\b\u0010!\u001a\u00020\"H\u0016J#\u0010#\u001a\u00020$*\u00020%2\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020)H\u0016¢\u0006\u0004\b*\u0010+R2\u0010\u0003\u001a\u001a\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0004R\b\u0012\u0004\u0012\u00028\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\"\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R \u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u0010\u0010\u001b\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u001c¨\u0006,"}, d2 = {"Landroidx/compose/animation/AnimatedContentTransitionScopeImpl$SizeModifierNode;", "S", "Landroidx/compose/animation/LayoutModifierNodeWithPassThroughIntrinsics;", "sizeAnimation", "Landroidx/compose/animation/core/Transition$DeferredAnimation;", "Landroidx/compose/ui/unit/IntSize;", "Landroidx/compose/animation/core/AnimationVector2D;", "Landroidx/compose/animation/core/Transition;", "sizeTransform", "Landroidx/compose/runtime/State;", "Landroidx/compose/animation/SizeTransform;", "scope", "Landroidx/compose/animation/AnimatedContentTransitionScopeImpl;", "<init>", "(Landroidx/compose/animation/core/Transition$DeferredAnimation;Landroidx/compose/runtime/State;Landroidx/compose/animation/AnimatedContentTransitionScopeImpl;)V", "getSizeAnimation", "()Landroidx/compose/animation/core/Transition$DeferredAnimation;", "setSizeAnimation", "(Landroidx/compose/animation/core/Transition$DeferredAnimation;)V", "getSizeTransform", "()Landroidx/compose/runtime/State;", "setSizeTransform", "(Landroidx/compose/runtime/State;)V", "getScope", "()Landroidx/compose/animation/AnimatedContentTransitionScopeImpl;", "setScope", "(Landroidx/compose/animation/AnimatedContentTransitionScopeImpl;)V", "lastSize", "J", "lastContinuousSizeOrDefault", "default", "lastContinuousSizeOrDefault-mzRDjE0", "(J)J", "onReset", "", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurable", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "animation"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class SizeModifierNode<S> extends LayoutModifierNodeWithPassThroughIntrinsics {
        private long lastSize = AnimatedContentKt.UnspecifiedSize;
        private AnimatedContentTransitionScopeImpl<S> scope;
        private Transition<S>.DeferredAnimation<IntSize, AnimationVector2D> sizeAnimation;
        private State<? extends SizeTransform> sizeTransform;

        public SizeModifierNode(Transition<S>.DeferredAnimation<IntSize, AnimationVector2D> deferredAnimation, State<? extends SizeTransform> state, AnimatedContentTransitionScopeImpl<S> animatedContentTransitionScopeImpl) {
            this.sizeAnimation = deferredAnimation;
            this.sizeTransform = state;
            this.scope = animatedContentTransitionScopeImpl;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: lastContinuousSizeOrDefault-mzRDjE0, reason: not valid java name */
        public final long m76lastContinuousSizeOrDefaultmzRDjE0(long j) {
            return IntSize.equals-impl0(this.lastSize, AnimatedContentKt.UnspecifiedSize) ? j : this.lastSize;
        }

        public final AnimatedContentTransitionScopeImpl<S> getScope() {
            return this.scope;
        }

        public final Transition<S>.DeferredAnimation<IntSize, AnimationVector2D> getSizeAnimation() {
            return this.sizeAnimation;
        }

        public final State<SizeTransform> getSizeTransform() {
            return this.sizeTransform;
        }

        /* JADX INFO: renamed from: measure-3p2s80s, reason: not valid java name */
        public MeasureResult m77measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
            final long j2;
            final Placeable placeable = measurable.measure-BRTryo0(j);
            if (measureScope.isLookingAhead()) {
                j2 = IntSize.constructor-impl((((long) placeable.getWidth()) << 32) | (((long) placeable.getHeight()) & 4294967295L));
            } else if (this.sizeAnimation == null) {
                j2 = IntSize.constructor-impl((((long) placeable.getWidth()) << 32) | (((long) placeable.getHeight()) & 4294967295L));
                this.lastSize = IntSize.constructor-impl((((long) placeable.getWidth()) << 32) | (((long) placeable.getHeight()) & 4294967295L));
            } else {
                final long j3 = IntSize.constructor-impl((((long) placeable.getWidth()) << 32) | (((long) placeable.getHeight()) & 4294967295L));
                Transition<S>.DeferredAnimation<IntSize, AnimationVector2D> deferredAnimation = this.sizeAnimation;
                deferredAnimation.getClass();
                State<IntSize> stateAnimate = deferredAnimation.animate(new Function1<Transition.Segment<S>, FiniteAnimationSpec<IntSize>>(this) { // from class: androidx.compose.animation.AnimatedContentTransitionScopeImpl$SizeModifierNode$measure$size$1
                    final /* synthetic */ AnimatedContentTransitionScopeImpl.SizeModifierNode<S> this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                        this.this$0 = this;
                    }

                    public final FiniteAnimationSpec<IntSize> invoke(Transition.Segment<S> segment) {
                        long jM76lastContinuousSizeOrDefaultmzRDjE0;
                        FiniteAnimationSpec<IntSize> finiteAnimationSpecMo177createAnimationSpecTemP2vQ;
                        boolean zAreEqual = Intrinsics.areEqual(segment.getInitialState(), this.this$0.getScope().getInitialState());
                        AnimatedContentTransitionScopeImpl.SizeModifierNode<S> sizeModifierNode = this.this$0;
                        if (zAreEqual) {
                            jM76lastContinuousSizeOrDefaultmzRDjE0 = sizeModifierNode.m76lastContinuousSizeOrDefaultmzRDjE0(j3);
                        } else {
                            State<IntSize> state = sizeModifierNode.getScope().getTargetSizeMap$animation().get(segment.getInitialState());
                            jM76lastContinuousSizeOrDefaultmzRDjE0 = state != null ? ((IntSize) state.getValue()).unbox-impl() : IntSize.Companion.getZero-YbymL2g();
                        }
                        State<IntSize> state2 = this.this$0.getScope().getTargetSizeMap$animation().get(segment.getTargetState());
                        long j4 = state2 != null ? ((IntSize) state2.getValue()).unbox-impl() : IntSize.Companion.getZero-YbymL2g();
                        SizeTransform sizeTransform = (SizeTransform) this.this$0.getSizeTransform().getValue();
                        return (sizeTransform == null || (finiteAnimationSpecMo177createAnimationSpecTemP2vQ = sizeTransform.mo177createAnimationSpecTemP2vQ(jM76lastContinuousSizeOrDefaultmzRDjE0, j4)) == null) ? AnimationSpecKt.spring$default(0.0f, 400.0f, null, 5, null) : finiteAnimationSpecMo177createAnimationSpecTemP2vQ;
                    }
                }, (Function1<? super S, ? extends IntSize>) new Function1<S, IntSize>(this) { // from class: androidx.compose.animation.AnimatedContentTransitionScopeImpl$SizeModifierNode$measure$size$2
                    final /* synthetic */ AnimatedContentTransitionScopeImpl.SizeModifierNode<S> this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                        this.this$0 = this;
                    }

                    /* JADX WARN: Multi-variable type inference failed */
                    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                        return IntSize.box-impl(m78invokeYEO4UFw(obj));
                    }

                    /* JADX INFO: renamed from: invoke-YEO4UFw, reason: not valid java name */
                    public final long m78invokeYEO4UFw(S s) {
                        boolean zAreEqual = Intrinsics.areEqual(s, this.this$0.getScope().getInitialState());
                        AnimatedContentTransitionScopeImpl.SizeModifierNode<S> sizeModifierNode = this.this$0;
                        if (zAreEqual) {
                            return sizeModifierNode.m76lastContinuousSizeOrDefaultmzRDjE0(j3);
                        }
                        State<IntSize> state = sizeModifierNode.getScope().getTargetSizeMap$animation().get(s);
                        return state != null ? ((IntSize) state.getValue()).unbox-impl() : IntSize.Companion.getZero-YbymL2g();
                    }
                });
                this.scope.setAnimatedSize$animation(stateAnimate);
                j2 = ((IntSize) stateAnimate.getValue()).unbox-impl();
                this.lastSize = ((IntSize) stateAnimate.getValue()).unbox-impl();
            }
            return MeasureScope.layout$default(measureScope, (int) (j2 >> 32), (int) (j2 & 4294967295L), (Map) null, new Function1<Placeable.PlacementScope, Unit>(this) { // from class: androidx.compose.animation.AnimatedContentTransitionScopeImpl$SizeModifierNode$measure$1
                final /* synthetic */ AnimatedContentTransitionScopeImpl.SizeModifierNode<S> this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                    this.this$0 = this;
                }

                public final void invoke(Placeable.PlacementScope placementScope) {
                    Placeable.PlacementScope.place-70tqf50$default(placementScope, placeable, this.this$0.getScope().getContentAlignment().align-KFBX0sM(IntSize.constructor-impl((((long) placeable.getWidth()) << 32) | (((long) placeable.getHeight()) & 4294967295L)), j2, LayoutDirection.Ltr), 0.0f, 2, (Object) null);
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    invoke((Placeable.PlacementScope) obj);
                    return Unit.INSTANCE;
                }
            }, 4, (Object) null);
        }

        public void onReset() {
            super.onReset();
            this.lastSize = AnimatedContentKt.UnspecifiedSize;
        }

        public final void setScope(AnimatedContentTransitionScopeImpl<S> animatedContentTransitionScopeImpl) {
            this.scope = animatedContentTransitionScopeImpl;
        }

        public final void setSizeAnimation(Transition<S>.DeferredAnimation<IntSize, AnimationVector2D> deferredAnimation) {
            this.sizeAnimation = deferredAnimation;
        }

        public final void setSizeTransform(State<? extends SizeTransform> state) {
            this.sizeTransform = state;
        }
    }

    public AnimatedContentTransitionScopeImpl(Transition<S> transition, Alignment alignment, LayoutDirection layoutDirection) {
        this.transition = transition;
        this.contentAlignment = alignment;
        this.layoutDirection = layoutDirection;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: calculateOffset-emnUabE, reason: not valid java name */
    public final long m68calculateOffsetemnUabE(long fullSize, long currentSize) {
        return getContentAlignment().align-KFBX0sM(fullSize, currentSize, LayoutDirection.Ltr);
    }

    private static final boolean createSizeAnimationModifier$lambda$1(MutableState<Boolean> mutableState) {
        return ((Boolean) mutableState.getValue()).booleanValue();
    }

    private static final void createSizeAnimationModifier$lambda$2(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getCurrentSize-YbymL2g, reason: not valid java name */
    public final long m69getCurrentSizeYbymL2g() {
        State<IntSize> state = this.animatedSize;
        return state != null ? ((IntSize) state.getValue()).unbox-impl() : m72getMeasuredSizeYbymL2g$animation();
    }

    /* JADX INFO: renamed from: isLeft-gWo6LJ4, reason: not valid java name */
    private final boolean m70isLeftgWo6LJ4(int i) {
        AnimatedContentTransitionScope.SlideDirection.Companion companion = AnimatedContentTransitionScope.SlideDirection.INSTANCE;
        if (AnimatedContentTransitionScope.SlideDirection.m56equalsimpl0(i, companion.m62getLeftDKzdypw())) {
            return true;
        }
        if (AnimatedContentTransitionScope.SlideDirection.m56equalsimpl0(i, companion.m64getStartDKzdypw()) && this.layoutDirection == LayoutDirection.Ltr) {
            return true;
        }
        return AnimatedContentTransitionScope.SlideDirection.m56equalsimpl0(i, companion.m61getEndDKzdypw()) && this.layoutDirection == LayoutDirection.Rtl;
    }

    /* JADX INFO: renamed from: isRight-gWo6LJ4, reason: not valid java name */
    private final boolean m71isRightgWo6LJ4(int i) {
        AnimatedContentTransitionScope.SlideDirection.Companion companion = AnimatedContentTransitionScope.SlideDirection.INSTANCE;
        if (AnimatedContentTransitionScope.SlideDirection.m56equalsimpl0(i, companion.m63getRightDKzdypw())) {
            return true;
        }
        if (AnimatedContentTransitionScope.SlideDirection.m56equalsimpl0(i, companion.m64getStartDKzdypw()) && this.layoutDirection == LayoutDirection.Rtl) {
            return true;
        }
        return AnimatedContentTransitionScope.SlideDirection.m56equalsimpl0(i, companion.m61getEndDKzdypw()) && this.layoutDirection == LayoutDirection.Ltr;
    }

    public final Modifier createSizeAnimationModifier$animation(ContentTransform contentTransform, Composer composer, int i) {
        Modifier modifier;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(93755870, i, -1, "androidx.compose.animation.AnimatedContentTransitionScopeImpl.createSizeAnimationModifier (AnimatedContent.kt:557)");
        }
        boolean zChanged = composer.changed(this);
        Object objRememberedValue = composer.rememberedValue();
        Transition.DeferredAnimation deferredAnimationCreateDeferredAnimation = null;
        if (zChanged || objRememberedValue == Composer.Companion.getEmpty()) {
            objRememberedValue = SnapshotStateKt.mutableStateOf$default(Boolean.FALSE, (SnapshotMutationPolicy) null, 2, (Object) null);
            composer.updateRememberedValue(objRememberedValue);
        }
        MutableState mutableState = (MutableState) objRememberedValue;
        State stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(contentTransform.getSizeTransform(), composer, 0);
        if (Intrinsics.areEqual(this.transition.getCurrentState(), this.transition.getTargetState())) {
            createSizeAnimationModifier$lambda$2(mutableState, false);
        } else if (stateRememberUpdatedState.getValue() != null) {
            createSizeAnimationModifier$lambda$2(mutableState, true);
        }
        if (createSizeAnimationModifier$lambda$1(mutableState)) {
            composer.startReplaceGroup(1353077497);
            deferredAnimationCreateDeferredAnimation = androidx.compose.animation.core.TransitionKt.createDeferredAnimation(this.transition, VectorConvertersKt.getVectorConverter(IntSize.Companion), null, composer, 0, 2);
            boolean zChanged2 = composer.changed(deferredAnimationCreateDeferredAnimation);
            Object objRememberedValue2 = composer.rememberedValue();
            if (zChanged2 || objRememberedValue2 == Composer.Companion.getEmpty()) {
                SizeTransform sizeTransform = (SizeTransform) stateRememberUpdatedState.getValue();
                objRememberedValue2 = (sizeTransform == null || sizeTransform.getClip()) ? ClipKt.clipToBounds(Modifier.Companion) : Modifier.Companion;
                composer.updateRememberedValue(objRememberedValue2);
            }
            modifier = (Modifier) objRememberedValue2;
            composer.endReplaceGroup();
        } else {
            composer.startReplaceGroup(1353343539);
            composer.endReplaceGroup();
            this.animatedSize = null;
            modifier = Modifier.Companion;
        }
        Modifier modifierThen = modifier.then(new SizeModifierElement(deferredAnimationCreateDeferredAnimation, stateRememberUpdatedState, this));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return modifierThen;
    }

    public final State<IntSize> getAnimatedSize$animation() {
        return this.animatedSize;
    }

    @Override // androidx.compose.animation.AnimatedContentTransitionScope
    public Alignment getContentAlignment() {
        return this.contentAlignment;
    }

    @Override // androidx.compose.animation.core.Transition.Segment
    public S getInitialState() {
        return this.transition.getSegment().getInitialState();
    }

    /* JADX INFO: renamed from: getLayoutDirection$animation, reason: from getter */
    public final LayoutDirection getLayoutDirection() {
        return this.layoutDirection;
    }

    /* JADX INFO: renamed from: getMeasuredSize-YbymL2g$animation, reason: not valid java name */
    public final long m72getMeasuredSizeYbymL2g$animation() {
        return ((IntSize) this.measuredSize.getValue()).unbox-impl();
    }

    public final MutableScatterMap<S, State<IntSize>> getTargetSizeMap$animation() {
        return this.targetSizeMap;
    }

    @Override // androidx.compose.animation.core.Transition.Segment
    public S getTargetState() {
        return this.transition.getSegment().getTargetState();
    }

    public final Transition<S> getTransition$animation() {
        return this.transition;
    }

    public final void setAnimatedSize$animation(State<IntSize> state) {
        this.animatedSize = state;
    }

    public void setContentAlignment(Alignment alignment) {
        this.contentAlignment = alignment;
    }

    public final void setLayoutDirection$animation(LayoutDirection layoutDirection) {
        this.layoutDirection = layoutDirection;
    }

    /* JADX INFO: renamed from: setMeasuredSize-ozmzZPI$animation, reason: not valid java name */
    public final void m73setMeasuredSizeozmzZPI$animation(long j) {
        this.measuredSize.setValue(IntSize.box-impl(j));
    }

    @Override // androidx.compose.animation.AnimatedContentTransitionScope
    /* JADX INFO: renamed from: slideIntoContainer-mOhB8PU */
    public EnterTransition mo51slideIntoContainermOhB8PU(int towards, FiniteAnimationSpec<IntOffset> animationSpec, final Function1<? super Integer, Integer> initialOffset) {
        if (m70isLeftgWo6LJ4(towards)) {
            return EnterExitTransitionKt.slideInHorizontally(animationSpec, new Function1<Integer, Integer>() { // from class: androidx.compose.animation.AnimatedContentTransitionScopeImpl$slideIntoContainer$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(1);
                }

                public final Integer invoke(int i) {
                    long j = i;
                    return (Integer) initialOffset.invoke(Integer.valueOf(((int) (this.m69getCurrentSizeYbymL2g() >> 32)) - IntOffset.getX-impl(this.m68calculateOffsetemnUabE(IntSize.constructor-impl((j & 4294967295L) | (j << 32)), this.m69getCurrentSizeYbymL2g()))));
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    return invoke(((Number) obj).intValue());
                }
            });
        }
        if (m71isRightgWo6LJ4(towards)) {
            return EnterExitTransitionKt.slideInHorizontally(animationSpec, new Function1<Integer, Integer>() { // from class: androidx.compose.animation.AnimatedContentTransitionScopeImpl$slideIntoContainer$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(1);
                }

                public final Integer invoke(int i) {
                    long j = i;
                    return (Integer) initialOffset.invoke(Integer.valueOf((-IntOffset.getX-impl(this.m68calculateOffsetemnUabE(IntSize.constructor-impl((j & 4294967295L) | (j << 32)), this.m69getCurrentSizeYbymL2g()))) - i));
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    return invoke(((Number) obj).intValue());
                }
            });
        }
        AnimatedContentTransitionScope.SlideDirection.Companion companion = AnimatedContentTransitionScope.SlideDirection.INSTANCE;
        if (AnimatedContentTransitionScope.SlideDirection.m56equalsimpl0(towards, companion.m65getUpDKzdypw())) {
            return EnterExitTransitionKt.slideInVertically(animationSpec, new Function1<Integer, Integer>() { // from class: androidx.compose.animation.AnimatedContentTransitionScopeImpl$slideIntoContainer$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(1);
                }

                public final Integer invoke(int i) {
                    long j = i;
                    return (Integer) initialOffset.invoke(Integer.valueOf(((int) (this.m69getCurrentSizeYbymL2g() & 4294967295L)) - IntOffset.getY-impl(this.m68calculateOffsetemnUabE(IntSize.constructor-impl((4294967295L & j) | (j << 32)), this.m69getCurrentSizeYbymL2g()))));
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    return invoke(((Number) obj).intValue());
                }
            });
        }
        return AnimatedContentTransitionScope.SlideDirection.m56equalsimpl0(towards, companion.m60getDownDKzdypw()) ? EnterExitTransitionKt.slideInVertically(animationSpec, new Function1<Integer, Integer>() { // from class: androidx.compose.animation.AnimatedContentTransitionScopeImpl$slideIntoContainer$4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            public final Integer invoke(int i) {
                long j = i;
                return (Integer) initialOffset.invoke(Integer.valueOf((-IntOffset.getY-impl(this.m68calculateOffsetemnUabE(IntSize.constructor-impl((j & 4294967295L) | (j << 32)), this.m69getCurrentSizeYbymL2g()))) - i));
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return invoke(((Number) obj).intValue());
            }
        }) : EnterTransition.INSTANCE.getNone();
    }

    @Override // androidx.compose.animation.AnimatedContentTransitionScope
    /* JADX INFO: renamed from: slideOutOfContainer-mOhB8PU */
    public ExitTransition mo52slideOutOfContainermOhB8PU(int towards, FiniteAnimationSpec<IntOffset> animationSpec, final Function1<? super Integer, Integer> targetOffset) {
        if (m70isLeftgWo6LJ4(towards)) {
            return EnterExitTransitionKt.slideOutHorizontally(animationSpec, new Function1<Integer, Integer>(this) { // from class: androidx.compose.animation.AnimatedContentTransitionScopeImpl$slideOutOfContainer$1
                final /* synthetic */ AnimatedContentTransitionScopeImpl<S> this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(1);
                    this.this$0 = this;
                }

                public final Integer invoke(int i) {
                    State state = (State) this.this$0.getTargetSizeMap$animation().get(this.this$0.getTransition$animation().getTargetState());
                    long j = i;
                    return (Integer) targetOffset.invoke(Integer.valueOf((-IntOffset.getX-impl(this.this$0.m68calculateOffsetemnUabE(IntSize.constructor-impl((j & 4294967295L) | (j << 32)), state != null ? ((IntSize) state.getValue()).unbox-impl() : IntSize.Companion.getZero-YbymL2g()))) - i));
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    return invoke(((Number) obj).intValue());
                }
            });
        }
        if (m71isRightgWo6LJ4(towards)) {
            return EnterExitTransitionKt.slideOutHorizontally(animationSpec, new Function1<Integer, Integer>(this) { // from class: androidx.compose.animation.AnimatedContentTransitionScopeImpl$slideOutOfContainer$2
                final /* synthetic */ AnimatedContentTransitionScopeImpl<S> this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(1);
                    this.this$0 = this;
                }

                public final Integer invoke(int i) {
                    State state = (State) this.this$0.getTargetSizeMap$animation().get(this.this$0.getTransition$animation().getTargetState());
                    long j = state != null ? ((IntSize) state.getValue()).unbox-impl() : IntSize.Companion.getZero-YbymL2g();
                    long j2 = i;
                    return (Integer) targetOffset.invoke(Integer.valueOf((-IntOffset.getX-impl(this.this$0.m68calculateOffsetemnUabE(IntSize.constructor-impl((j2 & 4294967295L) | (j2 << 32)), j))) + ((int) (j >> 32))));
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    return invoke(((Number) obj).intValue());
                }
            });
        }
        AnimatedContentTransitionScope.SlideDirection.Companion companion = AnimatedContentTransitionScope.SlideDirection.INSTANCE;
        if (AnimatedContentTransitionScope.SlideDirection.m56equalsimpl0(towards, companion.m65getUpDKzdypw())) {
            return EnterExitTransitionKt.slideOutVertically(animationSpec, new Function1<Integer, Integer>(this) { // from class: androidx.compose.animation.AnimatedContentTransitionScopeImpl$slideOutOfContainer$3
                final /* synthetic */ AnimatedContentTransitionScopeImpl<S> this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(1);
                    this.this$0 = this;
                }

                public final Integer invoke(int i) {
                    State state = (State) this.this$0.getTargetSizeMap$animation().get(this.this$0.getTransition$animation().getTargetState());
                    long j = i;
                    return (Integer) targetOffset.invoke(Integer.valueOf((-IntOffset.getY-impl(this.this$0.m68calculateOffsetemnUabE(IntSize.constructor-impl((j & 4294967295L) | (j << 32)), state != null ? ((IntSize) state.getValue()).unbox-impl() : IntSize.Companion.getZero-YbymL2g()))) - i));
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    return invoke(((Number) obj).intValue());
                }
            });
        }
        return AnimatedContentTransitionScope.SlideDirection.m56equalsimpl0(towards, companion.m60getDownDKzdypw()) ? EnterExitTransitionKt.slideOutVertically(animationSpec, new Function1<Integer, Integer>(this) { // from class: androidx.compose.animation.AnimatedContentTransitionScopeImpl$slideOutOfContainer$4
            final /* synthetic */ AnimatedContentTransitionScopeImpl<S> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
                this.this$0 = this;
            }

            public final Integer invoke(int i) {
                State state = (State) this.this$0.getTargetSizeMap$animation().get(this.this$0.getTransition$animation().getTargetState());
                long j = state != null ? ((IntSize) state.getValue()).unbox-impl() : IntSize.Companion.getZero-YbymL2g();
                long j2 = i;
                return (Integer) targetOffset.invoke(Integer.valueOf((-IntOffset.getY-impl(this.this$0.m68calculateOffsetemnUabE(IntSize.constructor-impl((j2 & 4294967295L) | (j2 << 32)), j))) + ((int) (j & 4294967295L))));
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return invoke(((Number) obj).intValue());
            }
        }) : ExitTransition.INSTANCE.getNone();
    }

    @Override // androidx.compose.animation.AnimatedContentTransitionScope
    public ContentTransform using(ContentTransform contentTransform, SizeTransform sizeTransform) {
        contentTransform.setSizeTransform$animation(sizeTransform);
        return contentTransform;
    }
}
