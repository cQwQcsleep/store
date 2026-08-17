package androidx.compose.compiler.plugins.kotlin.k2;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(k = 3, mv = {2, 4, 0}, xi = 48)
public final class ComposableCallCheckerKt$visitCurrentScope$4 implements Function1<FirFunctionCall, Unit> {
    public static final ComposableCallCheckerKt$visitCurrentScope$4 INSTANCE = new ComposableCallCheckerKt$visitCurrentScope$4();

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((FirFunctionCall) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(FirFunctionCall firFunctionCall) {
        firFunctionCall.getClass();
    }
}
