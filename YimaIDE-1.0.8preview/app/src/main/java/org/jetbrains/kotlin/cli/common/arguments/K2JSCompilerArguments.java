package org.jetbrains.kotlin.cli.common.arguments;

import com.intellij.util.xmlb.annotations.Transient;
import kotlin.Deprecated;
import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.inline.InlineCodegenUtilsKt;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b#\n\u0002\u0010\u000e\n\u0002\b%\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010R\u001a\u00020SH\u0014R&\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR&\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\nR&\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\b\"\u0004\b\u0010\u0010\nR,\u0010\u0011\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u0016\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R,\u0010\u0017\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u0016\u001a\u0004\b\u0018\u0010\u0013\"\u0004\b\u0019\u0010\u0015R,\u0010\u001a\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u0016\u001a\u0004\b\u001b\u0010\u0013\"\u0004\b\u001c\u0010\u0015R,\u0010\u001d\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u0016\u001a\u0004\b\u001e\u0010\u0013\"\u0004\b\u001f\u0010\u0015R&\u0010 \u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\b\"\u0004\b\"\u0010\nR&\u0010#\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\b\"\u0004\b%\u0010\nR&\u0010&\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\b\"\u0004\b(\u0010\nR*\u0010*\u001a\u0004\u0018\u00010)2\b\u0010\u0004\u001a\u0004\u0018\u00010)8\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R&\u0010/\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u0010\b\"\u0004\b1\u0010\nR&\u00102\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010\b\"\u0004\b4\u0010\nR&\u00105\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u0010\b\"\u0004\b7\u0010\nR&\u00108\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010\b\"\u0004\b:\u0010\nR*\u0010;\u001a\u0004\u0018\u00010)2\b\u0010\u0004\u001a\u0004\u0018\u00010)8\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010,\"\u0004\b=\u0010.R&\u0010>\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b?\u0010\b\"\u0004\b@\u0010\nR*\u0010A\u001a\u0004\u0018\u00010)2\b\u0010\u0004\u001a\u0004\u0018\u00010)8\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bB\u0010,\"\u0004\bC\u0010.R*\u0010D\u001a\u0004\u0018\u00010)2\b\u0010\u0004\u001a\u0004\u0018\u00010)8\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bE\u0010,\"\u0004\bF\u0010.R0\u0010G\u001a\u0004\u0018\u00010)2\b\u0010\u0004\u001a\u0004\u0018\u00010)8\u0006@FX\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\bH\u0010\u0003\u001a\u0004\bI\u0010,\"\u0004\bJ\u0010.R*\u0010K\u001a\u0004\u0018\u00010)2\b\u0010\u0004\u001a\u0004\u0018\u00010)8\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bL\u0010,\"\u0004\bM\u0010.R\u0016\u0010N\u001a\u00020O8\u0017X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bP\u0010Q¨\u0006T"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/arguments/K2JSCompilerArguments;", "Lorg/jetbrains/kotlin/cli/common/arguments/K2WasmCompilerArguments;", "<init>", "()V", "value", Argument.Delimiters.none, "extensionFunctionsInExternals", "getExtensionFunctionsInExternals", "()Z", "setExtensionFunctionsInExternals", "(Z)V", "allowImplementableInterfacesExporting", "getAllowImplementableInterfacesExporting", "setAllowImplementableInterfacesExporting", "allowExportingSuspendFunctions", "getAllowExportingSuspendFunctions", "setAllowExportingSuspendFunctions", "useEsArrowFunctions", "getUseEsArrowFunctions", "()Ljava/lang/Boolean;", "setUseEsArrowFunctions", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "useEsClasses", "getUseEsClasses", "setUseEsClasses", "useEsGenerators", "getUseEsGenerators", "setUseEsGenerators", "compileLongAsBigInt", "getCompileLongAsBigInt", "setCompileLongAsBigInt", "generatePolyfills", "getGeneratePolyfills", "setGeneratePolyfills", "irBuildCache", "getIrBuildCache", "setIrBuildCache", "irGenerateInlineAnonymousFunctions", "getIrGenerateInlineAnonymousFunctions", "setIrGenerateInlineAnonymousFunctions", Argument.Delimiters.none, "irKeep", "getIrKeep", "()Ljava/lang/String;", "setIrKeep", "(Ljava/lang/String;)V", "irMinimizedMemberNames", "getIrMinimizedMemberNames", "setIrMinimizedMemberNames", "irPerFile", "getIrPerFile", "setIrPerFile", "irPerModule", "getIrPerModule", "setIrPerModule", "irSafeExternalBoolean", "getIrSafeExternalBoolean", "setIrSafeExternalBoolean", "irSafeExternalBooleanDiagnostic", "getIrSafeExternalBooleanDiagnostic", "setIrSafeExternalBooleanDiagnostic", "optimizeGeneratedJs", "getOptimizeGeneratedJs", "setOptimizeGeneratedJs", "platformArgumentsProviderJsExpression", "getPlatformArgumentsProviderJsExpression", "setPlatformArgumentsProviderJsExpression", "moduleKind", "getModuleKind", "setModuleKind", "outputFile", "getOutputFile$annotations", "getOutputFile", "setOutputFile", "target", "getTarget", "setTarget", "configurator", "Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArgumentsConfigurator;", "getConfigurator", "()Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArgumentsConfigurator;", "copyOf", "Lorg/jetbrains/kotlin/cli/common/arguments/Freezable;", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class K2JSCompilerArguments extends K2WasmCompilerArguments {

    @Argument(description = "Enable exporting suspend functions to JavaScript/TypeScript.", value = "-Xenable-suspend-function-exporting")
    @Enables(feature = LanguageFeature.JsAllowExportingSuspendFunctions)
    private boolean allowExportingSuspendFunctions;

    @Argument(description = "Enable exporting of Kotlin interfaces to implement them from JavaScript/TypeScript.", value = "-Xenable-implementing-interfaces-from-typescript")
    @Enables(feature = LanguageFeature.JsExportInterfacesInImplementableWay)
    private boolean allowImplementableInterfacesExporting;

    @Argument(description = "Compile Long values as ES2020 bigint instead of object.", value = "-Xes-long-as-bigint")
    private Boolean compileLongAsBigInt;
    private final transient CommonCompilerArgumentsConfigurator configurator;

    @Argument(description = "Enable extension function members in external interfaces.", value = "-Xenable-extension-functions-in-externals")
    @Enables(feature = LanguageFeature.JsEnableExtensionFunctionInExternals)
    private boolean extensionFunctionsInExternals;

    @Argument(description = "Generate polyfills for features from the ES6+ standards.", value = "-Xgenerate-polyfills")
    private boolean generatePolyfills;

    @Argument(description = "Use the compiler to build the cache.", value = "-Xir-build-cache")
    private boolean irBuildCache;

    @Argument(description = "Lambda expressions that capture values are translated into in-line anonymous JavaScript functions.", value = "-Xir-generate-inline-anonymous-functions")
    private boolean irGenerateInlineAnonymousFunctions;

    @Argument(description = "Comma-separated list of fully qualified names not to be eliminated by DCE (if it can be reached), and for which to keep non-minified names.", value = "-Xir-keep")
    private String irKeep;

    @Argument(description = "Minimize the names of members.", value = "-Xir-minimized-member-names")
    private boolean irMinimizedMemberNames;

    @Argument(description = "Generate one .js file per source file.", value = "-Xir-per-file")
    private boolean irPerFile;

    @Argument(description = "Generate one .js file per module.", value = "-Xir-per-module")
    private boolean irPerModule;

    @Argument(description = "Wrap access to external 'Boolean' properties with an explicit conversion to 'Boolean'.", value = "-Xir-safe-external-boolean")
    private boolean irSafeExternalBoolean;

    @Argument(description = "Enable runtime diagnostics when accessing external 'Boolean' properties.", value = "-Xir-safe-external-boolean-diagnostic", valueDescription = "{log|exception}")
    private String irSafeExternalBooleanDiagnostic;

    @Argument(description = "The kind of JS module generated by the compiler. ES modules are enabled by default in case of ES2015 target usage", value = "-module-kind", valueDescription = "{plain|amd|commonjs|umd|es}")
    private String moduleKind;

    @Argument(description = "Perform additional optimizations on the generated JS code.", value = "-Xoptimize-generated-js")
    private boolean optimizeGeneratedJs;

    @Argument(description = Argument.Delimiters.none, isObsolete = InlineCodegenUtilsKt.GENERATE_SMAP, value = "-output", valueDescription = "<filepath>")
    private String outputFile;

    @Argument(description = "JS expression that will be executed in runtime and be put as an Array<String> parameter of the main function", value = "-Xplatform-arguments-in-main-function")
    private String platformArgumentsProviderJsExpression;

    @Argument(description = "Generate JS files for the specified ECMA version.", value = "-target", valueDescription = "{ es5, es2015 }")
    private String target;

    @Argument(description = "Use ES2015 arrow functions in the JavaScript code generated for Kotlin lambdas. Enabled by default in case of ES2015 target usage", value = "-Xes-arrow-functions")
    private Boolean useEsArrowFunctions;

    @Argument(description = "Let generated JavaScript code use ES2015 classes. Enabled by default in case of ES2015 target usage", value = "-Xes-classes")
    private Boolean useEsClasses;

    @Argument(description = "Enable ES2015 generator functions usage inside the compiled code. Enabled by default in case of ES2015 target usage", value = "-Xes-generators")
    private Boolean useEsGenerators;

    public K2JSCompilerArguments() {
        super(null);
        this.generatePolyfills = true;
        this.optimizeGeneratedJs = true;
        this.configurator = new K2JSCompilerArgumentsConfigurator();
    }

    @Deprecated(message = "It is senseless to use with IR compiler. Only for compatibility.")
    public static /* synthetic */ void getOutputFile$annotations() {
    }

    @Override // org.jetbrains.kotlin.cli.common.arguments.Freezable
    public Freezable copyOf() {
        return K2JSCompilerArgumentsCopyGeneratedKt.copyK2JSCompilerArguments(this, new K2JSCompilerArguments());
    }

    public final boolean getAllowExportingSuspendFunctions() {
        return this.allowExportingSuspendFunctions;
    }

    public final boolean getAllowImplementableInterfacesExporting() {
        return this.allowImplementableInterfacesExporting;
    }

    public final Boolean getCompileLongAsBigInt() {
        return this.compileLongAsBigInt;
    }

    @Override // org.jetbrains.kotlin.cli.common.arguments.CommonCompilerArguments
    @Transient
    public CommonCompilerArgumentsConfigurator getConfigurator() {
        return this.configurator;
    }

    public final boolean getExtensionFunctionsInExternals() {
        return this.extensionFunctionsInExternals;
    }

    public final boolean getGeneratePolyfills() {
        return this.generatePolyfills;
    }

    public final boolean getIrBuildCache() {
        return this.irBuildCache;
    }

    public final boolean getIrGenerateInlineAnonymousFunctions() {
        return this.irGenerateInlineAnonymousFunctions;
    }

    public final String getIrKeep() {
        return this.irKeep;
    }

    public final boolean getIrMinimizedMemberNames() {
        return this.irMinimizedMemberNames;
    }

    public final boolean getIrPerFile() {
        return this.irPerFile;
    }

    public final boolean getIrPerModule() {
        return this.irPerModule;
    }

    public final boolean getIrSafeExternalBoolean() {
        return this.irSafeExternalBoolean;
    }

    public final String getIrSafeExternalBooleanDiagnostic() {
        return this.irSafeExternalBooleanDiagnostic;
    }

    public final String getModuleKind() {
        return this.moduleKind;
    }

    public final boolean getOptimizeGeneratedJs() {
        return this.optimizeGeneratedJs;
    }

    public final String getOutputFile() {
        return this.outputFile;
    }

    public final String getPlatformArgumentsProviderJsExpression() {
        return this.platformArgumentsProviderJsExpression;
    }

    public final String getTarget() {
        return this.target;
    }

    public final Boolean getUseEsArrowFunctions() {
        return this.useEsArrowFunctions;
    }

    public final Boolean getUseEsClasses() {
        return this.useEsClasses;
    }

    public final Boolean getUseEsGenerators() {
        return this.useEsGenerators;
    }

    public final void setAllowExportingSuspendFunctions(boolean z) {
        checkFrozen();
        this.allowExportingSuspendFunctions = z;
    }

    public final void setAllowImplementableInterfacesExporting(boolean z) {
        checkFrozen();
        this.allowImplementableInterfacesExporting = z;
    }

    public final void setCompileLongAsBigInt(Boolean bool) {
        checkFrozen();
        this.compileLongAsBigInt = bool;
    }

    public final void setExtensionFunctionsInExternals(boolean z) {
        checkFrozen();
        this.extensionFunctionsInExternals = z;
    }

    public final void setGeneratePolyfills(boolean z) {
        checkFrozen();
        this.generatePolyfills = z;
    }

    public final void setIrBuildCache(boolean z) {
        checkFrozen();
        this.irBuildCache = z;
    }

    public final void setIrGenerateInlineAnonymousFunctions(boolean z) {
        checkFrozen();
        this.irGenerateInlineAnonymousFunctions = z;
    }

    public final void setIrKeep(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.irKeep = str;
    }

    public final void setIrMinimizedMemberNames(boolean z) {
        checkFrozen();
        this.irMinimizedMemberNames = z;
    }

    public final void setIrPerFile(boolean z) {
        checkFrozen();
        this.irPerFile = z;
    }

    public final void setIrPerModule(boolean z) {
        checkFrozen();
        this.irPerModule = z;
    }

    public final void setIrSafeExternalBoolean(boolean z) {
        checkFrozen();
        this.irSafeExternalBoolean = z;
    }

    public final void setIrSafeExternalBooleanDiagnostic(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.irSafeExternalBooleanDiagnostic = str;
    }

    public final void setModuleKind(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.moduleKind = str;
    }

    public final void setOptimizeGeneratedJs(boolean z) {
        checkFrozen();
        this.optimizeGeneratedJs = z;
    }

    public final void setOutputFile(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.outputFile = str;
    }

    public final void setPlatformArgumentsProviderJsExpression(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.platformArgumentsProviderJsExpression = str;
    }

    public final void setTarget(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.target = str;
    }

    public final void setUseEsArrowFunctions(Boolean bool) {
        checkFrozen();
        this.useEsArrowFunctions = bool;
    }

    public final void setUseEsClasses(Boolean bool) {
        checkFrozen();
        this.useEsClasses = bool;
    }

    public final void setUseEsGenerators(Boolean bool) {
        checkFrozen();
        this.useEsGenerators = bool;
    }
}
