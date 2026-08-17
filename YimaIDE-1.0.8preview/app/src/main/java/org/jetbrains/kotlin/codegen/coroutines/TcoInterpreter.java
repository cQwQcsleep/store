package org.jetbrains.kotlin.codegen.coroutines;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.analysis.BasicInterpreter;
import org.jetbrains.org.objectweb.asm.tree.analysis.BasicValue;
import org.jetbrains.org.objectweb.asm.tree.analysis.Value;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\b\t\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u001c\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\bH\u0016J\u0018\u0010\f\u001a\u0004\u0018\u00010\b*\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\nH\u0002J&\u0010\r\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\n2\u0012\u0010\u000e\u001a\u000e\u0012\b\b\u0001\u0012\u0004\u0018\u00010\b\u0018\u00010\u000fH\u0016J0\u0010\u0010\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u0011\u001a\u0004\u0018\u00010\b2\b\u0010\u0012\u001a\u0004\u0018\u00010\b2\b\u0010\u0013\u001a\u0004\u0018\u00010\bH\u0016J\u001c\u0010\u0014\u001a\u00020\b2\b\u0010\u0011\u001a\u0004\u0018\u00010\b2\b\u0010\u0012\u001a\u0004\u0018\u00010\bH\u0016J\u001c\u0010\u0015\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\bH\u0016J&\u0010\u0016\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u0011\u001a\u0004\u0018\u00010\b2\b\u0010\u0012\u001a\u0004\u0018\u00010\bH\u0016J\u0012\u0010\u0017\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\nH\u0016R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lorg/jetbrains/kotlin/codegen/coroutines/TcoInterpreter;", "Lorg/jetbrains/org/objectweb/asm/tree/analysis/BasicInterpreter;", "suspensionPoints", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/codegen/coroutines/SuspensionPoint;", "<init>", "(Ljava/util/List;)V", "copyOperation", "Lorg/jetbrains/org/objectweb/asm/tree/analysis/BasicValue;", "insn", "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "value", "convert", "naryOperation", "values", Argument.Delimiters.none, "ternaryOperation", "value1", "value2", "value3", "merge", "unaryOperation", "binaryOperation", "newOperation", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final class TcoInterpreter extends BasicInterpreter {
    private final List<SuspensionPoint> suspensionPoints;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TcoInterpreter(List<SuspensionPoint> list) {
        super(589824);
        list.getClass();
        this.suspensionPoints = list;
    }

    private final BasicValue convert(BasicValue basicValue, AbstractInsnNode abstractInsnNode) {
        return CoroutineTransformerMethodVisitorKt.contains(this.suspensionPoints, abstractInsnNode) ? TailCallOptimizationKt.toFromSuspensionPoint(basicValue) : basicValue;
    }

    public BasicValue binaryOperation(AbstractInsnNode insn, BasicValue value1, BasicValue value2) {
        insn.getClass();
        return convert(super.binaryOperation(insn, value1, value2), insn);
    }

    public BasicValue copyOperation(AbstractInsnNode insn, BasicValue value) {
        insn.getClass();
        return convert(super.copyOperation(insn, value), insn);
    }

    public BasicValue merge(BasicValue value1, BasicValue value2) {
        if ((value1 instanceof FromSuspensionPointValue) || (value2 instanceof FromSuspensionPointValue)) {
            return FromSuspensionPointValue.INSTANCE;
        }
        BasicValue basicValueMerge = super.merge(value1, value2);
        basicValueMerge.getClass();
        return basicValueMerge;
    }

    public BasicValue naryOperation(AbstractInsnNode insn, List<? extends BasicValue> values) {
        insn.getClass();
        return convert(super.naryOperation(insn, values), insn);
    }

    /* JADX INFO: renamed from: newOperation, reason: merged with bridge method [inline-methods] */
    public BasicValue m58newOperation(AbstractInsnNode insn) {
        insn.getClass();
        return convert(super.newOperation(insn), insn);
    }

    public BasicValue ternaryOperation(AbstractInsnNode insn, BasicValue value1, BasicValue value2, BasicValue value3) {
        insn.getClass();
        return convert(super.ternaryOperation(insn, value1, value2, value3), insn);
    }

    public BasicValue unaryOperation(AbstractInsnNode insn, BasicValue value) {
        insn.getClass();
        return ((value instanceof FromSuspensionPointValue) && insn.getOpcode() == 192) ? value : convert(super.unaryOperation(insn, value), insn);
    }

    /* JADX INFO: renamed from: naryOperation, reason: collision with other method in class */
    public /* bridge */ /* synthetic */ Value m57naryOperation(AbstractInsnNode abstractInsnNode, List list) {
        return naryOperation(abstractInsnNode, (List<? extends BasicValue>) list);
    }
}
