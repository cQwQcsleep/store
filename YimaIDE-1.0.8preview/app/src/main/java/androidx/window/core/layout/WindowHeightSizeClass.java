package androidx.window.core.layout;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Deprecated(message = "WindowHeightSizeClass will not be developed further, use WindowSizeClass instead.")
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0007\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\u0013\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u000b\u001a\u00020\u0003H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Landroidx/window/core/layout/WindowHeightSizeClass;", "", "rawValue", "", "<init>", "(I)V", "toString", "", "equals", "", "other", "hashCode", "Companion", "window-core"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class WindowHeightSizeClass {
    private final int rawValue;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final WindowHeightSizeClass COMPACT = new WindowHeightSizeClass(0);
    public static final WindowHeightSizeClass MEDIUM = new WindowHeightSizeClass(1);
    public static final WindowHeightSizeClass EXPANDED = new WindowHeightSizeClass(2);

    private WindowHeightSizeClass(int i) {
        this.rawValue = i;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return other != null && WindowHeightSizeClass.class == other.getClass() && this.rawValue == ((WindowHeightSizeClass) other).rawValue;
    }

    /* JADX INFO: renamed from: hashCode, reason: from getter */
    public int getRawValue() {
        return this.rawValue;
    }

    public String toString() {
        String str;
        if (Intrinsics.areEqual(this, COMPACT)) {
            str = "COMPACT";
        } else if (Intrinsics.areEqual(this, MEDIUM)) {
            str = "MEDIUM";
        } else {
            str = Intrinsics.areEqual(this, EXPANDED) ? "EXPANDED" : "UNKNOWN";
        }
        return "WindowHeightSizeClass: ".concat(str);
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\rH\u0001¢\u0006\u0002\b\u000eR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0003R\u0016\u0010\u0007\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u0012\u0004\b\b\u0010\u0003R\u0016\u0010\t\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u0012\u0004\b\n\u0010\u0003¨\u0006\u000f"}, d2 = {"Landroidx/window/core/layout/WindowHeightSizeClass$Companion;", "", "<init>", "()V", "COMPACT", "Landroidx/window/core/layout/WindowHeightSizeClass;", "getCOMPACT$annotations", "MEDIUM", "getMEDIUM$annotations", "EXPANDED", "getEXPANDED$annotations", "compute", "dpHeight", "", "compute$window_core", "window-core"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Deprecated(message = "WindowHeightSizeClass not be developed further.")
        public static /* synthetic */ void getCOMPACT$annotations() {
        }

        @Deprecated(message = "WindowHeightSizeClass not be developed further.")
        public static /* synthetic */ void getEXPANDED$annotations() {
        }

        @Deprecated(message = "WindowHeightSizeClass not be developed further.")
        public static /* synthetic */ void getMEDIUM$annotations() {
        }

        @Deprecated(message = "WindowHeightSizeClass not be developed further.")
        public final WindowHeightSizeClass compute$window_core(float dpHeight) {
            if (dpHeight >= 0.0f) {
                if (dpHeight < 480.0f) {
                    return WindowHeightSizeClass.COMPACT;
                }
                return dpHeight < 900.0f ? WindowHeightSizeClass.MEDIUM : WindowHeightSizeClass.EXPANDED;
            }
            throw new IllegalArgumentException(("Height must be positive, received " + dpHeight).toString());
        }

        private Companion() {
        }
    }
}
