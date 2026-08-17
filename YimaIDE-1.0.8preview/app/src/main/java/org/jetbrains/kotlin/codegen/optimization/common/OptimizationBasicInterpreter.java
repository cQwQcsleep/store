package org.jetbrains.kotlin.codegen.optimization.common;

import java.util.List;
import org.jetbrains.kotlin.codegen.AsmUtil;
import org.jetbrains.kotlin.codegen.inline.InlineCodegenUtilsKt;
import org.jetbrains.kotlin.codegen.optimization.OptimizationMethodVisitor;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.resolve.jvm.AsmTypes;
import org.jetbrains.org.objectweb.asm.Handle;
import org.jetbrains.org.objectweb.asm.Opcodes;
import org.jetbrains.org.objectweb.asm.Type;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.FieldInsnNode;
import org.jetbrains.org.objectweb.asm.tree.IntInsnNode;
import org.jetbrains.org.objectweb.asm.tree.InvokeDynamicInsnNode;
import org.jetbrains.org.objectweb.asm.tree.LdcInsnNode;
import org.jetbrains.org.objectweb.asm.tree.MethodInsnNode;
import org.jetbrains.org.objectweb.asm.tree.MultiANewArrayInsnNode;
import org.jetbrains.org.objectweb.asm.tree.TypeInsnNode;
import org.jetbrains.org.objectweb.asm.tree.analysis.AnalyzerException;
import org.jetbrains.org.objectweb.asm.tree.analysis.BasicValue;
import org.jetbrains.org.objectweb.asm.tree.analysis.Interpreter;
import org.jetbrains.org.objectweb.asm.tree.analysis.Value;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
public class OptimizationBasicInterpreter extends Interpreter<BasicValue> implements Opcodes {
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str;
        int i2;
        switch (i) {
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
                str = "@NotNull method %s.%s must not return null";
                break;
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
        }
        switch (i) {
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
                i2 = 2;
                break;
            default:
                i2 = 3;
                break;
        }
        Object[] objArr = new Object[i2];
        switch (i) {
            case 3:
                objArr[0] = "value1";
                break;
            case 4:
                objArr[0] = "value2";
                break;
            case 5:
            case 14:
                objArr[0] = "v";
                break;
            case 6:
                objArr[0] = "w";
                break;
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
                objArr[0] = "org/jetbrains/kotlin/codegen/optimization/common/OptimizationBasicInterpreter";
                break;
            case 15:
                objArr[0] = "a";
                break;
            case 16:
                objArr[0] = "b";
                break;
            default:
                objArr[0] = "insn";
                break;
        }
        switch (i) {
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
                objArr[1] = "merge";
                break;
            default:
                objArr[1] = "org/jetbrains/kotlin/codegen/optimization/common/OptimizationBasicInterpreter";
                break;
        }
        switch (i) {
            case 1:
                objArr[2] = "copyOperation";
                break;
            case MavenComparableVersion.Item.LIST_ITEM /* 2 */:
            case 3:
            case 4:
                objArr[2] = "binaryOperation";
                break;
            case 5:
            case 6:
                objArr[2] = "merge";
                break;
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
                break;
            case 14:
                objArr[2] = "isReference";
                break;
            case 15:
            case 16:
                objArr[2] = "mergeReferenceTypes";
                break;
            default:
                objArr[2] = "newOperation";
                break;
        }
        String str2 = String.format(str, objArr);
        switch (i) {
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
                throw new IllegalStateException(str2);
            default:
                throw new IllegalArgumentException(str2);
        }
    }

    public OptimizationBasicInterpreter() {
        super(589824);
    }

    private static boolean isReference(BasicValue basicValue) {
        if (basicValue == null) {
            $$$reportNull$$$0(14);
        }
        return basicValue.getType().getSort() == 10 || basicValue.getType().getSort() == 9;
    }

    private BasicValue mergeReferenceTypes(Type type, Type type2) {
        if (type == null) {
            $$$reportNull$$$0(15);
        }
        if (type2 == null) {
            $$$reportNull$$$0(16);
        }
        int i = 0;
        while (type.getSort() == 9 && type2.getSort() == 9) {
            type = AsmUtil.correctElementType(type);
            type2 = AsmUtil.correctElementType(type2);
            i++;
        }
        if (i == 0) {
            return StrictBasicValue.REFERENCE_VALUE;
        }
        if (AsmUtil.isPrimitive(type) || AsmUtil.isPrimitive(type2)) {
            i--;
        }
        StringBuilder sb = new StringBuilder();
        while (true) {
            int i2 = i - 1;
            if (i <= 0) {
                sb.append(AsmTypes.OBJECT_TYPE.getDescriptor());
                return m69newValue(Type.getType(sb.toString()));
            }
            sb.append("[");
            i = i2;
        }
    }

    /* JADX WARN: Code duplicated, block: B:27:0x0052  */
    /* JADX WARN: Code duplicated, block: B:29:0x0055  */
    /* JADX WARN: Code duplicated, block: B:31:0x0058  */
    /* JADX WARN: Code duplicated, block: B:33:0x005b  */
    @Override // 
    public BasicValue binaryOperation(AbstractInsnNode abstractInsnNode, BasicValue basicValue, BasicValue basicValue2) throws AnalyzerException {
        if (abstractInsnNode == null) {
            $$$reportNull$$$0(2);
        }
        if (basicValue == null) {
            $$$reportNull$$$0(3);
        }
        if (basicValue2 == null) {
            $$$reportNull$$$0(4);
        }
        int opcode = abstractInsnNode.getOpcode();
        if (opcode != 181) {
            switch (opcode) {
                case 46:
                case 51:
                case 52:
                case 53:
                    return StrictBasicValue.INT_VALUE;
                case 47:
                    return StrictBasicValue.LONG_VALUE;
                case 48:
                    return StrictBasicValue.FLOAT_VALUE;
                case 49:
                    return StrictBasicValue.DOUBLE_VALUE;
                case OptimizationMethodVisitor.MEMORY_LIMIT_BY_METHOD_MB /* 50 */:
                    Type type = basicValue.getType();
                    return (type == null || type.getSort() != 9) ? StrictBasicValue.NULL_VALUE : new StrictBasicValue(AsmUtil.correctElementType(type));
                default:
                    switch (opcode) {
                        case 96:
                        case 100:
                        case 104:
                        case 108:
                        case 112:
                            return StrictBasicValue.INT_VALUE;
                        case 97:
                        case 101:
                        case 105:
                        case 109:
                        case 113:
                            return StrictBasicValue.LONG_VALUE;
                        case 98:
                        case 102:
                        case 106:
                        case 110:
                        case 114:
                            return StrictBasicValue.FLOAT_VALUE;
                        case 99:
                        case 103:
                        case 107:
                        case 111:
                        case 115:
                            return StrictBasicValue.DOUBLE_VALUE;
                        default:
                            switch (opcode) {
                                case 120:
                                case 122:
                                case 124:
                                case 126:
                                case 128:
                                case 130:
                                    return StrictBasicValue.INT_VALUE;
                                case 121:
                                case 123:
                                case 125:
                                case 127:
                                case 129:
                                case 131:
                                    return StrictBasicValue.LONG_VALUE;
                                default:
                                    switch (opcode) {
                                        case 148:
                                        case 149:
                                        case 150:
                                        case 151:
                                        case 152:
                                            return StrictBasicValue.INT_VALUE;
                                        default:
                                            switch (opcode) {
                                                case 159:
                                                case 160:
                                                case 161:
                                                case 162:
                                                case 163:
                                                case 164:
                                                case 165:
                                                case 166:
                                                    break;
                                                default:
                                                    z01.a("Unexpected instruction: ", InlineCodegenUtilsKt.getInsnOpcodeText(abstractInsnNode));
                                                    return null;
                                            }
                                            break;
                                    }
                                    break;
                            }
                            break;
                    }
                    break;
            }
        }
        return null;
    }

    @Override // 
    public BasicValue copyOperation(AbstractInsnNode abstractInsnNode, BasicValue basicValue) throws AnalyzerException {
        if (abstractInsnNode == null) {
            $$$reportNull$$$0(1);
        }
        return basicValue;
    }

    @Override // 
    public BasicValue merge(BasicValue basicValue, BasicValue basicValue2) {
        if (basicValue == null) {
            $$$reportNull$$$0(5);
        }
        if (basicValue2 == null) {
            $$$reportNull$$$0(6);
        }
        if (basicValue.equals(basicValue2)) {
            return basicValue;
        }
        StrictBasicValue strictBasicValue = StrictBasicValue.UNINITIALIZED_VALUE;
        if (basicValue == strictBasicValue || basicValue2 == strictBasicValue) {
            if (strictBasicValue == null) {
                $$$reportNull$$$0(8);
            }
        } else {
            if (isReference(basicValue) && isReference(basicValue2)) {
                StrictBasicValue strictBasicValue2 = StrictBasicValue.NULL_VALUE;
                if (basicValue == strictBasicValue2) {
                    StrictBasicValue strictBasicValueM69newValue = m69newValue(basicValue2.getType());
                    if (strictBasicValueM69newValue == null) {
                        $$$reportNull$$$0(9);
                    }
                    return strictBasicValueM69newValue;
                }
                if (basicValue2 == strictBasicValue2) {
                    StrictBasicValue strictBasicValueM69newValue2 = m69newValue(basicValue.getType());
                    if (strictBasicValueM69newValue2 == null) {
                        $$$reportNull$$$0(10);
                    }
                    return strictBasicValueM69newValue2;
                }
                BasicValue basicValueMergeReferenceTypes = mergeReferenceTypes(basicValue2.getType(), basicValue.getType());
                if (basicValueMergeReferenceTypes == null) {
                    $$$reportNull$$$0(11);
                }
                return basicValueMergeReferenceTypes;
            }
            if (basicValue.getType().getOpcode(54) == 54 && basicValue2.getType().getOpcode(54) == 54) {
                StrictBasicValue strictBasicValue3 = StrictBasicValue.INT_VALUE;
                if (strictBasicValue3 == null) {
                    $$$reportNull$$$0(12);
                }
                return strictBasicValue3;
            }
            if (strictBasicValue == null) {
                $$$reportNull$$$0(13);
                return strictBasicValue;
            }
        }
        return strictBasicValue;
    }

    public BasicValue naryOperation(AbstractInsnNode abstractInsnNode, List<? extends BasicValue> list) throws AnalyzerException {
        int opcode = abstractInsnNode.getOpcode();
        if (opcode == 197) {
            return m69newValue(Type.getType(((MultiANewArrayInsnNode) abstractInsnNode).desc));
        }
        return opcode == 186 ? m69newValue(Type.getReturnType(((InvokeDynamicInsnNode) abstractInsnNode).desc)) : m69newValue(Type.getReturnType(((MethodInsnNode) abstractInsnNode).desc));
    }

    @Override // 
    /* JADX INFO: renamed from: newOperation, reason: merged with bridge method [inline-methods] */
    public BasicValue mo59newOperation(AbstractInsnNode abstractInsnNode) throws AnalyzerException {
        if (abstractInsnNode == null) {
            $$$reportNull$$$0(0);
        }
        int opcode = abstractInsnNode.getOpcode();
        if (opcode == 178) {
            return m69newValue(Type.getType(((FieldInsnNode) abstractInsnNode).desc));
        }
        if (opcode == 187) {
            return m69newValue(Type.getObjectType(((TypeInsnNode) abstractInsnNode).desc));
        }
        switch (opcode) {
            case 1:
                return StrictBasicValue.NULL_VALUE;
            case MavenComparableVersion.Item.LIST_ITEM /* 2 */:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                return StrictBasicValue.INT_VALUE;
            case 9:
            case 10:
                return StrictBasicValue.LONG_VALUE;
            case 11:
            case 12:
            case 13:
                return StrictBasicValue.FLOAT_VALUE;
            case 14:
            case 15:
                return StrictBasicValue.DOUBLE_VALUE;
            case 16:
            case 17:
                return StrictBasicValue.INT_VALUE;
            case 18:
                Object obj = ((LdcInsnNode) abstractInsnNode).cst;
                if (obj instanceof Integer) {
                    return StrictBasicValue.INT_VALUE;
                }
                if (obj instanceof Float) {
                    return StrictBasicValue.FLOAT_VALUE;
                }
                if (obj instanceof Long) {
                    return StrictBasicValue.LONG_VALUE;
                }
                if (obj instanceof Double) {
                    return StrictBasicValue.DOUBLE_VALUE;
                }
                if (obj instanceof String) {
                    return m69newValue(Type.getObjectType("java/lang/String"));
                }
                if (!(obj instanceof Type)) {
                    if (obj instanceof Handle) {
                        return m69newValue(Type.getObjectType("java/lang/invoke/MethodHandle"));
                    }
                    aca.a("Illegal LDC constant ", obj);
                    return null;
                }
                int sort = ((Type) obj).getSort();
                if (sort == 10 || sort == 9) {
                    return m69newValue(Type.getObjectType("java/lang/Class"));
                }
                if (sort == 11) {
                    return m69newValue(Type.getObjectType("java/lang/invoke/MethodType"));
                }
                aca.a("Illegal LDC constant ", obj);
                return null;
            default:
                z01.a("Unexpected instruction: ", InlineCodegenUtilsKt.getInsnOpcodeText(abstractInsnNode));
                return null;
        }
    }

    /* JADX INFO: renamed from: newValue, reason: merged with bridge method [inline-methods] */
    public StrictBasicValue m69newValue(Type type) {
        if (type == null) {
            return StrictBasicValue.UNINITIALIZED_VALUE;
        }
        switch (type.getSort()) {
            case MavenComparableVersion.Item.INTEGER_ITEM /* 0 */:
                return null;
            case 1:
                return StrictBasicValue.BOOLEAN_VALUE;
            case MavenComparableVersion.Item.LIST_ITEM /* 2 */:
                return StrictBasicValue.CHAR_VALUE;
            case 3:
                return StrictBasicValue.BYTE_VALUE;
            case 4:
                return StrictBasicValue.SHORT_VALUE;
            case 5:
                return StrictBasicValue.INT_VALUE;
            case 6:
                return StrictBasicValue.FLOAT_VALUE;
            case 7:
                return StrictBasicValue.LONG_VALUE;
            case 8:
                return StrictBasicValue.DOUBLE_VALUE;
            case 9:
            case 10:
                return new StrictBasicValue(type);
            default:
                jt6.a("Unknown type sort ", type.getSort());
                return null;
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.org.objectweb.asm.tree.analysis.AnalyzerException */
    /* JADX WARN: Code duplicated, block: B:49:0x00ce  */
    /* JADX WARN: Code duplicated, block: B:51:0x00d1  */
    /* JADX WARN: Code duplicated, block: B:53:0x00d4  */
    /* JADX WARN: Code duplicated, block: B:55:0x00d7  */
    @Override // 
    public BasicValue unaryOperation(AbstractInsnNode abstractInsnNode, BasicValue basicValue) throws AnalyzerException {
        int opcode = abstractInsnNode.getOpcode();
        if (opcode != 179) {
            if (opcode == 180) {
                return m69newValue(Type.getType(((FieldInsnNode) abstractInsnNode).desc));
            }
            if (opcode != 198 && opcode != 199) {
                switch (opcode) {
                    case 116:
                        return StrictBasicValue.INT_VALUE;
                    case 117:
                        return StrictBasicValue.LONG_VALUE;
                    case 118:
                        return StrictBasicValue.FLOAT_VALUE;
                    case 119:
                        return StrictBasicValue.DOUBLE_VALUE;
                    default:
                        switch (opcode) {
                            case 132:
                            case 136:
                            case 139:
                            case 142:
                            case 145:
                            case 146:
                            case 147:
                                return StrictBasicValue.INT_VALUE;
                            case 133:
                            case 140:
                            case 143:
                                return StrictBasicValue.LONG_VALUE;
                            case 134:
                            case 137:
                            case 144:
                                return StrictBasicValue.FLOAT_VALUE;
                            case 135:
                            case 138:
                            case 141:
                                return StrictBasicValue.DOUBLE_VALUE;
                            default:
                                switch (opcode) {
                                    default:
                                        switch (opcode) {
                                            case 170:
                                            case 171:
                                            case 172:
                                            case 173:
                                            case 174:
                                            case 175:
                                            case 176:
                                                break;
                                            default:
                                                switch (opcode) {
                                                    case 188:
                                                        switch (((IntInsnNode) abstractInsnNode).operand) {
                                                            case 4:
                                                                return m69newValue(Type.getType("[Z"));
                                                            case 5:
                                                                return m69newValue(Type.getType("[C"));
                                                            case 6:
                                                                return m69newValue(Type.getType("[F"));
                                                            case 7:
                                                                return m69newValue(Type.getType("[D"));
                                                            case 8:
                                                                return m69newValue(Type.getType("[B"));
                                                            case 9:
                                                                return m69newValue(Type.getType("[S"));
                                                            case 10:
                                                                return m69newValue(Type.getType("[I"));
                                                            case 11:
                                                                return m69newValue(Type.getType("[J"));
                                                            default:
                                                                throw new AnalyzerException(abstractInsnNode, "Invalid array type");
                                                        }
                                                    case 189:
                                                        return m69newValue(Type.getType("[" + Type.getObjectType(((TypeInsnNode) abstractInsnNode).desc)));
                                                    case 190:
                                                        return StrictBasicValue.INT_VALUE;
                                                    case 191:
                                                        return null;
                                                    case 192:
                                                        StrictBasicValue strictBasicValue = StrictBasicValue.NULL_VALUE;
                                                        return basicValue == strictBasicValue ? strictBasicValue : m69newValue(Type.getObjectType(((TypeInsnNode) abstractInsnNode).desc));
                                                    case 193:
                                                        return StrictBasicValue.INT_VALUE;
                                                    case 194:
                                                    case 195:
                                                        break;
                                                    default:
                                                        z01.a("Unexpected instruction: ", InlineCodegenUtilsKt.getInsnOpcodeText(abstractInsnNode));
                                                        return null;
                                                }
                                                break;
                                        }
                                    case 153:
                                    case 154:
                                    case 155:
                                    case 156:
                                    case 157:
                                    case 158:
                                        return null;
                                }
                                break;
                        }
                        break;
                }
            }
            return null;
        }
        return null;
    }

    public void returnOperation(AbstractInsnNode abstractInsnNode, BasicValue basicValue, BasicValue basicValue2) throws AnalyzerException {
    }

    @Override // 
    public BasicValue ternaryOperation(AbstractInsnNode abstractInsnNode, BasicValue basicValue, BasicValue basicValue2, BasicValue basicValue3) throws AnalyzerException {
        return null;
    }

    /* JADX INFO: renamed from: naryOperation */
    public /* bridge */ /* synthetic */ Value mo53naryOperation(AbstractInsnNode abstractInsnNode, List list) throws AnalyzerException {
        return naryOperation(abstractInsnNode, (List<? extends BasicValue>) list);
    }
}
