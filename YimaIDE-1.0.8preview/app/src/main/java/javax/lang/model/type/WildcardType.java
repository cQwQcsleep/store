package javax.lang.model.type;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
public interface WildcardType extends TypeMirror {
    TypeMirror getExtendsBound();

    TypeMirror getSuperBound();
}
