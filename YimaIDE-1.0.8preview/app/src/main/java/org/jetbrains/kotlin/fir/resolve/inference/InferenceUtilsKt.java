package org.jetbrains.kotlin.fir.resolve.inference;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.builtins.functions.FunctionTypeKind;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.diagnostics.ConeCannotInferValueParameterType;
import org.jetbrains.kotlin.fir.expressions.FirAnonymousFunctionExpression;
import org.jetbrains.kotlin.fir.resolve.BodyResolveComponents;
import org.jetbrains.kotlin.fir.resolve.TypeAttributeUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.resolve.calls.ConeResolvedLambdaAtom;
import org.jetbrains.kotlin.fir.types.CompilerConeAttributesKt;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeErrorType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeRigidType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirFunctionTypeKindServiceKt;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FunctionalTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001aF\u0010\u0000\u001a\u0004\u0018\u00010\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f¨\u0006\u0010"}, d2 = {"extractLambdaInfoFromFunctionType", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolvedLambdaAtom;", "expectedType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "argument", "Lorg/jetbrains/kotlin/fir/expressions/FirAnonymousFunctionExpression;", "lambda", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "returnTypeVariable", "Lorg/jetbrains/kotlin/fir/resolve/inference/ConeTypeVariableForLambdaReturnType;", "components", "Lorg/jetbrains/kotlin/fir/resolve/BodyResolveComponents;", "allowCoercionToExtensionReceiver", Argument.Delimiters.none, "sourceForFunctionExpression", "Lorg/jetbrains/kotlin/KtSourceElement;", "org.jetbrains.kotlin:resolve"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class InferenceUtilsKt {
    /* JADX WARN: Code duplicated, block: B:118:0x01e8  */
    /* JADX WARN: Code duplicated, block: B:119:0x01f0  */
    /* JADX WARN: Code duplicated, block: B:76:0x011d  */
    /* JADX WARN: Code duplicated, block: B:80:0x0125  */
    public static final ConeResolvedLambdaAtom extractLambdaInfoFromFunctionType(ConeKotlinType coneKotlinType, FirAnonymousFunctionExpression firAnonymousFunctionExpression, FirAnonymousFunction firAnonymousFunction, ConeTypeVariableForLambdaReturnType coneTypeVariableForLambdaReturnType, BodyResolveComponents bodyResolveComponents, boolean z, KtSourceElement ktSourceElement) {
        FunctionTypeKind.Function functionFunctionTypeKind$default;
        boolean z2;
        boolean z3;
        ArrayList arrayList;
        ConeKotlinType coneType;
        FirResolvedTypeRef returnTypeRef;
        List<ConeKotlinType> list;
        List<ConeKotlinType> listSubList;
        FunctionTypeKind.Function function;
        ConeKotlinType coneKotlinTypeFullyExpandedType$default;
        firAnonymousFunctionExpression.getClass();
        firAnonymousFunction.getClass();
        bodyResolveComponents.getClass();
        FirSession session = bodyResolveComponents.getSession();
        ConeRigidType coneRigidTypeLowerBoundIfFlexible = (coneKotlinType == null || (coneKotlinTypeFullyExpandedType$default = TypeExpansionUtilsKt.fullyExpandedType$default(coneKotlinType, session, (Function1) null, 2, (Object) null)) == null) ? null : ConeTypeUtilsKt.lowerBoundIfFlexible(coneKotlinTypeFullyExpandedType$default);
        ConeClassLikeType coneClassLikeType = coneRigidTypeLowerBoundIfFlexible instanceof ConeClassLikeType ? (ConeClassLikeType) coneRigidTypeLowerBoundIfFlexible : null;
        if (coneClassLikeType == null || (functionFunctionTypeKind$default = FunctionalTypeUtilsKt.functionTypeKind$default((ConeRigidType) coneClassLikeType, session, false, 2, (Object) null)) == null) {
            return null;
        }
        FunctionTypeKind.Function functionExtractSingleSpecialKindForFunction = FirFunctionTypeKindServiceKt.getFunctionTypeService(session).extractSingleSpecialKindForFunction(firAnonymousFunction.getSymbol());
        if (functionExtractSingleSpecialKindForFunction == null) {
            functionExtractSingleSpecialKindForFunction = !firAnonymousFunction.getIsLambda() ? FunctionTypeKind.Function.INSTANCE : null;
        }
        ConeKotlinType returnType = org.jetbrains.kotlin.fir.types.InferenceUtilsKt.getReturnType(firAnonymousFunction);
        if (returnType == null) {
            returnType = FunctionalTypeUtilsKt.returnType(coneClassLikeType, session);
        }
        ConeKotlinType coneKotlinType2 = returnType;
        ConeKotlinType coneKotlinTypeReceiverType = firAnonymousFunction.getIsLambda() ? FunctionalTypeUtilsKt.receiverType(coneClassLikeType, session) : org.jetbrains.kotlin.fir.types.InferenceUtilsKt.getReceiverType(firAnonymousFunction);
        int contextParameterNumberForFunctionType = firAnonymousFunction.getIsLambda() ? CompilerConeAttributesKt.getContextParameterNumberForFunctionType(coneClassLikeType) : firAnonymousFunction.getContextParameters().size();
        List<ConeKotlinType> listValueParameterTypesIncludingReceiver = FunctionalTypeUtilsKt.valueParameterTypesIncludingReceiver(coneClassLikeType, session);
        boolean zIsExtensionFunctionType = TypeUtilsKt.isExtensionFunctionType(coneClassLikeType, session);
        int i = ((coneKotlinTypeReceiverType == null || !zIsExtensionFunctionType) ? 0 : 1) + contextParameterNumberForFunctionType;
        List<ConeKotlinType> listDrop = i > 0 ? CollectionsKt.drop(listValueParameterTypesIncludingReceiver, i) : listValueParameterTypesIncludingReceiver;
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(listDrop, 10));
        Iterator<T> it = listDrop.iterator();
        while (it.hasNext()) {
            arrayList2.add(TypeAttributeUtilsKt.removeParameterNameAnnotation((ConeKotlinType) it.next()));
        }
        List<FirValueParameter> valueParameters = firAnonymousFunction.getValueParameters();
        if (!firAnonymousFunction.getIsLambda() || firAnonymousFunction.getHasExplicitParameterList() || arrayList2.size() >= 2) {
            if (z && firAnonymousFunction.getIsLambda() && zIsExtensionFunctionType && listValueParameterTypesIncludingReceiver.size() == valueParameters.size()) {
                if (!FirLanguageSettingsComponentKt.getLanguageVersionSettings(session).supportsFeature(LanguageFeature.LexicographicVariableReadinessCalculation)) {
                    FirValueParameter firValueParameter = (FirValueParameter) CollectionsKt.firstOrNull(valueParameters);
                    ConeKotlinType coneKotlinType3 = (ConeKotlinType) CollectionsKt.firstOrNull(listValueParameterTypesIncludingReceiver);
                    if (firValueParameter == null || (returnTypeRef = firValueParameter.getReturnTypeRef()) == null) {
                        coneType = null;
                    } else {
                        FirResolvedTypeRef firResolvedTypeRef = returnTypeRef instanceof FirResolvedTypeRef ? returnTypeRef : null;
                        coneType = firResolvedTypeRef != null ? firResolvedTypeRef.getConeType() : null;
                        if (coneType == null) {
                            coneType = null;
                        }
                    }
                    if (!Intrinsics.areEqual(coneType, coneKotlinType3)) {
                        z2 = false;
                    }
                }
                z2 = true;
            } else {
                z2 = false;
            }
            if (z2) {
                valueParameters = CollectionsKt.drop(valueParameters, 1);
            }
            List<FirValueParameter> list2 = valueParameters;
            ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
            int i2 = 0;
            for (Object obj : list2) {
                int i3 = i2 + 1;
                if (i2 < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                FirValueParameter firValueParameter2 = (FirValueParameter) obj;
                FirResolvedTypeRef returnTypeRef2 = firValueParameter2.getReturnTypeRef();
                FirResolvedTypeRef firResolvedTypeRef2 = returnTypeRef2 instanceof FirResolvedTypeRef ? returnTypeRef2 : null;
                ConeKotlinType coneType2 = firResolvedTypeRef2 != null ? firResolvedTypeRef2.getConeType() : null;
                if (coneType2 == null) {
                    coneType2 = null;
                }
                if (coneType2 == null && (coneType2 = (ConeKotlinType) CollectionsKt.getOrNull(arrayList2, i2)) == null) {
                    coneType2 = new ConeErrorType(new ConeCannotInferValueParameterType(firValueParameter2.getSymbol(), null, false, 6, null), false, null, null, null, null, null, 126, null);
                }
                arrayList3.add(coneType2);
                i2 = i3;
            }
            z3 = z2;
            arrayList = arrayList3;
        } else {
            z3 = false;
            arrayList = arrayList2;
        }
        if (contextParameterNumberForFunctionType != 0) {
            if (firAnonymousFunction.getIsLambda()) {
                listSubList = listValueParameterTypesIncludingReceiver.subList(0, contextParameterNumberForFunctionType);
            } else {
                List<FirValueParameter> contextParameters = firAnonymousFunction.getContextParameters();
                ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(contextParameters, 10));
                Iterator<T> it2 = contextParameters.iterator();
                while (it2.hasNext()) {
                    arrayList4.add(FirTypeUtilsKt.getConeType(((FirValueParameter) it2.next()).getReturnTypeRef()));
                }
                list = arrayList4;
            }
            if (functionExtractSingleSpecialKindForFunction == null) {
                function = functionFunctionTypeKind$default;
            } else {
                function = functionExtractSingleSpecialKindForFunction;
            }
            return new ConeResolvedLambdaAtom(firAnonymousFunctionExpression, coneClassLikeType, function, coneKotlinTypeReceiverType, list, arrayList, coneKotlinType2, coneTypeVariableForLambdaReturnType, z3, ktSourceElement);
        }
        listSubList = CollectionsKt.emptyList();
        list = listSubList;
        if (functionExtractSingleSpecialKindForFunction == null) {
            function = functionFunctionTypeKind$default;
        } else {
            function = functionExtractSingleSpecialKindForFunction;
        }
        return new ConeResolvedLambdaAtom(firAnonymousFunctionExpression, coneClassLikeType, function, coneKotlinTypeReceiverType, list, arrayList, coneKotlinType2, coneTypeVariableForLambdaReturnType, z3, ktSourceElement);
    }
}
