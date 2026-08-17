package androidx.window.area;

import android.app.Activity;
import android.os.Binder;
import android.util.DisplayMetrics;
import android.util.Log;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.core.app.NotificationCompat;
import androidx.window.area.WindowAreaControllerImpl;
import androidx.window.area.adapter.WindowAreaAdapter;
import androidx.window.core.BuildConfig;
import androidx.window.core.ExtensionsUtil;
import androidx.window.core.VerificationMode;
import androidx.window.extensions.area.ExtensionWindowAreaPresentation;
import androidx.window.extensions.area.ExtensionWindowAreaStatus;
import androidx.window.extensions.area.WindowAreaComponent;
import androidx.window.layout.WindowMetrics;
import androidx.window.layout.WindowMetricsCalculator;
import androidx.window.reflection.Consumer2;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0001\u0018\u0000 62\u00020\u0001:\u0003456B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\bH\u0002J\u0010\u0010\u001c\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J \u0010\u001f\u001a\u00020\u001a2\u0006\u0010 \u001a\u00020!2\u0006\u0010\u001b\u001a\u00020\n2\u0006\u0010\"\u001a\u00020#H\u0002J\u0010\u0010$\u001a\u00020\r2\u0006\u0010%\u001a\u00020\u0012H\u0002J(\u0010&\u001a\u00020\u001a2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.H\u0016J(\u0010/\u001a\u00020\u001a2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,2\u0006\u00100\u001a\u000201H\u0016J \u00102\u001a\u00020\u001a2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.H\u0002J \u00103\u001a\u00020\u001a2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,2\u0006\u00100\u001a\u000201H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010\u000f\u001a\u001e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00120\u0010j\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0012`\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00160\u00158VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018¨\u00067"}, d2 = {"Landroidx/window/area/WindowAreaControllerImpl;", "Landroidx/window/area/WindowAreaController;", "windowAreaComponent", "Landroidx/window/extensions/area/WindowAreaComponent;", "<init>", "(Landroidx/window/extensions/area/WindowAreaComponent;)V", "rearDisplaySessionConsumer", "Landroidx/window/reflection/Consumer2;", "", "currentRearDisplayModeStatus", "Landroidx/window/area/WindowAreaCapability$Status;", "currentRearDisplayPresentationStatus", "activeWindowAreaSession", "", "presentationSessionActive", "currentWindowAreaInfoMap", "Ljava/util/HashMap;", "", "Landroidx/window/area/WindowAreaInfo;", "Lkotlin/collections/HashMap;", "windowAreaInfos", "Lkotlinx/coroutines/flow/Flow;", "", "getWindowAreaInfos", "()Lkotlinx/coroutines/flow/Flow;", "updateRearDisplayAvailability", "", NotificationCompat.CATEGORY_STATUS, "updateRearDisplayPresentationAvailability", "extensionWindowAreaStatus", "Landroidx/window/extensions/area/ExtensionWindowAreaStatus;", "updateRearDisplayWindowArea", "operation", "Landroidx/window/area/WindowAreaCapability$Operation;", "metrics", "Landroidx/window/layout/WindowMetrics;", "shouldRemoveWindowAreaInfo", "windowAreaInfo", "transferActivityToWindowArea", "token", "Landroid/os/Binder;", "activity", "Landroid/app/Activity;", "executor", "Ljava/util/concurrent/Executor;", "windowAreaSessionCallback", "Landroidx/window/area/WindowAreaSessionCallback;", "presentContentOnWindowArea", "windowAreaPresentationSessionCallback", "Landroidx/window/area/WindowAreaPresentationSessionCallback;", "startRearDisplayMode", "startRearDisplayPresentationMode", "RearDisplaySessionConsumer", "RearDisplayPresentationSessionConsumer", "Companion", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class WindowAreaControllerImpl extends WindowAreaController {
    private static final String REAR_DISPLAY_BINDER_DESCRIPTOR = "WINDOW_AREA_REAR_DISPLAY";
    private boolean activeWindowAreaSession;
    private WindowAreaCapability.Status currentRearDisplayModeStatus;
    private WindowAreaCapability.Status currentRearDisplayPresentationStatus;
    private final HashMap<String, WindowAreaInfo> currentWindowAreaInfoMap;
    private boolean presentationSessionActive;
    private Consumer2<Integer> rearDisplaySessionConsumer;
    private final WindowAreaComponent windowAreaComponent;
    private static final String TAG = Reflection.getOrCreateKotlinClass(WindowAreaControllerImpl.class).getSimpleName();

    public WindowAreaControllerImpl(WindowAreaComponent windowAreaComponent) {
        windowAreaComponent.getClass();
        this.windowAreaComponent = windowAreaComponent;
        WindowAreaCapability.Status.Companion companion = WindowAreaCapability.Status.INSTANCE;
        this.currentRearDisplayModeStatus = companion.getWINDOW_AREA_STATUS_UNKNOWN$window_release();
        this.currentRearDisplayPresentationStatus = companion.getWINDOW_AREA_STATUS_UNKNOWN$window_release();
        this.currentWindowAreaInfoMap = new HashMap<>();
    }

    public static void b(WindowAreaSessionCallback windowAreaSessionCallback) {
        windowAreaSessionCallback.onSessionEnded(new IllegalArgumentException("Invalid WindowAreaInfo token"));
    }

    public static void c(WindowAreaPresentationSessionCallback windowAreaPresentationSessionCallback) {
        windowAreaPresentationSessionCallback.onSessionEnded(new IllegalArgumentException("Invalid WindowAreaInfo token"));
    }

    private final boolean shouldRemoveWindowAreaInfo(WindowAreaInfo windowAreaInfo) {
        for (WindowAreaCapability windowAreaCapability : windowAreaInfo.getCapabilityMap$window_release().values()) {
            windowAreaCapability.getClass();
            if (!Intrinsics.areEqual(windowAreaCapability.getStatus(), WindowAreaCapability.Status.WINDOW_AREA_STATUS_UNSUPPORTED)) {
                return false;
            }
        }
        return true;
    }

    private final void startRearDisplayMode(Activity activity, Executor executor, WindowAreaSessionCallback windowAreaSessionCallback) {
        if (Intrinsics.areEqual(this.currentRearDisplayModeStatus, WindowAreaCapability.Status.WINDOW_AREA_STATUS_ACTIVE)) {
            windowAreaSessionCallback.onSessionEnded(new IllegalStateException("The WindowArea feature is currently active, WindowAreaInfo#getActiveSessioncan be used to get an instance of the current active session"));
            return;
        }
        if (!Intrinsics.areEqual(this.currentRearDisplayModeStatus, WindowAreaCapability.Status.WINDOW_AREA_STATUS_AVAILABLE)) {
            windowAreaSessionCallback.onSessionEnded(new IllegalStateException("The WindowArea feature is currently not available to be entered"));
            return;
        }
        this.activeWindowAreaSession = true;
        RearDisplaySessionConsumer rearDisplaySessionConsumer = new RearDisplaySessionConsumer(this, executor, windowAreaSessionCallback, this.windowAreaComponent);
        this.rearDisplaySessionConsumer = rearDisplaySessionConsumer;
        this.windowAreaComponent.startRearDisplaySession(activity, rearDisplaySessionConsumer);
    }

    private final void startRearDisplayPresentationMode(Activity activity, Executor executor, WindowAreaPresentationSessionCallback windowAreaPresentationSessionCallback) {
        if (!Intrinsics.areEqual(this.currentRearDisplayPresentationStatus, WindowAreaCapability.Status.WINDOW_AREA_STATUS_AVAILABLE)) {
            windowAreaPresentationSessionCallback.onSessionEnded(new IllegalStateException("The WindowArea feature is currently not available to be entered"));
        } else {
            this.presentationSessionActive = true;
            this.windowAreaComponent.startRearDisplayPresentationSession(activity, new RearDisplayPresentationSessionConsumer(this, executor, windowAreaPresentationSessionCallback, this.windowAreaComponent));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateRearDisplayAvailability(int status) {
        WindowMetricsCalculator.Companion companion = WindowMetricsCalculator.INSTANCE;
        DisplayMetrics rearDisplayMetrics = this.windowAreaComponent.getRearDisplayMetrics();
        rearDisplayMetrics.getClass();
        WindowMetrics windowMetricsFromDisplayMetrics$window_release = companion.fromDisplayMetrics$window_release(rearDisplayMetrics);
        WindowAreaCapability.Status statusTranslate$window_release$default = WindowAreaAdapter.translate$window_release$default(WindowAreaAdapter.INSTANCE, status, this.activeWindowAreaSession, 0, 4, null);
        this.currentRearDisplayModeStatus = statusTranslate$window_release$default;
        updateRearDisplayWindowArea(WindowAreaCapability.Operation.OPERATION_TRANSFER_ACTIVITY_TO_AREA, statusTranslate$window_release$default, windowMetricsFromDisplayMetrics$window_release);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateRearDisplayPresentationAvailability(ExtensionWindowAreaStatus extensionWindowAreaStatus) {
        this.currentRearDisplayPresentationStatus = WindowAreaAdapter.translate$window_release$default(WindowAreaAdapter.INSTANCE, extensionWindowAreaStatus.getWindowAreaStatus(), this.presentationSessionActive, 0, 4, null);
        WindowMetricsCalculator.Companion companion = WindowMetricsCalculator.INSTANCE;
        DisplayMetrics windowAreaDisplayMetrics = extensionWindowAreaStatus.getWindowAreaDisplayMetrics();
        windowAreaDisplayMetrics.getClass();
        updateRearDisplayWindowArea(WindowAreaCapability.Operation.OPERATION_PRESENT_ON_AREA, this.currentRearDisplayPresentationStatus, companion.fromDisplayMetrics$window_release(windowAreaDisplayMetrics));
    }

    private final void updateRearDisplayWindowArea(WindowAreaCapability.Operation operation, WindowAreaCapability.Status status, WindowMetrics metrics) {
        WindowAreaInfo windowAreaInfo = this.currentWindowAreaInfoMap.get(REAR_DISPLAY_BINDER_DESCRIPTOR);
        if (!Intrinsics.areEqual(status, WindowAreaCapability.Status.WINDOW_AREA_STATUS_UNSUPPORTED)) {
            if (windowAreaInfo == null) {
                windowAreaInfo = new WindowAreaInfo(metrics, WindowAreaInfo.Type.TYPE_REAR_FACING, new Binder(REAR_DISPLAY_BINDER_DESCRIPTOR), this.windowAreaComponent);
            }
            windowAreaInfo.getCapabilityMap$window_release().put(operation, new WindowAreaCapability(operation, status));
            windowAreaInfo.setMetrics(metrics);
            this.currentWindowAreaInfoMap.put(REAR_DISPLAY_BINDER_DESCRIPTOR, windowAreaInfo);
            return;
        }
        if (windowAreaInfo != null) {
            if (shouldRemoveWindowAreaInfo(windowAreaInfo)) {
                this.currentWindowAreaInfoMap.remove(REAR_DISPLAY_BINDER_DESCRIPTOR);
            } else {
                windowAreaInfo.getCapabilityMap$window_release().put(operation, new WindowAreaCapability(operation, status));
            }
        }
    }

    @Override // androidx.window.area.WindowAreaController
    public Flow<List<WindowAreaInfo>> getWindowAreaInfos() {
        return FlowKt.callbackFlow(new WindowAreaControllerImpl$windowAreaInfos$1(this, null));
    }

    @Override // androidx.window.area.WindowAreaController
    public void presentContentOnWindowArea(Binder token, Activity activity, Executor executor, final WindowAreaPresentationSessionCallback windowAreaPresentationSessionCallback) {
        token.getClass();
        activity.getClass();
        executor.getClass();
        windowAreaPresentationSessionCallback.getClass();
        if (Intrinsics.areEqual(token.getInterfaceDescriptor(), REAR_DISPLAY_BINDER_DESCRIPTOR)) {
            startRearDisplayPresentationMode(activity, executor, windowAreaPresentationSessionCallback);
        } else {
            executor.execute(new Runnable() { // from class: gnf
                @Override // java.lang.Runnable
                public final void run() {
                    WindowAreaControllerImpl.c(windowAreaPresentationSessionCallback);
                }
            });
        }
    }

    @Override // androidx.window.area.WindowAreaController
    public void transferActivityToWindowArea(Binder token, Activity activity, Executor executor, final WindowAreaSessionCallback windowAreaSessionCallback) {
        token.getClass();
        activity.getClass();
        executor.getClass();
        windowAreaSessionCallback.getClass();
        if (Intrinsics.areEqual(token.getInterfaceDescriptor(), REAR_DISPLAY_BINDER_DESCRIPTOR)) {
            startRearDisplayMode(activity, executor, windowAreaSessionCallback);
        } else {
            executor.execute(new Runnable() { // from class: hnf
                @Override // java.lang.Runnable
                public final void run() {
                    WindowAreaControllerImpl.b(windowAreaSessionCallback);
                }
            });
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0080\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0002H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0002X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Landroidx/window/area/WindowAreaControllerImpl$RearDisplayPresentationSessionConsumer;", "Landroidx/window/reflection/Consumer2;", "", "executor", "Ljava/util/concurrent/Executor;", "windowAreaPresentationSessionCallback", "Landroidx/window/area/WindowAreaPresentationSessionCallback;", "windowAreaComponent", "Landroidx/window/extensions/area/WindowAreaComponent;", "<init>", "(Landroidx/window/area/WindowAreaControllerImpl;Ljava/util/concurrent/Executor;Landroidx/window/area/WindowAreaPresentationSessionCallback;Landroidx/window/extensions/area/WindowAreaComponent;)V", "lastReportedSessionStatus", "accept", "", "value", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public final class RearDisplayPresentationSessionConsumer implements Consumer2<Integer> {
        private final Executor executor;
        private int lastReportedSessionStatus;
        final /* synthetic */ WindowAreaControllerImpl this$0;
        private final WindowAreaComponent windowAreaComponent;
        private final WindowAreaPresentationSessionCallback windowAreaPresentationSessionCallback;

        public RearDisplayPresentationSessionConsumer(WindowAreaControllerImpl windowAreaControllerImpl, Executor executor, WindowAreaPresentationSessionCallback windowAreaPresentationSessionCallback, WindowAreaComponent windowAreaComponent) {
            executor.getClass();
            windowAreaPresentationSessionCallback.getClass();
            windowAreaComponent.getClass();
            this.this$0 = windowAreaControllerImpl;
            this.executor = executor;
            this.windowAreaPresentationSessionCallback = windowAreaPresentationSessionCallback;
            this.windowAreaComponent = windowAreaComponent;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void accept$lambda$0(int i, int i2, RearDisplayPresentationSessionConsumer rearDisplayPresentationSessionConsumer, WindowAreaControllerImpl windowAreaControllerImpl) {
            if (i == 0) {
                windowAreaControllerImpl.presentationSessionActive = false;
                rearDisplayPresentationSessionConsumer.windowAreaPresentationSessionCallback.onSessionEnded(null);
                return;
            }
            if (i != 1) {
                if (i == 2) {
                    rearDisplayPresentationSessionConsumer.windowAreaPresentationSessionCallback.onContainerVisibilityChanged(true);
                    return;
                }
                Log.e(WindowAreaControllerImpl.TAG, "Invalid session state value received: " + i);
                return;
            }
            if (i2 == 2) {
                rearDisplayPresentationSessionConsumer.windowAreaPresentationSessionCallback.onContainerVisibilityChanged(false);
                return;
            }
            WindowAreaPresentationSessionCallback windowAreaPresentationSessionCallback = rearDisplayPresentationSessionConsumer.windowAreaPresentationSessionCallback;
            WindowAreaComponent windowAreaComponent = rearDisplayPresentationSessionConsumer.windowAreaComponent;
            ExtensionWindowAreaPresentation rearDisplayPresentation = windowAreaComponent.getRearDisplayPresentation();
            rearDisplayPresentation.getClass();
            windowAreaPresentationSessionCallback.onSessionStarted(new RearDisplayPresentationSessionPresenterImpl(windowAreaComponent, rearDisplayPresentation, ExtensionsUtil.INSTANCE.getSafeVendorApiLevel()));
        }

        public void accept(final int value) {
            final int i = this.lastReportedSessionStatus;
            this.lastReportedSessionStatus = value;
            Executor executor = this.executor;
            final WindowAreaControllerImpl windowAreaControllerImpl = this.this$0;
            executor.execute(new Runnable() { // from class: inf
                @Override // java.lang.Runnable
                public final void run() {
                    WindowAreaControllerImpl.RearDisplayPresentationSessionConsumer.accept$lambda$0(value, i, this, windowAreaControllerImpl);
                }
            });
        }

        @Override // androidx.window.reflection.Consumer2
        public /* bridge */ /* synthetic */ void accept(Integer num) {
            accept(num.intValue());
        }
    }

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0080\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0002H\u0016J\b\u0010\u0010\u001a\u00020\u000eH\u0002J\b\u0010\u0011\u001a\u00020\u000eH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Landroidx/window/area/WindowAreaControllerImpl$RearDisplaySessionConsumer;", "Landroidx/window/reflection/Consumer2;", "", "executor", "Ljava/util/concurrent/Executor;", "appCallback", "Landroidx/window/area/WindowAreaSessionCallback;", "extensionsComponent", "Landroidx/window/extensions/area/WindowAreaComponent;", "<init>", "(Landroidx/window/area/WindowAreaControllerImpl;Ljava/util/concurrent/Executor;Landroidx/window/area/WindowAreaSessionCallback;Landroidx/window/extensions/area/WindowAreaComponent;)V", "session", "Landroidx/window/area/WindowAreaSession;", "accept", "", "value", "onSessionStarted", "onSessionFinished", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public final class RearDisplaySessionConsumer implements Consumer2<Integer> {
        private final WindowAreaSessionCallback appCallback;
        private final Executor executor;
        private final WindowAreaComponent extensionsComponent;
        private WindowAreaSession session;
        final /* synthetic */ WindowAreaControllerImpl this$0;

        public RearDisplaySessionConsumer(WindowAreaControllerImpl windowAreaControllerImpl, Executor executor, WindowAreaSessionCallback windowAreaSessionCallback, WindowAreaComponent windowAreaComponent) {
            executor.getClass();
            windowAreaSessionCallback.getClass();
            windowAreaComponent.getClass();
            this.this$0 = windowAreaControllerImpl;
            this.executor = executor;
            this.appCallback = windowAreaSessionCallback;
            this.extensionsComponent = windowAreaComponent;
        }

        public static void a(RearDisplaySessionConsumer rearDisplaySessionConsumer, WindowAreaSession windowAreaSession) {
            rearDisplaySessionConsumer.appCallback.onSessionStarted(windowAreaSession);
        }

        public static void b(RearDisplaySessionConsumer rearDisplaySessionConsumer) {
            rearDisplaySessionConsumer.appCallback.onSessionEnded(null);
        }

        private final void onSessionFinished() {
            this.this$0.activeWindowAreaSession = false;
            this.session = null;
            this.executor.execute(new Runnable() { // from class: knf
                @Override // java.lang.Runnable
                public final void run() {
                    WindowAreaControllerImpl.RearDisplaySessionConsumer.b(this.b);
                }
            });
        }

        private final void onSessionStarted() {
            final RearDisplaySessionImpl rearDisplaySessionImpl = new RearDisplaySessionImpl(this.extensionsComponent);
            this.session = rearDisplaySessionImpl;
            this.executor.execute(new Runnable() { // from class: jnf
                @Override // java.lang.Runnable
                public final void run() {
                    WindowAreaControllerImpl.RearDisplaySessionConsumer.a(this.b, rearDisplaySessionImpl);
                }
            });
        }

        public void accept(int value) {
            if (value == 0) {
                onSessionFinished();
                return;
            }
            if (value == 1) {
                onSessionStarted();
                return;
            }
            if (BuildConfig.INSTANCE.getVerificationMode() == VerificationMode.STRICT) {
                Log.d(WindowAreaControllerImpl.TAG, "Received an unknown session status value: " + value);
            }
            onSessionFinished();
        }

        @Override // androidx.window.reflection.Consumer2
        public /* bridge */ /* synthetic */ void accept(Integer num) {
            accept(num.intValue());
        }
    }
}
