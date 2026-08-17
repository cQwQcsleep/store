package com.sun.tools.javap;

import com.sun.tools.classfile.Code_attribute;
import com.sun.tools.classfile.ConstantPool;
import com.sun.tools.classfile.ConstantPoolException;
import com.sun.tools.classfile.DescriptorException;
import com.sun.tools.classfile.Instruction;
import com.sun.tools.classfile.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class CodeWriter extends BasicWriter {
    private AttributeWriter attrWriter;
    private ClassWriter classWriter;
    private ConstantWriter constantWriter;
    Instruction.KindVisitor<Void, Integer> instructionPrinter;
    private LocalVariableTableWriter localVariableTableWriter;
    private LocalVariableTypeTableWriter localVariableTypeTableWriter;
    private Options options;
    private SourceWriter sourceWriter;
    private StackMapWriter stackMapWriter;
    private TryBlockWriter tryBlockWriter;
    private TypeAnnotationWriter typeAnnotationWriter;

    public CodeWriter(Context context) {
        super(context);
        this.instructionPrinter = new Instruction.KindVisitor<Void, Integer>() { // from class: com.sun.tools.javap.CodeWriter.1
            @Override // com.sun.tools.classfile.Instruction.KindVisitor
            public Void visitArrayType(Instruction instruction, Instruction.TypeKind typeKind, Integer num) {
                CodeWriter.this.print(" " + typeKind.name);
                return null;
            }

            @Override // com.sun.tools.classfile.Instruction.KindVisitor
            public Void visitBranch(Instruction instruction, int i, Integer num) {
                CodeWriter.this.print(Integer.valueOf(instruction.getPC() + i));
                return null;
            }

            @Override // com.sun.tools.classfile.Instruction.KindVisitor
            public Void visitConstantPoolRef(Instruction instruction, int i, Integer num) {
                CodeWriter.this.print("#" + i);
                CodeWriter.this.tab();
                CodeWriter.this.print("// ");
                CodeWriter.this.printConstant(i);
                return null;
            }

            @Override // com.sun.tools.classfile.Instruction.KindVisitor
            public Void visitConstantPoolRefAndValue(Instruction instruction, int i, int i2, Integer num) {
                CodeWriter.this.print("#" + i + ",  " + i2);
                CodeWriter.this.tab();
                CodeWriter.this.print("// ");
                CodeWriter.this.printConstant(i);
                return null;
            }

            @Override // com.sun.tools.classfile.Instruction.KindVisitor
            public Void visitLocal(Instruction instruction, int i, Integer num) {
                CodeWriter.this.print(Integer.valueOf(i));
                return null;
            }

            @Override // com.sun.tools.classfile.Instruction.KindVisitor
            public Void visitLocalAndValue(Instruction instruction, int i, int i2, Integer num) {
                CodeWriter.this.print(i + ", " + i2);
                return null;
            }

            @Override // com.sun.tools.classfile.Instruction.KindVisitor
            public Void visitLookupSwitch(Instruction instruction, int i, int i2, int[] iArr, int[] iArr2, Integer num) {
                int pc = instruction.getPC();
                CodeWriter.this.print("{ // " + i2);
                CodeWriter.this.indent(num.intValue());
                int i3 = 0;
                while (true) {
                    CodeWriter codeWriter = CodeWriter.this;
                    if (i3 >= i2) {
                        codeWriter.print("\n     default: " + (pc + i) + "\n}");
                        CodeWriter.this.indent(-num.intValue());
                        return null;
                    }
                    codeWriter.print(String.format("%n%12d: %d", Integer.valueOf(iArr[i3]), Integer.valueOf(iArr2[i3] + pc)));
                    i3++;
                }
            }

            @Override // com.sun.tools.classfile.Instruction.KindVisitor
            public Void visitTableSwitch(Instruction instruction, int i, int i2, int i3, int[] iArr, Integer num) {
                int pc = instruction.getPC();
                CodeWriter.this.print("{ // " + i2 + " to " + i3);
                CodeWriter.this.indent(num.intValue());
                int i4 = 0;
                while (true) {
                    int length = iArr.length;
                    CodeWriter codeWriter = CodeWriter.this;
                    if (i4 >= length) {
                        codeWriter.print("\n     default: " + (pc + i) + "\n}");
                        CodeWriter.this.indent(-num.intValue());
                        return null;
                    }
                    codeWriter.print(String.format("%n%12d: %d", Integer.valueOf(i2 + i4), Integer.valueOf(iArr[i4] + pc)));
                    i4++;
                }
            }

            @Override // com.sun.tools.classfile.Instruction.KindVisitor
            public Void visitValue(Instruction instruction, int i, Integer num) {
                CodeWriter.this.print(Integer.valueOf(i));
                return null;
            }

            @Override // com.sun.tools.classfile.Instruction.KindVisitor
            public Void visitNoOperands(Instruction instruction, Integer num) {
                return null;
            }

            @Override // com.sun.tools.classfile.Instruction.KindVisitor
            public Void visitUnknown(Instruction instruction, Integer num) {
                return null;
            }
        };
        context.put(CodeWriter.class, this);
        this.attrWriter = AttributeWriter.instance(context);
        this.classWriter = ClassWriter.instance(context);
        this.constantWriter = ConstantWriter.instance(context);
        this.sourceWriter = SourceWriter.instance(context);
        this.tryBlockWriter = TryBlockWriter.instance(context);
        this.stackMapWriter = StackMapWriter.instance(context);
        this.localVariableTableWriter = LocalVariableTableWriter.instance(context);
        this.localVariableTypeTableWriter = LocalVariableTypeTableWriter.instance(context);
        this.typeAnnotationWriter = TypeAnnotationWriter.instance(context);
        this.options = Options.instance(context);
    }

    private List<InstructionDetailWriter> getDetailWriters(Code_attribute code_attribute) {
        ArrayList arrayList = new ArrayList();
        if (this.options.details.contains(InstructionDetailWriter.Kind.SOURCE)) {
            this.sourceWriter.reset(this.classWriter.getClassFile(), code_attribute);
            if (this.sourceWriter.hasSource()) {
                arrayList.add(this.sourceWriter);
            } else {
                println("(Source code not available)");
            }
        }
        if (this.options.details.contains(InstructionDetailWriter.Kind.LOCAL_VARS)) {
            this.localVariableTableWriter.reset(code_attribute);
            arrayList.add(this.localVariableTableWriter);
        }
        if (this.options.details.contains(InstructionDetailWriter.Kind.LOCAL_VAR_TYPES)) {
            this.localVariableTypeTableWriter.reset(code_attribute);
            arrayList.add(this.localVariableTypeTableWriter);
        }
        if (this.options.details.contains(InstructionDetailWriter.Kind.STACKMAPS)) {
            this.stackMapWriter.reset(code_attribute);
            this.stackMapWriter.writeInitialDetails();
            arrayList.add(this.stackMapWriter);
        }
        if (this.options.details.contains(InstructionDetailWriter.Kind.TRY_BLOCKS)) {
            this.tryBlockWriter.reset(code_attribute);
            arrayList.add(this.tryBlockWriter);
        }
        if (this.options.details.contains(InstructionDetailWriter.Kind.TYPE_ANNOS)) {
            this.typeAnnotationWriter.reset(code_attribute);
            arrayList.add(this.typeAnnotationWriter);
        }
        return arrayList;
    }

    public static CodeWriter instance(Context context) {
        CodeWriter codeWriter = (CodeWriter) context.get(CodeWriter.class);
        return codeWriter == null ? new CodeWriter(context) : codeWriter;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void printConstant(int i) {
        this.constantWriter.write(i);
    }

    public void write(Code_attribute code_attribute, ConstantPool constantPool) {
        println("Code:");
        indent(1);
        writeVerboseHeader(code_attribute, constantPool);
        writeInstrs(code_attribute);
        writeExceptionTable(code_attribute);
        this.attrWriter.write(code_attribute, code_attribute.attributes, constantPool);
        indent(-1);
    }

    public void writeExceptionTable(Code_attribute code_attribute) {
        if (code_attribute.exception_table_length <= 0) {
            return;
        }
        println("Exception table:");
        indent(1);
        println(" from    to  target type");
        int i = 0;
        while (true) {
            Code_attribute.Exception_data[] exception_dataArr = code_attribute.exception_table;
            if (i >= exception_dataArr.length) {
                indent(-1);
                return;
            }
            Code_attribute.Exception_data exception_data = exception_dataArr[i];
            print(String.format(" %5d %5d %5d", Integer.valueOf(exception_data.start_pc), Integer.valueOf(exception_data.end_pc), Integer.valueOf(exception_data.handler_pc)));
            print("   ");
            int i2 = exception_data.catch_type;
            if (i2 == 0) {
                println("any");
            } else {
                print("Class ");
                println(this.constantWriter.stringValue(i2));
            }
            i++;
        }
    }

    public void writeInstr(Instruction instruction) {
        print(String.format("%4d: %-13s ", Integer.valueOf(instruction.getPC()), instruction.getMnemonic()));
        int i = this.options.indentWidth;
        instruction.accept(this.instructionPrinter, Integer.valueOf((i + 5) / i));
        println();
    }

    public void writeInstrs(Code_attribute code_attribute) {
        List<InstructionDetailWriter> detailWriters = getDetailWriters(code_attribute);
        for (Instruction instruction : code_attribute.getInstructions()) {
            try {
                Iterator<InstructionDetailWriter> it = detailWriters.iterator();
                while (it.hasNext()) {
                    it.next().writeDetails(instruction);
                }
                writeInstr(instruction);
            } catch (ArrayIndexOutOfBoundsException | IllegalStateException unused) {
                println(report("error at or after byte " + instruction.getPC()));
            }
        }
        Iterator<InstructionDetailWriter> it2 = detailWriters.iterator();
        while (it2.hasNext()) {
            it2.next().flush();
        }
    }

    public void writeVerboseHeader(Code_attribute code_attribute, ConstantPool constantPool) {
        String strReport;
        Method method = this.classWriter.getMethod();
        try {
            int parameterCount = method.descriptor.getParameterCount(constantPool);
            if (!method.access_flags.is(8)) {
                parameterCount++;
            }
            strReport = Integer.toString(parameterCount);
        } catch (ConstantPoolException e) {
            strReport = report(e);
        } catch (DescriptorException e2) {
            strReport = report(e2);
        }
        println("stack=" + code_attribute.max_stack + ", locals=" + code_attribute.max_locals + ", args_size=" + strReport);
    }
}
