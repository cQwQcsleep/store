package org.jetbrains.kotlin.ir;

import kotlin.Metadata;
import org.eclipse.jdt.internal.compiler.util.Util;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0010\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0011\u001a\u00020\u0012HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/ir/LineAndColumn;", "", "line", "", "column", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(II)V", "getLine", "()I", "getColumn", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final /* data */ class LineAndColumn {
    private final int column;
    private final int line;

    public LineAndColumn(int i, int i2) {
        this.line = i;
        this.column = i2;
    }

    public static /* synthetic */ LineAndColumn copy$default(LineAndColumn lineAndColumn, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = lineAndColumn.line;
        }
        if ((i3 & 2) != 0) {
            i2 = lineAndColumn.column;
        }
        return lineAndColumn.copy(i, i2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getLine() {
        return this.line;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getColumn() {
        return this.column;
    }

    public final LineAndColumn copy(int line, int column) {
        return new LineAndColumn(line, column);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LineAndColumn)) {
            return false;
        }
        LineAndColumn lineAndColumn = (LineAndColumn) other;
        return this.line == lineAndColumn.line && this.column == lineAndColumn.column;
    }

    public final int getColumn() {
        return this.column;
    }

    public final int getLine() {
        return this.line;
    }

    public int hashCode() {
        return (Integer.hashCode(this.line) * 31) + Integer.hashCode(this.column);
    }

    public String toString() {
        return "LineAndColumn(line=" + this.line + ", column=" + this.column + Util.C_PARAM_END;
    }
}
