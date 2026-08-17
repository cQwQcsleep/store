package org.jetbrains.kotlin.backend.wasm.serialization;

import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0010\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0004\n\u0002\u0010\u0006¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/backend/wasm/serialization/LocationTags;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "NO_LOCATION", "Lkotlin/UInt;", "I", "DEFINED_LOCATION", "IGNORED_LOCATION", "NEXT_LOCATION", "org.jetbrains.kotlin:backend.wasm"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class LocationTags {
    public static final int DEFINED_LOCATION = 1;
    public static final int IGNORED_LOCATION = 2;
    public static final LocationTags INSTANCE = new LocationTags();
    public static final int NEXT_LOCATION = 3;
    public static final int NO_LOCATION = 0;

    private LocationTags() {
    }
}
