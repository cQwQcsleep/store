package org.jetbrains.kotlin.builtins;

import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.container.DefaultImplementation;
import org.jetbrains.kotlin.types.KotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@DefaultImplementation(impl = Default.class)
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001:\u0001\u0007J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/builtins/PlatformSpecificCastChecker;", "", "isCastPossible", "", "fromType", "Lorg/jetbrains/kotlin/types/KotlinType;", "toType", "Default", "org.jetbrains.kotlin:descriptors"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface PlatformSpecificCastChecker {

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0016¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/builtins/PlatformSpecificCastChecker$Default;", "Lorg/jetbrains/kotlin/builtins/PlatformSpecificCastChecker;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "isCastPossible", "", "fromType", "Lorg/jetbrains/kotlin/types/KotlinType;", "toType", "org.jetbrains.kotlin:descriptors"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Default implements PlatformSpecificCastChecker {
        @Override // org.jetbrains.kotlin.builtins.PlatformSpecificCastChecker
        public boolean isCastPossible(KotlinType fromType, KotlinType toType) {
            fromType.getClass();
            toType.getClass();
            return false;
        }
    }

    boolean isCastPossible(KotlinType fromType, KotlinType toType);
}
