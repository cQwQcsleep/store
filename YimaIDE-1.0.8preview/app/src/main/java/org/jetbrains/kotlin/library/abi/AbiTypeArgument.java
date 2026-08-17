package org.jetbrains.kotlin.library.abi;

import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\bw\u0018\u00002\u00020\u0001:\u0002\u0002\u0003\u0082\u0001\u0002\u0004\u0005Ê\u0001\u0002\b\u0007ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0006À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/library/abi/AbiTypeArgument;", "", "StarProjection", "TypeProjection", "Lorg/jetbrains/kotlin/library/abi/AbiTypeArgument$StarProjection;", "Lorg/jetbrains/kotlin/library/abi/AbiTypeArgument$TypeProjection;", "org.jetbrains.kotlin:kotlin-util-klib-abi", "Lorg/jetbrains/kotlin/library/abi/ExperimentalLibraryAbiReader;"}, k = 1, mv = {2, 4, 0}, xi = 48)
@ExperimentalLibraryAbiReader
public interface AbiTypeArgument {

    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0002À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/library/abi/AbiTypeArgument$StarProjection;", "Lorg/jetbrains/kotlin/library/abi/AbiTypeArgument;", "org.jetbrains.kotlin:kotlin-util-klib-abi"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public interface StarProjection extends AbiTypeArgument {
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\nÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/library/abi/AbiTypeArgument$TypeProjection;", "Lorg/jetbrains/kotlin/library/abi/AbiTypeArgument;", "type", "Lorg/jetbrains/kotlin/library/abi/AbiType;", "getType", "()Lorg/jetbrains/kotlin/library/abi/AbiType;", "variance", "Lorg/jetbrains/kotlin/library/abi/AbiVariance;", "getVariance", "()Lorg/jetbrains/kotlin/library/abi/AbiVariance;", "org.jetbrains.kotlin:kotlin-util-klib-abi"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public interface TypeProjection extends AbiTypeArgument {
        AbiType getType();

        AbiVariance getVariance();
    }
}
