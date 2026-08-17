package org.jetbrains.kotlin.cli.metadata;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.builtins.DefaultBuiltIns;
import org.jetbrains.kotlin.builtins.KotlinBuiltIns;
import org.jetbrains.kotlin.cli.metadata.K1MetadataKlibSerializerKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.library.metadata.KlibMetadataFactories;
import org.jetbrains.kotlin.library.metadata.NullFlexibleTypeDeserializer;
import org.jetbrains.kotlin.storage.StorageManager;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0002"}, d2 = {"MetadataFactories", "Lorg/jetbrains/kotlin/library/metadata/KlibMetadataFactories;", "org.jetbrains.kotlin:cli-metadata"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class K1MetadataKlibSerializerKt {
    private static final KlibMetadataFactories MetadataFactories = new KlibMetadataFactories(new Function1() { // from class: f38
        public final Object invoke(Object obj) {
            return K1MetadataKlibSerializerKt.a((StorageManager) obj);
        }
    }, NullFlexibleTypeDeserializer.INSTANCE);

    public static KotlinBuiltIns a(StorageManager storageManager) {
        storageManager.getClass();
        return DefaultBuiltIns.Companion.getInstance();
    }
}
