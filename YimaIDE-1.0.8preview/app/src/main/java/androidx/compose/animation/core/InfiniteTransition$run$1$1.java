package androidx.compose.animation.core;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import androidx.compose.runtime.collection.MutableVector;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.animation.core.InfiniteTransition$run$1$1", f = "InfiniteTransition.kt", i = {0, 0, 1, 1}, l = {172, 193}, m = "invokeSuspend", n = {"$this$LaunchedEffect", "durationScale", "$this$LaunchedEffect", "durationScale"}, s = {"L$0", "L$1", "L$0", "L$1"}, v = 1)
public final class InfiniteTransition$run$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MutableState<State<Long>> $toolingOverride;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ InfiniteTransition this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public InfiniteTransition$run$1$1(MutableState<State<Long>> mutableState, InfiniteTransition infiniteTransition, Continuation<? super InfiniteTransition$run$1$1> continuation) {
        super(2, continuation);
        this.$toolingOverride = mutableState;
        this.this$0 = infiniteTransition;
    }

    public static Unit b(MutableState mutableState, InfiniteTransition infiniteTransition, Ref.FloatRef floatRef, CoroutineScope coroutineScope, long j) {
        State state = (State) mutableState.getValue();
        long jLongValue = state != null ? ((Number) state.getValue()).longValue() : j;
        if (infiniteTransition.startTimeNanos == Long.MIN_VALUE || floatRef.element != SuspendAnimationKt.getDurationScale(coroutineScope.getCoroutineContext())) {
            infiniteTransition.startTimeNanos = j;
            MutableVector mutableVector = infiniteTransition._animations;
            Object[] objArr = mutableVector.content;
            int size = mutableVector.getSize();
            for (int i = 0; i < size; i++) {
                ((InfiniteTransition.TransitionAnimationState) objArr[i]).reset$animation_core();
            }
            floatRef.element = SuspendAnimationKt.getDurationScale(coroutineScope.getCoroutineContext());
        }
        if (floatRef.element == 0.0f) {
            MutableVector mutableVector2 = infiniteTransition._animations;
            Object[] objArr2 = mutableVector2.content;
            int size2 = mutableVector2.getSize();
            for (int i2 = 0; i2 < size2; i2++) {
                ((InfiniteTransition.TransitionAnimationState) objArr2[i2]).skipToEnd$animation_core();
            }
        } else {
            infiniteTransition.onFrame((long) ((jLongValue - infiniteTransition.startTimeNanos) / floatRef.element));
        }
        return Unit.INSTANCE;
    }

    public static float d(CoroutineScope coroutineScope) {
        return SuspendAnimationKt.getDurationScale(coroutineScope.getCoroutineContext());
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        InfiniteTransition$run$1$1 infiniteTransition$run$1$1 = new InfiniteTransition$run$1$1(this.$toolingOverride, this.this$0, continuation);
        infiniteTransition$run$1$1.L$0 = obj;
        return infiniteTransition$run$1$1;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:11:0x003f A[PHI: r1 r9
      0x003f: PHI (r1v3 kotlin.jvm.internal.Ref$FloatRef) = 
      (r1v1 kotlin.jvm.internal.Ref$FloatRef)
      (r1v2 kotlin.jvm.internal.Ref$FloatRef)
      (r1v2 kotlin.jvm.internal.Ref$FloatRef)
      (r1v7 kotlin.jvm.internal.Ref$FloatRef)
     binds: [B:10:0x002f, B:15:0x005a, B:17:0x0074, B:6:0x000f] A[DONT_GENERATE, DONT_INLINE]
      0x003f: PHI (r9v4 kotlinx.coroutines.CoroutineScope) = 
      (r9v2 kotlinx.coroutines.CoroutineScope)
      (r9v3 kotlinx.coroutines.CoroutineScope)
      (r9v3 kotlinx.coroutines.CoroutineScope)
      (r9v6 kotlinx.coroutines.CoroutineScope)
     binds: [B:10:0x002f, B:15:0x005a, B:17:0x0074, B:6:0x000f] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:14:0x0055 A[PHI: r1 r9
      0x0055: PHI (r1v2 kotlin.jvm.internal.Ref$FloatRef) = (r1v3 kotlin.jvm.internal.Ref$FloatRef), (r1v5 kotlin.jvm.internal.Ref$FloatRef) binds: [B:12:0x0052, B:9:0x0022] A[DONT_GENERATE, DONT_INLINE]
      0x0055: PHI (r9v3 kotlinx.coroutines.CoroutineScope) = (r9v4 kotlinx.coroutines.CoroutineScope), (r9v5 kotlinx.coroutines.CoroutineScope) binds: [B:12:0x0052, B:9:0x0022] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:16:0x005c  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:15:0x005a -> B:11:0x003f). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x0074 -> B:11:0x003f). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public final java.lang.Object invokeSuspend(java.lang.Object r9) {
        /*
            r8 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r8.label
            r2 = 0
            r3 = 2
            r4 = 1
            if (r1 == 0) goto L2f
            if (r1 == r4) goto L22
            if (r1 != r3) goto L1c
            java.lang.Object r1 = r8.L$1
            kotlin.jvm.internal.Ref$FloatRef r1 = (kotlin.jvm.internal.Ref.FloatRef) r1
            java.lang.Object r5 = r8.L$0
            kotlinx.coroutines.CoroutineScope r5 = (kotlinx.coroutines.CoroutineScope) r5
            kotlin.ResultKt.throwOnFailure(r9)
            r9 = r5
            goto L3f
        L1c:
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            k2d.a(r8)
            return r2
        L22:
            java.lang.Object r1 = r8.L$1
            kotlin.jvm.internal.Ref$FloatRef r1 = (kotlin.jvm.internal.Ref.FloatRef) r1
            java.lang.Object r5 = r8.L$0
            kotlinx.coroutines.CoroutineScope r5 = (kotlinx.coroutines.CoroutineScope) r5
            kotlin.ResultKt.throwOnFailure(r9)
            r9 = r5
            goto L55
        L2f:
            kotlin.ResultKt.throwOnFailure(r9)
            java.lang.Object r9 = r8.L$0
            kotlinx.coroutines.CoroutineScope r9 = (kotlinx.coroutines.CoroutineScope) r9
            kotlin.jvm.internal.Ref$FloatRef r1 = new kotlin.jvm.internal.Ref$FloatRef
            r1.<init>()
            r5 = 1065353216(0x3f800000, float:1.0)
            r1.element = r5
        L3f:
            androidx.compose.runtime.MutableState<androidx.compose.runtime.State<java.lang.Long>> r5 = r8.$toolingOverride
            androidx.compose.animation.core.InfiniteTransition r6 = r8.this$0
            androidx.compose.animation.core.b r7 = new androidx.compose.animation.core.b
            r7.<init>()
            r8.L$0 = r9
            r8.L$1 = r1
            r8.label = r4
            java.lang.Object r5 = androidx.compose.animation.core.InfiniteAnimationPolicyKt.withInfiniteAnimationFrameNanos(r7, r8)
            if (r5 != r0) goto L55
            goto L76
        L55:
            float r5 = r1.element
            r6 = 0
            int r5 = (r5 > r6 ? 1 : (r5 == r6 ? 0 : -1))
            if (r5 != 0) goto L3f
            androidx.compose.animation.core.c r5 = new androidx.compose.animation.core.c
            r5.<init>()
            kotlinx.coroutines.flow.Flow r5 = androidx.compose.runtime.SnapshotStateKt.snapshotFlow(r5)
            androidx.compose.animation.core.InfiniteTransition$run$1$1$3 r6 = new androidx.compose.animation.core.InfiniteTransition$run$1$1$3
            r6.<init>(r2)
            r8.L$0 = r9
            r8.L$1 = r1
            r8.label = r3
            java.lang.Object r5 = kotlinx.coroutines.flow.FlowKt.first(r5, r6, r8)
            if (r5 != r0) goto L3f
        L76:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.animation.core.InfiniteTransition$run$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    /* JADX INFO: renamed from: androidx.compose.animation.core.InfiniteTransition$run$1$1$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", ""}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.animation.core.InfiniteTransition$run$1$1$3", f = "InfiniteTransition.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    public static final class AnonymousClass3 extends SuspendLambda implements Function2<Float, Continuation<? super Boolean>, Object> {
        /* synthetic */ float F$0;
        int label;

        public AnonymousClass3(Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass3 anonymousClass3 = new AnonymousClass3(continuation);
            anonymousClass3.F$0 = ((Number) obj).floatValue();
            return anonymousClass3;
        }

        public final Object invoke(float f, Continuation<? super Boolean> continuation) {
            return create(Float.valueOf(f), continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return Boxing.boxBoolean(this.F$0 > 0.0f);
            }
            k2d.a("call to 'resume' before 'invoke' with coroutine");
            return null;
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            return invoke(((Number) obj).floatValue(), (Continuation<? super Boolean>) obj2);
        }
    }
}
