package org.jetbrains.kotlin.backend.jvm.codegen;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.declarations.IrVariable;
import org.jetbrains.org.objectweb.asm.Label;
import org.jetbrains.org.objectweb.asm.Type;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u001c\u0010\u0019\u001a\u0004\u0018\u00010\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0013\"\u0004\b\u001b\u0010\u001c¨\u0006\u001d"}, d2 = {"Lorg/jetbrains/kotlin/backend/jvm/codegen/VariableInfo;", "", "declaration", "Lorg/jetbrains/kotlin/ir/declarations/IrVariable;", "index", "", "type", "Lorg/jetbrains/org/objectweb/asm/Type;", "startLabel", "Lorg/jetbrains/org/objectweb/asm/Label;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/ir/declarations/IrVariable;ILorg/jetbrains/org/objectweb/asm/Type;Lorg/jetbrains/org/objectweb/asm/Label;)V", "getDeclaration", "()Lorg/jetbrains/kotlin/ir/declarations/IrVariable;", "getIndex", "()I", "getType", "()Lorg/jetbrains/org/objectweb/asm/Type;", "getStartLabel", "()Lorg/jetbrains/org/objectweb/asm/Label;", "gaps", "", "Lorg/jetbrains/kotlin/backend/jvm/codegen/Gap;", "getGaps", "()Ljava/util/List;", "explicitEndLabel", "getExplicitEndLabel", "setExplicitEndLabel", "(Lorg/jetbrains/org/objectweb/asm/Label;)V", "org.jetbrains.kotlin:backend.jvm.codegen"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class VariableInfo {
    private final IrVariable declaration;
    private Label explicitEndLabel;
    private final List<Gap> gaps;
    private final int index;
    private final Label startLabel;
    private final Type type;

    public VariableInfo(IrVariable irVariable, int i, Type type, Label label) {
        irVariable.getClass();
        type.getClass();
        label.getClass();
        this.declaration = irVariable;
        this.index = i;
        this.type = type;
        this.startLabel = label;
        this.gaps = new ArrayList();
    }

    public final IrVariable getDeclaration() {
        return this.declaration;
    }

    public final Label getExplicitEndLabel() {
        return this.explicitEndLabel;
    }

    public final List<Gap> getGaps() {
        return this.gaps;
    }

    public final int getIndex() {
        return this.index;
    }

    public final Label getStartLabel() {
        return this.startLabel;
    }

    public final Type getType() {
        return this.type;
    }

    public final void setExplicitEndLabel(Label label) {
        this.explicitEndLabel = label;
    }
}
