package org.jetbrains.kotlin.contracts.description;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0003H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lorg/jetbrains/kotlin/contracts/description/ContractProviderImpl;", "Lorg/jetbrains/kotlin/contracts/description/AbstractContractProvider;", "contractDescription", "Lorg/jetbrains/kotlin/contracts/description/ContractDescription;", "<init>", "(Lorg/jetbrains/kotlin/contracts/description/ContractDescription;)V", "getContractDescription", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ContractProviderImpl extends AbstractContractProvider {
    private final ContractDescription contractDescription;

    public ContractProviderImpl(ContractDescription contractDescription) {
        contractDescription.getClass();
        this.contractDescription = contractDescription;
    }

    @Override // org.jetbrains.kotlin.contracts.description.AbstractContractProvider
    public ContractDescription getContractDescription() {
        return this.contractDescription;
    }
}
