package org.jetbrains.kotlin.fir.analysis.checkers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirOperation;
import org.jetbrains.kotlin.fir.expressions.FirTypeOperatorCall;
import org.jetbrains.kotlin.fir.expressions.ReferenceUtilsKt;
import org.jetbrains.kotlin.fir.resolve.LookupTagUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ScopeUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutorByMapKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeErrorType;
import org.jetbrains.kotlin.fir.types.ConeInferenceContext;
import org.jetbrains.kotlin.fir.types.ConeIntersectionType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeKotlinTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeStarProjection;
import org.jetbrains.kotlin.fir.types.ConeStubTypeForTypeVariableInSubtyping;
import org.jetbrains.kotlin.fir.types.ConeTypeParameterType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeProjectionKt;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeTypeVariable;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.fir.types.TypeUnificationKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.types.AbstractTypeChecker;
import org.jetbrains.kotlin.types.TypeCheckerState;
import org.jetbrains.kotlin.types.model.RigidTypeMarker;
import org.jetbrains.kotlin.types.model.TypeCheckerProviderContext;
import org.jetbrains.kotlin.types.model.TypeSystemContextHelpersKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a'\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005R\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0007\u001a'\u0010\b\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nR\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u000b\u001a\n\u0010\f\u001a\u00020\u0001*\u00020\u0005\u001a'\u0010\r\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0005R\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0007\u001a1\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0013H\u0000R\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0014\u001a)\u0010\u0015\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0005H\u0002R\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0007¨\u0006\u0016"}, d2 = {"isCastErased", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "supertype", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "subtype", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)Z", "findStaticallyKnownSubtype", "subTypeClassSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;)Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "isNonReifiedTypeParameter", "isUpcast", "candidateType", "targetType", "isRefinementUseless", "lhsType", "expression", "Lorg/jetbrains/kotlin/fir/expressions/FirTypeOperatorCall;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/expressions/FirTypeOperatorCall;)Z", "isExactTypeCast", "org.jetbrains.kotlin:checkers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirCastDiagnosticsHelpersKt {

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[FirOperation.values().length];
            try {
                iArr[FirOperation.AS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[FirOperation.SAFE_AS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[FirOperation.IS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[FirOperation.NOT_IS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final ConeKotlinType findStaticallyKnownSubtype(CheckerContext checkerContext, ConeKotlinType coneKotlinType, FirRegularClassSymbol firRegularClassSymbol) {
        Collection<ConeKotlinType> intersectedTypes;
        LinkedHashMap linkedHashMap;
        checkerContext.getClass();
        coneKotlinType.getClass();
        firRegularClassSymbol.getClass();
        ConeTypeUtilsKt.isMarkedNullable(coneKotlinType);
        if ((coneKotlinType instanceof ConeClassLikeType) && Intrinsics.areEqual(ToSymbolUtilsKt.toSymbol((SessionHolder) checkerContext, (ConeClassLikeType) coneKotlinType), firRegularClassSymbol)) {
            return coneKotlinType;
        }
        ConeClassLikeType coneClassLikeTypeDefaultType = ScopeUtilsKt.defaultType(firRegularClassSymbol);
        TypeCheckerState typeCheckerStateNewTypeCheckerState$default = TypeCheckerProviderContext.newTypeCheckerState$default(TypeComponentsKt.getTypeContext(checkerContext.getSession()), false, false, false, 4, (Object) null);
        if (coneKotlinType instanceof ConeIntersectionType) {
            intersectedTypes = ((ConeIntersectionType) coneKotlinType).getIntersectedTypes();
        } else {
            ArrayList arrayList = new ArrayList(1);
            arrayList.add(coneKotlinType);
            intersectedTypes = arrayList;
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        Iterator<ConeKotlinType> it = intersectedTypes.iterator();
        while (it.hasNext()) {
            ConeKotlinTypeProjection coneKotlinTypeProjection = (RigidTypeMarker) CollectionsKt.firstOrNull(AbstractTypeChecker.INSTANCE.findCorrespondingSupertypes(typeCheckerStateNewTypeCheckerState$default, coneClassLikeTypeDefaultType, TypeSystemContextHelpersKt.typeConstructor(it.next(), TypeComponentsKt.getTypeContext(checkerContext.getSession()))));
            List<FirTypeParameterSymbol> typeParameterSymbols = firRegularClassSymbol.getTypeParameterSymbols();
            if (coneKotlinTypeProjection != null) {
                linkedHashMap = new LinkedHashMap();
                if (!TypeUnificationKt.doUnify(checkerContext.getSession(), coneKotlinType, coneKotlinTypeProjection, CollectionsKt.toSet(typeParameterSymbols), linkedHashMap)) {
                    linkedHashMap = new LinkedHashMap();
                }
            } else {
                linkedHashMap = new LinkedHashMap();
            }
            for (FirTypeParameterSymbol firTypeParameterSymbol : typeParameterSymbols) {
                ConeTypeProjection coneTypeProjection = (ConeTypeProjection) linkedHashMap.get(firTypeParameterSymbol);
                ConeKotlinType coneStubTypeForTypeVariableInSubtyping = coneTypeProjection == null ? null : coneTypeProjection instanceof ConeStarProjection ? new ConeStubTypeForTypeVariableInSubtyping(new ConeTypeVariable(Argument.Delimiters.none, null), true) : ConeTypeProjectionKt.getType(coneTypeProjection);
                if (coneStubTypeForTypeVariableInSubtyping != null) {
                    linkedHashMap2.put(firTypeParameterSymbol, coneStubTypeForTypeVariableInSubtyping);
                }
            }
        }
        return ConeSubstitutorByMapKt.substitutorByMap$default(linkedHashMap2, checkerContext.getSession(), false, 4, null).substituteOrSelf(coneClassLikeTypeDefaultType);
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x003d, code lost:
    
        if (kotlin.jvm.internal.Intrinsics.areEqual(org.jetbrains.kotlin.fir.types.TypeUtilsKt.withNullability$default(r12, false, r3, null, false, 12, null), r13) != false) goto L12;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final boolean isCastErased(CheckerContext checkerContext, ConeKotlinType coneKotlinType, ConeKotlinType coneKotlinType2) {
        ConeInferenceContext coneInferenceContext;
        checkerContext.getClass();
        coneKotlinType.getClass();
        coneKotlinType2.getClass();
        ConeInferenceContext typeContext = TypeComponentsKt.getTypeContext(checkerContext.getSession());
        boolean zIsNonReifiedTypeParameter = isNonReifiedTypeParameter(coneKotlinType2);
        boolean zIsUpcast = isUpcast(checkerContext, coneKotlinType, coneKotlinType2);
        if (zIsNonReifiedTypeParameter && !zIsUpcast) {
            if (!TypeUtilsKt.canBeNull$default(coneKotlinType2, checkerContext.getSession(), false, null, 6, null)) {
                coneInferenceContext = typeContext;
            }
            return true;
        }
        coneInferenceContext = typeContext;
        if ((!(coneKotlinType instanceof ConeErrorType) && ConeTypeUtilsKt.isMarkedNullable(coneKotlinType)) || (!(coneKotlinType2 instanceof ConeErrorType) && ConeTypeUtilsKt.isMarkedNullable(coneKotlinType2))) {
            return isCastErased(checkerContext, TypeUtilsKt.withNullability$default(coneKotlinType, false, coneInferenceContext, null, false, 12, null), TypeUtilsKt.withNullability$default(coneKotlinType2, false, coneInferenceContext, null, false, 12, null));
        }
        if (zIsUpcast) {
            return false;
        }
        if (zIsNonReifiedTypeParameter) {
            return true;
        }
        if (coneKotlinType2 instanceof ConeTypeParameterType) {
            return false;
        }
        FirRegularClassSymbol regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(checkerContext, coneKotlinType2);
        if (regularClassSymbol == null) {
            return true;
        }
        Set<FirClassLikeSymbol<?>> classAndItsOuterClassesWhenLocal = LookupTagUtilsKt.getClassAndItsOuterClassesWhenLocal(regularClassSymbol, checkerContext.getSession());
        if (((FirClassLikeDeclaration) regularClassSymbol.getFir()).getIsLocal()) {
            List<FirTypeParameterSymbol> typeParameterSymbols = regularClassSymbol.getTypeParameterSymbols();
            if (!(typeParameterSymbols instanceof Collection) || !typeParameterSymbols.isEmpty()) {
                Iterator<T> it = typeParameterSymbols.iterator();
                while (it.hasNext()) {
                    if (!CollectionsKt.contains(classAndItsOuterClassesWhenLocal, ((FirTypeParameterSymbol) it.next()).getContainingDeclarationSymbol())) {
                        return true;
                    }
                }
            }
        }
        return !AbstractTypeChecker.isSubtypeOf$default(AbstractTypeChecker.INSTANCE, TypeComponentsKt.getTypeContext(checkerContext.getSession()).newTypeCheckerState(true, false, false), findStaticallyKnownSubtype(checkerContext, coneKotlinType, regularClassSymbol), coneKotlinType2, false, 8, (Object) null);
    }

    private static final boolean isExactTypeCast(CheckerContext checkerContext, ConeKotlinType coneKotlinType, ConeKotlinType coneKotlinType2) {
        return AbstractTypeChecker.equalTypes$default(AbstractTypeChecker.INSTANCE, TypeComponentsKt.getTypeContext(checkerContext.getSession()), coneKotlinType, coneKotlinType2, false, false, 16, (Object) null);
    }

    public static final boolean isNonReifiedTypeParameter(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return (coneKotlinType instanceof ConeTypeParameterType) && !((ConeTypeParameterType) coneKotlinType).getLookupTag().getTypeParameterSymbol().isReified();
    }

    public static final boolean isRefinementUseless(CheckerContext checkerContext, ConeKotlinType coneKotlinType, ConeKotlinType coneKotlinType2, FirTypeOperatorCall firTypeOperatorCall) {
        checkerContext.getClass();
        coneKotlinType.getClass();
        coneKotlinType2.getClass();
        firTypeOperatorCall.getClass();
        if ((coneKotlinType instanceof ConeErrorType) || (coneKotlinType2 instanceof ConeErrorType)) {
            return false;
        }
        FirExpression firExpression = (FirExpression) CollectionsKt.first(firTypeOperatorCall.getArgumentList().getArguments());
        int i = WhenMappings.$EnumSwitchMapping$0[firTypeOperatorCall.getOperation().ordinal()];
        if (i != 1 && i != 2) {
            if (i == 3 || i == 4) {
                return isUpcast(checkerContext, coneKotlinType, coneKotlinType2);
            }
            pe1.a("Should not be here: ", firTypeOperatorCall.getOperation());
            return false;
        }
        if (firExpression instanceof FirFunctionCall) {
            FirCallableSymbol<?> resolvedCallableSymbol = ReferenceUtilsKt.toResolvedCallableSymbol(firExpression, checkerContext.getSession());
            FirFunctionSymbol firFunctionSymbol = resolvedCallableSymbol instanceof FirFunctionSymbol ? (FirFunctionSymbol) resolvedCallableSymbol : null;
            if (firFunctionSymbol != null && FirHelpersKt.isFunctionForExpectTypeFromCastFeature(firFunctionSymbol)) {
                return false;
            }
        }
        return isExactTypeCast(checkerContext, coneKotlinType, (firTypeOperatorCall.getOperation() == FirOperation.SAFE_AS && ConeTypeUtilsKt.isMarkedOrFlexiblyNullable(coneKotlinType)) ? TypeUtilsKt.withNullability$default(coneKotlinType2, true, TypeComponentsKt.getTypeContext(checkerContext.getSession()), null, false, 12, null) : coneKotlinType2);
    }

    public static final boolean isUpcast(CheckerContext checkerContext, ConeKotlinType coneKotlinType, ConeKotlinType coneKotlinType2) {
        checkerContext.getClass();
        coneKotlinType.getClass();
        coneKotlinType2.getClass();
        return AbstractTypeChecker.INSTANCE.isSubtypeOf(TypeComponentsKt.getTypeContext(checkerContext.getSession()), coneKotlinType, coneKotlinType2, false);
    }
}
