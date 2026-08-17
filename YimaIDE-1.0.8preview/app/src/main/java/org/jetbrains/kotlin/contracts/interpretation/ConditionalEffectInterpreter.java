package org.jetbrains.kotlin.contracts.interpretation;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.description.ConditionalEffectDeclaration;
import org.jetbrains.kotlin.contracts.model.ConditionalEffect;
import org.jetbrains.kotlin.contracts.model.ESEffect;
import org.jetbrains.kotlin.contracts.model.ESExpression;
import org.jetbrains.kotlin.contracts.model.SimpleEffect;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/contracts/interpretation/ConditionalEffectInterpreter;", Argument.Delimiters.none, "dispatcher", "Lorg/jetbrains/kotlin/contracts/interpretation/ContractInterpretationDispatcher;", "<init>", "(Lorg/jetbrains/kotlin/contracts/interpretation/ContractInterpretationDispatcher;)V", "interpret", "Lorg/jetbrains/kotlin/contracts/model/ConditionalEffect;", "conditionalEffectDeclaration", "Lorg/jetbrains/kotlin/contracts/description/ConditionalEffectDeclaration;", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConditionalEffectInterpreter {
    private final ContractInterpretationDispatcher dispatcher;

    public ConditionalEffectInterpreter(ContractInterpretationDispatcher contractInterpretationDispatcher) {
        contractInterpretationDispatcher.getClass();
        this.dispatcher = contractInterpretationDispatcher;
    }

    public final ConditionalEffect interpret(ConditionalEffectDeclaration conditionalEffectDeclaration) {
        ESExpression eSExpressionInterpretCondition$org_jetbrains_kotlin_resolution;
        conditionalEffectDeclaration.getClass();
        ESEffect eSEffectInterpretEffect$org_jetbrains_kotlin_resolution = this.dispatcher.interpretEffect$org_jetbrains_kotlin_resolution(conditionalEffectDeclaration.getEffect());
        SimpleEffect simpleEffect = eSEffectInterpretEffect$org_jetbrains_kotlin_resolution instanceof SimpleEffect ? (SimpleEffect) eSEffectInterpretEffect$org_jetbrains_kotlin_resolution : null;
        if (simpleEffect == null || (eSExpressionInterpretCondition$org_jetbrains_kotlin_resolution = this.dispatcher.interpretCondition$org_jetbrains_kotlin_resolution(conditionalEffectDeclaration.getCondition())) == null) {
            return null;
        }
        return new ConditionalEffect(eSExpressionInterpretCondition$org_jetbrains_kotlin_resolution, simpleEffect);
    }
}
