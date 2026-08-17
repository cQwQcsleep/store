package com.sun.tools.javap;

import com.intellij.psi.PsiKeyword;
import com.sun.org.apache.xalan.internal.templates.Constants;
import com.sun.tools.classfile.ClassFile;
import com.sun.tools.classfile.ConstantPool;
import com.sun.tools.classfile.ConstantPoolException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ConstantWriter extends BasicWriter {
    private final ClassWriter classWriter;
    private final Options options;
    StringValueVisitor stringValueVisitor;

    public ConstantWriter(Context context) {
        super(context);
        this.stringValueVisitor = new StringValueVisitor();
        context.put(ConstantWriter.class, this);
        this.classWriter = ClassWriter.instance(context);
        this.options = Options.instance(context);
    }

    private static String addEscapes(String str) {
        int length = str.length();
        StringBuilder sb = null;
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            int iIndexOf = "\\\"\n\t".indexOf(str.charAt(i2));
            if (iIndexOf >= 0) {
                if (sb == null) {
                    sb = new StringBuilder(length * 2);
                }
                if (i < i2) {
                    sb.append((CharSequence) str, i, i2);
                }
                sb.append('\\');
                sb.append("\\\"nt".charAt(iIndexOf));
                i = i2 + 1;
            }
        }
        if (sb == null) {
            return str;
        }
        if (i < length) {
            sb.append((CharSequence) str, i, length);
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String checkName(String str) {
        if (str == null) {
            return PsiKeyword.NULL;
        }
        int length = str.length();
        if (length == 0) {
            return "\"\"";
        }
        int iCharCount = 0;
        int i = 47;
        while (iCharCount < length) {
            int iCodePointAt = str.codePointAt(iCharCount);
            if ((i == 47 && !Character.isJavaIdentifierStart(iCodePointAt)) || (iCodePointAt != 47 && !Character.isJavaIdentifierPart(iCodePointAt))) {
                return "\"" + addEscapes(str) + "\"";
            }
            iCharCount += Character.charCount(iCodePointAt);
            i = iCodePointAt;
        }
        return str;
    }

    public static ConstantWriter instance(Context context) {
        ConstantWriter constantWriter = (ConstantWriter) context.get(ConstantWriter.class);
        return constantWriter == null ? new ConstantWriter(context) : constantWriter;
    }

    public String booleanValue(int i) {
        try {
            ConstantPool.CPInfo cPInfo = this.classWriter.getClassFile().constant_pool.get(i);
            if (cPInfo instanceof ConstantPool.CONSTANT_Integer_info) {
                int i2 = ((ConstantPool.CONSTANT_Integer_info) cPInfo).value;
                if (i2 == 0) {
                    return "false";
                }
                if (i2 == 1) {
                    return "true";
                }
            }
            return "#" + i;
        } catch (ConstantPool.InvalidIndex e) {
            return report(e);
        }
    }

    public String charValue(int i) {
        try {
            ConstantPool.CPInfo cPInfo = this.classWriter.getClassFile().constant_pool.get(i);
            if (cPInfo instanceof ConstantPool.CONSTANT_Integer_info) {
                return String.valueOf((char) ((ConstantPool.CONSTANT_Integer_info) cPInfo).value);
            }
            return "#" + i;
        } catch (ConstantPool.InvalidIndex e) {
            return report(e);
        }
    }

    public String cpTagName(ConstantPool.CPInfo cPInfo) {
        return cPInfo.getClass().getSimpleName().replace("CONSTANT_", "").replace("_info", "");
    }

    public String stringValue(int i) {
        try {
            return stringValue(this.classWriter.getClassFile().constant_pool.get(i));
        } catch (ConstantPool.InvalidIndex e) {
            return report(e);
        }
    }

    public String tagName(int i) {
        switch (i) {
            case 1:
                return "Utf8";
            case 2:
            case 13:
            case 14:
            default:
                return "(unknown tag " + i + ")";
            case 3:
                return "int";
            case 4:
                return "float";
            case 5:
                return "long";
            case 6:
                return "double";
            case 7:
                return "class";
            case 8:
                return "String";
            case 9:
                return "Field";
            case 10:
                return "Method";
            case 11:
                return "InterfaceMethod";
            case 12:
                return "NameAndType";
            case 15:
                return "MethodHandle";
            case 16:
                return "MethodType";
            case 17:
                return "Dynamic";
            case 18:
                return "InvokeDynamic";
        }
    }

    public void write(int i) {
        ClassFile classFile = this.classWriter.getClassFile();
        if (i == 0) {
            print("#0");
            return;
        }
        try {
            ConstantPool.CPInfo cPInfo = classFile.constant_pool.get(i);
            int tag = cPInfo.getTag();
            switch (tag) {
                case 9:
                case 10:
                case 11:
                    ConstantPool.CPRefInfo cPRefInfo = (ConstantPool.CPRefInfo) cPInfo;
                    try {
                        if (cPRefInfo.class_index == classFile.this_class) {
                            cPInfo = classFile.constant_pool.get(cPRefInfo.name_and_type_index);
                        }
                    } catch (ConstantPool.InvalidIndex unused) {
                    }
                    break;
            }
            print(tagName(tag) + " " + stringValue(cPInfo));
        } catch (ConstantPoolException unused2) {
            print("#" + i);
        }
    }

    public void writeConstantPool(ConstantPool constantPool) {
        ConstantPool.Visitor<Integer, Void> visitor = new ConstantPool.Visitor<Integer, Void>() { // from class: com.sun.tools.javap.ConstantWriter.1
            @Override // com.sun.tools.classfile.ConstantPool.Visitor
            public Integer visitClass(ConstantPool.CONSTANT_Class_info cONSTANT_Class_info, Void r4) {
                ConstantWriter.this.print("#" + cONSTANT_Class_info.name_index);
                ConstantWriter.this.tab();
                ConstantWriter.this.println("// " + ConstantWriter.this.stringValue(cONSTANT_Class_info));
                return 1;
            }

            @Override // com.sun.tools.classfile.ConstantPool.Visitor
            public Integer visitDouble(ConstantPool.CONSTANT_Double_info cONSTANT_Double_info, Void r2) {
                ConstantWriter constantWriter = ConstantWriter.this;
                constantWriter.println(constantWriter.stringValue(cONSTANT_Double_info));
                return 2;
            }

            @Override // com.sun.tools.classfile.ConstantPool.Visitor
            public Integer visitDynamicConstant(ConstantPool.CONSTANT_Dynamic_info cONSTANT_Dynamic_info, Void r4) {
                ConstantWriter.this.print("#" + cONSTANT_Dynamic_info.bootstrap_method_attr_index + ":#" + cONSTANT_Dynamic_info.name_and_type_index);
                ConstantWriter.this.tab();
                ConstantWriter.this.println("// " + ConstantWriter.this.stringValue(cONSTANT_Dynamic_info));
                return 1;
            }

            @Override // com.sun.tools.classfile.ConstantPool.Visitor
            public Integer visitFieldref(ConstantPool.CONSTANT_Fieldref_info cONSTANT_Fieldref_info, Void r4) {
                ConstantWriter.this.print("#" + cONSTANT_Fieldref_info.class_index + ".#" + cONSTANT_Fieldref_info.name_and_type_index);
                ConstantWriter.this.tab();
                ConstantWriter.this.println("// " + ConstantWriter.this.stringValue(cONSTANT_Fieldref_info));
                return 1;
            }

            @Override // com.sun.tools.classfile.ConstantPool.Visitor
            public Integer visitFloat(ConstantPool.CONSTANT_Float_info cONSTANT_Float_info, Void r2) {
                ConstantWriter constantWriter = ConstantWriter.this;
                constantWriter.println(constantWriter.stringValue(cONSTANT_Float_info));
                return 1;
            }

            @Override // com.sun.tools.classfile.ConstantPool.Visitor
            public Integer visitInteger(ConstantPool.CONSTANT_Integer_info cONSTANT_Integer_info, Void r2) {
                ConstantWriter constantWriter = ConstantWriter.this;
                constantWriter.println(constantWriter.stringValue(cONSTANT_Integer_info));
                return 1;
            }

            @Override // com.sun.tools.classfile.ConstantPool.Visitor
            public Integer visitInterfaceMethodref(ConstantPool.CONSTANT_InterfaceMethodref_info cONSTANT_InterfaceMethodref_info, Void r4) {
                ConstantWriter.this.print("#" + cONSTANT_InterfaceMethodref_info.class_index + ".#" + cONSTANT_InterfaceMethodref_info.name_and_type_index);
                ConstantWriter.this.tab();
                ConstantWriter.this.println("// " + ConstantWriter.this.stringValue(cONSTANT_InterfaceMethodref_info));
                return 1;
            }

            @Override // com.sun.tools.classfile.ConstantPool.Visitor
            public Integer visitInvokeDynamic(ConstantPool.CONSTANT_InvokeDynamic_info cONSTANT_InvokeDynamic_info, Void r4) {
                ConstantWriter.this.print("#" + cONSTANT_InvokeDynamic_info.bootstrap_method_attr_index + ":#" + cONSTANT_InvokeDynamic_info.name_and_type_index);
                ConstantWriter.this.tab();
                ConstantWriter.this.println("// " + ConstantWriter.this.stringValue(cONSTANT_InvokeDynamic_info));
                return 1;
            }

            @Override // com.sun.tools.classfile.ConstantPool.Visitor
            public Integer visitLong(ConstantPool.CONSTANT_Long_info cONSTANT_Long_info, Void r2) {
                ConstantWriter constantWriter = ConstantWriter.this;
                constantWriter.println(constantWriter.stringValue(cONSTANT_Long_info));
                return 2;
            }

            @Override // com.sun.tools.classfile.ConstantPool.Visitor
            public Integer visitMethodHandle(ConstantPool.CONSTANT_MethodHandle_info cONSTANT_MethodHandle_info, Void r4) {
                ConstantWriter.this.print(cONSTANT_MethodHandle_info.reference_kind.tag + ":#" + cONSTANT_MethodHandle_info.reference_index);
                ConstantWriter.this.tab();
                ConstantWriter.this.println("// " + ConstantWriter.this.stringValue(cONSTANT_MethodHandle_info));
                return 1;
            }

            @Override // com.sun.tools.classfile.ConstantPool.Visitor
            public Integer visitMethodType(ConstantPool.CONSTANT_MethodType_info cONSTANT_MethodType_info, Void r4) {
                ConstantWriter.this.print("#" + cONSTANT_MethodType_info.descriptor_index);
                ConstantWriter.this.tab();
                ConstantWriter.this.println("//  " + ConstantWriter.this.stringValue(cONSTANT_MethodType_info));
                return 1;
            }

            @Override // com.sun.tools.classfile.ConstantPool.Visitor
            public Integer visitMethodref(ConstantPool.CONSTANT_Methodref_info cONSTANT_Methodref_info, Void r4) {
                ConstantWriter.this.print("#" + cONSTANT_Methodref_info.class_index + ".#" + cONSTANT_Methodref_info.name_and_type_index);
                ConstantWriter.this.tab();
                ConstantWriter.this.println("// " + ConstantWriter.this.stringValue(cONSTANT_Methodref_info));
                return 1;
            }

            @Override // com.sun.tools.classfile.ConstantPool.Visitor
            public Integer visitModule(ConstantPool.CONSTANT_Module_info cONSTANT_Module_info, Void r4) {
                ConstantWriter.this.print("#" + cONSTANT_Module_info.name_index);
                ConstantWriter.this.tab();
                ConstantWriter.this.println("// " + ConstantWriter.this.stringValue(cONSTANT_Module_info));
                return 1;
            }

            @Override // com.sun.tools.classfile.ConstantPool.Visitor
            public Integer visitNameAndType(ConstantPool.CONSTANT_NameAndType_info cONSTANT_NameAndType_info, Void r4) {
                ConstantWriter.this.print("#" + cONSTANT_NameAndType_info.name_index + ":#" + cONSTANT_NameAndType_info.type_index);
                ConstantWriter.this.tab();
                ConstantWriter.this.println("// " + ConstantWriter.this.stringValue(cONSTANT_NameAndType_info));
                return 1;
            }

            @Override // com.sun.tools.classfile.ConstantPool.Visitor
            public Integer visitPackage(ConstantPool.CONSTANT_Package_info cONSTANT_Package_info, Void r4) {
                ConstantWriter.this.print("#" + cONSTANT_Package_info.name_index);
                ConstantWriter.this.tab();
                ConstantWriter.this.println("// " + ConstantWriter.this.stringValue(cONSTANT_Package_info));
                return 1;
            }

            @Override // com.sun.tools.classfile.ConstantPool.Visitor
            public Integer visitString(ConstantPool.CONSTANT_String_info cONSTANT_String_info, Void r4) {
                ConstantWriter.this.print("#" + cONSTANT_String_info.string_index);
                ConstantWriter.this.tab();
                ConstantWriter.this.println("// " + ConstantWriter.this.stringValue(cONSTANT_String_info));
                return 1;
            }

            @Override // com.sun.tools.classfile.ConstantPool.Visitor
            public Integer visitUtf8(ConstantPool.CONSTANT_Utf8_info cONSTANT_Utf8_info, Void r2) {
                ConstantWriter constantWriter = ConstantWriter.this;
                constantWriter.println(constantWriter.stringValue(cONSTANT_Utf8_info));
                return 1;
            }
        };
        println("Constant pool:");
        int iIntValue = 1;
        indent(1);
        int length = String.valueOf(constantPool.size()).length() + 1;
        while (iIntValue < constantPool.size()) {
            print(String.format("%" + length + "s", "#" + iIntValue));
            try {
                ConstantPool.CPInfo cPInfo = constantPool.get(iIntValue);
                print(String.format(" = %-18s ", cpTagName(cPInfo)));
                iIntValue += ((Integer) cPInfo.accept(visitor, null)).intValue();
            } catch (ConstantPool.InvalidIndex unused) {
            }
        }
        indent(-1);
    }

    public class StringValueVisitor implements ConstantPool.Visitor<String, Void> {
        private StringValueVisitor() {
        }

        public String getCheckedClassName(ConstantPool.CPRefInfo cPRefInfo) {
            try {
                return ConstantWriter.checkName(cPRefInfo.getClassName());
            } catch (ConstantPoolException e) {
                return ConstantWriter.this.report(e);
            }
        }

        public String getCheckedName(ConstantPool.CONSTANT_Class_info cONSTANT_Class_info) {
            try {
                return ConstantWriter.checkName(cONSTANT_Class_info.getName());
            } catch (ConstantPoolException e) {
                return ConstantWriter.this.report(e);
            }
        }

        public String getType(ConstantPool.CONSTANT_NameAndType_info cONSTANT_NameAndType_info) {
            try {
                return cONSTANT_NameAndType_info.getType();
            } catch (ConstantPoolException e) {
                return ConstantWriter.this.report(e);
            }
        }

        public String visit(ConstantPool.CPInfo cPInfo) {
            return (String) cPInfo.accept(this, null);
        }

        @Override // com.sun.tools.classfile.ConstantPool.Visitor
        public String visitDouble(ConstantPool.CONSTANT_Double_info cONSTANT_Double_info, Void r2) {
            return cONSTANT_Double_info.value + "d";
        }

        @Override // com.sun.tools.classfile.ConstantPool.Visitor
        public String visitDynamicConstant(ConstantPool.CONSTANT_Dynamic_info cONSTANT_Dynamic_info, Void r4) {
            try {
                return "#" + cONSTANT_Dynamic_info.bootstrap_method_attr_index + ":" + ConstantWriter.this.stringValue(cONSTANT_Dynamic_info.getNameAndTypeInfo());
            } catch (ConstantPoolException e) {
                return ConstantWriter.this.report(e);
            }
        }

        @Override // com.sun.tools.classfile.ConstantPool.Visitor
        public String visitFloat(ConstantPool.CONSTANT_Float_info cONSTANT_Float_info, Void r2) {
            return cONSTANT_Float_info.value + "f";
        }

        @Override // com.sun.tools.classfile.ConstantPool.Visitor
        public String visitInvokeDynamic(ConstantPool.CONSTANT_InvokeDynamic_info cONSTANT_InvokeDynamic_info, Void r4) {
            try {
                return "#" + cONSTANT_InvokeDynamic_info.bootstrap_method_attr_index + ":" + ConstantWriter.this.stringValue(cONSTANT_InvokeDynamic_info.getNameAndTypeInfo());
            } catch (ConstantPoolException e) {
                return ConstantWriter.this.report(e);
            }
        }

        @Override // com.sun.tools.classfile.ConstantPool.Visitor
        public String visitLong(ConstantPool.CONSTANT_Long_info cONSTANT_Long_info, Void r2) {
            return cONSTANT_Long_info.value + "l";
        }

        @Override // com.sun.tools.classfile.ConstantPool.Visitor
        public String visitMethodHandle(ConstantPool.CONSTANT_MethodHandle_info cONSTANT_MethodHandle_info, Void r3) {
            try {
                return cONSTANT_MethodHandle_info.reference_kind + " " + ConstantWriter.this.stringValue(cONSTANT_MethodHandle_info.getCPRefInfo());
            } catch (ConstantPoolException e) {
                return ConstantWriter.this.report(e);
            }
        }

        @Override // com.sun.tools.classfile.ConstantPool.Visitor
        public String visitMethodType(ConstantPool.CONSTANT_MethodType_info cONSTANT_MethodType_info, Void r2) {
            try {
                return cONSTANT_MethodType_info.getType();
            } catch (ConstantPoolException e) {
                return ConstantWriter.this.report(e);
            }
        }

        @Override // com.sun.tools.classfile.ConstantPool.Visitor
        public String visitModule(ConstantPool.CONSTANT_Module_info cONSTANT_Module_info, Void r2) {
            try {
                return ConstantWriter.checkName(cONSTANT_Module_info.getName());
            } catch (ConstantPoolException e) {
                return ConstantWriter.this.report(e);
            }
        }

        @Override // com.sun.tools.classfile.ConstantPool.Visitor
        public String visitNameAndType(ConstantPool.CONSTANT_NameAndType_info cONSTANT_NameAndType_info, Void r3) {
            return getCheckedName(cONSTANT_NameAndType_info) + ":" + getType(cONSTANT_NameAndType_info);
        }

        @Override // com.sun.tools.classfile.ConstantPool.Visitor
        public String visitPackage(ConstantPool.CONSTANT_Package_info cONSTANT_Package_info, Void r2) {
            try {
                return ConstantWriter.checkName(cONSTANT_Package_info.getName());
            } catch (ConstantPoolException e) {
                return ConstantWriter.this.report(e);
            }
        }

        public String visitRef(ConstantPool.CPRefInfo cPRefInfo, Void r3) {
            String strReport;
            String checkedClassName = getCheckedClassName(cPRefInfo);
            try {
                strReport = ConstantWriter.this.stringValue(cPRefInfo.getNameAndTypeInfo());
            } catch (ConstantPoolException e) {
                strReport = ConstantWriter.this.report(e);
            }
            return checkedClassName + Constants.ATTRVAL_THIS + strReport;
        }

        @Override // com.sun.tools.classfile.ConstantPool.Visitor
        public String visitString(ConstantPool.CONSTANT_String_info cONSTANT_String_info, Void r3) {
            try {
                ClassFile classFile = ConstantWriter.this.classWriter.getClassFile();
                return ConstantWriter.this.stringValue(classFile.constant_pool.getUTF8Info(cONSTANT_String_info.string_index));
            } catch (ConstantPoolException e) {
                return ConstantWriter.this.report(e);
            }
        }

        @Override // com.sun.tools.classfile.ConstantPool.Visitor
        public String visitUtf8(ConstantPool.CONSTANT_Utf8_info cONSTANT_Utf8_info, Void r4) {
            String str = cONSTANT_Utf8_info.value;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < str.length(); i++) {
                char cCharAt = str.charAt(i);
                if (cCharAt == '\f') {
                    sb.append("\\f");
                } else if (cCharAt == '\r') {
                    sb.append("\\r");
                } else if (cCharAt == '\"') {
                    sb.append("\\\"");
                } else if (cCharAt == '\'') {
                    sb.append("\\'");
                } else if (cCharAt != '\\') {
                    switch (cCharAt) {
                        case '\b':
                            sb.append("\\b");
                            break;
                        case '\t':
                            sb.append("\\t");
                            break;
                        case '\n':
                            sb.append("\\n");
                            break;
                        default:
                            if (Character.isISOControl(cCharAt)) {
                                sb.append(String.format("\\u%04x", Integer.valueOf(cCharAt)));
                            } else {
                                sb.append(cCharAt);
                            }
                            break;
                    }
                } else {
                    sb.append("\\\\");
                }
            }
            return sb.toString();
        }

        @Override // com.sun.tools.classfile.ConstantPool.Visitor
        public String visitClass(ConstantPool.CONSTANT_Class_info cONSTANT_Class_info, Void r2) {
            return getCheckedName(cONSTANT_Class_info);
        }

        @Override // com.sun.tools.classfile.ConstantPool.Visitor
        public String visitFieldref(ConstantPool.CONSTANT_Fieldref_info cONSTANT_Fieldref_info, Void r2) {
            return visitRef(cONSTANT_Fieldref_info, r2);
        }

        @Override // com.sun.tools.classfile.ConstantPool.Visitor
        public String visitInteger(ConstantPool.CONSTANT_Integer_info cONSTANT_Integer_info, Void r2) {
            return String.valueOf(cONSTANT_Integer_info.value);
        }

        @Override // com.sun.tools.classfile.ConstantPool.Visitor
        public String visitInterfaceMethodref(ConstantPool.CONSTANT_InterfaceMethodref_info cONSTANT_InterfaceMethodref_info, Void r2) {
            return visitRef(cONSTANT_InterfaceMethodref_info, r2);
        }

        @Override // com.sun.tools.classfile.ConstantPool.Visitor
        public String visitMethodref(ConstantPool.CONSTANT_Methodref_info cONSTANT_Methodref_info, Void r2) {
            return visitRef(cONSTANT_Methodref_info, r2);
        }

        public String getCheckedName(ConstantPool.CONSTANT_NameAndType_info cONSTANT_NameAndType_info) {
            try {
                return ConstantWriter.checkName(cONSTANT_NameAndType_info.getName());
            } catch (ConstantPoolException e) {
                return ConstantWriter.this.report(e);
            }
        }
    }

    public String stringValue(ConstantPool.CPInfo cPInfo) {
        return this.stringValueVisitor.visit(cPInfo);
    }

    public void writeConstantPool() {
        writeConstantPool(this.classWriter.getClassFile().constant_pool);
    }
}
