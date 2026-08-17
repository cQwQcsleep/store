package androidx.compose.compiler.plugins.kotlin.k2;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.expressions.FirTryExpression;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(k = 3, mv = {2, 4, 0}, xi = 48)
public final class ComposableCallCheckerKt$visitCurrentScope$3 implements Function2<FirTryExpression, FirElement, Unit> {
    public static final ComposableCallCheckerKt$visitCurrentScope$3 INSTANCE = new ComposableCallCheckerKt$visitCurrentScope$3();

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((FirTryExpression) obj, (FirElement) obj2);
        return Unit.INSTANCE;
    }

    public final void invoke(FirTryExpression firTryExpression, FirElement firElement) {
        firTryExpression.getClass();
        firElement.getClass();
    }
}
