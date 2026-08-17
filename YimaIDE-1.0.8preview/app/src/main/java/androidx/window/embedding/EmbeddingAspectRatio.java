package androidx.window.embedding;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\f\u001a\u00020\u0003H\u0016J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0004\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0013"}, d2 = {"Landroidx/window/embedding/EmbeddingAspectRatio;", "", "description", "", "value", "", "<init>", "(Ljava/lang/String;F)V", "getDescription$window_release", "()Ljava/lang/String;", "getValue$window_release", "()F", "toString", "equals", "", "other", "hashCode", "", "Companion", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class EmbeddingAspectRatio {
    private final String description;
    private final float value;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final EmbeddingAspectRatio ALWAYS_ALLOW = new EmbeddingAspectRatio("ALWAYS_ALLOW", 0.0f);
    public static final EmbeddingAspectRatio ALWAYS_DISALLOW = new EmbeddingAspectRatio("ALWAYS_DISALLOW", -1.0f);

    private EmbeddingAspectRatio(String str, float f) {
        this.description = str;
        this.value = f;
    }

    @JvmStatic
    public static final EmbeddingAspectRatio ratio(float f) {
        return INSTANCE.ratio(f);
    }

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof EmbeddingAspectRatio)) {
            return false;
        }
        EmbeddingAspectRatio embeddingAspectRatio = (EmbeddingAspectRatio) other;
        return this.value == embeddingAspectRatio.value && Intrinsics.areEqual(this.description, embeddingAspectRatio.description);
    }

    /* JADX INFO: renamed from: getDescription$window_release, reason: from getter */
    public final String getDescription() {
        return this.description;
    }

    /* JADX INFO: renamed from: getValue$window_release, reason: from getter */
    public final float getValue() {
        return this.value;
    }

    public int hashCode() {
        return this.description.hashCode() + (Float.hashCode(this.value) * 31);
    }

    public String toString() {
        return "EmbeddingAspectRatio(" + this.description + ')';
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u0010\u0004\u001a\u00020\u0006H\u0007J\u0015\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0006H\u0000¢\u0006\u0002\b\u000bR\u0010\u0010\u0007\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Landroidx/window/embedding/EmbeddingAspectRatio$Companion;", "", "<init>", "()V", "ratio", "Landroidx/window/embedding/EmbeddingAspectRatio;", "", "ALWAYS_ALLOW", "ALWAYS_DISALLOW", "buildAspectRatioFromValue", "value", "buildAspectRatioFromValue$window_release", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final EmbeddingAspectRatio buildAspectRatioFromValue$window_release(float value) {
            EmbeddingAspectRatio embeddingAspectRatio = EmbeddingAspectRatio.ALWAYS_ALLOW;
            if (value == embeddingAspectRatio.getValue()) {
                return embeddingAspectRatio;
            }
            EmbeddingAspectRatio embeddingAspectRatio2 = EmbeddingAspectRatio.ALWAYS_DISALLOW;
            return value == embeddingAspectRatio2.getValue() ? embeddingAspectRatio2 : ratio(value);
        }

        @JvmStatic
        public final EmbeddingAspectRatio ratio(float ratio) {
            DefaultConstructorMarker defaultConstructorMarker = null;
            if (ratio <= 1.0f) {
                w01.a("Ratio must be greater than 1.");
                return null;
            }
            return new EmbeddingAspectRatio("ratio:" + ratio, ratio, defaultConstructorMarker);
        }

        private Companion() {
        }
    }

    public /* synthetic */ EmbeddingAspectRatio(String str, float f, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, f);
    }
}
