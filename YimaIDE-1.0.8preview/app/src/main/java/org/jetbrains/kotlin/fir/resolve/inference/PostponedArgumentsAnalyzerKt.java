package org.jetbrains.kotlin.fir.resolve.inference;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.resolve.calls.ConeLambdaWithTypeVariableAsExpectedTypeAtom;
import org.jetbrains.kotlin.fir.resolve.calls.ConeResolutionAtomWithPostponedChild;
import org.jetbrains.kotlin.fir.resolve.calls.ConeResolvedLambdaAtom;
import org.jetbrains.kotlin.fir.resolve.calls.ResolutionContext;
import org.jetbrains.kotlin.fir.resolve.calls.stages.ArgumentCheckingProcessor;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.resolve.calls.inference.ConstraintSystemBuilder;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a2\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n¨\u0006\u000b"}, d2 = {"transformToResolvedLambda", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolvedLambdaAtom;", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConeLambdaWithTypeVariableAsExpectedTypeAtom;", "csBuilder", "Lorg/jetbrains/kotlin/resolve/calls/inference/ConstraintSystemBuilder;", "context", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;", "expectedType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "returnTypeVariable", "Lorg/jetbrains/kotlin/fir/resolve/inference/ConeTypeVariableForLambdaReturnType;", "org.jetbrains.kotlin:resolve"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class PostponedArgumentsAnalyzerKt {
    public static final ConeResolvedLambdaAtom transformToResolvedLambda(ConeLambdaWithTypeVariableAsExpectedTypeAtom coneLambdaWithTypeVariableAsExpectedTypeAtom, ConstraintSystemBuilder constraintSystemBuilder, ResolutionContext resolutionContext, ConeKotlinType coneKotlinType, ConeTypeVariableForLambdaReturnType coneTypeVariableForLambdaReturnType) {
        coneLambdaWithTypeVariableAsExpectedTypeAtom.getClass();
        constraintSystemBuilder.getClass();
        resolutionContext.getClass();
        ConeSubstitutor coneSubstitutor = (ConeSubstitutor) constraintSystemBuilder.buildCurrentSubstitutor();
        if (coneKotlinType == null) {
            coneKotlinType = coneLambdaWithTypeVariableAsExpectedTypeAtom.mo581getExpectedType();
        }
        ConeResolvedLambdaAtom coneResolvedLambdaAtomCreateResolvedLambdaAtomDuringCompletion = ArgumentCheckingProcessor.INSTANCE.createResolvedLambdaAtomDuringCompletion(coneLambdaWithTypeVariableAsExpectedTypeAtom.getCandidateOfOuterCall(), constraintSystemBuilder, new ConeResolutionAtomWithPostponedChild(coneLambdaWithTypeVariableAsExpectedTypeAtom.getExpression(), null, 2, null), coneSubstitutor.substituteOrSelf(coneKotlinType), resolutionContext, coneTypeVariableForLambdaReturnType, coneLambdaWithTypeVariableAsExpectedTypeAtom.getAnonymousFunctionIfReturnExpression());
        coneLambdaWithTypeVariableAsExpectedTypeAtom.setSubAtom(coneResolvedLambdaAtomCreateResolvedLambdaAtomDuringCompletion);
        coneLambdaWithTypeVariableAsExpectedTypeAtom.setAnalyzed(true);
        return coneResolvedLambdaAtomCreateResolvedLambdaAtomDuringCompletion;
    }

    public static /* synthetic */ ConeResolvedLambdaAtom transformToResolvedLambda$default(ConeLambdaWithTypeVariableAsExpectedTypeAtom coneLambdaWithTypeVariableAsExpectedTypeAtom, ConstraintSystemBuilder constraintSystemBuilder, ResolutionContext resolutionContext, ConeKotlinType coneKotlinType, ConeTypeVariableForLambdaReturnType coneTypeVariableForLambdaReturnType, int i, Object obj) {
        if ((i & 4) != 0) {
            coneKotlinType = null;
        }
        if ((i & 8) != 0) {
            coneTypeVariableForLambdaReturnType = null;
        }
        return transformToResolvedLambda(coneLambdaWithTypeVariableAsExpectedTypeAtom, constraintSystemBuilder, resolutionContext, coneKotlinType, coneTypeVariableForLambdaReturnType);
    }
}
