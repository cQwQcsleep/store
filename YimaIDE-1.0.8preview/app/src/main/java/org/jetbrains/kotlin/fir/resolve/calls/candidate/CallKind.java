package org.jetbrains.kotlin.fir.resolve.calls.candidate;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SpreadBuilder;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.resolve.calls.stages.CheckArguments;
import org.jetbrains.kotlin.fir.resolve.calls.stages.CheckCallModifiers;
import org.jetbrains.kotlin.fir.resolve.calls.stages.CheckCallableReferenceExpectedType;
import org.jetbrains.kotlin.fir.resolve.calls.stages.CheckContextArguments;
import org.jetbrains.kotlin.fir.resolve.calls.stages.CheckDispatchReceiver;
import org.jetbrains.kotlin.fir.resolve.calls.stages.CheckExtensionReceiver;
import org.jetbrains.kotlin.fir.resolve.calls.stages.CheckHiddenDeclaration;
import org.jetbrains.kotlin.fir.resolve.calls.stages.CheckIncompatibleTypeVariableUpperBounds;
import org.jetbrains.kotlin.fir.resolve.calls.stages.CheckLambdaAgainstTypeVariableContradiction;
import org.jetbrains.kotlin.fir.resolve.calls.stages.CheckLowPriorityInOverloadResolution;
import org.jetbrains.kotlin.fir.resolve.calls.stages.CheckShadowedImplicits;
import org.jetbrains.kotlin.fir.resolve.calls.stages.CheckVisibility;
import org.jetbrains.kotlin.fir.resolve.calls.stages.CollectTypeVariableUsagesInfo;
import org.jetbrains.kotlin.fir.resolve.calls.stages.ConstraintSystemForks;
import org.jetbrains.kotlin.fir.resolve.calls.stages.CreateFreshTypeVariableSubstitutorStage;
import org.jetbrains.kotlin.fir.resolve.calls.stages.DiscriminateSyntheticAndForbiddenProperties;
import org.jetbrains.kotlin.fir.resolve.calls.stages.EagerResolveOfCallableReferences;
import org.jetbrains.kotlin.fir.resolve.calls.stages.EagerResolveOfCollectionLiteral;
import org.jetbrains.kotlin.fir.resolve.calls.stages.InitializeEmptyArgumentMap;
import org.jetbrains.kotlin.fir.resolve.calls.stages.LowerPriorityIfDynamic;
import org.jetbrains.kotlin.fir.resolve.calls.stages.MapArguments;
import org.jetbrains.kotlin.fir.resolve.calls.stages.MapTypeArguments;
import org.jetbrains.kotlin.fir.resolve.calls.stages.NoTypeArguments;
import org.jetbrains.kotlin.fir.resolve.calls.stages.ProcessDynamicExtensionAnnotation;
import org.jetbrains.kotlin.fir.resolve.calls.stages.ResolutionStage;
import org.jetbrains.kotlin.fir.resolve.calls.stages.TypeParameterAsCallable;
import org.jetbrains.kotlin.fir.resolve.calls.stages.TypeVariablesInExplicitReceivers;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\b\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016B-\b\u0004\u0012\u0012\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003\"\u00020\u0004\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\n\u0010\r\u001a\u00020\u000eH\u0086\u0080\u0004R\u001b\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tR\u001b\u0010\u000b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\f\u0010\t\u0082\u0001\b\u0017\u0018\u0019\u001a\u001b\u001c\u001d\u001e¨\u0006\u001f"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallKind;", Argument.Delimiters.none, "resolutionSequence", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/calls/stages/ResolutionStage;", "additionalStages", "<init>", "([Lorg/jetbrains/kotlin/fir/resolve/calls/stages/ResolutionStage;[Lorg/jetbrains/kotlin/fir/resolve/calls/stages/ResolutionStage;)V", "getResolutionSequence", "()[Lorg/jetbrains/kotlin/fir/resolve/calls/stages/ResolutionStage;", "[Lorg/jetbrains/kotlin/fir/resolve/calls/stages/ResolutionStage;", "resolutionSequenceWithAdditionalStages", "getResolutionSequenceWithAdditionalStages", "toString", Argument.Delimiters.none, "VariableAccess", "SyntheticSelect", "Function", "CollectionLiteral", "DelegatingConstructorCall", "CallableReference", "SyntheticIdForCallableReferencesResolution", "CustomForIde", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallKind$CallableReference;", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallKind$CollectionLiteral;", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallKind$CustomForIde;", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallKind$DelegatingConstructorCall;", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallKind$Function;", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallKind$SyntheticIdForCallableReferencesResolution;", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallKind$SyntheticSelect;", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallKind$VariableAccess;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class CallKind {
    private final ResolutionStage[] resolutionSequence;
    private final ResolutionStage[] resolutionSequenceWithAdditionalStages;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallKind$CallableReference;", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallKind;", "<init>", "()V", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class CallableReference extends CallKind {
        public static final CallableReference INSTANCE = new CallableReference();

        /* JADX WARN: Multi-variable type inference failed */
        private CallableReference() {
            super(new ResolutionStage[]{CheckHiddenDeclaration.INSTANCE, CheckVisibility.INSTANCE, DiscriminateSyntheticAndForbiddenProperties.INSTANCE, NoTypeArguments.INSTANCE, InitializeEmptyArgumentMap.INSTANCE, CreateFreshTypeVariableSubstitutorStage.INSTANCE, CollectTypeVariableUsagesInfo.INSTANCE, CheckDispatchReceiver.INSTANCE, CheckExtensionReceiver.INSTANCE, CheckShadowedImplicits.INSTANCE, CheckCallableReferenceExpectedType.INSTANCE, CheckLowPriorityInOverloadResolution.INSTANCE, CheckIncompatibleTypeVariableUpperBounds.INSTANCE, ProcessDynamicExtensionAnnotation.INSTANCE, LowerPriorityIfDynamic.INSTANCE, TypeVariablesInExplicitReceivers.INSTANCE}, null, 2, 0 == true ? 1 : 0);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallKind$CollectionLiteral;", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallKind;", "<init>", "()V", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class CollectionLiteral extends CallKind {
        public static final CollectionLiteral INSTANCE = new CollectionLiteral();

        private CollectionLiteral() {
            super(new ResolutionStage[]{CheckHiddenDeclaration.INSTANCE, MapArguments.INSTANCE, MapTypeArguments.INSTANCE, CreateFreshTypeVariableSubstitutorStage.INSTANCE, CollectTypeVariableUsagesInfo.INSTANCE, CheckCallModifiers.INSTANCE, CheckLowPriorityInOverloadResolution.INSTANCE}, new ResolutionStage[]{CheckVisibility.INSTANCE, CheckArguments.INSTANCE, CheckDispatchReceiver.INSTANCE, CheckExtensionReceiver.INSTANCE, CheckContextArguments.INSTANCE, CheckShadowedImplicits.INSTANCE, EagerResolveOfCollectionLiteral.INSTANCE, EagerResolveOfCallableReferences.INSTANCE, CheckLambdaAgainstTypeVariableContradiction.INSTANCE, CheckIncompatibleTypeVariableUpperBounds.INSTANCE}, null);
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u001b\u0012\u0012\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003\"\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallKind$CustomForIde;", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallKind;", "resolutionSequence", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/calls/stages/ResolutionStage;", "<init>", "([Lorg/jetbrains/kotlin/fir/resolve/calls/stages/ResolutionStage;)V", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class CustomForIde extends CallKind {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public CustomForIde(ResolutionStage... resolutionStageArr) {
            super((ResolutionStage[]) Arrays.copyOf(resolutionStageArr, resolutionStageArr.length), null, 2, 0 == true ? 1 : 0);
            resolutionStageArr.getClass();
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallKind$DelegatingConstructorCall;", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallKind;", "<init>", "()V", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class DelegatingConstructorCall extends CallKind {
        public static final DelegatingConstructorCall INSTANCE = new DelegatingConstructorCall();

        /* JADX WARN: Multi-variable type inference failed */
        private DelegatingConstructorCall() {
            super(new ResolutionStage[]{CheckHiddenDeclaration.INSTANCE, CheckVisibility.INSTANCE, MapArguments.INSTANCE, MapTypeArguments.INSTANCE, CreateFreshTypeVariableSubstitutorStage.INSTANCE, CollectTypeVariableUsagesInfo.INSTANCE, CheckDispatchReceiver.INSTANCE, CheckExtensionReceiver.INSTANCE, CheckArguments.INSTANCE, CheckContextArguments.INSTANCE, CheckShadowedImplicits.INSTANCE, EagerResolveOfCallableReferences.INSTANCE, EagerResolveOfCollectionLiteral.INSTANCE, ConstraintSystemForks.INSTANCE, CheckIncompatibleTypeVariableUpperBounds.INSTANCE, CheckLambdaAgainstTypeVariableContradiction.INSTANCE}, null, 2, 0 == true ? 1 : 0);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallKind$Function;", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallKind;", "<init>", "()V", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Function extends CallKind {
        public static final Function INSTANCE = new Function();

        /* JADX WARN: Multi-variable type inference failed */
        private Function() {
            super(new ResolutionStage[]{CheckHiddenDeclaration.INSTANCE, CheckVisibility.INSTANCE, DiscriminateSyntheticAndForbiddenProperties.INSTANCE, MapArguments.INSTANCE, MapTypeArguments.INSTANCE, CreateFreshTypeVariableSubstitutorStage.INSTANCE, CollectTypeVariableUsagesInfo.INSTANCE, CheckDispatchReceiver.INSTANCE, CheckExtensionReceiver.INSTANCE, CheckArguments.INSTANCE, CheckContextArguments.INSTANCE, CheckShadowedImplicits.INSTANCE, CheckCallModifiers.INSTANCE, EagerResolveOfCallableReferences.INSTANCE, EagerResolveOfCollectionLiteral.INSTANCE, CheckLowPriorityInOverloadResolution.INSTANCE, ProcessDynamicExtensionAnnotation.INSTANCE, LowerPriorityIfDynamic.INSTANCE, ConstraintSystemForks.INSTANCE, CheckIncompatibleTypeVariableUpperBounds.INSTANCE, TypeParameterAsCallable.INSTANCE, TypeVariablesInExplicitReceivers.INSTANCE, CheckLambdaAgainstTypeVariableContradiction.INSTANCE}, null, 2, 0 == true ? 1 : 0);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallKind$SyntheticIdForCallableReferencesResolution;", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallKind;", "<init>", "()V", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class SyntheticIdForCallableReferencesResolution extends CallKind {
        public static final SyntheticIdForCallableReferencesResolution INSTANCE = new SyntheticIdForCallableReferencesResolution();

        /* JADX WARN: Multi-variable type inference failed */
        private SyntheticIdForCallableReferencesResolution() {
            super(new ResolutionStage[]{MapArguments.INSTANCE, MapTypeArguments.INSTANCE, CreateFreshTypeVariableSubstitutorStage.INSTANCE, CollectTypeVariableUsagesInfo.INSTANCE, CheckArguments.INSTANCE, EagerResolveOfCallableReferences.INSTANCE, ConstraintSystemForks.INSTANCE, CheckIncompatibleTypeVariableUpperBounds.INSTANCE}, null, 2, 0 == true ? 1 : 0);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallKind$SyntheticSelect;", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallKind;", "<init>", "()V", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class SyntheticSelect extends CallKind {
        public static final SyntheticSelect INSTANCE = new SyntheticSelect();

        /* JADX WARN: Multi-variable type inference failed */
        private SyntheticSelect() {
            super(new ResolutionStage[]{MapArguments.INSTANCE, NoTypeArguments.INSTANCE, CreateFreshTypeVariableSubstitutorStage.INSTANCE, CollectTypeVariableUsagesInfo.INSTANCE, CheckArguments.INSTANCE, EagerResolveOfCallableReferences.INSTANCE, ConstraintSystemForks.INSTANCE, CheckIncompatibleTypeVariableUpperBounds.INSTANCE}, null, 2, 0 == true ? 1 : 0);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallKind$VariableAccess;", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallKind;", "<init>", "()V", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class VariableAccess extends CallKind {
        public static final VariableAccess INSTANCE = new VariableAccess();

        /* JADX WARN: Multi-variable type inference failed */
        private VariableAccess() {
            super(new ResolutionStage[]{CheckHiddenDeclaration.INSTANCE, CheckVisibility.INSTANCE, DiscriminateSyntheticAndForbiddenProperties.INSTANCE, NoTypeArguments.INSTANCE, InitializeEmptyArgumentMap.INSTANCE, CreateFreshTypeVariableSubstitutorStage.INSTANCE, CollectTypeVariableUsagesInfo.INSTANCE, CheckDispatchReceiver.INSTANCE, CheckExtensionReceiver.INSTANCE, CheckContextArguments.INSTANCE, CheckShadowedImplicits.INSTANCE, CheckLowPriorityInOverloadResolution.INSTANCE, ProcessDynamicExtensionAnnotation.INSTANCE, LowerPriorityIfDynamic.INSTANCE, ConstraintSystemForks.INSTANCE, CheckIncompatibleTypeVariableUpperBounds.INSTANCE, TypeParameterAsCallable.INSTANCE, TypeVariablesInExplicitReceivers.INSTANCE}, null, 2, 0 == true ? 1 : 0);
        }
    }

    private CallKind(ResolutionStage[] resolutionStageArr, ResolutionStage[] resolutionStageArr2) {
        this.resolutionSequence = resolutionStageArr;
        SpreadBuilder spreadBuilder = new SpreadBuilder(2);
        spreadBuilder.addSpread(resolutionStageArr);
        spreadBuilder.addSpread(resolutionStageArr2);
        this.resolutionSequenceWithAdditionalStages = (ResolutionStage[]) spreadBuilder.toArray(new ResolutionStage[spreadBuilder.size()]);
    }

    public final ResolutionStage[] getResolutionSequence() {
        return this.resolutionSequence;
    }

    public final ResolutionStage[] getResolutionSequenceWithAdditionalStages() {
        return this.resolutionSequenceWithAdditionalStages;
    }

    public final String toString() {
        String simpleName = Reflection.getOrCreateKotlinClass(getClass()).getSimpleName();
        return simpleName == null ? super.toString() : simpleName;
    }

    public /* synthetic */ CallKind(ResolutionStage[] resolutionStageArr, ResolutionStage[] resolutionStageArr2, DefaultConstructorMarker defaultConstructorMarker) {
        this(resolutionStageArr, resolutionStageArr2);
    }

    public /* synthetic */ CallKind(ResolutionStage[] resolutionStageArr, ResolutionStage[] resolutionStageArr2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(resolutionStageArr, (i & 2) != 0 ? new ResolutionStage[0] : resolutionStageArr2, null);
    }
}
