package com.reandroid.dex.key;

import com.intellij.psi.PsiKeyword;
import com.reandroid.dex.common.MethodHandleType;
import com.reandroid.dex.smali.SmaliParseException;
import com.reandroid.dex.smali.SmaliReader;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.utils.CompareUtil;
import com.reandroid.utils.ObjectsUtil;
import com.reandroid.utils.StringsUtil;
import defpackage.l78;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class CallSiteKey implements Key {
    private final ArrayKey<?> arguments;
    private final MethodHandleKey methodHandle;
    private final StringKey name;
    private final ProtoKey proto;

    public CallSiteKey(MethodHandleKey methodHandleKey, StringKey stringKey, ProtoKey protoKey, ArrayKey<?> arrayKey) {
        this.methodHandle = methodHandleKey;
        this.name = stringKey;
        this.proto = protoKey;
        this.arguments = arrayKey;
    }

    public static CallSiteKey parse(String str) {
        throw new RuntimeException("CallSiteKey.parse not implemented");
    }

    public static CallSiteKey read(SmaliReader smaliReader) throws IOException {
        smaliReader.skipWhitespaces();
        readCallSiteLabel(smaliReader);
        SmaliParseException.expect(smaliReader, '(');
        StringKey stringKey = StringKey.read(smaliReader);
        smaliReader.skipWhitespacesOrComment();
        SmaliParseException.expect(smaliReader, ',');
        ProtoKey protoKey = ProtoKey.read(smaliReader);
        smaliReader.skipWhitespacesOrComment();
        SmaliParseException.expect(smaliReader, ',');
        ArrayKey arrayKey = ArrayKey.read(smaliReader, ')');
        smaliReader.skipWhitespacesOrComment();
        SmaliParseException.expect(smaliReader, '@');
        return new CallSiteKey(MethodHandleKey.read(MethodHandleType.INVOKE_STATIC, smaliReader), stringKey, protoKey, arrayKey);
    }

    private static String readCallSiteLabel(SmaliReader smaliReader) throws IOException {
        int iPosition = smaliReader.position();
        if (smaliReader.getASCII(iPosition) == '(') {
            return null;
        }
        int iIndexOfBeforeLineEnd = smaliReader.indexOfBeforeLineEnd('(');
        if (iIndexOfBeforeLineEnd >= 0) {
            return smaliReader.readString(iIndexOfBeforeLineEnd - iPosition);
        }
        l78.a("Expecting call site", smaliReader);
        return null;
    }

    /* JADX WARN: Type inference failed for: r5v0, types: [com.reandroid.dex.key.Key] */
    @Override // com.reandroid.dex.key.Key, com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        smaliWriter.append('(');
        getName().append(smaliWriter);
        smaliWriter.append(", ");
        getProto().append(smaliWriter);
        ArrayKey<?> arguments = getArguments();
        int size = arguments.size();
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                smaliWriter.append(", ");
            }
            arguments.get(i).append(smaliWriter);
        }
        smaliWriter.append(')');
        getMethodHandle().append(smaliWriter, false);
    }

    public CallSiteKey changeArguments(ArrayKey<?> arrayKey) {
        return arrayKey.equals(getArguments()) ? this : new CallSiteKey(getMethodHandle(), getName(), getProto(), arrayKey);
    }

    public CallSiteKey changeMethodHandle(MethodHandleKey methodHandleKey) {
        return methodHandleKey.equals(getMethodHandle()) ? this : new CallSiteKey(methodHandleKey, getName(), getProto(), getArguments());
    }

    public CallSiteKey changeName(StringKey stringKey) {
        return stringKey.equals(getName()) ? this : new CallSiteKey(getMethodHandle(), stringKey, getProto(), getArguments());
    }

    public CallSiteKey changeProto(ProtoKey protoKey) {
        return protoKey.equals(getProto()) ? this : new CallSiteKey(getMethodHandle(), getName(), protoKey, getArguments());
    }

    @Override // com.reandroid.dex.key.Key, java.lang.Comparable
    public int compareTo(Object obj) {
        if (obj == this) {
            return 0;
        }
        if (!(obj instanceof CallSiteKey)) {
            return StringsUtil.compareToString(this, obj);
        }
        CallSiteKey callSiteKey = (CallSiteKey) obj;
        int iCompare = CompareUtil.compare(getMethodHandle(), callSiteKey.getMethodHandle());
        return (iCompare == 0 && (iCompare = CompareUtil.compare(getArguments(), callSiteKey.getArguments())) == 0 && (iCompare = CompareUtil.compare(getName(), callSiteKey.getName())) == 0) ? CompareUtil.compare(getProto(), callSiteKey.getProto()) : iCompare;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CallSiteKey)) {
            return false;
        }
        CallSiteKey callSiteKey = (CallSiteKey) obj;
        return ObjectsUtil.equals(getMethodHandle(), callSiteKey.getMethodHandle()) && ObjectsUtil.equals(getName(), callSiteKey.getName()) && ObjectsUtil.equals(getProto(), callSiteKey.getProto()) && ObjectsUtil.equals(getArguments(), callSiteKey.getArguments());
    }

    public ArrayKey<?> getArguments() {
        return this.arguments;
    }

    public MethodHandleKey getMethodHandle() {
        return this.methodHandle;
    }

    public StringKey getName() {
        return this.name;
    }

    public ProtoKey getProto() {
        return this.proto;
    }

    public int hashCode() {
        return ObjectsUtil.hash(getMethodHandle(), getName(), getProto(), getArguments());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ArrayKey<?> toArrayKey() {
        ArrayKey<?> arguments = getArguments();
        int size = arguments.size();
        Key[] keyArr = new Key[arguments.size() + 3];
        keyArr[0] = getMethodHandle();
        keyArr[1] = getName();
        keyArr[2] = getProto();
        for (int i = 0; i < size; i++) {
            keyArr[i + 3] = arguments.get(i);
        }
        return ArrayKey.create(keyArr);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        sb.append(getName());
        sb.append(", ");
        sb.append(getProto());
        ArrayKey<?> arguments = getArguments();
        if (!arguments.isEmpty()) {
            sb.append(", ");
            sb.append(arguments.toString(", "));
        }
        sb.append(")@");
        MethodHandleKey methodHandle = getMethodHandle();
        if (methodHandle != null) {
            sb.append(methodHandle.getMember());
        } else {
            sb.append(PsiKeyword.NULL);
        }
        return sb.toString();
    }
}
