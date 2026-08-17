package androidx.window.layout;

import android.app.Activity;
import android.content.Context;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.core.util.Consumer;
import androidx.window.WindowSdkExtensions;
import androidx.window.layout.adapter.WindowBackend;
import defpackage.nab;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0016\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0016\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u0016\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u00128VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0017"}, d2 = {"Landroidx/window/layout/WindowInfoTrackerImpl;", "Landroidx/window/layout/WindowInfoTracker;", "windowMetricsCalculator", "Landroidx/window/layout/WindowMetricsCalculator;", "windowBackend", "Landroidx/window/layout/adapter/WindowBackend;", "windowSdkExtensions", "Landroidx/window/WindowSdkExtensions;", "<init>", "(Landroidx/window/layout/WindowMetricsCalculator;Landroidx/window/layout/adapter/WindowBackend;Landroidx/window/WindowSdkExtensions;)V", "windowLayoutInfo", "Lkotlinx/coroutines/flow/Flow;", "Landroidx/window/layout/WindowLayoutInfo;", "context", "Landroid/content/Context;", "activity", "Landroid/app/Activity;", "supportedPostures", "", "Landroidx/window/layout/SupportedPosture;", "getSupportedPostures", "()Ljava/util/List;", "getCurrentWindowLayoutInfo", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class WindowInfoTrackerImpl implements WindowInfoTracker {
    private final WindowBackend windowBackend;
    private final WindowMetricsCalculator windowMetricsCalculator;
    private final WindowSdkExtensions windowSdkExtensions;

    /* JADX INFO: renamed from: androidx.window.layout.WindowInfoTrackerImpl$windowLayoutInfo$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/channels/ProducerScope;", "Landroidx/window/layout/WindowLayoutInfo;"}, k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    @DebugMetadata(c = "androidx.window.layout.WindowInfoTrackerImpl$windowLayoutInfo$1", f = "WindowInfoTrackerImpl.kt", i = {}, l = {52}, m = "invokeSuspend", n = {}, s = {})
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<ProducerScope<? super WindowLayoutInfo>, Continuation<? super Unit>, Object> {
        final /* synthetic */ Context $context;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(Context context, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$context = context;
        }

        public static Unit b(WindowInfoTrackerImpl windowInfoTrackerImpl, Consumer consumer) {
            windowInfoTrackerImpl.windowBackend.unregisterLayoutChangeCallback(consumer);
            return Unit.INSTANCE;
        }

        public static void d(ProducerScope producerScope, WindowLayoutInfo windowLayoutInfo) {
            producerScope.trySend-JP2dKIU(windowLayoutInfo);
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = WindowInfoTrackerImpl.this.new AnonymousClass1(this.$context, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        public final Object invoke(ProducerScope<? super WindowLayoutInfo> producerScope, Continuation<? super Unit> continuation) {
            return create(producerScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                final ProducerScope producerScope = (ProducerScope) this.L$0;
                final Consumer<WindowLayoutInfo> consumer = new Consumer() { // from class: androidx.window.layout.a
                    @Override // androidx.core.util.Consumer
                    public final void accept(Object obj2) {
                        WindowInfoTrackerImpl.AnonymousClass1.d(producerScope, (WindowLayoutInfo) obj2);
                    }
                };
                WindowInfoTrackerImpl.this.windowBackend.registerLayoutChangeCallback(this.$context, new nab(), consumer);
                final WindowInfoTrackerImpl windowInfoTrackerImpl = WindowInfoTrackerImpl.this;
                Function0 function0 = new Function0() { // from class: androidx.window.layout.b
                    public final Object invoke() {
                        return WindowInfoTrackerImpl.AnonymousClass1.b(windowInfoTrackerImpl, consumer);
                    }
                };
                this.label = 1;
                if (ProduceKt.awaitClose(producerScope, function0, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    k2d.a("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: androidx.window.layout.WindowInfoTrackerImpl$windowLayoutInfo$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/channels/ProducerScope;", "Landroidx/window/layout/WindowLayoutInfo;"}, k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    @DebugMetadata(c = "androidx.window.layout.WindowInfoTrackerImpl$windowLayoutInfo$2", f = "WindowInfoTrackerImpl.kt", i = {}, l = {62}, m = "invokeSuspend", n = {}, s = {})
    public static final class AnonymousClass2 extends SuspendLambda implements Function2<ProducerScope<? super WindowLayoutInfo>, Continuation<? super Unit>, Object> {
        final /* synthetic */ Activity $activity;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(Activity activity, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$activity = activity;
        }

        public static Unit b(WindowInfoTrackerImpl windowInfoTrackerImpl, Consumer consumer) {
            windowInfoTrackerImpl.windowBackend.unregisterLayoutChangeCallback(consumer);
            return Unit.INSTANCE;
        }

        public static void d(ProducerScope producerScope, WindowLayoutInfo windowLayoutInfo) {
            producerScope.trySend-JP2dKIU(windowLayoutInfo);
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = WindowInfoTrackerImpl.this.new AnonymousClass2(this.$activity, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        public final Object invoke(ProducerScope<? super WindowLayoutInfo> producerScope, Continuation<? super Unit> continuation) {
            return create(producerScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                final ProducerScope producerScope = (ProducerScope) this.L$0;
                final Consumer<WindowLayoutInfo> consumer = new Consumer() { // from class: androidx.window.layout.c
                    @Override // androidx.core.util.Consumer
                    public final void accept(Object obj2) {
                        WindowInfoTrackerImpl.AnonymousClass2.d(producerScope, (WindowLayoutInfo) obj2);
                    }
                };
                WindowInfoTrackerImpl.this.windowBackend.registerLayoutChangeCallback(this.$activity, new nab(), consumer);
                final WindowInfoTrackerImpl windowInfoTrackerImpl = WindowInfoTrackerImpl.this;
                Function0 function0 = new Function0() { // from class: androidx.window.layout.d
                    public final Object invoke() {
                        return WindowInfoTrackerImpl.AnonymousClass2.b(windowInfoTrackerImpl, consumer);
                    }
                };
                this.label = 1;
                if (ProduceKt.awaitClose(producerScope, function0, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    k2d.a("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    public WindowInfoTrackerImpl(WindowMetricsCalculator windowMetricsCalculator, WindowBackend windowBackend, WindowSdkExtensions windowSdkExtensions) {
        windowMetricsCalculator.getClass();
        windowBackend.getClass();
        windowSdkExtensions.getClass();
        this.windowMetricsCalculator = windowMetricsCalculator;
        this.windowBackend = windowBackend;
        this.windowSdkExtensions = windowSdkExtensions;
    }

    @Override // androidx.window.layout.WindowInfoTracker
    public WindowLayoutInfo getCurrentWindowLayoutInfo(Context context) {
        context.getClass();
        this.windowSdkExtensions.requireExtensionVersion$window_release(9);
        return this.windowBackend.getCurrentWindowLayoutInfo(context);
    }

    @Override // androidx.window.layout.WindowInfoTracker
    public List<SupportedPosture> getSupportedPostures() {
        this.windowSdkExtensions.requireExtensionVersion$window_release(6);
        return this.windowBackend.getSupportedPostures();
    }

    @Override // androidx.window.layout.WindowInfoTracker
    public Flow<WindowLayoutInfo> windowLayoutInfo(Context context) {
        context.getClass();
        return FlowKt.flowOn(FlowKt.callbackFlow(new AnonymousClass1(context, null)), Dispatchers.getMain());
    }

    @Override // androidx.window.layout.WindowInfoTracker
    public Flow<WindowLayoutInfo> windowLayoutInfo(Activity activity) {
        activity.getClass();
        return FlowKt.flowOn(FlowKt.callbackFlow(new AnonymousClass2(activity, null)), Dispatchers.getMain());
    }
}
