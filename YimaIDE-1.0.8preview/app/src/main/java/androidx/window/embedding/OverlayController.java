package androidx.window.embedding;

import android.content.Context;
import android.os.Bundle;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.core.util.Consumer;
import defpackage.nab;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u0011\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001d\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nH\u0001¢\u0006\u0002\b\u000bJ\u001c\u0010\f\u001a\u00020\r2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000fH\u0007J\b\u0010\u0012\u001a\u00020\rH\u0007J\u0018\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0011H\u0007J\u0016\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u00182\u0006\u0010\u0014\u001a\u00020\u0015H\u0007R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Landroidx/window/embedding/OverlayController;", "", "backend", "Landroidx/window/embedding/EmbeddingBackend;", "<init>", "(Landroidx/window/embedding/EmbeddingBackend;)V", "setOverlayCreateParams", "Landroid/os/Bundle;", "options", "overlayCreateParams", "Landroidx/window/embedding/OverlayCreateParams;", "setOverlayCreateParams$window_release", "setOverlayAttributesCalculator", "", "calculator", "Lkotlin/Function1;", "Landroidx/window/embedding/OverlayAttributesCalculatorParams;", "Landroidx/window/embedding/OverlayAttributes;", "clearOverlayAttributesCalculator", "updateOverlayAttributes", "overlayTag", "", "overlayAttributes", "overlayInfo", "Lkotlinx/coroutines/flow/Flow;", "Landroidx/window/embedding/OverlayInfo;", "Companion", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class OverlayController {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int OVERLAY_FEATURE_VERSION = 8;
    private final EmbeddingBackend backend;

    /* JADX INFO: renamed from: androidx.window.embedding.OverlayController$overlayInfo$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/channels/ProducerScope;", "Landroidx/window/embedding/OverlayInfo;"}, k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    @DebugMetadata(c = "androidx.window.embedding.OverlayController$overlayInfo$1", f = "OverlayController.kt", i = {}, l = {136}, m = "invokeSuspend", n = {}, s = {})
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<ProducerScope<? super OverlayInfo>, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $overlayTag;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(String str, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$overlayTag = str;
        }

        public static void b(ProducerScope producerScope, OverlayInfo overlayInfo) {
            producerScope.trySend-JP2dKIU(overlayInfo);
        }

        public static Unit d(OverlayController overlayController, Consumer consumer) {
            overlayController.backend.removeOverlayInfoCallback(consumer);
            return Unit.INSTANCE;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = OverlayController.this.new AnonymousClass1(this.$overlayTag, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        public final Object invoke(ProducerScope<? super OverlayInfo> producerScope, Continuation<? super Unit> continuation) {
            return create(producerScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                final ProducerScope producerScope = (ProducerScope) this.L$0;
                final Consumer<OverlayInfo> consumer = new Consumer() { // from class: androidx.window.embedding.h
                    @Override // androidx.core.util.Consumer
                    public final void accept(Object obj2) {
                        OverlayController.AnonymousClass1.b(producerScope, (OverlayInfo) obj2);
                    }
                };
                OverlayController.this.backend.addOverlayInfoCallback(this.$overlayTag, new nab(), consumer);
                final OverlayController overlayController = OverlayController.this;
                Function0 function0 = new Function0() { // from class: androidx.window.embedding.i
                    public final Object invoke() {
                        return OverlayController.AnonymousClass1.d(overlayController, consumer);
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

    public OverlayController(EmbeddingBackend embeddingBackend) {
        embeddingBackend.getClass();
        this.backend = embeddingBackend;
    }

    @JvmStatic
    public static final OverlayController getInstance(Context context) {
        return INSTANCE.getInstance(context);
    }

    public final void clearOverlayAttributesCalculator() {
        this.backend.clearOverlayAttributesCalculator();
    }

    public final Flow<OverlayInfo> overlayInfo(String overlayTag) {
        overlayTag.getClass();
        return FlowKt.callbackFlow(new AnonymousClass1(overlayTag, null));
    }

    public final void setOverlayAttributesCalculator(Function1<? super OverlayAttributesCalculatorParams, OverlayAttributes> calculator) {
        calculator.getClass();
        this.backend.setOverlayAttributesCalculator(calculator);
    }

    public final Bundle setOverlayCreateParams$window_release(Bundle options, OverlayCreateParams overlayCreateParams) {
        options.getClass();
        overlayCreateParams.getClass();
        return this.backend.setOverlayCreateParams(options, overlayCreateParams);
    }

    public final void updateOverlayAttributes(String overlayTag, OverlayAttributes overlayAttributes) {
        overlayTag.getClass();
        overlayAttributes.getClass();
        this.backend.updateOverlayAttributes(overlayTag, overlayAttributes);
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Landroidx/window/embedding/OverlayController$Companion;", "", "<init>", "()V", "OVERLAY_FEATURE_VERSION", "", "getInstance", "Landroidx/window/embedding/OverlayController;", "context", "Landroid/content/Context;", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final OverlayController getInstance(Context context) {
            context.getClass();
            return new OverlayController(EmbeddingBackend.INSTANCE.getInstance(context));
        }

        private Companion() {
        }
    }
}
