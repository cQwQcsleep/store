package org.jetbrains.kotlin.cli.pipeline.web;

import java.io.File;
import kotlin.Metadata;
import kotlin.io.FilesKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.js.config.JSConfigurationKeysKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"computeOutputKlibPath", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "org.jetbrains.kotlin:cli-js"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class WebKlibSerializationPipelinePhaseKt {
    public static final String computeOutputKlibPath(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        if (!JSConfigurationKeysKt.getProduceKlibFile(compilerConfiguration)) {
            File outputDir = JSConfigurationKeysKt.getOutputDir(compilerConfiguration);
            outputDir.getClass();
            String absolutePath = outputDir.getAbsolutePath();
            absolutePath.getClass();
            return absolutePath;
        }
        File outputDir2 = JSConfigurationKeysKt.getOutputDir(compilerConfiguration);
        outputDir2.getClass();
        StringBuilder sb = new StringBuilder();
        String outputName = JSConfigurationKeysKt.getOutputName(compilerConfiguration);
        outputName.getClass();
        sb.append(outputName);
        sb.append(".klib");
        String absolutePath2 = FilesKt.normalize(FilesKt.resolve(outputDir2, sb.toString())).getAbsolutePath();
        absolutePath2.getClass();
        return absolutePath2;
    }
}
