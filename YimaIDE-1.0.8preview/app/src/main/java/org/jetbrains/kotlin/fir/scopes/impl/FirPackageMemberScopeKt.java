package org.jetbrains.kotlin.fir.scopes.impl;

import kotlin.Metadata;
import kotlin.Pair;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.resolve.ScopeSessionKey;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\")\u0010\u0000\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002\u0012\u0004\u0012\u00020\u00050\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"PACKAGE_MEMBER", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSessionKey;", "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/name/FqName;", "Lorg/jetbrains/kotlin/fir/FirSession;", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirPackageMemberScope;", "getPACKAGE_MEMBER", "()Lorg/jetbrains/kotlin/fir/resolve/ScopeSessionKey;", "org.jetbrains.kotlin:providers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirPackageMemberScopeKt {
    private static final ScopeSessionKey<Pair<FqName, FirSession>, FirPackageMemberScope> PACKAGE_MEMBER = new ScopeSessionKey<Pair<? extends FqName, ? extends FirSession>, FirPackageMemberScope>() { // from class: org.jetbrains.kotlin.fir.scopes.impl.FirPackageMemberScopeKt$special$$inlined$scopeSessionKey$1
    };

    public static final ScopeSessionKey<Pair<FqName, FirSession>, FirPackageMemberScope> getPACKAGE_MEMBER() {
        return PACKAGE_MEMBER;
    }
}
