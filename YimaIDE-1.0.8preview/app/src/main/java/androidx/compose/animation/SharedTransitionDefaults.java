package androidx.compose.animation;

import androidx.compose.animation.SharedTransitionDefaults;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.ui.geometry.Rect;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\bÇ\u0002\u0018\u00002\u00020\u0001:\u0001\bB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\t"}, d2 = {"Landroidx/compose/animation/SharedTransitionDefaults;", "", "<init>", "()V", "BoundsTransform", "Landroidx/compose/animation/BoundsTransform;", "getBoundsTransform", "()Landroidx/compose/animation/BoundsTransform;", "SharedContentConfig", "animation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SharedTransitionDefaults {
    public static final SharedTransitionDefaults INSTANCE = new SharedTransitionDefaults();
    private static final BoundsTransform BoundsTransform = new BoundsTransform() { // from class: w8d
        @Override // androidx.compose.animation.BoundsTransform
        public final FiniteAnimationSpec createAnimationSpec(Rect rect, Rect rect2) {
            return SharedTransitionDefaults.a(rect, rect2);
        }
    };
    public static final int $stable = 8;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Landroidx/compose/animation/SharedTransitionDefaults$SharedContentConfig;", "Landroidx/compose/animation/SharedTransitionScope$SharedContentConfig;", "<init>", "()V", "animation"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class SharedContentConfig implements SharedTransitionScope.SharedContentConfig {
        public static final int $stable = 0;
        public static final SharedContentConfig INSTANCE = new SharedContentConfig();

        private SharedContentConfig() {
        }
    }

    private SharedTransitionDefaults() {
    }

    public static FiniteAnimationSpec a(Rect rect, Rect rect2) {
        return SharedTransitionScopeKt.DefaultSpring;
    }

    public final BoundsTransform getBoundsTransform() {
        return BoundsTransform;
    }
}
