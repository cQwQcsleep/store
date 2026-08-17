package org.jetbrains.kotlin.cli.common.repl;

import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.arguments.K2JsArgumentConstants;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0086\b\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0006HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0014\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015HÖ\u0083\u0004J\n\u0010\u0016\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0017\u001a\u00020\u0006HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/repl/ReplCodeLine;", "Ljava/io/Serializable;", K2JsArgumentConstants.SOURCE_MAP_NAMES_POLICY_NO, Argument.Delimiters.none, "generation", "code", Argument.Delimiters.none, "<init>", "(IILjava/lang/String;)V", "getNo", "()I", "getGeneration", "getCode", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", "toString", "Companion", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class ReplCodeLine implements Serializable {
    private static final long serialVersionUID = 8228357578L;
    private final String code;
    private final int generation;
    private final int no;

    public ReplCodeLine(int i, int i2, String str) {
        str.getClass();
        this.no = i;
        this.generation = i2;
        this.code = str;
    }

    public static /* synthetic */ ReplCodeLine copy$default(ReplCodeLine replCodeLine, int i, int i2, String str, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = replCodeLine.no;
        }
        if ((i3 & 2) != 0) {
            i2 = replCodeLine.generation;
        }
        if ((i3 & 4) != 0) {
            str = replCodeLine.code;
        }
        return replCodeLine.copy(i, i2, str);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getNo() {
        return this.no;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getGeneration() {
        return this.generation;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getCode() {
        return this.code;
    }

    public final ReplCodeLine copy(int no, int generation, String code) {
        code.getClass();
        return new ReplCodeLine(no, generation, code);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ReplCodeLine)) {
            return false;
        }
        ReplCodeLine replCodeLine = (ReplCodeLine) other;
        return this.no == replCodeLine.no && this.generation == replCodeLine.generation && Intrinsics.areEqual(this.code, replCodeLine.code);
    }

    public final String getCode() {
        return this.code;
    }

    public final int getGeneration() {
        return this.generation;
    }

    public final int getNo() {
        return this.no;
    }

    public int hashCode() {
        return (((Integer.hashCode(this.no) * 31) + Integer.hashCode(this.generation)) * 31) + this.code.hashCode();
    }

    public String toString() {
        return "ReplCodeLine(no=" + this.no + ", generation=" + this.generation + ", code=" + this.code + ')';
    }
}
