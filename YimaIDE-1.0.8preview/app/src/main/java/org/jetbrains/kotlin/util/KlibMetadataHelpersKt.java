package org.jetbrains.kotlin.util;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.CommonConfigurationKeysKt;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.KlibAbiCompatibilityLevel;
import org.jetbrains.kotlin.config.LanguageVersion;
import org.jetbrains.kotlin.library.abi.AbiCompoundName;
import org.jetbrains.kotlin.metadata.deserialization.BinaryVersion;
import org.jetbrains.kotlin.metadata.deserialization.MetadataVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0002\u001a\u00020\u0001*\u00020\u0003\u001a\u0014\u0010\u0004\u001a\u00020\u0001*\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u001a\n\u0010\u0007\u001a\u00020\u0001*\u00020\b\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"KLIB_LEGACY_METADATA_VERSION", "Lorg/jetbrains/kotlin/metadata/deserialization/MetadataVersion;", "toKlibMetadataVersion", "Lorg/jetbrains/kotlin/config/LanguageVersion;", "klibMetadataVersionOrDefault", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "languageVersion", "toCInteropKlibMetadataVersion", "Lorg/jetbrains/kotlin/config/KlibAbiCompatibilityLevel;", "org.jetbrains.kotlin:frontend.common"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class KlibMetadataHelpersKt {
    private static final MetadataVersion KLIB_LEGACY_METADATA_VERSION = new MetadataVersion(1, 4, 1);

    public static final MetadataVersion klibMetadataVersionOrDefault(CompilerConfiguration compilerConfiguration, LanguageVersion languageVersion) {
        compilerConfiguration.getClass();
        languageVersion.getClass();
        BinaryVersion metadataVersion = CommonConfigurationKeysKt.getMetadataVersion(compilerConfiguration);
        MetadataVersion metadataVersion2 = metadataVersion instanceof MetadataVersion ? (MetadataVersion) metadataVersion : null;
        return metadataVersion2 == null ? toKlibMetadataVersion(languageVersion) : metadataVersion2;
    }

    public static /* synthetic */ MetadataVersion klibMetadataVersionOrDefault$default(CompilerConfiguration compilerConfiguration, LanguageVersion languageVersion, int i, Object obj) {
        if ((i & 1) != 0) {
            languageVersion = CommonConfigurationKeysKt.getLanguageVersionSettings(compilerConfiguration).getLanguageVersion();
        }
        return klibMetadataVersionOrDefault(compilerConfiguration, languageVersion);
    }

    public static final MetadataVersion toCInteropKlibMetadataVersion(KlibAbiCompatibilityLevel klibAbiCompatibilityLevel) {
        MetadataVersion klibMetadataVersion;
        klibAbiCompatibilityLevel.getClass();
        LanguageVersion.Companion companion = LanguageVersion.Companion;
        StringBuilder sb = new StringBuilder();
        sb.append(klibAbiCompatibilityLevel.getMajor());
        sb.append(AbiCompoundName.SEPARATOR);
        sb.append(klibAbiCompatibilityLevel.getMinor());
        LanguageVersion languageVersionFromVersionString = companion.fromVersionString(sb.toString());
        if (languageVersionFromVersionString != null && (klibMetadataVersion = toKlibMetadataVersion(languageVersionFromVersionString)) != null) {
            return klibMetadataVersion;
        }
        x04.a("Cannot convert ", klibAbiCompatibilityLevel, " to MetadataVersion");
        return null;
    }

    public static final MetadataVersion toKlibMetadataVersion(LanguageVersion languageVersion) {
        languageVersion.getClass();
        return languageVersion.compareTo(LanguageVersion.KOTLIN_2_3) < 0 ? KLIB_LEGACY_METADATA_VERSION : MetadataHelpersKt.toJvmMetadataVersion(languageVersion);
    }
}
