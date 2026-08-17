package org.jetbrains.kotlin.backend.konan;

import kotlin.Metadata;
import org.eclipse.jdt.internal.compiler.impl.CompilerOptions;
import org.jetbrains.kotlin.backend.common.ErrorReportingContext;
import org.jetbrains.kotlin.backend.common.ErrorReportingContextKt;
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageLocation;
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageSeverity;
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageSourceLocation;
import org.jetbrains.kotlin.cli.common.messages.MessageCollector;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.config.CommonConfigurationKeys;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.ir.IrElement;
import org.jetbrains.kotlin.ir.declarations.IrFile;
import org.jetbrains.kotlin.ir.util.DumpIrTreeOptions;
import org.jetbrains.kotlin.ir.util.RenderIrElementKt;
import org.jetbrains.kotlin.utils.KotlinExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0010\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u001a\"\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u001a\u0010\f\u001a\u00020\r*\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u0004\u001a\"\u0010\u0010\u001a\u00020\u00012\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\u0011\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0003\u001a\u00020\u0004\u001a\"\u0010\u0012\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\u0011\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0013"}, d2 = {"reportCompilationError", "", "Lorg/jetbrains/kotlin/backend/common/ErrorReportingContext;", "message", "", "compilerMessageLocation", "Lorg/jetbrains/kotlin/cli/common/messages/CompilerMessageLocation;", "irFile", "Lorg/jetbrains/kotlin/ir/declarations/IrFile;", "irElement", "Lorg/jetbrains/kotlin/ir/IrElement;", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "report", "", "priority", "Lorg/jetbrains/kotlin/cli/common/messages/CompilerMessageSeverity;", CompilerOptions.ERROR, CapturedVarsOptimizationMethodTransformerKt.REF_ELEMENT_FIELD, "renderCompilerError", "org.jetbrains.kotlin:ir.backend.native"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class ReportingKt {
    public static final Void error(IrFile irFile, IrElement irElement, String str) {
        str.getClass();
        throw new IllegalStateException(renderCompilerError(irFile, irElement, str).toString());
    }

    public static final String renderCompilerError(IrFile irFile, IrElement irElement, String str) {
        String strRender$default;
        str.getClass();
        StringBuilder sb = new StringBuilder();
        sb.append("Internal compiler error: " + str + '\n');
        if (irElement == null) {
            sb.append("(IR element is null)");
        } else {
            if (irFile != null) {
                sb.append("at " + ErrorReportingContextKt.getCompilerMessageLocation(irElement, irFile) + '\n');
            }
            try {
                strRender$default = RenderIrElementKt.render$default(irElement, (DumpIrTreeOptions) null, 1, (Object) null);
            } catch (Throwable unused) {
                strRender$default = "(unable to render IR element)";
            }
            sb.append(strRender$default);
        }
        return sb.toString();
    }

    public static final void report(CompilerConfiguration compilerConfiguration, CompilerMessageSeverity compilerMessageSeverity, String str) {
        compilerConfiguration.getClass();
        compilerMessageSeverity.getClass();
        str.getClass();
        MessageCollector.report$default((MessageCollector) compilerConfiguration.getNotNull(CommonConfigurationKeys.MESSAGE_COLLECTOR_KEY), compilerMessageSeverity, str, (CompilerMessageSourceLocation) null, 4, (Object) null);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.KotlinExceptionWithAttachments */
    public static final Void reportCompilationError(ErrorReportingContext errorReportingContext, String str, IrFile irFile, IrElement irElement) throws KotlinExceptionWithAttachments {
        errorReportingContext.getClass();
        str.getClass();
        irFile.getClass();
        irElement.getClass();
        ErrorReportingContextKt.report(errorReportingContext, CompilerMessageSeverity.ERROR, irElement, irFile, str);
        throw new KonanCompilationException(null, null, 3, null);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.KotlinExceptionWithAttachments */
    public static final Void reportCompilationError(ErrorReportingContext errorReportingContext, String str, CompilerMessageLocation compilerMessageLocation) throws KotlinExceptionWithAttachments {
        errorReportingContext.getClass();
        str.getClass();
        errorReportingContext.getMessageCollector().report(CompilerMessageSeverity.ERROR, str, compilerMessageLocation);
        throw new KonanCompilationException(null, null, 3, null);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.KotlinExceptionWithAttachments */
    public static final Void reportCompilationError(ErrorReportingContext errorReportingContext, String str) throws KotlinExceptionWithAttachments {
        errorReportingContext.getClass();
        str.getClass();
        ErrorReportingContextKt.report(errorReportingContext, CompilerMessageSeverity.ERROR, (IrElement) null, (IrFile) null, str);
        throw new KonanCompilationException(null, null, 3, null);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.KotlinExceptionWithAttachments */
    public static final Void reportCompilationError(CompilerConfiguration compilerConfiguration, String str) throws KotlinExceptionWithAttachments {
        compilerConfiguration.getClass();
        str.getClass();
        report(compilerConfiguration, CompilerMessageSeverity.ERROR, str);
        throw new KonanCompilationException(null, null, 3, null);
    }
}
