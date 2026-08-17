package com.reandroid.dex.key;

import com.intellij.psi.PsiKeyword;
import com.reandroid.dex.common.DexUtils;
import com.reandroid.dex.smali.SmaliReader;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.utils.CompareUtil;
import com.reandroid.utils.StringsUtil;
import com.reandroid.utils.collection.CombiningIterator;
import com.reandroid.utils.collection.EmptyIterator;
import com.reandroid.utils.collection.SingleIterator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Constants;
import defpackage.l78;
import java.io.IOException;
import java.lang.annotation.ElementType;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class TypeKey implements ProgramKey {
    private String simpleName;
    private final String typeName;
    public static final TypeKey TYPE_B = new PrimitiveTypeKey("B", "byte");
    public static final TypeKey TYPE_C = new PrimitiveTypeKey("C", PsiKeyword.CHAR);
    public static final TypeKey TYPE_D = new PrimitiveTypeKey("D", "double") { // from class: com.reandroid.dex.key.TypeKey.2
        @Override // com.reandroid.dex.key.TypeKey.PrimitiveTypeKey, com.reandroid.dex.key.TypeKey
        public boolean isWide() {
            return true;
        }
    };
    public static final TypeKey TYPE_F = new PrimitiveTypeKey("F", "float");
    public static final TypeKey TYPE_I = new PrimitiveTypeKey("I", "int");
    public static final TypeKey TYPE_J = new PrimitiveTypeKey("J", "long") { // from class: com.reandroid.dex.key.TypeKey.3
        @Override // com.reandroid.dex.key.TypeKey.PrimitiveTypeKey, com.reandroid.dex.key.TypeKey
        public boolean isWide() {
            return true;
        }
    };
    public static final TypeKey TYPE_S = new PrimitiveTypeKey("S", "short");
    public static final TypeKey TYPE_V = new PrimitiveTypeKey("V", PsiKeyword.VOID);
    public static final TypeKey TYPE_Z = new PrimitiveTypeKey(Constants.HASIDCALL_INDEX_SIG, "boolean");
    public static final TypeKey CLASS = new TypeKey(Constants.CLASS_SIG);
    public static final TypeKey OBJECT = new TypeKey(Constants.OBJECT_SIG);
    public static final TypeKey STRING = new TypeKey(Constants.STRING_SIG);
    public static final TypeKey EXCEPTION = new TypeKey("Ljava/lang/Exception;");
    public static final TypeKey DALVIK_EnclosingClass = new TypeKey("Ldalvik/annotation/EnclosingClass;");
    public static final TypeKey DALVIK_EnclosingMethod = new TypeKey("Ldalvik/annotation/EnclosingMethod;");
    public static final TypeKey DALVIK_InnerClass = new TypeKey("Ldalvik/annotation/InnerClass;");
    public static final TypeKey DALVIK_MemberClass = new TypeKey("Ldalvik/annotation/MemberClasses;");
    public static final TypeKey DALVIK_Signature = new TypeKey("Ldalvik/annotation/Signature;");
    public static final TypeKey DALVIK_Throws = new TypeKey("Ldalvik/annotation/Throws;");

    public static class PrimitiveTypeKey extends TypeKey {
        private final String sourceName;

        public PrimitiveTypeKey(String str, String str2) {
            super(str);
            this.sourceName = str2;
        }

        @Override // com.reandroid.dex.key.TypeKey, com.reandroid.dex.key.ProgramKey, com.reandroid.dex.key.Key
        public TypeKey getDeclaring() {
            return this;
        }

        @Override // com.reandroid.dex.key.TypeKey
        public String getSourceName() {
            return this.sourceName;
        }

        @Override // com.reandroid.dex.key.TypeKey
        public boolean isInnerName() {
            return false;
        }

        @Override // com.reandroid.dex.key.TypeKey
        public boolean isOuterOf(TypeKey typeKey, boolean z) {
            return false;
        }

        @Override // com.reandroid.dex.key.TypeKey
        public boolean isPrimitive() {
            return true;
        }

        @Override // com.reandroid.dex.key.TypeKey
        public boolean isTypeArray() {
            return false;
        }

        @Override // com.reandroid.dex.key.TypeKey
        public boolean isTypeDefinition() {
            return false;
        }

        @Override // com.reandroid.dex.key.TypeKey
        public boolean isTypeObject() {
            return false;
        }

        @Override // com.reandroid.dex.key.TypeKey
        public boolean isWide() {
            return false;
        }

        @Override // com.reandroid.dex.key.Key
        public boolean uses(Key key) {
            return equals(key);
        }
    }

    public TypeKey(String str) {
        this.typeName = str;
    }

    public static TypeKey convert(Class<?> cls) {
        String name = cls.getName();
        if (cls.isArray()) {
            return new TypeKey(name.replace('.', '/'));
        }
        if (cls.isPrimitive()) {
            return primitiveType(name);
        }
        return new TypeKey("L" + name.replace('.', '/') + ';');
    }

    public static TypeKey create(String str) {
        int length;
        if (str == null || (length = str.length()) == 0) {
            return null;
        }
        return length != 1 ? new TypeKey(str) : primitiveType(str.charAt(0));
    }

    public static boolean isPrimitive(char c) {
        return primitiveType(c) != null;
    }

    public static TypeKey parse(String str) {
        if (str == null || str.length() == 0 || str.indexOf(62) > 0 || str.indexOf(40) > 0 || str.indexOf(64) > 0) {
            return null;
        }
        return (str.indexOf(47) > 0 || str.indexOf(59) > 0 || str.charAt(0) == '[') ? new TypeKey(str.replace('.', '/')) : parseSourceName(str);
    }

    public static TypeKey parseBinaryType(String str, int i, int i2) {
        boolean z = false;
        int i3 = 0;
        for (int i4 = i; i4 < i2; i4++) {
            char cCharAt = str.charAt(i4);
            if (cCharAt == '[') {
                if (z) {
                    return null;
                }
                i3++;
            } else {
                if (cCharAt == ';') {
                    if (z) {
                        return create(str.substring(i, i4 + 1));
                    }
                    return null;
                }
                if (z) {
                    continue;
                } else {
                    if (cCharAt != 'L') {
                        TypeKey typeKeyPrimitiveType = primitiveType(cCharAt);
                        return typeKeyPrimitiveType != null ? typeKeyPrimitiveType.setArrayDimension(i3) : typeKeyPrimitiveType;
                    }
                    z = true;
                }
            }
        }
        return null;
    }

    public static TypeKey parseSignature(String str) {
        if (DexUtils.isTypeOrSignature(str)) {
            return new TypeKey(str);
        }
        return null;
    }

    private static TypeKey parseSourceName(String str) {
        int length = str.length();
        int i = 0;
        for (int iIndexOf = str.indexOf(91); iIndexOf > 0 && iIndexOf < length && str.charAt(iIndexOf) == '['; iIndexOf += 2) {
            i++;
            int i2 = iIndexOf + 1;
            if (i2 == length || str.charAt(i2) != ']') {
                return null;
            }
        }
        int length2 = str.length() - (i * 2);
        if (length2 == 0) {
            return null;
        }
        if (i != 0) {
            str = str.substring(0, length2);
        }
        TypeKey typeKeyPrimitiveType = primitiveType(str);
        if (typeKeyPrimitiveType == null) {
            typeKeyPrimitiveType = new TypeKey("L" + str.replace('.', '/') + ';');
        }
        return typeKeyPrimitiveType.setArrayDimension(i);
    }

    private static TypeKey primitiveType(String str) {
        int length = str.length();
        if (length >= 3 && length <= 7) {
            TypeKey typeKey = TYPE_B;
            if (str.equals(typeKey.getSourceName())) {
                return typeKey;
            }
            TypeKey typeKey2 = TYPE_D;
            if (str.equals(typeKey2.getSourceName())) {
                return typeKey2;
            }
            TypeKey typeKey3 = TYPE_F;
            if (str.equals(typeKey3.getSourceName())) {
                return typeKey3;
            }
            TypeKey typeKey4 = TYPE_I;
            if (str.equals(typeKey4.getSourceName())) {
                return typeKey4;
            }
            TypeKey typeKey5 = TYPE_J;
            if (str.equals(typeKey5.getSourceName())) {
                return typeKey5;
            }
            TypeKey typeKey6 = TYPE_S;
            if (str.equals(typeKey6.getSourceName())) {
                return typeKey6;
            }
            TypeKey typeKey7 = TYPE_V;
            if (str.equals(typeKey7.getSourceName())) {
                return typeKey7;
            }
            TypeKey typeKey8 = TYPE_Z;
            if (str.equals(typeKey8.getSourceName())) {
                return typeKey8;
            }
        }
        return null;
    }

    public static TypeKey read(SmaliReader smaliReader) throws IOException {
        smaliReader.skipWhitespacesOrComment();
        int iPosition = smaliReader.position();
        StringBuilder sb = new StringBuilder();
        while (smaliReader.get() == 91) {
            sb.append(smaliReader.readASCII());
        }
        if (smaliReader.get() != 76) {
            sb.append(smaliReader.readASCII());
        } else {
            int iIndexOfBeforeLineEnd = smaliReader.indexOfBeforeLineEnd(';');
            if (iIndexOfBeforeLineEnd < 0) {
                smaliReader.position(iPosition);
                l78.a("Invalid type, missing ';'", smaliReader);
                return null;
            }
            sb.append(smaliReader.readString((iIndexOfBeforeLineEnd + 1) - smaliReader.position()));
        }
        TypeKey typeKeyCreate = create(sb.toString());
        if (typeKeyCreate != null) {
            return typeKeyCreate;
        }
        smaliReader.position(iPosition);
        l78.a("Invalid type", smaliReader);
        return null;
    }

    private TypeKey setPackage(String str, String str2) {
        if (str.equals(str2)) {
            return this;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str2);
        int length = str2.length() - 1;
        if (length > 0 && str2.charAt(length) != '/') {
            sb.append('/');
        }
        sb.append(getSimpleName());
        String typeName = getTypeName();
        char cCharAt = typeName.charAt(typeName.length() - 1);
        if (cCharAt == ';' || cCharAt == '<') {
            sb.append(cCharAt);
        }
        return new TypeKey(sb.toString()).setArrayDimension(getArrayDimension());
    }

    @Override // com.reandroid.dex.key.Key, com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        smaliWriter.append((CharSequence) getTypeName());
    }

    public TypeKey changeTypeName(TypeKey typeKey) {
        return equals(typeKey) ? this : typeKey.setArrayDimension(getArrayDimension());
    }

    public int compareInnerFirst(TypeKey typeKey) {
        if (equals(typeKey)) {
            return 0;
        }
        String simpleName = getSimpleName();
        String simpleName2 = typeKey.getSimpleName();
        int iDiffStart = StringsUtil.diffStart(simpleName, simpleName2);
        return (iDiffStart <= 0 || simpleName.charAt(iDiffStart) != '$' || iDiffStart <= simpleName.lastIndexOf(47) + 1) ? CompareUtil.compare(getTypeName(), typeKey.getTypeName()) : CompareUtil.compare(simpleName2, simpleName);
    }

    @Override // com.reandroid.dex.key.Key, java.lang.Comparable
    public int compareTo(Object obj) {
        if (obj == this) {
            return 0;
        }
        return !(obj instanceof TypeKey) ? StringsUtil.compareToString(this, obj) : CompareUtil.compare(getTypeName(), ((TypeKey) obj).getTypeName());
    }

    public TypeKey createInnerClass(String str) {
        String typeName = getTypeName();
        String strCreateChildClass = DexUtils.createChildClass(typeName, str);
        return typeName.equals(strCreateChildClass) ? this : new TypeKey(strCreateChildClass);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof TypeKey) {
            return getTypeName().equals(((TypeKey) obj).getTypeName());
        }
        return false;
    }

    public boolean equalsPackage(TypeKey typeKey) {
        if (typeKey == this) {
            return true;
        }
        if (typeKey == null) {
            return false;
        }
        String strTrimStart = StringsUtil.trimStart(getTypeName(), '[');
        String strTrimStart2 = StringsUtil.trimStart(typeKey.getTypeName(), '[');
        if (strTrimStart.charAt(0) == 'L' && strTrimStart2.charAt(0) == 'L') {
            if (strTrimStart.equals(strTrimStart2)) {
                return true;
            }
            int iDiffStart = StringsUtil.diffStart(strTrimStart, strTrimStart2);
            if (iDiffStart >= 0 && StringsUtil.indexOfFrom(strTrimStart, iDiffStart, '/') < 0 && StringsUtil.indexOfFrom(strTrimStart2, iDiffStart, '/') < 0) {
                return true;
            }
        }
        return false;
    }

    public int getArrayDimension() {
        return DexUtils.countArrayPrefix(getTypeName());
    }

    public String getArrayType(int i) {
        return DexUtils.makeArrayType(getTypeName(), i);
    }

    @Override // com.reandroid.dex.key.ProgramKey, com.reandroid.dex.key.Key
    public TypeKey getDeclaring() {
        String declaringName = getDeclaringName();
        return declaringName.equals(getTypeName()) ? this : create(declaringName);
    }

    public String getDeclaringName() {
        return DexUtils.toDeclaringType(getTypeName());
    }

    @Override // com.reandroid.dex.key.ProgramKey
    public ElementType getElementType() {
        return ElementType.TYPE;
    }

    public TypeKey getEnclosingClass() {
        String typeName = getTypeName();
        String parentClassName = DexUtils.getParentClassName(typeName);
        return typeName.equals(parentClassName) ? this : new TypeKey(parentClassName);
    }

    public String getPackageName() {
        return DexUtils.getPackageName(getTypeName());
    }

    public String getPackageSourceName() {
        String packageName = getPackageName();
        int length = packageName.length() - 1;
        return length < 1 ? StringsUtil.EMPTY : packageName.substring(1, length).replace('/', '.');
    }

    public String getSignatureTypeName() {
        return DexUtils.toSignatureType(getTypeName());
    }

    public String getSimpleInnerName() {
        String simpleName = getSimpleName();
        int iLastIndexOf = simpleName.lastIndexOf(36);
        if (iLastIndexOf <= 0 || iLastIndexOf >= simpleName.length() - 1) {
            return null;
        }
        return simpleName.substring(iLastIndexOf + 1);
    }

    public String getSimpleName() {
        if (this.simpleName == null) {
            this.simpleName = DexUtils.getSimpleName(getTypeName());
        }
        return this.simpleName;
    }

    public String getSourceName() {
        TypeKey typeKeyPrimitiveType;
        int arrayDimension = getArrayDimension();
        if (arrayDimension == 0) {
            String typeName = getTypeName();
            return (typeName.length() != 1 || (typeKeyPrimitiveType = primitiveType(typeName.charAt(0))) == null) ? DexUtils.toSourceName(typeName) : typeKeyPrimitiveType.getSourceName();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(getDeclaring().getSourceName());
        for (int i = 0; i < arrayDimension; i++) {
            sb.append("[]");
        }
        return sb.toString();
    }

    public String getTypeName() {
        return this.typeName;
    }

    public int hashCode() {
        return getTypeName().hashCode();
    }

    public boolean isInnerName() {
        return getSimpleInnerName() != null;
    }

    public boolean isOuterOf(TypeKey typeKey, boolean z) {
        int iDiffStart;
        if (typeKey == null) {
            return false;
        }
        String typeName = getTypeName();
        String typeName2 = typeKey.getTypeName();
        if (typeName.length() < typeName2.length() && (iDiffStart = StringsUtil.diffStart(typeName, typeName2)) >= 0 && typeName.charAt(iDiffStart) == ';' && typeName2.charAt(iDiffStart) == '$') {
            int i = iDiffStart + 1;
            if (typeName.indexOf(47, i) <= 0 && typeName2.indexOf(47, i) <= 0) {
                if (!z) {
                    return true;
                }
                int length = typeName2.length();
                while (i < length && typeName2.charAt(i) == '$') {
                    i++;
                }
                return i != length && typeName2.indexOf(36, i) < 0;
            }
        }
        return false;
    }

    public boolean isPackage(String str, boolean z) {
        if (isPrimitive()) {
            return false;
        }
        String packageName = getPackageName();
        return z ? packageName.startsWith(str) : packageName.equals(str);
    }

    public boolean isTypeArray() {
        String typeName = getTypeName();
        return typeName.length() > 1 && typeName.charAt(0) == '[';
    }

    public boolean isTypeDefinition() {
        String typeName = getTypeName();
        int length = typeName.length() - 1;
        return length > 1 && typeName.charAt(0) == 'L' && typeName.charAt(length) == ';';
    }

    public boolean isTypeObject() {
        return DexUtils.isTypeObject(getTypeName());
    }

    public boolean isWide() {
        String typeName = getTypeName();
        if (typeName.length() != 1) {
            return false;
        }
        return typeName.equals(TYPE_D.getTypeName()) || typeName.equals(TYPE_J.getTypeName());
    }

    public Iterator<String> iteratePackageNames() {
        return getTypeName().indexOf(47) < 0 ? EmptyIterator.of() : new Iterator<String>(getPackageName()) { // from class: com.reandroid.dex.key.TypeKey.1
            String name;
            final /* synthetic */ String val$packageName;

            {
                this.val$packageName = str;
                this.name = str;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                String str = this.name;
                int length = str.length();
                return length != 0 && str.charAt(length - 1) == '/';
            }

            @Override // java.util.Iterator
            public String next() {
                String str = this.name;
                String strSubstring = str;
                while (strSubstring.charAt(strSubstring.length() - 1) == '/') {
                    strSubstring = strSubstring.substring(0, strSubstring.length() - 1);
                }
                int iLastIndexOf = strSubstring.lastIndexOf(47);
                if (iLastIndexOf > 0) {
                    strSubstring = strSubstring.substring(0, iLastIndexOf + 1);
                }
                this.name = strSubstring;
                return str;
            }
        };
    }

    @Override // com.reandroid.dex.key.Key
    public Iterator<TypeKey> mentionedKeys() {
        Iterator<TypeKey> itOf = SingleIterator.of(this);
        return isTypeArray() ? CombiningIterator.singleOne(getDeclaring(), itOf) : itOf;
    }

    public TypeKey renamePackage(String str, String str2) {
        String packageName = getPackageName();
        if (packageName.equals(str)) {
            return setPackage(packageName, str2);
        }
        int length = str.length();
        return (length == 1 || packageName.length() < length || !packageName.startsWith(str)) ? this : setPackage(packageName, packageName.replace(str, str2));
    }

    @Override // com.reandroid.dex.key.Key
    public Key replaceKey(Key key, Key key2) {
        return key.equals(this) ? key2 : this;
    }

    public TypeKey setArrayDimension(int i) {
        return i == getArrayDimension() ? this : new TypeKey(getArrayType(i));
    }

    public char shorty() {
        String typeName = getTypeName();
        if (typeName.length() == 1) {
            return typeName.charAt(0);
        }
        return 'L';
    }

    public boolean startsWith(String str) {
        return getTypeName().startsWith(str);
    }

    public String toString() {
        return getTypeName();
    }

    public boolean isPrimitive() {
        return DexUtils.isPrimitive(getTypeName());
    }

    public TypeKey changeTypeName(String str) {
        return changeTypeName(create(str));
    }

    public boolean isPackage(String str) {
        return isPackage(str, !"L".equals(str));
    }

    public TypeKey setPackage(String str) {
        return setPackage(getPackageName(), str);
    }

    public boolean isOuterOf(TypeKey typeKey) {
        return isOuterOf(typeKey, false);
    }

    public static TypeKey primitiveType(char c) {
        if (c == 'F') {
            return TYPE_F;
        }
        if (c == 'S') {
            return TYPE_S;
        }
        if (c == 'V') {
            return TYPE_V;
        }
        if (c == 'Z') {
            return TYPE_Z;
        }
        if (c == 'I') {
            return TYPE_I;
        }
        if (c != 'J') {
            switch (c) {
                case 'B':
                    return TYPE_B;
                case 'C':
                    return TYPE_C;
                case 'D':
                    return TYPE_D;
                default:
                    return null;
            }
        }
        return TYPE_J;
    }
}
