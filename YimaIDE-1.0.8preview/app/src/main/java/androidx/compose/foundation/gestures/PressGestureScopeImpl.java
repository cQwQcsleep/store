package androidx.compose.foundation.gestures;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DpRect;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.Mutex$DefaultImpls;
import kotlinx.coroutines.sync.MutexKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\b\u0001\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\u000b\u001a\u00020\fJ\u0006\u0010\r\u001a\u00020\fJ\u000e\u0010\u000e\u001a\u00020\fH\u0086@¢\u0006\u0002\u0010\u000fJ\u000e\u0010\u0010\u001a\u00020\fH\u0096@¢\u0006\u0002\u0010\u000fJ\u000e\u0010\u0011\u001a\u00020\u0007H\u0096@¢\u0006\u0002\u0010\u000fJ\u0014\u0010\u0012\u001a\u00020\u0013*\u00020\u0014H\u0097\u0001¢\u0006\u0004\b\u0015\u0010\u0016J\u0014\u0010\u0012\u001a\u00020\u0013*\u00020\u0017H\u0097\u0001¢\u0006\u0004\b\u0018\u0010\u0019J\u0014\u0010\u001a\u001a\u00020\u0014*\u00020\u0013H\u0097\u0001¢\u0006\u0004\b\u001b\u0010\u001cJ\u0014\u0010\u001a\u001a\u00020\u0014*\u00020\u001dH\u0097\u0001¢\u0006\u0004\b\u001b\u0010\u001eJ\u0014\u0010\u001a\u001a\u00020\u0014*\u00020\u0017H\u0097\u0001¢\u0006\u0004\b\u001f\u0010 J\u0014\u0010!\u001a\u00020\"*\u00020#H\u0097\u0001¢\u0006\u0004\b$\u0010%J\u0014\u0010&\u001a\u00020\u001d*\u00020\u0014H\u0097\u0001¢\u0006\u0004\b'\u0010\u001eJ\u0014\u0010&\u001a\u00020\u001d*\u00020\u0017H\u0097\u0001¢\u0006\u0004\b(\u0010 J\r\u0010)\u001a\u00020**\u00020+H\u0097\u0001J\u0014\u0010,\u001a\u00020#*\u00020\"H\u0097\u0001¢\u0006\u0004\b-\u0010%J\u0014\u0010.\u001a\u00020\u0017*\u00020\u0013H\u0097\u0001¢\u0006\u0004\b/\u00100J\u0014\u0010.\u001a\u00020\u0017*\u00020\u001dH\u0097\u0001¢\u0006\u0004\b/\u00101J\u0014\u0010.\u001a\u00020\u0017*\u00020\u0014H\u0097\u0001¢\u0006\u0004\b2\u00101R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\u00020\u001d8\u0016X\u0097\u0005¢\u0006\u0006\u001a\u0004\b3\u00104R\u0014\u00105\u001a\u00020\u001d8\u0016X\u0097\u0005¢\u0006\u0006\u001a\u0004\b6\u00104¨\u00067"}, d2 = {"Landroidx/compose/foundation/gestures/PressGestureScopeImpl;", "Landroidx/compose/foundation/gestures/PressGestureScope;", "Landroidx/compose/ui/unit/Density;", "density", "<init>", "(Landroidx/compose/ui/unit/Density;)V", "isReleased", "", "isCanceled", "mutex", "Lkotlinx/coroutines/sync/Mutex;", "cancel", "", "release", "reset", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitRelease", "tryAwaitRelease", "roundToPx", "", "Landroidx/compose/ui/unit/Dp;", "roundToPx-0680j_4", "(F)I", "Landroidx/compose/ui/unit/TextUnit;", "roundToPx--R2X_6o", "(J)I", "toDp", "toDp-u2uoSUM", "(I)F", "", "(F)F", "toDp-GaN1DYA", "(J)F", "toDpSize", "Landroidx/compose/ui/unit/DpSize;", "Landroidx/compose/ui/geometry/Size;", "toDpSize-k-rfVVM", "(J)J", "toPx", "toPx-0680j_4", "toPx--R2X_6o", "toRect", "Landroidx/compose/ui/geometry/Rect;", "Landroidx/compose/ui/unit/DpRect;", "toSize", "toSize-XkaWNTQ", "toSp", "toSp-kPz2Gy4", "(I)J", "(F)J", "toSp-0xMU5do", "getDensity", "()F", "fontScale", "getFontScale", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class PressGestureScopeImpl implements PressGestureScope, Density {
    public static final int $stable = 0;
    private final /* synthetic */ Density $$delegate_0;
    private boolean isCanceled;
    private boolean isReleased;
    private final Mutex mutex = MutexKt.Mutex(false);

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.PressGestureScopeImpl$awaitRelease$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.PressGestureScopeImpl", f = "TapGestureDetector.kt", i = {}, l = {533}, m = "awaitRelease", n = {}, s = {}, v = 1)
    public static final class AnonymousClass1 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PressGestureScopeImpl.this.awaitRelease(this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.PressGestureScopeImpl$reset$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.PressGestureScopeImpl", f = "TapGestureDetector.kt", i = {}, l = {527}, m = "reset", n = {}, s = {}, v = 1)
    public static final class C01161 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        public C01161(Continuation<? super C01161> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PressGestureScopeImpl.this.reset(this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.PressGestureScopeImpl$tryAwaitRelease$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.PressGestureScopeImpl", f = "TapGestureDetector.kt", i = {}, l = {540}, m = "tryAwaitRelease", n = {}, s = {}, v = 1)
    public static final class C01171 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        public C01171(Continuation<? super C01171> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PressGestureScopeImpl.this.tryAwaitRelease(this);
        }
    }

    public PressGestureScopeImpl(Density density) {
        this.$$delegate_0 = density;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    @Override // androidx.compose.foundation.gestures.PressGestureScope
    public Object awaitRelease(Continuation<? super Unit> continuation) {
        AnonymousClass1 anonymousClass1;
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
        Object objTryAwaitRelease = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = anonymousClass1.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(objTryAwaitRelease);
            anonymousClass1.label = 1;
            objTryAwaitRelease = tryAwaitRelease(anonymousClass1);
            if (objTryAwaitRelease == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i2 != 1) {
                k2d.a("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(objTryAwaitRelease);
        }
        if (((Boolean) objTryAwaitRelease).booleanValue()) {
            return Unit.INSTANCE;
        }
        throw new GestureCancellationException("The press gesture was canceled.");
    }

    public final void cancel() {
        this.isCanceled = true;
        if (this.mutex.isLocked()) {
            Mutex$DefaultImpls.unlock$default(this.mutex, null, 1, null);
        }
    }

    public float getDensity() {
        return this.$$delegate_0.getDensity();
    }

    public float getFontScale() {
        return this.$$delegate_0.getFontScale();
    }

    public final void release() {
        this.isReleased = true;
        if (this.mutex.isLocked()) {
            Mutex$DefaultImpls.unlock$default(this.mutex, null, 1, null);
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public final Object reset(Continuation<? super Unit> continuation) {
        C01161 c01161;
        if (continuation instanceof C01161) {
            c01161 = (C01161) continuation;
            int i = c01161.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                c01161.label = i - Integer.MIN_VALUE;
            } else {
                c01161 = new C01161(continuation);
            }
        } else {
            c01161 = new C01161(continuation);
        }
        Object obj = c01161.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c01161.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            Mutex mutex = this.mutex;
            c01161.label = 1;
            if (Mutex$DefaultImpls.lock$default(mutex, null, c01161, 1, null) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i2 != 1) {
                k2d.a("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
        }
        this.isReleased = false;
        this.isCanceled = false;
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: roundToPx--R2X_6o, reason: not valid java name */
    public int m657roundToPxR2X_6o(long j) {
        return this.$$delegate_0.roundToPx--R2X_6o(j);
    }

    /* JADX INFO: renamed from: roundToPx-0680j_4, reason: not valid java name */
    public int m658roundToPx0680j_4(float f) {
        return this.$$delegate_0.roundToPx-0680j_4(f);
    }

    /* JADX INFO: renamed from: toDp-GaN1DYA, reason: not valid java name */
    public float m659toDpGaN1DYA(long j) {
        return this.$$delegate_0.toDp-GaN1DYA(j);
    }

    /* JADX INFO: renamed from: toDp-u2uoSUM, reason: not valid java name */
    public float m660toDpu2uoSUM(float f) {
        return this.$$delegate_0.toDp-u2uoSUM(f);
    }

    /* JADX INFO: renamed from: toDpSize-k-rfVVM, reason: not valid java name */
    public long m662toDpSizekrfVVM(long j) {
        return this.$$delegate_0.toDpSize-k-rfVVM(j);
    }

    /* JADX INFO: renamed from: toPx--R2X_6o, reason: not valid java name */
    public float m663toPxR2X_6o(long j) {
        return this.$$delegate_0.toPx--R2X_6o(j);
    }

    /* JADX INFO: renamed from: toPx-0680j_4, reason: not valid java name */
    public float m664toPx0680j_4(float f) {
        return this.$$delegate_0.toPx-0680j_4(f);
    }

    public Rect toRect(DpRect dpRect) {
        return this.$$delegate_0.toRect(dpRect);
    }

    /* JADX INFO: renamed from: toSize-XkaWNTQ, reason: not valid java name */
    public long m665toSizeXkaWNTQ(long j) {
        return this.$$delegate_0.toSize-XkaWNTQ(j);
    }

    /* JADX INFO: renamed from: toSp-0xMU5do, reason: not valid java name */
    public long m666toSp0xMU5do(float f) {
        return this.$$delegate_0.toSp-0xMU5do(f);
    }

    /* JADX INFO: renamed from: toSp-kPz2Gy4, reason: not valid java name */
    public long m667toSpkPz2Gy4(float f) {
        return this.$$delegate_0.toSp-kPz2Gy4(f);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    @Override // androidx.compose.foundation.gestures.PressGestureScope
    public Object tryAwaitRelease(Continuation<? super Boolean> continuation) {
        C01171 c01171;
        if (continuation instanceof C01171) {
            c01171 = (C01171) continuation;
            int i = c01171.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                c01171.label = i - Integer.MIN_VALUE;
            } else {
                c01171 = new C01171(continuation);
            }
        } else {
            c01171 = new C01171(continuation);
        }
        Object obj = c01171.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c01171.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            if (!this.isReleased && !this.isCanceled) {
                Mutex mutex = this.mutex;
                c01171.label = 1;
                if (Mutex$DefaultImpls.lock$default(mutex, null, c01171, 1, null) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            return Boxing.boxBoolean(this.isReleased);
        }
        if (i2 != 1) {
            k2d.a("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        ResultKt.throwOnFailure(obj);
        Mutex$DefaultImpls.unlock$default(this.mutex, null, 1, null);
        return Boxing.boxBoolean(this.isReleased);
    }

    /* JADX INFO: renamed from: toDp-u2uoSUM, reason: not valid java name */
    public float m661toDpu2uoSUM(int i) {
        return this.$$delegate_0.toDp-u2uoSUM(i);
    }

    /* JADX INFO: renamed from: toSp-kPz2Gy4, reason: not valid java name */
    public long m668toSpkPz2Gy4(int i) {
        return this.$$delegate_0.toSp-kPz2Gy4(i);
    }
}
