package org.jetbrains.kotlin.cli.common;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.CliDiagnosticReportingKt;
import org.jetbrains.kotlin.cli.CliDiagnostics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageSeverity;
import org.jetbrains.kotlin.cli.common.messages.MessageCollector;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.progress.CompilationCanceledException;
import org.jetbrains.kotlin.progress.IncrementalNextRoundException;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a@\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u001a\n\u0010\n\u001a\u00020\u0001*\u00020\u000b\u001a\u0012\u0010\f\u001a\u00020\r*\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010¨\u0006\u0011"}, d2 = {"checkPluginsArguments", Argument.Delimiters.none, "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "useK2", "pluginClasspaths", Argument.Delimiters.none, Argument.Delimiters.none, "pluginOptions", "pluginConfigurations", "hasOOMCause", Argument.Delimiters.none, "reportCompilationCancelled", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/cli/common/messages/MessageCollector;", "e", "Lorg/jetbrains/kotlin/progress/CompilationCanceledException;", "org.jetbrains.kotlin:cli"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CLICompilerKt {
    public static final boolean checkPluginsArguments(CompilerConfiguration compilerConfiguration, boolean z, List<String> list, List<String> list2, List<String> list3) {
        compilerConfiguration.getClass();
        list.getClass();
        list2.getClass();
        list3.getClass();
        for (String str : list) {
            if (!new File(str).exists()) {
                CliDiagnosticReportingKt.report$default(compilerConfiguration, CliDiagnostics.INSTANCE.getCOMPILER_ARGUMENTS_ERROR(), "Plugin classpath entry points to a non-existent location: " + str, null, 4, null);
            }
        }
        boolean z2 = false;
        if (!list3.isEmpty()) {
            CliDiagnostics cliDiagnostics = CliDiagnostics.INSTANCE;
            CliDiagnosticReportingKt.report$default(compilerConfiguration, cliDiagnostics.getCOMPILER_PLUGIN_ARG_IS_EXPERIMENTAL(), "Argument -Xcompiler-plugin is experimental", null, 4, null);
            if (!z) {
                CliDiagnosticReportingKt.report$default(compilerConfiguration, cliDiagnostics.getCOMPILER_ARGUMENTS_ERROR(), "-Xcompiler-plugin argument is allowed only for language version 2.0. Please use -Xplugin argument for language version 1.9 and below", null, 4, null);
                z2 = true;
            }
            List<String> list4 = list;
            if (!list4.isEmpty() || !list2.isEmpty()) {
                StringBuilder sb = new StringBuilder("Mixing legacy and modern plugin arguments is prohibited. Please use only one syntax\nLegacy arguments:\n");
                if (!list4.isEmpty()) {
                    sb.append("  -Xplugin=" + CollectionsKt.joinToString$default(list, Argument.Delimiters.default, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null));
                    sb.append('\n');
                }
                Iterator<T> it = list2.iterator();
                while (it.hasNext()) {
                    sb.append("  -P " + ((String) it.next()));
                    sb.append('\n');
                }
                sb.append("Modern arguments:\n");
                Iterator<T> it2 = list3.iterator();
                while (it2.hasNext()) {
                    sb.append("  -Xcompiler-plugin=" + ((String) it2.next()));
                    sb.append('\n');
                }
                CliDiagnosticReportingKt.report$default(compilerConfiguration, CliDiagnostics.INSTANCE.getCOMPILER_ARGUMENTS_ERROR(), sb.toString(), null, 4, null);
                z2 = true;
            }
        }
        return !z2;
    }

    public static final boolean hasOOMCause(Throwable th) {
        th.getClass();
        if (th.getCause() instanceof OutOfMemoryError) {
            return true;
        }
        Throwable cause = th.getCause();
        if (cause != null) {
            return hasOOMCause(cause);
        }
        return false;
    }

    public static final void reportCompilationCancelled(MessageCollector messageCollector, CompilationCanceledException compilationCanceledException) {
        messageCollector.getClass();
        compilationCanceledException.getClass();
        if (compilationCanceledException instanceof IncrementalNextRoundException) {
            return;
        }
        messageCollector.report(CompilerMessageSeverity.INFO, "Compilation was canceled", null);
    }
}
