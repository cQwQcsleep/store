package androidx.window.embedding;

import android.app.Activity;
import android.graphics.Rect;
import android.util.ArrayMap;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.core.util.Consumer;
import androidx.window.WindowSdkExtensions;
import androidx.window.embedding.ActivityWindowInfoCallbackController;
import androidx.window.extensions.embedding.ActivityEmbeddingComponent;
import androidx.window.extensions.embedding.EmbeddedActivityWindowInfo;
import androidx.window.reflection.Consumer2;
import defpackage.nab;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0011\u0018\u00002\u00020\u0001:\u0001\u001fB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001c\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u000e0\rJ\u0014\u0010\u001b\u001a\u00020\u00172\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u000e0\rJ\u0015\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\nH\u0011¢\u0006\u0002\b\u001eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t8\u0002X\u0083\u0004¢\u0006\u0002\n\u0000R:\u0010\u000b\u001a\u0018\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\b\u0012\u00060\u000fR\u00020\u00000\f8\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006 "}, d2 = {"Landroidx/window/embedding/ActivityWindowInfoCallbackController;", "", "embeddingExtension", "Landroidx/window/extensions/embedding/ActivityEmbeddingComponent;", "<init>", "(Landroidx/window/extensions/embedding/ActivityEmbeddingComponent;)V", "globalLock", "Ljava/util/concurrent/locks/ReentrantLock;", "extensionsCallback", "Landroidx/window/extensions/core/util/function/Consumer;", "Landroidx/window/extensions/embedding/EmbeddedActivityWindowInfo;", "callbacks", "", "Landroidx/core/util/Consumer;", "Landroidx/window/embedding/EmbeddedActivityWindowInfo;", "Landroidx/window/embedding/ActivityWindowInfoCallbackController$CallbackWrapper;", "getCallbacks$window_release$annotations", "()V", "getCallbacks$window_release", "()Ljava/util/Map;", "setCallbacks$window_release", "(Ljava/util/Map;)V", "addCallback", "", "activity", "Landroid/app/Activity;", "callback", "removeCallback", "translate", "info", "translate$window_release", "CallbackWrapper", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public class ActivityWindowInfoCallbackController {
    private Map<Consumer<EmbeddedActivityWindowInfo>, CallbackWrapper> callbacks;
    private final ActivityEmbeddingComponent embeddingExtension;
    private final androidx.window.extensions.core.util.function.Consumer<androidx.window.extensions.embedding.EmbeddedActivityWindowInfo> extensionsCallback;
    private final ReentrantLock globalLock;

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0081\u0004\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0006H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0017"}, d2 = {"Landroidx/window/embedding/ActivityWindowInfoCallbackController$CallbackWrapper;", "", "activity", "Landroid/app/Activity;", "callback", "Landroidx/core/util/Consumer;", "Landroidx/window/embedding/EmbeddedActivityWindowInfo;", "<init>", "(Landroidx/window/embedding/ActivityWindowInfoCallbackController;Landroid/app/Activity;Landroidx/core/util/Consumer;)V", "getCallback", "()Landroidx/core/util/Consumer;", "lastReportedInfo", "getLastReportedInfo", "()Landroidx/window/embedding/EmbeddedActivityWindowInfo;", "setLastReportedInfo", "(Landroidx/window/embedding/EmbeddedActivityWindowInfo;)V", "accept", "", "extensionsActivityWindowInfo", "Landroidx/window/extensions/embedding/EmbeddedActivityWindowInfo;", "shouldReportInfo", "", "newInfo", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public final class CallbackWrapper {
        private final Activity activity;
        private final Consumer<EmbeddedActivityWindowInfo> callback;
        private EmbeddedActivityWindowInfo lastReportedInfo;
        final /* synthetic */ ActivityWindowInfoCallbackController this$0;

        public CallbackWrapper(ActivityWindowInfoCallbackController activityWindowInfoCallbackController, Activity activity, Consumer<EmbeddedActivityWindowInfo> consumer) {
            activity.getClass();
            consumer.getClass();
            this.this$0 = activityWindowInfoCallbackController;
            this.activity = activity;
            this.callback = consumer;
        }

        private final boolean shouldReportInfo(EmbeddedActivityWindowInfo newInfo) {
            EmbeddedActivityWindowInfo embeddedActivityWindowInfo = this.lastReportedInfo;
            if (embeddedActivityWindowInfo == null) {
                return newInfo.getIsEmbedded();
            }
            if (embeddedActivityWindowInfo.getIsEmbedded() != newInfo.getIsEmbedded()) {
                return true;
            }
            if (newInfo.getIsEmbedded()) {
                return !Intrinsics.areEqual(embeddedActivityWindowInfo, newInfo);
            }
            return false;
        }

        public final void accept(androidx.window.extensions.embedding.EmbeddedActivityWindowInfo extensionsActivityWindowInfo) {
            extensionsActivityWindowInfo.getClass();
            Activity activity = extensionsActivityWindowInfo.getActivity();
            activity.getClass();
            if (Intrinsics.areEqual(this.activity, activity)) {
                EmbeddedActivityWindowInfo embeddedActivityWindowInfoTranslate$window_release = this.this$0.translate$window_release(extensionsActivityWindowInfo);
                if (shouldReportInfo(embeddedActivityWindowInfoTranslate$window_release)) {
                    this.lastReportedInfo = embeddedActivityWindowInfoTranslate$window_release;
                    this.callback.accept(embeddedActivityWindowInfoTranslate$window_release);
                }
            }
        }

        public final Consumer<EmbeddedActivityWindowInfo> getCallback() {
            return this.callback;
        }

        public final EmbeddedActivityWindowInfo getLastReportedInfo() {
            return this.lastReportedInfo;
        }

        public final void setLastReportedInfo(EmbeddedActivityWindowInfo embeddedActivityWindowInfo) {
            this.lastReportedInfo = embeddedActivityWindowInfo;
        }
    }

    public ActivityWindowInfoCallbackController(ActivityEmbeddingComponent activityEmbeddingComponent) {
        activityEmbeddingComponent.getClass();
        this.embeddingExtension = activityEmbeddingComponent;
        this.globalLock = new ReentrantLock();
        this.callbacks = new ArrayMap();
        WindowSdkExtensions.INSTANCE.getInstance().requireExtensionVersion$window_release(6);
        this.extensionsCallback = new Consumer2() { // from class: wu
            @Override // androidx.window.reflection.Consumer2
            public final void accept(Object obj) {
                ActivityWindowInfoCallbackController.a(this.b, (EmbeddedActivityWindowInfo) obj);
            }
        };
    }

    public static void a(ActivityWindowInfoCallbackController activityWindowInfoCallbackController, androidx.window.extensions.embedding.EmbeddedActivityWindowInfo embeddedActivityWindowInfo) {
        embeddedActivityWindowInfo.getClass();
        ReentrantLock reentrantLock = activityWindowInfoCallbackController.globalLock;
        reentrantLock.lock();
        try {
            Iterator<CallbackWrapper> it = activityWindowInfoCallbackController.callbacks.values().iterator();
            while (it.hasNext()) {
                it.next().accept(embeddedActivityWindowInfo);
            }
            Unit unit = Unit.INSTANCE;
        } finally {
            reentrantLock.unlock();
        }
    }

    public static /* synthetic */ void getCallbacks$window_release$annotations() {
    }

    public final void addCallback(Activity activity, Consumer<EmbeddedActivityWindowInfo> callback) {
        activity.getClass();
        callback.getClass();
        ReentrantLock reentrantLock = this.globalLock;
        reentrantLock.lock();
        try {
            if (this.callbacks.isEmpty()) {
                this.embeddingExtension.setEmbeddedActivityWindowInfoCallback(new nab(), this.extensionsCallback);
            }
            CallbackWrapper callbackWrapper = new CallbackWrapper(this, activity, callback);
            this.callbacks.put(callback, callbackWrapper);
            androidx.window.extensions.embedding.EmbeddedActivityWindowInfo embeddedActivityWindowInfo = this.embeddingExtension.getEmbeddedActivityWindowInfo(activity);
            if (embeddedActivityWindowInfo != null) {
                callbackWrapper.accept(embeddedActivityWindowInfo);
            }
        } finally {
            reentrantLock.unlock();
        }
    }

    public final Map<Consumer<EmbeddedActivityWindowInfo>, CallbackWrapper> getCallbacks$window_release() {
        return this.callbacks;
    }

    public final void removeCallback(Consumer<EmbeddedActivityWindowInfo> callback) {
        callback.getClass();
        ReentrantLock reentrantLock = this.globalLock;
        reentrantLock.lock();
        try {
            if (this.callbacks.remove(callback) == null) {
                return;
            }
            if (this.callbacks.isEmpty()) {
                this.embeddingExtension.clearEmbeddedActivityWindowInfoCallback();
            }
            Unit unit = Unit.INSTANCE;
        } finally {
            reentrantLock.unlock();
        }
    }

    public final void setCallbacks$window_release(Map<Consumer<EmbeddedActivityWindowInfo>, CallbackWrapper> map) {
        map.getClass();
        this.callbacks = map;
    }

    public EmbeddedActivityWindowInfo translate$window_release(androidx.window.extensions.embedding.EmbeddedActivityWindowInfo info) {
        info.getClass();
        Rect rect = new Rect(info.getTaskBounds());
        Rect rect2 = new Rect(info.getActivityStackBounds());
        rect2.offset(-rect.left, -rect.top);
        return new EmbeddedActivityWindowInfo(info.isEmbedded(), rect, rect2);
    }
}
