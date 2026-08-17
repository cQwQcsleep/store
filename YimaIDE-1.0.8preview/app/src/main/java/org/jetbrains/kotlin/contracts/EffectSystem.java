package org.jetbrains.kotlin.contracts;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import org.jetbrains.kotlin.builtins.KotlinBuiltIns;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.model.Computation;
import org.jetbrains.kotlin.contracts.model.ESEffect;
import org.jetbrains.kotlin.contracts.model.ESValue;
import org.jetbrains.kotlin.contracts.model.MutableContextInfo;
import org.jetbrains.kotlin.contracts.model.functors.EqualsFunctor;
import org.jetbrains.kotlin.contracts.model.structure.ESCalls;
import org.jetbrains.kotlin.contracts.model.structure.ESConstants;
import org.jetbrains.kotlin.contracts.model.structure.ESReturns;
import org.jetbrains.kotlin.contracts.model.structure.UNKNOWN_COMPUTATION;
import org.jetbrains.kotlin.contracts.model.visitors.InfoCollector;
import org.jetbrains.kotlin.descriptors.ModuleDescriptor;
import org.jetbrains.kotlin.psi.KtCallExpression;
import org.jetbrains.kotlin.psi.KtDeclaration;
import org.jetbrains.kotlin.psi.KtExpression;
import org.jetbrains.kotlin.psi.KtLambdaExpression;
import org.jetbrains.kotlin.psi.psiUtil.PsiUtilsKt;
import org.jetbrains.kotlin.resolve.BindingContext;
import org.jetbrains.kotlin.resolve.BindingTrace;
import org.jetbrains.kotlin.resolve.calls.model.ResolvedCall;
import org.jetbrains.kotlin.resolve.calls.smartcasts.ConditionalDataFlowInfo;
import org.jetbrains.kotlin.resolve.calls.smartcasts.DataFlowInfo;
import org.jetbrains.kotlin.resolve.calls.smartcasts.DataFlowValueFactory;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\"\u0010\u0010\u001a\u00020\u00112\n\u0010\u0012\u001a\u0006\u0012\u0002\b\u00030\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J*\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J\"\u0010\u001d\u001a\u00020\u001e2\n\u0010\u0012\u001a\u0006\u0012\u0002\b\u00030\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J(\u0010\u001f\u001a\u00020\u00112\b\u0010 \u001a\u0004\u0018\u00010\u001b2\u0006\u0010!\u001a\u00020\"2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J(\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\u001b2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\"\u0010(\u001a\u0004\u0018\u00010)2\u0006\u0010'\u001a\u00020\u001b2\u0006\u0010*\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006+"}, d2 = {"Lorg/jetbrains/kotlin/contracts/EffectSystem;", Argument.Delimiters.none, "languageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "dataFlowValueFactory", "Lorg/jetbrains/kotlin/resolve/calls/smartcasts/DataFlowValueFactory;", "builtIns", "Lorg/jetbrains/kotlin/builtins/KotlinBuiltIns;", "<init>", "(Lorg/jetbrains/kotlin/config/LanguageVersionSettings;Lorg/jetbrains/kotlin/resolve/calls/smartcasts/DataFlowValueFactory;Lorg/jetbrains/kotlin/builtins/KotlinBuiltIns;)V", "getLanguageVersionSettings", "()Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "getDataFlowValueFactory", "()Lorg/jetbrains/kotlin/resolve/calls/smartcasts/DataFlowValueFactory;", "getBuiltIns", "()Lorg/jetbrains/kotlin/builtins/KotlinBuiltIns;", "getDataFlowInfoForFinishedCall", "Lorg/jetbrains/kotlin/resolve/calls/smartcasts/DataFlowInfo;", "resolvedCall", "Lorg/jetbrains/kotlin/resolve/calls/model/ResolvedCall;", "bindingTrace", "Lorg/jetbrains/kotlin/resolve/BindingTrace;", "moduleDescriptor", "Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;", "getDataFlowInfoWhenEquals", "Lorg/jetbrains/kotlin/resolve/calls/smartcasts/ConditionalDataFlowInfo;", "leftExpression", "Lorg/jetbrains/kotlin/psi/KtExpression;", "rightExpression", "recordDefiniteInvocations", Argument.Delimiters.none, "extractDataFlowInfoFromCondition", "condition", "value", Argument.Delimiters.none, "getContextInfoWhen", "Lorg/jetbrains/kotlin/contracts/model/MutableContextInfo;", "observedEffect", "Lorg/jetbrains/kotlin/contracts/model/ESEffect;", "expression", "getNonTrivialComputation", "Lorg/jetbrains/kotlin/contracts/model/Computation;", "trace", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class EffectSystem {
    private final KotlinBuiltIns builtIns;
    private final DataFlowValueFactory dataFlowValueFactory;
    private final LanguageVersionSettings languageVersionSettings;

    public EffectSystem(LanguageVersionSettings languageVersionSettings, DataFlowValueFactory dataFlowValueFactory, KotlinBuiltIns kotlinBuiltIns) {
        languageVersionSettings.getClass();
        dataFlowValueFactory.getClass();
        kotlinBuiltIns.getClass();
        this.languageVersionSettings = languageVersionSettings;
        this.dataFlowValueFactory = dataFlowValueFactory;
        this.builtIns = kotlinBuiltIns;
    }

    private final MutableContextInfo getContextInfoWhen(ESEffect observedEffect, KtExpression expression, BindingTrace bindingTrace, ModuleDescriptor moduleDescriptor) {
        Sequence sequenceFilter = SequencesKt.filter(PsiUtilsKt.getParentsWithSelf(expression), new Function1<Object, Boolean>() { // from class: org.jetbrains.kotlin.contracts.EffectSystem$getContextInfoWhen$$inlined$filterIsInstance$1
            /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
            public final Boolean m83invoke(Object obj) {
                return Boolean.valueOf(obj instanceof KtExpression);
            }
        });
        sequenceFilter.getClass();
        Iterator it = sequenceFilter.iterator();
        while (it.hasNext()) {
            if (Intrinsics.areEqual(bindingTrace.getBindingContext().get(BindingContext.IS_CONTRACT_DECLARATION_BLOCK, (KtExpression) it.next()), Boolean.TRUE)) {
                return MutableContextInfo.INSTANCE.getEMPTY();
            }
        }
        Computation nonTrivialComputation = getNonTrivialComputation(expression, bindingTrace, moduleDescriptor);
        return nonTrivialComputation == null ? MutableContextInfo.INSTANCE.getEMPTY() : new InfoCollector(observedEffect, this.builtIns).collectFromSchema(nonTrivialComputation.getEffects());
    }

    private final Computation getNonTrivialComputation(KtExpression expression, BindingTrace trace, ModuleDescriptor moduleDescriptor) {
        Computation computationExtractOrGetCached = new EffectsExtractingVisitor(trace, moduleDescriptor, this.dataFlowValueFactory, this.languageVersionSettings).extractOrGetCached(expression);
        if (Intrinsics.areEqual(computationExtractOrGetCached, UNKNOWN_COMPUTATION.INSTANCE)) {
            return null;
        }
        return computationExtractOrGetCached;
    }

    public final DataFlowInfo extractDataFlowInfoFromCondition(KtExpression condition, boolean value, BindingTrace bindingTrace, ModuleDescriptor moduleDescriptor) {
        bindingTrace.getClass();
        moduleDescriptor.getClass();
        if (this.languageVersionSettings.supportsFeature(LanguageFeature.UseReturnsEffect) && condition != null) {
            return ContextInfoToDataFlowInfoKt.toDataFlowInfo(getContextInfoWhen(new ESReturns(ESConstants.INSTANCE.booleanValue(value)), condition, bindingTrace, moduleDescriptor), this.languageVersionSettings, moduleDescriptor.getBuiltIns());
        }
        return DataFlowInfo.Companion.getEMPTY();
    }

    public final KotlinBuiltIns getBuiltIns() {
        return this.builtIns;
    }

    public final DataFlowInfo getDataFlowInfoForFinishedCall(ResolvedCall<?> resolvedCall, BindingTrace bindingTrace, ModuleDescriptor moduleDescriptor) {
        resolvedCall.getClass();
        bindingTrace.getClass();
        moduleDescriptor.getClass();
        if (!this.languageVersionSettings.supportsFeature(LanguageFeature.UseReturnsEffect)) {
            return DataFlowInfo.Companion.getEMPTY();
        }
        KtCallExpression callElement = resolvedCall.getCall().getCallElement();
        KtCallExpression ktCallExpression = callElement instanceof KtCallExpression ? callElement : null;
        if (ktCallExpression != null && !(ktCallExpression instanceof KtDeclaration)) {
            return ContextInfoToDataFlowInfoKt.toDataFlowInfo(getContextInfoWhen(new ESReturns(ESConstants.INSTANCE.getWildcard()), ktCallExpression, bindingTrace, moduleDescriptor), this.languageVersionSettings, this.builtIns);
        }
        return DataFlowInfo.Companion.getEMPTY();
    }

    public final ConditionalDataFlowInfo getDataFlowInfoWhenEquals(KtExpression leftExpression, KtExpression rightExpression, BindingTrace bindingTrace, ModuleDescriptor moduleDescriptor) {
        Computation nonTrivialComputation;
        bindingTrace.getClass();
        moduleDescriptor.getClass();
        if (!this.languageVersionSettings.supportsFeature(LanguageFeature.UseReturnsEffect)) {
            return ConditionalDataFlowInfo.Companion.getEMPTY();
        }
        if (leftExpression == null || rightExpression == null) {
            return ConditionalDataFlowInfo.Companion.getEMPTY();
        }
        Computation nonTrivialComputation2 = getNonTrivialComputation(leftExpression, bindingTrace, moduleDescriptor);
        if (nonTrivialComputation2 != null && (nonTrivialComputation = getNonTrivialComputation(rightExpression, bindingTrace, moduleDescriptor)) != null) {
            List<ESEffect> listInvokeWithArguments = new EqualsFunctor(false).invokeWithArguments(nonTrivialComputation2, nonTrivialComputation);
            ESConstants eSConstants = ESConstants.INSTANCE;
            return new ConditionalDataFlowInfo(ContextInfoToDataFlowInfoKt.toDataFlowInfo(new InfoCollector(new ESReturns(eSConstants.getTrueValue()), this.builtIns).collectFromSchema(listInvokeWithArguments), this.languageVersionSettings, this.builtIns), ContextInfoToDataFlowInfoKt.toDataFlowInfo(new InfoCollector(new ESReturns(eSConstants.getFalseValue()), this.builtIns).collectFromSchema(listInvokeWithArguments), this.languageVersionSettings, this.builtIns));
        }
        return ConditionalDataFlowInfo.Companion.getEMPTY();
    }

    public final DataFlowValueFactory getDataFlowValueFactory() {
        return this.dataFlowValueFactory;
    }

    public final LanguageVersionSettings getLanguageVersionSettings() {
        return this.languageVersionSettings;
    }

    public final void recordDefiniteInvocations(ResolvedCall<?> resolvedCall, BindingTrace bindingTrace, ModuleDescriptor moduleDescriptor) {
        KtLambdaExpression lambda;
        resolvedCall.getClass();
        bindingTrace.getClass();
        moduleDescriptor.getClass();
        if (this.languageVersionSettings.supportsFeature(LanguageFeature.UseCallsInPlaceEffect)) {
            KtCallExpression callElement = resolvedCall.getCall().getCallElement();
            KtCallExpression ktCallExpression = callElement instanceof KtCallExpression ? callElement : null;
            if (ktCallExpression == null || (ktCallExpression instanceof KtDeclaration)) {
                return;
            }
            for (ESEffect eSEffect : getContextInfoWhen(new ESReturns(ESConstants.INSTANCE.getWildcard()), ktCallExpression, bindingTrace, moduleDescriptor).getFiredEffects()) {
                ESCalls eSCalls = eSEffect instanceof ESCalls ? (ESCalls) eSEffect : null;
                if (eSCalls != null) {
                    ESValue callable = eSCalls.getCallable();
                    ESLambda eSLambda = callable instanceof ESLambda ? (ESLambda) callable : null;
                    if (eSLambda != null && (lambda = eSLambda.getLambda()) != null) {
                        bindingTrace.record(BindingContext.LAMBDA_INVOCATIONS, lambda, eSCalls.getKind());
                    }
                }
            }
        }
    }
}
