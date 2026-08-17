package org.jetbrains.kotlin.ir.backend.js.tsexport;

import kotlin.Metadata;
import org.jetbrains.kotlin.types.Variance;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"exportedVariance", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedVariance;", "Lorg/jetbrains/kotlin/types/Variance;", "getExportedVariance", "(Lorg/jetbrains/kotlin/types/Variance;)Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedVariance;", "org.jetbrains.kotlin:typescript-export-model"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class ExportModelExtensionsKt {

    @Metadata(k = 3, mv = {2, 4, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Variance.values().length];
            try {
                iArr[Variance.INVARIANT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Variance.IN_VARIANCE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[Variance.OUT_VARIANCE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final ExportedVariance getExportedVariance(Variance variance) {
        variance.getClass();
        int i = WhenMappings.$EnumSwitchMapping$0[variance.ordinal()];
        if (i == 1) {
            return ExportedVariance.INVARIANT;
        }
        if (i == 2) {
            return ExportedVariance.CONTRAVARIANT;
        }
        if (i == 3) {
            return ExportedVariance.COVARIANT;
        }
        bu8.a();
        return null;
    }
}
