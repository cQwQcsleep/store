package androidx.window.layout;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.window.WindowSdkExtensions;
import androidx.window.core.ConsumerAdapter;
import androidx.window.extensions.layout.WindowLayoutComponent;
import androidx.window.layout.WindowInfoTracker;
import androidx.window.layout.adapter.WindowBackend;
import androidx.window.layout.adapter.extensions.ExtensionWindowBackend;
import androidx.window.layout.adapter.sidecar.SidecarWindowBackend;
import androidx.window.layout.util.DensityCompatHelper;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.flow.Flow;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\bf\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011J\u0016\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0016\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0007\u001a\u00020\bH&J\u0010\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0017R \u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n8WX\u0097\u0004¢\u0006\f\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0012À\u0006\u0001"}, d2 = {"Landroidx/window/layout/WindowInfoTracker;", "", "windowLayoutInfo", "Lkotlinx/coroutines/flow/Flow;", "Landroidx/window/layout/WindowLayoutInfo;", "context", "Landroid/content/Context;", "activity", "Landroid/app/Activity;", "supportedPostures", "", "Landroidx/window/layout/SupportedPosture;", "getSupportedPostures$annotations", "()V", "getSupportedPostures", "()Ljava/util/List;", "getCurrentWindowLayoutInfo", "Companion", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public interface WindowInfoTracker {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0007J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0010H\u0007J\b\u0010\u0018\u001a\u00020\u0016H\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R#\u0010\b\u001a\u0004\u0018\u00010\t8@X\u0080\u0084\u0002¢\u0006\u0012\n\u0004\b\r\u0010\u000e\u0012\u0004\b\n\u0010\u0003\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Landroidx/window/layout/WindowInfoTracker$Companion;", "", "<init>", "()V", "DEBUG", "", "TAG", "", "extensionBackend", "Landroidx/window/layout/adapter/WindowBackend;", "getExtensionBackend$window_release$annotations", "getExtensionBackend$window_release", "()Landroidx/window/layout/adapter/WindowBackend;", "extensionBackend$delegate", "Lkotlin/Lazy;", "decorator", "Landroidx/window/layout/WindowInfoTrackerDecorator;", "getOrCreate", "Landroidx/window/layout/WindowInfoTracker;", "context", "Landroid/content/Context;", "overrideDecorator", "", "overridingDecorator", "reset", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Companion {
        private static final boolean DEBUG = false;
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final String TAG = Reflection.getOrCreateKotlinClass(WindowInfoTracker.class).getSimpleName();

        /* JADX INFO: renamed from: extensionBackend$delegate, reason: from kotlin metadata */
        private static final Lazy<WindowBackend> extensionBackend = LazyKt.lazy(new Function0() { // from class: lnf
            public final Object invoke() {
                return WindowInfoTracker.Companion.a();
            }
        });
        private static WindowInfoTrackerDecorator decorator = EmptyDecorator.INSTANCE;

        private Companion() {
        }

        public static WindowBackend a() {
            WindowLayoutComponent windowLayoutComponent;
            try {
                ClassLoader classLoader = WindowInfoTracker.class.getClassLoader();
                SafeWindowLayoutComponentProvider safeWindowLayoutComponentProvider = classLoader != null ? new SafeWindowLayoutComponentProvider(classLoader, new ConsumerAdapter(classLoader)) : null;
                if (safeWindowLayoutComponentProvider != null && (windowLayoutComponent = safeWindowLayoutComponentProvider.getWindowLayoutComponent()) != null) {
                    return ExtensionWindowBackend.INSTANCE.newInstance(windowLayoutComponent, new ConsumerAdapter(classLoader));
                }
            } catch (Throwable unused) {
                if (DEBUG) {
                    Log.d(TAG, "Failed to load WindowExtensions");
                }
            }
            return null;
        }

        public static /* synthetic */ void getExtensionBackend$window_release$annotations() {
        }

        public final WindowBackend getExtensionBackend$window_release() {
            return (WindowBackend) extensionBackend.getValue();
        }

        @JvmStatic
        public final WindowInfoTracker getOrCreate(Context context) {
            context.getClass();
            WindowBackend extensionBackend$window_release = getExtensionBackend$window_release();
            if (extensionBackend$window_release == null) {
                extensionBackend$window_release = SidecarWindowBackend.INSTANCE.getInstance(context);
            }
            DensityCompatHelper densityCompatHelper = null;
            return decorator.decorate(new WindowInfoTrackerImpl(new WindowMetricsCalculatorCompat(densityCompatHelper, 1, densityCompatHelper), extensionBackend$window_release, WindowSdkExtensions.INSTANCE.getInstance()));
        }

        @JvmStatic
        public final void overrideDecorator(WindowInfoTrackerDecorator overridingDecorator) {
            overridingDecorator.getClass();
            decorator = overridingDecorator;
        }

        @JvmStatic
        public final void reset() {
            decorator = EmptyDecorator.INSTANCE;
        }
    }

    @JvmStatic
    static WindowInfoTracker getOrCreate(Context context) {
        return INSTANCE.getOrCreate(context);
    }

    static /* synthetic */ void getSupportedPostures$annotations() {
    }

    @JvmStatic
    static void overrideDecorator(WindowInfoTrackerDecorator windowInfoTrackerDecorator) {
        INSTANCE.overrideDecorator(windowInfoTrackerDecorator);
    }

    @JvmStatic
    static void reset() {
        INSTANCE.reset();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NotImplementedError */
    default WindowLayoutInfo getCurrentWindowLayoutInfo(Context context) throws NotImplementedError {
        context.getClass();
        throw new NotImplementedError("Method was not implemented.");
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NotImplementedError */
    default List<SupportedPosture> getSupportedPostures() throws NotImplementedError {
        throw new NotImplementedError("Method was not implemented.");
    }

    Flow<WindowLayoutInfo> windowLayoutInfo(Activity activity);

    default Flow<WindowLayoutInfo> windowLayoutInfo(Context context) {
        context.getClass();
        Activity activity = context instanceof Activity ? (Activity) context : null;
        Flow<WindowLayoutInfo> flowWindowLayoutInfo = activity != null ? windowLayoutInfo(activity) : null;
        if (flowWindowLayoutInfo != null) {
            return flowWindowLayoutInfo;
        }
        e2f.a("Must override windowLayoutInfo(context) and provide an implementation.");
        return null;
    }
}
