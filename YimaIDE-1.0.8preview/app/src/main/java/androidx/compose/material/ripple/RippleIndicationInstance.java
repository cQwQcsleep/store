package androidx.compose.material.ripple;

import androidx.compose.foundation.IndicationInstance;
import androidx.compose.foundation.interaction.Interaction;
import androidx.compose.foundation.interaction.PressInteraction;
import androidx.compose.material.ripple.RippleIndicationInstance;
import androidx.compose.runtime.State;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Deprecated(message = "Replaced by the new RippleNode implementation")
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b!\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H&J\u0010\u0010\u0011\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH&J\u001d\u0010\u0012\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00132\u0006\u0010\u000f\u001a\u00020\u0010H\u0000¢\u0006\u0002\b\u0014J!\u0010\u0015\u001a\u00020\f*\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a¢\u0006\u0004\b\u001b\u0010\u001cR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Landroidx/compose/material/ripple/RippleIndicationInstance;", "Landroidx/compose/foundation/IndicationInstance;", "bounded", "", "rippleAlpha", "Landroidx/compose/runtime/State;", "Landroidx/compose/material/ripple/RippleAlpha;", "<init>", "(ZLandroidx/compose/runtime/State;)V", "stateLayer", "Landroidx/compose/material/ripple/StateLayer;", "addRipple", "", "interaction", "Landroidx/compose/foundation/interaction/PressInteraction$Press;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "removeRipple", "updateStateLayer", "Landroidx/compose/foundation/interaction/Interaction;", "updateStateLayer$material_ripple", "drawStateLayer", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "radius", "Landroidx/compose/ui/unit/Dp;", "color", "Landroidx/compose/ui/graphics/Color;", "drawStateLayer-H2RKhps", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;FJ)V", "material-ripple"}, k = 1, mv = {2, 0, 0}, xi = 48)
public abstract class RippleIndicationInstance implements IndicationInstance {
    public static final int $stable = 8;
    private final boolean bounded;
    private final StateLayer stateLayer;

    public RippleIndicationInstance(boolean z, final State<RippleAlpha> state) {
        this.bounded = z;
        this.stateLayer = new StateLayer(z, new Function0() { // from class: pkc
            public final Object invoke() {
                return RippleIndicationInstance.a(state);
            }
        });
    }

    public static RippleAlpha a(State state) {
        return (RippleAlpha) state.getValue();
    }

    public abstract void addRipple(PressInteraction.Press interaction, CoroutineScope scope);

    /* JADX INFO: renamed from: drawStateLayer-H2RKhps, reason: not valid java name */
    public final void m1840drawStateLayerH2RKhps(DrawScope drawScope, float f, long j) {
        this.stateLayer.m1849drawStateLayermxwnekA(drawScope, Float.isNaN(f) ? RippleAnimationKt.m1836getRippleEndRadiuscSwnlzA(drawScope, this.bounded, drawScope.getSize-NH-jbRc()) : drawScope.toPx-0680j_4(f), j);
    }

    public abstract void removeRipple(PressInteraction.Press interaction);

    public final void updateStateLayer$material_ripple(Interaction interaction, CoroutineScope scope) {
        this.stateLayer.handleInteraction$material_ripple(interaction, scope);
    }
}
