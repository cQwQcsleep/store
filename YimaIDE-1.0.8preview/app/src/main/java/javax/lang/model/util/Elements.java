package javax.lang.model.util;

import java.io.Writer;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.lang.model.AnnotatedConstruct;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.ModuleElement;
import javax.lang.model.element.Name;
import javax.lang.model.element.NestingKind;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.RecordComponentElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.tools.JavaFileObject;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface Elements {

    /* JADX INFO: renamed from: javax.lang.model.util.Elements$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$javax$lang$model$element$ElementKind;

        static {
            int[] iArr = new int[ElementKind.values().length];
            $SwitchMap$javax$lang$model$element$ElementKind = iArr;
            try {
                iArr[ElementKind.PACKAGE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$javax$lang$model$element$ElementKind[ElementKind.MODULE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$javax$lang$model$element$ElementKind[ElementKind.OTHER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$javax$lang$model$element$ElementKind[ElementKind.ENUM_CONSTANT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public enum DocCommentKind {
        END_OF_LINE,
        TRADITIONAL
    }

    public enum Origin {
        EXPLICIT,
        MANDATED,
        SYNTHETIC;

        public boolean isDeclared() {
            return this != SYNTHETIC;
        }
    }

    List<? extends AnnotationMirror> getAllAnnotationMirrors(Element element);

    List<? extends Element> getAllMembers(TypeElement typeElement);

    default Set<? extends ModuleElement> getAllModuleElements() {
        return Collections.EMPTY_SET;
    }

    default Set<? extends PackageElement> getAllPackageElements(CharSequence charSequence) {
        Set<? extends ModuleElement> allModuleElements = getAllModuleElements();
        if (allModuleElements.isEmpty()) {
            PackageElement packageElement = getPackageElement(charSequence);
            return packageElement != null ? Collections.singleton(packageElement) : Collections.EMPTY_SET;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet(1);
        Iterator<? extends ModuleElement> it = allModuleElements.iterator();
        while (it.hasNext()) {
            PackageElement packageElement2 = getPackageElement(it.next(), charSequence);
            if (packageElement2 != null) {
                linkedHashSet.add(packageElement2);
            }
        }
        return Collections.unmodifiableSet(linkedHashSet);
    }

    default Set<? extends TypeElement> getAllTypeElements(CharSequence charSequence) {
        Set<? extends ModuleElement> allModuleElements = getAllModuleElements();
        if (allModuleElements.isEmpty()) {
            TypeElement typeElement = getTypeElement(charSequence);
            return typeElement != null ? Collections.singleton(typeElement) : Collections.EMPTY_SET;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet(1);
        Iterator<? extends ModuleElement> it = allModuleElements.iterator();
        while (it.hasNext()) {
            TypeElement typeElement2 = getTypeElement(it.next(), charSequence);
            if (typeElement2 != null) {
                linkedHashSet.add(typeElement2);
            }
        }
        return Collections.unmodifiableSet(linkedHashSet);
    }

    Name getBinaryName(TypeElement typeElement);

    String getConstantExpression(Object obj);

    String getDocComment(Element element);

    default DocCommentKind getDocCommentKind(Element element) {
        return null;
    }

    Map<? extends ExecutableElement, ? extends AnnotationValue> getElementValuesWithDefaults(AnnotationMirror annotationMirror);

    default TypeElement getEnumConstantBody(VariableElement variableElement) {
        if (AnonymousClass1.$SwitchMap$javax$lang$model$element$ElementKind[variableElement.getKind().ordinal()] != 4) {
            throw new IllegalArgumentException("Argument not an enum constant");
        }
        throw new UnsupportedOperationException();
    }

    default JavaFileObject getFileObjectOf(Element element) {
        throw new UnsupportedOperationException();
    }

    default ModuleElement getModuleElement(CharSequence charSequence) {
        return null;
    }

    default ModuleElement getModuleOf(Element element) {
        return null;
    }

    Name getName(CharSequence charSequence);

    default Origin getOrigin(Element element) {
        return Origin.EXPLICIT;
    }

    default TypeElement getOutermostTypeElement(Element element) {
        int i = AnonymousClass1.$SwitchMap$javax$lang$model$element$ElementKind[element.getKind().ordinal()];
        if (i == 1 || i == 2 || i == 3) {
            return null;
        }
        while (true) {
            List<TypeElement> listTypesIn = ElementFilter.typesIn(Collections.unmodifiableList(Arrays.asList(element)));
            if (!listTypesIn.isEmpty()) {
                TypeElement typeElement = listTypesIn.get(0);
                if (typeElement.getNestingKind() == NestingKind.TOP_LEVEL) {
                    return typeElement;
                }
            }
            element = element.getEnclosingElement();
        }
    }

    PackageElement getPackageElement(CharSequence charSequence);

    default PackageElement getPackageElement(ModuleElement moduleElement, CharSequence charSequence) {
        return null;
    }

    PackageElement getPackageOf(Element element);

    TypeElement getTypeElement(CharSequence charSequence);

    default TypeElement getTypeElement(ModuleElement moduleElement, CharSequence charSequence) {
        return null;
    }

    boolean hides(Element element, Element element2);

    default boolean isAutomaticModule(ModuleElement moduleElement) {
        return false;
    }

    default boolean isBridge(ExecutableElement executableElement) {
        return false;
    }

    default boolean isCanonicalConstructor(ExecutableElement executableElement) {
        return false;
    }

    default boolean isCompactConstructor(ExecutableElement executableElement) {
        return false;
    }

    boolean isDeprecated(Element element);

    boolean isFunctionalInterface(TypeElement typeElement);

    boolean overrides(ExecutableElement executableElement, ExecutableElement executableElement2, TypeElement typeElement);

    void printElements(Writer writer, Element... elementArr);

    default RecordComponentElement recordComponentFor(ExecutableElement executableElement) {
        if (executableElement.getEnclosingElement().getKind() != ElementKind.RECORD) {
            return null;
        }
        for (RecordComponentElement recordComponentElement : ElementFilter.recordComponentsIn(executableElement.getEnclosingElement().getEnclosedElements())) {
            if (Objects.equals(recordComponentElement.getAccessor(), executableElement)) {
                return recordComponentElement;
            }
        }
        return null;
    }

    default Origin getOrigin(AnnotatedConstruct annotatedConstruct, AnnotationMirror annotationMirror) {
        return Origin.EXPLICIT;
    }

    default Origin getOrigin(ModuleElement moduleElement, ModuleElement.Directive directive) {
        return Origin.EXPLICIT;
    }
}
