package androidx.window.area;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001:\u0002\u0013\u0014B\u0019\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\f\u001a\u00020\rH\u0016J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Landroidx/window/area/WindowAreaCapability;", "", "operation", "Landroidx/window/area/WindowAreaCapability$Operation;", NotificationCompat.CATEGORY_STATUS, "Landroidx/window/area/WindowAreaCapability$Status;", "<init>", "(Landroidx/window/area/WindowAreaCapability$Operation;Landroidx/window/area/WindowAreaCapability$Status;)V", "getOperation", "()Landroidx/window/area/WindowAreaCapability$Operation;", "getStatus", "()Landroidx/window/area/WindowAreaCapability$Status;", "toString", "", "equals", "", "other", "hashCode", "", "Status", "Operation", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class WindowAreaCapability {
    private final Operation operation;
    private final Status status;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0007\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0003H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Landroidx/window/area/WindowAreaCapability$Operation;", "", "description", "", "<init>", "(Ljava/lang/String;)V", "toString", "Companion", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Operation {
        private final String description;
        public static final Operation OPERATION_TRANSFER_ACTIVITY_TO_AREA = new Operation("TRANSFER");
        public static final Operation OPERATION_PRESENT_ON_AREA = new Operation("PRESENT");

        private Operation(String str) {
            this.description = str;
        }

        /* JADX INFO: renamed from: toString, reason: from getter */
        public String getDescription() {
            return this.description;
        }
    }

    public WindowAreaCapability(Operation operation, Status status) {
        operation.getClass();
        status.getClass();
        this.operation = operation;
        this.status = status;
    }

    public boolean equals(Object other) {
        if (!(other instanceof WindowAreaCapability)) {
            return false;
        }
        WindowAreaCapability windowAreaCapability = (WindowAreaCapability) other;
        return Intrinsics.areEqual(this.operation, windowAreaCapability.operation) && Intrinsics.areEqual(this.status, windowAreaCapability.status);
    }

    public final Operation getOperation() {
        return this.operation;
    }

    public final Status getStatus() {
        return this.status;
    }

    public int hashCode() {
        return (this.operation.hashCode() * 31) + this.status.hashCode();
    }

    public String toString() {
        return "Operation: " + this.operation + ": Status: " + this.status;
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0007\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0003H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Landroidx/window/area/WindowAreaCapability$Status;", "", "description", "", "<init>", "(Ljava/lang/String;)V", "toString", "Companion", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Status {
        private final String description;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final Status WINDOW_AREA_STATUS_UNKNOWN = new Status("UNKNOWN");
        public static final Status WINDOW_AREA_STATUS_UNSUPPORTED = new Status("UNSUPPORTED");
        public static final Status WINDOW_AREA_STATUS_UNAVAILABLE = new Status("UNAVAILABLE");
        public static final Status WINDOW_AREA_STATUS_AVAILABLE = new Status("AVAILABLE");
        public static final Status WINDOW_AREA_STATUS_ACTIVE = new Status("ACTIVE");

        private Status(String str) {
            this.description = str;
        }

        /* JADX INFO: renamed from: toString, reason: from getter */
        public String getDescription() {
            return this.description;
        }

        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0004\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0010\u0010\b\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Landroidx/window/area/WindowAreaCapability$Status$Companion;", "", "<init>", "()V", "WINDOW_AREA_STATUS_UNKNOWN", "Landroidx/window/area/WindowAreaCapability$Status;", "getWINDOW_AREA_STATUS_UNKNOWN$window_release", "()Landroidx/window/area/WindowAreaCapability$Status;", "WINDOW_AREA_STATUS_UNSUPPORTED", "WINDOW_AREA_STATUS_UNAVAILABLE", "WINDOW_AREA_STATUS_AVAILABLE", "WINDOW_AREA_STATUS_ACTIVE", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            public final Status getWINDOW_AREA_STATUS_UNKNOWN$window_release() {
                return Status.WINDOW_AREA_STATUS_UNKNOWN;
            }

            private Companion() {
            }
        }
    }
}
