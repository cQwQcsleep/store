package org.jetbrains.kotlin.backend.common;

import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationsKt;
import org.jetbrains.kotlin.ir.declarations.IrFile;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0012\u0010\u0011\u001a\u00020\u00122\n\u0010\u0013\u001a\u00060\u0014j\u0002`\u0015R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000e¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/CompilationExceptionFileDetails;", "", "file", "Lorg/jetbrains/kotlin/ir/declarations/IrFile;", "irStartOffset", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/ir/declarations/IrFile;Ljava/lang/Integer;)V", "path", "", "getPath", "()Ljava/lang/String;", "line", "getLine", "()I", "column", "getColumn", "render", "", "stringBuilder", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "org.jetbrains.kotlin:ir.backend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
final class CompilationExceptionFileDetails {
    private final int column;
    private final int line;
    private final String path;

    public CompilationExceptionFileDetails(IrFile irFile, Integer num) {
        irFile.getClass();
        this.path = IrDeclarationsKt.getPath(irFile);
        int columnNumber = -1;
        this.line = (num == null || num.intValue() == -1) ? -1 : irFile.getFileEntry().getLineNumber(num.intValue()) + 1;
        if (num != null && num.intValue() != -1) {
            columnNumber = irFile.getFileEntry().getColumnNumber(num.intValue()) + 1;
        }
        this.column = columnNumber;
    }

    public final int getColumn() {
        return this.column;
    }

    public final int getLine() {
        return this.line;
    }

    public final String getPath() {
        return this.path;
    }

    public final void render(StringBuilder stringBuilder) {
        stringBuilder.getClass();
        stringBuilder.append(this.path + ':' + this.line + ':' + this.column);
        stringBuilder.append('\n');
    }
}
