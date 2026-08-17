package org.jetbrains.kotlin.cli.jvm.compiler;

import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.PrintStream;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.text.Charsets;
import org.jetbrains.kotlin.cli.CliDiagnosticReportingKt;
import org.jetbrains.kotlin.cli.CliDiagnostics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageLocation;
import org.jetbrains.kotlin.cli.jvm.compiler.JvmPackagePartProviderKt;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.KtSourcelessDiagnosticFactory;
import org.jetbrains.kotlin.load.kotlin.ModuleMappingUtilKt;
import org.jetbrains.kotlin.metadata.deserialization.MetadataVersion;
import org.jetbrains.kotlin.metadata.jvm.deserialization.ModuleMapping;
import org.jetbrains.kotlin.resolve.JvmCompilerDeserializationConfiguration;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a6\u0010\u0000\u001a\u0004\u0018\u00010\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b¨\u0006\f"}, d2 = {"tryLoadModuleMapping", "Lorg/jetbrains/kotlin/metadata/jvm/deserialization/ModuleMapping;", "getModuleBytes", "Lkotlin/Function0;", Argument.Delimiters.none, "debugName", Argument.Delimiters.none, "modulePath", "deserializationConfiguration", "Lorg/jetbrains/kotlin/resolve/JvmCompilerDeserializationConfiguration;", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "org.jetbrains.kotlin:cli-base"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class JvmPackagePartProviderKt {
    public static Unit a(CompilerConfiguration compilerConfiguration, String str, MetadataVersion metadataVersion) {
        metadataVersion.getClass();
        CliDiagnosticReportingKt.report(compilerConfiguration, CliDiagnostics.INSTANCE.getJAVA_MODULE_RESOLUTION_ERROR(), "Module was compiled with an incompatible version of Kotlin. The binary version of its metadata is " + metadataVersion + ", expected version is " + MetadataVersion.INSTANCE + '.', CompilerMessageLocation.INSTANCE.create(str));
        return Unit.INSTANCE;
    }

    public static final ModuleMapping tryLoadModuleMapping(Function0<byte[]> function0, String str, final String str2, JvmCompilerDeserializationConfiguration jvmCompilerDeserializationConfiguration, final CompilerConfiguration compilerConfiguration) {
        function0.getClass();
        str.getClass();
        str2.getClass();
        jvmCompilerDeserializationConfiguration.getClass();
        compilerConfiguration.getClass();
        try {
            return ModuleMappingUtilKt.loadModuleMapping(ModuleMapping.Companion, (byte[]) function0.invoke(), str, jvmCompilerDeserializationConfiguration, new Function1() { // from class: qz7
                public final Object invoke(Object obj) {
                    return JvmPackagePartProviderKt.a(compilerConfiguration, str2, (MetadataVersion) obj);
                }
            });
        } catch (EOFException e) {
            KtSourcelessDiagnosticFactory java_module_resolution_error = CliDiagnostics.INSTANCE.getJAVA_MODULE_RESOLUTION_ERROR();
            String str3 = "Error occurred when reading the module: " + e.getMessage();
            CompilerMessageLocation.Companion companion = CompilerMessageLocation.INSTANCE;
            CliDiagnosticReportingKt.report(compilerConfiguration, java_module_resolution_error, str3, companion.create(str2));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(byteArrayOutputStream));
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            byteArray.getClass();
            CliDiagnosticReportingKt.reportLog(compilerConfiguration, new String(byteArray, Charsets.UTF_8), companion.create(str2));
            return null;
        }
    }
}
