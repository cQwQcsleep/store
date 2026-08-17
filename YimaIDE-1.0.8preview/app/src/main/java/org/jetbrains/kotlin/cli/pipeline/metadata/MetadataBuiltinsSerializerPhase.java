package org.jetbrains.kotlin.cli.pipeline.metadata;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.io.FilesKt;
import org.jetbrains.kotlin.builtins.StandardNames;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.metadata.AbstractMetadataSerializer;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirModuleDataKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.pipeline.SingleModuleFrontendOutput;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.providers.impl.FirCloneableSymbolProvider;
import org.jetbrains.kotlin.fir.scopes.FirKotlinScopeProviderKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.metadata.builtins.BuiltInsBinaryVersion;
import org.jetbrains.kotlin.metadata.deserialization.BinaryVersion;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.serialization.deserialization.builtins.BuiltInSerializerProtocol;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J&\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0014¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/metadata/MetadataBuiltinsSerializerPhase;", "Lorg/jetbrains/kotlin/cli/pipeline/metadata/MetadataLegacySerializerPhaseBase;", "<init>", "()V", "serialize", "Lorg/jetbrains/kotlin/cli/metadata/AbstractMetadataSerializer$OutputInfo;", "analysisResult", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/pipeline/SingleModuleFrontendOutput;", "destDir", "Ljava/io/File;", "metadataVersion", "Lorg/jetbrains/kotlin/metadata/deserialization/BinaryVersion;", "org.jetbrains.kotlin:cli-metadata"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class MetadataBuiltinsSerializerPhase extends MetadataLegacySerializerPhaseBase {
    public static final MetadataBuiltinsSerializerPhase INSTANCE = new MetadataBuiltinsSerializerPhase();

    private MetadataBuiltinsSerializerPhase() {
        super("MetadataBuiltinsSerializerPhase");
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.cli.pipeline.metadata.MetadataLegacySerializerPhaseBase
    public AbstractMetadataSerializer.OutputInfo serialize(List<SingleModuleFrontendOutput> analysisResult, File destDir, BinaryVersion metadataVersion) throws IOException, KotlinIllegalArgumentExceptionWithAttachments {
        analysisResult.getClass();
        destDir.getClass();
        metadataVersion.getClass();
        SingleModuleFrontendOutput singleModuleFrontendOutput = (SingleModuleFrontendOutput) CollectionsKt.single(analysisResult);
        FirSession session = singleModuleFrontendOutput.getSession();
        ScopeSession scopeSession = singleModuleFrontendOutput.getScopeSession();
        List<FirFile> listComponent3 = singleModuleFrontendOutput.component3();
        FilesKt.deleteRecursively(destDir);
        if (!destDir.mkdirs()) {
            w04.a("Could not make directories: ", destDir);
            return null;
        }
        Map<FqName, MetadataLegacySerializerPhaseBase.PackageContent> mapCollectPackagesContent = collectPackagesContent(listComponent3);
        FqName fqName = StandardNames.BUILT_INS_PACKAGE_FQ_NAME;
        MetadataLegacySerializerPhaseBase.PackageContent packageContent = mapCollectPackagesContent.get(fqName);
        if (packageContent == null) {
            packageContent = new MetadataLegacySerializerPhaseBase.PackageContent(null, null, 3, null);
            mapCollectPackagesContent.put(fqName, packageContent);
        }
        List<FirRegularClass> classes = packageContent.getClasses();
        FirClassLikeSymbol<?> classLikeSymbolByClassId = new FirCloneableSymbolProvider(session, FirModuleDataKt.getModuleData(session), FirKotlinScopeProviderKt.getKotlinScopeProvider(session)).getClassLikeSymbolByClassId(StandardClassIds.INSTANCE.getCloneable());
        classLikeSymbolByClassId.getClass();
        E fir = classLikeSymbolByClassId.getFir();
        fir.getClass();
        classes.add((FirRegularClass) fir);
        MetadataLegacySerializerPhaseBase.Counters counters = new MetadataLegacySerializerPhaseBase.Counters();
        for (Map.Entry<FqName, MetadataLegacySerializerPhaseBase.PackageContent> entry : mapCollectPackagesContent.entrySet()) {
            FqName key = entry.getKey();
            MetadataLegacySerializerPhaseBase.PackageContent value = entry.getValue();
            new MetadataLegacySerializerPhaseBase.PackageSerializer(key, value.getClasses(), CollectionsKt.flatten(value.getMembersPerFile().values()), new File(destDir, BuiltInSerializerProtocol.INSTANCE.getBuiltInsFilePath(key)), session, scopeSession, BuiltInsBinaryVersion.INSTANCE, counters).serialize();
        }
        return new AbstractMetadataSerializer.OutputInfo(counters.getTotalSize(), counters.getTotalFiles());
    }
}
