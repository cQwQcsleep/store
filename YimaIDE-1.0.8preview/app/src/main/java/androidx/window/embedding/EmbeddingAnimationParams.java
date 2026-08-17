package androidx.window.embedding;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0002\u0017\u0018B1\b\u0002\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005¢\u0006\u0004\b\b\u0010\tJ\b\u0010\u0010\u001a\u00020\u0011H\u0016J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0015\u001a\u00020\u0016H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\r¨\u0006\u0019"}, d2 = {"Landroidx/window/embedding/EmbeddingAnimationParams;", "", "animationBackground", "Landroidx/window/embedding/EmbeddingAnimationBackground;", "openAnimation", "Landroidx/window/embedding/EmbeddingAnimationParams$AnimationSpec;", "closeAnimation", "changeAnimation", "<init>", "(Landroidx/window/embedding/EmbeddingAnimationBackground;Landroidx/window/embedding/EmbeddingAnimationParams$AnimationSpec;Landroidx/window/embedding/EmbeddingAnimationParams$AnimationSpec;Landroidx/window/embedding/EmbeddingAnimationParams$AnimationSpec;)V", "getAnimationBackground", "()Landroidx/window/embedding/EmbeddingAnimationBackground;", "getOpenAnimation", "()Landroidx/window/embedding/EmbeddingAnimationParams$AnimationSpec;", "getCloseAnimation", "getChangeAnimation", "hashCode", "", "equals", "", "other", "toString", "", "AnimationSpec", "Builder", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class EmbeddingAnimationParams {
    private final EmbeddingAnimationBackground animationBackground;
    private final AnimationSpec changeAnimation;
    private final AnimationSpec closeAnimation;
    private final AnimationSpec openAnimation;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\n\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u0005H\u0007J\u0010\u0010\f\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\u0007H\u0007J\u0010\u0010\u000e\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\u0007H\u0007J\u0010\u0010\u000f\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\u0007H\u0007J\u0006\u0010\u0010\u001a\u00020\u0011R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Landroidx/window/embedding/EmbeddingAnimationParams$Builder;", "", "<init>", "()V", "animationBackground", "Landroidx/window/embedding/EmbeddingAnimationBackground;", "openAnimation", "Landroidx/window/embedding/EmbeddingAnimationParams$AnimationSpec;", "closeAnimation", "changeAnimation", "setAnimationBackground", "background", "setOpenAnimation", "spec", "setCloseAnimation", "setChangeAnimation", "build", "Landroidx/window/embedding/EmbeddingAnimationParams;", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Builder {
        private EmbeddingAnimationBackground animationBackground = EmbeddingAnimationBackground.DEFAULT;
        private AnimationSpec changeAnimation;
        private AnimationSpec closeAnimation;
        private AnimationSpec openAnimation;

        public Builder() {
            AnimationSpec animationSpec = AnimationSpec.DEFAULT;
            this.openAnimation = animationSpec;
            this.closeAnimation = animationSpec;
            this.changeAnimation = animationSpec;
        }

        public final EmbeddingAnimationParams build() {
            return new EmbeddingAnimationParams(this.animationBackground, this.openAnimation, this.closeAnimation, this.changeAnimation, null);
        }

        public final Builder setAnimationBackground(EmbeddingAnimationBackground background) {
            background.getClass();
            this.animationBackground = background;
            return this;
        }

        public final Builder setChangeAnimation(AnimationSpec spec) {
            spec.getClass();
            this.changeAnimation = spec;
            return this;
        }

        public final Builder setCloseAnimation(AnimationSpec spec) {
            spec.getClass();
            this.closeAnimation = spec;
            return this;
        }

        public final Builder setOpenAnimation(AnimationSpec spec) {
            spec.getClass();
            this.openAnimation = spec;
            return this;
        }
    }

    public /* synthetic */ EmbeddingAnimationParams(EmbeddingAnimationBackground embeddingAnimationBackground, AnimationSpec animationSpec, AnimationSpec animationSpec2, AnimationSpec animationSpec3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? EmbeddingAnimationBackground.DEFAULT : embeddingAnimationBackground, (i & 2) != 0 ? AnimationSpec.DEFAULT : animationSpec, (i & 4) != 0 ? AnimationSpec.DEFAULT : animationSpec2, (i & 8) != 0 ? AnimationSpec.DEFAULT : animationSpec3);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EmbeddingAnimationParams)) {
            return false;
        }
        EmbeddingAnimationParams embeddingAnimationParams = (EmbeddingAnimationParams) other;
        return Intrinsics.areEqual(this.animationBackground, embeddingAnimationParams.animationBackground) && Intrinsics.areEqual(this.openAnimation, embeddingAnimationParams.openAnimation) && Intrinsics.areEqual(this.closeAnimation, embeddingAnimationParams.closeAnimation) && Intrinsics.areEqual(this.changeAnimation, embeddingAnimationParams.changeAnimation);
    }

    public final EmbeddingAnimationBackground getAnimationBackground() {
        return this.animationBackground;
    }

    public final AnimationSpec getChangeAnimation() {
        return this.changeAnimation;
    }

    public final AnimationSpec getCloseAnimation() {
        return this.closeAnimation;
    }

    public final AnimationSpec getOpenAnimation() {
        return this.openAnimation;
    }

    public int hashCode() {
        return (((((this.animationBackground.hashCode() * 31) + this.openAnimation.hashCode()) * 31) + this.closeAnimation.hashCode()) * 31) + this.changeAnimation.hashCode();
    }

    public String toString() {
        return EmbeddingAnimationParams.class.getSimpleName() + ":{animationBackground=" + this.animationBackground + ", openAnimation=" + this.openAnimation + ", closeAnimation=" + this.closeAnimation + ", changeAnimation=" + this.changeAnimation + " }";
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\b\u001a\u00020\tH\u0016J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\r\u001a\u00020\u0003H\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000f"}, d2 = {"Landroidx/window/embedding/EmbeddingAnimationParams$AnimationSpec;", "", "value", "", "<init>", "(I)V", "getValue$window_release", "()I", "toString", "", "equals", "", "other", "hashCode", "Companion", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class AnimationSpec {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        public static final AnimationSpec DEFAULT = new AnimationSpec(0);
        public static final AnimationSpec JUMP_CUT = new AnimationSpec(1);
        private final int value;

        private AnimationSpec(int i) {
            this.value = i;
        }

        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            return (other instanceof AnimationSpec) && this.value == ((AnimationSpec) other).value;
        }

        /* JADX INFO: renamed from: getValue$window_release, reason: from getter */
        public final int getValue() {
            return this.value;
        }

        public int hashCode() {
            return this.value * 31;
        }

        public String toString() {
            int i = this.value;
            if (i == 0) {
                return "DEFAULT";
            }
            if (i == 1) {
                return "JUMP_CUT";
            }
            return "Unknown value: " + this.value;
        }

        @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00052\b\b\u0001\u0010\b\u001a\u00020\tH\u0001¢\u0006\u0002\b\nR\u0010\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Landroidx/window/embedding/EmbeddingAnimationParams$AnimationSpec$Companion;", "", "<init>", "()V", "DEFAULT", "Landroidx/window/embedding/EmbeddingAnimationParams$AnimationSpec;", "JUMP_CUT", "getAnimationSpecFromValue", "value", "", "getAnimationSpecFromValue$window_release", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            @JvmStatic
            public final AnimationSpec getAnimationSpecFromValue$window_release(int value) {
                AnimationSpec animationSpec = AnimationSpec.DEFAULT;
                if (value == animationSpec.getValue()) {
                    return animationSpec;
                }
                AnimationSpec animationSpec2 = AnimationSpec.JUMP_CUT;
                if (value == animationSpec2.getValue()) {
                    return animationSpec2;
                }
                qf1.a("Undefined value:", value);
                return null;
            }

            private Companion() {
            }
        }
    }

    private EmbeddingAnimationParams(EmbeddingAnimationBackground embeddingAnimationBackground, AnimationSpec animationSpec, AnimationSpec animationSpec2, AnimationSpec animationSpec3) {
        this.animationBackground = embeddingAnimationBackground;
        this.openAnimation = animationSpec;
        this.closeAnimation = animationSpec2;
        this.changeAnimation = animationSpec3;
    }

    public /* synthetic */ EmbeddingAnimationParams(EmbeddingAnimationBackground embeddingAnimationBackground, AnimationSpec animationSpec, AnimationSpec animationSpec2, AnimationSpec animationSpec3, DefaultConstructorMarker defaultConstructorMarker) {
        this(embeddingAnimationBackground, animationSpec, animationSpec2, animationSpec3);
    }
}
