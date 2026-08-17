package org.jetbrains.kotlin.contracts.parsing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.TuplesKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.Diagnostic;
import org.jetbrains.kotlin.diagnostics.Errors;
import org.jetbrains.kotlin.psi.KtCallExpression;
import org.jetbrains.kotlin.psi.KtElement;
import org.jetbrains.kotlin.psi.KtExpression;
import org.jetbrains.kotlin.resolve.BindingTrace;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007B\u0011\b\u0016\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\u0006\u0010\nJ\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0010\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\b\u0010\u0019\u001a\u00020\u0010H\u0016J\b\u0010\u001a\u001a\u00020\u0010H\u0016J\b\u0010\u001b\u001a\u00020\u001cH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lorg/jetbrains/kotlin/contracts/parsing/TraceBasedCollector;", "Lorg/jetbrains/kotlin/contracts/parsing/ContractParsingDiagnosticsCollector;", "bindingTrace", "Lorg/jetbrains/kotlin/resolve/BindingTrace;", "mainCall", "Lorg/jetbrains/kotlin/psi/KtExpression;", "<init>", "(Lorg/jetbrains/kotlin/resolve/BindingTrace;Lorg/jetbrains/kotlin/psi/KtExpression;)V", "callContext", "Lorg/jetbrains/kotlin/contracts/parsing/ContractCallContext;", "(Lorg/jetbrains/kotlin/contracts/parsing/ContractCallContext;)V", "reportedErrors", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/diagnostics/Diagnostic;", "mainCallReportTarget", "contractNotAllowed", Argument.Delimiters.none, "message", Argument.Delimiters.none, "badDescription", "reportOn", "Lorg/jetbrains/kotlin/psi/KtElement;", "unsupportedFeature", "languageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "addFallbackErrorIfNecessary", "flushDiagnostics", "hasErrors", Argument.Delimiters.none, "org.jetbrains.kotlin:frontend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class TraceBasedCollector implements ContractParsingDiagnosticsCollector {
    private final BindingTrace bindingTrace;
    private final KtExpression mainCallReportTarget;
    private final List<Diagnostic> reportedErrors;

    public TraceBasedCollector(BindingTrace bindingTrace, KtExpression ktExpression) {
        KtExpression calleeExpression;
        bindingTrace.getClass();
        ktExpression.getClass();
        this.bindingTrace = bindingTrace;
        this.reportedErrors = new ArrayList();
        KtCallExpression ktCallExpression = ktExpression instanceof KtCallExpression ? (KtCallExpression) ktExpression : null;
        if (ktCallExpression != null && (calleeExpression = ktCallExpression.getCalleeExpression()) != null) {
            ktExpression = calleeExpression;
        }
        this.mainCallReportTarget = ktExpression;
    }

    @Override // org.jetbrains.kotlin.contracts.parsing.ContractParsingDiagnosticsCollector
    public void addFallbackErrorIfNecessary() {
        if (this.reportedErrors.isEmpty()) {
            this.reportedErrors.add(Errors.ERROR_IN_CONTRACT_DESCRIPTION.on(this.mainCallReportTarget, "Error in contract description"));
        }
    }

    @Override // org.jetbrains.kotlin.contracts.parsing.ContractParsingDiagnosticsCollector
    public void badDescription(String message, KtElement reportOn) {
        message.getClass();
        reportOn.getClass();
        this.reportedErrors.add(Errors.ERROR_IN_CONTRACT_DESCRIPTION.on(reportOn, message));
    }

    @Override // org.jetbrains.kotlin.contracts.parsing.ContractParsingDiagnosticsCollector
    public void contractNotAllowed(String message) {
        message.getClass();
        this.reportedErrors.add(Errors.CONTRACT_NOT_ALLOWED.on(this.mainCallReportTarget, message));
    }

    @Override // org.jetbrains.kotlin.contracts.parsing.ContractParsingDiagnosticsCollector
    public void flushDiagnostics() {
        Iterator<T> it = this.reportedErrors.iterator();
        while (it.hasNext()) {
            this.bindingTrace.report((Diagnostic) it.next());
        }
    }

    @Override // org.jetbrains.kotlin.contracts.parsing.ContractParsingDiagnosticsCollector
    public boolean hasErrors() {
        return !this.reportedErrors.isEmpty();
    }

    @Override // org.jetbrains.kotlin.contracts.parsing.ContractParsingDiagnosticsCollector
    public void unsupportedFeature(LanguageVersionSettings languageVersionSettings) {
        languageVersionSettings.getClass();
        this.reportedErrors.add(Errors.UNSUPPORTED_FEATURE.on(this.mainCallReportTarget, TuplesKt.to(LanguageFeature.AllowContractsForCustomFunctions, languageVersionSettings)));
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public TraceBasedCollector(ContractCallContext contractCallContext) {
        this(contractCallContext.getTrace(), contractCallContext.getContractCallExpression());
        contractCallContext.getClass();
    }
}
