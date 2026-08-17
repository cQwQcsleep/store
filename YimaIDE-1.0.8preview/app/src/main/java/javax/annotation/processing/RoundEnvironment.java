package javax.annotation.processing;

import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface RoundEnvironment {
    boolean errorRaised();

    Set<? extends Element> getElementsAnnotatedWith(Class<? extends Annotation> cls);

    Set<? extends Element> getElementsAnnotatedWith(TypeElement typeElement);

    default Set<? extends Element> getElementsAnnotatedWithAny(Set<Class<? extends Annotation>> set) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator<Class<? extends Annotation>> it = set.iterator();
        while (it.hasNext()) {
            linkedHashSet.addAll(getElementsAnnotatedWith(it.next()));
        }
        return Collections.unmodifiableSet(linkedHashSet);
    }

    Set<? extends Element> getRootElements();

    boolean processingOver();

    default Set<? extends Element> getElementsAnnotatedWithAny(TypeElement... typeElementArr) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (TypeElement typeElement : typeElementArr) {
            linkedHashSet.addAll(getElementsAnnotatedWith(typeElement));
        }
        return Collections.unmodifiableSet(linkedHashSet);
    }
}
