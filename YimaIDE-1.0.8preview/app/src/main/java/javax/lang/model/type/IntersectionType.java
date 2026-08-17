package javax.lang.model.type;

import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface IntersectionType extends TypeMirror {
    List<? extends TypeMirror> getBounds();
}
