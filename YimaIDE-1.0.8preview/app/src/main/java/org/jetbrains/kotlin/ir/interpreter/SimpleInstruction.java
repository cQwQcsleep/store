package org.jetbrains.kotlin.ir.interpreter;

import kotlin.Metadata;
import org.jetbrains.kotlin.ir.IrElement;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/ir/interpreter/SimpleInstruction;", "Lorg/jetbrains/kotlin/ir/interpreter/Instruction;", "element", "Lorg/jetbrains/kotlin/ir/IrElement;", "<init>", "(Lorg/jetbrains/kotlin/ir/IrElement;)V", "getElement", "()Lorg/jetbrains/kotlin/ir/IrElement;", "org.jetbrains.kotlin:ir.interpreter"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class SimpleInstruction implements Instruction {
    private final IrElement element;

    public SimpleInstruction(IrElement irElement) {
        irElement.getClass();
        this.element = irElement;
    }

    @Override // org.jetbrains.kotlin.ir.interpreter.Instruction
    public IrElement getElement() {
        return this.element;
    }
}
