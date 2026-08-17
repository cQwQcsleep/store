package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutorByMapKt;
import org.jetbrains.kotlin.fir.scopes.impl.FirNestedClassifierScopeKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeErrorType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.types.AbstractTypeChecker;
import org.jetbrains.kotlin.types.TypeCheckerState;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J5\u0010\b\u001a\u00020\t*\u00020\t2\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\r2\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\rH\u0002R\u00020\nj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u000fJA\u0010\u0010\u001a\b\u0012\u0002\b\u0003\u0018\u00010\r*\u0006\u0012\u0002\b\u00030\r2\u0010\u0010\u0011\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\r0\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0004R\u00020\nj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u0015¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirAbstractOverrideChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirClassChecker;", "mppKind", "Lorg/jetbrains/kotlin/fir/analysis/checkers/MppCheckerKind;", "<init>", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/MppCheckerKind;)V", "substituteAllTypeParameters", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "overrideDeclaration", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "baseDeclaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;)Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "checkReturnType", "overriddenSymbols", Argument.Delimiters.none, "typeCheckerState", "Lorg/jetbrains/kotlin/types/TypeCheckerState;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;Ljava/util/List;Lorg/jetbrains/kotlin/types/TypeCheckerState;)Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirAbstractOverrideChecker extends FirDeclarationChecker<FirClass> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirAbstractOverrideChecker(MppCheckerKind mppCheckerKind) {
        super(mppCheckerKind);
        mppCheckerKind.getClass();
    }

    private final ConeKotlinType substituteAllTypeParameters(CheckerContext checkerContext, ConeKotlinType coneKotlinType, FirCallableSymbol<?> firCallableSymbol, FirCallableSymbol<?> firCallableSymbol2) {
        List<FirTypeParameterSymbol> typeParameterSymbols = firCallableSymbol.getTypeParameterSymbols();
        if (typeParameterSymbols.isEmpty()) {
            return coneKotlinType;
        }
        List<FirTypeParameterSymbol> typeParameterSymbols2 = firCallableSymbol2.getTypeParameterSymbols();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int iMin = Math.min(typeParameterSymbols.size(), typeParameterSymbols2.size());
        for (int i = 0; i < iMin; i++) {
            linkedHashMap.put(typeParameterSymbols2.get(i), FirNestedClassifierScopeKt.toConeType(typeParameterSymbols.get(i)));
        }
        return ConeSubstitutorByMapKt.substitutorByMap$default(linkedHashMap, checkerContext.getSession(), false, 4, null).substituteOrSelf(coneKotlinType);
    }

    public final FirCallableSymbol<?> checkReturnType(CheckerContext checkerContext, FirCallableSymbol<?> firCallableSymbol, List<? extends FirCallableSymbol<?>> list, TypeCheckerState typeCheckerState) {
        checkerContext.getClass();
        firCallableSymbol.getClass();
        list.getClass();
        typeCheckerState.getClass();
        ConeKotlinType coneType = firCallableSymbol.getResolvedReturnTypeRef().getConeType();
        if (coneType instanceof ConeErrorType) {
            return null;
        }
        List<? extends FirCallableSymbol<?>> list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        Iterator<T> it = list2.iterator();
        while (it.hasNext()) {
            arrayList.add(checkerContext.getReturnTypeCalculator().tryCalculateReturnType((FirCallableSymbol<?>) it.next()).getConeType());
        }
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            FirCallableSymbol<?> firCallableSymbol2 = list.get(i);
            ConeKotlinType coneKotlinTypeSubstituteAllTypeParameters = substituteAllTypeParameters(checkerContext, (ConeKotlinType) arrayList.get(i), firCallableSymbol, firCallableSymbol2);
            if (!(((firCallableSymbol2 instanceof FirPropertySymbol) && ((FirPropertySymbol) firCallableSymbol2).isVar()) ? AbstractTypeChecker.INSTANCE.equalTypes(typeCheckerState, coneType, coneKotlinTypeSubstituteAllTypeParameters) : AbstractTypeChecker.isSubtypeOf$default(AbstractTypeChecker.INSTANCE, typeCheckerState, coneType, coneKotlinTypeSubstituteAllTypeParameters, false, 8, (Object) null))) {
                return firCallableSymbol2;
            }
        }
        return null;
    }
}
