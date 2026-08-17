package org.jetbrains.kotlin.cli.js;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.CliDiagnosticReportingKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.arguments.CommonJsAndWasmCompilerArguments;
import org.jetbrains.kotlin.cli.common.arguments.KotlinWasmCompilerArguments;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.cli.js.IcCachesKt;
import org.jetbrains.kotlin.cli.pipeline.web.wasm.WasmCompilationMode;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.ir.backend.js.JsICContext;
import org.jetbrains.kotlin.ir.backend.js.ic.CacheUpdater;
import org.jetbrains.kotlin.ir.backend.js.ic.DirtyFileState;
import org.jetbrains.kotlin.ir.backend.js.ic.KotlinLibraryFile;
import org.jetbrains.kotlin.ir.backend.js.ic.KotlinSourceFile;
import org.jetbrains.kotlin.ir.backend.js.ic.PlatformDependentICContext;
import org.jetbrains.kotlin.js.config.JSConfigurationKeysKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\u001a8\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000bH\u0000\u001a8\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000bH\u0000¨\u0006\u000e"}, d2 = {"prepareIcCaches", "Lorg/jetbrains/kotlin/cli/js/IcCachesArtifacts;", "cacheDirectory", Argument.Delimiters.none, "arguments", "Lorg/jetbrains/kotlin/cli/common/arguments/CommonJsAndWasmCompilerArguments;", ModuleXmlParser.OUTPUT_DIR, "Ljava/io/File;", "targetConfiguration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "mainCallArguments", Argument.Delimiters.none, "icConfigurationData", "Lorg/jetbrains/kotlin/cli/js/IcCachesConfigurationData;", "org.jetbrains.kotlin:cli-js"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class IcCachesKt {

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[WasmCompilationMode.values().length];
            try {
                iArr[WasmCompilationMode.REGULAR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[WasmCompilationMode.MULTI_MODULE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[WasmCompilationMode.SINGLE_MODULE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static CharSequence a(DirtyFileState dirtyFileState) {
        return dirtyFileState.getStr();
    }

    public static final IcCachesArtifacts prepareIcCaches(String str, IcCachesConfigurationData icCachesConfigurationData, File file, CompilerConfiguration compilerConfiguration, List<String> list) {
        Function4 function4;
        JsICContext jsICContext;
        boolean z;
        DirtyFileState dirtyFileState;
        Pair pair;
        String strJoinToString$default;
        str.getClass();
        icCachesConfigurationData.getClass();
        file.getClass();
        compilerConfiguration.getClass();
        CliDiagnosticReportingKt.reportLog$default(compilerConfiguration, Argument.Delimiters.none, null, 2, null);
        CliDiagnosticReportingKt.reportLog$default(compilerConfiguration, "Building cache:", null, 2, null);
        CliDiagnosticReportingKt.reportLog$default(compilerConfiguration, "to: " + file, null, 2, null);
        CliDiagnosticReportingKt.reportLog$default(compilerConfiguration, "cache directory: " + str, null, 2, null);
        CliDiagnosticReportingKt.reportLog$default(compilerConfiguration, JSConfigurationKeysKt.getLibraries(compilerConfiguration).toString(), null, 2, null);
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (icCachesConfigurationData instanceof IcCachesConfigurationData.Js) {
            jsICContext = new JsICContext(list, ((IcCachesConfigurationData.Js) icCachesConfigurationData).getGranularity(), (Set) null, 4, (DefaultConstructorMarker) null);
            z = false;
        } else {
            if (!(icCachesConfigurationData instanceof IcCachesConfigurationData.Wasm)) {
                bu8.a();
                return null;
            }
            IcCachesConfigurationData.Wasm wasm = (IcCachesConfigurationData.Wasm) icCachesConfigurationData;
            boolean z2 = wasm.getMode() == WasmCompilationMode.SINGLE_MODULE;
            int i = WhenMappings.$EnumSwitchMapping$0[wasm.getMode().ordinal()];
            if (i == 1) {
                function4 = IcCachesKt$prepareIcCaches$icContext$contextConstructor$1.INSTANCE;
            } else if (i == 2) {
                function4 = IcCachesKt$prepareIcCaches$icContext$contextConstructor$2.INSTANCE;
            } else {
                if (i != 3) {
                    bu8.a();
                    return null;
                }
                function4 = IcCachesKt$prepareIcCaches$icContext$contextConstructor$3.INSTANCE;
            }
            jsICContext = (PlatformDependentICContext) function4.invoke(Boolean.FALSE, Boolean.valueOf(!wasm.getWasmDebug()), Boolean.valueOf(!wasm.getGenerateWat()), Boolean.valueOf(!wasm.getGenerateDebugInformation()));
            z = z2;
        }
        CacheUpdater cacheUpdater = new CacheUpdater(str, compilerConfiguration, jsICContext, icCachesConfigurationData instanceof IcCachesConfigurationData.Wasm, z);
        List listActualizeCaches = cacheUpdater.actualizeCaches();
        CliDiagnosticReportingKt.reportLog$default(compilerConfiguration, "IC rebuilt overall time: " + (System.currentTimeMillis() - jCurrentTimeMillis) + "ms", null, 2, null);
        for (Pair pair2 : cacheUpdater.getStopwatchLastLaps()) {
            CliDiagnosticReportingKt.reportLog$default(compilerConfiguration, "  " + ((String) pair2.component1()) + ": " + ((int) (((Number) pair2.component2()).longValue() / 1000000.0d)) + "ms", null, 2, null);
        }
        int i2 = 0;
        for (Map.Entry entry : cacheUpdater.getDirtyFileLastStats().entrySet()) {
            String str2 = ((KotlinLibraryFile) entry.getKey()).unbox-impl();
            Map map = (Map) entry.getValue();
            EnumSet enumSet = (EnumSet) CollectionsKt.firstOrNull(map.values());
            if (enumSet != null && (dirtyFileState = (DirtyFileState) CollectionsKt.singleOrNull(enumSet)) != null) {
                Collection collectionValues = map.values();
                if (!(collectionValues instanceof Collection) || !collectionValues.isEmpty()) {
                    Iterator it = collectionValues.iterator();
                    while (it.hasNext()) {
                        if (CollectionsKt.singleOrNull((EnumSet) it.next()) != dirtyFileState) {
                            dirtyFileState = null;
                            break;
                        }
                    }
                }
            } else {
                dirtyFileState = null;
                break;
            }
            if (dirtyFileState != DirtyFileState.NON_MODIFIED_IR) {
                if (dirtyFileState == DirtyFileState.REMOVED_FILE) {
                    pair = TuplesKt.to("removed", MapsKt.emptyMap());
                } else if (dirtyFileState != DirtyFileState.ADDED_FILE) {
                    Collection collectionValues2 = map.values();
                    if (!(collectionValues2 instanceof Collection) || !collectionValues2.isEmpty()) {
                        Iterator it2 = collectionValues2.iterator();
                        while (true) {
                            if (!it2.hasNext()) {
                                pair = TuplesKt.to("fully rebuilt", map);
                                break;
                            }
                            if (CollectionsKt.singleOrNull((EnumSet) it2.next()) == DirtyFileState.NON_MODIFIED_IR) {
                                pair = TuplesKt.to("partially rebuilt", map);
                                break;
                            }
                        }
                    } else {
                        pair = TuplesKt.to("fully rebuilt", map);
                        break;
                    }
                } else {
                    pair = TuplesKt.to("built clean", MapsKt.emptyMap());
                }
                String str3 = (String) pair.component1();
                Map map2 = (Map) pair.component2();
                StringBuilder sb = new StringBuilder();
                i2++;
                sb.append(i2);
                sb.append(") module [");
                sb.append(new File(str2).getName());
                sb.append("] was ");
                sb.append(str3);
                CliDiagnosticReportingKt.reportLog$default(compilerConfiguration, sb.toString(), null, 2, null);
                int i3 = 0;
                for (Map.Entry entry2 : map2.entrySet()) {
                    KotlinSourceFile kotlinSourceFile = (KotlinSourceFile) entry2.getKey();
                    EnumSet enumSet2 = (EnumSet) entry2.getValue();
                    ArrayList arrayList = new ArrayList();
                    for (Object obj : enumSet2) {
                        if (((DirtyFileState) obj) != DirtyFileState.NON_MODIFIED_IR) {
                            arrayList.add(obj);
                        }
                    }
                    ArrayList arrayList2 = !arrayList.isEmpty() ? arrayList : null;
                    if (arrayList2 != null && (strJoinToString$default = CollectionsKt.joinToString$default(arrayList2, (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: ph6
                        public final Object invoke(Object obj2) {
                            return IcCachesKt.a((DirtyFileState) obj2);
                        }
                    }, 31, (Object) null)) != null) {
                        StringBuilder sb2 = new StringBuilder("  ");
                        sb2.append(i2);
                        sb2.append('.');
                        i3++;
                        sb2.append(i3);
                        sb2.append(") file [");
                        sb2.append(new File(kotlinSourceFile.getPath()).getName());
                        sb2.append("]: (");
                        sb2.append(strJoinToString$default);
                        sb2.append(')');
                        CliDiagnosticReportingKt.reportLog$default(compilerConfiguration, sb2.toString(), null, 2, null);
                    }
                }
            }
        }
        return new IcCachesArtifacts(listActualizeCaches);
    }

    public static final IcCachesArtifacts prepareIcCaches(String str, CommonJsAndWasmCompilerArguments commonJsAndWasmCompilerArguments, File file, CompilerConfiguration compilerConfiguration, List<String> list) {
        IcCachesConfigurationData js;
        str.getClass();
        commonJsAndWasmCompilerArguments.getClass();
        file.getClass();
        compilerConfiguration.getClass();
        if (commonJsAndWasmCompilerArguments instanceof KotlinWasmCompilerArguments) {
            KotlinWasmCompilerArguments kotlinWasmCompilerArguments = (KotlinWasmCompilerArguments) commonJsAndWasmCompilerArguments;
            js = new IcCachesConfigurationData.Wasm(kotlinWasmCompilerArguments.getWasmDebug(), kotlinWasmCompilerArguments.getWasmGenerateWat(), commonJsAndWasmCompilerArguments.getSourceMap() || kotlinWasmCompilerArguments.getGenerateDwarf(), WasmCompilationMode.INSTANCE.wasmCompilationMode(compilerConfiguration));
        } else {
            js = new IcCachesConfigurationData.Js(HelpersKt.getGranularity(commonJsAndWasmCompilerArguments));
        }
        return prepareIcCaches(str, js, file, compilerConfiguration, list);
    }
}
