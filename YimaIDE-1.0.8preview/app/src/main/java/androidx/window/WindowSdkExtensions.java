package androidx.window;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.window.core.ExtensionsUtil;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.IntRange;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\t\b\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\b\u001a\u00020\t2\b\b\u0001\u0010\n\u001a\u00020\u0005H\u0000¢\u0006\u0002\b\u000bJ\u0015\u0010\b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\rH\u0000¢\u0006\u0002\b\u000bR\u0016\u0010\u0004\u001a\u00020\u00058WX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000f"}, d2 = {"Landroidx/window/WindowSdkExtensions;", "", "<init>", "()V", "extensionVersion", "", "getExtensionVersion", "()I", "requireExtensionVersion", "", "version", "requireExtensionVersion$window_release", "range", "Lkotlin/ranges/IntRange;", "Companion", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public abstract class WindowSdkExtensions {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static WindowSdkExtensionsDecorator decorator = EmptyDecoratorWindowSdk.INSTANCE;
    private final int extensionVersion = ExtensionsUtil.INSTANCE.getSafeVendorApiLevel();

    @JvmStatic
    public static final WindowSdkExtensions getInstance() {
        return INSTANCE.getInstance();
    }

    public int getExtensionVersion() {
        return this.extensionVersion;
    }

    public final void requireExtensionVersion$window_release(IntRange range) {
        range.getClass();
        int first = range.getFirst();
        int last = range.getLast();
        int extensionVersion = getExtensionVersion();
        if (first > extensionVersion || extensionVersion > last) {
            StringBuilder sb = new StringBuilder("This API requires extension version ");
            sb.append(range);
            int extensionVersion2 = getExtensionVersion();
            sb.append(", but the device is on ");
            sb.append(extensionVersion2);
            throw new UnsupportedOperationException(sb.toString());
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0007H\u0007J\b\u0010\u000b\u001a\u00020\tH\u0007R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Landroidx/window/WindowSdkExtensions$Companion;", "", "<init>", "()V", "getInstance", "Landroidx/window/WindowSdkExtensions;", "decorator", "Landroidx/window/WindowSdkExtensionsDecorator;", "overrideDecorator", "", "overridingDecorator", "reset", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final WindowSdkExtensions getInstance() {
            return WindowSdkExtensions.decorator.decorate(new WindowSdkExtensions() { // from class: androidx.window.WindowSdkExtensions$Companion$getInstance$1
            });
        }

        public final void overrideDecorator(WindowSdkExtensionsDecorator overridingDecorator) {
            overridingDecorator.getClass();
            WindowSdkExtensions.decorator = overridingDecorator;
        }

        public final void reset() {
            WindowSdkExtensions.decorator = EmptyDecoratorWindowSdk.INSTANCE;
        }

        private Companion() {
        }
    }

    public final void requireExtensionVersion$window_release(int version) {
        if (getExtensionVersion() >= version) {
            return;
        }
        throw new UnsupportedOperationException("This API requires extension version " + version + ", but the device is on " + getExtensionVersion());
    }
}
