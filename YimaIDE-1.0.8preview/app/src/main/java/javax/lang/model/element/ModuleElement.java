package javax.lang.model.element;

import java.util.List;
import javax.lang.model.UnknownEntityException;
import javax.lang.model.type.TypeMirror;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface ModuleElement extends Element, QualifiedNameable {

    public interface Directive {
        <R, P> R accept(DirectiveVisitor<R, P> directiveVisitor, P p);

        DirectiveKind getKind();
    }

    public enum DirectiveKind {
        REQUIRES,
        EXPORTS,
        OPENS,
        USES,
        PROVIDES
    }

    public interface ExportsDirective extends Directive {
        PackageElement getPackage();

        List<? extends ModuleElement> getTargetModules();
    }

    public interface OpensDirective extends Directive {
        PackageElement getPackage();

        List<? extends ModuleElement> getTargetModules();
    }

    public interface ProvidesDirective extends Directive {
        List<? extends TypeElement> getImplementations();

        TypeElement getService();
    }

    public interface RequiresDirective extends Directive {
        ModuleElement getDependency();

        boolean isStatic();

        boolean isTransitive();
    }

    public interface UsesDirective extends Directive {
        TypeElement getService();
    }

    @Override // javax.lang.model.element.Element
    TypeMirror asType();

    List<? extends Directive> getDirectives();

    @Override // javax.lang.model.element.Element
    List<? extends Element> getEnclosedElements();

    @Override // javax.lang.model.element.Element
    Element getEnclosingElement();

    @Override // javax.lang.model.element.QualifiedNameable
    Name getQualifiedName();

    @Override // javax.lang.model.element.Element
    Name getSimpleName();

    boolean isOpen();

    boolean isUnnamed();

    public interface DirectiveVisitor<R, P> {
        default R visit(Directive directive) {
            return (R) directive.accept(this, null);
        }

        R visitExports(ExportsDirective exportsDirective, P p);

        R visitOpens(OpensDirective opensDirective, P p);

        R visitProvides(ProvidesDirective providesDirective, P p);

        R visitRequires(RequiresDirective requiresDirective, P p);

        /* JADX INFO: Thrown type has an unknown type hierarchy: javax.lang.model.UnknownEntityException */
        default R visitUnknown(Directive directive, P p) throws UnknownEntityException {
            throw new UnknownDirectiveException(directive, p);
        }

        R visitUses(UsesDirective usesDirective, P p);

        default R visit(Directive directive, P p) {
            return (R) directive.accept(this, p);
        }
    }
}
