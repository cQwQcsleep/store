package org.jetbrains.kotlin.fir.serialization;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.serialization.SerializableStringTable;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/fir/serialization/FirElementAwareSerializableStringTable;", "Lorg/jetbrains/kotlin/fir/serialization/FirElementAwareStringTable;", "Lorg/jetbrains/kotlin/serialization/SerializableStringTable;", "<init>", "()V", "getLocalClassLikeDeclarationIdReplacement", "Lorg/jetbrains/kotlin/name/ClassId;", "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;", "org.jetbrains.kotlin:fir-serialization"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirElementAwareSerializableStringTable extends SerializableStringTable implements FirElementAwareStringTable {
    @Override // org.jetbrains.kotlin.fir.serialization.FirElementAwareStringTable
    public ClassId getLocalClassLikeDeclarationIdReplacement(FirClassLikeDeclaration declaration) {
        declaration.getClass();
        return StandardClassIds.INSTANCE.getAny();
    }
}
