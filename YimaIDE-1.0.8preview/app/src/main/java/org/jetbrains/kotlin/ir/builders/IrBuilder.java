package org.jetbrains.kotlin.ir.builders;

import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\f\b&\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u0006\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\f\"\u0004\b\u0010\u0010\u000e¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/ir/builders/IrBuilder;", "Lorg/jetbrains/kotlin/ir/builders/IrGenerator;", "context", "Lorg/jetbrains/kotlin/ir/builders/IrGeneratorContext;", "startOffset", "", "endOffset", "<init>", "(Lorg/jetbrains/kotlin/ir/builders/IrGeneratorContext;II)V", "getContext", "()Lorg/jetbrains/kotlin/ir/builders/IrGeneratorContext;", "getStartOffset", "()I", "setStartOffset", "(I)V", "getEndOffset", "setEndOffset", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class IrBuilder implements IrGenerator {
    private final IrGeneratorContext context;
    private int endOffset;
    private int startOffset;

    public IrBuilder(IrGeneratorContext irGeneratorContext, int i, int i2) {
        irGeneratorContext.getClass();
        this.context = irGeneratorContext;
        this.startOffset = i;
        this.endOffset = i2;
    }

    @Override // org.jetbrains.kotlin.ir.builders.IrGenerator
    public IrGeneratorContext getContext() {
        return this.context;
    }

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
}
