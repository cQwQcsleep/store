package androidx.compose.compiler.plugins.kotlin.lower;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.ir.declarations.IrValueDeclaration;
import org.jetbrains.kotlin.ir.expressions.IrExpression;
import org.jetbrains.kotlin.ir.expressions.IrFunctionAccessExpression;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0018\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\bf\u0018\u00002\u00020\u0001J\b\u0010\u000b\u001a\u00020\fH&J\u0018\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0003H&J\u0018\u0010\u0011\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u000fH&J\u0010\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u0015H&J\b\u0010\u0016\u001a\u00020\fH&J(\u0010\u0017\u001a\u00020\u00182\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u00032\b\b\u0002\u0010\u001c\u001a\u00020\u0003H&J \u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u000f2\u0006\u0010\"\u001a\u00020\u0003H&J\u0018\u0010#\u001a\u00020\f2\u0006\u0010$\u001a\u00020\u000f2\u0006\u0010%\u001a\u00020\u000fH&J\u0010\u0010&\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000fH&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0018\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006'À\u0006\u0003"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/lower/IrChangedBitMaskValue;", "", "used", "", "getUsed", "()Z", "declarations", "", "Lorg/jetbrains/kotlin/ir/declarations/IrValueDeclaration;", "getDeclarations", "()Ljava/util/List;", "irLowBit", "Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", "irIsolateBitsAtSlot", "slot", "", "includeStableBit", "irSlotAnd", "bits", "irHasDifferences", "usedParams", "", "irRestartFlags", "irCopyToTemporary", "Landroidx/compose/compiler/plugins/kotlin/lower/IrChangedBitMaskVariable;", "nameHint", "", "isVar", "exactName", "putAsValueArgumentInWithLowBit", "", "fn", "Lorg/jetbrains/kotlin/ir/expressions/IrFunctionAccessExpression;", "startIndex", "lowBit", "irShiftBits", "fromSlot", "toSlot", "irStableBitAtSlot", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface IrChangedBitMaskValue {

    @Metadata(k = 3, mv = {2, 4, 0}, xi = 48)
    public static final class DefaultImpls {
    }

    static /* synthetic */ IrChangedBitMaskVariable irCopyToTemporary$default(IrChangedBitMaskValue irChangedBitMaskValue, String str, boolean z, boolean z2, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: irCopyToTemporary");
            return null;
        }
        if ((i & 1) != 0) {
            str = null;
        }
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            z2 = false;
        }
        return irChangedBitMaskValue.irCopyToTemporary(str, z, z2);
    }

    List<IrValueDeclaration> getDeclarations();

    boolean getUsed();

    IrChangedBitMaskVariable irCopyToTemporary(String nameHint, boolean isVar, boolean exactName);

    IrExpression irHasDifferences(boolean[] usedParams);

    IrExpression irIsolateBitsAtSlot(int slot, boolean includeStableBit);

    IrExpression irLowBit();

    IrExpression irRestartFlags();

    IrExpression irShiftBits(int fromSlot, int toSlot);

    IrExpression irSlotAnd(int slot, int bits);

    IrExpression irStableBitAtSlot(int slot);

    void putAsValueArgumentInWithLowBit(IrFunctionAccessExpression fn, int startIndex, boolean lowBit);
}
