package org.jetbrains.kotlin.fir.builder;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.FunctionReferenceImpl;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirFunctionTarget;
import org.jetbrains.kotlin.fir.declarations.FirFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* synthetic */ class PsiRawFirBuilder$Visitor$toFirProperty$2$1$3$5 extends FunctionReferenceImpl implements Function2<FirFunctionTarget, FirFunction, Unit> {
    public PsiRawFirBuilder$Visitor$toFirProperty$2$1$3$5(Object obj) {
        super(2, obj, PsiRawFirBuilder.class, "bindFunctionTarget", "bindFunctionTarget(Lorg/jetbrains/kotlin/fir/FirFunctionTarget;Lorg/jetbrains/kotlin/fir/declarations/FirFunction;)V", 0);
    }

    public final void invoke(FirFunctionTarget firFunctionTarget, FirFunction firFunction) {
        firFunctionTarget.getClass();
        firFunction.getClass();
        ((PsiRawFirBuilder) ((CallableReference) this).receiver).bindFunctionTarget(firFunctionTarget, firFunction);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((FirFunctionTarget) obj, (FirFunction) obj2);
        return Unit.INSTANCE;
    }
}
