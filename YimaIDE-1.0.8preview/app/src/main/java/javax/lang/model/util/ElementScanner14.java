package javax.lang.model.util;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Parameterizable;
import javax.lang.model.element.RecordComponentElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.TypeParameterElement;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
@SupportedSourceVersion(SourceVersion.RELEASE_26)
public class ElementScanner14<R, P> extends ElementScanner9<R, P> {
    public ElementScanner14() {
        super(null);
    }

    private List<? extends Element> createScanningList(Parameterizable parameterizable, List<? extends Element> list) {
        List<? extends TypeParameterElement> typeParameters = parameterizable.getTypeParameters();
        if (typeParameters.isEmpty()) {
            return list;
        }
        ArrayList arrayList = new ArrayList(typeParameters);
        arrayList.addAll(list);
        return arrayList;
    }

    @Override // javax.lang.model.util.ElementScanner6, javax.lang.model.element.ElementVisitor
    public R visitExecutable(ExecutableElement executableElement, P p) {
        return scan(createScanningList(executableElement, executableElement.getParameters()), p);
    }

    @Override // javax.lang.model.util.ElementScanner6, javax.lang.model.util.AbstractElementVisitor6, javax.lang.model.element.ElementVisitor
    public R visitRecordComponent(RecordComponentElement recordComponentElement, P p) {
        return scan(recordComponentElement.getEnclosedElements(), p);
    }

    @Override // javax.lang.model.util.ElementScanner6, javax.lang.model.element.ElementVisitor
    public R visitType(TypeElement typeElement, P p) {
        return scan(createScanningList(typeElement, typeElement.getEnclosedElements()), p);
    }

    public ElementScanner14(R r) {
        super(r);
    }
}
