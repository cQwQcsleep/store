package com.sun.org.apache.bcel.internal.generic;

import com.sun.org.apache.bcel.internal.classfile.ClassFormatException;
import com.sun.org.apache.bcel.internal.classfile.Utility;
import com.sun.org.apache.bcel.internal.generic.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Constants;
import defpackage.ise;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.IntFunction;
import java.util.function.Supplier;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class Type {

    @Deprecated
    protected String signature;

    @Deprecated
    protected byte type;
    public static final BasicType VOID = new BasicType((byte) 12);
    public static final BasicType BOOLEAN = new BasicType((byte) 4);
    public static final BasicType INT = new BasicType((byte) 10);
    public static final BasicType SHORT = new BasicType((byte) 9);
    public static final BasicType BYTE = new BasicType((byte) 8);
    public static final BasicType LONG = new BasicType((byte) 11);
    public static final BasicType DOUBLE = new BasicType((byte) 7);
    public static final BasicType FLOAT = new BasicType((byte) 6);
    public static final BasicType CHAR = new BasicType((byte) 5);
    public static final ObjectType OBJECT = new ObjectType(Constants.OBJECT_CLASS);
    public static final ObjectType CLASS = new ObjectType(Constants.CLASS_CLASS);
    public static final ObjectType STRING = new ObjectType("java.lang.String");
    public static final ObjectType STRINGBUFFER = new ObjectType(Constants.STRING_BUFFER_CLASS);
    public static final ObjectType THROWABLE = new ObjectType("java.lang.Throwable");
    public static final Type[] NO_ARGS = new Type[0];
    public static final ReferenceType NULL = new ReferenceType() { // from class: com.sun.org.apache.bcel.internal.generic.Type.1
    };
    public static final Type UNKNOWN = new Type(15, "<unknown object>") { // from class: com.sun.org.apache.bcel.internal.generic.Type.2
    };
    private static final ThreadLocal<Integer> CONSUMED_CHARS = ThreadLocal.withInitial(new Supplier() { // from class: mse
        @Override // java.util.function.Supplier
        public final Object get() {
            return Type.b();
        }
    });

    public Type(byte b, String str) {
        this.type = b;
        this.signature = str;
    }

    public static /* synthetic */ Integer b() {
        return 0;
    }

    public static int consumed(int i) {
        return i >> 2;
    }

    public static int encode(int i, int i2) {
        return i | (i2 << 2);
    }

    public static Type[] getArgumentTypes(String str) {
        ArrayList arrayList = new ArrayList();
        try {
            int iIndexOf = str.indexOf(40) + 1;
            if (iIndexOf <= 0) {
                throw new ClassFormatException("Invalid method signature: ".concat(str));
            }
            while (str.charAt(iIndexOf) != ')') {
                arrayList.add(getType(str.substring(iIndexOf)));
                iIndexOf += unwrap(CONSUMED_CHARS);
            }
            Type[] typeArr = new Type[arrayList.size()];
            arrayList.toArray(typeArr);
            return typeArr;
        } catch (StringIndexOutOfBoundsException e) {
            ise.a("Invalid method signature: ", str, e);
            return null;
        }
    }

    public static int getArgumentTypesSize(String str) {
        try {
            int iIndexOf = str.indexOf(40) + 1;
            if (iIndexOf <= 0) {
                throw new ClassFormatException("Invalid method signature: ".concat(str));
            }
            int size = 0;
            while (str.charAt(iIndexOf) != ')') {
                int typeSize = getTypeSize(str.substring(iIndexOf));
                size += size(typeSize);
                iIndexOf += consumed(typeSize);
            }
            return size;
        } catch (StringIndexOutOfBoundsException e) {
            ise.a("Invalid method signature: ", str, e);
            return 0;
        }
    }

    public static String getMethodSignature(Type type, Type[] typeArr) {
        StringBuilder sb = new StringBuilder("(");
        if (typeArr != null) {
            for (Type type2 : typeArr) {
                sb.append(type2.getSignature());
            }
        }
        sb.append(')');
        sb.append(type.getSignature());
        return sb.toString();
    }

    public static Type getReturnType(String str) {
        try {
            return getType(str.substring(str.lastIndexOf(41) + 1));
        } catch (StringIndexOutOfBoundsException e) {
            ise.a("Invalid method signature: ", str, e);
            return null;
        }
    }

    public static int getReturnTypeSize(String str) {
        return size(getTypeSize(str.substring(str.lastIndexOf(41) + 1)));
    }

    public static String getSignature(Method method) {
        StringBuilder sb = new StringBuilder("(");
        for (Class<?> cls : method.getParameterTypes()) {
            sb.append(getType(cls).getSignature());
        }
        sb.append(")");
        sb.append(getType(method.getReturnType()).getSignature());
        return sb.toString();
    }

    public static Type getType(Class<?> cls) {
        Objects.requireNonNull(cls, "cls");
        if (cls.isArray()) {
            return getType(cls.getName());
        }
        if (!cls.isPrimitive()) {
            return ObjectType.getInstance(cls.getName());
        }
        if (cls == Integer.TYPE) {
            return INT;
        }
        if (cls == Void.TYPE) {
            return VOID;
        }
        if (cls == Double.TYPE) {
            return DOUBLE;
        }
        if (cls == Float.TYPE) {
            return FLOAT;
        }
        if (cls == Boolean.TYPE) {
            return BOOLEAN;
        }
        if (cls == Byte.TYPE) {
            return BYTE;
        }
        if (cls == Short.TYPE) {
            return SHORT;
        }
        if (cls == Long.TYPE) {
            return LONG;
        }
        if (cls == Character.TYPE) {
            return CHAR;
        }
        qu7.a("Unknown primitive type ", cls);
        return null;
    }

    public static int getTypeSize(String str) throws StringIndexOutOfBoundsException {
        byte bTypeOfSignature = Utility.typeOfSignature(str);
        if (bTypeOfSignature <= 12) {
            return encode(BasicType.getType(bTypeOfSignature).getSize(), 1);
        }
        if (bTypeOfSignature == 13) {
            int i = 0;
            do {
                i++;
            } while (str.charAt(i) == '[');
            return encode(1, i + consumed(getTypeSize(str.substring(i))));
        }
        int iIndexOf = str.indexOf(59);
        if (iIndexOf >= 0) {
            return encode(1, iIndexOf + 1);
        }
        throw new ClassFormatException("Invalid signature: ".concat(str));
    }

    public static Type[] getTypes(final Class<?>[] clsArr) {
        Type[] typeArr = new Type[clsArr.length];
        Arrays.setAll(typeArr, new IntFunction() { // from class: lse
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return Type.getType((Class<?>) clsArr[i]);
            }
        });
        return typeArr;
    }

    public static int size(int i) {
        return i & 3;
    }

    private static int unwrap(ThreadLocal<Integer> threadLocal) {
        return threadLocal.get().intValue();
    }

    private static void wrap(ThreadLocal<Integer> threadLocal, int i) {
        threadLocal.set(Integer.valueOf(i));
    }

    public boolean equals(Object obj) {
        if (obj instanceof Type) {
            Type type = (Type) obj;
            if (this.type == type.type && this.signature.equals(type.signature)) {
                return true;
            }
        }
        return false;
    }

    public String getClassName() {
        return toString();
    }

    public int getSize() {
        byte b = this.type;
        if (b == 7 || b == 11) {
            return 2;
        }
        return b != 12 ? 1 : 0;
    }

    public int hashCode() {
        return this.signature.hashCode() ^ this.type;
    }

    public Type normalizeForStackOrLocal() {
        return (this == BOOLEAN || this == BYTE || this == SHORT || this == CHAR) ? INT : this;
    }

    public void setSignature(String str) {
        this.signature = str;
    }

    public String toString() {
        return (equals(NULL) || this.type >= 15) ? this.signature : Utility.signatureToString(this.signature, false);
    }

    public String getSignature() {
        return this.signature;
    }

    public static Type getType(String str) throws StringIndexOutOfBoundsException {
        byte bTypeOfSignature = Utility.typeOfSignature(str);
        if (bTypeOfSignature <= 12) {
            wrap(CONSUMED_CHARS, 1);
            return BasicType.getType(bTypeOfSignature);
        }
        int i = 0;
        if (bTypeOfSignature != 13) {
            String strTypeSignatureToString = Utility.typeSignatureToString(str, false);
            wrap(CONSUMED_CHARS, strTypeSignatureToString.length() + 2);
            return ObjectType.getInstance(Utility.pathToPackage(strTypeSignatureToString));
        }
        do {
            i++;
        } while (str.charAt(i) == '[');
        Type type = getType(str.substring(i));
        ThreadLocal<Integer> threadLocal = CONSUMED_CHARS;
        wrap(threadLocal, unwrap(threadLocal) + i);
        return new ArrayType(type, i);
    }

    public byte getType() {
        return this.type;
    }
}
