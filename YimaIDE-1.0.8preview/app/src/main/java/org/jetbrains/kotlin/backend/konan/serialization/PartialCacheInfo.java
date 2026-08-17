package org.jetbrains.kotlin.backend.konan.serialization;

import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.library.KotlinLibrary;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/backend/konan/serialization/PartialCacheInfo;", "", "klib", "Lorg/jetbrains/kotlin/library/KotlinLibrary;", "strategy", "Lorg/jetbrains/kotlin/backend/konan/serialization/CacheDeserializationStrategy;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/library/KotlinLibrary;Lorg/jetbrains/kotlin/backend/konan/serialization/CacheDeserializationStrategy;)V", "getKlib", "()Lorg/jetbrains/kotlin/library/KotlinLibrary;", "getStrategy", "()Lorg/jetbrains/kotlin/backend/konan/serialization/CacheDeserializationStrategy;", "org.jetbrains.kotlin:ir.serialization.native"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class PartialCacheInfo {
    private final KotlinLibrary klib;
    private final CacheDeserializationStrategy strategy;

    public PartialCacheInfo(KotlinLibrary kotlinLibrary, CacheDeserializationStrategy cacheDeserializationStrategy) {
        kotlinLibrary.getClass();
        cacheDeserializationStrategy.getClass();
        this.klib = kotlinLibrary;
        this.strategy = cacheDeserializationStrategy;
    }

    public final KotlinLibrary getKlib() {
        return this.klib;
    }

    public final CacheDeserializationStrategy getStrategy() {
        return this.strategy;
    }
}
