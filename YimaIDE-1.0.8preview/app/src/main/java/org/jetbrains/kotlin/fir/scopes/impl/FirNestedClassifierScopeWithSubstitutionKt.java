package org.jetbrains.kotlin.fir.scopes.impl;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.resolve.SupertypeUtilsKt;
import org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001a\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\u0006"}, d2 = {"wrapNestedClassifierScopeWithSubstitutionForSuperType", "Lorg/jetbrains/kotlin/fir/scopes/FirContainingNamesAwareScope;", "superType", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "org.jetbrains.kotlin:providers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirNestedClassifierScopeWithSubstitutionKt {
    public static final FirContainingNamesAwareScope wrapNestedClassifierScopeWithSubstitutionForSuperType(FirContainingNamesAwareScope firContainingNamesAwareScope, ConeClassLikeType coneClassLikeType, FirSession firSession) {
        firContainingNamesAwareScope.getClass();
        coneClassLikeType.getClass();
        firSession.getClass();
        return new FirNestedClassifierScopeWithSubstitution(firContainingNamesAwareScope, SupertypeUtilsKt.createSubstitutionForSupertype(coneClassLikeType, firSession));
    }
}
