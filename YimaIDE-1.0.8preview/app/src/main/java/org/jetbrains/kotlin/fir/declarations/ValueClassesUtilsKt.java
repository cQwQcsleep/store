package org.jetbrains.kotlin.fir.declarations;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.InlineClassRepresentation;
import org.jetbrains.kotlin.descriptors.ValueClassRepresentation;
import org.jetbrains.kotlin.descriptors.ValueClassRepresentationKt;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.resolve.ScopeUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolverKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeRigidType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.util.OperatorNameConventions;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000F\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010#\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0016\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000\u001a\u001e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u001c\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n*\u00020\b2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002\u001a&\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0010H\u0002\u001a(\u0010\u0011\u001a\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00060\u0012\u0018\u00010\n*\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u0003H\u0002\u001a\u0012\u0010\u0014\u001a\u00020\r*\u00020\u00152\u0006\u0010\u0002\u001a\u00020\u0003¨\u0006\u0016"}, d2 = {"unsubstitutedUnderlyingTypeForInlineClass", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "computeValueClassRepresentation", "Lorg/jetbrains/kotlin/descriptors/ValueClassRepresentation;", "Lorg/jetbrains/kotlin/fir/types/ConeRigidType;", "klass", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "getValueClassUnderlyingParameters", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "isRecursiveSingleFieldValueClass", Argument.Delimiters.none, ModuleXmlParser.TYPE, "visited", Argument.Delimiters.none, "valueClassRepresentationTypeMarkersList", "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/name/Name;", "isTypedEqualsInValueClass", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "org.jetbrains.kotlin:providers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ValueClassesUtilsKt {
    public static final ValueClassRepresentation<ConeRigidType> computeValueClassRepresentation(FirRegularClass firRegularClass, FirSession firSession) {
        firRegularClass.getClass();
        firSession.getClass();
        List<FirValueParameter> valueClassUnderlyingParameters = getValueClassUnderlyingParameters(firRegularClass, firSession);
        if (valueClassUnderlyingParameters != null) {
            if (valueClassUnderlyingParameters.isEmpty()) {
                valueClassUnderlyingParameters = null;
            }
            if (valueClassUnderlyingParameters != null) {
                List<FirValueParameter> list = valueClassUnderlyingParameters;
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                for (FirValueParameter firValueParameter : list) {
                    Name name = firValueParameter.getName();
                    ConeKotlinType resolvedReturnType = firValueParameter.getSymbol().getResolvedReturnType();
                    resolvedReturnType.getClass();
                    arrayList.add(TuplesKt.to(name, (ConeRigidType) resolvedReturnType));
                }
                Pair pair = (Pair) CollectionsKt.singleOrNull(arrayList);
                if (pair != null) {
                    Name name2 = (Name) pair.component1();
                    ConeRigidType coneRigidType = (ConeRigidType) pair.component2();
                    if (isRecursiveSingleFieldValueClass(coneRigidType, firSession, SetsKt.mutableSetOf(new ConeRigidType[]{coneRigidType}))) {
                        return new InlineClassRepresentation(name2, coneRigidType);
                    }
                }
                return ValueClassRepresentationKt.createValueClassRepresentation(TypeComponentsKt.getTypeContext(firSession), arrayList);
            }
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final List<FirValueParameter> getValueClassUnderlyingParameters(FirRegularClass firRegularClass, FirSession firSession) {
        FirConstructorSymbol firConstructorSymbolPrimaryConstructorIfAny;
        FirConstructor firConstructor;
        if ((!firRegularClass.getStatus().isInline() && !firRegularClass.getStatus().isValue()) || (firConstructorSymbolPrimaryConstructorIfAny = DeclarationUtilsKt.primaryConstructorIfAny(firRegularClass, firSession)) == null || (firConstructor = (FirConstructor) firConstructorSymbolPrimaryConstructorIfAny.getFir()) == null) {
            return null;
        }
        return firConstructor.getValueParameters();
    }

    private static final boolean isRecursiveSingleFieldValueClass(ConeRigidType coneRigidType, FirSession firSession, Set<ConeRigidType> set) {
        Pair pair;
        ConeRigidType coneRigidType2;
        List<Pair<Name, ConeRigidType>> listValueClassRepresentationTypeMarkersList = valueClassRepresentationTypeMarkersList(coneRigidType, firSession);
        if (listValueClassRepresentationTypeMarkersList == null || (pair = (Pair) CollectionsKt.singleOrNull(listValueClassRepresentationTypeMarkersList)) == null || (coneRigidType2 = (ConeRigidType) pair.getSecond()) == null) {
            return false;
        }
        return !set.add(coneRigidType2) || isRecursiveSingleFieldValueClass(coneRigidType2, firSession, set);
    }

    public static final boolean isTypedEqualsInValueClass(FirNamedFunctionSymbol firNamedFunctionSymbol, FirSession firSession) {
        FirRegularClassSymbol regularClassSymbol;
        firNamedFunctionSymbol.getClass();
        firSession.getClass();
        ConeClassLikeLookupTag coneClassLikeLookupTagContainingClassLookupTag = ClassMembersKt.containingClassLookupTag(firNamedFunctionSymbol);
        if (coneClassLikeLookupTagContainingClassLookupTag != null && (regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(coneClassLikeLookupTagContainingClassLookupTag, firSession)) != null) {
            ConeClassLikeType coneClassLikeTypeReplaceArgumentsWithStarProjections = ConeTypeUtilsKt.replaceArgumentsWithStarProjections(ScopeUtilsKt.defaultType(regularClassSymbol));
            if (firNamedFunctionSymbol.getContextParameterSymbols().isEmpty() && firNamedFunctionSymbol.getReceiverParameterSymbol() == null && Intrinsics.areEqual(firNamedFunctionSymbol.getName(), OperatorNameConventions.EQUALS) && ((regularClassSymbol.getRawStatus().isInline() || regularClassSymbol.getRawStatus().isValue()) && firNamedFunctionSymbol.getValueParameterSymbols().size() == 1)) {
                ConeKotlinType coneKotlinTypeFullyExpandedType$default = TypeExpansionUtilsKt.fullyExpandedType$default(firNamedFunctionSymbol.getResolvedReturnTypeRef().getConeType(), firSession, (Function1) null, 2, (Object) null);
                if (ConeBuiltinTypeUtilsKt.isBoolean(coneKotlinTypeFullyExpandedType$default) || ConeBuiltinTypeUtilsKt.isNothing(coneKotlinTypeFullyExpandedType$default)) {
                    ConeKotlinType coneType = firNamedFunctionSymbol.getValueParameterSymbols().get(0).getResolvedReturnTypeRef().getConeType();
                    if ((coneType instanceof ConeClassLikeType) && Intrinsics.areEqual(ConeTypeUtilsKt.replaceArgumentsWithStarProjections((ConeClassLikeType) coneType), coneClassLikeTypeReplaceArgumentsWithStarProjections)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final ConeKotlinType unsubstitutedUnderlyingTypeForInlineClass(ConeKotlinType coneKotlinType, FirSession firSession) {
        coneKotlinType.getClass();
        firSession.getClass();
        FirRegularClassSymbol regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(TypeExpansionUtilsKt.fullyExpandedType$default(coneKotlinType, firSession, (Function1) null, 2, (Object) null), firSession);
        if (regularClassSymbol == null) {
            return null;
        }
        FirLazyDeclarationResolverKt.lazyResolveToPhase(regularClassSymbol, FirResolvePhase.STATUS);
        InlineClassRepresentation<ConeRigidType> inlineClassRepresentation = FirValueClassRepresentationKt.getInlineClassRepresentation((FirRegularClass) regularClassSymbol.getFir());
        if (inlineClassRepresentation != null) {
            return (ConeRigidType) inlineClassRepresentation.getUnderlyingType();
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final List<Pair<Name, ConeRigidType>> valueClassRepresentationTypeMarkersList(ConeRigidType coneRigidType, FirSession firSession) {
        FirRegularClassSymbol regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(coneRigidType, firSession);
        if (regularClassSymbol == null) {
            return null;
        }
        FirClass firClass = (FirClass) regularClassSymbol.getFir();
        if (!firClass.getStatus().isInline() && !firClass.getStatus().isValue()) {
            return null;
        }
        ValueClassRepresentation<ConeRigidType> valueClassRepresentation = FirValueClassRepresentationKt.getValueClassRepresentation((FirRegularClass) regularClassSymbol.getFir());
        if (valueClassRepresentation != null) {
            return valueClassRepresentation.getUnderlyingPropertyNamesToTypes();
        }
        FirConstructorSymbol firConstructorSymbolPrimaryConstructorIfAny = DeclarationUtilsKt.primaryConstructorIfAny((FirClass) regularClassSymbol.getFir(), firSession);
        if (firConstructorSymbolPrimaryConstructorIfAny == null) {
            return null;
        }
        List<FirValueParameterSymbol> valueParameterSymbols = firConstructorSymbolPrimaryConstructorIfAny.getValueParameterSymbols();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(valueParameterSymbols, 10));
        for (FirValueParameterSymbol firValueParameterSymbol : valueParameterSymbols) {
            Name name = firValueParameterSymbol.getName();
            ConeKotlinType resolvedReturnType = firValueParameterSymbol.getResolvedReturnType();
            resolvedReturnType.getClass();
            arrayList.add(TuplesKt.to(name, (ConeRigidType) resolvedReturnType));
        }
        return arrayList;
    }
}
