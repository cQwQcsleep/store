package org.jetbrains.kotlin.fir.resolve.calls.candidate;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.expressions.FirResolvable;
import org.jetbrains.kotlin.fir.references.FirReference;
import org.jetbrains.kotlin.fir.resolve.calls.ConeAtomWithCandidate;
import org.jetbrains.kotlin.fir.resolve.calls.ConeResolutionAtom;
import org.jetbrains.kotlin.fir.resolve.calls.ConeResolutionAtomWithSingleChild;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CandidateFactoryKt;
import org.jetbrains.kotlin.resolve.calls.components.PostponedArgumentsAnalyzerContext;
import org.jetbrains.kotlin.resolve.calls.inference.model.ConstraintStorage;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a$\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005H\u0002\u001a\u0012\u0010\b\u001a\u00020\u0001*\u00020\t2\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u000e\u0010\n\u001a\u0004\u0018\u00010\u000b*\u00020\fH\u0000¨\u0006\r"}, d2 = {"processConstraintStorageFromAtom", Argument.Delimiters.none, "atom", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolutionAtom;", "processor", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/resolve/calls/inference/model/ConstraintStorage;", Argument.Delimiters.none, "addSubsystemFromAtom", "Lorg/jetbrains/kotlin/resolve/calls/components/PostponedArgumentsAnalyzerContext;", "candidate", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "Lorg/jetbrains/kotlin/fir/expressions/FirResolvable;", "org.jetbrains.kotlin:resolve"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CandidateFactoryKt {
    public static Unit a(PostponedArgumentsAnalyzerContext postponedArgumentsAnalyzerContext, ConstraintStorage constraintStorage) {
        constraintStorage.getClass();
        if (!constraintStorage.getUsesOuterCs()) {
            postponedArgumentsAnalyzerContext.addOtherSystem(constraintStorage);
        }
        return Unit.INSTANCE;
    }

    public static final boolean addSubsystemFromAtom(final PostponedArgumentsAnalyzerContext postponedArgumentsAnalyzerContext, ConeResolutionAtom coneResolutionAtom) {
        postponedArgumentsAnalyzerContext.getClass();
        coneResolutionAtom.getClass();
        return processConstraintStorageFromAtom(coneResolutionAtom, new Function1() { // from class: dc1
            public final Object invoke(Object obj) {
                return CandidateFactoryKt.a(postponedArgumentsAnalyzerContext, (ConstraintStorage) obj);
            }
        });
    }

    public static final Candidate candidate(FirResolvable firResolvable) {
        firResolvable.getClass();
        FirReference calleeReference = firResolvable.getCalleeReference();
        if (calleeReference instanceof FirNamedReferenceWithCandidate) {
            return ((FirNamedReferenceWithCandidate) calleeReference).getCandidate();
        }
        return null;
    }

    private static final boolean processConstraintStorageFromAtom(ConeResolutionAtom coneResolutionAtom, Function1<? super ConstraintStorage, Unit> function1) {
        ConeResolutionAtom subAtom;
        if (coneResolutionAtom instanceof ConeAtomWithCandidate) {
            function1.invoke(((ConeAtomWithCandidate) coneResolutionAtom).getCandidate().getSystem().asReadOnlyStorage());
            return true;
        }
        if (!(coneResolutionAtom instanceof ConeResolutionAtomWithSingleChild) || (subAtom = ((ConeResolutionAtomWithSingleChild) coneResolutionAtom).getSubAtom()) == null) {
            return false;
        }
        return processConstraintStorageFromAtom(subAtom, function1);
    }
}
