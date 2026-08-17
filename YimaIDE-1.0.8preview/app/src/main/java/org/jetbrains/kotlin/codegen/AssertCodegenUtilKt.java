package org.jetbrains.kotlin.codegen;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.resolve.jvm.diagnostics.JvmDeclarationOrigin;
import org.jetbrains.org.objectweb.asm.Label;
import org.jetbrains.org.objectweb.asm.MethodVisitor;
import org.jetbrains.org.objectweb.asm.Type;
import org.jetbrains.org.objectweb.asm.commons.InstructionAdapter;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.FieldInsnNode;
import org.jetbrains.org.objectweb.asm.tree.InsnList;
import org.jetbrains.org.objectweb.asm.tree.LdcInsnNode;
import org.jetbrains.org.objectweb.asm.tree.MethodInsnNode;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0002\u001a\u00020\u0003*\u00020\u0004\u001a\u001e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0001\u001a\u0016\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\u0001\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"ASSERTIONS_DISABLED_FIELD_NAME", Argument.Delimiters.none, "isCheckAssertionsStatus", Argument.Delimiters.none, "Lorg/jetbrains/org/objectweb/asm/tree/FieldInsnNode;", "generateAssertionsDisabledFieldInitialization", Argument.Delimiters.none, "classBuilder", "Lorg/jetbrains/kotlin/codegen/ClassBuilder;", "clInitBuilder", "Lorg/jetbrains/org/objectweb/asm/MethodVisitor;", "className", "rewriteAssertionsDisabledFieldInitialization", "methodNode", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "org.jetbrains.kotlin:backend"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class AssertCodegenUtilKt {
    public static final String ASSERTIONS_DISABLED_FIELD_NAME = "$assertionsDisabled";

    public static final void generateAssertionsDisabledFieldInitialization(ClassBuilder classBuilder, MethodVisitor methodVisitor, String str) {
        classBuilder.getClass();
        methodVisitor.getClass();
        str.getClass();
        classBuilder.newField(JvmDeclarationOrigin.NO_ORIGIN, 4120, ASSERTIONS_DISABLED_FIELD_NAME, "Z", null, null);
        Label label = new Label();
        Label label2 = new Label();
        InstructionAdapter instructionAdapter = new InstructionAdapter(methodVisitor);
        instructionAdapter.mark(new Label());
        instructionAdapter.aconst(Type.getObjectType(str));
        instructionAdapter.invokevirtual("java/lang/Class", "desiredAssertionStatus", "()Z", false);
        instructionAdapter.ifne(label);
        instructionAdapter.iconst(1);
        instructionAdapter.goTo(label2);
        instructionAdapter.mark(label);
        instructionAdapter.iconst(0);
        instructionAdapter.mark(label2);
        instructionAdapter.putstatic(classBuilder.getThisName(), ASSERTIONS_DISABLED_FIELD_NAME, "Z");
    }

    public static final boolean isCheckAssertionsStatus(FieldInsnNode fieldInsnNode) {
        fieldInsnNode.getClass();
        return fieldInsnNode.getOpcode() == 178 && Intrinsics.areEqual(fieldInsnNode.name, ASSERTIONS_DISABLED_FIELD_NAME) && Intrinsics.areEqual(fieldInsnNode.desc, Type.BOOLEAN_TYPE.getDescriptor());
    }

    /* JADX WARN: Code duplicated, block: B:31:0x0080  */
    public static final void rewriteAssertionsDisabledFieldInitialization(MethodNode methodNode, String str) {
        Object next;
        AbstractInsnNode previous;
        methodNode.getClass();
        str.getClass();
        InsnList insnList = methodNode.instructions;
        insnList.getClass();
        Iterator it = new InsnSequence(insnList).iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            FieldInsnNode fieldInsnNode = (AbstractInsnNode) next;
            if (fieldInsnNode instanceof FieldInsnNode) {
                FieldInsnNode fieldInsnNode2 = fieldInsnNode;
                if (fieldInsnNode2.getOpcode() == 179 && Intrinsics.areEqual(fieldInsnNode2.name, ASSERTIONS_DISABLED_FIELD_NAME)) {
                    break;
                }
            }
        }
        AbstractInsnNode abstractInsnNode = (AbstractInsnNode) next;
        if (abstractInsnNode != null) {
            MethodInsnNode previous2 = abstractInsnNode.getPrevious();
            while (previous2 != null) {
                if (previous2 instanceof MethodInsnNode) {
                    MethodInsnNode methodInsnNode = previous2;
                    if (methodInsnNode.getOpcode() == 182 && Intrinsics.areEqual(methodInsnNode.owner, "java/lang/Class") && Intrinsics.areEqual(methodInsnNode.name, "desiredAssertionStatus") && Intrinsics.areEqual(methodInsnNode.desc, "()Z")) {
                        break;
                    }
                }
                previous2 = previous2.getPrevious();
            }
            if (previous2 != null) {
                previous = previous2.getPrevious();
            } else {
                previous = null;
            }
        } else {
            previous = null;
        }
        LdcInsnNode ldcInsnNode = previous instanceof LdcInsnNode ? (LdcInsnNode) previous : null;
        if (ldcInsnNode != null) {
            ldcInsnNode.cst = Type.getObjectType(str);
        }
    }
}
