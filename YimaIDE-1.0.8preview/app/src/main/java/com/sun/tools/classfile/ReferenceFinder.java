package com.sun.tools.classfile;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class ReferenceFinder {
    private final Filter filter;
    private final Visitor visitor;
    private ConstantPool.Visitor<Boolean, ConstantPool> cpVisitor = new ConstantPool.Visitor<Boolean, ConstantPool>() { // from class: com.sun.tools.classfile.ReferenceFinder.1
        @Override // com.sun.tools.classfile.ConstantPool.Visitor
        public Boolean visitFieldref(ConstantPool.CONSTANT_Fieldref_info cONSTANT_Fieldref_info, ConstantPool constantPool) {
            return Boolean.valueOf(ReferenceFinder.this.filter.accept(constantPool, cONSTANT_Fieldref_info));
        }

        @Override // com.sun.tools.classfile.ConstantPool.Visitor
        public Boolean visitInterfaceMethodref(ConstantPool.CONSTANT_InterfaceMethodref_info cONSTANT_InterfaceMethodref_info, ConstantPool constantPool) {
            return Boolean.valueOf(ReferenceFinder.this.filter.accept(constantPool, cONSTANT_InterfaceMethodref_info));
        }

        @Override // com.sun.tools.classfile.ConstantPool.Visitor
        public Boolean visitMethodref(ConstantPool.CONSTANT_Methodref_info cONSTANT_Methodref_info, ConstantPool constantPool) {
            return Boolean.valueOf(ReferenceFinder.this.filter.accept(constantPool, cONSTANT_Methodref_info));
        }

        @Override // com.sun.tools.classfile.ConstantPool.Visitor
        public Boolean visitClass(ConstantPool.CONSTANT_Class_info cONSTANT_Class_info, ConstantPool constantPool) {
            return Boolean.FALSE;
        }

        @Override // com.sun.tools.classfile.ConstantPool.Visitor
        public Boolean visitDouble(ConstantPool.CONSTANT_Double_info cONSTANT_Double_info, ConstantPool constantPool) {
            return Boolean.FALSE;
        }

        @Override // com.sun.tools.classfile.ConstantPool.Visitor
        public Boolean visitDynamicConstant(ConstantPool.CONSTANT_Dynamic_info cONSTANT_Dynamic_info, ConstantPool constantPool) {
            return Boolean.FALSE;
        }

        @Override // com.sun.tools.classfile.ConstantPool.Visitor
        public Boolean visitFloat(ConstantPool.CONSTANT_Float_info cONSTANT_Float_info, ConstantPool constantPool) {
            return Boolean.FALSE;
        }

        @Override // com.sun.tools.classfile.ConstantPool.Visitor
        public Boolean visitInteger(ConstantPool.CONSTANT_Integer_info cONSTANT_Integer_info, ConstantPool constantPool) {
            return Boolean.FALSE;
        }

        @Override // com.sun.tools.classfile.ConstantPool.Visitor
        public Boolean visitInvokeDynamic(ConstantPool.CONSTANT_InvokeDynamic_info cONSTANT_InvokeDynamic_info, ConstantPool constantPool) {
            return Boolean.FALSE;
        }

        @Override // com.sun.tools.classfile.ConstantPool.Visitor
        public Boolean visitLong(ConstantPool.CONSTANT_Long_info cONSTANT_Long_info, ConstantPool constantPool) {
            return Boolean.FALSE;
        }

        @Override // com.sun.tools.classfile.ConstantPool.Visitor
        public Boolean visitMethodHandle(ConstantPool.CONSTANT_MethodHandle_info cONSTANT_MethodHandle_info, ConstantPool constantPool) {
            return Boolean.FALSE;
        }

        @Override // com.sun.tools.classfile.ConstantPool.Visitor
        public Boolean visitMethodType(ConstantPool.CONSTANT_MethodType_info cONSTANT_MethodType_info, ConstantPool constantPool) {
            return Boolean.FALSE;
        }

        @Override // com.sun.tools.classfile.ConstantPool.Visitor
        public Boolean visitModule(ConstantPool.CONSTANT_Module_info cONSTANT_Module_info, ConstantPool constantPool) {
            return Boolean.FALSE;
        }

        @Override // com.sun.tools.classfile.ConstantPool.Visitor
        public Boolean visitNameAndType(ConstantPool.CONSTANT_NameAndType_info cONSTANT_NameAndType_info, ConstantPool constantPool) {
            return Boolean.FALSE;
        }

        @Override // com.sun.tools.classfile.ConstantPool.Visitor
        public Boolean visitPackage(ConstantPool.CONSTANT_Package_info cONSTANT_Package_info, ConstantPool constantPool) {
            return Boolean.FALSE;
        }

        @Override // com.sun.tools.classfile.ConstantPool.Visitor
        public Boolean visitString(ConstantPool.CONSTANT_String_info cONSTANT_String_info, ConstantPool constantPool) {
            return Boolean.FALSE;
        }

        @Override // com.sun.tools.classfile.ConstantPool.Visitor
        public Boolean visitUtf8(ConstantPool.CONSTANT_Utf8_info cONSTANT_Utf8_info, ConstantPool constantPool) {
            return Boolean.FALSE;
        }
    };
    private Instruction.KindVisitor<Integer, List<Integer>> codeVisitor = new Instruction.KindVisitor<Integer, List<Integer>>() { // from class: com.sun.tools.classfile.ReferenceFinder.2
        @Override // com.sun.tools.classfile.Instruction.KindVisitor
        public Integer visitConstantPoolRef(Instruction instruction, int i, List<Integer> list) {
            if (!list.contains(Integer.valueOf(i))) {
                i = 0;
            }
            return Integer.valueOf(i);
        }

        @Override // com.sun.tools.classfile.Instruction.KindVisitor
        public Integer visitConstantPoolRefAndValue(Instruction instruction, int i, int i2, List<Integer> list) {
            if (!list.contains(Integer.valueOf(i))) {
                i = 0;
            }
            return Integer.valueOf(i);
        }

        @Override // com.sun.tools.classfile.Instruction.KindVisitor
        public Integer visitArrayType(Instruction instruction, Instruction.TypeKind typeKind, List<Integer> list) {
            return 0;
        }

        @Override // com.sun.tools.classfile.Instruction.KindVisitor
        public Integer visitBranch(Instruction instruction, int i, List<Integer> list) {
            return 0;
        }

        @Override // com.sun.tools.classfile.Instruction.KindVisitor
        public Integer visitLocal(Instruction instruction, int i, List<Integer> list) {
            return 0;
        }

        @Override // com.sun.tools.classfile.Instruction.KindVisitor
        public Integer visitLocalAndValue(Instruction instruction, int i, int i2, List<Integer> list) {
            return 0;
        }

        @Override // com.sun.tools.classfile.Instruction.KindVisitor
        public Integer visitLookupSwitch(Instruction instruction, int i, int i2, int[] iArr, int[] iArr2, List<Integer> list) {
            return 0;
        }

        @Override // com.sun.tools.classfile.Instruction.KindVisitor
        public Integer visitNoOperands(Instruction instruction, List<Integer> list) {
            return 0;
        }

        @Override // com.sun.tools.classfile.Instruction.KindVisitor
        public Integer visitTableSwitch(Instruction instruction, int i, int i2, int i3, int[] iArr, List<Integer> list) {
            return 0;
        }

        @Override // com.sun.tools.classfile.Instruction.KindVisitor
        public Integer visitUnknown(Instruction instruction, List<Integer> list) {
            return 0;
        }

        @Override // com.sun.tools.classfile.Instruction.KindVisitor
        public Integer visitValue(Instruction instruction, int i, List<Integer> list) {
            return 0;
        }
    };

    public interface Filter {
        boolean accept(ConstantPool constantPool, ConstantPool.CPRefInfo cPRefInfo);
    }

    public interface Visitor {
        void visit(ClassFile classFile, Method method, List<ConstantPool.CPRefInfo> list);
    }

    public ReferenceFinder(Filter filter, Visitor visitor) {
        Objects.requireNonNull(filter);
        this.filter = filter;
        Objects.requireNonNull(visitor);
        this.visitor = visitor;
    }

    public boolean parse(ClassFile classFile) throws ConstantPoolException {
        ArrayList arrayList = new ArrayList();
        int size = 1;
        for (ConstantPool.CPInfo cPInfo : classFile.constant_pool.entries()) {
            if (((Boolean) cPInfo.accept(this.cpVisitor, classFile.constant_pool)).booleanValue()) {
                arrayList.add(Integer.valueOf(size));
            }
            size += cPInfo.size();
        }
        if (arrayList.isEmpty()) {
            return false;
        }
        for (Method method : classFile.methods) {
            HashSet hashSet = new HashSet();
            Code_attribute code_attribute = (Code_attribute) method.attributes.get(Attribute.Code);
            if (code_attribute != null) {
                Iterator<Instruction> it = code_attribute.getInstructions().iterator();
                while (it.hasNext()) {
                    Integer num = (Integer) it.next().accept(this.codeVisitor, arrayList);
                    if (num.intValue() > 0) {
                        hashSet.add(num);
                    }
                }
            }
            if (hashSet.size() > 0) {
                ArrayList arrayList2 = new ArrayList(hashSet.size());
                Iterator it2 = hashSet.iterator();
                while (it2.hasNext()) {
                    arrayList2.add((ConstantPool.CPRefInfo) ConstantPool.CPRefInfo.class.cast(classFile.constant_pool.get(((Integer) it2.next()).intValue())));
                }
                this.visitor.visit(classFile, method, arrayList2);
            }
        }
        return true;
    }
}
