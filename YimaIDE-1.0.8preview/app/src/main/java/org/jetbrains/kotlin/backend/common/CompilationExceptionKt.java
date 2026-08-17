package org.jetbrains.kotlin.backend.common;

import kotlin.Metadata;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.IrElement;
import org.jetbrains.kotlin.ir.declarations.IrDeclaration;
import org.jetbrains.kotlin.ir.declarations.IrFile;
import org.jetbrains.kotlin.ir.types.IrType;
import org.jetbrains.kotlin.ir.util.IrUtilsKt;
import org.jetbrains.kotlin.utils.exceptions.PlatformExceptionUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0016\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u001a\u0016\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\t\u001a$\u0010\n\u001a\u00020\u000b*\u00020\u000b2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\r2\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¨\u0006\u000e"}, d2 = {"compilationException", "", "message", "", CapturedVarsOptimizationMethodTransformerKt.REF_ELEMENT_FIELD, "Lorg/jetbrains/kotlin/ir/IrElement;", "type", "Lorg/jetbrains/kotlin/ir/types/IrType;", "declaration", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclaration;", "wrapWithCompilationException", "", "file", "Lorg/jetbrains/kotlin/ir/declarations/IrFile;", "org.jetbrains.kotlin:ir.backend.common"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class CompilationExceptionKt {
    public static final Void compilationException(String str, IrDeclaration irDeclaration) {
        IrFile fileOrNull;
        str.getClass();
        irDeclaration.getClass();
        try {
            fileOrNull = IrUtilsKt.getFileOrNull(irDeclaration);
        } catch (Throwable unused) {
            fileOrNull = null;
        }
        throw new CompilationException(str, fileOrNull, irDeclaration, null, 8, null);
    }

    public static final Throwable wrapWithCompilationException(Throwable th, String str, IrFile irFile, IrElement irElement) {
        th.getClass();
        str.getClass();
        irFile.getClass();
        if (PlatformExceptionUtilsKt.shouldIjPlatformExceptionBeRethrown(th)) {
            return th;
        }
        CompilationException compilationException = new CompilationException(str + ": " + Reflection.getOrCreateKotlinClass(th.getClass()).getQualifiedName() + ": " + th.getMessage(), irFile, irElement, th);
        compilationException.setStackTrace(th.getStackTrace());
        return compilationException;
    }

    public static final Void compilationException(String str, IrType irType) {
        str.getClass();
        throw new CompilationException(str, null, irType, null, 8, null);
    }

    public static final Void compilationException(String str, IrElement irElement) {
        str.getClass();
        irElement.getClass();
        throw new CompilationException(str, null, irElement, null, 8, null);
    }
}
