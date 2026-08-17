package org.jetbrains.kotlin.codegen.inline;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.Type;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.FieldInsnNode;
import org.jetbrains.org.objectweb.asm.tree.analysis.BasicInterpreter;
import org.jetbrains.org.objectweb.asm.tree.analysis.BasicValue;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J \u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\u001a\u0010\u000e\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0007H\u0016J\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0014\u001a\u0004\u0018\u00010\u0007H\u0002J\u001e\u0010\u0015\u001a\u0004\u0018\u00010\u00072\b\u0010\u0016\u001a\u0004\u0018\u00010\u00072\b\u0010\u0017\u001a\u0004\u0018\u00010\u0007H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/FunctionalArgumentInterpreter;", "Lorg/jetbrains/org/objectweb/asm/tree/analysis/BasicInterpreter;", "inliner", "Lorg/jetbrains/kotlin/codegen/inline/MethodInliner;", "<init>", "(Lorg/jetbrains/kotlin/codegen/inline/MethodInliner;)V", "newParameterValue", "Lorg/jetbrains/org/objectweb/asm/tree/analysis/BasicValue;", "isInstanceMethod", Argument.Delimiters.none, "local", Argument.Delimiters.none, ModuleXmlParser.TYPE, "Lorg/jetbrains/org/objectweb/asm/Type;", "unaryOperation", "insn", "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "value", "newOperation", "wrapArgumentInValueIfNeeded", "basicValue", "merge", "v", "w", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FunctionalArgumentInterpreter extends BasicInterpreter {
    private final MethodInliner inliner;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FunctionalArgumentInterpreter(MethodInliner methodInliner) {
        super(589824);
        methodInliner.getClass();
        this.inliner = methodInliner;
    }

    private final BasicValue wrapArgumentInValueIfNeeded(AbstractInsnNode insn, BasicValue basicValue) {
        FunctionalArgument functionalArgumentIfExists$org_jetbrains_kotlin_backend;
        return (!(insn instanceof FieldInsnNode) || (functionalArgumentIfExists$org_jetbrains_kotlin_backend = this.inliner.getFunctionalArgumentIfExists$org_jetbrains_kotlin_backend((FieldInsnNode) insn)) == null) ? basicValue : new FunctionalArgumentValue(functionalArgumentIfExists$org_jetbrains_kotlin_backend, basicValue);
    }

    public BasicValue merge(BasicValue v, BasicValue w) {
        return ((v instanceof FunctionalArgumentValue) && (w instanceof FunctionalArgumentValue) && Intrinsics.areEqual(((FunctionalArgumentValue) v).getFunctionalArgument(), ((FunctionalArgumentValue) w).getFunctionalArgument())) ? v : super.merge(v, w);
    }

    /* JADX INFO: renamed from: newOperation, reason: merged with bridge method [inline-methods] */
    public BasicValue m63newOperation(AbstractInsnNode insn) {
        insn.getClass();
        return wrapArgumentInValueIfNeeded(insn, super.newOperation(insn));
    }

    /* JADX INFO: renamed from: newParameterValue, reason: merged with bridge method [inline-methods] */
    public BasicValue m64newParameterValue(boolean isInstanceMethod, int local, Type type) {
        type.getClass();
        FunctionalArgument functionalArgumentIfExists$org_jetbrains_kotlin_backend = this.inliner.getFunctionalArgumentIfExists$org_jetbrains_kotlin_backend(local);
        if (functionalArgumentIfExists$org_jetbrains_kotlin_backend != null) {
            return new FunctionalArgumentValue(functionalArgumentIfExists$org_jetbrains_kotlin_backend, newValue(type));
        }
        BasicValue basicValueNewValue = newValue(type);
        basicValueNewValue.getClass();
        return basicValueNewValue;
    }

    public BasicValue unaryOperation(AbstractInsnNode insn, BasicValue value) {
        insn.getClass();
        value.getClass();
        return wrapArgumentInValueIfNeeded(insn, super.unaryOperation(insn, value));
    }
}
