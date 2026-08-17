package javax.lang.model.util;

import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.element.VariableElement;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class ElementKindVisitor6<R, P> extends SimpleElementVisitor6<R, P> {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    /* JADX INFO: renamed from: javax.lang.model.util.ElementKindVisitor6$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$javax$lang$model$element$ElementKind;

        static {
            int[] iArr = new int[ElementKind.values().length];
            $SwitchMap$javax$lang$model$element$ElementKind = iArr;
            try {
                iArr[ElementKind.ANNOTATION_TYPE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$javax$lang$model$element$ElementKind[ElementKind.CLASS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$javax$lang$model$element$ElementKind[ElementKind.ENUM.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$javax$lang$model$element$ElementKind[ElementKind.INTERFACE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$javax$lang$model$element$ElementKind[ElementKind.RECORD.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$javax$lang$model$element$ElementKind[ElementKind.ENUM_CONSTANT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$javax$lang$model$element$ElementKind[ElementKind.EXCEPTION_PARAMETER.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$javax$lang$model$element$ElementKind[ElementKind.FIELD.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$javax$lang$model$element$ElementKind[ElementKind.LOCAL_VARIABLE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$javax$lang$model$element$ElementKind[ElementKind.PARAMETER.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$javax$lang$model$element$ElementKind[ElementKind.RESOURCE_VARIABLE.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$javax$lang$model$element$ElementKind[ElementKind.BINDING_VARIABLE.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$javax$lang$model$element$ElementKind[ElementKind.CONSTRUCTOR.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$javax$lang$model$element$ElementKind[ElementKind.INSTANCE_INIT.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$javax$lang$model$element$ElementKind[ElementKind.METHOD.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$javax$lang$model$element$ElementKind[ElementKind.STATIC_INIT.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
        }
    }

    @Deprecated
    public ElementKindVisitor6() {
        super(null);
    }

    @Override // javax.lang.model.util.SimpleElementVisitor6, javax.lang.model.element.ElementVisitor
    public R visitExecutable(ExecutableElement executableElement, P p) {
        ElementKind kind = executableElement.getKind();
        switch (AnonymousClass1.$SwitchMap$javax$lang$model$element$ElementKind[kind.ordinal()]) {
            case 13:
                return visitExecutableAsConstructor(executableElement, p);
            case 14:
                return visitExecutableAsInstanceInit(executableElement, p);
            case 15:
                return visitExecutableAsMethod(executableElement, p);
            case 16:
                return visitExecutableAsStaticInit(executableElement, p);
            default:
                md6.a("Bad kind ", kind, " for ExecutableElement", executableElement);
                return null;
        }
    }

    public R visitExecutableAsConstructor(ExecutableElement executableElement, P p) {
        return defaultAction(executableElement, p);
    }

    public R visitExecutableAsInstanceInit(ExecutableElement executableElement, P p) {
        return defaultAction(executableElement, p);
    }

    public R visitExecutableAsMethod(ExecutableElement executableElement, P p) {
        return defaultAction(executableElement, p);
    }

    public R visitExecutableAsStaticInit(ExecutableElement executableElement, P p) {
        return defaultAction(executableElement, p);
    }

    @Override // javax.lang.model.util.SimpleElementVisitor6, javax.lang.model.element.ElementVisitor
    public R visitPackage(PackageElement packageElement, P p) {
        return defaultAction(packageElement, p);
    }

    @Override // javax.lang.model.util.SimpleElementVisitor6, javax.lang.model.element.ElementVisitor
    public R visitType(TypeElement typeElement, P p) {
        ElementKind kind = typeElement.getKind();
        int i = AnonymousClass1.$SwitchMap$javax$lang$model$element$ElementKind[kind.ordinal()];
        if (i == 1) {
            return visitTypeAsAnnotationType(typeElement, p);
        }
        if (i == 2) {
            return visitTypeAsClass(typeElement, p);
        }
        if (i == 3) {
            return visitTypeAsEnum(typeElement, p);
        }
        if (i == 4) {
            return visitTypeAsInterface(typeElement, p);
        }
        if (i == 5) {
            return visitTypeAsRecord(typeElement, p);
        }
        md6.a("Bad kind ", kind, " for TypeElement", typeElement);
        return null;
    }

    public R visitTypeAsAnnotationType(TypeElement typeElement, P p) {
        return defaultAction(typeElement, p);
    }

    public R visitTypeAsClass(TypeElement typeElement, P p) {
        return defaultAction(typeElement, p);
    }

    public R visitTypeAsEnum(TypeElement typeElement, P p) {
        return defaultAction(typeElement, p);
    }

    public R visitTypeAsInterface(TypeElement typeElement, P p) {
        return defaultAction(typeElement, p);
    }

    public R visitTypeAsRecord(TypeElement typeElement, P p) {
        return visitUnknown(typeElement, p);
    }

    @Override // javax.lang.model.util.SimpleElementVisitor6, javax.lang.model.element.ElementVisitor
    public R visitTypeParameter(TypeParameterElement typeParameterElement, P p) {
        return defaultAction(typeParameterElement, p);
    }

    @Override // javax.lang.model.util.SimpleElementVisitor6, javax.lang.model.element.ElementVisitor
    public R visitVariable(VariableElement variableElement, P p) {
        ElementKind kind = variableElement.getKind();
        switch (AnonymousClass1.$SwitchMap$javax$lang$model$element$ElementKind[kind.ordinal()]) {
            case 6:
                return visitVariableAsEnumConstant(variableElement, p);
            case 7:
                return visitVariableAsExceptionParameter(variableElement, p);
            case 8:
                return visitVariableAsField(variableElement, p);
            case 9:
                return visitVariableAsLocalVariable(variableElement, p);
            case 10:
                return visitVariableAsParameter(variableElement, p);
            case 11:
                return visitVariableAsResourceVariable(variableElement, p);
            case 12:
                return visitVariableAsBindingVariable(variableElement, p);
            default:
                md6.a("Bad kind ", kind, " for VariableElement", variableElement);
                return null;
        }
    }

    public R visitVariableAsBindingVariable(VariableElement variableElement, P p) {
        return visitUnknown(variableElement, p);
    }

    public R visitVariableAsEnumConstant(VariableElement variableElement, P p) {
        return defaultAction(variableElement, p);
    }

    public R visitVariableAsExceptionParameter(VariableElement variableElement, P p) {
        return defaultAction(variableElement, p);
    }

    public R visitVariableAsField(VariableElement variableElement, P p) {
        return defaultAction(variableElement, p);
    }

    public R visitVariableAsLocalVariable(VariableElement variableElement, P p) {
        return defaultAction(variableElement, p);
    }

    public R visitVariableAsParameter(VariableElement variableElement, P p) {
        return defaultAction(variableElement, p);
    }

    public R visitVariableAsResourceVariable(VariableElement variableElement, P p) {
        return visitUnknown(variableElement, p);
    }

    @Deprecated
    public ElementKindVisitor6(R r) {
        super(r);
    }
}
