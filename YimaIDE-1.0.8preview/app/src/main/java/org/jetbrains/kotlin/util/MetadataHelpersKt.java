package org.jetbrains.kotlin.util;

import java.util.EnumMap;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import org.jetbrains.kotlin.config.CommonConfigurationKeys;
import org.jetbrains.kotlin.config.CommonConfigurationKeysKt;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.LanguageVersion;
import org.jetbrains.kotlin.metadata.deserialization.BinaryVersion;
import org.jetbrains.kotlin.metadata.deserialization.MetadataVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\n\u0010\u0004\u001a\u00020\u0003*\u00020\u0002\u001a\u0014\u0010\u0005\u001a\u00020\u0006*\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0002\"\u001a\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"LANGUAGE_TO_JVM_METADATA_VERSION", "Ljava/util/EnumMap;", "Lorg/jetbrains/kotlin/config/LanguageVersion;", "Lorg/jetbrains/kotlin/metadata/deserialization/MetadataVersion;", "toJvmMetadataVersion", "jvmMetadataVersion", "Lorg/jetbrains/kotlin/metadata/deserialization/BinaryVersion;", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "languageVersion", "org.jetbrains.kotlin:frontend.common"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class MetadataHelpersKt {
    private static final EnumMap<LanguageVersion, MetadataVersion> LANGUAGE_TO_JVM_METADATA_VERSION;

    static {
        EnumMap<LanguageVersion, MetadataVersion> enumMap = new EnumMap<>(LanguageVersion.class);
        MetadataVersion metadataVersion = new MetadataVersion(1, 1, 18);
        enumMap.put(LanguageVersion.KOTLIN_1_0, metadataVersion);
        enumMap.put(LanguageVersion.KOTLIN_1_1, metadataVersion);
        enumMap.put(LanguageVersion.KOTLIN_1_2, metadataVersion);
        enumMap.put(LanguageVersion.KOTLIN_1_3, metadataVersion);
        enumMap.put(LanguageVersion.KOTLIN_1_4, new MetadataVersion(1, 4, 3));
        enumMap.put(LanguageVersion.KOTLIN_1_5, new MetadataVersion(1, 5, 1));
        enumMap.put(LanguageVersion.KOTLIN_1_6, new MetadataVersion(1, 6, 0));
        enumMap.put(LanguageVersion.KOTLIN_1_7, new MetadataVersion(1, 7, 0));
        enumMap.put(LanguageVersion.KOTLIN_1_8, new MetadataVersion(1, 8, 0));
        enumMap.put(LanguageVersion.KOTLIN_1_9, new MetadataVersion(1, 9, 0));
        enumMap.put(LanguageVersion.KOTLIN_2_0, new MetadataVersion(2, 0, 0));
        enumMap.put(LanguageVersion.KOTLIN_2_1, new MetadataVersion(2, 1, 0));
        enumMap.put(LanguageVersion.KOTLIN_2_2, new MetadataVersion(2, 2, 0));
        enumMap.put(LanguageVersion.KOTLIN_2_3, new MetadataVersion(2, 3, 0));
        enumMap.put(LanguageVersion.KOTLIN_2_4, MetadataVersion.INSTANCE);
        enumMap.put(LanguageVersion.KOTLIN_2_5, new MetadataVersion(2, 5, 0));
        if (enumMap.size() == LanguageVersion.getEntries().size()) {
            LANGUAGE_TO_JVM_METADATA_VERSION = enumMap;
        } else {
            k2d.a("Please add mappings from the missing LanguageVersion instances to the corresponding MetadataVersion in `LANGUAGE_TO_METADATA_VERSION`");
        }
    }

    public static final BinaryVersion jvmMetadataVersion(CompilerConfiguration compilerConfiguration, LanguageVersion languageVersion) {
        compilerConfiguration.getClass();
        languageVersion.getClass();
        BinaryVersion binaryVersion = (BinaryVersion) compilerConfiguration.get(CommonConfigurationKeys.METADATA_VERSION);
        return binaryVersion == null ? toJvmMetadataVersion(languageVersion) : binaryVersion;
    }

    public static /* synthetic */ BinaryVersion jvmMetadataVersion$default(CompilerConfiguration compilerConfiguration, LanguageVersion languageVersion, int i, Object obj) {
        if ((i & 1) != 0) {
            languageVersion = CommonConfigurationKeysKt.getLanguageVersionSettings(compilerConfiguration).getLanguageVersion();
        }
        return jvmMetadataVersion(compilerConfiguration, languageVersion);
    }

    public static final MetadataVersion toJvmMetadataVersion(LanguageVersion languageVersion) {
        languageVersion.getClass();
        Object value = MapsKt.getValue(LANGUAGE_TO_JVM_METADATA_VERSION, languageVersion);
        value.getClass();
        return (MetadataVersion) value;
    }
}
