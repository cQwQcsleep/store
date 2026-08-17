package javax.lang.model.element;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public enum ElementKind {
    PACKAGE,
    ENUM,
    CLASS,
    ANNOTATION_TYPE,
    INTERFACE,
    ENUM_CONSTANT,
    FIELD,
    PARAMETER,
    LOCAL_VARIABLE,
    EXCEPTION_PARAMETER,
    METHOD,
    CONSTRUCTOR,
    STATIC_INIT,
    INSTANCE_INIT,
    TYPE_PARAMETER,
    OTHER,
    RESOURCE_VARIABLE,
    MODULE,
    RECORD,
    RECORD_COMPONENT,
    BINDING_VARIABLE;

    public boolean isClass() {
        return this == CLASS || this == ENUM || this == RECORD;
    }

    public boolean isDeclaredType() {
        return isClass() || isInterface();
    }

    public boolean isExecutable() {
        switch (ordinal()) {
            case 10:
            case 11:
            case 12:
            case 13:
                return true;
            default:
                return false;
        }
    }

    public boolean isField() {
        return this == FIELD || this == ENUM_CONSTANT;
    }

    public boolean isInitializer() {
        int iOrdinal = ordinal();
        return iOrdinal == 12 || iOrdinal == 13;
    }

    public boolean isInterface() {
        return this == INTERFACE || this == ANNOTATION_TYPE;
    }

    public boolean isVariable() {
        int iOrdinal = ordinal();
        if (iOrdinal == 16 || iOrdinal == 20) {
            return true;
        }
        switch (iOrdinal) {
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                return true;
            default:
                return false;
        }
    }
}
