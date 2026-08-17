package org.jetbrains.kotlin.cli.js;

import com.intellij.openapi.Disposable;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.CLICompiler;
import org.jetbrains.kotlin.cli.common.ExitCode;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.arguments.CommonCompilerArguments;
import org.jetbrains.kotlin.cli.common.arguments.CommonJsAndWasmCompilerArguments;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.metadata.deserialization.BinaryVersion;
import org.jetbrains.kotlin.metadata.deserialization.MetadataVersion;
import org.jetbrains.kotlin.utils.KotlinPaths;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u0002\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0002\b\u0002\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J/\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00028\u00002\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0014¢\u0006\u0002\u0010\u000fJ\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0014J\u001f\u0010\u0014\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00170\u00162\u0006\u0010\b\u001a\u00028\u0000H\u0014¢\u0006\u0002\u0010\u0018¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/cli/js/KotlinWebCompilerBase;", "T", "Lorg/jetbrains/kotlin/cli/common/arguments/CommonJsAndWasmCompilerArguments;", "Lorg/jetbrains/kotlin/cli/common/CLICompiler;", "<init>", "()V", "doExecute", "Lorg/jetbrains/kotlin/cli/common/ExitCode;", "arguments", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "rootDisposable", "Lcom/intellij/openapi/Disposable;", "paths", "Lorg/jetbrains/kotlin/utils/KotlinPaths;", "(Lorg/jetbrains/kotlin/cli/common/arguments/CommonJsAndWasmCompilerArguments;Lorg/jetbrains/kotlin/config/CompilerConfiguration;Lcom/intellij/openapi/Disposable;Lorg/jetbrains/kotlin/utils/KotlinPaths;)Lorg/jetbrains/kotlin/cli/common/ExitCode;", "createMetadataVersion", "Lorg/jetbrains/kotlin/metadata/deserialization/BinaryVersion;", "versionArray", Argument.Delimiters.none, "addPlatformOptions", Argument.Delimiters.none, Argument.Delimiters.none, Argument.Delimiters.none, "(Ljava/util/List;Lorg/jetbrains/kotlin/cli/common/arguments/CommonJsAndWasmCompilerArguments;)V", "org.jetbrains.kotlin:cli-js"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class KotlinWebCompilerBase<T extends CommonJsAndWasmCompilerArguments> extends CLICompiler<T> {
    public void addPlatformOptions(List<String> list, T t) {
        list.getClass();
        t.getClass();
    }

    @Override // org.jetbrains.kotlin.cli.common.CLICompiler
    public BinaryVersion createMetadataVersion(int[] versionArray) {
        versionArray.getClass();
        return new MetadataVersion(Arrays.copyOf(versionArray, versionArray.length));
    }

    @Override // org.jetbrains.kotlin.cli.common.CLICompiler
    public ExitCode doExecute(T arguments, CompilerConfiguration configuration, Disposable rootDisposable, KotlinPaths paths) {
        arguments.getClass();
        configuration.getClass();
        rootDisposable.getClass();
        throw new IllegalStateException("K1 compiler entry point is no longer supported.");
    }

    @Override // org.jetbrains.kotlin.cli.common.CLICompiler
    public /* bridge */ /* synthetic */ void addPlatformOptions(List list, CommonCompilerArguments commonCompilerArguments) {
        addPlatformOptions((List<String>) list, (CommonJsAndWasmCompilerArguments) commonCompilerArguments);
    }
}
