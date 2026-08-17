package com.sun.org.apache.bcel.internal.classfile;

import com.sun.jna.platform.win32.COM.tlb.imp.TlbBase;
import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.bcel.internal.classfile.InnerClass;
import com.sun.org.apache.bcel.internal.classfile.JavaClass;
import com.sun.org.apache.bcel.internal.generic.Type;
import com.sun.org.apache.bcel.internal.util.BCELComparator;
import com.sun.org.apache.bcel.internal.util.ClassQueue;
import com.sun.org.apache.bcel.internal.util.Repository;
import com.sun.org.apache.bcel.internal.util.SyntheticRepository;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Constants;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.function.IntFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class JavaClass extends AccessFlags implements Cloneable, Node, Comparable<JavaClass> {
    public static final String EXTENSION = ".class";
    public static final byte FILE = 2;
    public static final byte HEAP = 1;
    public static final byte ZIP = 3;
    private AnnotationEntry[] annotations;
    private Attribute[] attributes;
    private String className;
    private int classNameIndex;
    private boolean computedNestedTypeStatus;
    private ConstantPool constantPool;
    private Field[] fields;
    private String fileName;
    private String[] interfaceNames;
    private int[] interfaces;
    private boolean isAnonymous;
    private boolean isNested;
    private int major;
    private Method[] methods;
    private int minor;
    private final String packageName;
    private transient Repository repository;
    private byte source;
    private String sourceFileName;
    private String superclassName;
    private int superclassNameIndex;
    public static final JavaClass[] EMPTY_ARRAY = new JavaClass[0];
    private static BCELComparator bcelComparator = new BCELComparator() { // from class: com.sun.org.apache.bcel.internal.classfile.JavaClass.1
        @Override // com.sun.org.apache.bcel.internal.util.BCELComparator
        public boolean equals(Object obj, Object obj2) {
            return Objects.equals(((JavaClass) obj).getClassName(), ((JavaClass) obj2).getClassName());
        }

        @Override // com.sun.org.apache.bcel.internal.util.BCELComparator
        public int hashCode(Object obj) {
            return ((JavaClass) obj).getClassName().hashCode();
        }
    };

    public JavaClass(int i, int i2, String str, int i3, int i4, int i5, ConstantPool constantPool, int[] iArr, Field[] fieldArr, Method[] methodArr, Attribute[] attributeArr, byte b) {
        super(i5);
        this.sourceFileName = "<Unknown>";
        this.source = (byte) 1;
        this.repository = SyntheticRepository.getInstance();
        iArr = iArr == null ? Const.EMPTY_INT_ARRAY : iArr;
        attributeArr = attributeArr == null ? Attribute.EMPTY_ARRAY : attributeArr;
        fieldArr = fieldArr == null ? Field.EMPTY_FIELD_ARRAY : fieldArr;
        methodArr = methodArr == null ? Method.EMPTY_METHOD_ARRAY : methodArr;
        this.classNameIndex = i;
        this.superclassNameIndex = i2;
        this.fileName = str;
        this.major = i3;
        this.minor = i4;
        this.constantPool = constantPool;
        this.interfaces = iArr;
        this.fields = fieldArr;
        this.methods = methodArr;
        this.attributes = attributeArr;
        this.source = b;
        for (Attribute attribute : attributeArr) {
            if (attribute instanceof SourceFile) {
                this.sourceFileName = ((SourceFile) attribute).getSourceFileName();
                break;
            }
        }
        String constantString = constantPool.getConstantString(i, (byte) 7);
        this.className = constantString;
        String strCompactClassName = Utility.compactClassName(constantString, false);
        this.className = strCompactClassName;
        int iLastIndexOf = strCompactClassName.lastIndexOf(46);
        if (iLastIndexOf < 0) {
            this.packageName = "";
        } else {
            this.packageName = this.className.substring(0, iLastIndexOf);
        }
        if (i2 > 0) {
            String constantString2 = constantPool.getConstantString(i2, (byte) 7);
            this.superclassName = constantString2;
            this.superclassName = Utility.compactClassName(constantString2, false);
        } else {
            this.superclassName = Constants.OBJECT_CLASS;
        }
        this.interfaceNames = new String[iArr.length];
        for (int i6 = 0; i6 < iArr.length; i6++) {
            this.interfaceNames[i6] = Utility.compactClassName(constantPool.getConstantString(iArr[i6], (byte) 7), false);
        }
    }

    private void computeNestedTypeStatus() {
        if (this.computedNestedTypeStatus) {
            return;
        }
        for (Attribute attribute : this.attributes) {
            if (attribute instanceof InnerClasses) {
                ((InnerClasses) attribute).forEach(new Consumer() { // from class: nb7
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        JavaClass.d(this.b, (InnerClass) obj);
                    }
                });
            }
        }
        this.computedNestedTypeStatus = true;
    }

    public static /* synthetic */ void d(JavaClass javaClass, InnerClass innerClass) {
        if (Utility.compactClassName(javaClass.constantPool.getConstantString(innerClass.getInnerClassIndex(), (byte) 7), false).equals(javaClass.getClassName())) {
            javaClass.isNested = true;
            if (innerClass.getInnerNameIndex() == 0) {
                javaClass.isAnonymous = true;
            }
        }
    }

    public static BCELComparator getComparator() {
        return bcelComparator;
    }

    private static String indent(Object obj) {
        StringTokenizer stringTokenizer = new StringTokenizer(obj.toString(), "\n");
        StringBuilder sb = new StringBuilder();
        while (stringTokenizer.hasMoreTokens()) {
            sb.append(TlbBase.TAB);
            sb.append(stringTokenizer.nextToken());
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void setComparator(BCELComparator bCELComparator) {
        bcelComparator = bCELComparator;
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Node
    public void accept(Visitor visitor) {
        visitor.visitJavaClass(this);
    }

    @Override // java.lang.Comparable
    public int compareTo(JavaClass javaClass) {
        return getClassName().compareTo(javaClass.getClassName());
    }

    public JavaClass copy() {
        try {
            final JavaClass javaClass = (JavaClass) clone();
            javaClass.constantPool = this.constantPool.copy();
            javaClass.interfaces = (int[]) this.interfaces.clone();
            javaClass.interfaceNames = (String[]) this.interfaceNames.clone();
            Field[] fieldArr = new Field[this.fields.length];
            javaClass.fields = fieldArr;
            Arrays.setAll(fieldArr, new IntFunction() { // from class: kb7
                @Override // java.util.function.IntFunction
                public final Object apply(int i) {
                    return this.b.fields[i].copy(javaClass.constantPool);
                }
            });
            Method[] methodArr = new Method[this.methods.length];
            javaClass.methods = methodArr;
            Arrays.setAll(methodArr, new IntFunction() { // from class: lb7
                @Override // java.util.function.IntFunction
                public final Object apply(int i) {
                    return this.b.methods[i].copy(javaClass.constantPool);
                }
            });
            Attribute[] attributeArr = new Attribute[this.attributes.length];
            javaClass.attributes = attributeArr;
            Arrays.setAll(attributeArr, new IntFunction() { // from class: mb7
                @Override // java.util.function.IntFunction
                public final Object apply(int i) {
                    return this.b.attributes[i].copy(javaClass.constantPool);
                }
            });
            return javaClass;
        } catch (CloneNotSupportedException unused) {
            return null;
        }
    }

    public void dump(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeInt(-889275714);
        dataOutputStream.writeShort(this.minor);
        dataOutputStream.writeShort(this.major);
        this.constantPool.dump(dataOutputStream);
        dataOutputStream.writeShort(super.getAccessFlags());
        dataOutputStream.writeShort(this.classNameIndex);
        dataOutputStream.writeShort(this.superclassNameIndex);
        dataOutputStream.writeShort(this.interfaces.length);
        for (int i : this.interfaces) {
            dataOutputStream.writeShort(i);
        }
        dataOutputStream.writeShort(this.fields.length);
        for (Field field : this.fields) {
            field.dump(dataOutputStream);
        }
        dataOutputStream.writeShort(this.methods.length);
        for (Method method : this.methods) {
            method.dump(dataOutputStream);
        }
        Attribute[] attributeArr = this.attributes;
        if (attributeArr != null) {
            dataOutputStream.writeShort(attributeArr.length);
            for (Attribute attribute : this.attributes) {
                attribute.dump(dataOutputStream);
            }
        } else {
            dataOutputStream.writeShort(0);
        }
        dataOutputStream.flush();
    }

    public boolean equals(Object obj) {
        return bcelComparator.equals(this, obj);
    }

    public JavaClass[] getAllInterfaces() throws ClassNotFoundException {
        ClassQueue classQueue = new ClassQueue();
        TreeSet treeSet = new TreeSet();
        classQueue.enqueue(this);
        while (!classQueue.empty()) {
            JavaClass javaClassDequeue = classQueue.dequeue();
            JavaClass superClass = javaClassDequeue.getSuperClass();
            JavaClass[] interfaces = javaClassDequeue.getInterfaces();
            if (javaClassDequeue.isInterface()) {
                treeSet.add(javaClassDequeue);
            } else if (superClass != null) {
                classQueue.enqueue(superClass);
            }
            for (JavaClass javaClass : interfaces) {
                classQueue.enqueue(javaClass);
            }
        }
        return (JavaClass[]) treeSet.toArray(EMPTY_ARRAY);
    }

    public AnnotationEntry[] getAnnotationEntries() {
        if (this.annotations == null) {
            this.annotations = AnnotationEntry.createAnnotationEntries(getAttributes());
        }
        return this.annotations;
    }

    public Attribute[] getAttributes() {
        return this.attributes;
    }

    public byte[] getBytes() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            try {
                dump(dataOutputStream);
                dataOutputStream.close();
            } catch (Throwable th) {
                try {
                    dataOutputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }

    public String getClassName() {
        return this.className;
    }

    public int getClassNameIndex() {
        return this.classNameIndex;
    }

    public ConstantPool getConstantPool() {
        return this.constantPool;
    }

    public Field[] getFields() {
        return this.fields;
    }

    public String getFileName() {
        return this.fileName;
    }

    public int[] getInterfaceIndices() {
        return this.interfaces;
    }

    public String[] getInterfaceNames() {
        return this.interfaceNames;
    }

    public JavaClass[] getInterfaces() throws ClassNotFoundException {
        String[] interfaceNames = getInterfaceNames();
        JavaClass[] javaClassArr = new JavaClass[interfaceNames.length];
        for (int i = 0; i < interfaceNames.length; i++) {
            javaClassArr[i] = this.repository.loadClass(interfaceNames[i]);
        }
        return javaClassArr;
    }

    public int getMajor() {
        return this.major;
    }

    public Method getMethod(java.lang.reflect.Method method) {
        for (Method method2 : this.methods) {
            if (method.getName().equals(method2.getName()) && method.getModifiers() == method2.getModifiers() && Type.getSignature(method).equals(method2.getSignature())) {
                return method2;
            }
        }
        return null;
    }

    public Method[] getMethods() {
        return this.methods;
    }

    public int getMinor() {
        return this.minor;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public Repository getRepository() {
        return this.repository;
    }

    public final byte getSource() {
        return this.source;
    }

    public String getSourceFileName() {
        return this.sourceFileName;
    }

    public String getSourceFilePath() {
        StringBuilder sb = new StringBuilder();
        if (!this.packageName.isEmpty()) {
            sb.append(Utility.packageToPath(this.packageName));
            sb.append('/');
        }
        sb.append(this.sourceFileName);
        return sb.toString();
    }

    public JavaClass getSuperClass() throws ClassNotFoundException {
        if (Constants.OBJECT_CLASS.equals(getClassName())) {
            return null;
        }
        return this.repository.loadClass(getSuperclassName());
    }

    public JavaClass[] getSuperClasses() throws ClassNotFoundException {
        ArrayList arrayList = new ArrayList();
        while (true) {
            this = this.getSuperClass();
            if (this == null) {
                return (JavaClass[]) arrayList.toArray(EMPTY_ARRAY);
            }
            arrayList.add(this);
        }
    }

    public String getSuperclassName() {
        return this.superclassName;
    }

    public int getSuperclassNameIndex() {
        return this.superclassNameIndex;
    }

    public int hashCode() {
        return bcelComparator.hashCode(this);
    }

    public boolean implementationOf(JavaClass javaClass) throws ClassNotFoundException {
        if (!javaClass.isInterface()) {
            iu9.a(javaClass.getClassName(), " is no interface");
            return false;
        }
        if (equals(javaClass)) {
            return true;
        }
        for (JavaClass javaClass2 : getAllInterfaces()) {
            if (javaClass2.equals(javaClass)) {
                return true;
            }
        }
        return false;
    }

    public final boolean instanceOf(JavaClass javaClass) throws ClassNotFoundException {
        if (equals(javaClass)) {
            return true;
        }
        for (JavaClass javaClass2 : getSuperClasses()) {
            if (javaClass2.equals(javaClass)) {
                return true;
            }
        }
        if (javaClass.isInterface()) {
            return implementationOf(javaClass);
        }
        return false;
    }

    public final boolean isAnonymous() {
        computeNestedTypeStatus();
        return this.isAnonymous;
    }

    public final boolean isClass() {
        return (super.getAccessFlags() & 512) == 0;
    }

    public final boolean isNested() {
        computeNestedTypeStatus();
        return this.isNested;
    }

    public final boolean isSuper() {
        return (super.getAccessFlags() & 32) != 0;
    }

    public void setAttributes(Attribute[] attributeArr) {
        this.attributes = attributeArr;
    }

    public void setClassName(String str) {
        this.className = str;
    }

    public void setClassNameIndex(int i) {
        this.classNameIndex = i;
    }

    public void setConstantPool(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    public void setFields(Field[] fieldArr) {
        this.fields = fieldArr;
    }

    public void setFileName(String str) {
        this.fileName = str;
    }

    public void setInterfaceNames(String[] strArr) {
        this.interfaceNames = strArr;
    }

    public void setInterfaces(int[] iArr) {
        this.interfaces = iArr;
    }

    public void setMajor(int i) {
        this.major = i;
    }

    public void setMethods(Method[] methodArr) {
        this.methods = methodArr;
    }

    public void setMinor(int i) {
        this.minor = i;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public void setSourceFileName(String str) {
        this.sourceFileName = str;
    }

    public void setSuperclassName(String str) {
        this.superclassName = str;
    }

    public void setSuperclassNameIndex(int i) {
        this.superclassNameIndex = i;
    }

    public String toString() {
        String strAccessToString = Utility.accessToString(super.getAccessFlags(), true);
        String strConcat = strAccessToString.isEmpty() ? "" : strAccessToString.concat(" ");
        StringBuilder sb = new StringBuilder(128);
        sb.append(strConcat);
        sb.append(Utility.classOrInterface(super.getAccessFlags()));
        sb.append(" ");
        sb.append(this.className);
        sb.append(" extends ");
        sb.append(Utility.compactClassName(this.superclassName, false));
        sb.append('\n');
        int length = this.interfaces.length;
        if (length > 0) {
            sb.append("implements\t\t");
            for (int i = 0; i < length; i++) {
                sb.append(this.interfaceNames[i]);
                if (i < length - 1) {
                    sb.append(", ");
                }
            }
            sb.append('\n');
        }
        sb.append("file name\t\t");
        sb.append(this.fileName);
        sb.append("\ncompiled from\t\t");
        sb.append(this.sourceFileName);
        sb.append("\ncompiler version\t");
        sb.append(this.major);
        sb.append(com.sun.org.apache.xalan.internal.templates.Constants.ATTRVAL_THIS);
        sb.append(this.minor);
        sb.append("\naccess flags\t\t");
        sb.append(super.getAccessFlags());
        sb.append("\nconstant pool\t\t");
        sb.append(this.constantPool.getLength());
        sb.append(" entries\nACC_SUPER flag\t\t");
        sb.append(isSuper());
        sb.append("\n");
        if (this.attributes.length > 0) {
            sb.append("\nAttribute(s):\n");
            for (Attribute attribute : this.attributes) {
                sb.append(indent(attribute));
            }
        }
        AnnotationEntry[] annotationEntries = getAnnotationEntries();
        if (annotationEntries != null && annotationEntries.length > 0) {
            sb.append("\nAnnotation(s):\n");
            for (AnnotationEntry annotationEntry : annotationEntries) {
                sb.append(indent(annotationEntry));
            }
        }
        if (this.fields.length > 0) {
            sb.append("\n");
            sb.append(this.fields.length);
            sb.append(" fields:\n");
            for (Field field : this.fields) {
                sb.append(TlbBase.TAB);
                sb.append(field);
                sb.append('\n');
            }
        }
        if (this.methods.length > 0) {
            sb.append("\n");
            sb.append(this.methods.length);
            sb.append(" methods:\n");
            for (Method method : this.methods) {
                sb.append(TlbBase.TAB);
                sb.append(method);
                sb.append('\n');
            }
        }
        return sb.toString();
    }

    public void dump(File file) throws IOException {
        String parent = file.getParent();
        if (parent != null) {
            File file2 = new File(parent);
            if (!file2.mkdirs() && !file2.isDirectory()) {
                r8g.a("Could not create the directory ", file2);
                return;
            }
        }
        DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file));
        try {
            dump(dataOutputStream);
            dataOutputStream.close();
        } catch (Throwable th) {
            try {
                dataOutputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public void dump(OutputStream outputStream) throws IOException {
        dump(new DataOutputStream(outputStream));
    }

    public void dump(String str) throws IOException {
        dump(new File(str));
    }

    public JavaClass(int i, int i2, String str, int i3, int i4, int i5, ConstantPool constantPool, int[] iArr, Field[] fieldArr, Method[] methodArr, Attribute[] attributeArr) {
        this(i, i2, str, i3, i4, i5, constantPool, iArr, fieldArr, methodArr, attributeArr, (byte) 1);
    }
}
