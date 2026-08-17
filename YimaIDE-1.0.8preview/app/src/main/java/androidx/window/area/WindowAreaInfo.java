package androidx.window.area;

import android.os.Binder;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import androidx.window.core.ExtensionsUtil;
import androidx.window.extensions.area.ExtensionWindowAreaPresentation;
import androidx.window.extensions.area.WindowAreaComponent;
import androidx.window.layout.WindowMetrics;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0001'B)\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u000e\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00020\u0016J\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\u0006\u0010\u001c\u001a\u00020\u0016J\u0010\u0010\u001f\u001a\u00020\u001e2\u0006\u0010\u001c\u001a\u00020\u0016H\u0002J\u0013\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010#\u001a\u00020$H\u0016J\b\u0010%\u001a\u00020&H\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R0\u0010\u0014\u001a\u001e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00170\u0015j\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u0017`\u0018X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a¨\u0006("}, d2 = {"Landroidx/window/area/WindowAreaInfo;", "", "metrics", "Landroidx/window/layout/WindowMetrics;", ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY, "Landroidx/window/area/WindowAreaInfo$Type;", "token", "Landroid/os/Binder;", "windowAreaComponent", "Landroidx/window/extensions/area/WindowAreaComponent;", "<init>", "(Landroidx/window/layout/WindowMetrics;Landroidx/window/area/WindowAreaInfo$Type;Landroid/os/Binder;Landroidx/window/extensions/area/WindowAreaComponent;)V", "getMetrics", "()Landroidx/window/layout/WindowMetrics;", "setMetrics", "(Landroidx/window/layout/WindowMetrics;)V", "getType", "()Landroidx/window/area/WindowAreaInfo$Type;", "getToken", "()Landroid/os/Binder;", "capabilityMap", "Ljava/util/HashMap;", "Landroidx/window/area/WindowAreaCapability$Operation;", "Landroidx/window/area/WindowAreaCapability;", "Lkotlin/collections/HashMap;", "getCapabilityMap$window_release", "()Ljava/util/HashMap;", "getCapability", "operation", "getActiveSession", "Landroidx/window/area/WindowAreaSession;", "createRearFacingSession", "equals", "", "other", "hashCode", "", "toString", "", "Type", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class WindowAreaInfo {
    private final HashMap<WindowAreaCapability.Operation, WindowAreaCapability> capabilityMap;
    private WindowMetrics metrics;
    private final Binder token;
    private final Type type;
    private final WindowAreaComponent windowAreaComponent;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0007\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0003H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Landroidx/window/area/WindowAreaInfo$Type;", "", "description", "", "<init>", "(Ljava/lang/String;)V", "toString", "Companion", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Type {
        public static final Type TYPE_REAR_FACING = new Type("REAR FACING");
        private final String description;

        private Type(String str) {
            this.description = str;
        }

        /* JADX INFO: renamed from: toString, reason: from getter */
        public String getDescription() {
            return this.description;
        }
    }

    public WindowAreaInfo(WindowMetrics windowMetrics, Type type, Binder binder, WindowAreaComponent windowAreaComponent) {
        windowMetrics.getClass();
        type.getClass();
        binder.getClass();
        windowAreaComponent.getClass();
        this.metrics = windowMetrics;
        this.type = type;
        this.token = binder;
        this.windowAreaComponent = windowAreaComponent;
        this.capabilityMap = new HashMap<>();
    }

    private final WindowAreaSession createRearFacingSession(WindowAreaCapability.Operation operation) {
        if (Intrinsics.areEqual(operation, WindowAreaCapability.Operation.OPERATION_TRANSFER_ACTIVITY_TO_AREA)) {
            return new RearDisplaySessionImpl(this.windowAreaComponent);
        }
        if (!Intrinsics.areEqual(operation, WindowAreaCapability.Operation.OPERATION_PRESENT_ON_AREA)) {
            w01.a("Invalid operation provided");
            return null;
        }
        WindowAreaComponent windowAreaComponent = this.windowAreaComponent;
        ExtensionWindowAreaPresentation rearDisplayPresentation = windowAreaComponent.getRearDisplayPresentation();
        rearDisplayPresentation.getClass();
        return new RearDisplayPresentationSessionPresenterImpl(windowAreaComponent, rearDisplayPresentation, ExtensionsUtil.INSTANCE.getSafeVendorApiLevel());
    }

    public boolean equals(Object other) {
        if (!(other instanceof WindowAreaInfo)) {
            return false;
        }
        WindowAreaInfo windowAreaInfo = (WindowAreaInfo) other;
        return Intrinsics.areEqual(this.metrics, windowAreaInfo.metrics) && Intrinsics.areEqual(this.type, windowAreaInfo.type) && Intrinsics.areEqual(this.capabilityMap.entrySet(), windowAreaInfo.capabilityMap.entrySet());
    }

    public final WindowAreaSession getActiveSession(WindowAreaCapability.Operation operation) {
        operation.getClass();
        if (!Intrinsics.areEqual(getCapability(operation).getStatus(), WindowAreaCapability.Status.WINDOW_AREA_STATUS_ACTIVE)) {
            k2d.a("No session is currently active");
            return null;
        }
        if (Intrinsics.areEqual(this.type, Type.TYPE_REAR_FACING)) {
            return createRearFacingSession(operation);
        }
        return null;
    }

    public final WindowAreaCapability getCapability(WindowAreaCapability.Operation operation) {
        operation.getClass();
        WindowAreaCapability windowAreaCapability = this.capabilityMap.get(operation);
        return windowAreaCapability == null ? new WindowAreaCapability(operation, WindowAreaCapability.Status.WINDOW_AREA_STATUS_UNSUPPORTED) : windowAreaCapability;
    }

    public final HashMap<WindowAreaCapability.Operation, WindowAreaCapability> getCapabilityMap$window_release() {
        return this.capabilityMap;
    }

    public final WindowMetrics getMetrics() {
        return this.metrics;
    }

    public final Binder getToken() {
        return this.token;
    }

    public final Type getType() {
        return this.type;
    }

    public int hashCode() {
        return (((this.metrics.hashCode() * 31) + this.type.hashCode()) * 31) + this.capabilityMap.entrySet().hashCode();
    }

    public final void setMetrics(WindowMetrics windowMetrics) {
        windowMetrics.getClass();
        this.metrics = windowMetrics;
    }

    public String toString() {
        return "WindowAreaInfo{ Metrics: " + this.metrics + ", type: " + this.type + ", Capabilities: " + this.capabilityMap.entrySet() + " }";
    }
}
