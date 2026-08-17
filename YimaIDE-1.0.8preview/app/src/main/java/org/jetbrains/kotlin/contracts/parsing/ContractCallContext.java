package org.jetbrains.kotlin.contracts.parsing;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.FunctionDescriptor;
import org.jetbrains.kotlin.psi.KtExpression;
import org.jetbrains.kotlin.resolve.BindingContext;
import org.jetbrains.kotlin.resolve.BindingTrace;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0014\u001a\u00020\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, d2 = {"Lorg/jetbrains/kotlin/contracts/parsing/ContractCallContext;", Argument.Delimiters.none, "contractCallExpression", "Lorg/jetbrains/kotlin/psi/KtExpression;", "functionDescriptor", "Lorg/jetbrains/kotlin/descriptors/FunctionDescriptor;", "trace", "Lorg/jetbrains/kotlin/resolve/BindingTrace;", "languageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "<init>", "(Lorg/jetbrains/kotlin/psi/KtExpression;Lorg/jetbrains/kotlin/descriptors/FunctionDescriptor;Lorg/jetbrains/kotlin/resolve/BindingTrace;Lorg/jetbrains/kotlin/config/LanguageVersionSettings;)V", "getContractCallExpression", "()Lorg/jetbrains/kotlin/psi/KtExpression;", "getFunctionDescriptor", "()Lorg/jetbrains/kotlin/descriptors/FunctionDescriptor;", "getTrace", "()Lorg/jetbrains/kotlin/resolve/BindingTrace;", "getLanguageVersionSettings", "()Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "bindingContext", "Lorg/jetbrains/kotlin/resolve/BindingContext;", "getBindingContext", "()Lorg/jetbrains/kotlin/resolve/BindingContext;", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ContractCallContext {
    private final BindingContext bindingContext;
    private final KtExpression contractCallExpression;
    private final FunctionDescriptor functionDescriptor;
    private final LanguageVersionSettings languageVersionSettings;
    private final BindingTrace trace;

    public ContractCallContext(KtExpression ktExpression, FunctionDescriptor functionDescriptor, BindingTrace bindingTrace, LanguageVersionSettings languageVersionSettings) {
        ktExpression.getClass();
        functionDescriptor.getClass();
        bindingTrace.getClass();
        languageVersionSettings.getClass();
        this.contractCallExpression = ktExpression;
        this.functionDescriptor = functionDescriptor;
        this.trace = bindingTrace;
        this.languageVersionSettings = languageVersionSettings;
        BindingContext bindingContext = bindingTrace.getBindingContext();
        bindingContext.getClass();
        this.bindingContext = bindingContext;
    }

    public final BindingContext getBindingContext() {
        return this.bindingContext;
    }

    public final KtExpression getContractCallExpression() {
        return this.contractCallExpression;
    }

    public final FunctionDescriptor getFunctionDescriptor() {
        return this.functionDescriptor;
    }

    public final LanguageVersionSettings getLanguageVersionSettings() {
        return this.languageVersionSettings;
    }

    public final BindingTrace getTrace() {
        return this.trace;
    }
}
