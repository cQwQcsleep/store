package org.jetbrains.kotlin.fir.resolve.calls.stages;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCallOrigin;
import org.jetbrains.kotlin.fir.expressions.FirNamedArgumentExpression;
import org.jetbrains.kotlin.fir.expressions.builder.FirExpressionStubBuilder;
import org.jetbrains.kotlin.fir.resolve.BodyResolveComponents;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.resolve.calls.ConeResolutionAtom;
import org.jetbrains.kotlin.fir.resolve.calls.ConeResolutionAtomWithSingleChild;
import org.jetbrains.kotlin.fir.resolve.calls.HiddenCandidate;
import org.jetbrains.kotlin.fir.resolve.calls.ResolutionContext;
import org.jetbrains.kotlin.fir.resolve.calls.ResolutionDiagnostic;
import org.jetbrains.kotlin.fir.resolve.calls.UnsupportedContextualDeclarationCall;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CheckerSink;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.ImplicitInvokeMode;
import org.jetbrains.kotlin.fir.scopes.FirScope;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FunctionalTypeUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J.\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0096@R\u00020\u0006R\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006j\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\fJ \u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e*\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J9\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u000e*\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002R\u00020\u0006R\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006j\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u0014¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/stages/MapArguments;", "Lorg/jetbrains/kotlin/fir/resolve/calls/stages/ResolutionStage;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CheckerSink;", "sink", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;", "context", "candidate", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "(Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CheckerSink;Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "unwrapNamedArgumentsForDynamicCall", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolutionAtom;", "function", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "getExpectedContextParameterTypesForInvoke", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "(Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CheckerSink;Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;Lorg/jetbrains/kotlin/fir/declarations/FirFunction;)Ljava/util/List;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class MapArguments extends ResolutionStage {
    public static final MapArguments INSTANCE = new MapArguments();

    private MapArguments() {
    }

    private final List<ConeKotlinType> getExpectedContextParameterTypesForInvoke(CheckerSink checkerSink, ResolutionContext resolutionContext, Candidate candidate, FirFunction firFunction) {
        FirExpression expression;
        ConeKotlinType resolvedType;
        if (!candidate.getCallInfo().isImplicitInvoke()) {
            return null;
        }
        ConeResolutionAtom dispatchReceiver = candidate.getDispatchReceiver();
        ConeKotlinType coneKotlinTypeFullyExpandedType = (dispatchReceiver == null || (expression = dispatchReceiver.getExpression()) == null || (resolvedType = FirTypeUtilsKt.getResolvedType(expression)) == null) ? null : TypeExpansionUtilsKt.fullyExpandedType(resolutionContext, resolvedType);
        List<ConeKotlinType> listContextParameterTypes = coneKotlinTypeFullyExpandedType != null ? FunctionalTypeUtilsKt.contextParameterTypes(coneKotlinTypeFullyExpandedType, resolutionContext.getSession()) : null;
        List<ConeKotlinType> list = listContextParameterTypes;
        if (list != null && !list.isEmpty()) {
            if (listContextParameterTypes.size() + candidate.getCallInfo().getArguments().size() > firFunction.getValueParameters().size()) {
                if (candidate.getCallInfo().getImplicitInvokeMode() == ImplicitInvokeMode.ReceiverAsArgument) {
                    checkerSink.reportDiagnostic(UnsupportedContextualDeclarationCall.INSTANCE);
                }
                return null;
            }
            LanguageVersionSettings languageVersionSettings = FirLanguageSettingsComponentKt.getLanguageVersionSettings(resolutionContext.getSession());
            if (languageVersionSettings.supportsFeature(LanguageFeature.ContextParameters) || languageVersionSettings.supportsFeature(LanguageFeature.ContextReceivers)) {
                return listContextParameterTypes;
            }
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final List<ConeResolutionAtom> unwrapNamedArgumentsForDynamicCall(List<? extends ConeResolutionAtom> list, FirFunction firFunction) {
        if (!Intrinsics.areEqual(firFunction.getOrigin(), FirDeclarationOrigin.DynamicScope.INSTANCE)) {
            return list;
        }
        List<? extends ConeResolutionAtom> list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        for (ConeResolutionAtom subAtom : list2) {
            if (subAtom instanceof ConeResolutionAtomWithSingleChild) {
                ConeResolutionAtomWithSingleChild coneResolutionAtomWithSingleChild = (ConeResolutionAtomWithSingleChild) subAtom;
                if ((coneResolutionAtomWithSingleChild.getExpression() instanceof FirNamedArgumentExpression) && (subAtom = coneResolutionAtomWithSingleChild.getSubAtom()) == null) {
                    k2d.a("SubAtom for named argument is null");
                    return null;
                }
            }
            arrayList.add(subAtom);
        }
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.resolve.calls.stages.ResolutionStage
    public Object check(CheckerSink checkerSink, ResolutionContext resolutionContext, Candidate candidate, Continuation<? super Unit> continuation) {
        FirBasedSymbol<?> symbol = candidate.getSymbol();
        FirFunctionSymbol firFunctionSymbol = symbol instanceof FirFunctionSymbol ? (FirFunctionSymbol) symbol : null;
        if (firFunctionSymbol == null) {
            checkerSink.reportDiagnostic(HiddenCandidate.INSTANCE);
            return Unit.INSTANCE;
        }
        FirFunction firFunction = (FirFunction) firFunctionSymbol.getFir();
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        List<ConeKotlinType> expectedContextParameterTypesForInvoke = INSTANCE.getExpectedContextParameterTypesForInvoke(checkerSink, resolutionContext, candidate, firFunction);
        if (expectedContextParameterTypesForInvoke != null) {
            List list = listCreateListBuilder;
            for (ConeKotlinType coneKotlinType : expectedContextParameterTypesForInvoke) {
                ConeResolutionAtom.Companion companion = ConeResolutionAtom.INSTANCE;
                FirExpressionStubBuilder firExpressionStubBuilder = new FirExpressionStubBuilder();
                Unit unit = Unit.INSTANCE;
                list.add(companion.createRawAtomForPotentiallyUnresolvedExpression(firExpressionStubBuilder.mo288build()));
            }
            candidate.setExpectedContextParameterCountForInvoke(Boxing.boxInt(expectedContextParameterTypesForInvoke.size()));
        }
        List list2 = listCreateListBuilder;
        Iterator<T> it = candidate.getCallInfo().getArguments().iterator();
        while (it.hasNext()) {
            list2.add(ConeResolutionAtom.INSTANCE.createRawAtom((FirExpression) it.next()));
        }
        List<? extends ConeResolutionAtom> listBuild = CollectionsKt.build(listCreateListBuilder);
        BodyResolveComponents bodyResolveComponents = resolutionContext.getBodyResolveComponents();
        FirScope originScope = candidate.getOriginScope();
        FirElement callSite = candidate.getCallInfo().getCallSite();
        FirFunctionCall firFunctionCall = callSite instanceof FirFunctionCall ? (FirFunctionCall) callSite : null;
        ArgumentMapping argumentMappingMapArguments = FirArgumentsToParametersMapperKt.mapArguments(bodyResolveComponents, listBuild, firFunction, originScope, (firFunctionCall != null ? firFunctionCall.getOrigin() : null) == FirFunctionCallOrigin.Operator, LanguageVersionUtilsKt.isEnabled(resolutionContext, LanguageFeature.ExplicitContextArguments));
        candidate.initializeArgumentMapping(unwrapNamedArgumentsForDynamicCall(listBuild, firFunction), argumentMappingMapArguments.toArgumentToParameterMapping());
        candidate.setNumDefaults(argumentMappingMapArguments.numDefaults());
        Iterator<T> it2 = argumentMappingMapArguments.getDiagnostics().iterator();
        while (it2.hasNext()) {
            checkerSink.reportDiagnostic((ResolutionDiagnostic) it2.next());
        }
        return checkerSink.getNeedYielding() ? checkerSink.yield(continuation) : Unit.INSTANCE;
    }
}
