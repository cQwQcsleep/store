package androidx.compose.foundation.gestures;

import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationSpecKt;
import kotlin.Deprecated;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\n\bg\u0018\u0000 \r2\u00020\u0001:\u0001\rJ \u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0004H\u0016R \u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038WX\u0097\u0004¢\u0006\f\u0012\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\bø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000eÀ\u0006\u0001"}, d2 = {"Landroidx/compose/foundation/gestures/BringIntoViewSpec;", "", "scrollAnimationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "", "getScrollAnimationSpec$annotations", "()V", "getScrollAnimationSpec", "()Landroidx/compose/animation/core/AnimationSpec;", "calculateScrollDistance", "offset", "size", "containerSize", "Companion", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface BringIntoViewSpec {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J%\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u0006H\u0000¢\u0006\u0002\b\u0011R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\nX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0012"}, d2 = {"Landroidx/compose/foundation/gestures/BringIntoViewSpec$Companion;", "", "<init>", "()V", "DefaultScrollAnimationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "", "getDefaultScrollAnimationSpec$foundation", "()Landroidx/compose/animation/core/AnimationSpec;", "DefaultBringIntoViewSpec", "Landroidx/compose/foundation/gestures/BringIntoViewSpec;", "getDefaultBringIntoViewSpec$foundation", "()Landroidx/compose/foundation/gestures/BringIntoViewSpec;", "defaultCalculateScrollDistance", "offset", "size", "containerSize", "defaultCalculateScrollDistance$foundation", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final AnimationSpec<Float> DefaultScrollAnimationSpec = AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null);
        private static final BringIntoViewSpec DefaultBringIntoViewSpec = new BringIntoViewSpec() { // from class: androidx.compose.foundation.gestures.BringIntoViewSpec$Companion$DefaultBringIntoViewSpec$1
        };

        private Companion() {
        }

        public final float defaultCalculateScrollDistance$foundation(float offset, float size, float containerSize) {
            float f = size + offset;
            if (offset >= 0.0f && f <= containerSize) {
                return 0.0f;
            }
            if (offset < 0.0f && f > containerSize) {
                return 0.0f;
            }
            float f2 = f - containerSize;
            return Math.abs(offset) < Math.abs(f2) ? offset : f2;
        }

        public final BringIntoViewSpec getDefaultBringIntoViewSpec$foundation() {
            return DefaultBringIntoViewSpec;
        }

        public final AnimationSpec<Float> getDefaultScrollAnimationSpec$foundation() {
            return DefaultScrollAnimationSpec;
        }
    }

    @Deprecated(message = "Animation spec customization is no longer supported.")
    static /* synthetic */ void getScrollAnimationSpec$annotations() {
    }

    default float calculateScrollDistance(float offset, float size, float containerSize) {
        return INSTANCE.defaultCalculateScrollDistance$foundation(offset, size, containerSize);
    }

    @Deprecated(message = "Animation spec customization is no longer supported.")
    default AnimationSpec<Float> getScrollAnimationSpec() {
        return INSTANCE.getDefaultScrollAnimationSpec$foundation();
    }
}
