package org.jetbrains.kotlin.fir.resolve.calls.stages;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.resolve.calls.ArgumentTypeMismatch;
import org.jetbrains.kotlin.fir.resolve.calls.ConeLambdaWithTypeVariableAsExpectedTypeAtom;
import org.jetbrains.kotlin.fir.resolve.calls.ConePostponedResolvedAtom;
import org.jetbrains.kotlin.fir.resolve.calls.ResolutionContext;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CheckerSink;
import org.jetbrains.kotlin.fir.resolve.inference.ConstraintSystemCompleterKt;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.FunctionalTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeConstructionUtilsKt;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.resolve.calls.inference.ConstraintSystemBuilderKt;
import org.jetbrains.kotlin.resolve.calls.inference.model.Constraint;
import org.jetbrains.kotlin.resolve.calls.inference.model.NewConstraintSystemImpl;
import org.jetbrains.kotlin.resolve.calls.inference.model.VariableWithConstraints;
import org.jetbrains.kotlin.types.model.TypeSystemContextHelpersKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J.\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0096@R\u00020\u0006R\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006j\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\fJ1\u0010\r\u001a\u00020\u0005*\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002R\u00020\u0006R\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006j\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u0011J%\u0010\u0012\u001a\u00020\u0013*\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u0014¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/stages/CheckLambdaAgainstTypeVariableContradiction;", "Lorg/jetbrains/kotlin/fir/resolve/calls/stages/ResolutionStage;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CheckerSink;", "sink", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;", "context", "candidate", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "(Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CheckerSink;Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "checkForContradiction", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConeLambdaWithTypeVariableAsExpectedTypeAtom;", "csBuilder", "Lorg/jetbrains/kotlin/resolve/calls/inference/model/NewConstraintSystemImpl;", "(Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CheckerSink;Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;Lorg/jetbrains/kotlin/fir/resolve/calls/ConeLambdaWithTypeVariableAsExpectedTypeAtom;Lorg/jetbrains/kotlin/resolve/calls/inference/model/NewConstraintSystemImpl;)V", "hasFunctionTypeConstraint", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;Lorg/jetbrains/kotlin/fir/resolve/calls/ConeLambdaWithTypeVariableAsExpectedTypeAtom;Lorg/jetbrains/kotlin/resolve/calls/inference/model/NewConstraintSystemImpl;)Z", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CheckLambdaAgainstTypeVariableContradiction extends ResolutionStage {
    public static final CheckLambdaAgainstTypeVariableContradiction INSTANCE = new CheckLambdaAgainstTypeVariableContradiction();

    private CheckLambdaAgainstTypeVariableContradiction() {
    }

    private final void checkForContradiction(CheckerSink checkerSink, ResolutionContext resolutionContext, ConeLambdaWithTypeVariableAsExpectedTypeAtom coneLambdaWithTypeVariableAsExpectedTypeAtom, NewConstraintSystemImpl newConstraintSystemImpl) {
        if (hasFunctionTypeConstraint(resolutionContext, coneLambdaWithTypeVariableAsExpectedTypeAtom, newConstraintSystemImpl)) {
            return;
        }
        ConeClassLikeType coneClassLikeTypeConstructClassLikeType$default = TypeConstructionUtilsKt.constructClassLikeType$default(StandardClassIds.INSTANCE.getFunction(), new ConeClassLikeType[]{resolutionContext.getSession().getBuiltinTypes().getNothingType().getConeType()}, false, null, 6, null);
        if (ConstraintSystemBuilderKt.isSubtypeConstraintCompatible(newConstraintSystemImpl, coneClassLikeTypeConstructClassLikeType$default, coneLambdaWithTypeVariableAsExpectedTypeAtom.mo581getExpectedType())) {
            return;
        }
        checkerSink.reportDiagnostic(new ArgumentTypeMismatch(coneLambdaWithTypeVariableAsExpectedTypeAtom.mo581getExpectedType(), coneClassLikeTypeConstructClassLikeType$default, coneLambdaWithTypeVariableAsExpectedTypeAtom.getExpression(), false, null, false, 48, null));
    }

    private final boolean hasFunctionTypeConstraint(ResolutionContext resolutionContext, ConeLambdaWithTypeVariableAsExpectedTypeAtom coneLambdaWithTypeVariableAsExpectedTypeAtom, NewConstraintSystemImpl newConstraintSystemImpl) {
        VariableWithConstraints variableWithConstraints = (VariableWithConstraints) newConstraintSystemImpl.currentStorage().getNotFixedTypeVariables().get(TypeSystemContextHelpersKt.typeConstructor(coneLambdaWithTypeVariableAsExpectedTypeAtom.mo581getExpectedType(), resolutionContext.getTypeContext()));
        if (variableWithConstraints == null) {
            return false;
        }
        List constraints = variableWithConstraints.getConstraints();
        if ((constraints instanceof Collection) && constraints.isEmpty()) {
            return false;
        }
        Iterator it = constraints.iterator();
        while (it.hasNext()) {
            if (FunctionalTypeUtilsKt.isSomeFunctionType(((Constraint) it.next()).getType(), resolutionContext.getSession())) {
                return true;
            }
        }
        return false;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.stages.ResolutionStage
    public Object check(CheckerSink checkerSink, ResolutionContext resolutionContext, Candidate candidate, Continuation<? super Unit> continuation) {
        if (!LanguageVersionUtilsKt.isEnabled(resolutionContext, LanguageFeature.CheckLambdaAgainstTypeVariableContradictionInResolution)) {
            return Unit.INSTANCE;
        }
        NewConstraintSystemImpl csBuilder = ConstraintSystemCompleterKt.getCsBuilder(candidate);
        if (csBuilder.getHasContradiction()) {
            return Unit.INSTANCE;
        }
        for (ConePostponedResolvedAtom conePostponedResolvedAtom : candidate.getPostponedAtoms()) {
            if (conePostponedResolvedAtom instanceof ConeLambdaWithTypeVariableAsExpectedTypeAtom) {
                checkForContradiction(checkerSink, resolutionContext, (ConeLambdaWithTypeVariableAsExpectedTypeAtom) conePostponedResolvedAtom, csBuilder);
            }
        }
        return Unit.INSTANCE;
    }
}
