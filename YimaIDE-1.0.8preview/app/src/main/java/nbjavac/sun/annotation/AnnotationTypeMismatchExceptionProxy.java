package nbjavac.sun.annotation;

import java.lang.annotation.AnnotationTypeMismatchException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
class AnnotationTypeMismatchExceptionProxy extends ExceptionProxy {
    private static final long serialVersionUID = 7844069490309503934L;
    private String foundType;
    private Method member;

    public AnnotationTypeMismatchExceptionProxy(String str) {
        this.foundType = str;
    }

    @Override // nbjavac.sun.annotation.ExceptionProxy
    public RuntimeException generateException() {
        return new AnnotationTypeMismatchException(this.member, this.foundType);
    }

    public AnnotationTypeMismatchExceptionProxy setMember(Method method) {
        this.member = method;
        return this;
    }
}
