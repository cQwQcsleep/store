package org.jetbrains.kotlin.serialization.deserialization;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001:\u0001\u0006J\u0016\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0004H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0007À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/serialization/deserialization/MetadataPartProvider;", "", "findMetadataPackageParts", "", "", "packageFqName", "Empty", "org.jetbrains.kotlin:deserialization.common"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public interface MetadataPartProvider {

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/serialization/deserialization/MetadataPartProvider$Empty;", "Lorg/jetbrains/kotlin/serialization/deserialization/MetadataPartProvider;", "<init>", "()V", "findMetadataPackageParts", "", "", "packageFqName", "org.jetbrains.kotlin:deserialization.common"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
    public static final class Empty implements MetadataPartProvider {
        public static final Empty INSTANCE = new Empty();

        private Empty() {
        }

        @Override // org.jetbrains.kotlin.serialization.deserialization.MetadataPartProvider
        public List<String> findMetadataPackageParts(String packageFqName) {
            packageFqName.getClass();
            return CollectionsKt.emptyList();
        }
    }

    List<String> findMetadataPackageParts(String packageFqName);
}
