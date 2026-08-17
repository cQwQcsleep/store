package javax.annotation.processing;

import com.sun.org.apache.xalan.internal.templates.Constants;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class AbstractProcessor implements Processor {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private boolean initialized = false;
    protected ProcessingEnvironment processingEnv;

    private Set<String> arrayToSet(String[] strArr, boolean z, String str, String str2) {
        boolean z2;
        int iIndexOf;
        HashSet hashSet = new HashSet();
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            String strSubstring = strArr[i];
            if (!z || (iIndexOf = strSubstring.indexOf(47)) == -1) {
                z2 = false;
            } else {
                strSubstring = strSubstring.substring(iIndexOf + 1);
                z2 = true;
            }
            if (!hashSet.add(strSubstring) && !z2 && isInitialized()) {
                this.processingEnv.getMessager().printMessage(Diagnostic.Kind.WARNING, "Duplicate " + str + " ``" + strSubstring + "'' for processor " + getClass().getName() + " in its " + str2 + "annotation.");
            }
        }
        return Collections.unmodifiableSet(hashSet);
    }

    @Override // javax.annotation.processing.Processor
    public Iterable<? extends Completion> getCompletions(Element element, AnnotationMirror annotationMirror, ExecutableElement executableElement, String str) {
        return Collections.unmodifiableList(Arrays.asList(new Completion[0]));
    }

    @Override // javax.annotation.processing.Processor
    public Set<String> getSupportedAnnotationTypes() {
        SupportedAnnotationTypes supportedAnnotationTypes = (SupportedAnnotationTypes) getClass().getAnnotation(SupportedAnnotationTypes.class);
        boolean zIsInitialized = isInitialized();
        boolean z = false;
        if (supportedAnnotationTypes != null) {
            if (zIsInitialized && this.processingEnv.getSourceVersion().compareTo(SourceVersion.RELEASE_8) <= 0) {
                z = true;
            }
            return arrayToSet(supportedAnnotationTypes.value(), z, "annotation interface", "@SupportedAnnotationTypes");
        }
        if (zIsInitialized) {
            this.processingEnv.getMessager().printMessage(Diagnostic.Kind.WARNING, "No SupportedAnnotationTypes annotation found on " + getClass().getName() + ", returning an empty set.");
        }
        return Collections.unmodifiableSet(new HashSet(Arrays.asList(new String[0])));
    }

    @Override // javax.annotation.processing.Processor
    public Set<String> getSupportedOptions() {
        SupportedOptions supportedOptions = (SupportedOptions) getClass().getAnnotation(SupportedOptions.class);
        return supportedOptions == null ? Collections.unmodifiableSet(new HashSet(Arrays.asList(new String[0]))) : arrayToSet(supportedOptions.value(), false, "option value", "@SupportedOptions");
    }

    @Override // javax.annotation.processing.Processor
    public SourceVersion getSupportedSourceVersion() {
        SupportedSourceVersion supportedSourceVersion = (SupportedSourceVersion) getClass().getAnnotation(SupportedSourceVersion.class);
        if (supportedSourceVersion != null) {
            return supportedSourceVersion.value();
        }
        SourceVersion sourceVersion = SourceVersion.RELEASE_6;
        if (isInitialized()) {
            this.processingEnv.getMessager().printMessage(Diagnostic.Kind.WARNING, "No SupportedSourceVersion annotation found on " + getClass().getName() + ", returning " + sourceVersion + Constants.ATTRVAL_THIS);
        }
        return sourceVersion;
    }

    @Override // javax.annotation.processing.Processor
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        if (this.initialized) {
            throw new IllegalStateException("Cannot call init more than once.");
        }
        Objects.requireNonNull(processingEnvironment, "Tool provided null ProcessingEnvironment");
        this.processingEnv = processingEnvironment;
        this.initialized = true;
    }

    public synchronized boolean isInitialized() {
        return this.initialized;
    }

    @Override // javax.annotation.processing.Processor
    public abstract boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment);
}
