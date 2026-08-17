package javax.lang.model.type;

import java.util.List;
import javax.lang.model.element.Element;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface DeclaredType extends ReferenceType {
    Element asElement();

    TypeMirror getEnclosingType();

    List<? extends TypeMirror> getTypeArguments();
}
