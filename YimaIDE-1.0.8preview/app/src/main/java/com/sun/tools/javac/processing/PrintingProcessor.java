package com.sun.tools.javac.processing;

import com.sun.tools.javac.processing.PrintingProcessor;
import com.sun.tools.javac.util.StringUtils;
import defpackage.hh5;
import defpackage.s22;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.ModuleElement;
import javax.lang.model.element.NestingKind;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.Parameterizable;
import javax.lang.model.element.QualifiedNameable;
import javax.lang.model.element.RecordComponentElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.util.Elements;
import javax.lang.model.util.SimpleAnnotationValueVisitor14;
import javax.lang.model.util.SimpleElementVisitor14;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
@SupportedSourceVersion(SourceVersion.RELEASE_26)
@SupportedAnnotationTypes({"*"})
public class PrintingProcessor extends AbstractProcessor {
    PrintWriter writer = new PrintWriter(System.out);

    /* JADX INFO: renamed from: com.sun.tools.javac.processing.PrintingProcessor$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$javax$lang$model$element$ElementKind;

        static {
            int[] iArr = new int[ElementKind.values().length];
            $SwitchMap$javax$lang$model$element$ElementKind = iArr;
            try {
                iArr[ElementKind.CONSTRUCTOR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$javax$lang$model$element$ElementKind[ElementKind.METHOD.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$javax$lang$model$element$ElementKind[ElementKind.ANNOTATION_TYPE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$javax$lang$model$element$ElementKind[ElementKind.INTERFACE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$javax$lang$model$element$ElementKind[ElementKind.ENUM.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$javax$lang$model$element$ElementKind[ElementKind.RECORD.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$javax$lang$model$element$ElementKind[ElementKind.FIELD.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public void print(Element element) {
        ((PrintingElementVisitor) new PrintingElementVisitor(this.writer, this.processingEnv.getElementUtils()).visit(element)).flush();
    }

    @Override // javax.annotation.processing.AbstractProcessor, javax.annotation.processing.Processor
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        Iterator<? extends Element> it = roundEnvironment.getRootElements().iterator();
        while (it.hasNext()) {
            print(it.next());
        }
        return true;
    }

    public void setWriter(Writer writer) {
        this.writer = new PrintWriter(writer);
    }

    public static class PrintingElementVisitor extends SimpleElementVisitor14<PrintingElementVisitor, Boolean> {
        private static final String[] spaces = {"", "  ", "    ", "      ", "        ", "          ", "            ", "              ", "                ", "                  ", "                    "};
        final Elements elementUtils;
        int indentation = 0;
        final PrintWriter writer;

        public static class PrintDirective implements ModuleElement.DirectiveVisitor<Void, Void> {
            private final PrintWriter writer;

            public PrintDirective(PrintWriter printWriter) {
                this.writer = printWriter;
            }

            private void printModuleList(List<? extends ModuleElement> list) {
                if (list != null) {
                    this.writer.print(" to ");
                    printNameableList(list);
                }
            }

            private void printNameableList(List<? extends QualifiedNameable> list) {
                this.writer.print((String) list.stream().map(new Function() { // from class: g8b
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return ((QualifiedNameable) obj).getQualifiedName();
                    }
                }).collect(Collectors.joining(", ")));
            }

            @Override // javax.lang.model.element.ModuleElement.DirectiveVisitor
            public Void visitExports(ModuleElement.ExportsDirective exportsDirective, Void r3) {
                this.writer.print("exports ");
                this.writer.print(exportsDirective.getPackage().getQualifiedName());
                printModuleList(exportsDirective.getTargetModules());
                return null;
            }

            @Override // javax.lang.model.element.ModuleElement.DirectiveVisitor
            public Void visitOpens(ModuleElement.OpensDirective opensDirective, Void r3) {
                this.writer.print("opens ");
                this.writer.print(opensDirective.getPackage().getQualifiedName());
                printModuleList(opensDirective.getTargetModules());
                return null;
            }

            @Override // javax.lang.model.element.ModuleElement.DirectiveVisitor
            public Void visitProvides(ModuleElement.ProvidesDirective providesDirective, Void r3) {
                this.writer.print("provides ");
                this.writer.print(providesDirective.getService().getQualifiedName());
                this.writer.print(" with ");
                printNameableList(providesDirective.getImplementations());
                return null;
            }

            @Override // javax.lang.model.element.ModuleElement.DirectiveVisitor
            public Void visitRequires(ModuleElement.RequiresDirective requiresDirective, Void r3) {
                this.writer.print("requires ");
                if (requiresDirective.isStatic()) {
                    this.writer.print("static ");
                }
                if (requiresDirective.isTransitive()) {
                    this.writer.print("transitive ");
                }
                this.writer.print(requiresDirective.getDependency().getQualifiedName());
                return null;
            }

            @Override // javax.lang.model.element.ModuleElement.DirectiveVisitor
            public Void visitUses(ModuleElement.UsesDirective usesDirective, Void r3) {
                this.writer.print("uses ");
                this.writer.print(usesDirective.getService().getQualifiedName());
                return null;
            }
        }

        public PrintingElementVisitor(Writer writer, Elements elements) {
            this.writer = new PrintWriter(writer);
            this.elementUtils = elements;
        }

        public static /* synthetic */ String a(PrintingElementVisitor printingElementVisitor, TypeParameterElement typeParameterElement) {
            return printingElementVisitor.annotationsToString(typeParameterElement) + typeParameterElement.toString() + printingElementVisitor.printTypeVariableBoundsIfNeeded(typeParameterElement);
        }

