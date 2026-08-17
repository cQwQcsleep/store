package org.jetbrains.kotlin.fir.resolve.calls.overloads;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.arguments.K2JsArgumentConstants;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.OnlyForDefaultLanguageFeatureDisabled;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirResolvable;
import org.jetbrains.kotlin.fir.references.FirReference;
import org.jetbrains.kotlin.fir.resolve.ResolveUtilsKt;
import org.jetbrains.kotlin.fir.resolve.calls.AbstractConeResolutionAtom;
import org.jetbrains.kotlin.fir.resolve.calls.CandidateChosenUsingOverloadResolutionByLambdaAnnotation;
import org.jetbrains.kotlin.fir.resolve.calls.ConePostponedResolvedAtom;
import org.jetbrains.kotlin.fir.resolve.calls.ConeResolutionAtom;
import org.jetbrains.kotlin.fir.resolve.calls.ConeResolutionAtomWithPostponedChild;
import org.jetbrains.kotlin.fir.resolve.calls.ConeResolvedLambdaAtom;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.FirNamedReferenceWithCandidate;
import org.jetbrains.kotlin.fir.resolve.calls.overloads.FirOverloadByLambdaReturnTypeResolver;
import org.jetbrains.kotlin.fir.resolve.inference.FirCallCompleter;
import org.jetbrains.kotlin.fir.resolve.inference.FirInferenceSession;
import org.jetbrains.kotlin.fir.resolve.inference.PostponedArgumentsAnalyzer;
import org.jetbrains.kotlin.fir.resolve.inference.ReturnArgumentsAnalysisResult;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirAbstractBodyResolveTransformer;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FunctionalTypeUtilsKt;
import org.jetbrains.kotlin.resolve.calls.inference.components.ConstraintSystemCompletionMode;
import org.jetbrains.kotlin.resolve.calls.inference.model.NewConstraintSystemImpl;
import org.jetbrains.kotlin.resolve.descriptorUtil.AnnotationsForResolveKt;
import org.jetbrains.kotlin.types.model.KotlinTypeMarker;
import org.jetbrains.kotlin.types.model.TypeConstructorMarker;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@OnlyForDefaultLanguageFeatureDisabled(languageFeature = LanguageFeature.EagerLambdaAnalysis)
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005JC\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f\"\f\b\u0000\u0010\u0011*\u00020\u0012*\u00020\u00132\u0006\u0010\u0014\u001a\u0002H\u00112\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00100\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f¢\u0006\u0002\u0010\u0018JG\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000f\"\f\b\u0000\u0010\u0011*\u00020\u0013*\u00020\u00122\u0006\u0010\u001a\u001a\u0002H\u00112\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00100\u0016H\u0002¢\u0006\u0002\u0010\u001cJ9\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000f\"\f\b\u0000\u0010\u0011*\u00020\u0013*\u00020\u00122\u0006\u0010\u001a\u001a\u0002H\u00112\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0002¢\u0006\u0002\u0010\u001fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\u00020\u000b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rÊ\u0001\u000e\b!\u0012\n\b\"\u0012\u0006\b\n0#8$¨\u0006 "}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/overloads/FirOverloadByLambdaReturnTypeResolver;", Argument.Delimiters.none, "components", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirAbstractBodyResolveTransformer$BodyResolveTransformerComponents;", "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirAbstractBodyResolveTransformer$BodyResolveTransformerComponents;)V", "getComponents", "()Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirAbstractBodyResolveTransformer$BodyResolveTransformerComponents;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "callCompleter", "Lorg/jetbrains/kotlin/fir/resolve/inference/FirCallCompleter;", "getCallCompleter", "()Lorg/jetbrains/kotlin/fir/resolve/inference/FirCallCompleter;", "reduceCandidates", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "T", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "Lorg/jetbrains/kotlin/fir/expressions/FirResolvable;", "qualifiedAccess", "allCandidates", Argument.Delimiters.none, "bestCandidates", "(Lorg/jetbrains/kotlin/fir/expressions/FirExpression;Ljava/util/Collection;Ljava/util/Set;)Ljava/util/Set;", "reduceCandidatesImpl", K2JsArgumentConstants.CALL, "reducedCandidates", "(Lorg/jetbrains/kotlin/fir/expressions/FirExpression;Ljava/util/Set;Ljava/util/Collection;)Ljava/util/Set;", "analyzeLambdaAndReduceNumberOfCandidatesRegardingOverloadResolutionByLambdaReturnType", "candidates", "(Lorg/jetbrains/kotlin/fir/expressions/FirExpression;Ljava/util/Set;)Ljava/util/Set;", "org.jetbrains.kotlin:resolve", "Lorg/jetbrains/kotlin/fir/OnlyForDefaultLanguageFeatureDisabled;", "languageFeature", "Lorg/jetbrains/kotlin/config/LanguageFeature;", "EagerLambdaAnalysis"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirOverloadByLambdaReturnTypeResolver {
    private final FirAbstractBodyResolveTransformer.BodyResolveTransformerComponents components;
    private final FirSession session;

    public FirOverloadByLambdaReturnTypeResolver(FirAbstractBodyResolveTransformer.BodyResolveTransformerComponents bodyResolveTransformerComponents) {
        bodyResolveTransformerComponents.getClass();
        this.components = bodyResolveTransformerComponents;
        this.session = bodyResolveTransformerComponents.getSession();
    }

    public static ConeKotlinType a(ConeSubstitutor coneSubstitutor, ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return coneSubstitutor.substituteOrSelf(coneKotlinType);
    }

    private final <T extends FirExpression & FirResolvable> Set<Candidate> analyzeLambdaAndReduceNumberOfCandidatesRegardingOverloadResolutionByLambdaReturnType(T call, Set<Candidate> candidates) {
        Map map;
        Set<Candidate> set = candidates;
        if (!(set instanceof Collection) || !set.isEmpty()) {
            Iterator<T> it = set.iterator();
            while (it.hasNext()) {
                if (!((Candidate) it.next()).isSuccessful()) {
                    return candidates;
                }
            }
        }
        ArrayList arrayList = new ArrayList();
        for (Candidate candidate : set) {
            List<ConePostponedResolvedAtom> postponedAtoms = candidate.getPostponedAtoms();
            ArrayList<ConePostponedResolvedAtom> arrayList2 = new ArrayList();
            for (Object obj : postponedAtoms) {
                ConePostponedResolvedAtom conePostponedResolvedAtom = (ConePostponedResolvedAtom) obj;
                if ((conePostponedResolvedAtom instanceof ConeResolvedLambdaAtom) && !conePostponedResolvedAtom.getAnalyzed()) {
                    arrayList2.add(obj);
                }
            }
            ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
            for (ConePostponedResolvedAtom conePostponedResolvedAtom2 : arrayList2) {
                conePostponedResolvedAtom2.getClass();
                arrayList3.add(TuplesKt.to(candidate, (ConeResolvedLambdaAtom) conePostponedResolvedAtom2));
            }
            CollectionsKt.addAll(arrayList, arrayList3);
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object obj2 : arrayList) {
            FirAnonymousFunction anonymousFunction = ((ConeResolvedLambdaAtom) ((Pair) obj2).component2()).getAnonymousFunction();
            Object arrayList4 = linkedHashMap.get(anonymousFunction);
            if (arrayList4 == null) {
                arrayList4 = new ArrayList();
                linkedHashMap.put(anonymousFunction, arrayList4);
            }
            ((List) arrayList4).add(obj2);
        }
        List list = (List) CollectionsKt.singleOrNull(linkedHashMap.values());
        if (list == null || (map = MapsKt.toMap(list)) == null) {
            return null;
        }
        Iterator it2 = map.values().iterator();
        int size = ((ConeResolvedLambdaAtom) it2.next()).getParameterTypes$org_jetbrains_kotlin_resolve().size();
        while (it2.hasNext()) {
            if (((ConeResolvedLambdaAtom) it2.next()).getParameterTypes$org_jetbrains_kotlin_resolve().size() != size) {
                return null;
            }
        }
        Collection collectionValues = map.values();
        if (!(collectionValues instanceof Collection) || !collectionValues.isEmpty()) {
            Iterator it3 = collectionValues.iterator();
            while (it3.hasNext()) {
                ConeKotlinType coneKotlinTypeMo581getExpectedType = ((ConeResolvedLambdaAtom) it3.next()).mo581getExpectedType();
                if (coneKotlinTypeMo581getExpectedType == null || !FunctionalTypeUtilsKt.isSomeFunctionType(coneKotlinTypeMo581getExpectedType, this.session)) {
                    return null;
                }
            }
        }
        T t = call;
        FirReference calleeReference = t.getCalleeReference();
        try {
            FirInferenceSession inferenceSession = this.components.getContext().getInferenceSession();
            for (Map.Entry entry : map.entrySet()) {
                Candidate candidate2 = (Candidate) entry.getKey();
                ConeResolvedLambdaAtom coneResolvedLambdaAtom = (ConeResolvedLambdaAtom) entry.getValue();
                call.replaceCalleeReference(new FirNamedReferenceWithCandidate(null, candidate2.getCallInfo().getName(), candidate2));
                FirCallCompleter.runCompletionForCall$default(getCallCompleter(), candidate2, ConstraintSystemCompletionMode.UNTIL_FIRST_LAMBDA, call, ResolveUtilsKt.initialTypeOfCandidate(this.components, candidate2), null, 16, null);
                Iterator<ConeKotlinType> it4 = coneResolvedLambdaAtom.getInputTypes().iterator();
                while (it4.hasNext()) {
                    inferenceSession.semiFixTypeVariablesAllowingFixationToOtherOnes(it4.next(), candidate2.getSystem());
                }
            }
            Map<TypeConstructorMarker, KotlinTypeMarker> semiFixedVariables = inferenceSession.getSemiFixedVariables();
            Iterator it5 = map.entrySet().iterator();
            Map.Entry entry2 = (Map.Entry) it5.next();
            Candidate candidate3 = (Candidate) entry2.getKey();
            ConeResolvedLambdaAtom coneResolvedLambdaAtom2 = (ConeResolvedLambdaAtom) entry2.getValue();
            ConeSubstitutor coneSubstitutor = (ConeSubstitutor) candidate3.getSystem().buildCurrentSubstitutor(semiFixedVariables);
            Collection<ConeKotlinType> inputTypes = coneResolvedLambdaAtom2.getInputTypes();
            ArrayList arrayList5 = new ArrayList(CollectionsKt.collectionSizeOrDefault(inputTypes, 10));
            Iterator<T> it6 = inputTypes.iterator();
            while (it6.hasNext()) {
                arrayList5.add(coneSubstitutor.substituteOrSelf((ConeKotlinType) it6.next()));
            }
            while (it5.hasNext()) {
                Map.Entry entry3 = (Map.Entry) it5.next();
                Candidate candidate4 = (Candidate) entry3.getKey();
                ConeResolvedLambdaAtom coneResolvedLambdaAtom3 = (ConeResolvedLambdaAtom) entry3.getValue();
                ConeSubstitutor coneSubstitutor2 = (ConeSubstitutor) candidate4.getSystem().buildCurrentSubstitutor(semiFixedVariables);
                Collection<ConeKotlinType> inputTypes2 = coneResolvedLambdaAtom3.getInputTypes();
                ArrayList arrayList6 = new ArrayList(CollectionsKt.collectionSizeOrDefault(inputTypes2, 10));
                Iterator<T> it7 = inputTypes2.iterator();
                while (it7.hasNext()) {
                    arrayList6.add(coneSubstitutor2.substituteOrSelf((ConeKotlinType) it7.next()));
                }
                if (!Intrinsics.areEqual(arrayList6, arrayList5)) {
                    return null;
                }
            }
            for (Map.Entry entry4 : map.entrySet()) {
                getCallCompleter().prepareLambdaAtomForFactoryPattern((ConeResolvedLambdaAtom) entry4.getValue(), (Candidate) entry4.getKey());
            }
            Iterator it8 = map.entrySet().iterator();
            Map.Entry entry5 = (Map.Entry) it8.next();
            Candidate candidate5 = (Candidate) entry5.getKey();
            ConeResolvedLambdaAtom coneResolvedLambdaAtom4 = (ConeResolvedLambdaAtom) entry5.getValue();
            PostponedArgumentsAnalyzer postponedArgumentsAnalyzerCreatePostponedArgumentsAnalyzer = getCallCompleter().createPostponedArgumentsAnalyzer(this.components.getTransformer().getResolutionContext());
            call.replaceCalleeReference(new FirNamedReferenceWithCandidate(null, candidate5.getCallInfo().getName(), candidate5));
            ReturnArgumentsAnalysisResult returnArgumentsAnalysisResultAnalyzeLambda = postponedArgumentsAnalyzerCreatePostponedArgumentsAnalyzer.analyzeLambda(candidate5.getSystem(), coneResolvedLambdaAtom4, candidate5, true, false, !semiFixedVariables.isEmpty());
            while (it8.hasNext()) {
                Map.Entry entry6 = (Map.Entry) it8.next();
                Candidate candidate6 = (Candidate) entry6.getKey();
                ConeResolvedLambdaAtom coneResolvedLambdaAtom5 = (ConeResolvedLambdaAtom) entry6.getValue();
                call.replaceCalleeReference(new FirNamedReferenceWithCandidate(null, candidate6.getCallInfo().getName(), candidate6));
                final ConeSubstitutor coneSubstitutor3 = (ConeSubstitutor) candidate6.getSystem().buildCurrentSubstitutor(semiFixedVariables);
                NewConstraintSystemImpl system = candidate6.getSystem();
                Collection<ConeResolutionAtom> returnArguments = returnArgumentsAnalysisResultAnalyzeLambda.getReturnArguments();
                ArrayList arrayList7 = new ArrayList(CollectionsKt.collectionSizeOrDefault(returnArguments, 10));
                for (AbstractConeResolutionAtom abstractConeResolutionAtomMakeFreshCopy : returnArguments) {
                    if (abstractConeResolutionAtomMakeFreshCopy instanceof ConeResolutionAtomWithPostponedChild) {
                        abstractConeResolutionAtomMakeFreshCopy = ((ConeResolutionAtomWithPostponedChild) abstractConeResolutionAtomMakeFreshCopy).makeFreshCopy();
                    }
                    arrayList7.add(abstractConeResolutionAtomMakeFreshCopy);
                }
                postponedArgumentsAnalyzerCreatePostponedArgumentsAnalyzer.applyResultsOfAnalyzedLambdaToCandidateSystem(system, coneResolvedLambdaAtom5, candidate6, ReturnArgumentsAnalysisResult.copy$default(returnArgumentsAnalysisResultAnalyzeLambda, arrayList7, null, 2, null), false, new Function1() { // from class: mb5
                    public final Object invoke(Object obj3) {
                        return FirOverloadByLambdaReturnTypeResolver.a(coneSubstitutor3, (ConeKotlinType) obj3);
                    }
                });
            }
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            LinkedHashSet linkedHashSet2 = new LinkedHashSet();
            for (Candidate candidate7 : candidates) {
                if (candidate7.isSuccessful()) {
                    linkedHashSet2.add(candidate7);
                } else {
                    linkedHashSet.add(candidate7);
                }
            }
            if (!linkedHashSet2.isEmpty()) {
                linkedHashSet = linkedHashSet2;
            }
            return linkedHashSet;
        } finally {
            t.replaceCalleeReference(calleeReference);
        }
    }

    private final FirCallCompleter getCallCompleter() {
        return this.components.getCallCompleter();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private final <T extends FirExpression & FirResolvable> Set<Candidate> reduceCandidatesImpl(T call, Set<Candidate> reducedCandidates, Collection<Candidate> allCandidates) throws KotlinIllegalArgumentExceptionWithAttachments {
        ArrayList arrayList = new ArrayList();
        for (Object obj : allCandidates) {
            FirDeclaration fir = ((Candidate) obj).getSymbol().getFir();
            fir.getClass();
            List<FirAnnotation> annotations = fir.getAnnotations();
            if (!(annotations instanceof Collection) || !annotations.isEmpty()) {
                Iterator<T> it = annotations.iterator();
                while (it.hasNext()) {
                    if (Intrinsics.areEqual(ConeTypeUtilsKt.getClassId(FirTypeUtilsKt.getConeType(((FirAnnotation) it.next()).getAnnotationTypeRef())), AnnotationsForResolveKt.getOVERLOAD_RESOLUTION_BY_LAMBDA_ANNOTATION_CLASS_ID())) {
                        arrayList.add(obj);
                        break;
                    }
                }
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        Set setMinus = SetsKt.minus(reducedCandidates, arrayList);
        Set<Candidate> setAnalyzeLambdaAndReduceNumberOfCandidatesRegardingOverloadResolutionByLambdaReturnType = analyzeLambdaAndReduceNumberOfCandidatesRegardingOverloadResolutionByLambdaReturnType(call, reducedCandidates);
        if (setAnalyzeLambdaAndReduceNumberOfCandidatesRegardingOverloadResolutionByLambdaReturnType == null) {
            return null;
        }
        Set<Candidate> setChooseMaximallySpecificCandidates = this.components.getCallResolver().getConflictResolver().chooseMaximallySpecificCandidates(setAnalyzeLambdaAndReduceNumberOfCandidatesRegardingOverloadResolutionByLambdaReturnType);
        if (setChooseMaximallySpecificCandidates.size() > 1) {
            Set set = setMinus;
            if ((set instanceof Collection) && set.isEmpty()) {
                return setChooseMaximallySpecificCandidates;
            }
            Iterator it2 = set.iterator();
            while (it2.hasNext()) {
                if (setChooseMaximallySpecificCandidates.contains((Candidate) it2.next())) {
                    setChooseMaximallySpecificCandidates = CollectionsKt.toMutableSet(setChooseMaximallySpecificCandidates);
                    setChooseMaximallySpecificCandidates.removeAll(arrayList);
                    Candidate candidate = (Candidate) CollectionsKt.singleOrNull(setChooseMaximallySpecificCandidates);
                    if (candidate == null) {
                        break;
                    }
                    candidate.addDiagnostic(CandidateChosenUsingOverloadResolutionByLambdaAnnotation.INSTANCE);
                    break;
                }
            }
        }
        return setChooseMaximallySpecificCandidates;
    }

    public final FirAbstractBodyResolveTransformer.BodyResolveTransformerComponents getComponents() {
        return this.components;
    }

    public final <T extends FirExpression & FirResolvable> Set<Candidate> reduceCandidates(T qualifiedAccess, Collection<Candidate> allCandidates, Set<Candidate> bestCandidates) {
        Set<Candidate> setReduceCandidatesImpl;
        qualifiedAccess.getClass();
        allCandidates.getClass();
        bestCandidates.getClass();
        return (bestCandidates.size() > 1 && (setReduceCandidatesImpl = reduceCandidatesImpl(qualifiedAccess, bestCandidates, allCandidates)) != null) ? setReduceCandidatesImpl : bestCandidates;
    }
}
