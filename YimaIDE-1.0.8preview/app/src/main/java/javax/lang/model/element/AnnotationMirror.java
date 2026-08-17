package javax.lang.model.element;

import java.util.Map;
import javax.lang.model.type.DeclaredType;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface AnnotationMirror {
    DeclaredType getAnnotationType();

    Map<? extends ExecutableElement, ? extends AnnotationValue> getElementValues();
}
