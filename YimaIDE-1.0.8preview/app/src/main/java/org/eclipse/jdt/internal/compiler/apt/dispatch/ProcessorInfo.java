package org.eclipse.jdt.internal.compiler.apt.dispatch;

import java.util.Iterator;
import java.util.Set;
import java.util.regex.Pattern;
import javax.annotation.processing.Processor;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class ProcessorInfo {
    private boolean _hasBeenCalled = false;
    final Processor _processor;
    private final Pattern _supportedAnnotationTypesPattern;
    final Set<String> _supportedOptions;
    final SourceVersion _supportedSourceVersion;
    private final boolean _supportsStar;

    public ProcessorInfo(Processor processor) {
        this._processor = processor;
        boolean zEquals = false;
        this._supportedSourceVersion = processor.getSupportedSourceVersion();
        this._supportedOptions = processor.getSupportedOptions();
        Set supportedAnnotationTypes = processor.getSupportedAnnotationTypes();
        if (supportedAnnotationTypes == null || supportedAnnotationTypes.isEmpty()) {
            this._supportedAnnotationTypesPattern = null;
        } else {
            StringBuilder sb = new StringBuilder();
            Iterator it = supportedAnnotationTypes.iterator();
            while (true) {
                String str = (String) it.next();
                zEquals |= "*".equals(str);
                sb.append(str.replace(".", "\\.").replace("*", ".*"));
                if (!it.hasNext()) {
                    break;
                } else {
                    sb.append('|');
                }
            }
            this._supportedAnnotationTypesPattern = Pattern.compile(sb.toString());
        }
        this._supportsStar = zEquals;
    }

    public boolean computeSupportedAnnotations(Set<TypeElement> set, Set<TypeElement> set2) {
        if (set != null && !set.isEmpty() && this._supportedAnnotationTypesPattern != null) {
            for (TypeElement typeElement : set) {
                if (this._supportedAnnotationTypesPattern.matcher(typeElement.getQualifiedName().toString()).matches()) {
                    set2.add(typeElement);
                }
            }
        }
        boolean z = this._hasBeenCalled || this._supportsStar || !set2.isEmpty();
        this._hasBeenCalled |= z;
        return z;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this._processor.getClass().equals(((ProcessorInfo) obj)._processor.getClass());
    }

    public String getSupportedAnnotationTypesAsString() {
        StringBuilder sb = new StringBuilder("[");
        Iterator it = this._processor.getSupportedAnnotationTypes().iterator();
        boolean zHasNext = it.hasNext();
        while (zHasNext) {
            sb.append((String) it.next());
            zHasNext = it.hasNext();
            if (zHasNext) {
                sb.append(',');
            }
        }
        sb.append(']');
        return sb.toString();
    }

    public int hashCode() {
        return this._processor.getClass().hashCode();
    }

    public void reset() {
        this._hasBeenCalled = false;
    }

    public boolean supportsStar() {
        return this._supportsStar;
    }

    public String toString() {
        return this._processor.getClass().getName();
    }
}
