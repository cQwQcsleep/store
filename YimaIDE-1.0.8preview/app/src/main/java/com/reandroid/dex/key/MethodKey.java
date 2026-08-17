package com.reandroid.dex.key;

import com.reandroid.dex.smali.SmaliParseException;
import com.reandroid.dex.smali.SmaliReader;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.utils.CompareUtil;
import com.reandroid.utils.ObjectsUtil;
import com.reandroid.utils.StringsUtil;
import com.reandroid.utils.collection.CombiningIterator;
import com.reandroid.utils.collection.SingleIterator;
import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.xml.internal.utils.LocaleUtility;
import java.io.IOException;
import java.lang.annotation.ElementType;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Iterator;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class MethodKey implements ProgramKey {
    public static final MethodKey CONSTRUCTOR;
    public static final MethodKey CONSTRUCTOR_STATIC;
    public static final MethodKey EQUALS;
    public static final MethodKey HASHCODE;
    public static final MethodKey STATIC_CONSTRUCTOR;
    public static final MethodKey TO_STRING;
    private final TypeKey declaring;
    private final StringKey nameKey;
    private final ProtoKey proto;

    static {
        TypeKey typeKey = TypeKey.OBJECT;
        TypeKey typeKey2 = TypeKey.TYPE_V;
        STATIC_CONSTRUCTOR = new MethodKey(typeKey, Const.STATIC_INITIALIZER_NAME, ProtoKey.emptyParameters(typeKey2));
        EQUALS = new MethodKey(typeKey, "equals", ProtoKey.create(TypeKey.TYPE_Z, typeKey));
        HASHCODE = new MethodKey(typeKey, "hashCode", ProtoKey.emptyParameters(TypeKey.TYPE_I));
        TO_STRING = new MethodKey(typeKey, "toString", ProtoKey.emptyParameters(TypeKey.STRING));
        CONSTRUCTOR = new MethodKey(typeKey, Const.CONSTRUCTOR_NAME, ProtoKey.emptyParameters(typeKey2));
        CONSTRUCTOR_STATIC = new MethodKey(typeKey, Const.STATIC_INITIALIZER_NAME, ProtoKey.emptyParameters(typeKey2));
    }

    public MethodKey(TypeKey typeKey, StringKey stringKey, ProtoKey protoKey) {
        this.declaring = typeKey;
        this.nameKey = stringKey;
        this.proto = protoKey;
    }

    public static MethodKey convert(Method method) {
        TypeKey typeKeyConvert = TypeKey.convert(method.getDeclaringClass());
        TypeKey typeKeyConvert2 = TypeKey.convert(method.getReturnType());
        Parameter[] parameters = method.getParameters();
        int length = parameters.length;
        TypeKey[] typeKeyArr = new TypeKey[length];
        for (int i = 0; i < length; i++) {
            typeKeyArr[i] = TypeKey.convert(parameters[i].getType());
        }
        return create(typeKeyConvert, StringKey.create(method.getName()), ProtoKey.create(typeKeyConvert2, typeKeyArr));
    }

    public static MethodKey create(TypeKey typeKey, StringKey stringKey, ProtoKey protoKey) {
        if (typeKey == null || stringKey == null || protoKey == null) {
            return null;
        }
        return new MethodKey(typeKey, stringKey, protoKey);
    }

    public static MethodKey parse(String str, int i) {
        int iIndexOf;
        TypeKey binaryType;
        int length;
        int iIndexOf2;
        ProtoKey protoKey;
        if (str.length() - i < 6 || (!(str.charAt(i) == 'L' || str.charAt(i) == '[') || (iIndexOf = str.indexOf("->", i)) < 0 || (binaryType = TypeKey.parseBinaryType(str, i, iIndexOf)) == null || (iIndexOf2 = str.indexOf(40, (length = i + binaryType.getTypeName().length() + 2))) < 0)) {
            return null;
        }
        String strSubstring = str.substring(length, iIndexOf2);
        if (str.indexOf(41, iIndexOf2) >= 0 && (protoKey = ProtoKey.parse(str, iIndexOf2)) != null) {
            return create(binaryType, StringKey.create(strSubstring), protoKey);
        }
        return null;
    }

    public static MethodKey read(SmaliReader smaliReader) throws IOException {
        TypeKey typeKey = TypeKey.read(smaliReader);
        smaliReader.skipWhitespacesOrComment();
        SmaliParseException.expect(smaliReader, LocaleUtility.IETF_SEPARATOR);
        SmaliParseException.expect(smaliReader, '>');
        smaliReader.skipWhitespacesOrComment();
        return create(typeKey, StringKey.readSimpleName(smaliReader, '('), ProtoKey.read(smaliReader));
    }

    @Override // com.reandroid.dex.key.Key, com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        getDeclaring().append(smaliWriter);
        smaliWriter.append("->");
        appendDefinition(smaliWriter);
    }

    public void appendDefinition(SmaliWriter smaliWriter) throws IOException {
        getNameKey().appendSimpleName(smaliWriter);
        getProto().append(smaliWriter);
    }

    public MethodKey changeDeclaring(TypeKey typeKey) {
        return getDeclaring().equals(typeKey) ? this : new MethodKey(typeKey, getNameKey(), getProto());
    }

    public MethodKey changeName(StringKey stringKey) {
        return stringKey.equals(getNameKey()) ? this : new MethodKey(getDeclaring(), stringKey, getProto());
    }

    public MethodKey changeParameter(int i, TypeKey typeKey) {
        return changeProto(getProto().changeParameter(i, typeKey));
    }

    public MethodKey changeParameters(TypeListKey typeListKey) {
        return changeProto(getProto().changeParameters(typeListKey));
    }

    public MethodKey changeProto(ProtoKey protoKey) {
        return protoKey.equals(getProto()) ? this : create(getDeclaring(), getNameKey(), protoKey);
    }

    public MethodKey changeReturnType(TypeKey typeKey) {
        return changeProto(getProto().changeReturnType(typeKey));
    }

    @Override // com.reandroid.dex.key.Key, java.lang.Comparable
    public int compareTo(Object obj) {
        if (obj == this) {
            return 0;
        }
        if (!(obj instanceof MethodKey)) {
            return StringsUtil.compareToString(this, obj);
        }
        MethodKey methodKey = (MethodKey) obj;
        int iCompare = CompareUtil.compare(getDeclaring(), methodKey.getDeclaring());
        if (iCompare != 0) {
            return iCompare;
        }
        int iCompare2 = CompareUtil.compare(getNameKey(), methodKey.getNameKey());
        return iCompare2 != 0 ? iCompare2 : CompareUtil.compare(getProto(), methodKey.getProto());
    }

    public boolean equals(Object obj, boolean z, boolean z2) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MethodKey)) {
            return false;
        }
        MethodKey methodKey = (MethodKey) obj;
        if (!ObjectsUtil.equals(getNameKey(), methodKey.getNameKey()) || !KeyList.equalsIgnoreEmpty(getParameters(), methodKey.getParameters())) {
            return false;
        }
        if (z && !ObjectsUtil.equals(getDeclaring(), methodKey.getDeclaring())) {
            return false;
        }
        if (z2) {
            return ObjectsUtil.equals(getReturnType(), methodKey.getReturnType());
        }
        return true;
    }

    public boolean equalsDeclaring(MethodKey methodKey) {
        if (methodKey == null) {
            return false;
        }
        if (methodKey == this) {
            return true;
        }
        return ObjectsUtil.equals(getDeclaring(), methodKey.getDeclaring());
    }

    public boolean equalsIgnoreDeclaring(MethodKey methodKey) {
        if (methodKey == null) {
            return false;
        }
        if (methodKey == this) {
            return true;
        }
        return ObjectsUtil.equals(getNameKey(), methodKey.getNameKey()) && ObjectsUtil.equals(getProto(), methodKey.getProto());
    }

    public boolean equalsIgnoreName(MethodKey methodKey) {
        if (methodKey == null) {
            return false;
        }
        if (methodKey == this) {
            return true;
        }
        if (ObjectsUtil.equals(getDeclaring(), methodKey.getDeclaring())) {
            return ObjectsUtil.equals(getProto(), methodKey.getProto());
        }
        return false;
    }

    public boolean equalsIgnoreReturnType(MethodKey methodKey) {
        if (methodKey == null) {
            return false;
        }
        if (methodKey == this) {
            return true;
        }
        if (ObjectsUtil.equals(getDeclaring(), methodKey.getDeclaring()) && ObjectsUtil.equals(getNameKey(), methodKey.getNameKey())) {
            return getProto().equalsParameters(methodKey.getProto());
        }
        return false;
    }

    public boolean equalsName(MethodKey methodKey) {
        if (methodKey == null) {
            return false;
        }
        if (methodKey == this) {
            return true;
        }
        return KeyUtil.matches(getName(), methodKey.getName());
    }

    public boolean equalsNameAndParameters(MethodKey methodKey) {
        if (methodKey == null) {
            return false;
        }
        if (methodKey == this) {
            return true;
        }
        if (KeyUtil.matches(getName(), methodKey.getName())) {
            return KeyList.equalsIgnoreEmpty(getParameters(), methodKey.getParameters());
        }
        return false;
    }

    public boolean equalsProto(MethodKey methodKey) {
        if (methodKey == null) {
            return false;
        }
        if (methodKey == this) {
            return true;
        }
        return ObjectsUtil.equals(getProto(), methodKey.getProto());
    }

    public boolean equalsReturnType(MethodKey methodKey) {
        if (methodKey == null) {
            return false;
        }
        if (methodKey == this) {
            return true;
        }
        return getProto().equalsReturnType(methodKey.getProto());
    }

    @Override // com.reandroid.dex.key.ProgramKey, com.reandroid.dex.key.Key
    public TypeKey getDeclaring() {
        return this.declaring;
    }

    @Override // com.reandroid.dex.key.ProgramKey
    public ElementType getElementType() {
        return ElementType.METHOD;
    }

    public String getName() {
        return getNameKey().getString();
    }

    public StringKey getNameKey() {
        return this.nameKey;
    }

    public TypeKey getParameter(int i) {
        return getParameters().get(i);
    }

    public int getParameterIndex(int i) {
        return getProto().getParameterIndex(i);
    }

    public int getParameterRegistersCount() {
        return getProto().getParameterRegistersCount();
    }

    public TypeListKey getParameters() {
        return getProto().getParameters();
    }

    public int getParametersCount() {
        return getParameters().size();
    }

    public ProtoKey getProto() {
        return this.proto;
    }

    public int getRegister(int i) {
        return getProto().getRegister(i);
    }

    public TypeKey getReturnType() {
        return getProto().getReturnType();
    }

    public int hashCode() {
        return ObjectsUtil.hash(getDeclaring(), getNameKey(), getProto());
    }

    @Override // com.reandroid.dex.key.Key
    public Iterator<Key> mentionedKeys() {
        return CombiningIterator.singleTwo(this, CombiningIterator.singleOne(getDeclaring(), SingleIterator.of(getNameKey())), getProto().mentionedKeys());
    }

    public Iterator<TypeKey> parameters() {
        return getParameters().iterator();
    }

    public MethodKey removeParameter(int i) {
        return changeProto(getProto().removeParameter(i));
    }

    @Override // com.reandroid.dex.key.Key
    public MethodKey replaceKey(Key key, Key key2) {
        if (key.equals(this)) {
            return (MethodKey) key2;
        }
        MethodKey methodKeyChangeDeclaring = key.equals(getDeclaring()) ? changeDeclaring((TypeKey) key2) : this;
        if ((key2 instanceof StringKey) && key.equals(methodKeyChangeDeclaring.getNameKey())) {
            methodKeyChangeDeclaring = methodKeyChangeDeclaring.changeName((StringKey) key2);
        }
        return methodKeyChangeDeclaring.changeProto(getProto().replaceKey(key, key2));
    }

    public MethodKey replaceTypes(Function<TypeKey, TypeKey> function) {
        TypeKey declaring = getDeclaring();
        MethodKey methodKeyChangeDeclaring = changeDeclaring(declaring.changeTypeName(function.apply(declaring)));
        TypeKey returnType = getReturnType();
        MethodKey methodKeyChangeReturnType = methodKeyChangeDeclaring.changeReturnType(returnType.changeTypeName(function.apply(returnType)));
        int parametersCount = getParametersCount();
        for (int i = 0; i < parametersCount; i++) {
            TypeKey parameter = getParameter(i);
            methodKeyChangeReturnType = methodKeyChangeReturnType.changeParameter(i, parameter.changeTypeName(function.apply(parameter)));
        }
        return methodKeyChangeReturnType;
    }

    public String toString() {
        return getDeclaring() + "->" + getNameKey().getAsSimpleName() + getProto();
    }

    public MethodKey(TypeKey typeKey, String str, ProtoKey protoKey) {
        this(typeKey, StringKey.create(str), protoKey);
    }

    public static MethodKey create(TypeKey typeKey, String str, ProtoKey protoKey) {
        return create(typeKey, StringKey.create(str), protoKey);
    }

    public boolean equalsDeclaring(TypeKey typeKey) {
        return ObjectsUtil.equals(getDeclaring(), typeKey);
    }

    public boolean equalsName(String str) {
        return KeyUtil.matches(getName(), str);
    }

    public boolean equalsReturnType(TypeKey typeKey) {
        return getProto().equalsReturnType(typeKey);
    }

    public MethodKey changeName(String str) {
        return changeName(StringKey.create(str));
    }

    public int compareTo(Object obj, boolean z) {
        int iCompare;
        if (obj == null) {
            return -1;
        }
        if (obj == this) {
            return 0;
        }
        MethodKey methodKey = (MethodKey) obj;
        if (z && (iCompare = CompareUtil.compare(getDeclaring(), methodKey.getDeclaring())) != 0) {
            return iCompare;
        }
        int iCompare2 = CompareUtil.compare(getNameKey(), methodKey.getNameKey());
        return iCompare2 != 0 ? iCompare2 : CompareUtil.compare(getProto(), methodKey.getProto());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MethodKey)) {
            return false;
        }
        MethodKey methodKey = (MethodKey) obj;
        return ObjectsUtil.equals(getNameKey(), methodKey.getNameKey()) && ObjectsUtil.equals(getDeclaring(), methodKey.getDeclaring()) && ObjectsUtil.equals(getProto(), methodKey.getProto());
    }

    public static MethodKey parse(String str) {
        return parse(str, 0);
    }
}
