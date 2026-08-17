package org.jetbrains.kotlin.fir.symbols;

import kotlin.Metadata;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/symbols/FirLazyResolveContractViolationException;", "Ljava/lang/IllegalStateException;", "Lkotlin/IllegalStateException;", "currentPhase", "Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;", "requestedPhase", "<init>", "(Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;)V", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirLazyResolveContractViolationException extends IllegalStateException {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirLazyResolveContractViolationException(FirResolvePhase firResolvePhase, FirResolvePhase firResolvePhase2) {
        super(StringsKt.trimIndent("\n        `lazyResolveToPhase(" + firResolvePhase2 + ")` cannot be called from a transformer with a phase " + firResolvePhase + ".\n        `lazyResolveToPhase` can be called only from a transformer with a phase which is strictly greater than a requested phase;\n         i.e., `lazyResolveToPhase(A)` may be only called from a lazy transformer with a phase B, where A < B. This is a contract of lazy resolve\n     "));
        firResolvePhase.getClass();
        firResolvePhase2.getClass();
    }
}
