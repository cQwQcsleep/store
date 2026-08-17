package javax.lang.model.util;

import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.ErrorType;
import javax.lang.model.type.ExecutableType;
import javax.lang.model.type.NoType;
import javax.lang.model.type.NullType;
import javax.lang.model.type.PrimitiveType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.TypeVariable;
import javax.lang.model.type.WildcardType;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class SimpleTypeVisitor6<R, P> extends AbstractTypeVisitor6<R, P> {
    protected final R DEFAULT_VALUE;

    @Deprecated
    public SimpleTypeVisitor6() {
        this.DEFAULT_VALUE = null;
    }

    public R defaultAction(TypeMirror typeMirror, P p) {
        return this.DEFAULT_VALUE;
    }

    @Override // javax.lang.model.type.TypeVisitor
    public R visitArray(ArrayType arrayType, P p) {
        return defaultAction(arrayType, p);
    }

    @Override // javax.lang.model.type.TypeVisitor
    public R visitDeclared(DeclaredType declaredType, P p) {
        return defaultAction(declaredType, p);
    }

    @Override // javax.lang.model.type.TypeVisitor
    public R visitError(ErrorType errorType, P p) {
        return defaultAction(errorType, p);
    }

    @Override // javax.lang.model.type.TypeVisitor
    public R visitExecutable(ExecutableType executableType, P p) {
        return defaultAction(executableType, p);
    }

    @Override // javax.lang.model.type.TypeVisitor
    public R visitNoType(NoType noType, P p) {
        return defaultAction(noType, p);
    }

    @Override // javax.lang.model.type.TypeVisitor
    public R visitNull(NullType nullType, P p) {
        return defaultAction(nullType, p);
    }

    @Override // javax.lang.model.type.TypeVisitor
    public R visitPrimitive(PrimitiveType primitiveType, P p) {
        return defaultAction(primitiveType, p);
    }

    @Override // javax.lang.model.type.TypeVisitor
    public R visitTypeVariable(TypeVariable typeVariable, P p) {
        return defaultAction(typeVariable, p);
    }

    @Override // javax.lang.model.type.TypeVisitor
    public R visitWildcard(WildcardType wildcardType, P p) {
        return defaultAction(wildcardType, p);
    }

    @Deprecated
    public SimpleTypeVisitor6(R r) {
        this.DEFAULT_VALUE = r;
    }
}
