package androidx.compose.material3;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.material3.WideNavigationRailState;
import androidx.compose.material3.WideNavigationRailStateImpl;
import androidx.compose.material3.WideNavigationRailValue;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.runtime.saveable.SaverKt;
import androidx.compose.runtime.saveable.SaverScope;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FloatCompanionObject;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0007\b\u0001\u0018\u0000 !2\u00020\u0001:\u0001!B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u000e\u0010\u001a\u001a\u00020\u001bH\u0096@¢\u0006\u0002\u0010\u001cJ\u000e\u0010\u001d\u001a\u00020\u001bH\u0096@¢\u0006\u0002\u0010\u001cJ\u000e\u0010\u001e\u001a\u00020\u001bH\u0096@¢\u0006\u0002\u0010\u001cJ\u0016\u0010\u001f\u001a\u00020\u001b2\u0006\u0010\u0016\u001a\u00020\u0003H\u0096@¢\u0006\u0002\u0010 R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00030\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\u00020\u00148VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0015R\u0014\u0010\u0016\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\nR\u0014\u0010\u0018\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\n¨\u0006\""}, d2 = {"Landroidx/compose/material3/WideNavigationRailStateImpl;", "Landroidx/compose/material3/WideNavigationRailState;", "initialValue", "Landroidx/compose/material3/WideNavigationRailValue;", "animationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "", "<init>", "(Landroidx/compose/material3/WideNavigationRailValue;Landroidx/compose/animation/core/AnimationSpec;)V", "getInitialValue", "()Landroidx/compose/material3/WideNavigationRailValue;", "setInitialValue", "(Landroidx/compose/material3/WideNavigationRailValue;)V", "internalValue", "internalState", "Landroidx/compose/animation/core/Animatable;", "Landroidx/compose/animation/core/AnimationVector1D;", "_currentVal", "Landroidx/compose/runtime/State;", "isAnimating", "", "()Z", "targetValue", "getTargetValue", "currentValue", "getCurrentValue", "expand", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "collapse", "toggle", "snapTo", "(Landroidx/compose/material3/WideNavigationRailValue;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class WideNavigationRailStateImpl implements WideNavigationRailState {
    private static final float Collapsed = 0.0f;
    private static final float Expanded = 1.0f;
    private final State<WideNavigationRailValue> _currentVal;
    private final AnimationSpec<Float> animationSpec;
    private WideNavigationRailValue initialValue;
    private final Animatable<Float, AnimationVector1D> internalState;
    private final float internalValue;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    public WideNavigationRailStateImpl(WideNavigationRailValue wideNavigationRailValue, AnimationSpec<Float> animationSpec) {
        this.initialValue = wideNavigationRailValue;
        this.animationSpec = animationSpec;
        float f = WideNavigationRailStateKt.isExpanded(wideNavigationRailValue) ? 1.0f : 0.0f;
        this.internalValue = f;
        this.internalState = new Animatable<>(Float.valueOf(f), VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE), (Object) null, (String) null, 12, (DefaultConstructorMarker) null);
        this._currentVal = SnapshotStateKt.derivedStateOf(new Function0() { // from class: zmf
            public final Object invoke() {
                return WideNavigationRailStateImpl.a(this.b);
            }
        });
    }

    public static WideNavigationRailValue a(WideNavigationRailStateImpl wideNavigationRailStateImpl) {
        return ((Number) wideNavigationRailStateImpl.internalState.getValue()).floatValue() == 1.0f ? WideNavigationRailValue.Expanded : WideNavigationRailValue.Collapsed;
    }

    @Override // androidx.compose.material3.WideNavigationRailState
    public Object collapse(Continuation<? super Unit> continuation) {
        Object objAnimateTo$default = Animatable.animateTo$default(this.internalState, Boxing.boxFloat(0.0f), this.animationSpec, (Object) null, (Function1) null, continuation, 12, (Object) null);
        return objAnimateTo$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAnimateTo$default : Unit.INSTANCE;
    }

    @Override // androidx.compose.material3.WideNavigationRailState
    public Object expand(Continuation<? super Unit> continuation) {
        Object objAnimateTo$default = Animatable.animateTo$default(this.internalState, Boxing.boxFloat(1.0f), this.animationSpec, (Object) null, (Function1) null, continuation, 12, (Object) null);
        return objAnimateTo$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAnimateTo$default : Unit.INSTANCE;
    }

    @Override // androidx.compose.material3.WideNavigationRailState
    public WideNavigationRailValue getCurrentValue() {
        return this._currentVal.getValue();
    }

    public final WideNavigationRailValue getInitialValue() {
        return this.initialValue;
    }

    @Override // androidx.compose.material3.WideNavigationRailState
    public WideNavigationRailValue getTargetValue() {
        return ((Number) this.internalState.getTargetValue()).floatValue() == 1.0f ? WideNavigationRailValue.Expanded : WideNavigationRailValue.Collapsed;
    }

    @Override // androidx.compose.material3.WideNavigationRailState
    public boolean isAnimating() {
        return this.internalState.isRunning();
    }

    public final void setInitialValue(WideNavigationRailValue wideNavigationRailValue) {
        this.initialValue = wideNavigationRailValue;
    }

    @Override // androidx.compose.material3.WideNavigationRailState
    public Object snapTo(WideNavigationRailValue wideNavigationRailValue, Continuation<? super Unit> continuation) {
        Object objSnapTo = this.internalState.snapTo(Boxing.boxFloat(WideNavigationRailStateKt.isExpanded(wideNavigationRailValue) ? 1.0f : 0.0f), continuation);
        return objSnapTo == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objSnapTo : Unit.INSTANCE;
    }

    @Override // androidx.compose.material3.WideNavigationRailState
    public Object toggle(Continuation<? super Unit> continuation) {
        Object objAnimateTo$default = Animatable.animateTo$default(this.internalState, Boxing.boxFloat(WideNavigationRailStateKt.isExpanded(getTargetValue()) ? 0.0f : 1.0f), this.animationSpec, (Object) null, (Function1) null, continuation, 12, (Object) null);
        return objAnimateTo$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAnimateTo$default : Unit.INSTANCE;
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Landroidx/compose/material3/WideNavigationRailStateImpl$Companion;", "", "<init>", "()V", "Collapsed", "", "Expanded", "Saver", "Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/material3/WideNavigationRailState;", "Landroidx/compose/material3/WideNavigationRailValue;", "animationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static WideNavigationRailValue a(SaverScope saverScope, WideNavigationRailState wideNavigationRailState) {
            return wideNavigationRailState.getTargetValue();
        }

        public static WideNavigationRailState b(AnimationSpec animationSpec, WideNavigationRailValue wideNavigationRailValue) {
            return new WideNavigationRailStateImpl(wideNavigationRailValue, animationSpec);
        }

        public final Saver<WideNavigationRailState, WideNavigationRailValue> Saver(final AnimationSpec<Float> animationSpec) {
            return SaverKt.Saver(new Function2() { // from class: anf
                public final Object invoke(Object obj, Object obj2) {
                    return WideNavigationRailStateImpl.Companion.a((SaverScope) obj, (WideNavigationRailState) obj2);
                }
            }, new Function1() { // from class: bnf
                public final Object invoke(Object obj) {
                    return WideNavigationRailStateImpl.Companion.b(animationSpec, (WideNavigationRailValue) obj);
                }
            });
        }

        private Companion() {
        }
    }
}
