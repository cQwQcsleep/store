package org.jetbrains.kotlin.contracts.model.structure;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.resolve.scopes.receivers.ReceiverValue;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0016\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/contracts/model/structure/ESReceiverValue;", "Lorg/jetbrains/kotlin/contracts/model/structure/AbstractESValue;", "Lorg/jetbrains/kotlin/contracts/model/structure/ESReceiver;", "receiverValue", "Lorg/jetbrains/kotlin/resolve/scopes/receivers/ReceiverValue;", "<init>", "(Lorg/jetbrains/kotlin/resolve/scopes/receivers/ReceiverValue;)V", "getReceiverValue", "()Lorg/jetbrains/kotlin/resolve/scopes/receivers/ReceiverValue;", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class ESReceiverValue extends AbstractESValue implements ESReceiver {
    private final ReceiverValue receiverValue;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ESReceiverValue(ReceiverValue receiverValue) {
        super(null);
        receiverValue.getClass();
        this.receiverValue = receiverValue;
    }

    @Override // org.jetbrains.kotlin.contracts.model.structure.ESReceiver
    public ReceiverValue getReceiverValue() {
        return this.receiverValue;
    }
}
