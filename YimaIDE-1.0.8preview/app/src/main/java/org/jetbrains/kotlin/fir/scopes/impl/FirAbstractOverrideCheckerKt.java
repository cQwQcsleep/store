package org.jetbrains.kotlin.fir.scopes.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutorByMapKt;
import org.jetbrains.kotlin.fir.types.impl.ConeTypeParameterTypeImpl;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a \u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"buildSubstitutorForOverridesCheck", "Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "overrideCandidate", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "baseDeclaration", "useSiteSession", "Lorg/jetbrains/kotlin/fir/FirSession;", "org.jetbrains.kotlin:providers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirAbstractOverrideCheckerKt {
    public static final ConeSubstitutor buildSubstitutorForOverridesCheck(FirCallableDeclaration firCallableDeclaration, FirCallableDeclaration firCallableDeclaration2, FirSession firSession) {
        firCallableDeclaration.getClass();
        firCallableDeclaration2.getClass();
        firSession.getClass();
        if (firCallableDeclaration.getTypeParameters().size() != firCallableDeclaration2.getTypeParameters().size()) {
            return null;
        }
        if (firCallableDeclaration2.getTypeParameters().isEmpty()) {
            return ConeSubstitutor.Empty.INSTANCE;
        }
        List<FirTypeParameterRef> typeParameters = firCallableDeclaration2.getTypeParameters();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(typeParameters, 10));
        Iterator<T> it = typeParameters.iterator();
        while (it.hasNext()) {
            arrayList.add(new ConeTypeParameterTypeImpl(((FirTypeParameterRef) it.next()).getSymbol().getLookupTag(), false, null, 4, null));
        }
        List<FirTypeParameterRef> typeParameters2 = firCallableDeclaration.getTypeParameters();
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(typeParameters2, 10));
        Iterator<T> it2 = typeParameters2.iterator();
        while (it2.hasNext()) {
            arrayList2.add(((FirTypeParameterRef) it2.next()).getSymbol());
        }
        return ConeSubstitutorByMapKt.substitutorByMap$default(MapsKt.toMap(CollectionsKt.zip(arrayList2, arrayList)), firSession, false, 4, null);
    }
}
