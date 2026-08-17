package org.jetbrains.kotlin.fir;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u001a7\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0002*\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\b\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\t"}, d2 = {"unwrapOr", "T", "Lorg/jetbrains/kotlin/fir/FirElement;", "Lorg/jetbrains/kotlin/fir/FirEvaluatorResult;", "action", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/FirEvaluatorResult$CompileTimeException;", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/FirEvaluatorResult;Lkotlin/jvm/functions/Function1;)Lorg/jetbrains/kotlin/fir/FirElement;", "org.jetbrains.kotlin:tree"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirEvaluatorResultKt {
    public static final /* synthetic */ <T extends FirElement> T unwrapOr(FirEvaluatorResult firEvaluatorResult, Function1<? super FirEvaluatorResult.CompileTimeException, Unit> function1) {
        firEvaluatorResult.getClass();
        function1.getClass();
        if (firEvaluatorResult instanceof FirEvaluatorResult.CompileTimeException) {
            function1.invoke(firEvaluatorResult);
            return null;
        }
        if (!(firEvaluatorResult instanceof FirEvaluatorResult.Evaluated)) {
            return null;
        }
        T t = (T) ((FirEvaluatorResult.Evaluated) firEvaluatorResult).getResult();
        Intrinsics.reifiedOperationMarker(2, "T");
        return t;
    }
}
