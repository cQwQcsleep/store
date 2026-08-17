package androidx.window.embedding;

import android.graphics.Color;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\b&\u0018\u0000 \u00062\u00020\u0001:\u0003\u0004\u0005\u0006B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0007"}, d2 = {"Landroidx/window/embedding/EmbeddingAnimationBackground;", "", "<init>", "()V", "ColorBackground", "DefaultBackground", "Companion", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public abstract class EmbeddingAnimationBackground {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final EmbeddingAnimationBackground DEFAULT = new DefaultBackground();

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0013\b\u0000\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\b\u001a\u00020\tH\u0016J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0096\u0002J\b\u0010\u000e\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000f"}, d2 = {"Landroidx/window/embedding/EmbeddingAnimationBackground$ColorBackground;", "Landroidx/window/embedding/EmbeddingAnimationBackground;", "color", "", "<init>", "(I)V", "getColor", "()I", "toString", "", "equals", "", "other", "", "hashCode", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class ColorBackground extends EmbeddingAnimationBackground {
        private final int color;

        public ColorBackground(int i) {
            super(null);
            this.color = i;
            if (Color.alpha(i) == 255) {
                return;
            }
            w01.a("Background color must be opaque");
            throw null;
        }

        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            return (other instanceof ColorBackground) && this.color == ((ColorBackground) other).color;
        }

        public final int getColor() {
            return this.color;
        }

        public int hashCode() {
            return Integer.hashCode(this.color);
        }

        public String toString() {
            return "ColorBackground{color:" + Integer.toHexString(this.color) + '}';
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"Landroidx/window/embedding/EmbeddingAnimationBackground$DefaultBackground;", "Landroidx/window/embedding/EmbeddingAnimationBackground;", "<init>", "()V", "toString", "", "hashCode", "", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class DefaultBackground extends EmbeddingAnimationBackground {
        public DefaultBackground() {
            super(null);
        }

        public int hashCode() {
            return toString().hashCode();
        }

        public String toString() {
            return "DefaultBackground";
        }
    }

    public /* synthetic */ EmbeddingAnimationBackground(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @JvmStatic
    public static final ColorBackground createColorBackground(int i) {
        return INSTANCE.createColorBackground(i);
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u0007J\u0017\u0010\n\u001a\u00020\t2\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u0000¢\u0006\u0002\b\u000bR\u0010\u0010\b\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Landroidx/window/embedding/EmbeddingAnimationBackground$Companion;", "", "<init>", "()V", "createColorBackground", "Landroidx/window/embedding/EmbeddingAnimationBackground$ColorBackground;", "color", "", "DEFAULT", "Landroidx/window/embedding/EmbeddingAnimationBackground;", "buildFromValue", "buildFromValue$window_release", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final EmbeddingAnimationBackground buildFromValue$window_release(int color) {
            return Color.alpha(color) != 255 ? EmbeddingAnimationBackground.DEFAULT : createColorBackground(color);
        }

        @JvmStatic
        public final ColorBackground createColorBackground(int color) {
            return new ColorBackground(color);
        }

        private Companion() {
        }
    }

    private EmbeddingAnimationBackground() {
    }
}
