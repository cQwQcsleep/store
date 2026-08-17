package org.jetbrains.kotlin.codegen.inline.coroutines;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.codegen.inline.InlineCodegenUtilsKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.Type;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.FieldInsnNode;
import org.jetbrains.org.objectweb.asm.tree.analysis.BasicInterpreter;
import org.jetbrains.org.objectweb.asm.tree.analysis.BasicValue;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u000e\u0010\b\u001a\u0004\u0018\u00010\t*\u00020\u0007H\u0002J\u001c\u0010\n\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\u000b\u001a\u0004\u0018\u00010\u0005H\u0016J\u001c\u0010\f\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\u000b\u001a\u0004\u0018\u00010\u0005H\u0016J\u001e\u0010\r\u001a\u0004\u0018\u00010\u00052\b\u0010\u000e\u001a\u0004\u0018\u00010\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\u0005H\u0016¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/coroutines/CapturedLambdaInterpreter;", "Lorg/jetbrains/org/objectweb/asm/tree/analysis/BasicInterpreter;", "<init>", "()V", "newOperation", "Lorg/jetbrains/org/objectweb/asm/tree/analysis/BasicValue;", "insn", "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "fieldLoad", "Lorg/jetbrains/kotlin/codegen/inline/coroutines/PossibleLambdaLoad;", "copyOperation", "value", "unaryOperation", "merge", "v", "w", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final class CapturedLambdaInterpreter extends BasicInterpreter {
    public CapturedLambdaInterpreter() {
        super(589824);
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0051, code lost:
    
        if (org.jetbrains.kotlin.codegen.inline.InlineCodegenUtilsKt.isCapturedFieldName(r5) != false) goto L17;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final PossibleLambdaLoad fieldLoad(AbstractInsnNode abstractInsnNode) {
        if (!(abstractInsnNode instanceof FieldInsnNode)) {
            return null;
        }
        FieldInsnNode fieldInsnNode = (FieldInsnNode) abstractInsnNode;
        String str = fieldInsnNode.desc;
        str.getClass();
        if (StringsKt.startsWith$default(str, 'L', false, 2, (Object) null)) {
            String internalName = Type.getType(fieldInsnNode.desc).getInternalName();
            internalName.getClass();
            if (InlineCodegenUtilsKt.isNumberedFunctionInternalName(internalName)) {
                if (fieldInsnNode.getOpcode() == 178) {
                    String str2 = fieldInsnNode.name;
                    str2.getClass();
                    if (!StringsKt.startsWith$default(str2, "$$$$", false, 2, (Object) null)) {
                    }
                    return new PossibleLambdaLoad(abstractInsnNode);
                }
                if (fieldInsnNode.getOpcode() == 180) {
                    String str3 = fieldInsnNode.name;
                    str3.getClass();
                }
            }
        }
        return null;
    }

    public BasicValue copyOperation(AbstractInsnNode insn, BasicValue value) {
        insn.getClass();
        return insn.getOpcode() == 25 ? new PossibleLambdaLoad(insn) : super.copyOperation(insn, value);
    }

    public BasicValue merge(BasicValue v, BasicValue w) {
        return ((v instanceof PossibleLambdaLoad) && (w instanceof PossibleLambdaLoad) && Intrinsics.areEqual(((PossibleLambdaLoad) v).getInsn(), ((PossibleLambdaLoad) w).getInsn())) ? v : super.merge(v, w);
    }

    /* JADX INFO: renamed from: newOperation, reason: merged with bridge method [inline-methods] */
    public BasicValue m67newOperation(AbstractInsnNode insn) {
        PossibleLambdaLoad possibleLambdaLoadFieldLoad;
        insn.getClass();
        return (insn.getOpcode() != 178 || (possibleLambdaLoadFieldLoad = fieldLoad(insn)) == null) ? super.newOperation(insn) : possibleLambdaLoadFieldLoad;
    }

    public BasicValue unaryOperation(AbstractInsnNode insn, BasicValue value) {
        PossibleLambdaLoad possibleLambdaLoadFieldLoad;
        insn.getClass();
        return (insn.getOpcode() != 180 || (possibleLambdaLoadFieldLoad = fieldLoad(insn)) == null) ? super.unaryOperation(insn, value) : possibleLambdaLoadFieldLoad;
    }
}
