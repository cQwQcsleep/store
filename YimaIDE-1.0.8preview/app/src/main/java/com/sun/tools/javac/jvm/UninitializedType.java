package com.sun.tools.javac.jvm;

import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.code.TypeMetadata;
import com.sun.tools.javac.code.TypeTag;
import com.sun.tools.javac.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
class UninitializedType extends Type.DelegatedType {
    public final int offset;

    private UninitializedType(TypeTag typeTag, Type type, int i, List<TypeMetadata> list) {
        super(typeTag, type, list);
        this.offset = i;
    }

    public static UninitializedType uninitializedObject(Type type, int i) {
        return new UninitializedType(TypeTag.UNINITIALIZED_OBJECT, type, i, type.getMetadata());
    }

    public static UninitializedType uninitializedThis(Type type) {
        return new UninitializedType(TypeTag.UNINITIALIZED_THIS, type, -1, type.getMetadata());
    }

    @Override // com.sun.tools.javac.code.Type
    public UninitializedType cloneWithMetadata(List<TypeMetadata> list) {
        return new UninitializedType(this.tag, this.qtype, this.offset, list);
    }

    public Type initializedType() {
        return this.qtype;
    }

    @Override // com.sun.tools.javac.code.Type
    public /* bridge */ /* synthetic */ Type cloneWithMetadata(List list) {
        return cloneWithMetadata((List<TypeMetadata>) list);
    }
}
