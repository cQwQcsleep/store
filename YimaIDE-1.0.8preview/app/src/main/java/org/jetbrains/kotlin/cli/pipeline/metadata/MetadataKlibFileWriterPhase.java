package org.jetbrains.kotlin.cli.pipeline.metadata;

import java.io.File;
import java.io.IOException;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function3;
import org.jetbrains.kotlin.cli.common.CLIConfigurationKeysKt;
import org.jetbrains.kotlin.cli.metadata.MetadataUtilsKt;
import org.jetbrains.kotlin.cli.pipeline.CheckCompilationErrors;
import org.jetbrains.kotlin.cli.pipeline.PerformanceNotifications;
import org.jetbrains.kotlin.cli.pipeline.PipelinePhase;
import org.jetbrains.kotlin.config.CommonConfigurationKeysKt;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.library.KlibElementWithSize;
import org.jetbrains.kotlin.library.KlibSizeInfoKt;
import org.jetbrains.kotlin.util.PerformanceManager;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0002H\u0016¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/metadata/MetadataKlibFileWriterPhase;", "Lorg/jetbrains/kotlin/cli/pipeline/PipelinePhase;", "Lorg/jetbrains/kotlin/cli/pipeline/metadata/MetadataInMemorySerializationArtifact;", "Lorg/jetbrains/kotlin/cli/pipeline/metadata/MetadataSerializationArtifact;", "<init>", "()V", "executePhase", "input", "org.jetbrains.kotlin:cli-metadata"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class MetadataKlibFileWriterPhase extends PipelinePhase<MetadataInMemorySerializationArtifact, MetadataSerializationArtifact> {
    public static final MetadataKlibFileWriterPhase INSTANCE = new MetadataKlibFileWriterPhase();

    private MetadataKlibFileWriterPhase() {
        super("MetadataKlibFileWriterPhase", SetsKt.setOf(PerformanceNotifications.KlibWritingStarted.INSTANCE), SetsKt.setOf(new Function3[]{PerformanceNotifications.KlibWritingFinished.INSTANCE, CheckCompilationErrors.CheckDiagnosticCollector.INSTANCE}));
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.PipelinePhase
    public MetadataSerializationArtifact executePhase(MetadataInMemorySerializationArtifact input) throws IOException {
        List listFlatten;
        PerformanceManager perfManager;
        input.getClass();
        File metadataDestinationDirectory = CLIConfigurationKeysKt.getMetadataDestinationDirectory(input.getConfiguration());
        metadataDestinationDirectory.getClass();
        MetadataUtilsKt.buildKotlinMetadataLibrary(input.getConfiguration(), input.getMetadata(), metadataDestinationDirectory);
        String absolutePath = metadataDestinationDirectory.getAbsolutePath();
        absolutePath.getClass();
        KlibElementWithSize klibElementWithSizeLoadSizeInfo = KlibSizeInfoKt.loadSizeInfo(new org.jetbrains.kotlin.konan.file.File(absolutePath));
        if (klibElementWithSizeLoadSizeInfo != null && (listFlatten = klibElementWithSizeLoadSizeInfo.flatten()) != null && (perfManager = CommonConfigurationKeysKt.getPerfManager(input.getConfiguration())) != null) {
            perfManager.registerKlibElementStats(listFlatten);
        }
        CompilerConfiguration configuration = input.getConfiguration();
        String canonicalPath = metadataDestinationDirectory.getCanonicalPath();
        canonicalPath.getClass();
        return new MetadataSerializationArtifact(null, configuration, canonicalPath);
    }
}
