package org.jetbrains.kotlin.fir.resolve.calls;

import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.analysis.collectors.AbstractDiagnosticCollector;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.resolve.calls.AbstractConeResolutionAtom;
import org.jetbrains.kotlin.resolve.calls.inference.NewConstraintSystem;
import org.jetbrains.kotlin.resolve.calls.inference.model.ConstraintSystemError;
import org.jetbrains.kotlin.resolve.calls.tasks.ExplicitReceiverKind;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B\u0007¢\u0006\u0004\b\u0004\u0010\u0005R.\u0010\u0006\u001a\u001e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\b0\u0007j\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\b`\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0012\u0010\f\u001a\u00020\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u0004\u0018\u00010\u0002X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u0004\u0018\u00010\u0002X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0012R\u0012\u0010\u0015\u001a\u00020\u0016X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u001aX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u0012\u0010\u001d\u001a\u00020\u001eX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R\u0018\u0010!\u001a\b\u0012\u0004\u0012\u00020\"0\u001aX¦\u0004¢\u0006\u0006\u001a\u0004\b#\u0010\u001cR\u0018\u0010$\u001a\b\u0012\u0004\u0012\u00020%0\u001aX¦\u0004¢\u0006\u0006\u001a\u0004\b&\u0010\u001cR\u0012\u0010'\u001a\u00020(X¦\u0004¢\u0006\u0006\u001a\u0004\b)\u0010*R\u0012\u0010+\u001a\u00020\rX¦\u0004¢\u0006\u0006\u001a\u0004\b,\u0010\u000f¨\u0006-"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/AbstractCallCandidate;", "P", "Lorg/jetbrains/kotlin/fir/resolve/calls/AbstractConeResolutionAtom;", "Lorg/jetbrains/kotlin/fir/resolve/calls/AbstractCandidate;", "<init>", "()V", "argumentMapping", "Ljava/util/LinkedHashMap;", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "Lkotlin/collections/LinkedHashMap;", "getArgumentMapping", "()Ljava/util/LinkedHashMap;", "argumentMappingInitialized", Argument.Delimiters.none, "getArgumentMappingInitialized", "()Z", "dispatchReceiver", "getDispatchReceiver", "()Lorg/jetbrains/kotlin/fir/resolve/calls/AbstractConeResolutionAtom;", "chosenExtensionReceiver", "getChosenExtensionReceiver", "explicitReceiverKind", "Lorg/jetbrains/kotlin/resolve/calls/tasks/ExplicitReceiverKind;", "getExplicitReceiverKind", "()Lorg/jetbrains/kotlin/resolve/calls/tasks/ExplicitReceiverKind;", "contextArguments", Argument.Delimiters.none, "getContextArguments", "()Ljava/util/List;", "callInfo", "Lorg/jetbrains/kotlin/fir/resolve/calls/AbstractCallInfo;", "getCallInfo", "()Lorg/jetbrains/kotlin/fir/resolve/calls/AbstractCallInfo;", "diagnostics", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionDiagnostic;", "getDiagnostics", AbstractDiagnosticCollector.SUPPRESS_ALL_ERRORS, "Lorg/jetbrains/kotlin/resolve/calls/inference/model/ConstraintSystemError;", "getErrors", "system", "Lorg/jetbrains/kotlin/resolve/calls/inference/NewConstraintSystem;", "getSystem", "()Lorg/jetbrains/kotlin/resolve/calls/inference/NewConstraintSystem;", "usedOuterCs", "getUsedOuterCs", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class AbstractCallCandidate<P extends AbstractConeResolutionAtom> extends AbstractCandidate {
    public abstract LinkedHashMap<P, FirValueParameter> getArgumentMapping();

    public abstract boolean getArgumentMappingInitialized();

    public abstract AbstractCallInfo getCallInfo();

    public abstract AbstractConeResolutionAtom getChosenExtensionReceiver();

    public abstract List<AbstractConeResolutionAtom> getContextArguments();

    public abstract List<ResolutionDiagnostic> getDiagnostics();

    public abstract AbstractConeResolutionAtom getDispatchReceiver();

    public abstract List<ConstraintSystemError> getErrors();

    public abstract ExplicitReceiverKind getExplicitReceiverKind();

    public abstract NewConstraintSystem getSystem();

    public abstract boolean getUsedOuterCs();
}
