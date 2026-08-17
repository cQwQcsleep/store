package org.jetbrains.kotlin.cli.common.messages;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.analysis.collectors.AbstractDiagnosticCollector;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001:\u0001\u0019B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0001J\b\u0010\u000e\u001a\u00020\fH\u0016J\"\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\n\u0010\u0018\u001a\u00020\u0013H\u0096\u0080\u0004R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058F¢\u0006\u0006\u001a\u0004\b\n\u0010\b¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/messages/MessageCollectorImpl;", "Lorg/jetbrains/kotlin/cli/common/messages/MessageCollector;", "<init>", "()V", "messages", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/cli/common/messages/MessageCollectorImpl$Message;", "getMessages", "()Ljava/util/List;", AbstractDiagnosticCollector.SUPPRESS_ALL_ERRORS, "getErrors", "forward", Argument.Delimiters.none, "other", "clear", "report", "severity", "Lorg/jetbrains/kotlin/cli/common/messages/CompilerMessageSeverity;", "message", Argument.Delimiters.none, "location", "Lorg/jetbrains/kotlin/cli/common/messages/CompilerMessageSourceLocation;", "hasErrors", Argument.Delimiters.none, "toString", "Message", "org.jetbrains.kotlin:util"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class MessageCollectorImpl implements MessageCollector {
    private final List<Message> messages = new ArrayList();

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\n\u0010\u0010\u001a\u00020\u0005H\u0096\u0080\u0004J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0007HÆ\u0003J)\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0014\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0018\u001a\u00020\u0019HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/messages/MessageCollectorImpl$Message;", Argument.Delimiters.none, "severity", "Lorg/jetbrains/kotlin/cli/common/messages/CompilerMessageSeverity;", "message", Argument.Delimiters.none, "location", "Lorg/jetbrains/kotlin/cli/common/messages/CompilerMessageSourceLocation;", "<init>", "(Lorg/jetbrains/kotlin/cli/common/messages/CompilerMessageSeverity;Ljava/lang/String;Lorg/jetbrains/kotlin/cli/common/messages/CompilerMessageSourceLocation;)V", "getSeverity", "()Lorg/jetbrains/kotlin/cli/common/messages/CompilerMessageSeverity;", "getMessage", "()Ljava/lang/String;", "getLocation", "()Lorg/jetbrains/kotlin/cli/common/messages/CompilerMessageSourceLocation;", "toString", "component1", "component2", "component3", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "org.jetbrains.kotlin:util"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class Message {
        private final CompilerMessageSourceLocation location;
        private final String message;
        private final CompilerMessageSeverity severity;

        public Message(CompilerMessageSeverity compilerMessageSeverity, String str, CompilerMessageSourceLocation compilerMessageSourceLocation) {
            compilerMessageSeverity.getClass();
            str.getClass();
            this.severity = compilerMessageSeverity;
            this.message = str;
            this.location = compilerMessageSourceLocation;
        }

        public static /* synthetic */ Message copy$default(Message message, CompilerMessageSeverity compilerMessageSeverity, String str, CompilerMessageSourceLocation compilerMessageSourceLocation, int i, Object obj) {
            if ((i & 1) != 0) {
                compilerMessageSeverity = message.severity;
            }
            if ((i & 2) != 0) {
                str = message.message;
            }
            if ((i & 4) != 0) {
                compilerMessageSourceLocation = message.location;
            }
            return message.copy(compilerMessageSeverity, str, compilerMessageSourceLocation);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final CompilerMessageSeverity getSeverity() {
            return this.severity;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getMessage() {
            return this.message;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final CompilerMessageSourceLocation getLocation() {
            return this.location;
        }

        public final Message copy(CompilerMessageSeverity severity, String message, CompilerMessageSourceLocation location) {
            severity.getClass();
            message.getClass();
            return new Message(severity, message, location);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Message)) {
                return false;
            }
            Message message = (Message) other;
            return this.severity == message.severity && Intrinsics.areEqual(this.message, message.message) && Intrinsics.areEqual(this.location, message.location);
        }

        public final CompilerMessageSourceLocation getLocation() {
            return this.location;
        }

        public final String getMessage() {
            return this.message;
        }

        public final CompilerMessageSeverity getSeverity() {
            return this.severity;
        }

        public int hashCode() {
            int iHashCode = ((this.severity.hashCode() * 31) + this.message.hashCode()) * 31;
            CompilerMessageSourceLocation compilerMessageSourceLocation = this.location;
            return iHashCode + (compilerMessageSourceLocation == null ? 0 : compilerMessageSourceLocation.hashCode());
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            CompilerMessageSourceLocation compilerMessageSourceLocation = this.location;
            if (compilerMessageSourceLocation != null) {
                sb.append(compilerMessageSourceLocation);
                sb.append(": ");
            }
            sb.append(this.severity.getPresentableName());
            sb.append(": ");
            sb.append(this.message);
            return sb.toString();
        }
    }

    @Override // org.jetbrains.kotlin.cli.common.messages.MessageCollector
    public void clear() {
        this.messages.clear();
    }

    public final void forward(MessageCollector other) {
        other.getClass();
        for (Message message : this.messages) {
            other.report(message.getSeverity(), message.getMessage(), message.getLocation());
        }
    }

    public final List<Message> getErrors() {
        List<Message> list = this.messages;
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (((Message) obj).getSeverity().isError()) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public final List<Message> getMessages() {
        return this.messages;
    }

    @Override // org.jetbrains.kotlin.cli.common.messages.MessageCollector
    public boolean hasErrors() {
        List<Message> list = this.messages;
        if ((list instanceof Collection) && list.isEmpty()) {
            return false;
        }
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            if (((Message) it.next()).getSeverity().isError()) {
                return true;
            }
        }
        return false;
    }

    @Override // org.jetbrains.kotlin.cli.common.messages.MessageCollector
    public void report(CompilerMessageSeverity severity, String message, CompilerMessageSourceLocation location) {
        severity.getClass();
        message.getClass();
        this.messages.add(new Message(severity, message, location));
    }

    public String toString() {
        return CollectionsKt.joinToString$default(this.messages, "\n", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
    }
}
