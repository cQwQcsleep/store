package org.jetbrains.kotlin.fir.resolve.calls;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.scopes.FirScope;
import org.jetbrains.kotlin.fir.scopes.impl.FirOnlyCallablesScope;
import org.jetbrains.kotlin.fir.scopes.impl.FirOnlyClassifiersScope;
import org.jetbrains.kotlin.fir.scopes.impl.FirPackageMemberScope;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0016R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/PackageQualifierReceiver;", "Lorg/jetbrains/kotlin/fir/resolve/calls/QualifierReceiver;", "explicitReceiver", "Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;", "useSiteSession", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;Lorg/jetbrains/kotlin/fir/FirSession;)V", "scope", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirPackageMemberScope;", "getScope", "()Lorg/jetbrains/kotlin/fir/scopes/impl/FirPackageMemberScope;", "classifierScope", "Lorg/jetbrains/kotlin/fir/scopes/FirScope;", "callableScope", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirOnlyCallablesScope;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class PackageQualifierReceiver extends QualifierReceiver {
    private final FirPackageMemberScope scope;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PackageQualifierReceiver(FirResolvedQualifier firResolvedQualifier, FirSession firSession) {
        super(firResolvedQualifier);
        firResolvedQualifier.getClass();
        firSession.getClass();
        this.scope = new FirPackageMemberScope(firResolvedQualifier.getPackageFqName(), firSession, null, null, 12, null);
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.QualifierReceiver
    public FirOnlyCallablesScope callableScope() {
        return new FirOnlyCallablesScope(this.scope);
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.QualifierReceiver
    public FirScope classifierScope() {
        return new FirOnlyClassifiersScope(this.scope);
    }

    public final FirPackageMemberScope getScope() {
        return this.scope;
    }
}
