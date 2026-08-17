package com.sun.org.apache.bcel.internal.util;

import com.intellij.psi.PsiKeyword;
import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.bcel.internal.classfile.Utility;
import com.sun.org.apache.bcel.internal.generic.AllocationInstruction;
import com.sun.org.apache.bcel.internal.generic.ArrayInstruction;
import com.sun.org.apache.bcel.internal.generic.ArrayType;
import com.sun.org.apache.bcel.internal.generic.BranchHandle;
import com.sun.org.apache.bcel.internal.generic.BranchInstruction;
import com.sun.org.apache.bcel.internal.generic.CHECKCAST;
import com.sun.org.apache.bcel.internal.generic.CPInstruction;
import com.sun.org.apache.bcel.internal.generic.CodeExceptionGen;
import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.ConstantPushInstruction;
import com.sun.org.apache.bcel.internal.generic.EmptyVisitor;
import com.sun.org.apache.bcel.internal.generic.FieldInstruction;
import com.sun.org.apache.bcel.internal.generic.IINC;
import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;
import com.sun.org.apache.bcel.internal.generic.Instruction;
import com.sun.org.apache.bcel.internal.generic.InstructionConst;
import com.sun.org.apache.bcel.internal.generic.InstructionHandle;
import com.sun.org.apache.bcel.internal.generic.InvokeInstruction;
import com.sun.org.apache.bcel.internal.generic.LDC;
import com.sun.org.apache.bcel.internal.generic.LDC2_W;
import com.sun.org.apache.bcel.internal.generic.LocalVariableInstruction;
import com.sun.org.apache.bcel.internal.generic.MULTIANEWARRAY;
import com.sun.org.apache.bcel.internal.generic.MethodGen;
import com.sun.org.apache.bcel.internal.generic.NEWARRAY;
import com.sun.org.apache.bcel.internal.generic.ObjectType;
import com.sun.org.apache.bcel.internal.generic.RET;
import com.sun.org.apache.bcel.internal.generic.ReturnInstruction;
import com.sun.org.apache.bcel.internal.generic.Select;
import com.sun.org.apache.bcel.internal.generic.Type;
import com.sun.org.apache.xalan.internal.templates.Constants;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
class BCELFactory extends EmptyVisitor {
    private static final String CONSTANT_PREFIX = Const.class.getSimpleName().concat(Constants.ATTRVAL_THIS);
    private final Map<Instruction, InstructionHandle> branchMap = new HashMap();
    private final List<BranchInstruction> branches = new ArrayList();
    private final ConstantPoolGen constantPoolGen;
    private final MethodGen methodGen;
    private final PrintWriter printWriter;

    public BCELFactory(MethodGen methodGen, PrintWriter printWriter) {
        this.methodGen = methodGen;
        this.constantPoolGen = methodGen.getConstantPool();
        this.printWriter = printWriter;
    }

    public static /* synthetic */ void a(BCELFactory bCELFactory, BranchInstruction branchInstruction) {
        BranchHandle branchHandle = (BranchHandle) bCELFactory.branchMap.get(branchInstruction);
        String str = branchInstruction.getName() + "_" + branchHandle.getPosition();
        int position = branchHandle.getTarget().getPosition();
        bCELFactory.printWriter.println("    " + str + ".setTarget(ih_" + position + ");");
        if (branchInstruction instanceof Select) {
            InstructionHandle[] targets = ((Select) branchInstruction).getTargets();
            for (int i = 0; i < targets.length; i++) {
                int position2 = targets[i].getPosition();
                bCELFactory.printWriter.println("    " + str + ".setTarget(" + i + ", ih_" + position2 + ");");
            }
        }
    }

