package com.sun.tools.javac.code;

import com.sun.source.tree.MemberReferenceTree;
import com.sun.source.tree.Tree;
import com.sun.tools.javac.code.Attribute;
import com.sun.tools.javac.code.TypeAnnotations;
import com.sun.tools.javac.comp.Annotate;
import com.sun.tools.javac.comp.Attr;
import com.sun.tools.javac.comp.AttrContext;
import com.sun.tools.javac.comp.Env;
import com.sun.tools.javac.resources.CompilerProperties;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeInfo;
import com.sun.tools.javac.tree.TreeScanner;
import com.sun.tools.javac.util.Assert;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.JCDiagnostic;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.ListBuffer;
import com.sun.tools.javac.util.Log;
import com.sun.tools.javac.util.Name;
import com.sun.tools.javac.util.Names;
import java.util.Iterator;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.type.TypeKind;
import javax.tools.JavaFileObject;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class TypeAnnotations {
    protected static final Context.Key<TypeAnnotations> typeAnnosKey = new Context.Key<>();
    final Annotate annotate;
    final Attr attr;
    final Log log;
    final Names names;
    final Symtab syms;

    /* JADX INFO: renamed from: com.sun.tools.javac.code.TypeAnnotations$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$sun$source$tree$MemberReferenceTree$ReferenceMode;
        static final /* synthetic */ int[] $SwitchMap$com$sun$source$tree$Tree$Kind;
        static final /* synthetic */ int[] $SwitchMap$javax$lang$model$element$ElementKind;

        static {
            int[] iArr = new int[Tree.Kind.values().length];
            $SwitchMap$com$sun$source$tree$Tree$Kind = iArr;
            try {
                iArr[Tree.Kind.TYPE_CAST.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$sun$source$tree$Tree$Kind[Tree.Kind.INSTANCE_OF.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$sun$source$tree$Tree$Kind[Tree.Kind.NEW_CLASS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$sun$source$tree$Tree$Kind[Tree.Kind.NEW_ARRAY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$sun$source$tree$Tree$Kind[Tree.Kind.ANNOTATION_TYPE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$sun$source$tree$Tree$Kind[Tree.Kind.CLASS.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$sun$source$tree$Tree$Kind[Tree.Kind.ENUM.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$sun$source$tree$Tree$Kind[Tree.Kind.INTERFACE.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$sun$source$tree$Tree$Kind[Tree.Kind.RECORD.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$sun$source$tree$Tree$Kind[Tree.Kind.METHOD.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$sun$source$tree$Tree$Kind[Tree.Kind.PARAMETERIZED_TYPE.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$sun$source$tree$Tree$Kind[Tree.Kind.MEMBER_REFERENCE.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$sun$source$tree$Tree$Kind[Tree.Kind.ARRAY_TYPE.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$sun$source$tree$Tree$Kind[Tree.Kind.TYPE_PARAMETER.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$sun$source$tree$Tree$Kind[Tree.Kind.VARIABLE.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$sun$source$tree$Tree$Kind[Tree.Kind.ANNOTATED_TYPE.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$sun$source$tree$Tree$Kind[Tree.Kind.UNION_TYPE.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$sun$source$tree$Tree$Kind[Tree.Kind.INTERSECTION_TYPE.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$com$sun$source$tree$Tree$Kind[Tree.Kind.METHOD_INVOCATION.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$com$sun$source$tree$Tree$Kind[Tree.Kind.EXTENDS_WILDCARD.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$com$sun$source$tree$Tree$Kind[Tree.Kind.SUPER_WILDCARD.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                $SwitchMap$com$sun$source$tree$Tree$Kind[Tree.Kind.MEMBER_SELECT.ordinal()] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                $SwitchMap$com$sun$source$tree$Tree$Kind[Tree.Kind.DECONSTRUCTION_PATTERN.ordinal()] = 23;
            } catch (NoSuchFieldError unused23) {
            }
            int[] iArr2 = new int[ElementKind.values().length];
            $SwitchMap$javax$lang$model$element$ElementKind = iArr2;
            try {
                iArr2[ElementKind.BINDING_VARIABLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                $SwitchMap$javax$lang$model$element$ElementKind[ElementKind.LOCAL_VARIABLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                $SwitchMap$javax$lang$model$element$ElementKind[ElementKind.FIELD.ordinal()] = 3;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                $SwitchMap$javax$lang$model$element$ElementKind[ElementKind.PARAMETER.ordinal()] = 4;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                $SwitchMap$javax$lang$model$element$ElementKind[ElementKind.EXCEPTION_PARAMETER.ordinal()] = 5;
            } catch (NoSuchFieldError unused28) {
            }
            try {
                $SwitchMap$javax$lang$model$element$ElementKind[ElementKind.RESOURCE_VARIABLE.ordinal()] = 6;
            } catch (NoSuchFieldError unused29) {
            }
            int[] iArr3 = new int[MemberReferenceTree.ReferenceMode.values().length];
            $SwitchMap$com$sun$source$tree$MemberReferenceTree$ReferenceMode = iArr3;
            try {
                iArr3[MemberReferenceTree.ReferenceMode.INVOKE.ordinal()] = 1;
            } catch (NoSuchFieldError unused30) {
            }
            try {
                $SwitchMap$com$sun$source$tree$MemberReferenceTree$ReferenceMode[MemberReferenceTree.ReferenceMode.NEW.ordinal()] = 2;
            } catch (NoSuchFieldError unused31) {
            }
        }
    }

    public enum AnnotationType {
        DECLARATION,
        TYPE,
        NONE,
        BOTH
    }

    public TypeAnnotations(Context context) {
        context.put(typeAnnosKey, this);
        this.names = Names.instance(context);
        this.log = Log.instance(context);
        this.syms = Symtab.instance(context);
        this.annotate = Annotate.instance(context);
        this.attr = Attr.instance(context);
    }

    public static /* synthetic */ void a(TypeAnnotations typeAnnotations, Env env, JCTree.JCClassDecl jCClassDecl) {
        JavaFileObject javaFileObjectUseSource = typeAnnotations.log.useSource(env.toplevel.sourcefile);
        try {
            typeAnnotations.new TypeAnnotationPositions(true).scan(jCClassDecl);
        } finally {
            typeAnnotations.log.useSource(javaFileObjectUseSource);
        }
    }

    public static /* synthetic */ boolean b(Attribute attribute) {
        return !(attribute instanceof Attribute.Enum);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AnnotationType combineAnnotationType(AnnotationType annotationType, AnnotationType annotationType2) {
        AnnotationType annotationType3 = AnnotationType.NONE;
        if (annotationType == annotationType3) {
            return annotationType2;
        }
        return (annotationType2 == annotationType3 || annotationType == annotationType2) ? annotationType : AnnotationType.BOTH;
    }

    public static /* synthetic */ void d(TypeAnnotations typeAnnotations, Env env, JCTree.JCClassDecl jCClassDecl) {
        JavaFileObject javaFileObjectUseSource = typeAnnotations.log.useSource(env.toplevel.sourcefile);
        try {
            typeAnnotations.attr.validateTypeAnnotations(jCClassDecl, true);
        } finally {
            typeAnnotations.log.useSource(javaFileObjectUseSource);
        }
    }

    public static TypeAnnotations instance(Context context) {
        TypeAnnotations typeAnnotations = (TypeAnnotations) context.get(typeAnnosKey);
        return typeAnnotations == null ? new TypeAnnotations(context) : typeAnnotations;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AnnotationType targetToAnnotationType(JCTree jCTree, Attribute.Compound compound, Attribute attribute, Symbol symbol) {
        Kinds.Kind kind;
        Name name = ((Attribute.Enum) attribute).value.name;
        Names names = this.names;
        if (name == names.TYPE) {
            if (symbol.kind == Kinds.Kind.TYP) {
                return AnnotationType.DECLARATION;
            }
        } else if (name == names.FIELD || name == names.RECORD_COMPONENT) {
            if (symbol.kind == Kinds.Kind.VAR && symbol.owner.kind != Kinds.Kind.MTH) {
                return AnnotationType.DECLARATION;
            }
        } else if (name == names.METHOD) {
            if (symbol.kind == Kinds.Kind.MTH && !symbol.isConstructor()) {
                return AnnotationType.DECLARATION;
            }
        } else if (name == names.PARAMETER) {
            if (symbol.kind == Kinds.Kind.VAR && symbol.owner.kind == Kinds.Kind.MTH && (symbol.flags() & 8589934592L) != 0) {
                return AnnotationType.DECLARATION;
            }
        } else if (name == names.CONSTRUCTOR) {
            if (symbol.kind == Kinds.Kind.MTH && symbol.isConstructor()) {
                return AnnotationType.DECLARATION;
            }
        } else if (name == names.LOCAL_VARIABLE) {
            if (symbol.kind == Kinds.Kind.VAR && symbol.owner.kind == Kinds.Kind.MTH && (symbol.flags() & 8589934592L) == 0) {
                return AnnotationType.DECLARATION;
            }
        } else if (name == names.ANNOTATION_TYPE) {
            if (symbol.kind == Kinds.Kind.TYP && (symbol.flags() & 8192) != 0) {
                return AnnotationType.DECLARATION;
            }
        } else if (name == names.PACKAGE) {
            if (symbol.kind == Kinds.Kind.PCK) {
                return AnnotationType.DECLARATION;
            }
        } else if (name == names.TYPE_USE) {
            Kinds.Kind kind2 = symbol.kind;
            if (kind2 == Kinds.Kind.TYP || kind2 == Kinds.Kind.VAR || ((kind2 == (kind = Kinds.Kind.MTH) && !symbol.isConstructor() && !symbol.type.mo73getReturnType().hasTag(TypeTag.VOID)) || (symbol.kind == kind && symbol.isConstructor()))) {
                return AnnotationType.TYPE;
            }
        } else if (name != names.TYPE_PARAMETER) {
            if (name != names.MODULE) {
                return AnnotationType.DECLARATION;
            }
            if (symbol.kind == Kinds.Kind.MDL) {
                return AnnotationType.DECLARATION;
            }
        }
        return AnnotationType.NONE;
    }

    public AnnotationType annotationTargetType(final JCTree jCTree, final Attribute.Compound compound, final Symbol symbol) {
        if (!compound.type.tsym.isAnnotationType()) {
            return AnnotationType.NONE;
        }
        List<Attribute> listAnnotationTargets = annotationTargets(compound.type.tsym);
        return listAnnotationTargets == null ? AnnotationType.DECLARATION : (AnnotationType) listAnnotationTargets.stream().map(new Function() { // from class: sse
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.targetToAnnotationType(jCTree, compound, (Attribute) obj, symbol);
            }
        }).reduce(AnnotationType.NONE, new BinaryOperator() { // from class: tse
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return this.b.combineAnnotationType((TypeAnnotations.AnnotationType) obj, (TypeAnnotations.AnnotationType) obj2);
            }
        });
    }

    public List<Attribute> annotationTargets(Symbol.TypeSymbol typeSymbol) {
        Attribute.Compound target = typeSymbol.getAnnotationTypeMetadata().getTarget();
        if (target == null) {
            return null;
        }
        Attribute attributeMember = target.member(this.names.value);
        if (!(attributeMember instanceof Attribute.Array)) {
            return null;
        }
        List<Attribute> value = ((Attribute.Array) attributeMember).getValue();
        if (value.stream().anyMatch(new Predicate() { // from class: use
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return TypeAnnotations.b((Attribute) obj);
            }
        })) {
            return null;
        }
        return value;
    }

    public void organizeTypeAnnotationsBodies(JCTree.JCClassDecl jCClassDecl) {
        new TypeAnnotationPositions(false).scan(jCClassDecl);
    }

    public void organizeTypeAnnotationsSignatures(final Env<AttrContext> env, final JCTree.JCClassDecl jCClassDecl) {
        this.annotate.afterTypes(new Runnable() { // from class: wse
            @Override // java.lang.Runnable
            public final void run() {
                TypeAnnotations.a(this.b, env, jCClassDecl);
            }
        });
    }

    public void validateTypeAnnotationsSignatures(final Env<AttrContext> env, final JCTree.JCClassDecl jCClassDecl) {
        this.annotate.validate(new Runnable() { // from class: vse
            @Override // java.lang.Runnable
            public final void run() {
                TypeAnnotations.d(this.b, env, jCClassDecl);
            }
        });
    }

    public class TypeAnnotationPositions extends TreeScanner {
        private final boolean sigOnly;
        private List<JCTree> frames = List.nil();
        private boolean isInClass = false;
        private JCTree.JCLambda currentLambda = null;

        public TypeAnnotationPositions(boolean z) {
            this.sigOnly = z;
        }

        public static /* synthetic */ void a(TypeAnnotationPositions typeAnnotationPositions, Symbol.RecordComponent recordComponent) {
            typeAnnotationPositions.getClass();
            typeAnnotationPositions.scan(recordComponent.accessorMeth);
        }

        private void appendTypeAnnotationsToOwner(Symbol symbol, List<Attribute.TypeCompound> list) {
            long jFlags = symbol.owner.flags();
            long j = 1048576 & jFlags;
            Symbol symbol2 = symbol.owner;
            if (j == 0) {
                symbol2.appendUniqueTypeAttributes(list);
                return;
            }
            Symbol.ClassSymbol classSymbol = (Symbol.ClassSymbol) symbol2.owner;
            if ((jFlags & 8) != 0) {
                classSymbol.appendClassInitTypeAttributes(list);
            } else {
                classSymbol.appendInitTypeAttributes(list);
            }
        }

        private JCTree arrayElemTypeTree(JCTree jCTree) {
            if (jCTree.getKind() == Tree.Kind.ANNOTATED_TYPE) {
                jCTree = ((JCTree.JCAnnotatedType) jCTree).underlyingType;
            }
            return ((JCTree.JCArrayTypeTree) jCTree).elemtype;
        }

        private void findPosition(JCTree jCTree, JCTree jCTree2, List<JCTree.JCAnnotation> list) {
            if (list.isEmpty()) {
                return;
            }
            setTypeAnnotationPos(list, resolveFrame(jCTree, jCTree2, this.frames, this.currentLambda, 0, new ListBuffer<>()));
        }

        private ListBuffer<TypeAnnotationPosition.TypePathEntry> locateNestedTypes(Type type, ListBuffer<TypeAnnotationPosition.TypePathEntry> listBuffer) {
            for (Type enclosingType = type.getEnclosingType(); enclosingType != null && enclosingType.getKind() != TypeKind.NONE && enclosingType.getKind() != TypeKind.ERROR; enclosingType = enclosingType.getEnclosingType()) {
                listBuffer = listBuffer.prepend(TypeAnnotationPosition.TypePathEntry.INNER_TYPE);
            }
            return listBuffer;
        }

        /* JADX WARN: Multi-variable type inference failed */
        private int methodParamIndex(List<JCTree> list, JCTree jCTree) {
            JCTree.Tag tag;
            List list2 = list;
            while (true) {
                JCTree.Tag tag2 = ((JCTree) list2.head).getTag();
                tag = JCTree.Tag.METHODDEF;
                if (tag2 == tag || ((JCTree) list2.head).getTag() == JCTree.Tag.LAMBDA) {
                    break;
                }
                list2 = list2.tail;
            }
            JCTree.Tag tag3 = ((JCTree) list2.head).getTag();
            A a = list2.head;
            if (tag3 == tag) {
                return ((JCTree.JCMethodDecl) a).params.indexOf(jCTree);
            }
            if (((JCTree) a).getTag() == JCTree.Tag.LAMBDA) {
                return ((JCTree.JCLambda) list2.head).params.indexOf(jCTree);
            }
            Assert.error("methodParamIndex expected to find method or lambda for param: " + jCTree);
            return -1;
        }

        private JCTree peek2() {
            return this.frames.tail.head;
        }

        private void propagateNewClassAnnotationsToOwner(JCTree.JCNewClass jCNewClass) {
            Symbol.ClassSymbol classSymbol = jCNewClass.def.sym;
            TypeAnnotationPosition typeAnnotationPositionNewObj = TypeAnnotationPosition.newObj(locateNestedTypes(classSymbol.owner.enclClass().type, new ListBuffer<>()).append(TypeAnnotationPosition.TypePathEntry.INNER_TYPE).toList(), null, jCNewClass.pos);
            ListBuffer listBuffer = new ListBuffer();
            List<TypeAnnotationPosition.TypePathEntry> list = locateNestedTypes(jCNewClass.clazz.type, new ListBuffer<>()).toList();
            for (Attribute.TypeCompound typeCompound : classSymbol.getRawTypeAttributes()) {
                if (typeCompound.position.location.equals(list)) {
                    listBuffer.append(new Attribute.TypeCompound(typeCompound.type, typeCompound.values, typeAnnotationPositionNewObj));
                }
            }
            appendTypeAnnotationsToOwner(classSymbol, listBuffer.toList());
        }

        /* JADX WARN: Multi-variable type inference failed */
        private TypeAnnotationPosition resolveFrame(JCTree jCTree, JCTree jCTree2, List<JCTree> list, JCTree.JCLambda jCLambda, int i, ListBuffer<TypeAnnotationPosition.TypePathEntry> listBuffer) {
            List list2;
            switch (AnonymousClass1.$SwitchMap$com$sun$source$tree$Tree$Kind[jCTree2.getKind().ordinal()]) {
                case 1:
                    return TypeAnnotationPosition.typeCast(listBuffer.toList(), jCLambda, i, jCTree2.pos);
                case 2:
                    return TypeAnnotationPosition.instanceOf(listBuffer.toList(), jCLambda, jCTree2.pos);
                case 3:
                    JCTree.JCNewClass jCNewClass = (JCTree.JCNewClass) jCTree2;
                    JCTree.JCClassDecl jCClassDecl = jCNewClass.def;
                    if (jCClassDecl != null) {
                        if (jCClassDecl.implementing.contains(jCTree)) {
                            return TypeAnnotationPosition.classExtends(listBuffer.toList(), jCLambda, jCClassDecl.implementing.indexOf(jCTree), jCTree2.pos);
                        }
                        return TypeAnnotationPosition.classExtends(listBuffer.toList(), jCLambda, jCTree2.pos);
                    }
                    if (jCNewClass.typeargs.contains(jCTree)) {
                        return TypeAnnotationPosition.constructorInvocationTypeArg(listBuffer.toList(), jCLambda, jCNewClass.typeargs.indexOf(jCTree), jCTree2.pos);
                    }
                    return TypeAnnotationPosition.newObj(listBuffer.toList(), jCLambda, jCTree2.pos);
                case 4:
                    return TypeAnnotationPosition.newObj(listBuffer.toList(), jCLambda, jCTree2.pos);
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                    JCTree.JCClassDecl jCClassDecl2 = (JCTree.JCClassDecl) jCTree2;
                    if (jCClassDecl2.extending == jCTree) {
                        return TypeAnnotationPosition.classExtends(listBuffer.toList(), jCLambda, jCTree2.pos);
                    }
                    if (jCClassDecl2.implementing.contains(jCTree)) {
                        return TypeAnnotationPosition.classExtends(listBuffer.toList(), jCLambda, jCClassDecl2.implementing.indexOf(jCTree), jCTree2.pos);
                    }
                    if (jCClassDecl2.typarams.contains(jCTree)) {
                        return TypeAnnotationPosition.typeParameter(listBuffer.toList(), jCLambda, jCClassDecl2.typarams.indexOf(jCTree), jCTree2.pos);
                    }
                    md6.a("Could not determine position of tree ", jCTree, " within frame ", jCTree2);
                    return null;
                case 10:
                    JCTree.JCMethodDecl jCMethodDecl = (JCTree.JCMethodDecl) jCTree2;
                    if (jCMethodDecl.thrown.contains(jCTree)) {
                        return TypeAnnotationPosition.methodThrows(listBuffer.toList(), jCLambda, jCMethodDecl.thrown.indexOf(jCTree), jCTree2.pos);
                    }
                    if (jCMethodDecl.restype == jCTree) {
                        return TypeAnnotationPosition.methodReturn(listBuffer.toList(), jCLambda, jCTree2.pos);
                    }
                    if (jCMethodDecl.typarams.contains(jCTree)) {
                        return TypeAnnotationPosition.methodTypeParameter(listBuffer.toList(), jCLambda, jCMethodDecl.typarams.indexOf(jCTree), jCTree2.pos);
                    }
                    md6.a("Could not determine position of tree ", jCTree, " within frame ", jCTree2);
                    return null;
                case 11:
                    ListBuffer<TypeAnnotationPosition.TypePathEntry> listBufferLocateNestedTypes = listBuffer;
                    List<JCTree> list3 = list.tail;
                    JCTree.JCTypeApply jCTypeApply = (JCTree.JCTypeApply) jCTree2;
                    if (jCTypeApply.clazz != jCTree) {
                        if (!jCTypeApply.arguments.contains(jCTree)) {
                            md6.a("Could not determine type argument position of tree ", jCTree, " within frame ", jCTree2);
                            return null;
                        }
                        ListBuffer<TypeAnnotationPosition.TypePathEntry> listBufferPrepend = listBufferLocateNestedTypes.prepend(new TypeAnnotationPosition.TypePathEntry(TypeAnnotationPosition.TypePathEntryKind.TYPE_ARGUMENT, jCTypeApply.arguments.indexOf(jCTree)));
                        List<JCTree> list4 = list3.tail;
                        listBufferLocateNestedTypes = locateNestedTypes((list4 == null || !list4.head.hasTag(JCTree.Tag.NEWCLASS)) ? jCTypeApply.type : list3.tail.head.type, listBufferPrepend);
                    }
                    return resolveFrame(list3.head, list3.tail.head, list3, jCLambda, i, listBufferLocateNestedTypes);
                case 12:
                    JCTree.JCMemberReference jCMemberReference = (JCTree.JCMemberReference) jCTree2;
                    if (jCMemberReference.expr == jCTree) {
                        int i2 = AnonymousClass1.$SwitchMap$com$sun$source$tree$MemberReferenceTree$ReferenceMode[jCMemberReference.mode.ordinal()];
                        if (i2 == 1) {
                            return TypeAnnotationPosition.methodRef(listBuffer.toList(), jCLambda, jCTree2.pos);
                        }
                        if (i2 == 2) {
                            return TypeAnnotationPosition.constructorRef(listBuffer.toList(), jCLambda, jCTree2.pos);
                        }
                        throw new AssertionError("Unknown method reference mode " + jCMemberReference.mode + " for tree " + jCTree + " within frame " + jCTree2);
                    }
                    List<JCTree.JCExpression> list5 = jCMemberReference.typeargs;
                    if (list5 == null || !list5.contains(jCTree)) {
                        md6.a("Could not determine type argument position of tree ", jCTree, " within frame ", jCTree2);
                        return null;
                    }
                    int iIndexOf = jCMemberReference.typeargs.indexOf(jCTree);
                    int i3 = AnonymousClass1.$SwitchMap$com$sun$source$tree$MemberReferenceTree$ReferenceMode[jCMemberReference.mode.ordinal()];
                    if (i3 == 1) {
                        return TypeAnnotationPosition.methodRefTypeArg(listBuffer.toList(), jCLambda, iIndexOf, jCTree2.pos);
                    }
                    if (i3 == 2) {
                        return TypeAnnotationPosition.constructorRefTypeArg(listBuffer.toList(), jCLambda, iIndexOf, jCTree2.pos);
                    }
                    throw new AssertionError("Unknown method reference mode " + jCMemberReference.mode + " for tree " + jCTree + " within frame " + jCTree2);
                case 13:
                    ListBuffer<TypeAnnotationPosition.TypePathEntry> listBufferPrepend2 = listBuffer.prepend(TypeAnnotationPosition.TypePathEntry.ARRAY);
                    List list6 = list.tail;
                    while (true) {
                        JCTree jCTree3 = (JCTree) list6.tail.head;
                        if (jCTree3.hasTag(JCTree.Tag.TYPEARRAY)) {
                            list2 = list6.tail;
                            listBufferPrepend2 = listBufferPrepend2.prepend(TypeAnnotationPosition.TypePathEntry.ARRAY);
                        } else {
                            if (!jCTree3.hasTag(JCTree.Tag.ANNOTATED_TYPE)) {
                                return resolveFrame((JCTree) list6.head, (JCTree) list6.tail.head, list6, jCLambda, i, listBufferPrepend2);
                            }
                            list2 = list6.tail;
                        }
                        list6 = list2;
                    }
                    break;
                case 14:
                    boolean zHasTag = list.tail.tail.head.hasTag(JCTree.Tag.CLASSDEF);
                    List<JCTree> list7 = list.tail;
                    if (zHasTag) {
                        int iIndexOf2 = ((JCTree.JCClassDecl) list7.tail.head).typarams.indexOf(list7.head);
                        JCTree.JCTypeParameter jCTypeParameter = (JCTree.JCTypeParameter) jCTree2;
                        boolean zIsInterface = jCTypeParameter.bounds.get(0).type.isInterface();
                        List<JCTree.JCExpression> list8 = jCTypeParameter.bounds;
                        return TypeAnnotationPosition.typeParameterBound(listBuffer.toList(), jCLambda, iIndexOf2, zIsInterface ? list8.indexOf(jCTree) + 1 : list8.indexOf(jCTree), jCTree2.pos);
                    }
                    if (!list7.tail.head.hasTag(JCTree.Tag.METHODDEF)) {
                        md6.a("Could not determine position of tree ", jCTree, " within frame ", jCTree2);
                        return null;
                    }
                    List<JCTree> list9 = list.tail;
                    int iIndexOf3 = ((JCTree.JCMethodDecl) list9.tail.head).typarams.indexOf(list9.head);
                    JCTree.JCTypeParameter jCTypeParameter2 = (JCTree.JCTypeParameter) jCTree2;
                    boolean zIsInterface2 = jCTypeParameter2.bounds.get(0).type.isInterface();
                    List<JCTree.JCExpression> list10 = jCTypeParameter2.bounds;
                    return TypeAnnotationPosition.methodTypeParameterBound(listBuffer.toList(), jCLambda, iIndexOf3, zIsInterface2 ? list10.indexOf(jCTree) + 1 : list10.indexOf(jCTree), jCTree2.pos);
                case 15:
                    Symbol.VarSymbol varSymbol = ((JCTree.JCVariableDecl) jCTree2).sym;
                    if (varSymbol.getKind() != ElementKind.FIELD) {
                        appendTypeAnnotationsToOwner(varSymbol, varSymbol.getRawTypeAttributes());
                    }
                    switch (AnonymousClass1.$SwitchMap$javax$lang$model$element$ElementKind[varSymbol.getKind().ordinal()]) {
                        case 1:
                        case 2:
                            return TypeAnnotationPosition.localVariable(listBuffer.toList(), jCLambda, jCTree2.pos);
                        case 3:
                            return TypeAnnotationPosition.field(listBuffer.toList(), jCLambda, jCTree2.pos);
                        case 4:
                            if (varSymbol.getQualifiedName().equals(TypeAnnotations.this.names._this)) {
                                return TypeAnnotationPosition.methodReceiver(listBuffer.toList(), jCLambda, jCTree2.pos);
                            }
                            return TypeAnnotationPosition.methodParameter(listBuffer.toList(), jCLambda, methodParamIndex(list, jCTree2), jCTree2.pos);
                        case 5:
                            return TypeAnnotationPosition.exceptionParameter(listBuffer.toList(), jCLambda, jCTree2.pos);
                        case 6:
                            return TypeAnnotationPosition.resourceVariable(listBuffer.toList(), jCLambda, jCTree2.pos);
                        default:
                            StringBuilder sb = new StringBuilder("Found unexpected type annotation for variable: ");
                            sb.append(varSymbol);
                            cmb.a(sb, " with kind: ", varSymbol.getKind());
                            return null;
                    }
                case 16:
                    ListBuffer<TypeAnnotationPosition.TypePathEntry> listBufferLocateNestedTypes2 = listBuffer;
                    if (jCTree2 == jCTree) {
                        Type type = ((JCTree.JCAnnotatedType) jCTree2).underlyingType.type;
                        Assert.checkNonNull(type);
                        if (!type.tsym.getKind().equals(ElementKind.TYPE_PARAMETER) && !type.getKind().equals(TypeKind.WILDCARD) && !type.getKind().equals(TypeKind.ARRAY)) {
                            listBufferLocateNestedTypes2 = locateNestedTypes(type, listBufferLocateNestedTypes2);
                        }
                    }
                    List<JCTree> list11 = list.tail;
                    return resolveFrame(list11.head, list11.tail.head, list11, jCLambda, i, listBufferLocateNestedTypes2);
                case 17:
                    List<JCTree> list12 = list.tail;
                    return resolveFrame(list12.head, list12.tail.head, list12, jCLambda, i, listBuffer);
                case 18:
                    List<JCTree> list13 = list.tail;
                    return resolveFrame(list13.head, list13.tail.head, list13, jCLambda, ((JCTree.JCTypeIntersection) jCTree2).bounds.indexOf(jCTree), listBuffer);
                case 19:
                    JCTree.JCMethodInvocation jCMethodInvocation = (JCTree.JCMethodInvocation) jCTree2;
                    if (!jCMethodInvocation.typeargs.contains(jCTree)) {
                        return TypeAnnotationPosition.unknown;
                    }
                    Symbol symbol = TreeInfo.symbol(jCMethodInvocation.getMethodSelect());
                    if (symbol.type.isErroneous()) {
                        return TypeAnnotationPosition.unknown;
                    }
                    int iIndexOf4 = jCMethodInvocation.typeargs.indexOf(jCTree);
                    return symbol.isConstructor() ? TypeAnnotationPosition.constructorInvocationTypeArg(listBuffer.toList(), jCLambda, iIndexOf4, jCMethodInvocation.pos) : TypeAnnotationPosition.methodInvocationTypeArg(listBuffer.toList(), jCLambda, iIndexOf4, jCMethodInvocation.pos);
                case 20:
                case 21:
                    List<JCTree> list14 = list.tail;
                    return resolveFrame(list14.head, list14.tail.head, list14, jCLambda, i, listBuffer.prepend(TypeAnnotationPosition.TypePathEntry.WILDCARD));
                case 22:
                    List<JCTree> list15 = list.tail;
                    return resolveFrame(list15.head, list15.tail.head, list15, jCLambda, i, listBuffer);
                case 23:
                    return TypeAnnotationPosition.unknown;
                default:
                    StringBuilder sb2 = new StringBuilder("Unresolved frame: ");
                    sb2.append(jCTree2);
                    otb.a(sb2, " of kind: ", jCTree2.getKind(), "\n    Looking for tree: ", jCTree);
                    return null;
            }
        }

        private Type rewriteArrayType(JCTree jCTree, Type.ArrayType arrayType, List<Attribute.TypeCompound> list, List<Attribute.TypeCompound> list2, TypeAnnotationPosition typeAnnotationPosition) {
            Type.ArrayType arrayType2 = new Type.ArrayType(arrayType);
            typeAnnotationPosition.location = typeAnnotationPosition.location.append(TypeAnnotationPosition.TypePathEntry.ARRAY);
            arrayType2.elemtype = typeWithAnnotations(arrayElemTypeTree(jCTree), arrayType.elemtype, list, list2, typeAnnotationPosition);
            return arrayType2;
        }

        /* JADX WARN: Multi-variable type inference failed */
        private void separateAnnotationsKinds(JCTree jCTree, JCTree jCTree2, Type type, Symbol symbol, TypeAnnotationPosition typeAnnotationPosition) {
            List<Attribute.Compound> rawAttributes = symbol.getRawAttributes();
            ListBuffer listBuffer = new ListBuffer();
            ListBuffer listBuffer2 = new ListBuffer();
            ListBuffer listBuffer3 = new ListBuffer();
            for (Attribute.Compound compound : rawAttributes) {
                int iOrdinal = TypeAnnotations.this.annotationTargetType(jCTree, compound, symbol).ordinal();
                if (iOrdinal == 0) {
                    listBuffer.append(compound);
                } else if (iOrdinal == 1) {
                    Attribute.TypeCompound typeCompound = toTypeCompound(compound, typeAnnotationPosition);
                    listBuffer2.append(typeCompound);
                    listBuffer3.append(typeCompound);
                } else if (iOrdinal == 3) {
                    listBuffer.append(compound);
                    listBuffer2.append(toTypeCompound(compound, typeAnnotationPosition));
                }
            }
            if (listBuffer2.isEmpty()) {
                return;
            }
            symbol.resetAnnotations();
            symbol.setDeclarationAttributes(listBuffer.toList());
            List<Attribute.TypeCompound> list = listBuffer2.toList();
            if (type == null) {
                typeWithAnnotations(jCTree2, symbol.getEnclosingElement().asType(), list, list, typeAnnotationPosition);
                symbol.appendUniqueTypeAttributes(list);
                return;
            }
            Type typeTypeWithAnnotations = typeWithAnnotations(jCTree2, type, list, listBuffer3.toList(), typeAnnotationPosition);
            if (symbol.getKind() == ElementKind.METHOD) {
                symbol.type.asMethodType().restype = typeTypeWithAnnotations;
            } else if (symbol.getKind() == ElementKind.PARAMETER && this.currentLambda == null) {
                symbol.type = typeTypeWithAnnotations;
                boolean zEquals = symbol.getQualifiedName().equals(TypeAnnotations.this.names._this);
                Symbol symbol2 = symbol.owner;
                if (zEquals) {
                    symbol2.type.asMethodType().recvtype = typeTypeWithAnnotations;
                } else {
                    Type.MethodType methodTypeAsMethodType = symbol2.type.asMethodType();
                    List list2 = methodTypeAsMethodType.argtypes;
                    ListBuffer listBuffer4 = new ListBuffer();
                    for (List list3 = ((Symbol.MethodSymbol) symbol.owner).params; list3.nonEmpty(); list3 = list3.tail) {
                        if (list3.head == symbol) {
                            listBuffer4.add(typeTypeWithAnnotations);
                        } else {
                            listBuffer4.add((Type) list2.head);
                        }
                        list2 = list2.tail;
                    }
                    methodTypeAsMethodType.argtypes = listBuffer4.toList();
                }
            } else {
                symbol.type = typeTypeWithAnnotations;
            }
            symbol.appendUniqueTypeAttributes(list);
            if (symbol.getKind() == ElementKind.PARAMETER || symbol.getKind() == ElementKind.LOCAL_VARIABLE || symbol.getKind() == ElementKind.RESOURCE_VARIABLE || symbol.getKind() == ElementKind.EXCEPTION_PARAMETER || symbol.getKind() == ElementKind.BINDING_VARIABLE) {
                appendTypeAnnotationsToOwner(symbol, list);
            }
        }

        private void setTypeAnnotationPos(List<JCTree.JCAnnotation> list, TypeAnnotationPosition typeAnnotationPosition) {
            Iterator<JCTree.JCAnnotation> it = list.iterator();
            while (it.hasNext()) {
                Attribute.Compound compound = it.next().attribute;
                if (compound != null) {
                    ((Attribute.TypeCompound) compound).position = typeAnnotationPosition;
                }
            }
        }

        private Attribute.TypeCompound toTypeCompound(Attribute.Compound compound, TypeAnnotationPosition typeAnnotationPosition) {
            return new Attribute.TypeCompound(compound, typeAnnotationPosition);
        }

        private Type typeWithAnnotations(JCTree jCTree, Type type, List<Attribute.TypeCompound> list, List<Attribute.TypeCompound> list2, TypeAnnotationPosition typeAnnotationPosition) {
            if (list.isEmpty()) {
                return type;
            }
            Iterator<Attribute.TypeCompound> it = list.iterator();
            while (true) {
                boolean z = false;
                if (!it.hasNext()) {
                    break;
                }
                if (it.next().position == typeAnnotationPosition) {
                    z = true;
                }
                Assert.check(z);
            }
            if (type.hasTag(TypeTag.ARRAY)) {
                return rewriteArrayType(jCTree, (Type.ArrayType) type, list, list2, typeAnnotationPosition);
            }
            if (type.hasTag(TypeTag.TYPEVAR)) {
                return type.annotatedType(list2);
            }
            if (type.getKind() == TypeKind.UNION) {
                JCTree.JCExpression jCExpression = ((JCTree.JCTypeUnion) jCTree).alternatives.get(0);
                jCExpression.type = typeWithAnnotations(jCExpression, jCExpression.type, list, list2, typeAnnotationPosition);
                return type;
            }
            Element elementAsElement = type.asElement();
            JCTree expression = jCTree;
            Type enclosingType = type;
            while (elementAsElement != null && elementAsElement.getKind() != ElementKind.PACKAGE && enclosingType != null && enclosingType.getKind() != TypeKind.NONE) {
                Tree.Kind kind = expression.getKind();
                Tree.Kind kind2 = Tree.Kind.MEMBER_SELECT;
                if (kind != kind2 && expression.getKind() != Tree.Kind.PARAMETERIZED_TYPE && expression.getKind() != Tree.Kind.ANNOTATED_TYPE) {
                    break;
                }
                if (expression.getKind() == kind2) {
                    enclosingType = enclosingType.getEnclosingType();
                    elementAsElement = elementAsElement.getEnclosingElement();
                    expression = ((JCTree.JCFieldAccess) expression).getExpression();
                } else {
                    expression = expression.getKind() == Tree.Kind.PARAMETERIZED_TYPE ? ((JCTree.JCTypeApply) expression).getType() : ((JCTree.JCAnnotatedType) expression).getUnderlyingType();
                }
            }
            if (enclosingType != null && enclosingType.hasTag(TypeTag.NONE)) {
                if (list2.isEmpty()) {
                    return type;
                }
                Type typeTypeWithAnnotations = typeWithAnnotations(type.stripMetadata(), enclosingType, list);
                TypeAnnotations.this.log.error(jCTree.pos(), CompilerProperties.Errors.TypeAnnotationInadmissible(list2.size() == 1 ? CompilerProperties.Fragments.TypeAnnotation1(list2.head) : CompilerProperties.Fragments.TypeAnnotation(list2), typeTypeWithAnnotations.tsym.owner, new JCDiagnostic.AnnotatedType(typeTypeWithAnnotations)));
                return type;
            }
            ListBuffer listBuffer = new ListBuffer();
            Type enclosingType2 = enclosingType;
            while (elementAsElement != null && elementAsElement.getKind() != ElementKind.PACKAGE && enclosingType2 != null) {
                TypeKind kind3 = enclosingType2.getKind();
                TypeKind typeKind = TypeKind.NONE;
                if (kind3 == typeKind || enclosingType2.getKind() == TypeKind.ERROR) {
                    break;
                }
                enclosingType2 = enclosingType2.getEnclosingType();
                elementAsElement = elementAsElement.getEnclosingElement();
                if (enclosingType2 != null && enclosingType2.getKind() != typeKind) {
                    listBuffer = listBuffer.append(TypeAnnotationPosition.TypePathEntry.INNER_TYPE);
                }
            }
            if (listBuffer.nonEmpty()) {
                typeAnnotationPosition.location = typeAnnotationPosition.location.appendList(listBuffer.toList());
            }
            Type typeTypeWithAnnotations2 = typeWithAnnotations(type, enclosingType, list);
            jCTree.type = typeTypeWithAnnotations2;
            return typeTypeWithAnnotations2;
        }

        public JCTree pop() {
            List<JCTree> list = this.frames;
            JCTree jCTree = list.head;
            this.frames = list.tail;
            return jCTree;
        }

        public void push(JCTree jCTree) {
            this.frames = this.frames.prepend(jCTree);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner
        public void scan(JCTree jCTree) {
            push(jCTree);
            try {
                super.scan(jCTree);
            } finally {
                pop();
            }
        }

        public String toString() {
            return super.toString() + ": sigOnly: " + this.sigOnly;
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitAnnotatedType(JCTree.JCAnnotatedType jCAnnotatedType) {
            push(jCAnnotatedType);
            findPosition(jCAnnotatedType, jCAnnotatedType, jCAnnotatedType.annotations);
            pop();
            super.visitAnnotatedType(jCAnnotatedType);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitBlock(JCTree.JCBlock jCBlock) {
            if (this.sigOnly) {
                return;
            }
            scan(jCBlock.stats);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitClassDef(JCTree.JCClassDecl jCClassDecl) {
            if (this.isInClass) {
                return;
            }
            this.isInClass = true;
            if (this.sigOnly) {
                scan(jCClassDecl.mods);
                scan(jCClassDecl.typarams);
                scan(jCClassDecl.extending);
                scan(jCClassDecl.implementing);
            }
            scan(jCClassDecl.defs);
            if (jCClassDecl.sym.isRecord()) {
                jCClassDecl.sym.getRecordComponents().forEach(new Consumer() { // from class: com.sun.tools.javac.code.r
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        TypeAnnotations.TypeAnnotationPositions.a(this.b, (Symbol.RecordComponent) obj);
                    }
                });
            }
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitLambda(JCTree.JCLambda jCLambda) throws Throwable {
            TypeAnnotationPositions typeAnnotationPositions;
            JCTree.JCLambda jCLambda2 = this.currentLambda;
            try {
                this.currentLambda = jCLambda;
                int i = 0;
                for (JCTree.JCVariableDecl jCVariableDecl : jCLambda.params) {
                    if (jCVariableDecl.mods.annotations.isEmpty()) {
                        typeAnnotationPositions = this;
                    } else {
                        TypeAnnotationPosition typeAnnotationPositionMethodParameter = TypeAnnotationPosition.methodParameter(jCLambda, i, jCVariableDecl.vartype.pos);
                        this.push(jCVariableDecl);
                        try {
                            if (jCVariableDecl.declaredUsingVar()) {
                                typeAnnotationPositions = this;
                            } else {
                                JCTree.JCExpression jCExpression = jCVariableDecl.vartype;
                                Symbol.VarSymbol varSymbol = jCVariableDecl.sym;
                                typeAnnotationPositions = this;
                                try {
                                    typeAnnotationPositions.separateAnnotationsKinds(jCVariableDecl, jCExpression, varSymbol.type, varSymbol, typeAnnotationPositionMethodParameter);
                                } catch (Throwable th) {
                                    th = th;
                                    Throwable th2 = th;
                                    typeAnnotationPositions.pop();
                                    throw th2;
                                }
                            }
                            try {
                                typeAnnotationPositions.pop();
                            } catch (Throwable th3) {
                                th = th3;
                                Throwable th4 = th;
                                typeAnnotationPositions.currentLambda = jCLambda2;
                                throw th4;
                            }
                        } catch (Throwable th5) {
                            th = th5;
                            typeAnnotationPositions = this;
                        }
                    }
                    i++;
                    this = typeAnnotationPositions;
                }
                TypeAnnotationPositions typeAnnotationPositions2 = this;
                typeAnnotationPositions2.scan(jCLambda.body);
                typeAnnotationPositions2.scan(jCLambda.params);
                typeAnnotationPositions2.currentLambda = jCLambda2;
            } catch (Throwable th6) {
                th = th6;
                typeAnnotationPositions = this;
            }
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitMethodDef(JCTree.JCMethodDecl jCMethodDecl) {
            TypeAnnotationPositions typeAnnotationPositions;
            JCTree.JCMethodDecl jCMethodDecl2;
            if (jCMethodDecl.sym == null) {
                Assert.error("Visiting tree node before memberEnter");
            }
            if (this.sigOnly) {
                if (jCMethodDecl.mods.annotations.isEmpty()) {
                    typeAnnotationPositions = this;
                    jCMethodDecl2 = jCMethodDecl;
                } else if (jCMethodDecl.sym.isConstructor()) {
                    separateAnnotationsKinds(jCMethodDecl, jCMethodDecl, null, jCMethodDecl.sym, TypeAnnotationPosition.methodReturn(jCMethodDecl.pos));
                    typeAnnotationPositions = this;
                    jCMethodDecl2 = jCMethodDecl;
                } else {
                    typeAnnotationPositions = this;
                    jCMethodDecl2 = jCMethodDecl;
                    typeAnnotationPositions.separateAnnotationsKinds(jCMethodDecl2, jCMethodDecl2.restype, jCMethodDecl2.sym.type.mo73getReturnType(), jCMethodDecl2.sym, TypeAnnotationPosition.methodReturn(jCMethodDecl2.restype.pos));
                }
                JCTree.JCVariableDecl jCVariableDecl = jCMethodDecl2.recvparam;
                if (jCVariableDecl != null && jCVariableDecl.sym != null && !jCVariableDecl.mods.annotations.isEmpty()) {
                    TypeAnnotationPosition typeAnnotationPositionMethodReceiver = TypeAnnotationPosition.methodReceiver(jCMethodDecl2.recvparam.vartype.pos);
                    typeAnnotationPositions.push(jCMethodDecl2.recvparam);
                    try {
                        JCTree.JCVariableDecl jCVariableDecl2 = jCMethodDecl2.recvparam;
                        JCTree.JCExpression jCExpression = jCVariableDecl2.vartype;
                        Symbol.VarSymbol varSymbol = jCVariableDecl2.sym;
                        typeAnnotationPositions.separateAnnotationsKinds(jCMethodDecl2, jCExpression, varSymbol.type, varSymbol, typeAnnotationPositionMethodReceiver);
                        typeAnnotationPositions.pop();
                    } catch (Throwable th) {
                        typeAnnotationPositions.pop();
                        throw th;
                    }
                }
                int i = 0;
                for (JCTree.JCVariableDecl jCVariableDecl3 : jCMethodDecl2.params) {
                    if (!jCVariableDecl3.mods.annotations.isEmpty()) {
                        TypeAnnotationPosition typeAnnotationPositionMethodParameter = TypeAnnotationPosition.methodParameter(i, jCVariableDecl3.vartype.pos);
                        typeAnnotationPositions.push(jCVariableDecl3);
                        try {
                            JCTree.JCExpression jCExpression2 = jCVariableDecl3.vartype;
                            Symbol.VarSymbol varSymbol2 = jCVariableDecl3.sym;
                            typeAnnotationPositions.separateAnnotationsKinds(jCVariableDecl3, jCExpression2, varSymbol2.type, varSymbol2, typeAnnotationPositionMethodParameter);
                            typeAnnotationPositions.pop();
                        } catch (Throwable th2) {
                            typeAnnotationPositions.pop();
                            throw th2;
                        }
                    }
                    i++;
                }
            } else {
                typeAnnotationPositions = this;
                jCMethodDecl2 = jCMethodDecl;
            }
            if (!typeAnnotationPositions.sigOnly) {
                typeAnnotationPositions.scan(jCMethodDecl2.defaultValue);
                typeAnnotationPositions.scan(jCMethodDecl2.body);
                return;
            }
            typeAnnotationPositions.scan(jCMethodDecl2.mods);
            typeAnnotationPositions.scan(jCMethodDecl2.restype);
            typeAnnotationPositions.scan(jCMethodDecl2.typarams);
            typeAnnotationPositions.scan(jCMethodDecl2.recvparam);
            typeAnnotationPositions.scan(jCMethodDecl2.params);
            typeAnnotationPositions.scan(jCMethodDecl2.thrown);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitNewArray(JCTree.JCNewArray jCNewArray) {
            findPosition(jCNewArray, jCNewArray, jCNewArray.annotations);
            int size = jCNewArray.dimAnnotations.size();
            ListBuffer listBuffer = new ListBuffer();
            for (int i = 0; i < size; i++) {
                ListBuffer listBuffer2 = new ListBuffer();
                if (i != 0) {
                    listBuffer = listBuffer.append(TypeAnnotationPosition.TypePathEntry.ARRAY);
                    listBuffer2 = listBuffer2.appendList(listBuffer.toList());
                }
                setTypeAnnotationPos(jCNewArray.dimAnnotations.get(i), TypeAnnotationPosition.newObj(listBuffer2.toList(), this.currentLambda, jCNewArray.pos));
            }
            JCTree.JCExpression jCExpression = jCNewArray.elemtype;
            ListBuffer listBufferAppend = listBuffer.append(TypeAnnotationPosition.TypePathEntry.ARRAY);
            while (jCExpression != null) {
                if (jCExpression.hasTag(JCTree.Tag.ANNOTATED_TYPE)) {
                    JCTree.JCAnnotatedType jCAnnotatedType = (JCTree.JCAnnotatedType) jCExpression;
                    setTypeAnnotationPos(jCAnnotatedType.annotations, TypeAnnotationPosition.newObj(locateNestedTypes(jCExpression.type, new ListBuffer<>()).toList().prependList(listBufferAppend.toList()), this.currentLambda, jCNewArray.pos));
                    jCExpression = jCAnnotatedType.underlyingType;
                } else if (jCExpression.hasTag(JCTree.Tag.TYPEARRAY)) {
                    listBufferAppend = listBufferAppend.append(TypeAnnotationPosition.TypePathEntry.ARRAY);
                    jCExpression = ((JCTree.JCArrayTypeTree) jCExpression).elemtype;
                } else if (!jCExpression.hasTag(JCTree.Tag.SELECT)) {
                    break;
                } else {
                    jCExpression = ((JCTree.JCFieldAccess) jCExpression).selected;
                }
            }
            scan(jCNewArray.elems);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitNewClass(JCTree.JCNewClass jCNewClass) {
            JCTree.JCClassDecl jCClassDecl = jCNewClass.def;
            if (jCClassDecl != null && jCClassDecl.sym != null) {
                propagateNewClassAnnotationsToOwner(jCNewClass);
            }
            scan(jCNewClass.encl);
            scan(jCNewClass.typeargs);
            if (jCNewClass.def == null) {
                scan(jCNewClass.clazz);
            }
            scan(jCNewClass.args);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitTypeParameter(JCTree.JCTypeParameter jCTypeParameter) {
            findPosition(jCTypeParameter, peek2(), jCTypeParameter.annotations);
            super.visitTypeParameter(jCTypeParameter);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitVarDef(JCTree.JCVariableDecl jCVariableDecl) {
            TypeAnnotationPositions typeAnnotationPositions;
            JCTree.JCVariableDecl jCVariableDecl2;
            Symbol.VarSymbol varSymbol;
            if (jCVariableDecl.mods.annotations.isEmpty()) {
                typeAnnotationPositions = this;
                jCVariableDecl2 = jCVariableDecl;
            } else {
                Symbol.VarSymbol varSymbol2 = jCVariableDecl.sym;
                if (varSymbol2 == null) {
                    Assert.error("Visiting tree node before memberEnter");
                } else if (varSymbol2.getKind() != ElementKind.PARAMETER) {
                    if (jCVariableDecl.sym.getKind() != ElementKind.FIELD) {
                        typeAnnotationPositions = this;
                        jCVariableDecl2 = jCVariableDecl;
                        if (jCVariableDecl2.sym.getKind() == ElementKind.LOCAL_VARIABLE) {
                            TypeAnnotationPosition typeAnnotationPositionLocalVariable = TypeAnnotationPosition.localVariable(typeAnnotationPositions.currentLambda, jCVariableDecl2.pos);
                            if (!jCVariableDecl2.declaredUsingVar()) {
                                JCTree.JCExpression jCExpression = jCVariableDecl2.vartype;
                                Symbol.VarSymbol varSymbol3 = jCVariableDecl2.sym;
                                typeAnnotationPositions.separateAnnotationsKinds(jCVariableDecl2, jCExpression, varSymbol3.type, varSymbol3, typeAnnotationPositionLocalVariable);
                            }
                        } else if (jCVariableDecl2.sym.getKind() == ElementKind.BINDING_VARIABLE) {
                            TypeAnnotationPosition typeAnnotationPositionLocalVariable2 = TypeAnnotationPosition.localVariable(typeAnnotationPositions.currentLambda, jCVariableDecl2.pos);
                            JCTree.JCExpression jCExpression2 = jCVariableDecl2.vartype;
                            Symbol.VarSymbol varSymbol4 = jCVariableDecl2.sym;
                            typeAnnotationPositions.separateAnnotationsKinds(jCVariableDecl2, jCExpression2, varSymbol4.type, varSymbol4, typeAnnotationPositionLocalVariable2);
                        } else if (jCVariableDecl2.sym.getKind() == ElementKind.EXCEPTION_PARAMETER) {
                            TypeAnnotationPosition typeAnnotationPositionExceptionParameter = TypeAnnotationPosition.exceptionParameter(typeAnnotationPositions.currentLambda, jCVariableDecl2.pos);
                            JCTree.JCExpression jCExpression3 = jCVariableDecl2.vartype;
                            Symbol.VarSymbol varSymbol5 = jCVariableDecl2.sym;
                            typeAnnotationPositions.separateAnnotationsKinds(jCVariableDecl2, jCExpression3, varSymbol5.type, varSymbol5, typeAnnotationPositionExceptionParameter);
                        } else if (jCVariableDecl2.sym.getKind() == ElementKind.RESOURCE_VARIABLE) {
                            TypeAnnotationPosition typeAnnotationPositionResourceVariable = TypeAnnotationPosition.resourceVariable(typeAnnotationPositions.currentLambda, jCVariableDecl2.pos);
                            JCTree.JCExpression jCExpression4 = jCVariableDecl2.vartype;
                            Symbol.VarSymbol varSymbol6 = jCVariableDecl2.sym;
                            typeAnnotationPositions.separateAnnotationsKinds(jCVariableDecl2, jCExpression4, varSymbol6.type, varSymbol6, typeAnnotationPositionResourceVariable);
                        } else if (jCVariableDecl2.sym.getKind() != ElementKind.ENUM_CONSTANT) {
                            Assert.error("Unhandled variable kind: " + jCVariableDecl2.sym.getKind());
                        }
                    } else if (this.sigOnly) {
                        TypeAnnotationPosition typeAnnotationPositionField = TypeAnnotationPosition.field(jCVariableDecl.pos);
                        JCTree.JCExpression jCExpression5 = jCVariableDecl.vartype;
                        Symbol.VarSymbol varSymbol7 = jCVariableDecl.sym;
                        separateAnnotationsKinds(jCVariableDecl, jCExpression5, varSymbol7.type, varSymbol7, typeAnnotationPositionField);
                        typeAnnotationPositions = this;
                        jCVariableDecl2 = jCVariableDecl;
                    }
                }
                typeAnnotationPositions = this;
                jCVariableDecl2 = jCVariableDecl;
            }
            typeAnnotationPositions.scan(jCVariableDecl2.mods);
            typeAnnotationPositions.scan(jCVariableDecl2.vartype);
            if (!typeAnnotationPositions.sigOnly) {
                typeAnnotationPositions.scan(jCVariableDecl2.init);
            }
            if (typeAnnotationPositions.sigOnly && (varSymbol = jCVariableDecl2.sym) != null && varSymbol.getKind() == ElementKind.FIELD) {
                Symbol.VarSymbol varSymbol8 = jCVariableDecl2.sym;
                if ((varSymbol8.flags_field & Flags.RECORD) != 0) {
                    Symbol.RecordComponent recordComponent = ((Symbol.ClassSymbol) varSymbol8.owner).getRecordComponent(varSymbol8);
                    recordComponent.setTypeAttributes(jCVariableDecl2.sym.getRawTypeAttributes());
                    recordComponent.type = jCVariableDecl2.sym.type;
                }
            }
        }

        private Type typeWithAnnotations(Type type, final Type type2, List<Attribute.TypeCompound> list) {
            return (Type) type.accept((Type.Visitor<R, List<Attribute.TypeCompound>>) new Type.Visitor<Type, List<Attribute.TypeCompound>>(this) { // from class: com.sun.tools.javac.code.TypeAnnotations.TypeAnnotationPositions.1
                final /* synthetic */ TypeAnnotationPositions this$1;

                {
                    this.this$1 = this;
                }

                @Override // com.sun.tools.javac.code.Type.Visitor
                public Type visitArrayType(Type.ArrayType arrayType, List<Attribute.TypeCompound> list2) {
                    return new Type.ArrayType((Type) arrayType.elemtype.accept(this, list2), arrayType.tsym, arrayType.getMetadata());
                }

                @Override // com.sun.tools.javac.code.Type.Visitor
                public Type visitClassType(Type.ClassType classType, List<Attribute.TypeCompound> list2) {
                    if (classType == type2 || classType.getEnclosingType() == Type.noType) {
                        return classType.annotatedType(list2);
                    }
                    Type.ClassType classType2 = new Type.ClassType((Type) classType.getEnclosingType().accept(this, list2), classType.typarams_field, classType.tsym, classType.getMetadata());
                    classType2.all_interfaces_field = classType.all_interfaces_field;
                    classType2.allparams_field = classType.allparams_field;
                    classType2.interfaces_field = classType.interfaces_field;
                    classType2.rank_field = classType.rank_field;
                    classType2.supertype_field = classType.supertype_field;
                    return classType2;
                }

                @Override // com.sun.tools.javac.code.Type.Visitor
                public Type visitCapturedType(Type.CapturedType capturedType, List<Attribute.TypeCompound> list2) {
                    return capturedType.annotatedType(list2);
                }

                @Override // com.sun.tools.javac.code.Type.Visitor
                public Type visitErrorType(Type.ErrorType errorType, List<Attribute.TypeCompound> list2) {
                    return errorType.annotatedType(list2);
                }

                @Override // com.sun.tools.javac.code.Type.Visitor
                public Type visitForAll(Type.ForAll forAll, List<Attribute.TypeCompound> list2) {
                    return forAll;
                }

                @Override // com.sun.tools.javac.code.Type.Visitor
                public Type visitMethodType(Type.MethodType methodType, List<Attribute.TypeCompound> list2) {
                    return methodType;
                }

                @Override // com.sun.tools.javac.code.Type.Visitor
                public Type visitModuleType(Type.ModuleType moduleType, List<Attribute.TypeCompound> list2) {
                    return moduleType.annotatedType(list2);
                }

                @Override // com.sun.tools.javac.code.Type.Visitor
                public Type visitPackageType(Type.PackageType packageType, List<Attribute.TypeCompound> list2) {
                    return packageType;
                }

                @Override // com.sun.tools.javac.code.Type.Visitor
                public Type visitType(Type type3, List<Attribute.TypeCompound> list2) {
                    return type3.annotatedType(list2);
                }

                @Override // com.sun.tools.javac.code.Type.Visitor
                public Type visitTypeVar(Type.TypeVar typeVar, List<Attribute.TypeCompound> list2) {
                    return typeVar.annotatedType(list2);
                }

                @Override // com.sun.tools.javac.code.Type.Visitor
                public Type visitUndetVar(Type.UndetVar undetVar, List<Attribute.TypeCompound> list2) {
                    return undetVar;
                }

                @Override // com.sun.tools.javac.code.Type.Visitor
                public Type visitWildcardType(Type.WildcardType wildcardType, List<Attribute.TypeCompound> list2) {
                    return wildcardType.annotatedType(list2);
                }
            }, list);
        }
    }
}
