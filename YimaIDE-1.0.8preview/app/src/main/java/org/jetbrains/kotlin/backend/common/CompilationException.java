package org.jetbrains.kotlin.backend.common;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.IrElement;
import org.jetbrains.kotlin.ir.declarations.IrFile;
import org.jetbrains.kotlin.ir.types.IrType;
import org.jetbrains.kotlin.ir.util.DumpKotlinLikeKt;
import org.jetbrains.kotlin.ir.util.KotlinLikeDumpOptions;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\t\u0018\u00002\u00060\u0001j\u0002`\u0002B/\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u000b\u0010\fJ\u000e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0005\u001a\u00020\u0006R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0015\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\u0019\u001a\u0004\u0018\u00010\u00048F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u0016R\u0011\u0010\u001b\u001a\u00020\u00108F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u001e\u001a\u00020\u00108F¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u001dR\u0015\u0010\u0003\u001a\u00020\u00048VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b \u0010\u0016¨\u0006!"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/CompilationException;", "Ljava/lang/RuntimeException;", "Lkotlin/RuntimeException;", "message", "", "file", "Lorg/jetbrains/kotlin/ir/declarations/IrFile;", "ir", "", "cause", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Ljava/lang/String;Lorg/jetbrains/kotlin/ir/declarations/IrFile;Ljava/lang/Object;Ljava/lang/Throwable;)V", "fileDetails", "Lorg/jetbrains/kotlin/backend/common/CompilationExceptionFileDetails;", "irStartOffset", "", "getIrStartOffset", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "content", "getContent", "()Ljava/lang/String;", "initializeFileDetails", "", "path", "getPath", "line", "getLine", "()I", "column", "getColumn", "getMessage", "org.jetbrains.kotlin:ir.backend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class CompilationException extends RuntimeException {
    private final String content;
    private CompilationExceptionFileDetails fileDetails;
    private final Integer irStartOffset;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CompilationException(String str, IrFile irFile, Object obj, Throwable th) {
        super(str, th);
        str.getClass();
        boolean z = obj instanceof IrElement;
        String strDumpKotlinLike = null;
        IrElement irElement = z ? (IrElement) obj : null;
        this.irStartOffset = irElement != null ? Integer.valueOf(irElement.getStartOffset()) : null;
        if (z) {
            strDumpKotlinLike = DumpKotlinLikeKt.dumpKotlinLike$default((IrElement) obj, (KotlinLikeDumpOptions) null, 1, (Object) null);
        } else if (obj instanceof IrType) {
            strDumpKotlinLike = DumpKotlinLikeKt.dumpKotlinLike((IrType) obj);
        }
        this.content = strDumpKotlinLike;
        if (irFile != null) {
            initializeFileDetails(irFile);
        }
    }

    public final int getColumn() {
        CompilationExceptionFileDetails compilationExceptionFileDetails = this.fileDetails;
        if (compilationExceptionFileDetails != null) {
            return compilationExceptionFileDetails.getColumn();
        }
        return -1;
    }

    public final String getContent() {
        return this.content;
    }

    public final Integer getIrStartOffset() {
        return this.irStartOffset;
    }

    public final int getLine() {
        CompilationExceptionFileDetails compilationExceptionFileDetails = this.fileDetails;
        if (compilationExceptionFileDetails != null) {
            return compilationExceptionFileDetails.getLine();
        }
        return -1;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("Back-end: Please report this problem https://kotl.in/issue");
            sb.append('\n');
            CompilationExceptionFileDetails compilationExceptionFileDetails = this.fileDetails;
            if (compilationExceptionFileDetails != null) {
                compilationExceptionFileDetails.render(sb);
            }
            String str = this.content;
            if (str != null) {
                sb.append("Problem with `" + str + '`');
                sb.append('\n');
            }
            sb.append("Details: " + super.getMessage());
            return sb.toString();
        } catch (Throwable unused) {
            IllegalStateException illegalStateException = new IllegalStateException("Problem with constructing exception message");
            illegalStateException.setStackTrace(getStackTrace());
            throw illegalStateException;
        }
    }

    public final String getPath() {
        CompilationExceptionFileDetails compilationExceptionFileDetails = this.fileDetails;
        if (compilationExceptionFileDetails != null) {
            return compilationExceptionFileDetails.getPath();
        }
        return null;
    }

    public final void initializeFileDetails(IrFile file) {
        file.getClass();
        if (this.fileDetails != null) {
            return;
        }
        this.fileDetails = new CompilationExceptionFileDetails(file, this.irStartOffset);
    }

    public /* synthetic */ CompilationException(String str, IrFile irFile, Object obj, Throwable th, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, irFile, obj, (i & 8) != 0 ? null : th);
    }
}
