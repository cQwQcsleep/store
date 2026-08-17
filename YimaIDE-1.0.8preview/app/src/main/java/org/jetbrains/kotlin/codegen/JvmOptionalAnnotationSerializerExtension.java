package org.jetbrains.kotlin.codegen;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.metadata.deserialization.BinaryVersion;
import org.jetbrains.kotlin.metadata.deserialization.MetadataVersion;
import org.jetbrains.kotlin.serialization.KotlinSerializerExtensionBase;
import org.jetbrains.kotlin.serialization.StringTableImpl;
import org.jetbrains.kotlin.serialization.deserialization.builtins.BuiltInSerializerProtocol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\f\u001a\u00020\rH\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/codegen/JvmOptionalAnnotationSerializerExtension;", "Lorg/jetbrains/kotlin/serialization/KotlinSerializerExtensionBase;", "stringTable", "Lorg/jetbrains/kotlin/serialization/StringTableImpl;", "<init>", "(Lorg/jetbrains/kotlin/serialization/StringTableImpl;)V", "getStringTable", "()Lorg/jetbrains/kotlin/serialization/StringTableImpl;", "metadataVersion", "Lorg/jetbrains/kotlin/metadata/deserialization/BinaryVersion;", "getMetadataVersion", "()Lorg/jetbrains/kotlin/metadata/deserialization/BinaryVersion;", "shouldUseTypeTable", Argument.Delimiters.none, "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class JvmOptionalAnnotationSerializerExtension extends KotlinSerializerExtensionBase {
    private final StringTableImpl stringTable;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JvmOptionalAnnotationSerializerExtension(StringTableImpl stringTableImpl) {
        super(BuiltInSerializerProtocol.INSTANCE);
        stringTableImpl.getClass();
        this.stringTable = stringTableImpl;
    }

    public BinaryVersion getMetadataVersion() {
        return MetadataVersion.INSTANCE;
    }

    public boolean shouldUseTypeTable() {
        return true;
    }

    public StringTableImpl getStringTable() {
        return this.stringTable;
    }
}
