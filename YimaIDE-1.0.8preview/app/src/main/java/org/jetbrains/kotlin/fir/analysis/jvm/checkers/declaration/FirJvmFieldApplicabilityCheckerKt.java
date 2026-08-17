package org.jetbrains.kotlin.fir.analysis.jvm.checkers.declaration;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.name.StandardClassIds;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\f\u0010\u0005\u001a\u00020\u0001*\u00020\u0006H\u0002¨\u0006\u0007"}, d2 = {"isInlineClassThatRequiresMangling", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "isDontMangleClass", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "org.jetbrains.kotlin:checkers.jvm"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJvmFieldApplicabilityCheckerKt {
    private static final boolean isDontMangleClass(FirRegularClassSymbol firRegularClassSymbol) {
        return Intrinsics.areEqual(firRegularClassSymbol.getClassId(), StandardClassIds.INSTANCE.getResult());
    }

    public static final boolean isInlineClassThatRequiresMangling(FirTypeRef firTypeRef, FirSession firSession) {
        firTypeRef.getClass();
        firSession.getClass();
        FirRegularClassSymbol regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(FirTypeUtilsKt.getConeType(firTypeRef), firSession);
        if (regularClassSymbol == null) {
            return false;
        }
        return (regularClassSymbol.getRawStatus().isInline() || regularClassSymbol.getRawStatus().isValue()) && !isDontMangleClass(regularClassSymbol);
    }
}
