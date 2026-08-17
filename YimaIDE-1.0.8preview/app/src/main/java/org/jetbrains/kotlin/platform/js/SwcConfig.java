package org.jetbrains.kotlin.platform.js;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import org.jetbrains.kotlin.js.config.ModuleKind;
import org.jetbrains.kotlin.library.components.KlibMetadataConstants;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010$\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J:\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0006J2\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0013¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/platform/js/SwcConfig;", "", "<init>", "()V", "getArgumentsWhen", "", "", "inputDirectoryOrFiles", "configPath", "outputDirectory", "environmentCode", "fileExtension", "getConfigWhen", "", "sourceMapEnabled", "", "target", "includeExternalHelpers", "moduleKind", "Lorg/jetbrains/kotlin/js/config/ModuleKind;", "org.jetbrains.kotlin:js.config"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SwcConfig {
    public static final SwcConfig INSTANCE = new SwcConfig();

    private SwcConfig() {
    }

    public final List<String> getArgumentsWhen(List<String> inputDirectoryOrFiles, String configPath, String outputDirectory, String environmentCode, String fileExtension) {
        inputDirectoryOrFiles.getClass();
        configPath.getClass();
        outputDirectory.getClass();
        environmentCode.getClass();
        fileExtension.getClass();
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        listCreateListBuilder.add("compile");
        Iterator<T> it = inputDirectoryOrFiles.iterator();
        while (it.hasNext()) {
            listCreateListBuilder.add((String) it.next());
        }
        listCreateListBuilder.add("--config-file");
        listCreateListBuilder.add(configPath);
        listCreateListBuilder.add("--env-name=" + environmentCode);
        listCreateListBuilder.add("--out-dir");
        listCreateListBuilder.add(outputDirectory);
        listCreateListBuilder.add("--out-file-extension=" + fileExtension);
        listCreateListBuilder.add("--extensions=" + fileExtension);
        return CollectionsKt.build(listCreateListBuilder);
    }

    public final Map<String, Object> getConfigWhen(boolean sourceMapEnabled, String target, boolean includeExternalHelpers, ModuleKind moduleKind) {
        target.getClass();
        moduleKind.getClass();
        Map mapCreateMapBuilder = MapsKt.createMapBuilder();
        mapCreateMapBuilder.put("$schema", "https://swc.rs/schema.json");
        mapCreateMapBuilder.put("sourceMaps", Boolean.valueOf(sourceMapEnabled));
        mapCreateMapBuilder.put("inputSourceMap", Boolean.valueOf(sourceMapEnabled));
        mapCreateMapBuilder.put("exclude", new String[]{".*\\.d\\.m?ts$"});
        Map mapCreateMapBuilder2 = MapsKt.createMapBuilder();
        Map mapCreateMapBuilder3 = MapsKt.createMapBuilder();
        mapCreateMapBuilder3.put("syntax", "ecmascript");
        Boolean bool = Boolean.TRUE;
        mapCreateMapBuilder3.put("dynamicImport", bool);
        mapCreateMapBuilder3.put("functionBind", bool);
        mapCreateMapBuilder3.put("importMeta", bool);
        mapCreateMapBuilder2.put("parser", MapsKt.build(mapCreateMapBuilder3));
        mapCreateMapBuilder2.put("loose", bool);
        mapCreateMapBuilder2.put("externalHelpers", Boolean.valueOf(includeExternalHelpers));
        mapCreateMapBuilder2.put("target", target);
        mapCreateMapBuilder.put("jsc", MapsKt.build(mapCreateMapBuilder2));
        Map mapCreateMapBuilder4 = MapsKt.createMapBuilder();
        mapCreateMapBuilder4.put("resolveFully", bool);
        mapCreateMapBuilder4.put("type", moduleKind == ModuleKind.ES ? "nodenext" : moduleKind.getType());
        mapCreateMapBuilder4.put("outFileExtension", moduleKind.getJsExtension());
        mapCreateMapBuilder.put(KlibMetadataConstants.KLIB_MODULE_METADATA_FILE_NAME, MapsKt.build(mapCreateMapBuilder4));
        return MapsKt.build(mapCreateMapBuilder);
    }
}
