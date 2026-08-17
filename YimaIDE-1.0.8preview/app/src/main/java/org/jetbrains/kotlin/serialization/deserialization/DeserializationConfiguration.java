package org.jetbrains.kotlin.serialization.deserialization;

import kotlin.Metadata;
import org.jetbrains.kotlin.metadata.deserialization.MetadataVersion;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0011\bf\u0018\u00002\u00020\u0001:\u0001\u0017R\u0014\u0010\u0002\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0006\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\tR\u0014\u0010\f\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\tR\u0014\u0010\u000e\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\tR\u0014\u0010\u0010\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\tR\u0014\u0010\u0012\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\tR\u0014\u0010\u0013\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\tR\u0014\u0010\u0015\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\tø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0018À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/serialization/deserialization/DeserializationConfiguration;", "", "metadataVersion", "Lorg/jetbrains/kotlin/metadata/deserialization/MetadataVersion;", "getMetadataVersion", "()Lorg/jetbrains/kotlin/metadata/deserialization/MetadataVersion;", "skipMetadataVersionCheck", "", "getSkipMetadataVersionCheck", "()Z", "skipPrereleaseCheck", "getSkipPrereleaseCheck", "reportErrorsOnPreReleaseDependencies", "getReportErrorsOnPreReleaseDependencies", "allowUnstableDependencies", "getAllowUnstableDependencies", "typeAliasesAllowed", "getTypeAliasesAllowed", "isJvmPackageNameSupported", "readDeserializedContracts", "getReadDeserializedContracts", "preserveDeclarationsOrdering", "getPreserveDeclarationsOrdering", "Default", "org.jetbrains.kotlin:deserialization"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public interface DeserializationConfiguration {

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/serialization/deserialization/DeserializationConfiguration$Default;", "Lorg/jetbrains/kotlin/serialization/deserialization/DeserializationConfiguration;", "<init>", "()V", "org.jetbrains.kotlin:deserialization"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
    public static final class Default implements DeserializationConfiguration {
        public static final Default INSTANCE = new Default();

        private Default() {
        }
    }

    default boolean getAllowUnstableDependencies() {
        return false;
    }

    default MetadataVersion getMetadataVersion() {
        return MetadataVersion.INSTANCE;
    }

    default boolean getPreserveDeclarationsOrdering() {
        return false;
    }

    default boolean getReadDeserializedContracts() {
        return false;
    }

    default boolean getReportErrorsOnPreReleaseDependencies() {
        return false;
    }

    default boolean getSkipMetadataVersionCheck() {
        return false;
    }

    default boolean getSkipPrereleaseCheck() {
        return false;
    }

    default boolean getTypeAliasesAllowed() {
        return true;
    }

    default boolean isJvmPackageNameSupported() {
        return true;
    }
}
