package org.jetbrains.kotlin.cli.pipeline.web;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.arguments.K2JSCompilerArguments;
import org.jetbrains.kotlin.cli.pipeline.AbstractConfigurationPhase;
import org.jetbrains.kotlin.cli.pipeline.CheckCompilationErrors;
import org.jetbrains.kotlin.cli.pipeline.ConfigurationUpdater;
import org.jetbrains.kotlin.cli.pipeline.web.js.JsConfigurationUpdater;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.metadata.deserialization.BinaryVersion;
import org.jetbrains.kotlin.metadata.deserialization.MetadataVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0000\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0014¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/web/JsConfigurationPhase;", "Lorg/jetbrains/kotlin/cli/pipeline/AbstractConfigurationPhase;", "Lorg/jetbrains/kotlin/cli/common/arguments/K2JSCompilerArguments;", "<init>", "()V", "createMetadataVersion", "Lorg/jetbrains/kotlin/metadata/deserialization/BinaryVersion;", "versionArray", Argument.Delimiters.none, "org.jetbrains.kotlin:cli-js"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class JsConfigurationPhase extends AbstractConfigurationPhase<K2JSCompilerArguments> {
    public static final JsConfigurationPhase INSTANCE = new JsConfigurationPhase();

    private JsConfigurationPhase() {
        super("JsConfigurationPhase", null, SetsKt.setOf(CheckCompilationErrors.CheckDiagnosticCollector.INSTANCE), CollectionsKt.listOf(new ConfigurationUpdater[]{CommonJsConfigurationUpdater.INSTANCE, JsConfigurationUpdater.INSTANCE}), 2, null);
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.AbstractConfigurationPhase
    public BinaryVersion createMetadataVersion(int[] versionArray) {
        versionArray.getClass();
        return new MetadataVersion(Arrays.copyOf(versionArray, versionArray.length));
    }
}
