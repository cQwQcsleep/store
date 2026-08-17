package org.jetbrains.kotlin.ir.builders;

import kotlin.Metadata;
import org.jetbrains.kotlin.ir.IrElement;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\t¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/ir/builders/IrElementBuilder;", "", "<init>", "()V", "startOffset", "", "getStartOffset", "()I", "setStartOffset", "(I)V", "endOffset", "getEndOffset", "setEndOffset", "updateFrom", "", "from", "Lorg/jetbrains/kotlin/ir/IrElement;", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class IrElementBuilder {
    private int startOffset = -1;
    private int endOffset = -1;

    public final int getEndOffset() {
        return this.endOffset;
    }

    public final int getStartOffset() {
        return this.startOffset;
    }

    public final void setEndOffset(int i) {
        this.endOffset = i;
    }

    public final void setStartOffset(int i) {
        this.startOffset = i;
    }

    public final void updateFrom(IrElement from) {
        from.getClass();
        this.startOffset = from.getStartOffset();
        this.endOffset = from.getEndOffset();
    }
}
