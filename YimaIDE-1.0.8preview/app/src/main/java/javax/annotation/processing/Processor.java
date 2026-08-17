package javax.annotation.processing;

import java.util.Set;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface Processor {
    Iterable<? extends Completion> getCompletions(Element element, AnnotationMirror annotationMirror, ExecutableElement executableElement, String str);

    Set<String> getSupportedAnnotationTypes();

    Set<String> getSupportedOptions();

    SourceVersion getSupportedSourceVersion();

    void init(ProcessingEnvironment processingEnvironment);

    boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment);
}
