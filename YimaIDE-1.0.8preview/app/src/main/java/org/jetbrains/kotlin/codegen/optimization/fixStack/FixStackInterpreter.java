package org.jetbrains.kotlin.codegen.optimization.fixStack;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.Handle;
import org.jetbrains.org.objectweb.asm.Type;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0002H\u0014J\b\u0010\u0006\u001a\u00020\u0002H\u0014J\b\u0010\u0007\u001a\u00020\u0002H\u0014J\b\u0010\b\u001a\u00020\u0002H\u0014J\b\u0010\t\u001a\u00020\u0002H\u0014J\b\u0010\n\u001a\u00020\u0002H\u0014J\b\u0010\u000b\u001a\u00020\u0002H\u0014J\b\u0010\f\u001a\u00020\u0002H\u0014J\b\u0010\r\u001a\u00020\u0002H\u0014J\b\u0010\u000e\u001a\u00020\u0002H\u0014J\u0010\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u0011H\u0014J\u0010\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u0011H\u0014J\u0010\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u0011H\u0014J\u0010\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0016H\u0014J\u0010\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0011H\u0014J\u0010\u0010\u0019\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0002H\u0014J\u0018\u0010\u001a\u001a\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0002H\u0016J\u0018\u0010\u001e\u001a\u00020\u00022\u0006\u0010\u001f\u001a\u00020\u00022\u0006\u0010 \u001a\u00020\u0002H\u0016¨\u0006!"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/fixStack/FixStackInterpreter;", "Lorg/jetbrains/kotlin/codegen/optimization/fixStack/BasicTypeInterpreter;", "Lorg/jetbrains/kotlin/codegen/optimization/fixStack/FixStackValue;", "<init>", "()V", "uninitializedValue", "booleanValue", "charValue", "byteValue", "shortValue", "intValue", "longValue", "floatValue", "doubleValue", "nullValue", "objectValue", ModuleXmlParser.TYPE, "Lorg/jetbrains/org/objectweb/asm/Type;", "arrayValue", "methodValue", "handleValue", "handle", "Lorg/jetbrains/org/objectweb/asm/Handle;", "typeConstValue", "typeConst", "aaLoadValue", "copyOperation", "insn", "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "value", "merge", "v", "w", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FixStackInterpreter extends BasicTypeInterpreter<FixStackValue> {
    @Override // org.jetbrains.kotlin.codegen.optimization.fixStack.BasicTypeInterpreter
    public FixStackValue arrayValue(Type type) {
        type.getClass();
        return FixStackValue.OBJECT;
    }

    public FixStackValue copyOperation(AbstractInsnNode insn, FixStackValue value) {
        insn.getClass();
        value.getClass();
        switch (insn.getOpcode()) {
            case 21:
                return FixStackValue.INT;
            case 22:
                return FixStackValue.LONG;
            case 23:
                return FixStackValue.FLOAT;
            case 24:
                return FixStackValue.DOUBLE;
            case 25:
                return FixStackValue.OBJECT;
            default:
                return value;
        }
    }

    @Override // org.jetbrains.kotlin.codegen.optimization.fixStack.BasicTypeInterpreter
    public FixStackValue handleValue(Handle handle) {
        handle.getClass();
        return FixStackValue.OBJECT;
    }

    public FixStackValue merge(FixStackValue v, FixStackValue w) {
        v.getClass();
        w.getClass();
        if (v == w) {
            return v;
        }
        md6.a("Mismatching value kinds: ", v, " != ", w);
        return null;
    }

    @Override // org.jetbrains.kotlin.codegen.optimization.fixStack.BasicTypeInterpreter
    public FixStackValue methodValue(Type type) {
        type.getClass();
        return FixStackValue.OBJECT;
    }

    @Override // org.jetbrains.kotlin.codegen.optimization.fixStack.BasicTypeInterpreter
    public FixStackValue objectValue(Type type) {
        type.getClass();
        return FixStackValue.OBJECT;
    }

    @Override // org.jetbrains.kotlin.codegen.optimization.fixStack.BasicTypeInterpreter
    public FixStackValue typeConstValue(Type typeConst) {
        typeConst.getClass();
        return FixStackValue.OBJECT;
    }

    @Override // org.jetbrains.kotlin.codegen.optimization.fixStack.BasicTypeInterpreter
    public FixStackValue booleanValue() {
        return FixStackValue.INT;
    }

    @Override // org.jetbrains.kotlin.codegen.optimization.fixStack.BasicTypeInterpreter
    public FixStackValue byteValue() {
        return FixStackValue.INT;
    }

    @Override // org.jetbrains.kotlin.codegen.optimization.fixStack.BasicTypeInterpreter
    public FixStackValue charValue() {
        return FixStackValue.INT;
    }

    @Override // org.jetbrains.kotlin.codegen.optimization.fixStack.BasicTypeInterpreter
    public FixStackValue doubleValue() {
        return FixStackValue.DOUBLE;
    }

    @Override // org.jetbrains.kotlin.codegen.optimization.fixStack.BasicTypeInterpreter
    public FixStackValue floatValue() {
        return FixStackValue.FLOAT;
    }

    @Override // org.jetbrains.kotlin.codegen.optimization.fixStack.BasicTypeInterpreter
    public FixStackValue intValue() {
        return FixStackValue.INT;
    }

    @Override // org.jetbrains.kotlin.codegen.optimization.fixStack.BasicTypeInterpreter
    public FixStackValue longValue() {
        return FixStackValue.LONG;
    }

    @Override // org.jetbrains.kotlin.codegen.optimization.fixStack.BasicTypeInterpreter
    public FixStackValue nullValue() {
        return FixStackValue.OBJECT;
    }

    @Override // org.jetbrains.kotlin.codegen.optimization.fixStack.BasicTypeInterpreter
    public FixStackValue shortValue() {
        return FixStackValue.INT;
    }

    @Override // org.jetbrains.kotlin.codegen.optimization.fixStack.BasicTypeInterpreter
    public FixStackValue uninitializedValue() {
        return FixStackValue.UNINITIALIZED;
    }

    @Override // org.jetbrains.kotlin.codegen.optimization.fixStack.BasicTypeInterpreter
    public FixStackValue aaLoadValue(FixStackValue arrayValue) {
        arrayValue.getClass();
        return FixStackValue.OBJECT;
    }
}
