package com.sun.tools.javac.tree;

import com.sun.source.tree.AnnotatedTypeTree;
import com.sun.source.tree.AnnotationTree;
import com.sun.source.tree.AnyPatternTree;
import com.sun.source.tree.ArrayAccessTree;
import com.sun.source.tree.ArrayTypeTree;
import com.sun.source.tree.AssertTree;
import com.sun.source.tree.AssignmentTree;
import com.sun.source.tree.BinaryTree;
import com.sun.source.tree.BindingPatternTree;
import com.sun.source.tree.BlockTree;
import com.sun.source.tree.BreakTree;
import com.sun.source.tree.CaseLabelTree;
import com.sun.source.tree.CaseTree;
import com.sun.source.tree.CatchTree;
import com.sun.source.tree.ClassTree;
import com.sun.source.tree.CompilationUnitTree;
import com.sun.source.tree.CompoundAssignmentTree;
import com.sun.source.tree.ConditionalExpressionTree;
import com.sun.source.tree.ConstantCaseLabelTree;
import com.sun.source.tree.ContinueTree;
import com.sun.source.tree.DeconstructionPatternTree;
import com.sun.source.tree.DefaultCaseLabelTree;
import com.sun.source.tree.DirectiveTree;
import com.sun.source.tree.DoWhileLoopTree;
import com.sun.source.tree.EmptyStatementTree;
import com.sun.source.tree.EnhancedForLoopTree;
import com.sun.source.tree.ErroneousTree;
import com.sun.source.tree.ExportsTree;
import com.sun.source.tree.ExpressionStatementTree;
import com.sun.source.tree.ExpressionTree;
import com.sun.source.tree.ForLoopTree;
import com.sun.source.tree.IdentifierTree;
import com.sun.source.tree.IfTree;
import com.sun.source.tree.ImportTree;
import com.sun.source.tree.InstanceOfTree;
import com.sun.source.tree.IntersectionTypeTree;
import com.sun.source.tree.LabeledStatementTree;
import com.sun.source.tree.LambdaExpressionTree;
import com.sun.source.tree.LiteralTree;
import com.sun.source.tree.MemberReferenceTree;
import com.sun.source.tree.MemberSelectTree;
import com.sun.source.tree.MethodInvocationTree;
import com.sun.source.tree.MethodTree;
import com.sun.source.tree.ModifiersTree;
import com.sun.source.tree.ModuleTree;
import com.sun.source.tree.NewArrayTree;
import com.sun.source.tree.NewClassTree;
import com.sun.source.tree.OpensTree;
import com.sun.source.tree.PackageTree;
import com.sun.source.tree.ParameterizedTypeTree;
import com.sun.source.tree.ParenthesizedTree;
import com.sun.source.tree.PatternCaseLabelTree;
import com.sun.source.tree.PatternTree;
import com.sun.source.tree.PrimitiveTypeTree;
import com.sun.source.tree.ProvidesTree;
import com.sun.source.tree.RequiresTree;
import com.sun.source.tree.ReturnTree;
import com.sun.source.tree.StatementTree;
import com.sun.source.tree.SwitchExpressionTree;
import com.sun.source.tree.SwitchTree;
import com.sun.source.tree.SynchronizedTree;
import com.sun.source.tree.ThrowTree;
import com.sun.source.tree.Tree;
import com.sun.source.tree.TreeVisitor;
import com.sun.source.tree.TryTree;
import com.sun.source.tree.TypeCastTree;
import com.sun.source.tree.TypeParameterTree;
import com.sun.source.tree.UnaryTree;
import com.sun.source.tree.UnionTypeTree;
import com.sun.source.tree.UsesTree;
import com.sun.source.tree.VariableTree;
import com.sun.source.tree.WhileLoopTree;
import com.sun.source.tree.WildcardTree;
import com.sun.source.tree.YieldTree;
import com.sun.tools.javac.code.Attribute;
import com.sun.tools.javac.code.BoundKind;
import com.sun.tools.javac.code.Directive;
import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.code.Scope;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.code.TypeTag;
import com.sun.tools.javac.code.Types;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.util.Assert;
import com.sun.tools.javac.util.JCDiagnostic;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.ListBuffer;
import com.sun.tools.javac.util.Name;
import com.sun.tools.javac.util.Position;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeKind;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class JCTree implements Tree, Cloneable, JCDiagnostic.DiagnosticPosition {
    public int pos;
    public Type type;

    /* JADX INFO: renamed from: com.sun.tools.javac.tree.JCTree$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$javac$code$BoundKind;
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$javac$code$TypeTag;

        static {
            int[] iArr = new int[BoundKind.values().length];
            $SwitchMap$com$sun$tools$javac$code$BoundKind = iArr;
            try {
                iArr[BoundKind.UNBOUND.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$BoundKind[BoundKind.EXTENDS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$BoundKind[BoundKind.SUPER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[TypeTag.values().length];
            $SwitchMap$com$sun$tools$javac$code$TypeTag = iArr2;
            try {
                iArr2[TypeTag.BOOLEAN.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.CHAR.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public interface Factory {
        JCAnnotation Annotation(JCTree jCTree, List<JCExpression> list);

        JCMethodInvocation Apply(List<JCExpression> list, JCExpression jCExpression, List<JCExpression> list2);

        JCAssert Assert(JCExpression jCExpression, JCExpression jCExpression2);

        JCAssign Assign(JCExpression jCExpression, JCExpression jCExpression2);

        JCAssignOp Assignop(Tag tag, JCTree jCTree, JCTree jCTree2);

        JCBinary Binary(Tag tag, JCExpression jCExpression, JCExpression jCExpression2);

        JCBindingPattern BindingPattern(JCVariableDecl jCVariableDecl);

        JCBlock Block(long j, List<JCStatement> list);

        JCBreak Break(Name name);

        JCCase Case(CaseTree.CaseKind caseKind, List<JCCaseLabel> list, JCExpression jCExpression, List<JCStatement> list2, JCTree jCTree);

        JCCatch Catch(JCVariableDecl jCVariableDecl, JCBlock jCBlock);

        JCClassDecl ClassDef(JCModifiers jCModifiers, Name name, List<JCTypeParameter> list, JCExpression jCExpression, List<JCExpression> list2, List<JCTree> list3);

        JCConditional Conditional(JCExpression jCExpression, JCExpression jCExpression2, JCExpression jCExpression3);

        JCContinue Continue(Name name);

        JCDoWhileLoop DoLoop(JCStatement jCStatement, JCExpression jCExpression);

        JCErroneous Erroneous(List<? extends JCTree> list);

        JCExpressionStatement Exec(JCExpression jCExpression);

        JCExports Exports(JCExpression jCExpression, List<JCExpression> list);

        JCForLoop ForLoop(List<JCStatement> list, JCExpression jCExpression, List<JCExpressionStatement> list2, JCStatement jCStatement);

        JCEnhancedForLoop ForeachLoop(JCVariableDecl jCVariableDecl, JCExpression jCExpression, JCStatement jCStatement);

        JCIdent Ident(Name name);

        JCIf If(JCExpression jCExpression, JCStatement jCStatement, JCStatement jCStatement2);

        JCImport Import(JCFieldAccess jCFieldAccess, boolean z);

        JCArrayAccess Indexed(JCExpression jCExpression, JCExpression jCExpression2);

        JCLabeledStatement Labelled(Name name, JCStatement jCStatement);

        LetExpr LetExpr(List<JCStatement> list, JCExpression jCExpression);

        JCLiteral Literal(TypeTag typeTag, Object obj);

        JCMethodDecl MethodDef(JCModifiers jCModifiers, Name name, JCExpression jCExpression, List<JCTypeParameter> list, JCVariableDecl jCVariableDecl, List<JCVariableDecl> list2, List<JCExpression> list3, JCBlock jCBlock, JCExpression jCExpression2);

        JCModifiers Modifiers(long j, List<JCAnnotation> list);

        JCModuleDecl ModuleDef(JCModifiers jCModifiers, ModuleTree.ModuleKind moduleKind, JCExpression jCExpression, List<JCDirective> list);

        JCNewArray NewArray(JCExpression jCExpression, List<JCExpression> list, List<JCExpression> list2);

        JCNewClass NewClass(JCExpression jCExpression, List<JCExpression> list, JCExpression jCExpression2, List<JCExpression> list2, JCClassDecl jCClassDecl);

        JCOpens Opens(JCExpression jCExpression, List<JCExpression> list);

        JCPackageDecl PackageDecl(List<JCAnnotation> list, JCExpression jCExpression);

        JCParens Parens(JCExpression jCExpression);

        JCProvides Provides(JCExpression jCExpression, List<JCExpression> list);

        JCRequires Requires(boolean z, boolean z2, JCExpression jCExpression);

        JCReturn Return(JCExpression jCExpression);

        JCFieldAccess Select(JCExpression jCExpression, Name name);

        JCSkip Skip();

        JCSwitch Switch(JCExpression jCExpression, List<JCCase> list);

        JCSwitchExpression SwitchExpression(JCExpression jCExpression, List<JCCase> list);

        JCSynchronized Synchronized(JCExpression jCExpression, JCBlock jCBlock);

        JCThrow Throw(JCExpression jCExpression);

        JCCompilationUnit TopLevel(List<JCTree> list);

        JCTry Try(JCBlock jCBlock, List<JCCatch> list, JCBlock jCBlock2);

        JCTry Try(List<JCTree> list, JCBlock jCBlock, List<JCCatch> list2, JCBlock jCBlock2);

        JCTypeApply TypeApply(JCExpression jCExpression, List<JCExpression> list);

        JCArrayTypeTree TypeArray(JCExpression jCExpression);

        TypeBoundKind TypeBoundKind(BoundKind boundKind);

        JCTypeCast TypeCast(JCTree jCTree, JCExpression jCExpression);

        JCPrimitiveTypeTree TypeIdent(TypeTag typeTag);

        JCTypeParameter TypeParameter(Name name, List<JCExpression> list);

        JCInstanceOf TypeTest(JCExpression jCExpression, JCTree jCTree);

        JCUnary Unary(Tag tag, JCExpression jCExpression);

        JCUses Uses(JCExpression jCExpression);

        JCVariableDecl VarDef(JCModifiers jCModifiers, Name name, JCExpression jCExpression, JCExpression jCExpression2);

        JCWhileLoop WhileLoop(JCExpression jCExpression, JCStatement jCStatement);

        JCWildcard Wildcard(TypeBoundKind typeBoundKind, JCTree jCTree);

        JCYield Yield(JCExpression jCExpression);
    }

    public static abstract class JCCaseLabel extends JCTree implements CaseLabelTree {
    }

    public static abstract class JCDirective extends JCTree implements DirectiveTree {
    }

    public static abstract class JCFunctionalExpression extends JCPolyExpression {
        public Symbol owner;
        public Type target;

        public JCFunctionalExpression() {
            this.polyKind = JCPolyExpression.PolyKind.POLY;
        }

        public Type getDescriptorType(Types types) {
            Type type = this.target;
            return type != null ? types.findDescriptorType(type) : types.createErrorType(null);
        }
    }

    public static abstract class JCImportBase extends JCTree implements ImportTree {
        @Override // com.sun.tools.javac.tree.JCTree, com.sun.source.tree.Tree
        public <R, D> R accept(TreeVisitor<R, D> treeVisitor, D d) {
            return treeVisitor.visitImport(this, d);
        }

        @Override // com.sun.source.tree.Tree
        public Tree.Kind getKind() {
            return Tree.Kind.IMPORT;
        }

        @Override // com.sun.source.tree.ImportTree
        public abstract JCTree getQualifiedIdentifier();
    }

    public static abstract class JCOperatorExpression extends JCExpression {
        protected Tag opcode;
        public Symbol.OperatorSymbol operator;

        public enum OperandPos {
            LEFT,
            RIGHT
        }

        public abstract JCExpression getOperand(OperandPos operandPos);

        public Symbol.OperatorSymbol getOperator() {
            return this.operator;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public Tag getTag() {
            return this.opcode;
        }
    }

    public static abstract class JCPattern extends JCTree implements PatternTree {
    }

    public static abstract class JCPolyExpression extends JCExpression {
        public PolyKind polyKind;

        public enum PolyKind {
            STANDALONE,
            POLY
        }

        @Override // com.sun.tools.javac.tree.JCTree.JCExpression
        public boolean isPoly() {
            return this.polyKind == PolyKind.POLY;
        }

        @Override // com.sun.tools.javac.tree.JCTree.JCExpression
        public boolean isStandalone() {
            return this.polyKind == PolyKind.STANDALONE;
        }
    }

    public static abstract class Visitor {
        public void visitAnnotatedType(JCAnnotatedType jCAnnotatedType) {
            visitTree(jCAnnotatedType);
        }

        public void visitAnnotation(JCAnnotation jCAnnotation) {
            visitTree(jCAnnotation);
        }

        public void visitAnyPattern(JCAnyPattern jCAnyPattern) {
            visitTree(jCAnyPattern);
        }

        public void visitApply(JCMethodInvocation jCMethodInvocation) {
            visitTree(jCMethodInvocation);
        }

        public void visitAssert(JCAssert jCAssert) {
            visitTree(jCAssert);
        }

        public void visitAssign(JCAssign jCAssign) {
            visitTree(jCAssign);
        }

        public void visitAssignop(JCAssignOp jCAssignOp) {
            visitTree(jCAssignOp);
        }

        public void visitBinary(JCBinary jCBinary) {
            visitTree(jCBinary);
        }

        public void visitBindingPattern(JCBindingPattern jCBindingPattern) {
            visitTree(jCBindingPattern);
        }

        public void visitBlock(JCBlock jCBlock) {
            visitTree(jCBlock);
        }

        public void visitBreak(JCBreak jCBreak) {
            visitTree(jCBreak);
        }

        public void visitCase(JCCase jCCase) {
            visitTree(jCCase);
        }

        public void visitCatch(JCCatch jCCatch) {
            visitTree(jCCatch);
        }

        public void visitClassDef(JCClassDecl jCClassDecl) {
            visitTree(jCClassDecl);
        }

        public void visitConditional(JCConditional jCConditional) {
            visitTree(jCConditional);
        }

        public void visitConstantCaseLabel(JCConstantCaseLabel jCConstantCaseLabel) {
            visitTree(jCConstantCaseLabel);
        }

        public void visitContinue(JCContinue jCContinue) {
            visitTree(jCContinue);
        }

        public void visitDefaultCaseLabel(JCDefaultCaseLabel jCDefaultCaseLabel) {
            visitTree(jCDefaultCaseLabel);
        }

        public void visitDoLoop(JCDoWhileLoop jCDoWhileLoop) {
            visitTree(jCDoWhileLoop);
        }

        public void visitErroneous(JCErroneous jCErroneous) {
            visitTree(jCErroneous);
        }

        public void visitExec(JCExpressionStatement jCExpressionStatement) {
            visitTree(jCExpressionStatement);
        }

        public void visitExports(JCExports jCExports) {
            visitTree(jCExports);
        }

        public void visitForLoop(JCForLoop jCForLoop) {
            visitTree(jCForLoop);
        }

        public void visitForeachLoop(JCEnhancedForLoop jCEnhancedForLoop) {
            visitTree(jCEnhancedForLoop);
        }

        public void visitIdent(JCIdent jCIdent) {
            visitTree(jCIdent);
        }

        public void visitIf(JCIf jCIf) {
            visitTree(jCIf);
        }

        public void visitImport(JCImport jCImport) {
            visitTree(jCImport);
        }

        public void visitIndexed(JCArrayAccess jCArrayAccess) {
            visitTree(jCArrayAccess);
        }

        public void visitLabelled(JCLabeledStatement jCLabeledStatement) {
            visitTree(jCLabeledStatement);
        }

        public void visitLambda(JCLambda jCLambda) {
            visitTree(jCLambda);
        }

        public void visitLetExpr(LetExpr letExpr) {
            visitTree(letExpr);
        }

        public void visitLiteral(JCLiteral jCLiteral) {
            visitTree(jCLiteral);
        }

        public void visitMethodDef(JCMethodDecl jCMethodDecl) {
            visitTree(jCMethodDecl);
        }

        public void visitModifiers(JCModifiers jCModifiers) {
            visitTree(jCModifiers);
        }

        public void visitModuleDef(JCModuleDecl jCModuleDecl) {
            visitTree(jCModuleDecl);
        }

        public void visitModuleImport(JCModuleImport jCModuleImport) {
            visitTree(jCModuleImport);
        }

        public void visitNewArray(JCNewArray jCNewArray) {
            visitTree(jCNewArray);
        }

        public void visitNewClass(JCNewClass jCNewClass) {
            visitTree(jCNewClass);
        }

        public void visitOpens(JCOpens jCOpens) {
            visitTree(jCOpens);
        }

        public void visitPackageDef(JCPackageDecl jCPackageDecl) {
            visitTree(jCPackageDecl);
        }

        public void visitParens(JCParens jCParens) {
            visitTree(jCParens);
        }

        public void visitPatternCaseLabel(JCPatternCaseLabel jCPatternCaseLabel) {
            visitTree(jCPatternCaseLabel);
        }

        public void visitProvides(JCProvides jCProvides) {
            visitTree(jCProvides);
        }

        public void visitRecordPattern(JCRecordPattern jCRecordPattern) {
            visitTree(jCRecordPattern);
        }

        public void visitReference(JCMemberReference jCMemberReference) {
            visitTree(jCMemberReference);
        }

        public void visitRequires(JCRequires jCRequires) {
            visitTree(jCRequires);
        }

        public void visitReturn(JCReturn jCReturn) {
            visitTree(jCReturn);
        }

        public void visitSelect(JCFieldAccess jCFieldAccess) {
            visitTree(jCFieldAccess);
        }

        public void visitSkip(JCSkip jCSkip) {
            visitTree(jCSkip);
        }

        public void visitSwitch(JCSwitch jCSwitch) {
            visitTree(jCSwitch);
        }

        public void visitSwitchExpression(JCSwitchExpression jCSwitchExpression) {
            visitTree(jCSwitchExpression);
        }

        public void visitSynchronized(JCSynchronized jCSynchronized) {
            visitTree(jCSynchronized);
        }

        public void visitThrow(JCThrow jCThrow) {
            visitTree(jCThrow);
        }

        public void visitTopLevel(JCCompilationUnit jCCompilationUnit) {
            visitTree(jCCompilationUnit);
        }

        public void visitTree(JCTree jCTree) {
            Assert.error();
        }

        public void visitTry(JCTry jCTry) {
            visitTree(jCTry);
        }

        public void visitTypeApply(JCTypeApply jCTypeApply) {
            visitTree(jCTypeApply);
        }

        public void visitTypeArray(JCArrayTypeTree jCArrayTypeTree) {
            visitTree(jCArrayTypeTree);
        }

        public void visitTypeBoundKind(TypeBoundKind typeBoundKind) {
            visitTree(typeBoundKind);
        }

        public void visitTypeCast(JCTypeCast jCTypeCast) {
            visitTree(jCTypeCast);
        }

        public void visitTypeIdent(JCPrimitiveTypeTree jCPrimitiveTypeTree) {
            visitTree(jCPrimitiveTypeTree);
        }

        public void visitTypeIntersection(JCTypeIntersection jCTypeIntersection) {
            visitTree(jCTypeIntersection);
        }

        public void visitTypeParameter(JCTypeParameter jCTypeParameter) {
            visitTree(jCTypeParameter);
        }

        public void visitTypeTest(JCInstanceOf jCInstanceOf) {
            visitTree(jCInstanceOf);
        }

        public void visitTypeUnion(JCTypeUnion jCTypeUnion) {
            visitTree(jCTypeUnion);
        }

        public void visitUnary(JCUnary jCUnary) {
            visitTree(jCUnary);
        }

        public void visitUses(JCUses jCUses) {
            visitTree(jCUses);
        }

        public void visitVarDef(JCVariableDecl jCVariableDecl) {
            visitTree(jCVariableDecl);
        }

        public void visitWhileLoop(JCWhileLoop jCWhileLoop) {
            visitTree(jCWhileLoop);
        }

        public void visitWildcard(JCWildcard jCWildcard) {
            visitTree(jCWildcard);
        }

        public void visitYield(JCYield jCYield) {
            visitTree(jCYield);
        }
    }

    private int noNoPos(int i) {
        return ((long) i) == -1 ? this.pos : i;
    }

    @Override // com.sun.source.tree.Tree
    public abstract <R, D> R accept(TreeVisitor<R, D> treeVisitor, D d);

    public abstract void accept(Visitor visitor);

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            rc6.a(e);
            return null;
        }
    }

    @Override // com.sun.tools.javac.util.JCDiagnostic.DiagnosticPosition
    public int getEndPosition(EndPosTable endPosTable) {
        return noNoPos(TreeInfo.getEndPos(this, endPosTable));
    }

    @Override // com.sun.tools.javac.util.JCDiagnostic.DiagnosticPosition
    public int getPreferredPosition() {
        return this.pos;
    }

    @Override // com.sun.tools.javac.util.JCDiagnostic.DiagnosticPosition
    public int getStartPosition() {
        return noNoPos(TreeInfo.getStartPos(this));
    }

    public abstract Tag getTag();

    @Override // com.sun.tools.javac.util.JCDiagnostic.DiagnosticPosition
    public JCTree getTree() {
        return this;
    }

    public boolean hasTag(Tag tag) {
        return tag == getTag();
    }

    public JCDiagnostic.DiagnosticPosition pos() {
        return this;
    }

    public JCTree setPos(int i) {
        this.pos = i;
        return this;
    }

    public JCTree setType(Type type) {
        this.type = type;
        return this;
    }

    public String toString() {
        StringWriter stringWriter = new StringWriter();
        try {
            new Pretty(stringWriter, false).printExpr(this);
            return stringWriter.toString();
        } catch (IOException e) {
            x01.a(e);
            return null;
        }
    }

    public static class JCAnnotatedType extends JCExpression implements AnnotatedTypeTree {
        public List<JCAnnotation> annotations;
        public JCExpression underlyingType;

        public JCAnnotatedType(List<JCAnnotation> list, JCExpression jCExpression) {
            Assert.check(list != null && list.nonEmpty());
            this.annotations = list;
            this.underlyingType = jCExpression;
        }

        @Override // com.sun.tools.javac.tree.JCTree, com.sun.source.tree.Tree
        public <R, D> R accept(TreeVisitor<R, D> treeVisitor, D d) {
            return treeVisitor.visitAnnotatedType(this, d);
        }

        @Override // com.sun.source.tree.Tree
        public Tree.Kind getKind() {
            return Tree.Kind.ANNOTATED_TYPE;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public Tag getTag() {
            return Tag.ANNOTATED_TYPE;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public void accept(Visitor visitor) {
            visitor.visitAnnotatedType(this);
        }

        @Override // com.sun.source.tree.AnnotatedTypeTree
        public List<JCAnnotation> getAnnotations() {
            return this.annotations;
        }

        @Override // com.sun.source.tree.AnnotatedTypeTree
        public JCExpression getUnderlyingType() {
            return this.underlyingType;
        }
    }

    public static class JCAnnotation extends JCExpression implements AnnotationTree {
        public JCTree annotationType;
        public List<JCExpression> args;
        public Attribute.Compound attribute;
        private Tag tag;

        public JCAnnotation(Tag tag, JCTree jCTree, List<JCExpression> list) {
            this.tag = tag;
            this.annotationType = jCTree;
            this.args = list;
        }

        @Override // com.sun.tools.javac.tree.JCTree, com.sun.source.tree.Tree
        public <R, D> R accept(TreeVisitor<R, D> treeVisitor, D d) {
            return treeVisitor.visitAnnotation(this, d);
        }

        @Override // com.sun.source.tree.Tree
        public Tree.Kind getKind() {
            return TreeInfo.tagToKind(getTag());
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public Tag getTag() {
            return this.tag;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public void accept(Visitor visitor) {
            visitor.visitAnnotation(this);
        }

        @Override // com.sun.source.tree.AnnotationTree
        public JCTree getAnnotationType() {
            return this.annotationType;
        }

        @Override // com.sun.source.tree.AnnotationTree
        public List<JCExpression> getArguments() {
            return this.args;
        }
    }

    public static class JCAnyPattern extends JCPattern implements AnyPatternTree {
        @Override // com.sun.tools.javac.tree.JCTree, com.sun.source.tree.Tree
        public <R, D> R accept(TreeVisitor<R, D> treeVisitor, D d) {
            return treeVisitor.visitAnyPattern(this, d);
        }

        @Override // com.sun.source.tree.Tree
        public Tree.Kind getKind() {
            return Tree.Kind.ANY_PATTERN;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public Tag getTag() {
            return Tag.ANYPATTERN;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public void accept(Visitor visitor) {
            visitor.visitAnyPattern(this);
        }
    }

    public static class JCArrayAccess extends JCExpression implements ArrayAccessTree {
        public JCExpression index;
        public JCExpression indexed;

        public JCArrayAccess(JCExpression jCExpression, JCExpression jCExpression2) {
            this.indexed = jCExpression;
            this.index = jCExpression2;
        }

        @Override // com.sun.tools.javac.tree.JCTree, com.sun.source.tree.Tree
        public <R, D> R accept(TreeVisitor<R, D> treeVisitor, D d) {
            return treeVisitor.visitArrayAccess(this, d);
        }

        @Override // com.sun.source.tree.Tree
        public Tree.Kind getKind() {
            return Tree.Kind.ARRAY_ACCESS;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public Tag getTag() {
            return Tag.INDEXED;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public void accept(Visitor visitor) {
            visitor.visitIndexed(this);
        }

        @Override // com.sun.source.tree.ArrayAccessTree
        public JCExpression getExpression() {
            return this.indexed;
        }

        @Override // com.sun.source.tree.ArrayAccessTree
        public JCExpression getIndex() {
            return this.index;
        }
    }

    public static class JCArrayTypeTree extends JCExpression implements ArrayTypeTree {
        public JCExpression elemtype;

        public JCArrayTypeTree(JCExpression jCExpression) {
            this.elemtype = jCExpression;
        }

        @Override // com.sun.tools.javac.tree.JCTree, com.sun.source.tree.Tree
        public <R, D> R accept(TreeVisitor<R, D> treeVisitor, D d) {
            return treeVisitor.visitArrayType(this, d);
        }

        @Override // com.sun.source.tree.Tree
        public Tree.Kind getKind() {
            return Tree.Kind.ARRAY_TYPE;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public Tag getTag() {
            return Tag.TYPEARRAY;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public void accept(Visitor visitor) {
            visitor.visitTypeArray(this);
        }

        @Override // com.sun.source.tree.ArrayTypeTree
        public JCTree getType() {
            return this.elemtype;
        }
    }

    public static class JCAssert extends JCStatement implements AssertTree {
        public JCExpression cond;
        public JCExpression detail;

        public JCAssert(JCExpression jCExpression, JCExpression jCExpression2) {
            this.cond = jCExpression;
            this.detail = jCExpression2;
        }

        @Override // com.sun.tools.javac.tree.JCTree, com.sun.source.tree.Tree
        public <R, D> R accept(TreeVisitor<R, D> treeVisitor, D d) {
            return treeVisitor.visitAssert(this, d);
        }

        @Override // com.sun.source.tree.Tree
        public Tree.Kind getKind() {
            return Tree.Kind.ASSERT;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public Tag getTag() {
            return Tag.ASSERT;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public void accept(Visitor visitor) {
            visitor.visitAssert(this);
        }

        @Override // com.sun.source.tree.AssertTree
        public JCExpression getCondition() {
            return this.cond;
        }

        @Override // com.sun.source.tree.AssertTree
        public JCExpression getDetail() {
            return this.detail;
        }
    }

    public static class JCAssign extends JCExpression implements AssignmentTree {
        public JCExpression lhs;
        public JCExpression rhs;

        public JCAssign(JCExpression jCExpression, JCExpression jCExpression2) {
            this.lhs = jCExpression;
            this.rhs = jCExpression2;
        }

        @Override // com.sun.tools.javac.tree.JCTree, com.sun.source.tree.Tree
        public <R, D> R accept(TreeVisitor<R, D> treeVisitor, D d) {
            return treeVisitor.visitAssignment(this, d);
        }

        @Override // com.sun.source.tree.Tree
        public Tree.Kind getKind() {
            return Tree.Kind.ASSIGNMENT;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public Tag getTag() {
            return Tag.ASSIGN;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public void accept(Visitor visitor) {
            visitor.visitAssign(this);
        }

        @Override // com.sun.source.tree.AssignmentTree
        public JCExpression getExpression() {
            return this.rhs;
        }

        @Override // com.sun.source.tree.AssignmentTree
        public JCExpression getVariable() {
            return this.lhs;
        }
    }

    public static class JCAssignOp extends JCOperatorExpression implements CompoundAssignmentTree {
        public JCExpression lhs;
        public JCExpression rhs;

        public JCAssignOp(Tag tag, JCTree jCTree, JCTree jCTree2, Symbol.OperatorSymbol operatorSymbol) {
            this.opcode = tag;
            this.lhs = (JCExpression) jCTree;
            this.rhs = (JCExpression) jCTree2;
            this.operator = operatorSymbol;
        }

        @Override // com.sun.tools.javac.tree.JCTree, com.sun.source.tree.Tree
        public <R, D> R accept(TreeVisitor<R, D> treeVisitor, D d) {
            return treeVisitor.visitCompoundAssignment(this, d);
        }

        @Override // com.sun.source.tree.Tree
        public Tree.Kind getKind() {
            return TreeInfo.tagToKind(getTag());
        }

        @Override // com.sun.tools.javac.tree.JCTree.JCOperatorExpression
        public JCExpression getOperand(JCOperatorExpression.OperandPos operandPos) {
            return operandPos == JCOperatorExpression.OperandPos.LEFT ? this.lhs : this.rhs;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public void accept(Visitor visitor) {
            visitor.visitAssignop(this);
        }

        @Override // com.sun.source.tree.CompoundAssignmentTree
        public JCExpression getExpression() {
            return this.rhs;
        }

        @Override // com.sun.source.tree.CompoundAssignmentTree
        public JCExpression getVariable() {
            return this.lhs;
        }
    }

    public static class JCBinary extends JCOperatorExpression implements BinaryTree {
        public JCExpression lhs;
        public JCExpression rhs;

        public JCBinary(Tag tag, JCExpression jCExpression, JCExpression jCExpression2, Symbol.OperatorSymbol operatorSymbol) {
            this.opcode = tag;
            this.lhs = jCExpression;
            this.rhs = jCExpression2;
            this.operator = operatorSymbol;
        }

        @Override // com.sun.tools.javac.tree.JCTree, com.sun.source.tree.Tree
        public <R, D> R accept(TreeVisitor<R, D> treeVisitor, D d) {
            return treeVisitor.visitBinary(this, d);
        }

        @Override // com.sun.source.tree.Tree
        public Tree.Kind getKind() {
            return TreeInfo.tagToKind(getTag());
        }

        @Override // com.sun.tools.javac.tree.JCTree.JCOperatorExpression
        public JCExpression getOperand(JCOperatorExpression.OperandPos operandPos) {
            return operandPos == JCOperatorExpression.OperandPos.LEFT ? this.lhs : this.rhs;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public void accept(Visitor visitor) {
            visitor.visitBinary(this);
        }

        @Override // com.sun.source.tree.BinaryTree
        public JCExpression getLeftOperand() {
            return this.lhs;
        }

        @Override // com.sun.source.tree.BinaryTree
        public JCExpression getRightOperand() {
            return this.rhs;
        }
    }

    public static class JCBindingPattern extends JCPattern implements BindingPatternTree {
        public JCVariableDecl var;

        public JCBindingPattern(JCVariableDecl jCVariableDecl) {
            this.var = jCVariableDecl;
        }

        @Override // com.sun.tools.javac.tree.JCTree, com.sun.source.tree.Tree
        public <R, D> R accept(TreeVisitor<R, D> treeVisitor, D d) {
            return treeVisitor.visitBindingPattern(this, d);
        }

        @Override // com.sun.source.tree.Tree
        public Tree.Kind getKind() {
            return Tree.Kind.BINDING_PATTERN;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public Tag getTag() {
            return Tag.BINDINGPATTERN;
        }

        @Override // com.sun.source.tree.BindingPatternTree
        public VariableTree getVariable() {
            return this.var;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public void accept(Visitor visitor) {
            visitor.visitBindingPattern(this);
        }
    }

    public static class JCBlock extends JCStatement implements BlockTree {
        public int bracePos = -1;
        public long flags;
        public PatternMatchingCatch patternMatchingCatch;
        public List<JCStatement> stats;

        public static final class PatternMatchingCatch {
            private final Set<JCMethodInvocation> calls2Handle;
            private final JCCatch handler;

            public PatternMatchingCatch(JCCatch jCCatch, Set<JCMethodInvocation> set) {
                this.handler = jCCatch;
                this.calls2Handle = set;
            }

            public Set<JCMethodInvocation> calls2Handle() {
                return this.calls2Handle;
            }

            public final boolean equals(Object obj) {
                if (!(obj instanceof PatternMatchingCatch)) {
                    return false;
                }
                PatternMatchingCatch patternMatchingCatch = (PatternMatchingCatch) obj;
                return Objects.equals(this.calls2Handle, patternMatchingCatch.calls2Handle) && Objects.equals(this.handler, patternMatchingCatch.handler);
            }

            public JCCatch handler() {
                return this.handler;
            }

            public final int hashCode() {
                return (Objects.hashCode(this.handler) * 31) + Objects.hashCode(this.calls2Handle);
            }

            public final String toString() {
                return "PatternMatchingCatch[handler=" + Objects.toString(this.handler) + ", calls2Handle=" + Objects.toString(this.calls2Handle) + "]";
            }
        }

        public JCBlock(long j, List<JCStatement> list) {
            this.stats = list;
            this.flags = j;
        }

        @Override // com.sun.tools.javac.tree.JCTree, com.sun.source.tree.Tree
        public <R, D> R accept(TreeVisitor<R, D> treeVisitor, D d) {
            return treeVisitor.visitBlock(this, d);
        }

        @Override // com.sun.source.tree.Tree
        public Tree.Kind getKind() {
            return Tree.Kind.BLOCK;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public Tag getTag() {
            return Tag.BLOCK;
        }

        @Override // com.sun.source.tree.BlockTree
        public boolean isStatic() {
            return (this.flags & 8) != 0;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public void accept(Visitor visitor) {
            visitor.visitBlock(this);
        }

        @Override // com.sun.source.tree.BlockTree
        public List<JCStatement> getStatements() {
            return this.stats;
        }
    }

    public static class JCBreak extends JCStatement implements BreakTree {
        public Name label;
        public JCTree target;

        public JCBreak(Name name, JCTree jCTree) {
            this.label = name;
            this.target = jCTree;
        }

        @Override // com.sun.tools.javac.tree.JCTree, com.sun.source.tree.Tree
        public <R, D> R accept(TreeVisitor<R, D> treeVisitor, D d) {
            return treeVisitor.visitBreak(this, d);
        }

        @Override // com.sun.source.tree.Tree
        public Tree.Kind getKind() {
            return Tree.Kind.BREAK;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public Tag getTag() {
            return Tag.BREAK;
        }

        public boolean isValueBreak() {
            JCTree jCTree = this.target;
            return jCTree != null && jCTree.hasTag(Tag.SWITCH_EXPRESSION);
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public void accept(Visitor visitor) {
            visitor.visitBreak(this);
        }

        @Override // com.sun.source.tree.BreakTree
        public Name getLabel() {
            return this.label;
        }
    }

    public static class JCCase extends JCStatement implements CaseTree {
        public JCTree body;
        public final CaseTree.CaseKind caseKind;
        public boolean completesNormally;
        public JCExpression guard;
        public List<JCCaseLabel> labels;
        public List<JCStatement> stats;
        public static final CaseTree.CaseKind STATEMENT = CaseTree.CaseKind.STATEMENT;
        public static final CaseTree.CaseKind RULE = CaseTree.CaseKind.RULE;

        public JCCase(CaseTree.CaseKind caseKind, List<JCCaseLabel> list, JCExpression jCExpression, List<JCStatement> list2, JCTree jCTree) {
            Assert.checkNonNull(list);
            Assert.check(list.isEmpty() || list.head != null);
            this.caseKind = caseKind;
            this.labels = list;
            this.guard = jCExpression;
            this.stats = list2;
            this.body = jCTree;
        }

        @Override // com.sun.tools.javac.tree.JCTree, com.sun.source.tree.Tree
        public <R, D> R accept(TreeVisitor<R, D> treeVisitor, D d) {
            return treeVisitor.visitCase(this, d);
        }

        @Override // com.sun.source.tree.CaseTree
        public CaseTree.CaseKind getCaseKind() {
            return this.caseKind;
        }

        @Override // com.sun.source.tree.CaseTree
        @Deprecated
        public JCExpression getExpression() {
            return getExpressions().head;
        }

        @Override // com.sun.source.tree.CaseTree
        public List<JCExpression> getExpressions() {
            return (List) this.labels.stream().filter(new Predicate() { // from class: fa7
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ((JCTree.JCCaseLabel) obj).hasTag(JCTree.Tag.CONSTANTCASELABEL);
                }
            }).map(new Function() { // from class: ga7
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((JCTree.JCConstantCaseLabel) ((JCTree.JCCaseLabel) obj)).expr;
                }
            }).collect(List.collector());
        }

        @Override // com.sun.source.tree.Tree
        public Tree.Kind getKind() {
            return Tree.Kind.CASE;
        }

        @Override // com.sun.source.tree.CaseTree
        public List<JCStatement> getStatements() {
            if (this.caseKind == CaseTree.CaseKind.STATEMENT) {
                return this.stats;
            }
            return null;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public Tag getTag() {
            return Tag.CASE;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public void accept(Visitor visitor) {
            visitor.visitCase(this);
        }

        @Override // com.sun.source.tree.CaseTree
        public JCTree getBody() {
            return this.body;
        }

        @Override // com.sun.source.tree.CaseTree
        public JCExpression getGuard() {
            return this.guard;
        }

        @Override // com.sun.source.tree.CaseTree
        public List<JCCaseLabel> getLabels() {
            return this.labels;
        }
    }

    public static class JCCatch extends JCTree implements CatchTree {
        public JCBlock body;
        public JCVariableDecl param;

        public JCCatch(JCVariableDecl jCVariableDecl, JCBlock jCBlock) {
            this.param = jCVariableDecl;
            this.body = jCBlock;
        }

        @Override // com.sun.tools.javac.tree.JCTree, com.sun.source.tree.Tree
        public <R, D> R accept(TreeVisitor<R, D> treeVisitor, D d) {
            return treeVisitor.visitCatch(this, d);
        }

        @Override // com.sun.source.tree.Tree
        public Tree.Kind getKind() {
            return Tree.Kind.CATCH;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public Tag getTag() {
            return Tag.CATCH;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public void accept(Visitor visitor) {
            visitor.visitCatch(this);
        }

        @Override // com.sun.source.tree.CatchTree
        public JCBlock getBlock() {
            return this.body;
        }

        @Override // com.sun.source.tree.CatchTree
        public JCVariableDecl getParameter() {
            return this.param;
        }
    }

    public static class JCClassDecl extends JCStatement implements ClassTree {
        public List<JCTree> defs;
        public JCExpression extending;
        public List<JCExpression> implementing;
        public JCModifiers mods;
        public Name name;
        public List<JCExpression> permitting;
        public Symbol.ClassSymbol sym;
        public List<JCTypeParameter> typarams;

        public JCClassDecl(JCModifiers jCModifiers, Name name, List<JCTypeParameter> list, JCExpression jCExpression, List<JCExpression> list2, List<JCExpression> list3, List<JCTree> list4, Symbol.ClassSymbol classSymbol) {
            this.mods = jCModifiers;
            this.name = name;
            this.typarams = list;
            this.extending = jCExpression;
            this.implementing = list2;
            this.permitting = list3;
            this.defs = list4;
            this.sym = classSymbol;
        }

        @Override // com.sun.tools.javac.tree.JCTree, com.sun.source.tree.Tree
        public <R, D> R accept(TreeVisitor<R, D> treeVisitor, D d) {
            return treeVisitor.visitClass(this, d);
        }

        @Override // com.sun.source.tree.Tree
        public Tree.Kind getKind() {
            long j = this.mods.flags;
            if ((8192 & j) != 0) {
                return Tree.Kind.ANNOTATION_TYPE;
            }
            if ((512 & j) != 0) {
                return Tree.Kind.INTERFACE;
            }
            if ((16384 & j) != 0) {
                return Tree.Kind.ENUM;
            }
            return (j & Flags.RECORD) != 0 ? Tree.Kind.RECORD : Tree.Kind.CLASS;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public Tag getTag() {
            return Tag.CLASSDEF;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public void accept(Visitor visitor) {
            visitor.visitClassDef(this);
        }

        @Override // com.sun.source.tree.ClassTree
        public JCExpression getExtendsClause() {
            return this.extending;
        }

        @Override // com.sun.source.tree.ClassTree
        public List<JCExpression> getImplementsClause() {
            return this.implementing;
        }

        @Override // com.sun.source.tree.ClassTree
        public List<JCTree> getMembers() {
            return this.defs;
        }

        @Override // com.sun.source.tree.ClassTree
        public JCModifiers getModifiers() {
            return this.mods;
        }

        @Override // com.sun.source.tree.ClassTree
        public List<JCExpression> getPermitsClause() {
            return this.permitting;
        }

        @Override // com.sun.source.tree.ClassTree
        public Name getSimpleName() {
            return this.name;
        }

        @Override // com.sun.source.tree.ClassTree
        public List<JCTypeParameter> getTypeParameters() {
            return this.typarams;
        }
    }

    public static class JCCompilationUnit extends JCTree implements CompilationUnitTree {
        public List<JCTree> defs;
        public JavaFileManager.Location locn;
        public Symbol.ModuleSymbol modle;
        public Scope.StarImportScope moduleImportScope;
        public Scope.NamedImportScope namedImportScope;
        public Symbol.PackageSymbol packge;
        public JavaFileObject sourcefile;
        public Scope.StarImportScope starImportScope;
        public Scope.WriteableScope toplevelScope;
        public Position.LineMap lineMap = null;
        public DocCommentTable docComments = null;
        public EndPosTable endPositions = null;

        public JCCompilationUnit(List<JCTree> list) {
            this.defs = list;
        }

        @Override // com.sun.tools.javac.tree.JCTree, com.sun.source.tree.Tree
        public <R, D> R accept(TreeVisitor<R, D> treeVisitor, D d) {
            return treeVisitor.visitCompilationUnit(this, d);
        }

        @Override // com.sun.source.tree.CompilationUnitTree
        public List<JCImportBase> getImports() {
            ListBuffer listBuffer = new ListBuffer();
            for (JCTree jCTree : this.defs) {
                if (!(jCTree instanceof JCImportBase)) {
                    if (!jCTree.hasTag(Tag.PACKAGEDEF) && !jCTree.hasTag(Tag.SKIP)) {
                        break;
                    }
                } else {
                    listBuffer.append((JCImportBase) jCTree);
                }
            }
            return listBuffer.toList();
        }

        @Override // com.sun.source.tree.Tree
        public Tree.Kind getKind() {
            return Tree.Kind.COMPILATION_UNIT;
        }

        public JCModuleDecl getModuleDecl() {
            for (JCTree jCTree : this.defs) {
                if (jCTree.hasTag(Tag.MODULEDEF)) {
                    return (JCModuleDecl) jCTree;
                }
            }
            return null;
        }

        @Override // com.sun.source.tree.CompilationUnitTree
        public JCPackageDecl getPackage() {
            if (this.defs.isEmpty() || !this.defs.head.hasTag(Tag.PACKAGEDEF)) {
                return null;
            }
            return (JCPackageDecl) this.defs.head;
        }

        @Override // com.sun.source.tree.CompilationUnitTree
        public List<JCAnnotation> getPackageAnnotations() {
            JCPackageDecl jCPackageDecl = getPackage();
            return jCPackageDecl != null ? jCPackageDecl.getAnnotations() : List.nil();
        }

        @Override // com.sun.source.tree.CompilationUnitTree
        public ExpressionTree getPackageName() {
            JCPackageDecl jCPackageDecl = getPackage();
            if (jCPackageDecl != null) {
                return jCPackageDecl.getPackageName();
            }
            return null;
        }

        @Override // com.sun.source.tree.CompilationUnitTree
        public JavaFileObject getSourceFile() {
            return this.sourcefile;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public Tag getTag() {
            return Tag.TOPLEVEL;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.sun.source.tree.CompilationUnitTree
        public List<JCTree> getTypeDecls() {
            List list = this.defs;
            while (!list.isEmpty() && (((JCTree) list.head).hasTag(Tag.MODULEDEF) || ((JCTree) list.head).hasTag(Tag.PACKAGEDEF) || ((JCTree) list.head).hasTag(Tag.IMPORT) || ((JCTree) list.head).hasTag(Tag.MODULEIMPORT))) {
                list = list.tail;
            }
            return list;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public void accept(Visitor visitor) {
            visitor.visitTopLevel(this);
        }

        @Override // com.sun.source.tree.CompilationUnitTree
        public Position.LineMap getLineMap() {
            return this.lineMap;
        }

        @Override // com.sun.source.tree.CompilationUnitTree
        public JCModuleDecl getModule() {
            return getModuleDecl();
        }
    }

    public static class JCConditional extends JCPolyExpression implements ConditionalExpressionTree {
        public JCExpression cond;
        public JCExpression falsepart;
        public JCExpression truepart;

        public JCConditional(JCExpression jCExpression, JCExpression jCExpression2, JCExpression jCExpression3) {
            this.cond = jCExpression;
            this.truepart = jCExpression2;
            this.falsepart = jCExpression3;
        }

        @Override // com.sun.tools.javac.tree.JCTree, com.sun.source.tree.Tree
        public <R, D> R accept(TreeVisitor<R, D> treeVisitor, D d) {
            return treeVisitor.visitConditionalExpression(this, d);
        }

        @Override // com.sun.source.tree.Tree
        public Tree.Kind getKind() {
            return Tree.Kind.CONDITIONAL_EXPRESSION;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public Tag getTag() {
            return Tag.CONDEXPR;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public void accept(Visitor visitor) {
            visitor.visitConditional(this);
        }

        @Override // com.sun.source.tree.ConditionalExpressionTree
        public JCExpression getCondition() {
            return this.cond;
        }

        @Override // com.sun.source.tree.ConditionalExpressionTree
        public JCExpression getFalseExpression() {
            return this.falsepart;
        }

        @Override // com.sun.source.tree.ConditionalExpressionTree
        public JCExpression getTrueExpression() {
            return this.truepart;
        }
    }

    public static class JCConstantCaseLabel extends JCCaseLabel implements ConstantCaseLabelTree {
        public JCExpression expr;

        public JCConstantCaseLabel(JCExpression jCExpression) {
            this.expr = jCExpression;
        }

        @Override // com.sun.tools.javac.tree.JCTree, com.sun.source.tree.Tree
        public <R, D> R accept(TreeVisitor<R, D> treeVisitor, D d) {
            return treeVisitor.visitConstantCaseLabel(this, d);
        }

        @Override // com.sun.source.tree.Tree
        public Tree.Kind getKind() {
            return Tree.Kind.CONSTANT_CASE_LABEL;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public Tag getTag() {
            return Tag.CONSTANTCASELABEL;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public void accept(Visitor visitor) {
            visitor.visitConstantCaseLabel(this);
        }

        @Override // com.sun.source.tree.ConstantCaseLabelTree
        public JCExpression getConstantExpression() {
            return this.expr;
        }
    }

    public static class JCContinue extends JCStatement implements ContinueTree {
        public Name label;
        public JCTree target;

        public JCContinue(Name name, JCTree jCTree) {
            this.label = name;
            this.target = jCTree;
        }

        @Override // com.sun.tools.javac.tree.JCTree, com.sun.source.tree.Tree
        public <R, D> R accept(TreeVisitor<R, D> treeVisitor, D d) {
            return treeVisitor.visitContinue(this, d);
        }

        @Override // com.sun.source.tree.Tree
        public Tree.Kind getKind() {
            return Tree.Kind.CONTINUE;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public Tag getTag() {
            return Tag.CONTINUE;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public void accept(Visitor visitor) {
            visitor.visitContinue(this);
        }

        @Override // com.sun.source.tree.ContinueTree
        public Name getLabel() {
            return this.label;
        }
    }

    public static class JCDefaultCaseLabel extends JCCaseLabel implements DefaultCaseLabelTree {
        @Override // com.sun.tools.javac.tree.JCTree, com.sun.source.tree.Tree
        public <R, D> R accept(TreeVisitor<R, D> treeVisitor, D d) {
            return treeVisitor.visitDefaultCaseLabel(this, d);
        }

        @Override // com.sun.source.tree.Tree
        public Tree.Kind getKind() {
            return Tree.Kind.DEFAULT_CASE_LABEL;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public Tag getTag() {
            return Tag.DEFAULTCASELABEL;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public void accept(Visitor visitor) {
            visitor.visitDefaultCaseLabel(this);
        }
    }

    public static class JCDoWhileLoop extends JCStatement implements DoWhileLoopTree {
        public JCStatement body;
        public JCExpression cond;

        public JCDoWhileLoop(JCStatement jCStatement, JCExpression jCExpression) {
            this.body = jCStatement;
            this.cond = jCExpression;
        }

        @Override // com.sun.tools.javac.tree.JCTree, com.sun.source.tree.Tree
        public <R, D> R accept(TreeVisitor<R, D> treeVisitor, D d) {
            return treeVisitor.visitDoWhileLoop(this, d);
        }

        @Override // com.sun.source.tree.Tree
        public Tree.Kind getKind() {
            return Tree.Kind.DO_WHILE_LOOP;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public Tag getTag() {
            return Tag.DOLOOP;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public void accept(Visitor visitor) {
            visitor.visitDoLoop(this);
        }

        @Override // com.sun.source.tree.DoWhileLoopTree
        public JCExpression getCondition() {
            return this.cond;
        }

        @Override // com.sun.source.tree.DoWhileLoopTree
        public JCStatement getStatement() {
            return this.body;
        }
    }

    public static class JCEnhancedForLoop extends JCStatement implements EnhancedForLoopTree {
        public JCStatement body;
        public JCExpression expr;
        public JCVariableDecl var;

        public JCEnhancedForLoop(JCVariableDecl jCVariableDecl, JCExpression jCExpression, JCStatement jCStatement) {
            this.var = jCVariableDecl;
            this.expr = jCExpression;
            this.body = jCStatement;
        }

        @Override // com.sun.tools.javac.tree.JCTree, com.sun.source.tree.Tree
        public <R, D> R accept(TreeVisitor<R, D> treeVisitor, D d) {
            return treeVisitor.visitEnhancedForLoop(this, d);
        }

        @Override // com.sun.source.tree.Tree
        public Tree.Kind getKind() {
            return Tree.Kind.ENHANCED_FOR_LOOP;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public Tag getTag() {
            return Tag.FOREACHLOOP;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public void accept(Visitor visitor) {
            visitor.visitForeachLoop(this);
        }

        @Override // com.sun.source.tree.EnhancedForLoopTree
        public JCExpression getExpression() {
            return this.expr;
        }

        @Override // com.sun.source.tree.EnhancedForLoopTree
        public JCStatement getStatement() {
            return this.body;
        }

        @Override // com.sun.source.tree.EnhancedForLoopTree
        public JCVariableDecl getVariable() {
            return this.var;
        }
    }

    public static class JCErroneous extends JCExpression implements ErroneousTree {
        public List<? extends JCTree> errs;

        public JCErroneous(List<? extends JCTree> list) {
            this.errs = list;
        }

        @Override // com.sun.tools.javac.tree.JCTree, com.sun.source.tree.Tree
        public <R, D> R accept(TreeVisitor<R, D> treeVisitor, D d) {
            return treeVisitor.visitErroneous(this, d);
        }

        @Override // com.sun.source.tree.Tree
        public Tree.Kind getKind() {
            return Tree.Kind.ERRONEOUS;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public Tag getTag() {
            return Tag.ERRONEOUS;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public void accept(Visitor visitor) {
            visitor.visitErroneous(this);
        }

        @Override // com.sun.source.tree.ErroneousTree
        public List<? extends JCTree> getErrorTrees() {
            return this.errs;
        }
    }

    public static class JCExports extends JCDirective implements ExportsTree {
        public Directive.ExportsDirective directive;
        public List<JCExpression> moduleNames;
        public JCExpression qualid;

        public JCExports(JCExpression jCExpression, List<JCExpression> list) {
            this.qualid = jCExpression;
            this.moduleNames = list;
        }

        @Override // com.sun.tools.javac.tree.JCTree, com.sun.source.tree.Tree
        public <R, D> R accept(TreeVisitor<R, D> treeVisitor, D d) {
            return treeVisitor.visitExports(this, d);
        }

        @Override // com.sun.source.tree.Tree
        public Tree.Kind getKind() {
            return Tree.Kind.EXPORTS;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public Tag getTag() {
            return Tag.EXPORTS;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public void accept(Visitor visitor) {
            visitor.visitExports(this);
        }

        @Override // com.sun.source.tree.ExportsTree
        public List<JCExpression> getModuleNames() {
            return this.moduleNames;
        }

        @Override // com.sun.source.tree.ExportsTree
        public JCExpression getPackageName() {
            return this.qualid;
        }
    }

    public static abstract class JCExpression extends JCTree implements ExpressionTree {
        public boolean isPoly() {
            return false;
        }

        public boolean isStandalone() {
            return true;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public JCExpression setPos(int i) {
            super.setPos(i);
            return this;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public JCExpression setType(Type type) {
            super.setType(type);
            return this;
        }
    }

    public static class JCExpressionStatement extends JCStatement implements ExpressionStatementTree {
        public JCExpression expr;

        public JCExpressionStatement(JCExpression jCExpression) {
            this.expr = jCExpression;
        }

        @Override // com.sun.tools.javac.tree.JCTree, com.sun.source.tree.Tree
        public <R, D> R accept(TreeVisitor<R, D> treeVisitor, D d) {
            return treeVisitor.visitExpressionStatement(this, d);
        }

        @Override // com.sun.source.tree.Tree
        public Tree.Kind getKind() {
            return Tree.Kind.EXPRESSION_STATEMENT;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public Tag getTag() {
            return Tag.EXEC;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public String toString() {
            StringWriter stringWriter = new StringWriter();
            try {
                new Pretty(stringWriter, false).printStat(this);
                return stringWriter.toString();
            } catch (IOException e) {
                x01.a(e);
                return null;
            }
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public void accept(Visitor visitor) {
            visitor.visitExec(this);
        }

        @Override // com.sun.source.tree.ExpressionStatementTree
        public JCExpression getExpression() {
            return this.expr;
        }
    }

    public static class JCFieldAccess extends JCExpression implements MemberSelectTree {
        public Name name;
        public JCExpression selected;
        public Symbol sym;

        public JCFieldAccess(JCExpression jCExpression, Name name, Symbol symbol) {
            this.selected = jCExpression;
            this.name = name;
            this.sym = symbol;
        }

        @Override // com.sun.tools.javac.tree.JCTree, com.sun.source.tree.Tree
        public <R, D> R accept(TreeVisitor<R, D> treeVisitor, D d) {
            return treeVisitor.visitMemberSelect(this, d);
        }

        @Override // com.sun.source.tree.Tree
        public Tree.Kind getKind() {
            return Tree.Kind.MEMBER_SELECT;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public Tag getTag() {
            return Tag.SELECT;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public void accept(Visitor visitor) {
            visitor.visitSelect(this);
        }

        @Override // com.sun.source.tree.MemberSelectTree
        public JCExpression getExpression() {
            return this.selected;
        }

        @Override // com.sun.source.tree.MemberSelectTree
        public Name getIdentifier() {
            return this.name;
        }
    }

    public static class JCForLoop extends JCStatement implements ForLoopTree {
        public JCStatement body;
        public JCExpression cond;
        public List<JCStatement> init;
        public List<JCExpressionStatement> step;

        public JCForLoop(List<JCStatement> list, JCExpression jCExpression, List<JCExpressionStatement> list2, JCStatement jCStatement) {
            this.init = list;
            this.cond = jCExpression;
            this.step = list2;
            this.body = jCStatement;
        }

        @Override // com.sun.tools.javac.tree.JCTree, com.sun.source.tree.Tree
        public <R, D> R accept(TreeVisitor<R, D> treeVisitor, D d) {
            return treeVisitor.visitForLoop(this, d);
        }

        @Override // com.sun.source.tree.Tree
        public Tree.Kind getKind() {
            return Tree.Kind.FOR_LOOP;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public Tag getTag() {
            return Tag.FORLOOP;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public void accept(Visitor visitor) {
            visitor.visitForLoop(this);
        }

        @Override // com.sun.source.tree.ForLoopTree
        public JCExpression getCondition() {
            return this.cond;
        }

        @Override // com.sun.source.tree.ForLoopTree
        public List<JCStatement> getInitializer() {
            return this.init;
        }

        @Override // com.sun.source.tree.ForLoopTree
        public JCStatement getStatement() {
            return this.body;
        }

        @Override // com.sun.source.tree.ForLoopTree
        public List<JCExpressionStatement> getUpdate() {
            return this.step;
        }
    }

    public static class JCIdent extends JCExpression implements IdentifierTree {
        public Name name;
        public Symbol sym;

        public JCIdent(Name name, Symbol symbol) {
            this.name = name;
            this.sym = symbol;
        }

        @Override // com.sun.tools.javac.tree.JCTree, com.sun.source.tree.Tree
        public <R, D> R accept(TreeVisitor<R, D> treeVisitor, D d) {
            return treeVisitor.visitIdentifier(this, d);
        }

        @Override // com.sun.source.tree.Tree
        public Tree.Kind getKind() {
            return Tree.Kind.IDENTIFIER;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public Tag getTag() {
            return Tag.IDENT;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public void accept(Visitor visitor) {
            visitor.visitIdent(this);
        }

        @Override // com.sun.source.tree.IdentifierTree
        public Name getName() {
            return this.name;
        }
    }

    public static class JCIf extends JCStatement implements IfTree {
        public JCExpression cond;
        public JCStatement elsepart;
        public JCStatement thenpart;

        public JCIf(JCExpression jCExpression, JCStatement jCStatement, JCStatement jCStatement2) {
            this.cond = jCExpression;
            this.thenpart = jCStatement;
            this.elsepart = jCStatement2;
        }

        @Override // com.sun.tools.javac.tree.JCTree, com.sun.source.tree.Tree
        public <R, D> R accept(TreeVisitor<R, D> treeVisitor, D d) {
            return treeVisitor.visitIf(this, d);
        }

        @Override // com.sun.source.tree.Tree
        public Tree.Kind getKind() {
            return Tree.Kind.IF;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public Tag getTag() {
            return Tag.IF;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public void accept(Visitor visitor) {
            visitor.visitIf(this);
        }

        @Override // com.sun.source.tree.IfTree
        public JCExpression getCondition() {
            return this.cond;
        }

        @Override // com.sun.source.tree.IfTree
        public JCStatement getElseStatement() {
            return this.elsepart;
        }

        @Override // com.sun.source.tree.IfTree
        public JCStatement getThenStatement() {
            return this.thenpart;
        }
    }

    public static class JCInstanceOf extends JCExpression implements InstanceOfTree {
        public boolean allowNull;
        public Type erasedExprOriginalType;
        public JCExpression expr;
        public JCTree pattern;

        public JCInstanceOf(JCExpression jCExpression, JCTree jCTree) {
            this.expr = jCExpression;
            this.pattern = jCTree;
        }

        @Override // com.sun.tools.javac.tree.JCTree, com.sun.source.tree.Tree
        public <R, D> R accept(TreeVisitor<R, D> treeVisitor, D d) {
            return treeVisitor.visitInstanceOf(this, d);
        }

        @Override // com.sun.source.tree.Tree
        public Tree.Kind getKind() {
            return Tree.Kind.INSTANCE_OF;
        }

        @Override // com.sun.source.tree.InstanceOfTree
        public JCPattern getPattern() {
            JCTree jCTree = this.pattern;
            if (jCTree instanceof JCPattern) {
                return (JCPattern) jCTree;
            }
            return null;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public Tag getTag() {
            return Tag.TYPETEST;
        }

        @Override // com.sun.source.tree.InstanceOfTree
        public JCTree getType() {
            JCTree jCTree = this.pattern;
            if (!(jCTree instanceof JCPattern)) {
                return jCTree;
            }
            if (jCTree.hasTag(Tag.BINDINGPATTERN)) {
                return ((JCBindingPattern) this.pattern).var.vartype;
            }
            return null;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public void accept(Visitor visitor) {
            visitor.visitTypeTest(this);
        }

        @Override // com.sun.source.tree.InstanceOfTree
        public JCExpression getExpression() {
            return this.expr;
        }
    }

    public static class JCLabeledStatement extends JCStatement implements LabeledStatementTree {
        public JCStatement body;
        public Name label;

        public JCLabeledStatement(Name name, JCStatement jCStatement) {
            this.label = name;
            this.body = jCStatement;
        }

        @Override // com.sun.tools.javac.tree.JCTree, com.sun.source.tree.Tree
        public <R, D> R accept(TreeVisitor<R, D> treeVisitor, D d) {
            return treeVisitor.visitLabeledStatement(this, d);
        }

        @Override // com.sun.source.tree.Tree
        public Tree.Kind getKind() {
            return Tree.Kind.LABELED_STATEMENT;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public Tag getTag() {
            return Tag.LABELLED;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public void accept(Visitor visitor) {
            visitor.visitLabelled(this);
        }

        @Override // com.sun.source.tree.LabeledStatementTree
        public Name getLabel() {
            return this.label;
        }

        @Override // com.sun.source.tree.LabeledStatementTree
        public JCStatement getStatement() {
            return this.body;
        }
    }

    public static class JCLambda extends JCFunctionalExpression implements LambdaExpressionTree {
        public JCTree body;
        public boolean canCompleteNormally = true;
        public ParameterKind paramKind;
        public List<JCVariableDecl> params;
        public boolean wasMethodReference;

        public enum ParameterKind {
            IMPLICIT,
            EXPLICIT
        }

        public JCLambda(List<JCVariableDecl> list, JCTree jCTree) {
            this.params = list;
            this.body = jCTree;
            if (list.isEmpty() || list.head.vartype != null) {
                this.paramKind = ParameterKind.EXPLICIT;
            } else {
                this.paramKind = ParameterKind.IMPLICIT;
            }
        }

        @Override // com.sun.tools.javac.tree.JCTree, com.sun.source.tree.Tree
        public <R, D> R accept(TreeVisitor<R, D> treeVisitor, D d) {
            return treeVisitor.visitLambdaExpression(this, d);
        }

        @Override // com.sun.source.tree.LambdaExpressionTree
        public LambdaExpressionTree.BodyKind getBodyKind() {
            return this.body.hasTag(Tag.BLOCK) ? LambdaExpressionTree.BodyKind.STATEMENT : LambdaExpressionTree.BodyKind.EXPRESSION;
        }

        @Override // com.sun.source.tree.Tree
        public Tree.Kind getKind() {
            return Tree.Kind.LAMBDA_EXPRESSION;
        }

        @Override // com.sun.source.tree.LambdaExpressionTree
        public java.util.List<? extends VariableTree> getParameters() {
            return this.params;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public Tag getTag() {
            return Tag.LAMBDA;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public void accept(Visitor visitor) {
            visitor.visitLambda(this);
        }

        @Override // com.sun.source.tree.LambdaExpressionTree
        public JCTree getBody() {
            return this.body;
        }

        @Override // com.sun.tools.javac.tree.JCTree.JCExpression, com.sun.tools.javac.tree.JCTree
        public JCLambda setType(Type type) {
            super.setType(type);
            return this;
        }
    }

    public static class JCLiteral extends JCExpression implements LiteralTree {
        public TypeTag typetag;
        public Object value;

        public JCLiteral(TypeTag typeTag, Object obj) {
            this.typetag = typeTag;
            this.value = obj;
        }

        @Override // com.sun.tools.javac.tree.JCTree, com.sun.source.tree.Tree
        public <R, D> R accept(TreeVisitor<R, D> treeVisitor, D d) {
            return treeVisitor.visitLiteral(this, d);
        }

        @Override // com.sun.source.tree.Tree
        public Tree.Kind getKind() {
            return this.typetag.getKindLiteral();
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public Tag getTag() {
            return Tag.LITERAL;
        }

        @Override // com.sun.source.tree.LiteralTree
        public Object getValue() {
            int i = AnonymousClass1.$SwitchMap$com$sun$tools$javac$code$TypeTag[this.typetag.ordinal()];
            if (i == 1) {
                return Boolean.valueOf(((Integer) this.value).intValue() != 0);
            }
            Object obj = this.value;
            if (i != 2) {
                return obj;
            }
            int iIntValue = ((Integer) obj).intValue();
            char c = (char) iIntValue;
            if (c == iIntValue) {
                return Character.valueOf(c);
            }
            x01.a("bad value for char literal");
            return null;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public void accept(Visitor visitor) {
            visitor.visitLiteral(this);
        }

        @Override // com.sun.tools.javac.tree.JCTree.JCExpression, com.sun.tools.javac.tree.JCTree
        public JCLiteral setType(Type type) {
            super.setType(type);
            return this;
        }
    }

    public static class JCMemberReference extends JCFunctionalExpression implements MemberReferenceTree {
        public JCExpression expr;
        public ReferenceKind kind;
        public MemberReferenceTree.ReferenceMode mode;
        public Name name;
        private OverloadKind overloadKind;
        public boolean ownerAccessible;
        public JCPolyExpression.PolyKind refPolyKind;
        public Type referentType;
        public Symbol sym;
        public List<JCExpression> typeargs;
        public Type varargsElement;

        public enum OverloadKind {
            OVERLOADED,
            UNOVERLOADED,
            ERROR
        }

        /* JADX WARN: Enum visitor error
        jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'SUPER' uses external variables
        	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:485)
        	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByField(EnumVisitor.java:399)
        	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByWrappedInsn(EnumVisitor.java:364)
        	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:349)
        	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:284)
        	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInvoke(EnumVisitor.java:315)
        	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:288)
        	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:153)
        	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:102)
         */
        /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
        public static final class ReferenceKind {
            private static final /* synthetic */ ReferenceKind[] $VALUES;
            public static final ReferenceKind ARRAY_CTOR;
            public static final ReferenceKind BOUND;
            public static final ReferenceKind IMPLICIT_INNER;
            public static final ReferenceKind STATIC;
            public static final ReferenceKind SUPER;
            public static final ReferenceKind TOPLEVEL;
            public static final ReferenceKind UNBOUND;
            final MemberReferenceTree.ReferenceMode mode;
            final boolean unbound;

            private static /* synthetic */ ReferenceKind[] $values() {
                return new ReferenceKind[]{SUPER, UNBOUND, STATIC, BOUND, IMPLICIT_INNER, TOPLEVEL, ARRAY_CTOR};
            }

            static {
                MemberReferenceTree.ReferenceMode referenceMode = MemberReferenceTree.ReferenceMode.INVOKE;
                SUPER = new ReferenceKind("SUPER", 0, referenceMode, false);
                UNBOUND = new ReferenceKind("UNBOUND", 1, referenceMode, true);
                STATIC = new ReferenceKind("STATIC", 2, referenceMode, false);
                BOUND = new ReferenceKind("BOUND", 3, referenceMode, false);
                MemberReferenceTree.ReferenceMode referenceMode2 = MemberReferenceTree.ReferenceMode.NEW;
                IMPLICIT_INNER = new ReferenceKind("IMPLICIT_INNER", 4, referenceMode2, false);
                TOPLEVEL = new ReferenceKind("TOPLEVEL", 5, referenceMode2, false);
                ARRAY_CTOR = new ReferenceKind("ARRAY_CTOR", 6, referenceMode2, false);
                $VALUES = $values();
            }

            private ReferenceKind(String str, int i, MemberReferenceTree.ReferenceMode referenceMode, boolean z) {
                super(str, i);
                this.mode = referenceMode;
                this.unbound = z;
            }

            public static ReferenceKind valueOf(String str) {
                return (ReferenceKind) Enum.valueOf(ReferenceKind.class, str);
            }

            public static ReferenceKind[] values() {
                return (ReferenceKind[]) $VALUES.clone();
            }

            public boolean isUnbound() {
                return this.unbound;
            }
        }

        public JCMemberReference(MemberReferenceTree.ReferenceMode referenceMode, Name name, JCExpression jCExpression, List<JCExpression> list) {
            this.mode = referenceMode;
            this.name = name;
            this.expr = jCExpression;
            this.typeargs = list;
        }

        @Override // com.sun.tools.javac.tree.JCTree, com.sun.source.tree.Tree
        public <R, D> R accept(TreeVisitor<R, D> treeVisitor, D d) {
            return treeVisitor.visitMemberReference(this, d);
        }

        @Override // com.sun.source.tree.Tree
        public Tree.Kind getKind() {
            return Tree.Kind.MEMBER_REFERENCE;
        }

        @Override // com.sun.source.tree.MemberReferenceTree
        public MemberReferenceTree.ReferenceMode getMode() {
            return this.mode;
        }

        public OverloadKind getOverloadKind() {
            return this.overloadKind;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public Tag getTag() {
            return Tag.REFERENCE;
        }

        public boolean hasKind(ReferenceKind referenceKind) {
            return this.kind == referenceKind;
        }

        public void setOverloadKind(OverloadKind overloadKind) {
            this.overloadKind = overloadKind;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public void accept(Visitor visitor) {
            visitor.visitReference(this);
        }

        @Override // com.sun.source.tree.MemberReferenceTree
        public Name getName() {
            return this.name;
        }

        @Override // com.sun.source.tree.MemberReferenceTree
        public JCExpression getQualifierExpression() {
            return this.expr;
        }

        @Override // com.sun.source.tree.MemberReferenceTree
        public List<JCExpression> getTypeArguments() {
            return this.typeargs;
        }
    }

    public static class JCMethodDecl extends JCTree implements MethodTree {
        public JCBlock body;
        public boolean completesNormally;
        public JCExpression defaultValue;
        public JCModifiers mods;
        public Name name;
        public List<JCVariableDecl> params;
        public JCVariableDecl recvparam;
        public JCExpression restype;
        public Symbol.MethodSymbol sym;
        public List<JCExpression> thrown;
        public List<JCTypeParameter> typarams;

        public JCMethodDecl(JCModifiers jCModifiers, Name name, JCExpression jCExpression, List<JCTypeParameter> list, JCVariableDecl jCVariableDecl, List<JCVariableDecl> list2, List<JCExpression> list3, JCBlock jCBlock, JCExpression jCExpression2, Symbol.MethodSymbol methodSymbol) {
            this.mods = jCModifiers;
            this.name = name;
            this.restype = jCExpression;
            this.typarams = list;
            this.params = list2;
            this.recvparam = jCVariableDecl;
            this.thrown = list3;
            this.body = jCBlock;
            this.defaultValue = jCExpression2;
            this.sym = methodSymbol;
        }

        @Override // com.sun.tools.javac.tree.JCTree, com.sun.source.tree.Tree
        public <R, D> R accept(TreeVisitor<R, D> treeVisitor, D d) {
            return treeVisitor.visitMethod(this, d);
        }

        @Override // com.sun.source.tree.Tree
        public Tree.Kind getKind() {
            return Tree.Kind.METHOD;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public Tag getTag() {
            return Tag.METHODDEF;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public void accept(Visitor visitor) {
            visitor.visitMethodDef(this);
        }

        @Override // com.sun.source.tree.MethodTree
        public JCBlock getBody() {
            return this.body;
        }

        @Override // com.sun.source.tree.MethodTree
        public JCTree getDefaultValue() {
            return this.defaultValue;
        }

        @Override // com.sun.source.tree.MethodTree
        public JCModifiers getModifiers() {
            return this.mods;
        }

        @Override // com.sun.source.tree.MethodTree
        public Name getName() {
            return this.name;
        }

        @Override // com.sun.source.tree.MethodTree
        public List<JCVariableDecl> getParameters() {
            return this.params;
        }

        @Override // com.sun.source.tree.MethodTree
        public JCVariableDecl getReceiverParameter() {
            return this.recvparam;
        }

        @Override // com.sun.source.tree.MethodTree
        public JCTree getReturnType() {
            return this.restype;
        }

        @Override // com.sun.source.tree.MethodTree
        public List<JCExpression> getThrows() {
            return this.thrown;
        }

        @Override // com.sun.source.tree.MethodTree
        public List<JCTypeParameter> getTypeParameters() {
            return this.typarams;
        }
    }

    public static class JCMethodInvocation extends JCPolyExpression implements MethodInvocationTree {
        public List<JCExpression> args;
        public JCExpression meth;
        public List<JCExpression> typeargs;
        public Type varargsElement;

        public JCMethodInvocation(List<JCExpression> list, JCExpression jCExpression, List<JCExpression> list2) {
            this.typeargs = list == null ? List.nil() : list;
            this.meth = jCExpression;
            this.args = list2;
        }

        @Override // com.sun.tools.javac.tree.JCTree, com.sun.source.tree.Tree
        public <R, D> R accept(TreeVisitor<R, D> treeVisitor, D d) {
            return treeVisitor.visitMethodInvocation(this, d);
        }

        @Override // com.sun.source.tree.Tree
        public Tree.Kind getKind() {
            return Tree.Kind.METHOD_INVOCATION;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public Tag getTag() {
            return Tag.APPLY;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public void accept(Visitor visitor) {
            visitor.visitApply(this);
        }

        @Override // com.sun.source.tree.MethodInvocationTree
        public List<JCExpression> getArguments() {
            return this.args;
        }

        @Override // com.sun.source.tree.MethodInvocationTree
        public JCExpression getMethodSelect() {
            return this.meth;
        }

        @Override // com.sun.source.tree.MethodInvocationTree
        public List<JCExpression> getTypeArguments() {
            return this.typeargs;
        }

        @Override // com.sun.tools.javac.tree.JCTree.JCExpression, com.sun.tools.javac.tree.JCTree
        public JCMethodInvocation setType(Type type) {
            super.setType(type);
            return this;
        }
    }

    public static class JCModifiers extends JCTree implements ModifiersTree {
        public List<JCAnnotation> annotations;
        public long flags;

        public JCModifiers(long j, List<JCAnnotation> list) {
            this.flags = j;
            this.annotations = list;
        }

        @Override // com.sun.tools.javac.tree.JCTree, com.sun.source.tree.Tree
        public <R, D> R accept(TreeVisitor<R, D> treeVisitor, D d) {
            return treeVisitor.visitModifiers(this, d);
        }

        @Override // com.sun.source.tree.ModifiersTree
        public Set<Modifier> getFlags() {
            return Flags.asModifierSet(this.flags);
        }

        @Override // com.sun.source.tree.Tree
        public Tree.Kind getKind() {
            return Tree.Kind.MODIFIERS;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public Tag getTag() {
            return Tag.MODIFIERS;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public void accept(Visitor visitor) {
            visitor.visitModifiers(this);
        }

        @Override // com.sun.source.tree.ModifiersTree
        public List<JCAnnotation> getAnnotations() {
            return this.annotations;
        }
    }

    public static class JCModuleDecl extends JCTree implements ModuleTree {
        public List<JCDirective> directives;
        private final ModuleTree.ModuleKind kind;
        public JCModifiers mods;
        public JCExpression qualId;
        public Symbol.ModuleSymbol sym;
        public Type.ModuleType type;

        public JCModuleDecl(JCModifiers jCModifiers, ModuleTree.ModuleKind moduleKind, JCExpression jCExpression, List<JCDirective> list) {
            this.mods = jCModifiers;
            this.kind = moduleKind;
            this.qualId = jCExpression;
            this.directives = list;
        }

        @Override // com.sun.tools.javac.tree.JCTree, com.sun.source.tree.Tree
        public <R, D> R accept(TreeVisitor<R, D> treeVisitor, D d) {
            return treeVisitor.visitModule(this, d);
        }

        @Override // com.sun.source.tree.Tree
        public Tree.Kind getKind() {
            return Tree.Kind.MODULE;
        }

        @Override // com.sun.source.tree.ModuleTree
        public ModuleTree.ModuleKind getModuleType() {
            return this.kind;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public Tag getTag() {
            return Tag.MODULEDEF;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public void accept(Visitor visitor) {
            visitor.visitModuleDef(this);
        }

        @Override // com.sun.source.tree.ModuleTree
        public List<? extends AnnotationTree> getAnnotations() {
            return this.mods.annotations;
        }

        @Override // com.sun.source.tree.ModuleTree
        public List<JCDirective> getDirectives() {
            return this.directives;
        }

        @Override // com.sun.source.tree.ModuleTree
        public JCExpression getName() {
            return this.qualId;
        }
    }

    public static class JCModuleImport extends JCImportBase {
        public JCExpression module;

        public JCModuleImport(JCExpression jCExpression) {
            this.module = jCExpression;
        }

        @Override // com.sun.tools.javac.tree.JCTree.JCImportBase, com.sun.tools.javac.tree.JCTree, com.sun.source.tree.Tree
        public <R, D> R accept(TreeVisitor<R, D> treeVisitor, D d) {
            return treeVisitor.visitImport(this, d);
        }

        @Override // com.sun.tools.javac.tree.JCTree.JCImportBase, com.sun.source.tree.Tree
        public Tree.Kind getKind() {
            return Tree.Kind.IMPORT;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public Tag getTag() {
            return Tag.MODULEIMPORT;
        }

        @Override // com.sun.source.tree.ImportTree
        public boolean isModule() {
            return true;
        }

        @Override // com.sun.source.tree.ImportTree
        public boolean isStatic() {
            return false;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public void accept(Visitor visitor) {
            visitor.visitModuleImport(this);
        }

        @Override // com.sun.tools.javac.tree.JCTree.JCImportBase, com.sun.source.tree.ImportTree
        public JCExpression getQualifiedIdentifier() {
            return this.module;
        }
    }

    public static class JCNewArray extends JCExpression implements NewArrayTree {
        public List<JCAnnotation> annotations = List.nil();
        public List<List<JCAnnotation>> dimAnnotations = List.nil();
        public List<JCExpression> dims;
        public List<JCExpression> elems;
        public JCExpression elemtype;

        public JCNewArray(JCExpression jCExpression, List<JCExpression> list, List<JCExpression> list2) {
            this.elemtype = jCExpression;
            this.dims = list;
            this.elems = list2;
        }

        @Override // com.sun.tools.javac.tree.JCTree, com.sun.source.tree.Tree
        public <R, D> R accept(TreeVisitor<R, D> treeVisitor, D d) {
            return treeVisitor.visitNewArray(this, d);
        }

        @Override // com.sun.source.tree.Tree
        public Tree.Kind getKind() {
            return Tree.Kind.NEW_ARRAY;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public Tag getTag() {
            return Tag.NEWARRAY;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public void accept(Visitor visitor) {
            visitor.visitNewArray(this);
        }

        @Override // com.sun.source.tree.NewArrayTree
        public List<JCAnnotation> getAnnotations() {
            return this.annotations;
        }

        @Override // com.sun.source.tree.NewArrayTree
        public List<List<JCAnnotation>> getDimAnnotations() {
            return this.dimAnnotations;
        }

        @Override // com.sun.source.tree.NewArrayTree
        public List<JCExpression> getDimensions() {
            return this.dims;
        }

        @Override // com.sun.source.tree.NewArrayTree
        public List<JCExpression> getInitializers() {
            return this.elems;
        }

        @Override // com.sun.source.tree.NewArrayTree
        public JCExpression getType() {
            return this.elemtype;
        }
    }

    public static class JCNewClass extends JCPolyExpression implements NewClassTree {
        public List<JCExpression> args;
        public JCExpression clazz;
        public Symbol constructor;
        public Type constructorType;
        public JCClassDecl def;
        public JCExpression encl;
        public List<JCExpression> typeargs;
        public Type varargsElement;

        public JCNewClass(JCExpression jCExpression, List<JCExpression> list, JCExpression jCExpression2, List<JCExpression> list2, JCClassDecl jCClassDecl) {
            this.encl = jCExpression;
            this.typeargs = list == null ? List.nil() : list;
            this.clazz = jCExpression2;
            this.args = list2;
            this.def = jCClassDecl;
        }

        @Override // com.sun.tools.javac.tree.JCTree, com.sun.source.tree.Tree
        public <R, D> R accept(TreeVisitor<R, D> treeVisitor, D d) {
            return treeVisitor.visitNewClass(this, d);
        }

        public boolean classDeclRemoved() {
            return false;
        }

        @Override // com.sun.source.tree.Tree
        public Tree.Kind getKind() {
            return Tree.Kind.NEW_CLASS;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public Tag getTag() {
            return Tag.NEWCLASS;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public void accept(Visitor visitor) {
            visitor.visitNewClass(this);
        }

        @Override // com.sun.source.tree.NewClassTree
        public List<JCExpression> getArguments() {
            return this.args;
        }

        @Override // com.sun.source.tree.NewClassTree
        public JCClassDecl getClassBody() {
            return this.def;
        }

        @Override // com.sun.source.tree.NewClassTree
        public JCExpression getEnclosingExpression() {
            return this.encl;
        }

        @Override // com.sun.source.tree.NewClassTree
        public JCExpression getIdentifier() {
            return this.clazz;
        }

        @Override // com.sun.source.tree.NewClassTree
        public List<JCExpression> getTypeArguments() {
            return this.typeargs;
        }
    }

    public static class JCOpens extends JCDirective implements OpensTree {
        public Directive.OpensDirective directive;
        public List<JCExpression> moduleNames;
        public JCExpression qualid;

        public JCOpens(JCExpression jCExpression, List<JCExpression> list) {
            this.qualid = jCExpression;
            this.moduleNames = list;
        }

        @Override // com.sun.tools.javac.tree.JCTree, com.sun.source.tree.Tree
        public <R, D> R accept(TreeVisitor<R, D> treeVisitor, D d) {
            return treeVisitor.visitOpens(this, d);
        }

        @Override // com.sun.source.tree.Tree
        public Tree.Kind getKind() {
            return Tree.Kind.OPENS;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public Tag getTag() {
            return Tag.OPENS;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public void accept(Visitor visitor) {
            visitor.visitOpens(this);
        }

        @Override // com.sun.source.tree.OpensTree
        public List<JCExpression> getModuleNames() {
            return this.moduleNames;
        }

        @Override // com.sun.source.tree.OpensTree
        public JCExpression getPackageName() {
            return this.qualid;
        }
    }

    public static class JCPackageDecl extends JCTree implements PackageTree {
        public List<JCAnnotation> annotations;
        public Symbol.PackageSymbol packge;
        public JCExpression pid;

        public JCPackageDecl(List<JCAnnotation> list, JCExpression jCExpression) {
            this.annotations = list;
            this.pid = jCExpression;
        }

        @Override // com.sun.tools.javac.tree.JCTree, com.sun.source.tree.Tree
        public <R, D> R accept(TreeVisitor<R, D> treeVisitor, D d) {
            return treeVisitor.visitPackage(this, d);
        }

        @Override // com.sun.source.tree.Tree
        public Tree.Kind getKind() {
            return Tree.Kind.PACKAGE;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public Tag getTag() {
            return Tag.PACKAGEDEF;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public void accept(Visitor visitor) {
            visitor.visitPackageDef(this);
        }

        @Override // com.sun.source.tree.PackageTree
        public List<JCAnnotation> getAnnotations() {
            return this.annotations;
        }

        @Override // com.sun.source.tree.PackageTree
        public JCExpression getPackageName() {
            return this.pid;
        }
    }

    public static class JCParens extends JCExpression implements ParenthesizedTree {
        public JCExpression expr;

        public JCParens(JCExpression jCExpression) {
            this.expr = jCExpression;
        }

        @Override // com.sun.tools.javac.tree.JCTree, com.sun.source.tree.Tree
        public <R, D> R accept(TreeVisitor<R, D> treeVisitor, D d) {
            return treeVisitor.visitParenthesized(this, d);
        }

        @Override // com.sun.source.tree.Tree
        public Tree.Kind getKind() {
            return Tree.Kind.PARENTHESIZED;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public Tag getTag() {
            return Tag.PARENS;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public void accept(Visitor visitor) {
            visitor.visitParens(this);
        }

        @Override // com.sun.source.tree.ParenthesizedTree
        public JCExpression getExpression() {
            return this.expr;
        }
    }

    public static class JCPatternCaseLabel extends JCCaseLabel implements PatternCaseLabelTree {
        public JCPattern pat;
        public JCExpression syntheticGuard;

        public JCPatternCaseLabel(JCPattern jCPattern) {
            this.pat = jCPattern;
        }

        @Override // com.sun.tools.javac.tree.JCTree, com.sun.source.tree.Tree
        public <R, D> R accept(TreeVisitor<R, D> treeVisitor, D d) {
            return treeVisitor.visitPatternCaseLabel(this, d);
        }

        @Override // com.sun.source.tree.Tree
        public Tree.Kind getKind() {
            return Tree.Kind.PATTERN_CASE_LABEL;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public Tag getTag() {
            return Tag.PATTERNCASELABEL;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public void accept(Visitor visitor) {
            visitor.visitPatternCaseLabel(this);
        }

        @Override // com.sun.source.tree.PatternCaseLabelTree
        public JCPattern getPattern() {
            return this.pat;
        }
    }

    public static class JCPrimitiveTypeTree extends JCExpression implements PrimitiveTypeTree {
        public TypeTag typetag;

        public JCPrimitiveTypeTree(TypeTag typeTag) {
            this.typetag = typeTag;
        }

        @Override // com.sun.tools.javac.tree.JCTree, com.sun.source.tree.Tree
        public <R, D> R accept(TreeVisitor<R, D> treeVisitor, D d) {
            return treeVisitor.visitPrimitiveType(this, d);
        }

        @Override // com.sun.source.tree.Tree
        public Tree.Kind getKind() {
            return Tree.Kind.PRIMITIVE_TYPE;
        }

        @Override // com.sun.source.tree.PrimitiveTypeTree
        public TypeKind getPrimitiveTypeKind() {
            return this.typetag.getPrimitiveTypeKind();
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public Tag getTag() {
            return Tag.TYPEIDENT;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public void accept(Visitor visitor) {
            visitor.visitTypeIdent(this);
        }
    }

    public static class JCProvides extends JCDirective implements ProvidesTree {
        public List<JCExpression> implNames;
        public JCExpression serviceName;

        public JCProvides(JCExpression jCExpression, List<JCExpression> list) {
            this.serviceName = jCExpression;
            this.implNames = list;
        }

        @Override // com.sun.tools.javac.tree.JCTree, com.sun.source.tree.Tree
        public <R, D> R accept(TreeVisitor<R, D> treeVisitor, D d) {
            return treeVisitor.visitProvides(this, d);
        }

        @Override // com.sun.source.tree.Tree
        public Tree.Kind getKind() {
            return Tree.Kind.PROVIDES;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public Tag getTag() {
            return Tag.PROVIDES;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public void accept(Visitor visitor) {
            visitor.visitProvides(this);
        }

        @Override // com.sun.source.tree.ProvidesTree
        public List<JCExpression> getImplementationNames() {
            return this.implNames;
        }

        @Override // com.sun.source.tree.ProvidesTree
        public JCExpression getServiceName() {
            return this.serviceName;
        }
    }

    public static class JCRecordPattern extends JCPattern implements DeconstructionPatternTree {
        public JCExpression deconstructor;
        public List<Type> fullComponentTypes;
        public List<JCPattern> nested;
        public Symbol.ClassSymbol record;

        public JCRecordPattern(JCExpression jCExpression, List<JCPattern> list) {
            this.deconstructor = jCExpression;
            this.nested = list;
        }

        @Override // com.sun.tools.javac.tree.JCTree, com.sun.source.tree.Tree
        public <R, D> R accept(TreeVisitor<R, D> treeVisitor, D d) {
            return treeVisitor.visitDeconstructionPattern(this, d);
        }

        public Name getBinding() {
            return null;
        }

        @Override // com.sun.source.tree.DeconstructionPatternTree
        public ExpressionTree getDeconstructor() {
            return this.deconstructor;
        }

        @Override // com.sun.source.tree.Tree
        public Tree.Kind getKind() {
            return Tree.Kind.DECONSTRUCTION_PATTERN;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public Tag getTag() {
            return Tag.RECORDPATTERN;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public void accept(Visitor visitor) {
            visitor.visitRecordPattern(this);
        }

        @Override // com.sun.source.tree.DeconstructionPatternTree
        public List<? extends JCPattern> getNestedPatterns() {
            return this.nested;
        }
    }

    public static class JCRequires extends JCDirective implements RequiresTree {
        public Directive.RequiresDirective directive;
        public boolean isStaticPhase;
        public boolean isTransitive;
        public JCExpression moduleName;

        public JCRequires(boolean z, boolean z2, JCExpression jCExpression) {
            this.isTransitive = z;
            this.isStaticPhase = z2;
            this.moduleName = jCExpression;
        }

        @Override // com.sun.tools.javac.tree.JCTree, com.sun.source.tree.Tree
        public <R, D> R accept(TreeVisitor<R, D> treeVisitor, D d) {
            return treeVisitor.visitRequires(this, d);
        }

        @Override // com.sun.source.tree.Tree
        public Tree.Kind getKind() {
            return Tree.Kind.REQUIRES;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public Tag getTag() {
            return Tag.REQUIRES;
        }

        @Override // com.sun.source.tree.RequiresTree
        public boolean isStatic() {
            return this.isStaticPhase;
        }

        @Override // com.sun.source.tree.RequiresTree
        public boolean isTransitive() {
            return this.isTransitive;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public void accept(Visitor visitor) {
            visitor.visitRequires(this);
        }

        @Override // com.sun.source.tree.RequiresTree
        public JCExpression getModuleName() {
            return this.moduleName;
        }
    }

    public static class JCReturn extends JCStatement implements ReturnTree {
        public JCExpression expr;

        public JCReturn(JCExpression jCExpression) {
            this.expr = jCExpression;
        }

        @Override // com.sun.tools.javac.tree.JCTree, com.sun.source.tree.Tree
        public <R, D> R accept(TreeVisitor<R, D> treeVisitor, D d) {
            return treeVisitor.visitReturn(this, d);
        }

        @Override // com.sun.source.tree.Tree
        public Tree.Kind getKind() {
            return Tree.Kind.RETURN;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public Tag getTag() {
            return Tag.RETURN;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public void accept(Visitor visitor) {
            visitor.visitReturn(this);
        }

        @Override // com.sun.source.tree.ReturnTree
        public JCExpression getExpression() {
            return this.expr;
        }
    }

    public static class JCSkip extends JCStatement implements EmptyStatementTree {
        @Override // com.sun.tools.javac.tree.JCTree, com.sun.source.tree.Tree
        public <R, D> R accept(TreeVisitor<R, D> treeVisitor, D d) {
            return treeVisitor.visitEmptyStatement(this, d);
        }

        @Override // com.sun.source.tree.Tree
        public Tree.Kind getKind() {
            return Tree.Kind.EMPTY_STATEMENT;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public Tag getTag() {
            return Tag.SKIP;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public void accept(Visitor visitor) {
            visitor.visitSkip(this);
        }
    }

    public static abstract class JCStatement extends JCTree implements StatementTree {
        @Override // com.sun.tools.javac.tree.JCTree
        public JCStatement setPos(int i) {
            super.setPos(i);
            return this;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public JCStatement setType(Type type) {
            super.setType(type);
            return this;
        }
    }

    public static class JCSwitch extends JCStatement implements SwitchTree {
        public int bracePos = -1;
        public List<JCCase> cases;
        public boolean hasUnconditionalPattern;
        public boolean isExhaustive;
        public boolean patternSwitch;
        public JCExpression selector;
        public boolean wasEnumSelector;

        public JCSwitch(JCExpression jCExpression, List<JCCase> list) {
            this.selector = jCExpression;
            this.cases = list;
        }

        @Override // com.sun.tools.javac.tree.JCTree, com.sun.source.tree.Tree
        public <R, D> R accept(TreeVisitor<R, D> treeVisitor, D d) {
            return treeVisitor.visitSwitch(this, d);
        }

        @Override // com.sun.source.tree.Tree
        public Tree.Kind getKind() {
            return Tree.Kind.SWITCH;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public Tag getTag() {
            return Tag.SWITCH;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public void accept(Visitor visitor) {
            visitor.visitSwitch(this);
        }

        @Override // com.sun.source.tree.SwitchTree
        public List<JCCase> getCases() {
            return this.cases;
        }

        @Override // com.sun.source.tree.SwitchTree
        public JCExpression getExpression() {
            return this.selector;
        }
    }

    public static class JCSwitchExpression extends JCPolyExpression implements SwitchExpressionTree {
        public int bracePos = -1;
        public List<JCCase> cases;
        public boolean hasUnconditionalPattern;
        public boolean isExhaustive;
        public boolean patternSwitch;
        public JCExpression selector;
        public boolean wasEnumSelector;

        public JCSwitchExpression(JCExpression jCExpression, List<JCCase> list) {
            this.selector = jCExpression;
            this.cases = list;
        }

        @Override // com.sun.tools.javac.tree.JCTree, com.sun.source.tree.Tree
        public <R, D> R accept(TreeVisitor<R, D> treeVisitor, D d) {
            return treeVisitor.visitSwitchExpression(this, d);
        }

        @Override // com.sun.source.tree.Tree
        public Tree.Kind getKind() {
            return Tree.Kind.SWITCH_EXPRESSION;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public Tag getTag() {
            return Tag.SWITCH_EXPRESSION;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public void accept(Visitor visitor) {
            visitor.visitSwitchExpression(this);
        }

        @Override // com.sun.source.tree.SwitchExpressionTree
        public List<JCCase> getCases() {
            return this.cases;
        }

        @Override // com.sun.source.tree.SwitchExpressionTree
        public JCExpression getExpression() {
            return this.selector;
        }
    }

    public static class JCSynchronized extends JCStatement implements SynchronizedTree {
        public JCBlock body;
        public JCExpression lock;

        public JCSynchronized(JCExpression jCExpression, JCBlock jCBlock) {
            this.lock = jCExpression;
            this.body = jCBlock;
        }

        @Override // com.sun.tools.javac.tree.JCTree, com.sun.source.tree.Tree
        public <R, D> R accept(TreeVisitor<R, D> treeVisitor, D d) {
            return treeVisitor.visitSynchronized(this, d);
        }

        @Override // com.sun.source.tree.Tree
        public Tree.Kind getKind() {
            return Tree.Kind.SYNCHRONIZED;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public Tag getTag() {
            return Tag.SYNCHRONIZED;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public void accept(Visitor visitor) {
            visitor.visitSynchronized(this);
        }

        @Override // com.sun.source.tree.SynchronizedTree
        public JCBlock getBlock() {
            return this.body;
        }

        @Override // com.sun.source.tree.SynchronizedTree
        public JCExpression getExpression() {
            return this.lock;
        }
    }

    public static class JCThrow extends JCStatement implements ThrowTree {
        public JCExpression expr;

        public JCThrow(JCExpression jCExpression) {
            this.expr = jCExpression;
        }

        @Override // com.sun.tools.javac.tree.JCTree, com.sun.source.tree.Tree
        public <R, D> R accept(TreeVisitor<R, D> treeVisitor, D d) {
            return treeVisitor.visitThrow(this, d);
        }

        @Override // com.sun.source.tree.Tree
        public Tree.Kind getKind() {
            return Tree.Kind.THROW;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public Tag getTag() {
            return Tag.THROW;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public void accept(Visitor visitor) {
            visitor.visitThrow(this);
        }

        @Override // com.sun.source.tree.ThrowTree
        public JCExpression getExpression() {
            return this.expr;
        }
    }

    public static class JCTry extends JCStatement implements TryTree {
        public JCBlock body;
        public List<JCCatch> catchers;
        public JCBlock finalizer;
        public boolean finallyCanCompleteNormally;
        public List<JCTree> resources;

        public JCTry(List<JCTree> list, JCBlock jCBlock, List<JCCatch> list2, JCBlock jCBlock2) {
            this.body = jCBlock;
            this.catchers = list2;
            this.finalizer = jCBlock2;
            this.resources = list;
        }

        @Override // com.sun.tools.javac.tree.JCTree, com.sun.source.tree.Tree
        public <R, D> R accept(TreeVisitor<R, D> treeVisitor, D d) {
            return treeVisitor.visitTry(this, d);
        }

        @Override // com.sun.source.tree.Tree
        public Tree.Kind getKind() {
            return Tree.Kind.TRY;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public Tag getTag() {
            return Tag.TRY;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public void accept(Visitor visitor) {
            visitor.visitTry(this);
        }

        @Override // com.sun.source.tree.TryTree
        public JCBlock getBlock() {
            return this.body;
        }

        @Override // com.sun.source.tree.TryTree
        public List<JCCatch> getCatches() {
            return this.catchers;
        }

        @Override // com.sun.source.tree.TryTree
        public JCBlock getFinallyBlock() {
            return this.finalizer;
        }

        @Override // com.sun.source.tree.TryTree
        public List<JCTree> getResources() {
            return this.resources;
        }
    }

    public static class JCTypeApply extends JCExpression implements ParameterizedTypeTree {
        public List<JCExpression> arguments;
        public JCExpression clazz;

        public JCTypeApply(JCExpression jCExpression, List<JCExpression> list) {
            this.clazz = jCExpression;
            this.arguments = list;
        }

        @Override // com.sun.tools.javac.tree.JCTree, com.sun.source.tree.Tree
        public <R, D> R accept(TreeVisitor<R, D> treeVisitor, D d) {
            return treeVisitor.visitParameterizedType(this, d);
        }

        @Override // com.sun.source.tree.Tree
        public Tree.Kind getKind() {
            return Tree.Kind.PARAMETERIZED_TYPE;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public Tag getTag() {
            return Tag.TYPEAPPLY;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public void accept(Visitor visitor) {
            visitor.visitTypeApply(this);
        }

        @Override // com.sun.source.tree.ParameterizedTypeTree
        public JCTree getType() {
            return this.clazz;
        }

        @Override // com.sun.source.tree.ParameterizedTypeTree
        public List<JCExpression> getTypeArguments() {
            return this.arguments;
        }
    }

    public static class JCTypeCast extends JCExpression implements TypeCastTree {
        public JCTree clazz;
        public JCExpression expr;

        public JCTypeCast(JCTree jCTree, JCExpression jCExpression) {
            this.clazz = jCTree;
            this.expr = jCExpression;
        }

        @Override // com.sun.tools.javac.tree.JCTree, com.sun.source.tree.Tree
        public <R, D> R accept(TreeVisitor<R, D> treeVisitor, D d) {
            return treeVisitor.visitTypeCast(this, d);
        }

        @Override // com.sun.source.tree.Tree
        public Tree.Kind getKind() {
            return Tree.Kind.TYPE_CAST;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public Tag getTag() {
            return Tag.TYPECAST;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public void accept(Visitor visitor) {
            visitor.visitTypeCast(this);
        }

        @Override // com.sun.source.tree.TypeCastTree
        public JCExpression getExpression() {
            return this.expr;
        }

        @Override // com.sun.source.tree.TypeCastTree
        public JCTree getType() {
            return this.clazz;
        }
    }

    public static class JCTypeIntersection extends JCExpression implements IntersectionTypeTree {
        public List<JCExpression> bounds;

        public JCTypeIntersection(List<JCExpression> list) {
            this.bounds = list;
        }

        @Override // com.sun.tools.javac.tree.JCTree, com.sun.source.tree.Tree
        public <R, D> R accept(TreeVisitor<R, D> treeVisitor, D d) {
            return treeVisitor.visitIntersectionType(this, d);
        }

        @Override // com.sun.source.tree.Tree
        public Tree.Kind getKind() {
            return Tree.Kind.INTERSECTION_TYPE;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public Tag getTag() {
            return Tag.TYPEINTERSECTION;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public void accept(Visitor visitor) {
            visitor.visitTypeIntersection(this);
        }

        @Override // com.sun.source.tree.IntersectionTypeTree
        public List<JCExpression> getBounds() {
            return this.bounds;
        }
    }

    public static class JCTypeParameter extends JCTree implements TypeParameterTree {
        public List<JCAnnotation> annotations;
        public List<JCExpression> bounds;
        public Name name;

        public JCTypeParameter(Name name, List<JCExpression> list, List<JCAnnotation> list2) {
            this.name = name;
            this.bounds = list;
            this.annotations = list2;
        }

        @Override // com.sun.tools.javac.tree.JCTree, com.sun.source.tree.Tree
        public <R, D> R accept(TreeVisitor<R, D> treeVisitor, D d) {
            return treeVisitor.visitTypeParameter(this, d);
        }

        @Override // com.sun.source.tree.Tree
        public Tree.Kind getKind() {
            return Tree.Kind.TYPE_PARAMETER;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public Tag getTag() {
            return Tag.TYPEPARAMETER;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public void accept(Visitor visitor) {
            visitor.visitTypeParameter(this);
        }

        @Override // com.sun.source.tree.TypeParameterTree
        public List<JCAnnotation> getAnnotations() {
            return this.annotations;
        }

        @Override // com.sun.source.tree.TypeParameterTree
        public List<JCExpression> getBounds() {
            return this.bounds;
        }

        @Override // com.sun.source.tree.TypeParameterTree
        public Name getName() {
            return this.name;
        }
    }

    public static class JCTypeUnion extends JCExpression implements UnionTypeTree {
        public List<JCExpression> alternatives;

        public JCTypeUnion(List<JCExpression> list) {
            this.alternatives = list;
        }

        @Override // com.sun.tools.javac.tree.JCTree, com.sun.source.tree.Tree
        public <R, D> R accept(TreeVisitor<R, D> treeVisitor, D d) {
            return treeVisitor.visitUnionType(this, d);
        }

        @Override // com.sun.source.tree.Tree
        public Tree.Kind getKind() {
            return Tree.Kind.UNION_TYPE;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public Tag getTag() {
            return Tag.TYPEUNION;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public void accept(Visitor visitor) {
            visitor.visitTypeUnion(this);
        }

        @Override // com.sun.source.tree.UnionTypeTree
        public List<JCExpression> getTypeAlternatives() {
            return this.alternatives;
        }
    }

    public static class JCUnary extends JCOperatorExpression implements UnaryTree {
        public JCExpression arg;

        public JCUnary(Tag tag, JCExpression jCExpression) {
            this.opcode = tag;
            this.arg = jCExpression;
        }

        @Override // com.sun.tools.javac.tree.JCTree, com.sun.source.tree.Tree
        public <R, D> R accept(TreeVisitor<R, D> treeVisitor, D d) {
            return treeVisitor.visitUnary(this, d);
        }

        @Override // com.sun.source.tree.Tree
        public Tree.Kind getKind() {
            return TreeInfo.tagToKind(getTag());
        }

        @Override // com.sun.tools.javac.tree.JCTree.JCOperatorExpression
        public JCExpression getOperand(JCOperatorExpression.OperandPos operandPos) {
            return this.arg;
        }

        public void setTag(Tag tag) {
            this.opcode = tag;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public void accept(Visitor visitor) {
            visitor.visitUnary(this);
        }

        @Override // com.sun.source.tree.UnaryTree
        public JCExpression getExpression() {
            return this.arg;
        }
    }

    public static class JCUses extends JCDirective implements UsesTree {
        public JCExpression qualid;

        public JCUses(JCExpression jCExpression) {
            this.qualid = jCExpression;
        }

        @Override // com.sun.tools.javac.tree.JCTree, com.sun.source.tree.Tree
        public <R, D> R accept(TreeVisitor<R, D> treeVisitor, D d) {
            return treeVisitor.visitUses(this, d);
        }

        @Override // com.sun.source.tree.Tree
        public Tree.Kind getKind() {
            return Tree.Kind.USES;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public Tag getTag() {
            return Tag.USES;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public void accept(Visitor visitor) {
            visitor.visitUses(this);
        }

        @Override // com.sun.source.tree.UsesTree
        public JCExpression getServiceName() {
            return this.qualid;
        }
    }

    public static class JCVariableDecl extends JCStatement implements VariableTree {
        public DeclKind declKind;
        public JCExpression init;
        public JCModifiers mods;
        public Name name;
        public JCExpression nameexpr;
        public Symbol.VarSymbol sym;
        public int typePos;
        public JCExpression vartype;

        public enum DeclKind {
            EXPLICIT,
            IMPLICIT,
            VAR
        }

        public JCVariableDecl(JCModifiers jCModifiers, JCExpression jCExpression, JCExpression jCExpression2) {
            this(jCModifiers, null, jCExpression2, null, null, DeclKind.EXPLICIT, -1);
            this.nameexpr = jCExpression;
            if (jCExpression.hasTag(Tag.IDENT)) {
                this.name = ((JCIdent) jCExpression).name;
            } else {
                this.name = ((JCFieldAccess) jCExpression).name;
            }
        }

        @Override // com.sun.tools.javac.tree.JCTree, com.sun.source.tree.Tree
        public <R, D> R accept(TreeVisitor<R, D> treeVisitor, D d) {
            return treeVisitor.visitVariable(this, d);
        }

        public boolean declaredUsingVar() {
            return this.declKind == DeclKind.VAR;
        }

        @Override // com.sun.source.tree.Tree
        public Tree.Kind getKind() {
            return Tree.Kind.VARIABLE;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public Tag getTag() {
            return Tag.VARDEF;
        }

        public boolean isImplicitlyTyped() {
            return this.vartype == null;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public void accept(Visitor visitor) {
            visitor.visitVarDef(this);
        }

        @Override // com.sun.source.tree.VariableTree
        public JCExpression getInitializer() {
            return this.init;
        }

        @Override // com.sun.source.tree.VariableTree
        public JCModifiers getModifiers() {
            return this.mods;
        }

        @Override // com.sun.source.tree.VariableTree
        public Name getName() {
            return this.name;
        }

        @Override // com.sun.source.tree.VariableTree
        public JCExpression getNameExpression() {
            return this.nameexpr;
        }

        @Override // com.sun.source.tree.VariableTree
        public JCTree getType() {
            return this.vartype;
        }

        public JCVariableDecl(JCModifiers jCModifiers, Name name, JCExpression jCExpression, JCExpression jCExpression2, Symbol.VarSymbol varSymbol, DeclKind declKind, int i) {
            this.mods = jCModifiers;
            this.name = name;
            this.vartype = jCExpression;
            this.init = jCExpression2;
            this.sym = varSymbol;
            this.declKind = declKind;
            this.typePos = i;
        }

        public JCVariableDecl(JCModifiers jCModifiers, Name name, JCExpression jCExpression, JCExpression jCExpression2, Symbol.VarSymbol varSymbol) {
            this(jCModifiers, name, jCExpression, jCExpression2, varSymbol, DeclKind.EXPLICIT, -1);
        }
    }

    public static class JCWhileLoop extends JCStatement implements WhileLoopTree {
        public JCStatement body;
        public JCExpression cond;

        public JCWhileLoop(JCExpression jCExpression, JCStatement jCStatement) {
            this.cond = jCExpression;
            this.body = jCStatement;
        }

        @Override // com.sun.tools.javac.tree.JCTree, com.sun.source.tree.Tree
        public <R, D> R accept(TreeVisitor<R, D> treeVisitor, D d) {
            return treeVisitor.visitWhileLoop(this, d);
        }

        @Override // com.sun.source.tree.Tree
        public Tree.Kind getKind() {
            return Tree.Kind.WHILE_LOOP;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public Tag getTag() {
            return Tag.WHILELOOP;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public void accept(Visitor visitor) {
            visitor.visitWhileLoop(this);
        }

        @Override // com.sun.source.tree.WhileLoopTree
        public JCExpression getCondition() {
            return this.cond;
        }

        @Override // com.sun.source.tree.WhileLoopTree
        public JCStatement getStatement() {
            return this.body;
        }
    }

    public static class JCWildcard extends JCExpression implements WildcardTree {
        public JCTree inner;
        public TypeBoundKind kind;

        public JCWildcard(TypeBoundKind typeBoundKind, JCTree jCTree) {
            this.kind = (TypeBoundKind) Assert.checkNonNull(typeBoundKind);
            this.inner = jCTree;
        }

        @Override // com.sun.tools.javac.tree.JCTree, com.sun.source.tree.Tree
        public <R, D> R accept(TreeVisitor<R, D> treeVisitor, D d) {
            return treeVisitor.visitWildcard(this, d);
        }

        @Override // com.sun.source.tree.Tree
        public Tree.Kind getKind() {
            int i = AnonymousClass1.$SwitchMap$com$sun$tools$javac$code$BoundKind[this.kind.kind.ordinal()];
            if (i == 1) {
                return Tree.Kind.UNBOUNDED_WILDCARD;
            }
            if (i == 2) {
                return Tree.Kind.EXTENDS_WILDCARD;
            }
            if (i == 3) {
                return Tree.Kind.SUPER_WILDCARD;
            }
            pe1.a("Unknown wildcard bound ", this.kind);
            return null;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public Tag getTag() {
            return Tag.WILDCARD;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public void accept(Visitor visitor) {
            visitor.visitWildcard(this);
        }

        @Override // com.sun.source.tree.WildcardTree
        public JCTree getBound() {
            return this.inner;
        }
    }

    public static class JCYield extends JCStatement implements YieldTree {
        public JCTree target;
        public JCExpression value;

        public JCYield(JCExpression jCExpression, JCTree jCTree) {
            this.value = jCExpression;
            this.target = jCTree;
        }

        @Override // com.sun.tools.javac.tree.JCTree, com.sun.source.tree.Tree
        public <R, D> R accept(TreeVisitor<R, D> treeVisitor, D d) {
            return treeVisitor.visitYield(this, d);
        }

        @Override // com.sun.source.tree.Tree
        public Tree.Kind getKind() {
            return Tree.Kind.YIELD;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public Tag getTag() {
            return Tag.YIELD;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public void accept(Visitor visitor) {
            visitor.visitYield(this);
        }

        @Override // com.sun.source.tree.YieldTree
        public JCExpression getValue() {
            return this.value;
        }
    }

    public static class JCImport extends JCImportBase {
        public Scope importScope;
        public JCFieldAccess qualid;
        public boolean staticImport;

        public JCImport(JCFieldAccess jCFieldAccess, boolean z) {
            this.qualid = jCFieldAccess;
            this.staticImport = z;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public void accept(Visitor visitor) {
            visitor.visitImport(this);
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public Tag getTag() {
            return Tag.IMPORT;
        }

        @Override // com.sun.source.tree.ImportTree
        public boolean isModule() {
            return false;
        }

        @Override // com.sun.source.tree.ImportTree
        public boolean isStatic() {
            return this.staticImport;
        }

        @Override // com.sun.tools.javac.tree.JCTree.JCImportBase, com.sun.source.tree.ImportTree
        public JCFieldAccess getQualifiedIdentifier() {
            return this.qualid;
        }
    }

    /* JADX WARN: Enum visitor error
    jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'MOD_ASG' uses external variables
    	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:485)
    	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByField(EnumVisitor.java:399)
    	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByWrappedInsn(EnumVisitor.java:364)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:349)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:284)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInvoke(EnumVisitor.java:315)
    	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:288)
    	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:153)
    	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:102)
     */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    public static final class Tag {
        private static final /* synthetic */ Tag[] $VALUES;
        public static final Tag AND;
        public static final Tag BITAND;
        public static final Tag BITAND_ASG;
        public static final Tag BITOR;
        public static final Tag BITOR_ASG;
        public static final Tag BITXOR;
        public static final Tag BITXOR_ASG;
        public static final Tag COMPL;
        public static final Tag DIV;
        public static final Tag DIV_ASG;
        public static final Tag EQ;
        public static final Tag EXPORTS;
        public static final Tag GE;
        public static final Tag GT;
        public static final Tag LE;
        public static final Tag LETEXPR;
        public static final Tag LT;
        public static final Tag MINUS;
        public static final Tag MINUS_ASG;
        public static final Tag MOD;
        public static final Tag MODULEDEF;
        public static final Tag MOD_ASG;
        public static final Tag MUL;
        public static final Tag MUL_ASG;
        public static final Tag NE;
        public static final Tag NEG;
        public static final Tag NOT;
        public static final Tag NULLCHK;
        public static final Tag OPENS;
        public static final Tag OR;
        public static final Tag PLUS;
        public static final Tag PLUS_ASG;
        public static final Tag POS;
        public static final Tag POSTDEC;
        public static final Tag POSTINC;
        public static final Tag PREDEC;
        public static final Tag PREINC;
        public static final Tag PROVIDES;
        public static final Tag REQUIRES;
        public static final Tag SL;
        public static final Tag SL_ASG;
        public static final Tag SR;
        public static final Tag SR_ASG;
        public static final Tag USES;
        public static final Tag USR;
        public static final Tag USR_ASG;
        private static final int numberOfOperators;
        private final Tag noAssignTag;
        public static final Tag NO_TAG = new Tag("NO_TAG", 0);
        public static final Tag TOPLEVEL = new Tag("TOPLEVEL", 1);
        public static final Tag PACKAGEDEF = new Tag("PACKAGEDEF", 2);
        public static final Tag IMPORT = new Tag("IMPORT", 3);
        public static final Tag MODULEIMPORT = new Tag("MODULEIMPORT", 4);
        public static final Tag CLASSDEF = new Tag("CLASSDEF", 5);
        public static final Tag METHODDEF = new Tag("METHODDEF", 6);
        public static final Tag VARDEF = new Tag("VARDEF", 7);
        public static final Tag SKIP = new Tag("SKIP", 8);
        public static final Tag BLOCK = new Tag("BLOCK", 9);
        public static final Tag DOLOOP = new Tag("DOLOOP", 10);
        public static final Tag WHILELOOP = new Tag("WHILELOOP", 11);
        public static final Tag FORLOOP = new Tag("FORLOOP", 12);
        public static final Tag FOREACHLOOP = new Tag("FOREACHLOOP", 13);
        public static final Tag LABELLED = new Tag("LABELLED", 14);
        public static final Tag SWITCH = new Tag("SWITCH", 15);
        public static final Tag CASE = new Tag("CASE", 16);
        public static final Tag SWITCH_EXPRESSION = new Tag("SWITCH_EXPRESSION", 17);
        public static final Tag SYNCHRONIZED = new Tag("SYNCHRONIZED", 18);
        public static final Tag TRY = new Tag("TRY", 19);
        public static final Tag CATCH = new Tag("CATCH", 20);
        public static final Tag CONDEXPR = new Tag("CONDEXPR", 21);
        public static final Tag IF = new Tag("IF", 22);
        public static final Tag EXEC = new Tag("EXEC", 23);
        public static final Tag BREAK = new Tag("BREAK", 24);
        public static final Tag YIELD = new Tag("YIELD", 25);
        public static final Tag CONTINUE = new Tag("CONTINUE", 26);
        public static final Tag RETURN = new Tag("RETURN", 27);
        public static final Tag THROW = new Tag("THROW", 28);
        public static final Tag ASSERT = new Tag("ASSERT", 29);
        public static final Tag APPLY = new Tag("APPLY", 30);
        public static final Tag NEWCLASS = new Tag("NEWCLASS", 31);
        public static final Tag NEWARRAY = new Tag("NEWARRAY", 32);
        public static final Tag LAMBDA = new Tag("LAMBDA", 33);
        public static final Tag PARENS = new Tag("PARENS", 34);
        public static final Tag ASSIGN = new Tag("ASSIGN", 35);
        public static final Tag TYPECAST = new Tag("TYPECAST", 36);
        public static final Tag TYPETEST = new Tag("TYPETEST", 37);
        public static final Tag ANYPATTERN = new Tag("ANYPATTERN", 38);
        public static final Tag BINDINGPATTERN = new Tag("BINDINGPATTERN", 39);
        public static final Tag RECORDPATTERN = new Tag("RECORDPATTERN", 40);
        public static final Tag DEFAULTCASELABEL = new Tag("DEFAULTCASELABEL", 41);
        public static final Tag CONSTANTCASELABEL = new Tag("CONSTANTCASELABEL", 42);
        public static final Tag PATTERNCASELABEL = new Tag("PATTERNCASELABEL", 43);
        public static final Tag INDEXED = new Tag("INDEXED", 44);
        public static final Tag SELECT = new Tag("SELECT", 45);
        public static final Tag REFERENCE = new Tag("REFERENCE", 46);
        public static final Tag IDENT = new Tag("IDENT", 47);
        public static final Tag LITERAL = new Tag("LITERAL", 48);
        public static final Tag TYPEIDENT = new Tag("TYPEIDENT", 49);
        public static final Tag TYPEARRAY = new Tag("TYPEARRAY", 50);
        public static final Tag TYPEAPPLY = new Tag("TYPEAPPLY", 51);
        public static final Tag TYPEUNION = new Tag("TYPEUNION", 52);
        public static final Tag TYPEINTERSECTION = new Tag("TYPEINTERSECTION", 53);
        public static final Tag TYPEPARAMETER = new Tag("TYPEPARAMETER", 54);
        public static final Tag WILDCARD = new Tag("WILDCARD", 55);
        public static final Tag TYPEBOUNDKIND = new Tag("TYPEBOUNDKIND", 56);
        public static final Tag ANNOTATION = new Tag("ANNOTATION", 57);
        public static final Tag TYPE_ANNOTATION = new Tag("TYPE_ANNOTATION", 58);
        public static final Tag MODIFIERS = new Tag("MODIFIERS", 59);
        public static final Tag ANNOTATED_TYPE = new Tag("ANNOTATED_TYPE", 60);
        public static final Tag ERRONEOUS = new Tag("ERRONEOUS", 61);

        private static /* synthetic */ Tag[] $values() {
            return new Tag[]{NO_TAG, TOPLEVEL, PACKAGEDEF, IMPORT, MODULEIMPORT, CLASSDEF, METHODDEF, VARDEF, SKIP, BLOCK, DOLOOP, WHILELOOP, FORLOOP, FOREACHLOOP, LABELLED, SWITCH, CASE, SWITCH_EXPRESSION, SYNCHRONIZED, TRY, CATCH, CONDEXPR, IF, EXEC, BREAK, YIELD, CONTINUE, RETURN, THROW, ASSERT, APPLY, NEWCLASS, NEWARRAY, LAMBDA, PARENS, ASSIGN, TYPECAST, TYPETEST, ANYPATTERN, BINDINGPATTERN, RECORDPATTERN, DEFAULTCASELABEL, CONSTANTCASELABEL, PATTERNCASELABEL, INDEXED, SELECT, REFERENCE, IDENT, LITERAL, TYPEIDENT, TYPEARRAY, TYPEAPPLY, TYPEUNION, TYPEINTERSECTION, TYPEPARAMETER, WILDCARD, TYPEBOUNDKIND, ANNOTATION, TYPE_ANNOTATION, MODIFIERS, ANNOTATED_TYPE, ERRONEOUS, POS, NEG, NOT, COMPL, PREINC, PREDEC, POSTINC, POSTDEC, NULLCHK, OR, AND, BITOR, BITXOR, BITAND, EQ, NE, LT, GT, LE, GE, SL, SR, USR, PLUS, MINUS, MUL, DIV, MOD, BITOR_ASG, BITXOR_ASG, BITAND_ASG, SL_ASG, SR_ASG, USR_ASG, PLUS_ASG, MINUS_ASG, MUL_ASG, DIV_ASG, MOD_ASG, MODULEDEF, EXPORTS, OPENS, PROVIDES, REQUIRES, USES, LETEXPR};
        }

        static {
            Tag tag = new Tag("POS", 62);
            POS = tag;
            NEG = new Tag("NEG", 63);
            NOT = new Tag("NOT", 64);
            COMPL = new Tag("COMPL", 65);
            PREINC = new Tag("PREINC", 66);
            PREDEC = new Tag("PREDEC", 67);
            POSTINC = new Tag("POSTINC", 68);
            POSTDEC = new Tag("POSTDEC", 69);
            NULLCHK = new Tag("NULLCHK", 70);
            OR = new Tag("OR", 71);
            AND = new Tag("AND", 72);
            Tag tag2 = new Tag("BITOR", 73);
            BITOR = tag2;
            Tag tag3 = new Tag("BITXOR", 74);
            BITXOR = tag3;
            Tag tag4 = new Tag("BITAND", 75);
            BITAND = tag4;
            EQ = new Tag("EQ", 76);
            NE = new Tag("NE", 77);
            LT = new Tag("LT", 78);
            GT = new Tag("GT", 79);
            LE = new Tag("LE", 80);
            GE = new Tag("GE", 81);
            Tag tag5 = new Tag("SL", 82);
            SL = tag5;
            Tag tag6 = new Tag("SR", 83);
            SR = tag6;
            Tag tag7 = new Tag("USR", 84);
            USR = tag7;
            Tag tag8 = new Tag("PLUS", 85);
            PLUS = tag8;
            Tag tag9 = new Tag("MINUS", 86);
            MINUS = tag9;
            Tag tag10 = new Tag("MUL", 87);
            MUL = tag10;
            Tag tag11 = new Tag("DIV", 88);
            DIV = tag11;
            Tag tag12 = new Tag("MOD", 89);
            MOD = tag12;
            BITOR_ASG = new Tag("BITOR_ASG", 90, tag2);
            BITXOR_ASG = new Tag("BITXOR_ASG", 91, tag3);
            BITAND_ASG = new Tag("BITAND_ASG", 92, tag4);
            SL_ASG = new Tag("SL_ASG", 93, tag5);
            SR_ASG = new Tag("SR_ASG", 94, tag6);
            USR_ASG = new Tag("USR_ASG", 95, tag7);
            PLUS_ASG = new Tag("PLUS_ASG", 96, tag8);
            MINUS_ASG = new Tag("MINUS_ASG", 97, tag9);
            MUL_ASG = new Tag("MUL_ASG", 98, tag10);
            DIV_ASG = new Tag("DIV_ASG", 99, tag11);
            MOD_ASG = new Tag("MOD_ASG", 100, tag12);
            MODULEDEF = new Tag("MODULEDEF", 101);
            EXPORTS = new Tag("EXPORTS", 102);
            OPENS = new Tag("OPENS", 103);
            PROVIDES = new Tag("PROVIDES", 104);
            REQUIRES = new Tag("REQUIRES", 105);
            USES = new Tag("USES", 106);
            LETEXPR = new Tag("LETEXPR", 107);
            $VALUES = $values();
            numberOfOperators = (tag12.ordinal() - tag.ordinal()) + 1;
        }

        private Tag(String str, int i, Tag tag) {
            super(str, i);
            this.noAssignTag = tag;
        }

        public static int getNumberOfOperators() {
            return numberOfOperators;
        }

        public static Tag valueOf(String str) {
            return (Tag) Enum.valueOf(Tag.class, str);
        }

        public static Tag[] values() {
            return (Tag[]) $VALUES.clone();
        }

        public boolean isAssignop() {
            return this.noAssignTag != null;
        }

        public boolean isIncOrDecUnaryOp() {
            return this == PREINC || this == PREDEC || this == POSTINC || this == POSTDEC;
        }

        public boolean isPostUnaryOp() {
            return this == POSTINC || this == POSTDEC;
        }

        public Tag noAssignOp() {
            Tag tag = this.noAssignTag;
            if (tag != null) {
                return tag;
            }
            x01.a("noAssignOp() method is not available for non assignment tags");
            return null;
        }

        public int operatorIndex() {
            return ordinal() - POS.ordinal();
        }

        private Tag(String str, int i) {
            this(str, i, null);
        }
    }

    public static class LetExpr extends JCExpression {
        public List<JCStatement> defs;
        public JCExpression expr;
        public boolean needsCond;

        public LetExpr(List<JCStatement> list, JCExpression jCExpression) {
            this.defs = list;
            this.expr = jCExpression;
        }

        @Override // com.sun.tools.javac.tree.JCTree, com.sun.source.tree.Tree
        public <R, D> R accept(TreeVisitor<R, D> treeVisitor, D d) {
            throw new AssertionError("LetExpr is not part of a public API");
        }

        @Override // com.sun.source.tree.Tree
        public Tree.Kind getKind() {
            throw new AssertionError("LetExpr is not part of a public API");
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public Tag getTag() {
            return Tag.LETEXPR;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public void accept(Visitor visitor) {
            visitor.visitLetExpr(this);
        }
    }

    public static class TypeBoundKind extends JCTree {
        public BoundKind kind;

        public TypeBoundKind(BoundKind boundKind) {
            this.kind = boundKind;
        }

        @Override // com.sun.tools.javac.tree.JCTree, com.sun.source.tree.Tree
        public <R, D> R accept(TreeVisitor<R, D> treeVisitor, D d) {
            throw new AssertionError("TypeBoundKind is not part of a public API");
        }

        @Override // com.sun.source.tree.Tree
        public Tree.Kind getKind() {
            throw new AssertionError("TypeBoundKind is not part of a public API");
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public Tag getTag() {
            return Tag.TYPEBOUNDKIND;
        }

        @Override // com.sun.tools.javac.tree.JCTree
        public void accept(Visitor visitor) {
            visitor.visitTypeBoundKind(this);
        }
    }
}
