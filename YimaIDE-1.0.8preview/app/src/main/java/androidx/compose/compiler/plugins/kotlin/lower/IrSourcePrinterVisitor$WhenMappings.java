package androidx.compose.compiler.plugins.kotlin.lower;

import kotlin.Metadata;
import org.jetbrains.kotlin.ir.expressions.IrTypeOperator;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(k = 3, mv = {2, 4, 0}, xi = 48)
public final /* synthetic */ class IrSourcePrinterVisitor$WhenMappings {
    public static final /* synthetic */ int[] $EnumSwitchMapping$0;

    static {
        int[] iArr = new int[IrTypeOperator.values().length];
        try {
            iArr[IrTypeOperator.IMPLICIT_COERCION_TO_UNIT.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr[IrTypeOperator.NOT_INSTANCEOF.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            iArr[IrTypeOperator.CAST.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            iArr[IrTypeOperator.SAFE_CAST.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            iArr[IrTypeOperator.IMPLICIT_CAST.ordinal()] = 5;
        } catch (NoSuchFieldError unused5) {
        }
        try {
            iArr[IrTypeOperator.SAM_CONVERSION.ordinal()] = 6;
        } catch (NoSuchFieldError unused6) {
        }
        try {
            iArr[IrTypeOperator.IMPLICIT_NOTNULL.ordinal()] = 7;
        } catch (NoSuchFieldError unused7) {
        }
        try {
            iArr[IrTypeOperator.INSTANCEOF.ordinal()] = 8;
        } catch (NoSuchFieldError unused8) {
        }
        $EnumSwitchMapping$0 = iArr;
    }
}
