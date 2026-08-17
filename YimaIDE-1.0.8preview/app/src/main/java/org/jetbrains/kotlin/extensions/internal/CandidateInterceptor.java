package org.jetbrains.kotlin.extensions.internal;

import com.intellij.openapi.project.Project;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.CallableDescriptor;
import org.jetbrains.kotlin.descriptors.FunctionDescriptor;
import org.jetbrains.kotlin.descriptors.VariableDescriptor;
import org.jetbrains.kotlin.extensions.ProjectExtensionDescriptor;
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
@Metadata(d1 = {"\u0000\u009a\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 32\u00020\u0001:\u00013B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J8\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013JJ\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u00132\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00160\u00132\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#JJ\u0010$\u001a\b\u0012\u0004\u0012\u00020%0\u00132\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020%0\u00132\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020\u00192\u0006\u0010)\u001a\u00020*2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010+\u001a\u00020,J^\u0010$\u001a\b\u0012\u0004\u0012\u00020%0\u00132\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020%0\u00132\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020\u00192\u0006\u0010)\u001a\u00020*2\u0006\u0010\u001c\u001a\u00020-2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010+\u001a\u00020,2\b\u0010.\u001a\u0004\u0018\u00010/2\b\u00100\u001a\u0004\u0018\u00010/JJ\u00101\u001a\b\u0012\u0004\u0012\u0002020\u00132\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u0002020\u00132\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020\u00192\u0006\u0010)\u001a\u00020*2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010+\u001a\u00020,J^\u00101\u001a\b\u0012\u0004\u0012\u0002020\u00132\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u0002020\u00132\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020\u00192\u0006\u0010)\u001a\u00020*2\u0006\u0010\u001c\u001a\u00020-2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010+\u001a\u00020,2\b\u0010.\u001a\u0004\u0018\u00010/2\b\u00100\u001a\u0004\u0018\u00010/R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00064"}, d2 = {"Lorg/jetbrains/kotlin/extensions/internal/CandidateInterceptor;", Argument.Delimiters.none, "project", "Lcom/intellij/openapi/project/Project;", "<init>", "(Lcom/intellij/openapi/project/Project;)V", "extensions", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/extensions/internal/CallResolutionInterceptorExtension;", "interceptResolvedCallAtomCandidate", "Lorg/jetbrains/kotlin/descriptors/CallableDescriptor;", "candidateDescriptor", "completedCallAtom", "Lorg/jetbrains/kotlin/resolve/calls/model/ResolvedCallAtom;", "trace", "Lorg/jetbrains/kotlin/resolve/BindingTrace;", "resultSubstitutor", "Lorg/jetbrains/kotlin/resolve/calls/inference/components/NewTypeSubstitutor;", "diagnostics", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/resolve/calls/model/KotlinCallDiagnostic;", "interceptResolvedCandidates", "Lorg/jetbrains/kotlin/resolve/calls/tower/NewResolutionOldInference$MyCandidate;", "candidates", "context", "Lorg/jetbrains/kotlin/resolve/calls/context/BasicCallResolutionContext;", "candidateResolver", "Lorg/jetbrains/kotlin/resolve/calls/CandidateResolver;", "callResolver", "Lorg/jetbrains/kotlin/resolve/calls/CallResolver;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "kind", "Lorg/jetbrains/kotlin/resolve/calls/tower/NewResolutionOldInference$ResolutionKind;", "tracing", "Lorg/jetbrains/kotlin/resolve/calls/tasks/TracingStrategy;", "interceptFunctionCandidates", "Lorg/jetbrains/kotlin/descriptors/FunctionDescriptor;", "scopeTower", "Lorg/jetbrains/kotlin/resolve/calls/tower/ImplicitScopeTower;", "resolutionContext", "resolutionScope", "Lorg/jetbrains/kotlin/resolve/scopes/ResolutionScope;", "location", "Lorg/jetbrains/kotlin/incremental/components/LookupLocation;", "Lorg/jetbrains/kotlin/resolve/calls/tower/PSICallResolver;", "dispatchReceiver", "Lorg/jetbrains/kotlin/resolve/scopes/receivers/ReceiverValueWithSmartCastInfo;", "extensionReceiver", "interceptVariableCandidates", "Lorg/jetbrains/kotlin/descriptors/VariableDescriptor;", "Companion", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CandidateInterceptor {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final List<CallResolutionInterceptorExtension> extensions;

    public CandidateInterceptor(Project project) {
        project.getClass();
        this.extensions = INSTANCE.getInstances(project);
    }

    public final Collection<FunctionDescriptor> interceptFunctionCandidates(Collection<? extends FunctionDescriptor> candidates, ImplicitScopeTower scopeTower, BasicCallResolutionContext resolutionContext, ResolutionScope resolutionScope, PSICallResolver callResolver, Name name, LookupLocation location, ReceiverValueWithSmartCastInfo dispatchReceiver, ReceiverValueWithSmartCastInfo extensionReceiver) {
        candidates.getClass();
        scopeTower.getClass();
        resolutionContext.getClass();
        resolutionScope.getClass();
        callResolver.getClass();
        name.getClass();
        location.getClass();
        Iterator<T> it = this.extensions.iterator();
        Collection<? extends FunctionDescriptor> collectionInterceptFunctionCandidates = candidates;
        while (it.hasNext()) {
            collectionInterceptFunctionCandidates = ((CallResolutionInterceptorExtension) it.next()).interceptFunctionCandidates(collectionInterceptFunctionCandidates, scopeTower, resolutionContext, resolutionScope, callResolver, name, location, dispatchReceiver, extensionReceiver);
        }
        return collectionInterceptFunctionCandidates;
    }

    public final CallableDescriptor interceptResolvedCallAtomCandidate(CallableDescriptor candidateDescriptor, ResolvedCallAtom completedCallAtom, BindingTrace trace, NewTypeSubstitutor resultSubstitutor, Collection<? extends KotlinCallDiagnostic> diagnostics) {
        candidateDescriptor.getClass();
        completedCallAtom.getClass();
        diagnostics.getClass();
        Iterator<T> it = this.extensions.iterator();
        CallableDescriptor callableDescriptorInterceptResolvedCallAtomCandidate = candidateDescriptor;
        while (it.hasNext()) {
            callableDescriptorInterceptResolvedCallAtomCandidate = ((CallResolutionInterceptorExtension) it.next()).interceptResolvedCallAtomCandidate(callableDescriptorInterceptResolvedCallAtomCandidate, completedCallAtom, trace, resultSubstitutor, diagnostics);
        }
        return callableDescriptorInterceptResolvedCallAtomCandidate;
    }

    public final Collection<NewResolutionOldInference.MyCandidate> interceptResolvedCandidates(Collection<NewResolutionOldInference.MyCandidate> candidates, BasicCallResolutionContext context, CandidateResolver candidateResolver, CallResolver callResolver, Name name, NewResolutionOldInference.ResolutionKind kind, TracingStrategy tracing) {
        candidates.getClass();
        context.getClass();
        candidateResolver.getClass();
        callResolver.getClass();
        name.getClass();
        kind.getClass();
        tracing.getClass();
        Iterator<T> it = this.extensions.iterator();
        Collection<NewResolutionOldInference.MyCandidate> collectionInterceptCandidates = candidates;
        while (it.hasNext()) {
            collectionInterceptCandidates = ((CallResolutionInterceptorExtension) it.next()).interceptCandidates(collectionInterceptCandidates, context, candidateResolver, callResolver, name, kind, tracing);
        }
        return collectionInterceptCandidates;
    }

    public final Collection<VariableDescriptor> interceptVariableCandidates(Collection<? extends VariableDescriptor> candidates, ImplicitScopeTower scopeTower, BasicCallResolutionContext resolutionContext, ResolutionScope resolutionScope, PSICallResolver callResolver, Name name, LookupLocation location, ReceiverValueWithSmartCastInfo dispatchReceiver, ReceiverValueWithSmartCastInfo extensionReceiver) {
        candidates.getClass();
        scopeTower.getClass();
        resolutionContext.getClass();
        resolutionScope.getClass();
        callResolver.getClass();
        name.getClass();
        location.getClass();
        Iterator<T> it = this.extensions.iterator();
        Collection<? extends VariableDescriptor> collectionInterceptVariableCandidates = candidates;
        while (it.hasNext()) {
            collectionInterceptVariableCandidates = ((CallResolutionInterceptorExtension) it.next()).interceptVariableCandidates(collectionInterceptVariableCandidates, scopeTower, resolutionContext, resolutionScope, callResolver, name, location, dispatchReceiver, extensionReceiver);
        }
        return collectionInterceptVariableCandidates;
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lorg/jetbrains/kotlin/extensions/internal/CandidateInterceptor$Companion;", "Lorg/jetbrains/kotlin/extensions/ProjectExtensionDescriptor;", "Lorg/jetbrains/kotlin/extensions/internal/CallResolutionInterceptorExtension;", "<init>", "()V", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion extends ProjectExtensionDescriptor<CallResolutionInterceptorExtension> {
        private Companion() {
            super("org.jetbrains.kotlin.extensions.internal.callResolutionInterceptorExtension", CallResolutionInterceptorExtension.class);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public final Collection<FunctionDescriptor> interceptFunctionCandidates(Collection<? extends FunctionDescriptor> candidates, ImplicitScopeTower scopeTower, BasicCallResolutionContext resolutionContext, ResolutionScope resolutionScope, CallResolver callResolver, Name name, LookupLocation location) {
        candidates.getClass();
        scopeTower.getClass();
        resolutionContext.getClass();
        resolutionScope.getClass();
        callResolver.getClass();
        name.getClass();
        location.getClass();
        Iterator<T> it = this.extensions.iterator();
        Collection<? extends FunctionDescriptor> collectionInterceptFunctionCandidates = candidates;
        while (it.hasNext()) {
            collectionInterceptFunctionCandidates = ((CallResolutionInterceptorExtension) it.next()).interceptFunctionCandidates(collectionInterceptFunctionCandidates, scopeTower, resolutionContext, resolutionScope, callResolver, name, location);
        }
        return collectionInterceptFunctionCandidates;
    }

    public final Collection<VariableDescriptor> interceptVariableCandidates(Collection<? extends VariableDescriptor> candidates, ImplicitScopeTower scopeTower, BasicCallResolutionContext resolutionContext, ResolutionScope resolutionScope, CallResolver callResolver, Name name, LookupLocation location) {
        candidates.getClass();
        scopeTower.getClass();
        resolutionContext.getClass();
        resolutionScope.getClass();
        callResolver.getClass();
        name.getClass();
        location.getClass();
        Iterator<T> it = this.extensions.iterator();
        Collection<? extends VariableDescriptor> collectionInterceptVariableCandidates = candidates;
        while (it.hasNext()) {
            collectionInterceptVariableCandidates = ((CallResolutionInterceptorExtension) it.next()).interceptVariableCandidates(collectionInterceptVariableCandidates, scopeTower, resolutionContext, resolutionScope, callResolver, name, location);
        }
        return collectionInterceptVariableCandidates;
    }
}