    private void createConstant(Object obj) {
        String str;
        String string = obj.toString();
        if (obj instanceof String) {
            string = "\"" + Utility.convertString(string) + '\"';
        } else if (obj instanceof Character) {
            string = "(char)0x" + Integer.toHexString(((Character) obj).charValue());
        } else {
            if (obj instanceof Float) {
                Float f = (Float) obj;
                if (Float.isNaN(f.floatValue())) {
                    str = "Float.NaN";
                } else if (f.floatValue() == Float.POSITIVE_INFINITY) {
                    str = "Float.POSITIVE_INFINITY";
                } else if (f.floatValue() == Float.NEGATIVE_INFINITY) {
                    str = "Float.NEGATIVE_INFINITY";
                } else {
                    str = string + "f";
                }
            } else if (obj instanceof Double) {
                Double d = (Double) obj;
                if (Double.isNaN(d.doubleValue())) {
                    str = "Double.NaN";
                } else if (d.doubleValue() == Double.POSITIVE_INFINITY) {
                    str = "Double.POSITIVE_INFINITY";
                } else if (d.doubleValue() == Double.NEGATIVE_INFINITY) {
                    str = "Double.NEGATIVE_INFINITY";
                } else {
                    str = string + "d";
                }
            } else if (obj instanceof Long) {
                string = string + "L";
            } else if (obj instanceof ObjectType) {
                string = "new ObjectType(\"" + ((ObjectType) obj).getClassName() + "\")";
            } else if (obj instanceof ArrayType) {
                ArrayType arrayType = (ArrayType) obj;
                string = "new ArrayType(" + BCELifier.printType(arrayType.getBasicType()) + ", " + arrayType.getDimensions() + ")";
            }
            string = str;
        }
        this.printWriter.println("il.append(new PUSH(_cp, " + string + "));");
    }

