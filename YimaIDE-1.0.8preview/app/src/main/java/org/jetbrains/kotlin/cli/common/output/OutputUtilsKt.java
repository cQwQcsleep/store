package org.jetbrains.kotlin.cli.common.output;

import com.intellij.openapi.util.io.FileUtil;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import org.jetbrains.kotlin.backend.common.output.OutputFile;
import org.jetbrains.kotlin.backend.common.output.OutputFileCollection;
import org.jetbrains.kotlin.cli.CliDiagnosticReportingKt;
import org.jetbrains.kotlin.cli.CliDiagnostics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.messages.OutputMessageUtil;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.cli.common.output.OutputUtilsKt;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.KtSourcelessDiagnosticFactory;
import org.jetbrains.kotlin.incremental.components.ICFileMappingTracker;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00004\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001aL\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u000428\u0010\u0005\u001a4\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0006\u001a\u0012\u0010\f\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a,\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012¨\u0006\u0013"}, d2 = {"writeAll", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/backend/common/output/OutputFileCollection;", ModuleXmlParser.OUTPUT_DIR, "Ljava/io/File;", "report", "Lkotlin/Function2;", "Lorg/jetbrains/kotlin/backend/common/output/OutputFile;", "Lkotlin/ParameterName;", ModuleXmlParser.NAME, "outputInfo", "output", "writeAllTo", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "reportOutputFiles", Argument.Delimiters.none, "fileMappingTracker", "Lorg/jetbrains/kotlin/incremental/components/ICFileMappingTracker;", "org.jetbrains.kotlin:cli"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class OutputUtilsKt {
    public static Unit b(ICFileMappingTracker iCFileMappingTracker, boolean z, CompilerConfiguration compilerConfiguration, OutputFile outputFile, File file) {
        outputFile.getClass();
        file.getClass();
        if (iCFileMappingTracker != null) {
            iCFileMappingTracker.recordSourceFilesToOutputFileMapping(outputFile.getSourceFiles(), file);
            if (outputFile.getGeneratedForCompilerPlugin()) {
                List<File> sourceFiles = outputFile.getSourceFiles();
                if (!(sourceFiles instanceof Collection) || !sourceFiles.isEmpty()) {
                    Iterator<T> it = sourceFiles.iterator();
                    do {
                        if (it.hasNext()) {
                        }
                    } while (((File) it.next()).exists());
                    iCFileMappingTracker.recordOutputFileGeneratedForPlugin(file);
                }
                cpa.a("Output file affected by plugin-generated files should be based on at least one synthetic source file, but got ", CollectionsKt.joinToString$default(outputFile.getSourceFiles(), (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: wsa
                    public final Object invoke(Object obj) {
                        return OutputUtilsKt.writeAll$lambda$0$0$1$0((File) obj);
                    }
                }, 31, (Object) null));
                return null;
            }
        }
        if (z) {
            String outputMessage = OutputMessageUtil.formatOutputMessage(outputFile.getSourceFiles(), file);
            outputMessage.getClass();
            CliDiagnosticReportingKt.reportOutput$default(compilerConfiguration, outputMessage, null, 2, null);
        }
        return Unit.INSTANCE;
    }

    public static final void writeAll(OutputFileCollection outputFileCollection, File file, Function2<? super OutputFile, ? super File, Unit> function2) throws FileNotFoundException {
        outputFileCollection.getClass();
        file.getClass();
        for (OutputFile outputFile : outputFileCollection.asList()) {
            File file2 = new File(file, outputFile.getRelativePath());
            if (function2 != null) {
                function2.invoke(outputFile, file2);
            }
            try {
                FileUtil.writeToFile(file2, outputFile.asByteArray());
            } catch (FileNotFoundException e) {
                if (!file.isDirectory()) {
                    throw e;
                }
                throw new NoPermissionException("error while writing " + file2 + " (Permission denied)", e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence writeAll$lambda$0$0$1$0(File file) {
        file.getClass();
        String path = file.getPath();
        path.getClass();
        return path;
    }

    public static final void writeAllTo(OutputFileCollection outputFileCollection, File file) throws FileNotFoundException {
        outputFileCollection.getClass();
        file.getClass();
        writeAll(outputFileCollection, file, null);
    }

    public static final void writeAll(OutputFileCollection outputFileCollection, File file, final CompilerConfiguration compilerConfiguration, final boolean z, final ICFileMappingTracker iCFileMappingTracker) {
        outputFileCollection.getClass();
        file.getClass();
        compilerConfiguration.getClass();
        try {
            if (!z && iCFileMappingTracker == null) {
                writeAllTo(outputFileCollection, file);
            } else {
                writeAll(outputFileCollection, file, new Function2() { // from class: vsa
                    public final Object invoke(Object obj, Object obj2) {
                        return OutputUtilsKt.b(iCFileMappingTracker, z, compilerConfiguration, (OutputFile) obj, (File) obj2);
                    }
                });
            }
        } catch (FileNotFoundException unused) {
            CliDiagnosticReportingKt.report$default(compilerConfiguration, CliDiagnostics.INSTANCE.getIO_ERROR(), "directory not found: " + file, null, 4, null);
        } catch (NoPermissionException e) {
            KtSourcelessDiagnosticFactory io_error = CliDiagnostics.INSTANCE.getIO_ERROR();
            String message = e.getMessage();
            message.getClass();
            CliDiagnosticReportingKt.report$default(compilerConfiguration, io_error, message, null, 4, null);
        }
    }
}
