package org.jetbrains.kotlin.cli.metadata;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.metadata.builtins.BuiltInsBinaryVersion;
import org.jetbrains.kotlin.serialization.ApproximatingStringTable;
import org.jetbrains.kotlin.serialization.KotlinSerializerExtensionBase;
import org.jetbrains.kotlin.serialization.deserialization.builtins.BuiltInSerializerProtocol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\b\u001a\u00020\tH\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\n\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/cli/metadata/MetadataSerializerExtension;", "Lorg/jetbrains/kotlin/serialization/KotlinSerializerExtensionBase;", "metadataVersion", "Lorg/jetbrains/kotlin/metadata/builtins/BuiltInsBinaryVersion;", "<init>", "(Lorg/jetbrains/kotlin/metadata/builtins/BuiltInsBinaryVersion;)V", "getMetadataVersion", "()Lorg/jetbrains/kotlin/metadata/builtins/BuiltInsBinaryVersion;", "shouldUseTypeTable", Argument.Delimiters.none, "stringTable", "Lorg/jetbrains/kotlin/serialization/ApproximatingStringTable;", "getStringTable", "()Lorg/jetbrains/kotlin/serialization/ApproximatingStringTable;", "org.jetbrains.kotlin:cli-metadata"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class MetadataSerializerExtension extends KotlinSerializerExtensionBase {
    private final BuiltInsBinaryVersion metadataVersion;
    private final ApproximatingStringTable stringTable;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MetadataSerializerExtension(BuiltInsBinaryVersion builtInsBinaryVersion) {
        super(BuiltInSerializerProtocol.INSTANCE);
        builtInsBinaryVersion.getClass();
        this.metadataVersion = builtInsBinaryVersion;
        this.stringTable = new ApproximatingStringTable();
    }

    public boolean shouldUseTypeTable() {
        return true;
    }

    /* JADX INFO: renamed from: getMetadataVersion, reason: from getter and merged with bridge method [inline-methods] */
    public BuiltInsBinaryVersion m27getMetadataVersion() {
        return this.metadataVersion;
    }

    /* JADX INFO: renamed from: getStringTable, reason: from getter and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public ApproximatingStringTable m29getStringTable() {
        return this.stringTable;
    }
}
