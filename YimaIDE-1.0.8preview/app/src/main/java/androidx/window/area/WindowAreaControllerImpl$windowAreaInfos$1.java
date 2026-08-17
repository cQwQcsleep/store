package androidx.window.area;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.window.extensions.area.ExtensionWindowAreaStatus;
import androidx.window.reflection.Consumer2;
import io.github.rosemoe.sora.util.CharCode;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.channels.SendChannel;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/channels/ProducerScope;", "", "Landroidx/window/area/WindowAreaInfo;"}, k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
@DebugMetadata(c = "androidx.window.area.WindowAreaControllerImpl$windowAreaInfos$1", f = "WindowAreaControllerImpl.kt", i = {}, l = {CharCode.Backslash}, m = "invokeSuspend", n = {}, s = {})
public final class WindowAreaControllerImpl$windowAreaInfos$1 extends SuspendLambda implements Function2<ProducerScope<? super List<? extends WindowAreaInfo>>, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ WindowAreaControllerImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WindowAreaControllerImpl$windowAreaInfos$1(WindowAreaControllerImpl windowAreaControllerImpl, Continuation<? super WindowAreaControllerImpl$windowAreaInfos$1> continuation) {
        super(2, continuation);
        this.this$0 = windowAreaControllerImpl;
    }

    public static void b(WindowAreaControllerImpl windowAreaControllerImpl, ProducerScope producerScope, ExtensionWindowAreaStatus extensionWindowAreaStatus) {
        windowAreaControllerImpl.updateRearDisplayPresentationAvailability(extensionWindowAreaStatus);
        SendChannel channel = producerScope.getChannel();
        Collection collectionValues = windowAreaControllerImpl.currentWindowAreaInfoMap.values();
        collectionValues.getClass();
        channel.trySend-JP2dKIU(CollectionsKt.toList(collectionValues));
    }

    public static void d(WindowAreaControllerImpl windowAreaControllerImpl, ProducerScope producerScope, int i) {
        windowAreaControllerImpl.updateRearDisplayAvailability(i);
        SendChannel channel = producerScope.getChannel();
        Collection collectionValues = windowAreaControllerImpl.currentWindowAreaInfoMap.values();
        collectionValues.getClass();
        channel.trySend-JP2dKIU(CollectionsKt.toList(collectionValues));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invokeSuspend$lambda$2(WindowAreaControllerImpl windowAreaControllerImpl, Consumer2 consumer2, Consumer2 consumer3) {
        windowAreaControllerImpl.windowAreaComponent.removeRearDisplayStatusListener(consumer2);
        windowAreaControllerImpl.windowAreaComponent.removeRearDisplayPresentationStatusListener(consumer3);
        return Unit.INSTANCE;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        WindowAreaControllerImpl$windowAreaInfos$1 windowAreaControllerImpl$windowAreaInfos$1 = new WindowAreaControllerImpl$windowAreaInfos$1(this.this$0, continuation);
        windowAreaControllerImpl$windowAreaInfos$1.L$0 = obj;
        return windowAreaControllerImpl$windowAreaInfos$1;
    }

    public final Object invoke(ProducerScope<? super List<WindowAreaInfo>> producerScope, Continuation<? super Unit> continuation) {
        return create(producerScope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final ProducerScope producerScope = (ProducerScope) this.L$0;
            final WindowAreaControllerImpl windowAreaControllerImpl = this.this$0;
            final Consumer2 consumer2 = new Consumer2() { // from class: androidx.window.area.a
                @Override // androidx.window.reflection.Consumer2
                public final void accept(Object obj2) {
                    WindowAreaControllerImpl$windowAreaInfos$1.d(windowAreaControllerImpl, producerScope, ((Integer) obj2).intValue());
                }
            };
            final WindowAreaControllerImpl windowAreaControllerImpl2 = this.this$0;
            final Consumer2 consumer3 = new Consumer2() { // from class: androidx.window.area.b
                @Override // androidx.window.reflection.Consumer2
                public final void accept(Object obj2) {
                    WindowAreaControllerImpl$windowAreaInfos$1.b(windowAreaControllerImpl2, producerScope, (ExtensionWindowAreaStatus) obj2);
                }
            };
            this.this$0.windowAreaComponent.addRearDisplayStatusListener(consumer2);
            this.this$0.windowAreaComponent.addRearDisplayPresentationStatusListener(consumer3);
            final WindowAreaControllerImpl windowAreaControllerImpl3 = this.this$0;
            Function0 function0 = new Function0() { // from class: androidx.window.area.c
                public final Object invoke() {
                    return WindowAreaControllerImpl$windowAreaInfos$1.invokeSuspend$lambda$2(windowAreaControllerImpl3, consumer2, consumer3);
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
