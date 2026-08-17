package javax.lang.model.element;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface ElementVisitor<R, P> {
    default R visit(Element element) {
        return visit(element, null);
    }

    R visit(Element element, P p);

    R visitExecutable(ExecutableElement executableElement, P p);

    default R visitModule(ModuleElement moduleElement, P p) {
        return visitUnknown(moduleElement, p);
    }

    R visitPackage(PackageElement packageElement, P p);

    default R visitRecordComponent(RecordComponentElement recordComponentElement, P p) {
        return visitUnknown(recordComponentElement, p);
    }

    R visitType(TypeElement typeElement, P p);

    R visitTypeParameter(TypeParameterElement typeParameterElement, P p);

    R visitUnknown(Element element, P p);

    R visitVariable(VariableElement variableElement, P p);
}
