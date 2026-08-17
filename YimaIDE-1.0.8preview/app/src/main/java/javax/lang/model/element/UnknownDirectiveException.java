package javax.lang.model.element;

import javax.lang.model.UnknownEntityException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class UnknownDirectiveException extends UnknownEntityException {
    private static final long serialVersionUID = 269;
    private final transient ModuleElement.Directive directive;
    private final transient Object parameter;

    public UnknownDirectiveException(ModuleElement.Directive directive, Object obj) {
        super("Unknown directive: " + directive);
        this.directive = directive;
        this.parameter = obj;
    }

    public Object getArgument() {
        return this.parameter;
    }

    public ModuleElement.Directive getUnknownDirective() {
        return this.directive;
    }
}
