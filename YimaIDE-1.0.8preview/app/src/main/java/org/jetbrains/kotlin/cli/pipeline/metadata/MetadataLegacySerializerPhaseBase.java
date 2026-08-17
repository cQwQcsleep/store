package org.jetbrains.kotlin.cli.pipeline.metadata;

import defpackage.f2f;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.CLIConfigurationKeysKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.cli.metadata.AbstractMetadataSerializer;
import org.jetbrains.kotlin.cli.pipeline.CheckCompilationErrors;
import org.jetbrains.kotlin.cli.pipeline.PerformanceNotifications;
import org.jetbrains.kotlin.cli.pipeline.PipelinePhase;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.LanguageVersion;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.declarations.comparators.FirMemberDeclarationComparator;
import org.jetbrains.kotlin.fir.pipeline.SingleModuleFrontendOutput;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.providers.FirProviderKt;
import org.jetbrains.kotlin.fir.serialization.FirAdditionalMetadataProvider;
import org.jetbrains.kotlin.fir.serialization.FirElementAwareSerializableStringTable;
import org.jetbrains.kotlin.fir.serialization.FirElementSerializer;
import org.jetbrains.kotlin.fir.serialization.FirSerializerExtensionBase;
import org.jetbrains.kotlin.fir.serialization.TypeApproximatorForMetadataSerializer;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.metadata.ProtoBuf;
import org.jetbrains.kotlin.metadata.deserialization.BinaryVersion;
import org.jetbrains.kotlin.metadata.deserialization.MetadataVersion;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.serialization.deserialization.builtins.BuiltInSerializerProtocol;
import org.jetbrains.kotlin.util.KlibMetadataHelpersKt;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b&\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0004\u0019\u001a\u001b\u001cB\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0002J(\u0010\n\u001a\u0004\u0018\u00010\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H$J\"\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00160\u00142\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\rH\u0004¨\u0006\u001d"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/metadata/MetadataLegacySerializerPhaseBase;", "Lorg/jetbrains/kotlin/cli/pipeline/PipelinePhase;", "Lorg/jetbrains/kotlin/cli/pipeline/metadata/MetadataFrontendPipelineArtifact;", "Lorg/jetbrains/kotlin/cli/pipeline/metadata/MetadataSerializationArtifact;", ModuleXmlParser.NAME, Argument.Delimiters.none, "<init>", "(Ljava/lang/String;)V", "executePhase", "input", "serialize", "Lorg/jetbrains/kotlin/cli/metadata/AbstractMetadataSerializer$OutputInfo;", "analysisResult", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/pipeline/SingleModuleFrontendOutput;", "destDir", "Ljava/io/File;", "metadataVersion", "Lorg/jetbrains/kotlin/metadata/deserialization/BinaryVersion;", "collectPackagesContent", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/FqName;", "Lorg/jetbrains/kotlin/cli/pipeline/metadata/MetadataLegacySerializerPhaseBase$PackageContent;", "firFiles", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "PackageContent", "PackageSerializer", "Counters", "FirLegacySerializerExtension", "org.jetbrains.kotlin:cli-metadata"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class MetadataLegacySerializerPhaseBase extends PipelinePhase<MetadataFrontendPipelineArtifact, MetadataSerializationArtifact> {

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\b\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\t¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/metadata/MetadataLegacySerializerPhaseBase$Counters;", Argument.Delimiters.none, "<init>", "()V", "totalSize", Argument.Delimiters.none, "getTotalSize", "()I", "setTotalSize", "(I)V", "totalFiles", "getTotalFiles", "setTotalFiles", "org.jetbrains.kotlin:cli-metadata"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Counters {
        private int totalFiles;
        private int totalSize;

        public final int getTotalFiles() {
            return this.totalFiles;
        }

        public final int getTotalSize() {
            return this.totalSize;
        }

        public final void setTotalFiles(int i) {
            this.totalFiles = i;
        }

        public final void setTotalSize(int i) {
            this.totalSize = i;
        }
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\b\u0010\u0014\u001a\u00020\u0015H\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0010\u001a\u0004\u0018\u00010\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/metadata/MetadataLegacySerializerPhaseBase$FirLegacySerializerExtension;", "Lorg/jetbrains/kotlin/fir/serialization/FirSerializerExtensionBase;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "metadataVersion", "Lorg/jetbrains/kotlin/metadata/deserialization/BinaryVersion;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;Lorg/jetbrains/kotlin/metadata/deserialization/BinaryVersion;)V", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "getScopeSession", "()Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "getMetadataVersion", "()Lorg/jetbrains/kotlin/metadata/deserialization/BinaryVersion;", "additionalMetadataProvider", "Lorg/jetbrains/kotlin/fir/serialization/FirAdditionalMetadataProvider;", "getAdditionalMetadataProvider", "()Lorg/jetbrains/kotlin/fir/serialization/FirAdditionalMetadataProvider;", "shouldUseTypeTable", Argument.Delimiters.none, "org.jetbrains.kotlin:cli-metadata"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class FirLegacySerializerExtension extends FirSerializerExtensionBase {
        private final BinaryVersion metadataVersion;
        private final ScopeSession scopeSession;
        private final FirSession session;

        /* JADX WARN: Illegal instructions before constructor call */
        public FirLegacySerializerExtension(FirSession firSession, ScopeSession scopeSession, BinaryVersion binaryVersion) {
            firSession.getClass();
            scopeSession.getClass();
            binaryVersion.getClass();
            LanguageFeature languageFeature = null;
            super(BuiltInSerializerProtocol.INSTANCE, languageFeature, 2, languageFeature);
            this.session = firSession;
            this.scopeSession = scopeSession;
            this.metadataVersion = binaryVersion;
        }

        @Override // org.jetbrains.kotlin.fir.serialization.FirSerializerExtension
        public FirAdditionalMetadataProvider getAdditionalMetadataProvider() {
            return null;
        }

        @Override // org.jetbrains.kotlin.fir.serialization.FirSerializerExtension
        public BinaryVersion getMetadataVersion() {
            return this.metadataVersion;
        }

        @Override // org.jetbrains.kotlin.fir.ScopeSessionHolder
        public ScopeSession getScopeSession() {
            return this.scopeSession;
        }

        @Override // org.jetbrains.kotlin.fir.SessionHolder
        public FirSession getSession() {
            return this.session;
        }

        @Override // org.jetbrains.kotlin.fir.serialization.FirSerializerExtension
        public boolean shouldUseTypeTable() {
            return true;
        }
    }

    @Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\b\u0004\u0018\u00002\u00020\u0001BS\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012¢\u0006\u0004\b\u0013\u0010\u0014J\u0006\u0010-\u001a\u00020.J\u001e\u0010/\u001a\u00020.2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u00100\u001a\u00020,H\u0002J\b\u00101\u001a\u00020.H\u0002J\b\u00102\u001a\u00020.H\u0002J\b\u00103\u001a\u00020.H\u0002J\u0010\u00104\u001a\u00020.2\u0006\u00105\u001a\u000206H\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0018R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u000e\u0010\"\u001a\u00020#X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010$\u001a\n &*\u0004\u0018\u00010%0%X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010'\u001a\u00020(¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u000e\u0010+\u001a\u00020,X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00067"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/metadata/MetadataLegacySerializerPhaseBase$PackageSerializer;", Argument.Delimiters.none, "packageFqName", "Lorg/jetbrains/kotlin/name/FqName;", "classes", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "members", "Lorg/jetbrains/kotlin/fir/declarations/FirMemberDeclaration;", "destFile", "Ljava/io/File;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "metadataVersion", "Lorg/jetbrains/kotlin/metadata/deserialization/BinaryVersion;", "counters", "Lorg/jetbrains/kotlin/cli/pipeline/metadata/MetadataLegacySerializerPhaseBase$Counters;", "<init>", "(Lorg/jetbrains/kotlin/name/FqName;Ljava/util/List;Ljava/util/List;Ljava/io/File;Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;Lorg/jetbrains/kotlin/metadata/deserialization/BinaryVersion;Lorg/jetbrains/kotlin/cli/pipeline/metadata/MetadataLegacySerializerPhaseBase$Counters;)V", "getPackageFqName", "()Lorg/jetbrains/kotlin/name/FqName;", "getClasses", "()Ljava/util/List;", "getMembers", "getDestFile", "()Ljava/io/File;", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "getScopeSession", "()Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "getCounters", "()Lorg/jetbrains/kotlin/cli/pipeline/metadata/MetadataLegacySerializerPhaseBase$Counters;", "extension", "Lorg/jetbrains/kotlin/cli/pipeline/metadata/MetadataLegacySerializerPhaseBase$FirLegacySerializerExtension;", "proto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$PackageFragment$Builder;", "kotlin.jvm.PlatformType", "typeApproximator", "Lorg/jetbrains/kotlin/fir/serialization/TypeApproximatorForMetadataSerializer;", "getTypeApproximator", "()Lorg/jetbrains/kotlin/fir/serialization/TypeApproximatorForMetadataSerializer;", "rootSerializer", "Lorg/jetbrains/kotlin/fir/serialization/FirElementSerializer;", "serialize", Argument.Delimiters.none, "serializeClasses", "parentSerializer", "serializeMembers", "serializeStringTable", "serializeBuiltInsFile", "write", "stream", "Ljava/io/ByteArrayOutputStream;", "org.jetbrains.kotlin:cli-metadata"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class PackageSerializer {
        private final List<FirRegularClass> classes;
        private final Counters counters;
        private final File destFile;
        private final FirLegacySerializerExtension extension;
        private final List<FirMemberDeclaration> members;
        private final FqName packageFqName;
        private final ProtoBuf.PackageFragment.Builder proto;
        private final FirElementSerializer rootSerializer;
        private final ScopeSession scopeSession;
        private final FirSession session;
        private final TypeApproximatorForMetadataSerializer typeApproximator;

        /* JADX WARN: Multi-variable type inference failed */
        public PackageSerializer(FqName fqName, List<? extends FirRegularClass> list, List<? extends FirMemberDeclaration> list2, File file, FirSession firSession, ScopeSession scopeSession, BinaryVersion binaryVersion, Counters counters) {
            fqName.getClass();
            list.getClass();
            list2.getClass();
            file.getClass();
            firSession.getClass();
            scopeSession.getClass();
            binaryVersion.getClass();
            counters.getClass();
            this.packageFqName = fqName;
            this.classes = list;
            this.members = list2;
            this.destFile = file;
            this.session = firSession;
            this.scopeSession = scopeSession;
            this.counters = counters;
            FirLegacySerializerExtension firLegacySerializerExtension = new FirLegacySerializerExtension(firSession, scopeSession, binaryVersion);
            this.extension = firLegacySerializerExtension;
            this.proto = ProtoBuf.PackageFragment.newBuilder();
            TypeApproximatorForMetadataSerializer typeApproximatorForMetadataSerializer = new TypeApproximatorForMetadataSerializer(firSession);
            this.typeApproximator = typeApproximatorForMetadataSerializer;
            this.rootSerializer = FirElementSerializer.Companion.createTopLevel$default(FirElementSerializer.INSTANCE, firSession, scopeSession, firLegacySerializerExtension, typeApproximatorForMetadataSerializer, FirLanguageSettingsComponentKt.getLanguageVersionSettings(firSession), false, 32, null);
        }

        private final void serializeBuiltInsFile() throws IOException {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            int[] array = this.extension.getMetadataVersion().toArray();
            dataOutputStream.writeInt(array.length);
            for (int i : array) {
                dataOutputStream.writeInt(i);
            }
            this.proto.build().writeTo(byteArrayOutputStream);
            write(byteArrayOutputStream);
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
        /* JADX WARN: Multi-variable type inference failed */
        private final void serializeClasses(List<? extends FirRegularClass> classes, FirElementSerializer parentSerializer) throws KotlinIllegalArgumentExceptionWithAttachments {
            for (FirRegularClass firRegularClass : CollectionsKt.sortedWith(classes, FirMemberDeclarationComparator.INSTANCE)) {
                FirElementSerializer.Companion companion = FirElementSerializer.INSTANCE;
                FirSession firSession = this.session;
                FirElementSerializer firElementSerializer = parentSerializer;
                FirElementSerializer firElementSerializerCreate = companion.create(firSession, this.scopeSession, firRegularClass, this.extension, firElementSerializer, this.typeApproximator, FirLanguageSettingsComponentKt.getLanguageVersionSettings(firSession), (128 & 128) != 0 ? false : false);
                List<FirClassifierSymbol<?>> listComputeNestedClassifiersForClass = firElementSerializerCreate.computeNestedClassifiersForClass(firRegularClass.getSymbol());
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listComputeNestedClassifiersForClass, 10));
                Iterator<T> it = listComputeNestedClassifiersForClass.iterator();
                while (it.hasNext()) {
                    E fir = ((FirClassifierSymbol) it.next()).getFir();
                    fir.getClass();
                    arrayList.add((FirRegularClass) fir);
                }
                if (!arrayList.isEmpty()) {
                    serializeClasses(arrayList, firElementSerializerCreate);
                }
                this.proto.addClass_(firElementSerializerCreate.classProto(firRegularClass, FirProviderKt.getFirProvider(this.session).getFirClassifierContainerFileIfAny(firRegularClass.getSymbol())).build());
                parentSerializer = firElementSerializer;
            }
        }

        private final void serializeMembers() {
            this.proto.setPackage(this.rootSerializer.packagePartProto(this.packageFqName, CollectionsKt.sortedWith(this.members, FirMemberDeclarationComparator.INSTANCE), null).build());
        }

        private final void serializeStringTable() {
            Pair pairBuildProto;
            FirElementAwareSerializableStringTable stringTable = this.extension.getStringTable();
            if (stringTable == null) {
                stringTable = null;
            }
            if (stringTable == null || (pairBuildProto = stringTable.buildProto()) == null) {
                return;
            }
            ProtoBuf.StringTable stringTable2 = (ProtoBuf.StringTable) pairBuildProto.component1();
            ProtoBuf.QualifiedNameTable qualifiedNameTable = (ProtoBuf.QualifiedNameTable) pairBuildProto.component2();
            this.proto.setStrings(stringTable2);
            this.proto.setQualifiedNames(qualifiedNameTable);
        }

        private final void write(ByteArrayOutputStream stream) {
            Counters counters = this.counters;
            counters.setTotalSize(counters.getTotalSize() + stream.size());
            Counters counters2 = this.counters;
            counters2.setTotalFiles(counters2.getTotalFiles() + 1);
            this.destFile.isDirectory();
            this.destFile.getParentFile().mkdirs();
            File file = this.destFile;
            byte[] byteArray = stream.toByteArray();
            byteArray.getClass();
            FilesKt.writeBytes(file, byteArray);
        }

        public final List<FirRegularClass> getClasses() {
            return this.classes;
        }

        public final Counters getCounters() {
            return this.counters;
        }

        public final File getDestFile() {
            return this.destFile;
        }

        public final List<FirMemberDeclaration> getMembers() {
            return this.members;
        }

        public final FqName getPackageFqName() {
            return this.packageFqName;
        }

        public final ScopeSession getScopeSession() {
            return this.scopeSession;
        }

        public final FirSession getSession() {
            return this.session;
        }

        public final TypeApproximatorForMetadataSerializer getTypeApproximator() {
            return this.typeApproximator;
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
        public final void serialize() throws IOException, KotlinIllegalArgumentExceptionWithAttachments {
            serializeClasses(this.classes, this.rootSerializer);
            serializeMembers();
            serializeStringTable();
            serializeBuiltInsFile();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MetadataLegacySerializerPhaseBase(String str) {
        super(str, SetsKt.setOf(PerformanceNotifications.BackendStarted.INSTANCE), SetsKt.setOf(new Function3[]{PerformanceNotifications.BackendFinished.INSTANCE, CheckCompilationErrors.CheckDiagnosticCollector.INSTANCE}));
        str.getClass();
    }

    public final Map<FqName, PackageContent> collectPackagesContent(List<? extends FirFile> firFiles) {
        Object obj;
        firFiles.getClass();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (FirFile firFile : firFiles) {
            FqName packageFqName = UtilsKt.getPackageFqName(firFile);
            for (FirDeclaration firDeclaration : firFile.getDeclarations()) {
                Object obj2 = linkedHashMap.get(packageFqName);
                List list = null;
                boolean z = false;
                boolean z2 = false;
                if (obj2 == null) {
                    obj = obj2;
                    PackageContent packageContent = new PackageContent(list, z2 ? 1 : 0, 3, z ? 1 : 0);
                    linkedHashMap.put(packageFqName, packageContent);
                    obj = packageContent;
                }
                obj = obj2;
                PackageContent packageContent2 = (PackageContent) obj;
                if ((firDeclaration instanceof FirCallableDeclaration) || (firDeclaration instanceof FirTypeAlias)) {
                    Map<FirFile, List<FirMemberDeclaration>> membersPerFile = packageContent2.getMembersPerFile();
                    List<FirMemberDeclaration> arrayList = membersPerFile.get(firFile);
                    if (arrayList == null) {
                        arrayList = new ArrayList<>();
                        membersPerFile.put(firFile, arrayList);
                    }
                    arrayList.add(firDeclaration);
                } else {
                    if (!(firDeclaration instanceof FirRegularClass)) {
                        f2f.a("Unexpected declaration: ", UtilsKt.render(firDeclaration));
                        return null;
                    }
                    packageContent2.getClasses().add(firDeclaration);
                }
            }
        }
        return linkedHashMap;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.cli.pipeline.PipelinePhase
    public final MetadataSerializationArtifact executePhase(MetadataFrontendPipelineArtifact input) throws IOException {
        input.getClass();
        List<? extends SingleModuleFrontendOutput> listM38component1QYgrGdg = input.m38component1QYgrGdg();
        CompilerConfiguration configuration = input.getConfiguration();
        MetadataVersion metadataVersionKlibMetadataVersionOrDefault$default = KlibMetadataHelpersKt.klibMetadataVersionOrDefault$default(configuration, (LanguageVersion) null, 1, (Object) null);
        File metadataDestinationDirectory = CLIConfigurationKeysKt.getMetadataDestinationDirectory(configuration);
        metadataDestinationDirectory.getClass();
        AbstractMetadataSerializer.OutputInfo outputInfoSerialize = serialize(listM38component1QYgrGdg, metadataDestinationDirectory, metadataVersionKlibMetadataVersionOrDefault$default);
        String canonicalPath = metadataDestinationDirectory.getCanonicalPath();
        canonicalPath.getClass();
        return new MetadataSerializationArtifact(outputInfoSerialize, configuration, canonicalPath);
    }

    public abstract AbstractMetadataSerializer.OutputInfo serialize(List<SingleModuleFrontendOutput> analysisResult, File destDir, BinaryVersion metadataVersion);

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0084\b\u0018\u00002\u00020\u0001B3\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u001a\b\u0002\u0010\u0005\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00030\u0006¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u001b\u0010\u0010\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00030\u0006HÆ\u0003J5\u0010\u0011\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u001a\b\u0002\u0010\u0005\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00030\u0006HÆ\u0001J\u0014\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J\n\u0010\u0017\u001a\u00020\u0018HÖ\u0081\u0004R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR#\u0010\u0005\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00030\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/metadata/MetadataLegacySerializerPhaseBase$PackageContent;", Argument.Delimiters.none, "classes", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "membersPerFile", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "Lorg/jetbrains/kotlin/fir/declarations/FirMemberDeclaration;", "<init>", "(Ljava/util/List;Ljava/util/Map;)V", "getClasses", "()Ljava/util/List;", "getMembersPerFile", "()Ljava/util/Map;", "component1", "component2", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:cli-metadata"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class PackageContent {
        private final List<FirRegularClass> classes;
        private final Map<FirFile, List<FirMemberDeclaration>> membersPerFile;

        public /* synthetic */ PackageContent(List list, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? new ArrayList() : list, (i & 2) != 0 ? new LinkedHashMap() : map);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ PackageContent copy$default(PackageContent packageContent, List list, Map map, int i, Object obj) {
            if ((i & 1) != 0) {
                list = packageContent.classes;
            }
            if ((i & 2) != 0) {
                map = packageContent.membersPerFile;
            }
            return packageContent.copy(list, map);
        }

        public final List<FirRegularClass> component1() {
            return this.classes;
        }

        public final Map<FirFile, List<FirMemberDeclaration>> component2() {
            return this.membersPerFile;
        }

        public final PackageContent copy(List<FirRegularClass> classes, Map<FirFile, List<FirMemberDeclaration>> membersPerFile) {
            classes.getClass();
            membersPerFile.getClass();
            return new PackageContent(classes, membersPerFile);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof PackageContent)) {
                return false;
            }
            PackageContent packageContent = (PackageContent) other;
            return Intrinsics.areEqual(this.classes, packageContent.classes) && Intrinsics.areEqual(this.membersPerFile, packageContent.membersPerFile);
        }

        public final List<FirRegularClass> getClasses() {
            return this.classes;
        }

        public final Map<FirFile, List<FirMemberDeclaration>> getMembersPerFile() {
            return this.membersPerFile;
        }

        public int hashCode() {
            return (this.classes.hashCode() * 31) + this.membersPerFile.hashCode();
        }

        public String toString() {
            return "PackageContent(classes=" + this.classes + ", membersPerFile=" + this.membersPerFile + ')';
        }

        public PackageContent(List<FirRegularClass> list, Map<FirFile, List<FirMemberDeclaration>> map) {
            list.getClass();
            map.getClass();
            this.classes = list;
            this.membersPerFile = map;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public PackageContent() {
            this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
        }
    }
}
