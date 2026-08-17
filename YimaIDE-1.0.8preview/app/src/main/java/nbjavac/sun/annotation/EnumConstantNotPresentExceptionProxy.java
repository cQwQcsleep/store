package nbjavac.sun.annotation;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class EnumConstantNotPresentExceptionProxy extends ExceptionProxy {
    private static final long serialVersionUID = -604662101303187330L;
    String constName;
    Class<? extends Enum<?>> enumType;

    public EnumConstantNotPresentExceptionProxy(Class<? extends Enum<?>> cls, String str) {
        this.enumType = cls;
        this.constName = str;
    }

    public RuntimeException generateException() {
        return new EnumConstantNotPresentException(this.enumType, this.constName);
    }
}
