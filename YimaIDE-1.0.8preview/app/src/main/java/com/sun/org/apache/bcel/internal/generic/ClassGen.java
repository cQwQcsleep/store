package com.sun.org.apache.bcel.internal.generic;

import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.bcel.internal.classfile.AccessFlags;
import com.sun.org.apache.bcel.internal.classfile.AnnotationEntry;
import com.sun.org.apache.bcel.internal.classfile.Annotations;
import com.sun.org.apache.bcel.internal.classfile.Attribute;
import com.sun.org.apache.bcel.internal.classfile.Field;
import com.sun.org.apache.bcel.internal.classfile.JavaClass;
import com.sun.org.apache.bcel.internal.classfile.Method;
import com.sun.org.apache.bcel.internal.classfile.RuntimeInvisibleAnnotations;
import com.sun.org.apache.bcel.internal.classfile.RuntimeVisibleAnnotations;
import com.sun.org.apache.bcel.internal.classfile.SourceFile;
import com.sun.org.apache.bcel.internal.classfile.Utility;
import com.sun.org.apache.bcel.internal.generic.ClassGen;
import com.sun.org.apache.bcel.internal.util.BCELComparator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.IntUnaryOperator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ClassGen extends AccessFlags implements Cloneable {
    private static BCELComparator bcelComparator = new BCELComparator() { // from class: com.sun.org.apache.bcel.internal.generic.ClassGen.1
        @Override // com.sun.org.apache.bcel.internal.util.BCELComparator
        public boolean equals(Object obj, Object obj2) {
            return Objects.equals(((ClassGen) obj).getClassName(), ((ClassGen) obj2).getClassName());
        }

        @Override // com.sun.org.apache.bcel.internal.util.BCELComparator
        public int hashCode(Object obj) {
            return ((ClassGen) obj).getClassName().hashCode();
        }
    };
    private final List<AnnotationEntryGen> annotationList;
    private final List<Attribute> attributeList;
    private String className;
    private int classNameIndex;
    private ConstantPoolGen cp;
    private final List<Field> fieldList;
    private final String fileName;
    private final List<String> interfaceList;
    private int major;
    private final List<Method> methodList;
    private int minor;
    private List<ClassObserver> observers;
    private String superClassName;
    private int superclassNameIndex;

    public ClassGen(JavaClass javaClass) {
        super(javaClass.getAccessFlags());
        this.fieldList = new ArrayList();
        this.methodList = new ArrayList();
        this.attributeList = new ArrayList();
        ArrayList arrayList = new ArrayList();
        this.interfaceList = arrayList;
        this.annotationList = new ArrayList();
        this.classNameIndex = -1;
        this.superclassNameIndex = -1;
        this.major = 45;
        this.minor = 3;
        this.classNameIndex = javaClass.getClassNameIndex();
        this.superclassNameIndex = javaClass.getSuperclassNameIndex();
        this.className = javaClass.getClassName();
        this.superClassName = javaClass.getSuperclassName();
        this.fileName = javaClass.getSourceFileName();
        this.cp = new ConstantPoolGen(javaClass.getConstantPool());
        this.major = javaClass.getMajor();
        this.minor = javaClass.getMinor();
        Attribute[] attributes = javaClass.getAttributes();
        AnnotationEntryGen[] annotationEntryGenArrUnpackAnnotations = unpackAnnotations(attributes);
        Collections.addAll(arrayList, javaClass.getInterfaceNames());
        for (Attribute attribute : attributes) {
            if (!(attribute instanceof Annotations)) {
                addAttribute(attribute);
            }
        }
        Collections.addAll(this.annotationList, annotationEntryGenArrUnpackAnnotations);
        Collections.addAll(this.methodList, javaClass.getMethods());
        Collections.addAll(this.fieldList, javaClass.getFields());
    }

    public static /* synthetic */ void a(ClassGen classGen, List list, AnnotationEntry annotationEntry) {
        classGen.getClass();
        list.add(new AnnotationEntryGen(annotationEntry, classGen.getConstantPool(), false));
    }

    public static /* synthetic */ void b(ClassGen classGen, List list, AnnotationEntry annotationEntry) {
        classGen.getClass();
        list.add(new AnnotationEntryGen(annotationEntry, classGen.getConstantPool(), false));
    }

    public static BCELComparator getComparator() {
        return bcelComparator;
    }

    public static void setComparator(BCELComparator bCELComparator) {
        bcelComparator = bCELComparator;
    }

    private AnnotationEntryGen[] unpackAnnotations(Attribute[] attributeArr) {
        final ArrayList arrayList = new ArrayList();
        for (Attribute attribute : attributeArr) {
            if (attribute instanceof RuntimeVisibleAnnotations) {
                ((RuntimeVisibleAnnotations) attribute).forEach(new Consumer() { // from class: jv1
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        ClassGen.a(this.b, arrayList, (AnnotationEntry) obj);
                    }
                });
            } else if (attribute instanceof RuntimeInvisibleAnnotations) {
                ((RuntimeInvisibleAnnotations) attribute).forEach(new Consumer() { // from class: kv1
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        ClassGen.b(this.b, arrayList, (AnnotationEntry) obj);
                    }
                });
            }
        }
        return (AnnotationEntryGen[]) arrayList.toArray(AnnotationEntryGen.EMPTY_ARRAY);
    }

    public void addAnnotationEntry(AnnotationEntryGen annotationEntryGen) {
        this.annotationList.add(annotationEntryGen);
    }

    public void addAttribute(Attribute attribute) {
        this.attributeList.add(attribute);
    }

    public void addEmptyConstructor(int i) {
        InstructionList instructionList = new InstructionList();
        instructionList.append(InstructionConst.THIS);
        instructionList.append(new INVOKESPECIAL(this.cp.addMethodref(this.superClassName, Const.CONSTRUCTOR_NAME, "()V")));
        instructionList.append(InstructionConst.RETURN);
        MethodGen methodGen = new MethodGen(i, Type.VOID, Type.NO_ARGS, null, Const.CONSTRUCTOR_NAME, this.className, instructionList, this.cp);
        methodGen.setMaxStack(1);
        addMethod(methodGen.getMethod());
    }

    public void addField(Field field) {
        this.fieldList.add(field);
    }

    public void addInterface(String str) {
        this.interfaceList.add(str);
    }

    public void addMethod(Method method) {
        this.methodList.add(method);
    }

    public void addObserver(ClassObserver classObserver) {
        if (this.observers == null) {
            this.observers = new ArrayList();
        }
        this.observers.add(classObserver);
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException unused) {
            throw new Error("Clone Not Supported");
        }
    }

    public Field containsField(String str) {
        for (Field field : this.fieldList) {
            if (field.getName().equals(str)) {
                return field;
            }
        }
        return null;
    }

    public Method containsMethod(String str, String str2) {
        for (Method method : this.methodList) {
            if (method.getName().equals(str) && method.getSignature().equals(str2)) {
                return method;
            }
        }
        return null;
    }

    public boolean equals(Object obj) {
        return bcelComparator.equals(this, obj);
    }

    public AnnotationEntryGen[] getAnnotationEntries() {
        return (AnnotationEntryGen[]) this.annotationList.toArray(AnnotationEntryGen.EMPTY_ARRAY);
    }

    public Attribute[] getAttributes() {
        return (Attribute[]) this.attributeList.toArray(Attribute.EMPTY_ARRAY);
    }

    public String getClassName() {
        return this.className;
    }

    public int getClassNameIndex() {
        return this.classNameIndex;
    }

    public ConstantPoolGen getConstantPool() {
        return this.cp;
    }

    public Field[] getFields() {
        return (Field[]) this.fieldList.toArray(Field.EMPTY_ARRAY);
    }

    public String getFileName() {
        return this.fileName;
    }

    public String[] getInterfaceNames() {
        return (String[]) this.interfaceList.toArray(Const.EMPTY_STRING_ARRAY);
    }

    public int[] getInterfaces() {
        int[] iArr = new int[this.interfaceList.size()];
        Arrays.setAll(iArr, new IntUnaryOperator() { // from class: lv1
            @Override // java.util.function.IntUnaryOperator
            public final int applyAsInt(int i) {
                ClassGen classGen = this.b;
                return classGen.cp.addClass(classGen.interfaceList.get(i));
            }
        });
        return iArr;
    }

    public JavaClass getJavaClass() {
        Attribute[] attributes;
        int[] interfaces = getInterfaces();
        Field[] fields = getFields();
        Method[] methods = getMethods();
        if (this.annotationList.isEmpty()) {
            attributes = getAttributes();
        } else {
            Attribute[] annotationAttributes = AnnotationEntryGen.getAnnotationAttributes(this.cp, getAnnotationEntries());
            Attribute[] attributeArr = new Attribute[this.attributeList.size() + annotationAttributes.length];
            this.attributeList.toArray(attributeArr);
            System.arraycopy(annotationAttributes, 0, attributeArr, this.attributeList.size(), annotationAttributes.length);
            attributes = attributeArr;
        }
        return new JavaClass(this.classNameIndex, this.superclassNameIndex, this.fileName, this.major, this.minor, super.getAccessFlags(), this.cp.getFinalConstantPool(), interfaces, fields, methods, attributes);
    }

    public int getMajor() {
        return this.major;
    }

    public Method getMethodAt(int i) {
        return this.methodList.get(i);
    }

    public Method[] getMethods() {
        return (Method[]) this.methodList.toArray(Method.EMPTY_ARRAY);
    }

    public int getMinor() {
        return this.minor;
    }

    public String getSuperclassName() {
        return this.superClassName;
    }

    public int getSuperclassNameIndex() {
        return this.superclassNameIndex;
    }

    public int hashCode() {
        return bcelComparator.hashCode(this);
    }

    public void removeAttribute(Attribute attribute) {
        this.attributeList.remove(attribute);
    }

    public void removeField(Field field) {
        this.fieldList.remove(field);
    }

    public void removeInterface(String str) {
        this.interfaceList.remove(str);
    }

    public void removeMethod(Method method) {
        this.methodList.remove(method);
    }

    public void removeObserver(ClassObserver classObserver) {
        List<ClassObserver> list = this.observers;
        if (list != null) {
            list.remove(classObserver);
        }
    }

    public void replaceField(Field field, Field field2) {
        if (field2 == null) {
            throw new ClassGenException("Replacement method must not be null");
        }
        int iIndexOf = this.fieldList.indexOf(field);
        List<Field> list = this.fieldList;
        if (iIndexOf < 0) {
            list.add(field2);
        } else {
            list.set(iIndexOf, field2);
        }
    }

    public void replaceMethod(Method method, Method method2) {
        if (method2 == null) {
            throw new ClassGenException("Replacement method must not be null");
        }
        int iIndexOf = this.methodList.indexOf(method);
        List<Method> list = this.methodList;
        if (iIndexOf < 0) {
            list.add(method2);
        } else {
            list.set(iIndexOf, method2);
        }
    }

    public void setClassName(String str) {
        this.className = Utility.pathToPackage(str);
        this.classNameIndex = this.cp.addClass(str);
    }

    public void setClassNameIndex(int i) {
        this.classNameIndex = i;
        this.className = Utility.pathToPackage(this.cp.getConstantPool().getConstantString(i, (byte) 7));
    }

    public void setConstantPool(ConstantPoolGen constantPoolGen) {
        this.cp = constantPoolGen;
    }

    public void setMajor(int i) {
        this.major = i;
    }

    public void setMethodAt(Method method, int i) {
        this.methodList.set(i, method);
    }

    public void setMethods(Method[] methodArr) {
        this.methodList.clear();
        Collections.addAll(this.methodList, methodArr);
    }

    public void setMinor(int i) {
        this.minor = i;
    }

    public void setSuperclassName(String str) {
        this.superClassName = Utility.pathToPackage(str);
        this.superclassNameIndex = this.cp.addClass(str);
    }

    public void setSuperclassNameIndex(int i) {
        this.superclassNameIndex = i;
        this.superClassName = Utility.pathToPackage(this.cp.getConstantPool().getConstantString(i, (byte) 7));
    }

    public void update() {
        List<ClassObserver> list = this.observers;
        if (list != null) {
            Iterator<ClassObserver> it = list.iterator();
            while (it.hasNext()) {
                it.next().notify(this);
            }
        }
    }

    public boolean containsField(Field field) {
        return this.fieldList.contains(field);
    }

    public ClassGen(String str, String str2, String str3, int i, String[] strArr) {
        this(str, str2, str3, i, strArr, new ConstantPoolGen());
    }

    public ClassGen(String str, String str2, String str3, int i, String[] strArr, ConstantPoolGen constantPoolGen) {
        super(i);
        this.fieldList = new ArrayList();
        this.methodList = new ArrayList();
        this.attributeList = new ArrayList();
        ArrayList arrayList = new ArrayList();
        this.interfaceList = arrayList;
        this.annotationList = new ArrayList();
        this.classNameIndex = -1;
        this.superclassNameIndex = -1;
        this.major = 45;
        this.minor = 3;
        this.className = str;
        this.superClassName = str2;
        this.fileName = str3;
        this.cp = constantPoolGen;
        if (str3 != null) {
            addAttribute(new SourceFile(constantPoolGen.addUtf8(com.sun.tools.classfile.Attribute.SourceFile), 2, constantPoolGen.addUtf8(str3), constantPoolGen.getConstantPool()));
        }
        this.classNameIndex = constantPoolGen.addClass(str);
        this.superclassNameIndex = constantPoolGen.addClass(str2);
        if (strArr != null) {
            Collections.addAll(arrayList, strArr);
        }
    }
}