        private String annotationsToString(Element element) {
            List<? extends AnnotationMirror> annotationMirrors = element.getAnnotationMirrors();
            return annotationMirrors.isEmpty() ? "" : (String) annotationMirrors.stream().map(new Function() { // from class: b8b
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((AnnotationMirror) obj).toString();
                }
            }).collect(Collectors.joining(" ", "", " "));
        }

        public static /* synthetic */ String c(PrintingElementVisitor printingElementVisitor, RecordComponentElement recordComponentElement) {
            return printingElementVisitor.annotationsToString(recordComponentElement) + recordComponentElement.asType().toString() + " " + recordComponentElement.getSimpleName();
        }

        public static /* synthetic */ boolean e(PrintingElementVisitor printingElementVisitor, Element element) {
            return printingElementVisitor.elementUtils.getOrigin(element) == Elements.Origin.EXPLICIT;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void indent() {
            int i = this.indentation;
            if (i < 0) {
                return;
            }
            int length = spaces.length - 1;
            while (true) {
                PrintWriter printWriter = this.writer;
                if (i <= length) {
                    printWriter.print(spaces[i]);
                    return;
                } else {
                    printWriter.print(spaces[length]);
                    i -= length;
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isImportantType(TypeMirror typeMirror) {
            if (!typeMirror.getAnnotationMirrors().isEmpty()) {
                return true;
            }
            TypeElement typeElement = (TypeElement) ((DeclaredType) typeMirror).asElement();
            return (typeElement.getKind().isClass() && typeElement.getSuperclass().getKind() == TypeKind.NONE) ? false : true;
        }

        private void printAnnotations(Element element) {
            for (AnnotationMirror annotationMirror : element.getAnnotationMirrors()) {
                if (!printedContainerAnnotation(element, annotationMirror)) {
                    indent();
                    this.writer.println(annotationMirror);
                }
            }
        }

        private void printDirective(ModuleElement.Directive directive) {
            indent();
            new PrintDirective(this.writer).visit(directive);
            this.writer.println(";");
        }

        private void printDocComment(Element element) {
            String docComment = this.elementUtils.getDocComment(element);
            if (docComment != null) {
                StringTokenizer stringTokenizer = new StringTokenizer(docComment, "\n\r");
                indent();
                this.writer.println("/**");
                while (stringTokenizer.hasMoreTokens()) {
                    indent();
                    this.writer.print(" *");
                    this.writer.println(stringTokenizer.nextToken());
                }
                indent();
                this.writer.println(" */");
            }
        }

        private void printFormalTypeParameters(Parameterizable parameterizable, boolean z) {
            List<? extends TypeParameterElement> typeParameters = parameterizable.getTypeParameters();
            if (typeParameters.isEmpty()) {
                return;
            }
            this.writer.print((String) typeParameters.stream().map(new Function() { // from class: c8b
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return PrintingProcessor.PrintingElementVisitor.a(this.b, (TypeParameterElement) obj);
                }
            }).collect(Collectors.joining(", ", "<", ">")));
            if (z) {
                this.writer.print(" ");
            }
        }

        private void printInterfaces(TypeElement typeElement) {
            ElementKind kind = typeElement.getKind();
            if (kind != ElementKind.ANNOTATION_TYPE) {
                List<? extends TypeMirror> interfaces = typeElement.getInterfaces();
                if (interfaces.isEmpty()) {
                    return;
                }
                this.writer.print(kind.isClass() ? " implements " : " extends ");
                this.writer.print((String) interfaces.stream().map(new Function() { // from class: y7b
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return ((TypeMirror) obj).toString();
                    }
                }).collect(Collectors.joining(", ")));
            }
        }

        private void printModifiers(Element element) {
            ElementKind kind = element.getKind();
            if (kind == ElementKind.PARAMETER || kind == ElementKind.RECORD_COMPONENT) {
                this.writer.print(annotationsToString(element));
            } else {
                printAnnotations(element);
                indent();
            }
            if (kind == ElementKind.ENUM_CONSTANT || kind == ElementKind.RECORD_COMPONENT) {
                return;
            }
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            linkedHashSet.addAll(element.getModifiers());
            switch (AnonymousClass1.$SwitchMap$javax$lang$model$element$ElementKind[kind.ordinal()]) {
                case 2:
                case 7:
                    Element enclosingElement = element.getEnclosingElement();
                    if (enclosingElement != null && enclosingElement.getKind().isInterface()) {
                        linkedHashSet.remove(Modifier.PUBLIC);
                        linkedHashSet.remove(Modifier.ABSTRACT);
                        linkedHashSet.remove(Modifier.STATIC);
                        linkedHashSet.remove(Modifier.FINAL);
                    }
                    break;
                case 3:
                case 4:
                    linkedHashSet.remove(Modifier.ABSTRACT);
                    break;
                case 5:
                    linkedHashSet.remove(Modifier.FINAL);
                    linkedHashSet.remove(Modifier.ABSTRACT);
                    linkedHashSet.remove(Modifier.SEALED);
                    break;
                case 6:
                    linkedHashSet.remove(Modifier.FINAL);
                    break;
            }
            if (linkedHashSet.isEmpty()) {
                return;
            }
            this.writer.print((String) linkedHashSet.stream().map(new hh5()).collect(Collectors.joining(" ", "", " ")));
        }

        private void printParameters(ExecutableElement executableElement) {
            List<? extends VariableElement> parameters = executableElement.getParameters();
            int size = parameters.size();
            if (size != 0) {
                if (size == 1) {
                    for (VariableElement variableElement : parameters) {
                        printModifiers(variableElement);
                        if (executableElement.isVarArgs()) {
                            TypeMirror typeMirrorAsType = variableElement.asType();
                            if (typeMirrorAsType.getKind() != TypeKind.ARRAY) {
                                s22.a("Var-args parameter is not an array type: ", typeMirrorAsType);
                                return;
                            } else {
                                this.writer.print(((ArrayType) ArrayType.class.cast(typeMirrorAsType)).getComponentType());
                                this.writer.print("...");
                            }
                        } else {
                            this.writer.print(variableElement.asType());
                        }
                        this.writer.print(" " + variableElement.getSimpleName());
                    }
                    return;
                }
                int i = 1;
                for (VariableElement variableElement2 : parameters) {
                    if (i == 2) {
                        this.indentation++;
                    }
                    if (i > 1) {
                        indent();
                    }
                    printModifiers(variableElement2);
                    if (i == size && executableElement.isVarArgs()) {
                        TypeMirror typeMirrorAsType2 = variableElement2.asType();
                        if (typeMirrorAsType2.getKind() != TypeKind.ARRAY) {
                            s22.a("Var-args parameter is not an array type: ", typeMirrorAsType2);
                            return;
                        } else {
                            this.writer.print(((ArrayType) ArrayType.class.cast(typeMirrorAsType2)).getComponentType());
                            this.writer.print("...");
                        }
                    } else {
                        this.writer.print(variableElement2.asType());
                    }
                    this.writer.print(" " + variableElement2.getSimpleName());
                    if (i < size) {
                        this.writer.println(",");
                    }
                    i++;
                }
                if (parameters.size() >= 2) {
                    this.indentation--;
                }
            }
        }

        private void printPermittedSubclasses(TypeElement typeElement) {
            if (typeElement.getKind() == ElementKind.ENUM) {
                return;
            }
            List<? extends TypeMirror> permittedSubclasses = typeElement.getPermittedSubclasses();
            if (permittedSubclasses.isEmpty()) {
                return;
            }
            this.writer.print(" permits ");
            this.writer.print((String) permittedSubclasses.stream().map(new Function() { // from class: f8b
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((TypeMirror) obj).toString();
                }
            }).collect(Collectors.joining(", ")));
        }

        private void printThrows(ExecutableElement executableElement) {
            List<? extends TypeMirror> thrownTypes = executableElement.getThrownTypes();
            int size = thrownTypes.size();
            if (size != 0) {
                this.writer.print(" throws");
                int i = 1;
                for (TypeMirror typeMirror : thrownTypes) {
                    if (i == 1) {
                        this.writer.print(" ");
                    }
                    if (i == 2) {
                        this.indentation++;
                    }
                    if (i >= 2) {
                        indent();
                    }
                    this.writer.print(typeMirror);
                    if (i != size) {
                        this.writer.println(", ");
                    }
                    i++;
                }
                if (size >= 2) {
                    this.indentation--;
                }
            }
        }

        private String printTypeVariableBoundsIfNeeded(TypeParameterElement typeParameterElement) {
            List list = (List) typeParameterElement.getBounds().stream().filter(new Predicate() { // from class: d8b
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return this.b.isImportantType((TypeMirror) obj);
                }
            }).collect(Collectors.toList());
            if (list.isEmpty()) {
                return "";
            }
            return " extends " + ((String) list.stream().map(new Function() { // from class: e8b
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((TypeMirror) obj).toString();
                }
            }).collect(Collectors.joining(" & ")));
        }

        private boolean printedContainerAnnotation(Element element, AnnotationMirror annotationMirror) {
            if (this.elementUtils.getOrigin(element, annotationMirror) == Elements.Origin.MANDATED) {
                Set<Map.Entry<? extends ExecutableElement, ? extends AnnotationValue>> setEntrySet = annotationMirror.getElementValues().entrySet();
                if (setEntrySet.size() == 1) {
                    Element elementAsElement = annotationMirror.getAnnotationType().asElement();
                    AnnotationValue value = setEntrySet.iterator().next().getValue();
                    if (elementAsElement.getKind() == ElementKind.ANNOTATION_TYPE) {
                        List<ExecutableElement> listMethodsIn = ElementFilter.methodsIn(elementAsElement.getEnclosedElements());
                        if (listMethodsIn.size() == 1) {
                            ExecutableElement executableElement = listMethodsIn.get(0);
                            TypeMirror returnType = executableElement.getReturnType();
                            if ("value".equals(executableElement.getSimpleName().toString()) && returnType.getKind() == TypeKind.ARRAY) {
                                return ((Boolean) new SimpleAnnotationValueVisitor14<Boolean, Void>(Boolean.FALSE) { // from class: com.sun.tools.javac.processing.PrintingProcessor.PrintingElementVisitor.2
                                    public Boolean visitArray(List<? extends AnnotationValue> list, Void r3) {
                                        if (list.size() < 2) {
                                            return Boolean.FALSE;
                                        }
                                        for (AnnotationValue annotationValue : list) {
                                            PrintingElementVisitor.this.indent();
                                            PrintingElementVisitor.this.writer.println(annotationValue.toString());
                                        }
                                        return Boolean.TRUE;
                                    }

                                    public /* bridge */ /* synthetic */ Object visitArray(List list, Object obj) {
                                        return visitArray((List<? extends AnnotationValue>) list, (Void) obj);
                                    }
                                }.visit(value)).booleanValue();
                            }
                        }
                    }
                }
            }
            return false;
        }

        public PrintingElementVisitor defaultAction(Element element, Boolean bool) {
            if (bool != null && bool.booleanValue()) {
                this.writer.println();
            }
            printDocComment(element);
            printModifiers(element);
            return this;
        }

        public void flush() {
            this.writer.flush();
        }

        public PrintingElementVisitor visitExecutable(ExecutableElement executableElement, Boolean bool) {
            ElementKind kind = executableElement.getKind();
            if (kind != ElementKind.STATIC_INIT && kind != ElementKind.INSTANCE_INIT) {
                Element enclosingElement = executableElement.getEnclosingElement();
                if (kind != ElementKind.CONSTRUCTOR || enclosingElement == null || NestingKind.ANONYMOUS != new SimpleElementVisitor14<NestingKind, Void>() { // from class: com.sun.tools.javac.processing.PrintingProcessor.PrintingElementVisitor.1
                    public NestingKind visitType(TypeElement typeElement, Void r2) {
                        return typeElement.getNestingKind();
                    }
                }.visit(enclosingElement)) {
                    defaultAction((Element) executableElement, Boolean.TRUE);
                    printFormalTypeParameters(executableElement, true);
                    int i = AnonymousClass1.$SwitchMap$javax$lang$model$element$ElementKind[kind.ordinal()];
                    if (i == 1) {
                        this.writer.print(executableElement.getEnclosingElement().getSimpleName());
                    } else if (i == 2) {
                        this.writer.print(executableElement.getReturnType().toString());
                        this.writer.print(" ");
                        this.writer.print(executableElement.getSimpleName().toString());
                    }
                    boolean zIsCompactConstructor = this.elementUtils.isCompactConstructor(executableElement);
                    PrintWriter printWriter = this.writer;
                    if (zIsCompactConstructor) {
                        printWriter.print(" {} /* compact constructor */ ");
                    } else {
                        printWriter.print("(");
                        printParameters(executableElement);
                        this.writer.print(")");
                        AnnotationValue defaultValue = executableElement.getDefaultValue();
                        if (defaultValue != null) {
                            this.writer.print(" default " + defaultValue);
                        }
                        printThrows(executableElement);
                    }
                    this.writer.println(";");
                }
            }
            return this;
        }

        public PrintingElementVisitor visitModule(ModuleElement moduleElement, Boolean bool) {
            defaultAction((Element) moduleElement, Boolean.FALSE);
            if (moduleElement.isUnnamed()) {
                this.writer.println("// Unnamed module");
                return this;
            }
            if (moduleElement.isOpen()) {
                this.writer.print("open ");
            }
            this.writer.println("module " + moduleElement.getQualifiedName() + " {");
            this.indentation = this.indentation + 1;
            Iterator<? extends ModuleElement.Directive> it = moduleElement.getDirectives().iterator();
            while (it.hasNext()) {
                printDirective(it.next());
            }
            this.indentation--;
            this.writer.println("}");
            return this;
        }

        public PrintingElementVisitor visitPackage(PackageElement packageElement, Boolean bool) {
            defaultAction((Element) packageElement, Boolean.FALSE);
            boolean zIsUnnamed = packageElement.isUnnamed();
            PrintWriter printWriter = this.writer;
            if (zIsUnnamed) {
                printWriter.println("// Unnamed package");
                return this;
            }
            printWriter.println("package " + packageElement.getQualifiedName() + ";");
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public PrintingElementVisitor visitType(TypeElement typeElement, Boolean bool) {
            ElementKind kind = typeElement.getKind();
            NestingKind nestingKind = typeElement.getNestingKind();
            int i = 0;
            if (NestingKind.ANONYMOUS == nestingKind) {
                TypeMirror superclass = typeElement.getSuperclass();
                if (superclass.getKind() != TypeKind.NONE && ((TypeElement) ((DeclaredType) superclass).asElement()).getKind() == ElementKind.ENUM) {
                    return this;
                }
                this.writer.print("new ");
                List<? extends TypeMirror> interfaces = typeElement.getInterfaces();
                boolean zIsEmpty = interfaces.isEmpty();
                PrintWriter printWriter = this.writer;
                if (zIsEmpty) {
                    printWriter.print(typeElement.getSuperclass());
                } else {
                    printWriter.print(interfaces.get(0));
                }
                this.writer.print("(");
                if (interfaces.isEmpty()) {
                    List<ExecutableElement> listConstructorsIn = ElementFilter.constructorsIn(typeElement.getEnclosedElements());
                    if (!listConstructorsIn.isEmpty()) {
                        printParameters(listConstructorsIn.get(0));
                    }
                }
                this.writer.print(")");
            } else {
                if (nestingKind == NestingKind.TOP_LEVEL) {
                    PackageElement packageOf = this.elementUtils.getPackageOf(typeElement);
                    if (!packageOf.isUnnamed()) {
                        this.writer.print("package " + packageOf.getQualifiedName() + ";\n");
                    }
                }
                defaultAction((Element) typeElement, Boolean.TRUE);
                int i2 = AnonymousClass1.$SwitchMap$javax$lang$model$element$ElementKind[kind.ordinal()];
                PrintWriter printWriter2 = this.writer;
                if (i2 != 3) {
                    printWriter2.print(StringUtils.toLowerCase(kind.toString()));
                } else {
                    printWriter2.print("@interface");
                }
                this.writer.print(" ");
                this.writer.print(typeElement.getSimpleName());
                printFormalTypeParameters(typeElement, false);
                if (kind == ElementKind.RECORD) {
                    this.writer.print("(");
                    this.writer.print((String) typeElement.getRecordComponents().stream().map(new Function() { // from class: z7b
                        @Override // java.util.function.Function
                        public final Object apply(Object obj) {
                            return PrintingProcessor.PrintingElementVisitor.c(this.b, (RecordComponentElement) obj);
                        }
                    }).collect(Collectors.joining(", ")));
                    this.writer.print(")");
                }
                if (kind == ElementKind.CLASS) {
                    TypeMirror superclass2 = typeElement.getSuperclass();
                    if (superclass2.getKind() != TypeKind.NONE && isImportantType(superclass2)) {
                        this.writer.print(" extends " + superclass2);
                    }
                }
                printInterfaces(typeElement);
                printPermittedSubclasses(typeElement);
            }
            this.writer.println(" {");
            this.indentation++;
            if (kind == ElementKind.ENUM) {
                ArrayList<Element> arrayList = new ArrayList(typeElement.getEnclosedElements());
                ArrayList arrayList2 = new ArrayList();
                for (Element element : arrayList) {
                    if (element.getKind() == ElementKind.ENUM_CONSTANT) {
                        arrayList2.add(element);
                    }
                }
                if (!arrayList2.isEmpty()) {
                    while (i < arrayList2.size() - 1) {
                        visit((Element) arrayList2.get(i), Boolean.TRUE);
                        this.writer.print(",");
                        i++;
                    }
                    visit((Element) arrayList2.get(i), Boolean.TRUE);
                    this.writer.println(";\n");
                    arrayList.removeAll(arrayList2);
                }
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    visit((Element) it.next());
                }
            } else {
                Iterator<? extends Element> it2 = (kind != ElementKind.RECORD ? typeElement.getEnclosedElements() : (List) typeElement.getEnclosedElements().stream().filter(new Predicate() { // from class: a8b
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return PrintingProcessor.PrintingElementVisitor.e(this.b, (Element) obj);
                    }
                }).collect(Collectors.toList())).iterator();
                while (it2.hasNext()) {
                    visit(it2.next());
                }
            }
            this.indentation--;
            indent();
            this.writer.println("}");
            return this;
        }

        public PrintingElementVisitor visitTypeParameter(TypeParameterElement typeParameterElement, Boolean bool) {
            this.writer.print(typeParameterElement.getSimpleName());
            return this;
        }

        public PrintingElementVisitor visitVariable(VariableElement variableElement, Boolean bool) {
            ElementKind kind = variableElement.getKind();
            defaultAction((Element) variableElement, bool);
            ElementKind elementKind = ElementKind.ENUM_CONSTANT;
            PrintWriter printWriter = this.writer;
            if (kind == elementKind) {
                printWriter.print(variableElement.getSimpleName());
                return this;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(variableElement.asType().toString());
            sb.append(" ");
            sb.append((Object) (variableElement.getSimpleName().length() == 0 ? "_" : variableElement.getSimpleName()));
            printWriter.print(sb.toString());
            Object constantValue = variableElement.getConstantValue();
            if (constantValue != null) {
                this.writer.print(" = ");
                this.writer.print(this.elementUtils.getConstantExpression(constantValue));
            }
            this.writer.println(";");
            return this;
        }

        public PrintingElementVisitor visitRecordComponent(RecordComponentElement recordComponentElement, Boolean bool) {
            return this;
        }
    }
}
