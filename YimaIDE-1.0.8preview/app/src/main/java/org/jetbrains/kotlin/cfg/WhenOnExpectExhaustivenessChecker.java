package org.jetbrains.kotlin.cfg;

import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.descriptors.ClassDescriptor;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.diagnostics.WhenMissingCase;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/cfg/WhenOnExpectExhaustivenessChecker;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "getMissingCase", "Lorg/jetbrains/kotlin/diagnostics/WhenMissingCase;", "subjectDescriptor", "Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {2, 4, 0}, xi = 48)
final class WhenOnExpectExhaustivenessChecker {
    public static final WhenOnExpectExhaustivenessChecker INSTANCE = new WhenOnExpectExhaustivenessChecker();

    @Metadata(k = 3, mv = {2, 4, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ClassKind.values().length];
            try {
                iArr[ClassKind.CLASS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ClassKind.INTERFACE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ClassKind.ENUM_CLASS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private WhenOnExpectExhaustivenessChecker() {
    }

    public final WhenMissingCase getMissingCase(ClassDescriptor subjectDescriptor) {
        boolean z = false;
        if (subjectDescriptor != null && subjectDescriptor.isExpect()) {
            z = true;
        }
        if (!z) {
            return null;
        }
        subjectDescriptor.getClass();
        int i = WhenMappings.$EnumSwitchMapping$0[subjectDescriptor.getKind().ordinal()];
        if (i == 1) {
            return WhenMissingCase.ConditionTypeIsExpect.SealedClass.INSTANCE;
        }
        if (i != 2) {
            return i != 3 ? WhenMissingCase.Unknown.INSTANCE : WhenMissingCase.ConditionTypeIsExpect.Enum.INSTANCE;
        }
        return WhenMissingCase.ConditionTypeIsExpect.SealedInterface.INSTANCE;
    }
}
