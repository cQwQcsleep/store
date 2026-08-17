package androidx.compose.ui.input.nestedscroll;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.node.TraversableNode;
import androidx.compose.ui.node.TraversableNodeKt;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Velocity;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\b\b\u0001\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0019\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u001f\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\"2\u0006\u0010$\u001a\u00020%H\u0016¢\u0006\u0004\b&\u0010'J'\u0010(\u001a\u00020\"2\u0006\u0010)\u001a\u00020\"2\u0006\u0010#\u001a\u00020\"2\u0006\u0010$\u001a\u00020%H\u0016¢\u0006\u0004\b*\u0010+J\u0018\u0010,\u001a\u00020-2\u0006\u0010#\u001a\u00020-H\u0096@¢\u0006\u0004\b.\u0010/J \u00100\u001a\u00020-2\u0006\u0010)\u001a\u00020-2\u0006\u0010#\u001a\u00020-H\u0096@¢\u0006\u0004\b1\u00102J\u0012\u00103\u001a\u0002042\b\u00105\u001a\u0004\u0018\u00010\u0006H\u0002J\b\u00106\u001a\u000204H\u0016J\b\u00107\u001a\u000204H\u0016J\b\u00108\u001a\u000204H\u0002J\b\u00109\u001a\u000204H\u0002J\u001f\u0010:\u001a\u0002042\u0006\u0010\u0004\u001a\u00020\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0000¢\u0006\u0002\b;R\u001a\u0010\u0004\u001a\u00020\u0002X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u0000X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0013\u001a\u0004\u0018\u00010\u00008@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0010R\u0016\u0010\u0015\u001a\u0004\u0018\u00010\u00028BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\nR\u0014\u0010\u0017\u001a\u00020\u0018X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u00020\u001c8BX\u0082\u0004¢\u0006\f\u0012\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 ¨\u0006<"}, d2 = {"Landroidx/compose/ui/input/nestedscroll/NestedScrollNode;", "Landroidx/compose/ui/node/TraversableNode;", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "Landroidx/compose/ui/Modifier$Node;", "connection", "dispatcher", "Landroidx/compose/ui/input/nestedscroll/NestedScrollDispatcher;", "<init>", "(Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;Landroidx/compose/ui/input/nestedscroll/NestedScrollDispatcher;)V", "getConnection", "()Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "setConnection", "(Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;)V", "resolvedDispatcher", "lastKnownParentNode", "getLastKnownParentNode$ui", "()Landroidx/compose/ui/input/nestedscroll/NestedScrollNode;", "setLastKnownParentNode$ui", "(Landroidx/compose/ui/input/nestedscroll/NestedScrollNode;)V", "parentNestedScrollNode", "getParentNestedScrollNode$ui", "parentConnection", "getParentConnection", "traverseKey", "", "getTraverseKey", "()Ljava/lang/Object;", "nestedCoroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "getNestedCoroutineScope$annotations", "()V", "getNestedCoroutineScope", "()Lkotlinx/coroutines/CoroutineScope;", "onPreScroll", "Landroidx/compose/ui/geometry/Offset;", "available", "source", "Landroidx/compose/ui/input/nestedscroll/NestedScrollSource;", "onPreScroll-OzD1aCk", "(JI)J", "onPostScroll", "consumed", "onPostScroll-DzOQY0M", "(JJI)J", "onPreFling", "Landroidx/compose/ui/unit/Velocity;", "onPreFling-QWom1Mo", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onPostFling", "onPostFling-RZ2iAVY", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateDispatcher", "", "newDispatcher", "onAttach", "onDetach", "updateDispatcherFields", "resetDispatcherFields", "updateNode", "updateNode$ui", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class NestedScrollNode extends Modifier.Node implements TraversableNode, NestedScrollConnection {
    public static final int $stable = 8;
    private NestedScrollConnection connection;
    private NestedScrollNode lastKnownParentNode;
    private NestedScrollDispatcher resolvedDispatcher;
    private final Object traverseKey;

    public NestedScrollNode(NestedScrollConnection nestedScrollConnection, NestedScrollDispatcher nestedScrollDispatcher) {
        this.connection = nestedScrollConnection;
        this.resolvedDispatcher = nestedScrollDispatcher == null ? new NestedScrollDispatcher() : nestedScrollDispatcher;
        this.traverseKey = "androidx.compose.ui.input.nestedscroll.NestedScrollNode";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final CoroutineScope getNestedCoroutineScope() {
        NestedScrollNode parentNestedScrollNode$ui = getParentNestedScrollNode$ui();
        CoroutineScope nestedCoroutineScope = parentNestedScrollNode$ui != null ? parentNestedScrollNode$ui.getNestedCoroutineScope() : null;
        if (nestedCoroutineScope != null && CoroutineScopeKt.isActive(nestedCoroutineScope)) {
            return nestedCoroutineScope;
        }
        CoroutineScope scope$ui = this.resolvedDispatcher.getScope();
        if (scope$ui != null) {
            return scope$ui;
        }
        k2d.a("in order to access nested coroutine scope you need to attach dispatcher to the `Modifier.nestedScroll` first.");
        return null;
    }

    private static /* synthetic */ void getNestedCoroutineScope$annotations() {
    }

    private final NestedScrollConnection getParentConnection() {
        if (getIsAttached()) {
            return getParentNestedScrollNode$ui();
        }
        return null;
    }

    private final void resetDispatcherFields() {
        if (this.resolvedDispatcher.getNestedScrollNode() == this) {
            this.resolvedDispatcher.setNestedScrollNode$ui(null);
        }
    }

    private final void updateDispatcher(NestedScrollDispatcher newDispatcher) {
        resetDispatcherFields();
        if (newDispatcher == null) {
            this.resolvedDispatcher = new NestedScrollDispatcher();
        } else if (!Intrinsics.areEqual(newDispatcher, this.resolvedDispatcher)) {
            this.resolvedDispatcher = newDispatcher;
        }
        if (getIsAttached()) {
            updateDispatcherFields();
        }
    }

    private final void updateDispatcherFields() {
        this.resolvedDispatcher.setNestedScrollNode$ui(this);
        this.resolvedDispatcher.setLastKnownParentNode$ui(null);
        this.lastKnownParentNode = null;
        this.resolvedDispatcher.setCalculateNestedScrollScope$ui(new Function0<CoroutineScope>() { // from class: androidx.compose.ui.input.nestedscroll.NestedScrollNode.updateDispatcherFields.1
            {
                super(0);
            }

            public final CoroutineScope invoke() {
                return NestedScrollNode.this.getNestedCoroutineScope();
            }
        });
        this.resolvedDispatcher.setScope$ui(getCoroutineScope());
    }

    public final NestedScrollConnection getConnection() {
        return this.connection;
    }

    /* JADX INFO: renamed from: getLastKnownParentNode$ui, reason: from getter */
    public final NestedScrollNode getLastKnownParentNode() {
        return this.lastKnownParentNode;
    }

    public final NestedScrollNode getParentNestedScrollNode$ui() {
        if (getIsAttached()) {
            return (NestedScrollNode) TraversableNodeKt.findNearestAncestor(this);
        }
        return null;
    }

    @Override // androidx.compose.ui.node.TraversableNode
    public Object getTraverseKey() {
        return this.traverseKey;
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onAttach() {
        updateDispatcherFields();
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onDetach() {
        NestedScrollNode nestedScrollNode = (NestedScrollNode) NestedScrollNodeKt.findNearestAttachedAncestor(this);
        this.lastKnownParentNode = nestedScrollNode;
        this.resolvedDispatcher.setLastKnownParentNode$ui(nestedScrollNode);
        resetDispatcherFields();
    }

    /* JADX WARN: Code duplicated, block: B:8:0x0016  */
    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPostFling-RZ2iAVY */
    public Object mo428onPostFlingRZ2iAVY(long j, long j2, Continuation<? super Velocity> continuation) {
        NestedScrollNode$onPostFling$1 nestedScrollNode$onPostFling$1;
        long j3;
        long packedValue;
        long jM6271getZero9UxMQ8M;
        long j4;
        if (continuation instanceof NestedScrollNode$onPostFling$1) {
            nestedScrollNode$onPostFling$1 = (NestedScrollNode$onPostFling$1) continuation;
            int i = nestedScrollNode$onPostFling$1.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                nestedScrollNode$onPostFling$1.label = i - Integer.MIN_VALUE;
            } else {
                nestedScrollNode$onPostFling$1 = new NestedScrollNode$onPostFling$1(this, continuation);
            }
        } else {
            nestedScrollNode$onPostFling$1 = new NestedScrollNode$onPostFling$1(this, continuation);
        }
        NestedScrollNode$onPostFling$1 nestedScrollNode$onPostFling$2 = nestedScrollNode$onPostFling$1;
        Object objMo428onPostFlingRZ2iAVY = nestedScrollNode$onPostFling$2.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = nestedScrollNode$onPostFling$2.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(objMo428onPostFlingRZ2iAVY);
            NestedScrollConnection nestedScrollConnection = this.connection;
            nestedScrollNode$onPostFling$2.J$0 = j;
            nestedScrollNode$onPostFling$2.J$1 = j2;
            nestedScrollNode$onPostFling$2.label = 1;
            objMo428onPostFlingRZ2iAVY = nestedScrollConnection.mo428onPostFlingRZ2iAVY(j, j2, nestedScrollNode$onPostFling$2);
            if (objMo428onPostFlingRZ2iAVY != coroutine_suspended) {
                j3 = j2;
            }
            return coroutine_suspended;
        }
        if (i2 == 1) {
            long j5 = nestedScrollNode$onPostFling$2.J$1;
            long j6 = nestedScrollNode$onPostFling$2.J$0;
            ResultKt.throwOnFailure(objMo428onPostFlingRZ2iAVY);
            j3 = j5;
            j = j6;
        } else {
            if (i2 != 2) {
                k2d.a("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            j4 = nestedScrollNode$onPostFling$2.J$0;
            ResultKt.throwOnFailure(objMo428onPostFlingRZ2iAVY);
        }
        jM6271getZero9UxMQ8M = ((Velocity) objMo428onPostFlingRZ2iAVY).getPackedValue();
        packedValue = j4;
        return Velocity.m6251boximpl(Velocity.m6264plusAH228Gc(packedValue, jM6271getZero9UxMQ8M));
        packedValue = ((Velocity) objMo428onPostFlingRZ2iAVY).getPackedValue();
        NestedScrollConnection parentConnection = getIsAttached() ? getParentConnection() : this.lastKnownParentNode;
        if (parentConnection != null) {
            long jM6264plusAH228Gc = Velocity.m6264plusAH228Gc(j, packedValue);
            long jM6263minusAH228Gc = Velocity.m6263minusAH228Gc(j3, packedValue);
            nestedScrollNode$onPostFling$2.J$0 = packedValue;
            nestedScrollNode$onPostFling$2.label = 2;
            objMo428onPostFlingRZ2iAVY = parentConnection.mo428onPostFlingRZ2iAVY(jM6264plusAH228Gc, jM6263minusAH228Gc, nestedScrollNode$onPostFling$2);
            if (objMo428onPostFlingRZ2iAVY != coroutine_suspended) {
                j4 = packedValue;
                jM6271getZero9UxMQ8M = ((Velocity) objMo428onPostFlingRZ2iAVY).getPackedValue();
                packedValue = j4;
            }
            return coroutine_suspended;
        }
        jM6271getZero9UxMQ8M = Velocity.INSTANCE.m6271getZero9UxMQ8M();
        return Velocity.m6251boximpl(Velocity.m6264plusAH228Gc(packedValue, jM6271getZero9UxMQ8M));
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPostScroll-DzOQY0M */
    public long mo429onPostScrollDzOQY0M(long consumed, long available, int source) {
        long jMo429onPostScrollDzOQY0M = this.connection.mo429onPostScrollDzOQY0M(consumed, available, source);
        NestedScrollConnection parentConnection = getParentConnection();
        return Offset.m2894plusMKHz9U(jMo429onPostScrollDzOQY0M, parentConnection != null ? parentConnection.mo429onPostScrollDzOQY0M(Offset.m2894plusMKHz9U(consumed, jMo429onPostScrollDzOQY0M), Offset.m2893minusMKHz9U(available, jMo429onPostScrollDzOQY0M), source) : Offset.INSTANCE.m2905getZeroF1C5BW0());
    }

    /* JADX WARN: Code duplicated, block: B:26:0x006d  */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x004c, code lost:
    
        if (r9 == r1) goto L25;
     */
    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPreFling-QWom1Mo */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object mo863onPreFlingQWom1Mo(long j, Continuation<? super Velocity> continuation) {
        NestedScrollNode$onPreFling$1 nestedScrollNode$onPreFling$1;
        long jM6271getZero9UxMQ8M;
        long j2;
        if (continuation instanceof NestedScrollNode$onPreFling$1) {
            nestedScrollNode$onPreFling$1 = (NestedScrollNode$onPreFling$1) continuation;
            int i = nestedScrollNode$onPreFling$1.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                nestedScrollNode$onPreFling$1.label = i - Integer.MIN_VALUE;
            } else {
                nestedScrollNode$onPreFling$1 = new NestedScrollNode$onPreFling$1(this, continuation);
            }
        } else {
            nestedScrollNode$onPreFling$1 = new NestedScrollNode$onPreFling$1(this, continuation);
        }
        Object objMo863onPreFlingQWom1Mo = nestedScrollNode$onPreFling$1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = nestedScrollNode$onPreFling$1.label;
        if (i2 != 0) {
            if (i2 == 1) {
                j = nestedScrollNode$onPreFling$1.J$0;
                ResultKt.throwOnFailure(objMo863onPreFlingQWom1Mo);
            } else {
                if (i2 != 2) {
                    k2d.a("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                j2 = nestedScrollNode$onPreFling$1.J$0;
                ResultKt.throwOnFailure(objMo863onPreFlingQWom1Mo);
            }
            return Velocity.m6251boximpl(Velocity.m6264plusAH228Gc(j2, ((Velocity) objMo863onPreFlingQWom1Mo).getPackedValue()));
        }
        ResultKt.throwOnFailure(objMo863onPreFlingQWom1Mo);
        NestedScrollConnection parentConnection = getParentConnection();
        if (parentConnection != null) {
            nestedScrollNode$onPreFling$1.J$0 = j;
            nestedScrollNode$onPreFling$1.label = 1;
            objMo863onPreFlingQWom1Mo = parentConnection.mo863onPreFlingQWom1Mo(j, nestedScrollNode$onPreFling$1);
        } else {
            jM6271getZero9UxMQ8M = Velocity.INSTANCE.m6271getZero9UxMQ8M();
            NestedScrollConnection nestedScrollConnection = this.connection;
            long jM6263minusAH228Gc = Velocity.m6263minusAH228Gc(j, jM6271getZero9UxMQ8M);
            nestedScrollNode$onPreFling$1.J$0 = jM6271getZero9UxMQ8M;
            nestedScrollNode$onPreFling$1.label = 2;
            objMo863onPreFlingQWom1Mo = nestedScrollConnection.mo863onPreFlingQWom1Mo(jM6263minusAH228Gc, nestedScrollNode$onPreFling$1);
            if (objMo863onPreFlingQWom1Mo != coroutine_suspended) {
                j2 = jM6271getZero9UxMQ8M;
                return Velocity.m6251boximpl(Velocity.m6264plusAH228Gc(j2, ((Velocity) objMo863onPreFlingQWom1Mo).getPackedValue()));
            }
        }
        return coroutine_suspended;
        jM6271getZero9UxMQ8M = ((Velocity) objMo863onPreFlingQWom1Mo).getPackedValue();
        NestedScrollConnection nestedScrollConnection2 = this.connection;
        long jM6263minusAH228Gc2 = Velocity.m6263minusAH228Gc(j, jM6271getZero9UxMQ8M);
        nestedScrollNode$onPreFling$1.J$0 = jM6271getZero9UxMQ8M;
        nestedScrollNode$onPreFling$1.label = 2;
        objMo863onPreFlingQWom1Mo = nestedScrollConnection2.mo863onPreFlingQWom1Mo(jM6263minusAH228Gc2, nestedScrollNode$onPreFling$1);
        if (objMo863onPreFlingQWom1Mo != coroutine_suspended) {
            j2 = jM6271getZero9UxMQ8M;
            return Velocity.m6251boximpl(Velocity.m6264plusAH228Gc(j2, ((Velocity) objMo863onPreFlingQWom1Mo).getPackedValue()));
        }
        return coroutine_suspended;
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPreScroll-OzD1aCk */
    public long mo430onPreScrollOzD1aCk(long available, int source) {
        NestedScrollConnection parentConnection = getParentConnection();
        long jMo430onPreScrollOzD1aCk = parentConnection != null ? parentConnection.mo430onPreScrollOzD1aCk(available, source) : Offset.INSTANCE.m2905getZeroF1C5BW0();
        return Offset.m2894plusMKHz9U(jMo430onPreScrollOzD1aCk, this.connection.mo430onPreScrollOzD1aCk(Offset.m2893minusMKHz9U(available, jMo430onPreScrollOzD1aCk), source));
    }

    public final void setConnection(NestedScrollConnection nestedScrollConnection) {
        this.connection = nestedScrollConnection;
    }

    public final void setLastKnownParentNode$ui(NestedScrollNode nestedScrollNode) {
        this.lastKnownParentNode = nestedScrollNode;
    }

    public final void updateNode$ui(NestedScrollConnection connection, NestedScrollDispatcher dispatcher) {
        this.connection = connection;
        updateDispatcher(dispatcher);
    }
}
