package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutorByMapKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeDefinitelyNotNullType;
import org.jetbrains.kotlin.fir.types.ConeFlexibleType;
import org.jetbrains.kotlin.fir.types.ConeIntersectionType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeKotlinTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeProjectionKt;
import org.jetbrains.kotlin.utils.SmartSet;
import org.jetbrains.kotlin.utils.addToStdlib.AddToStdlibKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0002H\u0002\u001a,\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00062\u0006\u0010\n\u001a\u00020\u000bH\u0002¨\u0006\f"}, d2 = {"constituentTypes", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "substitutorByType", "Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "parameters", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeParameterSymbol;", "arguments", "Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "org.jetbrains.kotlin:checkers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirNonExpansiveInheritanceRestrictionCheckerKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Set<ConeKotlinType> constituentTypes(ConeKotlinType coneKotlinType) {
        SmartSet smartSetCreate = SmartSet.Companion.create();
        List listMutableListOf = CollectionsKt.mutableListOf(new ConeKotlinType[]{coneKotlinType});
        while (!listMutableListOf.isEmpty()) {
            ConeKotlinType coneKotlinType2 = (ConeKotlinType) AddToStdlibKt.popLast(listMutableListOf);
            smartSetCreate.add(coneKotlinType2);
            if (coneKotlinType2 instanceof ConeFlexibleType) {
                ConeFlexibleType coneFlexibleType = (ConeFlexibleType) coneKotlinType2;
                listMutableListOf.add(coneFlexibleType.getLowerBound());
                if (!coneFlexibleType.getIsTrivial()) {
                    listMutableListOf.add(coneFlexibleType.getUpperBound());
                }
            } else if (coneKotlinType2 instanceof ConeDefinitelyNotNullType) {
                listMutableListOf.add(((ConeDefinitelyNotNullType) coneKotlinType2).getOriginal());
            } else if (coneKotlinType2 instanceof ConeIntersectionType) {
                listMutableListOf.addAll(((ConeIntersectionType) coneKotlinType2).getIntersectedTypes());
            } else {
                for (ConeKotlinTypeProjection coneKotlinTypeProjection : coneKotlinType2.getTypeArguments()) {
                    if (coneKotlinTypeProjection instanceof ConeKotlinTypeProjection) {
                        listMutableListOf.add(coneKotlinTypeProjection.getType());
                    }
                }
            }
        }
        return smartSetCreate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ConeSubstitutor substitutorByType(List<FirTypeParameterSymbol> list, List<? extends ConeTypeProjection> list2, FirSession firSession) {
        if (list.size() != list2.size()) {
            w01.a("Failed requirement.");
            return null;
        }
        Map mapCreateMapBuilder = MapsKt.createMapBuilder();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            ConeKotlinType type = ConeTypeProjectionKt.getType(list2.get(i));
            if (type != null) {
                mapCreateMapBuilder.put(list.get(i), type);
            }
        }
        return ConeSubstitutorByMapKt.substitutorByMap$default(MapsKt.build(mapCreateMapBuilder), firSession, false, 4, null);
    }
}
