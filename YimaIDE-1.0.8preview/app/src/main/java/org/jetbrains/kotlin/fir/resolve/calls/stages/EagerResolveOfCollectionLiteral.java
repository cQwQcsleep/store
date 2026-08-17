package org.jetbrains.kotlin.fir.resolve.calls.stages;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.resolve.CollectionLiteralOuterCandidateContext;
import org.jetbrains.kotlin.fir.resolve.CollectionLiteralResolutionKt;
import org.jetbrains.kotlin.fir.resolve.CollectionLiteralResolutionUtilsKt;
import org.jetbrains.kotlin.fir.resolve.calls.ConeCollectionLiteralAtom;
import org.jetbrains.kotlin.fir.resolve.calls.ConePostponedResolvedAtom;
import org.jetbrains.kotlin.fir.resolve.calls.ResolutionContext;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CheckerSink;
import org.jetbrains.kotlin.fir.resolve.inference.CollectionLiteralBounds;
import org.jetbrains.kotlin.fir.types.ConeInferenceContext;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.types.model.TypeSystemContextContextualKt;
import org.jetbrains.kotlin.types.model.TypeVariableTypeConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J.\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0096@R\u00020\u0006R\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006j\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/stages/EagerResolveOfCollectionLiteral;", "Lorg/jetbrains/kotlin/fir/resolve/calls/stages/ResolutionStage;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CheckerSink;", "sink", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;", "context", "candidate", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "(Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CheckerSink;Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class EagerResolveOfCollectionLiteral extends ResolutionStage {
    public static final EagerResolveOfCollectionLiteral INSTANCE = new EagerResolveOfCollectionLiteral();

    private EagerResolveOfCollectionLiteral() {
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.stages.ResolutionStage
    public Object check(CheckerSink checkerSink, ResolutionContext resolutionContext, Candidate candidate, Continuation<? super Unit> continuation) {
        ConeKotlinType coneKotlinTypeMo581getExpectedType;
        ConeInferenceContext typeContext = resolutionContext.getTypeContext();
        CollectionLiteralOuterCandidateContext collectionLiteralOuterCandidateContext = new CollectionLiteralOuterCandidateContext(candidate, checkerSink);
        if (candidate.getPostponedAtoms().isEmpty()) {
            return Unit.INSTANCE;
        }
        for (ConePostponedResolvedAtom conePostponedResolvedAtom : candidate.getPostponedAtoms()) {
            if (conePostponedResolvedAtom instanceof ConeCollectionLiteralAtom) {
                ConeCollectionLiteralAtom coneCollectionLiteralAtom = (ConeCollectionLiteralAtom) conePostponedResolvedAtom;
                if (!coneCollectionLiteralAtom.getAnalyzed() && (coneKotlinTypeMo581getExpectedType = coneCollectionLiteralAtom.mo581getExpectedType()) != null) {
                    if (TypeSystemContextContextualKt.typeConstructor(typeContext, coneKotlinTypeMo581getExpectedType) instanceof TypeVariableTypeConstructorMarker) {
                        coneKotlinTypeMo581getExpectedType = null;
                    }
                    if (coneKotlinTypeMo581getExpectedType != null) {
                        CollectionLiteralBounds.NonTvExpected nonTvExpected = new CollectionLiteralBounds.NonTvExpected(coneCollectionLiteralAtom, CollectionLiteralResolutionUtilsKt.getClassRepresentativeForCollectionLiteralResolution(resolutionContext, coneKotlinTypeMo581getExpectedType));
                        coneCollectionLiteralAtom.setAnalyzed(true);
                        CollectionLiteralResolutionKt.runCollectionLiteralResolution(resolutionContext, collectionLiteralOuterCandidateContext, coneCollectionLiteralAtom, nonTvExpected);
                    }
                }
            }
        }
        return Unit.INSTANCE;
    }
}
