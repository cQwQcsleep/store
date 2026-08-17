package org.jetbrains.kotlin.codegen.optimization.fixStack;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.inline.InlineCodegenUtilsKt;
import org.jetbrains.kotlin.codegen.optimization.OptimizationMethodVisitor;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.resolve.jvm.AsmTypes;
import org.jetbrains.org.objectweb.asm.Handle;
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
import org.jetbrains.org.objectweb.asm.tree.analysis.Interpreter;
import org.jetbrains.org.objectweb.asm.tree.analysis.Value;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\r\u0010\u0006\u001a\u00028\u0000H$¢\u0006\u0002\u0010\u0007J\r\u0010\b\u001a\u00028\u0000H$¢\u0006\u0002\u0010\u0007J\r\u0010\t\u001a\u00028\u0000H$¢\u0006\u0002\u0010\u0007J\r\u0010\n\u001a\u00028\u0000H$¢\u0006\u0002\u0010\u0007J\r\u0010\u000b\u001a\u00028\u0000H$¢\u0006\u0002\u0010\u0007J\r\u0010\f\u001a\u00028\u0000H$¢\u0006\u0002\u0010\u0007J\r\u0010\r\u001a\u00028\u0000H$¢\u0006\u0002\u0010\u0007J\r\u0010\u000e\u001a\u00028\u0000H$¢\u0006\u0002\u0010\u0007J\r\u0010\u000f\u001a\u00028\u0000H$¢\u0006\u0002\u0010\u0007J\r\u0010\u0010\u001a\u00028\u0000H$¢\u0006\u0002\u0010\u0007J\u0015\u0010\u0011\u001a\u00028\u00002\u0006\u0010\u0012\u001a\u00020\u0013H$¢\u0006\u0002\u0010\u0014J\u0015\u0010\u0015\u001a\u00028\u00002\u0006\u0010\u0012\u001a\u00020\u0013H$¢\u0006\u0002\u0010\u0014J\u0015\u0010\u0016\u001a\u00028\u00002\u0006\u0010\u0012\u001a\u00020\u0013H$¢\u0006\u0002\u0010\u0014J\u0015\u0010\u0017\u001a\u00028\u00002\u0006\u0010\u0018\u001a\u00020\u0019H$¢\u0006\u0002\u0010\u001aJ\u0015\u0010\u001b\u001a\u00028\u00002\u0006\u0010\u001c\u001a\u00020\u0013H$¢\u0006\u0002\u0010\u0014J\u0015\u0010\u001d\u001a\u00028\u00002\u0006\u0010\u0015\u001a\u00028\u0000H$¢\u0006\u0002\u0010\u001eJ\u0019\u0010\u001f\u001a\u0004\u0018\u00018\u00002\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016¢\u0006\u0002\u0010\u0014J\u0015\u0010 \u001a\u00028\u00002\u0006\u0010!\u001a\u00020\"H\u0016¢\u0006\u0002\u0010#J\u0017\u0010$\u001a\u0004\u0018\u00018\u00002\u0006\u0010%\u001a\u00020&H\u0016¢\u0006\u0002\u0010'J'\u0010(\u001a\u0004\u0018\u00018\u00002\u0006\u0010%\u001a\u00020&2\u0006\u0010)\u001a\u00028\u00002\u0006\u0010*\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010+J/\u0010,\u001a\u0004\u0018\u00018\u00002\u0006\u0010%\u001a\u00020&2\u0006\u0010)\u001a\u00028\u00002\u0006\u0010*\u001a\u00028\u00002\u0006\u0010-\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010.J%\u0010/\u001a\u0004\u0018\u00018\u00002\u0006\u0010%\u001a\u00020&2\f\u00100\u001a\b\u0012\u0004\u0012\u00028\u000001H\u0016¢\u0006\u0002\u00102J)\u00103\u001a\u0002042\u0006\u0010%\u001a\u00020&2\b\u00105\u001a\u0004\u0018\u00018\u00002\b\u00106\u001a\u0004\u0018\u00018\u0000H\u0016¢\u0006\u0002\u00107J\u001f\u00108\u001a\u0004\u0018\u00018\u00002\u0006\u0010%\u001a\u00020&2\u0006\u00105\u001a\u00028\u0000H\u0016¢\u0006\u0002\u00109¨\u0006:"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/fixStack/BasicTypeInterpreter;", "V", "Lorg/jetbrains/org/objectweb/asm/tree/analysis/Value;", "Lorg/jetbrains/org/objectweb/asm/tree/analysis/Interpreter;", "<init>", "()V", "uninitializedValue", "()Lorg/jetbrains/org/objectweb/asm/tree/analysis/Value;", "booleanValue", "charValue", "byteValue", "shortValue", "intValue", "longValue", "floatValue", "doubleValue", "nullValue", "objectValue", ModuleXmlParser.TYPE, "Lorg/jetbrains/org/objectweb/asm/Type;", "(Lorg/jetbrains/org/objectweb/asm/Type;)Lorg/jetbrains/org/objectweb/asm/tree/analysis/Value;", "arrayValue", "methodValue", "handleValue", "handle", "Lorg/jetbrains/org/objectweb/asm/Handle;", "(Lorg/jetbrains/org/objectweb/asm/Handle;)Lorg/jetbrains/org/objectweb/asm/tree/analysis/Value;", "typeConstValue", "typeConst", "aaLoadValue", "(Lorg/jetbrains/org/objectweb/asm/tree/analysis/Value;)Lorg/jetbrains/org/objectweb/asm/tree/analysis/Value;", "newValue", "newEmptyValue", "local", Argument.Delimiters.none, "(I)Lorg/jetbrains/org/objectweb/asm/tree/analysis/Value;", "newOperation", "insn", "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "(Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;)Lorg/jetbrains/org/objectweb/asm/tree/analysis/Value;", "binaryOperation", "value1", "value2", "(Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;Lorg/jetbrains/org/objectweb/asm/tree/analysis/Value;Lorg/jetbrains/org/objectweb/asm/tree/analysis/Value;)Lorg/jetbrains/org/objectweb/asm/tree/analysis/Value;", "ternaryOperation", "value3", "(Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;Lorg/jetbrains/org/objectweb/asm/tree/analysis/Value;Lorg/jetbrains/org/objectweb/asm/tree/analysis/Value;Lorg/jetbrains/org/objectweb/asm/tree/analysis/Value;)Lorg/jetbrains/org/objectweb/asm/tree/analysis/Value;", "naryOperation", "values", Argument.Delimiters.none, "(Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;Ljava/util/List;)Lorg/jetbrains/org/objectweb/asm/tree/analysis/Value;", "returnOperation", Argument.Delimiters.none, "value", "expected", "(Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;Lorg/jetbrains/org/objectweb/asm/tree/analysis/Value;Lorg/jetbrains/org/objectweb/asm/tree/analysis/Value;)V", "unaryOperation", "(Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;Lorg/jetbrains/org/objectweb/asm/tree/analysis/Value;)Lorg/jetbrains/org/objectweb/asm/tree/analysis/Value;", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class BasicTypeInterpreter<V extends Value> extends Interpreter<V> {
    public BasicTypeInterpreter() {
        super(589824);
    }

    public abstract V aaLoadValue(V arrayValue);

    public abstract V arrayValue(Type type);

    /* JADX WARN: Code duplicated, block: B:15:0x0035  */
    /* JADX WARN: Code duplicated, block: B:17:0x003a  */
    /* JADX WARN: Code duplicated, block: B:19:0x003f  */
    /* JADX WARN: Code duplicated, block: B:21:0x0044  */
    public V binaryOperation(AbstractInsnNode insn, V value1, V value2) {
        insn.getClass();
        value1.getClass();
        value2.getClass();
        int opcode = insn.getOpcode();
        if (opcode != 181) {
            switch (opcode) {
                case 46:
                case 51:
                case 52:
                case 53:
                    return (V) intValue();
                case 47:
                    return (V) longValue();
                case 48:
                    return (V) floatValue();
                case 49:
                    return (V) doubleValue();
                case OptimizationMethodVisitor.MEMORY_LIMIT_BY_METHOD_MB /* 50 */:
                    return (V) aaLoadValue(value1);
                default:
                    switch (opcode) {
                        case 96:
                        case 100:
                        case 104:
                        case 108:
                        case 112:
                            return (V) intValue();
                        case 97:
                        case 101:
                        case 105:
                        case 109:
                        case 113:
                            return (V) longValue();
                        case 98:
                        case 102:
                        case 106:
                        case 110:
                        case 114:
                            return (V) floatValue();
                        case 99:
                        case 103:
                        case 107:
                        case 111:
                        case 115:
                            return (V) doubleValue();
                        default:
                            switch (opcode) {
                                case 120:
                                case 122:
                                case 124:
                                case 126:
                                case 128:
                                case 130:
                                    return (V) intValue();
                                case 121:
                                case 123:
                                case 125:
                                case 127:
                                case 129:
                                case 131:
                                    return (V) longValue();
                                default:
                                    switch (opcode) {
                                        case 148:
                                        case 149:
                                        case 150:
                                        case 151:
                                        case 152:
                                            return (V) intValue();
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
                                                    z01.a("Unexpected instruction: ", InlineCodegenUtilsKt.getInsnOpcodeText(insn));
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

    public abstract V booleanValue();

    public abstract V byteValue();

    public abstract V charValue();

    public abstract V doubleValue();

    public abstract V floatValue();

    public abstract V handleValue(Handle handle);

    public abstract V intValue();

    public abstract V longValue();

    public abstract V methodValue(Type type);

    public V naryOperation(AbstractInsnNode insn, List<? extends V> values) {
        insn.getClass();
        values.getClass();
        int opcode = insn.getOpcode();
        if (opcode != 186) {
            return opcode != 197 ? (V) newValue(Type.getReturnType(((MethodInsnNode) insn).desc)) : (V) newValue(Type.getType(((MultiANewArrayInsnNode) insn).desc));
        }
        return (V) newValue(Type.getReturnType(((InvokeDynamicInsnNode) insn).desc));
    }

    public V newEmptyValue(int local) {
        return (V) uninitializedValue();
    }

    public V newOperation(AbstractInsnNode insn) {
        insn.getClass();
        int opcode = insn.getOpcode();
        if (opcode == 178) {
            return (V) newValue(Type.getType(((FieldInsnNode) insn).desc));
        }
        if (opcode == 187) {
            return (V) newValue(Type.getObjectType(((TypeInsnNode) insn).desc));
        }
        switch (opcode) {
            case 1:
                return (V) nullValue();
            case MavenComparableVersion.Item.LIST_ITEM /* 2 */:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                return (V) intValue();
            case 9:
            case 10:
                return (V) longValue();
            case 11:
            case 12:
            case 13:
                return (V) floatValue();
            case 14:
            case 15:
                return (V) doubleValue();
            case 16:
            case 17:
                return (V) intValue();
            case 18:
                Object obj = ((LdcInsnNode) insn).cst;
                if (obj instanceof Integer) {
                    return (V) intValue();
                }
                if (obj instanceof Float) {
                    return (V) floatValue();
                }
                if (obj instanceof Long) {
                    return (V) longValue();
                }
                if (obj instanceof Double) {
                    return (V) doubleValue();
                }
                if (obj instanceof String) {
                    Type type = AsmTypes.JAVA_STRING_TYPE;
                    type.getClass();
                    return (V) objectValue(type);
                }
                if (obj instanceof Handle) {
                    return (V) handleValue((Handle) obj);
                }
                if (obj instanceof Type) {
                    return (V) typeConstValue((Type) obj);
                }
                aca.a("Illegal LDC constant ", obj);
                return null;
            default:
                z01.a("Unexpected instruction: ", InlineCodegenUtilsKt.getInsnOpcodeText(insn));
                return null;
        }
    }

    public V newValue(Type type) {
        if (type == null) {
            return (V) uninitializedValue();
        }
        switch (type.getSort()) {
            case MavenComparableVersion.Item.INTEGER_ITEM /* 0 */:
                return null;
            case 1:
                return (V) booleanValue();
            case MavenComparableVersion.Item.LIST_ITEM /* 2 */:
                return (V) charValue();
            case 3:
                return (V) byteValue();
            case 4:
                return (V) shortValue();
            case 5:
                return (V) intValue();
            case 6:
                return (V) floatValue();
            case 7:
                return (V) longValue();
            case 8:
                return (V) doubleValue();
            case 9:
                return (V) arrayValue(type);
            case 10:
                return (V) objectValue(type);
            case 11:
                return (V) methodValue(type);
            default:
                s22.a("Unexpected type: ", type);
                return null;
        }
    }

    public abstract V nullValue();

    public abstract V objectValue(Type type);

    public void returnOperation(AbstractInsnNode insn, V value, V expected) {
        insn.getClass();
    }

    public abstract V shortValue();

    public V ternaryOperation(AbstractInsnNode insn, V value1, V value2, V value3) {
        insn.getClass();
        value1.getClass();
        value2.getClass();
        value3.getClass();
        return null;
    }

    public abstract V typeConstValue(Type typeConst);

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.org.objectweb.asm.tree.analysis.AnalyzerException */
    /* JADX WARN: Code duplicated, block: B:46:0x00d3  */
    /* JADX WARN: Code duplicated, block: B:48:0x00d8  */
    /* JADX WARN: Code duplicated, block: B:50:0x00dd  */
    /* JADX WARN: Code duplicated, block: B:52:0x00e2  */
    public V unaryOperation(AbstractInsnNode insn, V value) throws AnalyzerException {
        insn.getClass();
        value.getClass();
        int opcode = insn.getOpcode();
        if (opcode != 179) {
            if (opcode == 180) {
                return (V) newValue(Type.getType(((FieldInsnNode) insn).desc));
            }
            if (opcode != 198 && opcode != 199) {
                switch (opcode) {
                    case 116:
                        return (V) intValue();
                    case 117:
                        return (V) longValue();
                    case 118:
                        return (V) floatValue();
                    case 119:
                        return (V) doubleValue();
                    default:
                        switch (opcode) {
                            case 132:
                            case 136:
                            case 139:
                            case 142:
                            case 145:
                            case 146:
                            case 147:
                                return (V) intValue();
                            case 133:
                            case 140:
                            case 143:
                                return (V) longValue();
                            case 134:
                            case 137:
                            case 144:
                                return (V) floatValue();
                            case 135:
                            case 138:
                            case 141:
                                return (V) doubleValue();
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
                                                        switch (((IntInsnNode) insn).operand) {
                                                            case 4:
                                                                return (V) newValue(Type.getType("[Z"));
                                                            case 5:
                                                                return (V) newValue(Type.getType("[C"));
                                                            case 6:
                                                                return (V) newValue(Type.getType("[F"));
                                                            case 7:
                                                                return (V) newValue(Type.getType("[D"));
                                                            case 8:
                                                                return (V) newValue(Type.getType("[B"));
                                                            case 9:
                                                                return (V) newValue(Type.getType("[S"));
                                                            case 10:
                                                                return (V) newValue(Type.getType("[I"));
                                                            case 11:
                                                                return (V) newValue(Type.getType("[J"));
                                                            default:
                                                                throw new AnalyzerException(insn, "Invalid array type");
                                                        }
                                                    case 189:
                                                        return (V) newValue(Type.getType("[" + Type.getObjectType(((TypeInsnNode) insn).desc)));
                                                    case 190:
                                                        return (V) intValue();
                                                    case 191:
                                                        return null;
                                                    case 192:
                                                        return (V) newValue(Type.getObjectType(((TypeInsnNode) insn).desc));
                                                    case 193:
                                                        return (V) intValue();
                                                    case 194:
                                                    case 195:
                                                        break;
                                                    default:
                                                        z01.a("Unexpected instruction: ", InlineCodegenUtilsKt.getInsnOpcodeText(insn));
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

    public abstract V uninitializedValue();
}
