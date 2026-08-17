package org.jetbrains.kotlin.extensions.internal;

import java.util.Collection;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.CallableDescriptor;
import org.jetbrains.kotlin.descriptors.FunctionDescriptor;
import org.jetbrains.kotlin.descriptors.VariableDescriptor;
import org.jetbrains.kotlin.incremental.components.LookupLocation;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.resolve.BindingTrace;
import org.jetbrains.kotlin.resolve.calls.CallResolver;
import org.jetbrains.kotlin.resolve.calls.CandidateResolver;
import org.jetbrains.kotlin.resolve.calls.context.BasicCallResolutionContext;
import org.jetbrains.kotlin.resolve.calls.inference.components.NewTypeSubstitutor;
import org.jetbrains.kotlin.resolve.calls.model.KotlinCallDiagnostic;
import org.jetbrains.kotlin.resolve.calls.model.ResolvedCallAtom;
import org.jetbrains.kotlin.resolve.calls.tasks.TracingStrategy;
import org.jetbrains.kotlin.resolve.calls.tower.ImplicitScopeTower;
import org.jetbrains.kotlin.resolve.calls.tower.NewResolutionOldInference;
import org.jetbrains.kotlin.resolve.calls.tower.PSICallResolver;
import org.jetbrains.kotlin.resolve.scopes.ResolutionScope;
import org.jetbrains.kotlin.resolve.scopes.receivers.ReceiverValueWithSmartCastInfo;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\bg\u0018\u00002\u00020\u0001J:\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0016JL\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\f2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0016JL\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001e0\f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u001e0\f2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u00122\u0006\u0010\"\u001a\u00020#2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010$\u001a\u00020%H\u0016J`\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001e0\f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u001e0\f2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u00122\u0006\u0010\"\u001a\u00020#2\u0006\u0010\u0015\u001a\u00020&2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010$\u001a\u00020%2\b\u0010'\u001a\u0004\u0018\u00010(2\b\u0010)\u001a\u0004\u0018\u00010(H\u0016JL\u0010*\u001a\b\u0012\u0004\u0012\u00020+0\f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020+0\f2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u00122\u0006\u0010\"\u001a\u00020#2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010$\u001a\u00020%H\u0016J`\u0010*\u001a\b\u0012\u0004\u0012\u00020+0\f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020+0\f2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u00122\u0006\u0010\"\u001a\u00020#2\u0006\u0010\u0015\u001a\u00020&2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010$\u001a\u00020%2\b\u0010'\u001a\u0004\u0018\u00010(2\b\u0010)\u001a\u0004\u0018\u00010(H\u0016Ê\u0001\u0002\b-ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006,À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/extensions/internal/CallResolutionInterceptorExtension;", Argument.Delimiters.none, "interceptResolvedCallAtomCandidate", "Lorg/jetbrains/kotlin/descriptors/CallableDescriptor;", "candidateDescriptor", "completedCallAtom", "Lorg/jetbrains/kotlin/resolve/calls/model/ResolvedCallAtom;", "trace", "Lorg/jetbrains/kotlin/resolve/BindingTrace;", "resultSubstitutor", "Lorg/jetbrains/kotlin/resolve/calls/inference/components/NewTypeSubstitutor;", "diagnostics", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/resolve/calls/model/KotlinCallDiagnostic;", "interceptCandidates", "Lorg/jetbrains/kotlin/resolve/calls/tower/NewResolutionOldInference$MyCandidate;", "candidates", "context", "Lorg/jetbrains/kotlin/resolve/calls/context/BasicCallResolutionContext;", "candidateResolver", "Lorg/jetbrains/kotlin/resolve/calls/CandidateResolver;", "callResolver", "Lorg/jetbrains/kotlin/resolve/calls/CallResolver;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "kind", "Lorg/jetbrains/kotlin/resolve/calls/tower/NewResolutionOldInference$ResolutionKind;", "tracing", "Lorg/jetbrains/kotlin/resolve/calls/tasks/TracingStrategy;", "interceptFunctionCandidates", "Lorg/jetbrains/kotlin/descriptors/FunctionDescriptor;", "scopeTower", "Lorg/jetbrains/kotlin/resolve/calls/tower/ImplicitScopeTower;", "resolutionContext", "resolutionScope", "Lorg/jetbrains/kotlin/resolve/scopes/ResolutionScope;", "location", "Lorg/jetbrains/kotlin/incremental/components/LookupLocation;", "Lorg/jetbrains/kotlin/resolve/calls/tower/PSICallResolver;", "dispatchReceiver", "Lorg/jetbrains/kotlin/resolve/scopes/receivers/ReceiverValueWithSmartCastInfo;", "extensionReceiver", "interceptVariableCandidates", "Lorg/jetbrains/kotlin/descriptors/VariableDescriptor;", "org.jetbrains.kotlin:frontend", "Lorg/jetbrains/kotlin/extensions/internal/InternalNonStableExtensionPoints;"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public interface CallResolutionInterceptorExtension {
    default Collection<NewResolutionOldInference.MyCandidate> interceptCandidates(Collection<NewResolutionOldInference.MyCandidate> candidates, BasicCallResolutionContext context, CandidateResolver candidateResolver, CallResolver callResolver, Name name, NewResolutionOldInference.ResolutionKind kind, TracingStrategy tracing) {
        candidates.getClass();
        context.getClass();
        candidateResolver.getClass();
        callResolver.getClass();
        name.getClass();
        kind.getClass();
        tracing.getClass();
        return candidates;
    }

    default Collection<FunctionDescriptor> interceptFunctionCandidates(Collection<? extends FunctionDescriptor> candidates, ImplicitScopeTower scopeTower, BasicCallResolutionContext resolutionContext, ResolutionScope resolutionScope, CallResolver callResolver, Name name, LookupLocation location) {
        candidates.getClass();
        scopeTower.getClass();
        resolutionContext.getClass();
        resolutionScope.getClass();
        callResolver.getClass();
        name.getClass();
        location.getClass();
        return candidates;
    }

    default CallableDescriptor interceptResolvedCallAtomCandidate(CallableDescriptor candidateDescriptor, ResolvedCallAtom completedCallAtom, BindingTrace trace, NewTypeSubstitutor resultSubstitutor, Collection<? extends KotlinCallDiagnostic> diagnostics) {
        candidateDescriptor.getClass();
        completedCallAtom.getClass();
        diagnostics.getClass();
        return candidateDescriptor;
    }

    default Collection<VariableDescriptor> interceptVariableCandidates(Collection<? extends VariableDescriptor> candidates, ImplicitScopeTower scopeTower, BasicCallResolutionContext resolutionContext, ResolutionScope resolutionScope, CallResolver callResolver, Name name, LookupLocation location) {
        candidates.getClass();
        scopeTower.getClass();
        resolutionContext.getClass();
        resolutionScope.getClass();
        callResolver.getClass();
        name.getClass();
        location.getClass();
        return candidates;
    }

    default Collection<FunctionDescriptor> interceptFunctionCandidates(Collection<? extends FunctionDescriptor> candidates, ImplicitScopeTower scopeTower, BasicCallResolutionContext resolutionContext, ResolutionScope resolutionScope, PSICallResolver callResolver, Name name, LookupLocation location, ReceiverValueWithSmartCastInfo dispatchReceiver, ReceiverValueWithSmartCastInfo extensionReceiver) {
        candidates.getClass();
        scopeTower.getClass();
        resolutionContext.getClass();
        resolutionScope.getClass();
        callResolver.getClass();
        name.getClass();
        location.getClass();
        return candidates;
    }

    default Collection<VariableDescriptor> interceptVariableCandidates(Collection<? extends VariableDescriptor> candidates, ImplicitScopeTower scopeTower, BasicCallResolutionContext resolutionContext, ResolutionScope resolutionScope, PSICallResolver callResolver, Name name, LookupLocation location, ReceiverValueWithSmartCastInfo dispatchReceiver, ReceiverValueWithSmartCastInfo extensionReceiver) {
        candidates.getClass();
        scopeTower.getClass();
        resolutionContext.getClass();
        resolutionScope.getClass();
        callResolver.getClass();
        name.getClass();
        location.getClass();
        return candidates;
    }
}
