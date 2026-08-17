package org.jetbrains.kotlin.fir;

import defpackage.dwe;
import defpackage.ewe;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.symbols.ConeTypeParameterLookupTag;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeCapturedType;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeDefinitelyNotNullType;
import org.jetbrains.kotlin.fir.types.ConeErrorType;
import org.jetbrains.kotlin.fir.types.ConeFlexibleType;
import org.jetbrains.kotlin.fir.types.ConeIntegerConstantOperatorType;
import org.jetbrains.kotlin.fir.types.ConeIntegerLiteralConstantType;
import org.jetbrains.kotlin.fir.types.ConeIntegerLiteralType;
import org.jetbrains.kotlin.fir.types.ConeIntersectionType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeLookupTagBasedType;
import org.jetbrains.kotlin.fir.types.ConeStubType;
import org.jetbrains.kotlin.fir.types.ConeTypeContext;
import org.jetbrains.kotlin.fir.types.ConeTypeParameterType;
import org.jetbrains.kotlin.fir.types.ConeTypeVariableType;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.types.model.TypeParameterMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001a\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\u0006"}, d2 = {"collectUpperBounds", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "typeContext", "Lorg/jetbrains/kotlin/fir/types/ConeTypeContext;", "org.jetbrains.kotlin:semantics"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class TypeUtilsKt {
    public static final Set<ConeClassLikeType> collectUpperBounds(ConeKotlinType coneKotlinType, ConeTypeContext coneTypeContext) {
        coneTypeContext.getClass();
        if (coneKotlinType == null) {
            return SetsKt.emptySet();
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        collectUpperBounds$collect(new LinkedHashSet(), linkedHashSet, coneKotlinType, coneTypeContext, coneKotlinType);
        return linkedHashSet;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final void collectUpperBounds$collect(Set<ConeKotlinType> set, Set<ConeClassLikeType> set2, ConeKotlinType coneKotlinType, ConeTypeContext coneTypeContext, ConeKotlinType coneKotlinType2) {
        FirTypeParameterSymbol typeParameterSymbol;
        if (set.add(coneKotlinType2) && !(coneKotlinType2 instanceof ConeErrorType)) {
            if (coneKotlinType2 instanceof ConeLookupTagBasedType) {
                ConeLookupTagBasedType coneLookupTagBasedType = (ConeLookupTagBasedType) coneKotlinType2;
                if (coneLookupTagBasedType instanceof ConeClassLikeType) {
                    set2.add(coneKotlinType2);
                    return;
                }
                if (!(coneLookupTagBasedType instanceof ConeTypeParameterType)) {
                    dwe.a("missing branch for ".concat(coneKotlinType.getClass().getName()));
                    return;
                }
                ConeTypeParameterType coneTypeParameterType = (ConeTypeParameterType) coneKotlinType2;
                for (FirResolvedTypeRef firResolvedTypeRef : coneTypeParameterType.getLookupTag().getTypeParameterSymbol().getResolvedBounds()) {
                    ConeTypeContext coneTypeContext2 = coneTypeContext;
                    collectUpperBounds$collect(set, set2, coneKotlinType, coneTypeContext2, org.jetbrains.kotlin.fir.types.TypeUtilsKt.withNullability$default(firResolvedTypeRef.getConeType(), coneTypeContext.isNullableType(firResolvedTypeRef.getConeType()) || coneTypeParameterType.getIsMarkedNullable(), coneTypeContext2, null, false, 12, null));
                    coneTypeContext = coneTypeContext2;
                }
                return;
            }
            if (coneKotlinType2 instanceof ConeTypeVariableType) {
                TypeParameterMarker originalTypeParameter = ((ConeTypeVariableType) coneKotlinType2).getTypeConstructor().getOriginalTypeParameter();
                ConeTypeParameterLookupTag coneTypeParameterLookupTag = originalTypeParameter instanceof ConeTypeParameterLookupTag ? (ConeTypeParameterLookupTag) originalTypeParameter : null;
                if (coneTypeParameterLookupTag == null || (typeParameterSymbol = coneTypeParameterLookupTag.getTypeParameterSymbol()) == null) {
                    return;
                }
                Iterator<T> it = typeParameterSymbol.getResolvedBounds().iterator();
                while (it.hasNext()) {
                    collectUpperBounds$collect(set, set2, coneKotlinType, coneTypeContext, ((FirResolvedTypeRef) it.next()).getConeType());
                }
                return;
            }
            if (coneKotlinType2 instanceof ConeDefinitelyNotNullType) {
                collectUpperBounds$collect(set, set2, coneKotlinType, coneTypeContext, ((ConeDefinitelyNotNullType) coneKotlinType2).getOriginal());
                return;
            }
            if (coneKotlinType2 instanceof ConeIntersectionType) {
                Iterator<T> it2 = ((ConeIntersectionType) coneKotlinType2).getIntersectedTypes().iterator();
                while (it2.hasNext()) {
                    collectUpperBounds$collect(set, set2, coneKotlinType, coneTypeContext, (ConeKotlinType) it2.next());
                }
                return;
            }
            if (coneKotlinType2 instanceof ConeFlexibleType) {
                collectUpperBounds$collect(set, set2, coneKotlinType, coneTypeContext, ((ConeFlexibleType) coneKotlinType2).getUpperBound());
                return;
            }
            if (coneKotlinType2 instanceof ConeCapturedType) {
                List<ConeKotlinType> supertypes = ((ConeCapturedType) coneKotlinType2).getConstructor().getSupertypes();
                if (supertypes != null) {
                    Iterator<T> it3 = supertypes.iterator();
                    while (it3.hasNext()) {
                        collectUpperBounds$collect(set, set2, coneKotlinType, coneTypeContext, (ConeKotlinType) it3.next());
                    }
                    return;
                }
                return;
            }
            if (coneKotlinType2 instanceof ConeIntegerConstantOperatorType) {
                set2.add(ConeIntegerLiteralType.getApproximatedType$default((ConeIntegerLiteralType) coneKotlinType2, null, 1, null));
            } else if ((coneKotlinType2 instanceof ConeStubType) || (coneKotlinType2 instanceof ConeIntegerLiteralConstantType)) {
                ewe.a(coneKotlinType2, " should not reach here");
            } else {
                bu8.a();
            }
        }
    }
}
