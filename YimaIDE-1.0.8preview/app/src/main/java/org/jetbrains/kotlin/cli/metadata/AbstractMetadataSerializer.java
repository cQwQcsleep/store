package org.jetbrains.kotlin.cli.metadata;

import java.io.File;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.K1DeprecationKt;
import org.jetbrains.kotlin.cli.CliDiagnosticReportingKt;
import org.jetbrains.kotlin.cli.CliDiagnostics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.jvm.compiler.KotlinCoreEnvironment;
import org.jetbrains.kotlin.config.CommonConfigurationKeys;
import org.jetbrains.kotlin.config.CommonConfigurationKeysKt;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.metadata.builtins.BuiltInsBinaryVersion;
import org.jetbrains.kotlin.util.PerformanceManager;
import org.jetbrains.kotlin.util.PhaseType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0001\u001bB#\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\t\u0010\nJ\n\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0007J\u000f\u0010\u0014\u001a\u0004\u0018\u00018\u0000H$¢\u0006\u0002\u0010\u0015J\u001f\u0010\u0016\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0017\u001a\u00028\u00002\u0006\u0010\u0018\u001a\u00020\u0019H$¢\u0006\u0002\u0010\u001aR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\bX\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001c"}, d2 = {"Lorg/jetbrains/kotlin/cli/metadata/AbstractMetadataSerializer;", "T", Argument.Delimiters.none, "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "environment", "Lorg/jetbrains/kotlin/cli/jvm/compiler/KotlinCoreEnvironment;", "definedMetadataVersion", "Lorg/jetbrains/kotlin/metadata/builtins/BuiltInsBinaryVersion;", "<init>", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;Lorg/jetbrains/kotlin/cli/jvm/compiler/KotlinCoreEnvironment;Lorg/jetbrains/kotlin/metadata/builtins/BuiltInsBinaryVersion;)V", "getConfiguration", "()Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "getEnvironment", "()Lorg/jetbrains/kotlin/cli/jvm/compiler/KotlinCoreEnvironment;", "metadataVersion", "getMetadataVersion", "()Lorg/jetbrains/kotlin/metadata/builtins/BuiltInsBinaryVersion;", "analyzeAndSerialize", "Lorg/jetbrains/kotlin/cli/metadata/AbstractMetadataSerializer$OutputInfo;", "analyze", "()Ljava/lang/Object;", "serialize", "analysisResult", "destDir", "Ljava/io/File;", "(Ljava/lang/Object;Ljava/io/File;)Lorg/jetbrains/kotlin/cli/metadata/AbstractMetadataSerializer$OutputInfo;", "OutputInfo", "org.jetbrains.kotlin:cli-metadata"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class AbstractMetadataSerializer<T> {
    private final CompilerConfiguration configuration;
    private final KotlinCoreEnvironment environment;
    private final BuiltInsBinaryVersion metadataVersion;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0010\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0011\u001a\u00020\u0012HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/cli/metadata/AbstractMetadataSerializer$OutputInfo;", Argument.Delimiters.none, "totalSize", Argument.Delimiters.none, "totalFiles", "<init>", "(II)V", "getTotalSize", "()I", "getTotalFiles", "component1", "component2", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:cli-metadata"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class OutputInfo {
        private final int totalFiles;
        private final int totalSize;

        public OutputInfo(int i, int i2) {
            this.totalSize = i;
            this.totalFiles = i2;
        }

        public static /* synthetic */ OutputInfo copy$default(OutputInfo outputInfo, int i, int i2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                i = outputInfo.totalSize;
            }
            if ((i3 & 2) != 0) {
                i2 = outputInfo.totalFiles;
            }
            return outputInfo.copy(i, i2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getTotalSize() {
            return this.totalSize;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getTotalFiles() {
            return this.totalFiles;
        }

        public final OutputInfo copy(int totalSize, int totalFiles) {
            return new OutputInfo(totalSize, totalFiles);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof OutputInfo)) {
                return false;
            }
            OutputInfo outputInfo = (OutputInfo) other;
            return this.totalSize == outputInfo.totalSize && this.totalFiles == outputInfo.totalFiles;
        }

        public final int getTotalFiles() {
            return this.totalFiles;
        }

        public final int getTotalSize() {
            return this.totalSize;
        }

        public int hashCode() {
            return (Integer.hashCode(this.totalSize) * 31) + Integer.hashCode(this.totalFiles);
        }

        public String toString() {
            return "OutputInfo(totalSize=" + this.totalSize + ", totalFiles=" + this.totalFiles + ')';
        }
    }

    public AbstractMetadataSerializer(CompilerConfiguration compilerConfiguration, KotlinCoreEnvironment kotlinCoreEnvironment, BuiltInsBinaryVersion builtInsBinaryVersion) {
        compilerConfiguration.getClass();
        kotlinCoreEnvironment.getClass();
        this.configuration = compilerConfiguration;
        this.environment = kotlinCoreEnvironment;
        if (builtInsBinaryVersion == null) {
            Object obj = compilerConfiguration.get(CommonConfigurationKeys.METADATA_VERSION);
            builtInsBinaryVersion = obj instanceof BuiltInsBinaryVersion ? (BuiltInsBinaryVersion) obj : null;
            if (builtInsBinaryVersion == null) {
                builtInsBinaryVersion = BuiltInsBinaryVersion.INSTANCE;
            }
        }
        this.metadataVersion = builtInsBinaryVersion;
    }

    public abstract T analyze();

    @Deprecated(level = DeprecationLevel.ERROR, message = K1DeprecationKt.K1_DEPRECATION_WARNING)
    public final OutputInfo analyzeAndSerialize() {
        File destDir = CommonAnalysisKt.getDestDir(this.environment);
        if (destDir == null) {
            CliDiagnosticReportingKt.report$default(this.environment.getConfiguration(), CliDiagnostics.INSTANCE.getCOMPILER_ARGUMENTS_ERROR(), "Specify destination via -d", null, 4, null);
            return null;
        }
        T tAnalyze = analyze();
        if (tAnalyze == null) {
            return null;
        }
        PerformanceManager perfManager = CommonConfigurationKeysKt.getPerfManager(this.environment.getConfiguration());
        PhaseType phaseType = PhaseType.Backend;
        if (perfManager == null) {
            return serialize(tAnalyze, destDir);
        }
        try {
            perfManager.notifyPhaseStarted(phaseType);
            return serialize(tAnalyze, destDir);
        } finally {
            perfManager.notifyPhaseFinished(phaseType);
        }
    }

    public final CompilerConfiguration getConfiguration() {
        return this.configuration;
    }

    public final KotlinCoreEnvironment getEnvironment() {
        return this.environment;
    }

    public final BuiltInsBinaryVersion getMetadataVersion() {
        return this.metadataVersion;
    }

    public abstract OutputInfo serialize(T analysisResult, File destDir);

    public /* synthetic */ AbstractMetadataSerializer(CompilerConfiguration compilerConfiguration, KotlinCoreEnvironment kotlinCoreEnvironment, BuiltInsBinaryVersion builtInsBinaryVersion, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(compilerConfiguration, kotlinCoreEnvironment, (i & 4) != 0 ? null : builtInsBinaryVersion);
    }
}
