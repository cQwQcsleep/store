package org.jetbrains.kotlin.fir.java.enhancement;

import java.util.function.UnaryOperator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.java.declarations.FirJavaTypeParameter;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirSignatureEnhancement$replaceEnhancedBounds$1$1<T> implements UnaryOperator {
    final /* synthetic */ Function2<FirTypeParameter, FirResolvedTypeRef, FirResolvedTypeRef> $block;
    final /* synthetic */ FirJavaTypeParameter $typeParameter;

    /* JADX WARN: Multi-variable type inference failed */
    public FirSignatureEnhancement$replaceEnhancedBounds$1$1(Function2<? super FirTypeParameter, ? super FirResolvedTypeRef, ? extends FirResolvedTypeRef> function2, FirJavaTypeParameter firJavaTypeParameter) {
        this.$block = function2;
        this.$typeParameter = firJavaTypeParameter;
    }

    @Override // java.util.function.Function
    public final FirResolvedTypeRef apply(FirResolvedTypeRef firResolvedTypeRef) {
        firResolvedTypeRef.getClass();
        return (FirResolvedTypeRef) this.$block.invoke(this.$typeParameter, firResolvedTypeRef);
    }
}
