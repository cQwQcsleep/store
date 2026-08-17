package org.jetbrains.kotlin.codegen.inline;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.optimization.nullCheck.RedundantNullCheckMethodTransformerKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.resolve.jvm.AsmTypes;
import org.jetbrains.org.objectweb.asm.Type;
import org.jetbrains.org.objectweb.asm.commons.InstructionAdapter;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.FieldInsnNode;
import org.jetbrains.org.objectweb.asm.tree.IincInsnNode;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;
import org.jetbrains.org.objectweb.asm.tree.TryCatchBlockNode;
import org.jetbrains.org.objectweb.asm.tree.VarInsnNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0018\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\n\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\f\u0010\u0007\u001a\u00020\u0001*\u00020\bH\u0002\u001a\u0014\u0010\u0010\u001a\u00020\u0011*\u00020\u00122\u0006\u0010\u0013\u001a\u00020\fH\u0002\u001a\n\u0010\u0014\u001a\u00020\u0011*\u00020\u0012\u001a\n\u0010\u0015\u001a\u00020\u0011*\u00020\u0012\u001a\n\u0010\u0016\u001a\u00020\u0011*\u00020\u0012\u001a\n\u0010\u0017\u001a\u00020\u0011*\u00020\u0012\u001a\f\u0010\u0018\u001a\u00020\u0001*\u00020\bH\u0000\u001a\f\u0010\u0019\u001a\u00020\u0001*\u00020\bH\u0000\u001a\f\u0010\u001a\u001a\u00020\u0001*\u00020\bH\u0000\u001a\f\u0010\u001b\u001a\u00020\u0001*\u00020\bH\u0000\"\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\r\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000e\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000f\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"canInlineArgumentsInPlace", Argument.Delimiters.none, "methodNode", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "whitelistedStaticFields", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/codegen/inline/FieldSignature;", "isProhibitedDuringArgumentsEvaluation", "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "opcodeProhibitedDuringArgumentsEvaluation", Argument.Delimiters.none, "MARKER_INPLACE_CALL_START", Argument.Delimiters.none, "MARKER_INPLACE_ARGUMENT_START", "MARKER_INPLACE_ARGUMENT_END", "MARKER_INPLACE_CALL_END", "addMarker", Argument.Delimiters.none, "Lorg/jetbrains/org/objectweb/asm/commons/InstructionAdapter;", ModuleXmlParser.NAME, "addInplaceCallStartMarker", "addInplaceCallEndMarker", "addInplaceArgumentStartMarker", "addInplaceArgumentEndMarker", "isInplaceCallStartMarker", "isInplaceCallEndMarker", "isInplaceArgumentStartMarker", "isInplaceArgumentEndMarker", "org.jetbrains.kotlin:backend"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class InlineArgumentsInPlaceKt {
    private static final boolean[] opcodeProhibitedDuringArgumentsEvaluation;
    private static final Set<FieldSignature> whitelistedStaticFields = SetsKt.hashSetOf(new FieldSignature[]{new FieldSignature("kotlin/Result", "Companion", "Lkotlin/Result$Companion;"), new FieldSignature("kotlin/_Assertions", "ENABLED", "Z")});

    static {
        boolean[] zArr = new boolean[256];
        for (int i = 153; i < 178; i++) {
            zArr[i] = true;
        }
        zArr[198] = true;
        zArr[199] = true;
        zArr[191] = true;
        zArr[179] = true;
        zArr[181] = true;
        zArr[182] = true;
        zArr[183] = true;
        zArr[184] = true;
        zArr[185] = true;
        zArr[186] = true;
        zArr[194] = true;
        zArr[195] = true;
        zArr[108] = true;
        zArr[109] = true;
        zArr[112] = true;
        zArr[113] = true;
        zArr[192] = true;
        zArr[188] = true;
        zArr[189] = true;
        zArr[197] = true;
        for (int i2 = 46; i2 < 54; i2++) {
            zArr[i2] = true;
        }
        for (int i3 = 79; i3 < 87; i3++) {
            zArr[i3] = true;
        }
        opcodeProhibitedDuringArgumentsEvaluation = zArr;
    }

    public static final void addInplaceArgumentEndMarker(InstructionAdapter instructionAdapter) {
        instructionAdapter.getClass();
        addMarker(instructionAdapter, "<INPLACE-ARGUMENT-END>");
    }

    public static final void addInplaceArgumentStartMarker(InstructionAdapter instructionAdapter) {
        instructionAdapter.getClass();
        addMarker(instructionAdapter, "<INPLACE-ARGUMENT-START>");
    }

    public static final void addInplaceCallEndMarker(InstructionAdapter instructionAdapter) {
        instructionAdapter.getClass();
        addMarker(instructionAdapter, "<INPLACE-CALL-END>");
    }

    public static final void addInplaceCallStartMarker(InstructionAdapter instructionAdapter) {
        instructionAdapter.getClass();
        addMarker(instructionAdapter, "<INPLACE-CALL-START>");
    }

    private static final void addMarker(InstructionAdapter instructionAdapter, String str) {
        instructionAdapter.visitMethodInsn(184, InlineCodegenUtilsKt.INLINE_MARKER_CLASS_NAME, str, "()V", false);
    }

    public static final boolean canInlineArgumentsInPlace(MethodNode methodNode) {
        int opcode;
        boolean z;
        methodNode.getClass();
        List list = methodNode.tryCatchBlocks;
        list.getClass();
        HashSet hashSet = new HashSet();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            hashSet.add(((TryCatchBlockNode) it.next()).start);
        }
        Type[] argumentTypes = Type.getArgumentTypes(methodNode.desc);
        ArrayList arrayList = new ArrayList(argumentTypes.length + 1);
        if ((methodNode.access & 8) == 0) {
            arrayList.add(AsmTypes.OBJECT_TYPE);
        }
        CollectionsKt.addAll(arrayList, argumentTypes);
        Iterator it2 = arrayList.iterator();
        boolean z2 = false;
        int size = 0;
        while (it2.hasNext()) {
            size += ((Type) it2.next()).getSize();
        }
        FieldInsnNode first = methodNode.instructions.getFirst();
        int size2 = 0;
        int i = 0;
        while (first != null && size2 < size) {
            if (CollectionsKt.contains(hashSet, first) || isProhibitedDuringArgumentsEvaluation(first)) {
                return z2;
            }
            if (first.getOpcode() == 178) {
                FieldInsnNode fieldInsnNode = first;
                String str = fieldInsnNode.owner;
                str.getClass();
                z = z2;
                String str2 = fieldInsnNode.name;
                str2.getClass();
                String str3 = fieldInsnNode.desc;
                str3.getClass();
                if (!whitelistedStaticFields.contains(new FieldSignature(str, str2, str3))) {
                    return z;
                }
            } else {
                z = z2;
            }
            int opcode2 = first.getOpcode();
            if (54 <= opcode2 && opcode2 < 59 && ((VarInsnNode) first).var < size) {
                return z;
            }
            if (first.getOpcode() == 132 && ((IincInsnNode) first).var < size) {
                return z;
            }
            int opcode3 = first.getOpcode();
            if (21 > opcode3 || opcode3 >= 26) {
                first = first.getNext();
            } else if (first.getOpcode() == 25 && RedundantNullCheckMethodTransformerKt.isParameterCheckedForNull(first)) {
                first = first.getNext().getNext().getNext();
            } else {
                VarInsnNode varInsnNode = (VarInsnNode) first;
                int i2 = varInsnNode.var;
                if (i2 == size2) {
                    size2 += ((Type) arrayList.get(i)).getSize();
                    i++;
                    do {
                        first = first.getNext();
                        if (first == null || first.getOpcode() != varInsnNode.getOpcode()) {
                            break;
                        }
                    } while (((VarInsnNode) first).var == i2);
                } else {
                    if (i2 < size) {
                        return z;
                    }
                    first = varInsnNode.getNext();
                }
            }
            z2 = z;
        }
        boolean z3 = z2;
        if (size2 < size) {
            return z3;
        }
        while (first != null) {
            int opcode4 = first.getOpcode();
            if ((21 > opcode4 || opcode4 >= 26) && (54 > (opcode = first.getOpcode()) || opcode >= 59)) {
                if (first.getOpcode() == 132 && ((IincInsnNode) first).var < size) {
                    return z3;
                }
            } else if (((VarInsnNode) first).var < size) {
                return z3;
            }
            first = first.getNext();
        }
        return true;
    }

    public static final boolean isInplaceArgumentEndMarker(AbstractInsnNode abstractInsnNode) {
        abstractInsnNode.getClass();
        return InlineCodegenUtilsKt.isInlineMarker(abstractInsnNode, "<INPLACE-ARGUMENT-END>");
    }

    public static final boolean isInplaceArgumentStartMarker(AbstractInsnNode abstractInsnNode) {
        abstractInsnNode.getClass();
        return InlineCodegenUtilsKt.isInlineMarker(abstractInsnNode, "<INPLACE-ARGUMENT-START>");
    }

    public static final boolean isInplaceCallEndMarker(AbstractInsnNode abstractInsnNode) {
        abstractInsnNode.getClass();
        return InlineCodegenUtilsKt.isInlineMarker(abstractInsnNode, "<INPLACE-CALL-END>");
    }

    public static final boolean isInplaceCallStartMarker(AbstractInsnNode abstractInsnNode) {
        abstractInsnNode.getClass();
        return InlineCodegenUtilsKt.isInlineMarker(abstractInsnNode, "<INPLACE-CALL-START>");
    }

    private static final boolean isProhibitedDuringArgumentsEvaluation(AbstractInsnNode abstractInsnNode) {
        boolean[] zArr = opcodeProhibitedDuringArgumentsEvaluation;
        int length = zArr.length;
        int opcode = abstractInsnNode.getOpcode();
        return opcode >= 0 && opcode < length && zArr[abstractInsnNode.getOpcode()];
    }
}
