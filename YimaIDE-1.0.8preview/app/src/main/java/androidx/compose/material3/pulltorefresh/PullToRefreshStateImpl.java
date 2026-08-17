package androidx.compose.material3.pulltorefresh;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.material3.pulltorefresh.PullToRefreshStateImpl;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.runtime.saveable.SaverKt;
import androidx.compose.runtime.saveable.SaverScope;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FloatCompanionObject;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\b\u0001\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u001d\b\u0002\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0004\b\u0006\u0010\u0007B\t\b\u0016¢\u0006\u0004\b\u0006\u0010\bJ\u000e\u0010\u000f\u001a\u00020\u0010H\u0096@¢\u0006\u0002\u0010\u0011J\u000e\u0010\u0012\u001a\u00020\u0010H\u0096@¢\u0006\u0002\u0010\u0011J\u0016\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0004H\u0096@¢\u0006\u0002\u0010\u0015R\u001a\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\u000e¨\u0006\u0017"}, d2 = {"Landroidx/compose/material3/pulltorefresh/PullToRefreshStateImpl;", "Landroidx/compose/material3/pulltorefresh/PullToRefreshState;", "anim", "Landroidx/compose/animation/core/Animatable;", "", "Landroidx/compose/animation/core/AnimationVector1D;", "<init>", "(Landroidx/compose/animation/core/Animatable;)V", "()V", "distanceFraction", "getDistanceFraction", "()F", "isAnimating", "", "()Z", "animateToThreshold", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "animateToHidden", "snapTo", "targetValue", "(FLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class PullToRefreshStateImpl implements PullToRefreshState {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Saver<PullToRefreshStateImpl, Float> Saver = SaverKt.Saver(new Function2() { // from class: nvb
        public final Object invoke(Object obj, Object obj2) {
            return PullToRefreshStateImpl.a((SaverScope) obj, (PullToRefreshStateImpl) obj2);
        }
    }, new Function1() { // from class: ovb
        public final Object invoke(Object obj) {
            return PullToRefreshStateImpl.b(((Float) obj).floatValue());
        }
    });
    private final Animatable<Float, AnimationVector1D> anim;

    public PullToRefreshStateImpl() {
        this(new Animatable(Float.valueOf(0.0f), VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE), (Object) null, (String) null, 12, (DefaultConstructorMarker) null));
    }

    public static Float a(SaverScope saverScope, PullToRefreshStateImpl pullToRefreshStateImpl) {
        return (Float) pullToRefreshStateImpl.anim.getValue();
    }

    public static PullToRefreshStateImpl b(float f) {
        return new PullToRefreshStateImpl(new Animatable(Float.valueOf(f), VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE), (Object) null, (String) null, 12, (DefaultConstructorMarker) null));
    }

    @Override // androidx.compose.material3.pulltorefresh.PullToRefreshState
    public Object animateToHidden(Continuation<? super Unit> continuation) {
        Object objAnimateTo$default = Animatable.animateTo$default(this.anim, Boxing.boxFloat(0.0f), (AnimationSpec) null, (Object) null, (Function1) null, continuation, 14, (Object) null);
        return objAnimateTo$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAnimateTo$default : Unit.INSTANCE;
    }

    @Override // androidx.compose.material3.pulltorefresh.PullToRefreshState
    public Object animateToThreshold(Continuation<? super Unit> continuation) {
        Object objAnimateTo$default = Animatable.animateTo$default(this.anim, Boxing.boxFloat(1.0f), (AnimationSpec) null, (Object) null, (Function1) null, continuation, 14, (Object) null);
        return objAnimateTo$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAnimateTo$default : Unit.INSTANCE;
    }

    @Override // androidx.compose.material3.pulltorefresh.PullToRefreshState
    public float getDistanceFraction() {
        return ((Number) this.anim.getValue()).floatValue();
    }

    @Override // androidx.compose.material3.pulltorefresh.PullToRefreshState
    public boolean isAnimating() {
        return this.anim.isRunning();
    }

    @Override // androidx.compose.material3.pulltorefresh.PullToRefreshState
    public Object snapTo(float f, Continuation<? super Unit> continuation) {
        Object objSnapTo = this.anim.snapTo(Boxing.boxFloat(f), continuation);
        return objSnapTo == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objSnapTo : Unit.INSTANCE;
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Landroidx/compose/material3/pulltorefresh/PullToRefreshStateImpl$Companion;", "", "<init>", "()V", "Saver", "Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/material3/pulltorefresh/PullToRefreshStateImpl;", "", "getSaver", "()Landroidx/compose/runtime/saveable/Saver;", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Saver<PullToRefreshStateImpl, Float> getSaver() {
            return PullToRefreshStateImpl.Saver;
        }

        private Companion() {
        }
    }

    private PullToRefreshStateImpl(Animatable<Float, AnimationVector1D> animatable) {
        this.anim = animatable;
    }
}
