package javax.lang.model.util;

import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.element.VariableElement;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class SimpleElementVisitor6<R, P> extends AbstractElementVisitor6<R, P> {
    protected final R DEFAULT_VALUE;

    @Deprecated
    public SimpleElementVisitor6() {
        this.DEFAULT_VALUE = null;
    }

    public R defaultAction(Element element, P p) {
        return this.DEFAULT_VALUE;
    }

    @Override // javax.lang.model.element.ElementVisitor
    public R visitExecutable(ExecutableElement executableElement, P p) {
        return defaultAction(executableElement, p);
    }

    @Override // javax.lang.model.element.ElementVisitor
    public R visitPackage(PackageElement packageElement, P p) {
        return defaultAction(packageElement, p);
    }

    @Override // javax.lang.model.element.ElementVisitor
    public R visitType(TypeElement typeElement, P p) {
        return defaultAction(typeElement, p);
    }

    @Override // javax.lang.model.element.ElementVisitor
    public R visitTypeParameter(TypeParameterElement typeParameterElement, P p) {
        return defaultAction(typeParameterElement, p);
    }

    @Override // javax.lang.model.element.ElementVisitor
    public R visitVariable(VariableElement variableElement, P p) {
        return variableElement.getKind() != ElementKind.RESOURCE_VARIABLE ? defaultAction(variableElement, p) : visitUnknown(variableElement, p);
    }

    @Deprecated
    public SimpleElementVisitor6(R r) {
        this.DEFAULT_VALUE = r;
    }
}
