package org.jetbrains.kotlin.backend.jvm.codegen;

import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.org.objectweb.asm.Label;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/backend/jvm/codegen/Gap;", "", "start", "Lorg/jetbrains/org/objectweb/asm/Label;", "end", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/org/objectweb/asm/Label;Lorg/jetbrains/org/objectweb/asm/Label;)V", "getStart", "()Lorg/jetbrains/org/objectweb/asm/Label;", "getEnd", "org.jetbrains.kotlin:backend.jvm.codegen"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class Gap {
    private final Label end;
    private final Label start;

    public Gap(Label label, Label label2) {
        label.getClass();
        label2.getClass();
        this.start = label;
        this.end = label2;
    }

    public final Label getEnd() {
        return this.end;
    }

    public final Label getStart() {
        return this.start;
    }
}
