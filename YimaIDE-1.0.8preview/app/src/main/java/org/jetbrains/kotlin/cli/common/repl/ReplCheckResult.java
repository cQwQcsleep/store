package org.jetbrains.kotlin.cli.common.repl;

import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageLocation;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \u00072\u00020\u0001:\u0004\u0004\u0005\u0006\u0007B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0003\b\t\n¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/repl/ReplCheckResult;", "Ljava/io/Serializable;", "<init>", "()V", "Ok", "Incomplete", "Error", "Companion", "Lorg/jetbrains/kotlin/cli/common/repl/ReplCheckResult$Error;", "Lorg/jetbrains/kotlin/cli/common/repl/ReplCheckResult$Incomplete;", "Lorg/jetbrains/kotlin/cli/common/repl/ReplCheckResult$Ok;", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class ReplCheckResult implements Serializable {
    private static final long serialVersionUID = 8228307678L;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/repl/ReplCheckResult$Incomplete;", "Lorg/jetbrains/kotlin/cli/common/repl/ReplCheckResult;", "<init>", "()V", "Companion", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Incomplete extends ReplCheckResult {
        private static final long serialVersionUID = 1;

        public Incomplete() {
            super(null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/repl/ReplCheckResult$Ok;", "Lorg/jetbrains/kotlin/cli/common/repl/ReplCheckResult;", "<init>", "()V", "Companion", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Ok extends ReplCheckResult {
        private static final long serialVersionUID = 1;

        public Ok() {
            super(null);
        }
    }

    public /* synthetic */ ReplCheckResult(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private ReplCheckResult() {
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\n\u0010\f\u001a\u00020\u0003H\u0096\u0080\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/repl/ReplCheckResult$Error;", "Lorg/jetbrains/kotlin/cli/common/repl/ReplCheckResult;", "message", Argument.Delimiters.none, "location", "Lorg/jetbrains/kotlin/cli/common/messages/CompilerMessageLocation;", "<init>", "(Ljava/lang/String;Lorg/jetbrains/kotlin/cli/common/messages/CompilerMessageLocation;)V", "getMessage", "()Ljava/lang/String;", "getLocation", "()Lorg/jetbrains/kotlin/cli/common/messages/CompilerMessageLocation;", "toString", "Companion", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Error extends ReplCheckResult {
        private static final long serialVersionUID = 1;
        private final CompilerMessageLocation location;
        private final String message;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Error(String str, CompilerMessageLocation compilerMessageLocation) {
            super(null);
            str.getClass();
            this.message = str;
            this.location = compilerMessageLocation;
        }

        public final CompilerMessageLocation getLocation() {
            return this.location;
        }

        public final String getMessage() {
            return this.message;
        }

        public String toString() {
            return "Error(message = \"" + this.message + "\")";
        }

        public /* synthetic */ Error(String str, CompilerMessageLocation compilerMessageLocation, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i & 2) != 0 ? null : compilerMessageLocation);
        }
    }
}
