package org.jetbrains.kotlin.cli.js;

import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.CliDiagnosticReportingKt;
import org.jetbrains.kotlin.cli.CliDiagnostics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.arguments.K2JSCompilerArguments;
import org.jetbrains.kotlin.cli.common.arguments.K2JSCompilerArgumentsToKotlinWasmCompilerArgumentsCopyGeneratedKt;
import org.jetbrains.kotlin.cli.common.arguments.K2JsArgumentConstants;
import org.jetbrains.kotlin.cli.common.arguments.KotlinWasmCompilerArguments;
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageSeverity;
import org.jetbrains.kotlin.cli.common.messages.MessageCollector;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.js.config.RuntimeDiagnostic;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0002\u001a\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u0006*\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000b¨\u0006\f"}, d2 = {"toWasmArguments", "Lorg/jetbrains/kotlin/cli/common/arguments/KotlinWasmCompilerArguments;", "Lorg/jetbrains/kotlin/cli/common/arguments/K2JSCompilerArguments;", "diagnosticsCollector", "Lorg/jetbrains/kotlin/cli/common/messages/MessageCollector;", "resolve", "Lorg/jetbrains/kotlin/js/config/RuntimeDiagnostic;", "Lorg/jetbrains/kotlin/js/config/RuntimeDiagnostic$Companion;", "value", Argument.Delimiters.none, "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "org.jetbrains.kotlin:cli-js"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class K2JSCompilerKt {
    public static final RuntimeDiagnostic resolve(RuntimeDiagnostic.Companion companion, String str, CompilerConfiguration compilerConfiguration) {
        String lowerCase;
        companion.getClass();
        compilerConfiguration.getClass();
        if (str != null) {
            lowerCase = str.toLowerCase(Locale.ROOT);
            lowerCase.getClass();
        } else {
            lowerCase = null;
        }
        if (Intrinsics.areEqual(lowerCase, K2JsArgumentConstants.RUNTIME_DIAGNOSTIC_LOG)) {
            return RuntimeDiagnostic.LOG;
        }
        if (Intrinsics.areEqual(lowerCase, K2JsArgumentConstants.RUNTIME_DIAGNOSTIC_EXCEPTION)) {
            return RuntimeDiagnostic.EXCEPTION;
        }
        if (lowerCase == null) {
            return null;
        }
        CliDiagnosticReportingKt.report$default(compilerConfiguration, CliDiagnostics.INSTANCE.getWEB_ARGUMENT_WARNING(), "Unknown runtime diagnostic '" + str + '\'', null, 4, null);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final KotlinWasmCompilerArguments toWasmArguments(K2JSCompilerArguments k2JSCompilerArguments, MessageCollector messageCollector) {
        MessageCollector.report$default(messageCollector, CompilerMessageSeverity.WARNING, "Use `KotlinWasmCompiler` when compiling to Wasm. Using Wasm related arguments with `K2JSCompiler` will become an error in a future compiler version.", null, 4, null);
        return K2JSCompilerArgumentsToKotlinWasmCompilerArgumentsCopyGeneratedKt.copyK2JSCompilerArguments(k2JSCompilerArguments, new KotlinWasmCompilerArguments());
    }
}
