package org.jetbrains.kotlin.js.parser;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.ir.BuiltInOperatorNames;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000e\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0012\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u0000H\u0096\u0082\u0004J\n\u0010\f\u001a\u00020\rH\u0096\u0080\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/js/parser/CodePosition;", "", "line", "", "offset", "<init>", "(II)V", "getLine", "()I", "getOffset", BuiltInOperatorNames.COMPARE_TO, "other", "toString", "", "org.jetbrains.kotlin:js.parser"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class CodePosition implements Comparable<CodePosition> {
    private final int line;
    private final int offset;

    public CodePosition(int i, int i2) {
        this.line = i;
        this.offset = i2;
    }

    @Override // java.lang.Comparable
    public int compareTo(CodePosition other) {
        other.getClass();
        int i = this.line;
        int i2 = other.line;
        if (i < i2) {
            return -1;
        }
        if (i > i2) {
            return 1;
        }
        return Intrinsics.compare(this.offset, other.offset);
    }

    public final int getLine() {
        return this.line;
    }

    public final int getOffset() {
        return this.offset;
    }

    public String toString() {
        return "(" + this.line + ", " + this.offset + ')';
    }
}
