package org.jetbrains.kotlin.analysis.decompiler.psi.text;

import kotlin.Metadata;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.metadata.deserialization.BinaryVersion;
import org.jetbrains.kotlin.metadata.deserialization.MetadataVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a%\u0010\u0005\u001a\u00020\u0001\"\b\b\u0000\u0010\u0006*\u00020\u00072\u0006\u0010\b\u001a\u0002H\u00062\u0006\u0010\t\u001a\u0002H\u0006¢\u0006\u0002\u0010\n\u001a\u001d\u0010\u0005\u001a\u00020\u0001\"\b\b\u0000\u0010\u0006*\u00020\u00072\u0006\u0010\t\u001a\u0002H\u0006¢\u0006\u0002\u0010\u000b\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"FILE_METADATA_VERSION_MARKER", "", "CURRENT_METADATA_VERSION_MARKER", "INCOMPATIBLE_METADATA_VERSION_GENERAL_COMMENT", "INCOMPATIBLE_METADATA_VERSION_COMMENT", "createIncompatibleMetadataVersionDecompiledText", "V", "Lorg/jetbrains/kotlin/metadata/deserialization/BinaryVersion;", "expectedVersion", "actualVersion", "(Lorg/jetbrains/kotlin/metadata/deserialization/BinaryVersion;Lorg/jetbrains/kotlin/metadata/deserialization/BinaryVersion;)Ljava/lang/String;", "(Lorg/jetbrains/kotlin/metadata/deserialization/BinaryVersion;)Ljava/lang/String;", "org.jetbrains.kotlin:decompiler-to-file-stubs"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class IncompatibleAbiVersionKt {
    public static final String INCOMPATIBLE_METADATA_VERSION_GENERAL_COMMENT = "// This file was compiled with a newer version of Kotlin compiler and can't be decompiled.";

    public static final <V extends BinaryVersion> String createIncompatibleMetadataVersionDecompiledText(V v, V v2) {
        v.getClass();
        v2.getClass();
        return StringsKt.replace$default(StringsKt.replace$default("// This file was compiled with a newer version of Kotlin compiler and can't be decompiled.\n//\n// The current compiler supports reading only metadata of version CURRENT_METADATA or lower.\n// The file metadata version is FILE_METADATA", "CURRENT_METADATA", v.toString(), false, 4, (Object) null), "FILE_METADATA", v2.toString(), false, 4, (Object) null);
    }

    public static final <V extends BinaryVersion> String createIncompatibleMetadataVersionDecompiledText(V v) {
        v.getClass();
        return createIncompatibleMetadataVersionDecompiledText(MetadataVersion.INSTANCE_NEXT, v);
    }
}
