package org.jetbrains.kotlin.cli.metadata;

import com.intellij.openapi.project.Project;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import org.jetbrains.kotlin.analyzer.common.CommonDependenciesContainer;
import org.jetbrains.kotlin.backend.common.serialization.metadata.KlibMetadataMonolithicSerializer;
import org.jetbrains.kotlin.cli.jvm.compiler.KotlinCoreEnvironment;
import org.jetbrains.kotlin.cli.metadata.K1MetadataKlibSerializer;
import org.jetbrains.kotlin.config.CommonConfigurationKeysKt;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.storage.LockBasedStorageManager;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\n\u0010\t\u001a\u0004\u0018\u00010\u0002H\u0014J\u001a\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eH\u0014¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/cli/metadata/K1MetadataKlibSerializer;", "Lorg/jetbrains/kotlin/cli/metadata/AbstractMetadataSerializer;", "Lorg/jetbrains/kotlin/cli/metadata/CommonAnalysisResult;", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "environment", "Lorg/jetbrains/kotlin/cli/jvm/compiler/KotlinCoreEnvironment;", "<init>", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Lorg/jetbrains/kotlin/cli/jvm/compiler/KotlinCoreEnvironment;)V", "analyze", "serialize", "Lorg/jetbrains/kotlin/cli/metadata/AbstractMetadataSerializer$OutputInfo;", "analysisResult", "destDir", "Ljava/io/File;", "org.jetbrains.kotlin:cli-metadata"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class K1MetadataKlibSerializer extends AbstractMetadataSerializer<CommonAnalysisResult> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public K1MetadataKlibSerializer(CompilerConfiguration compilerConfiguration, KotlinCoreEnvironment kotlinCoreEnvironment) {
        super(compilerConfiguration, kotlinCoreEnvironment, null, 4, null);
        compilerConfiguration.getClass();
        kotlinCoreEnvironment.getClass();
    }

    public static CommonDependenciesContainer a(K1MetadataKlibSerializer k1MetadataKlibSerializer) {
        return new KlibMetadataDependencyContainer(k1MetadataKlibSerializer.getConfiguration(), new LockBasedStorageManager("K2MetadataKlibSerializer"));
    }

    @Override // org.jetbrains.kotlin.cli.metadata.AbstractMetadataSerializer
    public CommonAnalysisResult analyze() {
        return CommonAnalysisKt.runCommonAnalysisForSerialization(getEnvironment(), true, new Function0() { // from class: e38
            public final Object invoke() {
                return K1MetadataKlibSerializer.a(this.b);
            }
        });
    }

    @Override // org.jetbrains.kotlin.cli.metadata.AbstractMetadataSerializer
    public AbstractMetadataSerializer.OutputInfo serialize(CommonAnalysisResult analysisResult, File destDir) {
        analysisResult.getClass();
        destDir.getClass();
        Project project = getEnvironment().getProject();
        MetadataUtilsKt.buildKotlinMetadataLibrary(getConfiguration(), new KlibMetadataMonolithicSerializer(CommonConfigurationKeysKt.getLanguageVersionSettings(getConfiguration()), getMetadataVersion(), project, false, false, true, false).serializeModule(analysisResult.getModuleDescriptor()), destDir);
        return null;
    }
}
