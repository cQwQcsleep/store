package org.jetbrains.kotlin.cli.js;

import com.intellij.util.ExceptionUtil;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import org.jetbrains.kotlin.cli.CliDiagnosticReportingKt;
import org.jetbrains.kotlin.cli.CliDiagnostics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.arguments.CommonJsAndWasmCompilerArguments;
import org.jetbrains.kotlin.cli.common.arguments.K2JSCompilerArguments;
import org.jetbrains.kotlin.cli.common.arguments.K2JsArgumentConstants;
import org.jetbrains.kotlin.config.CommonConfigurationKeysKt;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.js.config.EcmaVersion;
import org.jetbrains.kotlin.js.config.JSConfigurationKeysKt;
import org.jetbrains.kotlin.js.config.JsGenerationGranularity;
import org.jetbrains.kotlin.js.config.ModuleKind;
import org.jetbrains.kotlin.js.config.SourceMapNamesPolicy;
import org.jetbrains.kotlin.js.config.SourceMapSourceEmbedding;
import org.jetbrains.kotlin.js.config.TsCompilationStrategy;
import org.jetbrains.kotlin.js.config.WebArtifactConfiguration;
import org.jetbrains.kotlin.library.loader.KlibPlatformChecker;
import org.jetbrains.kotlin.wasm.config.WasmConfigurationKeysKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000`\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\u001a\u0018\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00100\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0010H\u0000\u001a\u0012\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00100\u001b*\u00020\u0010H\u0002\u001a\u0018\u0010\u001e\u001a\u00020\u00102\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u0007H\u0000\u001a\u0018\u0010&\u001a\u00020'2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u0007H\u0000\"\u0017\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0015\u0010\u0005\u001a\u00020\u0006*\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\b\u0010\t\"\u0015\u0010\n\u001a\u00020\u000b*\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\f\u0010\r\" \u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000fX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013\" \u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00150\u000fX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0013\" \u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00180\u000fX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0013\"\u0018\u0010\"\u001a\u00020#*\u00020 8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b$\u0010%¨\u0006("}, d2 = {"targetVersion", "Lorg/jetbrains/kotlin/js/config/EcmaVersion;", "Lorg/jetbrains/kotlin/cli/common/arguments/K2JSCompilerArguments;", "getTargetVersion", "(Lorg/jetbrains/kotlin/cli/common/arguments/K2JSCompilerArguments;)Lorg/jetbrains/kotlin/js/config/EcmaVersion;", "granularity", "Lorg/jetbrains/kotlin/js/config/JsGenerationGranularity;", "Lorg/jetbrains/kotlin/cli/common/arguments/CommonJsAndWasmCompilerArguments;", "getGranularity", "(Lorg/jetbrains/kotlin/cli/common/arguments/CommonJsAndWasmCompilerArguments;)Lorg/jetbrains/kotlin/js/config/JsGenerationGranularity;", "dtsStrategy", "Lorg/jetbrains/kotlin/js/config/TsCompilationStrategy;", "getDtsStrategy", "(Lorg/jetbrains/kotlin/cli/common/arguments/CommonJsAndWasmCompilerArguments;)Lorg/jetbrains/kotlin/js/config/TsCompilationStrategy;", "sourceMapContentEmbeddingMap", Argument.Delimiters.none, Argument.Delimiters.none, "Lorg/jetbrains/kotlin/js/config/SourceMapSourceEmbedding;", "getSourceMapContentEmbeddingMap", "()Ljava/util/Map;", "sourceMapNamesPolicyMap", "Lorg/jetbrains/kotlin/js/config/SourceMapNamesPolicy;", "getSourceMapNamesPolicyMap", "moduleKindMap", "Lorg/jetbrains/kotlin/js/config/ModuleKind;", "getModuleKindMap", "configureLibraries", Argument.Delimiters.none, "libraryString", "splitByPathSeparator", "calculateSourceMapSourceRoot", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "arguments", "platformChecker", "Lorg/jetbrains/kotlin/library/loader/KlibPlatformChecker;", "getPlatformChecker", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;)Lorg/jetbrains/kotlin/library/loader/KlibPlatformChecker;", "initializeFinalArtifactConfiguration", Argument.Delimiters.none, "org.jetbrains.kotlin:cli-js"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class HelpersKt {
    private static final Map<String, SourceMapSourceEmbedding> sourceMapContentEmbeddingMap = MapsKt.mapOf(new Pair[]{TuplesKt.to(K2JsArgumentConstants.SOURCE_MAP_SOURCE_CONTENT_ALWAYS, SourceMapSourceEmbedding.ALWAYS), TuplesKt.to(K2JsArgumentConstants.SOURCE_MAP_SOURCE_CONTENT_NEVER, SourceMapSourceEmbedding.NEVER), TuplesKt.to(K2JsArgumentConstants.SOURCE_MAP_SOURCE_CONTENT_INLINING, SourceMapSourceEmbedding.INLINING)});
    private static final Map<String, SourceMapNamesPolicy> sourceMapNamesPolicyMap = MapsKt.mapOf(new Pair[]{TuplesKt.to(K2JsArgumentConstants.SOURCE_MAP_NAMES_POLICY_NO, SourceMapNamesPolicy.NO), TuplesKt.to(K2JsArgumentConstants.SOURCE_MAP_NAMES_POLICY_SIMPLE_NAMES, SourceMapNamesPolicy.SIMPLE_NAMES), TuplesKt.to(K2JsArgumentConstants.SOURCE_MAP_NAMES_POLICY_FQ_NAMES, SourceMapNamesPolicy.FULLY_QUALIFIED_NAMES)});
    private static final Map<String, ModuleKind> moduleKindMap = MapsKt.mapOf(new Pair[]{TuplesKt.to(K2JsArgumentConstants.MODULE_PLAIN, ModuleKind.PLAIN), TuplesKt.to(K2JsArgumentConstants.MODULE_COMMONJS, ModuleKind.COMMON_JS), TuplesKt.to(K2JsArgumentConstants.MODULE_AMD, ModuleKind.AMD), TuplesKt.to(K2JsArgumentConstants.MODULE_UMD, ModuleKind.UMD), TuplesKt.to(K2JsArgumentConstants.MODULE_ES, ModuleKind.ES)});

    public static final String calculateSourceMapSourceRoot(CompilerConfiguration compilerConfiguration, CommonJsAndWasmCompilerArguments commonJsAndWasmCompilerArguments) {
        String path;
        compilerConfiguration.getClass();
        commonJsAndWasmCompilerArguments.getClass();
        ArrayList arrayList = new ArrayList();
        HashMap map = new HashMap();
        try {
            Iterator<String> it = commonJsAndWasmCompilerArguments.getFreeArgs().iterator();
            File file = null;
            while (it.hasNext()) {
                File canonicalFile = new File(it.next()).getCanonicalFile();
                if (file != null) {
                    while (canonicalFile != null) {
                        Integer num = (Integer) map.get(canonicalFile);
                        if (num != null) {
                            arrayList.subList(Math.min(num.intValue(), arrayList.size() - 1) + 1, arrayList.size()).clear();
                            file = (File) arrayList.get(arrayList.size() - 1);
                            break;
                        }
                        canonicalFile = canonicalFile.getParentFile();
                    }
                    if (canonicalFile == null) {
                        break;
                    }
                } else {
                    for (File parentFile = canonicalFile; parentFile != null; parentFile = parentFile.getParentFile()) {
                        arrayList.add(parentFile);
                    }
                    CollectionsKt.reverse(arrayList);
                    int size = arrayList.size();
                    for (int i = 0; i < size; i++) {
                        map.put(arrayList.get(i), Integer.valueOf(i));
                    }
                    file = canonicalFile;
                }
            }
            return (file == null || (path = file.getPath()) == null) ? "." : path;
        } catch (IOException e) {
            String throwableText = ExceptionUtil.getThrowableText(e);
            throwableText.getClass();
            CliDiagnosticReportingKt.report$default(compilerConfiguration, CliDiagnostics.INSTANCE.getIO_ERROR(), "IO error occurred calculating source root:\n" + throwableText, null, 4, null);
            return ".";
        }
    }

    public static final List<String> configureLibraries(String str) {
        List<String> listSplitByPathSeparator;
        return (str == null || (listSplitByPathSeparator = splitByPathSeparator(str)) == null) ? CollectionsKt.emptyList() : listSplitByPathSeparator;
    }

    public static final TsCompilationStrategy getDtsStrategy(CommonJsAndWasmCompilerArguments commonJsAndWasmCompilerArguments) {
        commonJsAndWasmCompilerArguments.getClass();
        if (commonJsAndWasmCompilerArguments.getGenerateDts()) {
            return ((commonJsAndWasmCompilerArguments instanceof K2JSCompilerArguments) && ((K2JSCompilerArguments) commonJsAndWasmCompilerArguments).getIrPerFile()) ? TsCompilationStrategy.EACH_FILE : TsCompilationStrategy.MERGED;
        }
        return TsCompilationStrategy.NONE;
    }

    public static final JsGenerationGranularity getGranularity(CommonJsAndWasmCompilerArguments commonJsAndWasmCompilerArguments) {
        commonJsAndWasmCompilerArguments.getClass();
        boolean z = commonJsAndWasmCompilerArguments instanceof K2JSCompilerArguments;
        if (z && ((K2JSCompilerArguments) commonJsAndWasmCompilerArguments).getIrPerFile()) {
            return JsGenerationGranularity.PER_FILE;
        }
        return (z && ((K2JSCompilerArguments) commonJsAndWasmCompilerArguments).getIrPerModule()) ? JsGenerationGranularity.PER_MODULE : JsGenerationGranularity.WHOLE_PROGRAM;
    }

    public static final Map<String, ModuleKind> getModuleKindMap() {
        return moduleKindMap;
    }

    public static final KlibPlatformChecker getPlatformChecker(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return JSConfigurationKeysKt.getWasmCompilation(compilerConfiguration) ? new KlibPlatformChecker.Wasm(WasmConfigurationKeysKt.getWasmTarget(compilerConfiguration).getAlias()) : KlibPlatformChecker.JS.INSTANCE;
    }

    public static final Map<String, SourceMapSourceEmbedding> getSourceMapContentEmbeddingMap() {
        return sourceMapContentEmbeddingMap;
    }

    public static final Map<String, SourceMapNamesPolicy> getSourceMapNamesPolicyMap() {
        return sourceMapNamesPolicyMap;
    }

    public static final EcmaVersion getTargetVersion(K2JSCompilerArguments k2JSCompilerArguments) {
        Object next;
        k2JSCompilerArguments.getClass();
        String target = k2JSCompilerArguments.getTarget();
        if (target == null) {
            return EcmaVersion.Companion.defaultVersion();
        }
        Iterator it = EcmaVersion.getEntries().iterator();
        while (it.hasNext()) {
            next = it.next();
            if (Intrinsics.areEqual(((EcmaVersion) next).name(), target)) {
                return (EcmaVersion) next;
            }
        }
        next = null;
        return (EcmaVersion) next;
    }

    public static final void initializeFinalArtifactConfiguration(CompilerConfiguration compilerConfiguration, CommonJsAndWasmCompilerArguments commonJsAndWasmCompilerArguments) {
        String moduleName;
        File outputDir;
        String outputName;
        compilerConfiguration.getClass();
        commonJsAndWasmCompilerArguments.getClass();
        ModuleKind moduleKind = JSConfigurationKeysKt.getModuleKind(compilerConfiguration);
        if (moduleKind == null || (moduleName = CommonConfigurationKeysKt.getModuleName(compilerConfiguration)) == null || (outputDir = JSConfigurationKeysKt.getOutputDir(compilerConfiguration)) == null || (outputName = JSConfigurationKeysKt.getOutputName(compilerConfiguration)) == null) {
            return;
        }
        JSConfigurationKeysKt.setArtifactConfiguration(compilerConfiguration, new WebArtifactConfiguration(moduleKind, moduleName, outputDir, outputName, getGranularity(commonJsAndWasmCompilerArguments), getDtsStrategy(commonJsAndWasmCompilerArguments)));
    }

    private static final List<String> splitByPathSeparator(String str) {
        List listEmptyList;
        String str2 = File.pathSeparator;
        str2.getClass();
        Regex regex = new Regex(str2);
        List listSplit = regex.split(str, 0);
        if (listSplit.isEmpty()) {
            listEmptyList = CollectionsKt.emptyList();
            break;
        }
        ListIterator listIterator = listSplit.listIterator(listSplit.size());
        while (true) {
            if (!listIterator.hasPrevious()) {
                listEmptyList = CollectionsKt.emptyList();
                break;
            }
            if (((String) listIterator.previous()).length() != 0) {
                listEmptyList = CollectionsKt.take(listSplit, listIterator.nextIndex() + 1);
                break;
            }
        }
        Object[] array = listEmptyList.toArray(new String[0]);
        ArrayList arrayList = new ArrayList();
        for (Object obj : array) {
            if (((String) obj).length() != 0) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }
}
