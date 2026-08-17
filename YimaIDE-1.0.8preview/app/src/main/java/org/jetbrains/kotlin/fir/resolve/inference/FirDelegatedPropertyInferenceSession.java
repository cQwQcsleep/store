package org.jetbrains.kotlin.fir.resolve.inference;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.arguments.K2JsArgumentConstants;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirResolvable;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.resolve.BodyResolveComponents;
import org.jetbrains.kotlin.fir.resolve.ResolutionMode;
import org.jetbrains.kotlin.fir.resolve.ResolveUtilsKt;
import org.jetbrains.kotlin.fir.resolve.calls.ConeAtomWithCandidate;
import org.jetbrains.kotlin.fir.resolve.calls.ConePostponedResolvedAtom;
import org.jetbrains.kotlin.fir.resolve.calls.ConeResolutionAtom;
import org.jetbrains.kotlin.fir.resolve.calls.InferenceError;
import org.jetbrains.kotlin.fir.resolve.calls.ResolutionContext;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CallInfo;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CandidateFactoryKt;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CandidateTraversalKt;
import org.jetbrains.kotlin.fir.resolve.inference.CollectionLiteralBounds;
import org.jetbrains.kotlin.fir.resolve.inference.FirDelegatedPropertyInferenceSession;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.resolve.transformers.FirCallCompletionResultsWriterTransformer;
import org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.BodyResolveContext;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.fir.visitors.FirTransformerUtilKt;
import org.jetbrains.kotlin.resolve.calls.inference.components.ConstraintSystemCompletionMode;
import org.jetbrains.kotlin.resolve.calls.inference.model.ConstraintStorage;
import org.jetbrains.kotlin.resolve.calls.inference.model.ConstraintSystemError;
import org.jetbrains.kotlin.resolve.calls.inference.model.NewConstraintSystemImpl;
import org.jetbrains.kotlin.util.OperatorNameConventions;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\u001a\u0010&\u001a\u0004\u0018\u00010 2\u0006\u0010\u0013\u001a\u00020\u000e2\u0006\u0010'\u001a\u00020(H\u0016J\u0012\u0010)\u001a\u0004\u0018\u00010*2\u0006\u0010+\u001a\u00020\rH\u0016J3\u0010,\u001a\u00020-\"\f\b\u0000\u0010.*\u00020\r*\u00020\u00072\u0006\u0010+\u001a\u0002H.2\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u00020*H\u0016¢\u0006\u0002\u00102J#\u00103\u001a\u00020-\"\f\b\u0000\u0010.*\u00020\r*\u0002042\u0006\u0010+\u001a\u0002H.H\u0002¢\u0006\u0002\u00105J\u001f\u00106\u001a\u00020\u0018\"\f\b\u0000\u0010.*\u00020\r*\u000204*\u0002H.H\u0002¢\u0006\u0002\u00107J\u001a\u00108\u001a\u00020-2\u0012\u00109\u001a\u000e\u0012\u0004\u0012\u00020;\u0012\u0004\u0012\u00020-0:J\u000e\u0010<\u001a\b\u0012\u0004\u0012\u00020\r0=H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\n\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\u00020\u00108BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0018\u0010\u0013\u001a\u00020\u000e*\u00020\r8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0017\u001a\u00020\u00188F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u001f\u001a\u00020 8F¢\u0006\u0006\u001a\u0004\b!\u0010\"R\u000e\u0010#\u001a\u00020$X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006>"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/inference/FirDelegatedPropertyInferenceSession;", "Lorg/jetbrains/kotlin/fir/resolve/inference/FirInferenceSession;", "resolutionContext", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;", "callCompleter", "Lorg/jetbrains/kotlin/fir/resolve/inference/FirCallCompleter;", "delegateExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;Lorg/jetbrains/kotlin/fir/resolve/inference/FirCallCompleter;Lorg/jetbrains/kotlin/fir/expressions/FirExpression;)V", "partiallyResolvedCalls", Argument.Delimiters.none, "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/fir/expressions/FirResolvable;", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "components", "Lorg/jetbrains/kotlin/fir/resolve/BodyResolveComponents;", "getComponents", "()Lorg/jetbrains/kotlin/fir/resolve/BodyResolveComponents;", "candidate", "getCandidate", "(Lorg/jetbrains/kotlin/fir/expressions/FirResolvable;)Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "nonTrivialParentSession", "parentSessionIsNonTrivial", Argument.Delimiters.none, "getParentSessionIsNonTrivial", "()Z", "delegateCandidate", "parentConstraintSystem", "Lorg/jetbrains/kotlin/resolve/calls/inference/model/NewConstraintSystemImpl;", "currentConstraintSystem", "currentConstraintStorage", "Lorg/jetbrains/kotlin/resolve/calls/inference/model/ConstraintStorage;", "getCurrentConstraintStorage", "()Lorg/jetbrains/kotlin/resolve/calls/inference/model/ConstraintStorage;", "unitType", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "wasCompletionRun", "baseConstraintStorageForCandidate", "bodyResolveContext", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/BodyResolveContext;", "customCompletionModeInsteadOfFull", "Lorg/jetbrains/kotlin/resolve/calls/inference/components/ConstraintSystemCompletionMode;", K2JsArgumentConstants.CALL, "processPartiallyResolvedCall", Argument.Delimiters.none, "T", "resolutionMode", "Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode;", "completionMode", "(Lorg/jetbrains/kotlin/fir/expressions/FirExpression;Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode;Lorg/jetbrains/kotlin/resolve/calls/inference/components/ConstraintSystemCompletionMode;)V", "requireCallIsDelegateOperator", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "(Lorg/jetbrains/kotlin/fir/expressions/FirResolvable;)V", "isProvideDelegate", "(Lorg/jetbrains/kotlin/fir/expressions/FirResolvable;)Z", "completeSessionOrPostponeIfNonRoot", "onCompletionResultsWriting", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "completeCandidatesForRootSession", Argument.Delimiters.none, "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirDelegatedPropertyInferenceSession extends FirInferenceSession {
    private final FirCallCompleter callCompleter;
    private final NewConstraintSystemImpl currentConstraintSystem;
    private final Candidate delegateCandidate;
    private final FirExpression delegateExpression;
    private final FirInferenceSession nonTrivialParentSession;
    private final NewConstraintSystemImpl parentConstraintSystem;
    private final List<Pair<FirResolvable, Candidate>> partiallyResolvedCalls;
    private final ResolutionContext resolutionContext;
    private final ConeClassLikeType unitType;
    private boolean wasCompletionRun;

    /* JADX WARN: Multi-variable type inference failed */
    public FirDelegatedPropertyInferenceSession(ResolutionContext resolutionContext, FirCallCompleter firCallCompleter, FirExpression firExpression) {
        NewConstraintSystemImpl currentCommonSystem;
        resolutionContext.getClass();
        firCallCompleter.getClass();
        this.resolutionContext = resolutionContext;
        this.callCompleter = firCallCompleter;
        this.delegateExpression = firExpression;
        this.partiallyResolvedCalls = new ArrayList();
        FirInferenceSession inferenceSession = resolutionContext.getBodyResolveContext().getInferenceSession();
        this.nonTrivialParentSession = inferenceSession == FirInferenceSession.INSTANCE.getDEFAULT() ? null : inferenceSession;
        FirResolvable firResolvable = firExpression instanceof FirResolvable ? (FirResolvable) firExpression : null;
        Candidate candidate = firResolvable != null ? CandidateFactoryKt.candidate(firResolvable) : null;
        this.delegateCandidate = candidate;
        if (candidate == null || (currentCommonSystem = candidate.getSystem()) == null) {
            FirInferenceSession inferenceSession2 = resolutionContext.getBodyResolveContext().getInferenceSession();
            FirPCLAInferenceSession firPCLAInferenceSession = inferenceSession2 instanceof FirPCLAInferenceSession ? (FirPCLAInferenceSession) inferenceSession2 : null;
            currentCommonSystem = firPCLAInferenceSession != null ? firPCLAInferenceSession.getCurrentCommonSystem() : InferenceComponents.createConstraintSystem$default(InferenceComponentsKt.getInferenceComponents(getComponents().getSession()), null, 1, null);
        }
        this.parentConstraintSystem = currentCommonSystem;
        this.currentConstraintSystem = FirInferenceSession.prepareSharedBaseSystem(currentCommonSystem, InferenceComponentsKt.getInferenceComponents(getComponents().getSession()));
        this.unitType = getComponents().getSession().getBuiltinTypes().getUnitType().getConeType();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final List<FirResolvable> completeCandidatesForRootSession() {
        ConeAtomWithCandidate coneAtomWithCandidate;
        Candidate candidate;
        final NewConstraintSystemImpl newConstraintSystemImpl = this.parentConstraintSystem;
        newConstraintSystemImpl.prepareForGlobalCompletion();
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        FirAnnotationContainer firAnnotationContainer = this.delegateExpression;
        if ((firAnnotationContainer instanceof FirResolvable) && (candidate = CandidateFactoryKt.candidate((FirResolvable) firAnnotationContainer)) != null) {
            listCreateListBuilder.add(new ConeAtomWithCandidate(this.delegateExpression, candidate));
        }
        List list = listCreateListBuilder;
        Iterator<T> it = this.partiallyResolvedCalls.iterator();
        while (it.hasNext()) {
            FirResolvable firResolvable = (FirResolvable) ((Pair) it.next()).component1();
            Candidate candidate2 = CandidateFactoryKt.candidate(firResolvable);
            if (candidate2 == null) {
                coneAtomWithCandidate = null;
            } else {
                firResolvable.getClass();
                coneAtomWithCandidate = new ConeAtomWithCandidate((FirExpression) firResolvable, candidate2);
            }
            if (coneAtomWithCandidate != null) {
                list.add(coneAtomWithCandidate);
            }
        }
        final List<? extends ConeResolutionAtom> listBuild = CollectionsKt.build(listCreateListBuilder);
        BodyResolveContext bodyResolveContext = this.resolutionContext.getBodyResolveContext();
        FirInferenceSession firInferenceSession = FirInferenceSession.INSTANCE.getDEFAULT();
        FirInferenceSession inferenceSession = bodyResolveContext.getInferenceSession();
        bodyResolveContext.setInferenceSession(firInferenceSession);
        try {
            getComponents().getCallCompleter().getCompleter().complete(newConstraintSystemImpl.asConstraintSystemCompleterContext(), ConstraintSystemCompletionMode.FULL, listBuild, this.unitType, this.resolutionContext, new ConstraintSystemCompleter.PostponedAtomAnalyzer() { // from class: w25
                @Override // org.jetbrains.kotlin.fir.resolve.inference.ConstraintSystemCompleter.PostponedAtomAnalyzer
                public final void analyzeInternal(ConePostponedResolvedAtom conePostponedResolvedAtom, boolean z, CollectionLiteralBounds collectionLiteralBounds) {
                    FirDelegatedPropertyInferenceSession.completeCandidatesForRootSession$lambda$2$0(listBuild, this, newConstraintSystemImpl, conePostponedResolvedAtom, z, collectionLiteralBounds);
                }
            });
            Unit unit = Unit.INSTANCE;
            bodyResolveContext.setInferenceSession(inferenceSession);
            List<? extends ConeResolutionAtom> list2 = listBuild;
            ArrayList<Candidate> arrayList = new ArrayList();
            Iterator<T> it2 = list2.iterator();
            while (it2.hasNext()) {
                FirAnnotationContainer expression = ((ConeAtomWithCandidate) it2.next()).getExpression();
                expression.getClass();
                Candidate candidate3 = CandidateFactoryKt.candidate((FirResolvable) expression);
                if (candidate3 != null) {
                    arrayList.add(candidate3);
                }
            }
            for (Candidate candidate4 : arrayList) {
                Iterator it3 = newConstraintSystemImpl.getErrors().iterator();
                while (it3.hasNext()) {
                    candidate4.addDiagnostic(new InferenceError((ConstraintSystemError) it3.next()));
                }
            }
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
            Iterator<T> it4 = list2.iterator();
            while (it4.hasNext()) {
                FirAnnotationContainer expression2 = ((ConeAtomWithCandidate) it4.next()).getExpression();
                expression2.getClass();
                arrayList2.add((FirResolvable) expression2);
            }
            return arrayList2;
        } catch (Throwable th) {
            bodyResolveContext.setInferenceSession(inferenceSession);
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void completeCandidatesForRootSession$lambda$2$0(List list, FirDelegatedPropertyInferenceSession firDelegatedPropertyInferenceSession, NewConstraintSystemImpl newConstraintSystemImpl, final ConePostponedResolvedAtom conePostponedResolvedAtom, boolean z, CollectionLiteralBounds collectionLiteralBounds) {
        conePostponedResolvedAtom.getClass();
        for (ConeAtomWithCandidate coneAtomWithCandidate : CollectionsKt.asReversed(list)) {
            final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
            CandidateTraversalKt.processPostponedAtoms(coneAtomWithCandidate, new Function1() { // from class: v25
                public final Object invoke(Object obj) {
                    return FirDelegatedPropertyInferenceSession.completeCandidatesForRootSession$lambda$2$0$0$0(booleanRef, conePostponedResolvedAtom, (ConePostponedResolvedAtom) obj);
                }
            });
            if (booleanRef.element) {
                firDelegatedPropertyInferenceSession.callCompleter.createPostponedArgumentsAnalyzer(firDelegatedPropertyInferenceSession.resolutionContext).analyze(newConstraintSystemImpl, conePostponedResolvedAtom, coneAtomWithCandidate.getCandidate(), z, collectionLiteralBounds);
                return;
            }
        }
        hb9.a("Collection contains no element matching the predicate.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit completeCandidatesForRootSession$lambda$2$0$0$0(Ref.BooleanRef booleanRef, ConePostponedResolvedAtom conePostponedResolvedAtom, ConePostponedResolvedAtom conePostponedResolvedAtom2) {
        conePostponedResolvedAtom2.getClass();
        booleanRef.element = booleanRef.element || Intrinsics.areEqual(conePostponedResolvedAtom2, conePostponedResolvedAtom);
        return Unit.INSTANCE;
    }

    private final Candidate getCandidate(FirResolvable firResolvable) {
        Candidate candidate = CandidateFactoryKt.candidate(firResolvable);
        candidate.getClass();
        return candidate;
    }

    private final BodyResolveComponents getComponents() {
        return this.resolutionContext.getBodyResolveComponents();
    }

    private final <T extends FirResolvable & FirStatement> boolean isProvideDelegate(T t) {
        CallInfo callInfo;
        if (!FirDelegatedPropertyInferenceSessionKt.isAnyOfDelegateOperators(t)) {
            return false;
        }
        t.getClass();
        Candidate candidate = CandidateFactoryKt.candidate(t);
        return Intrinsics.areEqual((candidate == null || (callInfo = candidate.getCallInfo()) == null) ? null : callInfo.getName(), OperatorNameConventions.PROVIDE_DELEGATE);
    }

    private final <T extends FirResolvable & FirStatement> void requireCallIsDelegateOperator(T call) {
        if (FirDelegatedPropertyInferenceSessionKt.isAnyOfDelegateOperators(call)) {
            return;
        }
        wec.a("Unexpected ", UtilsKt.render(call), " call");
    }

    @Override // org.jetbrains.kotlin.fir.resolve.inference.FirInferenceSession
    public ConstraintStorage baseConstraintStorageForCandidate(Candidate candidate, BodyResolveContext bodyResolveContext) {
        candidate.getClass();
        bodyResolveContext.getClass();
        if (this.wasCompletionRun || !FirDelegatedPropertyInferenceSessionKt.isAnyOfDelegateOperators(candidate.getCallInfo().getCallSite())) {
            return null;
        }
        return getCurrentConstraintStorage();
    }

    public final void completeSessionOrPostponeIfNonRoot(Function1<? super ConeSubstitutor, Unit> onCompletionResultsWriting) {
        onCompletionResultsWriting.getClass();
        if (this.wasCompletionRun) {
            k2d.a("Check failed.");
            return;
        }
        this.wasCompletionRun = true;
        this.parentConstraintSystem.replaceContentWith(getCurrentConstraintStorage());
        FirInferenceSession firInferenceSession = this.nonTrivialParentSession;
        FirPCLAInferenceSession firPCLAInferenceSession = firInferenceSession instanceof FirPCLAInferenceSession ? (FirPCLAInferenceSession) firInferenceSession : null;
        if (firPCLAInferenceSession == null) {
            List<FirResolvable> listCompleteCandidatesForRootSession = completeCandidatesForRootSession();
            ConeSubstitutor coneSubstitutor = (ConeSubstitutor) org.jetbrains.kotlin.resolve.calls.inference.InferenceUtilsKt.buildAbstractResultingSubstitutor$default(this.parentConstraintSystem.asReadOnlyStorage(), TypeComponentsKt.getTypeContext(getComponents().getSession()), false, 2, (Object) null);
            FirCallCompletionResultsWriterTransformer firCallCompletionResultsWriterTransformerCreateCompletionResultsWriter = this.callCompleter.createCompletionResultsWriter(coneSubstitutor, FirCallCompletionResultsWriterTransformer.Mode.DelegatedPropertyCompletion);
            Iterator<T> it = listCompleteCandidatesForRootSession.iterator();
            while (it.hasNext()) {
                FirTransformerUtilKt.transformSingle((FirResolvable) it.next(), firCallCompletionResultsWriterTransformerCreateCompletionResultsWriter, null);
            }
            onCompletionResultsWriting.invoke(coneSubstitutor);
            return;
        }
        Candidate candidate = this.delegateCandidate;
        if (candidate != null) {
            FirExpression firExpression = this.delegateExpression;
            if (!(firExpression instanceof FirResolvable)) {
                w01.a("Failed requirement.");
                return;
            }
            FirCallCompleter.runCompletionForCall$default(this.callCompleter, candidate, ConstraintSystemCompletionMode.PCLA_POSTPONED_CALL, firExpression, ResolveUtilsKt.initialTypeOfCandidate(getComponents(), this.delegateCandidate), null, 16, null);
        }
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        List list = listCreateListBuilder;
        org.jetbrains.kotlin.utils.CollectionsKt.addIfNotNull(list, ConeResolutionAtom.INSTANCE.createRawAtomNullable(this.delegateExpression));
        Iterator<T> it2 = this.partiallyResolvedCalls.iterator();
        while (it2.hasNext()) {
            Pair pair = (Pair) it2.next();
            Object obj = (FirResolvable) pair.component1();
            Candidate candidate2 = (Candidate) pair.component2();
            obj.getClass();
            list.add(new ConeAtomWithCandidate((FirExpression) obj, candidate2));
        }
        firPCLAInferenceSession.integrateChildSession(CollectionsKt.build(listCreateListBuilder), this.parentConstraintSystem.currentStorage(), onCompletionResultsWriting);
    }

    @Override // org.jetbrains.kotlin.fir.resolve.inference.FirInferenceSession
    public ConstraintSystemCompletionMode customCompletionModeInsteadOfFull(FirResolvable call) {
        call.getClass();
        if (!FirDelegatedPropertyInferenceSessionKt.isAnyOfDelegateOperators(call) || this.wasCompletionRun) {
            return null;
        }
        return ConstraintSystemCompletionMode.PARTIAL;
    }

    public final ConstraintStorage getCurrentConstraintStorage() {
        return this.currentConstraintSystem.currentStorage();
    }

    public final boolean getParentSessionIsNonTrivial() {
        return this.nonTrivialParentSession != null;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.inference.FirInferenceSession
    public <T extends FirExpression & FirResolvable> void processPartiallyResolvedCall(T call, ResolutionMode resolutionMode, ConstraintSystemCompletionMode completionMode) {
        call.getClass();
        resolutionMode.getClass();
        completionMode.getClass();
        if (this.wasCompletionRun || !FirDelegatedPropertyInferenceSessionKt.isAnyOfDelegateOperators(call)) {
            return;
        }
        T t = call;
        requireCallIsDelegateOperator(t);
        Candidate candidate = getCandidate(t);
        if (!isProvideDelegate(t) || candidate.isSuccessful()) {
            NewConstraintSystemImpl system = candidate.getSystem();
            this.partiallyResolvedCalls.add(TuplesKt.to(call, candidate));
            this.currentConstraintSystem.replaceContentWith(system.currentStorage());
        }
    }
}