    private void updateBranchTargets() {
        this.branches.forEach(new Consumer() { // from class: com.sun.org.apache.bcel.internal.util.b
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                BCELFactory.a(this.b, (BranchInstruction) obj);
            }
        });
    }

    private void updateExceptionHandlers() {
        for (CodeExceptionGen codeExceptionGen : this.methodGen.getExceptionHandlers()) {
            this.printWriter.println("    method.addExceptionHandler(ih_" + codeExceptionGen.getStartPC().getPosition() + ", ih_" + codeExceptionGen.getEndPC().getPosition() + ", ih_" + codeExceptionGen.getHandlerPC().getPosition() + ", " + (codeExceptionGen.getCatchType() == null ? PsiKeyword.NULL : BCELifier.printType(codeExceptionGen.getCatchType())) + ");");
        }
    }

    private boolean visitInstruction(Instruction instruction) {
        if (InstructionConst.getInstruction(instruction.getOpcode()) == null || (instruction instanceof ConstantPushInstruction) || (instruction instanceof ReturnInstruction)) {
            return false;
        }
        this.printWriter.println("il.append(InstructionConst." + instruction.getName().toUpperCase(Locale.ENGLISH) + ");");
        return true;
    }

    public void start() {
        if (this.methodGen.isAbstract() || this.methodGen.isNative()) {
            return;
        }
        for (InstructionHandle start = this.methodGen.getInstructionList().getStart(); start != null; start = start.getNext()) {
            Instruction instruction = start.getInstruction();
            boolean z = instruction instanceof BranchInstruction;
            if (z) {
                this.branchMap.put(instruction, start);
            }
            if (start.hasTargeters()) {
                PrintWriter printWriter = this.printWriter;
                if (z) {
                    printWriter.println("    InstructionHandle ih_" + start.getPosition() + ";");
                } else {
                    printWriter.print("    InstructionHandle ih_" + start.getPosition() + " = ");
                }
            } else {
                this.printWriter.print("    ");
            }
            if (!visitInstruction(instruction)) {
                instruction.accept(this);
            }
        }
        updateBranchTargets();
        updateExceptionHandlers();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sun.org.apache.bcel.internal.generic.EmptyVisitor, com.sun.org.apache.bcel.internal.generic.Visitor
    public void visitAllocationInstruction(AllocationInstruction allocationInstruction) {
        short dimensions;
        Type type = allocationInstruction instanceof CPInstruction ? ((CPInstruction) allocationInstruction).getType(this.constantPoolGen) : ((NEWARRAY) allocationInstruction).getType();
        short opcode = ((Instruction) allocationInstruction).getOpcode();
        if (opcode != 197) {
            dimensions = 1;
            switch (opcode) {
                case 187:
                    this.printWriter.println("il.append(_factory.createNew(\"" + ((ObjectType) type).getClassName() + "\"));");
                    return;
                case 188:
                    break;
                case 189:
                    break;
                default:
                    qf1.a("Unhandled opcode: ", opcode);
                    return;
            }
            this.printWriter.println("il.append(_factory.createNewArray(" + BCELifier.printType(type) + ", (short) " + ((int) dimensions) + "));");
        }
        dimensions = ((MULTIANEWARRAY) allocationInstruction).getDimensions();
        if (type instanceof ArrayType) {
            type = ((ArrayType) type).getBasicType();
        }
        this.printWriter.println("il.append(_factory.createNewArray(" + BCELifier.printType(type) + ", (short) " + ((int) dimensions) + "));");
    }

    @Override // com.sun.org.apache.bcel.internal.generic.EmptyVisitor, com.sun.org.apache.bcel.internal.generic.Visitor
    public void visitArrayInstruction(ArrayInstruction arrayInstruction) {
        short opcode = arrayInstruction.getOpcode();
        Type type = arrayInstruction.getType(this.constantPoolGen);
        String str = opcode < 79 ? "Load" : "Store";
        this.printWriter.println("il.append(_factory.createArray" + str + "(" + BCELifier.printType(type) + "));");
    }

    @Override // com.sun.org.apache.bcel.internal.generic.EmptyVisitor, com.sun.org.apache.bcel.internal.generic.Visitor
    public void visitBranchInstruction(BranchInstruction branchInstruction) {
        PrintWriter printWriter;
        BranchHandle branchHandle = (BranchHandle) this.branchMap.get(branchInstruction);
        int position = branchHandle.getPosition();
        String str = branchInstruction.getName() + "_" + position;
        boolean z = branchInstruction instanceof Select;
        String str2 = PsiKeyword.NULL;
        if (z) {
            this.branches.add(branchInstruction);
            StringBuilder sb = new StringBuilder("new int[] { ");
            int[] matchs = ((Select) branchInstruction).getMatchs();
            int i = 0;
            for (int i2 = 0; i2 < matchs.length; i2++) {
                sb.append(matchs[i2]);
                if (i2 < matchs.length - 1) {
                    sb.append(", ");
                }
            }
            sb.append(" }");
            this.printWriter.print("Select " + str + " = new " + branchInstruction.getName().toUpperCase(Locale.ENGLISH) + "(" + ((Object) sb) + ", new InstructionHandle[] { ");
            while (true) {
                int length = matchs.length;
                printWriter = this.printWriter;
                if (i >= length) {
                    break;
                }
                printWriter.print(PsiKeyword.NULL);
                if (i < matchs.length - 1) {
                    this.printWriter.print(", ");
                }
                i++;
            }
            printWriter.println(" }, null);");
        } else {
            int position2 = branchHandle.getTarget().getPosition();
            if (position > position2) {
                str2 = "ih_" + position2;
            } else {
                this.branches.add(branchInstruction);
            }
            this.printWriter.println("    BranchInstruction " + str + " = _factory.createBranchInstruction(" + CONSTANT_PREFIX + branchInstruction.getName().toUpperCase(Locale.ENGLISH) + ", " + str2 + ");");
        }
        boolean zHasTargeters = branchHandle.hasTargeters();
        PrintWriter printWriter2 = this.printWriter;
        if (!zHasTargeters) {
            printWriter2.println("    il.append(" + str + ");");
            return;
        }
        printWriter2.println("    ih_" + position + " = il.append(" + str + ");");
    }

    @Override // com.sun.org.apache.bcel.internal.generic.EmptyVisitor, com.sun.org.apache.bcel.internal.generic.Visitor
    public void visitCHECKCAST(CHECKCAST checkcast) {
        Type type = checkcast.getType(this.constantPoolGen);
        this.printWriter.println("il.append(_factory.createCheckCast(" + BCELifier.printType(type) + "));");
    }

    @Override // com.sun.org.apache.bcel.internal.generic.EmptyVisitor, com.sun.org.apache.bcel.internal.generic.Visitor
    public void visitConstantPushInstruction(ConstantPushInstruction constantPushInstruction) {
        createConstant(constantPushInstruction.getValue());
    }

    @Override // com.sun.org.apache.bcel.internal.generic.EmptyVisitor, com.sun.org.apache.bcel.internal.generic.Visitor
    public void visitFieldInstruction(FieldInstruction fieldInstruction) {
        short opcode = fieldInstruction.getOpcode();
        String className = fieldInstruction.getReferenceType(this.constantPoolGen).getClassName();
        String fieldName = fieldInstruction.getFieldName(this.constantPoolGen);
        Type fieldType = fieldInstruction.getFieldType(this.constantPoolGen);
        this.printWriter.println("il.append(_factory.createFieldAccess(\"" + className + "\", \"" + fieldName + "\", " + BCELifier.printType(fieldType) + ", " + CONSTANT_PREFIX + Const.getOpcodeName(opcode).toUpperCase(Locale.ENGLISH) + "));");
    }

    @Override // com.sun.org.apache.bcel.internal.generic.EmptyVisitor, com.sun.org.apache.bcel.internal.generic.Visitor
    public void visitINSTANCEOF(INSTANCEOF r3) {
        Type type = r3.getType(this.constantPoolGen);
        this.printWriter.println("il.append(_factory.createInstanceOf(" + BCELifier.printType(type) + "));");
    }

    @Override // com.sun.org.apache.bcel.internal.generic.EmptyVisitor, com.sun.org.apache.bcel.internal.generic.Visitor
    public void visitInvokeInstruction(InvokeInstruction invokeInstruction) {
        short opcode = invokeInstruction.getOpcode();
        String className = invokeInstruction.getReferenceType(this.constantPoolGen).getClassName();
        String methodName = invokeInstruction.getMethodName(this.constantPoolGen);
        Type returnType = invokeInstruction.getReturnType(this.constantPoolGen);
        Type[] argumentTypes = invokeInstruction.getArgumentTypes(this.constantPoolGen);
        this.printWriter.println("il.append(_factory.createInvoke(\"" + className + "\", \"" + methodName + "\", " + BCELifier.printType(returnType) + ", " + BCELifier.printArgumentTypes(argumentTypes) + ", " + CONSTANT_PREFIX + Const.getOpcodeName(opcode).toUpperCase(Locale.ENGLISH) + "));");
    }

    @Override // com.sun.org.apache.bcel.internal.generic.EmptyVisitor, com.sun.org.apache.bcel.internal.generic.Visitor
    public void visitLDC(LDC ldc) {
        createConstant(ldc.getValue(this.constantPoolGen));
    }

    @Override // com.sun.org.apache.bcel.internal.generic.EmptyVisitor, com.sun.org.apache.bcel.internal.generic.Visitor
    public void visitLDC2_W(LDC2_W ldc2_w) {
        createConstant(ldc2_w.getValue(this.constantPoolGen));
    }

    @Override // com.sun.org.apache.bcel.internal.generic.EmptyVisitor, com.sun.org.apache.bcel.internal.generic.Visitor
    public void visitLocalVariableInstruction(LocalVariableInstruction localVariableInstruction) {
        short opcode = localVariableInstruction.getOpcode();
        Type type = localVariableInstruction.getType(this.constantPoolGen);
        if (opcode == 132) {
            this.printWriter.println("il.append(new IINC(" + localVariableInstruction.getIndex() + ", " + ((IINC) localVariableInstruction).getIncrement() + "));");
            return;
        }
        String str = opcode < 54 ? "Load" : "Store";
        this.printWriter.println("il.append(_factory.create" + str + "(" + BCELifier.printType(type) + ", " + localVariableInstruction.getIndex() + "));");
    }

    @Override // com.sun.org.apache.bcel.internal.generic.EmptyVisitor, com.sun.org.apache.bcel.internal.generic.Visitor
    public void visitRET(RET ret) {
        this.printWriter.println("il.append(new RET(" + ret.getIndex() + "));");
    }

    @Override // com.sun.org.apache.bcel.internal.generic.EmptyVisitor, com.sun.org.apache.bcel.internal.generic.Visitor
    public void visitReturnInstruction(ReturnInstruction returnInstruction) {
        Type type = returnInstruction.getType(this.constantPoolGen);
        this.printWriter.println("il.append(_factory.createReturn(" + BCELifier.printType(type) + "));");
    }
}
