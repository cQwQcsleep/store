package com.sun.org.apache.xalan.internal.xsltc.compiler.util;

import com.intellij.psi.PsiKeyword;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class MethodType extends Type {
    private final List<Type> _argsType;
    private final Type _resultType;

    public MethodType(Type type, Type type2) {
        if (type2 != Type.Void) {
            ArrayList arrayList = new ArrayList();
            this._argsType = arrayList;
            arrayList.add(type2);
        } else {
            this._argsType = null;
        }
        this._resultType = type;
    }

    public int argsCount() {
        List<Type> list = this._argsType;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public List<Type> argsType() {
        return this._argsType;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public int distanceTo(Type type) {
        if (type instanceof MethodType) {
            MethodType methodType = (MethodType) type;
            List<Type> list = this._argsType;
            if (list != null) {
                int size = list.size();
                if (size == methodType._argsType.size()) {
                    int iDistanceTo = 0;
                    for (int i = 0; i < size; i++) {
                        Type type2 = this._argsType.get(i);
                        Type type3 = methodType._argsType.get(i);
                        int iDistanceTo2 = type2.distanceTo(type3);
                        if (iDistanceTo2 == Integer.MAX_VALUE) {
                            return iDistanceTo2;
                        }
                        iDistanceTo += type2.distanceTo(type3);
                    }
                    return iDistanceTo;
                }
            } else if (methodType._argsType == null) {
                return 0;
            }
        }
        return Integer.MAX_VALUE;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public boolean identicalTo(Type type) {
        if (type instanceof MethodType) {
            MethodType methodType = (MethodType) type;
            if (this._resultType.identicalTo(methodType._resultType)) {
                int iArgsCount = argsCount();
                boolean zIdenticalTo = iArgsCount == methodType.argsCount();
                for (int i = 0; i < iArgsCount && zIdenticalTo; i++) {
                    zIdenticalTo = this._argsType.get(i).identicalTo(methodType._argsType.get(i));
                }
                return zIdenticalTo;
            }
        }
        return false;
    }

    public Type resultType() {
        return this._resultType;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public com.sun.org.apache.bcel.internal.generic.Type toJCType() {
        return null;
    }

    public String toSignature(String str) {
        StringBuffer stringBuffer = new StringBuffer("(");
        List<Type> list = this._argsType;
        if (list != null) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                stringBuffer.append(this._argsType.get(i).toSignature());
            }
        }
        stringBuffer.append(str);
        stringBuffer.append(')');
        stringBuffer.append(this._resultType.toSignature());
        return stringBuffer.toString();
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("method{");
        List<Type> list = this._argsType;
        if (list != null) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                stringBuffer.append(this._argsType.get(i));
                if (i != size - 1) {
                    stringBuffer.append(',');
                }
            }
        } else {
            stringBuffer.append(PsiKeyword.VOID);
        }
        stringBuffer.append('}');
        return stringBuffer.toString();
    }

    public MethodType(Type type) {
        this._argsType = null;
        this._resultType = type;
    }

    public MethodType(Type type, Type type2, Type type3) {
        ArrayList arrayList = new ArrayList(2);
        this._argsType = arrayList;
        arrayList.add(type2);
        arrayList.add(type3);
        this._resultType = type;
    }

    public MethodType(Type type, Type type2, Type type3, Type type4) {
        ArrayList arrayList = new ArrayList(3);
        this._argsType = arrayList;
        arrayList.add(type2);
        arrayList.add(type3);
        arrayList.add(type4);
        this._resultType = type;
    }

    public MethodType(Type type, List<Type> list) {
        this._resultType = type;
        this._argsType = list.size() <= 0 ? null : list;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public String toSignature() {
        return toSignature("");
    }
}
