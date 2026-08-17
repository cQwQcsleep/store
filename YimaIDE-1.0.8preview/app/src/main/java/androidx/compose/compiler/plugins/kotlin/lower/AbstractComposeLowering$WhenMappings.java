package androidx.compose.compiler.plugins.kotlin.lower;

import kotlin.Metadata;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.ir.declarations.IrParameterKind;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(k = 3, mv = {2, 4, 0}, xi = 48)
public final /* synthetic */ class AbstractComposeLowering$WhenMappings {
    public static final /* synthetic */ int[] $EnumSwitchMapping$0;
    public static final /* synthetic */ int[] $EnumSwitchMapping$1;

    static {
        int[] iArr = new int[IrParameterKind.values().length];
        try {
            iArr[IrParameterKind.DispatchReceiver.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr[IrParameterKind.ExtensionReceiver.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            iArr[IrParameterKind.Context.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            iArr[IrParameterKind.Regular.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        $EnumSwitchMapping$0 = iArr;
        int[] iArr2 = new int[ClassKind.values().length];
        try {
            iArr2[ClassKind.ENUM_CLASS.ordinal()] = 1;
        } catch (NoSuchFieldError unused5) {
        }
        try {
            iArr2[ClassKind.ENUM_ENTRY.ordinal()] = 2;
        } catch (NoSuchFieldError unused6) {
        }
        $EnumSwitchMapping$1 = iArr2;
    }
}
