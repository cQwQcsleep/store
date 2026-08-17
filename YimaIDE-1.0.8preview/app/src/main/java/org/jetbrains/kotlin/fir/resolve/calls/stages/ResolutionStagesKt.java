package org.jetbrains.kotlin.fir.resolve.calls.stages;

import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirSuperReceiverExpression;
import org.jetbrains.kotlin.fir.resolve.BodyResolveComponents;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.resolve.calls.ArgumentUtilsKt;
import org.jetbrains.kotlin.fir.resolve.calls.ConeResolutionAtom;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate;
import org.jetbrains.kotlin.fir.resolve.calls.stages.ResolutionStagesKt;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.scopes.FirTypeScopeKt;
import org.jetbrains.kotlin.fir.scopes.ProcessorAction;
import org.jetbrains.kotlin.fir.scopes.impl.FirAbstractImportingScopeKt;
import org.jetbrains.kotlin.fir.scopes.impl.ImportedFromObjectOrStaticData;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirReceiverParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.types.CompilerConeAttributesKt;
import org.jetbrains.kotlin.fir.types.ConeIntegerConstantOperatorType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.resolve.descriptorUtil.AnnotationsForResolveKt;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000@\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a \u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0002\u001a\u0014\u0010\b\u001a\u0006\u0012\u0002\b\u00030\t*\u0006\u0012\u0002\b\u00030\tH\u0002\u001a\u0014\u0010\u000e\u001a\u00020\u000b*\u00020\f2\u0006\u0010\u000f\u001a\u00020\u0010H\u0000\u001a\f\u0010\u0011\u001a\u00020\u000b*\u00020\fH\u0002\u001a\f\u0010\u0012\u001a\u00020\u000b*\u00020\u0013H\u0000\"\u0018\u0010\n\u001a\u00020\u000b*\u00020\f8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\r\"\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"prepareImplicitArgument", "Lorg/jetbrains/kotlin/fir/resolve/calls/stages/ImplicitArgumentDescription;", "argumentExtensionReceiver", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolutionAtom;", "expectedType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "containingDeclarationIfParameter", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "isInvokeFromExtensionFunctionType", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "(Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;)Z", "shouldHaveLowPriorityDueToSAM", "bodyResolveComponents", "Lorg/jetbrains/kotlin/fir/resolve/BodyResolveComponents;", "isJavaApplicableCandidate", "isSuperCall", "Lorg/jetbrains/kotlin/fir/FirElement;", "DYNAMIC_EXTENSION_ANNOTATION_CLASS_ID", "Lorg/jetbrains/kotlin/name/ClassId;", "org.jetbrains.kotlin:resolve"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ResolutionStagesKt {
    private static final ClassId DYNAMIC_EXTENSION_ANNOTATION_CLASS_ID = ClassId.Companion.topLevel(AnnotationsForResolveKt.getDYNAMIC_EXTENSION_FQ_NAME());

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    public static ProcessorAction a(Ref.BooleanRef booleanRef, FirNamedFunctionSymbol firNamedFunctionSymbol) throws KotlinIllegalArgumentExceptionWithAttachments {
        ImportedFromObjectOrStaticData importedFromObjectOrStaticData;
        FirCallableDeclaration original;
        firNamedFunctionSymbol.getClass();
        FirDeclarationOrigin origin = firNamedFunctionSymbol.getOrigin();
        if (!(origin instanceof FirDeclarationOrigin.Java) && !Intrinsics.areEqual(origin, FirDeclarationOrigin.Enhancement.INSTANCE)) {
            D fir = firNamedFunctionSymbol.getFir();
            FirCallableDeclaration firCallableDeclaration = fir instanceof FirCallableDeclaration ? (FirCallableDeclaration) fir : null;
            if (firCallableDeclaration == null || (importedFromObjectOrStaticData = FirAbstractImportingScopeKt.getImportedFromObjectOrStaticData(firCallableDeclaration)) == null || (original = importedFromObjectOrStaticData.getOriginal()) == null || !DeclarationUtilsKt.isJavaOrEnhancement(original)) {
                return ProcessorAction.NEXT;
            }
        }
        booleanRef.element = true;
        return ProcessorAction.STOP;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final FirBasedSymbol<?> containingDeclarationIfParameter(FirBasedSymbol<?> firBasedSymbol) {
        if (firBasedSymbol instanceof FirReceiverParameterSymbol) {
            return ((FirReceiverParameterSymbol) firBasedSymbol).getContainingDeclarationSymbol();
        }
        return firBasedSymbol instanceof FirValueParameterSymbol ? ((FirValueParameterSymbol) firBasedSymbol).getContainingDeclarationSymbol() : firBasedSymbol;
    }

    public static final boolean isInvokeFromExtensionFunctionType(Candidate candidate) {
        ConeResolutionAtom dispatchReceiver;
        FirExpression expression;
        ConeKotlinType resolvedType;
        ConeKotlinType coneKotlinTypeFullyExpandedType$default;
        candidate.getClass();
        return (!candidate.getCallInfo().isImplicitInvoke() || (dispatchReceiver = candidate.getDispatchReceiver()) == null || (expression = dispatchReceiver.getExpression()) == null || (resolvedType = FirTypeUtilsKt.getResolvedType(expression)) == null || (coneKotlinTypeFullyExpandedType$default = TypeExpansionUtilsKt.fullyExpandedType$default(resolvedType, candidate.getCallInfo().getSession(), (Function1) null, 2, (Object) null)) == null || !CompilerConeAttributesKt.isExtensionFunctionType(coneKotlinTypeFullyExpandedType$default)) ? false : true;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    private static final boolean isJavaApplicableCandidate(Candidate candidate) throws KotlinIllegalArgumentExceptionWithAttachments {
        ImportedFromObjectOrStaticData importedFromObjectOrStaticData;
        FirCallableDeclaration original;
        FirBasedSymbol<?> symbol = candidate.getSymbol();
        FirFunctionSymbol firFunctionSymbol = symbol instanceof FirFunctionSymbol ? (FirFunctionSymbol) symbol : null;
        if (firFunctionSymbol == null) {
            return false;
        }
        FirDeclarationOrigin origin = firFunctionSymbol.getOrigin();
        if (!(origin instanceof FirDeclarationOrigin.Java) && !Intrinsics.areEqual(origin, FirDeclarationOrigin.Enhancement.INSTANCE)) {
            D fir = firFunctionSymbol.getFir();
            FirCallableDeclaration firCallableDeclaration = fir instanceof FirCallableDeclaration ? (FirCallableDeclaration) fir : null;
            if (firCallableDeclaration == null || (importedFromObjectOrStaticData = FirAbstractImportingScopeKt.getImportedFromObjectOrStaticData(firCallableDeclaration)) == null || (original = importedFromObjectOrStaticData.getOriginal()) == null || !DeclarationUtilsKt.isJavaOrEnhancement(original)) {
                if (!(candidate.getOriginScope() instanceof FirTypeScope) || !(firFunctionSymbol instanceof FirNamedFunctionSymbol)) {
                    return false;
                }
                final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
                FirTypeScopeKt.processOverriddenFunctions((FirTypeScope) candidate.getOriginScope(), (FirNamedFunctionSymbol) firFunctionSymbol, (Function1<? super FirNamedFunctionSymbol, ? extends ProcessorAction>) new Function1() { // from class: egc
                    public final Object invoke(Object obj) {
                        return ResolutionStagesKt.a(booleanRef, (FirNamedFunctionSymbol) obj);
                    }
                });
                return booleanRef.element;
            }
        }
        return true;
    }

    public static final boolean isSuperCall(FirElement firElement) {
        firElement.getClass();
        return (firElement instanceof FirQualifiedAccessExpression) && (((FirQualifiedAccessExpression) firElement).getExplicitReceiver() instanceof FirSuperReceiverExpression);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ImplicitArgumentDescription prepareImplicitArgument(ConeResolutionAtom coneResolutionAtom, ConeKotlinType coneKotlinType, FirSession firSession) {
        ConeKotlinType coneKotlinTypePrepareCapturedType = ArgumentUtilsKt.prepareCapturedType(ArgumentUtilsKt.captureFromTypeParameterUpperBoundIfNeeded(FirTypeUtilsKt.getResolvedType(coneResolutionAtom.getExpression()), coneKotlinType, firSession), firSession);
        if (coneKotlinTypePrepareCapturedType instanceof ConeIntegerConstantOperatorType) {
            coneKotlinTypePrepareCapturedType = (ConeKotlinType) CollectionsKt.first(((ConeIntegerConstantOperatorType) coneKotlinTypePrepareCapturedType).getPossibleTypes());
        }
        return new ImplicitArgumentDescription(coneResolutionAtom, coneKotlinTypePrepareCapturedType);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    public static final boolean shouldHaveLowPriorityDueToSAM(Candidate candidate, BodyResolveComponents bodyResolveComponents) throws KotlinIllegalArgumentExceptionWithAttachments {
        FirRegularClassSymbol regularClassSymbol;
        ImportedFromObjectOrStaticData importedFromObjectOrStaticData;
        FirCallableDeclaration original;
        candidate.getClass();
        bodyResolveComponents.getClass();
        if (candidate.getUsesSamConversion() && !isJavaApplicableCandidate(candidate)) {
            Collection<FirValueParameter> collectionValues = candidate.getArgumentMapping().values();
            collectionValues.getClass();
            Collection<FirValueParameter> collection = collectionValues;
            if (collection.isEmpty()) {
                return false;
            }
            Iterator<T> it = collection.iterator();
            while (it.hasNext()) {
                ConeKotlinType coneType = FirTypeUtilsKt.getConeType(((FirValueParameter) it.next()).getReturnTypeRef());
                if (bodyResolveComponents.getSamResolver().isSamType(coneType) && (regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(coneType, bodyResolveComponents.getSession())) != null) {
                    FirDeclarationOrigin origin = regularClassSymbol.getOrigin();
                    if (!(origin instanceof FirDeclarationOrigin.Java) && !Intrinsics.areEqual(origin, FirDeclarationOrigin.Enhancement.INSTANCE)) {
                        E fir = regularClassSymbol.getFir();
                        FirCallableDeclaration firCallableDeclaration = fir instanceof FirCallableDeclaration ? (FirCallableDeclaration) fir : null;
                        if (firCallableDeclaration == null || (importedFromObjectOrStaticData = FirAbstractImportingScopeKt.getImportedFromObjectOrStaticData(firCallableDeclaration)) == null || (original = importedFromObjectOrStaticData.getOriginal()) == null || !DeclarationUtilsKt.isJavaOrEnhancement(original)) {
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }
}
