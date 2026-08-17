package com.sun.tools.javac.processing;

import com.sun.tools.javac.code.Source;
import defpackage.aca;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ModuleElement;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementScanner14;
import javax.lang.model.util.Elements;
import nbjavac.ModuleWrapper;
import nbjavac.ObjectsWrapper;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class JavacRoundEnvironment implements RoundEnvironment {
    private static final String NOT_AN_ANNOTATION_TYPE = "The argument does not represent an annotation type: ";
    private final boolean allowModules;
    private final Elements eltUtils;
    private final boolean errorRaised;
    private final ProcessingEnvironment processingEnv;
    private final boolean processingOver;
    private final Set<? extends Element> rootElements;

    public JavacRoundEnvironment(boolean z, boolean z2, Set<? extends Element> set, JavacProcessingEnvironment javacProcessingEnvironment) {
        this.processingOver = z;
        this.errorRaised = z2;
        this.rootElements = set;
        this.processingEnv = javacProcessingEnvironment;
        this.allowModules = Source.Feature.MODULES.allowedInSource(javacProcessingEnvironment.source);
        this.eltUtils = javacProcessingEnvironment.getElementUtils();
    }

    private TypeElement annotationToElement(Class<? extends Annotation> cls) {
        String canonicalName = cls.getCanonicalName();
        TypeElement typeElement = this.eltUtils.getTypeElement(canonicalName);
        if (typeElement != null) {
            return typeElement;
        }
        if (!this.allowModules) {
            return null;
        }
        String str = (String) ObjectsWrapper.requireNonNullElse(ModuleWrapper.getModule(cls).getName(), "");
        Elements elements = this.eltUtils;
        return elements.getTypeElement(elements.getModuleElement(str), canonicalName);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Element mirrorAsElement(AnnotationMirror annotationMirror) {
        return annotationMirror.getAnnotationType().asElement();
    }

    private void throwIfNotAnnotation(TypeElement typeElement) {
        if (typeElement.getKind() == ElementKind.ANNOTATION_TYPE) {
            return;
        }
        aca.a(NOT_AN_ANNOTATION_TYPE, typeElement);
    }

    @Override // javax.annotation.processing.RoundEnvironment
    public boolean errorRaised() {
        return this.errorRaised;
    }

    @Override // javax.annotation.processing.RoundEnvironment
    public Set<? extends Element> getElementsAnnotatedWith(TypeElement typeElement) {
        throwIfNotAnnotation(typeElement);
        Set<Element> setScan = Collections.EMPTY_SET;
        AnnotationSetScanner annotationSetScanner = new AnnotationSetScanner(setScan);
        Iterator<? extends Element> it = this.rootElements.iterator();
        while (it.hasNext()) {
            setScan = annotationSetScanner.scan(it.next(), typeElement);
        }
        return setScan;
    }

    @Override // javax.annotation.processing.RoundEnvironment
    public Set<? extends Element> getElementsAnnotatedWithAny(Set<Class<? extends Annotation>> set) {
        ArrayList arrayList = new ArrayList(set.size());
        for (Class<? extends Annotation> cls : set) {
            throwIfNotAnnotation(cls);
            if (cls.getCanonicalName() != null) {
                arrayList.add(annotationToElement(cls));
            }
        }
        return getElementsAnnotatedWithAny((TypeElement[]) arrayList.toArray(new TypeElement[0]));
    }

    @Override // javax.annotation.processing.RoundEnvironment
    public Set<? extends Element> getRootElements() {
        return this.rootElements;
    }

    @Override // javax.annotation.processing.RoundEnvironment
    public boolean processingOver() {
        return this.processingOver;
    }

    public String toString() {
        return String.format("[errorRaised=%b, rootElements=%s, processingOver=%b]", Boolean.valueOf(this.errorRaised), this.rootElements, Boolean.valueOf(this.processingOver));
    }

    public class AnnotationSetMultiScanner extends ElementScanner14<Set<Element>, Set<TypeElement>> {
        private Set<Element> annotatedElements;

        public AnnotationSetMultiScanner(Set<Element> set) {
            super(set);
            this.annotatedElements = new LinkedHashSet();
        }

        @Override // javax.lang.model.util.ElementScanner6
        public Set<Element> scan(Element element, Set<TypeElement> set) {
            Iterator<? extends AnnotationMirror> it = JavacRoundEnvironment.this.eltUtils.getAllAnnotationMirrors(element).iterator();
            while (it.hasNext()) {
                if (set.contains(JavacRoundEnvironment.this.mirrorAsElement(it.next()))) {
                    this.annotatedElements.add(element);
                    break;
                }
            }
            element.accept(this, set);
            return this.annotatedElements;
        }

        @Override // javax.lang.model.util.ElementScanner9, javax.lang.model.util.AbstractElementVisitor6, javax.lang.model.element.ElementVisitor
        public Set<Element> visitModule(ModuleElement moduleElement, Set<TypeElement> set) {
            return this.annotatedElements;
        }

        @Override // javax.lang.model.util.ElementScanner6, javax.lang.model.element.ElementVisitor
        public Set<Element> visitPackage(PackageElement packageElement, Set<TypeElement> set) {
            return this.annotatedElements;
        }
    }

    public class AnnotationSetScanner extends ElementScanner14<Set<Element>, TypeElement> {
        private Set<Element> annotatedElements;

        public AnnotationSetScanner(Set<Element> set) {
            super(set);
            this.annotatedElements = new LinkedHashSet();
        }

        @Override // javax.lang.model.util.ElementScanner6
        public Set<Element> scan(Element element, TypeElement typeElement) {
            Iterator<? extends AnnotationMirror> it = JavacRoundEnvironment.this.eltUtils.getAllAnnotationMirrors(element).iterator();
            while (it.hasNext()) {
                if (typeElement.equals(JavacRoundEnvironment.this.mirrorAsElement(it.next()))) {
                    this.annotatedElements.add(element);
                    break;
                }
            }
            element.accept(this, typeElement);
            return this.annotatedElements;
        }

        @Override // javax.lang.model.util.ElementScanner9, javax.lang.model.util.AbstractElementVisitor6, javax.lang.model.element.ElementVisitor
        public Set<Element> visitModule(ModuleElement moduleElement, TypeElement typeElement) {
            return this.annotatedElements;
        }

        @Override // javax.lang.model.util.ElementScanner6, javax.lang.model.element.ElementVisitor
        public Set<Element> visitPackage(PackageElement packageElement, TypeElement typeElement) {
            return this.annotatedElements;
        }
    }

    private void throwIfNotAnnotation(Class<? extends Annotation> cls) {
        if (cls.isAnnotation()) {
            return;
        }
        aca.a(NOT_AN_ANNOTATION_TYPE, cls);
    }

    @Override // javax.annotation.processing.RoundEnvironment
    public Set<? extends Element> getElementsAnnotatedWith(Class<? extends Annotation> cls) {
        throwIfNotAnnotation(cls);
        if (cls.getCanonicalName() == null) {
            return Collections.EMPTY_SET;
        }
        TypeElement typeElementAnnotationToElement = annotationToElement(cls);
        if (typeElementAnnotationToElement == null) {
            return Collections.EMPTY_SET;
        }
        return getElementsAnnotatedWith(typeElementAnnotationToElement);
    }

    @Override // javax.annotation.processing.RoundEnvironment
    public Set<? extends Element> getElementsAnnotatedWithAny(TypeElement... typeElementArr) {
        LinkedHashSet linkedHashSet = new LinkedHashSet(typeElementArr.length);
        for (TypeElement typeElement : typeElementArr) {
            throwIfNotAnnotation(typeElement);
            linkedHashSet.add(typeElement);
        }
        Set<Element> setScan = Collections.EMPTY_SET;
        AnnotationSetMultiScanner annotationSetMultiScanner = new AnnotationSetMultiScanner(setScan);
        Iterator<? extends Element> it = this.rootElements.iterator();
        while (it.hasNext()) {
            setScan = annotationSetMultiScanner.scan(it.next(), (Set<TypeElement>) linkedHashSet);
        }
        return setScan;
    }
}
