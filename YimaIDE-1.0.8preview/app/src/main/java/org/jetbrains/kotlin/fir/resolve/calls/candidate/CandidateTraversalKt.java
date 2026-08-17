package org.jetbrains.kotlin.fir.resolve.calls.candidate;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.resolve.calls.ConeAtomWithCandidate;
import org.jetbrains.kotlin.fir.resolve.calls.ConeCollectionLiteralAtom;
import org.jetbrains.kotlin.fir.resolve.calls.ConeContextSensitiveAlternativeForQualifierAtom;
import org.jetbrains.kotlin.fir.resolve.calls.ConeLambdaWithTypeVariableAsExpectedTypeAtom;
import org.jetbrains.kotlin.fir.resolve.calls.ConePostponedResolvedAtom;
import org.jetbrains.kotlin.fir.resolve.calls.ConeResolutionAtom;
import org.jetbrains.kotlin.fir.resolve.calls.ConeResolutionAtomWithPostponedChild;
import org.jetbrains.kotlin.fir.resolve.calls.ConeResolutionAtomWithSingleChild;
import org.jetbrains.kotlin.fir.resolve.calls.ConeResolvedCallableReferenceAtom;
import org.jetbrains.kotlin.fir.resolve.calls.ConeResolvedLambdaAtom;
import org.jetbrains.kotlin.fir.resolve.calls.ConeSimpleLeafResolutionAtom;
import org.jetbrains.kotlin.fir.resolve.calls.ConeSimpleNameForContextSensitiveResolution;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CandidateTraversalKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a2\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u00042\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\u0004\u001a\u001e\u0010\b\u001a\u00020\u0001*\u00020\u00022\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\u0004\u001a\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0002H\u0002¨\u0006\u000b"}, d2 = {"processCandidatesAndPostponedAtoms", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolutionAtom;", "candidateProcessor", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "postponedAtomsProcessor", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConePostponedResolvedAtom;", "processPostponedAtoms", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Context;", "atom", "org.jetbrains.kotlin:resolve"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CandidateTraversalKt {
    public static Unit a(Candidate candidate) {
        candidate.getClass();
        return Unit.INSTANCE;
    }

    private static final void processCandidatesAndPostponedAtoms(Context context, ConeResolutionAtom coneResolutionAtom) {
        if (coneResolutionAtom == null || !context.getVisited().add(coneResolutionAtom) || (coneResolutionAtom instanceof ConeSimpleLeafResolutionAtom)) {
            return;
        }
        if (coneResolutionAtom instanceof ConeResolvedLambdaAtom) {
            context.getPostponedAtomsProcessor().invoke(coneResolutionAtom);
            ConeResolvedLambdaAtom coneResolvedLambdaAtom = (ConeResolvedLambdaAtom) coneResolutionAtom;
            if (coneResolvedLambdaAtom.getAnalyzed()) {
                Iterator<ConeResolutionAtom> it = coneResolvedLambdaAtom.getReturnStatements().iterator();
                while (it.hasNext()) {
                    processCandidatesAndPostponedAtoms(context, it.next());
                }
                return;
            }
            return;
        }
        if (coneResolutionAtom instanceof ConeLambdaWithTypeVariableAsExpectedTypeAtom) {
            context.getPostponedAtomsProcessor().invoke(coneResolutionAtom);
            processCandidatesAndPostponedAtoms(context, ((ConeLambdaWithTypeVariableAsExpectedTypeAtom) coneResolutionAtom).getSubAtom());
            return;
        }
        if (coneResolutionAtom instanceof ConeResolvedCallableReferenceAtom) {
            context.getPostponedAtomsProcessor().invoke(coneResolutionAtom);
            processCandidatesAndPostponedAtoms(context, ((ConeResolvedCallableReferenceAtom) coneResolutionAtom).getSubAtom());
            return;
        }
        if (coneResolutionAtom instanceof ConeSimpleNameForContextSensitiveResolution) {
            context.getPostponedAtomsProcessor().invoke(coneResolutionAtom);
            return;
        }
        if (coneResolutionAtom instanceof ConeContextSensitiveAlternativeForQualifierAtom) {
            context.getPostponedAtomsProcessor().invoke(coneResolutionAtom);
            return;
        }
        if (coneResolutionAtom instanceof ConeCollectionLiteralAtom) {
            context.getPostponedAtomsProcessor().invoke(coneResolutionAtom);
            processCandidatesAndPostponedAtoms(context, ((ConeCollectionLiteralAtom) coneResolutionAtom).getSubAtom());
            return;
        }
        if (!(coneResolutionAtom instanceof ConeAtomWithCandidate)) {
            if (coneResolutionAtom instanceof ConeResolutionAtomWithSingleChild) {
                processCandidatesAndPostponedAtoms(context, ((ConeResolutionAtomWithSingleChild) coneResolutionAtom).getSubAtom());
                return;
            } else if (coneResolutionAtom instanceof ConeResolutionAtomWithPostponedChild) {
                processCandidatesAndPostponedAtoms(context, ((ConeResolutionAtomWithPostponedChild) coneResolutionAtom).getSubAtom());
                return;
            } else {
                bu8.a();
                return;
            }
        }
        Candidate candidate = ((ConeAtomWithCandidate) coneResolutionAtom).getCandidate();
        context.getCandidateProcessor().invoke(candidate);
        Iterator<ConeResolutionAtom> it2 = candidate.getArguments().iterator();
        while (it2.hasNext()) {
            processCandidatesAndPostponedAtoms(context, it2.next());
        }
        Iterator<ConeResolutionAtom> it3 = candidate.getPostponedPCLACalls().iterator();
        while (it3.hasNext()) {
            processCandidatesAndPostponedAtoms(context, it3.next());
        }
    }

    public static final void processPostponedAtoms(ConeResolutionAtom coneResolutionAtom, Function1<? super ConePostponedResolvedAtom, Unit> function1) {
        coneResolutionAtom.getClass();
        function1.getClass();
        processCandidatesAndPostponedAtoms(coneResolutionAtom, new Function1() { // from class: fc1
            public final Object invoke(Object obj) {
                return CandidateTraversalKt.a((Candidate) obj);
            }
        }, function1);
    }

    public static final void processCandidatesAndPostponedAtoms(ConeResolutionAtom coneResolutionAtom, Function1<? super Candidate, Unit> function1, Function1<? super ConePostponedResolvedAtom, Unit> function2) {
        coneResolutionAtom.getClass();
        function1.getClass();
        function2.getClass();
        processCandidatesAndPostponedAtoms(new Context(function1, function2), coneResolutionAtom);
    }
}
