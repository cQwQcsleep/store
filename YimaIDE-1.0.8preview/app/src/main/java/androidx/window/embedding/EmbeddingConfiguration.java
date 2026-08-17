package androidx.window.embedding;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0002\u0011\u0012B\u001d\b\u0002\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\u000b\u001a\u00020\u00052\b\u0010\f\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\n¨\u0006\u0013"}, d2 = {"Landroidx/window/embedding/EmbeddingConfiguration;", "", "dimAreaBehavior", "Landroidx/window/embedding/EmbeddingConfiguration$DimAreaBehavior;", "isAutoSaveEmbeddingState", "", "<init>", "(Landroidx/window/embedding/EmbeddingConfiguration$DimAreaBehavior;Z)V", "getDimAreaBehavior", "()Landroidx/window/embedding/EmbeddingConfiguration$DimAreaBehavior;", "()Z", "equals", "other", "hashCode", "", "toString", "", "DimAreaBehavior", "Builder", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class EmbeddingConfiguration {
    private final DimAreaBehavior dimAreaBehavior;
    private final boolean isAutoSaveEmbeddingState;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0005H\u0007J\u0010\u0010\n\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u0007H\u0007J\u0006\u0010\f\u001a\u00020\rR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Landroidx/window/embedding/EmbeddingConfiguration$Builder;", "", "<init>", "()V", "mDimAreaBehavior", "Landroidx/window/embedding/EmbeddingConfiguration$DimAreaBehavior;", "mSaveEmbeddingState", "", "setDimAreaBehavior", "area", "setAutoSaveEmbeddingState", "saveState", "build", "Landroidx/window/embedding/EmbeddingConfiguration;", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Builder {
        private DimAreaBehavior mDimAreaBehavior = DimAreaBehavior.UNDEFINED;
        private boolean mSaveEmbeddingState;

        public final EmbeddingConfiguration build() {
            return new EmbeddingConfiguration(this.mDimAreaBehavior, this.mSaveEmbeddingState, null);
        }

        public final Builder setAutoSaveEmbeddingState(boolean saveState) {
            this.mSaveEmbeddingState = saveState;
            return this;
        }

        public final Builder setDimAreaBehavior(DimAreaBehavior area) {
            area.getClass();
            this.mDimAreaBehavior = area;
            return this;
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \n2\u00020\u0001:\u0001\nB\u0013\b\u0002\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\b\u001a\u00020\tH\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000b"}, d2 = {"Landroidx/window/embedding/EmbeddingConfiguration$DimAreaBehavior;", "", "value", "", "<init>", "(I)V", "getValue$window_release", "()I", "toString", "", "Companion", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class DimAreaBehavior {
        private final int value;
        public static final DimAreaBehavior UNDEFINED = new DimAreaBehavior(0);
        public static final DimAreaBehavior ON_ACTIVITY_STACK = new DimAreaBehavior(1);
        public static final DimAreaBehavior ON_TASK = new DimAreaBehavior(2);

        private DimAreaBehavior(int i) {
            this.value = i;
        }

        /* JADX INFO: renamed from: getValue$window_release, reason: from getter */
        public final int getValue() {
            return this.value;
        }

        public String toString() {
            String str;
            int i = this.value;
            if (i == 0) {
                str = "UNDEFINED";
            } else if (i != 1) {
                str = i != 2 ? "UNKNOWN" : "ON_TASK";
            } else {
                str = "ON_ACTIVITY_STACK";
            }
            return "DimAreaBehavior=".concat(str);
        }
    }

    public /* synthetic */ EmbeddingConfiguration(DimAreaBehavior dimAreaBehavior, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? DimAreaBehavior.UNDEFINED : dimAreaBehavior, (i & 2) != 0 ? false : z);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EmbeddingConfiguration)) {
            return false;
        }
        EmbeddingConfiguration embeddingConfiguration = (EmbeddingConfiguration) other;
        return Intrinsics.areEqual(this.dimAreaBehavior, embeddingConfiguration.dimAreaBehavior) && this.isAutoSaveEmbeddingState == embeddingConfiguration.isAutoSaveEmbeddingState;
    }

    public final DimAreaBehavior getDimAreaBehavior() {
        return this.dimAreaBehavior;
    }

    public int hashCode() {
        return (this.dimAreaBehavior.hashCode() * 31) + Boolean.hashCode(this.isAutoSaveEmbeddingState);
    }

    /* JADX INFO: renamed from: isAutoSaveEmbeddingState, reason: from getter */
    public final boolean getIsAutoSaveEmbeddingState() {
        return this.isAutoSaveEmbeddingState;
    }

    public String toString() {
        return "EmbeddingConfiguration{dimArea=" + this.dimAreaBehavior + ", saveState=" + this.isAutoSaveEmbeddingState + '}';
    }

    private EmbeddingConfiguration(DimAreaBehavior dimAreaBehavior, boolean z) {
        this.dimAreaBehavior = dimAreaBehavior;
        this.isAutoSaveEmbeddingState = z;
    }

    public /* synthetic */ EmbeddingConfiguration(DimAreaBehavior dimAreaBehavior, boolean z, DefaultConstructorMarker defaultConstructorMarker) {
        this(dimAreaBehavior, z);
    }
}
