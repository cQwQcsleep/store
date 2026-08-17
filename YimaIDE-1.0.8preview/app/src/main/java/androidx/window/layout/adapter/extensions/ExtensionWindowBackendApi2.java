package androidx.window.layout.adapter.extensions;

import android.content.Context;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.core.util.Consumer;
import androidx.window.core.ConsumerAdapter;
import androidx.window.extensions.layout.WindowLayoutComponent;
import androidx.window.layout.WindowLayoutInfo;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.Unit;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\b\u0011\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J&\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0016J\u0016\u0010\u0017\u001a\u00020\u00122\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0016J\b\u0010\u0018\u001a\u00020\u0019H\u0017R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b8\u0002X\u0083\u0004¢\u0006\u0002\n\u0000R\"\u0010\u000e\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f\u0012\u0004\u0012\u00020\f0\u000b8\u0002X\u0083\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Landroidx/window/layout/adapter/extensions/ExtensionWindowBackendApi2;", "Landroidx/window/layout/adapter/extensions/ExtensionWindowBackendApi1;", "component", "Landroidx/window/extensions/layout/WindowLayoutComponent;", "adapter", "Landroidx/window/core/ConsumerAdapter;", "<init>", "(Landroidx/window/extensions/layout/WindowLayoutComponent;Landroidx/window/core/ConsumerAdapter;)V", "globalLock", "Ljava/util/concurrent/locks/ReentrantLock;", "contextToListeners", "", "Landroid/content/Context;", "Landroidx/window/layout/adapter/extensions/MulticastConsumerApi2;", "listenerToContext", "Landroidx/core/util/Consumer;", "Landroidx/window/layout/WindowLayoutInfo;", "registerLayoutChangeCallback", "", "context", "executor", "Ljava/util/concurrent/Executor;", "callback", "unregisterLayoutChangeCallback", "hasRegisteredListeners", "", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public class ExtensionWindowBackendApi2 extends ExtensionWindowBackendApi1 {
    private final Map<Context, MulticastConsumerApi2> contextToListeners;
    private final ReentrantLock globalLock;
    private final Map<Consumer<WindowLayoutInfo>, Context> listenerToContext;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ExtensionWindowBackendApi2(WindowLayoutComponent windowLayoutComponent, ConsumerAdapter consumerAdapter) {
        super(windowLayoutComponent, consumerAdapter);
        windowLayoutComponent.getClass();
        consumerAdapter.getClass();
        this.globalLock = new ReentrantLock();
        this.contextToListeners = new LinkedHashMap();
        this.listenerToContext = new LinkedHashMap();
    }

    @Override // androidx.window.layout.adapter.extensions.ExtensionWindowBackendApi1, androidx.window.layout.adapter.WindowBackend
    public boolean hasRegisteredListeners() {
        return (this.contextToListeners.isEmpty() && this.listenerToContext.isEmpty()) ? false : true;
    }

    @Override // androidx.window.layout.adapter.extensions.ExtensionWindowBackendApi1, androidx.window.layout.adapter.extensions.ExtensionWindowBackendApi0, androidx.window.layout.adapter.WindowBackend
    public void registerLayoutChangeCallback(Context context, Executor executor, Consumer<WindowLayoutInfo> callback) {
        context.getClass();
        executor.getClass();
        callback.getClass();
        ReentrantLock reentrantLock = this.globalLock;
        reentrantLock.lock();
        try {
            MulticastConsumerApi2 multicastConsumerApi2 = this.contextToListeners.get(context);
            if (multicastConsumerApi2 != null) {
                multicastConsumerApi2.addListener(callback);
                this.listenerToContext.put(callback, context);
            } else {
                MulticastConsumerApi2 multicastConsumerApi3 = new MulticastConsumerApi2(context);
                this.contextToListeners.put(context, multicastConsumerApi3);
                this.listenerToContext.put(callback, context);
                multicastConsumerApi3.addListener(callback);
                getComponent().addWindowLayoutInfoListener(context, multicastConsumerApi3);
            }
            Unit unit = Unit.INSTANCE;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // androidx.window.layout.adapter.extensions.ExtensionWindowBackendApi1, androidx.window.layout.adapter.extensions.ExtensionWindowBackendApi0, androidx.window.layout.adapter.WindowBackend
    public void unregisterLayoutChangeCallback(Consumer<WindowLayoutInfo> callback) {
        callback.getClass();
        ReentrantLock reentrantLock = this.globalLock;
        reentrantLock.lock();
        try {
            Context context = this.listenerToContext.get(callback);
            if (context == null) {
                return;
            }
            MulticastConsumerApi2 multicastConsumerApi2 = this.contextToListeners.get(context);
            if (multicastConsumerApi2 == null) {
                return;
            }
            multicastConsumerApi2.removeListener(callback);
            this.listenerToContext.remove(callback);
            if (multicastConsumerApi2.isEmpty()) {
                this.contextToListeners.remove(context);
                getComponent().removeWindowLayoutInfoListener(multicastConsumerApi2);
            }
            Unit unit = Unit.INSTANCE;
        } finally {
            reentrantLock.unlock();
        }
    }
}
