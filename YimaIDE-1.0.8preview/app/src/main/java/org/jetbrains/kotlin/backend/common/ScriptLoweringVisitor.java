package org.jetbrains.kotlin.backend.common;

import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.IrElement;
import org.jetbrains.kotlin.ir.declarations.IrScript;
import org.jetbrains.kotlin.ir.visitors.IrVisitorVoid;
import org.jetbrains.kotlin.ir.visitors.IrVisitorsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/ScriptLoweringVisitor;", "Lorg/jetbrains/kotlin/ir/visitors/IrVisitorVoid;", "loweringPass", "Lorg/jetbrains/kotlin/backend/common/ScriptLoweringPass;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/backend/common/ScriptLoweringPass;)V", "visitElement", "", CapturedVarsOptimizationMethodTransformerKt.REF_ELEMENT_FIELD, "Lorg/jetbrains/kotlin/ir/IrElement;", "visitScript", "declaration", "Lorg/jetbrains/kotlin/ir/declarations/IrScript;", "org.jetbrains.kotlin:ir.backend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
final class ScriptLoweringVisitor extends IrVisitorVoid {
    private final ScriptLoweringPass loweringPass;

    public ScriptLoweringVisitor(ScriptLoweringPass scriptLoweringPass) {
        scriptLoweringPass.getClass();
        this.loweringPass = scriptLoweringPass;
    }

    public void visitElement(IrElement element) {
        element.getClass();
        IrVisitorsKt.acceptChildrenVoid(element, this);
    }

    public void visitScript(IrScript declaration) {
        declaration.getClass();
        IrVisitorsKt.acceptChildrenVoid(declaration, this);
        this.loweringPass.lower(declaration);
    }
}
