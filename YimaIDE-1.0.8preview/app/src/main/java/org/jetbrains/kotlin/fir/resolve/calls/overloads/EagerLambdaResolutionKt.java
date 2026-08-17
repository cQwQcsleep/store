package org.jetbrains.kotlin.fir.resolve.calls.overloads;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.arguments.K2JsArgumentConstants;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.declarations.FirContractDescriptionOwner;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.references.FirNamedReference;
import org.jetbrains.kotlin.fir.resolve.BodyResolveComponents;
import org.jetbrains.kotlin.fir.resolve.ResolveUtilsKt;
import org.jetbrains.kotlin.fir.resolve.calls.AbstractConeResolutionAtom;
import org.jetbrains.kotlin.fir.resolve.calls.ConePostponedResolvedAtom;
import org.jetbrains.kotlin.fir.resolve.calls.ConeResolutionAtom;
import org.jetbrains.kotlin.fir.resolve.calls.ConeResolutionAtomWithPostponedChild;
import org.jetbrains.kotlin.fir.resolve.calls.ConeResolvedLambdaAtom;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.FirNamedReferenceWithCandidate;
import org.jetbrains.kotlin.fir.resolve.calls.overloads.EagerLambdaResolutionKt;
import org.jetbrains.kotlin.fir.resolve.inference.FirCallCompleter;
import org.jetbrains.kotlin.fir.resolve.inference.FirInferenceSession;
import org.jetbrains.kotlin.fir.resolve.inference.PostponedArgumentsAnalyzer;
import org.jetbrains.kotlin.fir.resolve.inference.ReturnArgumentsAnalysisResult;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FunctionalTypeUtilsKt;
import org.jetbrains.kotlin.resolve.calls.inference.components.ConstraintSystemCompletionMode;
import org.jetbrains.kotlin.resolve.calls.inference.model.NewConstraintSystemImpl;
import org.jetbrains.kotlin.types.model.KotlinTypeMarker;
import org.jetbrains.kotlin.types.model.TypeConstructorMarker;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000D\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a$\u0010\u0000\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0005\u001a/\u0010\u0000\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\u0002R\u00020\u0005j\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006\u001a/\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\rH\u0002R\u00020\u0005j\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u000e\u001a&\u0010\u000f\u001a\u00020\b*\b\u0012\u0004\u0012\u00020\u000b0\n2\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00130\u0011H\u0002\u001a\f\u0010\u0014\u001a\u00020\u0015*\u00020\u0002H\u0002\u001a\u001e\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\n*\b\u0012\u0004\u0012\u00020\u00020\nH\u0002\u001a\f\u0010\u0017\u001a\u00020\b*\u00020\u0002H\u0002¨\u0006\u0018"}, d2 = {"runEagerLambdaAnalysisAndFilterOutInapplicableCandidates", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "candidates", "components", "Lorg/jetbrains/kotlin/fir/resolve/BodyResolveComponents;", "(Lorg/jetbrains/kotlin/fir/resolve/BodyResolveComponents;Ljava/util/Set;)Ljava/util/Set;", "runEagerLambdaAnalysisForLambdaAtomGroup", Argument.Delimiters.none, "lambdaAtomGroup", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/calls/overloads/LambdaAtomWithCandidate;", K2JsArgumentConstants.CALL, "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "(Lorg/jetbrains/kotlin/fir/resolve/BodyResolveComponents;Ljava/util/Collection;Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;)Z", "inputTypesAreTheSame", "semiFixedVariables", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/types/model/TypeConstructorMarker;", "Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;", "temporaryNamedReference", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/FirNamedReferenceWithCandidate;", "lambdaAtomGroups", "hasNonTrivialContracts", "org.jetbrains.kotlin:resolve"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class EagerLambdaResolutionKt {
    public static ConeKotlinType a(ConeSubstitutor coneSubstitutor, ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return coneSubstitutor.substituteOrSelf(coneKotlinType);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private static final boolean hasNonTrivialContracts(Candidate candidate) throws KotlinIllegalArgumentExceptionWithAttachments {
        FirAnnotationContainer fir = candidate.getSymbol().getFir();
        FirContractDescriptionOwner firContractDescriptionOwner = fir instanceof FirContractDescriptionOwner ? (FirContractDescriptionOwner) fir : null;
        return (firContractDescriptionOwner != null ? firContractDescriptionOwner.getContractDescription() : null) != null;
    }

    private static final boolean inputTypesAreTheSame(Collection<LambdaAtomWithCandidate> collection, Map<TypeConstructorMarker, ? extends KotlinTypeMarker> map) {
        Iterator<T> it = collection.iterator();
        LambdaAtomWithCandidate lambdaAtomWithCandidate = (LambdaAtomWithCandidate) it.next();
        Candidate candidate = lambdaAtomWithCandidate.getCandidate();
        ConeResolvedLambdaAtom atom = lambdaAtomWithCandidate.getAtom();
        ConeSubstitutor coneSubstitutor = (ConeSubstitutor) candidate.getSystem().buildCurrentSubstitutor(map);
        Collection<ConeKotlinType> inputTypes = atom.getInputTypes();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(inputTypes, 10));
        Iterator<T> it2 = inputTypes.iterator();
        while (it2.hasNext()) {
            arrayList.add(coneSubstitutor.substituteOrSelf((ConeKotlinType) it2.next()));
        }
        while (it.hasNext()) {
            LambdaAtomWithCandidate lambdaAtomWithCandidate2 = (LambdaAtomWithCandidate) it.next();
            Candidate candidate2 = lambdaAtomWithCandidate2.getCandidate();
            ConeResolvedLambdaAtom atom2 = lambdaAtomWithCandidate2.getAtom();
            ConeSubstitutor coneSubstitutor2 = (ConeSubstitutor) candidate2.getSystem().buildCurrentSubstitutor(map);
            Collection<ConeKotlinType> inputTypes2 = atom2.getInputTypes();
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(inputTypes2, 10));
            Iterator<T> it3 = inputTypes2.iterator();
            while (it3.hasNext()) {
                arrayList2.add(coneSubstitutor2.substituteOrSelf((ConeKotlinType) it3.next()));
            }
            if (!Intrinsics.areEqual(arrayList2, arrayList)) {
                return false;
            }
        }
        return true;
    }

    private static final Collection<Collection<LambdaAtomWithCandidate>> lambdaAtomGroups(Collection<Candidate> collection) {
        Map mapCreateMapBuilder = MapsKt.createMapBuilder();
        for (Candidate candidate : collection) {
            for (ConePostponedResolvedAtom conePostponedResolvedAtom : candidate.getPostponedAtoms()) {
                if ((conePostponedResolvedAtom instanceof ConeResolvedLambdaAtom) && !conePostponedResolvedAtom.getAnalyzed()) {
                    ConeResolvedLambdaAtom coneResolvedLambdaAtom = (ConeResolvedLambdaAtom) conePostponedResolvedAtom;
                    FirAnonymousFunction anonymousFunction = coneResolvedLambdaAtom.getAnonymousFunction();
                    Object arrayList = mapCreateMapBuilder.get(anonymousFunction);
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                        mapCreateMapBuilder.put(anonymousFunction, arrayList);
                    }
                    ((List) arrayList).add(new LambdaAtomWithCandidate(candidate, coneResolvedLambdaAtom));
                }
            }
        }
        return MapsKt.build(mapCreateMapBuilder).values();
    }

    private static final Set<Candidate> runEagerLambdaAnalysisAndFilterOutInapplicableCandidates(BodyResolveComponents bodyResolveComponents, Set<Candidate> set) {
        Collection collection;
        Set<Candidate> set2 = set;
        if (!(set2 instanceof Collection) || !set2.isEmpty()) {
            for (Candidate candidate : set2) {
                if (!candidate.isSuccessful() || hasNonTrivialContracts(candidate)) {
                    return null;
                }
            }
        }
        FirElement callSite = ((Candidate) CollectionsKt.first(set2)).getCallInfo().getCallSite();
        FirFunctionCall firFunctionCall = callSite instanceof FirFunctionCall ? (FirFunctionCall) callSite : null;
        if (firFunctionCall == null || (collection = (Collection) CollectionsKt.singleOrNull(lambdaAtomGroups(set))) == null) {
            return null;
        }
        Collection collection2 = collection;
        Iterator it = collection2.iterator();
        int size = ((LambdaAtomWithCandidate) it.next()).getAtom().getParameterTypes$org_jetbrains_kotlin_resolve().size();
        while (it.hasNext()) {
            if (((LambdaAtomWithCandidate) it.next()).getAtom().getParameterTypes$org_jetbrains_kotlin_resolve().size() != size) {
                return null;
            }
        }
        if (!(collection2 instanceof Collection) || !collection2.isEmpty()) {
            Iterator it2 = collection2.iterator();
            while (it2.hasNext()) {
                ConeKotlinType coneKotlinTypeMo581getExpectedType = ((LambdaAtomWithCandidate) it2.next()).getAtom().mo581getExpectedType();
                if (coneKotlinTypeMo581getExpectedType == null || !FunctionalTypeUtilsKt.isSomeFunctionType(coneKotlinTypeMo581getExpectedType, bodyResolveComponents.getSession())) {
                    return null;
                }
            }
        }
        FirNamedReference calleeReference = firFunctionCall.getCalleeReference();
        try {
            if (!runEagerLambdaAnalysisForLambdaAtomGroup(bodyResolveComponents, collection, firFunctionCall)) {
                firFunctionCall.replaceCalleeReference(calleeReference);
                return null;
            }
            firFunctionCall.replaceCalleeReference(calleeReference);
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            LinkedHashSet linkedHashSet2 = new LinkedHashSet();
            for (Candidate candidate2 : set) {
                if (candidate2.isSuccessful()) {
                    linkedHashSet2.add(candidate2);
                } else if (linkedHashSet.isEmpty()) {
                    linkedHashSet.add(candidate2);
                }
            }
            return !linkedHashSet2.isEmpty() ? linkedHashSet2 : linkedHashSet;
        } catch (Throwable th) {
            firFunctionCall.replaceCalleeReference(calleeReference);
            throw th;
        }
    }

    private static final boolean runEagerLambdaAnalysisForLambdaAtomGroup(BodyResolveComponents bodyResolveComponents, Collection<LambdaAtomWithCandidate> collection, FirFunctionCall firFunctionCall) {
        FirInferenceSession inferenceSession = bodyResolveComponents.getContext().getInferenceSession();
        FirCallCompleter callCompleter = bodyResolveComponents.getCallCompleter();
        for (LambdaAtomWithCandidate lambdaAtomWithCandidate : collection) {
            Candidate candidate = lambdaAtomWithCandidate.getCandidate();
            ConeResolvedLambdaAtom atom = lambdaAtomWithCandidate.getAtom();
            firFunctionCall.replaceCalleeReference((FirNamedReference) temporaryNamedReference(candidate));
            FirCallCompleter.runCompletionForCall$default(callCompleter, candidate, ConstraintSystemCompletionMode.UNTIL_FIRST_LAMBDA, firFunctionCall, ResolveUtilsKt.initialTypeOfCandidate(bodyResolveComponents, candidate), null, 16, null);
            Iterator<ConeKotlinType> it = atom.getInputTypes().iterator();
            while (it.hasNext()) {
                inferenceSession.semiFixTypeVariablesAllowingFixationToOtherOnes(it.next(), candidate.getSystem());
            }
        }
        Map<TypeConstructorMarker, KotlinTypeMarker> semiFixedVariables = inferenceSession.getSemiFixedVariables();
        if (!inputTypesAreTheSame(collection, semiFixedVariables)) {
            return false;
        }
        Iterator<LambdaAtomWithCandidate> it2 = collection.iterator();
        LambdaAtomWithCandidate next = it2.next();
        Candidate candidate2 = next.getCandidate();
        ConeResolvedLambdaAtom atom2 = next.getAtom();
        PostponedArgumentsAnalyzer postponedArgumentsAnalyzerCreatePostponedArgumentsAnalyzer = callCompleter.createPostponedArgumentsAnalyzer(bodyResolveComponents.getResolutionContext());
        firFunctionCall.replaceCalleeReference((FirNamedReference) temporaryNamedReference(candidate2));
        ReturnArgumentsAnalysisResult returnArgumentsAnalysisResultAnalyzeLambda = postponedArgumentsAnalyzerCreatePostponedArgumentsAnalyzer.analyzeLambda(candidate2.getSystem(), atom2, candidate2, true, false, !semiFixedVariables.isEmpty());
        while (it2.hasNext()) {
            LambdaAtomWithCandidate next2 = it2.next();
            Candidate candidate3 = next2.getCandidate();
            ConeResolvedLambdaAtom atom3 = next2.getAtom();
            firFunctionCall.replaceCalleeReference((FirNamedReference) temporaryNamedReference(candidate3));
            final ConeSubstitutor coneSubstitutor = (ConeSubstitutor) candidate3.getSystem().buildCurrentSubstitutor(semiFixedVariables);
            NewConstraintSystemImpl system = candidate3.getSystem();
            Collection<ConeResolutionAtom> returnArguments = returnArgumentsAnalysisResultAnalyzeLambda.getReturnArguments();
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(returnArguments, 10));
            for (AbstractConeResolutionAtom abstractConeResolutionAtomMakeFreshCopy : returnArguments) {
                if (abstractConeResolutionAtomMakeFreshCopy instanceof ConeResolutionAtomWithPostponedChild) {
                    abstractConeResolutionAtomMakeFreshCopy = ((ConeResolutionAtomWithPostponedChild) abstractConeResolutionAtomMakeFreshCopy).makeFreshCopy();
                }
                arrayList.add(abstractConeResolutionAtomMakeFreshCopy);
            }
            postponedArgumentsAnalyzerCreatePostponedArgumentsAnalyzer.applyResultsOfAnalyzedLambdaToCandidateSystem(system, atom3, candidate3, ReturnArgumentsAnalysisResult.copy$default(returnArgumentsAnalysisResultAnalyzeLambda, arrayList, null, 2, null), true, new Function1() { // from class: m44
                public final Object invoke(Object obj) {
                    return EagerLambdaResolutionKt.a(coneSubstitutor, (ConeKotlinType) obj);
                }
            });
        }
        return true;
    }

    private static final FirNamedReferenceWithCandidate temporaryNamedReference(Candidate candidate) {
        return new FirNamedReferenceWithCandidate(null, candidate.getCallInfo().getName(), candidate);
    }

    public static final Set<Candidate> runEagerLambdaAnalysisAndFilterOutInapplicableCandidates(Set<Candidate> set, BodyResolveComponents bodyResolveComponents) {
        set.getClass();
        bodyResolveComponents.getClass();
        return runEagerLambdaAnalysisAndFilterOutInapplicableCandidates(bodyResolveComponents, set);
    }
}
