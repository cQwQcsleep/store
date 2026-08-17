package org.jetbrains.kotlin.codegen.coroutines;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.optimization.common.OptimizationBasicInterpreter;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.Type;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.FieldInsnNode;
import org.jetbrains.org.objectweb.asm.tree.InvokeDynamicInsnNode;
import org.jetbrains.org.objectweb.asm.tree.MethodInsnNode;
import org.jetbrains.org.objectweb.asm.tree.VarInsnNode;
import org.jetbrains.org.objectweb.asm.tree.analysis.BasicValue;
import org.jetbrains.org.objectweb.asm.tree.analysis.Value;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0007H\u0002J\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\f\u001a\u0004\u0018\u00010\u0010H\u0016J\"\u0010\u0013\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u0010H\u0016J\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\f\u001a\u0004\u0018\u00010\u0010H\u0016J$\u0010\u0017\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0010\u0010\u0018\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00100\u0019H\u0016J0\u0010\u001a\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u001b\u001a\u0004\u0018\u00010\u00102\b\u0010\u001c\u001a\u0004\u0018\u00010\u00102\b\u0010\f\u001a\u0004\u0018\u00010\u0010H\u0016J\u0018\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u0010H\u0016R\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u001e"}, d2 = {"Lorg/jetbrains/kotlin/codegen/coroutines/IntLikeCoerceInterpreter;", "Lorg/jetbrains/kotlin/codegen/optimization/common/OptimizationBasicInterpreter;", "<init>", "()V", "needsToBeCoerced", Argument.Delimiters.none, "Lorg/jetbrains/org/objectweb/asm/tree/VarInsnNode;", "Lorg/jetbrains/org/objectweb/asm/Type;", "getNeedsToBeCoerced", "()Ljava/util/Map;", "coerce", Argument.Delimiters.none, "value", "Lorg/jetbrains/kotlin/codegen/coroutines/IloadedValue;", ModuleXmlParser.TYPE, "copyOperation", "Lorg/jetbrains/org/objectweb/asm/tree/analysis/BasicValue;", "insn", "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "binaryOperation", "v", "w", "unaryOperation", "naryOperation", "values", Argument.Delimiters.none, "ternaryOperation", "arrayref", "index", "merge", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final class IntLikeCoerceInterpreter extends OptimizationBasicInterpreter {
    private final Map<VarInsnNode, Type> needsToBeCoerced = new LinkedHashMap();

    private final void coerce(IloadedValue value, Type type) {
        Iterator<VarInsnNode> it = value.getInsns().iterator();
        while (it.hasNext()) {
            this.needsToBeCoerced.put(it.next(), type);
        }
    }

    private static final void naryOperation$checkTypes(List<? extends BasicValue> list, IntLikeCoerceInterpreter intLikeCoerceInterpreter, Type[] typeArr, boolean z) {
        int length = typeArr.length;
        for (int i = 0; i < length; i++) {
            Type type = typeArr[i];
            BasicValue basicValue = list.get(i + (z ? 1 : 0));
            if (basicValue != null && SpilledVariableFieldTypesAnalysisKt.isIntLike(type) && (basicValue instanceof IloadedValue)) {
                intLikeCoerceInterpreter.coerce((IloadedValue) basicValue, type);
            }
        }
    }

    @Override // org.jetbrains.kotlin.codegen.optimization.common.OptimizationBasicInterpreter
    public BasicValue binaryOperation(AbstractInsnNode insn, BasicValue v, BasicValue w) {
        insn.getClass();
        v.getClass();
        w.getClass();
        if (insn.getOpcode() == 181) {
            Type type = Type.getType(((FieldInsnNode) insn).desc);
            if (w instanceof IloadedValue) {
                type.getClass();
                if (SpilledVariableFieldTypesAnalysisKt.isIntLike(type)) {
                    coerce((IloadedValue) w, type);
                }
            }
        }
        return super.binaryOperation(insn, v, w);
    }

    @Override // org.jetbrains.kotlin.codegen.optimization.common.OptimizationBasicInterpreter
    public BasicValue copyOperation(AbstractInsnNode insn, BasicValue value) {
        insn.getClass();
        if (insn.getOpcode() == 21) {
            return new IloadedValue(SetsKt.setOf((VarInsnNode) insn));
        }
        if (value == null) {
            return null;
        }
        return new BasicValue(value.getType());
    }

    public final Map<VarInsnNode, Type> getNeedsToBeCoerced() {
        return this.needsToBeCoerced;
    }

    @Override // org.jetbrains.kotlin.codegen.optimization.common.OptimizationBasicInterpreter
    public BasicValue merge(BasicValue v, BasicValue w) {
        Object next;
        v.getClass();
        w.getClass();
        if ((v instanceof IloadedValue) && (w instanceof IloadedValue)) {
            IloadedValue iloadedValue = (IloadedValue) v;
            IloadedValue iloadedValue2 = (IloadedValue) w;
            if (Intrinsics.areEqual(iloadedValue.getType(), iloadedValue2.getType())) {
                Set setPlus = SetsKt.plus(iloadedValue.getInsns(), iloadedValue2.getInsns());
                Iterator it = setPlus.iterator();
                do {
                    if (!it.hasNext()) {
                        next = null;
                        break;
                    }
                    next = it.next();
                } while (!this.needsToBeCoerced.containsKey((VarInsnNode) next));
                VarInsnNode varInsnNode = (VarInsnNode) next;
                if (varInsnNode != null) {
                    Type type = this.needsToBeCoerced.get(varInsnNode);
                    type.getClass();
                    Type type2 = type;
                    coerce(iloadedValue, type2);
                    coerce(iloadedValue2, type2);
                }
                return new IloadedValue(setPlus);
            }
        }
        if (Intrinsics.areEqual(v.getType(), w.getType())) {
            return w instanceof IloadedValue ? w : v;
        }
        BasicValue basicValueMerge = super.merge(v, w);
        basicValueMerge.getClass();
        return basicValueMerge;
    }

    @Override // org.jetbrains.kotlin.codegen.optimization.common.OptimizationBasicInterpreter
    public BasicValue naryOperation(AbstractInsnNode insn, List<? extends BasicValue> values) {
        insn.getClass();
        values.getClass();
        switch (insn.getOpcode()) {
            case 182:
            case 183:
            case 185:
                Type[] argumentTypes = Type.getArgumentTypes(((MethodInsnNode) insn).desc);
                argumentTypes.getClass();
                naryOperation$checkTypes(values, this, argumentTypes, true);
                break;
            case 184:
                Type[] argumentTypes2 = Type.getArgumentTypes(((MethodInsnNode) insn).desc);
                argumentTypes2.getClass();
                naryOperation$checkTypes(values, this, argumentTypes2, false);
                break;
            case 186:
                Type[] argumentTypes3 = Type.getArgumentTypes(((InvokeDynamicInsnNode) insn).desc);
                argumentTypes3.getClass();
                naryOperation$checkTypes(values, this, argumentTypes3, false);
                break;
        }
        return super.naryOperation(insn, values);
    }

    @Override // org.jetbrains.kotlin.codegen.optimization.common.OptimizationBasicInterpreter
    public BasicValue ternaryOperation(AbstractInsnNode insn, BasicValue arrayref, BasicValue index, BasicValue value) {
        Type type;
        insn.getClass();
        switch (insn.getOpcode()) {
            case 84:
                if (value instanceof IloadedValue) {
                    Type type2 = Intrinsics.areEqual((arrayref == null || (type = arrayref.getType()) == null) ? null : type.getDescriptor(), "[Z") ? Type.BOOLEAN_TYPE : Type.BYTE_TYPE;
                    type2.getClass();
                    coerce((IloadedValue) value, type2);
                }
                break;
            case 85:
                if (value instanceof IloadedValue) {
                    Type type3 = Type.CHAR_TYPE;
                    type3.getClass();
                    coerce((IloadedValue) value, type3);
                }
                break;
            case 86:
                if (value instanceof IloadedValue) {
                    Type type4 = Type.SHORT_TYPE;
                    type4.getClass();
                    coerce((IloadedValue) value, type4);
                }
                break;
        }
        return super.ternaryOperation(insn, arrayref, index, value);
    }

    @Override // org.jetbrains.kotlin.codegen.optimization.common.OptimizationBasicInterpreter
    public BasicValue unaryOperation(AbstractInsnNode insn, BasicValue value) {
        insn.getClass();
        if (insn.getOpcode() == 179) {
            Type type = Type.getType(((FieldInsnNode) insn).desc);
            if (value instanceof IloadedValue) {
                type.getClass();
                if (SpilledVariableFieldTypesAnalysisKt.isIntLike(type)) {
                    coerce((IloadedValue) value, type);
                }
            }
        }
        return super.unaryOperation(insn, value);
    }

    @Override // org.jetbrains.kotlin.codegen.optimization.common.OptimizationBasicInterpreter
    /* JADX INFO: renamed from: naryOperation, reason: collision with other method in class */
    public /* bridge */ /* synthetic */ Value mo53naryOperation(AbstractInsnNode abstractInsnNode, List list) {
        return naryOperation(abstractInsnNode, (List<? extends BasicValue>) list);
    }
}
