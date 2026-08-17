package org.jetbrains.kotlin.cli.common.repl;

import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.arguments.K2JsArgumentConstants;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u0000 \u00182\u00020\u00012\u00020\u0002:\u0001\u0018B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u0012\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u0001H\u0096\u0082\u0004J\t\u0010\u000e\u001a\u00020\u0004HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0004HÂ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u0004HÆ\u0001J\u0014\u0010\u0012\u001a\u00020\u00132\b\u0010\r\u001a\u0004\u0018\u00010\u0014HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\u0004HÖ\u0081\u0004J\n\u0010\u0016\u001a\u00020\u0017HÖ\u0081\u0004R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u0005\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u000e\u0010\u0006\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/repl/LineId;", "Lorg/jetbrains/kotlin/cli/common/repl/ILineId;", "Ljava/io/Serializable;", K2JsArgumentConstants.SOURCE_MAP_NAMES_POLICY_NO, Argument.Delimiters.none, "generation", "codeHash", "<init>", "(III)V", "getNo", "()I", "getGeneration", "compareTo", "other", "component1", "component2", "component3", "copy", "equals", Argument.Delimiters.none, Argument.Delimiters.none, "hashCode", "toString", Argument.Delimiters.none, "Companion", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class LineId implements Serializable, ILineId {
    private static final long serialVersionUID = 8328354000L;
    private final int codeHash;
    private final int generation;
    private final int no;

    public LineId(int i, int i2, int i3) {
        this.no = i;
        this.generation = i2;
        this.codeHash = i3;
    }

    public static /* synthetic */ LineId copy$default(LineId lineId, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i = lineId.no;
        }
        if ((i4 & 2) != 0) {
            i2 = lineId.generation;
        }
        if ((i4 & 4) != 0) {
            i3 = lineId.codeHash;
        }
        return lineId.copy(i, i2, i3);
    }

    @Override // java.lang.Comparable
    public int compareTo(ILineId other) {
        other.getClass();
        LineId lineId = other instanceof LineId ? (LineId) other : null;
        if (lineId == null) {
            return -1;
        }
        Integer numValueOf = Integer.valueOf(Intrinsics.compare(getNo(), lineId.getNo()));
        Integer num = numValueOf.intValue() != 0 ? numValueOf : null;
        return num != null ? num.intValue() : Intrinsics.compare(this.codeHash, lineId.codeHash);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getNo() {
        return this.no;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getGeneration() {
        return this.generation;
    }

    public final LineId copy(int no, int generation, int codeHash) {
        return new LineId(no, generation, codeHash);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LineId)) {
            return false;
        }
        LineId lineId = (LineId) other;
        return this.no == lineId.no && this.generation == lineId.generation && this.codeHash == lineId.codeHash;
    }

    @Override // org.jetbrains.kotlin.cli.common.repl.ILineId
    public int getGeneration() {
        return this.generation;
    }

    @Override // org.jetbrains.kotlin.cli.common.repl.ILineId
    public int getNo() {
        return this.no;
    }

    public int hashCode() {
        return (((Integer.hashCode(this.no) * 31) + Integer.hashCode(this.generation)) * 31) + Integer.hashCode(this.codeHash);
    }

    public String toString() {
        return "LineId(no=" + this.no + ", generation=" + this.generation + ", codeHash=" + this.codeHash + ')';
    }
}
