package org.jetbrains.kotlin.cli.pipeline.metadata;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function3;
import org.jetbrains.kotlin.cli.pipeline.CheckCompilationErrors;
import org.jetbrains.kotlin.cli.pipeline.PerformanceNotifications;
import org.jetbrains.kotlin.cli.pipeline.PipelinePhase;
import org.jetbrains.kotlin.config.CommonConfigurationKeysKt;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.LanguageVersion;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirModuleDataKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.pipeline.SingleModuleFrontendOutput;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.providers.FirProviderKt;
import org.jetbrains.kotlin.fir.serialization.FirKLibSerializerExtension;
import org.jetbrains.kotlin.fir.serialization.FirKlibSerializationKt;
import org.jetbrains.kotlin.library.SerializedMetadata;
import org.jetbrains.kotlin.library.metadata.KlibMetadataProtoBuf;
import org.jetbrains.kotlin.metadata.ProtoBuf;
import org.jetbrains.kotlin.metadata.deserialization.MetadataVersion;
import org.jetbrains.kotlin.util.KlibMetadataHelpersKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0002H\u0016¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/metadata/MetadataKlibInMemorySerializerPhase;", "Lorg/jetbrains/kotlin/cli/pipeline/PipelinePhase;", "Lorg/jetbrains/kotlin/cli/pipeline/metadata/MetadataFrontendPipelineArtifact;", "Lorg/jetbrains/kotlin/cli/pipeline/metadata/MetadataInMemorySerializationArtifact;", "<init>", "()V", "executePhase", "input", "org.jetbrains.kotlin:cli-metadata"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class MetadataKlibInMemorySerializerPhase extends PipelinePhase<MetadataFrontendPipelineArtifact, MetadataInMemorySerializationArtifact> {
    public static final MetadataKlibInMemorySerializerPhase INSTANCE = new MetadataKlibInMemorySerializerPhase();

    private MetadataKlibInMemorySerializerPhase() {
        super("MetadataKlibInMemorySerializerPhase", SetsKt.setOf(PerformanceNotifications.KlibWritingStarted.INSTANCE), SetsKt.setOf(new Function3[]{PerformanceNotifications.KlibWritingFinished.INSTANCE, CheckCompilationErrors.CheckDiagnosticCollector.INSTANCE}));
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.PipelinePhase
    public MetadataInMemorySerializationArtifact executePhase(MetadataFrontendPipelineArtifact input) {
        input.getClass();
        List<? extends SingleModuleFrontendOutput> listM38component1QYgrGdg = input.m38component1QYgrGdg();
        CompilerConfiguration configuration = input.getConfiguration();
        MetadataVersion metadataVersionKlibMetadataVersionOrDefault$default = KlibMetadataHelpersKt.klibMetadataVersionOrDefault$default(configuration, (LanguageVersion) null, 1, (Object) null);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (SingleModuleFrontendOutput singleModuleFrontendOutput : listM38component1QYgrGdg) {
            FirSession session = singleModuleFrontendOutput.getSession();
            ScopeSession scopeSession = singleModuleFrontendOutput.getScopeSession();
            List<FirFile> listComponent3 = singleModuleFrontendOutput.component3();
            LanguageVersionSettings languageVersionSettings = CommonConfigurationKeysKt.getLanguageVersionSettings(configuration);
            for (FirFile firFile : listComponent3) {
                ProtoBuf.PackageFragment packageFragmentSerializeSingleFirFile$default = FirKlibSerializationKt.serializeSingleFirFile$default(firFile, session, scopeSession, null, new FirKLibSerializerExtension(session, scopeSession, FirProviderKt.getFirProvider(session), metadataVersionKlibMetadataVersionOrDefault$default, languageVersionSettings.supportsFeature(LanguageFeature.ExportKDocDocumentationToKlib), null), languageVersionSettings, false, 64, null);
                String strAsString = UtilsKt.getPackageFqName(firFile).asString();
                Object arrayList = linkedHashMap.get(strAsString);
                if (arrayList == null) {
                    arrayList = new ArrayList();
                    linkedHashMap.put(strAsString, arrayList);
                }
                byte[] byteArray = packageFragmentSerializeSingleFirFile$default.toByteArray();
                byteArray.getClass();
                ((List) arrayList).add(byteArray);
            }
        }
        KlibMetadataProtoBuf.Header.Builder builderNewBuilder = KlibMetadataProtoBuf.Header.newBuilder();
        builderNewBuilder.setModuleName(FirModuleDataKt.getModuleData(((SingleModuleFrontendOutput) CollectionsKt.last(listM38component1QYgrGdg)).getSession()).getName().asString());
        if (CommonConfigurationKeysKt.getLanguageVersionSettings(configuration).isPreRelease()) {
            builderNewBuilder.setFlags(2);
        }
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        for (Map.Entry entry : CollectionsKt.sortedWith(linkedHashMap.entrySet(), new Comparator() { // from class: org.jetbrains.kotlin.cli.pipeline.metadata.MetadataKlibInMemorySerializerPhase$executePhase$$inlined$sortedBy$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues((String) ((Map.Entry) t).getKey(), (String) ((Map.Entry) t2).getKey());
            }
        })) {
            String str = (String) entry.getKey();
            List list = (List) entry.getValue();
            arrayList2.add(str);
            arrayList3.add(list);
            builderNewBuilder.addPackageFragmentName(str);
        }
        byte[] byteArray2 = builderNewBuilder.build().toByteArray();
        byteArray2.getClass();
        return new MetadataInMemorySerializationArtifact(new SerializedMetadata(byteArray2, arrayList3, arrayList2, metadataVersionKlibMetadataVersionOrDefault$default.toArray()), configuration);
    }
}
