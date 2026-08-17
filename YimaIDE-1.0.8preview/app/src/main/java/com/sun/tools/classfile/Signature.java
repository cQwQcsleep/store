package com.sun.tools.classfile;

import com.intellij.psi.PsiKeyword;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Signature extends Descriptor {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private String sig;
    private int sigp;
    private Type type;

    public Signature(int i) {
        super(i);
    }

    private String debugInfo() {
        return this.sig.substring(0, this.sigp) + "!" + this.sig.charAt(this.sigp) + "!" + this.sig.substring(this.sigp + 1);
    }

    private Type parse(String str) {
        this.sig = str;
        this.sigp = 0;
        ArrayList arrayList = null;
        List<Type.TypeParamType> typeParamTypes = str.charAt(0) == '<' ? parseTypeParamTypes() : null;
        if (str.charAt(this.sigp) == '(') {
            List<Type> typeSignatures = parseTypeSignatures(')');
            Type typeSignature = parseTypeSignature();
            while (this.sigp < str.length() && str.charAt(this.sigp) == '^') {
                this.sigp++;
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(parseTypeSignature());
            }
            return new Type.MethodType(typeParamTypes, typeSignatures, typeSignature, arrayList);
        }
        Type typeSignature2 = parseTypeSignature();
        if (typeParamTypes == null && this.sigp == str.length()) {
            return typeSignature2;
        }
        while (this.sigp < str.length()) {
            if (arrayList == null) {
                arrayList = new ArrayList();
            }
            arrayList.add(parseTypeSignature());
        }
        return new Type.ClassSigType(typeParamTypes, typeSignature2, arrayList);
    }

    private Type parseClassTypeSignature() {
        this.sigp++;
        return parseClassTypeSignatureRest();
    }

    private Type parseClassTypeSignatureRest() {
        char cCharAt;
        StringBuilder sb = new StringBuilder();
        Type.ClassType classType = null;
        List<Type> typeSignatures = null;
        do {
            cCharAt = this.sig.charAt(this.sigp);
            if (cCharAt == '.' || cCharAt == ';') {
                this.sigp++;
                Type.ClassType classType2 = new Type.ClassType(classType, sb.toString(), typeSignatures);
                sb.setLength(0);
                typeSignatures = null;
                classType = classType2;
            } else if (cCharAt != '<') {
                this.sigp++;
                sb.append(cCharAt);
            } else {
                typeSignatures = parseTypeSignatures('>');
            }
        } while (cCharAt != ';');
        return classType;
    }

    private Type.TypeParamType parseTypeParamType() {
        ArrayList arrayList;
        int iIndexOf = this.sig.indexOf(":", this.sigp);
        String strSubstring = this.sig.substring(this.sigp, iIndexOf);
        int i = iIndexOf + 1;
        this.sigp = i;
        Type typeSignature = null;
        if (this.sig.charAt(i) != ':') {
            typeSignature = parseTypeSignature();
            arrayList = null;
        } else {
            arrayList = null;
        }
        while (this.sig.charAt(this.sigp) == ':') {
            this.sigp++;
            if (arrayList == null) {
                arrayList = new ArrayList();
            }
            arrayList.add(parseTypeSignature());
        }
        return new Type.TypeParamType(strSubstring, typeSignature, arrayList);
    }

    private List<Type.TypeParamType> parseTypeParamTypes() {
        this.sigp++;
        ArrayList arrayList = new ArrayList();
        while (this.sig.charAt(this.sigp) != '>') {
            arrayList.add(parseTypeParamType());
        }
        this.sigp++;
        return arrayList;
    }

    private Type parseTypeSignature() {
        char cCharAt = this.sig.charAt(this.sigp);
        if (cCharAt == '*') {
            this.sigp++;
            return new Type.WildcardType();
        }
        if (cCharAt == '+') {
            this.sigp++;
            return new Type.WildcardType(Type.WildcardType.Kind.EXTENDS, parseTypeSignature());
        }
        if (cCharAt == '-') {
            this.sigp++;
            return new Type.WildcardType(Type.WildcardType.Kind.SUPER, parseTypeSignature());
        }
        if (cCharAt == 'F') {
            this.sigp++;
            return new Type.SimpleType("float");
        }
        if (cCharAt == 'L') {
            return parseClassTypeSignature();
        }
        if (cCharAt == 'V') {
            this.sigp++;
            return new Type.SimpleType(PsiKeyword.VOID);
        }
        if (cCharAt == 'I') {
            this.sigp++;
            return new Type.SimpleType("int");
        }
        if (cCharAt == 'J') {
            this.sigp++;
            return new Type.SimpleType("long");
        }
        if (cCharAt == 'S') {
            this.sigp++;
            return new Type.SimpleType("short");
        }
        if (cCharAt == 'T') {
            return parseTypeVariableSignature();
        }
        if (cCharAt == 'Z') {
            this.sigp++;
            return new Type.SimpleType("boolean");
        }
        if (cCharAt == '[') {
            this.sigp++;
            return new Type.ArrayType(parseTypeSignature());
        }
        switch (cCharAt) {
            case 'B':
                this.sigp++;
                return new Type.SimpleType("byte");
            case 'C':
                this.sigp++;
                return new Type.SimpleType(PsiKeyword.CHAR);
            case 'D':
                this.sigp++;
                return new Type.SimpleType("double");
            default:
                k2d.a(debugInfo());
                return null;
        }
    }

    private List<Type> parseTypeSignatures(char c) {
        this.sigp++;
        ArrayList arrayList = new ArrayList();
        while (this.sig.charAt(this.sigp) != c) {
            arrayList.add(parseTypeSignature());
        }
        this.sigp++;
        return arrayList;
    }

    private Type parseTypeVariableSignature() {
        int i = this.sigp + 1;
        this.sigp = i;
        int iIndexOf = this.sig.indexOf(59, i);
        Type.SimpleType simpleType = new Type.SimpleType(this.sig.substring(this.sigp, iIndexOf));
        this.sigp = iIndexOf + 1;
        return simpleType;
    }

    @Override // com.sun.tools.classfile.Descriptor
    public String getFieldType(ConstantPool constantPool) throws ConstantPoolException {
        return getType(constantPool).toString();
    }

    @Override // com.sun.tools.classfile.Descriptor
    public int getParameterCount(ConstantPool constantPool) throws ConstantPoolException {
        return ((Type.MethodType) getType(constantPool)).paramTypes.size();
    }

    @Override // com.sun.tools.classfile.Descriptor
    public String getParameterTypes(ConstantPool constantPool) throws ConstantPoolException {
        Type.MethodType methodType = (Type.MethodType) getType(constantPool);
        StringBuilder sb = new StringBuilder("(");
        String str = "";
        for (Type type : methodType.paramTypes) {
            sb.append(str);
            sb.append(type);
            str = ", ";
        }
        sb.append(")");
        return sb.toString();
    }

    @Override // com.sun.tools.classfile.Descriptor
    public String getReturnType(ConstantPool constantPool) throws ConstantPoolException {
        return ((Type.MethodType) getType(constantPool)).returnType.toString();
    }

    public Type getType(ConstantPool constantPool) throws ConstantPoolException {
        if (this.type == null) {
            this.type = parse(getValue(constantPool));
        }
        return this.type;
    }
}
