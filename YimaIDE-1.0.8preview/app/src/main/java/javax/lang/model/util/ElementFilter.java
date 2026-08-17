package javax.lang.model.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.ModuleElement;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.RecordComponentElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ElementFilter {
    private static final Set<ElementKind> CONSTRUCTOR_KIND = Collections.unmodifiableSet(EnumSet.of(ElementKind.CONSTRUCTOR));
    private static final Set<ElementKind> FIELD_KINDS = Collections.unmodifiableSet(EnumSet.of(ElementKind.FIELD, ElementKind.ENUM_CONSTANT));
    private static final Set<ElementKind> METHOD_KIND = Collections.unmodifiableSet(EnumSet.of(ElementKind.METHOD));
    private static final Set<ElementKind> PACKAGE_KIND = Collections.unmodifiableSet(EnumSet.of(ElementKind.PACKAGE));
    private static final Set<ElementKind> MODULE_KIND = Collections.unmodifiableSet(EnumSet.of(ElementKind.MODULE));
    private static final Set<ElementKind> TYPE_KINDS = Collections.unmodifiableSet(EnumSet.of(ElementKind.CLASS, ElementKind.ENUM, ElementKind.INTERFACE, ElementKind.RECORD, ElementKind.ANNOTATION_TYPE));
    private static final Set<ElementKind> RECORD_COMPONENT_KIND = Collections.unmodifiableSet(new HashSet(Arrays.asList(ElementKind.RECORD_COMPONENT)));

    private ElementFilter() {
    }

    public static List<ExecutableElement> constructorsIn(Iterable<? extends Element> iterable) {
        return listFilter(iterable, CONSTRUCTOR_KIND, ExecutableElement.class);
    }

    public static List<ModuleElement.ExportsDirective> exportsIn(Iterable<? extends ModuleElement.Directive> iterable) {
        return listFilter(iterable, ModuleElement.DirectiveKind.EXPORTS, ModuleElement.ExportsDirective.class);
    }

    public static List<VariableElement> fieldsIn(Iterable<? extends Element> iterable) {
        return listFilter(iterable, FIELD_KINDS, VariableElement.class);
    }

    private static <E extends Element> List<E> listFilter(Iterable<? extends Element> iterable, Set<ElementKind> set, Class<E> cls) {
        ArrayList arrayList = new ArrayList();
        for (Element element : iterable) {
            if (set.contains(element.getKind())) {
                arrayList.add(cls.cast(element));
            }
        }
        return arrayList;
    }

    public static List<ExecutableElement> methodsIn(Iterable<? extends Element> iterable) {
        return listFilter(iterable, METHOD_KIND, ExecutableElement.class);
    }

    public static List<ModuleElement> modulesIn(Iterable<? extends Element> iterable) {
        return listFilter(iterable, MODULE_KIND, ModuleElement.class);
    }

    public static List<ModuleElement.OpensDirective> opensIn(Iterable<? extends ModuleElement.Directive> iterable) {
        return listFilter(iterable, ModuleElement.DirectiveKind.OPENS, ModuleElement.OpensDirective.class);
    }

    public static List<PackageElement> packagesIn(Iterable<? extends Element> iterable) {
        return listFilter(iterable, PACKAGE_KIND, PackageElement.class);
    }

    public static List<ModuleElement.ProvidesDirective> providesIn(Iterable<? extends ModuleElement.Directive> iterable) {
        return listFilter(iterable, ModuleElement.DirectiveKind.PROVIDES, ModuleElement.ProvidesDirective.class);
    }

    public static List<RecordComponentElement> recordComponentsIn(Iterable<? extends Element> iterable) {
        return listFilter(iterable, RECORD_COMPONENT_KIND, RecordComponentElement.class);
    }

    public static List<ModuleElement.RequiresDirective> requiresIn(Iterable<? extends ModuleElement.Directive> iterable) {
        return listFilter(iterable, ModuleElement.DirectiveKind.REQUIRES, ModuleElement.RequiresDirective.class);
    }

    private static <E extends Element> Set<E> setFilter(Set<? extends Element> set, Set<ElementKind> set2, Class<E> cls) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (Element element : set) {
            if (set2.contains(element.getKind())) {
                linkedHashSet.add(cls.cast(element));
            }
        }
        return linkedHashSet;
    }

    public static List<TypeElement> typesIn(Iterable<? extends Element> iterable) {
        return listFilter(iterable, TYPE_KINDS, TypeElement.class);
    }

    public static List<ModuleElement.UsesDirective> usesIn(Iterable<? extends ModuleElement.Directive> iterable) {
        return listFilter(iterable, ModuleElement.DirectiveKind.USES, ModuleElement.UsesDirective.class);
    }

    public static Set<ExecutableElement> constructorsIn(Set<? extends Element> set) {
        return setFilter(set, CONSTRUCTOR_KIND, ExecutableElement.class);
    }

    public static Set<VariableElement> fieldsIn(Set<? extends Element> set) {
        return setFilter(set, FIELD_KINDS, VariableElement.class);
    }

    public static Set<ExecutableElement> methodsIn(Set<? extends Element> set) {
        return setFilter(set, METHOD_KIND, ExecutableElement.class);
    }

    public static Set<ModuleElement> modulesIn(Set<? extends Element> set) {
        return setFilter(set, MODULE_KIND, ModuleElement.class);
    }

    public static Set<PackageElement> packagesIn(Set<? extends Element> set) {
        return setFilter(set, PACKAGE_KIND, PackageElement.class);
    }

    public static Set<RecordComponentElement> recordComponentsIn(Set<? extends Element> set) {
        return setFilter(set, RECORD_COMPONENT_KIND, RecordComponentElement.class);
    }

    public static Set<TypeElement> typesIn(Set<? extends Element> set) {
        return setFilter(set, TYPE_KINDS, TypeElement.class);
    }

    private static <D extends ModuleElement.Directive> List<D> listFilter(Iterable<? extends ModuleElement.Directive> iterable, ModuleElement.DirectiveKind directiveKind, Class<D> cls) {
        ArrayList arrayList = new ArrayList();
        for (ModuleElement.Directive directive : iterable) {
            if (directive.getKind() == directiveKind) {
                arrayList.add(cls.cast(directive));
            }
        }
        return arrayList;
    }
}
