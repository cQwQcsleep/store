package com.sun.tools.classfile;

import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ClassTranslator implements ConstantPool.Visitor<ConstantPool.CPInfo, Map<Object, Object>> {
    private static <T> boolean equal(T[] tArr, T[] tArr2) {
        if (tArr == null || tArr2 == null) {
            return tArr == tArr2;
        }
        if (tArr.length != tArr2.length) {
            return false;
        }
        for (int i = 0; i < tArr.length; i++) {
            if (tArr[i] != tArr2[i]) {
                return false;
            }
        }
        return true;
    }

    public ClassFile translate(ClassFile classFile, Map<Object, Object> map) {
        ClassFile classFile2 = (ClassFile) map.get(classFile);
        if (classFile2 != null) {
            return classFile2;
        }
        ConstantPool constantPoolTranslate = translate(classFile.constant_pool, map);
        Field[] fieldArrTranslate = translate(classFile.fields, classFile.constant_pool, map);
        Method[] methodArrTranslateMethods = translateMethods(classFile.methods, classFile.constant_pool, map);
        Attributes attributesTranslateAttributes = translateAttributes(classFile.attributes, classFile.constant_pool, map);
        ClassFile classFile3 = (constantPoolTranslate == classFile.constant_pool && fieldArrTranslate == classFile.fields && methodArrTranslateMethods == classFile.methods && attributesTranslateAttributes == classFile.attributes) ? classFile : new ClassFile(classFile.magic, classFile.minor_version, classFile.major_version, constantPoolTranslate, classFile.access_flags, classFile.this_class, classFile.super_class, classFile.interfaces, fieldArrTranslate, methodArrTranslateMethods, attributesTranslateAttributes);
        map.put(classFile, classFile3);
        return classFile3;
    }

    public Attributes translateAttributes(Attributes attributes, ConstantPool constantPool, Map<Object, Object> map) {
        Attributes attributes2 = (Attributes) map.get(attributes);
        if (attributes2 != null) {
            return attributes2;
        }
        Attribute[] attributeArr = new Attribute[attributes.size()];
        ConstantPool constantPoolTranslate = translate(constantPool, map);
        boolean z = true;
        for (int i = 0; i < attributes.size(); i++) {
            Attribute attribute = attributes.get(i);
            Attribute attributeTranslate = translate(attribute, map);
            if (attributeTranslate != attribute) {
                z = false;
            }
            attributeArr[i] = attributeTranslate;
        }
        Attributes attributes3 = (constantPoolTranslate == constantPool && z) ? attributes : new Attributes(constantPoolTranslate, attributeArr);
        map.put(attributes, attributes3);
        return attributes3;
    }

    public Method[] translateMethods(Method[] methodArr, ConstantPool constantPool, Map<Object, Object> map) {
        Method[] methodArr2 = (Method[]) map.get(methodArr);
        if (methodArr2 == null) {
            methodArr2 = new Method[methodArr.length];
            for (int i = 0; i < methodArr.length; i++) {
                methodArr2[i] = translate(methodArr[i], constantPool, map);
            }
            if (equal(methodArr, methodArr2)) {
                methodArr2 = methodArr;
            }
            map.put(methodArr, methodArr2);
        }
        return methodArr2;
    }

    @Override // com.sun.tools.classfile.ConstantPool.Visitor
    public ConstantPool.CPInfo visitClass(ConstantPool.CONSTANT_Class_info cONSTANT_Class_info, Map<Object, Object> map) {
        if (((ConstantPool.CONSTANT_Class_info) map.get(cONSTANT_Class_info)) == null) {
            ConstantPool constantPoolTranslate = translate(cONSTANT_Class_info.cp, map);
            map.put(cONSTANT_Class_info, constantPoolTranslate == cONSTANT_Class_info.cp ? cONSTANT_Class_info : new ConstantPool.CONSTANT_Class_info(constantPoolTranslate, cONSTANT_Class_info.name_index));
        }
        return cONSTANT_Class_info;
    }

    @Override // com.sun.tools.classfile.ConstantPool.Visitor
    public ConstantPool.CPInfo visitDouble(ConstantPool.CONSTANT_Double_info cONSTANT_Double_info, Map<Object, Object> map) {
        if (((ConstantPool.CONSTANT_Double_info) map.get(cONSTANT_Double_info)) == null) {
            map.put(cONSTANT_Double_info, cONSTANT_Double_info);
        }
        return cONSTANT_Double_info;
    }

    @Override // com.sun.tools.classfile.ConstantPool.Visitor
    public ConstantPool.CPInfo visitDynamicConstant(ConstantPool.CONSTANT_Dynamic_info cONSTANT_Dynamic_info, Map<Object, Object> map) {
        if (((ConstantPool.CONSTANT_Dynamic_info) map.get(cONSTANT_Dynamic_info)) == null) {
            ConstantPool constantPoolTranslate = translate(cONSTANT_Dynamic_info.cp, map);
            map.put(cONSTANT_Dynamic_info, constantPoolTranslate == cONSTANT_Dynamic_info.cp ? cONSTANT_Dynamic_info : new ConstantPool.CONSTANT_Dynamic_info(constantPoolTranslate, cONSTANT_Dynamic_info.bootstrap_method_attr_index, cONSTANT_Dynamic_info.name_and_type_index));
        }
        return cONSTANT_Dynamic_info;
    }

    @Override // com.sun.tools.classfile.ConstantPool.Visitor
    public ConstantPool.CPInfo visitFieldref(ConstantPool.CONSTANT_Fieldref_info cONSTANT_Fieldref_info, Map<Object, Object> map) {
        if (((ConstantPool.CONSTANT_Fieldref_info) map.get(cONSTANT_Fieldref_info)) == null) {
            ConstantPool constantPoolTranslate = translate(cONSTANT_Fieldref_info.cp, map);
            map.put(cONSTANT_Fieldref_info, constantPoolTranslate == cONSTANT_Fieldref_info.cp ? cONSTANT_Fieldref_info : new ConstantPool.CONSTANT_Fieldref_info(constantPoolTranslate, cONSTANT_Fieldref_info.class_index, cONSTANT_Fieldref_info.name_and_type_index));
        }
        return cONSTANT_Fieldref_info;
    }

    @Override // com.sun.tools.classfile.ConstantPool.Visitor
    public ConstantPool.CPInfo visitFloat(ConstantPool.CONSTANT_Float_info cONSTANT_Float_info, Map<Object, Object> map) {
        if (((ConstantPool.CONSTANT_Float_info) map.get(cONSTANT_Float_info)) == null) {
            map.put(cONSTANT_Float_info, cONSTANT_Float_info);
        }
        return cONSTANT_Float_info;
    }

    @Override // com.sun.tools.classfile.ConstantPool.Visitor
    public ConstantPool.CPInfo visitInteger(ConstantPool.CONSTANT_Integer_info cONSTANT_Integer_info, Map<Object, Object> map) {
        if (((ConstantPool.CONSTANT_Integer_info) map.get(cONSTANT_Integer_info)) == null) {
            map.put(cONSTANT_Integer_info, cONSTANT_Integer_info);
        }
        return cONSTANT_Integer_info;
    }

    @Override // com.sun.tools.classfile.ConstantPool.Visitor
    public ConstantPool.CPInfo visitInterfaceMethodref(ConstantPool.CONSTANT_InterfaceMethodref_info cONSTANT_InterfaceMethodref_info, Map<Object, Object> map) {
        if (((ConstantPool.CONSTANT_InterfaceMethodref_info) map.get(cONSTANT_InterfaceMethodref_info)) == null) {
            ConstantPool constantPoolTranslate = translate(cONSTANT_InterfaceMethodref_info.cp, map);
            map.put(cONSTANT_InterfaceMethodref_info, constantPoolTranslate == cONSTANT_InterfaceMethodref_info.cp ? cONSTANT_InterfaceMethodref_info : new ConstantPool.CONSTANT_InterfaceMethodref_info(constantPoolTranslate, cONSTANT_InterfaceMethodref_info.class_index, cONSTANT_InterfaceMethodref_info.name_and_type_index));
        }
        return cONSTANT_InterfaceMethodref_info;
    }

    @Override // com.sun.tools.classfile.ConstantPool.Visitor
    public ConstantPool.CPInfo visitInvokeDynamic(ConstantPool.CONSTANT_InvokeDynamic_info cONSTANT_InvokeDynamic_info, Map<Object, Object> map) {
        if (((ConstantPool.CONSTANT_InvokeDynamic_info) map.get(cONSTANT_InvokeDynamic_info)) == null) {
            ConstantPool constantPoolTranslate = translate(cONSTANT_InvokeDynamic_info.cp, map);
            map.put(cONSTANT_InvokeDynamic_info, constantPoolTranslate == cONSTANT_InvokeDynamic_info.cp ? cONSTANT_InvokeDynamic_info : new ConstantPool.CONSTANT_InvokeDynamic_info(constantPoolTranslate, cONSTANT_InvokeDynamic_info.bootstrap_method_attr_index, cONSTANT_InvokeDynamic_info.name_and_type_index));
        }
        return cONSTANT_InvokeDynamic_info;
    }

    @Override // com.sun.tools.classfile.ConstantPool.Visitor
    public ConstantPool.CPInfo visitLong(ConstantPool.CONSTANT_Long_info cONSTANT_Long_info, Map<Object, Object> map) {
        if (((ConstantPool.CONSTANT_Long_info) map.get(cONSTANT_Long_info)) == null) {
            map.put(cONSTANT_Long_info, cONSTANT_Long_info);
        }
        return cONSTANT_Long_info;
    }

    @Override // com.sun.tools.classfile.ConstantPool.Visitor
    public ConstantPool.CPInfo visitMethodHandle(ConstantPool.CONSTANT_MethodHandle_info cONSTANT_MethodHandle_info, Map<Object, Object> map) {
        if (((ConstantPool.CONSTANT_MethodHandle_info) map.get(cONSTANT_MethodHandle_info)) == null) {
            ConstantPool constantPoolTranslate = translate(cONSTANT_MethodHandle_info.cp, map);
            map.put(cONSTANT_MethodHandle_info, constantPoolTranslate == cONSTANT_MethodHandle_info.cp ? cONSTANT_MethodHandle_info : new ConstantPool.CONSTANT_MethodHandle_info(constantPoolTranslate, cONSTANT_MethodHandle_info.reference_kind, cONSTANT_MethodHandle_info.reference_index));
        }
        return cONSTANT_MethodHandle_info;
    }

    @Override // com.sun.tools.classfile.ConstantPool.Visitor
    public ConstantPool.CPInfo visitMethodType(ConstantPool.CONSTANT_MethodType_info cONSTANT_MethodType_info, Map<Object, Object> map) {
        if (((ConstantPool.CONSTANT_MethodType_info) map.get(cONSTANT_MethodType_info)) == null) {
            ConstantPool constantPoolTranslate = translate(cONSTANT_MethodType_info.cp, map);
            map.put(cONSTANT_MethodType_info, constantPoolTranslate == cONSTANT_MethodType_info.cp ? cONSTANT_MethodType_info : new ConstantPool.CONSTANT_MethodType_info(constantPoolTranslate, cONSTANT_MethodType_info.descriptor_index));
        }
        return cONSTANT_MethodType_info;
    }

    @Override // com.sun.tools.classfile.ConstantPool.Visitor
    public ConstantPool.CPInfo visitMethodref(ConstantPool.CONSTANT_Methodref_info cONSTANT_Methodref_info, Map<Object, Object> map) {
        if (((ConstantPool.CONSTANT_Methodref_info) map.get(cONSTANT_Methodref_info)) == null) {
            ConstantPool constantPoolTranslate = translate(cONSTANT_Methodref_info.cp, map);
            map.put(cONSTANT_Methodref_info, constantPoolTranslate == cONSTANT_Methodref_info.cp ? cONSTANT_Methodref_info : new ConstantPool.CONSTANT_Methodref_info(constantPoolTranslate, cONSTANT_Methodref_info.class_index, cONSTANT_Methodref_info.name_and_type_index));
        }
        return cONSTANT_Methodref_info;
    }

    @Override // com.sun.tools.classfile.ConstantPool.Visitor
    public ConstantPool.CPInfo visitModule(ConstantPool.CONSTANT_Module_info cONSTANT_Module_info, Map<Object, Object> map) {
        if (((ConstantPool.CONSTANT_Module_info) map.get(cONSTANT_Module_info)) == null) {
            ConstantPool constantPoolTranslate = translate(cONSTANT_Module_info.cp, map);
            map.put(cONSTANT_Module_info, constantPoolTranslate == cONSTANT_Module_info.cp ? cONSTANT_Module_info : new ConstantPool.CONSTANT_Module_info(constantPoolTranslate, cONSTANT_Module_info.name_index));
        }
        return cONSTANT_Module_info;
    }

    @Override // com.sun.tools.classfile.ConstantPool.Visitor
    public ConstantPool.CPInfo visitNameAndType(ConstantPool.CONSTANT_NameAndType_info cONSTANT_NameAndType_info, Map<Object, Object> map) {
        if (((ConstantPool.CONSTANT_NameAndType_info) map.get(cONSTANT_NameAndType_info)) == null) {
            ConstantPool constantPoolTranslate = translate(cONSTANT_NameAndType_info.cp, map);
            map.put(cONSTANT_NameAndType_info, constantPoolTranslate == cONSTANT_NameAndType_info.cp ? cONSTANT_NameAndType_info : new ConstantPool.CONSTANT_NameAndType_info(constantPoolTranslate, cONSTANT_NameAndType_info.name_index, cONSTANT_NameAndType_info.type_index));
        }
        return cONSTANT_NameAndType_info;
    }

    @Override // com.sun.tools.classfile.ConstantPool.Visitor
    public ConstantPool.CPInfo visitPackage(ConstantPool.CONSTANT_Package_info cONSTANT_Package_info, Map<Object, Object> map) {
        if (((ConstantPool.CONSTANT_Package_info) map.get(cONSTANT_Package_info)) == null) {
            ConstantPool constantPoolTranslate = translate(cONSTANT_Package_info.cp, map);
            map.put(cONSTANT_Package_info, constantPoolTranslate == cONSTANT_Package_info.cp ? cONSTANT_Package_info : new ConstantPool.CONSTANT_Package_info(constantPoolTranslate, cONSTANT_Package_info.name_index));
        }
        return cONSTANT_Package_info;
    }

    @Override // com.sun.tools.classfile.ConstantPool.Visitor
    public ConstantPool.CPInfo visitString(ConstantPool.CONSTANT_String_info cONSTANT_String_info, Map<Object, Object> map) {
        if (((ConstantPool.CONSTANT_String_info) map.get(cONSTANT_String_info)) == null) {
            ConstantPool constantPoolTranslate = translate(cONSTANT_String_info.cp, map);
            map.put(cONSTANT_String_info, constantPoolTranslate == cONSTANT_String_info.cp ? cONSTANT_String_info : new ConstantPool.CONSTANT_String_info(constantPoolTranslate, cONSTANT_String_info.string_index));
        }
        return cONSTANT_String_info;
    }

    @Override // com.sun.tools.classfile.ConstantPool.Visitor
    public ConstantPool.CPInfo visitUtf8(ConstantPool.CONSTANT_Utf8_info cONSTANT_Utf8_info, Map<Object, Object> map) {
        if (((ConstantPool.CONSTANT_Utf8_info) map.get(cONSTANT_Utf8_info)) == null) {
            map.put(cONSTANT_Utf8_info, cONSTANT_Utf8_info);
        }
        return cONSTANT_Utf8_info;
    }

    public ConstantPool translate(ConstantPool constantPool, Map<Object, Object> map) {
        ConstantPool constantPool2 = (ConstantPool) map.get(constantPool);
        if (constantPool2 != null) {
            return constantPool2;
        }
        ConstantPool.CPInfo[] cPInfoArr = new ConstantPool.CPInfo[constantPool.size()];
        int size = 0;
        boolean z = true;
        while (size < constantPool.size()) {
            try {
                ConstantPool.CPInfo cPInfo = constantPool.get(size);
                ConstantPool.CPInfo cPInfoTranslate = translate(cPInfo, map);
                z &= cPInfo == cPInfoTranslate;
                cPInfoArr[size] = cPInfoTranslate;
                if (cPInfo.getTag() == cPInfoTranslate.getTag()) {
                    size += cPInfo.size();
                } else {
                    g33.a();
                    return null;
                }
            } catch (ConstantPool.InvalidIndex e) {
                e7f.a(e);
                return null;
            }
        }
        ConstantPool constantPool3 = z ? constantPool : new ConstantPool(cPInfoArr);
        map.put(constantPool, constantPool3);
        return constantPool3;
    }

    public ConstantPool.CPInfo translate(ConstantPool.CPInfo cPInfo, Map<Object, Object> map) {
        ConstantPool.CPInfo cPInfo2 = (ConstantPool.CPInfo) map.get(cPInfo);
        if (cPInfo2 != null) {
            return cPInfo2;
        }
        ConstantPool.CPInfo cPInfo3 = (ConstantPool.CPInfo) cPInfo.accept(this, map);
        map.put(cPInfo, cPInfo3);
        return cPInfo3;
    }

    public Field[] translate(Field[] fieldArr, ConstantPool constantPool, Map<Object, Object> map) {
        Field[] fieldArr2 = (Field[]) map.get(fieldArr);
        if (fieldArr2 == null) {
            fieldArr2 = new Field[fieldArr.length];
            for (int i = 0; i < fieldArr.length; i++) {
                fieldArr2[i] = translate(fieldArr[i], constantPool, map);
            }
            if (equal(fieldArr, fieldArr2)) {
                fieldArr2 = fieldArr;
            }
            map.put(fieldArr, fieldArr2);
        }
        return fieldArr2;
    }

    public Field translate(Field field, ConstantPool constantPool, Map<Object, Object> map) {
        Field field2 = (Field) map.get(field);
        if (field2 != null) {
            return field2;
        }
        Attributes attributesTranslateAttributes = translateAttributes(field.attributes, constantPool, map);
        Field field3 = attributesTranslateAttributes == field.attributes ? field : new Field(field.access_flags, field.name_index, field.descriptor, attributesTranslateAttributes);
        map.put(field, field3);
        return field3;
    }

    public Method translate(Method method, ConstantPool constantPool, Map<Object, Object> map) {
        Method method2 = (Method) map.get(method);
        if (method2 != null) {
            return method2;
        }
        Attributes attributesTranslateAttributes = translateAttributes(method.attributes, constantPool, map);
        Method method3 = attributesTranslateAttributes == method.attributes ? method : new Method(method.access_flags, method.name_index, method.descriptor, attributesTranslateAttributes);
        map.put(method, method3);
        return method3;
    }

    public Attribute translate(Attribute attribute, Map<Object, Object> map) {
        Attribute attribute2 = (Attribute) map.get(attribute);
        if (attribute2 != null) {
            return attribute2;
        }
        map.put(attribute, attribute);
        return attribute;
    }
}
