package androidx.window.layout.adapter.extensions;

import android.app.Activity;
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
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\b\u0011\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J&\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u00192\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H\u0016J\u0016\u0010\u001b\u001a\u00020\u00162\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H\u0016J\b\u0010\u001c\u001a\u00020\u001dH\u0017R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r8\u0002X\u0083\u0004¢\u0006\u0002\n\u0000R\"\u0010\u0010\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u0011\u0012\u0004\u0012\u00020\u000e0\r8\u0002X\u0083\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00140\r8\u0002X\u0083\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Landroidx/window/layout/adapter/extensions/ExtensionWindowBackendApi1;", "Landroidx/window/layout/adapter/extensions/ExtensionWindowBackendApi0;", "component", "Landroidx/window/extensions/layout/WindowLayoutComponent;", "consumerAdapter", "Landroidx/window/core/ConsumerAdapter;", "<init>", "(Landroidx/window/extensions/layout/WindowLayoutComponent;Landroidx/window/core/ConsumerAdapter;)V", "getComponent", "()Landroidx/window/extensions/layout/WindowLayoutComponent;", "globalLock", "Ljava/util/concurrent/locks/ReentrantLock;", "contextToListeners", "", "Landroid/content/Context;", "Landroidx/window/layout/adapter/extensions/MulticastConsumer;", "listenerToContext", "Landroidx/core/util/Consumer;", "Landroidx/window/layout/WindowLayoutInfo;", "consumerToToken", "Landroidx/window/core/ConsumerAdapter$Subscription;", "registerLayoutChangeCallback", "", "context", "executor", "Ljava/util/concurrent/Executor;", "callback", "unregisterLayoutChangeCallback", "hasRegisteredListeners", "", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public class ExtensionWindowBackendApi1 extends ExtensionWindowBackendApi0 {
    private final WindowLayoutComponent component;
    private final ConsumerAdapter consumerAdapter;
    private final Map<MulticastConsumer, ConsumerAdapter.Subscription> consumerToToken;
    private final Map<Context, MulticastConsumer> contextToListeners;
    private final ReentrantLock globalLock;
    private final Map<Consumer<WindowLayoutInfo>, Context> listenerToContext;

    public ExtensionWindowBackendApi1(WindowLayoutComponent windowLayoutComponent, ConsumerAdapter consumerAdapter) {
        windowLayoutComponent.getClass();
        consumerAdapter.getClass();
        this.component = windowLayoutComponent;
        this.consumerAdapter = consumerAdapter;
        this.globalLock = new ReentrantLock();
        this.contextToListeners = new LinkedHashMap();
        this.listenerToContext = new LinkedHashMap();
        this.consumerToToken = new LinkedHashMap();
    }

    public final WindowLayoutComponent getComponent() {
        return this.component;
    }

    @Override // androidx.window.layout.adapter.WindowBackend
    public boolean hasRegisteredListeners() {
        return (this.contextToListeners.isEmpty() && this.listenerToContext.isEmpty() && this.consumerToToken.isEmpty()) ? false : true;
    }

    @Override // androidx.window.layout.adapter.extensions.ExtensionWindowBackendApi0, androidx.window.layout.adapter.WindowBackend
    public void registerLayoutChangeCallback(Context context, Executor executor, Consumer<WindowLayoutInfo> callback) {
        context.getClass();
        executor.getClass();
        callback.getClass();
        ReentrantLock reentrantLock = this.globalLock;
        reentrantLock.lock();
        try {
            MulticastConsumer multicastConsumer = this.contextToListeners.get(context);
            if (multicastConsumer != null) {
                multicastConsumer.addListener(callback);
                this.listenerToContext.put(callback, context);
            } else {
                MulticastConsumer multicastConsumer2 = new MulticastConsumer(context);
                this.contextToListeners.put(context, multicastConsumer2);
                this.listenerToContext.put(callback, context);
                multicastConsumer2.addListener(callback);
                if (!(context instanceof Activity)) {
                    multicastConsumer2.accept(new androidx.window.extensions.layout.WindowLayoutInfo(CollectionsKt.emptyList()));
                    return;
                } else {
                    this.consumerToToken.put(multicastConsumer2, this.consumerAdapter.createSubscription((Object) this.component, Reflection.getOrCreateKotlinClass(androidx.window.extensions.layout.WindowLayoutInfo.class), "addWindowLayoutInfoListener", "removeWindowLayoutInfoListener", (Activity) context, (Function1) new ExtensionWindowBackendApi1$registerLayoutChangeCallback$1$2$disposableToken$1(multicastConsumer2)));
                }
            }
            Unit unit = Unit.INSTANCE;
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // androidx.window.layout.adapter.extensions.ExtensionWindowBackendApi0, androidx.window.layout.adapter.WindowBackend
    public void unregisterLayoutChangeCallback(Consumer<WindowLayoutInfo> callback) {
        callback.getClass();
        ReentrantLock reentrantLock = this.globalLock;
        reentrantLock.lock();
        try {
            Context context = this.listenerToContext.get(callback);
            if (context == null) {
                return;
            }
            MulticastConsumer multicastConsumer = this.contextToListeners.get(context);
            if (multicastConsumer == null) {
                return;
            }
            multicastConsumer.removeListener(callback);
            this.listenerToContext.remove(callback);
            if (multicastConsumer.isEmpty()) {
                this.contextToListeners.remove(context);
                ConsumerAdapter.Subscription subscriptionRemove = this.consumerToToken.remove(multicastConsumer);
                if (subscriptionRemove != null) {
                    subscriptionRemove.dispose();
                }
            }
            Unit unit = Unit.INSTANCE;
        } finally {
            reentrantLock.unlock();
        }
    }
}
