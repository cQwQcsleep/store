package androidx.window.area;

import android.app.Activity;
import android.os.Binder;
import android.util.Log;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.window.area.WindowAreaController;
import androidx.window.core.BuildConfig;
import androidx.window.core.ExtensionsUtil;
import androidx.window.core.VerificationMode;
import androidx.window.extensions.area.WindowAreaComponent;
import java.util.List;
import java.util.concurrent.Executor;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.flow.Flow;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b'\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\t\b\u0007¢\u0006\u0004\b\u0002\u0010\u0003J(\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H&J(\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0016H&R\u001e\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u0018"}, d2 = {"Landroidx/window/area/WindowAreaController;", "", "<init>", "()V", "windowAreaInfos", "Lkotlinx/coroutines/flow/Flow;", "", "Landroidx/window/area/WindowAreaInfo;", "getWindowAreaInfos", "()Lkotlinx/coroutines/flow/Flow;", "transferActivityToWindowArea", "", "token", "Landroid/os/Binder;", "activity", "Landroid/app/Activity;", "executor", "Ljava/util/concurrent/Executor;", "windowAreaSessionCallback", "Landroidx/window/area/WindowAreaSessionCallback;", "presentContentOnWindowArea", "windowAreaPresentationSessionCallback", "Landroidx/window/area/WindowAreaPresentationSessionCallback;", "Companion", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public abstract class WindowAreaController {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = Reflection.getOrCreateKotlinClass(WindowAreaController.class).getSimpleName();
    private static WindowAreaControllerDecorator decorator = EmptyDecorator.INSTANCE;
    private static final Lazy<WindowAreaController> windowAreaController$delegate = LazyKt.lazy(new Function0() { // from class: fnf
        public final Object invoke() {
            return WindowAreaController.a();
        }
    });

    public static WindowAreaController a() {
        WindowAreaComponent windowAreaComponent = null;
        try {
            ClassLoader classLoader = INSTANCE.getClass().getClassLoader();
            if (classLoader != null) {
                windowAreaComponent = new SafeWindowAreaComponentProvider(classLoader).getWindowAreaComponent();
            }
        } catch (Throwable unused) {
            if (BuildConfig.INSTANCE.getVerificationMode() == VerificationMode.LOG) {
                Log.d(TAG, "Failed to load WindowExtensions");
            }
        }
        return (windowAreaComponent == null || ExtensionsUtil.INSTANCE.getSafeVendorApiLevel() < 3) ? new EmptyWindowAreaControllerImpl() : new WindowAreaControllerImpl(windowAreaComponent);
    }

    @JvmStatic
    public static final WindowAreaController getOrCreate() {
        return INSTANCE.getOrCreate();
    }

    @JvmStatic
    public static final void overrideDecorator(WindowAreaControllerDecorator windowAreaControllerDecorator) {
        INSTANCE.overrideDecorator(windowAreaControllerDecorator);
    }

    @JvmStatic
    public static final void reset() {
        INSTANCE.reset();
    }

    public abstract Flow<List<WindowAreaInfo>> getWindowAreaInfos();

    public abstract void presentContentOnWindowArea(Binder token, Activity activity, Executor executor, WindowAreaPresentationSessionCallback windowAreaPresentationSessionCallback);

    public abstract void transferActivityToWindowArea(Binder token, Activity activity, Executor executor, WindowAreaSessionCallback windowAreaSessionCallback);

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u000e\u001a\u00020\tH\u0007J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0007H\u0007J\b\u0010\u0012\u001a\u00020\u0010H\u0007R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\b\u001a\u00020\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000b¨\u0006\u0013"}, d2 = {"Landroidx/window/area/WindowAreaController$Companion;", "", "<init>", "()V", "TAG", "", "decorator", "Landroidx/window/area/WindowAreaControllerDecorator;", "windowAreaController", "Landroidx/window/area/WindowAreaController;", "getWindowAreaController", "()Landroidx/window/area/WindowAreaController;", "windowAreaController$delegate", "Lkotlin/Lazy;", "getOrCreate", "overrideDecorator", "", "overridingDecorator", "reset", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private final WindowAreaController getWindowAreaController() {
            return (WindowAreaController) WindowAreaController.windowAreaController$delegate.getValue();
        }

        @JvmStatic
        public final WindowAreaController getOrCreate() {
            return WindowAreaController.decorator.decorate(getWindowAreaController());
        }

        @JvmStatic
        public final void overrideDecorator(WindowAreaControllerDecorator overridingDecorator) {
            overridingDecorator.getClass();
            WindowAreaController.decorator = overridingDecorator;
        }

        @JvmStatic
        public final void reset() {
            WindowAreaController.decorator = EmptyDecorator.INSTANCE;
        }

        private Companion() {
        }
    }
}
