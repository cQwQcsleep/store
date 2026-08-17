package androidx.compose.ui.viewinterop;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.relocation.BringIntoViewModifierNodeKt;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0002\u0018\u00002\u00020\u0001B5\u0012,\u0010\u0002\u001a(\u0012\u001a\u0012\u0018\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0003j\u0004\u0018\u0001`\u0006\u0012\u0004\u0012\u00020\u00050\u0003j\u0002`\u0007¢\u0006\u0004\b\b\u0010\tJ\b\u0010\u000f\u001a\u00020\u0005H\u0016J\b\u0010\u0010\u001a\u00020\u0005H\u0016J4\u0010\u0011\u001a\u00020\u00052,\u0010\u0002\u001a(\u0012\u001a\u0012\u0018\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0003j\u0004\u0018\u0001`\u0006\u0012\u0004\u0012\u00020\u00050\u0003j\u0002`\u0007R@\u0010\u0002\u001a(\u0012\u001a\u0012\u0018\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0003j\u0004\u0018\u0001`\u0006\u0012\u0004\u0012\u00020\u00050\u0003j\u0002`\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\tR#\u0010\r\u001a\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0004\u0012\u00020\u00050\u0003j\u0002`\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000b¨\u0006\u0012"}, d2 = {"Landroidx/compose/ui/viewinterop/BringIntoViewNode;", "Landroidx/compose/ui/Modifier$Node;", "onRequesterReady", "Lkotlin/Function1;", "Landroidx/compose/ui/geometry/Rect;", "", "Landroidx/compose/ui/viewinterop/BringIntoViewRequester;", "Landroidx/compose/ui/viewinterop/OnRequesterReady;", "<init>", "(Lkotlin/jvm/functions/Function1;)V", "getOnRequesterReady", "()Lkotlin/jvm/functions/Function1;", "setOnRequesterReady", "requester", "getRequester", "onAttach", "onDetach", "update", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
final class BringIntoViewNode extends Modifier.Node {
    private Function1<? super Function1<? super Rect, Unit>, Unit> onRequesterReady;
    private final Function1<Rect, Unit> requester = new Function1<Rect, Unit>() { // from class: androidx.compose.ui.viewinterop.BringIntoViewNode$requester$1

        /* JADX INFO: renamed from: androidx.compose.ui.viewinterop.BringIntoViewNode$requester$1$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
        @DebugMetadata(c = "androidx.compose.ui.viewinterop.BringIntoViewNode$requester$1$1", f = "AndroidViewHolder.android.kt", i = {}, l = {763}, m = "invokeSuspend", n = {}, s = {}, v = 1)
        public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ Rect $rect;
            int label;
            final /* synthetic */ BringIntoViewNode this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(BringIntoViewNode bringIntoViewNode, Rect rect, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.this$0 = bringIntoViewNode;
                this.$rect = rect;
            }

            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.this$0, this.$rect, continuation);
            }

            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    BringIntoViewNode bringIntoViewNode = this.this$0;
                    final Rect rect = this.$rect;
                    Function0<Rect> function0 = new Function0<Rect>() { // from class: androidx.compose.ui.viewinterop.BringIntoViewNode.requester.1.1.1
                        {
                            super(0);
                        }

                        /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
                        public final Rect m6279invoke() {
                            return rect;
                        }
                    };
                    this.label = 1;
                    if (BringIntoViewModifierNodeKt.bringIntoView(bringIntoViewNode, function0, this) == coroutine_suspended) {
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

        {
            super(1);
        }

        public final void invoke(Rect rect) {
            if (this.this$0.getIsAttached()) {
                BuildersKt.launch$default(this.this$0.getCoroutineScope(), (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1(this.this$0, rect, null), 3, (Object) null);
            }
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Rect) obj);
            return Unit.INSTANCE;
        }
    };

    public BringIntoViewNode(Function1<? super Function1<? super Rect, Unit>, Unit> function1) {
        this.onRequesterReady = function1;
    }

    public final Function1<Function1<? super Rect, Unit>, Unit> getOnRequesterReady() {
        return this.onRequesterReady;
    }

    public final Function1<Rect, Unit> getRequester() {
        return this.requester;
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onAttach() {
        this.onRequesterReady.invoke(this.requester);
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onDetach() {
        this.onRequesterReady.invoke((Object) null);
    }

    public final void setOnRequesterReady(Function1<? super Function1<? super Rect, Unit>, Unit> function1) {
        this.onRequesterReady = function1;
    }

    public final void update(Function1<? super Function1<? super Rect, Unit>, Unit> onRequesterReady) {
        this.onRequesterReady = onRequesterReady;
        if (getIsAttached()) {
            onRequesterReady.invoke(this.requester);
        }
    }
}
