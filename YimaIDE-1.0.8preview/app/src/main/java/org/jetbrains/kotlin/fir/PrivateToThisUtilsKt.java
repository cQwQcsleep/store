package org.jetbrains.kotlin.fir;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.expressions.ReferenceUtilsKt;
import org.jetbrains.kotlin.fir.references.FirReference;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.references.FirResolvedErrorReference;
import org.jetbrains.kotlin.fir.references.FirResolvedNamedReference;
import org.jetbrains.kotlin.fir.references.FirThisReference;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeVisibilityError;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbolKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeCapturedType;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeDefinitelyNotNullType;
import org.jetbrains.kotlin.fir.types.ConeFlexibleType;
import org.jetbrains.kotlin.fir.types.ConeIntegerConstantOperatorType;
import org.jetbrains.kotlin.fir.types.ConeIntegerLiteralConstantType;
import org.jetbrains.kotlin.fir.types.ConeIntersectionType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeLookupTagBasedType;
import org.jetbrains.kotlin.fir.types.ConeSimpleKotlinType;
import org.jetbrains.kotlin.fir.types.ConeStubTypeForTypeVariableInSubtyping;
import org.jetbrains.kotlin.fir.types.ConeTypeParameterType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeProjectionKt;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeTypeVariableType;
import org.jetbrains.kotlin.resolve.DataClassResolver;
import org.jetbrains.kotlin.types.EnrichedProjectionKind;
import org.jetbrains.kotlin.types.Variance;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a&\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0007\u001a:\u0010\b\u001a\u0016\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0007\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\t2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0007\u001a*\u0010\u000b\u001a\u00020\u00012\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\u00072\f\u0010\r\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u000e2\u0006\u0010\u0004\u001a\u00020\u0005H\u0002\u001a$\u0010\u000f\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00072\f\u0010\u0010\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u000e2\u0006\u0010\u0004\u001a\u00020\u0005\u001a\u001c\u0010\u0011\u001a\u00020\u0001*\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0004\u001a\u00020\u0005H\u0002¨\u0006\u0015"}, d2 = {"isPrivateToThisInvisibleAccess", Argument.Delimiters.none, "expression", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "symbolFromSmartCast", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "getSymbolAndQualifierIfInvisibleAccess", "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;", "isPrivateToThis", "symbol", "containingClassSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "isDataClassCopy", "containingClass", "contradictsWith", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "requiredVariance", "Lorg/jetbrains/kotlin/types/Variance;", "org.jetbrains.kotlin:semantics"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class PrivateToThisUtilsKt {

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[EnrichedProjectionKind.values().length];
            try {
                iArr[EnrichedProjectionKind.OUT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[EnrichedProjectionKind.IN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[EnrichedProjectionKind.INV.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[EnrichedProjectionKind.STAR.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final boolean contradictsWith(ConeKotlinType coneKotlinType, Variance variance, FirSession firSession) {
        FirClassLikeSymbol<?> symbol;
        FirClassLikeDeclaration firClassLikeDeclaration;
        Variance varianceOpposite;
        if (!(coneKotlinType instanceof ConeLookupTagBasedType)) {
            if (coneKotlinType instanceof ConeFlexibleType) {
                return contradictsWith(((ConeFlexibleType) coneKotlinType).getLowerBound(), variance, firSession);
            }
            if (coneKotlinType instanceof ConeDefinitelyNotNullType) {
                return contradictsWith(((ConeDefinitelyNotNullType) coneKotlinType).getOriginal(), variance, firSession);
            }
            if (!(coneKotlinType instanceof ConeIntersectionType)) {
                if (!(coneKotlinType instanceof ConeCapturedType) && !(coneKotlinType instanceof ConeIntegerConstantOperatorType) && !(coneKotlinType instanceof ConeIntegerLiteralConstantType) && !(coneKotlinType instanceof ConeStubTypeForTypeVariableInSubtyping) && !(coneKotlinType instanceof ConeTypeVariableType)) {
                    bu8.a();
                }
                return false;
            }
            Collection<ConeKotlinType> intersectedTypes = ((ConeIntersectionType) coneKotlinType).getIntersectedTypes();
            if ((intersectedTypes instanceof Collection) && intersectedTypes.isEmpty()) {
                return false;
            }
            Iterator<T> it = intersectedTypes.iterator();
            while (it.hasNext()) {
                if (contradictsWith((ConeKotlinType) it.next(), variance, firSession)) {
                    return true;
                }
            }
            return false;
        }
        if (coneKotlinType instanceof ConeTypeParameterType) {
            return !((ConeTypeParameterType) coneKotlinType).getLookupTag().getTypeParameterSymbol().getVariance().allowsPosition(variance);
        }
        if ((coneKotlinType instanceof ConeClassLikeType) && (symbol = ToSymbolUtilsKt.toSymbol(((ConeClassLikeType) coneKotlinType).getLookupTag(), firSession)) != null && (firClassLikeDeclaration = (FirClassLikeDeclaration) symbol.getFir()) != null) {
            ConeTypeProjection[] typeArguments = coneKotlinType.getTypeArguments();
            int length = typeArguments.length;
            for (int i = 0; i < length; i++) {
                ConeTypeProjection coneTypeProjection = typeArguments[i];
                FirTypeParameterRef firTypeParameterRef = (FirTypeParameterRef) CollectionsKt.getOrNull(firClassLikeDeclaration.getTypeParameters(), i);
                if (firTypeParameterRef instanceof FirTypeParameter) {
                    int i2 = WhenMappings.$EnumSwitchMapping$0[EnrichedProjectionKind.Companion.getEffectiveProjectionKind(((FirTypeParameter) firTypeParameterRef).getVariance(), ConeTypeProjectionKt.getVariance(coneTypeProjection)).ordinal()];
                    if (i2 == 1) {
                        varianceOpposite = variance;
                    } else if (i2 == 2) {
                        varianceOpposite = variance.opposite();
                    } else if (i2 == 3) {
                        varianceOpposite = Variance.INVARIANT;
                    } else if (i2 != 4) {
                        bu8.a();
                        return false;
                    }
                    ConeKotlinType type = ConeTypeProjectionKt.getType(coneTypeProjection);
                    if (type != null && contradictsWith(type, varianceOpposite, firSession)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /* JADX WARN: Code duplicated, block: B:15:0x0035  */
    public static final Pair<FirCallableSymbol<?>, FirResolvedQualifier> getSymbolAndQualifierIfInvisibleAccess(FirQualifiedAccessExpression firQualifiedAccessExpression, FirSession firSession, FirCallableSymbol<?> firCallableSymbol) {
        FirResolvedQualifier firResolvedQualifier;
        firQualifiedAccessExpression.getClass();
        firSession.getClass();
        FirResolvedNamedReference resolved = FirReferenceUtilsKt.getResolved(firQualifiedAccessExpression.getCalleeReference());
        if (resolved == null) {
            return null;
        }
        if ((resolved instanceof FirResolvedErrorReference) && (((FirResolvedErrorReference) resolved).getDiagnostic() instanceof ConeVisibilityError)) {
            return null;
        }
        FirExpression dispatchReceiver = firQualifiedAccessExpression.getDispatchReceiver();
        if (firQualifiedAccessExpression instanceof FirCallableReferenceAccess) {
            FirExpression explicitReceiver = firQualifiedAccessExpression.getExplicitReceiver();
            if (explicitReceiver instanceof FirResolvedQualifier) {
                firResolvedQualifier = (FirResolvedQualifier) explicitReceiver;
            } else {
                firResolvedQualifier = null;
            }
        } else {
            firResolvedQualifier = null;
        }
        if (dispatchReceiver == null && firResolvedQualifier == null) {
            return null;
        }
        if ((firCallableSymbol == null && (firCallableSymbol = FirReferenceUtilsKt.toResolvedCallableSymbol(resolved, true)) == null) || !Intrinsics.areEqual(firCallableSymbol.getResolvedStatus().getVisibility(), Visibilities.Private.INSTANCE)) {
            return null;
        }
        ConeClassLikeLookupTag coneClassLikeLookupTagContainingClassLookupTag = ClassMembersKt.containingClassLookupTag(firCallableSymbol);
        FirClassSymbol<?> classSymbol = coneClassLikeLookupTagContainingClassLookupTag != null ? ToSymbolUtilsKt.toClassSymbol(coneClassLikeLookupTagContainingClassLookupTag, firSession) : null;
        FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) firCallableSymbol.getFir();
        while (true) {
            FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
            if (originalForSubstitutionOverrideAttr == null) {
                originalForSubstitutionOverrideAttr = ClassMembersKt.isIntersectionOverride(firCallableDeclaration) ? ClassMembersKt.getOriginalForIntersectionOverrideAttr(firCallableDeclaration) : null;
            }
            if (originalForSubstitutionOverrideAttr == null) {
                break;
            }
            firCallableDeclaration = originalForSubstitutionOverrideAttr;
        }
        FirCallableSymbol<FirCallableDeclaration> symbol = firCallableDeclaration.getSymbol();
        if (symbol == null) {
            x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol<org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration>");
            return null;
        }
        if (!isPrivateToThis(symbol, classSymbol, firSession)) {
            return null;
        }
        FirReference reference = dispatchReceiver != null ? ReferenceUtilsKt.toReference(dispatchReceiver, firSession) : null;
        if ((reference instanceof FirThisReference) && Intrinsics.areEqual(((FirThisReference) reference).getBoundSymbol(), classSymbol)) {
            return null;
        }
        return TuplesKt.to(firCallableSymbol, firResolvedQualifier);
    }

    public static /* synthetic */ Pair getSymbolAndQualifierIfInvisibleAccess$default(FirQualifiedAccessExpression firQualifiedAccessExpression, FirSession firSession, FirCallableSymbol firCallableSymbol, int i, Object obj) {
        if ((i & 4) != 0) {
            firCallableSymbol = null;
        }
        return getSymbolAndQualifierIfInvisibleAccess(firQualifiedAccessExpression, firSession, firCallableSymbol);
    }

    public static final boolean isDataClassCopy(FirCallableSymbol<?> firCallableSymbol, FirClassSymbol<?> firClassSymbol, FirSession firSession) {
        firCallableSymbol.getClass();
        firSession.getClass();
        FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) firCallableSymbol.getFir();
        while (true) {
            FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
            if (originalForSubstitutionOverrideAttr == null) {
                break;
            }
            firCallableDeclaration = originalForSubstitutionOverrideAttr;
        }
        FirCallableSymbol<FirCallableDeclaration> symbol = firCallableDeclaration.getSymbol();
        if (symbol == null) {
            x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol<*>");
            return false;
        }
        FirConstructorSymbol firConstructorSymbolPrimaryConstructorIfAny = firClassSymbol != null ? DeclarationUtilsKt.primaryConstructorIfAny(firClassSymbol, firSession) : null;
        if (symbol instanceof FirNamedFunctionSymbol) {
            FirNamedFunctionSymbol firNamedFunctionSymbol = (FirNamedFunctionSymbol) symbol;
            if (DataClassResolver.INSTANCE.isCopy(firNamedFunctionSymbol.getName()) && firClassSymbol != null && firClassSymbol.getRawStatus().isData() && firClassSymbol.getClassKind() == ClassKind.CLASS) {
                ConeSimpleKotlinType dispatchReceiverType = firNamedFunctionSymbol.getDispatchReceiverType();
                if (Intrinsics.areEqual(dispatchReceiverType != null ? ConeTypeUtilsKt.getClassId(dispatchReceiverType) : null, firClassSymbol.getClassId()) && Intrinsics.areEqual(ConeTypeUtilsKt.getClassId(firNamedFunctionSymbol.getResolvedReturnType()), firClassSymbol.getClassId()) && firConstructorSymbolPrimaryConstructorIfAny != null && !FirCallableSymbolKt.getHasContextParameters(symbol) && firNamedFunctionSymbol.getTypeParameterSymbols().isEmpty() && firNamedFunctionSymbol.getReceiverParameterSymbol() == null) {
                    List<FirValueParameterSymbol> valueParameterSymbols = firNamedFunctionSymbol.getValueParameterSymbols();
                    ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(valueParameterSymbols, 10));
                    for (FirValueParameterSymbol firValueParameterSymbol : valueParameterSymbols) {
                        arrayList.add(TuplesKt.to(Boolean.valueOf(firValueParameterSymbol.isVararg()), firValueParameterSymbol.getResolvedReturnType()));
                    }
                    List<FirValueParameterSymbol> valueParameterSymbols2 = firConstructorSymbolPrimaryConstructorIfAny.getValueParameterSymbols();
                    ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(valueParameterSymbols2, 10));
                    for (FirValueParameterSymbol firValueParameterSymbol2 : valueParameterSymbols2) {
                        arrayList2.add(TuplesKt.to(Boolean.valueOf(firValueParameterSymbol2.isVararg()), firValueParameterSymbol2.getResolvedReturnType()));
                    }
                    if (Intrinsics.areEqual(arrayList, arrayList2)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static final boolean isPrivateToThis(FirCallableSymbol<?> firCallableSymbol, FirClassSymbol<?> firClassSymbol, FirSession firSession) {
        if (firClassSymbol == null || (firCallableSymbol instanceof FirConstructorSymbol)) {
            return false;
        }
        List<FirTypeParameterSymbol> typeParameterSymbols = firClassSymbol.getTypeParameterSymbols();
        if (!(typeParameterSymbols instanceof Collection) || !typeParameterSymbols.isEmpty()) {
            Iterator<T> it = typeParameterSymbols.iterator();
            while (it.hasNext()) {
                Variance variance = ((FirTypeParameterSymbol) it.next()).getVariance();
                Variance variance2 = Variance.INVARIANT;
                if (variance != variance2) {
                    if (isDataClassCopy(firCallableSymbol, firClassSymbol, firSession)) {
                        return false;
                    }
                    ConeKotlinType resolvedReceiverType = firCallableSymbol.getResolvedReceiverType();
                    if (resolvedReceiverType != null && contradictsWith(resolvedReceiverType, Variance.IN_VARIANCE, firSession)) {
                        return true;
                    }
                    ConeKotlinType resolvedReturnType = firCallableSymbol.getResolvedReturnType();
                    if (!(firCallableSymbol instanceof FirPropertySymbol) || !((FirPropertySymbol) firCallableSymbol).isVar()) {
                        variance2 = Variance.OUT_VARIANCE;
                    }
                    if (!contradictsWith(resolvedReturnType, variance2, firSession)) {
                        if (firCallableSymbol instanceof FirFunctionSymbol) {
                            Iterator<FirValueParameterSymbol> it2 = ((FirFunctionSymbol) firCallableSymbol).getValueParameterSymbols().iterator();
                            while (it2.hasNext()) {
                                if (contradictsWith(it2.next().getResolvedReturnType(), Variance.IN_VARIANCE, firSession)) {
                                    return true;
                                }
                            }
                        }
                        Iterator<FirValueParameterSymbol> it3 = firCallableSymbol.getContextParameterSymbols().iterator();
                        while (it3.hasNext()) {
                            if (contradictsWith(it3.next().getResolvedReturnType(), Variance.IN_VARIANCE, firSession)) {
                                return true;
                            }
                        }
                        break;
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public static final boolean isPrivateToThisInvisibleAccess(FirQualifiedAccessExpression firQualifiedAccessExpression, FirSession firSession, FirCallableSymbol<?> firCallableSymbol) {
        firQualifiedAccessExpression.getClass();
        firSession.getClass();
        return getSymbolAndQualifierIfInvisibleAccess(firQualifiedAccessExpression, firSession, firCallableSymbol) != null;
    }

    public static /* synthetic */ boolean isPrivateToThisInvisibleAccess$default(FirQualifiedAccessExpression firQualifiedAccessExpression, FirSession firSession, FirCallableSymbol firCallableSymbol, int i, Object obj) {
        if ((i & 4) != 0) {
            firCallableSymbol = null;
        }
        return isPrivateToThisInvisibleAccess(firQualifiedAccessExpression, firSession, firCallableSymbol);
    }
}
