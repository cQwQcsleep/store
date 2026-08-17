package org.jetbrains.kotlin.contracts.parsing;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b \u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/contracts/parsing/AbstractPsiEffectParser;", "Lorg/jetbrains/kotlin/contracts/parsing/PsiEffectParser;", "collector", "Lorg/jetbrains/kotlin/contracts/parsing/ContractParsingDiagnosticsCollector;", "callContext", "Lorg/jetbrains/kotlin/contracts/parsing/ContractCallContext;", "contractParserDispatcher", "Lorg/jetbrains/kotlin/contracts/parsing/PsiContractParserDispatcher;", "<init>", "(Lorg/jetbrains/kotlin/contracts/parsing/ContractParsingDiagnosticsCollector;Lorg/jetbrains/kotlin/contracts/parsing/ContractCallContext;Lorg/jetbrains/kotlin/contracts/parsing/PsiContractParserDispatcher;)V", "getCollector", "()Lorg/jetbrains/kotlin/contracts/parsing/ContractParsingDiagnosticsCollector;", "getCallContext", "()Lorg/jetbrains/kotlin/contracts/parsing/ContractCallContext;", "getContractParserDispatcher", "()Lorg/jetbrains/kotlin/contracts/parsing/PsiContractParserDispatcher;", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class AbstractPsiEffectParser implements PsiEffectParser {
    private final ContractCallContext callContext;
    private final ContractParsingDiagnosticsCollector collector;
    private final PsiContractParserDispatcher contractParserDispatcher;

    public AbstractPsiEffectParser(ContractParsingDiagnosticsCollector contractParsingDiagnosticsCollector, ContractCallContext contractCallContext, PsiContractParserDispatcher psiContractParserDispatcher) {
        contractParsingDiagnosticsCollector.getClass();
        contractCallContext.getClass();
        psiContractParserDispatcher.getClass();
        this.collector = contractParsingDiagnosticsCollector;
        this.callContext = contractCallContext;
        this.contractParserDispatcher = psiContractParserDispatcher;
    }

    public final ContractCallContext getCallContext() {
        return this.callContext;
    }

    public final ContractParsingDiagnosticsCollector getCollector() {
        return this.collector;
    }

    public final PsiContractParserDispatcher getContractParserDispatcher() {
        return this.contractParserDispatcher;
    }
}
