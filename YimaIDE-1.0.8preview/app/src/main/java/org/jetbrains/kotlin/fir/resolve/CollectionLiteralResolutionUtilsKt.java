package org.jetbrains.kotlin.fir.resolve;

import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.diagnostics.ConeCollectionLiteralAmbiguity;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.expressions.FirCollectionLiteral;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.expressions.builder.FirFunctionCallBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirResolvedQualifierBuilder;
import org.jetbrains.kotlin.fir.references.FirNamedReference;
import org.jetbrains.kotlin.fir.references.builder.FirSimpleNamedReferenceBuilder;
import org.jetbrains.kotlin.fir.resolve.CollectionLiteralResolutionStrategy;
import org.jetbrains.kotlin.fir.resolve.CollectionLiteralResolutionUtilsKt;
import org.jetbrains.kotlin.fir.resolve.calls.ResolutionContext;
import org.jetbrains.kotlin.fir.resolve.calls.ResolutionDiagnostic;
import org.jetbrains.kotlin.fir.resolve.calls.UnsuccessfulCollectionLiteralArgument;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CheckerSink;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.ErrorCandidateUtilsKt;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.FirErrorReferenceWithCandidate;
import org.jetbrains.kotlin.fir.resolve.inference.CollectionLiteralBounds;
import org.jetbrains.kotlin.fir.symbols.impl.FirAnonymousObjectSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirErrorFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeAliasSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeCapturedType;
import org.jetbrains.kotlin.fir.types.ConeDefinitelyNotNullType;
import org.jetbrains.kotlin.fir.types.ConeDynamicType;
import org.jetbrains.kotlin.fir.types.ConeFlexibleType;
import org.jetbrains.kotlin.fir.types.ConeIntegerLiteralType;
import org.jetbrains.kotlin.fir.types.ConeIntersectionType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeLookupTagBasedType;
import org.jetbrains.kotlin.fir.types.ConeSimpleKotlinType;
import org.jetbrains.kotlin.fir.types.ConeStubType;
import org.jetbrains.kotlin.fir.types.ConeTypeVariableType;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.resolve.calls.tower.CandidateApplicability;
import org.jetbrains.kotlin.resolve.calls.tower.CandidateApplicabilityKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000`\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u001a\u001d\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0004R\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0005\u001a\u001b\u0010\u0006\u001a\u00020\u0007*\u00020\u0001R\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\b\u001a#\u0010\t\u001a\u0004\u0018\u00010\u0001*\b\u0012\u0004\u0012\u00020\u00010\u000bR\u00020\u0002j\u0006\u0010\n\u001a\u00020\u0002¢\u0006\u0002\u0010\f\u001a\f\u0010\r\u001a\u00020\u000e*\u0004\u0018\u00010\u000f\u001a\"\u0010\u0010\u001a\u00020\u0011*\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018\u001a'\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u001dR\u00020\u0002j\u0006\u0010\n\u001a\u00020\u0002¢\u0006\u0002\u0010\u001e\u001a\u0012\u0010\u001f\u001a\u00020\u001a*\u00020\u001d2\u0006\u0010 \u001a\u00020!¨\u0006\""}, d2 = {"getClassRepresentativeForCollectionLiteralResolution", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;", "resolutionContext", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "(Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "declaresOperatorOf", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;)Z", "chooseSingleClassFromIntersectionComponents", "context", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;Ljava/util/Collection;)Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "toConeDiagnostic", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "Lorg/jetbrains/kotlin/fir/resolve/inference/CollectionLiteralBounds;", "buildCollectionLiteralCallForStdlibType", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "Lorg/jetbrains/kotlin/fir/resolve/BodyResolveComponents;", "packageName", "Lorg/jetbrains/kotlin/name/FqName;", "functionName", "Lorg/jetbrains/kotlin/name/Name;", "collectionLiteral", "Lorg/jetbrains/kotlin/fir/expressions/FirCollectionLiteral;", "updateCalleeReferenceWithNewErrorsIfNeeded", Argument.Delimiters.none, "functionCall", "candidate", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "(Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;)V", "remapResolutionDiagnosticsToOuterCandidate", "outerCheckerSink", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CheckerSink;", "org.jetbrains.kotlin:resolve"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CollectionLiteralResolutionUtilsKt {
    public static Boolean a(FirRegularClassSymbol firRegularClassSymbol, CollectionLiteralResolutionStrategy collectionLiteralResolutionStrategy) {
        collectionLiteralResolutionStrategy.getClass();
        if (collectionLiteralResolutionStrategy.declaresOperatorOf$org_jetbrains_kotlin_resolve(firRegularClassSymbol)) {
            return Boolean.TRUE;
        }
        return null;
    }

    public static final FirFunctionCall buildCollectionLiteralCallForStdlibType(BodyResolveComponents bodyResolveComponents, FqName fqName, Name name, FirCollectionLiteral firCollectionLiteral) {
        bodyResolveComponents.getClass();
        fqName.getClass();
        name.getClass();
        firCollectionLiteral.getClass();
        FirFunctionCallBuilder firFunctionCallBuilder = new FirFunctionCallBuilder();
        FirResolvedQualifierBuilder firResolvedQualifierBuilder = new FirResolvedQualifierBuilder();
        firResolvedQualifierBuilder.setPackageFqName(fqName);
        KtSourceElement source = firCollectionLiteral.getSource();
        firResolvedQualifierBuilder.setSource(source != null ? KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.DesugaredReceiverForOperatorOfCall.INSTANCE, null, 2, null) : null);
        firResolvedQualifierBuilder.setResolvedToCompanionObject(false);
        FirResolvedQualifier firResolvedQualifierMo288build = firResolvedQualifierBuilder.mo288build();
        ResolveUtilsKt.setTypeOfQualifier(firResolvedQualifierMo288build, bodyResolveComponents);
        firFunctionCallBuilder.setExplicitReceiver(firResolvedQualifierMo288build);
        firFunctionCallBuilder.setSource(firCollectionLiteral.getSource());
        FirSimpleNamedReferenceBuilder firSimpleNamedReferenceBuilder = new FirSimpleNamedReferenceBuilder();
        KtSourceElement source2 = firCollectionLiteral.getSource();
        firSimpleNamedReferenceBuilder.setSource(source2 != null ? KtSourceElementKt.fakeElement$default(source2, KtFakeSourceElementKind.CalleeReferenceForOperatorOfCall.INSTANCE, null, 2, null) : null);
        firSimpleNamedReferenceBuilder.setName(name);
        firFunctionCallBuilder.setCalleeReference(firSimpleNamedReferenceBuilder.build());
        firFunctionCallBuilder.setArgumentList(firCollectionLiteral.getArgumentList());
        return firFunctionCallBuilder.mo288build();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final FirRegularClassSymbol chooseSingleClassFromIntersectionComponents(ResolutionContext resolutionContext, Collection<FirRegularClassSymbol> collection) {
        Object next;
        resolutionContext.getClass();
        collection.getClass();
        Collection<FirRegularClassSymbol> collection2 = collection;
        Iterator<T> it = collection2.iterator();
        while (it.hasNext()) {
            next = it.next();
            FirRegularClassSymbol firRegularClassSymbol = (FirRegularClassSymbol) next;
            if (!collection2.isEmpty()) {
                Iterator<T> it2 = collection2.iterator();
                do {
                    if (it2.hasNext()) {
                    }
                } while (SupertypeUtilsKt.isSubclassOf$default((FirClass) firRegularClassSymbol.getFir(), ((FirRegularClassSymbol) it2.next()).getLookupTag(), resolutionContext.getSession(), false, null, false, 24, null));
            }
            return (FirRegularClassSymbol) next;
        }
        next = null;
        return (FirRegularClassSymbol) next;
    }

    public static final boolean declaresOperatorOf(ResolutionContext resolutionContext, final FirRegularClassSymbol firRegularClassSymbol) {
        resolutionContext.getClass();
        firRegularClassSymbol.getClass();
        Boolean bool = (Boolean) CollectionLiteralResolutionKt.tryAllCLResolutionStrategies(resolutionContext, new Function1() { // from class: s52
            public final Object invoke(Object obj) {
                return CollectionLiteralResolutionUtilsKt.a(firRegularClassSymbol, (CollectionLiteralResolutionStrategy) obj);
            }
        });
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    public static final FirRegularClassSymbol getClassRepresentativeForCollectionLiteralResolution(ResolutionContext resolutionContext, ConeKotlinType coneKotlinType) {
        resolutionContext.getClass();
        coneKotlinType.getClass();
        if (coneKotlinType instanceof ConeFlexibleType) {
            return getClassRepresentativeForCollectionLiteralResolution(resolutionContext, ((ConeFlexibleType) coneKotlinType).getLowerBound());
        }
        if (coneKotlinType instanceof ConeCapturedType) {
            ConeKotlinType lowerType = ((ConeCapturedType) coneKotlinType).getConstructor().getLowerType();
            if (lowerType != null) {
                return getClassRepresentativeForCollectionLiteralResolution(resolutionContext, lowerType);
            }
            return null;
        }
        if (coneKotlinType instanceof ConeDefinitelyNotNullType) {
            return getClassRepresentativeForCollectionLiteralResolution(resolutionContext, ((ConeDefinitelyNotNullType) coneKotlinType).getOriginal());
        }
        if (!(coneKotlinType instanceof ConeDynamicType) && !(coneKotlinType instanceof ConeIntersectionType) && !(coneKotlinType instanceof ConeStubType) && !(coneKotlinType instanceof ConeTypeVariableType) && !(coneKotlinType instanceof ConeIntegerLiteralType)) {
            if (coneKotlinType instanceof ConeLookupTagBasedType) {
                FirClassifierSymbol<?> symbol = ToSymbolUtilsKt.toSymbol(resolutionContext, ((ConeLookupTagBasedType) coneKotlinType).getLookupTag());
                if (!(symbol instanceof FirTypeParameterSymbol) && !(symbol instanceof FirAnonymousObjectSymbol) && symbol != null) {
                    if (symbol instanceof FirRegularClassSymbol) {
                        return (FirRegularClassSymbol) symbol;
                    }
                    if (symbol instanceof FirTypeAliasSymbol) {
                        return getClassRepresentativeForCollectionLiteralResolution(resolutionContext, TypeExpansionUtilsKt.fullyExpandedType((SessionHolder) resolutionContext, (ConeSimpleKotlinType) coneKotlinType));
                    }
                    bu8.a();
                }
                return null;
            }
            bu8.a();
        }
        return null;
    }

    public static final void remapResolutionDiagnosticsToOuterCandidate(Candidate candidate, CheckerSink checkerSink) {
        candidate.getClass();
        checkerSink.getClass();
        for (ResolutionDiagnostic resolutionDiagnostic : candidate.getDiagnostics()) {
            CandidateApplicability applicability = resolutionDiagnostic.getApplicability();
            checkerSink.reportDiagnostic(resolutionDiagnostic instanceof UnsuccessfulCollectionLiteralArgument ? (UnsuccessfulCollectionLiteralArgument) resolutionDiagnostic : (CandidateApplicabilityKt.isSuccess(applicability) || applicability == CandidateApplicability.RESOLVED_WITH_ERROR) ? new UnsuccessfulCollectionLiteralArgument(resolutionDiagnostic.getApplicability()) : new UnsuccessfulCollectionLiteralArgument(CandidateApplicability.INAPPLICABLE));
        }
    }

    public static final ConeDiagnostic toConeDiagnostic(CollectionLiteralBounds collectionLiteralBounds) {
        if (collectionLiteralBounds instanceof CollectionLiteralBounds.Ambiguity) {
            return new ConeCollectionLiteralAmbiguity(CollectionsKt.toList(((CollectionLiteralBounds.Ambiguity) collectionLiteralBounds).getBounds()));
        }
        k2d.a("Fallback should be used instead");
        return null;
    }

    public static final void updateCalleeReferenceWithNewErrorsIfNeeded(ResolutionContext resolutionContext, FirFunctionCall firFunctionCall, Candidate candidate) {
        resolutionContext.getClass();
        firFunctionCall.getClass();
        candidate.getClass();
        if (candidate.isSuccessful()) {
            return;
        }
        FirNamedReference calleeReference = firFunctionCall.getCalleeReference();
        if ((calleeReference instanceof FirErrorReferenceWithCandidate) && (((FirErrorReferenceWithCandidate) calleeReference).getCandidateSymbol() instanceof FirErrorFunctionSymbol)) {
            return;
        }
        firFunctionCall.replaceCalleeReference((FirNamedReference) ErrorCandidateUtilsKt.createErrorReferenceWithExistingCandidate(candidate, ResolveUtilsKt.createConeDiagnosticForCandidateWithError(candidate.getLowestApplicability(), candidate), firFunctionCall.getCalleeReference().getSource(), resolutionContext, resolutionContext.getBodyResolveComponents().getResolutionStageRunner()));
    }
}
