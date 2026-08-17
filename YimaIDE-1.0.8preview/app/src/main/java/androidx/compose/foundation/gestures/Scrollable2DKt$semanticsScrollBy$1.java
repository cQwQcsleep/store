package androidx.compose.foundation.gestures;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.gestures.Scrollable2DKt", f = "Scrollable2D.kt", i = {0}, l = {509}, m = "semanticsScrollBy-d-4ec7I", n = {"previousValue"}, s = {"L$0"}, v = 1)
public final class Scrollable2DKt$semanticsScrollBy$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;

    public Scrollable2DKt$semanticsScrollBy$1(Continuation<? super Scrollable2DKt$semanticsScrollBy$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return Scrollable2DKt.m680semanticsScrollByd4ec7I(null, 0L, this);
    }
}
