package com.sun.tools.javac.comp;

import com.intellij.psi.PsiKeyword;
import com.sun.source.tree.CaseTree;
import com.sun.source.tree.IdentifierTree;
import com.sun.source.tree.LambdaExpressionTree;
import com.sun.source.tree.MemberReferenceTree;
import com.sun.source.tree.MemberSelectTree;
import com.sun.source.tree.Tree;
import com.sun.source.tree.TreeVisitor;
import com.sun.source.util.SimpleTreeVisitor;
import com.sun.tools.javac.code.Attribute;
import com.sun.tools.javac.code.BoundKind;
import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.code.Kinds;
import com.sun.tools.javac.code.Lint;
import com.sun.tools.javac.code.LintMapper;
import com.sun.tools.javac.code.Preview;
import com.sun.tools.javac.code.Scope;
import com.sun.tools.javac.code.Source;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.Symtab;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.code.TypeAnnotations;
import com.sun.tools.javac.code.TypeTag;
import com.sun.tools.javac.code.Types;
import com.sun.tools.javac.comp.Attr;
import com.sun.tools.javac.comp.DeferredAttr.DeferredTypeMap;
import com.sun.tools.javac.comp.Env;
import com.sun.tools.javac.comp.Resolve.AccessError;
import com.sun.tools.javac.comp.Resolve.MethodReferenceCheck;
import com.sun.tools.javac.comp.Resolve.ResolveDeferredRecoveryMap;
import com.sun.tools.javac.jvm.Target;
import com.sun.tools.javac.resources.CompilerProperties;
import com.sun.tools.javac.tree.EndPosTable;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeInfo;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.tree.TreeScanner;
import com.sun.tools.javac.tree.TreeTranslator;
import com.sun.tools.javac.util.Assert;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.Dependencies;
import com.sun.tools.javac.util.JCDiagnostic;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.ListBuffer;
import com.sun.tools.javac.util.Log;
import com.sun.tools.javac.util.Log.DiscardDiagnosticHandler;
import com.sun.tools.javac.util.Name;
import com.sun.tools.javac.util.Names;
import com.sun.tools.javac.util.Options;
import com.sun.tools.javac.util.Pair;
import com.sun.tools.javac.util.Warner;
import defpackage.s22;
import defpackage.ui0;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;
import javax.lang.model.element.ElementKind;
import javax.tools.JavaFileObject;
import org.eclipse.jdt.internal.compiler.classfmt.ClassFileConstants;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Attr extends JCTree.Visitor {
    protected static final Context.Key<Attr> attrKey = new Context.Key<>();
    static final TypeTag[] primitiveTags = {TypeTag.BYTE, TypeTag.CHAR, TypeTag.SHORT, TypeTag.INT, TypeTag.LONG, TypeTag.FLOAT, TypeTag.DOUBLE, TypeTag.BOOLEAN};
    private final boolean allowPatternSwitch;
    private final boolean allowRecords;
    boolean allowReifiableTypesInInstanceof;
    private final boolean allowUnconditionalPatternsInstanceOf;
    final Analyzer analyzer;
    final Annotate annotate;
    final ArgumentAttr argumentAttr;
    final AttrRecover attrRecover;
    final boolean captureMRefReturnType;
    final ConstFold cfolder;
    final Check chk;
    final DeferredAttr deferredAttr;
    final Dependencies dependencies;
    final JCDiagnostic.Factory diags;
    final Enter enter;
    Env<AttrContext> env;
    final Flow flow;
    final Infer infer;
    final Type.MethodType initBlockType;
    final LintMapper lintMapper;
    final Log log;
    final TreeMaker make;
    final MatchBindingsComputer matchBindingsComputer;
    final MemberEnter memberEnter;
    final ResultInfo methodAttrInfo;
    final Names names;
    final Operators operators;
    final Preview preview;
    final ResultInfo recoveryInfo;
    Type result;
    ResultInfo resultInfo;
    final Resolve rs;
    String sourceName;
    final ResultInfo statInfo;
    final Symtab syms;
    final Target target;
    final TypeAnnotations typeAnnotations;
    final TypeEnter typeEnter;
    final TypeEnvs typeEnvs;
    final Types types;
    final ResultInfo unknownExprInfo;
    final ResultInfo unknownTypeExprInfo;
    final ResultInfo unknownTypeInfo;
    boolean useBeforeDeclarationWarning;
    final ResultInfo varAssignmentInfo;
    private TreeVisitor<Symbol, Env<AttrContext>> identAttributer = new IdentAttributer();
    private JCTree breakTree = null;
    MatchBindingsComputer.MatchBindings matchBindings = MatchBindingsComputer.EMPTY;
    TreeTranslator removeClassParams = new TreeTranslator() { // from class: com.sun.tools.javac.comp.Attr.4
        @Override // com.sun.tools.javac.tree.TreeTranslator, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitTypeApply(JCTree.JCTypeApply jCTypeApply) {
            this.result = translate(jCTypeApply.clazz);
        }
    };
    Types.MapVisitor<JCDiagnostic.DiagnosticPosition> targetChecker = new Types.MapVisitor<JCDiagnostic.DiagnosticPosition>() { // from class: com.sun.tools.javac.comp.Attr.9
        private Symbol.TypeSymbol makeNotionalInterface(Type.IntersectionClassType intersectionClassType, JCDiagnostic.DiagnosticPosition diagnosticPosition) {
            ListBuffer listBuffer = new ListBuffer();
            ListBuffer listBuffer2 = new ListBuffer();
            for (Type type : intersectionClassType.interfaces_field) {
                if (type.isParameterized()) {
                    listBuffer.appendList(type.tsym.type.allparams());
                }
                listBuffer2.append(type.tsym.type);
            }
            Type.IntersectionClassType intersectionClassTypeMakeIntersectionType = Attr.this.types.makeIntersectionType(listBuffer2.toList());
            intersectionClassTypeMakeIntersectionType.allparams_field = listBuffer.toList();
            Symbol.TypeSymbol typeSymbol = intersectionClassTypeMakeIntersectionType.tsym;
            typeSymbol.flags_field |= 512;
            return typeSymbol;
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Type visitClassType(Type.ClassType classType, JCDiagnostic.DiagnosticPosition diagnosticPosition) {
            return classType.isIntersection() ? visitIntersectionClassType((Type.IntersectionClassType) classType, diagnosticPosition) : classType;
        }

        public Type visitIntersectionClassType(Type.IntersectionClassType intersectionClassType, JCDiagnostic.DiagnosticPosition diagnosticPosition) {
            Attr.this.types.findDescriptorSymbol(makeNotionalInterface(intersectionClassType, diagnosticPosition));
            return intersectionClassType;
        }
    };

    /* JADX INFO: renamed from: com.sun.tools.javac.comp.Attr$13, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass13 {
        static final /* synthetic */ int[] $SwitchMap$com$sun$source$tree$MemberReferenceTree$ReferenceMode;
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$javac$code$Kinds$Kind;
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$javac$code$TypeTag;
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag;

        static {
            int[] iArr = new int[TypeTag.values().length];
            $SwitchMap$com$sun$tools$javac$code$TypeTag = iArr;
            try {
                iArr[TypeTag.PACKAGE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.ARRAY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.CLASS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.WILDCARD.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.TYPEVAR.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.ERROR.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            int[] iArr2 = new int[MemberReferenceTree.ReferenceMode.values().length];
            $SwitchMap$com$sun$source$tree$MemberReferenceTree$ReferenceMode = iArr2;
            try {
                iArr2[MemberReferenceTree.ReferenceMode.NEW.ordinal()] = 1;
            } catch (NoSuchFieldError unused7) {
            }
            int[] iArr3 = new int[Kinds.Kind.values().length];
            $SwitchMap$com$sun$tools$javac$code$Kinds$Kind = iArr3;
            try {
                iArr3[Kinds.Kind.ABSENT_MTH.ordinal()] = 1;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$Kinds$Kind[Kinds.Kind.WRONG_MTH.ordinal()] = 2;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$Kinds$Kind[Kinds.Kind.WRONG_MTHS.ordinal()] = 3;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$Kinds$Kind[Kinds.Kind.AMBIGUOUS.ordinal()] = 4;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$Kinds$Kind[Kinds.Kind.HIDDEN.ordinal()] = 5;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$Kinds$Kind[Kinds.Kind.STATICERR.ordinal()] = 6;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$Kinds$Kind[Kinds.Kind.TYP.ordinal()] = 7;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$Kinds$Kind[Kinds.Kind.VAR.ordinal()] = 8;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$Kinds$Kind[Kinds.Kind.MTH.ordinal()] = 9;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$Kinds$Kind[Kinds.Kind.PCK.ordinal()] = 10;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$Kinds$Kind[Kinds.Kind.ERR.ordinal()] = 11;
            } catch (NoSuchFieldError unused18) {
            }
            int[] iArr4 = new int[JCTree.Tag.values().length];
            $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag = iArr4;
            try {
                iArr4[JCTree.Tag.LITERAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.LAMBDA.ordinal()] = 2;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.REFERENCE.ordinal()] = 3;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.PARENS.ordinal()] = 4;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.CONDEXPR.ordinal()] = 5;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.APPLY.ordinal()] = 6;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.NEWCLASS.ordinal()] = 7;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.LABELLED.ordinal()] = 8;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.DOLOOP.ordinal()] = 9;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.WHILELOOP.ordinal()] = 10;
            } catch (NoSuchFieldError unused28) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.FORLOOP.ordinal()] = 11;
            } catch (NoSuchFieldError unused29) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.FOREACHLOOP.ordinal()] = 12;
            } catch (NoSuchFieldError unused30) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.SWITCH.ordinal()] = 13;
            } catch (NoSuchFieldError unused31) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.SWITCH_EXPRESSION.ordinal()] = 14;
            } catch (NoSuchFieldError unused32) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.METHODDEF.ordinal()] = 15;
            } catch (NoSuchFieldError unused33) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.CLASSDEF.ordinal()] = 16;
            } catch (NoSuchFieldError unused34) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.AND.ordinal()] = 17;
            } catch (NoSuchFieldError unused35) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.OR.ordinal()] = 18;
            } catch (NoSuchFieldError unused36) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.VARDEF.ordinal()] = 19;
            } catch (NoSuchFieldError unused37) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.BLOCK.ordinal()] = 20;
            } catch (NoSuchFieldError unused38) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.TOPLEVEL.ordinal()] = 21;
            } catch (NoSuchFieldError unused39) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.MODULEDEF.ordinal()] = 22;
            } catch (NoSuchFieldError unused40) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[JCTree.Tag.PACKAGEDEF.ordinal()] = 23;
            } catch (NoSuchFieldError unused41) {
            }
        }
    }

    public static class BreakAttr extends RuntimeException {
        static final long serialVersionUID = -6924771130405446405L;
        private transient Env<AttrContext> env;

        private BreakAttr(Env<AttrContext> env) {
            this.env = env;
        }
    }

    public enum CheckMode {
        NORMAL,
        NO_TREE_UPDATE { // from class: com.sun.tools.javac.comp.Attr.CheckMode.1
            @Override // com.sun.tools.javac.comp.Attr.CheckMode
            public boolean updateTreeType() {
                return false;
            }
        },
        NO_INFERENCE_HOOK { // from class: com.sun.tools.javac.comp.Attr.CheckMode.2
            @Override // com.sun.tools.javac.comp.Attr.CheckMode
            public boolean installPostInferenceHook() {
                return false;
            }
        };

        public boolean installPostInferenceHook() {
            return true;
        }

        public boolean updateTreeType() {
            return true;
        }
    }

    public class ExpressionLambdaReturnContext extends FunctionalReturnContext {
        boolean expStmtExpected;
        JCTree.JCExpression expr;

        public ExpressionLambdaReturnContext(JCTree.JCExpression jCExpression, Check.CheckContext checkContext) {
            super(checkContext);
            this.expr = jCExpression;
        }

        @Override // com.sun.tools.javac.comp.Attr.FunctionalReturnContext, com.sun.tools.javac.comp.Check.NestedCheckContext, com.sun.tools.javac.comp.Check.CheckContext
        public boolean compatible(Type type, Type type2, Warner warner) {
            if (!type2.hasTag(TypeTag.VOID)) {
                return super.compatible(type, type2, warner);
            }
            this.expStmtExpected = true;
            return TreeInfo.isExpressionStatement(this.expr);
        }

        @Override // com.sun.tools.javac.comp.Attr.FunctionalReturnContext, com.sun.tools.javac.comp.Check.NestedCheckContext, com.sun.tools.javac.comp.Check.CheckContext
        public void report(JCDiagnostic.DiagnosticPosition diagnosticPosition, JCDiagnostic jCDiagnostic) {
            if (this.expStmtExpected) {
                this.enclosingContext.report(diagnosticPosition, Attr.this.diags.fragment(CompilerProperties.Fragments.StatExprExpected));
            } else {
                super.report(diagnosticPosition, jCDiagnostic);
            }
        }
    }

    public class FunctionalReturnContext extends Check.NestedCheckContext {
        public FunctionalReturnContext(Check.CheckContext checkContext) {
            super(checkContext);
        }

        @Override // com.sun.tools.javac.comp.Check.NestedCheckContext, com.sun.tools.javac.comp.Check.CheckContext
        public boolean compatible(Type type, Type type2, Warner warner) {
            return Attr.this.chk.basicHandler.compatible(inferenceContext().asUndetVar(type), inferenceContext().asUndetVar(type2), warner);
        }

        @Override // com.sun.tools.javac.comp.Check.NestedCheckContext, com.sun.tools.javac.comp.Check.CheckContext
        public void report(JCDiagnostic.DiagnosticPosition diagnosticPosition, JCDiagnostic jCDiagnostic) {
            this.enclosingContext.report(diagnosticPosition, Attr.this.diags.fragment(CompilerProperties.Fragments.IncompatibleRetTypeInLambda(jCDiagnostic)));
        }
    }

    public class IdentAttributer extends SimpleTreeVisitor<Symbol, Env<AttrContext>> {
        private IdentAttributer() {
        }

        @Override // com.sun.source.util.SimpleTreeVisitor, com.sun.source.tree.TreeVisitor
        public Symbol visitIdentifier(IdentifierTree identifierTree, Env<AttrContext> env) {
            return Attr.this.rs.findIdent(null, env, (Name) identifierTree.getName(), Kinds.KindSelector.TYP_PCK);
        }

        @Override // com.sun.source.util.SimpleTreeVisitor, com.sun.source.tree.TreeVisitor
        public Symbol visitMemberSelect(MemberSelectTree memberSelectTree, Env<AttrContext> env) {
            Kinds.Kind kind;
            Symbol symbolVisit = visit(memberSelectTree.getExpression(), env);
            if (symbolVisit == null || (kind = symbolVisit.kind) == Kinds.Kind.ERR || kind == Kinds.Kind.ABSENT_TYP || kind == Kinds.Kind.HIDDEN) {
                return symbolVisit;
            }
            Name name = (Name) memberSelectTree.getIdentifier();
            if (symbolVisit.kind == Kinds.Kind.PCK) {
                env.toplevel.packge = (Symbol.PackageSymbol) symbolVisit;
                return Attr.this.rs.findIdentInPackage(null, env, (Symbol.TypeSymbol) symbolVisit, name, Kinds.KindSelector.TYP_PCK);
            }
            env.enclClass.sym = (Symbol.ClassSymbol) symbolVisit;
            return Attr.this.rs.findMemberType(env, symbolVisit.asType(), name, (Symbol.TypeSymbol) symbolVisit);
        }
    }

    public static class LocalInitScanner extends TreeScanner {
        JCDiagnostic.Fragment badInferenceMsg = null;
        boolean needsTarget = true;

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitApply(JCTree.JCMethodInvocation jCMethodInvocation) {
            boolean z = this.needsTarget;
            try {
                this.needsTarget = false;
                super.visitApply(jCMethodInvocation);
            } finally {
                this.needsTarget = z;
            }
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitLambda(JCTree.JCLambda jCLambda) {
            if (this.needsTarget) {
                this.badInferenceMsg = CompilerProperties.Fragments.LocalLambdaMissingTarget;
            }
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitNewArray(JCTree.JCNewArray jCNewArray) {
            if (jCNewArray.elemtype == null && this.needsTarget) {
                this.badInferenceMsg = CompilerProperties.Fragments.LocalArrayMissingTarget;
            }
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitNewClass(JCTree.JCNewClass jCNewClass) {
            boolean z = this.needsTarget;
            try {
                this.needsTarget = false;
                super.visitNewClass(jCNewClass);
            } finally {
                this.needsTarget = z;
            }
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitReference(JCTree.JCMemberReference jCMemberReference) {
            if (this.needsTarget) {
                this.badInferenceMsg = CompilerProperties.Fragments.LocalMrefMissingTarget;
            }
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitTypeCast(JCTree.JCTypeCast jCTypeCast) {
            boolean z = this.needsTarget;
            try {
                this.needsTarget = false;
                super.visitTypeCast(jCTypeCast);
            } finally {
                this.needsTarget = z;
            }
        }
    }

    public class TargetInfo {
        Type descriptor;
        Type target;

        public TargetInfo(Type type, Type type2) {
            this.target = type;
            this.descriptor = type2;
        }
    }

    public final class TypeAnnotationsValidator extends TreeScanner {
        private final boolean sigOnly;

        public TypeAnnotationsValidator(boolean z) {
            this.sigOnly = z;
        }

        private void checkForDeclarationAnnotations(List<? extends JCTree.JCAnnotation> list, Symbol symbol) {
            for (JCTree.JCAnnotation jCAnnotation : list) {
                if (!jCAnnotation.type.isErroneous() && Attr.this.typeAnnotations.annotationTargetType(jCAnnotation, jCAnnotation.attribute, symbol) == TypeAnnotations.AnnotationType.DECLARATION) {
                    Attr.this.log.error(jCAnnotation.pos(), CompilerProperties.Errors.AnnotationTypeNotApplicableToType(jCAnnotation.type));
                }
            }
        }

        private void validateAnnotatedType(JCTree jCTree, Type type) {
            if (type.isPrimitiveOrVoid()) {
                return;
            }
            JCTree expression = jCTree;
            Type enclosingType = type;
            boolean z = true;
            while (z) {
                if (expression.hasTag(JCTree.Tag.TYPEAPPLY)) {
                    List<Type> typeArguments = enclosingType.getTypeArguments();
                    JCTree.JCTypeApply jCTypeApply = (JCTree.JCTypeApply) expression;
                    List<JCTree.JCExpression> typeArguments2 = jCTypeApply.getTypeArguments();
                    if (typeArguments2.length() > 0 && typeArguments.length() == typeArguments2.length()) {
                        for (int i = 0; i < typeArguments.length(); i++) {
                            validateAnnotatedType(typeArguments2.get(i), typeArguments.get(i));
                        }
                    }
                    expression = jCTypeApply.clazz;
                }
                if (expression.hasTag(JCTree.Tag.SELECT)) {
                    expression = ((JCTree.JCFieldAccess) expression).getExpression();
                    if (enclosingType != null && !enclosingType.hasTag(TypeTag.NONE)) {
                        enclosingType = enclosingType.getEnclosingType();
                    }
                } else if (expression.hasTag(JCTree.Tag.ANNOTATED_TYPE)) {
                    JCTree.JCAnnotatedType jCAnnotatedType = (JCTree.JCAnnotatedType) expression;
                    if (enclosingType == null || enclosingType.hasTag(TypeTag.NONE)) {
                        ListBuffer listBuffer = new ListBuffer();
                        for (JCTree.JCAnnotation jCAnnotation : jCAnnotatedType.getAnnotations()) {
                            if (Attr.this.chk.isTypeAnnotation(jCAnnotation, false)) {
                                listBuffer.add((Attribute.TypeCompound) jCAnnotation.attribute);
                            }
                        }
                        List<Attribute.TypeCompound> list = listBuffer.toList();
                        if (!list.isEmpty()) {
                            Attr.this.log.error(jCAnnotatedType.underlyingType.pos(), CompilerProperties.Errors.TypeAnnotationInadmissible(list.size() == 1 ? CompilerProperties.Fragments.TypeAnnotation1(list.head) : CompilerProperties.Fragments.TypeAnnotation(list), type.tsym.owner, new JCDiagnostic.AnnotatedType(type.stripMetadata().annotatedType(list))));
                        }
                        z = false;
                    }
                    expression = jCAnnotatedType.underlyingType;
                } else {
                    if (!expression.hasTag(JCTree.Tag.IDENT)) {
                        if (expression.hasTag(JCTree.Tag.WILDCARD)) {
                            JCTree.JCWildcard jCWildcard = (JCTree.JCWildcard) expression;
                            if (jCWildcard.getKind() == Tree.Kind.EXTENDS_WILDCARD || jCWildcard.getKind() == Tree.Kind.SUPER_WILDCARD) {
                                validateAnnotatedType(jCWildcard.getBound(), jCWildcard.getBound().type);
                            }
                        } else if (expression.hasTag(JCTree.Tag.TYPEARRAY)) {
                            JCTree.JCArrayTypeTree jCArrayTypeTree = (JCTree.JCArrayTypeTree) expression;
                            validateAnnotatedType(jCArrayTypeTree.getType(), jCArrayTypeTree.elemtype.type);
                        } else if (expression.hasTag(JCTree.Tag.TYPEUNION)) {
                            for (JCTree.JCExpression jCExpression : ((JCTree.JCTypeUnion) expression).getTypeAlternatives()) {
                                validateAnnotatedType(jCExpression, jCExpression.type);
                            }
                        } else if (expression.hasTag(JCTree.Tag.TYPEINTERSECTION)) {
                            for (JCTree.JCExpression jCExpression2 : ((JCTree.JCTypeIntersection) expression).getBounds()) {
                                validateAnnotatedType(jCExpression2, jCExpression2.type);
                            }
                        } else if (expression.getKind() != Tree.Kind.PRIMITIVE_TYPE && expression.getKind() != Tree.Kind.ERRONEOUS) {
                            Assert.error("Unexpected tree: " + expression + " with kind: " + expression.getKind() + " within: " + jCTree + " with kind: " + jCTree.getKind());
                        }
                    }
                    z = false;
                }
            }
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitAnnotatedType(JCTree.JCAnnotatedType jCAnnotatedType) {
            if (jCAnnotatedType.underlyingType.type.isErroneous()) {
                return;
            }
            super.visitAnnotatedType(jCAnnotatedType);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitAnnotation(JCTree.JCAnnotation jCAnnotation) {
            Attr.this.chk.validateTypeAnnotation(jCAnnotation, null, false);
            super.visitAnnotation(jCAnnotation);
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
            if (this.sigOnly) {
                scan(jCClassDecl.mods);
                scan(jCClassDecl.typarams);
                scan(jCClassDecl.extending);
                scan(jCClassDecl.implementing);
            }
            for (JCTree jCTree : jCClassDecl.defs) {
                if (!jCTree.hasTag(JCTree.Tag.CLASSDEF)) {
                    scan(jCTree);
                }
            }
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitMethodDef(JCTree.JCMethodDecl jCMethodDecl) {
            Type type;
            JCTree.JCVariableDecl jCVariableDecl = jCMethodDecl.recvparam;
            if (jCVariableDecl != null && !jCVariableDecl.vartype.type.isErroneous()) {
                JCTree.JCVariableDecl jCVariableDecl2 = jCMethodDecl.recvparam;
                checkForDeclarationAnnotations(jCVariableDecl2.mods.annotations, jCVariableDecl2.sym);
            }
            JCTree.JCExpression jCExpression = jCMethodDecl.restype;
            if (jCExpression != null && (type = jCExpression.type) != null) {
                validateAnnotatedType(jCExpression, type);
            }
            if (!this.sigOnly) {
                scan(jCMethodDecl.defaultValue);
                scan(jCMethodDecl.body);
                return;
            }
            scan(jCMethodDecl.mods);
            scan(jCMethodDecl.restype);
            scan(jCMethodDecl.typarams);
            scan(jCMethodDecl.recvparam);
            scan(jCMethodDecl.params);
            scan(jCMethodDecl.thrown);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitNewArray(JCTree.JCNewArray jCNewArray) {
            JCTree.JCExpression jCExpression = jCNewArray.elemtype;
            if (jCExpression != null && jCExpression.type != null) {
                if (jCExpression.hasTag(JCTree.Tag.ANNOTATED_TYPE)) {
                    JCTree.JCExpression jCExpression2 = jCNewArray.elemtype;
                    checkForDeclarationAnnotations(((JCTree.JCAnnotatedType) jCExpression2).annotations, jCExpression2.type.tsym);
                }
                JCTree.JCExpression jCExpression3 = jCNewArray.elemtype;
                validateAnnotatedType(jCExpression3, jCExpression3.type);
            }
            super.visitNewArray(jCNewArray);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitNewClass(JCTree.JCNewClass jCNewClass) {
            JCTree.JCExpression jCExpression = jCNewClass.clazz;
            if (jCExpression != null && jCExpression.type != null) {
                if (jCExpression.hasTag(JCTree.Tag.ANNOTATED_TYPE)) {
                    JCTree.JCExpression jCExpression2 = jCNewClass.clazz;
                    checkForDeclarationAnnotations(((JCTree.JCAnnotatedType) jCExpression2).annotations, jCExpression2.type.tsym);
                }
                JCTree.JCClassDecl jCClassDecl = jCNewClass.def;
                if (jCClassDecl != null) {
                    checkForDeclarationAnnotations(jCClassDecl.mods.annotations, jCNewClass.clazz.type.tsym);
                }
                JCTree.JCExpression jCExpression3 = jCNewClass.clazz;
                validateAnnotatedType(jCExpression3, jCExpression3.type);
            }
            super.visitNewClass(jCNewClass);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitTypeCast(JCTree.JCTypeCast jCTypeCast) {
            Type type;
            JCTree jCTree = jCTypeCast.clazz;
            if (jCTree != null && (type = jCTree.type) != null) {
                validateAnnotatedType(jCTree, type);
            }
            super.visitTypeCast(jCTypeCast);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitTypeParameter(JCTree.JCTypeParameter jCTypeParameter) {
            Attr.this.chk.validateTypeAnnotations(jCTypeParameter.annotations, jCTypeParameter.type.tsym, true);
            scan(jCTypeParameter.bounds);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitTypeTest(JCTree.JCInstanceOf jCInstanceOf) {
            Type type;
            JCTree jCTree = jCInstanceOf.pattern;
            if (jCTree != null && !(jCTree instanceof JCTree.JCPattern) && (type = jCTree.type) != null) {
                validateAnnotatedType(jCTree, type);
            }
            super.visitTypeTest(jCInstanceOf);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitVarDef(JCTree.JCVariableDecl jCVariableDecl) {
            Symbol.VarSymbol varSymbol = jCVariableDecl.sym;
            if (varSymbol != null && varSymbol.type != null && !jCVariableDecl.isImplicitlyTyped()) {
                validateAnnotatedType(jCVariableDecl.vartype, jCVariableDecl.sym.type);
            }
            scan(jCVariableDecl.mods);
            scan(jCVariableDecl.vartype);
            if (this.sigOnly) {
                return;
            }
            scan(jCVariableDecl.init);
        }
    }

    public Attr(Context context) {
        context.put(attrKey, this);
        this.names = Names.instance(context);
        this.log = Log.instance(context);
        this.lintMapper = LintMapper.instance(context);
        Symtab symtabInstance = Symtab.instance(context);
        this.syms = symtabInstance;
        this.rs = Resolve.instance(context);
        this.operators = Operators.instance(context);
        this.chk = Check.instance(context);
        this.flow = Flow.instance(context);
        this.memberEnter = MemberEnter.instance(context);
        this.typeEnter = TypeEnter.instance(context);
        this.make = TreeMaker.instance(context);
        this.enter = Enter.instance(context);
        this.infer = Infer.instance(context);
        this.analyzer = Analyzer.instance(context);
        DeferredAttr deferredAttrInstance = DeferredAttr.instance(context);
        this.deferredAttr = deferredAttrInstance;
        this.cfolder = ConstFold.instance(context);
        this.target = Target.instance(context);
        this.types = Types.instance(context);
        Preview previewInstance = Preview.instance(context);
        this.preview = previewInstance;
        this.diags = JCDiagnostic.Factory.instance(context);
        this.annotate = Annotate.instance(context);
        this.typeAnnotations = TypeAnnotations.instance(context);
        this.typeEnvs = TypeEnvs.instance(context);
        this.dependencies = Dependencies.instance(context);
        this.argumentAttr = ArgumentAttr.instance(context);
        this.matchBindingsComputer = MatchBindingsComputer.instance(context);
        this.attrRecover = AttrRecover.instance(context);
        Options optionsInstance = Options.instance(context);
        Source sourceInstance = Source.instance(context);
        this.allowReifiableTypesInInstanceof = Source.Feature.REIFIABLE_TYPES_INSTANCEOF.allowedInSource(sourceInstance);
        this.allowRecords = Source.Feature.RECORDS.allowedInSource(sourceInstance);
        this.allowPatternSwitch = (previewInstance.isEnabled() || !previewInstance.isPreview(Source.Feature.PATTERN_SWITCH)) && Source.Feature.PATTERN_SWITCH.allowedInSource(sourceInstance);
        this.allowUnconditionalPatternsInstanceOf = Source.Feature.UNCONDITIONAL_PATTERN_IN_INSTANCEOF.allowedInSource(sourceInstance);
        this.sourceName = sourceInstance.name;
        this.useBeforeDeclarationWarning = optionsInstance.isSet("useBeforeDeclarationWarning");
        this.captureMRefReturnType = Source.Feature.CAPTURE_MREF_RETURN_TYPE.allowedInSource(sourceInstance);
        Kinds.KindSelector kindSelector = Kinds.KindSelector.NIL;
        Type.JCNoType jCNoType = Type.noType;
        this.statInfo = new ResultInfo(this, kindSelector, jCNoType);
        this.varAssignmentInfo = new ResultInfo(this, Kinds.KindSelector.ASG, jCNoType);
        this.unknownExprInfo = new ResultInfo(this, Kinds.KindSelector.VAL, jCNoType);
        this.methodAttrInfo = new MethodAttrInfo(this);
        this.unknownTypeInfo = new ResultInfo(this, Kinds.KindSelector.TYP, jCNoType);
        this.unknownTypeExprInfo = new ResultInfo(this, Kinds.KindSelector.VAL_TYP, jCNoType);
        this.recoveryInfo = new RecoveryInfo(this, deferredAttrInstance.emptyDeferredAttrContext);
        this.initBlockType = new Type.MethodType(List.nil(), symtabInstance.voidType, List.nil(), symtabInstance.methodClass);
    }

    public static /* synthetic */ boolean C(Type type, JCTree.JCExpression jCExpression) {
        return TreeInfo.diagnosticPositionFor(type.tsym, jCExpression, true) != null;
    }

    public static /* synthetic */ boolean D(Symbol.ClassSymbol classSymbol, Type type) {
        return type.tsym == classSymbol;
    }

    public static /* synthetic */ boolean E(Type type) {
        return type.tsym.kind == Kinds.Kind.ERR;
    }

    public static /* synthetic */ Type H(Attr attr, Type type) {
        attr.getClass();
        return type.isPrimitive() ? attr.types.boxedClass(type).type : type;
    }

    public static /* synthetic */ void J(Attr attr, JCDiagnostic.DiagnosticPosition diagnosticPosition, Env env, List list, InferenceContext inferenceContext) {
        attr.getClass();
        attr.checkAccessibleTypes(diagnosticPosition, (Env<AttrContext>) env, inferenceContext, inferenceContext.asInstTypes(list));
    }

    public static /* synthetic */ void K(Attr attr, JCTree.JCCase jCCase, Env env) {
        attr.getClass();
        attr.attribStats(jCCase.stats, env);
    }

    public static /* synthetic */ boolean M(Attr attr, Type type, Type type2) {
        attr.getClass();
        return type2.hasTag(TypeTag.INT) && attr.types.isAssignable(type2, type);
    }

    public static /* synthetic */ void N(Attr attr, Env env) {
        attr.getClass();
        attr.attribStat(env.tree, env);
    }

    public static /* synthetic */ boolean R(Type type, Type type2) {
        return type2 != type;
    }

    public static /* synthetic */ void a(final Attr attr, List list, final Type type) {
        attr.getClass();
        list.forEach(new Consumer() { // from class: xh0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.types.isSubtype(type, (Type) obj);
            }
        });
    }

    private void addBindings2Scope(JCTree.JCStatement jCStatement, List<Symbol.BindingSymbol> list) {
        JCTree.JCLabeledStatement jCLabeledStatement;
        JCTree jCTree;
        if (list.isEmpty()) {
            return;
        }
        Env env = this.env;
        while (true) {
            JCTree jCTree2 = env.tree;
            if (!(jCTree2 instanceof JCTree.JCLabeledStatement) || (jCTree = (jCLabeledStatement = (JCTree.JCLabeledStatement) jCTree2).body) != jCStatement) {
                break;
            }
            if (breaksTo(this.env, jCLabeledStatement, jCTree)) {
                return;
            }
            env = env.next;
            jCStatement = jCLabeledStatement;
        }
        Scope.WriteableScope writeableScope = this.env.info.scope;
        Objects.requireNonNull(writeableScope);
        list.forEach(new ui0(writeableScope));
        list.forEach(new Consumer() { // from class: vh0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((Symbol.BindingSymbol) obj).preserveBinding();
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static void addVars(List<JCTree.JCStatement> list, Scope.WriteableScope writeableScope) {
        for (List list2 = list; list2.nonEmpty(); list2 = list2.tail) {
            JCTree jCTree = (JCTree) list2.head;
            if (jCTree.hasTag(JCTree.Tag.VARDEF)) {
                writeableScope.enter(((JCTree.JCVariableDecl) jCTree).sym);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void attribClassBody(Env<AttrContext> env, Symbol.ClassSymbol classSymbol) {
        JCTree.JCClassDecl jCClassDecl = (JCTree.JCClassDecl) env.tree;
        Assert.check(classSymbol == jCClassDecl.sym);
        attribStats(jCClassDecl.typarams, env);
        if (!classSymbol.isAnonymous()) {
            this.chk.validate(jCClassDecl.typarams, env);
            this.chk.validate(jCClassDecl.extending, env);
            this.chk.validate(jCClassDecl.implementing, env);
        }
        this.chk.checkRequiresIdentity(jCClassDecl, env.info.lint);
        classSymbol.markAbstractIfNeeded(this.types);
        if ((classSymbol.flags() & 1536) == 0) {
            this.chk.checkAllDefined(jCClassDecl.pos(), classSymbol);
        }
        if ((classSymbol.flags() & 8192) != 0) {
            if (jCClassDecl.implementing.nonEmpty()) {
                this.log.error(jCClassDecl.implementing.head.pos(), CompilerProperties.Errors.CantExtendIntfAnnotation);
            }
            if (jCClassDecl.typarams.nonEmpty()) {
                this.log.error(jCClassDecl.typarams.head.pos(), CompilerProperties.Errors.IntfAnnotationCantHaveTypeParams(classSymbol));
            }
            Attribute.Compound repeatable = classSymbol.getAnnotationTypeMetadata().getRepeatable();
            if (repeatable != null) {
                JCDiagnostic.DiagnosticPosition diagnosticPosition = getDiagnosticPosition(jCClassDecl, repeatable.type);
                Assert.checkNonNull(diagnosticPosition);
                this.chk.validateRepeatable(classSymbol, repeatable, diagnosticPosition);
            }
        } else {
            this.chk.checkCompatibleSupertypes(jCClassDecl.pos(), classSymbol.type);
            this.chk.checkDefaultMethodClashes(jCClassDecl.pos(), classSymbol.type);
            this.chk.checkPotentiallyAmbiguousOverloads(jCClassDecl, classSymbol.type);
        }
        this.chk.checkClassBounds(jCClassDecl.pos(), classSymbol.type);
        jCClassDecl.type = classSymbol.type;
        for (List list = jCClassDecl.typarams; list.nonEmpty(); list = list.tail) {
            Assert.checkNonNull(env.info.scope.findFirst(((JCTree.JCTypeParameter) list.head).name));
        }
        if (!classSymbol.type.allparams().isEmpty() && this.types.isSubtype(classSymbol.type, this.syms.throwableType)) {
            this.log.error(jCClassDecl.extending.pos(), CompilerProperties.Errors.GenericThrowable);
        }
        this.chk.checkImplementations(jCClassDecl);
        checkAutoCloseable(env, jCClassDecl, false);
        for (List list2 = jCClassDecl.defs; list2.nonEmpty(); list2 = list2.tail) {
            attribStat((JCTree) list2.head, env);
            if (!this.allowRecords && classSymbol.owner.kind != Kinds.Kind.PCK && (((classSymbol.flags() & 8) == 0 || classSymbol.name == this.names.empty) && (TreeInfo.flags((JCTree) list2.head) & 520) != 0)) {
                Symbol.VarSymbol varSymbol = ((JCTree) list2.head).hasTag(JCTree.Tag.VARDEF) ? ((JCTree.JCVariableDecl) list2.head).sym : null;
                if (varSymbol == null || varSymbol.kind != Kinds.Kind.VAR || varSymbol.getConstValue() == null) {
                    this.log.error(((JCTree) list2.head).pos(), CompilerProperties.Errors.IclsCantHaveStaticDecl(classSymbol));
                }
            }
        }
        this.chk.checkSuperInitCalls(jCClassDecl);
        this.chk.checkCyclicConstructors(jCClassDecl);
        this.chk.checkNonCyclicElements(jCClassDecl);
        if (env.info.lint.isEnabled(Lint.LintCategory.SERIAL) && this.rs.isSerializable(classSymbol.type) && !classSymbol.isAnonymous()) {
            this.chk.checkSerialStructure(jCClassDecl, classSymbol);
        }
        this.typeAnnotations.organizeTypeAnnotationsBodies(jCClassDecl);
        validateTypeAnnotations(jCClassDecl, false);
    }

    private Env<AttrContext> attribToTree(JCTree jCTree, Env<AttrContext> env, JCTree jCTree2, ResultInfo resultInfo) {
        Env<AttrContext> env2;
        this.breakTree = jCTree2;
        JavaFileObject javaFileObjectUseSource = this.log.useSource(env.toplevel.sourcefile);
        try {
            try {
                this.deferredAttr.attribSpeculative(jCTree, env, resultInfo, null, DeferredAttr.AttributionMode.ATTRIB_TO_TREE, this.argumentAttr.withLocalCacheContext());
                this.attrRecover.doRecovery();
                this.breakTree = null;
                this.log.useSource(javaFileObjectUseSource);
                return env;
            } catch (BreakAttr e) {
                env2 = e.env;
                this.breakTree = null;
                this.log.useSource(javaFileObjectUseSource);
                return env2;
            } catch (AssertionError e2) {
                Throwable cause = e2.getCause();
                if (!(cause instanceof BreakAttr)) {
                    throw e2;
                }
                env2 = ((BreakAttr) cause).env;
                this.breakTree = null;
                this.log.useSource(javaFileObjectUseSource);
                return env2;
            }
        } catch (Throwable th) {
            this.breakTree = null;
            this.log.useSource(javaFileObjectUseSource);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void attribWithLint(Symbol.TypeSymbol typeSymbol, Consumer<Env<AttrContext>> consumer) {
        Env<AttrContext> env = this.typeEnvs.get(typeSymbol);
        Env env2 = env;
        while (true) {
            A a = env2.info;
            if (((AttrContext) a).lint != null) {
                Lint lint = this.chk.setLint(((AttrContext) a).lint.augment(typeSymbol));
                JavaFileObject javaFileObjectUseSource = this.log.useSource(env.toplevel.sourcefile);
                try {
                    consumer.accept(env);
                    return;
                } finally {
                    this.log.useSource(javaFileObjectUseSource);
                    this.chk.setLint(lint);
                }
            }
            env2 = env2.next;
        }
    }

    private boolean breaksTo(Env<AttrContext> env, JCTree jCTree, JCTree jCTree2) {
        preFlow(jCTree2);
        return this.flow.breaksToTree(env, jCTree, jCTree2, this.make);
    }

    public static /* synthetic */ boolean c(Type type) {
        return !type.hasTag(TypeTag.BOT);
    }

    private Type capture(Type type) {
        return this.types.capture(type);
    }

    private ResultInfo caseLabelResultInfo(Type type) {
        Kinds.KindSelector kindSelector = Kinds.KindSelector.VAL_TYP;
        if (type.hasTag(TypeTag.ERROR)) {
            type = Type.noType;
        }
        return new ResultInfo(this, kindSelector, type);
    }

    private void checkAccessibleTypes(final JCDiagnostic.DiagnosticPosition diagnosticPosition, final Env<AttrContext> env, InferenceContext inferenceContext, final List<Type> list) {
        if (inferenceContext.free(list)) {
            inferenceContext.addFreeTypeListener(list, new Infer.FreeTypeListener() { // from class: com.sun.tools.javac.comp.i
                @Override // com.sun.tools.javac.comp.Infer.FreeTypeListener
                public final void typesInferred(InferenceContext inferenceContext2) {
                    Attr.J(this.a, diagnosticPosition, env, list, inferenceContext2);
                }
            });
            return;
        }
        Iterator<Type> it = list.iterator();
        while (it.hasNext()) {
            this.rs.checkAccessibleType(env, it.next());
        }
    }

    private boolean checkCastablePattern(JCDiagnostic.DiagnosticPosition diagnosticPosition, Type type, Type type2) {
        Warner warner = new Warner();
        if (type.isErroneous() || type2.isErroneous()) {
            return false;
        }
        if (!this.types.isCastable(type, type2, warner)) {
            this.chk.basicHandler.report(diagnosticPosition, this.diags.fragment(CompilerProperties.Fragments.InconvertibleTypes(type, type2)));
            return false;
        }
        if ((type.isPrimitive() || type2.isPrimitive()) && !(type.isPrimitive() && type2.isPrimitive() && this.types.isSameType(type, type2))) {
            this.preview.checkSourceLevel(diagnosticPosition, Source.Feature.PRIMITIVE_PATTERNS);
            return true;
        }
        if (!warner.hasLint(Lint.LintCategory.UNCHECKED)) {
            return true;
        }
        this.log.error(diagnosticPosition, CompilerProperties.Errors.InstanceofReifiableNotSafe(type, type2));
        return false;
    }

    private void checkEnumInitializer(JCTree jCTree, Env<AttrContext> env, Symbol.VarSymbol varSymbol) {
        Symbol.ClassSymbol classSymbolEnclClass;
        if (!isStaticEnumField(varSymbol) || (classSymbolEnclClass = env.info.scope.owner.enclClass()) == null || classSymbolEnclClass.owner == null) {
            return;
        }
        Symbol symbol = varSymbol.owner;
        if ((symbol == classSymbolEnclClass || this.types.isSubtype(classSymbolEnclClass.type, symbol.type)) && Resolve.isInitializer(env)) {
            this.log.error(jCTree.pos(), CompilerProperties.Errors.IllegalEnumStaticRef);
        }
    }

    private void checkInit(JCTree jCTree, Env<AttrContext> env, Symbol.VarSymbol varSymbol, boolean z) {
        Env<AttrContext> envEnclosingInitEnv = enclosingInitEnv(env);
        if (envEnclosingInitEnv != null && (envEnclosingInitEnv.info.enclVar == varSymbol || varSymbol.pos > jCTree.pos)) {
            Symbol symbol = varSymbol.owner;
            if (symbol.kind == Kinds.Kind.TYP && symbol == env.info.scope.owner.enclClass()) {
                if (((varSymbol.flags() & 8) != 0) == Resolve.isStatic(env) && (!env.tree.hasTag(JCTree.Tag.ASSIGN) || TreeInfo.skipParens(((JCTree.JCAssign) env.tree).lhs) != jCTree)) {
                    if (!z || isStaticEnumField(varSymbol)) {
                        this.log.error(jCTree.pos(), envEnclosingInitEnv.info.enclVar == varSymbol ? CompilerProperties.Errors.IllegalSelfRef : CompilerProperties.Errors.IllegalForwardRef);
                    } else if (this.useBeforeDeclarationWarning) {
                        this.log.warning(jCTree.pos(), envEnclosingInitEnv.info.enclVar == varSymbol ? CompilerProperties.Warnings.SelfRef(varSymbol) : CompilerProperties.Warnings.ForwardRef(varSymbol));
                    }
                }
            }
        }
        varSymbol.getConstValue();
        checkEnumInitializer(jCTree, env, varSymbol);
    }

    public static /* synthetic */ void d(Attr attr, JCTree.JCNewClass jCNewClass, JCTree.JCExpression jCExpression, ResultInfo resultInfo, JCTree.JCClassDecl jCClassDecl, Env env, List list, List list2, Kinds.KindSelector kindSelector, InferenceContext inferenceContext) {
        attr.getClass();
        jCNewClass.constructorType = inferenceContext.asInstType(jCNewClass.constructorType);
        JCTree.JCExpression jCExpression2 = jCNewClass.clazz;
        Type typeAsInstType = inferenceContext.asInstType(jCExpression.type);
        jCExpression.type = typeAsInstType;
        jCExpression2.type = typeAsInstType;
        ResultInfo resultInfo2 = attr.resultInfo;
        try {
            attr.resultInfo = resultInfo;
            attr.visitAnonymousClassDefinition(jCNewClass, jCExpression, jCExpression.type, jCClassDecl, env, list, list2, kindSelector);
        } finally {
            attr.resultInfo = resultInfo2;
        }
    }

    private void doQueueScanTreeAndTypeAnnotateForVarInit(JCTree.JCVariableDecl jCVariableDecl, Env<AttrContext> env) {
        JCTree.JCExpression jCExpression = jCVariableDecl.init;
        if (jCExpression != null) {
            JCTree.JCModifiers jCModifiers = jCVariableDecl.mods;
            long j = jCModifiers.flags;
            if ((j & 9007199254740992L) == 0) {
                AttrContext attrContext = env.info;
                if (attrContext.scope.owner.kind == Kinds.Kind.MTH || attrContext.scope.owner.kind == Kinds.Kind.VAR) {
                    return;
                }
                jCModifiers.flags = j | 9007199254740992L;
                this.annotate.queueScanTreeAndTypeAnnotate(jCExpression, env, jCVariableDecl.sym);
                this.annotate.flush();
            }
        }
    }

    private Symbol enumConstant(JCTree jCTree, Type type) {
        if (jCTree.hasTag(JCTree.Tag.IDENT)) {
            JCTree.JCIdent jCIdent = (JCTree.JCIdent) jCTree;
            for (Symbol symbol : type.tsym.members().getSymbolsByName(jCIdent.name)) {
                if (symbol.kind == Kinds.Kind.VAR) {
                    jCIdent.sym = symbol;
                    ((Symbol.VarSymbol) symbol).getConstValue();
                    jCIdent.type = symbol.type;
                    if ((symbol.flags_field & 16384) == 0) {
                        return null;
                    }
                    return symbol;
                }
            }
        }
        return null;
    }

    private Type fallbackDescriptorType(JCTree.JCExpression jCExpression) {
        Type type;
        int i = AnonymousClass13.$SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[jCExpression.getTag().ordinal()];
        if (i != 2) {
            if (i == 3) {
                return new Type.MethodType(List.nil(), Type.recoveryType, List.of(this.syms.throwableType), this.syms.methodClass);
            }
            Assert.error("Cannot get here!");
            return null;
        }
        List listNil = List.nil();
        Iterator<JCTree.JCVariableDecl> it = ((JCTree.JCLambda) jCExpression).params.iterator();
        while (it.hasNext()) {
            JCTree.JCExpression jCExpression2 = it.next().vartype;
            listNil = (jCExpression2 == null || (type = jCExpression2.type) == null) ? listNil.append(this.syms.errType) : listNil.append(type);
        }
        return new Type.MethodType(listNil, Type.recoveryType, List.of(this.syms.throwableType), this.syms.methodClass);
    }

    private JCTree findJumpTarget(JCDiagnostic.DiagnosticPosition diagnosticPosition, JCTree.Tag tag, Name name, Env<AttrContext> env) {
        Pair<JCTree, JCDiagnostic.Error> pairFindJumpTargetNoError = findJumpTargetNoError(tag, name, env);
        JCDiagnostic.Error error = pairFindJumpTargetNoError.snd;
        if (error != null) {
            this.log.error(diagnosticPosition, error);
        }
        return pairFindJumpTargetNoError.fst;
    }

    /* JADX WARN: Code duplicated, block: B:43:0x0091  */
    /* JADX WARN: Code duplicated, block: B:45:0x009a A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:46:0x009c  */
    /* JADX WARN: Code duplicated, block: B:48:0x00a1  */
    /* JADX WARN: Code duplicated, block: B:50:0x00a5  */
    /* JADX WARN: Code duplicated, block: B:52:0x00ac  */
    private Pair<JCTree, JCDiagnostic.Error> findJumpTargetNoError(JCTree.Tag tag, Name name, Env<AttrContext> env) {
        JCDiagnostic.Error errorNotLoopLabel = null;
        for (Env env2 = env; env2 != null; env2 = env2.next) {
            int i = AnonymousClass13.$SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[env2.tree.getTag().ordinal()];
            if (i != 2) {
                switch (i) {
                    case 8:
                        JCTree.JCLabeledStatement jCLabeledStatement = (JCTree.JCLabeledStatement) env2.tree;
                        if (name == jCLabeledStatement.label) {
                            if (tag != JCTree.Tag.CONTINUE) {
                                return Pair.of(jCLabeledStatement, errorNotLoopLabel);
                            }
                            if (!jCLabeledStatement.body.hasTag(JCTree.Tag.DOLOOP) && !jCLabeledStatement.body.hasTag(JCTree.Tag.WHILELOOP) && !jCLabeledStatement.body.hasTag(JCTree.Tag.FORLOOP) && !jCLabeledStatement.body.hasTag(JCTree.Tag.FOREACHLOOP)) {
                                errorNotLoopLabel = CompilerProperties.Errors.NotLoopLabel(name);
                            }
                            return Pair.of(TreeInfo.referencedStatement(jCLabeledStatement), errorNotLoopLabel);
                        }
                        continue;
                        break;
                        break;
                    case 9:
                    case 10:
                    case 11:
                    case 12:
                        if (name == null) {
                            return Pair.of(env2.tree, errorNotLoopLabel);
                        }
                        continue;
                        break;
                        break;
                    case 13:
                        if (name != null) {
                            continue;
                        } else if (tag == JCTree.Tag.BREAK) {
                            return Pair.of(env2.tree, null);
                        }
                    case 14:
                        if (tag == JCTree.Tag.YIELD) {
                            return Pair.of(env2.tree, null);
                        }
                        if (tag == JCTree.Tag.BREAK) {
                            errorNotLoopLabel = CompilerProperties.Errors.BreakOutsideSwitchExpression;
                            continue;
                        } else {
                            errorNotLoopLabel = CompilerProperties.Errors.ContinueOutsideSwitchExpression;
                        }
                        break;
                        break;
                    case 15:
                    case 16:
                        break;
                    default:
                        continue;
                        break;
                }
            }
            if (name != null) {
                return Pair.of(null, CompilerProperties.Errors.UndefLabel(name));
            }
            if (errorNotLoopLabel != null) {
                return Pair.of(null, errorNotLoopLabel);
            }
            return tag == JCTree.Tag.CONTINUE ? Pair.of(null, CompilerProperties.Errors.ContOutsideLoop) : Pair.of(null, CompilerProperties.Errors.BreakOutsideSwitchLoop);
        }
        if (name != null) {
            return Pair.of(null, CompilerProperties.Errors.UndefLabel(name));
        }
        if (errorNotLoopLabel != null) {
            return Pair.of(null, errorNotLoopLabel);
        }
        if (tag == JCTree.Tag.CONTINUE) {
        }
    }

    public static /* synthetic */ boolean g(JCTree.JCMethodDecl jCMethodDecl, Symbol.RecordComponent recordComponent) {
        Symbol.MethodSymbol methodSymbol = recordComponent.accessor;
        return methodSymbol == jCMethodDecl.sym && (methodSymbol.flags_field & 16777216) == 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private JCDiagnostic.DiagnosticPosition getDiagnosticPosition(JCTree.JCClassDecl jCClassDecl, Type type) {
        for (List list = jCClassDecl.mods.annotations; !list.isEmpty(); list = list.tail) {
            if (this.types.isSameType(((JCTree.JCAnnotation) list.head).annotationType.type, type)) {
                return ((JCTree.JCAnnotation) list.head).pos();
            }
        }
        return null;
    }

    public static /* synthetic */ void h(Attr attr, ResultInfo resultInfo, InferenceContext inferenceContext, JCTree jCTree, Type type, Kinds.KindSelector kindSelector, InferenceContext inferenceContext2) {
        attr.getClass();
        attr.check(jCTree, inferenceContext.asInstType(type), kindSelector, resultInfo.dup(inferenceContext.asInstType(resultInfo.pt)));
    }

    private void handleLoopConditionBindings(MatchBindingsComputer.MatchBindings matchBindings, JCTree.JCStatement jCStatement, JCTree.JCStatement jCStatement2) {
        if (!matchBindings.bindingsWhenFalse.nonEmpty() || breaksTo(this.env, jCStatement, jCStatement2)) {
            return;
        }
        addBindings2Scope(jCStatement, matchBindings.bindingsWhenFalse);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void handleSwitch(JCTree jCTree, JCTree.JCExpression jCExpression, List<JCTree.JCCase> list, BiConsumer<JCTree.JCCase, Env<AttrContext>> biConsumer) {
        boolean zAnyMatch;
        boolean z;
        CaseTree.CaseKind caseKind;
        List list2;
        Type type;
        JCDiagnostic.Error error;
        Type typeAttribExpr = attribExpr(jCExpression, this.env);
        Type typeUnboxedTypeOrType = this.types.unboxedTypeOrType(typeAttribExpr);
        Env<AttrContext> env = this.env;
        AttrContext attrContext = env.info;
        Env<AttrContext> envDup = env.dup(jCTree, attrContext.dup(attrContext.scope.dup()));
        try {
            boolean z2 = (typeAttribExpr.tsym.flags() & 16384) != 0;
            boolean zIsSameType = this.types.isSameType(typeAttribExpr, this.syms.stringType);
            boolean zIsSameType2 = this.types.isSameType(typeUnboxedTypeOrType, this.syms.booleanType);
            boolean zIsErrorEnumSwitch = TreeInfo.isErrorEnumSwitch(jCExpression, list);
            boolean zIsAssignable = this.types.isAssignable(typeAttribExpr, this.syms.intType);
            if (typeAttribExpr.isPrimitive() && !zIsAssignable) {
                this.preview.checkSourceLevel(jCExpression.pos(), Source.Feature.PRIMITIVE_PATTERNS);
            }
            if (z2 || zIsSameType || zIsErrorEnumSwitch || zIsAssignable) {
                zAnyMatch = list.stream().flatMap(new Function() { // from class: oi0
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return ((JCTree.JCCase) obj).labels.stream();
                    }
                }).anyMatch(new Predicate() { // from class: ti0
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return Attr.t((JCTree.JCCaseLabel) obj);
                    }
                });
            } else {
                this.preview.checkSourceLevel(jCExpression.pos(), Source.Feature.PATTERN_SWITCH);
                zAnyMatch = true;
            }
            HashSet hashSet = new HashSet();
            List list3 = list;
            boolean z3 = z2;
            CaseTree.CaseKind caseKind2 = null;
            JCTree.JCCaseLabel jCCaseLabel = null;
            boolean z4 = false;
            boolean z5 = false;
            boolean z6 = false;
            boolean zIsErroneous = false;
            boolean z7 = false;
            while (list3.nonEmpty()) {
                boolean z8 = zIsSameType;
                JCTree.JCCase jCCase = (JCTree.JCCase) list3.head;
                if (caseKind2 == null) {
                    caseKind = jCCase.caseKind;
                    z = zIsSameType2;
                } else {
                    z = zIsSameType2;
                    if (caseKind2 == jCCase.caseKind || z5) {
                        caseKind = caseKind2;
                    } else {
                        this.log.error(jCCase.pos(), CompilerProperties.Errors.SwitchMixingCaseTypes);
                        caseKind = caseKind2;
                        z5 = true;
                    }
                }
                List list4 = jCCase.labels;
                CaseTree.CaseKind caseKind3 = caseKind;
                MatchBindingsComputer.MatchBindings matchBindingsCaseGuard = null;
                MatchBindingsComputer.MatchBindings matchBindings = null;
                while (list4.nonEmpty()) {
                    JCTree.JCCaseLabel jCCaseLabel2 = jCCaseLabel;
                    JCTree.JCCaseLabel jCCaseLabel3 = (JCTree.JCCaseLabel) list4.head;
                    boolean z9 = zAnyMatch;
                    if (jCCaseLabel3 instanceof JCTree.JCConstantCaseLabel) {
                        JCTree jCTree2 = ((JCTree.JCConstantCaseLabel) jCCaseLabel3).expr;
                        if (TreeInfo.isNull(jCTree2)) {
                            list2 = list3;
                            this.preview.checkSourceLevel(jCTree2.pos(), Source.Feature.CASE_NULL);
                            if (z7) {
                                this.log.error(jCCaseLabel3.pos(), CompilerProperties.Errors.DuplicateCaseLabel);
                            }
                            attribExpr(jCTree2, envDup, typeAttribExpr);
                            MatchBindingsComputer.MatchBindings matchBindings2 = this.matchBindings;
                            this.matchBindings = new MatchBindingsComputer.MatchBindings(matchBindings2.bindingsWhenTrue, matchBindings2.bindingsWhenFalse, true);
                            z7 = true;
                        } else {
                            list2 = list3;
                            if (z3) {
                                Symbol symbolEnumConstant = enumConstant(jCTree2, typeAttribExpr);
                                if (symbolEnumConstant == null) {
                                    if (this.allowPatternSwitch) {
                                        attribTree(jCTree2, envDup, caseLabelResultInfo(typeAttribExpr));
                                        Symbol symbol = TreeInfo.symbol(jCTree2);
                                        if (symbol == null || !symbol.isEnum() || symbol.kind != Kinds.Kind.VAR) {
                                            this.log.error(jCTree2.pos(), CompilerProperties.Errors.EnumLabelMustBeEnumConstant);
                                        } else if (!hashSet.add(symbol)) {
                                            this.log.error(jCCaseLabel3.pos(), CompilerProperties.Errors.DuplicateCaseLabel);
                                        }
                                    } else {
                                        this.log.error(jCTree2.pos(), CompilerProperties.Errors.EnumLabelMustBeUnqualifiedEnum);
                                    }
                                } else if (!hashSet.add(symbolEnumConstant)) {
                                    this.log.error(jCCaseLabel3.pos(), CompilerProperties.Errors.DuplicateCaseLabel);
                                }
                            } else if (zIsErrorEnumSwitch) {
                                Resolve resolve = this.rs;
                                Resolve.LogResolveHelper logResolveHelper = resolve.basicLogResolveHelper;
                                try {
                                    resolve.basicLogResolveHelper = resolve.silentLogResolveHelper;
                                    attribExpr(jCTree2, envDup, typeAttribExpr);
                                    this.rs.basicLogResolveHelper = logResolveHelper;
                                } catch (Throwable th) {
                                    this.rs.basicLogResolveHelper = logResolveHelper;
                                    throw th;
                                }
                            } else {
                                Type typeAttribTree = attribTree(jCTree2, envDup, caseLabelResultInfo(typeAttribExpr));
                                if (!typeAttribTree.hasTag(TypeTag.ERROR)) {
                                    if (typeAttribTree.constValue() == null) {
                                        Symbol symbol2 = TreeInfo.symbol(jCTree2);
                                        if (symbol2 != null && symbol2.kind == Kinds.Kind.TYP) {
                                            this.log.error(jCTree2.pos(), CompilerProperties.Errors.PatternExpected);
                                        } else if (symbol2 == null || !symbol2.isEnum()) {
                                            Log log = this.log;
                                            JCDiagnostic.DiagnosticPosition diagnosticPositionPos = jCTree2.pos();
                                            if (z8) {
                                                error = CompilerProperties.Errors.StringConstReq;
                                            } else {
                                                error = zIsAssignable != 0 ? CompilerProperties.Errors.ConstExprReq : CompilerProperties.Errors.PatternOrEnumReq;
                                            }
                                            log.error(diagnosticPositionPos, error);
                                        } else if (!hashSet.add(symbol2)) {
                                            this.log.error(jCCaseLabel3.pos(), CompilerProperties.Errors.DuplicateCaseLabel);
                                        }
                                    } else {
                                        boolean z10 = typeAttribTree.getTag().isInSuperClassesOf(TypeTag.LONG) || typeAttribTree.getTag().equals(TypeTag.BOOLEAN);
                                        if (z10) {
                                            this.preview.checkSourceLevel(jCCaseLabel3.pos(), Source.Feature.PRIMITIVE_PATTERNS);
                                        }
                                        if (!z8 && zIsAssignable == 0 && (!z10 || !this.types.isSameType(typeUnboxedTypeOrType, typeAttribTree))) {
                                            this.log.error(jCCaseLabel3.pos(), CompilerProperties.Errors.ConstantLabelNotCompatible(typeAttribTree, typeAttribExpr));
                                        } else if (!hashSet.add(typeAttribTree.constValue())) {
                                            this.log.error(jCCase.pos(), CompilerProperties.Errors.DuplicateCaseLabel);
                                        }
                                    }
                                }
                            }
                        }
                        type = typeUnboxedTypeOrType;
                    } else {
                        list2 = list3;
                        if (jCCaseLabel3 instanceof JCTree.JCDefaultCaseLabel) {
                            if (z4) {
                                this.log.error(jCCaseLabel3.pos(), CompilerProperties.Errors.DuplicateDefaultLabel);
                            } else if (z6) {
                                this.log.error(jCCaseLabel3.pos(), CompilerProperties.Errors.UnconditionalPatternAndDefault);
                            } else if (z && hashSet.containsAll(Collections.unmodifiableSet(new HashSet(Arrays.asList(0, 1))))) {
                                this.log.error(jCCaseLabel3.pos(), CompilerProperties.Errors.DefaultAndBothBooleanValues);
                            }
                            this.matchBindings = MatchBindingsComputer.EMPTY;
                            type = typeUnboxedTypeOrType;
                            z4 = true;
                        } else if (jCCaseLabel3 instanceof JCTree.JCPatternCaseLabel) {
                            JCTree jCTree3 = ((JCTree.JCPatternCaseLabel) jCCaseLabel3).pat;
                            attribExpr(jCTree3, envDup, typeAttribExpr);
                            Type typePrimaryPatternType = TreeInfo.primaryPatternType(jCTree3);
                            if (typePrimaryPatternType.isPrimitive()) {
                                type = typeUnboxedTypeOrType;
                                this.preview.checkSourceLevel(jCTree3.pos(), Source.Feature.PRIMITIVE_PATTERNS);
                            } else {
                                type = typeUnboxedTypeOrType;
                                if (!typePrimaryPatternType.hasTag(TypeTag.TYPEVAR)) {
                                    typePrimaryPatternType = this.chk.checkClassOrArrayType(jCTree3.pos(), typePrimaryPatternType);
                                }
                            }
                            checkCastablePattern(jCTree3.pos(), typeAttribExpr, typePrimaryPatternType);
                            Type typeErasure = this.types.erasure(typePrimaryPatternType);
                            JCTree.JCExpression jCExpression2 = jCCase.guard;
                            if (matchBindings == null && jCExpression2 != null) {
                                MatchBindingsComputer.MatchBindings matchBindings3 = this.matchBindings;
                                Env<AttrContext> envBindingEnv = bindingEnv(envDup, matchBindings3.bindingsWhenTrue);
                                try {
                                    attribExpr(jCExpression2, envBindingEnv, this.syms.booleanType);
                                    envBindingEnv.info.scope.leave();
                                    matchBindings = this.matchBindings;
                                    this.matchBindings = matchBindings3;
                                    if (TreeInfo.isBooleanWithValue(jCExpression2, 0)) {
                                        this.log.error(jCExpression2.pos(), CompilerProperties.Errors.GuardHasConstantExpressionFalse);
                                    }
                                } catch (Throwable th2) {
                                    envBindingEnv.info.scope.leave();
                                    throw th2;
                                }
                            }
                            if (TreeInfo.unguardedCase(jCCase) && !jCTree3.hasTag(JCTree.Tag.RECORDPATTERN) && !typeErasure.isErroneous() && this.types.isUnconditionallyExactTypeBased(typeAttribExpr, typeErasure)) {
                                if (z6) {
                                    this.log.error(jCTree3.pos(), CompilerProperties.Errors.DuplicateUnconditionalPattern);
                                } else if (z4) {
                                    this.log.error(jCTree3.pos(), CompilerProperties.Errors.UnconditionalPatternAndDefault);
                                } else if (z && hashSet.containsAll(Collections.unmodifiableSet(new HashSet(Arrays.asList(0, 1))))) {
                                    this.log.error(jCTree3.pos(), CompilerProperties.Errors.UnconditionalPatternAndBothBooleanValues);
                                }
                                jCCaseLabel2 = jCCaseLabel3;
                                z6 = true;
                            }
                            zIsErroneous = typeErasure.isErroneous();
                        } else {
                            type = typeUnboxedTypeOrType;
                            Assert.error();
                        }
                    }
                    matchBindingsCaseGuard = this.matchBindingsComputer.switchCase(jCCaseLabel3, matchBindingsCaseGuard, this.matchBindings);
                    list4 = list4.tail;
                    jCCaseLabel = jCCaseLabel2;
                    zAnyMatch = z9;
                    list3 = list2;
                    typeUnboxedTypeOrType = type;
                }
                boolean z11 = zAnyMatch;
                Type type2 = typeUnboxedTypeOrType;
                JCTree.JCCaseLabel jCCaseLabel4 = jCCaseLabel;
                List list5 = list3;
                if (matchBindings != null) {
                    matchBindingsCaseGuard = this.matchBindingsComputer.caseGuard(jCCase, matchBindingsCaseGuard, matchBindings);
                }
                Env<AttrContext> envBindingEnv2 = bindingEnv(envDup, jCCase, matchBindingsCaseGuard.bindingsWhenTrue);
                try {
                    biConsumer.accept(jCCase, envBindingEnv2);
                    envBindingEnv2.info.scope.leave();
                    addVars(jCCase.stats, envDup.info.scope);
                    preFlow(jCCase);
                    jCCase.completesNormally = this.flow.aliveAfter(envBindingEnv2, jCCase, this.make);
                    list3 = list5.tail;
                    zIsSameType = z8;
                    zIsSameType2 = z;
                    caseKind2 = caseKind3;
                    zIsErrorEnumSwitch = zIsErrorEnumSwitch;
                    jCCaseLabel = jCCaseLabel4;
                    zAnyMatch = z11;
                    typeUnboxedTypeOrType = type2;
                } catch (Throwable th3) {
                    envBindingEnv2.info.scope.leave();
                    throw th3;
                }
            }
            boolean z12 = zAnyMatch;
            if (z12) {
                this.chk.checkSwitchCaseStructure(list);
                this.chk.checkSwitchCaseLabelDominated(jCCaseLabel, list);
            }
            if (jCTree.hasTag(JCTree.Tag.SWITCH)) {
                ((JCTree.JCSwitch) jCTree).hasUnconditionalPattern = z4 || z6 || zIsErroneous;
                ((JCTree.JCSwitch) jCTree).patternSwitch = z12;
            } else if (jCTree.hasTag(JCTree.Tag.SWITCH_EXPRESSION)) {
                ((JCTree.JCSwitchExpression) jCTree).hasUnconditionalPattern = z4 || z6 || zIsErroneous;
                ((JCTree.JCSwitchExpression) jCTree).patternSwitch = z12;
            } else {
                Assert.error(jCTree.getTag().name());
            }
            envDup.info.scope.leave();
        } catch (Throwable th4) {
            envDup.info.scope.leave();
            throw th4;
        }
    }

    public static Attr instance(Context context) {
        Attr attr = (Attr) context.get(attrKey);
        return attr == null ? new Attr(context) : attr;
    }

    private boolean isBooleanOrNumeric(Env<AttrContext> env, JCTree.JCExpression jCExpression) {
        TypeTag typeTag;
        switch (AnonymousClass13.$SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[jCExpression.getTag().ordinal()]) {
            case 1:
                JCTree.JCLiteral jCLiteral = (JCTree.JCLiteral) jCExpression;
                return jCLiteral.typetag.isSubRangeOf(TypeTag.DOUBLE) || (typeTag = jCLiteral.typetag) == TypeTag.BOOLEAN || typeTag == TypeTag.BOT;
            case 2:
            case 3:
                return false;
            case 4:
                return isBooleanOrNumeric(env, ((JCTree.JCParens) jCExpression).expr);
            case 5:
                JCTree.JCConditional jCConditional = (JCTree.JCConditional) jCExpression;
                return isBooleanOrNumeric(env, jCConditional.truepart) && isBooleanOrNumeric(env, jCConditional.falsepart);
            case 6:
                JCTree.JCMethodInvocation jCMethodInvocation = (JCTree.JCMethodInvocation) this.deferredAttr.attribSpeculative(jCExpression, env, this.unknownExprInfo, this.argumentAttr.withLocalCacheContext());
                return primitiveOrBoxed(this.types.memberType(jCMethodInvocation.meth.hasTag(JCTree.Tag.IDENT) ? env.enclClass.type : ((JCTree.JCFieldAccess) jCMethodInvocation.meth).selected.type, TreeInfo.symbol(jCMethodInvocation.meth)).mo73getReturnType());
            case 7:
                return primitiveOrBoxed(((JCTree.JCExpression) this.deferredAttr.attribSpeculative((JCTree.JCExpression) this.removeClassParams.translate(((JCTree.JCNewClass) jCExpression).clazz), env, this.unknownTypeInfo, this.argumentAttr.withLocalCacheContext())).type);
            default:
                return primitiveOrBoxed(this.deferredAttr.attribSpeculative(jCExpression, env, this.unknownExprInfo, this.argumentAttr.withLocalCacheContext()).type);
        }
    }

    private boolean isNonArgsMethodInObject(Name name) {
        Iterator<Symbol> it = this.syms.objectType.tsym.members().getSymbolsByName(name, new Predicate() { // from class: fi0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return Attr.m((Symbol) obj);
            }
        }).iterator();
        while (it.hasNext()) {
            if (it.next().type.mo71getParameterTypes().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    private boolean isStaticEnumField(Symbol.VarSymbol varSymbol) {
        return Flags.isEnum(varSymbol.owner) && Flags.isStatic(varSymbol) && !Flags.isConstant(varSymbol) && varSymbol.name != this.names._class;
    }

    public static boolean isType(Symbol symbol) {
        return symbol != null && symbol.kind == Kinds.Kind.TYP;
    }

    public static /* synthetic */ boolean j(Type type, Type type2) {
        return type2 != type;
    }

    public static /* synthetic */ boolean m(Symbol symbol) {
        return symbol.kind == Kinds.Kind.MTH;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void n(Attr attr, ResultInfo resultInfo, final JCTree.JCSwitchExpression jCSwitchExpression, final ListBuffer listBuffer, final ListBuffer listBuffer2, JCTree.JCCase jCCase, Env env) {
        attr.getClass();
        ((AttrContext) env.info).yieldResult = resultInfo;
        attr.attribStats(jCCase.stats, env);
        new TreeScanner(attr) { // from class: com.sun.tools.javac.comp.Attr.1
            final /* synthetic */ Attr this$0;

            {
                this.this$0 = attr;
            }

            @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
            public void visitClassDef(JCTree.JCClassDecl jCClassDecl) {
            }

            @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
            public void visitLambda(JCTree.JCLambda jCLambda) {
            }

            @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
            public void visitYield(JCTree.JCYield jCYield) {
                if (jCYield.target == jCSwitchExpression) {
                    ListBuffer listBuffer3 = listBuffer;
                    JCTree.JCExpression jCExpression = jCYield.value;
                    listBuffer3.append(jCExpression != null ? jCExpression.pos() : jCYield.pos());
                    ListBuffer listBuffer4 = listBuffer2;
                    JCTree.JCExpression jCExpression2 = jCYield.value;
                    listBuffer4.append(jCExpression2 != null ? jCExpression2.type : this.this$0.syms.errType);
                }
                super.visitYield(jCYield);
            }
        }.scan(jCCase.stats);
    }

    public static /* synthetic */ Type p(Attr attr, Type type) {
        Types types = attr.types;
        return types.upward(type, types.captures(type)).baseType();
    }

    private void reportIntersectionError(JCDiagnostic.DiagnosticPosition diagnosticPosition, String str, Object... objArr) {
        Check.CheckContext checkContext = this.resultInfo.checkContext;
        JCDiagnostic.Factory factory = this.diags;
        checkContext.report(diagnosticPosition, factory.fragment(CompilerProperties.Fragments.BadIntersectionTargetForFunctionalExpr(factory.fragment(str, objArr))));
    }

    public static /* synthetic */ void s(Attr attr, Env env, JCTree.JCFunctionalExpression jCFunctionalExpression, Type type, Type type2, Type type3, Check.CheckContext checkContext, InferenceContext inferenceContext) {
        attr.getClass();
        attr.setFunctionalInfo(env, jCFunctionalExpression, type, inferenceContext.asInstType(type2), inferenceContext.asInstType(type3), checkContext);
    }

    private Symbol selectSym(JCTree.JCFieldAccess jCFieldAccess, Symbol symbol, Type type, Env<AttrContext> env, ResultInfo resultInfo) {
        Symbol symbol2;
        Symbol symbolNotFoundError;
        JCDiagnostic.DiagnosticPosition diagnosticPositionPos = jCFieldAccess.pos();
        Name name = jCFieldAccess.name;
        Symbol symbolSelectSym = null;
        switch (AnonymousClass13.$SwitchMap$com$sun$tools$javac$code$TypeTag[type.getTag().ordinal()]) {
            case 1:
                Resolve resolve = this.rs;
                return resolve.accessBase(resolve.findIdentInPackage(diagnosticPositionPos, env, type.tsym, name, resultInfo.pkind), diagnosticPositionPos, symbol, type, name, true);
            case 2:
            case 3:
                if (resultInfo.pt.hasTag(TypeTag.METHOD) || resultInfo.pt.hasTag(TypeTag.FORALL)) {
                    return this.rs.resolveQualifiedMethod(diagnosticPositionPos, env, symbol, type, name, resultInfo.pt.mo71getParameterTypes(), resultInfo.pt.getTypeArguments());
                }
                Names names = this.names;
                if (name == names._this || name == names._super) {
                    return this.rs.resolveSelf(diagnosticPositionPos, env, type.tsym, jCFieldAccess);
                }
                if (name == names._class) {
                    return this.syms.getClassField(type, this.types);
                }
                return this.rs.accessBase(this.rs.findIdentInType(diagnosticPositionPos, env, type, name, resultInfo.pkind), diagnosticPositionPos, symbol, type, name, true);
            case 4:
                x01.a(jCFieldAccess);
                return null;
            case 5:
                if (type.getUpperBound() != null) {
                    symbolSelectSym = selectSym(jCFieldAccess, symbol, capture(type.getUpperBound()), env, resultInfo);
                }
                Symbol symbol3 = symbolSelectSym;
                if (symbol3 == null) {
                    this.log.error(diagnosticPositionPos, CompilerProperties.Errors.TypeVarCantBeDeref);
                    return this.syms.errSymbol;
                }
                if (!symbol3.isPrivate()) {
                    if (symbol3.owner.isInterface() && symbol3.kind == Kinds.Kind.MTH && (symbol3.flags() & 8) != 0) {
                        Resolve resolve2 = this.rs;
                        Objects.requireNonNull(resolve2);
                        symbolNotFoundError = new Resolve.SymbolNotFoundError(resolve2, Kinds.Kind.ABSENT_MTH);
                    } else {
                        symbol2 = symbol3;
                    }
                    this.rs.accessBase(symbol2, diagnosticPositionPos, symbol, type, name, true);
                    return symbol3;
                }
                Resolve resolve3 = this.rs;
                Objects.requireNonNull(resolve3);
                symbolNotFoundError = resolve3.new AccessError(env, type, symbol3);
                symbol2 = symbolNotFoundError;
                this.rs.accessBase(symbol2, diagnosticPositionPos, symbol, type, name, true);
                return symbol3;
            case 6:
                return this.types.createErrorType(name, type.tsym, type).tsym;
            default:
                if (name == this.names._class) {
                    return this.syms.getClassField(type, this.types);
                }
                this.log.error(diagnosticPositionPos, CompilerProperties.Errors.CantDeref(type));
                return this.syms.errSymbol;
        }
    }

    private void setFunctionalInfo(final Env<AttrContext> env, final JCTree.JCFunctionalExpression jCFunctionalExpression, final Type type, final Type type2, final Type type3, final Check.CheckContext checkContext) {
        if (checkContext.inferenceContext().free(type2)) {
            checkContext.inferenceContext().addFreeTypeListener(List.of(type, type2), new Infer.FreeTypeListener() { // from class: com.sun.tools.javac.comp.j
                @Override // com.sun.tools.javac.comp.Infer.FreeTypeListener
                public final void typesInferred(InferenceContext inferenceContext) {
                    Attr.s(this.a, env, jCFunctionalExpression, type, type2, type3, checkContext, inferenceContext);
                }
            });
            return;
        }
        jCFunctionalExpression.owner = env.info.scope.owner;
        if (type.hasTag(TypeTag.CLASS)) {
            jCFunctionalExpression.target = type3;
        }
        if (checkContext.deferredAttrContext().mode != DeferredAttr.AttrMode.CHECK || type == Type.recoveryType) {
            return;
        }
        try {
            Symbol.ClassSymbol classSymbolMakeFunctionalInterfaceClass = this.types.makeFunctionalInterfaceClass(env, this.names.empty, jCFunctionalExpression.target, 1024L);
            if (classSymbolMakeFunctionalInterfaceClass != null) {
                this.chk.checkImplementations(env.tree, classSymbolMakeFunctionalInterfaceClass, classSymbolMakeFunctionalInterfaceClass);
                try {
                    classSymbolMakeFunctionalInterfaceClass.flags_field |= 512;
                    this.types.findDescriptorType(classSymbolMakeFunctionalInterfaceClass.type);
                } catch (Types.FunctionDescriptorLookupError unused) {
                    this.resultInfo.checkContext.report(jCFunctionalExpression, this.diags.fragment(CompilerProperties.Fragments.NoSuitableFunctionalIntfInst(jCFunctionalExpression.target)));
                }
            }
        } catch (Types.FunctionDescriptorLookupError e) {
            this.resultInfo.checkContext.report(env.tree, e.getDiagnostic());
        }
    }

    private void setSyntheticVariableType(JCTree.JCVariableDecl jCVariableDecl, Type type) {
        if (type.isErroneous()) {
            jCVariableDecl.vartype = this.make.at(jCVariableDecl.pos()).Erroneous();
        } else if (!jCVariableDecl.declaredUsingVar()) {
            jCVariableDecl.vartype = this.make.at(jCVariableDecl.pos()).Type(type);
        } else {
            Assert.check(jCVariableDecl.typePos != -1);
            jCVariableDecl.vartype = this.make.at(jCVariableDecl.typePos).Type(type);
        }
    }

    public static /* synthetic */ boolean t(JCTree.JCCaseLabel jCCaseLabel) {
        return jCCaseLabel.hasTag(JCTree.Tag.PATTERNCASELABEL) || TreeInfo.isNullCaseLabel(jCCaseLabel);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void visitAnonymousClassDefinition(final JCTree.JCNewClass jCNewClass, final JCTree.JCExpression jCExpression, Type type, final JCTree.JCClassDecl jCClassDecl, Env<AttrContext> env, final List<Type> list, final List<Type> list2, final Kinds.KindSelector kindSelector) {
        Type typeCreateErrorType;
        Type type2;
        Type type3;
        Env<AttrContext> env2 = env;
        InferenceContext inferenceContext = this.resultInfo.checkContext.inferenceContext();
        Type enclosingType = type.getEnclosingType();
        if (enclosingType != null && enclosingType.hasTag(TypeTag.CLASS) && !this.chk.checkDenotable((Type.ClassType) enclosingType)) {
            this.log.error(jCNewClass.encl, CompilerProperties.Errors.EnclosingClassTypeNonDenotable(enclosingType));
        }
        boolean zIsDiamond = TreeInfo.isDiamond(jCNewClass);
        if (!zIsDiamond || (((type2 = jCNewClass.constructorType) == null || !inferenceContext.free(type2)) && ((type3 = jCNewClass.clazz.type) == null || !inferenceContext.free(type3)))) {
            if (zIsDiamond && type.hasTag(TypeTag.CLASS)) {
                List<Type> listCheckDiamondDenotable = this.chk.checkDiamondDenotable((Type.ClassType) type);
                if (!type.isErroneous() && listCheckDiamondDenotable.nonEmpty()) {
                    JCDiagnostic.Fragment fragmentDiamond = CompilerProperties.Fragments.Diamond(type.tsym);
                    this.log.error(jCNewClass.clazz.pos(), CompilerProperties.Errors.CantApplyDiamond1(fragmentDiamond, listCheckDiamondDenotable.size() > 1 ? CompilerProperties.Fragments.DiamondInvalidArgs(listCheckDiamondDenotable, fragmentDiamond) : CompilerProperties.Fragments.DiamondInvalidArg(listCheckDiamondDenotable, fragmentDiamond)));
                }
                Iterator<Type> it = type.getTypeArguments().iterator();
                while (it.hasNext()) {
                    this.rs.checkAccessibleType(this.env, it.next());
                }
            }
            if (type.tsym.isInterface() || (type.isErroneous() && !type.getOriginalType().hasTag(TypeTag.NONE) && type.getOriginalType().tsym.isInterface())) {
                jCClassDecl.implementing = List.of(jCExpression);
            } else {
                jCClassDecl.extending = jCExpression;
            }
            if (this.resultInfo.checkContext.deferredAttrContext().mode == DeferredAttr.AttrMode.CHECK && this.rs.isSerializable(type)) {
                env2.info.isSerializable = true;
            }
            attribStat(jCClassDecl, env2);
            List listPrepend = (jCNewClass.encl == null || type.tsym.isInterface()) ? list : list.prepend(jCNewClass.encl.type);
            if (zIsDiamond && kindSelector.contains(Kinds.KindSelector.POLY)) {
                listPrepend = listPrepend.map(this.deferredAttr.deferredCopier);
            }
            List list3 = listPrepend;
            Type typeCreateErrorType2 = type.hasTag(TypeTag.ERROR) ? this.types.createErrorType(jCClassDecl.sym.type) : jCClassDecl.sym.type;
            Symbol symbolResolveConstructor = this.rs.resolveConstructor(jCNewClass.pos(), env2, typeCreateErrorType2, list3, list2);
            typeCreateErrorType = typeCreateErrorType2;
            jCNewClass.constructor = symbolResolveConstructor;
            Assert.check(!symbolResolveConstructor.kind.isResolutionError());
            jCNewClass.constructor = symbolResolveConstructor;
            env2 = env;
            jCNewClass.constructorType = checkId(jCNewClass, typeCreateErrorType, symbolResolveConstructor, env, new ResultInfo(this, kindSelector, newMethodTemplate(this.syms.voidType, list3, list2), CheckMode.NO_TREE_UPDATE));
        } else {
            final ResultInfo resultInfo = this.resultInfo;
            final Env<AttrContext> envCopyEnv = copyEnv(env2);
            inferenceContext.addFreeTypeListener(List.of(jCNewClass.constructorType, jCNewClass.clazz.type), new Infer.FreeTypeListener() { // from class: com.sun.tools.javac.comp.m
                @Override // com.sun.tools.javac.comp.Infer.FreeTypeListener
                public final void typesInferred(InferenceContext inferenceContext2) {
                    Attr.d(this.a, jCNewClass, jCExpression, resultInfo, jCClassDecl, envCopyEnv, list, list2, kindSelector, inferenceContext2);
                }
            });
            typeCreateErrorType = type;
        }
        Symbol symbol = jCNewClass.constructor;
        if (symbol == null || symbol.kind != Kinds.Kind.MTH) {
            typeCreateErrorType = this.types.createErrorType(jCNewClass.type);
        }
        this.result = check(jCNewClass, typeCreateErrorType, Kinds.KindSelector.VAL, this.resultInfo.dup(CheckMode.NO_INFERENCE_HOOK));
        this.chk.validate(jCNewClass.typeargs, env2);
    }

    public static /* synthetic */ Symbol.ClassSymbol w(Type type) {
        return (Symbol.ClassSymbol) type.tsym;
    }

    public static /* synthetic */ Type x(Attr attr, Type type) {
        attr.getClass();
        return type.isPrimitive() ? type : attr.types.unboxedType(type);
    }

    public Type adjustMethodReturnType(Symbol symbol, Type type, Name name, List<Type> list, Type type2) {
        Symbol symbol2;
        if (symbol == null || !(((symbol2 = symbol.owner) == this.syms.objectType.tsym || symbol2.isInterface()) && name == this.names.getClass && list.isEmpty())) {
            return (symbol != null && symbol.owner == this.syms.arrayClass && name == this.names.clone && this.types.isArray(type)) ? type : type2;
        }
        return new Type.ClassType(type2.getEnclosingType(), List.of(new Type.WildcardType(this.types.erasure(type.baseType()), BoundKind.EXTENDS, this.syms.boundClass)), type2.tsym, type2.getMetadata());
    }

    public void attrib(Env<AttrContext> env) {
        int i = AnonymousClass13.$SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[env.tree.getTag().ordinal()];
        if (i != 22) {
            JCTree jCTree = env.tree;
            if (i != 23) {
                attribClass(jCTree.pos(), env.enclClass.sym);
            } else {
                attribPackage(jCTree.pos(), ((JCTree.JCPackageDecl) env.tree).packge);
            }
        } else {
            attribModule(env.tree.pos(), ((JCTree.JCModuleDecl) env.tree).sym);
        }
        this.annotate.flush();
        LintMapper lintMapper = this.lintMapper;
        JCTree.JCCompilationUnit jCCompilationUnit = env.toplevel;
        lintMapper.calculateLints(jCCompilationUnit.sourcefile, env.tree, jCCompilationUnit.endPositions);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void attribAnnotationTypes(List<JCTree.JCAnnotation> list, Env<AttrContext> env) {
        for (List list2 = list; list2.nonEmpty(); list2 = list2.tail) {
            attribType(((JCTree.JCAnnotation) list2.head).annotationType, env);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public List<Type> attribAnyTypes(List<JCTree.JCExpression> list, Env<AttrContext> env) {
        ListBuffer listBuffer = new ListBuffer();
        for (List list2 = list; list2.nonEmpty(); list2 = list2.tail) {
            listBuffer.append(attribType((JCTree) list2.head, env));
        }
        return listBuffer.toList();
    }

    public Kinds.KindSelector attribArgs(Kinds.KindSelector kindSelector, List<JCTree.JCExpression> list, Env<AttrContext> env, ListBuffer<Type> listBuffer) {
        for (JCTree.JCExpression jCExpression : list) {
            Type typeCheckNonVoid = this.chk.checkNonVoid(jCExpression, attribTree(jCExpression, env, this.methodAttrInfo));
            if (typeCheckNonVoid.hasTag(TypeTag.DEFERRED)) {
                kindSelector = Kinds.KindSelector.of(Kinds.KindSelector.POLY, kindSelector);
            }
            listBuffer.append(typeCheckNonVoid);
        }
        return kindSelector;
    }

    public Type attribBase(JCTree jCTree, Env<AttrContext> env, boolean z, boolean z2, boolean z3) {
        Type typeAttribType = jCTree.type;
        if (typeAttribType == null) {
            typeAttribType = attribType(jCTree, env);
        }
        Type type = typeAttribType;
        try {
            return checkBase(type, jCTree, env, z, z2, z3);
        } catch (Symbol.CompletionFailure e) {
            this.chk.completionError(jCTree.pos(), e);
            return type;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void attribClass(final Symbol.ClassSymbol classSymbol) throws Symbol.CompletionFailure {
        A a;
        boolean z;
        if (classSymbol.type.hasTag(TypeTag.ERROR)) {
            return;
        }
        this.chk.checkNonCyclic((JCDiagnostic.DiagnosticPosition) null, classSymbol.type);
        Type typeSupertype = this.types.supertype(classSymbol.type);
        long j = classSymbol.flags_field;
        long j2 = 0;
        if ((16777216 & j) == 0 && (j & 536870912) == 0) {
            TypeTag typeTag = TypeTag.CLASS;
            if (typeSupertype.hasTag(typeTag)) {
                attribClass((Symbol.ClassSymbol) typeSupertype.tsym);
            }
            Symbol symbol = classSymbol.owner;
            if (symbol.kind == Kinds.Kind.TYP && symbol.type.hasTag(typeTag)) {
                attribClass((Symbol.ClassSymbol) classSymbol.owner);
            }
            classSymbol.flags_field |= 536870912;
        }
        long j3 = classSymbol.flags_field;
        if ((268435456 & j3) != 0) {
            classSymbol.flags_field = j3 & (-268435457);
            Env<AttrContext> env = this.typeEnvs.get(classSymbol);
            Env env2 = env;
            while (true) {
                a = env2.info;
                if (((AttrContext) a).lint != null) {
                    break;
                } else {
                    env2 = env2.next;
                }
            }
            env.info.lint = ((AttrContext) a).lint.augment(classSymbol);
            Lint lint = this.chk.setLint(env.info.lint);
            JavaFileObject javaFileObjectUseSource = this.log.useSource(classSymbol.sourcefile);
            ResultInfo resultInfo = env.info.returnResult;
            try {
                if (classSymbol.isSealed() && !classSymbol.isEnum() && !classSymbol.isPermittedExplicit && classSymbol.getPermittedSubclasses().isEmpty()) {
                    this.log.error(TreeInfo.diagnosticPositionFor(classSymbol, env.tree), CompilerProperties.Errors.SealedClassMustHaveSubclasses);
                }
                if (classSymbol.isSealed()) {
                    HashSet hashSet = new HashSet();
                    boolean z2 = classSymbol.packge().modle == this.syms.unnamedModule || classSymbol.packge().modle == this.syms.noModule;
                    for (final Type type : classSymbol.getPermittedSubclasses()) {
                        if (!type.isErroneous()) {
                            long j4 = j2;
                            if (type.getTag() == TypeTag.TYPEVAR) {
                                this.log.error(TreeInfo.diagnosticPositionFor(type.tsym, env.tree), CompilerProperties.Errors.InvalidPermitsClause(CompilerProperties.Fragments.IsATypeVariable(type)));
                                z = true;
                            } else {
                                z = false;
                            }
                            if (type.tsym.isAnonymous() && !classSymbol.isEnum()) {
                                this.log.error(TreeInfo.diagnosticPositionFor(type.tsym, env.tree), CompilerProperties.Errors.LocalClassesCantExtendSealed(CompilerProperties.Fragments.Anonymous));
                            }
                            if (hashSet.contains(type.tsym)) {
                                this.log.error((JCDiagnostic.DiagnosticPosition) ((List) env.enclClass.permitting.stream().filter(new Predicate() { // from class: ei0
                                    @Override // java.util.function.Predicate
                                    public final boolean test(Object obj) {
                                        return Attr.C(type, (JCTree.JCExpression) obj);
                                    }
                                }).limit(2L).collect(List.collector())).get(1), CompilerProperties.Errors.InvalidPermitsClause(CompilerProperties.Fragments.IsDuplicated(type)));
                            } else {
                                hashSet.add(type.tsym);
                            }
                            Symbol.TypeSymbol typeSymbol = type.tsym;
                            if (z2) {
                                if (typeSymbol.packge() != classSymbol.packge()) {
                                    this.log.error(TreeInfo.diagnosticPositionFor(type.tsym, env.tree), CompilerProperties.Errors.ClassInUnnamedModuleCantExtendSealedInDiffPackage(classSymbol));
                                }
                            } else if (typeSymbol.packge().modle != classSymbol.packge().modle) {
                                this.log.error(TreeInfo.diagnosticPositionFor(type.tsym, env.tree), CompilerProperties.Errors.ClassInModuleCantExtendSealedInDiffModule(classSymbol, classSymbol.packge().modle));
                            }
                            Symbol.TypeSymbol typeSymbol2 = type.tsym;
                            Type type2 = classSymbol.type;
                            if (typeSymbol2 == type2.tsym || this.types.isSuperType(type, type2)) {
                                this.log.error(TreeInfo.diagnosticPositionFor(type.tsym, ((JCTree.JCClassDecl) env.tree).permitting), CompilerProperties.Errors.InvalidPermitsClause(type.tsym == classSymbol.type.tsym ? CompilerProperties.Fragments.MustNotBeSameClass : CompilerProperties.Fragments.MustNotBeSupertype(type)));
                            } else if (!z && !this.types.directSupertypes(type).stream().anyMatch(new Predicate() { // from class: gi0
                                @Override // java.util.function.Predicate
                                public final boolean test(Object obj) {
                                    return Attr.D(classSymbol, (Type) obj);
                                }
                            })) {
                                boolean zIsInterface = classSymbol.isInterface();
                                Log log = this.log;
                                if (zIsInterface) {
                                    log.error(TreeInfo.diagnosticPositionFor(type.tsym, env.tree), CompilerProperties.Errors.InvalidPermitsClause(CompilerProperties.Fragments.DoesntImplementSealed(Kinds.kindName(type.tsym), type)));
                                } else {
                                    log.error(TreeInfo.diagnosticPositionFor(type.tsym, env.tree), CompilerProperties.Errors.InvalidPermitsClause(CompilerProperties.Fragments.DoesntExtendSealed(type)));
                                }
                            }
                            j2 = j4;
                        }
                    }
                }
                long j5 = j2;
                List<Symbol.ClassSymbol> list = (List) this.types.directSupertypes(classSymbol.type).stream().filter(new Predicate() { // from class: hi0
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return ((Type) obj).tsym.isSealed();
                    }
                }).map(new Function() { // from class: ii0
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return Attr.w((Type) obj);
                    }
                }).collect(List.collector());
                if (!list.isEmpty()) {
                    if (classSymbol.isDirectlyOrIndirectlyLocal() && !classSymbol.isEnum()) {
                        this.log.error(TreeInfo.diagnosticPositionFor(classSymbol, env.tree), CompilerProperties.Errors.LocalClassesCantExtendSealed(classSymbol.isAnonymous() ? CompilerProperties.Fragments.Anonymous : CompilerProperties.Fragments.Local));
                    }
                    if (!classSymbol.type.isCompound()) {
                        for (Symbol.ClassSymbol classSymbol2 : list) {
                            if (!classSymbol2.isPermittedSubclass(classSymbol.type.tsym)) {
                                this.log.error(TreeInfo.diagnosticPositionFor(classSymbol.type.tsym, env.tree), CompilerProperties.Errors.CantInheritFromSealed(classSymbol2));
                            }
                        }
                        if (!classSymbol.isNonSealed() && !classSymbol.isFinal() && !classSymbol.isSealed()) {
                            this.log.error(TreeInfo.diagnosticPositionFor(classSymbol, env.tree), classSymbol.isInterface() ? CompilerProperties.Errors.NonSealedOrSealedExpected : CompilerProperties.Errors.NonSealedSealedOrFinalExpected);
                        }
                    }
                } else if ((classSymbol.flags_field & Long.MIN_VALUE) != j5) {
                    boolean zAnyMatch = this.types.directSupertypes(classSymbol.type).stream().anyMatch(new Predicate() { // from class: ji0
                        @Override // java.util.function.Predicate
                        public final boolean test(Object obj) {
                            return Attr.E((Type) obj);
                        }
                    });
                    Type.ClassType classType = (Type.ClassType) classSymbol.type;
                    if (!(zAnyMatch | ((classType.isCompound() || classType.interfaces_field == classType.all_interfaces_field) ? false : true))) {
                        this.log.error(TreeInfo.diagnosticPositionFor(classSymbol, env.tree), CompilerProperties.Errors.NonSealedWithNoSealedSupertype(classSymbol));
                    }
                }
                env.info.returnResult = null;
                if (typeSupertype.tsym == this.syms.enumSym && (classSymbol.flags_field & 16793600) == j5) {
                    this.log.error(env.tree.pos(), CompilerProperties.Errors.EnumNoSubclassing);
                }
                Symbol.TypeSymbol typeSymbol3 = typeSupertype.tsym;
                if (typeSymbol3 != null && (typeSymbol3.flags_field & 16384) != j5 && (classSymbol.flags_field & 16793600) == j5) {
                    this.log.error(env.tree.pos(), CompilerProperties.Errors.EnumTypesNotExtensible);
                }
                if (this.rs.isSerializable(classSymbol.type)) {
                    env.info.isSerializable = true;
                }
                attribClassBody(env, classSymbol);
                this.chk.checkDeprecatedAnnotation(env.tree.pos(), classSymbol);
                this.chk.checkClassOverrideEqualsAndHashIfNeeded(env.tree.pos(), classSymbol);
                this.chk.checkFunctionalInterface((JCTree.JCClassDecl) env.tree, classSymbol);
                this.chk.checkLeaksNotAccessible(env, (JCTree.JCClassDecl) env.tree);
                if (classSymbol.isImplicit()) {
                    this.chk.checkHasMain(env.tree.pos(), classSymbol);
                }
            } finally {
                env.info.returnResult = resultInfo;
                this.log.useSource(javaFileObjectUseSource);
                this.chk.setLint(lint);
            }
        }
    }

    public Type attribExpr(JCTree jCTree, Env<AttrContext> env, Type type) {
        Kinds.KindSelector kindSelector = Kinds.KindSelector.VAL;
        if (type.hasTag(TypeTag.ERROR)) {
            type = Type.noType;
        }
        return attribTree(jCTree, env, new ResultInfo(this, kindSelector, type));
    }

    public Env<AttrContext> attribExprToTree(JCTree jCTree, Env<AttrContext> env, JCTree jCTree2) {
        return attribToTree(jCTree, env, jCTree2, this.unknownExprInfo);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public List<Type> attribExprs(List<JCTree.JCExpression> list, Env<AttrContext> env, Type type) {
        ListBuffer listBuffer = new ListBuffer();
        for (List list2 = list; list2.nonEmpty(); list2 = list2.tail) {
            listBuffer.append(attribExpr((JCTree) list2.head, env, type));
        }
        return listBuffer.toList();
    }

    public Symbol attribIdent(JCTree jCTree, JCTree.JCCompilationUnit jCCompilationUnit) {
        Env<AttrContext> env = this.enter.topLevelEnv(jCCompilationUnit);
        TreeMaker treeMaker = this.make;
        JCTree.JCClassDecl jCClassDeclClassDef = treeMaker.ClassDef(treeMaker.Modifiers(0L), this.syms.errSymbol.name, null, null, null, null);
        env.enclClass = jCClassDeclClassDef;
        jCClassDeclClassDef.sym = this.syms.errSymbol;
        return attribIdent(jCTree, env);
    }

    public Type attribIdentAsEnumType(Env<AttrContext> env, JCTree.JCIdent jCIdent) {
        Assert.check((env.enclClass.sym.flags() & 16384) != 0);
        jCIdent.type = env.info.scope.owner.enclClass().type;
        jCIdent.sym = env.info.scope.owner.enclClass();
        return jCIdent.type;
    }

    public Type attribImportQualifier(JCTree.JCImport jCImport, Env<AttrContext> env) {
        return attribTree(jCImport.qualid.selected, env, new ResultInfo(this, jCImport.staticImport ? Kinds.KindSelector.TYP : Kinds.KindSelector.TYP_PCK, Type.noType));
    }

    public Object attribLazyConstantValue(Env<AttrContext> env, Env<AttrContext> env2, JCTree.JCVariableDecl jCVariableDecl, Type type) {
        JavaFileObject javaFileObjectUseSource = this.log.useSource(env.toplevel.sourcefile);
        try {
            doQueueScanTreeAndTypeAnnotateForVarInit(jCVariableDecl, env2);
            Type typeAttribExpr = attribExpr(jCVariableDecl.init, env, type);
            if (jCVariableDecl.isImplicitlyTyped()) {
                Symbol.VarSymbol varSymbol = jCVariableDecl.sym;
                type = this.chk.checkLocalVarType(jCVariableDecl, typeAttribExpr, jCVariableDecl.name);
                varSymbol.type = type;
                jCVariableDecl.type = type;
            }
            if (typeAttribExpr.constValue() != null) {
                return coerce(typeAttribExpr, type).constValue();
            }
            return null;
        } finally {
            this.log.useSource(javaFileObjectUseSource);
        }
    }

    public void attribModule(JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol.ModuleSymbol moduleSymbol) {
        try {
            this.annotate.flush();
            attribModule(moduleSymbol);
        } catch (Symbol.CompletionFailure e) {
            this.chk.completionError(diagnosticPosition, e);
        }
    }

    public void attribPackage(JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol.PackageSymbol packageSymbol) {
        try {
            this.annotate.flush();
            attribPackage(packageSymbol);
        } catch (Symbol.CompletionFailure e) {
            this.chk.completionError(diagnosticPosition, e);
        }
    }

    public Type attribStat(JCTree jCTree, Env<AttrContext> env) {
        Env<AttrContext> envCopyEnvIfNeeded = this.analyzer.copyEnvIfNeeded(jCTree, env);
        Type typeAttribTree = attribTree(jCTree, env, this.statInfo);
        this.analyzer.analyzeIfNeeded(jCTree, envCopyEnvIfNeeded);
        this.attrRecover.doRecovery();
        return typeAttribTree;
    }

    public Env<AttrContext> attribStatToTree(JCTree jCTree, Env<AttrContext> env, JCTree jCTree2) {
        return attribToTree(jCTree, env, jCTree2, this.statInfo);
    }

    public <T extends JCTree> void attribStats(List<T> list, Env<AttrContext> env) {
        while (list.nonEmpty()) {
            attribStat(list.head, env);
            list = list.tail;
        }
    }

    public Type attribTree(JCTree jCTree, Env<AttrContext> env, ResultInfo resultInfo) {
        Type typeCompletionError;
        Env<AttrContext> env2 = this.env;
        ResultInfo resultInfo2 = this.resultInfo;
        try {
            try {
                this.env = env;
                this.resultInfo = resultInfo;
                if (resultInfo.needsArgumentAttr(jCTree)) {
                    this.result = this.argumentAttr.attribArg(jCTree, env);
                } else {
                    jCTree.accept(this);
                }
                this.matchBindings = this.matchBindingsComputer.finishBindings(jCTree, this.matchBindings);
                if (jCTree == this.breakTree && resultInfo.checkContext.deferredAttrContext().mode == DeferredAttr.AttrMode.CHECK) {
                    breakTreeFound(copyEnv(env));
                }
                typeCompletionError = this.result;
            } catch (Symbol.CompletionFailure e) {
                jCTree.type = this.syms.errType;
                typeCompletionError = this.chk.completionError(jCTree.pos(), e);
            }
            return typeCompletionError;
        } finally {
            this.env = env2;
            this.resultInfo = resultInfo2;
        }
    }

    public Type attribType(JCTree jCTree, Symbol.TypeSymbol typeSymbol) {
        Env<AttrContext> env = this.typeEnvs.get(typeSymbol);
        return attribTree(jCTree, env.dup(jCTree, env.info.dup()), this.unknownTypeInfo);
    }

    public void attribTypeVariables(List<JCTree.JCTypeParameter> list, Env<AttrContext> env, boolean z) {
        for (JCTree.JCTypeParameter jCTypeParameter : list) {
            Type.TypeVar typeVar = (Type.TypeVar) jCTypeParameter.type;
            typeVar.tsym.flags_field |= 268435456;
            typeVar.setUpperBound(Type.noType);
            if (jCTypeParameter.bounds.isEmpty()) {
                this.types.setBounds(typeVar, List.of(this.syms.objectType));
            } else {
                List listOf = List.of(attribType(jCTypeParameter.bounds.head, env));
                Iterator<JCTree.JCExpression> it = jCTypeParameter.bounds.tail.iterator();
                while (it.hasNext()) {
                    listOf = listOf.prepend(attribType(it.next(), env));
                }
                this.types.setBounds(typeVar, listOf.reverse());
            }
            typeVar.tsym.flags_field &= -268435457;
        }
        if (z) {
            for (JCTree.JCTypeParameter jCTypeParameter2 : list) {
                this.chk.checkNonCyclic(jCTypeParameter2.pos(), (Type.TypeVar) jCTypeParameter2.type);
            }
        }
    }

    public List<Type> attribTypes(List<JCTree.JCExpression> list, Env<AttrContext> env) {
        return this.chk.checkRefTypes(list, attribAnyTypes(list, env));
    }

    public Env<AttrContext> bindingEnv(Env<AttrContext> env, JCTree jCTree, List<Symbol.BindingSymbol> list) {
        AttrContext attrContext = env.info;
        Env<AttrContext> envDup = env.dup(jCTree, attrContext.dup(attrContext.scope.dup()));
        Scope.WriteableScope writeableScope = envDup.info.scope;
        Objects.requireNonNull(writeableScope);
        list.forEach(new ui0(writeableScope));
        return envDup;
    }

    public void breakTreeFound(Env<AttrContext> env) {
        throw new BreakAttr(env);
    }

    public JCDiagnostic.Fragment canInferLocalVarType(JCTree.JCVariableDecl jCVariableDecl) {
        LocalInitScanner localInitScanner = new LocalInitScanner();
        localInitScanner.scan(jCVariableDecl.init);
        return localInitScanner.badInferenceMsg;
    }

    public Type check(JCTree jCTree, final Type type, final Kinds.KindSelector kindSelector, ResultInfo resultInfo) {
        final JCTree jCTree2;
        final ResultInfo resultInfo2;
        Type typeCheck;
        final InferenceContext inferenceContext = resultInfo.checkContext.inferenceContext();
        boolean z = (type.hasTag(TypeTag.ERROR) || resultInfo.pt.hasTag(TypeTag.METHOD) || resultInfo.pt.hasTag(TypeTag.FORALL)) ? false : true;
        if (z && !kindSelector.subset(resultInfo.pkind)) {
            this.log.error(jCTree.pos(), CompilerProperties.Errors.UnexpectedType(resultInfo.pkind.kindNames(), kindSelector.kindNames()));
            typeCheck = this.types.createErrorType(type);
            jCTree2 = jCTree;
            resultInfo2 = resultInfo;
        } else if (inferenceContext.free(type)) {
            Type type2 = z ? resultInfo.pt : type;
            if (resultInfo.checkMode.installPostInferenceHook()) {
                jCTree2 = jCTree;
                resultInfo2 = resultInfo;
                inferenceContext.addFreeTypeListener(List.of(type), new Infer.FreeTypeListener() { // from class: com.sun.tools.javac.comp.n
                    @Override // com.sun.tools.javac.comp.Infer.FreeTypeListener
                    public final void typesInferred(InferenceContext inferenceContext2) {
                        Attr.h(this.a, resultInfo2, inferenceContext, jCTree2, type, kindSelector, inferenceContext2);
                    }
                });
            } else {
                jCTree2 = jCTree;
                resultInfo2 = resultInfo;
            }
            typeCheck = type2;
        } else {
            jCTree2 = jCTree;
            resultInfo2 = resultInfo;
            typeCheck = z ? resultInfo2.check(jCTree2, type) : type;
        }
        if (resultInfo2.checkMode.updateTreeType()) {
            jCTree2.type = typeCheck;
        }
        return typeCheck;
    }

    public void checkAssignable(JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol.VarSymbol varSymbol, JCTree jCTree, Env<AttrContext> env) {
        if (varSymbol.name == this.names._this) {
            this.log.error(diagnosticPosition, CompilerProperties.Errors.CantAssignValToThis);
            return;
        }
        if ((varSymbol.flags() & 16) != 0 && ((varSymbol.flags() & 262144) != 0 || ((jCTree != null && !TreeInfo.isThisQualifier(jCTree)) || !isAssignableAsBlankFinal(varSymbol, env)))) {
            boolean zIsResourceVariable = varSymbol.isResourceVariable();
            Log log = this.log;
            if (zIsResourceVariable) {
                log.error(diagnosticPosition, CompilerProperties.Errors.TryResourceMayNotBeAssigned(varSymbol));
                return;
            } else {
                log.error(diagnosticPosition, CompilerProperties.Errors.CantAssignValToVar(Flags.toSource(varSymbol.flags() & 24), varSymbol));
                return;
            }
        }
        if (this.rs.isEarlyReference(env, jCTree, varSymbol)) {
            if (varSymbol.owner != env.enclClass.sym) {
                this.log.error(diagnosticPosition, CompilerProperties.Errors.CantRefBeforeCtorCalled(varSymbol));
            } else if ((varSymbol.flags() & 262144) != 0) {
                this.log.error(diagnosticPosition, CompilerProperties.Errors.CantAssignInitializedBeforeCtorCalled(varSymbol));
            }
        }
    }

    public void checkAutoCloseable(Env<AttrContext> env, JCTree jCTree, boolean z) {
        JCDiagnostic.DiagnosticPosition diagnosticPositionPos = jCTree.pos();
        Type type = jCTree.type;
        if (type.isErroneous() || this.types.asSuper(type, this.syms.autoCloseableType.tsym) == null || this.types.isSameType(type, this.syms.autoCloseableType)) {
            return;
        }
        Symbol.TypeSymbol typeSymbol = this.syms.noSymbol;
        Log log = this.log;
        Objects.requireNonNull(log);
        Log.DiscardDiagnosticHandler discardDiagnosticHandler = log.new DiscardDiagnosticHandler();
        try {
            Symbol symbolResolveQualifiedMethod = this.rs.resolveQualifiedMethod(diagnosticPositionPos, env, this.types.skipTypeVars(type, false), this.names.close, List.nil(), List.nil());
            this.log.popDiagnosticHandler(discardDiagnosticHandler);
            if (symbolResolveQualifiedMethod.kind == Kinds.Kind.MTH) {
                if ((z || symbolResolveQualifiedMethod.owner != this.syms.autoCloseableType.tsym) && ((Symbol.MethodSymbol) symbolResolveQualifiedMethod).binaryOverrides(this.syms.autoCloseableClose, type.tsym, this.types) && this.chk.isHandled(this.syms.interruptedExceptionType, this.types.memberType(type, symbolResolveQualifiedMethod).mo74getThrownTypes())) {
                    if (z || symbolResolveQualifiedMethod.owner != type.tsym) {
                        this.log.warning(diagnosticPositionPos, CompilerProperties.LintWarnings.TryResourceThrowsInterruptedExc(type));
                    } else {
                        this.log.warning(TreeInfo.diagnosticPositionFor(symbolResolveQualifiedMethod, jCTree), CompilerProperties.LintWarnings.TryResourceCanThrowInterruptedExc(type));
                    }
                }
            }
        } catch (Throwable th) {
            this.log.popDiagnosticHandler(discardDiagnosticHandler);
            throw th;
        }
    }

    public Type checkBase(Type type, JCTree jCTree, Env<AttrContext> env, boolean z, boolean z2, boolean z3) {
        if (jCTree.hasTag(JCTree.Tag.TYPEAPPLY)) {
            jCTree = ((JCTree.JCTypeApply) jCTree).clazz;
        }
        JCDiagnostic.DiagnosticPosition diagnosticPositionPos = jCTree.pos();
        if (type.tsym.isAnonymous()) {
            this.log.error(diagnosticPositionPos, CompilerProperties.Errors.CantInheritFromAnon);
            return this.types.createErrorType(type);
        }
        if (type.isErroneous()) {
            return type;
        }
        if (!type.hasTag(TypeTag.TYPEVAR) || z || z2) {
            type = this.chk.checkClassType(diagnosticPositionPos, type, z3);
        } else if (type.getUpperBound() == null) {
            this.log.error(diagnosticPositionPos, CompilerProperties.Errors.IllegalForwardRef);
            return this.types.createErrorType(type);
        }
        if (z2 && (type.tsym.flags() & 512) == 0) {
            this.log.error(diagnosticPositionPos, CompilerProperties.Errors.IntfExpectedHere);
            return this.types.createErrorType(type);
        }
        if (z3 && z && (type.tsym.flags() & 512) != 0) {
            this.log.error(diagnosticPositionPos, CompilerProperties.Errors.NoIntfExpectedHere);
            return this.types.createErrorType(type);
        }
        if (z3 && (type.tsym.flags() & 16) != 0) {
            this.log.error(diagnosticPositionPos, CompilerProperties.Errors.CantInheritFromFinal(type.tsym));
        }
        this.chk.checkNonCyclic(diagnosticPositionPos, type);
        return type;
    }

    public boolean checkExConstraints(List<Type> list, List<Type> list2, final InferenceContext inferenceContext) {
        List<Type> list3 = (List) list2.stream().filter(new Predicate() { // from class: wh0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return inferenceContext.free((Type) obj);
            }
        }).collect(List.collector());
        List<Type> listDiff = list2.diff(list3);
        List<Type> list4 = (List) list.stream().filter(new Predicate() { // from class: yh0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return this.b.chk.isChecked((Type) obj);
            }
        }).collect(List.collector());
        ListBuffer listBuffer = new ListBuffer();
        for (Type type : list4) {
            Iterator<Type> it = listDiff.iterator();
            do {
                if (!it.hasNext()) {
                    listBuffer.add(type);
                    break;
                }
            } while (!this.types.isSubtype(type, it.next()));
        }
        if (list3.isEmpty() && !listBuffer.isEmpty()) {
            return false;
        }
        final List<Type> listAsUndetVars = inferenceContext.asUndetVars(list3);
        listBuffer.forEach(new Consumer() { // from class: zh0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Attr.a(this.b, listAsUndetVars, (Type) obj);
            }
        });
        listAsUndetVars.stream().filter(new Predicate() { // from class: ai0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((Type) obj).hasTag(TypeTag.UNDETVAR);
            }
        }).forEach(new Consumer() { // from class: bi0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((Type.UndetVar) ((Type) obj)).setThrow();
            }
        });
        return true;
    }

    public Type checkId(JCTree jCTree, Type type, Symbol symbol, Env<AttrContext> env, ResultInfo resultInfo) {
        return (resultInfo.pt.hasTag(TypeTag.FORALL) || resultInfo.pt.hasTag(TypeTag.METHOD)) ? checkMethodIdInternal(jCTree, type, symbol, env, resultInfo) : checkIdInternal(jCTree, type, symbol, resultInfo.pt, env, resultInfo);
    }

    /* JADX WARN: Code duplicated, block: B:38:0x010a  */
    public Type checkIdInternal(JCTree jCTree, Type type, Symbol symbol, Type type2, Env<AttrContext> env, ResultInfo resultInfo) {
        Env<AttrContext> env2;
        Type classType;
        Type typeMemberType;
        Type typeAsOuterSuper;
        switch (AnonymousClass13.$SwitchMap$com$sun$tools$javac$code$Kinds$Kind[symbol.kind.ordinal()]) {
            case 7:
                env2 = env;
                classType = symbol.type;
                TypeTag typeTag = TypeTag.CLASS;
                if (classType.hasTag(typeTag)) {
                    this.chk.checkForBadAuxiliaryClassAccess(jCTree.pos(), env2, (Symbol.ClassSymbol) symbol);
                    Type enclosingType = classType.getEnclosingType();
                    if (classType.tsym.type.getTypeArguments().nonEmpty()) {
                        classType = this.types.erasure(classType);
                    } else if ((enclosingType.hasTag(typeTag) || enclosingType.hasTag(TypeTag.TYPEVAR)) && type != enclosingType) {
                        Type typeAsEnclosingSuper = this.types.asEnclosingSuper(type, enclosingType.tsym);
                        if (typeAsEnclosingSuper == null) {
                            typeAsEnclosingSuper = this.types.erasure(enclosingType);
                        }
                        if (typeAsEnclosingSuper != enclosingType) {
                            classType = new Type.ClassType(typeAsEnclosingSuper, List.nil(), classType.tsym, classType.getMetadata());
                        }
                    }
                }
                break;
            case 8:
                env2 = env;
                Symbol.VarSymbol varSymbol = (Symbol.VarSymbol) symbol;
                if (env2.info.enclVar != null && varSymbol.type.hasTag(TypeTag.NONE)) {
                    this.log.error(TreeInfo.positionFor(varSymbol, env2.enclClass), CompilerProperties.Errors.CantInferLocalVarType(varSymbol.name, CompilerProperties.Fragments.LocalSelfRef));
                    Type typeCreateErrorType = this.types.createErrorType(varSymbol.type);
                    varSymbol.type = typeCreateErrorType;
                    jCTree.type = typeCreateErrorType;
                    return typeCreateErrorType;
                }
                if (Kinds.KindSelector.ASG.subset(pkind()) && varSymbol.owner.kind == Kinds.Kind.TYP && (varSymbol.flags() & 8) == 0 && ((type.hasTag(TypeTag.CLASS) || type.hasTag(TypeTag.TYPEVAR)) && (typeAsOuterSuper = this.types.asOuterSuper(type, varSymbol.owner)) != null && typeAsOuterSuper.isRaw())) {
                    Types types = this.types;
                    if (!types.isSameType(varSymbol.type, varSymbol.erasure(types))) {
                        this.chk.warnUnchecked(jCTree.pos(), CompilerProperties.LintWarnings.UncheckedAssignToVar(varSymbol, typeAsOuterSuper));
                    }
                }
                if (symbol.owner.kind == Kinds.Kind.TYP) {
                    Name name = symbol.name;
                    Names names = this.names;
                    if (name == names._this || name == names._super) {
                        typeMemberType = symbol.type;
                    } else {
                        typeMemberType = this.types.memberType(type, symbol);
                    }
                } else {
                    typeMemberType = symbol.type;
                }
                classType = (varSymbol.getConstValue() == null || !isStaticReference(jCTree)) ? typeMemberType : typeMemberType.constType(varSymbol.getConstValue());
                if (resultInfo.pkind == Kinds.KindSelector.VAL) {
                    classType = capture(classType);
                }
                break;
                break;
            case 9:
                env2 = env;
                classType = checkMethod(type, symbol, new ResultInfo(resultInfo.pkind, resultInfo.pt.mo73getReturnType(), resultInfo.checkContext, resultInfo.checkMode), env2, TreeInfo.args(env.tree), resultInfo.pt.mo71getParameterTypes(), resultInfo.pt.getTypeArguments());
                this.chk.checkRestricted(jCTree.pos(), symbol);
                break;
            case 10:
            case 11:
                classType = symbol.type;
                env2 = env;
                break;
            default:
                mu3.a("unexpected kind: ", symbol.kind, " in tree ", jCTree);
                return null;
        }
        if (symbol.name != this.names.init || jCTree.hasTag(JCTree.Tag.REFERENCE)) {
            this.chk.checkDeprecated(jCTree.pos(), env2.info.scope.owner, symbol);
            this.chk.checkSunAPI(jCTree.pos(), symbol);
            this.chk.checkProfile(jCTree.pos(), symbol);
            this.chk.checkPreview(jCTree.pos(), env2.info.scope.owner, type, symbol);
        }
        if (type2.isErroneous()) {
            classType = this.types.createErrorType(classType);
        }
        return check(jCTree, classType, symbol.kind.toSelector(), resultInfo);
    }

    public Type checkIntersection(JCTree jCTree, List<JCTree.JCExpression> list) {
        Attr attr;
        JCTree.JCExpression jCExpression;
        List<JCTree.JCExpression> list2;
        HashSet hashSet = new HashSet();
        if (list.nonEmpty()) {
            JCTree.JCExpression jCExpression2 = list.head;
            attr = this;
            jCExpression2.type = checkBase(jCExpression2.type, jCExpression2, this.env, false, false, false);
            hashSet.add(attr.types.erasure(list.head.type).tsym);
            boolean zIsErroneous = list.head.type.isErroneous();
            JCTree.JCExpression jCExpression3 = list.head;
            if (zIsErroneous) {
                return jCExpression3.type;
            }
            boolean zHasTag = jCExpression3.type.hasTag(TypeTag.TYPEVAR);
            List<JCTree.JCExpression> list3 = list.tail;
            if (!zHasTag) {
                for (JCTree.JCExpression jCExpression4 : list3) {
                    Type typeCheckBase = attr.checkBase(jCExpression4.type, jCExpression4, attr.env, false, true, false);
                    jCExpression4.type = typeCheckBase;
                    if (typeCheckBase.isErroneous()) {
                        list = List.of(jCExpression4);
                    } else if (jCExpression4.type.hasTag(TypeTag.CLASS)) {
                        attr.chk.checkNotRepeated(jCExpression4.pos(), attr.types.erasure(jCExpression4.type), hashSet);
                    }
                }
            } else if (list3.nonEmpty()) {
                attr.log.error(list.tail.head.pos(), CompilerProperties.Errors.TypeVarMayNotBeFollowedByOtherBounds);
                return list.head.type;
            }
        } else {
            attr = this;
        }
        if (list.length() == 0) {
            return attr.syms.objectType;
        }
        if (list.length() == 1) {
            return list.head.type;
        }
        Type.IntersectionClassType intersectionClassTypeMakeIntersectionType = attr.types.makeIntersectionType(TreeInfo.types(list));
        if (list.head.type.isInterface()) {
            jCExpression = null;
            list2 = list;
        } else {
            jCExpression = list.head;
            list2 = list.tail;
        }
        JCTree.JCClassDecl jCClassDeclClassDef = attr.make.at(jCTree).ClassDef(attr.make.Modifiers(Flags.AnnotationTypeElementMask), attr.names.empty, List.nil(), jCExpression, list2, List.nil());
        Symbol.ClassSymbol classSymbol = (Symbol.ClassSymbol) intersectionClassTypeMakeIntersectionType.tsym;
        Assert.check((classSymbol.flags() & 16777216) != 0);
        jCClassDeclClassDef.sym = classSymbol;
        Env<AttrContext> env = attr.env;
        classSymbol.sourcefile = env.toplevel.sourcefile;
        classSymbol.flags_field |= 268435456;
        attr.typeEnvs.put(classSymbol, attr.enter.classEnv(jCClassDeclClassDef, env));
        attr.attribClass(classSymbol);
        return intersectionClassTypeMakeIntersectionType;
    }

    public void checkLambdaCompatible(JCTree.JCLambda jCLambda, Type type, Check.CheckContext checkContext) {
        Type typeAsUndetVar = checkContext.inferenceContext().asUndetVar(type.mo73getReturnType());
        if (jCLambda.getBodyKind() == LambdaExpressionTree.BodyKind.STATEMENT && jCLambda.canCompleteNormally && !typeAsUndetVar.hasTag(TypeTag.VOID) && typeAsUndetVar != Type.recoveryType) {
            checkContext.report(jCLambda, this.diags.fragment(CompilerProperties.Fragments.IncompatibleRetTypeInLambda(CompilerProperties.Fragments.MissingRetVal(typeAsUndetVar))));
        }
        if (this.types.isSameTypes(checkContext.inferenceContext().asUndetVars(type.mo71getParameterTypes()), TreeInfo.types(jCLambda.params))) {
            return;
        }
        checkContext.report(jCLambda, this.diags.fragment(CompilerProperties.Fragments.IncompatibleArgTypesInLambda));
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public Type checkMethod(Type type, Symbol symbol, ResultInfo resultInfo, Env<AttrContext> env, List<JCTree.JCExpression> list, List<Type> list2, List<Type> list3) {
        ResultInfo resultInfo2;
        List<Type> map;
        Env<AttrContext> env2;
        Env<AttrContext> env3;
        Symbol.MethodSymbol methodSymbol;
        Type typeAsOuterSuper;
        Type type2 = type;
        Env<AttrContext> env4 = env;
        if ((symbol.flags() & 8) == 0 && ((type2.hasTag(TypeTag.CLASS) || type2.hasTag(TypeTag.TYPEVAR)) && (typeAsOuterSuper = this.types.asOuterSuper(type2, symbol.owner)) != null && typeAsOuterSuper.isRaw() && !this.types.isSameTypes(symbol.type.mo71getParameterTypes(), symbol.erasure(this.types).mo71getParameterTypes()))) {
            this.chk.warnUnchecked(env4.tree.pos(), CompilerProperties.LintWarnings.UncheckedCallMbrOfRawType(symbol, typeAsOuterSuper));
        }
        if (env4.info.defaultSuperCallSite != null) {
            for (Type type3 : this.types.interfaces(env4.enclClass.type).prepend(this.types.supertype(env4.enclClass.type))) {
                if (type3.tsym.isSubClass(symbol.enclClass(), this.types) && !this.types.isSameType(type3, env4.info.defaultSuperCallSite)) {
                    List<Symbol.MethodSymbol> listInterfaceCandidates = this.types.interfaceCandidates(type3, (Symbol.MethodSymbol) symbol);
                    if (listInterfaceCandidates.nonEmpty() && (methodSymbol = listInterfaceCandidates.head) != symbol && methodSymbol.overrides(symbol, methodSymbol.enclClass(), this.types, true)) {
                        this.log.error(env4.tree.pos(), CompilerProperties.Errors.IllegalDefaultSuperCall(env4.info.defaultSuperCallSite, CompilerProperties.Fragments.OverriddenDefault(symbol, type3)));
                        break;
                    }
                }
            }
            env4.info.defaultSuperCallSite = null;
        }
        if (symbol.isStatic() && type2.isInterface() && env4.tree.hasTag(JCTree.Tag.APPLY)) {
            JCTree.JCMethodInvocation jCMethodInvocation = (JCTree.JCMethodInvocation) env4.tree;
            if (jCMethodInvocation.meth.hasTag(JCTree.Tag.SELECT) && !TreeInfo.isStaticSelector(((JCTree.JCFieldAccess) jCMethodInvocation.meth).selected, this.names)) {
                this.log.error(env4.tree.pos(), CompilerProperties.Errors.IllegalStaticIntfMethCall(type2));
            }
        }
        Warner warner = new Warner();
        try {
            try {
                map = list2;
                try {
                    Type typeCheckMethod = this.rs.checkMethod(env4, type2, symbol, resultInfo, map, list3, warner);
                    env4 = env4;
                    resultInfo2 = resultInfo;
                    try {
                        DeferredAttr deferredAttr = this.deferredAttr;
                        Objects.requireNonNull(deferredAttr);
                        DeferredAttr.DeferredTypeMap deferredTypeMap = deferredAttr.new DeferredTypeMap(DeferredAttr.AttrMode.CHECK, symbol, env4.info.pendingResolutionPhase);
                        map = list2;
                        try {
                            map = map.map(deferredTypeMap);
                            if (warner.hasNonSilentLint(Lint.LintCategory.UNCHECKED)) {
                                try {
                                    this.chk.warnUnchecked(env4.tree.pos(), CompilerProperties.LintWarnings.UncheckedMethInvocationApplied(Kinds.kindName(symbol), symbol.name, this.rs.methodArguments(symbol.type.mo71getParameterTypes()), this.rs.methodArguments(map.map(deferredTypeMap)), Kinds.kindName(symbol.location()), symbol.location()));
                                    if (resultInfo2.pt != Infer.anyPoly || !typeCheckMethod.hasTag(TypeTag.METHOD) || !typeCheckMethod.isPartial()) {
                                        typeCheckMethod = new Type.MethodType(typeCheckMethod.mo71getParameterTypes(), this.types.erasure(typeCheckMethod.mo73getReturnType()), this.types.erasure(typeCheckMethod.mo74getThrownTypes()), this.syms.methodClass);
                                    }
                                } catch (Infer.InferenceException e) {
                                    e = e;
                                    type2 = type;
                                    env3 = env4;
                                    resultInfo2.checkContext.report(env3.tree.pos(), e.getDiagnostic());
                                    return this.types.createErrorType(type2);
                                }
                            }
                            TreeInfo.setPolyKind(env4.tree, (symbol.type.hasTag(TypeTag.FORALL) && symbol.type.mo73getReturnType().containsAny(((Type.ForAll) symbol.type).tvars)) ? JCTree.JCPolyExpression.PolyKind.POLY : JCTree.JCPolyExpression.PolyKind.STANDALONE);
                            return resultInfo2.pt == Infer.anyPoly ? typeCheckMethod : this.chk.checkMethod(typeCheckMethod, symbol, env4, list, map, env4.info.lastResolveVarargs(), resultInfo2.checkContext.inferenceContext());
                        } catch (Resolve.InapplicableMethodException e2) {
                            e = e2;
                            env2 = env4;
                            JCDiagnostic diagnostic = e.getDiagnostic();
                            Resolve resolve = this.rs;
                            Objects.requireNonNull(resolve);
                            Resolve.InapplicableSymbolError inapplicableSymbolError = new Resolve.InapplicableSymbolError(this, resolve, null, symbol, diagnostic) { // from class: com.sun.tools.javac.comp.Attr.11
                                final /* synthetic */ Attr this$0;
                                final /* synthetic */ JCDiagnostic val$diag;
                                final /* synthetic */ Symbol val$sym;

                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(resolve, methodResolutionContext);
                                    this.val$sym = symbol;
                                    this.val$diag = diagnostic;
                                    this.this$0 = this;
                                    Objects.requireNonNull(resolve);
                                }

                                @Override // com.sun.tools.javac.comp.Resolve.InapplicableSymbolError
                                public Pair<Symbol, JCDiagnostic> errCandidate() {
                                    return new Pair<>(this.val$sym, this.val$diag);
                                }
                            };
                            Resolve resolve2 = this.rs;
                            Objects.requireNonNull(resolve2);
                            this.log.report(inapplicableSymbolError.getDiagnostic(JCDiagnostic.DiagnosticType.ERROR, env2.tree, symbol, type, symbol.name, map.map(resolve2.new ResolveDeferredRecoveryMap(DeferredAttr.AttrMode.CHECK, symbol, env2.info.pendingResolutionPhase)), list3));
                            return this.types.createErrorType(type);
                        }
                    } catch (Infer.InferenceException e3) {
                        e = e3;
                        env3 = env4;
                        type2 = type;
                        resultInfo2.checkContext.report(env3.tree.pos(), e.getDiagnostic());
                        return this.types.createErrorType(type2);
                    }
                } catch (Infer.InferenceException e4) {
                    e = e4;
                    resultInfo2 = resultInfo;
                    env3 = env4;
                } catch (Resolve.InapplicableMethodException e5) {
                    e = e5;
                    env2 = env4;
                    JCDiagnostic diagnostic2 = e.getDiagnostic();
                    Resolve resolve3 = this.rs;
                    Objects.requireNonNull(resolve3);
                    Resolve.InapplicableSymbolError inapplicableSymbolError2 = new Resolve.InapplicableSymbolError(this, resolve3, null, symbol, diagnostic2) { // from class: com.sun.tools.javac.comp.Attr.11
                        final /* synthetic */ Attr this$0;
                        final /* synthetic */ JCDiagnostic val$diag;
                        final /* synthetic */ Symbol val$sym;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(resolve3, methodResolutionContext);
                            this.val$sym = symbol;
                            this.val$diag = diagnostic2;
                            this.this$0 = this;
                            Objects.requireNonNull(resolve3);
                        }

                        @Override // com.sun.tools.javac.comp.Resolve.InapplicableSymbolError
                        public Pair<Symbol, JCDiagnostic> errCandidate() {
                            return new Pair<>(this.val$sym, this.val$diag);
                        }
                    };
                    Resolve resolve4 = this.rs;
                    Objects.requireNonNull(resolve4);
                    this.log.report(inapplicableSymbolError2.getDiagnostic(JCDiagnostic.DiagnosticType.ERROR, env2.tree, symbol, type, symbol.name, map.map(resolve4.new ResolveDeferredRecoveryMap(DeferredAttr.AttrMode.CHECK, symbol, env2.info.pendingResolutionPhase)), list3));
                    return this.types.createErrorType(type);
                }
            } catch (Resolve.InapplicableMethodException e6) {
                e = e6;
                map = list2;
            }
        } catch (Infer.InferenceException e7) {
            e = e7;
            resultInfo2 = resultInfo;
        }
    }

    public Type checkMethodIdInternal(JCTree jCTree, Type type, Symbol symbol, Env<AttrContext> env, ResultInfo resultInfo) {
        return resultInfo.pkind.contains(Kinds.KindSelector.POLY) ? this.attrRecover.recoverMethodInvocation(jCTree, type, symbol, env, resultInfo) : checkIdInternal(jCTree, type, symbol, resultInfo.pt, env, resultInfo);
    }

    public void checkNewInnerClass(JCDiagnostic.DiagnosticPosition diagnosticPosition, Env<AttrContext> env, Type type, boolean z) {
        Symbol.TypeSymbol typeSymbol = type.tsym;
        Kinds.Kind kind = typeSymbol.owner.kind;
        boolean z2 = kind == Kinds.Kind.VAR || kind == Kinds.Kind.MTH;
        if ((typeSymbol.flags() & 2305843009213710848L) == 0) {
            if (z2 || type.tsym.isInner()) {
                if (z && env.enclClass.sym.isAnonymous()) {
                    return;
                }
                Resolve resolve = this.rs;
                Symbol symbolFindLocalClassOwner = z2 ? resolve.findLocalClassOwner(env, type.tsym) : resolve.findSelfContaining(diagnosticPosition, env, type.getEnclosingType().tsym, z);
                if (symbolFindLocalClassOwner.exists()) {
                    this.rs.accessBase(symbolFindLocalClassOwner, diagnosticPosition, env.enclClass.sym.type, this.names._this, true);
                } else {
                    this.log.error(diagnosticPosition, CompilerProperties.Errors.EnclClassRequired(type.tsym));
                }
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:26:0x0069  */
    public void checkReferenceCompatible(final JCTree.JCMemberReference jCMemberReference, Type type, final Type type2, Check.CheckContext checkContext, boolean z) {
        InferenceContext inferenceContext = checkContext.inferenceContext();
        Type typeAsUndetVar = inferenceContext.asUndetVar(type.mo73getReturnType());
        Type typeMo73getReturnType = (AnonymousClass13.$SwitchMap$com$sun$source$tree$MemberReferenceTree$ReferenceMode[jCMemberReference.getMode().ordinal()] == 1 && !jCMemberReference.expr.type.isRaw()) ? jCMemberReference.expr.type : type2.mo73getReturnType();
        TypeTag typeTag = TypeTag.VOID;
        Type type3 = null;
        Type type4 = typeAsUndetVar.hasTag(typeTag) ? null : typeMo73getReturnType;
        if (typeAsUndetVar.hasTag(typeTag) || typeMo73getReturnType.hasTag(typeTag)) {
            type3 = type4;
        } else {
            Type typeCapture = this.captureMRefReturnType ? this.types.capture(typeMo73getReturnType) : typeMo73getReturnType;
            if (!typeMo73getReturnType.isErroneous() && !new FunctionalReturnContext(checkContext).compatible(typeCapture, typeAsUndetVar, checkContext.checkWarner(jCMemberReference, typeCapture, typeAsUndetVar))) {
                type3 = type4;
            }
        }
        if (type3 != null) {
            checkContext.report(jCMemberReference, this.diags.fragment(CompilerProperties.Fragments.IncompatibleRetTypeInMref(CompilerProperties.Fragments.InconvertibleTypes(typeMo73getReturnType, type.mo73getReturnType()))));
        } else if (inferenceContext.free(type2)) {
            inferenceContext.addFreeTypeListener(List.of(type2), new Infer.FreeTypeListener() { // from class: com.sun.tools.javac.comp.p
                @Override // com.sun.tools.javac.comp.Infer.FreeTypeListener
                public final void typesInferred(InferenceContext inferenceContext2) {
                    jCMemberReference.referentType = inferenceContext2.asInstType(type2);
                }
            });
        } else {
            jCMemberReference.referentType = type2;
        }
        if (z || checkExConstraints(type2.mo74getThrownTypes(), type.mo74getThrownTypes(), inferenceContext)) {
            return;
        }
        this.log.error(jCMemberReference, CompilerProperties.Errors.IncompatibleThrownTypesInMref(type2.mo74getThrownTypes()));
    }

    public Type coerce(Type type, Type type2) {
        return this.cfolder.coerce(type, type2);
    }

    public Type condType(List<JCDiagnostic.DiagnosticPosition> list, List<Type> list2) {
        if (list2.isEmpty()) {
            return this.syms.objectType;
        }
        final Type type = list2.head;
        if (list2.tail.stream().allMatch(new Predicate() { // from class: vi0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return this.b.types.isSameType(type, (Type) obj);
            }
        })) {
            return type.baseType();
        }
        List<Type> list3 = (List) list2.stream().map(new Function() { // from class: yi0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Attr.x(this.b, (Type) obj);
            }
        }).collect(List.collector());
        if (list3.stream().allMatch(new Predicate() { // from class: nh0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((Type) obj).isPrimitive();
            }
        })) {
            for (final Type type2 : list3) {
                if (type2.getTag().isStrictSubRangeOf(TypeTag.INT) && list3.stream().filter(new Predicate() { // from class: oh0
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return Attr.R(type2, (Type) obj);
                    }
                }).allMatch(new Predicate() { // from class: ph0
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return Attr.M(this.b, type2, (Type) obj);
                    }
                })) {
                    return type2.baseType();
                }
            }
            for (TypeTag typeTag : primitiveTags) {
                final Type type3 = this.syms.typeOfTag[typeTag.ordinal()];
                if (list3.stream().allMatch(new Predicate() { // from class: qh0
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return this.b.types.isSubtype((Type) obj, type3);
                    }
                })) {
                    return type3;
                }
            }
        }
        List<Type> list4 = (List) list2.stream().map(new Function() { // from class: rh0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Attr.H(this.b, (Type) obj);
            }
        }).collect(List.collector());
        for (final Type type4 : list4) {
            if (list4.stream().filter(new Predicate() { // from class: sh0
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return Attr.j(type4, (Type) obj);
                }
            }).allMatch(new Predicate() { // from class: th0
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return this.b.types.isAssignable((Type) obj, type4);
                }
            })) {
                return type4.baseType();
            }
        }
        final Iterator<JCDiagnostic.DiagnosticPosition> it = list.iterator();
        return this.types.lub((List<Type>) ((List) list4.stream().map(new Function() { // from class: uh0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.chk.checkNonVoid((JCDiagnostic.DiagnosticPosition) it.next(), (Type) obj);
            }
        }).collect(List.collector())).stream().map(new Function() { // from class: wi0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((Type) obj).baseType();
            }
        }).filter(new Predicate() { // from class: xi0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return Attr.c((Type) obj);
            }
        }).collect(List.collector()));
    }

    public Check.CheckContext conditionalContext(Check.CheckContext checkContext) {
        return new Check.NestedCheckContext(checkContext) { // from class: com.sun.tools.javac.comp.Attr.5
            @Override // com.sun.tools.javac.comp.Check.NestedCheckContext, com.sun.tools.javac.comp.Check.CheckContext
            public void report(JCDiagnostic.DiagnosticPosition diagnosticPosition, JCDiagnostic jCDiagnostic) {
                this.enclosingContext.report(diagnosticPosition, Attr.this.diags.fragment(CompilerProperties.Fragments.IncompatibleTypeInConditional(jCDiagnostic)));
            }
        };
    }

    public Env<AttrContext> copyEnv(Env<AttrContext> env) {
        JCTree jCTree = env.tree;
        AttrContext attrContext = env.info;
        Env<AttrContext> envDup = env.dup(jCTree, attrContext.dup(copyScope(attrContext.scope)));
        Env<AttrContext> env2 = envDup.outer;
        if (env2 != null) {
            envDup.outer = copyEnv(env2);
        }
        return envDup;
    }

    public Scope.WriteableScope copyScope(Scope.WriteableScope writeableScope) {
        Scope.WriteableScope writeableScopeCreate = Scope.WriteableScope.create(writeableScope.owner);
        List listNil = List.nil();
        Iterator<Symbol> it = writeableScope.getSymbols().iterator();
        while (it.hasNext()) {
            listNil = listNil.prepend(it.next());
        }
        Iterator it2 = listNil.iterator();
        while (it2.hasNext()) {
            writeableScopeCreate.enter((Symbol) it2.next());
        }
        return writeableScopeCreate;
    }

    public Check.CheckContext diamondContext(final JCTree.JCNewClass jCNewClass, final Symbol.TypeSymbol typeSymbol, Check.CheckContext checkContext) {
        return new Check.NestedCheckContext(this, checkContext) { // from class: com.sun.tools.javac.comp.Attr.7
            final /* synthetic */ Attr this$0;

            {
                this.this$0 = this;
            }

            @Override // com.sun.tools.javac.comp.Check.NestedCheckContext, com.sun.tools.javac.comp.Check.CheckContext
            public void report(JCDiagnostic.DiagnosticPosition diagnosticPosition, JCDiagnostic jCDiagnostic) {
                this.enclosingContext.report(jCNewClass.clazz, this.this$0.diags.fragment(CompilerProperties.Fragments.CantApplyDiamond1(CompilerProperties.Fragments.Diamond(typeSymbol), jCDiagnostic)));
            }
        };
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Env<AttrContext> enclosingInitEnv(Env<AttrContext> env) {
        Env env2 = env;
        while (true) {
            switch (AnonymousClass13.$SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[env2.tree.getTag().ordinal()]) {
                case 15:
                case 16:
                case 21:
                    return null;
                case 19:
                    if (((JCTree.JCVariableDecl) env2.tree).sym.owner.kind == Kinds.Kind.TYP) {
                        return env2;
                    }
                    break;
                case 20:
                    if (env2.next.tree.hasTag(JCTree.Tag.CLASSDEF)) {
                        return env2;
                    }
                    break;
            }
            Assert.checkNonNull(env2.next);
            env2 = env2.next;
        }
    }

    public TargetInfo getTargetInfo(JCTree.JCPolyExpression jCPolyExpression, ResultInfo resultInfo, List<Type> list) {
        Type typeFallbackDescriptorType;
        Type type = resultInfo.pt;
        Type typeCreateErrorType = Type.recoveryType;
        if (type != typeCreateErrorType) {
            Type typeVisit = this.targetChecker.visit(type, jCPolyExpression);
            if (typeVisit.isIntersection()) {
                ListBuffer listBuffer = new ListBuffer();
                Iterator<Type> it = ((Type.IntersectionClassType) typeVisit).getExplicitComponents().iterator();
                while (it.hasNext()) {
                    Type next = it.next();
                    if (list != null) {
                        try {
                            next = this.infer.instantiateFunctionalInterface(jCPolyExpression, next, list, resultInfo.checkContext);
                        } catch (Types.FunctionDescriptorLookupError unused) {
                        }
                    }
                    if (next.tsym != this.syms.objectType.tsym && (!next.isInterface() || (next.tsym.flags() & 8192) != 0)) {
                        reportIntersectionError(jCPolyExpression, "not.an.intf.component", next);
                    }
                    listBuffer.add(this.types.removeWildcards(next));
                }
                typeCreateErrorType = this.types.makeIntersectionType(listBuffer.toList());
                typeCreateErrorType.tsym.flags_field |= 512;
                typeFallbackDescriptorType = this.types.findDescriptorType(typeCreateErrorType);
            } else {
                if (list != null) {
                    typeVisit = this.infer.instantiateFunctionalInterface(jCPolyExpression, typeVisit, list, resultInfo.checkContext);
                }
                typeCreateErrorType = this.types.removeWildcards(typeVisit);
                typeFallbackDescriptorType = this.types.findDescriptorType(typeCreateErrorType);
            }
        } else {
            typeFallbackDescriptorType = fallbackDescriptorType(jCPolyExpression);
        }
        if (jCPolyExpression.hasTag(JCTree.Tag.LAMBDA) && typeFallbackDescriptorType.hasTag(TypeTag.FORALL)) {
            resultInfo.checkContext.report(jCPolyExpression, this.diags.fragment(CompilerProperties.Fragments.InvalidGenericLambdaTarget(typeFallbackDescriptorType, Kinds.kindName(typeCreateErrorType.tsym), typeCreateErrorType.tsym)));
            typeCreateErrorType = this.types.createErrorType(pt());
        }
        return new TargetInfo(typeCreateErrorType, typeFallbackDescriptorType);
    }

    /* JADX WARN: Code duplicated, block: B:20:0x0047  */
    public boolean isAssignableAsBlankFinal(Symbol.VarSymbol varSymbol, Env<AttrContext> env) {
        boolean z;
        Symbol symbol = env.info.scope.owner;
        boolean z2 = false;
        if (varSymbol.owner == symbol) {
            z = true;
        } else {
            if ((symbol.name == this.names.init || symbol.kind == Kinds.Kind.VAR || (symbol.flags() & 1048576) != 0) && varSymbol.owner == symbol.owner) {
                if (((varSymbol.flags() & 8) != 0) == Resolve.isStatic(env)) {
                    z = true;
                }
            }
            z = false;
        }
        JCTree.JCMethodDecl jCMethodDecl = env.enclMethod;
        if (jCMethodDecl != null && TreeInfo.isCompactConstructor(jCMethodDecl)) {
            z2 = true;
        }
        return z & (!z2);
    }

    public boolean isStaticReference(JCTree jCTree) {
        if (!jCTree.hasTag(JCTree.Tag.SELECT)) {
            return true;
        }
        Symbol symbol = TreeInfo.symbol(((JCTree.JCFieldAccess) jCTree).selected);
        return symbol != null && symbol.kind == Kinds.Kind.TYP;
    }

    public ResultInfo lambdaBodyResult(JCTree.JCLambda jCLambda, Type type, ResultInfo resultInfo) {
        return type.mo73getReturnType() == Type.recoveryType ? this.recoveryInfo : new ResultInfo(this, Kinds.KindSelector.VAL, type.mo73getReturnType(), jCLambda.getBodyKind() == LambdaExpressionTree.BodyKind.EXPRESSION ? new ExpressionLambdaReturnContext((JCTree.JCExpression) jCLambda.getBody(), resultInfo.checkContext) : new FunctionalReturnContext(resultInfo.checkContext));
    }

    public Env<AttrContext> lambdaEnv(JCTree.JCLambda jCLambda, Env<AttrContext> env) {
        Env<AttrContext> envDup;
        AttrContext attrContext = env.info;
        Symbol symbol = attrContext.scope.owner;
        if (symbol.kind == Kinds.Kind.VAR && symbol.owner.kind == Kinds.Kind.TYP) {
            Symbol.ClassSymbol classSymbolEnclClass = symbol.enclClass();
            boolean zIsStatic = symbol.isStatic();
            Names names = this.names;
            Symbol.MethodSymbol methodSymbol = new Symbol.MethodSymbol((symbol.isStatic() ? 8 : 0) | 1052674, zIsStatic ? names.clinit : names.init, this.initBlockType, classSymbolEnclClass);
            methodSymbol.params = List.nil();
            AttrContext attrContext2 = env.info;
            envDup = env.dup(jCLambda, attrContext2.dup(attrContext2.scope.dupUnshared(methodSymbol)));
        } else {
            envDup = env.dup(jCLambda, attrContext.dup(attrContext.scope.dup()));
        }
        AttrContext attrContext3 = envDup.info;
        attrContext3.yieldResult = null;
        attrContext3.isLambda = true;
        return envDup;
    }

    public Type litType(TypeTag typeTag) {
        TypeTag typeTag2 = TypeTag.CLASS;
        Symtab symtab = this.syms;
        return typeTag == typeTag2 ? symtab.stringType : symtab.typeOfTag[typeTag.ordinal()];
    }

    public JCTree.JCExpression makeNullCheck(JCTree.JCExpression jCExpression) {
        if (jCExpression.getTag() != JCTree.Tag.NEWCLASS) {
            Name name = TreeInfo.name(jCExpression);
            Names names = this.names;
            if (name != names._this && name != names._super) {
                JCTree.Tag tag = JCTree.Tag.NULLCHK;
                JCTree.JCUnary jCUnaryUnary = this.make.at(jCExpression.pos).Unary(tag, jCExpression);
                jCUnaryUnary.operator = this.operators.resolveUnary(jCExpression, tag, jCExpression.type);
                jCUnaryUnary.type = jCExpression.type;
                return jCUnaryUnary;
            }
        }
        return jCExpression;
    }

    public ResultInfo memberReferenceQualifierResult(JCTree.JCMemberReference jCMemberReference) {
        return new ResultInfo(this, jCMemberReference.getMode() == MemberReferenceTree.ReferenceMode.INVOKE ? Kinds.KindSelector.VAL_TYP : Kinds.KindSelector.TYP, Type.noType);
    }

    public Type newMethodTemplate(Type type, List<Type> list, List<Type> list2) {
        Type.MethodType methodType = new Type.MethodType(list, type, List.nil(), this.syms.methodClass);
        return list2 == null ? methodType : new Type.ForAll(list2, methodType);
    }

    public Kinds.KindSelector pkind() {
        return this.resultInfo.pkind;
    }

    public void postAttr(JCTree jCTree) {
        new PostAttrAnalyzer().scan(jCTree);
    }

    public void preFlow(JCTree.JCLambda jCLambda) {
        this.attrRecover.doRecovery();
        new PostAttrAnalyzer() { // from class: com.sun.tools.javac.comp.Attr.8
            @Override // com.sun.tools.javac.comp.Attr.PostAttrAnalyzer, com.sun.tools.javac.tree.TreeScanner
            public void scan(JCTree jCTree) {
                if (jCTree != null) {
                    Type type = jCTree.type;
                    if (type == null || type != Type.stuckType) {
                        super.scan(jCTree);
                    }
                }
            }

            @Override // com.sun.tools.javac.comp.Attr.PostAttrAnalyzer, com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
            public void visitClassDef(JCTree.JCClassDecl jCClassDecl) {
            }

            @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
            public void visitLambda(JCTree.JCLambda jCLambda2) {
            }
        }.scan(jCLambda.body);
    }

    public boolean primitiveOrBoxed(Type type) {
        return (type.hasTag(TypeTag.TYPEVAR) || type.isErroneous() || !this.types.unboxedTypeOrType(type).isPrimitive()) ? false : true;
    }

    public Type pt() {
        return this.resultInfo.pt;
    }

    public void setPackageSymbols(JCTree.JCExpression jCExpression, Symbol symbol) {
        new TreeScanner(this, symbol) { // from class: com.sun.tools.javac.comp.Attr.12
            Symbol packge;
            final /* synthetic */ Attr this$0;
            final /* synthetic */ Symbol val$pkg;

            {
                this.val$pkg = symbol;
                this.this$0 = this;
                this.packge = symbol;
            }

            @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
            public void visitIdent(JCTree.JCIdent jCIdent) {
                jCIdent.sym = this.packge;
            }

            @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
            public void visitSelect(JCTree.JCFieldAccess jCFieldAccess) {
                Symbol symbol2 = this.packge;
                jCFieldAccess.sym = symbol2;
                this.packge = symbol2.owner;
                super.visitSelect(jCFieldAccess);
            }
        }.scan(jCExpression);
    }

    public Check.CheckContext switchExpressionContext(Check.CheckContext checkContext) {
        return new Check.NestedCheckContext(checkContext) { // from class: com.sun.tools.javac.comp.Attr.2
            @Override // com.sun.tools.javac.comp.Check.NestedCheckContext, com.sun.tools.javac.comp.Check.CheckContext
            public void report(JCDiagnostic.DiagnosticPosition diagnosticPosition, JCDiagnostic jCDiagnostic) {
                this.enclosingContext.report(diagnosticPosition, Attr.this.diags.fragment(CompilerProperties.Fragments.IncompatibleTypeInSwitchExpression(jCDiagnostic)));
            }
        };
    }

    public void validateTypeAnnotations(JCTree jCTree, boolean z) {
        jCTree.accept(new TypeAnnotationsValidator(z));
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitAnnotatedType(JCTree.JCAnnotatedType jCAnnotatedType) {
        attribAnnotationTypes(jCAnnotatedType.annotations, this.env);
        Type typePreannotatedType = attribType(jCAnnotatedType.underlyingType, this.env).preannotatedType();
        this.annotate.annotateTypeSecondStage(jCAnnotatedType, jCAnnotatedType.annotations, typePreannotatedType);
        jCAnnotatedType.type = typePreannotatedType;
        this.result = typePreannotatedType;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitAnnotation(JCTree.JCAnnotation jCAnnotation) {
        Assert.error("should be handled in annotate");
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitAnyPattern(JCTree.JCAnyPattern jCAnyPattern) {
        Type type = this.resultInfo.pt;
        jCAnyPattern.type = type;
        this.result = type;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitApply(JCTree.JCMethodInvocation jCMethodInvocation) {
        Attr attr;
        Env<AttrContext> env = this.env;
        Env<AttrContext> envDup = env.dup(jCMethodInvocation, env.info.dup());
        Name name = TreeInfo.name(jCMethodInvocation.meth);
        Names names = this.names;
        boolean z = name == names._this || name == names._super;
        ListBuffer<Type> listBuffer = new ListBuffer<>();
        if (z) {
            Kinds.KindSelector kindSelectorAttribArgs = attribArgs(Kinds.KindSelector.MTH, jCMethodInvocation.args, envDup, listBuffer);
            List<Type> list = listBuffer.toList();
            List<Type> listAttribTypes = attribTypes(jCMethodInvocation.typeargs, envDup);
            Env<AttrContext> env2 = this.env;
            env2.info.ctorPrologue = false;
            Type typeSupertype = env2.enclClass.sym.type;
            if (name == this.names._super) {
                if (typeSupertype == this.syms.objectType) {
                    this.log.error(jCMethodInvocation.meth.pos(), CompilerProperties.Errors.NoSuperclass(typeSupertype));
                    typeSupertype = this.types.createErrorType(this.syms.objectType);
                } else {
                    typeSupertype = this.types.supertype(typeSupertype);
                }
            }
            if (typeSupertype.hasTag(TypeTag.CLASS)) {
                Type enclosingType = typeSupertype.getEnclosingType();
                while (enclosingType != null && enclosingType.hasTag(TypeTag.TYPEVAR)) {
                    enclosingType = enclosingType.getUpperBound();
                }
                boolean zHasTag = enclosingType.hasTag(TypeTag.CLASS);
                JCTree.JCExpression jCExpression = jCMethodInvocation.meth;
                if (zHasTag) {
                    if (jCExpression.hasTag(JCTree.Tag.SELECT)) {
                        JCTree.JCExpression jCExpression2 = ((JCTree.JCFieldAccess) jCMethodInvocation.meth).selected;
                        this.chk.checkRefType(jCExpression2.pos(), attribExpr(jCExpression2, envDup, enclosingType));
                    }
                } else if (jCExpression.hasTag(JCTree.Tag.SELECT)) {
                    this.log.error(jCMethodInvocation.meth.pos(), CompilerProperties.Errors.IllegalQualNotIcls(typeSupertype.tsym));
                    attribExpr(((JCTree.JCFieldAccess) jCMethodInvocation.meth).selected, envDup, typeSupertype);
                }
                if (jCMethodInvocation.meth.hasTag(JCTree.Tag.IDENT)) {
                    checkNewInnerClass(jCMethodInvocation.meth.pos(), envDup, typeSupertype, true);
                }
                Symbol.TypeSymbol typeSymbol = typeSupertype.tsym;
                Symtab symtab = this.syms;
                if (typeSymbol == symtab.enumSym) {
                    list = list.prepend(symtab.intType).prepend(this.syms.stringType);
                }
                List<Type> list2 = list;
                AttrContext attrContext = envDup.info;
                boolean z2 = attrContext.selectSuper;
                attrContext.selectSuper = true;
                attrContext.pendingResolutionPhase = null;
                Type type = typeSupertype;
                Symbol symbolResolveConstructor = this.rs.resolveConstructor(jCMethodInvocation.meth.pos(), envDup, type, list2, listAttribTypes);
                envDup.info.selectSuper = z2;
                TreeInfo.setSymbol(jCMethodInvocation.meth, symbolResolveConstructor);
                Type typeNewMethodTemplate = newMethodTemplate(this.resultInfo.pt, list2, listAttribTypes);
                JCTree.JCExpression jCExpression3 = jCMethodInvocation.meth;
                ResultInfo resultInfo = new ResultInfo(this, kindSelectorAttribArgs, typeNewMethodTemplate);
                attr = this;
                attr.checkId(jCExpression3, type, symbolResolveConstructor, envDup, resultInfo);
                envDup = envDup;
            } else {
                Type type2 = typeSupertype;
                attr = this;
                if (type2.hasTag(TypeTag.ERROR) && jCMethodInvocation.meth.hasTag(JCTree.Tag.SELECT)) {
                    attr.attribExpr(((JCTree.JCFieldAccess) jCMethodInvocation.meth).selected, envDup, type2);
                }
            }
            Type.JCVoidType jCVoidType = attr.syms.voidType;
            jCMethodInvocation.type = jCVoidType;
            attr.result = jCVoidType;
        } else {
            attr = this;
            Kinds.KindSelector kindSelector = Kinds.KindSelector.VAL;
            Kinds.KindSelector kindSelectorAttribArgs2 = attr.attribArgs(kindSelector, jCMethodInvocation.args, envDup, listBuffer);
            List<Type> list3 = listBuffer.toList();
            List<Type> listAttribAnyTypes = attr.attribAnyTypes(jCMethodInvocation.typeargs, envDup);
            Type typeNewMethodTemplate2 = attr.newMethodTemplate(attr.resultInfo.pt, list3, listAttribAnyTypes);
            envDup.info.pendingResolutionPhase = null;
            Type typeAttribTree = attr.attribTree(jCMethodInvocation.meth, envDup, new ResultInfo(attr, kindSelectorAttribArgs2, typeNewMethodTemplate2, attr.resultInfo.checkContext));
            Type typeMo73getReturnType = typeAttribTree.mo73getReturnType();
            if (typeMo73getReturnType.hasTag(TypeTag.WILDCARD)) {
                x01.a(typeAttribTree);
                return;
            } else {
                Type typeAdjustMethodReturnType = attr.adjustMethodReturnType(TreeInfo.symbol(jCMethodInvocation.meth), jCMethodInvocation.meth.hasTag(JCTree.Tag.SELECT) ? ((JCTree.JCFieldAccess) jCMethodInvocation.meth).selected.type : attr.env.enclClass.sym.type, name, list3, typeMo73getReturnType);
                attr.chk.checkRefTypes(jCMethodInvocation.typeargs, listAttribAnyTypes);
                attr.result = attr.check(jCMethodInvocation, attr.resultInfo.checkContext.inferenceContext().cachedCapture(jCMethodInvocation, typeAdjustMethodReturnType, true), kindSelector, attr.resultInfo);
            }
        }
        attr.chk.checkRequiresIdentity(jCMethodInvocation, attr.env.info.lint);
        attr.chk.validate(jCMethodInvocation.typeargs, envDup);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitAssert(JCTree.JCAssert jCAssert) {
        attribExpr(jCAssert.cond, this.env, this.syms.booleanType);
        JCTree.JCExpression jCExpression = jCAssert.detail;
        if (jCExpression != null) {
            this.chk.checkNonVoid(jCExpression.pos(), attribExpr(jCAssert.detail, this.env));
        }
        this.result = null;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitAssign(JCTree.JCAssign jCAssign) {
        Type typeAttribTree = attribTree(jCAssign.lhs, this.env.dup(jCAssign), this.varAssignmentInfo);
        Type typeCapture = capture(typeAttribTree);
        attribExpr(jCAssign.rhs, this.env, typeAttribTree);
        this.result = check(jCAssign, typeCapture, Kinds.KindSelector.VAL, this.resultInfo);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitAssignop(JCTree.JCAssignOp jCAssignOp) {
        Type typeAttribTree = attribTree(jCAssignOp.lhs, this.env, this.varAssignmentInfo);
        Type typeAttribExpr = attribExpr(jCAssignOp.rhs, this.env);
        Symbol.OperatorSymbol operatorSymbolResolveBinary = this.operators.resolveBinary(jCAssignOp, jCAssignOp.getTag().noAssignOp(), typeAttribTree, typeAttribExpr);
        jCAssignOp.operator = operatorSymbolResolveBinary;
        if (operatorSymbolResolveBinary != this.operators.noOpSymbol && !typeAttribTree.isErroneous() && !typeAttribExpr.isErroneous()) {
            this.chk.checkDivZero(jCAssignOp.rhs.pos(), operatorSymbolResolveBinary, typeAttribExpr);
            this.chk.checkCastable(jCAssignOp.rhs.pos(), operatorSymbolResolveBinary.type.mo73getReturnType(), typeAttribTree);
            this.chk.checkLossOfPrecision(jCAssignOp.rhs.pos(), typeAttribExpr, typeAttribTree);
            this.chk.checkOutOfRangeShift(jCAssignOp.rhs.pos(), operatorSymbolResolveBinary, typeAttribExpr);
        }
        this.result = check(jCAssignOp, typeAttribTree, Kinds.KindSelector.VAL, this.resultInfo);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitBinary(JCTree.JCBinary jCBinary) {
        List<Symbol.BindingSymbol> listNil;
        Type typeFold2;
        Type typeCheckNonVoid = this.chk.checkNonVoid(jCBinary.lhs.pos(), attribExpr(jCBinary.lhs, this.env));
        MatchBindingsComputer.MatchBindings matchBindings = this.matchBindings;
        int i = AnonymousClass13.$SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[jCBinary.getTag().ordinal()];
        if (i != 17) {
            listNil = i != 18 ? List.nil() : matchBindings.bindingsWhenFalse;
        } else {
            listNil = matchBindings.bindingsWhenTrue;
        }
        Env<AttrContext> envBindingEnv = bindingEnv(this.env, listNil);
        try {
            Type typeCheckNonVoid2 = this.chk.checkNonVoid(jCBinary.rhs.pos(), attribExpr(jCBinary.rhs, envBindingEnv));
            envBindingEnv.info.scope.leave();
            this.matchBindings = this.matchBindingsComputer.binary(jCBinary, matchBindings, this.matchBindings);
            Symbol.OperatorSymbol operatorSymbolResolveBinary = this.operators.resolveBinary(jCBinary, jCBinary.getTag(), typeCheckNonVoid, typeCheckNonVoid2);
            jCBinary.operator = operatorSymbolResolveBinary;
            Type typeCreateErrorType = this.types.createErrorType(jCBinary.type);
            if (operatorSymbolResolveBinary != this.operators.noOpSymbol && !typeCheckNonVoid.isErroneous() && !typeCheckNonVoid2.isErroneous()) {
                typeCreateErrorType = operatorSymbolResolveBinary.type.mo73getReturnType();
                int i2 = operatorSymbolResolveBinary.opcode;
                if (typeCheckNonVoid.constValue() != null && typeCheckNonVoid2.constValue() != null && (typeFold2 = this.cfolder.fold2(i2, typeCheckNonVoid, typeCheckNonVoid2)) != null) {
                    typeCreateErrorType = this.cfolder.coerce(typeFold2, typeCreateErrorType);
                }
                if ((i2 == 165 || i2 == 166) && !this.types.isCastable(typeCheckNonVoid, typeCheckNonVoid2, new Warner(jCBinary.pos()))) {
                    this.log.error(jCBinary.pos(), CompilerProperties.Errors.IncomparableTypes(typeCheckNonVoid, typeCheckNonVoid2));
                }
                this.chk.checkDivZero(jCBinary.rhs.pos(), operatorSymbolResolveBinary, typeCheckNonVoid2);
                this.chk.checkOutOfRangeShift(jCBinary.rhs.pos(), operatorSymbolResolveBinary, typeCheckNonVoid2);
            }
            this.result = check(jCBinary, typeCreateErrorType, Kinds.KindSelector.VAL, this.resultInfo);
        } catch (Throwable th) {
            envBindingEnv.info.scope.leave();
            throw th;
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitBindingPattern(JCTree.JCBindingPattern jCBindingPattern) {
        JCTree.JCExpression jCExpression = jCBindingPattern.var.vartype;
        Type typeAttribType = jCExpression != null ? attribType(jCExpression, this.env) : this.resultInfo.pt;
        JCTree.JCVariableDecl jCVariableDecl = jCBindingPattern.var;
        jCVariableDecl.type = typeAttribType;
        jCBindingPattern.type = typeAttribType;
        Symbol.BindingSymbol bindingSymbol = new Symbol.BindingSymbol(jCVariableDecl.mods.flags, jCVariableDecl.name, typeAttribType, this.env.info.scope.owner);
        bindingSymbol.pos = jCBindingPattern.pos;
        JCTree.JCVariableDecl jCVariableDecl2 = jCBindingPattern.var;
        jCVariableDecl2.sym = bindingSymbol;
        if (this.chk.checkUnique(jCVariableDecl2.pos(), bindingSymbol, this.env.info.scope)) {
            this.chk.checkTransparentVar(jCBindingPattern.var.pos(), bindingSymbol, this.env.info.scope);
        }
        this.chk.validate(jCBindingPattern.var.vartype, this.env, true);
        if (jCBindingPattern.var.isImplicitlyTyped()) {
            JCTree.JCVariableDecl jCVariableDecl3 = jCBindingPattern.var;
            if (typeAttribType == Type.noType) {
                typeAttribType = this.syms.errType;
            }
            setSyntheticVariableType(jCVariableDecl3, typeAttribType);
        }
        this.annotate.annotateLater(jCBindingPattern.var.mods.annotations, this.env, bindingSymbol);
        if (!jCBindingPattern.var.isImplicitlyTyped()) {
            this.annotate.queueScanTreeAndTypeAnnotate(jCBindingPattern.var.vartype, this.env, bindingSymbol);
        }
        this.annotate.flush();
        this.result = jCBindingPattern.type;
        if (bindingSymbol.isUnnamedVariable()) {
            this.matchBindings = MatchBindingsComputer.EMPTY;
        } else {
            this.matchBindings = new MatchBindingsComputer.MatchBindings(List.of(bindingSymbol), List.nil());
        }
        this.chk.checkRequiresIdentity(jCBindingPattern, this.env.info.lint);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitBlock(JCTree.JCBlock jCBlock) {
        Env<AttrContext> env = this.env;
        AttrContext attrContext = env.info;
        if (attrContext.scope.owner.kind == Kinds.Kind.TYP || attrContext.scope.owner.kind == Kinds.Kind.ERR) {
            Symbol.MethodSymbol methodSymbol = new Symbol.MethodSymbol(jCBlock.flags | 1048576 | (this.env.info.scope.owner.flags() & 2048), this.names.empty, this.initBlockType, this.env.info.scope.owner);
            Env<AttrContext> env2 = this.env;
            AttrContext attrContext2 = env2.info;
            Env<AttrContext> envDup = env2.dup(jCBlock, attrContext2.dup(attrContext2.scope.dupUnshared(methodSymbol)));
            if ((jCBlock.flags & 8) != 0) {
                envDup.info.staticLevel++;
            }
            this.annotate.queueScanTreeAndTypeAnnotate(jCBlock, envDup, envDup.info.scope.owner);
            this.annotate.flush();
            attribStats(jCBlock.stats, envDup);
            Symbol.ClassSymbol classSymbol = (Symbol.ClassSymbol) this.env.info.scope.owner;
            List<Attribute.TypeCompound> rawTypeAttributes = envDup.info.scope.owner.getRawTypeAttributes();
            if ((jCBlock.flags & 8) != 0) {
                classSymbol.appendClassInitTypeAttributes(rawTypeAttributes);
            } else {
                classSymbol.appendInitTypeAttributes(rawTypeAttributes);
            }
        } else {
            Env<AttrContext> envDup2 = env.dup(jCBlock, attrContext.dup(attrContext.scope.dup()));
            try {
                attribStats(jCBlock.stats, envDup2);
                envDup2.info.scope.leave();
            } catch (Throwable th) {
                envDup2.info.scope.leave();
                throw th;
            }
        }
        this.result = null;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitBreak(JCTree.JCBreak jCBreak) {
        jCBreak.target = findJumpTarget(jCBreak.pos(), jCBreak.getTag(), jCBreak.label, this.env);
        this.result = null;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitClassDef(JCTree.JCClassDecl jCClassDecl) {
        Optional optionalOfNullable = Optional.ofNullable(this.env.info.attributionMode.isSpeculative ? this.argumentAttr.withLocalCacheContext() : null);
        AttrContext attrContext = this.env.info;
        boolean z = attrContext.ctorPrologue;
        try {
            if (attrContext.scope.owner.kind.matches(Kinds.KindSelector.VAL_MTH)) {
                this.enter.classEnter(jCClassDecl, this.env);
            } else if (this.env.tree.hasTag(JCTree.Tag.NEWCLASS) && TreeInfo.isInAnnotation(this.env, jCClassDecl)) {
                this.enter.classEnter(jCClassDecl, this.env);
            }
            Symbol.ClassSymbol classSymbol = jCClassDecl.sym;
            if (classSymbol == null) {
                this.result = null;
            } else {
                classSymbol.complete();
                if (z) {
                    classSymbol.flags_field |= ClassFileConstants.JDK20;
                }
                attribClass(jCClassDecl.pos(), classSymbol);
                Type type = classSymbol.type;
                jCClassDecl.type = type;
                this.result = type;
            }
        } finally {
            optionalOfNullable.ifPresent(new Consumer() { // from class: com.sun.tools.javac.comp.o
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((ArgumentAttr.LocalCacheContext) obj).leave();
                }
            });
            this.env.info.ctorPrologue = z;
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitConditional(JCTree.JCConditional jCConditional) {
        ResultInfo resultInfoDup;
        Type typeAttribExpr = attribExpr(jCConditional.cond, this.env, this.syms.booleanType);
        MatchBindingsComputer.MatchBindings matchBindings = this.matchBindings;
        Type typePt = pt();
        TypeTag typeTag = TypeTag.NONE;
        JCTree.JCPolyExpression.PolyKind polyKind = ((!typePt.hasTag(typeTag) || pt() == Type.recoveryType || pt() == Infer.anyPoly) && !isBooleanOrNumeric(this.env, jCConditional)) ? JCTree.JCPolyExpression.PolyKind.POLY : JCTree.JCPolyExpression.PolyKind.STANDALONE;
        jCConditional.polyKind = polyKind;
        if (polyKind == JCTree.JCPolyExpression.PolyKind.POLY && this.resultInfo.pt.hasTag(TypeTag.VOID)) {
            this.resultInfo.checkContext.report(jCConditional, this.diags.fragment(CompilerProperties.Fragments.ConditionalTargetCantBeVoid));
            Type typeCreateErrorType = this.types.createErrorType(this.resultInfo.pt);
            jCConditional.type = typeCreateErrorType;
            this.result = typeCreateErrorType;
            return;
        }
        JCTree.JCPolyExpression.PolyKind polyKind2 = jCConditional.polyKind;
        JCTree.JCPolyExpression.PolyKind polyKind3 = JCTree.JCPolyExpression.PolyKind.STANDALONE;
        if (polyKind2 == polyKind3) {
            resultInfoDup = this.unknownExprInfo;
        } else {
            ResultInfo resultInfo = this.resultInfo;
            resultInfoDup = resultInfo.dup(conditionalContext(resultInfo.checkContext));
        }
        Env<AttrContext> envBindingEnv = bindingEnv(this.env, matchBindings.bindingsWhenTrue);
        try {
            Type typeAttribTree = attribTree(jCConditional.truepart, envBindingEnv, resultInfoDup);
            envBindingEnv.info.scope.leave();
            MatchBindingsComputer.MatchBindings matchBindings2 = this.matchBindings;
            Env<AttrContext> envBindingEnv2 = bindingEnv(this.env, matchBindings.bindingsWhenFalse);
            try {
                Type typeAttribTree2 = attribTree(jCConditional.falsepart, envBindingEnv2, resultInfoDup);
                envBindingEnv2.info.scope.leave();
                MatchBindingsComputer.MatchBindings matchBindings3 = this.matchBindings;
                Type typeCondType = jCConditional.polyKind == polyKind3 ? condType(List.of(jCConditional.truepart.pos(), jCConditional.falsepart.pos()), List.of(typeAttribTree, typeAttribTree2)) : pt();
                if (typeAttribExpr.constValue() != null && typeAttribTree.constValue() != null && typeAttribTree2.constValue() != null && !typeCondType.hasTag(typeTag)) {
                    ConstFold constFold = this.cfolder;
                    if (!typeAttribExpr.isTrue()) {
                        typeAttribTree = typeAttribTree2;
                    }
                    typeCondType = constFold.coerce(typeAttribTree, typeCondType);
                }
                this.result = check(jCConditional, typeCondType, Kinds.KindSelector.VAL, this.resultInfo);
                this.matchBindings = this.matchBindingsComputer.conditional(jCConditional, matchBindings, matchBindings2, matchBindings3);
            } catch (Throwable th) {
                envBindingEnv2.info.scope.leave();
                throw th;
            }
        } catch (Throwable th2) {
            envBindingEnv.info.scope.leave();
            throw th2;
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitContinue(JCTree.JCContinue jCContinue) {
        jCContinue.target = findJumpTarget(jCContinue.pos(), jCContinue.getTag(), jCContinue.label, this.env);
        this.result = null;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitDoLoop(JCTree.JCDoWhileLoop jCDoWhileLoop) {
        attribStat(jCDoWhileLoop.body, this.env.dup(jCDoWhileLoop));
        attribExpr(jCDoWhileLoop.cond, this.env, this.syms.booleanType);
        handleLoopConditionBindings(this.matchBindings, jCDoWhileLoop, jCDoWhileLoop.body);
        this.result = null;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitErroneous(JCTree.JCErroneous jCErroneous) {
        if (jCErroneous.errs != null) {
            Env<AttrContext> env = this.env;
            Scope.WriteableScope writeableScopeDupUnshared = env.info.scope;
            if (env.tree instanceof JCTree.JCClassDecl) {
                writeableScopeDupUnshared = writeableScopeDupUnshared.dupUnshared(new Symbol.MethodSymbol(1048576L, this.names.empty, null, this.env.info.scope.owner));
            }
            Env<AttrContext> env2 = this.env;
            Env<AttrContext> envDup = env2.dup(env2.tree, env2.info.dup(writeableScopeDupUnshared));
            envDup.info.returnResult = this.unknownExprInfo;
            Iterator<? extends JCTree> it = jCErroneous.errs.iterator();
            while (it.hasNext()) {
                attribTree(it.next(), envDup, new ResultInfo(this, Kinds.KindSelector.ERR, pt()));
            }
        }
        Type type = this.syms.errType;
        jCErroneous.type = type;
        this.result = type;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitExec(JCTree.JCExpressionStatement jCExpressionStatement) {
        attribExpr(jCExpressionStatement.expr, this.env.dup(jCExpressionStatement));
        this.result = null;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitForLoop(JCTree.JCForLoop jCForLoop) {
        Env<AttrContext> env = this.env;
        JCTree jCTree = env.tree;
        AttrContext attrContext = env.info;
        Env<AttrContext> envDup = env.dup(jCTree, attrContext.dup(attrContext.scope.dup()));
        MatchBindingsComputer.MatchBindings matchBindings = MatchBindingsComputer.EMPTY;
        try {
            attribStats(jCForLoop.init, envDup);
            JCTree.JCExpression jCExpression = jCForLoop.cond;
            if (jCExpression != null) {
                attribExpr(jCExpression, envDup, this.syms.booleanType);
                matchBindings = this.matchBindings;
            }
            Env<AttrContext> envBindingEnv = bindingEnv(envDup, matchBindings.bindingsWhenTrue);
            try {
                envBindingEnv.tree = jCForLoop;
                attribStats(jCForLoop.step, envBindingEnv);
                attribStat(jCForLoop.body, envBindingEnv);
                envBindingEnv.info.scope.leave();
                this.result = null;
                envDup.info.scope.leave();
                handleLoopConditionBindings(matchBindings, jCForLoop, jCForLoop.body);
            } catch (Throwable th) {
                envBindingEnv.info.scope.leave();
                throw th;
            }
        } catch (Throwable th2) {
            envDup.info.scope.leave();
            throw th2;
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitForeachLoop(JCTree.JCEnhancedForLoop jCEnhancedForLoop) {
        Env<AttrContext> env = this.env;
        JCTree jCTree = env.tree;
        AttrContext attrContext = env.info;
        Env<AttrContext> envDup = env.dup(jCTree, attrContext.dup(attrContext.scope.dup()));
        try {
            Type typeCvarUpperBound = this.types.cvarUpperBound(attribExpr(jCEnhancedForLoop.expr, envDup));
            this.chk.checkNonVoid(jCEnhancedForLoop.pos(), typeCvarUpperBound);
            Type typeElemtype = this.types.elemtype(typeCvarUpperBound);
            if (typeElemtype == null) {
                Type typeAsSuper = this.types.asSuper(typeCvarUpperBound, this.syms.iterableType.tsym);
                if (typeAsSuper == null) {
                    this.log.error(jCEnhancedForLoop.expr.pos(), CompilerProperties.Errors.ForeachNotApplicableToType(typeCvarUpperBound, CompilerProperties.Fragments.TypeReqArrayOrIterable));
                    typeElemtype = this.types.createErrorType(typeCvarUpperBound);
                } else {
                    List<Type> listAllparams = typeAsSuper.allparams();
                    typeElemtype = listAllparams.isEmpty() ? this.syms.objectType : this.types.wildUpperBound(listAllparams.head);
                    if (this.types.asSuper(this.rs.resolveInternalMethod(jCEnhancedForLoop.pos(), envDup, this.types.skipTypeVars(typeCvarUpperBound, false), this.names.iterator, List.nil(), List.nil()).type.mo73getReturnType(), this.syms.iteratorType.tsym) == null) {
                        this.log.error(jCEnhancedForLoop.pos(), CompilerProperties.Errors.ForeachNotApplicableToType(typeCvarUpperBound, CompilerProperties.Fragments.TypeReqArrayOrIterable));
                    }
                }
            }
            if (jCEnhancedForLoop.var.isImplicitlyTyped()) {
                Check check = this.chk;
                JCTree.JCVariableDecl jCVariableDecl = jCEnhancedForLoop.var;
                setSyntheticVariableType(jCEnhancedForLoop.var, check.checkLocalVarType(jCVariableDecl, typeElemtype, jCVariableDecl.name));
            }
            attribStat(jCEnhancedForLoop.var, envDup);
            this.chk.checkType(jCEnhancedForLoop.expr.pos(), typeElemtype, jCEnhancedForLoop.var.sym.type);
            envDup.tree = jCEnhancedForLoop;
            attribStat(jCEnhancedForLoop.body, envDup);
            this.result = null;
        } finally {
            envDup.info.scope.leave();
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitIdent(JCTree.JCIdent jCIdent) {
        Symbol symbolResolveMethod;
        Symbol symbol;
        if (pt().hasTag(TypeTag.METHOD) || pt().hasTag(TypeTag.FORALL)) {
            this.env.info.pendingResolutionPhase = null;
            symbolResolveMethod = this.rs.resolveMethod(jCIdent.pos(), this.env, jCIdent.name, pt().mo71getParameterTypes(), pt().getTypeArguments());
        } else {
            symbolResolveMethod = jCIdent.sym;
            if (symbolResolveMethod == null || symbolResolveMethod.kind == Kinds.Kind.VAR) {
                symbolResolveMethod = this.rs.resolveIdent(jCIdent.pos(), this.env, jCIdent.name, pkind());
            }
        }
        Symbol symbol2 = symbolResolveMethod;
        jCIdent.sym = symbol2;
        Env env = this.env;
        if (env.enclClass.sym.owner.kind != Kinds.Kind.PCK && symbol2.kind.matches(Kinds.KindSelector.VAL_MTH) && symbol2.owner.kind == Kinds.Kind.TYP) {
            Name name = jCIdent.name;
            Names names = this.names;
            if (name != names._this && name != names._super) {
                while (env.outer != null && !symbol2.isMemberOf(env.enclClass.sym, this.types)) {
                    env = env.outer;
                }
            }
        }
        if (symbol2.kind == Kinds.Kind.VAR) {
            Symbol.VarSymbol varSymbol = (Symbol.VarSymbol) symbol2;
            checkInit(jCIdent, this.env, varSymbol, false);
            if (Kinds.KindSelector.ASG.subset(pkind())) {
                checkAssignable(jCIdent.pos(), varSymbol, null, this.env);
            }
        }
        Env env2 = this.env;
        Kinds.Kind kind = symbol2.kind;
        if (kind != Kinds.Kind.ERR && kind != Kinds.Kind.TYP && (symbol = symbol2.owner) != null && symbol != env2.enclClass.sym) {
            while (env2.outer != null && !this.rs.isAccessible(this.env, env2.enclClass.sym.type, symbol2)) {
                env2 = env2.outer;
            }
        }
        AttrContext attrContext = this.env.info;
        if (attrContext.isSerializable) {
            this.chk.checkAccessFromSerializableElement(jCIdent, attrContext.isSerializableLambda);
        }
        this.result = checkId(jCIdent, env2.enclClass.sym.type, symbol2, this.env, this.resultInfo);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitIf(JCTree.JCIf jCIf) {
        boolean zAliveAfter;
        attribExpr(jCIf.cond, this.env, this.syms.booleanType);
        MatchBindingsComputer.MatchBindings matchBindings = this.matchBindings;
        Env<AttrContext> envBindingEnv = bindingEnv(this.env, matchBindings.bindingsWhenTrue);
        try {
            attribStat(jCIf.thenpart, envBindingEnv);
            envBindingEnv.info.scope.leave();
            preFlow(jCIf.thenpart);
            boolean zAliveAfter2 = this.flow.aliveAfter(this.env, jCIf.thenpart, this.make);
            if (jCIf.elsepart != null) {
                Env<AttrContext> envBindingEnv2 = bindingEnv(this.env, matchBindings.bindingsWhenFalse);
                try {
                    attribStat(jCIf.elsepart, envBindingEnv2);
                    envBindingEnv2.info.scope.leave();
                    preFlow(jCIf.elsepart);
                    zAliveAfter = this.flow.aliveAfter(this.env, jCIf.elsepart, this.make);
                } catch (Throwable th) {
                    envBindingEnv2.info.scope.leave();
                    throw th;
                }
            } else {
                zAliveAfter = true;
            }
            this.chk.checkEmptyIf(jCIf);
            List<Symbol.BindingSymbol> listNil = List.nil();
            if (zAliveAfter2 && !zAliveAfter) {
                listNil = matchBindings.bindingsWhenTrue;
            } else if (zAliveAfter && !zAliveAfter2) {
                listNil = matchBindings.bindingsWhenFalse;
            }
            addBindings2Scope(jCIf, listNil);
            this.result = null;
        } catch (Throwable th2) {
            envBindingEnv.info.scope.leave();
            throw th2;
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitImport(JCTree.JCImport jCImport) {
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitIndexed(JCTree.JCArrayAccess jCArrayAccess) {
        Type typeCreateErrorType = this.types.createErrorType(jCArrayAccess.type);
        Type typeAttribExpr = attribExpr(jCArrayAccess.indexed, this.env);
        attribExpr(jCArrayAccess.index, this.env, this.syms.intType);
        if (this.types.isArray(typeAttribExpr)) {
            typeCreateErrorType = this.types.elemtype(typeAttribExpr);
        } else if (!typeAttribExpr.hasTag(TypeTag.ERROR)) {
            this.log.error(jCArrayAccess.pos(), CompilerProperties.Errors.ArrayReqButFound(typeAttribExpr));
        }
        if (!pkind().contains(Kinds.KindSelector.VAL)) {
            typeCreateErrorType = capture(typeCreateErrorType);
        }
        this.result = check(jCArrayAccess, typeCreateErrorType, Kinds.KindSelector.VAR, this.resultInfo);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitLabelled(JCTree.JCLabeledStatement jCLabeledStatement) {
        for (Env env = this.env; env != null && !env.tree.hasTag(JCTree.Tag.CLASSDEF); env = env.next) {
            if (env.tree.hasTag(JCTree.Tag.LABELLED) && ((JCTree.JCLabeledStatement) env.tree).label == jCLabeledStatement.label) {
                this.log.error(jCLabeledStatement.pos(), CompilerProperties.Errors.LabelAlreadyInUse(jCLabeledStatement.label));
                break;
            }
        }
        attribStat(jCLabeledStatement.body, this.env.dup(jCLabeledStatement));
        this.result = null;
    }

    /* JADX WARN: Code duplicated, block: B:150:0x02de  */
    /* JADX WARN: Code duplicated, block: B:163:0x031d  */
    /* JADX WARN: Code duplicated, block: B:217:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:218:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:219:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:220:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code restructure failed: missing block: B:225:?, code lost:
    
        throw r0;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 6, insn: 0x034c: INVOKE 
      (r4v1 ?? I:com.sun.tools.javac.comp.Attr)
      (r6 I:com.sun.tools.javac.tree.JCTree)
      (r0 I:com.sun.tools.javac.comp.Env)
      (r1 I:com.sun.tools.javac.comp.Attr$ResultInfo)
     VIRTUAL call: com.sun.tools.javac.comp.Attr.attribTree(com.sun.tools.javac.tree.JCTree, com.sun.tools.javac.comp.Env, com.sun.tools.javac.comp.Attr$ResultInfo):com.sun.tools.javac.code.Type A[Catch: all -> 0x0358, MD:(com.sun.tools.javac.tree.JCTree, com.sun.tools.javac.comp.Env<com.sun.tools.javac.comp.AttrContext>, com.sun.tools.javac.comp.Attr$ResultInfo):com.sun.tools.javac.code.Type (m), TRY_LEAVE], block:B:177:0x0348 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.sun.tools.javac.comp.Attr, com.sun.tools.javac.comp.DeferredAttr$AttrMode] */
    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void visitLambda(JCTree.JCLambda jCLambda) {
        boolean z;
        JCTree jCTreeAttribTree;
        Type type;
        Type.JCNoType jCNoType;
        Attr attr;
        JCTree.JCLambda jCLambda2;
        Type type2;
        List<Type> listTypes;
        Type typeCheck;
        Type type3;
        Type.JCNoType jCNoType2;
        Type type4;
        Type.JCNoType jCNoType3;
        Type type5;
        Type.JCNoType jCNoType4;
        boolean z2 = true;
        boolean z3 = false;
        if (pt().isErroneous() || (pt().hasTag(TypeTag.NONE) && pt() != Type.recoveryType)) {
            if (pt().hasTag(TypeTag.NONE)) {
                AttrContext attrContext = this.env.info;
                if (attrContext.enclVar == null || !attrContext.enclVar.type.isErroneous()) {
                    this.log.error(jCLambda.pos(), CompilerProperties.Errors.UnexpectedLambda);
                }
            }
            this.resultInfo = this.recoveryInfo;
            z = true;
        } else {
            z = false;
        }
        Env<AttrContext> envLambdaEnv = lambdaEnv(jCLambda, this.env);
        DeferredAttr.AttrMode attrMode = this.resultInfo.checkContext.deferredAttrContext().mode;
        ?? r4 = DeferredAttr.AttrMode.CHECK;
        boolean z4 = attrMode == r4;
        try {
            if (z4) {
                try {
                    if (this.rs.isSerializable(pt())) {
                        AttrContext attrContext2 = envLambdaEnv.info;
                        attrContext2.isSerializable = true;
                        attrContext2.isSerializableLambda = true;
                    }
                } catch (Symbol.CompletionFailure e) {
                    e = e;
                    attr = this;
                    jCLambda2 = jCLambda;
                    z3 = z4;
                    attr.chk.completionError(jCLambda2.pos(), e);
                    envLambdaEnv.info.scope.leave();
                    if (z3) {
                        type2 = attr.result;
                        try {
                            attr.attribTree(jCLambda2, attr.env, attr.recoveryInfo);
                        } finally {
                            if (attr.result == Type.recoveryType) {
                                attr.result = type2;
                            }
                        }
                    }
                } catch (Types.FunctionDescriptorLookupError e2) {
                    e = e2;
                    attr = this;
                    jCLambda2 = jCLambda;
                    z3 = z4;
                    attr.resultInfo.checkContext.report(jCLambda2, e.getDiagnostic());
                    Type typeCreateErrorType = attr.types.createErrorType(attr.pt());
                    jCLambda2.type = typeCreateErrorType;
                    attr.result = typeCreateErrorType;
                    envLambdaEnv.info.scope.leave();
                    if (z3) {
                        type2 = attr.result;
                        try {
                            attr.attribTree(jCLambda2, attr.env, attr.recoveryInfo);
                        } finally {
                            if (attr.result == Type.recoveryType) {
                                attr.result = type2;
                            }
                        }
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            if (jCLambda.paramKind == JCTree.JCLambda.ParameterKind.EXPLICIT) {
                attribStats(jCLambda.params, envLambdaEnv);
                listTypes = TreeInfo.types(jCLambda.params);
            } else {
                listTypes = null;
            }
            TargetInfo targetInfo = getTargetInfo(jCLambda, this.resultInfo, listTypes);
            Type type6 = targetInfo.target;
            Type type7 = targetInfo.descriptor;
            if (type6.isErroneous()) {
                jCLambda.type = type6;
                this.result = type6;
                envLambdaEnv.info.scope.leave();
                if (z4) {
                    Type type8 = this.result;
                    try {
                        attribTree(jCLambda, this.env, this.recoveryInfo);
                        if (type5 == jCNoType4) {
                            return;
                        } else {
                            return;
                        }
                    } finally {
                        if (this.result == Type.recoveryType) {
                            this.result = type8;
                        }
                    }
                }
                return;
            }
            attr = this;
            jCLambda2 = jCLambda;
            try {
                attr.setFunctionalInfo(envLambdaEnv, jCLambda2, pt(), type7, type6, this.resultInfo.checkContext);
                if (type7.hasTag(TypeTag.FORALL)) {
                    attr.resultInfo.checkContext.report(jCLambda2, attr.diags.fragment(CompilerProperties.Fragments.InvalidGenericLambdaTarget(type7, Kinds.kindName(type6.tsym), type6.tsym)));
                    Type typeCreateErrorType2 = attr.types.createErrorType(attr.pt());
                    jCLambda2.type = typeCreateErrorType2;
                    attr.result = typeCreateErrorType2;
                    envLambdaEnv.info.scope.leave();
                    if (z4) {
                        Type type9 = attr.result;
                        try {
                            attr.attribTree(jCLambda2, attr.env, attr.recoveryInfo);
                            if (type4 == jCNoType3) {
                                return;
                            } else {
                                return;
                            }
                        } finally {
                            if (attr.result == Type.recoveryType) {
                                attr.result = type9;
                            }
                        }
                    }
                    return;
                }
                if (jCLambda2.paramKind == JCTree.JCLambda.ParameterKind.IMPLICIT) {
                    List listMo71getParameterTypes = type7.mo71getParameterTypes();
                    List list = jCLambda2.params;
                    boolean z5 = false;
                    List list2 = listMo71getParameterTypes;
                    while (list.nonEmpty()) {
                        if (list2.isEmpty()) {
                            z5 = true;
                        }
                        Type type10 = z5 ? attr.syms.errType : (Type) list2.head;
                        if (((JCTree.JCVariableDecl) list.head).isImplicitlyTyped()) {
                            attr.setSyntheticVariableType((JCTree.JCVariableDecl) list.head, type10);
                        }
                        ((JCTree.JCVariableDecl) list.head).sym = null;
                        if (!list2.isEmpty()) {
                            list2 = list2.tail;
                        }
                        list = list.tail;
                        list2 = list2;
                    }
                    attr.attribStats(jCLambda2.params, envLambdaEnv);
                    if (z5) {
                        attr.resultInfo.checkContext.report(jCLambda2, attr.diags.fragment(CompilerProperties.Fragments.IncompatibleArgTypesInLambda));
                        Type typeCreateErrorType3 = attr.types.createErrorType(type6);
                        jCLambda2.type = typeCreateErrorType3;
                        attr.result = typeCreateErrorType3;
                        envLambdaEnv.info.scope.leave();
                        if (z4) {
                            Type type11 = attr.result;
                            try {
                                attr.attribTree(jCLambda2, attr.env, attr.recoveryInfo);
                                if (type3 == jCNoType2) {
                                    return;
                                } else {
                                    return;
                                }
                            } finally {
                                if (attr.result == Type.recoveryType) {
                                    attr.result = type11;
                                }
                            }
                        }
                        return;
                    }
                }
                try {
                    AttrContext attrContext3 = envLambdaEnv.info;
                    ResultInfo resultInfoLambdaBodyResult = attr.lambdaBodyResult(jCLambda2, type7, attr.resultInfo);
                    attrContext3.returnResult = resultInfoLambdaBodyResult;
                    if (jCLambda2.getBodyKind() == LambdaExpressionTree.BodyKind.EXPRESSION) {
                        attr.attribTree(jCLambda2.getBody(), envLambdaEnv, resultInfoLambdaBodyResult);
                    } else {
                        JCTree.JCBlock jCBlock = (JCTree.JCBlock) jCLambda2.body;
                        if (jCBlock == attr.breakTree && attr.resultInfo.checkContext.deferredAttrContext().mode == DeferredAttr.AttrMode.CHECK) {
                            attr.breakTreeFound(attr.copyEnv(envLambdaEnv));
                        }
                        attr.attribStats(jCBlock.stats, envLambdaEnv);
                    }
                    Kinds.KindSelector kindSelector = Kinds.KindSelector.VAL;
                    attr.result = attr.check(jCLambda2, type6, kindSelector, attr.resultInfo);
                    if (attr.resultInfo.checkContext.deferredAttrContext().mode != DeferredAttr.AttrMode.SPECULATIVE) {
                        z2 = false;
                    }
                    attr.preFlow(jCLambda2);
                    attr.flow.analyzeLambda(attr.env, jCLambda2, attr.make, z2);
                    jCLambda2.type = type6;
                    attr.checkLambdaCompatible(jCLambda2, type7, attr.resultInfo.checkContext);
                    if (!z2) {
                        if (attr.resultInfo.checkContext.inferenceContext().free(type7.mo74getThrownTypes()) && !attr.checkExConstraints(attr.flow.analyzeLambdaThrownTypes(attr.env, jCLambda2, attr.make), type7.mo74getThrownTypes(), attr.resultInfo.checkContext.inferenceContext())) {
                            attr.log.error(jCLambda2, CompilerProperties.Errors.IncompatibleThrownTypesInMref(type7.mo74getThrownTypes()));
                        }
                        attr.checkAccessibleTypes(jCLambda2, envLambdaEnv, attr.resultInfo.checkContext.inferenceContext(), type7, type6);
                    }
                    if (z) {
                        typeCheck = attr.types.createErrorType(attr.pt());
                        jCLambda2.type = typeCheck;
                    } else {
                        typeCheck = attr.check(jCLambda2, type6, kindSelector, attr.resultInfo);
                    }
                    attr.result = typeCheck;
                    envLambdaEnv.info.scope.leave();
                } catch (Symbol.CompletionFailure e3) {
                    e = e3;
                    attr.chk.completionError(jCLambda2.pos(), e);
                    envLambdaEnv.info.scope.leave();
                    if (z3) {
                        type2 = attr.result;
                        attr.attribTree(jCLambda2, attr.env, attr.recoveryInfo);
                    }
                } catch (Types.FunctionDescriptorLookupError e4) {
                    e = e4;
                    attr.resultInfo.checkContext.report(jCLambda2, e.getDiagnostic());
                    Type typeCreateErrorType4 = attr.types.createErrorType(attr.pt());
                    jCLambda2.type = typeCreateErrorType4;
                    attr.result = typeCreateErrorType4;
                    envLambdaEnv.info.scope.leave();
                    if (z3) {
                        type2 = attr.result;
                        attr.attribTree(jCLambda2, attr.env, attr.recoveryInfo);
                    }
                }
            } catch (Symbol.CompletionFailure e5) {
                e = e5;
                z3 = z4;
                attr.chk.completionError(jCLambda2.pos(), e);
                envLambdaEnv.info.scope.leave();
                if (z3) {
                    type2 = attr.result;
                    attr.attribTree(jCLambda2, attr.env, attr.recoveryInfo);
                }
            } catch (Types.FunctionDescriptorLookupError e6) {
                e = e6;
                z3 = z4;
                attr.resultInfo.checkContext.report(jCLambda2, e.getDiagnostic());
                Type typeCreateErrorType5 = attr.types.createErrorType(attr.pt());
                jCLambda2.type = typeCreateErrorType5;
                attr.result = typeCreateErrorType5;
                envLambdaEnv.info.scope.leave();
                if (z3) {
                    type2 = attr.result;
                    attr.attribTree(jCLambda2, attr.env, attr.recoveryInfo);
                }
            }
        } catch (Throwable th2) {
            envLambdaEnv.info.scope.leave();
            if (0 == 0) {
                throw th2;
            }
            Type type12 = r4.result;
            try {
                r4.attribTree(jCTreeAttribTree, r4.env, r4.recoveryInfo);
                if (type != jCNoType) {
                    throw th2;
                }
                throw th2;
            } finally {
                if (r4.result == Type.recoveryType) {
                    r4.result = type12;
                }
            }
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitLiteral(JCTree.JCLiteral jCLiteral) {
        this.result = check(jCLiteral, litType(jCLiteral.typetag).constType(jCLiteral.value), Kinds.KindSelector.VAL, this.resultInfo);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitMethodDef(final JCTree.JCMethodDecl jCMethodDecl) {
        boolean z;
        boolean z2;
        JCDiagnostic.Error errorInvalidCanonicalConstructorInRecord;
        Symbol.MethodSymbol methodSymbol = jCMethodDecl.sym;
        long j = 0;
        boolean z3 = (methodSymbol.flags() & Flags.DEFAULT) != 0;
        Lint lintAugment = this.env.info.lint.augment(methodSymbol);
        Lint lint = this.chk.setLint(lintAugment);
        AttrContext attrContext = this.env.info;
        boolean z4 = attrContext.ctorPrologue;
        Assert.check(!attrContext.ctorPrologue);
        Symbol.MethodSymbol method = this.chk.setMethod(methodSymbol);
        try {
            this.chk.checkDeprecatedAnnotation(jCMethodDecl.pos(), methodSymbol);
            Env<AttrContext> envMethodEnv = this.memberEnter.methodEnv(jCMethodDecl, this.env);
            envMethodEnv.info.lint = lintAugment;
            attribStats(jCMethodDecl.typarams, envMethodEnv);
            boolean zIsStatic = methodSymbol.isStatic();
            Check check = this.chk;
            if (zIsStatic) {
                check.checkHideClashes(jCMethodDecl.pos(), this.env.enclClass.type, methodSymbol);
            } else {
                check.checkOverrideClashes(jCMethodDecl.pos(), this.env.enclClass.type, methodSymbol);
            }
            this.chk.checkOverride(this.env, jCMethodDecl, methodSymbol);
            if (z3 && this.types.overridesObjectMethod(methodSymbol.enclClass(), methodSymbol)) {
                this.log.error(jCMethodDecl, CompilerProperties.Errors.DefaultOverridesObjectMember(methodSymbol.name, Kinds.kindName(methodSymbol.location()), methodSymbol.location()));
            }
            for (List list = jCMethodDecl.typarams; list.nonEmpty(); list = list.tail) {
                envMethodEnv.info.scope.enterIfAbsent(((JCTree.JCTypeParameter) list.head).type.tsym);
            }
            Symbol.ClassSymbol classSymbol = this.env.enclClass.sym;
            long j2 = 8192;
            if ((classSymbol.flags() & 8192) != 0 && (jCMethodDecl.params.nonEmpty() || jCMethodDecl.recvparam != null)) {
                this.log.error(jCMethodDecl.params.nonEmpty() ? jCMethodDecl.params.head.pos() : jCMethodDecl.recvparam.pos(), CompilerProperties.Errors.IntfAnnotationMembersCantHaveParams);
            }
            for (List list2 = jCMethodDecl.params; list2.nonEmpty(); list2 = list2.tail) {
                attribStat((JCTree) list2.head, envMethodEnv);
            }
            this.chk.checkVarargsMethodDecl(envMethodEnv, jCMethodDecl);
            this.chk.validate(jCMethodDecl.typarams, envMethodEnv);
            JCTree.JCExpression jCExpression = jCMethodDecl.restype;
            if (jCExpression != null && !jCExpression.type.hasTag(TypeTag.VOID)) {
                this.chk.validate(jCMethodDecl.restype, envMethodEnv);
            }
            this.chk.checkRequiresIdentity(jCMethodDecl, this.env.info.lint);
            if (jCMethodDecl.recvparam != null) {
                Env<AttrContext> envMethodEnv2 = this.memberEnter.methodEnv(jCMethodDecl, this.env);
                attribType(jCMethodDecl.recvparam, envMethodEnv2);
                this.chk.validate(jCMethodDecl.recvparam, envMethodEnv2);
            }
            boolean zIsConstructor = TreeInfo.isConstructor(jCMethodDecl);
            if (this.env.enclClass.sym.isRecord() && jCMethodDecl.sym.owner.kind == Kinds.Kind.TYP) {
                Optional<? extends Symbol.RecordComponent> optionalFindFirst = this.env.enclClass.sym.getRecordComponents().stream().filter(new Predicate() { // from class: ki0
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return Attr.g(jCMethodDecl, (Symbol.RecordComponent) obj);
                    }
                }).findFirst();
                if (optionalFindFirst.isPresent()) {
                    if (!jCMethodDecl.sym.isPublic()) {
                        this.log.error(jCMethodDecl, CompilerProperties.Errors.InvalidAccessorMethodInRecord(this.env.enclClass.sym, CompilerProperties.Fragments.MethodMustBePublic));
                    }
                    if (!this.types.isSameType(jCMethodDecl.sym.type.mo73getReturnType(), optionalFindFirst.get().type)) {
                        this.log.error(jCMethodDecl, CompilerProperties.Errors.InvalidAccessorMethodInRecord(this.env.enclClass.sym, CompilerProperties.Fragments.AccessorReturnTypeDoesntMatch(jCMethodDecl.sym, optionalFindFirst.get())));
                    }
                    if (jCMethodDecl.sym.type.asMethodType().thrown != null && !jCMethodDecl.sym.type.asMethodType().thrown.isEmpty()) {
                        this.log.error(jCMethodDecl, CompilerProperties.Errors.InvalidAccessorMethodInRecord(this.env.enclClass.sym, CompilerProperties.Fragments.AccessorMethodCantThrowException));
                    }
                    if (!jCMethodDecl.typarams.isEmpty()) {
                        this.log.error(jCMethodDecl, CompilerProperties.Errors.InvalidAccessorMethodInRecord(this.env.enclClass.sym, CompilerProperties.Fragments.AccessorMethodMustNotBeGeneric));
                    }
                    if (jCMethodDecl.sym.isStatic()) {
                        this.log.error(jCMethodDecl, CompilerProperties.Errors.InvalidAccessorMethodInRecord(this.env.enclClass.sym, CompilerProperties.Fragments.AccessorMethodMustNotBeStatic));
                    }
                } else {
                    j = 0;
                }
                if (zIsConstructor) {
                    long j3 = jCMethodDecl.sym.flags_field;
                    if ((j3 & Flags.RECORD) != j) {
                        if ((j3 & Flags.GENERATEDCONSTR) == j) {
                            if (Check.protection(methodSymbol.flags()) > Check.protection(this.env.enclClass.sym.flags())) {
                                Log log = this.log;
                                if ((this.env.enclClass.sym.flags() & 7) == j) {
                                    errorInvalidCanonicalConstructorInRecord = CompilerProperties.Errors.InvalidCanonicalConstructorInRecord(CompilerProperties.Fragments.Canonical, this.env.enclClass.sym.name, CompilerProperties.Fragments.CanonicalMustNotHaveStrongerAccess(PsiKeyword.PACKAGE));
                                } else {
                                    JCDiagnostic.Fragment fragment = CompilerProperties.Fragments.Canonical;
                                    Symbol.ClassSymbol classSymbol2 = this.env.enclClass.sym;
                                    errorInvalidCanonicalConstructorInRecord = CompilerProperties.Errors.InvalidCanonicalConstructorInRecord(fragment, classSymbol2.name, CompilerProperties.Fragments.CanonicalMustNotHaveStrongerAccess(Flags.asFlagSet(classSymbol2.flags() & 7)));
                                }
                                log.error(jCMethodDecl, errorInvalidCanonicalConstructorInRecord);
                            }
                            if (TreeInfo.hasAnyConstructorCall(jCMethodDecl)) {
                                this.log.error(jCMethodDecl, CompilerProperties.Errors.InvalidCanonicalConstructorInRecord(CompilerProperties.Fragments.Canonical, this.env.enclClass.sym.name, CompilerProperties.Fragments.CanonicalMustNotContainExplicitConstructorInvocation));
                            }
                        }
                        if (!jCMethodDecl.typarams.isEmpty()) {
                            this.log.error(jCMethodDecl, CompilerProperties.Errors.InvalidCanonicalConstructorInRecord(CompilerProperties.Fragments.Canonical, this.env.enclClass.sym.name, CompilerProperties.Fragments.CanonicalMustNotDeclareTypeVariables));
                        }
                        List recordComponents = this.env.enclClass.sym.getRecordComponents();
                        List map = TreeInfo.recordFields(this.env.enclClass).map(new Function() { // from class: li0
                            @Override // java.util.function.Function
                            public final Object apply(Object obj) {
                                return ((JCTree.JCVariableDecl) obj).sym.type;
                            }
                        });
                        for (JCTree.JCVariableDecl jCVariableDecl : jCMethodDecl.params) {
                            boolean z5 = z3;
                            boolean z6 = zIsConstructor;
                            boolean z7 = (jCVariableDecl.sym.flags_field & Flags.VARARGS) != j;
                            long j4 = j2;
                            if (!this.types.isSameType(jCVariableDecl.type, (Type) map.head) || ((Symbol.RecordComponent) recordComponents.head).isVarargs() != z7) {
                                this.log.error(jCVariableDecl, CompilerProperties.Errors.InvalidCanonicalConstructorInRecord(CompilerProperties.Fragments.Canonical, this.env.enclClass.sym.name, CompilerProperties.Fragments.TypeMustBeIdenticalToCorrespondingRecordComponentType));
                            }
                            recordComponents = recordComponents.tail;
                            map = map.tail;
                            z3 = z5;
                            zIsConstructor = z6;
                            j2 = j4;
                        }
                    } else if (!TreeInfo.hasConstructorCall(jCMethodDecl, this.names._this)) {
                        this.log.error(jCMethodDecl, CompilerProperties.Errors.NonCanonicalConstructorInvokeAnotherConstructor(this.env.enclClass.sym));
                    }
                }
                z = z3;
                z2 = zIsConstructor;
            } else {
                z = z3;
                z2 = zIsConstructor;
                j = 0;
            }
            long j5 = j2;
            if ((classSymbol.flags() & j5) != j) {
                if (jCMethodDecl.thrown.nonEmpty()) {
                    this.log.error(jCMethodDecl.thrown.head.pos(), CompilerProperties.Errors.ThrowsNotAllowedInIntfAnnotation);
                }
                if (jCMethodDecl.typarams.nonEmpty()) {
                    this.log.error(jCMethodDecl.typarams.head.pos(), CompilerProperties.Errors.IntfAnnotationMembersCantHaveTypeParams);
                }
                this.chk.validateAnnotationType(jCMethodDecl.restype);
                this.chk.validateAnnotationMethod(jCMethodDecl.pos(), methodSymbol);
            }
            for (List list3 = jCMethodDecl.thrown; list3.nonEmpty(); list3 = list3.tail) {
                this.chk.checkType(((JCTree.JCExpression) list3.head).pos(), ((JCTree.JCExpression) list3.head).type, this.syms.throwableType);
            }
            if (jCMethodDecl.body == null) {
                if (jCMethodDecl.defaultValue != null && (classSymbol.flags() & j5) == j) {
                    this.log.error(jCMethodDecl.pos(), CompilerProperties.Errors.DefaultAllowedInIntfAnnotationMember);
                }
                if (z || (jCMethodDecl.sym.flags() & 1280) == j) {
                    this.log.error(jCMethodDecl.pos(), CompilerProperties.Errors.MissingMethBodyOrDeclAbstract(jCMethodDecl.sym, classSymbol));
                }
            } else {
                if ((jCMethodDecl.sym.flags() & 8796093023234L) == 1024) {
                    long jFlags = classSymbol.flags() & 512;
                    Log log2 = this.log;
                    if (jFlags != j) {
                        log2.error(jCMethodDecl.body.pos(), CompilerProperties.Errors.IntfMethCantHaveBody);
                    } else {
                        log2.error(jCMethodDecl.pos(), CompilerProperties.Errors.AbstractMethCantHaveBody);
                    }
                } else if ((jCMethodDecl.mods.flags & 256) != j) {
                    this.log.error(jCMethodDecl.pos(), CompilerProperties.Errors.NativeMethCantHaveBody);
                }
                if (z2 && classSymbol.type != this.syms.objectType) {
                    if (!TreeInfo.hasAnyConstructorCall(jCMethodDecl)) {
                        JCTree.JCExpressionStatement jCExpressionStatementExec = this.make.at(jCMethodDecl.body.pos).Exec(this.make.Apply(List.nil(), this.make.Ident(this.names._super), this.make.Idents(List.nil())));
                        JCTree.JCBlock jCBlock = jCMethodDecl.body;
                        jCBlock.stats = jCBlock.stats.prepend(jCExpressionStatementExec);
                    } else if ((this.env.enclClass.sym.flags() & 16384) != j && (jCMethodDecl.mods.flags & Flags.GENERATEDCONSTR) == j && TreeInfo.hasConstructorCall(jCMethodDecl, this.names._super)) {
                        this.log.error(jCMethodDecl.body.stats.head.pos(), CompilerProperties.Errors.CallToSuperNotAllowedInEnumCtor(this.env.enclClass.sym));
                    }
                    if (this.env.enclClass.sym.isRecord() && (jCMethodDecl.sym.flags_field & Flags.RECORD) != j) {
                        if (!jCMethodDecl.sym.params.map(new Function() { // from class: ni0
                            @Override // java.util.function.Function
                            public final Object apply(Object obj) {
                                return ((Symbol.VarSymbol) obj).name;
                            }
                        }).equals(TreeInfo.recordFields(this.env.enclClass).map(new Function() { // from class: mi0
                            @Override // java.util.function.Function
                            public final Object apply(Object obj) {
                                return ((JCTree.JCVariableDecl) obj).sym.name;
                            }
                        }))) {
                            this.log.error(jCMethodDecl, CompilerProperties.Errors.InvalidCanonicalConstructorInRecord(CompilerProperties.Fragments.Canonical, this.env.enclClass.sym.name, CompilerProperties.Fragments.CanonicalWithNameMismatch));
                        }
                        if (jCMethodDecl.sym.type.asMethodType().thrown != null && !jCMethodDecl.sym.type.asMethodType().thrown.isEmpty()) {
                            this.log.error(jCMethodDecl, CompilerProperties.Errors.InvalidCanonicalConstructorInRecord(TreeInfo.isCompactConstructor(jCMethodDecl) ? CompilerProperties.Fragments.Compact : CompilerProperties.Fragments.Canonical, this.env.enclClass.sym.name, CompilerProperties.Fragments.ThrowsClauseNotAllowedForCanonicalConstructor(TreeInfo.isCompactConstructor(jCMethodDecl) ? CompilerProperties.Fragments.Compact : CompilerProperties.Fragments.Canonical)));
                        }
                    }
                }
                this.annotate.queueScanTreeAndTypeAnnotate(jCMethodDecl.body, envMethodEnv, methodSymbol);
                this.annotate.flush();
                envMethodEnv.info.ctorPrologue = z2 && classSymbol.type != this.syms.objectType;
                attribStat(jCMethodDecl.body, envMethodEnv);
            }
            envMethodEnv.info.scope.leave();
            Type type = methodSymbol.type;
            jCMethodDecl.type = type;
            this.result = type;
        } finally {
            this.chk.setLint(lint);
            this.chk.setMethod(method);
            this.env.info.ctorPrologue = z4;
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitModifiers(JCTree.JCModifiers jCModifiers) {
        Assert.check(this.resultInfo.pkind == Kinds.KindSelector.ERR);
        attribAnnotationTypes(jCModifiers.annotations, this.env);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitModuleDef(JCTree.JCModuleDecl jCModuleDecl) {
        jCModuleDecl.sym.completeUsesProvides();
        Symbol.ModuleSymbol moduleSymbol = jCModuleDecl.sym;
        AttrContext attrContext = this.env.outer.info;
        AttrContext attrContext2 = attrContext;
        Lint lintAugment = attrContext.lint.augment(moduleSymbol);
        attrContext2.lint = lintAugment;
        Lint lint = this.chk.setLint(lintAugment);
        try {
            this.chk.checkModuleName(jCModuleDecl);
            this.chk.checkDeprecatedAnnotation(jCModuleDecl, moduleSymbol);
        } finally {
            this.chk.setLint(lint);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitNewArray(JCTree.JCNewArray jCNewArray) {
        Type typeCreateErrorType;
        Type arrayType;
        Type typeAttribType;
        Type typeCreateErrorType2 = this.types.createErrorType(jCNewArray.type);
        Env<AttrContext> envDup = this.env.dup(jCNewArray);
        JCTree.JCExpression jCExpression = jCNewArray.elemtype;
        if (jCExpression != null) {
            typeAttribType = attribType(jCExpression, envDup);
            this.chk.validate(jCNewArray.elemtype, envDup);
            List list = jCNewArray.dims;
            arrayType = typeAttribType;
            while (list.nonEmpty()) {
                attribExpr((JCTree) list.head, envDup, this.syms.intType);
                Type.ArrayType arrayType2 = new Type.ArrayType(arrayType, this.syms.arrayClass);
                list = list.tail;
                arrayType = arrayType2;
            }
        } else {
            if (pt().hasTag(TypeTag.ARRAY)) {
                typeCreateErrorType = this.types.elemtype(pt());
            } else {
                if (!pt().hasTag(TypeTag.ERROR)) {
                    AttrContext attrContext = this.env.info;
                    if (attrContext.enclVar == null || !attrContext.enclVar.type.isErroneous()) {
                        this.log.error(jCNewArray.pos(), CompilerProperties.Errors.IllegalInitializerForType(pt()));
                    }
                }
                typeCreateErrorType = this.types.createErrorType(pt());
            }
            Type type = typeCreateErrorType;
            arrayType = typeCreateErrorType2;
            typeAttribType = type;
        }
        List<JCTree.JCExpression> list2 = jCNewArray.elems;
        if (list2 != null) {
            attribExprs(list2, envDup, typeAttribType);
            arrayType = new Type.ArrayType(typeAttribType, this.syms.arrayClass);
        }
        if (!this.types.isReifiable(typeAttribType)) {
            this.log.error(jCNewArray.pos(), CompilerProperties.Errors.GenericArrayCreation);
        }
        this.result = check(jCNewArray, arrayType, Kinds.KindSelector.VAL, this.resultInfo);
    }

    /* JADX WARN: Code duplicated, block: B:101:0x0316  */
    /* JADX WARN: Code duplicated, block: B:103:0x031e  */
    /* JADX WARN: Code duplicated, block: B:105:0x032b  */
    /* JADX WARN: Code duplicated, block: B:106:0x032d  */
    /* JADX WARN: Code duplicated, block: B:109:0x034c  */
    /* JADX WARN: Code duplicated, block: B:111:0x036d  */
    /* JADX WARN: Code duplicated, block: B:117:0x037d  */
    /* JADX WARN: Code duplicated, block: B:119:0x0383  */
    /* JADX WARN: Code duplicated, block: B:122:0x0394  */
    /* JADX WARN: Code duplicated, block: B:124:0x039d  */
    /* JADX WARN: Code duplicated, block: B:126:0x03a2  */
    /* JADX WARN: Code duplicated, block: B:60:0x01b7  */
    /* JADX WARN: Code duplicated, block: B:88:0x0252  */
    /* JADX WARN: Code duplicated, block: B:94:0x027c  */
    /* JADX WARN: Code duplicated, block: B:97:0x02ed  */
    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitNewClass(JCTree.JCNewClass jCNewClass) {
        JCTree.JCExpression jCExpression;
        JCTree.JCAnnotatedType jCAnnotatedType;
        JCTree.JCExpression jCExpression2;
        JCTree.JCAnnotatedType jCAnnotatedType2;
        JCTree.JCExpression jCExpression3;
        JCTree.JCExpression jCExpressionTypeApply;
        List<Type> list;
        List<Type> list2;
        boolean z;
        JCTree.JCClassDecl jCClassDecl;
        List<Type> list3;
        Kinds.KindSelector kindSelector;
        Env<AttrContext> envDup;
        boolean z2;
        Kinds.KindSelector kindSelector2;
        Symbol symbolResolveConstructor;
        Env<AttrContext> env;
        List<Type> list4;
        final JCTree.JCNewClass jCNewClass2;
        boolean z3;
        Type type;
        Env<AttrContext> env2;
        Symbol symbol;
        boolean z4;
        Type typeCheckId;
        Attr attr = this;
        Type typeCreateErrorType = attr.types.createErrorType(jCNewClass.type);
        Env<AttrContext> env3 = attr.env;
        Env<AttrContext> envDup2 = env3.dup(jCNewClass, env3.info.dup());
        JCTree.JCClassDecl jCClassDecl2 = jCNewClass.def;
        JCTree.JCExpression jCExpression4 = jCNewClass.clazz;
        JCTree.Tag tag = JCTree.Tag.TYPEAPPLY;
        if (jCExpression4.hasTag(tag)) {
            jCExpression = ((JCTree.JCTypeApply) jCExpression4).clazz;
            if (jCExpression.hasTag(JCTree.Tag.ANNOTATED_TYPE)) {
                jCAnnotatedType = (JCTree.JCAnnotatedType) jCExpression;
                jCExpression2 = jCAnnotatedType.underlyingType;
                JCTree.JCExpression jCExpression5 = jCExpression2;
                jCAnnotatedType2 = jCAnnotatedType;
                jCExpression = jCExpression5;
            } else {
                jCAnnotatedType2 = null;
            }
        } else if (jCExpression4.hasTag(JCTree.Tag.ANNOTATED_TYPE)) {
            jCAnnotatedType = (JCTree.JCAnnotatedType) jCExpression4;
            jCExpression2 = jCAnnotatedType.underlyingType;
            JCTree.JCExpression jCExpression6 = jCExpression2;
            jCAnnotatedType2 = jCAnnotatedType;
            jCExpression = jCExpression6;
        } else {
            jCExpression = jCExpression4;
            jCAnnotatedType2 = null;
        }
        JCTree.JCExpression jCExpression7 = jCNewClass.encl;
        if (jCExpression7 != null) {
            JCTree.JCExpression jCExpressionSelect = attr.make.at(jCExpression4.pos).Select(attr.make.Type(attr.chk.checkRefType(jCExpression7.pos(), attr.attribExpr(jCNewClass.encl, attr.env))), ((JCTree.JCIdent) jCExpression).name);
            EndPosTable endPosTable = attr.env.toplevel.endPositions;
            endPosTable.storeEnd(jCExpressionSelect, jCExpression.getEndPosition(endPosTable));
            if (jCExpression4.hasTag(JCTree.Tag.ANNOTATED_TYPE)) {
                JCTree.JCAnnotatedType jCAnnotatedType3 = (JCTree.JCAnnotatedType) jCExpression4;
                List<JCTree.JCAnnotation> list5 = jCAnnotatedType3.annotations;
                if (jCAnnotatedType3.underlyingType.hasTag(tag)) {
                    jCExpressionSelect = attr.make.at(jCNewClass.pos).TypeApply(jCExpressionSelect, ((JCTree.JCTypeApply) jCExpression4).arguments);
                }
                jCExpressionTypeApply = attr.make.at(jCNewClass.pos).AnnotatedType(list5, jCExpressionSelect);
            } else {
                jCExpressionTypeApply = jCExpression4.hasTag(tag) ? attr.make.at(jCNewClass.pos).TypeApply(jCExpressionSelect, ((JCTree.JCTypeApply) jCExpression4).arguments) : jCExpressionSelect;
            }
            jCExpression3 = jCExpressionTypeApply;
        } else {
            jCExpression3 = jCExpression4;
            jCExpressionTypeApply = jCExpression;
        }
        boolean zIsEnumInit = TreeInfo.isEnumInit(attr.env.tree);
        Env<AttrContext> env4 = attr.env;
        Type typeCheckDiamond = attr.chk.checkDiamond(jCNewClass, zIsEnumInit ? attr.attribIdentAsEnumType(env4, (JCTree.JCIdent) jCExpression3) : attr.attribType(jCExpression3, env4));
        attr.chk.validate(jCExpression3, envDup2);
        if (jCNewClass.encl != null) {
            jCNewClass.clazz.type = typeCheckDiamond;
            TreeInfo.setSymbol(jCExpression, TreeInfo.symbol(jCExpressionTypeApply));
            Type type2 = ((JCTree.JCIdent) jCExpression).sym.type;
            jCExpression.type = type2;
            if (jCAnnotatedType2 != null) {
                jCAnnotatedType2.type = type2;
            }
            if (!typeCheckDiamond.isErroneous()) {
                if (jCClassDecl2 != null && typeCheckDiamond.tsym.isInterface()) {
                    attr.log.error(jCNewClass.encl.pos(), CompilerProperties.Errors.AnonClassImplIntfNoQualForNew);
                } else if (typeCheckDiamond.tsym.isStatic()) {
                    attr.log.error(jCNewClass.encl.pos(), CompilerProperties.Errors.QualifiedNewOfStaticClass(typeCheckDiamond.tsym));
                }
            }
        } else {
            attr.checkNewInnerClass(jCNewClass.pos(), attr.env, typeCheckDiamond, false);
        }
        ListBuffer<Type> listBuffer = new ListBuffer<>();
        Kinds.KindSelector kindSelector3 = Kinds.KindSelector.VAL;
        Kinds.KindSelector kindSelectorAttribArgs = attr.attribArgs(kindSelector3, jCNewClass.args, envDup2, listBuffer);
        List<Type> list6 = listBuffer.toList();
        List<Type> listAttribTypes = attr.attribTypes(jCNewClass.typeargs, envDup2);
        if (typeCheckDiamond.hasTag(TypeTag.CLASS) || typeCheckDiamond.hasTag(TypeTag.ERROR)) {
            if ((typeCheckDiamond.tsym.flags_field & 16384) != 0) {
                if (attr.env.tree.hasTag(JCTree.Tag.VARDEF)) {
                    JCTree jCTree = attr.env.tree;
                    if ((((JCTree.JCVariableDecl) jCTree).mods.flags & 16384) == 0 || ((JCTree.JCVariableDecl) jCTree).init != jCNewClass) {
                        attr.log.error(jCNewClass.pos(), CompilerProperties.Errors.EnumCantBeInstantiated);
                    }
                } else {
                    attr.log.error(jCNewClass.pos(), CompilerProperties.Errors.EnumCantBeInstantiated);
                }
            }
            boolean z5 = TreeInfo.isDiamond(jCNewClass) && attr.resultInfo.checkContext.deferredAttrContext().mode == DeferredAttr.AttrMode.SPECULATIVE;
            if (jCClassDecl2 != null || jCNewClass.classDeclRemoved() || z5 || (typeCheckDiamond.tsym.flags() & 1536) == 0) {
                if (jCClassDecl2 == null || !typeCheckDiamond.tsym.isInterface()) {
                    list = list6;
                    list2 = listAttribTypes;
                    z = false;
                } else {
                    if (!list6.isEmpty()) {
                        attr.log.error(jCNewClass.args.head.pos(), CompilerProperties.Errors.AnonClassImplIntfNoArgs);
                    }
                    if (!listAttribTypes.isEmpty()) {
                        attr.log.error(jCNewClass.typeargs.head.pos(), CompilerProperties.Errors.AnonClassImplIntfNoTypeargs);
                    }
                    list6 = List.nil();
                    listAttribTypes = List.nil();
                }
                if (TreeInfo.isDiamond(jCNewClass)) {
                    Type.ClassType classType = new Type.ClassType(typeCheckDiamond.getEnclosingType(), typeCheckDiamond.tsym.type.getTypeArguments(), typeCheckDiamond.tsym, typeCheckDiamond.getMetadata());
                    Env<AttrContext> envDup3 = envDup2.dup(jCNewClass);
                    AttrContext attrContext = envDup3.info;
                    if (jCClassDecl2 == null || jCNewClass.classDeclRemoved()) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    attrContext.selectSuper = z4;
                    envDup3.info.pendingResolutionPhase = null;
                    List<Type> list7 = list;
                    List<Type> list8 = list2;
                    Symbol symbolResolveDiamond = attr.rs.resolveDiamond(jCNewClass.pos(), envDup3, classType, list7, list8);
                    list3 = list7;
                    jCNewClass.constructor = symbolResolveDiamond.baseSymbol();
                    list2 = list8;
                    ResultInfo resultInfo = new ResultInfo(kindSelectorAttribArgs, attr.newMethodTemplate(attr.resultInfo.pt, list3, list8), attr.diamondContext(jCNewClass, typeCheckDiamond.tsym, attr.resultInfo.checkContext), CheckMode.NO_TREE_UPDATE);
                    attr = this;
                    jCClassDecl = jCClassDecl2;
                    kindSelector = kindSelectorAttribArgs;
                    jCNewClass.constructorType = attr.types.createErrorType(typeCheckDiamond);
                    typeCheckId = attr.checkId(jCNewClass, classType, symbolResolveDiamond, envDup3, resultInfo);
                    jCNewClass.clazz.type = attr.types.createErrorType(typeCheckDiamond);
                    if (!typeCheckId.isErroneous()) {
                        JCTree.JCExpression jCExpression8 = jCNewClass.clazz;
                        Type typeMo73getReturnType = typeCheckId.mo73getReturnType();
                        jCExpression3.type = typeMo73getReturnType;
                        jCExpression8.type = typeMo73getReturnType;
                        jCNewClass.constructorType = attr.types.createMethodTypeWithReturn(typeCheckId, attr.syms.voidType);
                    }
                    Check check = attr.chk;
                    JCTree.JCExpression jCExpression9 = jCNewClass.clazz;
                    typeCheckDiamond = check.checkClassType(jCExpression9, jCExpression9.type, true);
                } else {
                    jCClassDecl = jCClassDecl2;
                    list3 = list;
                    kindSelector = kindSelectorAttribArgs;
                    if (!z) {
                        envDup = envDup2.dup(jCNewClass);
                        AttrContext attrContext2 = envDup.info;
                        AttrContext attrContext3 = attrContext2;
                        if (jCClassDecl != null) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        attrContext3.selectSuper = z2;
                        attrContext2.pendingResolutionPhase = null;
                        kindSelector2 = kindSelector3;
                        symbolResolveConstructor = attr.rs.resolveConstructor(jCNewClass.pos(), envDup, typeCheckDiamond, list3, list2);
                        env = envDup2;
                        list3 = list3;
                        list4 = list2;
                        jCNewClass.constructor = symbolResolveConstructor;
                        if (jCClassDecl == null) {
                            jCNewClass2 = jCNewClass;
                            jCNewClass2.constructorType = attr.checkId(jCNewClass2, typeCheckDiamond, symbolResolveConstructor, envDup, new ResultInfo(attr, kindSelector, attr.newMethodTemplate(attr.syms.voidType, list3, list4), CheckMode.NO_TREE_UPDATE));
                            if (envDup.info.lastResolveVarargs()) {
                                if (jCNewClass2.constructorType.isErroneous() && jCNewClass2.varargsElement == null) {
                                    z3 = false;
                                } else {
                                    z3 = true;
                                }
                                Assert.check(z3);
                            }
                        } else {
                            jCNewClass2 = jCNewClass;
                        }
                    }
                    type = typeCheckDiamond;
                    attr.chk.checkRequiresIdentity(jCNewClass2, attr.env.info.lint);
                    if (jCClassDecl != null) {
                        attr.visitAnonymousClassDefinition(jCNewClass2, jCExpression3, type, jCClassDecl, env, list3, list4, kindSelector);
                        return;
                    }
                    env2 = env;
                    symbol = jCNewClass2.constructor;
                    if (symbol != null && symbol.kind == Kinds.Kind.MTH) {
                        typeCreateErrorType = type;
                    }
                }
                jCNewClass2 = jCNewClass;
                kindSelector2 = kindSelector3;
                env = envDup2;
                list4 = list2;
                type = typeCheckDiamond;
                attr.chk.checkRequiresIdentity(jCNewClass2, attr.env.info.lint);
                if (jCClassDecl != null) {
                    attr.visitAnonymousClassDefinition(jCNewClass2, jCExpression3, type, jCClassDecl, env, list3, list4, kindSelector);
                    return;
                }
                env2 = env;
                symbol = jCNewClass2.constructor;
                if (symbol != null) {
                    typeCreateErrorType = type;
                }
            } else {
                attr.log.error(jCNewClass.pos(), CompilerProperties.Errors.AbstractCantBeInstantiated(typeCheckDiamond.tsym));
            }
            list = list6;
            list2 = listAttribTypes;
            z = true;
            if (TreeInfo.isDiamond(jCNewClass)) {
                Type.ClassType classType2 = new Type.ClassType(typeCheckDiamond.getEnclosingType(), typeCheckDiamond.tsym.type.getTypeArguments(), typeCheckDiamond.tsym, typeCheckDiamond.getMetadata());
                Env<AttrContext> envDup4 = envDup2.dup(jCNewClass);
                AttrContext attrContext4 = envDup4.info;
                if (jCClassDecl2 == null) {
                    z4 = true;
                } else {
                    z4 = true;
                }
                attrContext4.selectSuper = z4;
                envDup4.info.pendingResolutionPhase = null;
                List<Type> list9 = list;
                List<Type> list10 = list2;
                Symbol symbolResolveDiamond2 = attr.rs.resolveDiamond(jCNewClass.pos(), envDup4, classType2, list9, list10);
                list3 = list9;
                jCNewClass.constructor = symbolResolveDiamond2.baseSymbol();
                list2 = list10;
                ResultInfo resultInfo2 = new ResultInfo(kindSelectorAttribArgs, attr.newMethodTemplate(attr.resultInfo.pt, list3, list10), attr.diamondContext(jCNewClass, typeCheckDiamond.tsym, attr.resultInfo.checkContext), CheckMode.NO_TREE_UPDATE);
                attr = this;
                jCClassDecl = jCClassDecl2;
                kindSelector = kindSelectorAttribArgs;
                jCNewClass.constructorType = attr.types.createErrorType(typeCheckDiamond);
                typeCheckId = attr.checkId(jCNewClass, classType2, symbolResolveDiamond2, envDup4, resultInfo2);
                jCNewClass.clazz.type = attr.types.createErrorType(typeCheckDiamond);
                if (!typeCheckId.isErroneous()) {
                    JCTree.JCExpression jCExpression10 = jCNewClass.clazz;
                    Type typeMo73getReturnType2 = typeCheckId.mo73getReturnType();
                    jCExpression3.type = typeMo73getReturnType2;
                    jCExpression10.type = typeMo73getReturnType2;
                    jCNewClass.constructorType = attr.types.createMethodTypeWithReturn(typeCheckId, attr.syms.voidType);
                }
                Check check2 = attr.chk;
                JCTree.JCExpression jCExpression11 = jCNewClass.clazz;
                typeCheckDiamond = check2.checkClassType(jCExpression11, jCExpression11.type, true);
            } else {
                jCClassDecl = jCClassDecl2;
                list3 = list;
                kindSelector = kindSelectorAttribArgs;
                if (!z) {
                    envDup = envDup2.dup(jCNewClass);
                    AttrContext attrContext5 = envDup.info;
                    AttrContext attrContext6 = attrContext5;
                    if (jCClassDecl != null) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    attrContext6.selectSuper = z2;
                    attrContext5.pendingResolutionPhase = null;
                    kindSelector2 = kindSelector3;
                    symbolResolveConstructor = attr.rs.resolveConstructor(jCNewClass.pos(), envDup, typeCheckDiamond, list3, list2);
                    env = envDup2;
                    list3 = list3;
                    list4 = list2;
                    jCNewClass.constructor = symbolResolveConstructor;
                    if (jCClassDecl == null) {
                        jCNewClass2 = jCNewClass;
                        jCNewClass2.constructorType = attr.checkId(jCNewClass2, typeCheckDiamond, symbolResolveConstructor, envDup, new ResultInfo(attr, kindSelector, attr.newMethodTemplate(attr.syms.voidType, list3, list4), CheckMode.NO_TREE_UPDATE));
                        if (envDup.info.lastResolveVarargs()) {
                            if (jCNewClass2.constructorType.isErroneous()) {
                                z3 = true;
                            } else {
                                z3 = true;
                            }
                            Assert.check(z3);
                        }
                    } else {
                        jCNewClass2 = jCNewClass;
                    }
                }
                type = typeCheckDiamond;
                attr.chk.checkRequiresIdentity(jCNewClass2, attr.env.info.lint);
                if (jCClassDecl != null) {
                    attr.visitAnonymousClassDefinition(jCNewClass2, jCExpression3, type, jCClassDecl, env, list3, list4, kindSelector);
                    return;
                }
                env2 = env;
                symbol = jCNewClass2.constructor;
                if (symbol != null) {
                    typeCreateErrorType = type;
                }
            }
            jCNewClass2 = jCNewClass;
            kindSelector2 = kindSelector3;
            env = envDup2;
            list4 = list2;
            type = typeCheckDiamond;
            attr.chk.checkRequiresIdentity(jCNewClass2, attr.env.info.lint);
            if (jCClassDecl != null) {
                attr.visitAnonymousClassDefinition(jCNewClass2, jCExpression3, type, jCClassDecl, env, list3, list4, kindSelector);
                return;
            }
            env2 = env;
            symbol = jCNewClass2.constructor;
            if (symbol != null) {
                typeCreateErrorType = type;
            }
        } else {
            jCNewClass2 = jCNewClass;
            env2 = envDup2;
            kindSelector2 = kindSelector3;
        }
        attr.result = attr.check(jCNewClass2, typeCreateErrorType, kindSelector2, attr.resultInfo);
        InferenceContext inferenceContext = attr.resultInfo.checkContext.inferenceContext();
        Type type3 = jCNewClass2.constructorType;
        if (type3 != null && inferenceContext.free(type3)) {
            inferenceContext.addFreeTypeListener(List.of(jCNewClass2.constructorType), new Infer.FreeTypeListener() { // from class: com.sun.tools.javac.comp.k
                @Override // com.sun.tools.javac.comp.Infer.FreeTypeListener
                public final void typesInferred(InferenceContext inferenceContext2) {
                    JCTree.JCNewClass jCNewClass3 = jCNewClass2;
                    jCNewClass3.constructorType = inferenceContext2.asInstType(jCNewClass3.constructorType);
                }
            });
        }
        attr.chk.validate(jCNewClass2.typeargs, env2);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitParens(JCTree.JCParens jCParens) {
        this.result = check(jCParens, attribTree(jCParens.expr, this.env, this.resultInfo), pkind(), this.resultInfo);
        Symbol symbol = TreeInfo.symbol(jCParens);
        if (symbol == null || !symbol.kind.matches(Kinds.KindSelector.TYP_PCK) || symbol.kind == Kinds.Kind.ERR) {
            return;
        }
        this.log.error(jCParens.pos(), CompilerProperties.Errors.IllegalParenthesizedExpression);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitRecordPattern(final JCTree.JCRecordPattern jCRecordPattern) {
        final Type typeCapture;
        List list;
        JCTree.JCExpression jCExpression = jCRecordPattern.deconstructor;
        if (jCExpression == null) {
            this.log.error(jCRecordPattern.pos(), CompilerProperties.Errors.DeconstructionPatternVarNotAllowed);
            Symbol.ClassSymbol classSymbol = this.syms.errSymbol;
            jCRecordPattern.record = classSymbol;
            typeCapture = this.types.createErrorType(classSymbol.type);
            jCRecordPattern.type = typeCapture;
        } else {
            Type typeAttribType = attribType(jCExpression, this.env);
            if (typeAttribType.isRaw() && typeAttribType.tsym.getTypeParameters().nonEmpty()) {
                Type typeInstantiatePatternType = this.infer.instantiatePatternType(this.resultInfo.pt, typeAttribType.tsym);
                if (typeInstantiatePatternType == null) {
                    this.log.error(jCRecordPattern.pos(), CompilerProperties.Errors.PatternTypeCannotInfer);
                } else {
                    typeAttribType = typeInstantiatePatternType;
                }
            }
            jCRecordPattern.deconstructor.type = typeAttribType;
            jCRecordPattern.type = typeAttribType;
            typeCapture = this.types.capture(typeAttribType);
        }
        Symbol.TypeSymbol typeSymbol = typeCapture.tsym;
        if (typeSymbol.kind == Kinds.Kind.TYP && ((Symbol.ClassSymbol) typeSymbol).isRecord()) {
            Symbol.ClassSymbol classSymbol2 = (Symbol.ClassSymbol) typeCapture.tsym;
            list = (List) classSymbol2.getRecordComponents().stream().map(new Function() { // from class: pi0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return this.b.types.memberType(typeCapture, (Symbol.RecordComponent) obj);
                }
            }).map(new Function() { // from class: qi0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return Attr.p(this.b, (Type) obj);
                }
            }).collect(List.collector());
            jCRecordPattern.record = classSymbol2;
        } else {
            this.log.error(jCRecordPattern.pos(), CompilerProperties.Errors.DeconstructionPatternOnlyRecords(typeCapture.tsym));
            list = (List) Stream.generate(new Supplier() { // from class: ri0
                @Override // java.util.function.Supplier
                public final Object get() {
                    return this.b.types.createErrorType(jCRecordPattern.type);
                }
            }).limit(jCRecordPattern.nested.size()).collect(List.collector());
            jCRecordPattern.record = this.syms.errSymbol;
        }
        ListBuffer listBuffer = new ListBuffer();
        List list2 = jCRecordPattern.nested;
        Env<AttrContext> env = this.env;
        AttrContext attrContext = env.info;
        Env<AttrContext> envDup = env.dup(jCRecordPattern, attrContext.dup(attrContext.scope.dup()));
        List list3 = list;
        while (list3.nonEmpty() && list2.nonEmpty()) {
            try {
                attribExpr((JCTree) list2.head, envDup, (Type) list3.head);
                checkCastablePattern(((JCTree.JCPattern) list2.head).pos(), (Type) list3.head, ((JCTree.JCPattern) list2.head).type);
                listBuffer.addAll(this.matchBindings.bindingsWhenTrue);
                List<Symbol.BindingSymbol> list4 = this.matchBindings.bindingsWhenTrue;
                Scope.WriteableScope writeableScope = envDup.info.scope;
                Objects.requireNonNull(writeableScope);
                list4.forEach(new ui0(writeableScope));
                list2 = list2.tail;
                list3 = list3.tail;
            } catch (Throwable th) {
                envDup.info.scope.leave();
                throw th;
            }
        }
        if (list3.nonEmpty() || list2.nonEmpty()) {
            while (list2.nonEmpty()) {
                attribExpr((JCTree) list2.head, envDup, Type.noType);
                list2 = list2.tail;
            }
            this.log.error(jCRecordPattern.pos(), CompilerProperties.Errors.IncorrectNumberOfNestedPatterns(list, (List) jCRecordPattern.nested.stream().map(new Function() { // from class: si0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((JCTree.JCPattern) obj).type;
                }
            }).collect(List.collector())));
        }
        envDup.info.scope.leave();
        this.chk.validate(jCRecordPattern.deconstructor, this.env, true);
        this.result = jCRecordPattern.type;
        this.matchBindings = new MatchBindingsComputer.MatchBindings(listBuffer.toList(), List.nil());
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:122:0x02f5  */
    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitReference(JCTree.JCMemberReference jCMemberReference) throws Throwable {
        List<Type> list;
        boolean z;
        boolean z2;
        boolean z3;
        Attr attr = this;
        JCTree.JCMemberReference jCMemberReference2 = jCMemberReference;
        if (attr.pt().isErroneous() || (attr.pt().hasTag(TypeTag.NONE) && attr.pt() != Type.recoveryType)) {
            if (attr.pt().hasTag(TypeTag.NONE)) {
                AttrContext attrContext = attr.env.info;
                if (attrContext.enclVar == null || !attrContext.enclVar.type.isErroneous()) {
                    attr.log.error(jCMemberReference2.pos(), CompilerProperties.Errors.UnexpectedMref);
                }
            }
            Type typeCreateErrorType = attr.types.createErrorType(attr.pt());
            jCMemberReference2.type = typeCreateErrorType;
            attr.result = typeCreateErrorType;
            return;
        }
        Env<AttrContext> envDup = attr.env.dup(jCMemberReference2);
        try {
            Type typeAttribTree = attr.attribTree(jCMemberReference2.expr, attr.env, memberReferenceQualifierResult(jCMemberReference));
            MemberReferenceTree.ReferenceMode mode = jCMemberReference2.getMode();
            MemberReferenceTree.ReferenceMode referenceMode = MemberReferenceTree.ReferenceMode.NEW;
            if (mode == referenceMode) {
                typeAttribTree = attr.chk.checkConstructorRefType(jCMemberReference2.expr, typeAttribTree);
                if (!typeAttribTree.isErroneous() && typeAttribTree.isRaw() && jCMemberReference2.typeargs != null) {
                    attr.log.error(jCMemberReference2.expr.pos(), CompilerProperties.Errors.InvalidMref(Kinds.kindName(jCMemberReference2.getMode()), CompilerProperties.Fragments.MrefInferAndExplicitParams));
                    typeAttribTree = attr.types.createErrorType(typeAttribTree);
                }
            }
            Type type = typeAttribTree;
            if (type.isErroneous()) {
                jCMemberReference2.type = type;
                attr.result = type;
                return;
            }
            if (TreeInfo.isStaticSelector(jCMemberReference2.expr, attr.names)) {
                attr.chk.validate(jCMemberReference2.expr, attr.env, false);
            } else {
                Symbol symbol = TreeInfo.symbol(jCMemberReference2.expr);
                envDup.info.selectSuper = symbol != null && symbol.name == attr.names._super;
            }
            List<Type> listNil = List.nil();
            List<JCTree.JCExpression> list2 = jCMemberReference2.typeargs;
            if (list2 != null) {
                listNil = attr.attribTypes(list2, envDup);
            }
            DeferredAttr.AttrMode attrMode = attr.resultInfo.checkContext.deferredAttrContext().mode;
            DeferredAttr.AttrMode attrMode2 = DeferredAttr.AttrMode.CHECK;
            boolean z4 = attrMode == attrMode2 && attr.rs.isSerializable(attr.pt());
            TargetInfo targetInfo = attr.getTargetInfo(jCMemberReference2, attr.resultInfo, null);
            Type type2 = targetInfo.target;
            Type type3 = targetInfo.descriptor;
            try {
                attr.setFunctionalInfo(envDup, jCMemberReference2, attr.pt(), type3, type2, attr.resultInfo.checkContext);
                jCMemberReference2 = jCMemberReference2;
                List<Type> listMo71getParameterTypes = type3.mo71getParameterTypes();
                Resolve.MethodCheck methodReferenceCheck = attr.rs.resolveMethodCheck;
                if (attr.resultInfo.checkContext.inferenceContext().free(listMo71getParameterTypes)) {
                    Resolve resolve = attr.rs;
                    Objects.requireNonNull(resolve);
                    methodReferenceCheck = resolve.new MethodReferenceCheck(attr.resultInfo.checkContext.inferenceContext());
                }
                List<Type> listSave = attr.resultInfo.checkContext.inferenceContext().save();
                List<Type> list3 = listNil;
                try {
                    list = listSave;
                    try {
                        Pair<Symbol, Resolve.ReferenceLookupHelper> pairResolveMemberReference = attr.rs.resolveMemberReference(envDup, jCMemberReference2, jCMemberReference2.expr.type, jCMemberReference2.name, listMo71getParameterTypes, list3, targetInfo.descriptor, methodReferenceCheck, attr.resultInfo.checkContext.inferenceContext(), attr.rs.basicReferenceChooser);
                        try {
                            attr.resultInfo.checkContext.inferenceContext().rollback(list);
                            Symbol symbol2 = pairResolveMemberReference.fst;
                            Resolve.ReferenceLookupHelper referenceLookupHelper = pairResolveMemberReference.snd;
                            Kinds.Kind kind = symbol2.kind;
                            if (kind != Kinds.Kind.MTH) {
                                switch (AnonymousClass13.$SwitchMap$com$sun$tools$javac$code$Kinds$Kind[kind.ordinal()]) {
                                    case 1:
                                        z3 = false;
                                        break;
                                    case 2:
                                    case 3:
                                    case 4:
                                    case 5:
                                    case 6:
                                        z3 = true;
                                        break;
                                    default:
                                        Assert.error("unexpected result kind " + symbol2.kind);
                                        z3 = false;
                                        break;
                                }
                                JCDiagnostic diagnostic = ((Resolve.ResolveError) symbol2.baseSymbol()).getDiagnostic(JCDiagnostic.DiagnosticType.FRAGMENT, jCMemberReference2, type.tsym, type, jCMemberReference2.name, listMo71getParameterTypes, list3);
                                JCDiagnostic jCDiagnosticCreate = attr.diags.create(attr.log.currentSource(), jCMemberReference2, z3 ? CompilerProperties.Fragments.InvalidMref(Kinds.kindName(jCMemberReference2.getMode()), diagnostic) : CompilerProperties.Errors.InvalidMref(Kinds.kindName(jCMemberReference2.getMode()), diagnostic));
                                if (z3 && type2 == Type.recoveryType) {
                                    jCMemberReference2.type = type2;
                                    attr.result = type2;
                                    return;
                                }
                                if (z3) {
                                    attr.resultInfo.checkContext.report(jCMemberReference2, jCDiagnosticCreate);
                                } else {
                                    attr.log.report(jCDiagnosticCreate);
                                }
                                Type typeCreateErrorType2 = attr.types.createErrorType(type2);
                                jCMemberReference2.type = typeCreateErrorType2;
                                attr.result = typeCreateErrorType2;
                                return;
                            }
                            Symbol symbolBaseSymbol = symbol2.isConstructor() ? symbol2.baseSymbol() : symbol2;
                            jCMemberReference2.sym = symbolBaseSymbol;
                            jCMemberReference2.kind = referenceLookupHelper.referenceKind(symbolBaseSymbol);
                            jCMemberReference2.ownerAccessible = attr.rs.isAccessible(envDup, jCMemberReference2.sym.enclClass());
                            if (type3.mo73getReturnType() == Type.recoveryType) {
                                jCMemberReference2.type = type2;
                                attr.result = type2;
                                return;
                            }
                            if (attr.env.info.attributionMode.isSpeculative || jCMemberReference2.getMode() != referenceMode) {
                                z = false;
                            } else {
                                z = false;
                                attr.checkNewInnerClass(jCMemberReference2.pos(), attr.env, type, false);
                            }
                            if (attr.resultInfo.checkContext.deferredAttrContext().mode != attrMode2) {
                                z2 = true;
                            } else {
                                if (jCMemberReference2.getMode() == MemberReferenceTree.ReferenceMode.INVOKE && TreeInfo.isStaticSelector(jCMemberReference2.expr, attr.names) && jCMemberReference2.kind.isUnbound() && referenceLookupHelper.site.isRaw()) {
                                    attr.chk.checkRaw(jCMemberReference2.expr, envDup);
                                }
                                if (jCMemberReference2.sym.isStatic() && TreeInfo.isStaticSelector(jCMemberReference2.expr, attr.names) && type.getTypeArguments().nonEmpty()) {
                                    attr.log.error(jCMemberReference2.expr.pos(), CompilerProperties.Errors.InvalidMref(Kinds.kindName(jCMemberReference2.getMode()), CompilerProperties.Fragments.StaticMrefWithTargs));
                                    Type typeCreateErrorType3 = attr.types.createErrorType(type2);
                                    jCMemberReference2.type = typeCreateErrorType3;
                                    attr.result = typeCreateErrorType3;
                                    return;
                                }
                                if (!symbol2.isStatic() && jCMemberReference2.kind == JCTree.JCMemberReference.ReferenceKind.SUPER) {
                                    attr.rs.checkNonAbstract(jCMemberReference2.pos(), jCMemberReference2.sym);
                                }
                                if (z4) {
                                    z2 = true;
                                    attr.chk.checkAccessFromSerializableElement(jCMemberReference2, true);
                                } else {
                                    z2 = true;
                                }
                            }
                            ResultInfo resultInfoDup = attr.resultInfo.dup(attr.newMethodTemplate(type3.mo73getReturnType().hasTag(TypeTag.VOID) ? Type.noType : type3.mo73getReturnType(), jCMemberReference2.kind.isUnbound() ? listMo71getParameterTypes.tail : listMo71getParameterTypes, list3), attr.new FunctionalReturnContext(attr.resultInfo.checkContext), CheckMode.NO_TREE_UPDATE);
                            attr = attr;
                            Type typeCheckId = attr.checkId(jCMemberReference2, referenceLookupHelper.site, symbol2, envDup, resultInfoDup);
                            try {
                                if (jCMemberReference2.kind.isUnbound() && attr.resultInfo.checkContext.inferenceContext().free(listMo71getParameterTypes.head) && !attr.types.isSubtype(attr.resultInfo.checkContext.inferenceContext().asUndetVar(listMo71getParameterTypes.head), type)) {
                                    Assert.error("Can't get here");
                                }
                                if (!typeCheckId.isErroneous()) {
                                    typeCheckId = attr.types.createMethodTypeWithReturn(typeCheckId, attr.adjustMethodReturnType(symbol2, referenceLookupHelper.site, jCMemberReference2.name, resultInfoDup.pt.mo71getParameterTypes(), typeCheckId.mo73getReturnType()));
                                }
                                Type type4 = typeCheckId;
                                boolean z5 = attr.resultInfo.checkContext.deferredAttrContext().mode == DeferredAttr.AttrMode.SPECULATIVE ? z2 : z;
                                jCMemberReference2.type = type2;
                                attr.checkReferenceCompatible(jCMemberReference2, type3, type4, attr.resultInfo.checkContext, z5);
                                if (!z5) {
                                    attr.checkAccessibleTypes(jCMemberReference2, envDup, attr.resultInfo.checkContext.inferenceContext(), type3, type2);
                                }
                                attr.chk.checkRequiresIdentity(jCMemberReference2, envDup.info.lint);
                                attr.result = attr.check(jCMemberReference2, type2, Kinds.KindSelector.VAL, attr.resultInfo);
                            } catch (Types.FunctionDescriptorLookupError e) {
                                e = e;
                                jCMemberReference2 = jCMemberReference2;
                                attr.resultInfo.checkContext.report(jCMemberReference2, e.getDiagnostic());
                                Type typeCreateErrorType4 = attr.types.createErrorType(attr.pt());
                                jCMemberReference2.type = typeCreateErrorType4;
                                attr.result = typeCreateErrorType4;
                            }
                        } catch (Types.FunctionDescriptorLookupError e2) {
                            e = e2;
                            attr = attr;
                        }
                    } catch (Throwable th) {
                        th = th;
                        attr = attr;
                        attr.resultInfo.checkContext.inferenceContext().rollback(list);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    list = listSave;
                }
            } catch (Types.FunctionDescriptorLookupError e3) {
                e = e3;
                jCMemberReference2 = jCMemberReference2;
            }
        } catch (Types.FunctionDescriptorLookupError e4) {
            e = e4;
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitReturn(JCTree.JCReturn jCReturn) {
        JCTree.JCMethodDecl jCMethodDecl;
        Env<AttrContext> env = this.env;
        AttrContext attrContext = env.info;
        if (attrContext.returnResult == null) {
            this.log.error(jCReturn.pos(), CompilerProperties.Errors.RetOutsideMeth);
        } else if (attrContext.yieldResult != null) {
            this.log.error(jCReturn.pos(), CompilerProperties.Errors.ReturnOutsideSwitchExpression);
            JCTree.JCExpression jCExpression = jCReturn.expr;
            if (jCExpression != null) {
                Env<AttrContext> env2 = this.env;
                attribExpr(jCExpression, env2, env2.info.yieldResult.pt);
            }
        } else if (attrContext.isLambda || (jCMethodDecl = env.enclMethod) == null || !TreeInfo.isCompactConstructor(jCMethodDecl)) {
            JCTree.JCExpression jCExpression2 = jCReturn.expr;
            Env<AttrContext> env3 = this.env;
            if (jCExpression2 != null) {
                if (env3.info.returnResult.pt.hasTag(TypeTag.VOID)) {
                    this.env.info.returnResult.checkContext.report(jCReturn.expr.pos(), this.diags.fragment(CompilerProperties.Fragments.UnexpectedRetVal));
                }
                JCTree.JCExpression jCExpression3 = jCReturn.expr;
                Env<AttrContext> env4 = this.env;
                attribTree(jCExpression3, env4, env4.info.returnResult);
            } else if (!env3.info.returnResult.pt.hasTag(TypeTag.VOID) && !this.env.info.returnResult.pt.hasTag(TypeTag.NONE)) {
                this.env.info.returnResult.checkContext.report(jCReturn.pos(), this.diags.fragment(CompilerProperties.Fragments.MissingRetVal(this.env.info.returnResult.pt)));
            }
        } else {
            Log log = this.log;
            JCTree.JCMethodDecl jCMethodDecl2 = this.env.enclMethod;
            log.error(jCMethodDecl2, CompilerProperties.Errors.InvalidCanonicalConstructorInRecord(CompilerProperties.Fragments.Compact, jCMethodDecl2.sym.name, CompilerProperties.Fragments.CanonicalCantHaveReturnStatement));
        }
        this.result = null;
    }

    /* JADX WARN: Code duplicated, block: B:120:0x029c  */
    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitSelect(JCTree.JCFieldAccess jCFieldAccess) {
        Type typeAsSuper;
        Kinds.Kind kind;
        Kinds.KindSelector kindSelectorOf = Kinds.KindSelector.NIL;
        Name name = jCFieldAccess.name;
        Names names = this.names;
        if (name == names._this || name == names._super || name == names._class) {
            kindSelectorOf = Kinds.KindSelector.TYP;
        } else {
            Kinds.KindSelector kindSelectorPkind = pkind();
            Kinds.KindSelector kindSelector = Kinds.KindSelector.PCK;
            if (kindSelectorPkind.contains(kindSelector)) {
                kindSelectorOf = Kinds.KindSelector.of(kindSelectorOf, kindSelector);
            }
            Kinds.KindSelector kindSelectorPkind2 = pkind();
            Kinds.KindSelector kindSelector2 = Kinds.KindSelector.TYP;
            if (kindSelectorPkind2.contains(kindSelector2)) {
                kindSelectorOf = Kinds.KindSelector.of(kindSelectorOf, kindSelector2, kindSelector);
            }
            if (pkind().contains(Kinds.KindSelector.VAL_MTH)) {
                kindSelectorOf = Kinds.KindSelector.of(kindSelectorOf, Kinds.KindSelector.VAL, kindSelector2);
            }
        }
        Type typeAttribTree = attribTree(jCFieldAccess.selected, this.env, new ResultInfo(this, kindSelectorOf, Type.noType));
        if (!pkind().contains(Kinds.KindSelector.TYP_PCK)) {
            typeAttribTree = capture(typeAttribTree);
        }
        if (kindSelectorOf == Kinds.KindSelector.TYP) {
            Type type = typeAttribTree;
            while (type.hasTag(TypeTag.ARRAY)) {
                type = ((Type.ArrayType) type).elemtype;
            }
            if (type.hasTag(TypeTag.TYPEVAR)) {
                this.log.error(jCFieldAccess.pos(), CompilerProperties.Errors.TypeVarCantBeDeref);
                Type typeCreateErrorType = this.types.createErrorType(jCFieldAccess.name, typeAttribTree.tsym, typeAttribTree);
                jCFieldAccess.type = typeCreateErrorType;
                this.result = typeCreateErrorType;
                jCFieldAccess.sym = typeCreateErrorType.tsym;
                return;
            }
        }
        Symbol symbol = TreeInfo.symbol(jCFieldAccess.selected);
        Env<AttrContext> env = this.env;
        AttrContext attrContext = env.info;
        boolean z = attrContext.selectSuper;
        attrContext.selectSuper = symbol != null && symbol.name == this.names._super;
        attrContext.pendingResolutionPhase = null;
        Symbol symbolSelectSym = selectSym(jCFieldAccess, symbol, typeAttribTree, env, this.resultInfo);
        Kinds.Kind kind2 = symbolSelectSym.kind;
        Kinds.Kind kind3 = Kinds.Kind.VAR;
        if (kind2 == kind3 && symbolSelectSym.name != this.names._super && this.env.info.defaultSuperCallSite != null) {
            this.log.error(jCFieldAccess.selected.pos(), CompilerProperties.Errors.NotEnclClass(typeAttribTree.tsym));
            symbolSelectSym = this.syms.errSymbol;
        }
        if (symbolSelectSym.exists() && !isType(symbolSelectSym) && pkind().contains(Kinds.KindSelector.TYP_PCK)) {
            typeAttribTree = capture(typeAttribTree);
            symbolSelectSym = selectSym(jCFieldAccess, symbol, typeAttribTree, this.env, this.resultInfo);
        }
        this.env.info.lastResolveVarargs();
        jCFieldAccess.sym = symbolSelectSym;
        if (typeAttribTree.hasTag(TypeTag.TYPEVAR) && !isType(symbolSelectSym) && symbolSelectSym.kind != Kinds.Kind.ERR) {
            typeAttribTree = this.types.skipTypeVars(typeAttribTree, true);
        }
        Type type2 = typeAttribTree;
        if (symbolSelectSym.kind == kind3) {
            Symbol.VarSymbol varSymbol = (Symbol.VarSymbol) symbolSelectSym;
            checkInit(jCFieldAccess, this.env, varSymbol, true);
            if (Kinds.KindSelector.ASG.subset(pkind())) {
                checkAssignable(jCFieldAccess.pos(), varSymbol, jCFieldAccess.selected, this.env);
            }
        }
        if (symbol != null && symbol.kind == kind3 && ((Symbol.VarSymbol) symbol).isResourceVariable() && symbolSelectSym.kind == Kinds.Kind.MTH && symbolSelectSym.name.equals(this.names.close) && symbolSelectSym.overrides(this.syms.autoCloseableClose, symbol.type.tsym, this.types, true)) {
            this.log.warning(jCFieldAccess, CompilerProperties.LintWarnings.TryExplicitCloseCall);
        }
        if (isType(symbolSelectSym) && (symbol == null || !symbol.kind.matches(Kinds.KindSelector.TYP_PCK))) {
            jCFieldAccess.type = check(jCFieldAccess.selected, pt(), symbol == null ? Kinds.KindSelector.VAL : symbol.kind.toSelector(), new ResultInfo(this, Kinds.KindSelector.TYP_PCK, pt()));
        }
        if (isType(symbol)) {
            Name name2 = symbolSelectSym.name;
            Names names2 = this.names;
            if (name2 != names2._this && name2 != names2._super && (symbolSelectSym.flags() & 8) == 0 && symbolSelectSym.name != this.names._super && ((kind = symbolSelectSym.kind) == kind3 || kind == Kinds.Kind.MTH)) {
                Resolve resolve = this.rs;
                Objects.requireNonNull(resolve);
                resolve.accessBase(new Resolve.StaticError(resolve, symbolSelectSym), jCFieldAccess.pos(), type2, symbolSelectSym.name, true);
            }
        } else if (symbolSelectSym.kind != Kinds.Kind.ERR && (symbolSelectSym.flags() & 8) != 0 && symbolSelectSym.name != this.names._class) {
            boolean zIsAnonymous = symbolSelectSym.owner.isAnonymous();
            Log log = this.log;
            if (zIsAnonymous) {
                log.warning(jCFieldAccess, CompilerProperties.LintWarnings.StaticNotQualifiedByType2(symbolSelectSym.kind.kindName()));
            } else {
                log.warning(jCFieldAccess, CompilerProperties.LintWarnings.StaticNotQualifiedByType(symbolSelectSym.kind.kindName(), symbolSelectSym.owner));
            }
        }
        if (this.env.info.selectSuper && (symbolSelectSym.flags() & 8) == 0) {
            this.rs.checkNonAbstract(jCFieldAccess.pos(), symbolSelectSym);
            if (!type2.isRaw() || (typeAsSuper = this.types.asSuper(this.env.enclClass.sym.type, type2.tsym)) == null) {
                typeAsSuper = type2;
            }
        } else {
            typeAsSuper = type2;
        }
        AttrContext attrContext2 = this.env.info;
        if (attrContext2.isSerializable) {
            this.chk.checkAccessFromSerializableElement(jCFieldAccess, attrContext2.isSerializableLambda);
        }
        Symbol symbol2 = symbolSelectSym;
        Env<AttrContext> env2 = this.env;
        env2.info.selectSuper = z;
        this.result = checkId(jCFieldAccess, typeAsSuper, symbol2, env2, this.resultInfo);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitSkip(JCTree.JCSkip jCSkip) {
        this.result = null;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitSwitch(JCTree.JCSwitch jCSwitch) {
        handleSwitch(jCSwitch, jCSwitch.selector, jCSwitch.cases, new BiConsumer() { // from class: di0
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                Attr.K(this.a, (JCTree.JCCase) obj, (Env) obj2);
            }
        });
        this.result = null;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitSwitchExpression(final JCTree.JCSwitchExpression jCSwitchExpression) {
        boolean z;
        ResultInfo resultInfoDup;
        JCTree.JCPolyExpression.PolyKind polyKind = (!pt().hasTag(TypeTag.NONE) || pt() == Type.recoveryType || pt() == Infer.anyPoly) ? JCTree.JCPolyExpression.PolyKind.POLY : JCTree.JCPolyExpression.PolyKind.STANDALONE;
        jCSwitchExpression.polyKind = polyKind;
        if (polyKind == JCTree.JCPolyExpression.PolyKind.POLY && this.resultInfo.pt.hasTag(TypeTag.VOID)) {
            this.resultInfo.checkContext.report(jCSwitchExpression, this.diags.fragment(CompilerProperties.Fragments.SwitchExpressionTargetCantBeVoid));
            this.resultInfo = this.recoveryInfo;
            z = true;
        } else {
            z = false;
        }
        JCTree.JCPolyExpression.PolyKind polyKind2 = jCSwitchExpression.polyKind;
        JCTree.JCPolyExpression.PolyKind polyKind3 = JCTree.JCPolyExpression.PolyKind.STANDALONE;
        if (polyKind2 == polyKind3) {
            resultInfoDup = this.unknownExprInfo;
        } else {
            ResultInfo resultInfo = this.resultInfo;
            resultInfoDup = resultInfo.dup(switchExpressionContext(resultInfo.checkContext));
        }
        final ResultInfo resultInfo2 = resultInfoDup;
        final ListBuffer listBuffer = new ListBuffer();
        final ListBuffer listBuffer2 = new ListBuffer();
        handleSwitch(jCSwitchExpression, jCSwitchExpression.selector, jCSwitchExpression.cases, new BiConsumer() { // from class: com.sun.tools.javac.comp.l
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                Attr.n(this.a, resultInfo2, jCSwitchExpression, listBuffer, listBuffer2, (JCTree.JCCase) obj, (Env) obj2);
            }
        });
        if (jCSwitchExpression.cases.isEmpty()) {
            this.log.error(jCSwitchExpression.pos(), CompilerProperties.Errors.SwitchExpressionEmpty);
        } else if (listBuffer2.isEmpty()) {
            this.log.error(jCSwitchExpression.pos(), CompilerProperties.Errors.SwitchExpressionNoResultExpressions);
        }
        Type typeCreateErrorType = z ? this.types.createErrorType(pt()) : check(jCSwitchExpression, jCSwitchExpression.polyKind == polyKind3 ? condType(listBuffer.toList(), listBuffer2.toList()) : pt(), Kinds.KindSelector.VAL, this.resultInfo);
        jCSwitchExpression.type = typeCreateErrorType;
        this.result = typeCreateErrorType;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitSynchronized(JCTree.JCSynchronized jCSynchronized) {
        this.chk.checkRefType(jCSynchronized.pos(), attribExpr(jCSynchronized.lock, this.env));
        Type type = jCSynchronized.lock.type;
        if (type != null && type.isValueBased()) {
            this.log.warning(jCSynchronized.pos(), CompilerProperties.LintWarnings.AttemptToSynchronizeOnInstanceOfValueBasedClass);
        }
        attribStat(jCSynchronized.body, this.env);
        this.result = null;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitThrow(JCTree.JCThrow jCThrow) {
        this.chk.checkType(jCThrow, attribExpr(jCThrow.expr, this.env, Type.noType), this.syms.throwableType);
        this.result = null;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTree(JCTree jCTree) {
        throw new AssertionError();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTry(JCTree.JCTry jCTry) {
        Env<AttrContext> envDup;
        Env<AttrContext> env = this.env;
        AttrContext attrContext = env.info;
        Env<AttrContext> envDup2 = env.dup(jCTry, attrContext.dup(attrContext.scope.dup()));
        try {
            boolean zNonEmpty = jCTry.resources.nonEmpty();
            if (zNonEmpty) {
                Env<AttrContext> env2 = this.env;
                AttrContext attrContext2 = envDup2.info;
                envDup = env2.dup(jCTry, attrContext2.dup(attrContext2.scope.dup()));
            } else {
                envDup = envDup2;
            }
            try {
                for (JCTree jCTree : jCTry.resources) {
                    ResultInfo resultInfo = new ResultInfo(this, Kinds.KindSelector.VAR, this.syms.autoCloseableType, new Check.NestedCheckContext(this.resultInfo.checkContext) { // from class: com.sun.tools.javac.comp.Attr.3
                        @Override // com.sun.tools.javac.comp.Check.NestedCheckContext, com.sun.tools.javac.comp.Check.CheckContext
                        public void report(JCDiagnostic.DiagnosticPosition diagnosticPosition, JCDiagnostic jCDiagnostic) {
                            Attr attr = Attr.this;
                            attr.chk.basicHandler.report(diagnosticPosition, attr.diags.fragment(CompilerProperties.Fragments.TryNotApplicableToType(jCDiagnostic)));
                        }
                    });
                    if (jCTree.hasTag(JCTree.Tag.VARDEF)) {
                        attribStat(jCTree, envDup);
                        resultInfo.check(jCTree, jCTree.type);
                        checkAutoCloseable(envDup2, jCTree, true);
                        Symbol.VarSymbol varSymbol = ((JCTree.JCVariableDecl) jCTree).sym;
                        varSymbol.flags_field |= 16;
                        varSymbol.setData(ElementKind.RESOURCE_VARIABLE);
                    } else {
                        attribTree(jCTree, envDup, resultInfo);
                    }
                }
                attribStat(jCTry.body, envDup);
                if (zNonEmpty) {
                    envDup.info.scope.leave();
                }
                for (List list = jCTry.catchers; list.nonEmpty(); list = list.tail) {
                    JCTree.JCCatch jCCatch = (JCTree.JCCatch) list.head;
                    AttrContext attrContext3 = envDup2.info;
                    Env<AttrContext> envDup3 = envDup2.dup(jCCatch, attrContext3.dup(attrContext3.scope.dup()));
                    try {
                        Type typeAttribStat = attribStat(jCCatch.param, envDup3);
                        if (TreeInfo.isMultiCatch(jCCatch)) {
                            jCCatch.param.sym.flags_field |= 549755813904L;
                        }
                        Symbol.VarSymbol varSymbol2 = jCCatch.param.sym;
                        if (varSymbol2.kind == Kinds.Kind.VAR) {
                            varSymbol2.setData(ElementKind.EXCEPTION_PARAMETER);
                        }
                        this.chk.checkType(jCCatch.param.vartype.pos(), this.chk.checkClassType(jCCatch.param.vartype.pos(), typeAttribStat), this.syms.throwableType);
                        attribStat(jCCatch.body, envDup3);
                        envDup3.info.scope.leave();
                    } catch (Throwable th) {
                        envDup3.info.scope.leave();
                        throw th;
                    }
                }
                JCTree.JCBlock jCBlock = jCTry.finalizer;
                if (jCBlock != null) {
                    attribStat(jCBlock, envDup2);
                }
                this.result = null;
                envDup2.info.scope.leave();
            } catch (Throwable th2) {
                if (zNonEmpty) {
                    envDup.info.scope.leave();
                }
                throw th2;
            }
        } catch (Throwable th3) {
            envDup2.info.scope.leave();
            throw th3;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v20, types: [A, com.sun.tools.javac.code.Type] */
    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTypeApply(JCTree.JCTypeApply jCTypeApply) {
        Type classType;
        Type typeAsEnclosingSuper;
        Type typeCreateErrorType = this.types.createErrorType(jCTypeApply.type);
        Type typeCheckClassType = this.chk.checkClassType(jCTypeApply.clazz.pos(), attribType(jCTypeApply.clazz, this.env));
        List listAttribTypes = attribTypes(jCTypeApply.arguments, this.env);
        if (typeCheckClassType.hasTag(TypeTag.CLASS)) {
            List typeArguments = typeCheckClassType.tsym.type.getTypeArguments();
            if (listAttribTypes.isEmpty()) {
                listAttribTypes = typeArguments;
            }
            if (listAttribTypes.length() == typeArguments.length()) {
                List list = listAttribTypes;
                while (list.nonEmpty()) {
                    list.head = ((Type) list.head).withTypeVar((Type) typeArguments.head);
                    list = list.tail;
                    typeArguments = typeArguments.tail;
                }
                Type enclosingType = typeCheckClassType.getEnclosingType();
                TypeTag typeTag = TypeTag.CLASS;
                if (enclosingType.hasTag(typeTag)) {
                    JCTree.JCExpression jCExpressionTypeIn = TreeInfo.typeIn(jCTypeApply.clazz);
                    if (jCExpressionTypeIn.hasTag(JCTree.Tag.IDENT)) {
                        typeAsEnclosingSuper = this.env.enclClass.sym.type;
                    } else {
                        if (!jCExpressionTypeIn.hasTag(JCTree.Tag.SELECT)) {
                            s22.a("", jCTypeApply);
                            return;
                        }
                        typeAsEnclosingSuper = ((JCTree.JCFieldAccess) jCExpressionTypeIn).selected.type;
                    }
                    if (enclosingType.hasTag(typeTag) && typeAsEnclosingSuper != enclosingType) {
                        if (typeAsEnclosingSuper.hasTag(typeTag) || typeAsEnclosingSuper.hasTag(TypeTag.TYPEVAR)) {
                            typeAsEnclosingSuper = this.types.asEnclosingSuper(typeAsEnclosingSuper, enclosingType.tsym);
                        }
                        enclosingType = typeAsEnclosingSuper == null ? this.types.erasure(enclosingType) : typeAsEnclosingSuper;
                    }
                }
                classType = new Type.ClassType(enclosingType, listAttribTypes, typeCheckClassType.tsym, typeCheckClassType.getMetadata());
            } else {
                int length = typeArguments.length();
                Log log = this.log;
                if (length != 0) {
                    log.error(jCTypeApply.pos(), CompilerProperties.Errors.WrongNumberTypeArgs(Integer.toString(typeArguments.length())));
                } else {
                    log.error(jCTypeApply.pos(), CompilerProperties.Errors.TypeDoesntTakeParams(typeCheckClassType.tsym));
                }
                classType = this.types.createErrorType(jCTypeApply.type);
            }
        } else if (typeCheckClassType.hasTag(TypeTag.ERROR)) {
            classType = typeCreateErrorType;
            Type.ErrorType errorType = new Type.ErrorType(typeCheckClassType.getOriginalType(), typeCheckClassType.tsym, typeCheckClassType.getMetadata());
            errorType.typarams_field = listAttribTypes;
            classType = errorType;
        }
        classType = typeCreateErrorType;
        this.result = check(jCTypeApply, classType, Kinds.KindSelector.TYP, this.resultInfo);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTypeArray(JCTree.JCArrayTypeTree jCArrayTypeTree) {
        this.result = check(jCArrayTypeTree, new Type.ArrayType(attribType(jCArrayTypeTree.elemtype, this.env), this.syms.arrayClass), Kinds.KindSelector.TYP, this.resultInfo);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTypeCast(JCTree.JCTypeCast jCTypeCast) {
        Type typeAttribType = attribType(jCTypeCast.clazz, this.env);
        this.chk.validate(jCTypeCast.clazz, this.env, false);
        this.chk.checkRequiresIdentity(jCTypeCast, this.env.info.lint);
        Env<AttrContext> envDup = this.env.dup(jCTypeCast);
        JCTree.JCExpression jCExpressionSkipParens = TreeInfo.skipParens(jCTypeCast.expr);
        boolean z = jCExpressionSkipParens.hasTag(JCTree.Tag.LAMBDA) || jCExpressionSkipParens.hasTag(JCTree.Tag.REFERENCE);
        Type typeAttribTree = attribTree(jCTypeCast.expr, envDup, z ? new ResultInfo(this, Kinds.KindSelector.VAL, typeAttribType, new Check.NestedCheckContext(this.resultInfo.checkContext) { // from class: com.sun.tools.javac.comp.Attr.10
            @Override // com.sun.tools.javac.comp.Check.NestedCheckContext, com.sun.tools.javac.comp.Check.CheckContext
            public boolean compatible(Type type, Type type2, Warner warner) {
                return Attr.this.types.isCastable(type, type2, warner);
            }
        }) : this.unknownExprInfo);
        if (!z) {
            typeAttribType = this.chk.checkCastable(jCTypeCast.expr.pos(), typeAttribTree, typeAttribType);
        }
        if (typeAttribTree.constValue() != null) {
            typeAttribType = this.cfolder.coerce(typeAttribTree, typeAttribType);
        }
        this.result = check(jCTypeCast, capture(typeAttribType), Kinds.KindSelector.VAL, this.resultInfo);
        if (z) {
            return;
        }
        this.chk.checkRedundantCast(envDup, jCTypeCast);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTypeIdent(JCTree.JCPrimitiveTypeTree jCPrimitiveTypeTree) {
        this.result = check(jCPrimitiveTypeTree, this.syms.typeOfTag[jCPrimitiveTypeTree.typetag.ordinal()], Kinds.KindSelector.TYP, this.resultInfo);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTypeIntersection(JCTree.JCTypeIntersection jCTypeIntersection) {
        attribTypes(jCTypeIntersection.bounds, this.env);
        Type typeCheckIntersection = checkIntersection(jCTypeIntersection, jCTypeIntersection.bounds);
        this.result = typeCheckIntersection;
        jCTypeIntersection.type = typeCheckIntersection;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTypeParameter(JCTree.JCTypeParameter jCTypeParameter) {
        Type.TypeVar typeVar = (Type.TypeVar) jCTypeParameter.type;
        List<JCTree.JCAnnotation> list = jCTypeParameter.annotations;
        if (list != null && list.nonEmpty()) {
            this.annotate.annotateTypeParameterSecondStage(jCTypeParameter, jCTypeParameter.annotations);
        }
        if (typeVar.getUpperBound().isErroneous()) {
            return;
        }
        typeVar.setUpperBound(checkIntersection(jCTypeParameter, jCTypeParameter.bounds));
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTypeTest(JCTree.JCInstanceOf jCInstanceOf) {
        Type typeCreateErrorType;
        JCTree jCTreePrimaryPatternTypeTree;
        Type typeAttribExpr = attribExpr(jCInstanceOf.expr, this.env);
        if (typeAttribExpr.isPrimitive()) {
            this.preview.checkSourceLevel(jCInstanceOf.expr.pos(), Source.Feature.PRIMITIVE_PATTERNS);
        } else {
            typeAttribExpr = this.chk.checkNullOrRefType(jCInstanceOf.expr.pos(), typeAttribExpr);
        }
        boolean zCheckCastablePattern = false;
        if (jCInstanceOf.pattern.getTag() == JCTree.Tag.BINDINGPATTERN || jCInstanceOf.pattern.getTag() == JCTree.Tag.RECORDPATTERN) {
            attribExpr(jCInstanceOf.pattern, this.env, typeAttribExpr);
            typeCreateErrorType = jCInstanceOf.pattern.type;
            if (this.types.isSubtype(typeAttribExpr, typeCreateErrorType) && !typeAttribExpr.isErroneous() && !typeCreateErrorType.isErroneous() && jCInstanceOf.pattern.getTag() != JCTree.Tag.RECORDPATTERN && !this.allowUnconditionalPatternsInstanceOf) {
                this.log.error(jCInstanceOf.pos(), Source.Feature.UNCONDITIONAL_PATTERN_IN_INSTANCEOF.error(this.sourceName));
            }
            jCTreePrimaryPatternTypeTree = TreeInfo.primaryPatternTypeTree((JCTree.JCPattern) jCInstanceOf.pattern);
        } else {
            typeCreateErrorType = attribType(jCInstanceOf.pattern, this.env);
            jCTreePrimaryPatternTypeTree = jCInstanceOf.pattern;
            this.chk.validate(jCTreePrimaryPatternTypeTree, this.env, false);
        }
        if (typeCreateErrorType.isPrimitive()) {
            this.preview.checkSourceLevel(jCInstanceOf.pattern.pos(), Source.Feature.PRIMITIVE_PATTERNS);
        } else {
            if (!typeCreateErrorType.hasTag(TypeTag.TYPEVAR)) {
                typeCreateErrorType = this.chk.checkClassOrArrayType(jCTreePrimaryPatternTypeTree.pos(), typeCreateErrorType);
            }
            if (!typeCreateErrorType.isErroneous() && !this.types.isReifiable(typeCreateErrorType)) {
                if (this.allowReifiableTypesInInstanceof) {
                    zCheckCastablePattern = checkCastablePattern(jCInstanceOf.expr.pos(), typeAttribExpr, typeCreateErrorType);
                } else {
                    this.log.error(jCInstanceOf.pos(), Source.Feature.REIFIABLE_TYPES_INSTANCEOF.error(this.sourceName));
                    this.allowReifiableTypesInInstanceof = true;
                }
                if (!zCheckCastablePattern) {
                    typeCreateErrorType = this.types.createErrorType(typeCreateErrorType);
                }
            }
        }
        this.chk.checkCastable(jCInstanceOf.expr.pos(), typeAttribExpr, typeCreateErrorType);
        this.result = check(jCInstanceOf, this.syms.booleanType, Kinds.KindSelector.VAL, this.resultInfo);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTypeUnion(JCTree.JCTypeUnion jCTypeUnion) {
        ListBuffer<Type> listBuffer = new ListBuffer();
        ListBuffer listBuffer2 = null;
        for (JCTree.JCExpression jCExpression : jCTypeUnion.alternatives) {
            Type typeCheckType = this.chk.checkType(jCExpression.pos(), this.chk.checkClassType(jCExpression.pos(), attribType(jCExpression, this.env)), this.syms.throwableType);
            if (typeCheckType.isErroneous()) {
                if (listBuffer2 == null) {
                    listBuffer2 = new ListBuffer();
                    listBuffer2.appendList(listBuffer);
                }
                listBuffer2.append(typeCheckType);
            } else {
                if (this.chk.intersects(typeCheckType, listBuffer.toList())) {
                    for (Type type : listBuffer) {
                        boolean zIsSubtype = this.types.isSubtype(typeCheckType, type);
                        boolean zIsSubtype2 = this.types.isSubtype(type, typeCheckType);
                        if (zIsSubtype || zIsSubtype2) {
                            Type type2 = zIsSubtype ? typeCheckType : type;
                            if (!zIsSubtype) {
                                type = typeCheckType;
                            }
                            this.log.error(jCExpression.pos(), CompilerProperties.Errors.MulticatchTypesMustBeDisjoint(type2, type));
                        }
                    }
                }
                listBuffer.append(typeCheckType);
                if (listBuffer2 != null) {
                    listBuffer2.append(typeCheckType);
                }
            }
        }
        Type typeCheck = check(jCTypeUnion, this.types.lub(listBuffer.toList()), Kinds.KindSelector.TYP, this.resultInfo.dup(CheckMode.NO_TREE_UPDATE));
        if (typeCheck.hasTag(TypeTag.CLASS)) {
            if (listBuffer2 != null) {
                listBuffer = listBuffer2;
            }
            typeCheck = new Type.UnionClassType((Type.ClassType) typeCheck, listBuffer.toList());
        }
        this.result = typeCheck;
        jCTypeUnion.type = typeCheck;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitUnary(JCTree.JCUnary jCUnary) {
        Type typeFold1;
        Type typeAttribTree = jCUnary.getTag().isIncOrDecUnaryOp() ? attribTree(jCUnary.arg, this.env, this.varAssignmentInfo) : this.chk.checkNonVoid(jCUnary.arg.pos(), attribExpr(jCUnary.arg, this.env));
        Symbol.OperatorSymbol operatorSymbolResolveUnary = this.operators.resolveUnary(jCUnary, jCUnary.getTag(), typeAttribTree);
        jCUnary.operator = operatorSymbolResolveUnary;
        Type typeCreateErrorType = this.types.createErrorType(jCUnary.type);
        if (operatorSymbolResolveUnary != this.operators.noOpSymbol && !typeAttribTree.isErroneous()) {
            typeCreateErrorType = jCUnary.getTag().isIncOrDecUnaryOp() ? jCUnary.arg.type : operatorSymbolResolveUnary.type.mo73getReturnType();
            int i = operatorSymbolResolveUnary.opcode;
            if (typeAttribTree.constValue() != null && (typeFold1 = this.cfolder.fold1(i, typeAttribTree)) != null) {
                typeCreateErrorType = this.cfolder.coerce(typeFold1, typeCreateErrorType);
            }
        }
        this.result = check(jCUnary, typeCreateErrorType, Kinds.KindSelector.VAL, this.resultInfo);
        this.matchBindings = this.matchBindingsComputer.unary(jCUnary, this.matchBindings);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitVarDef(JCTree.JCVariableDecl jCVariableDecl) {
        Env<AttrContext> env = this.env;
        AttrContext attrContext = env.info;
        if (attrContext.scope.owner.kind == Kinds.Kind.MTH || attrContext.scope.owner.kind == Kinds.Kind.VAR) {
            Symbol.VarSymbol varSymbol = jCVariableDecl.sym;
            if (varSymbol != null) {
                attrContext.scope.enter(varSymbol);
            } else {
                if (jCVariableDecl.isImplicitlyTyped() && (jCVariableDecl.getModifiers().flags & 8589934592L) == 0) {
                    if (jCVariableDecl.init == null) {
                        this.log.error(jCVariableDecl, CompilerProperties.Errors.CantInferLocalVarType(jCVariableDecl.name, CompilerProperties.Fragments.LocalMissingInit));
                        jCVariableDecl.vartype = this.make.at(jCVariableDecl.pos()).Erroneous();
                    } else {
                        JCDiagnostic.Fragment fragmentCanInferLocalVarType = canInferLocalVarType(jCVariableDecl);
                        if (fragmentCanInferLocalVarType != null) {
                            this.log.error(jCVariableDecl, CompilerProperties.Errors.CantInferLocalVarType(jCVariableDecl.name, fragmentCanInferLocalVarType));
                            jCVariableDecl.vartype = this.make.at(jCVariableDecl.pos()).Erroneous();
                        }
                    }
                }
                try {
                    this.annotate.blockAnnotations();
                    this.memberEnter.memberEnter(jCVariableDecl, this.env);
                    this.annotate.unblockAnnotations();
                } catch (Throwable th) {
                    this.annotate.unblockAnnotations();
                    throw th;
                }
            }
        } else {
            doQueueScanTreeAndTypeAnnotateForVarInit(jCVariableDecl, env);
        }
        Symbol.VarSymbol varSymbol2 = jCVariableDecl.sym;
        Lint lintAugment = this.env.info.lint.augment(varSymbol2);
        Lint lint = this.chk.setLint(lintAugment);
        boolean z = false;
        boolean z2 = this.env.tree.hasTag(JCTree.Tag.LAMBDA) && ((JCTree.JCLambda) this.env.tree).paramKind == JCTree.JCLambda.ParameterKind.IMPLICIT && (jCVariableDecl.sym.flags() & 8589934592L) != 0;
        Check check = this.chk;
        JCTree.JCExpression jCExpression = jCVariableDecl.vartype;
        Env<AttrContext> env2 = this.env;
        if (!z2 && !jCVariableDecl.isImplicitlyTyped()) {
            z = true;
        }
        check.validate(jCExpression, env2, z);
        try {
            varSymbol2.getConstValue();
            this.chk.checkDeprecatedAnnotation(jCVariableDecl.pos(), varSymbol2);
            JCTree.JCExpression jCExpression2 = jCVariableDecl.init;
            if (jCExpression2 != null) {
                if ((varSymbol2.flags_field & 16) == 0 || !this.memberEnter.needsLazyConstValue(jCExpression2)) {
                    Env<AttrContext> envInitEnv = this.memberEnter.initEnv(jCVariableDecl, this.env);
                    AttrContext attrContext2 = envInitEnv.info;
                    attrContext2.lint = lintAugment;
                    attrContext2.enclVar = varSymbol2;
                    attribExpr(jCVariableDecl.init, envInitEnv, varSymbol2.type);
                    if (jCVariableDecl.isImplicitlyTyped()) {
                        varSymbol2.type = this.chk.checkLocalVarType(jCVariableDecl, jCVariableDecl.init.type, jCVariableDecl.name);
                    }
                }
                if (jCVariableDecl.isImplicitlyTyped()) {
                    setSyntheticVariableType(jCVariableDecl, varSymbol2.type);
                }
            }
            Type type = varSymbol2.type;
            jCVariableDecl.type = type;
            this.result = type;
            if (this.env.enclClass.sym.isRecord() && jCVariableDecl.sym.owner.kind == Kinds.Kind.TYP && !varSymbol2.isStatic() && isNonArgsMethodInObject(varSymbol2.name)) {
                this.log.error(jCVariableDecl, CompilerProperties.Errors.IllegalRecordComponentName(varSymbol2));
            }
            this.chk.checkRequiresIdentity(jCVariableDecl, this.env.info.lint);
        } finally {
            this.chk.setLint(lint);
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitWhileLoop(JCTree.JCWhileLoop jCWhileLoop) {
        attribExpr(jCWhileLoop.cond, this.env, this.syms.booleanType);
        MatchBindingsComputer.MatchBindings matchBindings = this.matchBindings;
        Env<AttrContext> envBindingEnv = bindingEnv(this.env, matchBindings.bindingsWhenTrue);
        try {
            attribStat(jCWhileLoop.body, envBindingEnv.dup(jCWhileLoop));
            envBindingEnv.info.scope.leave();
            handleLoopConditionBindings(matchBindings, jCWhileLoop, jCWhileLoop.body);
            this.result = null;
        } catch (Throwable th) {
            envBindingEnv.info.scope.leave();
            throw th;
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitWildcard(JCTree.JCWildcard jCWildcard) {
        this.result = check(jCWildcard, new Type.WildcardType(this.chk.checkRefType(jCWildcard.pos(), jCWildcard.kind.kind == BoundKind.UNBOUND ? this.syms.objectType : attribType(jCWildcard.inner, this.env)), jCWildcard.kind.kind, this.syms.boundClass), Kinds.KindSelector.TYP, this.resultInfo);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitYield(JCTree.JCYield jCYield) {
        Env<AttrContext> env = this.env;
        AttrContext attrContext = env.info;
        if (attrContext.yieldResult != null) {
            attribTree(jCYield.value, env, attrContext.yieldResult);
            jCYield.target = findJumpTarget(jCYield.pos(), jCYield.getTag(), this.names.empty, this.env);
        } else {
            this.log.error(jCYield.pos(), jCYield.value.hasTag(JCTree.Tag.PARENS) ? CompilerProperties.Errors.NoSwitchExpressionQualify : CompilerProperties.Errors.NoSwitchExpression);
            attribTree(jCYield.value, this.env, this.unknownExprInfo);
        }
        this.result = null;
    }

    public class MethodAttrInfo extends ResultInfo {
        public MethodAttrInfo(Check.CheckContext checkContext) {
            super(Attr.this, Kinds.KindSelector.VAL, Infer.anyPoly, checkContext);
        }

        @Override // com.sun.tools.javac.comp.Attr.ResultInfo
        public ResultInfo dup(Check.CheckContext checkContext) {
            return Attr.this.new MethodAttrInfo(checkContext);
        }

        @Override // com.sun.tools.javac.comp.Attr.ResultInfo
        public boolean needsArgumentAttr(JCTree jCTree) {
            return true;
        }

        @Override // com.sun.tools.javac.comp.Attr.ResultInfo
        public ResultInfo dup(Type type) {
            throw new IllegalStateException();
        }

        @Override // com.sun.tools.javac.comp.Attr.ResultInfo
        public ResultInfo dup(Type type, Check.CheckContext checkContext) {
            throw new IllegalStateException();
        }

        public MethodAttrInfo(Attr attr) {
            this(attr.chk.basicHandler);
        }

        @Override // com.sun.tools.javac.comp.Attr.ResultInfo
        public ResultInfo dup(Type type, Check.CheckContext checkContext, CheckMode checkMode) {
            throw new IllegalStateException();
        }

        @Override // com.sun.tools.javac.comp.Attr.ResultInfo
        public ResultInfo dup(CheckMode checkMode) {
            throw new IllegalStateException();
        }
    }

    public class ResultInfo {
        final Check.CheckContext checkContext;
        final CheckMode checkMode;
        final Kinds.KindSelector pkind;
        final Type pt;

        public ResultInfo(Attr attr, Kinds.KindSelector kindSelector, Type type) {
            this(kindSelector, type, attr.chk.basicHandler, CheckMode.NORMAL);
        }

        public Type check(JCDiagnostic.DiagnosticPosition diagnosticPosition, Type type) {
            return Attr.this.chk.checkType(diagnosticPosition, type, this.pt, this.checkContext);
        }

        public ResultInfo dup(Type type) {
            return Attr.this.new ResultInfo(this.pkind, type, this.checkContext, this.checkMode);
        }

        public boolean needsArgumentAttr(JCTree jCTree) {
            return false;
        }

        public String toString() {
            Type type = this.pt;
            return type != null ? type.toString() : "";
        }

        public ResultInfo(Attr attr, Kinds.KindSelector kindSelector, Type type, CheckMode checkMode) {
            this(kindSelector, type, attr.chk.basicHandler, checkMode);
        }

        public ResultInfo(Attr attr, Kinds.KindSelector kindSelector, Type type, Check.CheckContext checkContext) {
            this(kindSelector, type, checkContext, CheckMode.NORMAL);
        }

        public ResultInfo dup(Check.CheckContext checkContext) {
            return Attr.this.new ResultInfo(this.pkind, this.pt, checkContext, this.checkMode);
        }

        public ResultInfo(Kinds.KindSelector kindSelector, Type type, Check.CheckContext checkContext, CheckMode checkMode) {
            this.pkind = kindSelector;
            this.pt = type;
            this.checkContext = checkContext;
            this.checkMode = checkMode;
        }

        public ResultInfo dup(Type type, Check.CheckContext checkContext) {
            return Attr.this.new ResultInfo(this.pkind, type, checkContext, this.checkMode);
        }

        public ResultInfo dup(Type type, Check.CheckContext checkContext, CheckMode checkMode) {
            return Attr.this.new ResultInfo(this.pkind, type, checkContext, checkMode);
        }

        public ResultInfo dup(CheckMode checkMode) {
            return Attr.this.new ResultInfo(this.pkind, this.pt, this.checkContext, checkMode);
        }
    }

    public class RecoveryInfo extends ResultInfo {
        public RecoveryInfo(final DeferredAttr.DeferredAttrContext deferredAttrContext, final Type type) {
            super(Attr.this, Kinds.KindSelector.VAL, type, new Check.NestedCheckContext(Attr.this.chk.basicHandler) { // from class: com.sun.tools.javac.comp.Attr.RecoveryInfo.1
                @Override // com.sun.tools.javac.comp.Check.NestedCheckContext, com.sun.tools.javac.comp.Check.CheckContext
                public boolean compatible(Type type2, Type type3, Warner warner) {
                    return true;
                }

                @Override // com.sun.tools.javac.comp.Check.NestedCheckContext, com.sun.tools.javac.comp.Check.CheckContext
                public DeferredAttr.DeferredAttrContext deferredAttrContext() {
                    return deferredAttrContext;
                }

                @Override // com.sun.tools.javac.comp.Check.NestedCheckContext, com.sun.tools.javac.comp.Check.CheckContext
                public void report(JCDiagnostic.DiagnosticPosition diagnosticPosition, JCDiagnostic jCDiagnostic) {
                    if (type == Type.recoveryType || (jCDiagnostic.getDiagnosticPosition() != null && jCDiagnostic.getDiagnosticPosition().getTree().hasTag(JCTree.Tag.LAMBDA))) {
                        attr.chk.basicHandler.report(diagnosticPosition, jCDiagnostic);
                    }
                }
            });
        }

        public RecoveryInfo(Attr attr, DeferredAttr.DeferredAttrContext deferredAttrContext) {
            this(deferredAttrContext, Type.recoveryType);
        }
    }

    public void attribModule(Symbol.ModuleSymbol moduleSymbol) {
        attribWithLint(moduleSymbol, new Consumer() { // from class: ci0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Attr.N(this.b, (Env) obj);
            }
        });
    }

    public void attribPackage(final Symbol.PackageSymbol packageSymbol) {
        attribWithLint(packageSymbol, new Consumer() { // from class: mh0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.chk.checkDeprecatedAnnotation(((JCTree.JCPackageDecl) ((Env) obj).tree).pid.pos(), packageSymbol);
            }
        });
    }

    public void preFlow(JCTree jCTree) {
        this.attrRecover.doRecovery();
        new PostAttrAnalyzer() { // from class: com.sun.tools.javac.comp.Attr.6
            @Override // com.sun.tools.javac.comp.Attr.PostAttrAnalyzer, com.sun.tools.javac.tree.TreeScanner
            public void scan(JCTree jCTree2) {
                if (jCTree2 != null) {
                    Type type = jCTree2.type;
                    if (type == null || type != Type.stuckType) {
                        super.scan(jCTree2);
                    }
                }
            }

            @Override // com.sun.tools.javac.comp.Attr.PostAttrAnalyzer, com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
            public void visitClassDef(JCTree.JCClassDecl jCClassDecl) {
                if (jCClassDecl.sym != null) {
                    super.visitClassDef(jCClassDecl);
                }
            }

            @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
            public void visitLambda(JCTree.JCLambda jCLambda) {
                if (jCLambda.type != null) {
                    super.visitLambda(jCLambda);
                }
            }
        }.scan(jCTree);
    }

    public Type attribExpr(JCTree jCTree, Env<AttrContext> env) {
        return attribTree(jCTree, env, this.unknownExprInfo);
    }

    public Type attribType(JCTree jCTree, Env<AttrContext> env) {
        return attribType(jCTree, env, Type.noType);
    }

    public Type attribType(JCTree jCTree, Env<AttrContext> env, Type type) {
        return attribTree(jCTree, env, new ResultInfo(this, Kinds.KindSelector.TYP, type));
    }

    private void checkAccessibleTypes(JCDiagnostic.DiagnosticPosition diagnosticPosition, Env<AttrContext> env, InferenceContext inferenceContext, Type... typeArr) {
        checkAccessibleTypes(diagnosticPosition, env, inferenceContext, List.from(typeArr));
    }

    public Env<AttrContext> bindingEnv(Env<AttrContext> env, List<Symbol.BindingSymbol> list) {
        return bindingEnv(env, env.tree, list);
    }

    public Symbol attribIdent(JCTree jCTree, Env<AttrContext> env) {
        return (Symbol) jCTree.accept(this.identAttributer, env);
    }

    public class PostAttrAnalyzer extends TreeScanner {
        public PostAttrAnalyzer() {
        }

        private Type dummyMethodType(JCTree.JCMethodDecl jCMethodDecl) {
            JCTree.JCExpression jCExpression;
            Type type = Attr.this.syms.unknownType;
            if (jCMethodDecl != null && (jCExpression = jCMethodDecl.restype) != null && jCExpression.hasTag(JCTree.Tag.TYPEIDENT) && ((JCTree.JCPrimitiveTypeTree) jCMethodDecl.restype).typetag == TypeTag.VOID) {
                type = Attr.this.syms.voidType;
            }
            return new Type.MethodType(List.nil(), type, List.nil(), Attr.this.syms.methodClass);
        }

        private void initTypeIfNeeded(JCTree jCTree) {
            if (jCTree.type == null) {
                if (jCTree.hasTag(JCTree.Tag.METHODDEF)) {
                    jCTree.type = dummyMethodType((JCTree.JCMethodDecl) jCTree);
                } else {
                    jCTree.type = Attr.this.syms.unknownType;
                }
            }
        }

        @Override // com.sun.tools.javac.tree.TreeScanner
        public void scan(JCTree jCTree) {
            if (jCTree == null) {
                return;
            }
            if (jCTree instanceof JCTree.JCExpression) {
                initTypeIfNeeded(jCTree);
            }
            super.scan(jCTree);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitAssignop(JCTree.JCAssignOp jCAssignOp) {
            if (jCAssignOp.operator == null) {
                jCAssignOp.operator = new Symbol.OperatorSymbol(Attr.this.names.empty, dummyMethodType(), -1, Attr.this.syms.noSymbol);
            }
            super.visitAssignop(jCAssignOp);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitBinary(JCTree.JCBinary jCBinary) {
            if (jCBinary.operator == null) {
                jCBinary.operator = new Symbol.OperatorSymbol(Attr.this.names.empty, dummyMethodType(), -1, Attr.this.syms.noSymbol);
            }
            super.visitBinary(jCBinary);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitBindingPattern(JCTree.JCBindingPattern jCBindingPattern) {
            initTypeIfNeeded(jCBindingPattern);
            initTypeIfNeeded(jCBindingPattern.var);
            JCTree.JCVariableDecl jCVariableDecl = jCBindingPattern.var;
            if (jCVariableDecl.sym == null) {
                jCVariableDecl.sym = new Symbol.BindingSymbol(0L, jCVariableDecl.name, jCVariableDecl.type, Attr.this.syms.noSymbol);
                jCBindingPattern.var.sym.adr = 0;
            }
            super.visitBindingPattern(jCBindingPattern);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitClassDef(JCTree.JCClassDecl jCClassDecl) {
            initTypeIfNeeded(jCClassDecl);
            if (jCClassDecl.sym == null) {
                jCClassDecl.sym = new Symbol.ClassSymbol(0L, jCClassDecl.name, jCClassDecl.type, Attr.this.syms.noSymbol);
            }
            super.visitClassDef(jCClassDecl);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitIdent(JCTree.JCIdent jCIdent) {
            if (jCIdent.sym == null) {
                jCIdent.sym = Attr.this.syms.unknownSymbol;
            }
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitMethodDef(JCTree.JCMethodDecl jCMethodDecl) {
            initTypeIfNeeded(jCMethodDecl);
            if (jCMethodDecl.sym == null) {
                jCMethodDecl.sym = new Symbol.MethodSymbol(0L, jCMethodDecl.name, jCMethodDecl.type, Attr.this.syms.noSymbol);
            }
            super.visitMethodDef(jCMethodDecl);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitNewClass(JCTree.JCNewClass jCNewClass) {
            if (jCNewClass.constructor == null) {
                jCNewClass.constructor = new Symbol.MethodSymbol(0L, Attr.this.names.init, dummyMethodType(), Attr.this.syms.noSymbol);
            }
            if (jCNewClass.constructorType == null) {
                jCNewClass.constructorType = Attr.this.syms.unknownType;
            }
            super.visitNewClass(jCNewClass);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitRecordPattern(JCTree.JCRecordPattern jCRecordPattern) {
            initTypeIfNeeded(jCRecordPattern);
            if (jCRecordPattern.record == null) {
                jCRecordPattern.record = new Symbol.ClassSymbol(0L, TreeInfo.name(jCRecordPattern.deconstructor), jCRecordPattern.type, Attr.this.syms.noSymbol);
            }
            if (jCRecordPattern.fullComponentTypes == null) {
                jCRecordPattern.fullComponentTypes = List.nil();
            }
            super.visitRecordPattern(jCRecordPattern);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitReference(JCTree.JCMemberReference jCMemberReference) {
            super.visitReference(jCMemberReference);
            if (jCMemberReference.sym == null) {
                jCMemberReference.sym = new Symbol.MethodSymbol(0L, Attr.this.names.empty, dummyMethodType(), Attr.this.syms.noSymbol);
            }
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitSelect(JCTree.JCFieldAccess jCFieldAccess) {
            if (jCFieldAccess.sym == null) {
                jCFieldAccess.sym = Attr.this.syms.unknownSymbol;
            }
            super.visitSelect(jCFieldAccess);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitUnary(JCTree.JCUnary jCUnary) {
            if (jCUnary.operator == null) {
                jCUnary.operator = new Symbol.OperatorSymbol(Attr.this.names.empty, dummyMethodType(), -1, Attr.this.syms.noSymbol);
            }
            super.visitUnary(jCUnary);
        }

        @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
        public void visitVarDef(JCTree.JCVariableDecl jCVariableDecl) {
            initTypeIfNeeded(jCVariableDecl);
            if (jCVariableDecl.sym == null) {
                Symbol.VarSymbol varSymbol = new Symbol.VarSymbol(0L, jCVariableDecl.name, jCVariableDecl.type, Attr.this.syms.noSymbol);
                jCVariableDecl.sym = varSymbol;
                varSymbol.adr = 0;
            }
            if (jCVariableDecl.vartype == null) {
                jCVariableDecl.vartype = Attr.this.make.at(-1).Erroneous();
            }
            super.visitVarDef(jCVariableDecl);
        }

        private Type dummyMethodType() {
            return dummyMethodType(null);
        }
    }

    public void attribClass(JCDiagnostic.DiagnosticPosition diagnosticPosition, Symbol.ClassSymbol classSymbol) {
        try {
            this.annotate.flush();
            attribClass(classSymbol);
        } catch (Symbol.CompletionFailure e) {
            this.chk.completionError(diagnosticPosition, e);
        }
    }
}
