package com.reandroid.dex.key;

import com.reandroid.dex.smali.SmaliParseException;
import com.reandroid.dex.smali.SmaliReader;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.utils.CompareUtil;
import com.reandroid.utils.ObjectsUtil;
import com.reandroid.utils.StringsUtil;
import com.reandroid.utils.collection.CombiningIterator;
import com.reandroid.utils.collection.IterableIterator;
import com.reandroid.utils.collection.SingleIterator;
import defpackage.l78;
import java.io.IOException;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ParameterisedTypeKey implements Key {
    private final ParameterName parameterName;
    private final ParameterisedProtoKey protoKey;

    public static class Definition extends ParameterName {
        private final int colons;

        public Definition(String str, int i) {
            super(str);
            this.colons = i;
        }

        public static boolean isDefinitionStop(char c) {
            return ParameterisedTypeKey.isNameStop(c) || ParameterisedTypeKey.isWild(c) || c == '/' || c == '.';
        }

        public static Definition readDefinition(SmaliReader smaliReader) {
            int iPosition = smaliReader.position();
            int iAvailable = smaliReader.available() + iPosition;
            int i = 0;
            int i2 = iPosition;
            while (i2 < iAvailable) {
                char ascii = smaliReader.getASCII(i2);
                if (ascii == ':') {
                    i++;
                } else if (i != 0 || isDefinitionStop(ascii)) {
                    break;
                }
                i2++;
            }
            if (i == 0) {
                return null;
            }
            String string = smaliReader.readString((i2 - iPosition) - i);
            smaliReader.position(smaliReader.position() + i);
            return new Definition(string, i);
        }

        @Override // com.reandroid.dex.key.ParameterisedTypeKey.ParameterName, com.reandroid.dex.key.Key, com.reandroid.dex.smali.SmaliFormat
        public void append(SmaliWriter smaliWriter) throws IOException {
            smaliWriter.append((CharSequence) getName());
            int i = this.colons;
            for (int i2 = 0; i2 < i; i2++) {
                smaliWriter.append(':');
            }
        }

        @Override // com.reandroid.dex.key.ParameterisedTypeKey.ParameterName
        public void appendString(StringBuilder sb, boolean z) {
            sb.append(getName());
            int i = this.colons;
            for (int i2 = 0; i2 < i; i2++) {
                sb.append(':');
            }
        }

        @Override // com.reandroid.dex.key.ParameterisedTypeKey.ParameterName
        public Definition changeName(String str) {
            return getName().equals(str) ? this : new Definition(str, this.colons);
        }

        @Override // com.reandroid.dex.key.ParameterisedTypeKey.ParameterName
        public String toString() {
            StringBuilder sb = new StringBuilder();
            appendString(sb, false);
            return sb.toString();
        }
    }

    public static class InnerClassName extends ParameterName {
        public InnerClassName(String str) {
            super(str);
        }

        public static InnerClassName readInnerClassName(SmaliReader smaliReader) {
            int iPosition = smaliReader.position();
            int iAvailable = smaliReader.available() + iPosition;
            boolean z = true;
            int i = iPosition;
            while (i < iAvailable) {
                char ascii = smaliReader.getASCII(i);
                if (ascii == '[') {
                    if (!z) {
                        break;
                    }
                    i++;
                } else {
                    if (z && ascii != '.') {
                        return null;
                    }
                    if (ParameterisedTypeKey.isNameStop(ascii) || ParameterisedTypeKey.isWild(ascii)) {
                        break;
                    }
                    i++;
                    z = false;
                }
            }
            if (i == iPosition) {
                return null;
            }
            return new InnerClassName(smaliReader.readString(i - smaliReader.position()));
        }

        @Override // com.reandroid.dex.key.ParameterisedTypeKey.ParameterName
        public void appendString(StringBuilder sb, boolean z) {
            String name = getName();
            if (z) {
                name = name.replace('.', '$');
            }
            sb.append(name);
        }

        @Override // com.reandroid.dex.key.ParameterisedTypeKey.ParameterName
        public InnerClassName changeName(String str) {
            return getName().equals(str) ? this : new InnerClassName(str);
        }

        public String createInnerName(TypeKey typeKey) {
            String[] strArrSplit = StringsUtil.split(typeKey.getSimpleName(), '$');
            int length = StringsUtil.split(getName().substring(1), '.').length;
            int length2 = strArrSplit.length;
            int i = length2 - length;
            if (i <= 0) {
                return null;
            }
            StringBuilder sb = new StringBuilder();
            while (i < length2) {
                sb.append('.');
                sb.append(strArrSplit[i]);
                i++;
            }
            return sb.toString();
        }

        public TypeKey createOuterKey(TypeKey typeKey) {
            String[] strArrSplit = StringsUtil.split(typeKey.getSimpleName(), '$');
            int length = strArrSplit.length - StringsUtil.split(getName().substring(1), '.').length;
            if (length <= 0) {
                return null;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(typeKey.getPackageName());
            for (int i = 0; i < length; i++) {
                if (i != 0) {
                    sb.append('$');
                }
                sb.append(strArrSplit[i]);
            }
            sb.append(';');
            return TypeKey.create(sb.toString());
        }

        @Override // com.reandroid.dex.key.ParameterisedTypeKey.ParameterName
        public boolean isInnerName() {
            return true;
        }
    }

    public static abstract class ParameterName implements Key {
        private final String name;

        public ParameterName(String str) {
            this.name = str;
        }

        @Override // com.reandroid.dex.key.Key, com.reandroid.dex.smali.SmaliFormat
        public void append(SmaliWriter smaliWriter) throws IOException {
            smaliWriter.append((CharSequence) getName());
        }

        public void appendString(StringBuilder sb, boolean z) {
            sb.append(getName());
        }

        public void buildSignature(DalvikSignatureBuilder dalvikSignatureBuilder) {
            appendString(dalvikSignatureBuilder.getStringBuilder(), false);
        }

        public ParameterName changeName(TypeKey typeKey) {
            return this;
        }

        public abstract ParameterName changeName(String str);

        @Override // com.reandroid.dex.key.Key, java.lang.Comparable
        public int compareTo(Object obj) {
            if (obj == this) {
                return 0;
            }
            if (obj == null) {
                return -1;
            }
            return !getClass().isInstance(obj) ? StringsUtil.compareToString(this, obj) : CompareUtil.compare(getName(), ((ParameterName) obj).getName());
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (getClass().isInstance(obj)) {
                return ObjectsUtil.equals(getName(), ((ParameterName) obj).getName());
            }
            return false;
        }

        @Override // com.reandroid.dex.key.Key
        public TypeKey getDeclaring() {
            return null;
        }

        public String getName() {
            return this.name;
        }

        public int hashCode() {
            return ObjectsUtil.hash(getName());
        }

        public boolean isClassType() {
            return false;
        }

        public boolean isInnerName() {
            return false;
        }

        public boolean isTypeUse() {
            return false;
        }

        public String toString() {
            return getName();
        }
    }

    public static class TypeUsePrimitive extends ParameterName {
        public TypeUsePrimitive(String str) {
            super(str);
        }

        public static TypeUsePrimitive readTypeUsePrimitive(SmaliReader smaliReader) {
            char ascii;
            int iPosition = smaliReader.position();
            int iAvailable = smaliReader.available() + iPosition;
            for (int i = iPosition; i < iAvailable; i++) {
                char ascii2 = smaliReader.getASCII(i);
                if (ascii2 != '[') {
                    if (!TypeKey.isPrimitive(ascii2)) {
                        return null;
                    }
                    int i2 = i + 1;
                    if (i2 >= iAvailable || !((ascii = smaliReader.getASCII(i2)) == ';' || ascii == '<')) {
                        return new TypeUsePrimitive(smaliReader.readString(i2 - iPosition));
                    }
                    return null;
                }
            }
            return null;
        }

        @Override // com.reandroid.dex.key.ParameterisedTypeKey.ParameterName
        public TypeUsePrimitive changeName(String str) {
            return getName().equals(str) ? this : new TypeUsePrimitive(str);
        }
    }

    public static class TypeUseVariable extends TypeUse {
        public TypeUseVariable(String str) {
            super(str);
        }

        private static boolean isTypeUseVariableStop(char c) {
            return ParameterisedTypeKey.isNameStop(c) || ParameterisedTypeKey.isWild(c) || c == '/' || c == '.';
        }

        public static TypeUseVariable readTypeUseVariable(SmaliReader smaliReader) {
            int iPosition = smaliReader.position();
            int iAvailable = smaliReader.available() + iPosition;
            boolean z = true;
            int i = iPosition;
            char ascii = 0;
            while (i < iAvailable) {
                ascii = smaliReader.getASCII(i);
                if (ascii == '[') {
                    if (!z) {
                        break;
                    }
                    i++;
                } else {
                    if (z && ascii != 'T') {
                        return null;
                    }
                    if (isTypeUseVariableStop(ascii)) {
                        break;
                    }
                    i++;
                    z = false;
                }
            }
            if (i == iPosition || !(ascii == ';' || ascii == '<')) {
                return null;
            }
            return new TypeUseVariable(smaliReader.readString(i - iPosition));
        }

        @Override // com.reandroid.dex.key.ParameterisedTypeKey.ParameterName
        public void appendString(StringBuilder sb, boolean z) {
            if (z) {
                sb.append(getVariableName());
            } else {
                sb.append(getName());
            }
        }

        public String getVariableName() {
            String name = getName();
            int i = 0;
            while (name.charAt(i) == '[') {
                i++;
            }
            return name.substring(0, i).concat(name.substring(i + 1));
        }

        @Override // com.reandroid.dex.key.ParameterisedTypeKey.TypeUse, com.reandroid.dex.key.ParameterisedTypeKey.ParameterName
        public boolean isClassType() {
            return false;
        }
    }

    public static class Wild extends ParameterName {
        private final String comment;
        static final Wild SUPER = new Wild("-", "? super ");
        static final Wild EXTENDS = new Wild("+", "? extends ");
        static final Wild ANY = new Wild("*", "?;");

        public Wild(String str, String str2) {
            super(str);
            this.comment = str2;
        }

        public static Wild getWild(char c) {
            if (c == '-') {
                return SUPER;
            }
            if (c == '+') {
                return EXTENDS;
            }
            if (c == '*') {
                return ANY;
            }
            return null;
        }

        public static Wild readWild(SmaliReader smaliReader) {
            int iPosition = smaliReader.position();
            Wild wild = getWild(smaliReader.getASCII(iPosition));
            if (wild == null) {
                return null;
            }
            smaliReader.position(iPosition + 1);
            return wild;
        }

        @Override // com.reandroid.dex.key.ParameterisedTypeKey.ParameterName
        public void appendString(StringBuilder sb, boolean z) {
            if (z) {
                sb.append(getComment());
            } else {
                sb.append(getName());
            }
        }

        @Override // com.reandroid.dex.key.ParameterisedTypeKey.ParameterName
        public Wild changeName(String str) {
            Wild wild;
            return (str.length() != 1 || getName().equals(str) || (wild = getWild(str.charAt(0))) == null) ? this : wild;
        }

        public String getComment() {
            return this.comment;
        }
    }

    private ParameterisedTypeKey(ParameterName parameterName, ParameterisedProtoKey parameterisedProtoKey) {
        this.parameterName = parameterName;
        this.protoKey = parameterisedProtoKey;
    }

    private ParameterisedTypeKey changeInnerKey(TypeKey typeKey) {
        ParameterisedProtoKey protoKey;
        ParameterisedTypeKey returnType;
        InnerClassName innerClassName;
        String strCreateInnerName;
        TypeKey typeKeyCreateOuterKey;
        TypeKey innerClassKey = getInnerClassKey();
        if (innerClassKey == null || innerClassKey.equals(typeKey) || (returnType = (protoKey = getProtoKey()).getReturnType()) == null) {
            return this;
        }
        ParameterName parameterName = returnType.getParameterName();
        return (!(parameterName instanceof InnerClassName) || (strCreateInnerName = (innerClassName = (InnerClassName) parameterName).createInnerName(typeKey)) == null || (typeKeyCreateOuterKey = innerClassName.createOuterKey(typeKey)) == null) ? this : changeProtoKey(protoKey.changeReturnType(returnType.changeParameterName(innerClassName.changeName(strCreateInnerName)))).changeParameterName(typeKeyCreateOuterKey);
    }

    private ParameterisedTypeKey changeParameterName(TypeKey typeKey) {
        TypeKey nameTypeKey = getNameTypeKey();
        return (nameTypeKey == null || ObjectsUtil.equals(nameTypeKey, typeKey)) ? this : changeParameterName(getParameterName().changeName(typeKey));
    }

    public static ParameterisedTypeKey create(ParameterName parameterName, ParameterisedProtoKey parameterisedProtoKey) {
        if (parameterisedProtoKey == null || parameterisedProtoKey.isBlank()) {
            if (parameterName == null) {
                return null;
            }
            parameterisedProtoKey = ParameterisedProtoKey.EMPTY;
        }
        return new ParameterisedTypeKey(parameterName, parameterisedProtoKey);
    }

    private TypeKey getInnerClassKey() {
        ParameterName parameterName;
        ParameterName parameterName2;
        ParameterisedTypeKey returnType = getProtoKey().getReturnType();
        if (returnType == null || (parameterName = returnType.getParameterName()) == null || !parameterName.isInnerName() || (parameterName2 = getParameterName()) == null) {
            return null;
        }
        return TypeKey.create(parameterName2.getName() + parameterName.getName().replace('.', '$') + ";");
    }

    private TypeKey getNameTypeKey() {
        ParameterName parameterName = getParameterName();
        if (parameterName != null) {
            return parameterName.getDeclaring();
        }
        return null;
    }

    public static boolean isNameStop(char c) {
        return c == '<' || c == '>' || c == '(' || c == ')' || c == ';';
    }

    public static boolean isWild(char c) {
        return c == ':' || c == '-' || c == '+' || c == '*';
    }

    public static ParameterisedTypeKey read(SmaliReader smaliReader) throws IOException {
        int i;
        ParameterName name = readName(smaliReader);
        ParameterisedProtoKey parameterisedProtoKey = (smaliReader.finished() || !((i = smaliReader.get() & 255) == 60 || (i == 40 && name == null))) ? null : ParameterisedProtoKey.read(smaliReader);
        if (name != null && name.isTypeUse()) {
            SmaliParseException.expect(smaliReader, ';');
        }
        ParameterisedTypeKey parameterisedTypeKeyCreate = create(name, parameterisedProtoKey);
        if (parameterisedTypeKeyCreate != null) {
            return parameterisedTypeKeyCreate;
        }
        l78.a("Invalid ParameterisedTypeKey", smaliReader);
        return null;
    }

    private static ParameterName readName(SmaliReader smaliReader) {
        if (isNameStop(smaliReader.getASCII(smaliReader.position()))) {
            return null;
        }
        ParameterName wild = Wild.readWild(smaliReader);
        if (wild == null) {
            wild = InnerClassName.readInnerClassName(smaliReader);
        }
        if (wild == null) {
            wild = Definition.readDefinition(smaliReader);
        }
        if (wild == null) {
            wild = TypeUsePrimitive.readTypeUsePrimitive(smaliReader);
        }
        if (wild == null) {
            wild = TypeUseVariable.readTypeUseVariable(smaliReader);
        }
        return wild == null ? TypeUse.readTypeUse(smaliReader) : wild;
    }

    public void appendString(StringBuilder sb, boolean z) {
        boolean zIsTypeUse;
        ParameterName parameterName = getParameterName();
        if (parameterName != null) {
            parameterName.appendString(sb, z);
            zIsTypeUse = parameterName.isTypeUse();
        } else {
            zIsTypeUse = false;
        }
        getProtoKey().appendString(sb, z);
        if (zIsTypeUse) {
            sb.append(';');
        }
    }

    public void buildSignature(DalvikSignatureBuilder dalvikSignatureBuilder) {
        boolean zIsTypeUse;
        ParameterName parameterName = getParameterName();
        if (parameterName != null) {
            parameterName.buildSignature(dalvikSignatureBuilder);
            zIsTypeUse = parameterName.isTypeUse();
        } else {
            zIsTypeUse = false;
        }
        getProtoKey().buildSignature(dalvikSignatureBuilder);
        if (zIsTypeUse) {
            dalvikSignatureBuilder.append(';');
            dalvikSignatureBuilder.flushPending();
        }
    }

    public ParameterisedTypeKey changeProtoKey(ParameterisedProtoKey parameterisedProtoKey) {
        return ObjectsUtil.equals(getProtoKey(), parameterisedProtoKey) ? this : create(getParameterName(), parameterisedProtoKey);
    }

    @Override // com.reandroid.dex.key.Key, java.lang.Comparable
    public int compareTo(Object obj) {
        if (obj == this) {
            return 0;
        }
        if (obj == null) {
            return -1;
        }
        if (!(obj instanceof ParameterisedTypeKey)) {
            return StringsUtil.compareToString(this, obj);
        }
        ParameterisedTypeKey parameterisedTypeKey = (ParameterisedTypeKey) obj;
        int iCompare = CompareUtil.compare(getParameterName(), parameterisedTypeKey.getParameterName());
        return iCompare == 0 ? CompareUtil.compare(getProtoKey(), parameterisedTypeKey.getProtoKey()) : iCompare;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ParameterisedTypeKey)) {
            return false;
        }
        ParameterisedTypeKey parameterisedTypeKey = (ParameterisedTypeKey) obj;
        return ObjectsUtil.equals(getParameterName(), parameterisedTypeKey.getParameterName()) && ObjectsUtil.equals(getProtoKey(), parameterisedTypeKey.getProtoKey());
    }

    public String getComment() {
        StringBuilder sb = new StringBuilder();
        appendString(sb, true);
        return sb.toString();
    }

    @Override // com.reandroid.dex.key.Key
    public TypeKey getDeclaring() {
        TypeKey innerClassKey = getInnerClassKey();
        return innerClassKey == null ? getNameTypeKey() : innerClassKey;
    }

    public String getName() {
        ParameterName parameterName = getParameterName();
        if (parameterName != null) {
            return parameterName.getName();
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ParameterisedTypeKey getParameter(int i) {
        return (ParameterisedTypeKey) getProtoKey().get(i);
    }

    public ParameterName getParameterName() {
        return this.parameterName;
    }

    public int getParametersCount() {
        return getProtoKey().size();
    }

    public ParameterisedProtoKey getProtoKey() {
        return this.protoKey;
    }

    public Iterator<TypeKey> getTypes() {
        return new IterableIterator<TypeKey, TypeKey>(CombiningIterator.singleTwo(getInnerClassKey(), getProtoKey().getTypes(), SingleIterator.of(getNameTypeKey()))) { // from class: com.reandroid.dex.key.ParameterisedTypeKey.1
            public Iterator<TypeKey> iterator(TypeKey typeKey) {
                return typeKey.mentionedKeys();
            }
        };
    }

    public int hashCode() {
        return ObjectsUtil.hash(getParameterName(), getProtoKey());
    }

    public boolean isParametrisedType() {
        ParameterisedProtoKey protoKey = getProtoKey();
        return (protoKey.isMethod() || protoKey.isEmpty()) ? false : true;
    }

    public boolean isTypeVariableDefinition() {
        return getParameterName() instanceof Definition;
    }

    @Override // com.reandroid.dex.key.Key
    public Iterator<? extends Key> mentionedKeys() {
        return getTypes();
    }

    @Override // com.reandroid.dex.key.Key
    public ParameterisedTypeKey replaceKey(Key key, Key key2) {
        if (key.equals(this)) {
            return (ParameterisedTypeKey) key2;
        }
        if (ObjectsUtil.equals(getInnerClassKey(), key)) {
            this = changeInnerKey((TypeKey) key2);
        }
        if (ObjectsUtil.equals(this.getNameTypeKey(), key)) {
            this = this.changeParameterName((TypeKey) key2);
        }
        return this.changeProtoKey(this.getProtoKey().replaceKey(key, key2));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        appendString(sb, false);
        return sb.toString();
    }

    public static class TypeUse extends ParameterName {
        public TypeUse(String str) {
            super(str);
        }

        private static boolean isTypeUseStop(char c) {
            return ParameterisedTypeKey.isNameStop(c) || ParameterisedTypeKey.isWild(c);
        }

        public static TypeUse readTypeUse(SmaliReader smaliReader) {
            int iPosition = smaliReader.position();
            int iAvailable = smaliReader.available() + iPosition;
            boolean z = true;
            int i = iPosition;
            while (i < iAvailable) {
                char ascii = smaliReader.getASCII(i);
                if (ascii == '[') {
                    if (!z) {
                        break;
                    }
                    i++;
                } else {
                    if (isTypeUseStop(ascii)) {
                        break;
                    }
                    i++;
                    z = false;
                }
            }
            if (i == iPosition) {
                return null;
            }
            return new TypeUse(smaliReader.readString(i - iPosition));
        }

        @Override // com.reandroid.dex.key.ParameterisedTypeKey.ParameterName
        public void buildSignature(DalvikSignatureBuilder dalvikSignatureBuilder) {
            boolean z;
            if (isClassType()) {
                dalvikSignatureBuilder.flush();
                z = true;
            } else {
                z = false;
            }
            dalvikSignatureBuilder.append(getName());
            if (z) {
                dalvikSignatureBuilder.markFlush();
            }
        }

        @Override // com.reandroid.dex.key.ParameterisedTypeKey.ParameterName
        public TypeUse changeName(TypeKey typeKey) {
            String typeName = typeKey.getTypeName();
            return changeName(typeName.substring(0, typeName.length() - 1));
        }

        @Override // com.reandroid.dex.key.ParameterisedTypeKey.ParameterName, com.reandroid.dex.key.Key
        public TypeKey getDeclaring() {
            if (!isClassType()) {
                return null;
            }
            return TypeKey.create(getName() + ";");
        }

        @Override // com.reandroid.dex.key.ParameterisedTypeKey.ParameterName
        public boolean isClassType() {
            String name = getName();
            int length = name.length();
            int i = 0;
            while (i < length && name.charAt(i) == '[') {
                i++;
            }
            return i + 2 < length && name.charAt(i) == 'L';
        }

        @Override // com.reandroid.dex.key.ParameterisedTypeKey.ParameterName
        public boolean isTypeUse() {
            return true;
        }

        @Override // com.reandroid.dex.key.ParameterisedTypeKey.ParameterName
        public TypeUse changeName(String str) {
            return getName().equals(str) ? this : new TypeUse(str);
        }
    }

    private ParameterisedTypeKey changeParameterName(ParameterName parameterName) {
        return ObjectsUtil.equals(getParameterName(), parameterName) ? this : create(parameterName, getProtoKey());
    }
}
