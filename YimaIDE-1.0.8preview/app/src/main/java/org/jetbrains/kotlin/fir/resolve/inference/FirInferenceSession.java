package org.jetbrains.kotlin.fir.resolve.inference;

import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.arguments.K2JsArgumentConstants;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirResolvable;
import org.jetbrains.kotlin.fir.resolve.ResolutionMode;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate;
import org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.BodyResolveContext;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.resolve.calls.inference.components.ConstraintSystemCompletionMode;
import org.jetbrains.kotlin.resolve.calls.inference.model.ConstraintStorage;
import org.jetbrains.kotlin.resolve.calls.inference.model.NewConstraintSystemImpl;
import org.jetbrains.kotlin.types.model.KotlinTypeMarker;
import org.jetbrains.kotlin.types.model.TypeConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u0000 /2\u00020\u0001:\u0001/B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J3\u0010\u0014\u001a\u00020\u0015\"\f\b\u0000\u0010\u0016*\u00020\u0013*\u00020\u00172\u0006\u0010\u0012\u001a\u0002H\u00162\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0011H&¢\u0006\u0002\u0010\u001bJ(\u0010\u001c\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020\u001e2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00150 H\u0016J \u0010!\u001a\u00020\u00152\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020#2\u0006\u0010%\u001a\u00020&H\u0016J\u0012\u0010'\u001a\u0004\u0018\u00010#2\u0006\u0010(\u001a\u00020#H\u0016J\u0018\u0010)\u001a\u00020\u00152\u0006\u0010(\u001a\u00020#2\u0006\u0010*\u001a\u00020+H\u0016J\u001c\u0010,\u001a\u00020\u00152\u0006\u0010-\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0017b\u0002\b.R \u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u00060"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/inference/FirInferenceSession;", Argument.Delimiters.none, "<init>", "()V", "semiFixedVariables", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/types/model/TypeConstructorMarker;", "Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;", "getSemiFixedVariables", "()Ljava/util/Map;", "baseConstraintStorageForCandidate", "Lorg/jetbrains/kotlin/resolve/calls/inference/model/ConstraintStorage;", "candidate", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "bodyResolveContext", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/BodyResolveContext;", "customCompletionModeInsteadOfFull", "Lorg/jetbrains/kotlin/resolve/calls/inference/components/ConstraintSystemCompletionMode;", K2JsArgumentConstants.CALL, "Lorg/jetbrains/kotlin/fir/expressions/FirResolvable;", "processPartiallyResolvedCall", Argument.Delimiters.none, "T", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "resolutionMode", "Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode;", "completionMode", "(Lorg/jetbrains/kotlin/fir/expressions/FirExpression;Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode;Lorg/jetbrains/kotlin/resolve/calls/inference/components/ConstraintSystemCompletionMode;)V", "runLambdaCompletion", "forOverloadByLambdaReturnType", Argument.Delimiters.none, "block", "Lkotlin/Function0;", "addSubtypeConstraintIfCompatible", "lowerType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "upperType", "element", "Lorg/jetbrains/kotlin/fir/FirElement;", "getAndSemiFixCurrentResultIfTypeVariable", ModuleXmlParser.TYPE, "semiFixTypeVariablesAllowingFixationToOtherOnes", "myCs", "Lorg/jetbrains/kotlin/resolve/calls/inference/model/NewConstraintSystemImpl;", "updateExpressionReturnTypeWithCurrentSubstitutorInPCLA", "expression", "Lorg/jetbrains/kotlin/fir/resolve/inference/TemporaryInferenceSessionHook;", "Companion", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirInferenceSession {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final FirInferenceSession DEFAULT = new FirInferenceSession() { // from class: org.jetbrains.kotlin.fir.resolve.inference.FirInferenceSession$Companion$DEFAULT$1
        @Override // org.jetbrains.kotlin.fir.resolve.inference.FirInferenceSession
        public <T extends FirExpression & FirResolvable> void processPartiallyResolvedCall(T call, ResolutionMode resolutionMode, ConstraintSystemCompletionMode completionMode) {
            call.getClass();
            resolutionMode.getClass();
            completionMode.getClass();
        }
    };

    @JvmStatic
    public static final NewConstraintSystemImpl prepareSharedBaseSystem(NewConstraintSystemImpl newConstraintSystemImpl, InferenceComponents inferenceComponents) {
        return INSTANCE.prepareSharedBaseSystem(newConstraintSystemImpl, inferenceComponents);
    }

    public void addSubtypeConstraintIfCompatible(ConeKotlinType lowerType, ConeKotlinType upperType, FirElement element) {
        lowerType.getClass();
        upperType.getClass();
        element.getClass();
    }

    public ConstraintStorage baseConstraintStorageForCandidate(Candidate candidate, BodyResolveContext bodyResolveContext) {
        candidate.getClass();
        bodyResolveContext.getClass();
        return null;
    }

    public ConstraintSystemCompletionMode customCompletionModeInsteadOfFull(FirResolvable call) {
        call.getClass();
        return null;
    }

    public ConeKotlinType getAndSemiFixCurrentResultIfTypeVariable(ConeKotlinType type) {
        type.getClass();
        return null;
    }

    public Map<TypeConstructorMarker, KotlinTypeMarker> getSemiFixedVariables() {
        return MapsKt.emptyMap();
    }

    public abstract <T extends FirExpression & FirResolvable> void processPartiallyResolvedCall(T call, ResolutionMode resolutionMode, ConstraintSystemCompletionMode completionMode);

    public ConstraintStorage runLambdaCompletion(Candidate candidate, boolean forOverloadByLambdaReturnType, Function0<Unit> block) {
        candidate.getClass();
        block.getClass();
        block.invoke();
        return null;
    }

    public void semiFixTypeVariablesAllowingFixationToOtherOnes(ConeKotlinType type, NewConstraintSystemImpl myCs) {
        type.getClass();
        myCs.getClass();
    }

    @TemporaryInferenceSessionHook
    public void updateExpressionReturnTypeWithCurrentSubstitutorInPCLA(FirExpression expression, ResolutionMode resolutionMode) {
        expression.getClass();
        resolutionMode.getClass();
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001c\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\fH\u0005b\u0002\b\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/inference/FirInferenceSession$Companion;", Argument.Delimiters.none, "<init>", "()V", "DEFAULT", "Lorg/jetbrains/kotlin/fir/resolve/inference/FirInferenceSession;", "getDEFAULT", "()Lorg/jetbrains/kotlin/fir/resolve/inference/FirInferenceSession;", "prepareSharedBaseSystem", "Lorg/jetbrains/kotlin/resolve/calls/inference/model/NewConstraintSystemImpl;", "outerSystem", "components", "Lorg/jetbrains/kotlin/fir/resolve/inference/InferenceComponents;", "Lkotlin/jvm/JvmStatic;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final FirInferenceSession getDEFAULT() {
            return FirInferenceSession.DEFAULT;
        }

        @JvmStatic
        public final NewConstraintSystemImpl prepareSharedBaseSystem(NewConstraintSystemImpl outerSystem, InferenceComponents components) {
            outerSystem.getClass();
            components.getClass();
            NewConstraintSystemImpl newConstraintSystemImplCreateConstraintSystem$default = InferenceComponents.createConstraintSystem$default(components, null, 1, null);
            newConstraintSystemImplCreateConstraintSystem$default.addOuterSystem(outerSystem.currentStorage());
            return newConstraintSystemImplCreateConstraintSystem$default;
        }

        private Companion() {
        }
    }
}
