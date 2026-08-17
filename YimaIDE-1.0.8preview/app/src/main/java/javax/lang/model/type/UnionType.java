package javax.lang.model.type;

import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface UnionType extends TypeMirror {
    List<? extends TypeMirror> getAlternatives();
}
