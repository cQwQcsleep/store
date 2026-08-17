package androidx.compose.foundation.gestures;

import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerEventPass;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputScope;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.JobKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u00004\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a;\u0010\u0000\u001a\u00020\u0001*\u00020\u00022'\u0010\u0003\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0004¢\u0006\u0002\b\u0007H\u0087@¢\u0006\u0002\u0010\b\u001a\f\u0010\t\u001a\u00020\n*\u00020\u000bH\u0000\u001a\u0012\u0010\f\u001a\u00020\u0001*\u00020\u0002H\u0080@¢\u0006\u0002\u0010\r\u001a\u001c\u0010\f\u001a\u00020\u0001*\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\u000fH\u0080@¢\u0006\u0002\u0010\u0010\u001a;\u0010\u0011\u001a\u00020\u0001*\u00020\u00022'\u0010\u0003\u001a#\b\u0001\u0012\u0004\u0012\u00020\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0004¢\u0006\u0002\b\u0007H\u0086@¢\u0006\u0002\u0010\b¨\u0006\u0012"}, d2 = {"forEachGesture", "", "Landroidx/compose/ui/input/pointer/PointerInputScope;", "block", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/ui/input/pointer/PointerInputScope;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "allPointersUp", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "awaitAllPointersUp", "(Landroidx/compose/ui/input/pointer/PointerInputScope;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "pass", "Landroidx/compose/ui/input/pointer/PointerEventPass;", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;Landroidx/compose/ui/input/pointer/PointerEventPass;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitEachGesture", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class ForEachGestureKt {

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.ForEachGestureKt$awaitAllPointersUp$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.ForEachGestureKt$awaitAllPointersUp$2", f = "ForEachGesture.kt", i = {}, l = {75}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    public static final class AnonymousClass2 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        public AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
            return create(awaitPointerEventScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                AwaitPointerEventScope awaitPointerEventScope = (AwaitPointerEventScope) this.L$0;
                this.label = 1;
                if (ForEachGestureKt.awaitAllPointersUp$default(awaitPointerEventScope, null, this, 1, null) == coroutine_suspended) {
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

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.ForEachGestureKt$awaitAllPointersUp$3, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.ForEachGestureKt", f = "ForEachGesture.kt", i = {0, 0}, l = {84}, m = "awaitAllPointersUp", n = {"$this$awaitAllPointersUp", "pass"}, s = {"L$0", "L$1"}, v = 1)
    public static final class AnonymousClass3 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass3(Continuation<? super AnonymousClass3> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ForEachGestureKt.awaitAllPointersUp(null, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.ForEachGestureKt$awaitEachGesture$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.ForEachGestureKt$awaitEachGesture$2", f = "ForEachGesture.kt", i = {0, 1, 2}, l = {102, 105, 110}, m = "invokeSuspend", n = {"$this$awaitPointerEventScope", "$this$awaitPointerEventScope", "$this$awaitPointerEventScope"}, s = {"L$0", "L$0", "L$0"}, v = 1)
    public static final class C01112 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> $block;
        final /* synthetic */ CoroutineContext $currentContext;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C01112(CoroutineContext coroutineContext, Function2<? super AwaitPointerEventScope, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super C01112> continuation) {
            super(2, continuation);
            this.$currentContext = coroutineContext;
            this.$block = function2;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C01112 c01112 = new C01112(this.$currentContext, this.$block, continuation);
            c01112.L$0 = obj;
            return c01112;
        }

        public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
            return create(awaitPointerEventScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Can't wrap try/catch for region: R(4:38|21|(2:24|25)|34) */
        /* JADX WARN: Code duplicated, block: B:24:0x0050  */
        /* JADX WARN: Code duplicated, block: B:32:0x0068  */
        /* JADX WARN: Code duplicated, block: B:35:0x0073  */
        /* JADX WARN: Code duplicated, block: B:36:0x0074  */
        /* JADX WARN: Code duplicated, block: B:38:0x0043 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Code restructure failed: missing block: B:26:0x0059, code lost:
        
            if (r9 == r0) goto L34;
         */
        /* JADX WARN: Code restructure failed: missing block: B:28:0x005c, code lost:
        
            r1 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:29:0x005d, code lost:
        
            r1 = r9;
            r9 = r1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:33:0x0070, code lost:
        
            if (androidx.compose.foundation.gestures.ForEachGestureKt.awaitAllPointersUp$default(r1, null, r8, 1, null) == r0) goto L34;
         */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r1v0, types: [int] */
        /* JADX WARN: Type inference failed for: r1v1 */
        /* JADX WARN: Type inference failed for: r1v10 */
        /* JADX WARN: Type inference failed for: r1v11 */
        /* JADX WARN: Type inference failed for: r1v18 */
        /* JADX WARN: Type inference failed for: r1v19 */
        /* JADX WARN: Type inference failed for: r1v2, types: [androidx.compose.ui.input.pointer.AwaitPointerEventScope, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r1v20 */
        /* JADX WARN: Type inference failed for: r1v21 */
        /* JADX WARN: Type inference failed for: r1v22 */
        /* JADX WARN: Type inference failed for: r1v3, types: [androidx.compose.ui.input.pointer.AwaitPointerEventScope, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r1v7 */
        /* JADX WARN: Type inference failed for: r9v10 */
        /* JADX WARN: Type inference failed for: r9v5, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r9v7 */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x0059 -> B:12:0x0028). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:33:0x0070 -> B:12:0x0028). Please report as a decompilation issue!!! */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            ?? r9;
            Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> function2;
            ?? r1;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            ?? r2 = this.label;
            try {
                if (r2 == 0) {
                    ResultKt.throwOnFailure(obj);
                    r9 = (AwaitPointerEventScope) this.L$0;
                    if (JobKt.isActive(this.$currentContext)) {
                        return Unit.INSTANCE;
                    }
                    function2 = this.$block;
                    this.L$0 = r9;
                    this.label = 1;
                    if (function2.invoke((Object) r9, this) != coroutine_suspended) {
                        r2 = r9;
                        this.L$0 = r2;
                        this.label = 2;
                        Object objAwaitAllPointersUp$default = ForEachGestureKt.awaitAllPointersUp$default(r2, null, this, 1, null);
                        r1 = r2;
                    }
                    r1 = r2;
                    return coroutine_suspended;
                }
                if (r2 == 1) {
                    AwaitPointerEventScope awaitPointerEventScope = (AwaitPointerEventScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    r2 = awaitPointerEventScope;
                    this.L$0 = r2;
                    this.label = 2;
                    Object objAwaitAllPointersUp$default2 = ForEachGestureKt.awaitAllPointersUp$default(r2, null, this, 1, null);
                    r1 = r2;
                } else if (r2 == 2) {
                    AwaitPointerEventScope awaitPointerEventScope2 = (AwaitPointerEventScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    r1 = awaitPointerEventScope2;
                } else {
                    if (r2 != 3) {
                        k2d.a("call to 'resume' before 'invoke' with coroutine");
                        return null;
                    }
                    AwaitPointerEventScope awaitPointerEventScope3 = (AwaitPointerEventScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    r1 = awaitPointerEventScope3;
                }
            } catch (CancellationException e) {
                e = e;
                if (!JobKt.isActive(this.$currentContext)) {
                    throw e;
                }
                this.L$0 = r2;
                this.label = 3;
            }
            r1 = r2;
            r9 = r1;
            if (JobKt.isActive(this.$currentContext)) {
                return Unit.INSTANCE;
            }
            function2 = this.$block;
            this.L$0 = r9;
            this.label = 1;
            if (function2.invoke((Object) r9, this) != coroutine_suspended) {
                r2 = r9;
                this.L$0 = r2;
                this.label = 2;
                Object objAwaitAllPointersUp$default3 = ForEachGestureKt.awaitAllPointersUp$default(r2, null, this, 1, null);
                r1 = r2;
            }
            r1 = r2;
            return coroutine_suspended;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.ForEachGestureKt$forEachGesture$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.ForEachGestureKt", f = "ForEachGesture.kt", i = {0, 0, 0, 1, 1, 1, 2, 2, 2}, l = {48, 51, 56}, m = "forEachGesture", n = {"$this$forEachGesture", "block", "currentContext", "$this$forEachGesture", "block", "currentContext", "$this$forEachGesture", "block", "currentContext"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2"}, v = 1)
    public static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ForEachGestureKt.forEachGesture(null, null, this);
        }
    }

    public static final boolean allPointersUp(AwaitPointerEventScope awaitPointerEventScope) {
        List changes = awaitPointerEventScope.getCurrentEvent().getChanges();
        int size = changes.size();
        boolean z = false;
        for (int i = 0; i < size; i++) {
            if (((PointerInputChange) changes.get(i)).getPressed()) {
                z = true;
                break;
            }
        }
        return !z;
    }

    /* JADX WARN: Code duplicated, block: B:21:0x0061  */
    /* JADX WARN: Code duplicated, block: B:24:0x006e A[LOOP:0: B:20:0x005f->B:24:0x006e, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:27:0x0071 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:28:0x0044 A[EDGE_INSN: B:28:0x0044->B:16:0x0044 BREAK  A[LOOP:0: B:20:0x005f->B:24:0x006e], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x004e -> B:19:0x0051). Please report as a decompilation issue!!! */
    /*  JADX ERROR: StackOverflowError in pass: RegionMakerVisitor
        java.lang.StackOverflowError
        	at jadx.core.utils.BlockUtils.traverseSuccessorsUntil(BlockUtils.java:731)
        	at jadx.core.utils.BlockUtils.traverseSuccessorsUntil(BlockUtils.java:749)
        */
    public static final java.lang.Object awaitAllPointersUp(androidx.compose.ui.input.pointer.AwaitPointerEventScope r7, androidx.compose.ui.input.pointer.PointerEventPass r8, kotlin.coroutines.Continuation<? super kotlin.Unit> r9) {
        /*
            boolean r0 = r9 instanceof androidx.compose.foundation.gestures.ForEachGestureKt.AnonymousClass3
            if (r0 == 0) goto L13
            r0 = r9
            androidx.compose.foundation.gestures.ForEachGestureKt$awaitAllPointersUp$3 r0 = (androidx.compose.foundation.gestures.ForEachGestureKt.AnonymousClass3) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.foundation.gestures.ForEachGestureKt$awaitAllPointersUp$3 r0 = new androidx.compose.foundation.gestures.ForEachGestureKt$awaitAllPointersUp$3
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3b
            if (r2 != r3) goto L34
            java.lang.Object r7 = r0.L$1
            androidx.compose.ui.input.pointer.PointerEventPass r7 = (androidx.compose.ui.input.pointer.PointerEventPass) r7
            java.lang.Object r8 = r0.L$0
            androidx.compose.ui.input.pointer.AwaitPointerEventScope r8 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r8
            kotlin.ResultKt.throwOnFailure(r9)
            r6 = r8
            r8 = r7
            r7 = r6
            goto L51
        L34:
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            k2d.a(r7)
            r7 = 0
            return r7
        L3b:
            kotlin.ResultKt.throwOnFailure(r9)
            boolean r9 = allPointersUp(r7)
            if (r9 != 0) goto L71
        L44:
            r0.L$0 = r7
            r0.L$1 = r8
            r0.label = r3
            java.lang.Object r9 = r7.awaitPointerEvent(r8, r0)
            if (r9 != r1) goto L51
            return r1
        L51:
            androidx.compose.ui.input.pointer.PointerEvent r9 = (androidx.compose.ui.input.pointer.PointerEvent) r9
            java.util.List r9 = r9.getChanges()
            r2 = r9
            java.util.Collection r2 = (java.util.Collection) r2
            int r2 = r2.size()
            r4 = 0
        L5f:
            if (r4 >= r2) goto L71
            java.lang.Object r5 = r9.get(r4)
            androidx.compose.ui.input.pointer.PointerInputChange r5 = (androidx.compose.ui.input.pointer.PointerInputChange) r5
            boolean r5 = r5.getPressed()
            if (r5 == 0) goto L6e
            goto L44
        L6e:
            int r4 = r4 + 1
            goto L5f
        L71:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.ForEachGestureKt.awaitAllPointersUp(androidx.compose.ui.input.pointer.AwaitPointerEventScope, androidx.compose.ui.input.pointer.PointerEventPass, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object awaitAllPointersUp$default(AwaitPointerEventScope awaitPointerEventScope, PointerEventPass pointerEventPass, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            pointerEventPass = PointerEventPass.Final;
        }
        return awaitAllPointersUp(awaitPointerEventScope, pointerEventPass, continuation);
    }

    public static final Object awaitEachGesture(PointerInputScope pointerInputScope, Function2<? super AwaitPointerEventScope, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super Unit> continuation) {
        Object objAwaitPointerEventScope = pointerInputScope.awaitPointerEventScope(new C01112(continuation.getContext(), function2, null), continuation);
        return objAwaitPointerEventScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAwaitPointerEventScope : Unit.INSTANCE;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(4:44|27|(2:30|31)|40) */
    /* JADX WARN: Code duplicated, block: B:30:0x0082  */
    /* JADX WARN: Code duplicated, block: B:38:0x009e  */
    /* JADX WARN: Code duplicated, block: B:41:0x00ad  */
    /* JADX WARN: Code duplicated, block: B:42:0x00ae  */
    /* JADX WARN: Code duplicated, block: B:44:0x0073 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0090, code lost:
    
        if (r10 == r1) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0093, code lost:
    
        r2 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0094, code lost:
    
        r2 = r8;
        r8 = r10;
        r10 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00aa, code lost:
    
        if (awaitAllPointersUp(r2, r0) == r1) goto L40;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [int] */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v16 */
    /* JADX WARN: Type inference failed for: r2v17 */
    /* JADX WARN: Type inference failed for: r2v18 */
    /* JADX WARN: Type inference failed for: r2v19 */
    /* JADX WARN: Type inference failed for: r2v2, types: [androidx.compose.ui.input.pointer.PointerInputScope, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v20 */
    /* JADX WARN: Type inference failed for: r2v3, types: [androidx.compose.ui.input.pointer.PointerInputScope, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v8 */
    /* JADX WARN: Type inference failed for: r2v9 */
    /* JADX WARN: Type inference failed for: r8v22 */
    /* JADX WARN: Type inference failed for: r8v4, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v9 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:32:0x0090 -> B:18:0x0051). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:39:0x00aa -> B:18:0x0051). Please report as a decompilation issue!!! */
    @Deprecated(message = "Use awaitEachGesture instead. forEachGesture() can drop events between gestures.", replaceWith = @ReplaceWith(expression = "awaitEachGesture(block)", imports = {}))
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final Object forEachGesture(PointerInputScope pointerInputScope, Function2<? super PointerInputScope, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super Unit> continuation) {
        AnonymousClass1 anonymousClass1;
        PointerInputScope context;
        ?? r8;
        PointerInputScope pointerInputScope2;
        ?? r2;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            int i = anonymousClass1.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label = i - Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = new AnonymousClass1(continuation);
        }
        Object obj = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ?? r3 = anonymousClass1.label;
        try {
            if (r3 == 0) {
                ResultKt.throwOnFailure(obj);
                r8 = pointerInputScope;
                context = anonymousClass1.getContext();
                if (JobKt.isActive(context)) {
                    return Unit.INSTANCE;
                }
                anonymousClass1.L$0 = r8;
                anonymousClass1.L$1 = function2;
                anonymousClass1.L$2 = context;
                anonymousClass1.label = 1;
                if (function2.invoke((Object) r8, anonymousClass1) != coroutine_suspended) {
                    r3 = r8;
                    pointerInputScope = context;
                    anonymousClass1.L$0 = r3;
                    anonymousClass1.L$1 = function2;
                    anonymousClass1.L$2 = pointerInputScope;
                    anonymousClass1.label = 2;
                    Object objAwaitAllPointersUp = awaitAllPointersUp(r3, anonymousClass1);
                    r2 = r3;
                    pointerInputScope2 = pointerInputScope;
                }
                r2 = r3;
                pointerInputScope2 = pointerInputScope;
                return coroutine_suspended;
            }
            if (r3 == 1) {
                PointerInputScope pointerInputScope3 = (CoroutineContext) anonymousClass1.L$2;
                function2 = (Function2) anonymousClass1.L$1;
                PointerInputScope pointerInputScope4 = (PointerInputScope) anonymousClass1.L$0;
                ResultKt.throwOnFailure(obj);
                r3 = pointerInputScope4;
                pointerInputScope = pointerInputScope3;
                anonymousClass1.L$0 = r3;
                anonymousClass1.L$1 = function2;
                anonymousClass1.L$2 = pointerInputScope;
                anonymousClass1.label = 2;
                Object objAwaitAllPointersUp2 = awaitAllPointersUp(r3, anonymousClass1);
                r2 = r3;
                pointerInputScope2 = pointerInputScope;
            } else if (r3 == 2) {
                PointerInputScope pointerInputScope5 = (CoroutineContext) anonymousClass1.L$2;
                function2 = (Function2) anonymousClass1.L$1;
                PointerInputScope pointerInputScope6 = (PointerInputScope) anonymousClass1.L$0;
                ResultKt.throwOnFailure(obj);
                r2 = pointerInputScope6;
                pointerInputScope2 = pointerInputScope5;
            } else {
                if (r3 != 3) {
                    k2d.a("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                PointerInputScope pointerInputScope7 = (CoroutineContext) anonymousClass1.L$2;
                function2 = (Function2) anonymousClass1.L$1;
                PointerInputScope pointerInputScope8 = (PointerInputScope) anonymousClass1.L$0;
                ResultKt.throwOnFailure(obj);
                r2 = pointerInputScope8;
                pointerInputScope2 = pointerInputScope7;
            }
        } catch (CancellationException e) {
            e = e;
            if (!JobKt.isActive(pointerInputScope)) {
                throw e;
            }
            anonymousClass1.L$0 = r3;
            anonymousClass1.L$1 = function2;
            anonymousClass1.L$2 = pointerInputScope;
            anonymousClass1.label = 3;
        }
        r2 = r3;
        pointerInputScope2 = pointerInputScope;
        context = pointerInputScope2;
        r8 = r2;
        if (JobKt.isActive(context)) {
            return Unit.INSTANCE;
        }
        anonymousClass1.L$0 = r8;
        anonymousClass1.L$1 = function2;
        anonymousClass1.L$2 = context;
        anonymousClass1.label = 1;
        if (function2.invoke((Object) r8, anonymousClass1) != coroutine_suspended) {
            r3 = r8;
            pointerInputScope = context;
            anonymousClass1.L$0 = r3;
            anonymousClass1.L$1 = function2;
            anonymousClass1.L$2 = pointerInputScope;
            anonymousClass1.label = 2;
            Object objAwaitAllPointersUp3 = awaitAllPointersUp(r3, anonymousClass1);
            r2 = r3;
            pointerInputScope2 = pointerInputScope;
        }
        r2 = r3;
        pointerInputScope2 = pointerInputScope;
        return coroutine_suspended;
    }

    public static final Object awaitAllPointersUp(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
        Object objAwaitPointerEventScope = pointerInputScope.awaitPointerEventScope(new AnonymousClass2(null), continuation);
        return objAwaitPointerEventScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAwaitPointerEventScope : Unit.INSTANCE;
    }
}
