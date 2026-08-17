package org.jetbrains.kotlin.backend.common.serialization.mangle;

import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\bf\u0018\u0000*\n\b\u0000\u0010\u0001 \u0000*\u00020\u00022\u00020\u0002:\u0001\bJ\n\u0010\u0003\u001a\u0004\u0018\u00010\u0004H&J\u0015\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u0007ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\tÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/serialization/mangle/PlatformSpecificFunctionNameMangleComputer;", "ValueParameter", "", "computePlatformSpecificFunctionName", "", "computePlatformSpecificValueParameterPrefix", "valueParameter", "(Ljava/lang/Object;)Ljava/lang/String;", "Default", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface PlatformSpecificFunctionNameMangleComputer<ValueParameter> {

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\n\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0002H\u0016¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/serialization/mangle/PlatformSpecificFunctionNameMangleComputer$Default;", "Lorg/jetbrains/kotlin/backend/common/serialization/mangle/PlatformSpecificFunctionNameMangleComputer;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "computePlatformSpecificFunctionName", "", "computePlatformSpecificValueParameterPrefix", "valueParameter", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Default implements PlatformSpecificFunctionNameMangleComputer<Object> {
        public static final Default INSTANCE = new Default();

        private Default() {
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.mangle.PlatformSpecificFunctionNameMangleComputer
        public String computePlatformSpecificFunctionName() {
            return null;
        }

        @Override // org.jetbrains.kotlin.backend.common.serialization.mangle.PlatformSpecificFunctionNameMangleComputer
        public String computePlatformSpecificValueParameterPrefix(Object valueParameter) {
            valueParameter.getClass();
            return "";
        }
    }

    String computePlatformSpecificFunctionName();

    String computePlatformSpecificValueParameterPrefix(ValueParameter valueParameter);
}
