package javax.lang.model.util;

import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.UnknownEntityException;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementVisitor;
import javax.lang.model.element.ModuleElement;
import javax.lang.model.element.RecordComponentElement;
import javax.lang.model.element.UnknownElementException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public abstract class AbstractElementVisitor6<R, P> implements ElementVisitor<R, P> {
    @Deprecated
    public AbstractElementVisitor6() {
    }

    @Override // javax.lang.model.element.ElementVisitor
    public final R visit(Element element) {
        return (R) element.accept(this, null);
    }

    @Override // javax.lang.model.element.ElementVisitor
    public R visitModule(ModuleElement moduleElement, P p) {
        return (R) super.visitModule(moduleElement, p);
    }

    @Override // javax.lang.model.element.ElementVisitor
    public R visitRecordComponent(RecordComponentElement recordComponentElement, P p) {
        return (R) super.visitRecordComponent(recordComponentElement, p);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.lang.model.UnknownEntityException */
    @Override // javax.lang.model.element.ElementVisitor
    public R visitUnknown(Element element, P p) throws UnknownEntityException {
        throw new UnknownElementException(element, p);
    }

    @Override // javax.lang.model.element.ElementVisitor
    public final R visit(Element element, P p) {
        return (R) element.accept(this, p);
    }
}
