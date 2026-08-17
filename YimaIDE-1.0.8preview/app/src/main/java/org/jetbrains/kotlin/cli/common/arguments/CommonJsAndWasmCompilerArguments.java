package org.jetbrains.kotlin.cli.common.arguments;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\bJ\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003R*\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR&\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u000b8\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R*\u0010\u0011\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\b\"\u0004\b\u0013\u0010\nR&\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u000b8\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u000e\"\u0004\b\u0016\u0010\u0010R&\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u000b8\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u000e\"\u0004\b\u0019\u0010\u0010R*\u0010\u001a\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\b\"\u0004\b\u001c\u0010\nR&\u0010\u001d\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u000b8\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u000e\"\u0004\b\u001f\u0010\u0010R&\u0010 \u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u000b8\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u000e\"\u0004\b\"\u0010\u0010R*\u0010#\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\b\"\u0004\b%\u0010\nR*\u0010&\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\b\"\u0004\b(\u0010\nR*\u0010)\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010\b\"\u0004\b+\u0010\nR&\u0010,\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u000b8\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010\u000e\"\u0004\b.\u0010\u0010R&\u0010/\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u000b8\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u0010\u000e\"\u0004\b1\u0010\u0010R&\u00102\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u000b8\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010\u000e\"\u0004\b4\u0010\u0010R&\u00105\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u000b8\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u0010\u000e\"\u0004\b7\u0010\u0010R&\u00108\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u000b8\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010\u000e\"\u0004\b:\u0010\u0010R*\u0010;\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010\b\"\u0004\b=\u0010\nR*\u0010>\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b?\u0010\b\"\u0004\b@\u0010\nR*\u0010A\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bB\u0010\b\"\u0004\bC\u0010\nR*\u0010D\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bE\u0010\b\"\u0004\bF\u0010\nR&\u0010G\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u000b8\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bH\u0010\u000e\"\u0004\bI\u0010\u0010R*\u0010J\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bK\u0010\b\"\u0004\bL\u0010\nR*\u0010M\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bN\u0010\b\"\u0004\bO\u0010\nR*\u0010P\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bQ\u0010\b\"\u0004\bR\u0010\nR*\u0010S\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bT\u0010\b\"\u0004\bU\u0010\n\u0082\u0001\u0002VW¨\u0006X"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/arguments/CommonJsAndWasmCompilerArguments;", "Lorg/jetbrains/kotlin/cli/common/arguments/CommonKlibBasedCompilerArguments;", "<init>", "()V", "value", Argument.Delimiters.none, "cacheDirectory", "getCacheDirectory", "()Ljava/lang/String;", "setCacheDirectory", "(Ljava/lang/String;)V", Argument.Delimiters.none, "fakeOverrideValidator", "getFakeOverrideValidator", "()Z", "setFakeOverrideValidator", "(Z)V", "friendModules", "getFriendModules", "setFriendModules", "friendModulesDisabled", "getFriendModulesDisabled", "setFriendModulesDisabled", "generateDts", "getGenerateDts", "setGenerateDts", "includes", "getIncludes", "setIncludes", "irDce", "getIrDce", "setIrDce", "irDcePrintReachabilityInfo", "getIrDcePrintReachabilityInfo", "setIrDcePrintReachabilityInfo", "irDceRuntimeDiagnostic", "getIrDceRuntimeDiagnostic", "setIrDceRuntimeDiagnostic", "irModuleName", "getIrModuleName", "setIrModuleName", "irPerModuleOutputName", "getIrPerModuleOutputName", "setIrPerModuleOutputName", "irProduceJs", "getIrProduceJs", "setIrProduceJs", "irProduceKlibDir", "getIrProduceKlibDir", "setIrProduceKlibDir", "irProduceKlibFile", "getIrProduceKlibFile", "setIrProduceKlibFile", "irPropertyLazyInitialization", "getIrPropertyLazyInitialization", "setIrPropertyLazyInitialization", "strictImplicitExportType", "getStrictImplicitExportType", "setStrictImplicitExportType", ModuleXmlParser.OUTPUT_DIR, "getOutputDir", "setOutputDir", "moduleName", "getModuleName", "setModuleName", "libraries", "getLibraries", "setLibraries", "main", "getMain", "setMain", "sourceMap", "getSourceMap", "setSourceMap", "sourceMapBaseDirs", "getSourceMapBaseDirs", "setSourceMapBaseDirs", "sourceMapEmbedSources", "getSourceMapEmbedSources", "setSourceMapEmbedSources", "sourceMapNamesPolicy", "getSourceMapNamesPolicy", "setSourceMapNamesPolicy", "sourceMapPrefix", "getSourceMapPrefix", "setSourceMapPrefix", "Lorg/jetbrains/kotlin/cli/common/arguments/K2WasmCompilerArguments;", "Lorg/jetbrains/kotlin/cli/common/arguments/KotlinWasmCompilerArguments;", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class CommonJsAndWasmCompilerArguments extends CommonKlibBasedCompilerArguments {

    @Argument(description = "Path to the cache directory.", value = "-Xcache-directory", valueDescription = "<path>")
    private String cacheDirectory;

    @Argument(description = "Enable the IR fake override validator.", value = "-Xfake-override-validator")
    private boolean fakeOverrideValidator;

    @Argument(description = "Paths to friend modules.", value = "-Xfriend-modules", valueDescription = "<path>")
    private String friendModules;

    @Argument(description = "Disable internal declaration export.", value = "-Xfriend-modules-disabled")
    private boolean friendModulesDisabled;

    @Argument(description = "Generate a TypeScript declaration .d.ts file alongside the JS file.", value = "-Xgenerate-dts")
    private boolean generateDts;

    @Argument(description = "Path to an intermediate library that should be processed in the same manner as source files.", value = "-Xinclude", valueDescription = "<path>")
    private String includes;

    @Argument(description = "Perform experimental dead code elimination.", value = "-Xir-dce")
    private boolean irDce;

    @Argument(description = "Print reachability information about declarations to 'stdout' while performing DCE.", value = "-Xir-dce-print-reachability-info")
    private boolean irDcePrintReachabilityInfo;

    @Argument(description = "Enable runtime diagnostics instead of removing declarations when performing DCE.", value = "-Xir-dce-runtime-diagnostic", valueDescription = "{log|exception}")
    private String irDceRuntimeDiagnostic;

    @Argument(description = "Specify the name of the compilation module for the IR backend.", value = "-Xir-module-name", valueDescription = "<name>")
    private String irModuleName;

    @Argument(description = "Add a custom output name to the split .js files.", value = "-Xir-per-module-output-name")
    private String irPerModuleOutputName;

    @Argument(description = "Generate a JS file using the IR backend.", value = "-Xir-produce-js")
    private boolean irProduceJs;

    @Argument(description = "Generate an unpacked klib into the parent directory of the output JS file.", value = "-Xir-produce-klib-dir")
    private boolean irProduceKlibDir;

    @Argument(description = "Generate a packed klib into the directory specified by '-ir-output-dir'.", value = "-Xir-produce-klib-file")
    private boolean irProduceKlibFile;

    @Argument(description = "Perform lazy initialization for properties.", value = "-Xir-property-lazy-initialization")
    private boolean irPropertyLazyInitialization;

    @Argument(description = "Paths to Kotlin libraries with .meta.js and .kjsm files, separated by the system path separator.", value = "-libraries", valueDescription = "<path>")
    private String libraries;

    @Argument(description = "Specify whether the 'main' function should be called upon execution.", value = "-main", valueDescription = "{call|noCall}")
    private String main;

    @Argument(description = "Base name of generated files.", value = "-ir-output-name")
    private String moduleName;

    @Argument(description = "Destination for generated files.", value = "-ir-output-dir", valueDescription = "<directory>")
    private String outputDir;

    @Argument(description = "Generate a source map.", value = "-source-map")
    private boolean sourceMap;

    @Argument(deprecatedName = "-source-map-source-roots", description = "Base directories for calculating relative paths to source files in the source map.", value = "-source-map-base-dirs", valueDescription = "<path>")
    private String sourceMapBaseDirs;

    @Argument(description = "Embed source files into the source map.", value = "-source-map-embed-sources", valueDescription = "{always|never|inlining}")
    private String sourceMapEmbedSources;

    @Argument(description = "Mode for mapping generated names to original names.", value = "-source-map-names-policy", valueDescription = "{no|simple-names|fully-qualified-names}")
    private String sourceMapNamesPolicy;

    @Argument(description = "Add the specified prefix to the paths in the source map.", value = "-source-map-prefix")
    private String sourceMapPrefix;

    @Argument(description = "Generate strict types for implicitly exported entities inside d.ts files.", value = "-Xstrict-implicit-export-types")
    private boolean strictImplicitExportType;

    private CommonJsAndWasmCompilerArguments() {
        this.irPropertyLazyInitialization = true;
    }

    public final String getCacheDirectory() {
        return this.cacheDirectory;
    }

    public final boolean getFakeOverrideValidator() {
        return this.fakeOverrideValidator;
    }

    public final String getFriendModules() {
        return this.friendModules;
    }

    public final boolean getFriendModulesDisabled() {
        return this.friendModulesDisabled;
    }

    public final boolean getGenerateDts() {
        return this.generateDts;
    }

    public final String getIncludes() {
        return this.includes;
    }

    public final boolean getIrDce() {
        return this.irDce;
    }

    public final boolean getIrDcePrintReachabilityInfo() {
        return this.irDcePrintReachabilityInfo;
    }

    public final String getIrDceRuntimeDiagnostic() {
        return this.irDceRuntimeDiagnostic;
    }

    public final String getIrModuleName() {
        return this.irModuleName;
    }

    public final String getIrPerModuleOutputName() {
        return this.irPerModuleOutputName;
    }

    public final boolean getIrProduceJs() {
        return this.irProduceJs;
    }

    public final boolean getIrProduceKlibDir() {
        return this.irProduceKlibDir;
    }

    public final boolean getIrProduceKlibFile() {
        return this.irProduceKlibFile;
    }

    public final boolean getIrPropertyLazyInitialization() {
        return this.irPropertyLazyInitialization;
    }

    public final String getLibraries() {
        return this.libraries;
    }

    public final String getMain() {
        return this.main;
    }

    public final String getModuleName() {
        return this.moduleName;
    }

    public final String getOutputDir() {
        return this.outputDir;
    }

    public final boolean getSourceMap() {
        return this.sourceMap;
    }

    public final String getSourceMapBaseDirs() {
        return this.sourceMapBaseDirs;
    }

    public final String getSourceMapEmbedSources() {
        return this.sourceMapEmbedSources;
    }

    public final String getSourceMapNamesPolicy() {
        return this.sourceMapNamesPolicy;
    }

    public final String getSourceMapPrefix() {
        return this.sourceMapPrefix;
    }

    public final boolean getStrictImplicitExportType() {
        return this.strictImplicitExportType;
    }

    public final void setCacheDirectory(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.cacheDirectory = str;
    }

    public final void setFakeOverrideValidator(boolean z) {
        checkFrozen();
        this.fakeOverrideValidator = z;
    }

    public final void setFriendModules(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.friendModules = str;
    }

    public final void setFriendModulesDisabled(boolean z) {
        checkFrozen();
        this.friendModulesDisabled = z;
    }

    public final void setGenerateDts(boolean z) {
        checkFrozen();
        this.generateDts = z;
    }

    public final void setIncludes(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.includes = str;
    }

    public final void setIrDce(boolean z) {
        checkFrozen();
        this.irDce = z;
    }

    public final void setIrDcePrintReachabilityInfo(boolean z) {
        checkFrozen();
        this.irDcePrintReachabilityInfo = z;
    }

    public final void setIrDceRuntimeDiagnostic(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.irDceRuntimeDiagnostic = str;
    }

    public final void setIrModuleName(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.irModuleName = str;
    }

    public final void setIrPerModuleOutputName(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.irPerModuleOutputName = str;
    }

    public final void setIrProduceJs(boolean z) {
        checkFrozen();
        this.irProduceJs = z;
    }

    public final void setIrProduceKlibDir(boolean z) {
        checkFrozen();
        this.irProduceKlibDir = z;
    }

    public final void setIrProduceKlibFile(boolean z) {
        checkFrozen();
        this.irProduceKlibFile = z;
    }

    public final void setIrPropertyLazyInitialization(boolean z) {
        checkFrozen();
        this.irPropertyLazyInitialization = z;
    }

    public final void setLibraries(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.libraries = str;
    }

    public final void setMain(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.main = str;
    }

    public final void setModuleName(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.moduleName = str;
    }

    public final void setOutputDir(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.outputDir = str;
    }

    public final void setSourceMap(boolean z) {
        checkFrozen();
        this.sourceMap = z;
    }

    public final void setSourceMapBaseDirs(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.sourceMapBaseDirs = str;
    }

    public final void setSourceMapEmbedSources(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.sourceMapEmbedSources = str;
    }

    public final void setSourceMapNamesPolicy(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.sourceMapNamesPolicy = str;
    }

    public final void setSourceMapPrefix(String str) {
        checkFrozen();
        if (str == null || str.length() == 0) {
            str = null;
        }
        this.sourceMapPrefix = str;
    }

    public final void setStrictImplicitExportType(boolean z) {
        checkFrozen();
        this.strictImplicitExportType = z;
    }

    public /* synthetic */ CommonJsAndWasmCompilerArguments(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
