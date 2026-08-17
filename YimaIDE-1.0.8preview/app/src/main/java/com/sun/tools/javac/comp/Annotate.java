package com.sun.tools.javac.comp;

import com.sun.tools.javac.code.Attribute;
import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.code.Kinds;
import com.sun.tools.javac.code.Scope;
import com.sun.tools.javac.code.Source;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.Symtab;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.code.TypeAnnotationPosition;
import com.sun.tools.javac.code.TypeMetadata;
import com.sun.tools.javac.code.TypeTag;
import com.sun.tools.javac.code.Types;
import com.sun.tools.javac.comp.Annotate;
import com.sun.tools.javac.resources.CompilerProperties;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeInfo;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.tree.TreeScanner;
import com.sun.tools.javac.util.Assert;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.JCDiagnostic;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.ListBuffer;
import com.sun.tools.javac.util.Log;
import com.sun.tools.javac.util.Name;
import com.sun.tools.javac.util.Names;
import com.sun.tools.javac.util.Pair;
import com.sun.tools.javac.util.Warner;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import javax.tools.JavaFileObject;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Annotate {
    protected static final Context.Key<Annotate> annotateKey = new Context.Key<>();
    private final Attr attr;
    private int blockCount;
    private final ConstFold cfolder;
    private final Check chk;
    private final Enter enter;
    private final Log log;
    private final TreeMaker make;
    private final Names names;
    private final Resolve resolve;
    private final String sourceName;
    private final Symtab syms;
    private final Attribute theUnfinishedDefaultValue;
    private final TypeEnvs typeEnvs;
    private final Types types;
    private ListBuffer<Runnable> q = new ListBuffer<>();
    private ListBuffer<Runnable> validateQ = new ListBuffer<>();
    private int flushCount = 0;
    ListBuffer<Runnable> typesQ = new ListBuffer<>();
    ListBuffer<Runnable> afterTypesQ = new ListBuffer<>();
    private AnnotationTypeCompleter theSourceCompleter = new AnnotationTypeCompleter() { // from class: com.sun.tools.javac.comp.Annotate.2
        @Override // com.sun.tools.javac.comp.Annotate.AnnotationTypeCompleter
        public void complete(Symbol.ClassSymbol classSymbol) throws Throwable {
            Annotate.this.attributeAnnotationType(Annotate.this.typeEnvs.get(classSymbol));
        }
    };

    public class AnnotationContext<T extends Attribute.Compound> {
        public final Map<Symbol.TypeSymbol, ListBuffer<T>> annotated;
        public final Env<AttrContext> env;
        public final boolean isTypeCompound;
        public final Map<T, JCDiagnostic.DiagnosticPosition> pos;

        public AnnotationContext(Env<AttrContext> env, Map<Symbol.TypeSymbol, ListBuffer<T>> map, Map<T, JCDiagnostic.DiagnosticPosition> map2, boolean z) {
            Assert.checkNonNull(env);
            Assert.checkNonNull(map);
            Assert.checkNonNull(map2);
            this.env = env;
            this.annotated = map;
            this.pos = map2;
            this.isTypeCompound = z;
        }
    }

    public interface AnnotationTypeCompleter {
        void complete(Symbol.ClassSymbol classSymbol) throws Symbol.CompletionFailure;
    }

    public static class AnnotationTypeMetadata {
        private static final AnnotationTypeMetadata NOT_AN_ANNOTATION_TYPE = new AnnotationTypeMetadata(null, 0 == true ? 1 : 0) { // from class: com.sun.tools.javac.comp.Annotate.AnnotationTypeMetadata.1
            @Override // com.sun.tools.javac.comp.Annotate.AnnotationTypeMetadata
            public void complete() {
            }

            @Override // com.sun.tools.javac.comp.Annotate.AnnotationTypeMetadata
            public Set<Symbol.MethodSymbol> getAnnotationElements() {
                return new LinkedHashSet(0);
            }

            @Override // com.sun.tools.javac.comp.Annotate.AnnotationTypeMetadata
            public Set<Symbol.MethodSymbol> getAnnotationElementsWithDefault() {
                return new LinkedHashSet(0);
            }

            @Override // com.sun.tools.javac.comp.Annotate.AnnotationTypeMetadata
            public Attribute.Compound getRepeatable() {
                return null;
            }

            @Override // com.sun.tools.javac.comp.Annotate.AnnotationTypeMetadata
            public Attribute.Compound getTarget() {
                return null;
            }

            @Override // com.sun.tools.javac.comp.Annotate.AnnotationTypeMetadata
            public boolean isMetadataForAnnotationType() {
                return false;
            }

            @Override // com.sun.tools.javac.comp.Annotate.AnnotationTypeMetadata
            public String toString() {
                return "Not an annotation type";
            }
        };
        private AnnotationTypeCompleter annotationTypeCompleter;
        final Symbol.ClassSymbol metaDataFor;
        private Attribute.Compound repeatable;
        private Attribute.Compound target;

        public AnnotationTypeMetadata(Symbol.ClassSymbol classSymbol, AnnotationTypeCompleter annotationTypeCompleter) {
            this.metaDataFor = classSymbol;
            this.annotationTypeCompleter = annotationTypeCompleter;
        }

        private void init() {
            while (!this.metaDataFor.isCompleted()) {
                this.metaDataFor.complete();
            }
            AnnotationTypeCompleter annotationTypeCompleter = this.annotationTypeCompleter;
            if (annotationTypeCompleter != null) {
                this.annotationTypeCompleter = null;
                annotationTypeCompleter.complete(this.metaDataFor);
            }
        }

        public static AnnotationTypeMetadata notAnAnnotationType() {
            return NOT_AN_ANNOTATION_TYPE;
        }

        public void complete() {
            init();
        }

        public Set<Symbol.MethodSymbol> getAnnotationElements() {
            init();
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            for (Symbol symbol : this.metaDataFor.members().getSymbols(Scope.LookupKind.NON_RECURSIVE)) {
                if (symbol.kind == Kinds.Kind.MTH) {
                    Name name = symbol.name;
                    if (name != name.table.names.clinit && (symbol.flags() & 4096) == 0) {
                        linkedHashSet.add((Symbol.MethodSymbol) symbol);
                    }
                }
            }
            return linkedHashSet;
        }

        public Set<Symbol.MethodSymbol> getAnnotationElementsWithDefault() {
            init();
            Set<Symbol.MethodSymbol> annotationElements = getAnnotationElements();
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            for (Symbol.MethodSymbol methodSymbol : annotationElements) {
                if (methodSymbol.defaultValue != null) {
                    linkedHashSet.add(methodSymbol);
                }
            }
            return linkedHashSet;
        }

        public Attribute.Compound getRepeatable() {
            init();
            return this.repeatable;
        }

        public Attribute.Compound getTarget() {
            init();
            return this.target;
        }

        public boolean isMetadataForAnnotationType() {
            return true;
        }

        public void setRepeatable(Attribute.Compound compound) {
            Assert.checkNull(this.repeatable);
            this.repeatable = compound;
        }

        public void setTarget(Attribute.Compound compound) {
            Assert.checkNull(this.target);
            this.target = compound;
        }

        public String toString() {
            return "Annotation type for: " + this.metaDataFor;
        }
    }

    public class AnnotationTypeVisitor extends TreeScanner {
        private final Attr attr;
        private final Check check;
        private Env<AttrContext> env;
        private Attribute.Compound repeatable;
        private final Symtab tab;
        private Attribute.Compound target;
        private final TypeEnvs typeEnvs;

        public AnnotationTypeVisitor(Attr attr, Check check, Symtab symtab, TypeEnvs typeEnvs) {
            this.attr = attr;
            this.check = check;
            this.tab = symtab;
            this.typeEnvs = typeEnvs;
        }

        public Attribute.Compound getRepeatable() {
            return this.repeatable;
        }

        public Attribute.Compound getTarget() {
            return this.target;
        }

        public void scanAnnotationType(JCTree.JCClassDecl jCClassDecl) {
            visitClassDef(jCClassDecl);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitAnnotation(JCTree.JCAnnotation jCAnnotation) {
            JCTree jCTree = jCAnnotation.annotationType;
            Type type = jCTree.type;
            if (type == null) {
                Type typeAttribType = this.attr.attribType(jCTree, this.env);
                JCTree jCTree2 = jCAnnotation.annotationType;
                Type typeCheckType = this.check.checkType(jCTree2.pos(), typeAttribType, this.tab.annotationType);
                jCTree2.type = typeCheckType;
                type = typeCheckType;
            }
            Symtab symtab = this.tab;
            Type type2 = symtab.annotationTargetType;
            if (type == type2) {
                this.target = Annotate.this.attributeAnnotation(jCAnnotation, type2, this.env);
                return;
            }
            Type type3 = symtab.repeatableType;
            if (type == type3) {
                this.repeatable = Annotate.this.attributeAnnotation(jCAnnotation, type3, this.env);
            }
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitClassDef(JCTree.JCClassDecl jCClassDecl) {
            Env<AttrContext> env = this.env;
            this.env = this.typeEnvs.get(jCClassDecl.sym);
            try {
                scan(jCClassDecl.mods);
            } finally {
                this.env = env;
            }
        }
    }

    public class AnnotationValueContext extends Check.NestedCheckContext {
        public AnnotationValueContext(Check.CheckContext checkContext) {
            super(checkContext);
        }

        @Override // com.sun.tools.javac.comp.Check.NestedCheckContext, com.sun.tools.javac.comp.Check.CheckContext
        public boolean compatible(Type type, Type type2, Warner warner) {
            return type.hasTag(TypeTag.NONE) || super.compatible(type, type2, warner);
        }
    }

    public class TypeAnnotate extends TreeScanner {
        private final Env<AttrContext> env;
        private final Symbol sym;

        public TypeAnnotate(Env<AttrContext> env, Symbol symbol) {
            this.env = env;
            this.sym = symbol;
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitAnnotatedType(JCTree.JCAnnotatedType jCAnnotatedType) {
            Annotate.this.enterTypeAnnotations(jCAnnotatedType.annotations, this.env, this.sym, false);
            scan(jCAnnotatedType.underlyingType);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitBindingPattern(JCTree.JCBindingPattern jCBindingPattern) {
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitClassDef(JCTree.JCClassDecl jCClassDecl) {
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitErroneous(JCTree.JCErroneous jCErroneous) {
            List<? extends JCTree> list = jCErroneous.errs;
            if (list != null) {
                Iterator<? extends JCTree> it = list.iterator();
                while (it.hasNext()) {
                    scan(it.next());
                }
            }
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitMethodDef(JCTree.JCMethodDecl jCMethodDecl) {
            scan(jCMethodDecl.mods);
            scan(jCMethodDecl.restype);
            scan(jCMethodDecl.typarams);
            scan(jCMethodDecl.recvparam);
            scan(jCMethodDecl.params);
            scan(jCMethodDecl.thrown);
            scan(jCMethodDecl.defaultValue);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitNewArray(JCTree.JCNewArray jCNewArray) {
            Annotate.this.enterTypeAnnotations(jCNewArray.annotations, this.env, this.sym, false);
            Iterator<List<JCTree.JCAnnotation>> it = jCNewArray.dimAnnotations.iterator();
            while (it.hasNext()) {
                Annotate.this.enterTypeAnnotations(it.next(), this.env, this.sym, false);
            }
            scan(jCNewArray.elemtype);
            scan(jCNewArray.elems);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitNewClass(JCTree.JCNewClass jCNewClass) {
            scan(jCNewClass.encl);
            scan(jCNewClass.typeargs);
            try {
                this.env.info.isAnonymousNewClass = jCNewClass.def != null;
                scan(jCNewClass.clazz);
                this.env.info.isAnonymousNewClass = false;
                scan(jCNewClass.args);
            } catch (Throwable th) {
                this.env.info.isAnonymousNewClass = false;
                throw th;
            }
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitTypeParameter(JCTree.JCTypeParameter jCTypeParameter) {
            Annotate.this.enterTypeAnnotations(jCTypeParameter.annotations, this.env, this.sym, true);
            scan(jCTypeParameter.bounds);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitVarDef(JCTree.JCVariableDecl jCVariableDecl) {
            Symbol symbol = this.sym;
            if (symbol != null && symbol.kind == Kinds.Kind.VAR) {
                scan(jCVariableDecl.mods);
                scan(jCVariableDecl.vartype);
            }
            scan(jCVariableDecl.init);
        }
    }

    public Annotate(Context context) {
        this.blockCount = 0;
        context.put(annotateKey, this);
        this.attr = Attr.instance(context);
        this.chk = Check.instance(context);
        this.cfolder = ConstFold.instance(context);
        this.enter = Enter.instance(context);
        this.log = Log.instance(context);
        this.make = TreeMaker.instance(context);
        this.names = Names.instance(context);
        this.resolve = Resolve.instance(context);
        Symtab symtabInstance = Symtab.instance(context);
        this.syms = symtabInstance;
        this.typeEnvs = TypeEnvs.instance(context);
        this.types = Types.instance(context);
        this.theUnfinishedDefaultValue = new Attribute.Error(symtabInstance.errType);
        this.sourceName = Source.instance(context).name;
        this.blockCount = 1;
    }

    public static /* synthetic */ void a(Annotate annotate, Env env, JCTree.JCExpression jCExpression) {
        JavaFileObject javaFileObjectUseSource = annotate.log.useSource(env.toplevel.sourcefile);
        try {
            annotate.chk.validateAnnotationTree(jCExpression);
        } finally {
            annotate.log.useSource(javaFileObjectUseSource);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <T extends Attribute.Compound> void annotateNow(Symbol symbol, List<JCTree.JCAnnotation> list, Env<AttrContext> env, boolean z, boolean z2) {
        Annotate annotate;
        Env<AttrContext> env2;
        boolean z3;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        HashMap map = new HashMap();
        for (List list2 = list; !list2.isEmpty(); list2 = list2.tail) {
            JCTree.JCAnnotation jCAnnotation = (JCTree.JCAnnotation) list2.head;
            Symtab symtab = this.syms;
            Attribute.Compound compoundAttributeTypeAnnotation = z ? attributeTypeAnnotation(jCAnnotation, symtab.annotationType, env) : attributeAnnotation(jCAnnotation, symtab.annotationType, env);
            Assert.checkNonNull(compoundAttributeTypeAnnotation, "Failed to create annotation");
            if (!env.info.isAnonymousNewClass) {
                if (jCAnnotation.type.isErroneous() || jCAnnotation.type.tsym.isAnnotationType()) {
                    boolean zContainsKey = linkedHashMap.containsKey(jCAnnotation.type.tsym);
                    Type type = jCAnnotation.type;
                    if (zContainsKey) {
                        linkedHashMap.put(jCAnnotation.type.tsym, ((ListBuffer) linkedHashMap.get(type.tsym)).append(compoundAttributeTypeAnnotation));
                        map.put(compoundAttributeTypeAnnotation, jCAnnotation.pos());
                    } else {
                        linkedHashMap.put(type.tsym, ListBuffer.of(compoundAttributeTypeAnnotation));
                        map.put(compoundAttributeTypeAnnotation, jCAnnotation.pos());
                    }
                }
                if (!compoundAttributeTypeAnnotation.type.isErroneous() && ((symbol.kind == Kinds.Kind.MDL || symbol.owner.kind != Kinds.Kind.MTH) && this.types.isSameType(compoundAttributeTypeAnnotation.type, this.syms.deprecatedType))) {
                    symbol.flags_field |= 18014398509613056L;
                    if (isAttributeTrue(compoundAttributeTypeAnnotation.member(this.names.forRemoval))) {
                        symbol.flags_field |= Flags.DEPRECATED_REMOVAL;
                    }
                }
                if (!compoundAttributeTypeAnnotation.type.isErroneous() && this.types.isSameType(compoundAttributeTypeAnnotation.type, this.syms.previewFeatureType)) {
                    symbol.flags_field |= Flags.PREVIEW_API;
                    if (isAttributeTrue(compoundAttributeTypeAnnotation.member(this.names.reflective))) {
                        symbol.flags_field |= Flags.PREVIEW_REFLECTIVE;
                    }
                }
                if (!compoundAttributeTypeAnnotation.type.isErroneous() && symbol.kind == Kinds.Kind.TYP && this.types.isSameType(compoundAttributeTypeAnnotation.type, this.syms.valueBasedType)) {
                    symbol.flags_field |= 9007199254740992L;
                }
                if (!compoundAttributeTypeAnnotation.type.isErroneous() && this.types.isSameType(compoundAttributeTypeAnnotation.type, this.syms.restrictedType)) {
                    symbol.flags_field |= 4611686018427387904L;
                }
                if (!compoundAttributeTypeAnnotation.type.isErroneous() && symbol.kind == Kinds.Kind.VAR && this.types.isSameType(compoundAttributeTypeAnnotation.type, this.syms.requiresIdentityType)) {
                    symbol.flags_field |= 4611686018427387904L;
                }
            }
        }
        List listNil = List.nil();
        for (ListBuffer listBuffer : linkedHashMap.values()) {
            if (listBuffer.size() == 1) {
                listNil = listNil.prepend((Attribute.Compound) listBuffer.first());
                annotate = this;
                env2 = env;
                z3 = z;
            } else {
                annotate = this;
                env2 = env;
                z3 = z;
                Attribute.Compound compoundMakeContainerAnnotation = annotate.makeContainerAnnotation(listBuffer.toList(), annotate.new AnnotationContext<>(env2, linkedHashMap, map, z3), symbol, z2);
                if (compoundMakeContainerAnnotation != null) {
                    listNil = listNil.prepend(compoundMakeContainerAnnotation);
                }
            }
            this = annotate;
            env = env2;
            z = z3;
        }
        if (z) {
            symbol.appendUniqueTypeAttributes(listNil.reverse());
            return;
        }
        List<Attribute.Compound> listReverse = listNil.reverse();
        symbol.resetAnnotations();
        symbol.setDeclarationAttributes(listReverse);
    }

    private Attr.ResultInfo annotationValueInfo(Type type) {
        Attr.ResultInfo resultInfo = this.attr.unknownExprInfo;
        return resultInfo.dup(type, new AnnotationValueContext(resultInfo.checkContext));
    }

    private Pair<Symbol.MethodSymbol, Attribute> attributeAnnotationNameValuePair(JCTree.JCExpression jCExpression, Type type, boolean z, Env<AttrContext> env, boolean z2) {
        if (!jCExpression.hasTag(JCTree.Tag.ASSIGN)) {
            this.log.error(jCExpression.pos(), CompilerProperties.Errors.AnnotationValueMustBeNameValue);
            Type type2 = this.syms.errType;
            jCExpression.type = type2;
            attributeAnnotationValue(type2, jCExpression, env);
            return null;
        }
        JCTree.JCAssign jCAssign = (JCTree.JCAssign) jCExpression;
        if (!jCAssign.lhs.hasTag(JCTree.Tag.IDENT)) {
            this.log.error(jCExpression.pos(), CompilerProperties.Errors.AnnotationValueMustBeNameValue);
            Type type3 = this.syms.errType;
            jCExpression.type = type3;
            attributeAnnotationValue(type3, jCExpression, env);
            return null;
        }
        JCTree.JCIdent jCIdent = (JCTree.JCIdent) jCAssign.lhs;
        Symbol symbolResolveQualifiedMethod = this.resolve.resolveQualifiedMethod(z2 ? jCAssign.rhs.pos() : jCIdent.pos(), env, type, jCIdent.name, List.nil(), null);
        jCIdent.sym = symbolResolveQualifiedMethod;
        jCIdent.type = symbolResolveQualifiedMethod.type;
        this.chk.checkDeprecated(jCIdent, env.info.scope.owner, symbolResolveQualifiedMethod);
        if (symbolResolveQualifiedMethod.owner != type.tsym && !z) {
            this.log.error(jCIdent.pos(), CompilerProperties.Errors.NoAnnotationMember(jCIdent.name, type));
        }
        Type typeMo73getReturnType = symbolResolveQualifiedMethod.type.mo73getReturnType();
        Attribute attributeAttributeAnnotationValue = attributeAnnotationValue(typeMo73getReturnType, jCAssign.rhs, env);
        jCExpression.type = typeMo73getReturnType;
        if (symbolResolveQualifiedMethod.type.isErroneous()) {
            return null;
        }
        return new Pair<>((Symbol.MethodSymbol) symbolResolveQualifiedMethod, attributeAttributeAnnotationValue);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void attributeAnnotationType(Env<AttrContext> env) throws Throwable {
        Annotate annotate;
        Assert.check(((JCTree.JCClassDecl) env.tree).sym.isAnnotationType(), "Trying to annotation type complete a non-annotation type");
        JavaFileObject javaFileObjectUseSource = this.log.useSource(env.toplevel.sourcefile);
        try {
            JCTree.JCClassDecl jCClassDecl = (JCTree.JCClassDecl) env.tree;
            annotate = this;
            try {
                AnnotationTypeVisitor annotationTypeVisitor = annotate.new AnnotationTypeVisitor(this.attr, this.chk, this.syms, this.typeEnvs);
                annotationTypeVisitor.scanAnnotationType(jCClassDecl);
                jCClassDecl.sym.getAnnotationTypeMetadata().setRepeatable(annotationTypeVisitor.repeatable);
                jCClassDecl.sym.getAnnotationTypeMetadata().setTarget(annotationTypeVisitor.target);
                annotate.log.useSource(javaFileObjectUseSource);
            } catch (Throwable th) {
                th = th;
                Throwable th2 = th;
                annotate.log.useSource(javaFileObjectUseSource);
                throw th2;
            }
        } catch (Throwable th3) {
            th = th3;
            annotate = this;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Attribute attributeAnnotationValue(Type type, JCTree.JCExpression jCExpression, Env<AttrContext> env) {
        try {
            type.tsym.complete();
        } catch (Symbol.CompletionFailure e) {
            this.log.error(jCExpression.pos(), CompilerProperties.Errors.CantResolve(Kinds.kindName(e.sym), e.sym.getQualifiedName(), null, null));
            type = this.syms.errType;
        }
        if (type.hasTag(TypeTag.ARRAY)) {
            return getAnnotationArrayValue(type, jCExpression, env);
        }
        if (!jCExpression.hasTag(JCTree.Tag.NEWARRAY)) {
            if (type.tsym.isAnnotationType()) {
                if (jCExpression.hasTag(JCTree.Tag.ANNOTATION)) {
                    return attributeAnnotation((JCTree.JCAnnotation) jCExpression, type, env);
                }
                this.log.error(jCExpression.pos(), CompilerProperties.Errors.AnnotationValueMustBeAnnotation);
                type = this.syms.errType;
            }
            if (jCExpression.hasTag(JCTree.Tag.ANNOTATION)) {
                if (!type.isErroneous()) {
                    this.log.error(jCExpression.pos(), CompilerProperties.Errors.AnnotationNotValidForType(type));
                }
                JCTree.JCAnnotation jCAnnotation = (JCTree.JCAnnotation) jCExpression;
                attributeAnnotation(jCAnnotation, this.syms.errType, env);
                return new Attribute.Error(jCAnnotation.annotationType.type);
            }
            MemberEnter.InitTreeVisitor initTreeVisitor = new MemberEnter.InitTreeVisitor() { // from class: com.sun.tools.javac.comp.Annotate.1
                @Override // com.sun.tools.javac.tree.JCTree.Visitor
                public void visitTypeArray(JCTree.JCArrayTypeTree jCArrayTypeTree) {
                }

                @Override // com.sun.tools.javac.tree.JCTree.Visitor
                public void visitTypeIdent(JCTree.JCPrimitiveTypeTree jCPrimitiveTypeTree) {
                }
            };
            jCExpression.accept(initTreeVisitor);
            if (!initTreeVisitor.result) {
                this.log.error(jCExpression.pos(), CompilerProperties.Errors.ExpressionNotAllowableAsAnnotationValue);
                return new Attribute.Error(this.syms.errType);
            }
            if (type.isPrimitive() || (this.types.isSameType(type, this.syms.stringType) && !type.hasTag(TypeTag.ERROR))) {
                return getAnnotationPrimitiveValue(type, jCExpression, env);
            }
            if (type.tsym == this.syms.classType.tsym) {
                return getAnnotationClassValue(type, jCExpression, env);
            }
            if (type.hasTag(TypeTag.CLASS) && (type.tsym.flags() & 16384) != 0) {
                return getAnnotationEnumValue(type, jCExpression, env);
            }
            if (!type.isErroneous()) {
                this.log.error(jCExpression.pos(), CompilerProperties.Errors.AnnotationValueNotAllowableType);
            }
            return new Attribute.Error(this.attr.attribExpr(jCExpression, env, type));
        }
        if (!type.isErroneous()) {
            this.log.error(jCExpression.pos(), CompilerProperties.Errors.AnnotationValueNotAllowableType);
        }
        JCTree.JCNewArray jCNewArray = (JCTree.JCNewArray) jCExpression;
        JCTree.JCExpression jCExpression2 = jCNewArray.elemtype;
        if (jCExpression2 != null) {
            this.log.error(jCExpression2.pos(), CompilerProperties.Errors.NewNotAllowedInAnnotation);
        }
        List list = jCNewArray.elems;
        while (true) {
            boolean zNonEmpty = list.nonEmpty();
            Symtab symtab = this.syms;
            if (!zNonEmpty) {
                return new Attribute.Error(symtab.errType);
            }
            attributeAnnotationValue(symtab.errType, (JCTree.JCExpression) list.head, env);
            list = list.tail;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v12, types: [A, com.sun.tools.javac.tree.JCTree$JCAssign] */
    private List<Pair<Symbol.MethodSymbol, Attribute>> attributeAnnotationValues(JCTree.JCAnnotation jCAnnotation, Type type, Env<AttrContext> env) {
        boolean z;
        JCTree jCTree = jCAnnotation.annotationType;
        Type typeAttribType = jCTree.type;
        if (typeAttribType == null) {
            typeAttribType = this.attr.attribType(jCTree, env);
        }
        Type typeCheckType = this.chk.checkType(jCAnnotation.annotationType.pos(), typeAttribType, type);
        jCAnnotation.type = typeCheckType;
        boolean zIsErroneous = typeCheckType.isErroneous();
        boolean z2 = true;
        if (jCAnnotation.type.tsym.isAnnotationType() || zIsErroneous) {
            z = zIsErroneous;
        } else {
            this.log.error(jCAnnotation.annotationType.pos(), CompilerProperties.Errors.NotAnnotationType(jCAnnotation.type));
            z = true;
        }
        List list = jCAnnotation.args;
        if (list.length() != 1 || ((JCTree.JCExpression) list.head).hasTag(JCTree.Tag.ASSIGN)) {
            z2 = false;
        } else {
            list.head = this.make.at(((JCTree.JCExpression) list.head).pos).Assign(this.make.Ident(this.names.value), (JCTree.JCExpression) list.head);
        }
        boolean z3 = z2;
        ListBuffer listBuffer = new ListBuffer();
        while (list.nonEmpty()) {
            Annotate annotate = this;
            Env<AttrContext> env2 = env;
            Pair<Symbol.MethodSymbol, Attribute> pairAttributeAnnotationNameValuePair = annotate.attributeAnnotationNameValuePair((JCTree.JCExpression) list.head, jCAnnotation.type, z, env2, z3);
            if (pairAttributeAnnotationNameValuePair != null && !pairAttributeAnnotationNameValuePair.fst.type.isErroneous()) {
                listBuffer.append(pairAttributeAnnotationNameValuePair);
            }
            list = list.tail;
            this = annotate;
            env = env2;
        }
        return listBuffer.toList();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void b(Annotate annotate, Symbol symbol, Env env, List list) throws Throwable {
        Annotate annotate2;
        Throwable th;
        annotate.getClass();
        Assert.check(symbol.kind == Kinds.Kind.PCK || symbol.annotationsPendingCompletion());
        JavaFileObject javaFileObjectUseSource = annotate.log.useSource(env.toplevel.sourcefile);
        try {
            if (symbol.hasAnnotations()) {
                try {
                    if (list.nonEmpty()) {
                        annotate.log.error(((JCTree.JCAnnotation) list.head).pos, CompilerProperties.Errors.AlreadyAnnotated(Kinds.kindName(symbol), symbol));
                    }
                } catch (Throwable th2) {
                    th = th2;
                    annotate2 = annotate;
                    annotate2.log.useSource(javaFileObjectUseSource);
                    throw th;
                }
            }
            Assert.checkNonNull(symbol, "Symbol argument to actualEnterAnnotations is null");
            annotate2 = annotate;
            try {
                annotate2.annotateNow(symbol, list, env, false, false);
                annotate2.log.useSource(javaFileObjectUseSource);
            } catch (Throwable th3) {
                th = th3;
                th = th;
                annotate2.log.useSource(javaFileObjectUseSource);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            annotate2 = annotate;
        }
    }

    public static /* synthetic */ void d(Annotate annotate, List list, Type type) {
        List<Attribute.TypeCompound> listFromAnnotations = annotate.fromAnnotations(list);
        Assert.check(list.size() == listFromAnnotations.size());
        TypeMetadata.Annotations annotations = (TypeMetadata.Annotations) type.getMetadata(TypeMetadata.Annotations.class);
        Assert.checkNonNull(annotations);
        Assert.check(annotations.annotationBuffer().isEmpty());
        annotations.annotationBuffer().appendList(listFromAnnotations);
    }

    private void doneFlushing() {
        this.flushCount--;
    }

    public static /* synthetic */ void e(Annotate annotate, JCTree jCTree, Env env, Symbol symbol) {
        annotate.getClass();
        jCTree.accept(annotate.new TypeAnnotate(env, symbol));
    }

    private void enterDefaultValue(JCTree.JCExpression jCExpression, Env<AttrContext> env, Symbol.MethodSymbol methodSymbol) {
        methodSymbol.defaultValue = attributeAnnotationValue(methodSymbol.type.mo73getReturnType(), jCExpression, env);
    }

    private Type extractContainingType(Attribute.Compound compound, JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol.TypeSymbol typeSymbol) {
        if (compound.values.isEmpty()) {
            this.log.error(diagnosticPosition, CompilerProperties.Errors.InvalidRepeatableAnnotation(typeSymbol));
            return null;
        }
        Pair<Symbol.MethodSymbol, Attribute> pair = compound.values.head;
        if (pair.fst.name != this.names.value) {
            this.log.error(diagnosticPosition, CompilerProperties.Errors.InvalidRepeatableAnnotation(typeSymbol));
            return null;
        }
        Attribute attribute = pair.snd;
        if (attribute instanceof Attribute.Class) {
            return ((Attribute.Class) attribute).getValue();
        }
        this.log.error(diagnosticPosition, CompilerProperties.Errors.InvalidRepeatableAnnotation(typeSymbol));
        return null;
    }

    public static /* synthetic */ void f(Annotate annotate, Env env, List list, Symbol symbol) {
        JavaFileObject javaFileObjectUseSource = annotate.log.useSource(env.toplevel.sourcefile);
        try {
            annotate.chk.validateAnnotations(list, TreeInfo.declarationFor(symbol, env.tree), symbol);
        } finally {
            annotate.log.useSource(javaFileObjectUseSource);
        }
    }

    private Type filterSame(Type type, Type type2) {
        if (type == null || type2 == null || !this.types.isSameType(type, type2)) {
            return type;
        }
        return null;
    }

    public static /* synthetic */ void g(Annotate annotate, Env env, JCTree.JCExpression jCExpression, Symbol.MethodSymbol methodSymbol) {
        JavaFileObject javaFileObjectUseSource = annotate.log.useSource(env.toplevel.sourcefile);
        try {
            annotate.enterDefaultValue(jCExpression, env, methodSymbol);
        } finally {
            annotate.log.useSource(javaFileObjectUseSource);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Attribute getAnnotationArrayValue(Type type, JCTree.JCExpression jCExpression, Env<AttrContext> env) {
        if (!jCExpression.hasTag(JCTree.Tag.NEWARRAY)) {
            jCExpression = this.make.at(jCExpression.pos).NewArray(null, List.nil(), List.of(jCExpression));
        }
        JCTree.JCNewArray jCNewArray = (JCTree.JCNewArray) jCExpression;
        List listNil = jCNewArray.elems;
        JCTree.JCExpression jCExpression2 = jCNewArray.elemtype;
        if (jCExpression2 != null) {
            this.log.error(jCExpression2.pos(), CompilerProperties.Errors.NewNotAllowedInAnnotation);
            if (listNil == null) {
                listNil = List.nil();
            }
        }
        ListBuffer listBuffer = new ListBuffer();
        while (listNil.nonEmpty()) {
            listBuffer.append(attributeAnnotationValue(this.types.elemtype(type), (JCTree.JCExpression) listNil.head, env));
            listNil = listNil.tail;
        }
        jCNewArray.type = type;
        return new Attribute.Array(type, (Attribute[]) listBuffer.toArray(new Attribute[listBuffer.length()]));
    }

    private Attribute getAnnotationClassValue(Type type, JCTree.JCExpression jCExpression, Env<AttrContext> env) {
        Type typeAttribTree = this.attr.attribTree(jCExpression, env, annotationValueInfo(type));
        if (!typeAttribTree.isErroneous()) {
            if (TreeInfo.name(jCExpression) == this.names._class) {
                return new Attribute.Class(this.types, ((JCTree.JCFieldAccess) jCExpression).selected.type);
            }
            this.log.error(jCExpression.pos(), CompilerProperties.Errors.AnnotationValueMustBeClassLiteral);
            return new Attribute.Error(this.syms.errType);
        }
        if (TreeInfo.name(jCExpression) == this.names._class) {
            JCTree.JCFieldAccess jCFieldAccess = (JCTree.JCFieldAccess) jCExpression;
            if (jCFieldAccess.selected.type.isErroneous()) {
                Name nameFlatName = jCFieldAccess.selected.type.tsym.flatName();
                Types types = this.types;
                Symtab symtab = this.syms;
                return new Attribute.UnresolvedClass(type, types.createErrorType(nameFlatName, symtab.unknownSymbol, symtab.classType));
            }
        }
        return new Attribute.Error(typeAttribTree.getOriginalType());
    }

    private Attribute getAnnotationEnumValue(Type type, JCTree.JCExpression jCExpression, Env<AttrContext> env) {
        Type typeAttribTree = this.attr.attribTree(jCExpression, env, annotationValueInfo(type));
        Symbol symbol = TreeInfo.symbol(jCExpression);
        if (symbol != null && !TreeInfo.nonstaticSelect(jCExpression) && symbol.kind == Kinds.Kind.VAR && (symbol.flags() & 16384) != 0) {
            return new Attribute.Enum(type, (Symbol.VarSymbol) symbol);
        }
        this.log.error(jCExpression.pos(), CompilerProperties.Errors.EnumAnnotationMustBeEnumConstant);
        return new Attribute.Error(typeAttribTree.getOriginalType());
    }

    private Attribute getAnnotationPrimitiveValue(Type type, JCTree.JCExpression jCExpression, Env<AttrContext> env) {
        Symbol.TypeSymbol typeSymbol;
        Type typeAttribTree = this.attr.attribTree(jCExpression, env, annotationValueInfo(type));
        if (typeAttribTree.isErroneous()) {
            return new Attribute.Error(typeAttribTree.getOriginalType());
        }
        if (typeAttribTree.constValue() == null) {
            this.log.error(jCExpression.pos(), CompilerProperties.Errors.AttributeValueMustBeConstant);
            return new Attribute.Error(type);
        }
        Type type2 = jCExpression.type;
        if (type2 != null && (typeSymbol = type2.tsym) != null) {
            queueScanTreeAndTypeAnnotate(jCExpression, env, typeSymbol);
        }
        return new Attribute.Constant(type, this.cfolder.coerce(typeAttribTree, type).constValue());
    }

    private Type getContainingType(Attribute.Compound compound, JCDiagnostic.DiagnosticPosition diagnosticPosition, boolean z) {
        Type type = compound.type;
        Symbol.TypeSymbol typeSymbol = type.tsym;
        Attribute.Compound repeatable = typeSymbol.getAnnotationTypeMetadata().getRepeatable();
        if (repeatable != null) {
            return filterSame(extractContainingType(repeatable, diagnosticPosition, typeSymbol), type);
        }
        if (!z) {
            return null;
        }
        this.log.error(diagnosticPosition, CompilerProperties.Errors.DuplicateAnnotationMissingContainer(type));
        return null;
    }

    public static Annotate instance(Context context) {
        Annotate annotate = (Annotate) context.get(annotateKey);
        return annotate == null ? new Annotate(context) : annotate;
    }

    private boolean isAttributeTrue(Attribute attribute) {
        if (!(attribute instanceof Attribute.Constant)) {
            return false;
        }
        Attribute.Constant constant = (Attribute.Constant) attribute;
        return constant.type == this.syms.booleanType && ((Integer) constant.value).intValue() != 0;
    }

    private boolean isFlushing() {
        return this.flushCount > 0;
    }

    private <T extends Attribute.Compound> T makeContainerAnnotation(List<T> list, AnnotationContext<T> annotationContext, Symbol symbol, boolean z) {
        ListBuffer<T> listBuffer;
        T t = (T) processRepeatedAnnotations(list, annotationContext, symbol, z);
        if (t != null && (listBuffer = annotationContext.annotated.get(t.type.tsym)) != null) {
            this.log.error(annotationContext.pos.get(listBuffer.first()), CompilerProperties.Errors.InvalidRepeatableAnnotationRepeatedAndContainerPresent(listBuffer.first().type.tsym));
        }
        return t;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <T extends Attribute.Compound> T processRepeatedAnnotations(List<T> list, AnnotationContext<T> annotationContext, Symbol symbol, boolean z) {
        T t = list.head;
        List listNil = List.nil();
        int i = 1;
        Assert.check((list.isEmpty() || list.tail.isEmpty()) ? false : true);
        List list2 = list;
        int i2 = 0;
        Type type = null;
        Type.ArrayType arrayTypeMakeArrayType = null;
        Symbol.MethodSymbol methodSymbolValidateContainer = null;
        Type type2 = null;
        while (!list2.isEmpty()) {
            i2 += i;
            Assert.check((i2 > i || !list2.tail.isEmpty()) ? i : 0);
            Attribute.Compound compound = (Attribute.Compound) list2.head;
            Type type3 = compound.type;
            if (arrayTypeMakeArrayType == null) {
                arrayTypeMakeArrayType = this.types.makeArrayType(type3);
            }
            Type containingType = getContainingType(compound, annotationContext.pos.get(compound), i2 > i ? i : 0);
            if (containingType != null) {
                Assert.check(type == null || containingType == type);
                methodSymbolValidateContainer = validateContainer(containingType, type3, annotationContext.pos.get(compound));
                if (methodSymbolValidateContainer != null) {
                    listNil = listNil.prepend(compound);
                }
                type = containingType;
            }
            list2 = list2.tail;
            type2 = type3;
            i = 1;
        }
        if (!listNil.isEmpty() && type == null) {
            this.log.error(annotationContext.pos.get(list.head), CompilerProperties.Errors.DuplicateAnnotationInvalidRepeated(type2));
            return null;
        }
        if (listNil.isEmpty()) {
            return null;
        }
        List listReverse = listNil.reverse();
        JCDiagnostic.DiagnosticPosition diagnosticPosition = annotationContext.pos.get(t);
        TreeMaker treeMakerAt = this.make.at(diagnosticPosition);
        Pair pair = new Pair(methodSymbolValidateContainer, new Attribute.Array(arrayTypeMakeArrayType, (List<Attribute>) listReverse));
        if (annotationContext.isTypeCompound) {
            Attribute.TypeCompound typeCompound = new Attribute.TypeCompound(type, List.of(pair), ((Attribute.TypeCompound) list.head).position);
            JCTree.JCAnnotation jCAnnotationTypeAnnotation = treeMakerAt.TypeAnnotation(typeCompound);
            if (!this.chk.validateAnnotationDeferErrors(jCAnnotationTypeAnnotation)) {
                this.log.error(jCAnnotationTypeAnnotation.pos(), CompilerProperties.Errors.DuplicateAnnotationInvalidRepeated(type2));
            }
            if (!this.chk.isTypeAnnotation(jCAnnotationTypeAnnotation, z)) {
                this.log.error(diagnosticPosition, z ? CompilerProperties.Errors.InvalidRepeatableAnnotationNotApplicable(type, symbol) : CompilerProperties.Errors.InvalidRepeatableAnnotationNotApplicableInContext(type));
            }
            typeCompound.setSynthesized(true);
            return typeCompound;
        }
        JCTree.JCAnnotation jCAnnotationAnnotation = treeMakerAt.Annotation(new Attribute.Compound(type, List.of(pair)));
        boolean z2 = (symbol.flags_field & Flags.RECORD) != 0 || (symbol.enclClass() != null && symbol.enclClass().isRecord());
        if (!this.chk.annotationApplicable(jCAnnotationAnnotation, symbol) && (!z2 || (z2 && (symbol.flags_field & 16777216) == 0))) {
            this.log.error(jCAnnotationAnnotation.pos(), CompilerProperties.Errors.InvalidRepeatableAnnotationNotApplicable(type, symbol));
        }
        if (!this.chk.validateAnnotationDeferErrors(jCAnnotationAnnotation)) {
            this.log.error(jCAnnotationAnnotation.pos(), CompilerProperties.Errors.DuplicateAnnotationInvalidRepeated(type2));
        }
        T t2 = (T) attributeAnnotation(jCAnnotationAnnotation, type, annotationContext.env);
        t2.setSynthesized(true);
        return t2;
    }

    private void startFlushing() {
        this.flushCount++;
    }

    private Symbol.MethodSymbol validateContainer(Type type, Type type2, JCDiagnostic.DiagnosticPosition diagnosticPosition) {
        boolean z;
        try {
            Iterator<Symbol> it = type.tsym.members().getSymbolsByName(this.names.value).iterator();
            boolean z2 = false;
            Symbol.MethodSymbol methodSymbol = null;
            boolean z3 = false;
            int i = 0;
            while (true) {
                z = true;
                if (!it.hasNext()) {
                    break;
                }
                Symbol next = it.next();
                i++;
                if (i == 1 && next.kind == Kinds.Kind.MTH) {
                    methodSymbol = (Symbol.MethodSymbol) next;
                } else {
                    z3 = true;
                }
            }
            if (z3) {
                this.log.error(diagnosticPosition, CompilerProperties.Errors.InvalidRepeatableAnnotationMultipleValues(type, i));
                return null;
            }
            if (i == 0) {
                this.log.error(diagnosticPosition, CompilerProperties.Errors.InvalidRepeatableAnnotationNoValue(type));
                return null;
            }
            if (methodSymbol.kind != Kinds.Kind.MTH) {
                this.log.error(diagnosticPosition, CompilerProperties.Errors.InvalidRepeatableAnnotationInvalidValue(type));
                z2 = true;
            }
            Type typeMo73getReturnType = methodSymbol.type.mo73getReturnType();
            Type.ArrayType arrayTypeMakeArrayType = this.types.makeArrayType(type2);
            if (this.types.isArray(typeMo73getReturnType) && this.types.isSameType(arrayTypeMakeArrayType, typeMo73getReturnType)) {
                z = z2;
            } else {
                this.log.error(diagnosticPosition, CompilerProperties.Errors.InvalidRepeatableAnnotationValueReturn(type, typeMo73getReturnType, arrayTypeMakeArrayType));
            }
            if (z) {
                return null;
            }
            return methodSymbol;
        } catch (Symbol.CompletionFailure e) {
            this.chk.completionError(diagnosticPosition, e);
            return null;
        }
    }

    public void afterTypes(Runnable runnable) {
        this.afterTypesQ.append(runnable);
    }

    public void annotateDefaultValueLater(final JCTree.JCExpression jCExpression, final Env<AttrContext> env, final Symbol.MethodSymbol methodSymbol) {
        normal(new Runnable() { // from class: g80
            @Override // java.lang.Runnable
            public final void run() {
                Annotate.g(this.b, env, jCExpression, methodSymbol);
            }
        });
        validate(new Runnable() { // from class: h80
            @Override // java.lang.Runnable
            public final void run() {
                Annotate.a(this.b, env, jCExpression);
            }
        });
    }

    public void annotateLater(final List<JCTree.JCAnnotation> list, final Env<AttrContext> env, final Symbol symbol) {
        if (list.isEmpty()) {
            return;
        }
        symbol.resetAnnotations();
        normal(new Runnable() { // from class: e80
            @Override // java.lang.Runnable
            public final void run() throws Throwable {
                Annotate.b(this.b, symbol, env, list);
            }
        });
        validate(new Runnable() { // from class: f80
            @Override // java.lang.Runnable
            public final void run() {
                Annotate.f(this.b, env, list, symbol);
            }
        });
    }

    public void annotateTypeParameterSecondStage(JCTree jCTree, final List<JCTree.JCAnnotation> list) {
        typeAnnotation(new Runnable() { // from class: b80
            @Override // java.lang.Runnable
            public final void run() {
                Annotate annotate = this.b;
                List list2 = list;
                Assert.check(list2.size() == annotate.fromAnnotations(list2).size());
            }
        });
    }

    public void annotateTypeSecondStage(JCTree jCTree, final List<JCTree.JCAnnotation> list, final Type type) {
        typeAnnotation(new Runnable() { // from class: c80
            @Override // java.lang.Runnable
            public final void run() {
                Annotate.d(this.b, list, type);
            }
        });
    }

    public AnnotationTypeCompleter annotationTypeSourceCompleter() {
        return this.theSourceCompleter;
    }

    public boolean annotationsBlocked() {
        return this.blockCount > 0;
    }

    public Attribute.Compound attributeAnnotation(JCTree.JCAnnotation jCAnnotation, Type type, Env<AttrContext> env) {
        Attribute.Compound compound = jCAnnotation.attribute;
        if (compound != null && jCAnnotation.type != null) {
            return compound;
        }
        Attribute.Compound compound2 = new Attribute.Compound(jCAnnotation.type, attributeAnnotationValues(jCAnnotation, type, env));
        jCAnnotation.attribute = compound2;
        return compound2;
    }

    public Attribute.TypeCompound attributeTypeAnnotation(JCTree.JCAnnotation jCAnnotation, Type type, Env<AttrContext> env) {
        Attribute.Compound compound = jCAnnotation.attribute;
        if (compound != null && jCAnnotation.type != null && (compound instanceof Attribute.TypeCompound)) {
            return (Attribute.TypeCompound) compound;
        }
        Attribute.TypeCompound typeCompound = new Attribute.TypeCompound(jCAnnotation.type, attributeAnnotationValues(jCAnnotation, type, env), TypeAnnotationPosition.unknown);
        jCAnnotation.attribute = typeCompound;
        return typeCompound;
    }

    public void blockAnnotations() {
        this.blockCount++;
    }

    public void enterDone() {
        unblockAnnotations();
    }

    public void enterTypeAnnotations(List<JCTree.JCAnnotation> list, Env<AttrContext> env, Symbol symbol, boolean z) {
        Assert.checkNonNull(symbol, "Symbol argument to actualEnterTypeAnnotations is nul/");
        JavaFileObject javaFileObjectUseSource = this.log.useSource(env.toplevel.sourcefile);
        try {
            annotateNow(symbol, list, env, true, z);
        } finally {
            this.log.useSource(javaFileObjectUseSource);
        }
    }

    public void flush() {
        if (annotationsBlocked() || isFlushing()) {
            return;
        }
        startFlushing();
        while (true) {
            try {
                if (!this.q.nonEmpty() && !this.typesQ.nonEmpty() && !this.afterTypesQ.nonEmpty() && !this.validateQ.nonEmpty()) {
                    doneFlushing();
                    return;
                }
                while (this.q.nonEmpty()) {
                    this.q.next().run();
                }
                while (this.typesQ.nonEmpty()) {
                    this.typesQ.next().run();
                }
                while (this.afterTypesQ.nonEmpty()) {
                    this.afterTypesQ.next().run();
                }
                while (this.validateQ.nonEmpty()) {
                    this.validateQ.next().run();
                }
            } catch (Throwable th) {
                doneFlushing();
                throw th;
            }
        }
    }

    public List<Attribute.TypeCompound> fromAnnotations(List<JCTree.JCAnnotation> list) {
        if (list.isEmpty()) {
            return List.nil();
        }
        ListBuffer listBuffer = new ListBuffer();
        for (JCTree.JCAnnotation jCAnnotation : list) {
            Assert.checkNonNull(jCAnnotation.attribute);
            listBuffer.append((Attribute.TypeCompound) jCAnnotation.attribute);
        }
        return listBuffer.toList();
    }

    public void newRound() {
        this.blockCount = 1;
    }

    public void normal(Runnable runnable) {
        this.q.append(runnable);
    }

    public void queueScanTreeAndTypeAnnotate(final JCTree jCTree, final Env<AttrContext> env, final Symbol symbol) {
        Assert.checkNonNull(symbol);
        normal(new Runnable() { // from class: d80
            @Override // java.lang.Runnable
            public final void run() {
                Annotate.e(this.b, jCTree, env, symbol);
            }
        });
    }

    public Queues setQueues(Queues queues) {
        Queues queues2 = new Queues(this.q, this.validateQ, this.typesQ, this.afterTypesQ);
        this.q = queues.q;
        this.typesQ = queues.typesQ;
        this.afterTypesQ = queues.afterTypesQ;
        this.validateQ = queues.validateQ;
        return queues2;
    }

    public void typeAnnotation(Runnable runnable) {
        this.typesQ.append(runnable);
    }

    public void unblockAnnotations() {
        int i = this.blockCount - 1;
        this.blockCount = i;
        if (i == 0) {
            flush();
        }
    }

    public void unblockAnnotationsNoFlush() {
        this.blockCount--;
    }

    public Attribute unfinishedDefaultValue() {
        return this.theUnfinishedDefaultValue;
    }

    public void validate(Runnable runnable) {
        this.validateQ.append(runnable);
    }

    public static class Queues {
        private final ListBuffer<Runnable> afterTypesQ;
        private final ListBuffer<Runnable> q;
        private final ListBuffer<Runnable> typesQ;
        private final ListBuffer<Runnable> validateQ;

        public Queues() {
            this(new ListBuffer(), new ListBuffer(), new ListBuffer(), new ListBuffer());
        }

        public Queues(ListBuffer<Runnable> listBuffer, ListBuffer<Runnable> listBuffer2, ListBuffer<Runnable> listBuffer3, ListBuffer<Runnable> listBuffer4) {
            this.q = listBuffer;
            this.validateQ = listBuffer2;
            this.typesQ = listBuffer3;
            this.afterTypesQ = listBuffer4;
        }
    }
}
