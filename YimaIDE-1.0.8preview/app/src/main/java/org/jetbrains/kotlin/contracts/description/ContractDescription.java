package org.jetbrains.kotlin.contracts.description;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.description.ContractDescription;
import org.jetbrains.kotlin.contracts.interpretation.ContractInterpretationDispatcher;
import org.jetbrains.kotlin.contracts.model.Functor;
import org.jetbrains.kotlin.descriptors.FunctionDescriptor;
import org.jetbrains.kotlin.descriptors.ModuleDescriptor;
import org.jetbrains.kotlin.storage.NullableLazyValue;
import org.jetbrains.kotlin.storage.StorageManager;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B%\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0013\u001a\u00020\u0014R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/contracts/description/ContractDescription;", Argument.Delimiters.none, "effects", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/contracts/description/EffectDeclaration;", "ownerFunction", "Lorg/jetbrains/kotlin/descriptors/FunctionDescriptor;", "storageManager", "Lorg/jetbrains/kotlin/storage/StorageManager;", "<init>", "(Ljava/util/List;Lorg/jetbrains/kotlin/descriptors/FunctionDescriptor;Lorg/jetbrains/kotlin/storage/StorageManager;)V", "getEffects", "()Ljava/util/List;", "getOwnerFunction", "()Lorg/jetbrains/kotlin/descriptors/FunctionDescriptor;", "computeFunctor", "Lorg/jetbrains/kotlin/storage/NullableLazyValue;", "Lorg/jetbrains/kotlin/contracts/model/Functor;", "getFunctor", "usageModule", "Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class ContractDescription {
    private final NullableLazyValue<Functor> computeFunctor;
    private final List<EffectDeclaration> effects;
    private final FunctionDescriptor ownerFunction;

    /* JADX WARN: Multi-variable type inference failed */
    public ContractDescription(List<? extends EffectDeclaration> list, FunctionDescriptor functionDescriptor, StorageManager storageManager) {
        list.getClass();
        functionDescriptor.getClass();
        storageManager.getClass();
        this.effects = list;
        this.ownerFunction = functionDescriptor;
        this.computeFunctor = storageManager.createNullableLazyValue(new Function0() { // from class: jw2
            public final Object invoke() {
                return ContractDescription.a(this.b);
            }
        });
    }

    public static Functor a(ContractDescription contractDescription) {
        return new ContractInterpretationDispatcher().convertContractDescriptorToFunctor(contractDescription);
    }

    public final List<EffectDeclaration> getEffects() {
        return this.effects;
    }

    public final Functor getFunctor(ModuleDescriptor usageModule) {
        usageModule.getClass();
        return (Functor) this.computeFunctor.invoke();
    }

    public final FunctionDescriptor getOwnerFunction() {
        return this.ownerFunction;
    }
}
