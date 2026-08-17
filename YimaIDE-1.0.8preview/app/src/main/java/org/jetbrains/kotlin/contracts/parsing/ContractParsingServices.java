package org.jetbrains.kotlin.contracts.parsing;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.description.ContractDescription;
import org.jetbrains.kotlin.contracts.description.ContractProviderKey;
import org.jetbrains.kotlin.contracts.description.LazyContractProvider;
import org.jetbrains.kotlin.descriptors.CallableDescriptor;
import org.jetbrains.kotlin.descriptors.FunctionDescriptor;
import org.jetbrains.kotlin.psi.KtExpression;
import org.jetbrains.kotlin.psi.psiUtil.KtPsiUtilKt;
import org.jetbrains.kotlin.resolve.BindingContext;
import org.jetbrains.kotlin.resolve.BindingTrace;
import org.jetbrains.kotlin.resolve.calls.model.ResolvedCall;
import org.jetbrains.kotlin.resolve.calls.util.CallUtilKt;
import org.jetbrains.kotlin.storage.StorageManager;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u001e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J\f\u0010\u0012\u001a\u00020\u0013*\u00020\u0014H\u0002J\u0012\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0017\u001a\u00020\u0014H\u0002J\u0010\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\u0014\u0010\u0012\u001a\u00020\u0013*\u00020\r2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lorg/jetbrains/kotlin/contracts/parsing/ContractParsingServices;", Argument.Delimiters.none, "languageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "storageManager", "Lorg/jetbrains/kotlin/storage/StorageManager;", "<init>", "(Lorg/jetbrains/kotlin/config/LanguageVersionSettings;Lorg/jetbrains/kotlin/storage/StorageManager;)V", "getLanguageVersionSettings", "()Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "checkContractAndRecordIfPresent", Argument.Delimiters.none, "expression", "Lorg/jetbrains/kotlin/psi/KtExpression;", "trace", "Lorg/jetbrains/kotlin/resolve/BindingTrace;", "ownerDescriptor", "Lorg/jetbrains/kotlin/descriptors/FunctionDescriptor;", "isContractDescriptionCallPreciseCheck", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/contracts/parsing/ContractCallContext;", "parseContractAndReportErrors", "Lorg/jetbrains/kotlin/contracts/description/ContractDescription;", "callContext", "checkFeatureEnabled", "collector", "Lorg/jetbrains/kotlin/contracts/parsing/ContractParsingDiagnosticsCollector;", "context", "Lorg/jetbrains/kotlin/resolve/BindingContext;", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ContractParsingServices {
    private final LanguageVersionSettings languageVersionSettings;
    private final StorageManager storageManager;

    public ContractParsingServices(LanguageVersionSettings languageVersionSettings, StorageManager storageManager) {
        languageVersionSettings.getClass();
        storageManager.getClass();
        this.languageVersionSettings = languageVersionSettings;
        this.storageManager = storageManager;
    }

    private final void checkFeatureEnabled(ContractParsingDiagnosticsCollector collector) {
        if (this.languageVersionSettings.supportsFeature(LanguageFeature.AllowContractsForCustomFunctions)) {
            return;
        }
        collector.unsupportedFeature(this.languageVersionSettings);
    }

    private final boolean isContractDescriptionCallPreciseCheck(KtExpression ktExpression, BindingContext bindingContext) {
        CallableDescriptor resultingDescriptor;
        ResolvedCall resolvedCall = CallUtilKt.getResolvedCall(ktExpression, bindingContext);
        if (resolvedCall == null || (resultingDescriptor = resolvedCall.getResultingDescriptor()) == null) {
            return false;
        }
        return PsiContractsUtilsKt.isContractCallDescriptor(resultingDescriptor);
    }

    private final ContractDescription parseContractAndReportErrors(ContractCallContext callContext) {
        TraceBasedCollector traceBasedCollector = new TraceBasedCollector(callContext);
        try {
            checkFeatureEnabled(traceBasedCollector);
            boolean zAreEqual = Intrinsics.areEqual(callContext.getBindingContext().get(BindingContext.CONTRACT_NOT_ALLOWED, callContext.getContractCallExpression()), Boolean.TRUE);
            ContractDescription contractDescription = null;
            if (!traceBasedCollector.hasErrors() && !zAreEqual) {
                ContractDescription contract = new PsiContractParserDispatcher(traceBasedCollector, callContext, this.storageManager).parseContract();
                if (contract == null) {
                    traceBasedCollector.addFallbackErrorIfNecessary();
                }
                if (contract != null && !traceBasedCollector.hasErrors()) {
                    contractDescription = contract;
                }
                return contractDescription;
            }
            return null;
        } finally {
            traceBasedCollector.flushDiagnostics();
        }
    }

    public final void checkContractAndRecordIfPresent(KtExpression expression, BindingTrace trace, FunctionDescriptor ownerDescriptor) {
        expression.getClass();
        trace.getClass();
        ownerDescriptor.getClass();
        if (KtPsiUtilKt.isContractDescriptionCallPsiCheck(expression)) {
            ContractCallContext contractCallContext = new ContractCallContext(expression, ownerDescriptor, trace, this.languageVersionSettings);
            Object userData = ownerDescriptor.getUserData(ContractProviderKey.INSTANCE);
            LazyContractProvider lazyContractProvider = userData instanceof LazyContractProvider ? (LazyContractProvider) userData : null;
            try {
                if (!isContractDescriptionCallPreciseCheck(contractCallContext)) {
                    if (lazyContractProvider != null) {
                        lazyContractProvider.setContractDescription((ContractDescription) null);
                    }
                } else {
                    ContractDescription contractAndReportErrors = parseContractAndReportErrors(contractCallContext);
                    if (lazyContractProvider != null) {
                        lazyContractProvider.setContractDescription(contractAndReportErrors);
                    }
                }
            } catch (Throwable th) {
                if (lazyContractProvider != null) {
                    lazyContractProvider.setContractDescription((ContractDescription) null);
                }
                throw th;
            }
        }
    }

    public final LanguageVersionSettings getLanguageVersionSettings() {
        return this.languageVersionSettings;
    }

    private final boolean isContractDescriptionCallPreciseCheck(ContractCallContext contractCallContext) {
        return isContractDescriptionCallPreciseCheck(contractCallContext.getContractCallExpression(), contractCallContext.getBindingContext());
    }
}
