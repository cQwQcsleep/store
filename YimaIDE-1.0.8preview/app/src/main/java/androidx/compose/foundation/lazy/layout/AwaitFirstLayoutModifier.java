package androidx.compose.foundation.lazy.layout;

import androidx.compose.foundation.lazy.layout.AwaitFirstLayoutModifier;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.OnLayoutRectChangedModifierKt;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.spatial.RelativeLayoutBounds;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.Job;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0001\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001\u0016B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u000e\u0010\t\u001a\u00020\bH\u0086@¢\u0006\u0002\u0010\nJ\f\u0010\u000b\u001a\u00060\u0002R\u00020\u0000H\u0016J\u0014\u0010\f\u001a\u00020\b2\n\u0010\r\u001a\u00060\u0002R\u00020\u0000H\u0016J\f\u0010\u000e\u001a\u00020\b*\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0096\u0002R\u0014\u0010\u0005\u001a\b\u0018\u00010\u0002R\u00020\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Landroidx/compose/foundation/lazy/layout/AwaitFirstLayoutModifier;", "Landroidx/compose/ui/node/ModifierNodeElement;", "Landroidx/compose/foundation/lazy/layout/AwaitFirstLayoutModifier$Node;", "<init>", "()V", "attachedNode", "lock", "Lkotlinx/coroutines/CompletableDeferred;", "", "waitForFirstLayout", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "create", "update", "node", "inspectableProperties", "Landroidx/compose/ui/platform/InspectorInfo;", "hashCode", "", "equals", "", "other", "", "Node", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class AwaitFirstLayoutModifier extends ModifierNodeElement<Node> {
    public static final int $stable = 0;
    private Node attachedNode;
    private CompletableDeferred<Unit> lock;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\u0006\u0010\b\u001a\u00020\u0005J\b\u0010\t\u001a\u00020\u0005H\u0016R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Landroidx/compose/foundation/lazy/layout/AwaitFirstLayoutModifier$Node;", "Landroidx/compose/ui/Modifier$Node;", "<init>", "(Landroidx/compose/foundation/lazy/layout/AwaitFirstLayoutModifier;)V", "onAttach", "", "handle", "Landroidx/compose/ui/node/DelegatableNode$RegistrationHandle;", "requestOnAfterLayoutCallback", "onDetach", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public final class Node extends Modifier.Node {
        private DelegatableNode.RegistrationHandle handle;

        public Node() {
        }

        public static Unit a(Node node, AwaitFirstLayoutModifier awaitFirstLayoutModifier, RelativeLayoutBounds relativeLayoutBounds) {
            DelegatableNode.RegistrationHandle registrationHandle = node.handle;
            if (registrationHandle != null) {
                registrationHandle.unregister();
            }
            node.handle = null;
            CompletableDeferred completableDeferred = awaitFirstLayoutModifier.lock;
            if (completableDeferred != null) {
                completableDeferred.complete(Unit.INSTANCE);
            }
            awaitFirstLayoutModifier.lock = null;
            return Unit.INSTANCE;
        }

        public void onAttach() {
            AwaitFirstLayoutModifier.this.attachedNode = this;
            if (AwaitFirstLayoutModifier.this.lock != null) {
                requestOnAfterLayoutCallback();
            }
        }

        public void onDetach() {
            if (AwaitFirstLayoutModifier.this.attachedNode == this) {
                AwaitFirstLayoutModifier.this.attachedNode = null;
            }
            DelegatableNode.RegistrationHandle registrationHandle = this.handle;
            if (registrationHandle != null) {
                registrationHandle.unregister();
            }
            this.handle = null;
        }

        public final void requestOnAfterLayoutCallback() {
            final AwaitFirstLayoutModifier awaitFirstLayoutModifier = AwaitFirstLayoutModifier.this;
            this.handle = OnLayoutRectChangedModifierKt.registerOnLayoutRectChanged(this, 0L, 0L, new Function1() { // from class: sj0
                public final Object invoke(Object obj) {
                    return AwaitFirstLayoutModifier.Node.a(this.b, awaitFirstLayoutModifier, (RelativeLayoutBounds) obj);
                }
            });
        }
    }

    /* JADX INFO: renamed from: create, reason: merged with bridge method [inline-methods] */
    public Node m1098create() {
        return new Node();
    }

    public boolean equals(Object other) {
        return other == this;
    }

    public int hashCode() {
        return 234;
    }

    public void inspectableProperties(InspectorInfo inspectorInfo) {
        inspectorInfo.setName("AwaitFirstLayoutModifier");
    }

    public final Object waitForFirstLayout(Continuation<? super Unit> continuation) {
        CompletableDeferred<Unit> completableDeferredCompletableDeferred$default = this.lock;
        if (completableDeferredCompletableDeferred$default == null) {
            completableDeferredCompletableDeferred$default = CompletableDeferredKt.CompletableDeferred$default((Job) null, 1, (Object) null);
            this.lock = completableDeferredCompletableDeferred$default;
            Node node = this.attachedNode;
            if (node != null && node.isAttached()) {
                node.requestOnAfterLayoutCallback();
            }
        }
        Object objAwait = completableDeferredCompletableDeferred$default.await(continuation);
        return objAwait == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAwait : Unit.INSTANCE;
    }

    public void update(Node node) {
    }
}
