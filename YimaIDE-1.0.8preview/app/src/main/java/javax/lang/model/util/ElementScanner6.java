package javax.lang.model.util;

import java.util.Iterator;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.RecordComponentElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.element.VariableElement;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class ElementScanner6<R, P> extends AbstractElementVisitor6<R, P> {
    protected final R DEFAULT_VALUE;

    @Deprecated
    public ElementScanner6() {
        this.DEFAULT_VALUE = null;
    }

    public final R scan(Iterable<? extends Element> iterable, P p) {
        R rScan = this.DEFAULT_VALUE;
        Iterator<? extends Element> it = iterable.iterator();
        while (it.hasNext()) {
            rScan = scan(it.next(), p);
        }
        return rScan;
    }

    @Override // javax.lang.model.element.ElementVisitor
    public R visitExecutable(ExecutableElement executableElement, P p) {
        return scan(executableElement.getParameters(), p);
    }

    @Override // javax.lang.model.element.ElementVisitor
    public R visitPackage(PackageElement packageElement, P p) {
        return scan(packageElement.getEnclosedElements(), p);
    }

    @Override // javax.lang.model.util.AbstractElementVisitor6, javax.lang.model.element.ElementVisitor
    public R visitRecordComponent(RecordComponentElement recordComponentElement, P p) {
        return visitUnknown(recordComponentElement, p);
    }

    @Override // javax.lang.model.element.ElementVisitor
    public R visitType(TypeElement typeElement, P p) {
        return scan(typeElement.getEnclosedElements(), p);
    }

    @Override // javax.lang.model.element.ElementVisitor
    public R visitTypeParameter(TypeParameterElement typeParameterElement, P p) {
        return scan(typeParameterElement.getEnclosedElements(), p);
    }

    @Override // javax.lang.model.element.ElementVisitor
    public R visitVariable(VariableElement variableElement, P p) {
        return variableElement.getKind() != ElementKind.RESOURCE_VARIABLE ? scan(variableElement.getEnclosedElements(), p) : visitUnknown(variableElement, p);
    }

    @Deprecated
    public ElementScanner6(R r) {
        this.DEFAULT_VALUE = r;
    }

    public R scan(Element element, P p) {
        return (R) element.accept(this, p);
    }

    public final R scan(Element element) {
        return scan(element, (Object) null);
    }
}
