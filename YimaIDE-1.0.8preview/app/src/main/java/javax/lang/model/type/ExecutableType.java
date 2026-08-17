package javax.lang.model.type;

import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface ExecutableType extends TypeMirror {
    /* JADX INFO: renamed from: getParameterTypes */
    List<? extends TypeMirror> mo71getParameterTypes();

    /* JADX INFO: renamed from: getReceiverType */
    TypeMirror mo72getReceiverType();

    /* JADX INFO: renamed from: getReturnType */
    TypeMirror mo73getReturnType();

    /* JADX INFO: renamed from: getThrownTypes */
    List<? extends TypeMirror> mo74getThrownTypes();

    List<? extends TypeVariable> getTypeVariables();
}
