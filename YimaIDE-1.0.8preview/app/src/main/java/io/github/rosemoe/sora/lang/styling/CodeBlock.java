package io.github.rosemoe.sora.lang.styling;

import io.github.rosemoe.sora.lang.styling.CodeBlock;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class CodeBlock {
    public static final Comparator<CodeBlock> COMPARATOR_END = new Comparator() { // from class: w22
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return CodeBlock.b((CodeBlock) obj, (CodeBlock) obj2);
        }
    };
    public static final Comparator<CodeBlock> COMPARATOR_START = new Comparator() { // from class: x22
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return CodeBlock.a((CodeBlock) obj, (CodeBlock) obj2);
        }
    };
    public int endColumn;
    public int endLine;
    public int startColumn;
    public int startLine;
    public boolean toBottomOfEndLine;

    public static /* synthetic */ int a(CodeBlock codeBlock, CodeBlock codeBlock2) {
        int iCompare = Integer.compare(codeBlock.startLine, codeBlock2.startLine);
        return iCompare == 0 ? Integer.compare(codeBlock.startColumn, codeBlock2.startColumn) : iCompare;
    }

    public static /* synthetic */ int b(CodeBlock codeBlock, CodeBlock codeBlock2) {
        int iCompare = Integer.compare(codeBlock.endLine, codeBlock2.endLine);
        return iCompare == 0 ? Integer.compare(codeBlock.endColumn, codeBlock2.endColumn) : iCompare;
    }

    public static int binarySearchEndBlock(int i, List<CodeBlock> list) {
        if (list != null && !list.isEmpty()) {
            int size = list.size() - 1;
            int i2 = 0;
            int i3 = size;
            while (i2 <= i3) {
                int i4 = ((i3 - i2) / 2) + i2;
                if (i4 >= 0 && i4 <= size) {
                    CodeBlock codeBlock = list.get(i4);
                    if (codeBlock == null) {
                        int i5 = i4 - 1;
                        while (true) {
                            i4++;
                            if (i5 < i2 && i4 > i3) {
                                return -1;
                            }
                            if (i5 >= i2 && list.get(i5) != null) {
                                i4 = i5;
                                break;
                            }
                            if (i4 <= i3 && list.get(i4) != null) {
                                break;
                            }
                            i5--;
                        }
                        codeBlock = list.get(i4);
                    }
                    int i6 = codeBlock.endLine;
                    if (i6 <= i) {
                        if (i6 >= i) {
                            i2 = i4;
                            break;
                        }
                        i2 = i4 + 1;
                    } else {
                        i3 = i4 - 1;
                    }
                } else {
                    return -1;
                }
            }
            if (i2 >= 0 && i2 <= size) {
                return i2;
            }
        }
        return -1;
    }

    public void clear() {
        this.endColumn = 0;
        this.endLine = 0;
        this.startLine = 0;
        this.startColumn = 0;
        this.toBottomOfEndLine = false;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            CodeBlock codeBlock = (CodeBlock) obj;
            if (this.startLine == codeBlock.startLine && this.startColumn == codeBlock.startColumn && this.endLine == codeBlock.endLine && this.endColumn == codeBlock.endColumn && this.toBottomOfEndLine == codeBlock.toBottomOfEndLine) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.startLine), Integer.valueOf(this.startColumn), Integer.valueOf(this.endLine), Integer.valueOf(this.endColumn), Boolean.valueOf(this.toBottomOfEndLine));
    }

    public String toString() {
        return "BlockLine{startLine=" + this.startLine + ", startColumn=" + this.startColumn + ", endLine=" + this.endLine + ", endColumn=" + this.endColumn + ", toBottomOfEndLine=" + this.toBottomOfEndLine + '}';
    }
}
