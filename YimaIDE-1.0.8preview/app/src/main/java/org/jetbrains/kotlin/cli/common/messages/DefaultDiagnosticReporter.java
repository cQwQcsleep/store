package org.jetbrains.kotlin.cli.common.messages;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/messages/DefaultDiagnosticReporter;", "Lorg/jetbrains/kotlin/cli/common/messages/MessageCollectorBasedReporter;", "messageCollector", "Lorg/jetbrains/kotlin/cli/common/messages/MessageCollector;", "<init>", "(Lorg/jetbrains/kotlin/cli/common/messages/MessageCollector;)V", "getMessageCollector", "()Lorg/jetbrains/kotlin/cli/common/messages/MessageCollector;", "org.jetbrains.kotlin:cli"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class DefaultDiagnosticReporter implements MessageCollectorBasedReporter {
    private final MessageCollector messageCollector;

    public DefaultDiagnosticReporter(MessageCollector messageCollector) {
        messageCollector.getClass();
        this.messageCollector = messageCollector;
    }

    @Override // org.jetbrains.kotlin.cli.common.messages.MessageCollectorBasedReporter
    public MessageCollector getMessageCollector() {
        return this.messageCollector;
    }
}
