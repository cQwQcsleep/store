package androidx.compose.animation;

import androidx.compose.animation.core.AnimationVector4D;
import androidx.compose.animation.core.Transition;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.platform.InspectorInfo;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001BC\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u001c\u0010\u0006\u001a\u0018\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007R\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u000fJ\b\u0010\u0018\u001a\u00020\u0002H\u0016J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0002H\u0016J\f\u0010\u001c\u001a\u00020\u001a*\u00020\u001dH\u0016J\u000f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0003J\u001f\u0010\u001f\u001a\u0018\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007R\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0003J\t\u0010 \u001a\u00020\u000bHÆ\u0003J\t\u0010!\u001a\u00020\rHÆ\u0003JM\u0010\"\u001a\u00020\u00002\u000e\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u001e\b\u0002\u0010\u0006\u001a\u0018\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007R\b\u0012\u0004\u0012\u00020\u00050\u00042\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\rHÆ\u0001J\u0013\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010&HÖ\u0003J\t\u0010'\u001a\u00020(HÖ\u0001J\t\u0010)\u001a\u00020*HÖ\u0001R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R'\u0010\u0006\u001a\u0018\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007R\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006+"}, d2 = {"Landroidx/compose/animation/VeilModifierElement;", "Landroidx/compose/ui/node/ModifierNodeElement;", "Landroidx/compose/animation/VeilModifierNode;", "transition", "Landroidx/compose/animation/core/Transition;", "Landroidx/compose/animation/EnterExitState;", "veilAnimation", "Landroidx/compose/animation/core/Transition$DeferredAnimation;", "Landroidx/compose/ui/graphics/Color;", "Landroidx/compose/animation/core/AnimationVector4D;", "enter", "Landroidx/compose/animation/EnterTransition;", "exit", "Landroidx/compose/animation/ExitTransition;", "<init>", "(Landroidx/compose/animation/core/Transition;Landroidx/compose/animation/core/Transition$DeferredAnimation;Landroidx/compose/animation/EnterTransition;Landroidx/compose/animation/ExitTransition;)V", "getTransition", "()Landroidx/compose/animation/core/Transition;", "getVeilAnimation", "()Landroidx/compose/animation/core/Transition$DeferredAnimation;", "getEnter", "()Landroidx/compose/animation/EnterTransition;", "getExit", "()Landroidx/compose/animation/ExitTransition;", "create", "update", "", "node", "inspectableProperties", "Landroidx/compose/ui/platform/InspectorInfo;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "animation"}, k = 1, mv = {2, 0, 0}, xi = 48)
final /* data */ class VeilModifierElement extends ModifierNodeElement<VeilModifierNode> {
    private final EnterTransition enter;
    private final ExitTransition exit;
    private final Transition<EnterExitState> transition;
    private final Transition<EnterExitState>.DeferredAnimation<Color, AnimationVector4D> veilAnimation;

    public VeilModifierElement(Transition<EnterExitState> transition, Transition<EnterExitState>.DeferredAnimation<Color, AnimationVector4D> deferredAnimation, EnterTransition enterTransition, ExitTransition exitTransition) {
        this.transition = transition;
        this.veilAnimation = deferredAnimation;
        this.enter = enterTransition;
        this.exit = exitTransition;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ VeilModifierElement copy$default(VeilModifierElement veilModifierElement, Transition transition, Transition.DeferredAnimation deferredAnimation, EnterTransition enterTransition, ExitTransition exitTransition, int i, Object obj) {
        if ((i & 1) != 0) {
            transition = veilModifierElement.transition;
        }
        if ((i & 2) != 0) {
            deferredAnimation = veilModifierElement.veilAnimation;
        }
        if ((i & 4) != 0) {
            enterTransition = veilModifierElement.enter;
        }
        if ((i & 8) != 0) {
            exitTransition = veilModifierElement.exit;
        }
        return veilModifierElement.copy(transition, deferredAnimation, enterTransition, exitTransition);
    }

    public final Transition<EnterExitState> component1() {
        return this.transition;
    }

    public final Transition<EnterExitState>.DeferredAnimation<Color, AnimationVector4D> component2() {
        return this.veilAnimation;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final EnterTransition getEnter() {
        return this.enter;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final ExitTransition getExit() {
        return this.exit;
    }

    public final VeilModifierElement copy(Transition<EnterExitState> transition, Transition<EnterExitState>.DeferredAnimation<Color, AnimationVector4D> veilAnimation, EnterTransition enter, ExitTransition exit) {
        return new VeilModifierElement(transition, veilAnimation, enter, exit);
    }

    /* JADX INFO: renamed from: create, reason: merged with bridge method [inline-methods] */
    public VeilModifierNode m198create() {
        return new VeilModifierNode(this.transition, this.veilAnimation, this.enter, this.exit);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof VeilModifierElement)) {
            return false;
        }
        VeilModifierElement veilModifierElement = (VeilModifierElement) other;
        return Intrinsics.areEqual(this.transition, veilModifierElement.transition) && Intrinsics.areEqual(this.veilAnimation, veilModifierElement.veilAnimation) && Intrinsics.areEqual(this.enter, veilModifierElement.enter) && Intrinsics.areEqual(this.exit, veilModifierElement.exit);
    }

    public final EnterTransition getEnter() {
        return this.enter;
    }

    public final ExitTransition getExit() {
        return this.exit;
    }

    public final Transition<EnterExitState> getTransition() {
        return this.transition;
    }

    public final Transition<EnterExitState>.DeferredAnimation<Color, AnimationVector4D> getVeilAnimation() {
        return this.veilAnimation;
    }

    public int hashCode() {
        return (((((this.transition.hashCode() * 31) + this.veilAnimation.hashCode()) * 31) + this.enter.hashCode()) * 31) + this.exit.hashCode();
    }

    public void inspectableProperties(InspectorInfo inspectorInfo) {
        inspectorInfo.setName("veil");
        inspectorInfo.getProperties().set("transition", this.transition);
        inspectorInfo.getProperties().set("veilAnimation", this.veilAnimation);
        inspectorInfo.getProperties().set("enter", this.enter);
        inspectorInfo.getProperties().set("exit", this.exit);
    }

    public String toString() {
        return "VeilModifierElement(transition=" + this.transition + ", veilAnimation=" + this.veilAnimation + ", enter=" + this.enter + ", exit=" + this.exit + ')';
    }

    public void update(VeilModifierNode node) {
        node.setTransition(this.transition);
        node.setVeilAnimation(this.veilAnimation);
        node.setEnter(this.enter);
        node.setExit(this.exit);
    }
}
