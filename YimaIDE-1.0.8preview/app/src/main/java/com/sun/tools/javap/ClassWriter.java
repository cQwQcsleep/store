package com.sun.tools.javap;

import com.intellij.psi.PsiKeyword;
import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Constants;
import com.sun.tools.classfile.AccessFlags;
import com.sun.tools.classfile.Attribute;
import com.sun.tools.classfile.Attributes;
import com.sun.tools.classfile.ClassFile;
import com.sun.tools.classfile.Code_attribute;
import com.sun.tools.classfile.ConstantPool;
import com.sun.tools.classfile.ConstantPoolException;
import com.sun.tools.classfile.ConstantValue_attribute;
import com.sun.tools.classfile.Descriptor;
import com.sun.tools.classfile.Exceptions_attribute;
import com.sun.tools.classfile.Field;
import com.sun.tools.classfile.Method;
import com.sun.tools.classfile.Module_attribute;
import com.sun.tools.classfile.Signature_attribute;
import com.sun.tools.classfile.SourceFile_attribute;
import com.sun.tools.classfile.Type;
import java.net.URI;
import java.text.DateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ClassWriter extends BasicWriter {
    private static final int DEFAULT_ALLOWED_MAJOR_VERSION = 52;
    private static final int DEFAULT_ALLOWED_MINOR_VERSION = 0;
    private final AttributeWriter attrWriter;
    private ClassFile classFile;
    private final CodeWriter codeWriter;
    private final ConstantWriter constantWriter;
    private ConstantPool constant_pool;
    private byte[] digest;
    private String digestName;
    private long lastModified;
    private Method method;
    private final Options options;
    private int size;
    private URI uri;

    /* JADX INFO: renamed from: com.sun.tools.javap.ClassWriter$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$classfile$Type$WildcardType$Kind;

        static {
            int[] iArr = new int[Type.WildcardType.Kind.values().length];
            $SwitchMap$com$sun$tools$classfile$Type$WildcardType$Kind = iArr;
            try {
                iArr[Type.WildcardType.Kind.UNBOUNDED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$Type$WildcardType$Kind[Type.WildcardType.Kind.EXTENDS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$sun$tools$classfile$Type$WildcardType$Kind[Type.WildcardType.Kind.SUPER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public ClassWriter(Context context) {
        super(context);
        context.put(ClassWriter.class, this);
        this.options = Options.instance(context);
        this.attrWriter = AttributeWriter.instance(context);
        this.codeWriter = CodeWriter.instance(context);
        this.constantWriter = ConstantWriter.instance(context);
    }

    private String esc(char c, char c2) {
        if (' ' <= c && c <= '~' && c != c2 && c != '\\') {
            return String.valueOf(c);
        }
        if (c == '\f') {
            return "\\f";
        }
        if (c == '\r') {
            return "\\r";
        }
        if (c == '\"') {
            return "\\\"";
        }
        if (c == '\'') {
            return "\\'";
        }
        if (c == '\\') {
            return "\\\\";
        }
        switch (c) {
            case '\b':
                return "\\b";
            case '\t':
                return "\\t";
            case '\n':
                return "\\n";
            default:
                return String.format("\\u%04x", Integer.valueOf(c));
        }
    }

    private String getConstantCharValue(char c) {
        return "'" + esc(c, '\'') + '\'';
    }

    private String getConstantStringValue(String str) {
        StringBuilder sb = new StringBuilder("\"");
        for (int i = 0; i < str.length(); i++) {
            sb.append(esc(str.charAt(i), '\"'));
        }
        sb.append("\"");
        return sb.toString();
    }

    public static ClassWriter instance(Context context) {
        ClassWriter classWriter = (ClassWriter) context.get(ClassWriter.class);
        return classWriter == null ? new ClassWriter(context) : classWriter;
    }

    public String adjustVarargs(AccessFlags accessFlags, String str) {
        int iLastIndexOf;
        if (!accessFlags.is(128) || (iLastIndexOf = str.lastIndexOf("[]")) <= 0) {
            return str;
        }
        return str.substring(0, iLastIndexOf) + "..." + str.substring(iLastIndexOf + 2);
    }

    public ClassFile getClassFile() {
        return this.classFile;
    }

    public String getClassName(int i) {
        try {
            return this.classFile.constant_pool.getClassInfo(i).getName();
        } catch (ConstantPoolException e) {
            return report(e);
        }
    }

    public String getConstantValue(Descriptor descriptor, int i) {
        try {
            ConstantPool.CPInfo cPInfo = this.constant_pool.get(i);
            int tag = cPInfo.getTag();
            if (tag != 3) {
                return tag != 8 ? this.constantWriter.stringValue(cPInfo) : getConstantStringValue(((ConstantPool.CONSTANT_String_info) cPInfo).getString());
            }
            ConstantPool.CONSTANT_Integer_info cONSTANT_Integer_info = (ConstantPool.CONSTANT_Integer_info) cPInfo;
            String value = descriptor.getValue(this.constant_pool);
            int iHashCode = value.hashCode();
            if (iHashCode != 67) {
                if (iHashCode == 90 && value.equals(Constants.HASIDCALL_INDEX_SIG)) {
                    boolean z = true;
                    if (cONSTANT_Integer_info.value != 1) {
                        z = false;
                    }
                    return String.valueOf(z);
                }
            } else if (value.equals("C")) {
                return getConstantCharValue((char) cONSTANT_Integer_info.value);
            }
            return String.valueOf(cONSTANT_Integer_info.value);
        } catch (ConstantPoolException unused) {
            return "#" + i;
        }
    }

    public String getFieldName(Field field) {
        try {
            return field.getName(this.constant_pool);
        } catch (ConstantPoolException e) {
            return report(e);
        }
    }

    public String getJavaException(Exceptions_attribute exceptions_attribute, int i) {
        try {
            return getJavaName(exceptions_attribute.getException(i, this.constant_pool));
        } catch (ConstantPoolException e) {
            return report(e);
        }
    }

    public String getJavaFieldType(Descriptor descriptor) {
        try {
            return getJavaName(descriptor.getFieldType(this.constant_pool));
        } catch (ConstantPoolException e) {
            return report(e);
        } catch (Descriptor.InvalidDescriptor e2) {
            return report(e2);
        }
    }

    public String getJavaInterfaceName(ClassFile classFile, int i) {
        try {
            return getJavaName(classFile.getInterfaceName(i));
        } catch (ConstantPoolException e) {
            return report(e);
        }
    }

    public String getJavaName(ClassFile classFile) {
        try {
            return getJavaName(classFile.getName());
        } catch (ConstantPoolException e) {
            return report(e);
        }
    }

    public String getJavaParameterTypes(Descriptor descriptor, AccessFlags accessFlags) {
        try {
            return getJavaName(adjustVarargs(accessFlags, descriptor.getParameterTypes(this.constant_pool)));
        } catch (ConstantPoolException e) {
            return report(e);
        } catch (Descriptor.InvalidDescriptor e2) {
            return report(e2);
        }
    }

    public String getJavaReturnType(Descriptor descriptor) {
        try {
            return getJavaName(descriptor.getReturnType(this.constant_pool));
        } catch (ConstantPoolException e) {
            return report(e);
        } catch (Descriptor.InvalidDescriptor e2) {
            return report(e2);
        }
    }

    public String getJavaSuperclassName(ClassFile classFile) {
        try {
            return getJavaName(classFile.getSuperclassName());
        } catch (ConstantPoolException e) {
            return report(e);
        }
    }

    public Method getMethod() {
        return this.method;
    }

    public String getModuleName(int i) throws ConstantPoolException {
        int tag = this.constant_pool.get(i).getTag();
        ConstantPool constantPool = this.constant_pool;
        return tag == 19 ? constantPool.getModuleInfo(i).getName() : constantPool.getUTF8Value(i);
    }

    public String getName(Method method) {
        try {
            return method.getName(this.constant_pool);
        } catch (ConstantPoolException e) {
            return report(e);
        }
    }

    public String getPackageName(int i) throws ConstantPoolException {
        int tag = this.constant_pool.get(i).getTag();
        ConstantPool constantPool = this.constant_pool;
        return tag == 20 ? constantPool.getPackageInfo(i).getName() : constantPool.getUTF8Value(i);
    }

    public Signature_attribute getSignature(Attributes attributes) {
        return (Signature_attribute) attributes.get(Attribute.Signature);
    }

    public String getSourceFile(SourceFile_attribute sourceFile_attribute) {
        try {
            return sourceFile_attribute.getSourceFile(this.constant_pool);
        } catch (ConstantPoolException e) {
            return report(e);
        }
    }

    public String getUTF8Value(int i) {
        try {
            return this.classFile.constant_pool.getUTF8Value(i);
        } catch (ConstantPoolException e) {
            return report(e);
        }
    }

    public String getValue(Descriptor descriptor) {
        try {
            return descriptor.getValue(this.constant_pool);
        } catch (ConstantPoolException e) {
            return report(e);
        }
    }

    public void setClassFile(ClassFile classFile) {
        this.classFile = classFile;
        this.constant_pool = classFile.constant_pool;
    }

    public void setDigest(String str, byte[] bArr) {
        this.digestName = str;
        this.digest = bArr;
    }

    public void setFile(URI uri) {
        this.uri = uri;
    }

    public void setFileSize(int i) {
        this.size = i;
    }

    public void setLastModified(long j) {
        this.lastModified = j;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public void write(ClassFile classFile) {
        String strReport;
        setClassFile(classFile);
        Options options = this.options;
        int i = 0;
        if (options.sysInfo || options.verbose) {
            URI uri = this.uri;
            if (uri != null) {
                boolean zEquals = uri.getScheme().equals("file");
                URI uri2 = this.uri;
                if (zEquals) {
                    println("Classfile " + uri2.getPath());
                } else {
                    println("Classfile " + uri2);
                }
            }
            indent(1);
            if (this.lastModified != -1) {
                Date date = new Date(this.lastModified);
                DateFormat dateInstance = DateFormat.getDateInstance();
                if (this.size > 0) {
                    println("Last modified " + dateInstance.format(date) + "; size " + this.size + " bytes");
                } else {
                    println("Last modified " + dateInstance.format(date));
                }
            } else if (this.size > 0) {
                println("Size " + this.size + " bytes");
            }
            if (this.digestName != null && this.digest != null) {
                StringBuilder sb = new StringBuilder();
                for (byte b : this.digest) {
                    sb.append(String.format("%02x", Byte.valueOf(b)));
                }
                println(this.digestName + " checksum " + ((Object) sb));
            }
        }
        Attribute attribute = classFile.getAttribute(Attribute.SourceFile);
        if (attribute instanceof SourceFile_attribute) {
            println("Compiled from \"" + getSourceFile((SourceFile_attribute) attribute) + "\"");
        }
        Options options2 = this.options;
        if (options2.sysInfo || options2.verbose) {
            indent(-1);
        }
        AccessFlags accessFlags = classFile.access_flags;
        writeModifiers(accessFlags.getClassModifiers());
        boolean zIs = this.classFile.access_flags.is(32768);
        ClassFile classFile2 = this.classFile;
        if (zIs) {
            Attribute attribute2 = classFile2.attributes.get(Attribute.Module);
            if (attribute2 instanceof Module_attribute) {
                Module_attribute module_attribute = (Module_attribute) attribute2;
                try {
                    int tag = this.constant_pool.get(module_attribute.module_name).getTag();
                    ConstantPool constantPool = this.constant_pool;
                    strReport = tag == 19 ? getJavaName(constantPool.getModuleInfo(module_attribute.module_name).getName()) : getJavaName(constantPool.getUTF8Value(module_attribute.module_name));
                } catch (ConstantPoolException e) {
                    strReport = report(e);
                }
                if ((module_attribute.module_flags & 32) != 0) {
                    print("open ");
                }
                print("module ");
                print(strReport);
                if (module_attribute.module_version_index != 0) {
                    print("@");
                    print(getUTF8Value(module_attribute.module_version_index));
                }
            } else {
                print("class ");
                print(getJavaName(this.classFile));
            }
        } else {
            if (classFile2.isClass()) {
                print("class ");
            } else if (this.classFile.isInterface()) {
                print("interface ");
            }
            print(getJavaName(this.classFile));
        }
        Signature_attribute signature = getSignature(classFile.attributes);
        if (signature == null) {
            if (this.classFile.isClass() && this.classFile.super_class != 0) {
                String javaSuperclassName = getJavaSuperclassName(classFile);
                if (!javaSuperclassName.equals(Constants.OBJECT_CLASS)) {
                    print(" extends ");
                    print(javaSuperclassName);
                }
            }
            while (true) {
                ClassFile classFile3 = this.classFile;
                if (i >= classFile3.interfaces.length) {
                    break;
                }
                print(i == 0 ? classFile3.isClass() ? " implements " : " extends " : ",");
                print(getJavaInterfaceName(this.classFile, i));
                i++;
            }
        } else {
            try {
                Type type = signature.getParsedSignature().getType(this.constant_pool);
                JavaTypePrinter javaTypePrinter = new JavaTypePrinter(this.classFile.isInterface());
                if (type instanceof Type.ClassSigType) {
                    print(javaTypePrinter.print(type));
                } else if (this.options.verbose || !type.isObject()) {
                    print(" extends ");
                    print(javaTypePrinter.print(type));
                }
            } catch (ConstantPoolException e2) {
                print(report(e2));
            } catch (IllegalStateException e3) {
                report("Invalid value for Signature attribute: " + e3.getMessage());
            }
        }
        if (this.options.verbose) {
            println();
            indent(1);
            println("minor version: " + classFile.minor_version);
            println("major version: " + classFile.major_version);
            writeList(String.format("flags: (0x%04x) ", Integer.valueOf(accessFlags.flags)), accessFlags.getClassFlags(), "\n");
            print("this_class: #" + classFile.this_class);
            if (classFile.this_class != 0) {
                tab();
                print("// " + this.constantWriter.stringValue(classFile.this_class));
            }
            println();
            print("super_class: #" + classFile.super_class);
            if (classFile.super_class != 0) {
                tab();
                print("// " + this.constantWriter.stringValue(classFile.super_class));
            }
            println();
            print("interfaces: " + classFile.interfaces.length);
            print(", fields: " + classFile.fields.length);
            print(", methods: " + classFile.methods.length);
            println(", attributes: " + classFile.attributes.attrs.length);
            indent(-1);
            this.constantWriter.writeConstantPool();
        } else {
            print(" ");
        }
        println("{");
        indent(1);
        if (accessFlags.is(32768) && !this.options.verbose) {
            writeDirectives();
        }
        writeFields();
        writeMethods();
        indent(-1);
        println("}");
        if (this.options.verbose) {
            this.attrWriter.write(classFile, classFile.attributes, this.constant_pool);
        }
    }

    public void writeDirectives() {
        char c;
        String strReport;
        String strReport2;
        String strReport3;
        String strReport4;
        String strReport5;
        Attribute attribute = this.classFile.attributes.get(Attribute.Module);
        if (attribute instanceof Module_attribute) {
            Module_attribute module_attribute = (Module_attribute) attribute;
            for (Module_attribute.RequiresEntry requiresEntry : module_attribute.requires) {
                print(PsiKeyword.REQUIRES);
                if ((requiresEntry.requires_flags & 64) != 0) {
                    print(" static");
                }
                if ((requiresEntry.requires_flags & 32) != 0) {
                    print(" transitive");
                }
                print(" ");
                try {
                    strReport5 = getModuleName(requiresEntry.requires_index);
                } catch (ConstantPoolException e) {
                    strReport5 = report(e);
                }
                print(strReport5);
                println(";");
            }
            Module_attribute.ExportsEntry[] exportsEntryArr = module_attribute.exports;
            int length = exportsEntryArr.length;
            int i = 0;
            while (true) {
                c = '.';
                if (i >= length) {
                    break;
                }
                Module_attribute.ExportsEntry exportsEntry = exportsEntryArr[i];
                print(PsiKeyword.EXPORTS);
                print(" ");
                try {
                    strReport3 = getPackageName(exportsEntry.exports_index).replace('/', '.');
                } catch (ConstantPoolException e2) {
                    strReport3 = report(e2);
                }
                print(strReport3);
                int[] iArr = exportsEntry.exports_to_index;
                boolean z = true;
                for (int i2 : iArr) {
                    try {
                        strReport4 = getModuleName(i2);
                    } catch (ConstantPoolException e3) {
                        strReport4 = report(e3);
                    }
                    if (z) {
                        println(" to");
                        indent(1);
                        z = false;
                    } else {
                        println(",");
                    }
                    print(strReport4);
                }
                println(";");
                if (!z) {
                    indent(-1);
                }
                i++;
            }
            Module_attribute.OpensEntry[] opensEntryArr = module_attribute.opens;
            int length2 = opensEntryArr.length;
            int i3 = 0;
            while (i3 < length2) {
                Module_attribute.OpensEntry opensEntry = opensEntryArr[i3];
                print(PsiKeyword.OPENS);
                print(" ");
                try {
                    strReport = getPackageName(opensEntry.opens_index).replace('/', c);
                } catch (ConstantPoolException e4) {
                    strReport = report(e4);
                }
                print(strReport);
                boolean z2 = true;
                for (int i4 : opensEntry.opens_to_index) {
                    try {
                        strReport2 = getModuleName(i4);
                    } catch (ConstantPoolException e5) {
                        strReport2 = report(e5);
                    }
                    if (z2) {
                        println(" to");
                        indent(1);
                        z2 = false;
                    } else {
                        println(",");
                    }
                    print(strReport2);
                }
                println(";");
                if (!z2) {
                    indent(-1);
                }
                i3++;
                c = '.';
            }
            for (int i5 : module_attribute.uses_index) {
                print("uses ");
                print(getClassName(i5).replace('/', '.'));
                println(";");
            }
            for (Module_attribute.ProvidesEntry providesEntry : module_attribute.provides) {
                print("provides  ");
                print(getClassName(providesEntry.provides_index).replace('/', '.'));
                boolean z3 = true;
                for (int i6 : providesEntry.with_index) {
                    if (z3) {
                        println(" with");
                        indent(1);
                        z3 = false;
                    } else {
                        println(",");
                    }
                    print(getClassName(i6).replace('/', '.'));
                }
                println(";");
                if (!z3) {
                    indent(-1);
                }
            }
        }
    }

    public void writeField(Field field) {
        if (this.options.checkAccess(field.access_flags)) {
            AccessFlags accessFlags = field.access_flags;
            writeModifiers(accessFlags.getFieldModifiers());
            Signature_attribute signature = getSignature(field.attributes);
            if (signature == null) {
                print(getJavaFieldType(field.descriptor));
            } else {
                try {
                    print(getJavaName(signature.getParsedSignature().getType(this.constant_pool).toString()));
                } catch (ConstantPoolException unused) {
                    print(getJavaFieldType(field.descriptor));
                }
            }
            print(" ");
            print(getFieldName(field));
            if (this.options.showConstants) {
                Attribute attribute = field.attributes.get(Attribute.ConstantValue);
                if (attribute instanceof ConstantValue_attribute) {
                    print(" = ");
                    print(getConstantValue(field.descriptor, ((ConstantValue_attribute) attribute).constantvalue_index));
                }
            }
            print(";");
            println();
            boolean z = true;
            indent(1);
            if (this.options.showDescriptors) {
                println("descriptor: " + getValue(field.descriptor));
            }
            if (this.options.verbose) {
                writeList(String.format("flags: (0x%04x) ", Integer.valueOf(accessFlags.flags)), accessFlags.getFieldFlags(), "\n");
            }
            if (this.options.showAllAttrs) {
                Iterator<Attribute> it = field.attributes.iterator();
                while (it.hasNext()) {
                    this.attrWriter.write(field, it.next(), this.constant_pool);
                }
            } else {
                z = false;
            }
            indent(-1);
            if (!z) {
                Options options = this.options;
                if (!options.showDisassembled && !options.showLineAndLocalVariableTables) {
                    return;
                }
            }
            println();
        }
    }

    public void writeFields() {
        for (Field field : this.classFile.fields) {
            writeField(field);
        }
    }

    public void writeList(String str, Collection<?> collection, String str2) {
        print(str);
        String str3 = "";
        for (Object obj : collection) {
            print(str3);
            print(obj);
            str3 = ", ";
        }
        print(str2);
    }

    public void writeListIfNotEmpty(String str, List<?> list, String str2) {
        if (list == null || list.size() <= 0) {
            return;
        }
        writeList(str, list, str2);
    }

    /* JADX WARN: Code duplicated, block: B:31:0x0075  */
    /* JADX WARN: Code duplicated, block: B:34:0x008c  */
    /* JADX WARN: Code duplicated, block: B:36:0x0094  */
    /* JADX WARN: Code duplicated, block: B:37:0x00ab  */
    /* JADX WARN: Code duplicated, block: B:38:0x00bc  */
    /* JADX WARN: Code duplicated, block: B:41:0x00cc  */
    /* JADX WARN: Code duplicated, block: B:43:0x00d0  */
    /* JADX WARN: Code duplicated, block: B:45:0x00d9  */
    /* JADX WARN: Code duplicated, block: B:46:0x00df  */
    /* JADX WARN: Code duplicated, block: B:49:0x00e4 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:50:0x00e6  */
    /* JADX WARN: Code duplicated, block: B:52:0x00f5  */
    /* JADX WARN: Code duplicated, block: B:55:0x0109  */
    /* JADX WARN: Code duplicated, block: B:58:0x0126  */
    /* JADX WARN: Code duplicated, block: B:61:0x0149  */
    /* JADX WARN: Code duplicated, block: B:63:0x014d  */
    /* JADX WARN: Code duplicated, block: B:64:0x0151  */
    /* JADX WARN: Code duplicated, block: B:67:0x015c  */
    /* JADX WARN: Code duplicated, block: B:69:0x0164 A[LOOP:1: B:68:0x0162->B:69:0x0164, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:70:0x0170  */
    /* JADX WARN: Code duplicated, block: B:71:0x0172  */
    /* JADX WARN: Code duplicated, block: B:73:0x0176  */
    /* JADX WARN: Code duplicated, block: B:76:0x018b  */
    /* JADX WARN: Code duplicated, block: B:87:0x01c3  */
    /* JADX WARN: Code duplicated, block: B:95:0x00eb A[SYNTHETIC] */
    /* JADX WARN: Instruction removed from duplicated block: B:55:0x0109, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:67:0x015c, please report this as an issue */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v0, types: [com.sun.tools.javap.BasicWriter, com.sun.tools.javap.ClassWriter] */
    /* JADX WARN: Type inference failed for: r1v15 */
    /* JADX WARN: Type inference failed for: r1v16, types: [com.sun.tools.classfile.Descriptor] */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v18 */
    /* JADX WARN: Type inference failed for: r1v19 */
    /* JADX WARN: Type inference failed for: r1v20 */
    /* JADX WARN: Type inference failed for: r1v3, types: [com.sun.tools.classfile.Signature] */
    /* JADX WARN: Type inference failed for: r1v4, types: [com.sun.tools.classfile.Descriptor] */
    public void writeMethod(Method method) {
        ?? parsedSignature;
        Type.MethodType methodType;
        List<? extends Type> list;
        ?? r1;
        Set<String> methodModifiers;
        String name;
        Attribute attribute;
        Attribute attribute2;
        Options options;
        int i;
        Exceptions_attribute exceptions_attribute;
        int i2;
        ClassFile classFile;
        int i3;
        if (this.options.checkAccess(method.access_flags)) {
            this.method = method;
            AccessFlags accessFlags = method.access_flags;
            Signature_attribute signature = getSignature(method.attributes);
            Code_attribute code_attribute = null;
            if (signature != null) {
                parsedSignature = signature.getParsedSignature();
                try {
                    methodType = (Type.MethodType) parsedSignature.getType(this.constant_pool);
                    list = methodType.throwsTypes;
                    r1 = parsedSignature;
                    if (list != null && list.isEmpty()) {
                        r1 = parsedSignature;
                        list = null;
                        r1 = parsedSignature;
                    }
                } catch (ConstantPoolException | IllegalStateException unused) {
                    methodType = null;
                    list = null;
                    r1 = parsedSignature;
                }
                r1 = parsedSignature;
                methodModifiers = accessFlags.getMethodModifiers();
                name = getName(method);
                if (this.classFile.isInterface() && !accessFlags.is(1024) && !name.equals(Const.STATIC_INITIALIZER_NAME) && (((i3 = (classFile = this.classFile).major_version) > 52 || (i3 == 52 && classFile.minor_version >= 0)) && !accessFlags.is(10))) {
                    methodModifiers.add("default");
                }
                writeModifiers(methodModifiers);
                if (methodType != null) {
                    print(new JavaTypePrinter(false).printTypeArgs(methodType.typeParamTypes));
                }
                name.getClass();
                if (!name.equals(Const.STATIC_INITIALIZER_NAME)) {
                    print("{}");
                } else if (name.equals(Const.CONSTRUCTOR_NAME)) {
                    print(getJavaName(this.classFile));
                    print(getJavaParameterTypes(r1, accessFlags));
                } else {
                    print(getJavaReturnType(r1));
                    print(" ");
                    print(name);
                    print(getJavaParameterTypes(r1, accessFlags));
                }
                attribute = method.attributes.get(Attribute.Exceptions);
                if (attribute != null) {
                    if (attribute instanceof Exceptions_attribute) {
                        exceptions_attribute = (Exceptions_attribute) attribute;
                        print(" throws ");
                        if (list != null) {
                            writeList("", list, "");
                        } else {
                            for (i2 = 0; i2 < exceptions_attribute.number_of_exceptions; i2++) {
                                if (i2 > 0) {
                                    print(", ");
                                }
                                print(getJavaException(exceptions_attribute, i2));
                            }
                        }
                    } else {
                        report("Unexpected or invalid value for Exceptions attribute");
                    }
                }
                println(";");
                indent(1);
                if (this.options.showDescriptors) {
                    println("descriptor: " + getValue(method.descriptor));
                }
                if (this.options.verbose) {
                    writeList(String.format("flags: (0x%04x) ", Integer.valueOf(accessFlags.flags)), accessFlags.getMethodFlags(), "\n");
                }
                attribute2 = method.attributes.get(Attribute.Code);
                if (attribute2 != null) {
                    if (attribute2 instanceof Code_attribute) {
                        code_attribute = (Code_attribute) attribute2;
                    } else {
                        report("Unexpected or invalid value for Code attribute");
                    }
                }
                options = this.options;
                if (options.showAllAttrs) {
                    for (Attribute attribute3 : method.attributes.attrs) {
                        this.attrWriter.write(method, attribute3, this.constant_pool);
                    }
                } else if (code_attribute != null) {
                    if (options.showDisassembled) {
                        println("Code:");
                        this.codeWriter.writeInstrs(code_attribute);
                        this.codeWriter.writeExceptionTable(code_attribute);
                    }
                    if (this.options.showLineAndLocalVariableTables) {
                        this.attrWriter.write(code_attribute, code_attribute.attributes.get(Attribute.LineNumberTable), this.constant_pool);
                        this.attrWriter.write(code_attribute, code_attribute.attributes.get(Attribute.LocalVariableTable), this.constant_pool);
                    }
                }
                indent(-1);
                Options options2 = this.options;
                setPendingNewline(!options2.showDisassembled || options2.showAllAttrs || options2.showDescriptors || options2.showLineAndLocalVariableTables || options2.verbose);
            }
            parsedSignature = method.descriptor;
            methodType = null;
            list = null;
            r1 = parsedSignature;
            r1 = parsedSignature;
            methodModifiers = accessFlags.getMethodModifiers();
            name = getName(method);
            if (this.classFile.isInterface()) {
                methodModifiers.add("default");
            }
            writeModifiers(methodModifiers);
            if (methodType != null) {
                print(new JavaTypePrinter(false).printTypeArgs(methodType.typeParamTypes));
            }
            name.getClass();
            if (!name.equals(Const.STATIC_INITIALIZER_NAME)) {
                print("{}");
            } else if (name.equals(Const.CONSTRUCTOR_NAME)) {
                print(getJavaReturnType(r1));
                print(" ");
                print(name);
                print(getJavaParameterTypes(r1, accessFlags));
            } else {
                print(getJavaName(this.classFile));
                print(getJavaParameterTypes(r1, accessFlags));
            }
            attribute = method.attributes.get(Attribute.Exceptions);
            if (attribute != null) {
                if (attribute instanceof Exceptions_attribute) {
                    exceptions_attribute = (Exceptions_attribute) attribute;
                    print(" throws ");
                    if (list != null) {
                        writeList("", list, "");
                    } else {
                        while (i2 < exceptions_attribute.number_of_exceptions) {
                            if (i2 > 0) {
                                print(", ");
                            }
                            print(getJavaException(exceptions_attribute, i2));
                        }
                    }
                } else {
                    report("Unexpected or invalid value for Exceptions attribute");
                }
            }
            println(";");
            indent(1);
            if (this.options.showDescriptors) {
                println("descriptor: " + getValue(method.descriptor));
            }
            if (this.options.verbose) {
                writeList(String.format("flags: (0x%04x) ", Integer.valueOf(accessFlags.flags)), accessFlags.getMethodFlags(), "\n");
            }
            attribute2 = method.attributes.get(Attribute.Code);
            if (attribute2 != null) {
                if (attribute2 instanceof Code_attribute) {
                    code_attribute = (Code_attribute) attribute2;
                } else {
                    report("Unexpected or invalid value for Code attribute");
                }
            }
            options = this.options;
            if (options.showAllAttrs) {
                while (i < r2) {
                    this.attrWriter.write(method, attribute3, this.constant_pool);
                }
            } else if (code_attribute != null) {
                if (options.showDisassembled) {
                    println("Code:");
                    this.codeWriter.writeInstrs(code_attribute);
                    this.codeWriter.writeExceptionTable(code_attribute);
                }
                if (this.options.showLineAndLocalVariableTables) {
                    this.attrWriter.write(code_attribute, code_attribute.attributes.get(Attribute.LineNumberTable), this.constant_pool);
                    this.attrWriter.write(code_attribute, code_attribute.attributes.get(Attribute.LocalVariableTable), this.constant_pool);
                }
            }
            indent(-1);
            Options options3 = this.options;
            setPendingNewline(!options3.showDisassembled || options3.showAllAttrs || options3.showDescriptors || options3.showLineAndLocalVariableTables || options3.verbose);
        }
    }

    public void writeMethods() {
        for (Method method : this.classFile.methods) {
            writeMethod(method);
        }
        setPendingNewline(false);
    }

    public void writeModifiers(Collection<String> collection) {
        Iterator<String> it = collection.iterator();
        while (it.hasNext()) {
            print((Object) it.next());
            print(" ");
        }
    }

    public static String getJavaName(String str) {
        return str.replace('/', '.');
    }

    public class JavaTypePrinter implements Type.Visitor<StringBuilder, StringBuilder> {
        boolean isInterface;

        public JavaTypePrinter(boolean z) {
            this.isInterface = z;
        }

        private void append(StringBuilder sb, String str, List<? extends Type> list, String str2) {
            sb.append(str);
            String str3 = "";
            for (Type type : list) {
                sb.append(str3);
                append(sb, type);
                str3 = ", ";
            }
            sb.append(str2);
        }

        private void appendIfNotEmpty(StringBuilder sb, String str, List<? extends Type> list, String str2) {
            if (isEmpty(list)) {
                return;
            }
            append(sb, str, list, str2);
        }

        private boolean isEmpty(List<? extends Type> list) {
            return list == null || list.isEmpty();
        }

        public String print(Type type) {
            return ((StringBuilder) type.accept(this, new StringBuilder())).toString();
        }

        public String printTypeArgs(List<? extends Type.TypeParamType> list) {
            StringBuilder sb = new StringBuilder();
            appendIfNotEmpty(sb, "<", list, "> ");
            return sb.toString();
        }

        @Override // com.sun.tools.classfile.Type.Visitor
        public StringBuilder visitArrayType(Type.ArrayType arrayType, StringBuilder sb) {
            append(sb, arrayType.elemType);
            sb.append("[]");
            return sb;
        }

        @Override // com.sun.tools.classfile.Type.Visitor
        public StringBuilder visitClassSigType(Type.ClassSigType classSigType, StringBuilder sb) {
            appendIfNotEmpty(sb, "<", classSigType.typeParamTypes, ">");
            if (this.isInterface) {
                appendIfNotEmpty(sb, " extends ", classSigType.superinterfaceTypes, "");
                return sb;
            }
            if (classSigType.superclassType != null && (ClassWriter.this.options.verbose || !classSigType.superclassType.isObject())) {
                sb.append(" extends ");
                append(sb, classSigType.superclassType);
            }
            appendIfNotEmpty(sb, " implements ", classSigType.superinterfaceTypes, "");
            return sb;
        }

        @Override // com.sun.tools.classfile.Type.Visitor
        public StringBuilder visitClassType(Type.ClassType classType, StringBuilder sb) {
            Type.ClassType classType2 = classType.outerType;
            if (classType2 != null) {
                append(sb, classType2);
                sb.append(com.sun.org.apache.xalan.internal.templates.Constants.ATTRVAL_THIS);
            }
            sb.append(ClassWriter.getJavaName(classType.name));
            appendIfNotEmpty(sb, "<", classType.typeArgs, ">");
            return sb;
        }

        @Override // com.sun.tools.classfile.Type.Visitor
        public StringBuilder visitMethodType(Type.MethodType methodType, StringBuilder sb) {
            appendIfNotEmpty(sb, "<", methodType.typeParamTypes, "> ");
            append(sb, methodType.returnType);
            append(sb, " (", methodType.paramTypes, ")");
            appendIfNotEmpty(sb, " throws ", methodType.throwsTypes, "");
            return sb;
        }

        @Override // com.sun.tools.classfile.Type.Visitor
        public StringBuilder visitSimpleType(Type.SimpleType simpleType, StringBuilder sb) {
            sb.append(ClassWriter.getJavaName(simpleType.name));
            return sb;
        }

        @Override // com.sun.tools.classfile.Type.Visitor
        public StringBuilder visitTypeParamType(Type.TypeParamType typeParamType, StringBuilder sb) {
            sb.append(typeParamType.name);
            String str = " extends ";
            if (typeParamType.classBound != null && (ClassWriter.this.options.verbose || !typeParamType.classBound.isObject())) {
                sb.append(" extends ");
                append(sb, typeParamType.classBound);
                str = " & ";
            }
            List<Type> list = typeParamType.interfaceBounds;
            if (list != null) {
                for (Type type : list) {
                    sb.append(str);
                    append(sb, type);
                    str = " & ";
                }
            }
            return sb;
        }

        @Override // com.sun.tools.classfile.Type.Visitor
        public StringBuilder visitWildcardType(Type.WildcardType wildcardType, StringBuilder sb) {
            int i = AnonymousClass1.$SwitchMap$com$sun$tools$classfile$Type$WildcardType$Kind[wildcardType.kind.ordinal()];
            if (i == 1) {
                sb.append("?");
                return sb;
            }
            if (i == 2) {
                sb.append("? extends ");
                append(sb, wildcardType.boundType);
                return sb;
            }
            if (i != 3) {
                x1f.a();
                return null;
            }
            sb.append("? super ");
            append(sb, wildcardType.boundType);
            return sb;
        }

        private void append(StringBuilder sb, Type type) {
            type.accept(this, sb);
        }
    }
}
