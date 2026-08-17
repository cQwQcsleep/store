package org.jetbrains.kotlin.cli.common.repl;

import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/repl/CompiledReplCodeLine;", "Ljava/io/Serializable;", "className", Argument.Delimiters.none, "source", "Lorg/jetbrains/kotlin/cli/common/repl/ReplCodeLine;", "<init>", "(Ljava/lang/String;Lorg/jetbrains/kotlin/cli/common/repl/ReplCodeLine;)V", "getClassName", "()Ljava/lang/String;", "getSource", "()Lorg/jetbrains/kotlin/cli/common/repl/ReplCodeLine;", "component1", "component2", "copy", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", "Companion", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class CompiledReplCodeLine implements Serializable {
    private static final long serialVersionUID = 8228307678L;
    private final String className;
    private final ReplCodeLine source;

    public CompiledReplCodeLine(String str, ReplCodeLine replCodeLine) {
        str.getClass();
        replCodeLine.getClass();
        this.className = str;
        this.source = replCodeLine;
    }

    public static /* synthetic */ CompiledReplCodeLine copy$default(CompiledReplCodeLine compiledReplCodeLine, String str, ReplCodeLine replCodeLine, int i, Object obj) {
        if ((i & 1) != 0) {
            str = compiledReplCodeLine.className;
        }
        if ((i & 2) != 0) {
            replCodeLine = compiledReplCodeLine.source;
        }
        return compiledReplCodeLine.copy(str, replCodeLine);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getClassName() {
        return this.className;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final ReplCodeLine getSource() {
        return this.source;
    }

    public final CompiledReplCodeLine copy(String className, ReplCodeLine source) {
        className.getClass();
        source.getClass();
        return new CompiledReplCodeLine(className, source);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CompiledReplCodeLine)) {
            return false;
        }
        CompiledReplCodeLine compiledReplCodeLine = (CompiledReplCodeLine) other;
        return Intrinsics.areEqual(this.className, compiledReplCodeLine.className) && Intrinsics.areEqual(this.source, compiledReplCodeLine.source);
    }

    public final String getClassName() {
        return this.className;
    }

    public final ReplCodeLine getSource() {
        return this.source;
    }

    public int hashCode() {
        return (this.className.hashCode() * 31) + this.source.hashCode();
    }

    public String toString() {
        return "CompiledReplCodeLine(className=" + this.className + ", source=" + this.source + ')';
    }
}
