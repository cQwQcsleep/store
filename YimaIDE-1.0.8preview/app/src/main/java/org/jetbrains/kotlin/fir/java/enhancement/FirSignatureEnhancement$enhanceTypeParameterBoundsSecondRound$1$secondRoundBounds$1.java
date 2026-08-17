package org.jetbrains.kotlin.fir.java.enhancement;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.java.JavaTypeParameterStack;
import org.jetbrains.kotlin.fir.java.declarations.FirJavaTypeParameter;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* synthetic */ class FirSignatureEnhancement$enhanceTypeParameterBoundsSecondRound$1$secondRoundBounds$1 extends FunctionReferenceImpl implements Function3<FirJavaTypeParameter, JavaTypeParameterStack, KtSourceElement, List<FirResolvedTypeRef>> {
    public static final FirSignatureEnhancement$enhanceTypeParameterBoundsSecondRound$1$secondRoundBounds$1 INSTANCE = new FirSignatureEnhancement$enhanceTypeParameterBoundsSecondRound$1$secondRoundBounds$1();

    public FirSignatureEnhancement$enhanceTypeParameterBoundsSecondRound$1$secondRoundBounds$1() {
        super(3, FirJavaTypeParameter.class, "performSecondRoundOfBoundsResolution", "performSecondRoundOfBoundsResolution$org_jetbrains_kotlin_fir_jvm(Lorg/jetbrains/kotlin/fir/java/JavaTypeParameterStack;Lorg/jetbrains/kotlin/KtSourceElement;)Ljava/util/List;", 0);
    }

    public final List<FirResolvedTypeRef> invoke(FirJavaTypeParameter firJavaTypeParameter, JavaTypeParameterStack javaTypeParameterStack, KtSourceElement ktSourceElement) {
        firJavaTypeParameter.getClass();
        javaTypeParameterStack.getClass();
        return firJavaTypeParameter.performSecondRoundOfBoundsResolution$org_jetbrains_kotlin_fir_jvm(javaTypeParameterStack, ktSourceElement);
    }
}
