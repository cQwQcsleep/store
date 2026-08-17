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
import com.sun.source.tree.DoWhileLoopTree;
import com.sun.source.tree.EmptyStatementTree;
import com.sun.source.tree.EnhancedForLoopTree;
import com.sun.source.tree.ErroneousTree;
import com.sun.source.tree.ExportsTree;
import com.sun.source.tree.ExpressionStatementTree;
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
import com.sun.source.tree.PrimitiveTypeTree;
import com.sun.source.tree.ProvidesTree;
import com.sun.source.tree.RequiresTree;
import com.sun.source.tree.ReturnTree;
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
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.ListBuffer;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class TreeCopier<P> implements TreeVisitor<JCTree, P> {
    private TreeMaker M;

    /* JADX INFO: renamed from: com.sun.tools.javac.tree.TreeCopier$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag;

        static {
            int[] iArr = new int[JCTree.Tag.values().length];
            $SwitchMap$com$sun$tools$javac$tree$JCTree$Tag = iArr;
            try {
                iArr[JCTree.Tag.LETEXPR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public TreeCopier(TreeMaker treeMaker) {
        this.M = treeMaker;
    }

    public <T extends JCTree> List<T> copy(List<T> list, P p) {
        if (list == null || list.isEmpty()) {
            return list;
        }
        ListBuffer listBuffer = new ListBuffer();
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            listBuffer.append(copy(it.next(), p));
        }
        return listBuffer.toList();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sun.source.tree.TreeVisitor
    public JCTree visitAnnotatedType(AnnotatedTypeTree annotatedTypeTree, P p) {
        JCTree.JCAnnotatedType jCAnnotatedType = (JCTree.JCAnnotatedType) annotatedTypeTree;
        return this.M.at(jCAnnotatedType.pos).AnnotatedType(copy(jCAnnotatedType.annotations, p), (JCTree.JCExpression) copy(jCAnnotatedType.underlyingType, p));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sun.source.tree.TreeVisitor
    public JCTree visitAnnotation(AnnotationTree annotationTree, P p) {
        JCTree.JCAnnotation jCAnnotation = (JCTree.JCAnnotation) annotationTree;
        JCTree jCTreeCopy = copy(jCAnnotation.annotationType, p);
        List<T> listCopy = copy(jCAnnotation.args, p);
        Tree.Kind kind = jCAnnotation.getKind();
        Tree.Kind kind2 = Tree.Kind.TYPE_ANNOTATION;
        TreeMaker treeMaker = this.M;
        if (kind == kind2) {
            JCTree.JCAnnotation jCAnnotationTypeAnnotation = treeMaker.at(jCAnnotation.pos).TypeAnnotation(jCTreeCopy, listCopy);
            jCAnnotationTypeAnnotation.attribute = jCAnnotation.attribute;
            return jCAnnotationTypeAnnotation;
        }
        JCTree.JCAnnotation jCAnnotationAnnotation = treeMaker.at(jCAnnotation.pos).Annotation(jCTreeCopy, listCopy);
        jCAnnotationAnnotation.attribute = jCAnnotation.attribute;
        return jCAnnotationAnnotation;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.sun.source.tree.TreeVisitor
    public JCTree visitAnyPattern(AnyPatternTree anyPatternTree, P p) {
        return this.M.at(((JCTree.JCAnyPattern) anyPatternTree).pos).AnyPattern();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.sun.source.tree.TreeVisitor
    public JCTree visitArrayAccess(ArrayAccessTree arrayAccessTree, P p) {
        JCTree.JCArrayAccess jCArrayAccess = (JCTree.JCArrayAccess) arrayAccessTree;
        return this.M.at(jCArrayAccess.pos).Indexed((JCTree.JCExpression) copy(jCArrayAccess.indexed, p), (JCTree.JCExpression) copy(jCArrayAccess.index, p));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.sun.source.tree.TreeVisitor
    public JCTree visitArrayType(ArrayTypeTree arrayTypeTree, P p) {
        JCTree.JCArrayTypeTree jCArrayTypeTree = (JCTree.JCArrayTypeTree) arrayTypeTree;
        return this.M.at(jCArrayTypeTree.pos).TypeArray((JCTree.JCExpression) copy(jCArrayTypeTree.elemtype, p));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.sun.source.tree.TreeVisitor
    public JCTree visitAssert(AssertTree assertTree, P p) {
        JCTree.JCAssert jCAssert = (JCTree.JCAssert) assertTree;
        return this.M.at(jCAssert.pos).Assert((JCTree.JCExpression) copy(jCAssert.cond, p), (JCTree.JCExpression) copy(jCAssert.detail, p));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.sun.source.tree.TreeVisitor
    public JCTree visitAssignment(AssignmentTree assignmentTree, P p) {
        JCTree.JCAssign jCAssign = (JCTree.JCAssign) assignmentTree;
        return this.M.at(jCAssign.pos).Assign((JCTree.JCExpression) copy(jCAssign.lhs, p), (JCTree.JCExpression) copy(jCAssign.rhs, p));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.sun.source.tree.TreeVisitor
    public JCTree visitBinary(BinaryTree binaryTree, P p) {
        JCTree.JCBinary jCBinary = (JCTree.JCBinary) binaryTree;
        return this.M.at(jCBinary.pos).Binary(jCBinary.getTag(), (JCTree.JCExpression) copy(jCBinary.lhs, p), (JCTree.JCExpression) copy(jCBinary.rhs, p));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.sun.source.tree.TreeVisitor
    public JCTree visitBindingPattern(BindingPatternTree bindingPatternTree, P p) {
        JCTree.JCBindingPattern jCBindingPattern = (JCTree.JCBindingPattern) bindingPatternTree;
        return this.M.at(jCBindingPattern.pos).BindingPattern((JCTree.JCVariableDecl) copy(jCBindingPattern.var, p));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sun.source.tree.TreeVisitor
    public JCTree visitBlock(BlockTree blockTree, P p) {
        JCTree.JCBlock jCBlock = (JCTree.JCBlock) blockTree;
        return this.M.at(jCBlock.pos).Block(jCBlock.flags, copy(jCBlock.stats, p));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.sun.source.tree.TreeVisitor
    public JCTree visitBreak(BreakTree breakTree, P p) {
        JCTree.JCBreak jCBreak = (JCTree.JCBreak) breakTree;
        return this.M.at(jCBreak.pos).Break(jCBreak.label);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sun.source.tree.TreeVisitor
    public JCTree visitCase(CaseTree caseTree, P p) {
        JCTree.JCStatement jCStatement;
        JCTree.JCCase jCCase = (JCTree.JCCase) caseTree;
        List<T> listCopy = copy(jCCase.labels, p);
        JCTree.JCExpression jCExpression = (JCTree.JCExpression) copy(jCCase.guard, p);
        List<T> listCopy2 = copy(jCCase.stats, p);
        if (caseTree.getCaseKind() == CaseTree.CaseKind.RULE) {
            jCStatement = ((jCCase.body instanceof JCTree.JCExpression) && jCCase.stats.head.hasTag(JCTree.Tag.YIELD)) ? ((JCTree.JCYield) jCCase.stats.head).value : jCCase.stats.head;
        } else {
            jCStatement = null;
        }
        return this.M.at(jCCase.pos).Case(jCCase.caseKind, listCopy, jCExpression, listCopy2, jCStatement);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.sun.source.tree.TreeVisitor
    public JCTree visitCatch(CatchTree catchTree, P p) {
        JCTree.JCCatch jCCatch = (JCTree.JCCatch) catchTree;
        return this.M.at(jCCatch.pos).Catch((JCTree.JCVariableDecl) copy(jCCatch.param, p), (JCTree.JCBlock) copy(jCCatch.body, p));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sun.source.tree.TreeVisitor
    public JCTree visitClass(ClassTree classTree, P p) {
        JCTree.JCClassDecl jCClassDecl = (JCTree.JCClassDecl) classTree;
        return this.M.at(jCClassDecl.pos).ClassDef((JCTree.JCModifiers) copy(jCClassDecl.mods, p), jCClassDecl.name, copy(jCClassDecl.typarams, p), (JCTree.JCExpression) copy(jCClassDecl.extending, p), copy(jCClassDecl.implementing, p), copy(jCClassDecl.defs, p));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.sun.source.tree.TreeVisitor
    public JCTree visitCompilationUnit(CompilationUnitTree compilationUnitTree, P p) {
        JCTree.JCCompilationUnit jCCompilationUnit = (JCTree.JCCompilationUnit) compilationUnitTree;
        return this.M.at(jCCompilationUnit.pos).TopLevel(copy(jCCompilationUnit.defs, p));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.sun.source.tree.TreeVisitor
    public JCTree visitCompoundAssignment(CompoundAssignmentTree compoundAssignmentTree, P p) {
        JCTree.JCAssignOp jCAssignOp = (JCTree.JCAssignOp) compoundAssignmentTree;
        return this.M.at(jCAssignOp.pos).Assignop(jCAssignOp.getTag(), copy(jCAssignOp.lhs, p), copy(jCAssignOp.rhs, p));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.sun.source.tree.TreeVisitor
    public JCTree visitConditionalExpression(ConditionalExpressionTree conditionalExpressionTree, P p) {
        JCTree.JCConditional jCConditional = (JCTree.JCConditional) conditionalExpressionTree;
        return this.M.at(jCConditional.pos).Conditional((JCTree.JCExpression) copy(jCConditional.cond, p), (JCTree.JCExpression) copy(jCConditional.truepart, p), (JCTree.JCExpression) copy(jCConditional.falsepart, p));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.sun.source.tree.TreeVisitor
    public JCTree visitConstantCaseLabel(ConstantCaseLabelTree constantCaseLabelTree, P p) {
        JCTree.JCConstantCaseLabel jCConstantCaseLabel = (JCTree.JCConstantCaseLabel) constantCaseLabelTree;
        return this.M.at(jCConstantCaseLabel.pos).ConstantCaseLabel((JCTree.JCExpression) copy(jCConstantCaseLabel.expr, p));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.sun.source.tree.TreeVisitor
    public JCTree visitContinue(ContinueTree continueTree, P p) {
        JCTree.JCContinue jCContinue = (JCTree.JCContinue) continueTree;
        return this.M.at(jCContinue.pos).Continue(jCContinue.label);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sun.source.tree.TreeVisitor
    public JCTree visitDeconstructionPattern(DeconstructionPatternTree deconstructionPatternTree, P p) {
        JCTree.JCRecordPattern jCRecordPattern = (JCTree.JCRecordPattern) deconstructionPatternTree;
        return this.M.at(jCRecordPattern.pos).RecordPattern((JCTree.JCExpression) copy(jCRecordPattern.deconstructor, p), copy(jCRecordPattern.nested, p));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.sun.source.tree.TreeVisitor
    public JCTree visitDefaultCaseLabel(DefaultCaseLabelTree defaultCaseLabelTree, P p) {
        return this.M.at(((JCTree.JCDefaultCaseLabel) defaultCaseLabelTree).pos).DefaultCaseLabel();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.sun.source.tree.TreeVisitor
    public JCTree visitDoWhileLoop(DoWhileLoopTree doWhileLoopTree, P p) {
        JCTree.JCDoWhileLoop jCDoWhileLoop = (JCTree.JCDoWhileLoop) doWhileLoopTree;
        return this.M.at(jCDoWhileLoop.pos).DoLoop((JCTree.JCStatement) copy(jCDoWhileLoop.body, p), (JCTree.JCExpression) copy(jCDoWhileLoop.cond, p));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.sun.source.tree.TreeVisitor
    public JCTree visitEmptyStatement(EmptyStatementTree emptyStatementTree, P p) {
        return this.M.at(((JCTree.JCSkip) emptyStatementTree).pos).Skip();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.sun.source.tree.TreeVisitor
    public JCTree visitEnhancedForLoop(EnhancedForLoopTree enhancedForLoopTree, P p) {
        JCTree.JCEnhancedForLoop jCEnhancedForLoop = (JCTree.JCEnhancedForLoop) enhancedForLoopTree;
        return this.M.at(jCEnhancedForLoop.pos).ForeachLoop((JCTree.JCVariableDecl) copy(jCEnhancedForLoop.var, p), (JCTree.JCExpression) copy(jCEnhancedForLoop.expr, p), (JCTree.JCStatement) copy(jCEnhancedForLoop.body, p));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.sun.source.tree.TreeVisitor
    public JCTree visitErroneous(ErroneousTree erroneousTree, P p) {
        JCTree.JCErroneous jCErroneous = (JCTree.JCErroneous) erroneousTree;
        return this.M.at(jCErroneous.pos).Erroneous(copy(jCErroneous.errs, p));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sun.source.tree.TreeVisitor
    public JCTree visitExports(ExportsTree exportsTree, P p) {
        JCTree.JCExports jCExports = (JCTree.JCExports) exportsTree;
        return this.M.at(jCExports.pos).Exports((JCTree.JCExpression) copy(jCExports.qualid, p), copy(jCExports.moduleNames, p));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.sun.source.tree.TreeVisitor
    public JCTree visitExpressionStatement(ExpressionStatementTree expressionStatementTree, P p) {
        JCTree.JCExpressionStatement jCExpressionStatement = (JCTree.JCExpressionStatement) expressionStatementTree;
        return this.M.at(jCExpressionStatement.pos).Exec((JCTree.JCExpression) copy(jCExpressionStatement.expr, p));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sun.source.tree.TreeVisitor
    public JCTree visitForLoop(ForLoopTree forLoopTree, P p) {
        JCTree.JCForLoop jCForLoop = (JCTree.JCForLoop) forLoopTree;
        return this.M.at(jCForLoop.pos).ForLoop(copy(jCForLoop.init, p), (JCTree.JCExpression) copy(jCForLoop.cond, p), copy(jCForLoop.step, p), (JCTree.JCStatement) copy(jCForLoop.body, p));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.sun.source.tree.TreeVisitor
    public JCTree visitIdentifier(IdentifierTree identifierTree, P p) {
        JCTree.JCIdent jCIdent = (JCTree.JCIdent) identifierTree;
        return this.M.at(jCIdent.pos).Ident(jCIdent.name);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.sun.source.tree.TreeVisitor
    public JCTree visitIf(IfTree ifTree, P p) {
        JCTree.JCIf jCIf = (JCTree.JCIf) ifTree;
        return this.M.at(jCIf.pos).If((JCTree.JCExpression) copy(jCIf.cond, p), (JCTree.JCStatement) copy(jCIf.thenpart, p), (JCTree.JCStatement) copy(jCIf.elsepart, p));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.sun.source.tree.TreeVisitor
    public JCTree visitImport(ImportTree importTree, P p) {
        if (importTree instanceof JCTree.JCModuleImport) {
            JCTree.JCModuleImport jCModuleImport = (JCTree.JCModuleImport) importTree;
            return this.M.at(jCModuleImport.pos).ModuleImport((JCTree.JCExpression) copy(jCModuleImport.module, p));
        }
        JCTree.JCImport jCImport = (JCTree.JCImport) importTree;
        return this.M.at(jCImport.pos).Import((JCTree.JCFieldAccess) copy(jCImport.qualid, p), jCImport.staticImport);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.sun.source.tree.TreeVisitor
    public JCTree visitInstanceOf(InstanceOfTree instanceOfTree, P p) {
        JCTree.JCInstanceOf jCInstanceOf = (JCTree.JCInstanceOf) instanceOfTree;
        return this.M.at(jCInstanceOf.pos).TypeTest((JCTree.JCExpression) copy(jCInstanceOf.expr, p), copy(jCInstanceOf.pattern, p));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sun.source.tree.TreeVisitor
    public JCTree visitIntersectionType(IntersectionTypeTree intersectionTypeTree, P p) {
        JCTree.JCTypeIntersection jCTypeIntersection = (JCTree.JCTypeIntersection) intersectionTypeTree;
        return this.M.at(jCTypeIntersection.pos).TypeIntersection(copy(jCTypeIntersection.bounds, p));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.sun.source.tree.TreeVisitor
    public JCTree visitLabeledStatement(LabeledStatementTree labeledStatementTree, P p) {
        JCTree.JCLabeledStatement jCLabeledStatement = (JCTree.JCLabeledStatement) labeledStatementTree;
        return this.M.at(jCLabeledStatement.pos).Labelled(jCLabeledStatement.label, (JCTree.JCStatement) copy(jCLabeledStatement.body, p));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sun.source.tree.TreeVisitor
    public JCTree visitLambdaExpression(LambdaExpressionTree lambdaExpressionTree, P p) {
        JCTree.JCLambda jCLambda = (JCTree.JCLambda) lambdaExpressionTree;
        return this.M.at(jCLambda.pos).Lambda(copy(jCLambda.params, p), copy(jCLambda.body, p));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.sun.source.tree.TreeVisitor
    public JCTree visitLiteral(LiteralTree literalTree, P p) {
        JCTree.JCLiteral jCLiteral = (JCTree.JCLiteral) literalTree;
        return this.M.at(jCLiteral.pos).Literal(jCLiteral.typetag, jCLiteral.value);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sun.source.tree.TreeVisitor
    public JCTree visitMemberReference(MemberReferenceTree memberReferenceTree, P p) {
        JCTree.JCMemberReference jCMemberReference = (JCTree.JCMemberReference) memberReferenceTree;
        return this.M.at(jCMemberReference.pos).Reference(jCMemberReference.mode, jCMemberReference.name, (JCTree.JCExpression) copy(jCMemberReference.expr, p), copy(jCMemberReference.typeargs, p));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.sun.source.tree.TreeVisitor
    public JCTree visitMemberSelect(MemberSelectTree memberSelectTree, P p) {
        JCTree.JCFieldAccess jCFieldAccess = (JCTree.JCFieldAccess) memberSelectTree;
        return this.M.at(jCFieldAccess.pos).Select((JCTree.JCExpression) copy(jCFieldAccess.selected, p), jCFieldAccess.name);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sun.source.tree.TreeVisitor
    public JCTree visitMethod(MethodTree methodTree, P p) {
        JCTree.JCMethodDecl jCMethodDecl = (JCTree.JCMethodDecl) methodTree;
        JCTree.JCModifiers jCModifiers = (JCTree.JCModifiers) copy(jCMethodDecl.mods, p);
        JCTree.JCExpression jCExpression = (JCTree.JCExpression) copy(jCMethodDecl.restype, p);
        List<T> listCopy = copy(jCMethodDecl.typarams, p);
        List<T> listCopy2 = copy(jCMethodDecl.params, p);
        return this.M.at(jCMethodDecl.pos).MethodDef(jCModifiers, jCMethodDecl.name, jCExpression, listCopy, (JCTree.JCVariableDecl) copy(jCMethodDecl.recvparam, p), listCopy2, copy(jCMethodDecl.thrown, p), (JCTree.JCBlock) copy(jCMethodDecl.body, p), (JCTree.JCExpression) copy(jCMethodDecl.defaultValue, p));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sun.source.tree.TreeVisitor
    public JCTree visitMethodInvocation(MethodInvocationTree methodInvocationTree, P p) {
        JCTree.JCMethodInvocation jCMethodInvocation = (JCTree.JCMethodInvocation) methodInvocationTree;
        return this.M.at(jCMethodInvocation.pos).Apply(copy(jCMethodInvocation.typeargs, p), (JCTree.JCExpression) copy(jCMethodInvocation.meth, p), copy(jCMethodInvocation.args, p));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sun.source.tree.TreeVisitor
    public JCTree visitModifiers(ModifiersTree modifiersTree, P p) {
        JCTree.JCModifiers jCModifiers = (JCTree.JCModifiers) modifiersTree;
        return this.M.at(jCModifiers.pos).Modifiers(jCModifiers.flags, copy(jCModifiers.annotations, p));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sun.source.tree.TreeVisitor
    public JCTree visitModule(ModuleTree moduleTree, P p) {
        JCTree.JCModuleDecl jCModuleDecl = (JCTree.JCModuleDecl) moduleTree;
        return this.M.at(jCModuleDecl.pos).ModuleDef((JCTree.JCModifiers) copy(jCModuleDecl.mods, p), jCModuleDecl.getModuleType(), (JCTree.JCExpression) copy(jCModuleDecl.qualId), copy(jCModuleDecl.directives));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sun.source.tree.TreeVisitor
    public JCTree visitNewArray(NewArrayTree newArrayTree, P p) {
        JCTree.JCNewArray jCNewArray = (JCTree.JCNewArray) newArrayTree;
        return this.M.at(jCNewArray.pos).NewArray((JCTree.JCExpression) copy(jCNewArray.elemtype, p), copy(jCNewArray.dims, p), copy(jCNewArray.elems, p));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sun.source.tree.TreeVisitor
    public JCTree visitNewClass(NewClassTree newClassTree, P p) {
        JCTree.JCNewClass jCNewClass = (JCTree.JCNewClass) newClassTree;
        return this.M.at(jCNewClass.pos).NewClass((JCTree.JCExpression) copy(jCNewClass.encl, p), copy(jCNewClass.typeargs, p), (JCTree.JCExpression) copy(jCNewClass.clazz, p), copy(jCNewClass.args, p), (JCTree.JCClassDecl) copy(jCNewClass.def, p));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sun.source.tree.TreeVisitor
    public JCTree visitOpens(OpensTree opensTree, P p) {
        JCTree.JCOpens jCOpens = (JCTree.JCOpens) opensTree;
        return this.M.at(jCOpens.pos).Opens((JCTree.JCExpression) copy(jCOpens.qualid, p), copy(jCOpens.moduleNames, p));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sun.source.tree.TreeVisitor
    public JCTree visitOther(Tree tree, P p) {
        JCTree jCTree = (JCTree) tree;
        if (AnonymousClass1.$SwitchMap$com$sun$tools$javac$tree$JCTree$Tag[jCTree.getTag().ordinal()] != 1) {
            pe1.a("unknown tree tag: ", jCTree.getTag());
            return null;
        }
        JCTree.LetExpr letExpr = (JCTree.LetExpr) tree;
        return this.M.at(letExpr.pos).LetExpr((List<JCTree.JCStatement>) copy(letExpr.defs, p), (JCTree.JCExpression) copy(letExpr.expr, p));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sun.source.tree.TreeVisitor
    public JCTree visitPackage(PackageTree packageTree, P p) {
        JCTree.JCPackageDecl jCPackageDecl = (JCTree.JCPackageDecl) packageTree;
        return this.M.at(jCPackageDecl.pos).PackageDecl(copy(jCPackageDecl.annotations, p), (JCTree.JCExpression) copy(jCPackageDecl.pid, p));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sun.source.tree.TreeVisitor
    public JCTree visitParameterizedType(ParameterizedTypeTree parameterizedTypeTree, P p) {
        JCTree.JCTypeApply jCTypeApply = (JCTree.JCTypeApply) parameterizedTypeTree;
        return this.M.at(jCTypeApply.pos).TypeApply((JCTree.JCExpression) copy(jCTypeApply.clazz, p), copy(jCTypeApply.arguments, p));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.sun.source.tree.TreeVisitor
    public JCTree visitParenthesized(ParenthesizedTree parenthesizedTree, P p) {
        JCTree.JCParens jCParens = (JCTree.JCParens) parenthesizedTree;
        return this.M.at(jCParens.pos).Parens((JCTree.JCExpression) copy(jCParens.expr, p));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.sun.source.tree.TreeVisitor
    public JCTree visitPatternCaseLabel(PatternCaseLabelTree patternCaseLabelTree, P p) {
        JCTree.JCPatternCaseLabel jCPatternCaseLabel = (JCTree.JCPatternCaseLabel) patternCaseLabelTree;
        return this.M.at(jCPatternCaseLabel.pos).PatternCaseLabel((JCTree.JCPattern) copy(jCPatternCaseLabel.pat, p));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.sun.source.tree.TreeVisitor
    public JCTree visitPrimitiveType(PrimitiveTypeTree primitiveTypeTree, P p) {
        JCTree.JCPrimitiveTypeTree jCPrimitiveTypeTree = (JCTree.JCPrimitiveTypeTree) primitiveTypeTree;
        return this.M.at(jCPrimitiveTypeTree.pos).TypeIdent(jCPrimitiveTypeTree.typetag);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sun.source.tree.TreeVisitor
    public JCTree visitProvides(ProvidesTree providesTree, P p) {
        JCTree.JCProvides jCProvides = (JCTree.JCProvides) providesTree;
        return this.M.at(jCProvides.pos).Provides((JCTree.JCExpression) copy(jCProvides.serviceName, p), copy(jCProvides.implNames, p));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.sun.source.tree.TreeVisitor
    public JCTree visitRequires(RequiresTree requiresTree, P p) {
        JCTree.JCRequires jCRequires = (JCTree.JCRequires) requiresTree;
        return this.M.at(jCRequires.pos).Requires(jCRequires.isTransitive, jCRequires.isStaticPhase, (JCTree.JCExpression) copy(jCRequires.moduleName, p));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.sun.source.tree.TreeVisitor
    public JCTree visitReturn(ReturnTree returnTree, P p) {
        JCTree.JCReturn jCReturn = (JCTree.JCReturn) returnTree;
        return this.M.at(jCReturn.pos).Return((JCTree.JCExpression) copy(jCReturn.expr, p));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sun.source.tree.TreeVisitor
    public JCTree visitSwitch(SwitchTree switchTree, P p) {
        JCTree.JCSwitch jCSwitch = (JCTree.JCSwitch) switchTree;
        return this.M.at(jCSwitch.pos).Switch((JCTree.JCExpression) copy(jCSwitch.selector, p), copy(jCSwitch.cases, p));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sun.source.tree.TreeVisitor
    public JCTree visitSwitchExpression(SwitchExpressionTree switchExpressionTree, P p) {
        JCTree.JCSwitchExpression jCSwitchExpression = (JCTree.JCSwitchExpression) switchExpressionTree;
        return this.M.at(jCSwitchExpression.pos).SwitchExpression((JCTree.JCExpression) copy(jCSwitchExpression.selector, p), copy(jCSwitchExpression.cases, p));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.sun.source.tree.TreeVisitor
    public JCTree visitSynchronized(SynchronizedTree synchronizedTree, P p) {
        JCTree.JCSynchronized jCSynchronized = (JCTree.JCSynchronized) synchronizedTree;
        return this.M.at(jCSynchronized.pos).Synchronized((JCTree.JCExpression) copy(jCSynchronized.lock, p), (JCTree.JCBlock) copy(jCSynchronized.body, p));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.sun.source.tree.TreeVisitor
    public JCTree visitThrow(ThrowTree throwTree, P p) {
        JCTree.JCThrow jCThrow = (JCTree.JCThrow) throwTree;
        return this.M.at(jCThrow.pos).Throw((JCTree.JCExpression) copy(jCThrow.expr, p));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sun.source.tree.TreeVisitor
    public JCTree visitTry(TryTree tryTree, P p) {
        JCTree.JCTry jCTry = (JCTree.JCTry) tryTree;
        return this.M.at(jCTry.pos).Try(copy(jCTry.resources, p), (JCTree.JCBlock) copy(jCTry.body, p), copy(jCTry.catchers, p), (JCTree.JCBlock) copy(jCTry.finalizer, p));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.sun.source.tree.TreeVisitor
    public JCTree visitTypeCast(TypeCastTree typeCastTree, P p) {
        JCTree.JCTypeCast jCTypeCast = (JCTree.JCTypeCast) typeCastTree;
        return this.M.at(jCTypeCast.pos).TypeCast(copy(jCTypeCast.clazz, p), (JCTree.JCExpression) copy(jCTypeCast.expr, p));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sun.source.tree.TreeVisitor
    public JCTree visitTypeParameter(TypeParameterTree typeParameterTree, P p) {
        JCTree.JCTypeParameter jCTypeParameter = (JCTree.JCTypeParameter) typeParameterTree;
        List<T> listCopy = copy(jCTypeParameter.annotations, p);
        return this.M.at(jCTypeParameter.pos).TypeParameter(jCTypeParameter.name, copy(jCTypeParameter.bounds, p), listCopy);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.sun.source.tree.TreeVisitor
    public JCTree visitUnary(UnaryTree unaryTree, P p) {
        JCTree.JCUnary jCUnary = (JCTree.JCUnary) unaryTree;
        return this.M.at(jCUnary.pos).Unary(jCUnary.getTag(), (JCTree.JCExpression) copy(jCUnary.arg, p));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sun.source.tree.TreeVisitor
    public JCTree visitUnionType(UnionTypeTree unionTypeTree, P p) {
        JCTree.JCTypeUnion jCTypeUnion = (JCTree.JCTypeUnion) unionTypeTree;
        return this.M.at(jCTypeUnion.pos).TypeUnion(copy(jCTypeUnion.alternatives, p));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.sun.source.tree.TreeVisitor
    public JCTree visitUses(UsesTree usesTree, P p) {
        JCTree.JCUses jCUses = (JCTree.JCUses) usesTree;
        return this.M.at(jCUses.pos).Uses((JCTree.JCExpression) copy(jCUses.qualid, p));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.sun.source.tree.TreeVisitor
    public JCTree visitVariable(VariableTree variableTree, P p) {
        JCTree.JCVariableDecl jCVariableDecl = (JCTree.JCVariableDecl) variableTree;
        JCTree.JCModifiers jCModifiers = (JCTree.JCModifiers) copy(jCVariableDecl.mods, p);
        JCTree.JCExpression jCExpression = (JCTree.JCExpression) copy(jCVariableDecl.vartype, p);
        JCTree.JCExpression jCExpression2 = jCVariableDecl.nameexpr;
        if (jCExpression2 == null) {
            return this.M.at(jCVariableDecl.pos).VarDef(jCModifiers, jCVariableDecl.name, jCExpression, (JCTree.JCExpression) copy(jCVariableDecl.init, p), jCVariableDecl.declKind, jCVariableDecl.typePos);
        }
        return this.M.at(jCVariableDecl.pos).ReceiverVarDef(jCModifiers, (JCTree.JCExpression) copy(jCExpression2, p), jCExpression);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.sun.source.tree.TreeVisitor
    public JCTree visitWhileLoop(WhileLoopTree whileLoopTree, P p) {
        JCTree.JCWhileLoop jCWhileLoop = (JCTree.JCWhileLoop) whileLoopTree;
        JCTree.JCStatement jCStatement = (JCTree.JCStatement) copy(jCWhileLoop.body, p);
        return this.M.at(jCWhileLoop.pos).WhileLoop((JCTree.JCExpression) copy(jCWhileLoop.cond, p), jCStatement);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.sun.source.tree.TreeVisitor
    public JCTree visitWildcard(WildcardTree wildcardTree, P p) {
        JCTree.JCWildcard jCWildcard = (JCTree.JCWildcard) wildcardTree;
        return this.M.at(jCWildcard.pos).Wildcard(this.M.at(jCWildcard.kind.pos).TypeBoundKind(jCWildcard.kind.kind), copy(jCWildcard.inner, p));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.sun.source.tree.TreeVisitor
    public JCTree visitYield(YieldTree yieldTree, P p) {
        JCTree.JCYield jCYield = (JCTree.JCYield) yieldTree;
        return this.M.at(jCYield.pos).Yield((JCTree.JCExpression) copy(jCYield.value, p));
    }

    public <T extends JCTree> T copy(T t, P p) {
        if (t == null) {
            return null;
        }
        return (T) t.accept(this, p);
    }

    public <T extends JCTree> List<T> copy(List<T> list) {
        return copy(list, (Object) null);
    }

    public <T extends JCTree> T copy(T t) {
        return (T) copy(t, (Object) null);
    }
}
