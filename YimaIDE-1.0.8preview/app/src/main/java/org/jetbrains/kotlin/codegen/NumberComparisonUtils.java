package org.jetbrains.kotlin.codegen;

import com.intellij.psi.tree.IElementType;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.eclipse.jdt.internal.compiler.lookup.TypeIds;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.lexer.KtTokens;
import org.jetbrains.org.objectweb.asm.Type;
import org.jetbrains.org.objectweb.asm.commons.InstructionAdapter;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0010%\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J(\u0010\t\u001a\u00020\n*\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u000b2\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u0006H\u0002J\u000e\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010J&\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u0016R\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/codegen/NumberComparisonUtils;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "negatedOperations", "", "", "getNegatedOperations", "()Ljava/util/Map;", "registerOperations", "", "", "op", "negatedOp", "getNumberCompareOpcode", "opToken", "Lcom/intellij/psi/tree/IElementType;", "patchOpcode", "opcode", "v", "Lorg/jetbrains/org/objectweb/asm/commons/InstructionAdapter;", "operandType", "Lorg/jetbrains/org/objectweb/asm/Type;", "org.jetbrains.kotlin:backend"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class NumberComparisonUtils {
    public static final NumberComparisonUtils INSTANCE;
    private static final Map<Integer, Integer> negatedOperations;

    static {
        NumberComparisonUtils numberComparisonUtils = new NumberComparisonUtils();
        INSTANCE = numberComparisonUtils;
        HashMap map = new HashMap();
        numberComparisonUtils.registerOperations(map, TypeIds.Int2Float, TypeIds.Float2Float);
        numberComparisonUtils.registerOperations(map, 158, 157);
        numberComparisonUtils.registerOperations(map, 155, 156);
        numberComparisonUtils.registerOperations(map, 156, 155);
        numberComparisonUtils.registerOperations(map, 157, 158);
        numberComparisonUtils.registerOperations(map, 166, TypeIds.Boolean2Int);
        numberComparisonUtils.registerOperations(map, 198, 199);
        negatedOperations = map;
    }

    private NumberComparisonUtils() {
    }

    private final void registerOperations(Map<Integer, Integer> map, int i, int i2) {
        map.put(Integer.valueOf(i), Integer.valueOf(i2));
        map.put(Integer.valueOf(i2), Integer.valueOf(i));
    }

    public final Map<Integer, Integer> getNegatedOperations() {
        return negatedOperations;
    }

    public final int getNumberCompareOpcode(IElementType opToken) {
        opToken.getClass();
        if (Intrinsics.areEqual(opToken, KtTokens.EQEQ) || Intrinsics.areEqual(opToken, KtTokens.EQEQEQ)) {
            return TypeIds.Int2Float;
        }
        if (Intrinsics.areEqual(opToken, KtTokens.EXCLEQ) || Intrinsics.areEqual(opToken, KtTokens.EXCLEQEQEQ)) {
            return TypeIds.Float2Float;
        }
        if (Intrinsics.areEqual(opToken, KtTokens.GT)) {
            return 158;
        }
        if (Intrinsics.areEqual(opToken, KtTokens.GTEQ)) {
            return 155;
        }
        if (Intrinsics.areEqual(opToken, KtTokens.LT)) {
            return 156;
        }
        if (Intrinsics.areEqual(opToken, KtTokens.LTEQ)) {
            return 157;
        }
        zwe.a("Don't know how to generate this condJump: ", opToken);
        return 0;
    }

    public final int patchOpcode(int opcode, InstructionAdapter v, IElementType opToken, Type operandType) {
        v.getClass();
        opToken.getClass();
        operandType.getClass();
        if (!Intrinsics.areEqual(operandType, Type.FLOAT_TYPE) && !Intrinsics.areEqual(operandType, Type.DOUBLE_TYPE)) {
            if (!Intrinsics.areEqual(operandType, Type.LONG_TYPE)) {
                return opcode + 6;
            }
            v.lcmp();
            return opcode;
        }
        if (Intrinsics.areEqual(opToken, KtTokens.GT) || Intrinsics.areEqual(opToken, KtTokens.GTEQ)) {
            v.cmpl(operandType);
            return opcode;
        }
        v.cmpg(operandType);
        return opcode;
    }
}
