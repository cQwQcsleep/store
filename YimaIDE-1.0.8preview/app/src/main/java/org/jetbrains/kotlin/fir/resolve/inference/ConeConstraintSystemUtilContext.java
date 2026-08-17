package org.jetbrains.kotlin.fir.resolve.inference;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.kotlin.K1Deprecation;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.declarations.FirReceiverParameter;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.resolve.calls.ConeLambdaWithTypeVariableAsExpectedTypeAtom;
import org.jetbrains.kotlin.fir.resolve.calls.ConePostponedAtomWithRevisableExpectedType;
import org.jetbrains.kotlin.fir.resolve.calls.ConePostponedResolvedAtom;
import org.jetbrains.kotlin.fir.resolve.inference.model.ConeArgumentConstraintPosition;
import org.jetbrains.kotlin.fir.resolve.inference.model.ConeRegularLambdaArgumentConstraintPosition;
import org.jetbrains.kotlin.fir.symbols.ConeTypeParameterLookupTag;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeFlexibleType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeVariable;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.StandardClassIds$Annotations;
import org.jetbrains.kotlin.resolve.calls.inference.components.ConstraintSystemUtilContext;
import org.jetbrains.kotlin.resolve.calls.inference.model.ArgumentConstraintPosition;
import org.jetbrains.kotlin.resolve.calls.inference.model.FixVariableConstraintPosition;
import org.jetbrains.kotlin.resolve.calls.model.PostponedAtomWithRevisableExpectedType;
import org.jetbrains.kotlin.types.model.KotlinTypeMarker;
import org.jetbrains.kotlin.types.model.TypeParameterMarker;
import org.jetbrains.kotlin.types.model.TypeVariableMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\n\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\u00020\u0005*\u00020\u0006H\u0016J\f\u0010\u0007\u001a\u00020\u0005*\u00020\u0006H\u0016J\f\u0010\b\u001a\u00020\t*\u00020\tH\u0016J\f\u0010\n\u001a\u00020\u0005*\u00020\u0006H\u0016J\f\u0010\u000b\u001a\u00020\t*\u00020\tH\u0016J\u0014\u0010\f\u001a\u0006\u0012\u0002\b\u00030\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J-\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u00120\u0011\"\u0004\b\u0000\u0010\u00122\u0006\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u0002H\u0012H\u0017b\u0002\b\u0016¢\u0006\u0002\u0010\u0015J\u001a\u0010\u0017\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0019\u0018\u00010\u00182\u0006\u0010\u001a\u001a\u00020\u000fH\u0016J\u0014\u0010\u001b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00190\u0018*\u00020\u001cH\u0002J\f\u0010\u001d\u001a\u00020\u0005*\u00020\u000fH\u0016J\f\u0010\u001e\u001a\u00020\u0005*\u00020\u000fH\u0016J\f\u0010\u001f\u001a\u00020 *\u00020\u000fH\u0016J\f\u0010!\u001a\u00020\u0005*\u00020\u000fH\u0016J\f\u0010\"\u001a\u00020\u0005*\u00020\u000fH\u0016J\b\u0010#\u001a\u00020\u0006H\u0016J\u0018\u0010$\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010%\u001a\u00020 H\u0016J\u0018\u0010&\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010%\u001a\u00020 H\u0016J\b\u0010'\u001a\u00020\u0006H\u0016R\u0014\u0010(\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b(\u0010)¨\u0006*"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/inference/ConeConstraintSystemUtilContext;", "Lorg/jetbrains/kotlin/resolve/calls/inference/components/ConstraintSystemUtilContext;", "<init>", "()V", "shouldBeFlexible", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/types/model/TypeVariableMarker;", "hasOnlyInputTypesAttribute", "unCapture", "Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;", "isReified", "refineType", "createArgumentConstraintPosition", "Lorg/jetbrains/kotlin/resolve/calls/inference/model/ArgumentConstraintPosition;", "argument", "Lorg/jetbrains/kotlin/resolve/calls/model/PostponedAtomWithRevisableExpectedType;", "createFixVariableConstraintPosition", "Lorg/jetbrains/kotlin/resolve/calls/inference/model/FixVariableConstraintPosition;", "T", "variable", "atom", "(Lorg/jetbrains/kotlin/types/model/TypeVariableMarker;Ljava/lang/Object;)Lorg/jetbrains/kotlin/resolve/calls/inference/model/FixVariableConstraintPosition;", "Lorg/jetbrains/kotlin/K1Deprecation;", "extractLambdaParameterTypesFromDeclaration", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "declaration", "collectDeclaredValueParameterTypes", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "isFunctionExpression", "isFunctionExpressionWithReceiver", "contextParameterCountOfFunctionExpression", Argument.Delimiters.none, "isLambda", "isSuspend", "createTypeVariableForLambdaReturnType", "createTypeVariableForLambdaParameterType", "index", "createTypeVariableForCallableReferenceParameterType", "createTypeVariableForCallableReferenceReturnType", "isForcedAllowForkingInferenceSystem", "()Z", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeConstraintSystemUtilContext implements ConstraintSystemUtilContext {
    public static final ConeConstraintSystemUtilContext INSTANCE = new ConeConstraintSystemUtilContext();

    private ConeConstraintSystemUtilContext() {
    }

    private final List<ConeKotlinType> collectDeclaredValueParameterTypes(FirAnonymousFunction firAnonymousFunction) {
        List<FirValueParameter> valueParameters = firAnonymousFunction.getValueParameters();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(valueParameters, 10));
        Iterator<T> it = valueParameters.iterator();
        while (it.hasNext()) {
            FirResolvedTypeRef returnTypeRef = ((FirValueParameter) it.next()).getReturnTypeRef();
            ConeKotlinType coneKotlinType = null;
            FirResolvedTypeRef firResolvedTypeRef = returnTypeRef instanceof FirResolvedTypeRef ? returnTypeRef : null;
            ConeKotlinType coneType = firResolvedTypeRef != null ? firResolvedTypeRef.getConeType() : null;
            if (coneType != null) {
                coneKotlinType = coneType;
            }
            arrayList.add(coneKotlinType);
        }
        return arrayList;
    }

    public int contextParameterCountOfFunctionExpression(PostponedAtomWithRevisableExpectedType postponedAtomWithRevisableExpectedType) {
        postponedAtomWithRevisableExpectedType.getClass();
        if (!(postponedAtomWithRevisableExpectedType instanceof ConePostponedResolvedAtom)) {
            w01.a("Failed requirement.");
            return 0;
        }
        if (postponedAtomWithRevisableExpectedType instanceof ConeLambdaWithTypeVariableAsExpectedTypeAtom) {
            ConeLambdaWithTypeVariableAsExpectedTypeAtom coneLambdaWithTypeVariableAsExpectedTypeAtom = (ConeLambdaWithTypeVariableAsExpectedTypeAtom) postponedAtomWithRevisableExpectedType;
            if (!coneLambdaWithTypeVariableAsExpectedTypeAtom.getAnonymousFunction().getIsLambda()) {
                return coneLambdaWithTypeVariableAsExpectedTypeAtom.getAnonymousFunction().getContextParameters().size();
            }
        }
        return 0;
    }

    /* JADX INFO: renamed from: createArgumentConstraintPosition, reason: merged with bridge method [inline-methods] */
    public ArgumentConstraintPosition<?> m593createArgumentConstraintPosition(PostponedAtomWithRevisableExpectedType argument) {
        argument.getClass();
        if (!(argument instanceof ConePostponedAtomWithRevisableExpectedType)) {
            throw new IllegalArgumentException(String.valueOf(Reflection.getOrCreateKotlinClass(argument.getClass())).toString());
        }
        ConePostponedAtomWithRevisableExpectedType conePostponedAtomWithRevisableExpectedType = (ConePostponedAtomWithRevisableExpectedType) argument;
        FirAnonymousFunction anonymousFunctionIfReturnExpression = conePostponedAtomWithRevisableExpectedType.getAnonymousFunctionIfReturnExpression();
        return anonymousFunctionIfReturnExpression != null ? new ConeRegularLambdaArgumentConstraintPosition(anonymousFunctionIfReturnExpression, conePostponedAtomWithRevisableExpectedType.getExpression()) : new ConeArgumentConstraintPosition(conePostponedAtomWithRevisableExpectedType.getExpression());
    }

    @K1Deprecation
    public <T> FixVariableConstraintPosition<T> createFixVariableConstraintPosition(TypeVariableMarker variable, T atom) {
        variable.getClass();
        throw new IllegalStateException("Should not be called in K2");
    }

    public TypeVariableMarker createTypeVariableForCallableReferenceParameterType(PostponedAtomWithRevisableExpectedType argument, int index) {
        argument.getClass();
        return new ConeTypeVariableForPostponedAtom("_QP" + index);
    }

    public TypeVariableMarker createTypeVariableForCallableReferenceReturnType() {
        return new ConeTypeVariableForPostponedAtom("_R");
    }

    public TypeVariableMarker createTypeVariableForLambdaParameterType(PostponedAtomWithRevisableExpectedType argument, int index) {
        argument.getClass();
        return new ConeTypeVariableForLambdaParameterType("_RP" + index);
    }

    public TypeVariableMarker createTypeVariableForLambdaReturnType() {
        return new ConeTypeVariableForPostponedAtom("_R");
    }

    public List<ConeKotlinType> extractLambdaParameterTypesFromDeclaration(PostponedAtomWithRevisableExpectedType declaration) {
        FirTypeRef typeRef;
        ConeKotlinType coneType;
        declaration.getClass();
        if (!(declaration instanceof ConePostponedResolvedAtom)) {
            w01.a("Failed requirement.");
            return null;
        }
        if (!(declaration instanceof ConeLambdaWithTypeVariableAsExpectedTypeAtom)) {
            return null;
        }
        FirAnonymousFunction anonymousFunction = ((ConeLambdaWithTypeVariableAsExpectedTypeAtom) declaration).getAnonymousFunction();
        if (anonymousFunction.getIsLambda()) {
            if (anonymousFunction.getValueParameters().isEmpty()) {
                return null;
            }
            return collectDeclaredValueParameterTypes(anonymousFunction);
        }
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        List list = listCreateListBuilder;
        Iterator<T> it = anonymousFunction.getContextParameters().iterator();
        while (it.hasNext()) {
            list.add(FirTypeUtilsKt.getConeType(((FirValueParameter) it.next()).getReturnTypeRef()));
        }
        FirReceiverParameter receiverParameter = anonymousFunction.getReceiverParameter();
        if (receiverParameter != null && (typeRef = receiverParameter.getTypeRef()) != null && (coneType = FirTypeUtilsKt.getConeType(typeRef)) != null) {
            listCreateListBuilder.add(coneType);
        }
        listCreateListBuilder.addAll(INSTANCE.collectDeclaredValueParameterTypes(anonymousFunction));
        return CollectionsKt.build(listCreateListBuilder);
    }

    public boolean hasOnlyInputTypesAttribute(TypeVariableMarker typeVariableMarker) {
        typeVariableMarker.getClass();
        if (!(typeVariableMarker instanceof ConeTypeParameterBasedTypeVariable)) {
            return false;
        }
        List<ClassId> resolvedAnnotationClassIds = ((ConeTypeParameterBasedTypeVariable) typeVariableMarker).getTypeParameterSymbol().getResolvedAnnotationClassIds();
        if ((resolvedAnnotationClassIds instanceof Collection) && resolvedAnnotationClassIds.isEmpty()) {
            return false;
        }
        Iterator<T> it = resolvedAnnotationClassIds.iterator();
        while (it.hasNext()) {
            if (Intrinsics.areEqual((ClassId) it.next(), StandardClassIds$Annotations.INSTANCE.getOnlyInputTypes())) {
                return true;
            }
        }
        return false;
    }

    public boolean isForcedAllowForkingInferenceSystem() {
        return true;
    }

    public boolean isFunctionExpression(PostponedAtomWithRevisableExpectedType postponedAtomWithRevisableExpectedType) {
        postponedAtomWithRevisableExpectedType.getClass();
        if (postponedAtomWithRevisableExpectedType instanceof ConePostponedResolvedAtom) {
            return (postponedAtomWithRevisableExpectedType instanceof ConeLambdaWithTypeVariableAsExpectedTypeAtom) && !((ConeLambdaWithTypeVariableAsExpectedTypeAtom) postponedAtomWithRevisableExpectedType).getAnonymousFunction().getIsLambda();
        }
        w01.a("Failed requirement.");
        return false;
    }

    public boolean isFunctionExpressionWithReceiver(PostponedAtomWithRevisableExpectedType postponedAtomWithRevisableExpectedType) {
        FirTypeRef typeRef;
        postponedAtomWithRevisableExpectedType.getClass();
        if (!(postponedAtomWithRevisableExpectedType instanceof ConePostponedResolvedAtom)) {
            w01.a("Failed requirement.");
            return false;
        }
        if (postponedAtomWithRevisableExpectedType instanceof ConeLambdaWithTypeVariableAsExpectedTypeAtom) {
            ConeLambdaWithTypeVariableAsExpectedTypeAtom coneLambdaWithTypeVariableAsExpectedTypeAtom = (ConeLambdaWithTypeVariableAsExpectedTypeAtom) postponedAtomWithRevisableExpectedType;
            if (!coneLambdaWithTypeVariableAsExpectedTypeAtom.getAnonymousFunction().getIsLambda()) {
                FirReceiverParameter receiverParameter = coneLambdaWithTypeVariableAsExpectedTypeAtom.getAnonymousFunction().getReceiverParameter();
                if (((receiverParameter == null || (typeRef = receiverParameter.getTypeRef()) == null) ? null : FirTypeUtilsKt.getConeType(typeRef)) != null) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isLambda(PostponedAtomWithRevisableExpectedType postponedAtomWithRevisableExpectedType) {
        postponedAtomWithRevisableExpectedType.getClass();
        if (postponedAtomWithRevisableExpectedType instanceof ConePostponedResolvedAtom) {
            return (postponedAtomWithRevisableExpectedType instanceof ConeLambdaWithTypeVariableAsExpectedTypeAtom) && ((ConeLambdaWithTypeVariableAsExpectedTypeAtom) postponedAtomWithRevisableExpectedType).getAnonymousFunction().getIsLambda();
        }
        w01.a("Failed requirement.");
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean isReified(TypeVariableMarker typeVariableMarker) {
        typeVariableMarker.getClass();
        return (typeVariableMarker instanceof ConeTypeParameterBasedTypeVariable) && ((FirTypeParameter) ((ConeTypeParameterBasedTypeVariable) typeVariableMarker).getTypeParameterSymbol().getFir()).getIsReified();
    }

    public boolean isSuspend(PostponedAtomWithRevisableExpectedType postponedAtomWithRevisableExpectedType) {
        postponedAtomWithRevisableExpectedType.getClass();
        if (postponedAtomWithRevisableExpectedType instanceof ConePostponedResolvedAtom) {
            return (postponedAtomWithRevisableExpectedType instanceof ConeLambdaWithTypeVariableAsExpectedTypeAtom) && ((ConeLambdaWithTypeVariableAsExpectedTypeAtom) postponedAtomWithRevisableExpectedType).getAnonymousFunction().getStatus().isSuspend();
        }
        w01.a("Failed requirement.");
        return false;
    }

    public KotlinTypeMarker refineType(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        return kotlinTypeMarker;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean shouldBeFlexible(TypeVariableMarker typeVariableMarker) {
        FirTypeParameterSymbol typeParameterSymbol;
        FirTypeParameter firTypeParameter;
        typeVariableMarker.getClass();
        if (!(typeVariableMarker instanceof ConeTypeVariable)) {
            return false;
        }
        TypeParameterMarker originalTypeParameter = ((ConeTypeVariable) typeVariableMarker).getTypeConstructor().getOriginalTypeParameter();
        ConeTypeParameterLookupTag coneTypeParameterLookupTag = originalTypeParameter instanceof ConeTypeParameterLookupTag ? (ConeTypeParameterLookupTag) originalTypeParameter : null;
        if (coneTypeParameterLookupTag != null && (typeParameterSymbol = coneTypeParameterLookupTag.getTypeParameterSymbol()) != null && (firTypeParameter = (FirTypeParameter) typeParameterSymbol.getFir()) != null) {
            List<FirTypeRef> bounds = firTypeParameter.getBounds();
            if ((bounds instanceof Collection) && bounds.isEmpty()) {
                return false;
            }
            Iterator<T> it = bounds.iterator();
            while (it.hasNext()) {
                if (FirTypeUtilsKt.getConeType((FirTypeRef) it.next()) instanceof ConeFlexibleType) {
                    return true;
                }
            }
        }
        return false;
    }

    public KotlinTypeMarker unCapture(KotlinTypeMarker kotlinTypeMarker) {
        kotlinTypeMarker.getClass();
        if (kotlinTypeMarker instanceof ConeKotlinType) {
            return kotlinTypeMarker;
        }
        w01.a("Failed requirement.");
        return null;
    }
}
