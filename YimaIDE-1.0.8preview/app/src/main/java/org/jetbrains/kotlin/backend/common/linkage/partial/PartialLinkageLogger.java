package org.jetbrains.kotlin.backend.common.linkage.partial;

import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.config.PartialLinkageLogLevel;
import org.jetbrains.kotlin.diagnostics.KtSourcelessDiagnosticFactory;
import org.jetbrains.kotlin.ir.IrDiagnosticReporter;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0014B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u001e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/linkage/partial/PartialLinkageLogger;", "", "diagnosticReporter", "Lorg/jetbrains/kotlin/ir/IrDiagnosticReporter;", "logLevel", "Lorg/jetbrains/kotlin/config/PartialLinkageLogLevel;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/ir/IrDiagnosticReporter;Lorg/jetbrains/kotlin/config/PartialLinkageLogLevel;)V", "getDiagnosticReporter", "()Lorg/jetbrains/kotlin/ir/IrDiagnosticReporter;", "getLogLevel", "()Lorg/jetbrains/kotlin/config/PartialLinkageLogLevel;", "log", "", "message", "", "location", "Lorg/jetbrains/kotlin/backend/common/linkage/partial/PartialLinkageLogger$Location;", "significance", "Lorg/jetbrains/kotlin/backend/common/linkage/partial/PartialLinkageIssueSignificance;", "Location", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PartialLinkageLogger {
    private final IrDiagnosticReporter diagnosticReporter;
    private final PartialLinkageLogLevel logLevel;

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\n\u0010\u0010\u001a\u00060\u0011j\u0002`\u0012J\n\u0010\u0013\u001a\u00020\u0003H\u0096\u0080\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000e¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/linkage/partial/PartialLinkageLogger$Location;", "", "moduleName", "", "filePath", "lineNumber", "", "columnNumber", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Ljava/lang/String;Ljava/lang/String;II)V", "getModuleName", "()Ljava/lang/String;", "getFilePath", "getLineNumber", "()I", "getColumnNumber", "render", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "toString", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Location {
        private final int columnNumber;
        private final String filePath;
        private final int lineNumber;
        private final String moduleName;

        public Location(String str, String str2, int i, int i2) {
            str.getClass();
            str2.getClass();
            this.moduleName = str;
            this.filePath = str2;
            this.lineNumber = i;
            this.columnNumber = i2;
        }

        public final int getColumnNumber() {
            return this.columnNumber;
        }

        public final String getFilePath() {
            return this.filePath;
        }

        public final int getLineNumber() {
            return this.lineNumber;
        }

        public final String getModuleName() {
            return this.moduleName;
        }

        public final StringBuilder render() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.moduleName);
            if (this.filePath.length() > 0) {
                sb.append(" @ ");
                sb.append(this.filePath);
                if (this.lineNumber != -1 && this.columnNumber != -1) {
                    sb.append(':');
                    sb.append(this.lineNumber);
                    sb.append(':');
                    sb.append(this.columnNumber);
                }
            }
            return sb;
        }

        public String toString() {
            return render().toString();
        }
    }

    public PartialLinkageLogger(IrDiagnosticReporter irDiagnosticReporter, PartialLinkageLogLevel partialLinkageLogLevel) {
        irDiagnosticReporter.getClass();
        partialLinkageLogLevel.getClass();
        this.diagnosticReporter = irDiagnosticReporter;
        this.logLevel = partialLinkageLogLevel;
    }

    public final IrDiagnosticReporter getDiagnosticReporter() {
        return this.diagnosticReporter;
    }

    public final PartialLinkageLogLevel getLogLevel() {
        return this.logLevel;
    }

    public final void log(String message, Location location, PartialLinkageIssueSignificance significance) {
        message.getClass();
        location.getClass();
        significance.getClass();
        IrDiagnosticReporter irDiagnosticReporter = this.diagnosticReporter;
        KtSourcelessDiagnosticFactory diagnosticFactory = significance.toDiagnosticFactory();
        StringBuilder sbRender = location.render();
        sbRender.append(": ");
        sbRender.append(message);
        irDiagnosticReporter.report(diagnosticFactory, sbRender.toString());
    }
}
