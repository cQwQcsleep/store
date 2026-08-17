package org.jetbrains.kotlin.fir.resolve.calls.stages;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.FirValueParameterKind;
import org.jetbrains.kotlin.fir.diagnostics.ConeSimpleDiagnostic;
import org.jetbrains.kotlin.fir.diagnostics.ConeUnreportedDuplicateDiagnostic;
import org.jetbrains.kotlin.fir.diagnostics.DiagnosticKind;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpressionUtilKt;
import org.jetbrains.kotlin.fir.resolve.calls.AmbiguousContextArgument;
import org.jetbrains.kotlin.fir.resolve.calls.ArgumentUtilsKt;
import org.jetbrains.kotlin.fir.resolve.calls.ConeResolutionAtom;
import org.jetbrains.kotlin.fir.resolve.calls.ConeSimpleLeafResolutionAtom;
import org.jetbrains.kotlin.fir.resolve.calls.ImplicitValue;
import org.jetbrains.kotlin.fir.resolve.calls.ImplicitValueKt;
import org.jetbrains.kotlin.fir.resolve.calls.NoContextArgument;
import org.jetbrains.kotlin.fir.resolve.calls.ResolutionContext;
import org.jetbrains.kotlin.fir.resolve.calls.UnsupportedContextualDeclarationCall;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CheckerSink;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.resolve.calls.inference.ConstraintSystemBuilderKt;
import org.jetbrains.kotlin.resolve.calls.inference.model.SimpleConstraintSystemConstraintPosition;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J.\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0096@R\u00020\u0006R\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006j\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\fJ\u0014\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e*\u00020\u000bH\u0002J\u0014\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e*\u00020\u000bH\u0002J?\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u000e*\u00020\u000b2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0002R\u00020\u0006R\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006j\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u0014J.\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u000e*\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\u00182\u0012\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u000e0\u000eH\u0002J$\u0010\u001b\u001a\u00020\u0005*\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u001d2\u000e\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u000eH\u0002J\f\u0010\u001f\u001a\u00020\u0012*\u00020\u0012H\u0002¨\u0006 "}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/stages/CheckContextArguments;", "Lorg/jetbrains/kotlin/fir/resolve/calls/stages/ResolutionStage;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CheckerSink;", "sink", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;", "context", "candidate", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "(Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CheckerSink;Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "obtainInvokeContextParametersOrNull", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirValueParameterSymbol;", "obtainRegularContextParametersOrNull", "mapContextArgumentsOrNull", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolutionAtom;", "contextSymbols", "(Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CheckerSink;Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;Ljava/util/List;)Ljava/util/List;", "findClosestMatchingContextArguments", "Lorg/jetbrains/kotlin/fir/resolve/calls/stages/ImplicitArgumentDescription;", "expectedType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "implicitGroups", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "replaceArgumentPrefixForInvokeWithImplicitlyMappedContextValues", "count", Argument.Delimiters.none, "resultingContextArguments", "copyImplicitValueExpression", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CheckContextArguments extends ResolutionStage {
    public static final CheckContextArguments INSTANCE = new CheckContextArguments();

    private CheckContextArguments() {
    }

    private final ConeResolutionAtom copyImplicitValueExpression(ConeResolutionAtom coneResolutionAtom) {
        return !(coneResolutionAtom instanceof ConeSimpleLeafResolutionAtom) ? coneResolutionAtom : ConeResolutionAtom.INSTANCE.createRawAtom(ImplicitValueKt.copyImplicitValueExpression(((ConeSimpleLeafResolutionAtom) coneResolutionAtom).getExpression()));
    }

    private final List<ImplicitArgumentDescription> findClosestMatchingContextArguments(Candidate candidate, ConeKotlinType coneKotlinType, List<? extends List<? extends FirExpression>> list) {
        for (List<? extends FirExpression> list2 : list) {
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
            Iterator<T> it = list2.iterator();
            while (it.hasNext()) {
                arrayList.add(ResolutionStagesKt.prepareImplicitArgument(ConeResolutionAtom.INSTANCE.createRawAtom((FirExpression) it.next()), coneKotlinType, candidate.getCallInfo().getSession()));
            }
            ArrayList arrayList2 = new ArrayList();
            for (Object obj : arrayList) {
                if (ConstraintSystemBuilderKt.isSubtypeConstraintCompatible(candidate.getSystem(), ((ImplicitArgumentDescription) obj).getType(), coneKotlinType)) {
                    arrayList2.add(obj);
                }
            }
            if (!arrayList2.isEmpty()) {
                return arrayList2;
            }
        }
        return CollectionsKt.emptyList();
    }

    private final List<ConeResolutionAtom> mapContextArgumentsOrNull(CheckerSink checkerSink, ResolutionContext resolutionContext, Candidate candidate, List<FirValueParameterSymbol> list) {
        Collection<ImplicitValue<?>> implicitValues = resolutionContext.getBodyResolveContext().getImplicitValueStorage().getImplicitValues();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Iterator<T> it = implicitValues.iterator();
        while (it.hasNext()) {
            ImplicitValue implicitValue = (ImplicitValue) it.next();
            FirBasedSymbol firBasedSymbolContainingDeclarationIfParameter = ResolutionStagesKt.containingDeclarationIfParameter(implicitValue.getBoundSymbol());
            Object arrayList = linkedHashMap.get(firBasedSymbolContainingDeclarationIfParameter);
            if (arrayList == null) {
                arrayList = new ArrayList();
                linkedHashMap.put(firBasedSymbolContainingDeclarationIfParameter, arrayList);
            }
            ((List) arrayList).add(implicitValue.computeExpression());
        }
        Collection<List> collectionValues = linkedHashMap.values();
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(collectionValues, 10));
        for (List list2 : collectionValues) {
            ArrayList arrayList3 = new ArrayList();
            for (Object obj : list2) {
                if (!ArgumentUtilsKt.isInaccessibleAndInapplicable((FirExpression) obj)) {
                    arrayList3.add(obj);
                }
            }
            arrayList2.add(arrayList3);
        }
        List<? extends List<? extends FirExpression>> listReversed = CollectionsKt.reversed(arrayList2);
        ArrayList arrayList4 = new ArrayList();
        Map mapCreateMapBuilder = MapsKt.createMapBuilder();
        for (Map.Entry<ConeResolutionAtom, FirValueParameter> entry : candidate.getArgumentMapping().entrySet()) {
            ConeResolutionAtom key = entry.getKey();
            FirValueParameter value = entry.getValue();
            if (value.getValueParameterKind() != FirValueParameterKind.Regular) {
                mapCreateMapBuilder.put(value.getSymbol(), key);
            }
        }
        Map mapBuild = MapsKt.build(mapCreateMapBuilder);
        boolean z = false;
        for (FirValueParameterSymbol firValueParameterSymbol : list) {
            ConeResolutionAtom coneResolutionAtom = (ConeResolutionAtom) mapBuild.get(firValueParameterSymbol);
            if (coneResolutionAtom != null) {
                arrayList4.add(coneResolutionAtom);
            } else {
                ConeKotlinType coneKotlinTypeSubstituteOrSelf = candidate.getSubstitutor().substituteOrSelf(firValueParameterSymbol.getResolvedReturnType());
                List<ImplicitArgumentDescription> listFindClosestMatchingContextArguments = findClosestMatchingContextArguments(candidate, coneKotlinTypeSubstituteOrSelf, listReversed);
                int size = listFindClosestMatchingContextArguments.size();
                if (size == 0) {
                    checkerSink.reportDiagnostic(new NoContextArgument(firValueParameterSymbol));
                } else if (size != 1) {
                    checkerSink.reportDiagnostic(new AmbiguousContextArgument(firValueParameterSymbol));
                } else {
                    ImplicitArgumentDescription implicitArgumentDescription = (ImplicitArgumentDescription) CollectionsKt.single(listFindClosestMatchingContextArguments);
                    arrayList4.add(implicitArgumentDescription.getAtom());
                    candidate.getSystem().addSubtypeConstraint(implicitArgumentDescription.getType(), coneKotlinTypeSubstituteOrSelf, SimpleConstraintSystemConstraintPosition.INSTANCE);
                }
                z = true;
            }
        }
        if (z) {
            return null;
        }
        return arrayList4;
    }

    private final List<FirValueParameterSymbol> obtainInvokeContextParametersOrNull(Candidate candidate) {
        List<FirValueParameterSymbol> valueParameterSymbols;
        Integer expectedContextParameterCountForInvoke = candidate.getExpectedContextParameterCountForInvoke();
        if (expectedContextParameterCountForInvoke != null) {
            int iIntValue = expectedContextParameterCountForInvoke.intValue();
            FirBasedSymbol<?> symbol = candidate.getSymbol();
            FirFunctionSymbol firFunctionSymbol = symbol instanceof FirFunctionSymbol ? (FirFunctionSymbol) symbol : null;
            if (firFunctionSymbol != null && (valueParameterSymbols = firFunctionSymbol.getValueParameterSymbols()) != null) {
                return CollectionsKt.take(valueParameterSymbols, iIntValue);
            }
        }
        return null;
    }

    private final List<FirValueParameterSymbol> obtainRegularContextParametersOrNull(Candidate candidate) {
        List<FirValueParameterSymbol> contextParameterSymbols;
        FirBasedSymbol<?> symbol = candidate.getSymbol();
        FirCallableSymbol firCallableSymbol = symbol instanceof FirCallableSymbol ? (FirCallableSymbol) symbol : null;
        if (firCallableSymbol == null || (contextParameterSymbols = firCallableSymbol.getContextParameterSymbols()) == null || contextParameterSymbols.isEmpty()) {
            return null;
        }
        return contextParameterSymbols;
    }

    private final void replaceArgumentPrefixForInvokeWithImplicitlyMappedContextValues(Candidate candidate, int i, List<? extends ConeResolutionAtom> list) {
        ConeResolutionAtom coneResolutionAtomCreateRawAtom;
        ConeResolutionAtom coneResolutionAtom;
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < i; i2++) {
            if (list == null || (coneResolutionAtom = list.get(i2)) == null || (coneResolutionAtomCreateRawAtom = INSTANCE.copyImplicitValueExpression(coneResolutionAtom)) == null) {
                coneResolutionAtomCreateRawAtom = ConeResolutionAtom.INSTANCE.createRawAtom(FirExpressionUtilKt.buildErrorExpression$default(candidate.getCallInfo().getCallSite().getSource(), new ConeUnreportedDuplicateDiagnostic(new ConeSimpleDiagnostic("Unresolved context argument", DiagnosticKind.Other)), null, 4, null));
            }
            arrayList.add(coneResolutionAtomCreateRawAtom);
        }
        candidate.replaceArgumentPrefix(arrayList);
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.stages.ResolutionStage
    public Object check(CheckerSink checkerSink, ResolutionContext resolutionContext, Candidate candidate, Continuation<? super Unit> continuation) {
        List<FirValueParameterSymbol> listObtainInvokeContextParametersOrNull = obtainInvokeContextParametersOrNull(candidate);
        if (listObtainInvokeContextParametersOrNull == null && (listObtainInvokeContextParametersOrNull = obtainRegularContextParametersOrNull(candidate)) == null) {
            return Unit.INSTANCE;
        }
        if (!LanguageVersionUtilsKt.isEnabled(resolutionContext, LanguageFeature.ContextParameters) && !LanguageVersionUtilsKt.isEnabled(resolutionContext, LanguageFeature.ContextReceivers)) {
            checkerSink.reportDiagnostic(UnsupportedContextualDeclarationCall.INSTANCE);
            return Unit.INSTANCE;
        }
        List<ConeResolutionAtom> listMapContextArgumentsOrNull = mapContextArgumentsOrNull(checkerSink, resolutionContext, candidate, listObtainInvokeContextParametersOrNull);
        Integer expectedContextParameterCountForInvoke = candidate.getExpectedContextParameterCountForInvoke();
        if (expectedContextParameterCountForInvoke == null) {
            candidate.setContextArguments(listMapContextArgumentsOrNull);
        } else {
            replaceArgumentPrefixForInvokeWithImplicitlyMappedContextValues(candidate, expectedContextParameterCountForInvoke.intValue(), listMapContextArgumentsOrNull);
        }
        return Unit.INSTANCE;
    }
}
