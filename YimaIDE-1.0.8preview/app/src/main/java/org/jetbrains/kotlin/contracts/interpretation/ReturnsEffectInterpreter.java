package org.jetbrains.kotlin.contracts.interpretation;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.description.EffectDeclaration;
import org.jetbrains.kotlin.contracts.description.ReturnsEffectDeclaration;
import org.jetbrains.kotlin.contracts.model.ESEffect;
import org.jetbrains.kotlin.contracts.model.structure.ESConstant;
import org.jetbrains.kotlin.contracts.model.structure.ESReturns;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\tH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/contracts/interpretation/ReturnsEffectInterpreter;", "Lorg/jetbrains/kotlin/contracts/interpretation/EffectDeclarationInterpreter;", "dispatcher", "Lorg/jetbrains/kotlin/contracts/interpretation/ContractInterpretationDispatcher;", "<init>", "(Lorg/jetbrains/kotlin/contracts/interpretation/ContractInterpretationDispatcher;)V", "tryInterpret", "Lorg/jetbrains/kotlin/contracts/model/ESEffect;", "effectDeclaration", "Lorg/jetbrains/kotlin/contracts/description/EffectDeclaration;", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ReturnsEffectInterpreter implements EffectDeclarationInterpreter {
    private final ContractInterpretationDispatcher dispatcher;

    public ReturnsEffectInterpreter(ContractInterpretationDispatcher contractInterpretationDispatcher) {
        contractInterpretationDispatcher.getClass();
        this.dispatcher = contractInterpretationDispatcher;
    }

    @Override // org.jetbrains.kotlin.contracts.interpretation.EffectDeclarationInterpreter
    public ESEffect tryInterpret(EffectDeclaration effectDeclaration) {
        ESConstant eSConstantInterpretConstant$org_jetbrains_kotlin_resolution;
        effectDeclaration.getClass();
        if ((effectDeclaration instanceof ReturnsEffectDeclaration) && (eSConstantInterpretConstant$org_jetbrains_kotlin_resolution = this.dispatcher.interpretConstant$org_jetbrains_kotlin_resolution(((ReturnsEffectDeclaration) effectDeclaration).getValue())) != null) {
            return new ESReturns(eSConstantInterpretConstant$org_jetbrains_kotlin_resolution);
        }
        return null;
    }
}
