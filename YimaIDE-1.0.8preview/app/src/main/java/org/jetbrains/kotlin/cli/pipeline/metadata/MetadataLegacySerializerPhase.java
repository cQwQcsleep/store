package org.jetbrains.kotlin.cli.pipeline.metadata;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.io.FilesKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.metadata.AbstractMetadataSerializer;
import org.jetbrains.kotlin.cli.metadata.K1LegacyMetadataSerializerKt;
import org.jetbrains.kotlin.codegen.JvmCodegenUtil;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirModuleDataKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationUtilKt;
import org.jetbrains.kotlin.fir.pipeline.SingleModuleFrontendOutput;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.metadata.deserialization.BinaryVersion;
import org.jetbrains.kotlin.metadata.deserialization.MetadataVersion;
import org.jetbrains.kotlin.metadata.jvm.JvmModuleProtoBuf;
import org.jetbrains.kotlin.metadata.jvm.deserialization.ModuleMappingKt;
import org.jetbrains.kotlin.metadata.jvm.deserialization.PackageParts;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J&\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0014¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/metadata/MetadataLegacySerializerPhase;", "Lorg/jetbrains/kotlin/cli/pipeline/metadata/MetadataLegacySerializerPhaseBase;", "<init>", "()V", "serialize", "Lorg/jetbrains/kotlin/cli/metadata/AbstractMetadataSerializer$OutputInfo;", "analysisResult", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/pipeline/SingleModuleFrontendOutput;", "destDir", "Ljava/io/File;", "metadataVersion", "Lorg/jetbrains/kotlin/metadata/deserialization/BinaryVersion;", "org.jetbrains.kotlin:cli-metadata"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class MetadataLegacySerializerPhase extends MetadataLegacySerializerPhaseBase {
    public static final MetadataLegacySerializerPhase INSTANCE = new MetadataLegacySerializerPhase();

    private MetadataLegacySerializerPhase() {
        super("MetadataLegacySerializerPhase");
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.cli.pipeline.metadata.MetadataLegacySerializerPhaseBase
    public AbstractMetadataSerializer.OutputInfo serialize(List<SingleModuleFrontendOutput> analysisResult, File destDir, BinaryVersion metadataVersion) throws IOException, KotlinIllegalArgumentExceptionWithAttachments {
        analysisResult.getClass();
        destDir.getClass();
        metadataVersion.getClass();
        SingleModuleFrontendOutput singleModuleFrontendOutput = (SingleModuleFrontendOutput) CollectionsKt.single(analysisResult);
        FirSession session = singleModuleFrontendOutput.getSession();
        ScopeSession scopeSession = singleModuleFrontendOutput.getScopeSession();
        Map<FqName, MetadataLegacySerializerPhaseBase.PackageContent> mapCollectPackagesContent = collectPackagesContent(singleModuleFrontendOutput.component3());
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        MetadataLegacySerializerPhaseBase.Counters counters = new MetadataLegacySerializerPhaseBase.Counters();
        for (Map.Entry<FqName, MetadataLegacySerializerPhaseBase.PackageContent> entry : mapCollectPackagesContent.entrySet()) {
            FqName key = entry.getKey();
            MetadataLegacySerializerPhaseBase.PackageContent value = entry.getValue();
            List<FirRegularClass> listComponent1 = value.component1();
            Map<FirFile, List<FirMemberDeclaration>> mapComponent2 = value.component2();
            for (FirRegularClass firRegularClass : listComponent1) {
                new MetadataLegacySerializerPhaseBase.PackageSerializer(key, CollectionsKt.listOf(firRegularClass), CollectionsKt.emptyList(), new File(destDir, K1LegacyMetadataSerializerKt.getClassFilePath(FirDeclarationUtilKt.getClassId(firRegularClass))), session, scopeSession, metadataVersion, counters).serialize();
            }
            BinaryVersion binaryVersion = metadataVersion;
            for (Map.Entry<FirFile, List<FirMemberDeclaration>> entry2 : mapComponent2.entrySet()) {
                FirFile key2 = entry2.getKey();
                List<FirMemberDeclaration> value2 = entry2.getValue();
                File file = new File(destDir, K1LegacyMetadataSerializerKt.getPackageFilePath(key, key2.getName()));
                new MetadataLegacySerializerPhaseBase.PackageSerializer(key, CollectionsKt.emptyList(), value2, file, session, scopeSession, binaryVersion, counters).serialize();
                Object packageParts = linkedHashMap.get(key);
                if (packageParts == null) {
                    packageParts = new PackageParts(key.asString());
                    linkedHashMap.put(key, packageParts);
                }
                ((PackageParts) packageParts).addMetadataPart(FilesKt.getNameWithoutExtension(file));
            }
            metadataVersion = binaryVersion;
        }
        File file2 = new File(destDir, JvmCodegenUtil.getMappingFileName(JvmCodegenUtil.prepareModuleName(FirModuleDataKt.getModuleData(session).getName())));
        JvmModuleProtoBuf.Module.Builder builderNewBuilder = JvmModuleProtoBuf.Module.newBuilder();
        for (PackageParts packageParts2 : linkedHashMap.values()) {
            builderNewBuilder.getClass();
            packageParts2.addTo(builderNewBuilder);
        }
        JvmModuleProtoBuf.Module moduleBuild = builderNewBuilder.build();
        moduleBuild.getClass();
        byte[] bArrSerializeToByteArray = ModuleMappingKt.serializeToByteArray(moduleBuild, MetadataVersion.INSTANCE, 0);
        file2.getParentFile().mkdirs();
        FilesKt.writeBytes(file2, bArrSerializeToByteArray);
        return new AbstractMetadataSerializer.OutputInfo(counters.getTotalSize(), counters.getTotalFiles());
    }
}
