package androidx.window.layout;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u0011\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\u0013\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u000b\u001a\u00020\u0003H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Landroidx/window/layout/SupportedPosture;", "", "rawValue", "", "<init>", "(I)V", "toString", "", "equals", "", "other", "hashCode", "Companion", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class SupportedPosture {
    public static final SupportedPosture TABLETOP = new SupportedPosture(0);
    private final int rawValue;

    public SupportedPosture(int i) {
        this.rawValue = i;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return other != null && other.getClass() == SupportedPosture.class && this.rawValue == ((SupportedPosture) other).rawValue;
    }

    /* JADX INFO: renamed from: hashCode, reason: from getter */
    public int getRawValue() {
        return this.rawValue;
    }

    public String toString() {
        return Intrinsics.areEqual(this, TABLETOP) ? "TABLETOP" : "UNKNOWN";
    }
}
