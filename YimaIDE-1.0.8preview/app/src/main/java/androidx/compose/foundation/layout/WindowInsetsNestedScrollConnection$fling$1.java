package androidx.compose.foundation.layout;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.layout.WindowInsetsNestedScrollConnection", f = "WindowInsetsConnection.android.kt", i = {0, 0, 1, 1, 2}, l = {301, 327, 352}, m = "fling-huYlsQE", n = {"$v$c$androidx-compose-ui-unit-Velocity$-available$0", "flingAmount", "endVelocity", "$v$c$androidx-compose-ui-unit-Velocity$-available$0", "$v$c$androidx-compose-ui-unit-Velocity$-available$0"}, s = {"J$0", "F$0", "L$0", "J$0", "J$0"}, v = 1)
public final class WindowInsetsNestedScrollConnection$fling$1 extends ContinuationImpl {
    float F$0;
    long J$0;
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ WindowInsetsNestedScrollConnection this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WindowInsetsNestedScrollConnection$fling$1(WindowInsetsNestedScrollConnection windowInsetsNestedScrollConnection, Continuation<? super WindowInsetsNestedScrollConnection$fling$1> continuation) {
        super(continuation);
        this.this$0 = windowInsetsNestedScrollConnection;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.m1019flinghuYlsQE(0L, 0.0f, false, this);
    }
}
