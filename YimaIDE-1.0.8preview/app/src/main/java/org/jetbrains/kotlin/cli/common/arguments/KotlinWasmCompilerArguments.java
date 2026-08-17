package org.jetbrains.kotlin.cli.common.arguments;

import com.intellij.util.xmlb.annotations.Transient;
import kotlin.Deprecated;
import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b:\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010L\u001a\u00020MH\u0014R*\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR*\u0010\u000b\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\nR,\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u000e8\u0006@FX\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0010\u0010\u0003\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R&\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u000e8\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0012\"\u0004\b\u0017\u0010\u0014R&\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u000e8\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0012\"\u0004\b\u001a\u0010\u0014R&\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u000e8\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0012\"\u0004\b\u001d\u0010\u0014R&\u0010\u001e\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u000e8\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0012\"\u0004\b \u0010\u0014R&\u0010!\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u000e8\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0012\"\u0004\b#\u0010\u0014R&\u0010$\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u000e8\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0012\"\u0004\b&\u0010\u0014R&\u0010'\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u000e8\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u0012\"\u0004\b)\u0010\u0014R&\u0010*\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u000e8\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\u0012\"\u0004\b,\u0010\u0014R&\u0010-\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u000e8\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010\u0012\"\u0004\b/\u0010\u0014R&\u00100\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010\b\"\u0004\b2\u0010\nR&\u00103\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u000e8\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010\u0012\"\u0004\b5\u0010\u0014R&\u00106\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u000e8\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u0010\u0012\"\u0004\b8\u0010\u0014R&\u00109\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u000e8\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010\u0012\"\u0004\b;\u0010\u0014R*\u0010<\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010\b\"\u0004\b>\u0010\nR,\u0010?\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0004\u001a\u0004\u0018\u00010\u000e8\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0002\u0010D\u001a\u0004\b@\u0010A\"\u0004\bB\u0010CR&\u0010E\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u000e8\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bF\u0010\u0012\"\u0004\bG\u0010\u0014R\u0016\u0010H\u001a\u00020I8\u0017X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bJ\u0010K¨\u0006N"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/arguments/KotlinWasmCompilerArguments;", "Lorg/jetbrains/kotlin/cli/common/arguments/CommonJsAndWasmCompilerArguments;", "<init>", "()V", "value", Argument.Delimiters.none, "irDceDumpReachabilityInfoToFile", "getIrDceDumpReachabilityInfoToFile", "()Ljava/lang/String;", "setIrDceDumpReachabilityInfoToFile", "(Ljava/lang/String;)V", "irDceDumpDeclarationIrSizesToFile", "getIrDceDumpDeclarationIrSizesToFile", "setIrDceDumpDeclarationIrSizesToFile", Argument.Delimiters.none, "wasm", "getWasm$annotations", "getWasm", "()Z", "setWasm", "(Z)V", "forceDebugFriendlyCompilation", "getForceDebugFriendlyCompilation", "setForceDebugFriendlyCompilation", "wasmDebug", "getWasmDebug", "setWasmDebug", "debuggerCustomFormatters", "getDebuggerCustomFormatters", "setDebuggerCustomFormatters", "wasmEnableArrayRangeChecks", "getWasmEnableArrayRangeChecks", "setWasmEnableArrayRangeChecks", "wasmEnableAsserts", "getWasmEnableAsserts", "setWasmEnableAsserts", "wasmGenerateClosedWorldMultimodule", "getWasmGenerateClosedWorldMultimodule", "setWasmGenerateClosedWorldMultimodule", "generateDwarf", "getGenerateDwarf", "setGenerateDwarf", "wasmGenerateWat", "getWasmGenerateWat", "setWasmGenerateWat", "wasmIncludedModuleOnly", "getWasmIncludedModuleOnly", "setWasmIncludedModuleOnly", "wasmInternalLocalVariablePrefix", "getWasmInternalLocalVariablePrefix", "setWasmInternalLocalVariablePrefix", "wasmKClassFqn", "getWasmKClassFqn", "setWasmKClassFqn", "wasmNoJsTag", "getWasmNoJsTag", "setWasmNoJsTag", "includeUnavailableSourcesIntoSourceMap", "getIncludeUnavailableSourcesIntoSourceMap", "setIncludeUnavailableSourcesIntoSourceMap", "wasmTarget", "getWasmTarget", "setWasmTarget", "wasmUseNewExceptionProposal", "getWasmUseNewExceptionProposal", "()Ljava/lang/Boolean;", "setWasmUseNewExceptionProposal", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "wasmUseTrapsInsteadOfExceptions", "getWasmUseTrapsInsteadOfExceptions", "setWasmUseTrapsInsteadOfExceptions", "configurator", "Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArgumentsConfigurator;", "getConfigurator", "()Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArgumentsConfigurator;", "copyOf", "Lorg/jetbrains/kotlin/cli/common/arguments/Freezable;", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class KotlinWasmCompilerArguments extends CommonJsAndWasmCompilerArguments {
    private final transient CommonCompilerArgumentsConfigurator configurator;

    @Argument(description = "Generates devtools custom formatters (https://firefox-source-docs.mozilla.org/devtools-user/custom_formatters) for Kotlin/Wasm values", value = "-Xwasm-debugger-custom-formatters")
    private boolean debuggerCustomFormatters;

    @Argument(description = "Avoid optimizations that can break debugging.", value = "-Xwasm-debug-friendly")
    private boolean forceDebugFriendlyCompilation;

    @Argument(description = "Generate DWARF debug information.", value = "-Xwasm-generate-dwarf")
    private boolean generateDwarf;

    @Argument(description = "Insert source mappings from libraries even if their sources are unavailable on the end-user machine.", value = "-Xwasm-source-map-include-mappings-from-unavailable-sources")
    private boolean includeUnavailableSourcesIntoSourceMap;

    @Argument(description = "Dump the IR size of each declaration into a file. The format will be chosen automatically depending on the file extension. Supported output formats include JSON for .json, a JS const initialized with a plain object containing information for .js, and plain text for all other file types.", value = "-Xir-dump-declaration-ir-sizes-to-file", valueDescription = "<path>")
    private String irDceDumpDeclarationIrSizesToFile;

    @Argument(description = "Dump reachability information collected about declarations while performing DCE to a file. The format will be chosen automatically based on the file extension. Supported output formats include JSON for .json, a JS const initialized with a plain object containing information for .js, and plain text for all other file types.", value = "-Xir-dce-dump-reachability-info-to-file", valueDescription = "<path>")
    private String irDceDumpReachabilityInfoToFile;

    @Argument(description = "Use the WebAssembly compiler backend.", value = "-Xwasm")
    private boolean wasm;

    @Argument(description = "Add debug info to the compiled WebAssembly module.", value = "-Xwasm-debug-info")
    private boolean wasmDebug;

    @Argument(description = "Turn on range checks for array access functions.", value = "-Xwasm-enable-array-range-checks")
    private boolean wasmEnableArrayRangeChecks;

    @Argument(description = "Turn on asserts.", value = "-Xwasm-enable-asserts")
    private boolean wasmEnableAsserts;

    @Argument(description = "Compile modules in multi-module closed-world mode using module passed in `-include` argument as main module", value = "-Xwasm-generate-closed-world-multimodule")
    private boolean wasmGenerateClosedWorldMultimodule;

    @Argument(description = "Generate a .wat file.", value = "-Xwasm-generate-wat")
    private boolean wasmGenerateWat;

    @Argument(description = "Compile only a module passed using `-include` option.", value = "-Xwasm-included-module-only")
    private boolean wasmIncludedModuleOnly;

    @Argument(description = "Prefix to use for internally generated local variables.", value = "-Xwasm-internal-local-variable-prefix")
    private String wasmInternalLocalVariablePrefix;

    @Argument(description = "Enable support for 'KClass.qualifiedName'.", value = "-Xwasm-kclass-fqn")
    private boolean wasmKClassFqn;

    @Argument(description = "Don't use WebAssembly.JSTag for throwing and catching exceptions", value = "-Xwasm-no-jstag")
    private boolean wasmNoJsTag;

    @Argument(description = "Set up the Wasm target (wasm-js or wasm-wasi).", value = "-Xwasm-target")
    private String wasmTarget;

    @Argument(description = "Use an updated version of the exception proposal with try_table.", value = "-Xwasm-use-new-exception-proposal")
    private Boolean wasmUseNewExceptionProposal;

    @Argument(description = "Use traps instead of throwing exceptions.", value = "-Xwasm-use-traps-instead-of-exceptions")
    private boolean wasmUseTrapsInsteadOfExceptions;

    public KotlinWasmCompilerArguments() {
        super(null);
        this.wasmDebug = true;
        this.wasmInternalLocalVariablePrefix = "~";
        this.wasmKClassFqn = true;
        this.configurator = new KotlinWasmCompilerArgumentsConfigurator();
    }

    @Deprecated(message = "This flag is deprecated. Use kotlinc-wasm or the KotlinWasmCompiler class instead to compile to WebAssembly.")
    public static /* synthetic */ void getWasm$annotations() {
    }

    @Override // org.jetbrains.kotlin.cli.common.arguments.Freezable
    public Freezable copyOf() {
        return KotlinWasmCompilerArgumentsCopyGeneratedKt.copyKotlinWasmCompilerArguments(this, new KotlinWasmCompilerArguments());
    }

    @Override // org.jetbrains.kotlin.cli.common.arguments.CommonCompilerArguments
    @Transient
    public CommonCompilerArgumentsConfigurator getConfigurator() {
        return this.configurator;
    }

    public final boolean getDebuggerCustomFormatters() {
        return this.debuggerCustomFormatters;
    }

    public final boolean getForceDebugFriendlyCompilation() {
        return this.forceDebugFriendlyCompilation;
    }

    public final boolean getGenerateDwarf() {
        return this.generateDwarf;
    }

    public final boolean getIncludeUnavailableSourcesIntoSourceMap() {
        return this.includeUnavailableSourcesIntoSourceMap;
    }

    public final String getIrDceDumpDeclarationIrSizesToFile() {
        return this.irDceDumpDeclarationIrSizesToFile;
    }

    public final String getIrDceDumpReachabilityInfoToFile() {
        return this.irDceDumpReachabilityInfoToFile;
    }

    public final boolean getWasm() {
        return this.wasm;
    }

    public final boolean getWasmDebug() {
        return this.wasmDebug;
    }

    public final boolean getWasmEnableArrayRangeChecks() {
        return this.wasmEnableArrayRangeChecks;
    }

    public final boolean getWasmEnableAsserts() {
        return this.wasmEnableAsserts;
    }

    public final boolean getWasmGenerateClosedWorldMultimodule() {
        return this.wasmGenerateClosedWorldMultimodule;
    }

    public final boolean getWasmGenerateWat() {
        return this.wasmGenerateWat;
    }

    public final boolean getWasmIncludedModuleOnly() {
        return this.wasmIncludedModuleOnly;
    }

    public final String getWasmInternalLocalVariablePrefix() {
        return this.wasmInternalLocalVariablePrefix;
    }

    public final boolean getWasmKClassFqn() {
        return this.wasmKClassFqn;
    }

    public final boolean getWasmNoJsTag() {
        return this.wasmNoJsTag;
    }

    public final String getWasmTarget() {
        return this.wasmTarget;
    }

    public final Boolean getWasmUseNewExceptionProposal() {
        return this.wasmUseNewExceptionProposal;
    }

    public final boolean getWasmUseTrapsInsteadOfExceptions() {
        return this.wasmUseTrapsInsteadOfExceptions;
    }

    public final void setDebuggerCustomFormatters(boolean z) {
        checkFrozen();
        this.debuggerCustomFormatters = z;
    }

    public final void setForceDebugFriendlyCompilation(boolean z) {
        checkFrozen();
        this.forceDebugFriendlyCompilation = z;
    }

    public final void setGenerateDwarf(boolean z) {
        checkFrozen();
        this.generateDwarf = z;
    }

    public final void setIncludeUnavailableSourcesIntoSourceMap(boolean z) {
        checkFrozen();
        this.includeUnavailableSourcesIntoSourceMap = z;
    }

    public final void setIrDceDumpDeclarationIrSizesToFile(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.irDceDumpDeclarationIrSizesToFile = str;
    }

    public final void setIrDceDumpReachabilityInfoToFile(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.irDceDumpReachabilityInfoToFile = str;
    }

    public final void setWasm(boolean z) {
        checkFrozen();
        this.wasm = z;
    }

    public final void setWasmDebug(boolean z) {
        checkFrozen();
        this.wasmDebug = z;
    }

    public final void setWasmEnableArrayRangeChecks(boolean z) {
        checkFrozen();
        this.wasmEnableArrayRangeChecks = z;
    }

    public final void setWasmEnableAsserts(boolean z) {
        checkFrozen();
        this.wasmEnableAsserts = z;
    }

    public final void setWasmGenerateClosedWorldMultimodule(boolean z) {
        checkFrozen();
        this.wasmGenerateClosedWorldMultimodule = z;
    }

    public final void setWasmGenerateWat(boolean z) {
        checkFrozen();
        this.wasmGenerateWat = z;
    }

    public final void setWasmIncludedModuleOnly(boolean z) {
        checkFrozen();
        this.wasmIncludedModuleOnly = z;
    }

    public final void setWasmInternalLocalVariablePrefix(String str) {
        str.getClass();
        checkFrozen();
        this.wasmInternalLocalVariablePrefix = str;
    }

    public final void setWasmKClassFqn(boolean z) {
        checkFrozen();
        this.wasmKClassFqn = z;
    }

    public final void setWasmNoJsTag(boolean z) {
        checkFrozen();
        this.wasmNoJsTag = z;
    }

    public final void setWasmTarget(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.wasmTarget = str;
    }

    public final void setWasmUseNewExceptionProposal(Boolean bool) {
        checkFrozen();
        this.wasmUseNewExceptionProposal = bool;
    }

    public final void setWasmUseTrapsInsteadOfExceptions(boolean z) {
        checkFrozen();
        this.wasmUseTrapsInsteadOfExceptions = z;
    }
}
