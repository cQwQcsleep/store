package org.jetbrains.kotlin.fir.pipeline;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import org.jetbrains.kotlin.KtSourceFile;
import org.jetbrains.kotlin.backend.common.actualizer.IrActualizedResult;
import org.jetbrains.kotlin.backend.common.serialization.SerializeModuleIntoKlibKt;
import org.jetbrains.kotlin.backend.common.serialization.metadata.FileVisitor;
import org.jetbrains.kotlin.backend.common.serialization.metadata.KlibSingleFileMetadataSerializer;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.CommonConfigurationKeysKt;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.LanguageVersion;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.backend.Fir2IrComponents;
import org.jetbrains.kotlin.fir.backend.Fir2IrIrGeneratedDeclarationsRegistrar;
import org.jetbrains.kotlin.fir.backend.utils.VariousUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.pipeline.Fir2KlibMetadataSerializer;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.providers.FirProvider;
import org.jetbrains.kotlin.fir.resolve.providers.FirProviderKt;
import org.jetbrains.kotlin.fir.serialization.FirAdditionalMetadataProvider;
import org.jetbrains.kotlin.fir.serialization.FirKLibSerializerExtension;
import org.jetbrains.kotlin.fir.serialization.FirKlibSerializationKt;
import org.jetbrains.kotlin.metadata.ProtoBuf;
import org.jetbrains.kotlin.metadata.deserialization.MetadataVersion;
import org.jetbrains.kotlin.util.KlibMetadataHelpersKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B7\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\r\u0010\u000eB1\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\r\u0010\u000fJ\u0010\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u0002H\u0016J\u0016\u0010'\u001a\u00020(2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00020*H\u0016R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010\u0010\u001a\u001a\u0012\u0004\u0012\u00020\u0002\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00140\u00120\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R#\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u00168BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u0018\u0010\u0019R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u0004¢\u0006\u0002\n\u0000R!\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\u00168VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b#\u0010\u001b\u001a\u0004\b\"\u0010\u0019R\u0018\u0010+\u001a\u00020!*\u00020\u00028BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b,\u0010-¨\u0006."}, d2 = {"Lorg/jetbrains/kotlin/fir/pipeline/Fir2KlibMetadataSerializer;", "Lorg/jetbrains/kotlin/backend/common/serialization/metadata/KlibSingleFileMetadataSerializer;", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "compilerConfiguration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "firOutputs", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/pipeline/SingleModuleFrontendOutput;", "fir2IrActualizedResult", "Lorg/jetbrains/kotlin/fir/pipeline/Fir2IrActualizedResult;", "exportKDoc", Argument.Delimiters.none, "produceHeaderKlib", "<init>", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Ljava/util/List;Lorg/jetbrains/kotlin/fir/pipeline/Fir2IrActualizedResult;ZZ)V", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Ljava/util/List;Lorg/jetbrains/kotlin/fir/pipeline/Fir2IrActualizedResult;Z)V", "firFilesAndSessions", Argument.Delimiters.none, "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/fir/FirSession;", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "actualizedExpectDeclarations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "getActualizedExpectDeclarations", "()Ljava/util/Set;", "actualizedExpectDeclarations$delegate", "Lkotlin/Lazy;", "languageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "metadataVersion", "Lorg/jetbrains/kotlin/metadata/deserialization/MetadataVersion;", "sourceFiles", "Ljava/io/File;", "getSourceFiles", "sourceFiles$delegate", "serializeSingleFileMetadata", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$PackageFragment;", "file", "forEachFile", Argument.Delimiters.none, "block", "Lorg/jetbrains/kotlin/backend/common/serialization/metadata/FileVisitor;", "ioFile", "getIoFile", "(Lorg/jetbrains/kotlin/fir/declarations/FirFile;)Ljava/io/File;", "org.jetbrains.kotlin:entrypoint"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class Fir2KlibMetadataSerializer implements KlibSingleFileMetadataSerializer<FirFile> {

    /* JADX INFO: renamed from: actualizedExpectDeclarations$delegate, reason: from kotlin metadata */
    private final Lazy actualizedExpectDeclarations;
    private final boolean exportKDoc;
    private final Fir2IrActualizedResult fir2IrActualizedResult;
    private final Map<FirFile, Pair<FirSession, ScopeSession>> firFilesAndSessions;
    private final List<SingleModuleFrontendOutput> firOutputs;
    private final LanguageVersionSettings languageVersionSettings;
    private final MetadataVersion metadataVersion;
    private final boolean produceHeaderKlib;

    /* JADX INFO: renamed from: sourceFiles$delegate, reason: from kotlin metadata */
    private final Lazy sourceFiles;

    public Fir2KlibMetadataSerializer(CompilerConfiguration compilerConfiguration, List<SingleModuleFrontendOutput> list, Fir2IrActualizedResult fir2IrActualizedResult, boolean z, boolean z2) {
        compilerConfiguration.getClass();
        list.getClass();
        this.firOutputs = list;
        this.fir2IrActualizedResult = fir2IrActualizedResult;
        this.exportKDoc = z;
        this.produceHeaderKlib = z2;
        Map mapCreateMapBuilder = MapsKt.createMapBuilder();
        for (SingleModuleFrontendOutput singleModuleFrontendOutput : CollectionsKt.reversed(list)) {
            Iterator<FirFile> it = singleModuleFrontendOutput.getFir().iterator();
            while (it.hasNext()) {
                mapCreateMapBuilder.put(it.next(), TuplesKt.to(singleModuleFrontendOutput.getSession(), singleModuleFrontendOutput.getScopeSession()));
            }
        }
        this.firFilesAndSessions = MapsKt.build(mapCreateMapBuilder);
        this.actualizedExpectDeclarations = LazyKt.lazy(new Function0() { // from class: zw4
            public final Object invoke() {
                return Fir2KlibMetadataSerializer.a(this.b);
            }
        });
        this.languageVersionSettings = CommonConfigurationKeysKt.getLanguageVersionSettings(compilerConfiguration);
        this.metadataVersion = KlibMetadataHelpersKt.klibMetadataVersionOrDefault$default(compilerConfiguration, (LanguageVersion) null, 1, (Object) null);
        this.sourceFiles = LazyKt.lazy(LazyThreadSafetyMode.NONE, new Function0() { // from class: ax4
            public final Object invoke() {
                return Fir2KlibMetadataSerializer.b(this.b);
            }
        });
    }

    public static Set a(Fir2KlibMetadataSerializer fir2KlibMetadataSerializer) {
        IrActualizedResult irActualizedResult;
        List actualizedExpectDeclarations;
        Fir2IrActualizedResult fir2IrActualizedResult = fir2KlibMetadataSerializer.fir2IrActualizedResult;
        if (fir2IrActualizedResult == null || (irActualizedResult = fir2IrActualizedResult.getIrActualizedResult()) == null || (actualizedExpectDeclarations = irActualizedResult.getActualizedExpectDeclarations()) == null) {
            return null;
        }
        return VariousUtilsKt.extractFirDeclarations(actualizedExpectDeclarations);
    }

    public static Set b(Fir2KlibMetadataSerializer fir2KlibMetadataSerializer) {
        Set<FirFile> setKeySet = fir2KlibMetadataSerializer.firFilesAndSessions.keySet();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator<T> it = setKeySet.iterator();
        while (it.hasNext()) {
            linkedHashSet.add(fir2KlibMetadataSerializer.getIoFile((FirFile) it.next()));
        }
        return linkedHashSet;
    }

    private final Set<FirDeclaration> getActualizedExpectDeclarations() {
        return (Set) this.actualizedExpectDeclarations.getValue();
    }

    private final File getIoFile(FirFile firFile) {
        File ioFileOrNull;
        KtSourceFile sourceFile = firFile.getSourceFile();
        return (sourceFile == null || (ioFileOrNull = SerializeModuleIntoKlibKt.toIoFileOrNull(sourceFile)) == null) ? new File(firFile.getName()) : ioFileOrNull;
    }

    public void forEachFile(FileVisitor<FirFile> block) {
        block.getClass();
        int i = 0;
        for (Object obj : this.firFilesAndSessions.keySet()) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            FirFile firFile = (FirFile) obj;
            block.visit(i, getIoFile(firFile), firFile, firFile.getSourceFile(), UtilsKt.getPackageFqName(firFile));
            i = i2;
        }
    }

    public Set<File> getSourceFiles() {
        return (Set) this.sourceFiles.getValue();
    }

    public ProtoBuf.PackageFragment serializeSingleFileMetadata(FirFile file) {
        FirProvider firProvider;
        ScopeSession scopeSession;
        FirSession firSession;
        Fir2IrIrGeneratedDeclarationsRegistrar annotationsFromPluginRegistrar;
        file.getClass();
        Fir2IrActualizedResult fir2IrActualizedResult = this.fir2IrActualizedResult;
        FirAdditionalMetadataProvider firAdditionalMetadataProviderCreateAdditionalMetadataProvider = null;
        Fir2IrComponents components = fir2IrActualizedResult != null ? fir2IrActualizedResult.getComponents() : null;
        if (components != null) {
            FirSession session = components.getSession();
            ScopeSession scopeSession2 = components.getScopeSession();
            firProvider = components.getFirProvider();
            firSession = session;
            scopeSession = scopeSession2;
        } else {
            Pair<FirSession, ScopeSession> pair = this.firFilesAndSessions.get(file);
            if (pair == null) {
                k2d.a("Missing FirSession and ScopeSession");
                return null;
            }
            FirSession firSession2 = (FirSession) pair.getFirst();
            ScopeSession scopeSession3 = (ScopeSession) pair.getSecond();
            firProvider = FirProviderKt.getFirProvider(firSession2);
            scopeSession = scopeSession3;
            firSession = firSession2;
        }
        FirProvider firProvider2 = firProvider;
        Set<FirDeclaration> actualizedExpectDeclarations = getActualizedExpectDeclarations();
        MetadataVersion metadataVersion = this.metadataVersion;
        boolean z = this.exportKDoc;
        if (components != null && (annotationsFromPluginRegistrar = components.getAnnotationsFromPluginRegistrar()) != null) {
            firAdditionalMetadataProviderCreateAdditionalMetadataProvider = annotationsFromPluginRegistrar.createAdditionalMetadataProvider();
        }
        return FirKlibSerializationKt.serializeSingleFirFile(file, firSession, scopeSession, actualizedExpectDeclarations, new FirKLibSerializerExtension(firSession, scopeSession, firProvider2, metadataVersion, z, firAdditionalMetadataProviderCreateAdditionalMetadataProvider), this.languageVersionSettings, this.produceHeaderKlib);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public Fir2KlibMetadataSerializer(CompilerConfiguration compilerConfiguration, List<SingleModuleFrontendOutput> list, Fir2IrActualizedResult fir2IrActualizedResult, boolean z) {
        this(compilerConfiguration, list, fir2IrActualizedResult, CommonConfigurationKeysKt.getLanguageVersionSettings(compilerConfiguration).supportsFeature(LanguageFeature.ExportKDocDocumentationToKlib), z);
        compilerConfiguration.getClass();
        list.getClass();
    }
}
