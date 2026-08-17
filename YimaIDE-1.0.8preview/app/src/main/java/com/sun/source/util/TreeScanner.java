package com.sun.source.util;

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
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class TreeScanner<R, P> implements TreeVisitor<R, P> {
    private R scanAndReduce(Tree tree, P p, R r) {
        return reduce(scan(tree, p), r);
    }

    public R reduce(R r, R r2) {
        return r;
    }

    public R scan(Iterable<? extends Tree> iterable, P p) {
        R rScan = null;
        if (iterable != null) {
            boolean z = true;
            for (Tree tree : iterable) {
                rScan = z ? scan(tree, p) : scanAndReduce(tree, p, rScan);
                z = false;
            }
        }
        return rScan;
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitAnnotatedType(AnnotatedTypeTree annotatedTypeTree, P p) {
        return scanAndReduce(annotatedTypeTree.getUnderlyingType(), p, scan(annotatedTypeTree.getAnnotations(), p));
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitAnnotation(AnnotationTree annotationTree, P p) {
        return scanAndReduce(annotationTree.getArguments(), p, scan(annotationTree.getAnnotationType(), p));
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitAnyPattern(AnyPatternTree anyPatternTree, P p) {
        return null;
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitArrayAccess(ArrayAccessTree arrayAccessTree, P p) {
        return scanAndReduce(arrayAccessTree.getIndex(), p, scan(arrayAccessTree.getExpression(), p));
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitArrayType(ArrayTypeTree arrayTypeTree, P p) {
        return scan(arrayTypeTree.getType(), p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitAssert(AssertTree assertTree, P p) {
        return scanAndReduce(assertTree.getDetail(), p, scan(assertTree.getCondition(), p));
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitAssignment(AssignmentTree assignmentTree, P p) {
        return scanAndReduce(assignmentTree.getExpression(), p, scan(assignmentTree.getVariable(), p));
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitBinary(BinaryTree binaryTree, P p) {
        return scanAndReduce(binaryTree.getRightOperand(), p, scan(binaryTree.getLeftOperand(), p));
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitBindingPattern(BindingPatternTree bindingPatternTree, P p) {
        return scan(bindingPatternTree.getVariable(), p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitBlock(BlockTree blockTree, P p) {
        return scan(blockTree.getStatements(), p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitBreak(BreakTree breakTree, P p) {
        return null;
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitCase(CaseTree caseTree, P p) {
        R rScanAndReduce = scanAndReduce(caseTree.getGuard(), p, scan(caseTree.getLabels(), p));
        return caseTree.getCaseKind() == CaseTree.CaseKind.RULE ? scanAndReduce(caseTree.getBody(), p, rScanAndReduce) : scanAndReduce(caseTree.getStatements(), p, rScanAndReduce);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitCatch(CatchTree catchTree, P p) {
        return scanAndReduce(catchTree.getBlock(), p, scan(catchTree.getParameter(), p));
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitClass(ClassTree classTree, P p) {
        return scanAndReduce(classTree.getMembers(), p, scanAndReduce(classTree.getPermitsClause(), p, scanAndReduce(classTree.getImplementsClause(), p, scanAndReduce(classTree.getExtendsClause(), p, scanAndReduce(classTree.getTypeParameters(), p, scan(classTree.getModifiers(), p))))));
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitCompilationUnit(CompilationUnitTree compilationUnitTree, P p) {
        return scanAndReduce(compilationUnitTree.getModule(), p, scanAndReduce(compilationUnitTree.getTypeDecls(), p, scanAndReduce(compilationUnitTree.getImports(), p, scan(compilationUnitTree.getPackage(), p))));
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitCompoundAssignment(CompoundAssignmentTree compoundAssignmentTree, P p) {
        return scanAndReduce(compoundAssignmentTree.getExpression(), p, scan(compoundAssignmentTree.getVariable(), p));
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitConditionalExpression(ConditionalExpressionTree conditionalExpressionTree, P p) {
        return scanAndReduce(conditionalExpressionTree.getFalseExpression(), p, scanAndReduce(conditionalExpressionTree.getTrueExpression(), p, scan(conditionalExpressionTree.getCondition(), p)));
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitConstantCaseLabel(ConstantCaseLabelTree constantCaseLabelTree, P p) {
        return scan(constantCaseLabelTree.getConstantExpression(), p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitContinue(ContinueTree continueTree, P p) {
        return null;
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitDeconstructionPattern(DeconstructionPatternTree deconstructionPatternTree, P p) {
        return scanAndReduce(deconstructionPatternTree.getNestedPatterns(), p, scan(deconstructionPatternTree.getDeconstructor(), p));
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitDefaultCaseLabel(DefaultCaseLabelTree defaultCaseLabelTree, P p) {
        return null;
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitDoWhileLoop(DoWhileLoopTree doWhileLoopTree, P p) {
        return scanAndReduce(doWhileLoopTree.getCondition(), p, scan(doWhileLoopTree.getStatement(), p));
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitEmptyStatement(EmptyStatementTree emptyStatementTree, P p) {
        return null;
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitEnhancedForLoop(EnhancedForLoopTree enhancedForLoopTree, P p) {
        return scanAndReduce(enhancedForLoopTree.getStatement(), p, scanAndReduce(enhancedForLoopTree.getExpression(), p, scan(enhancedForLoopTree.getVariable(), p)));
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitErroneous(ErroneousTree erroneousTree, P p) {
        return null;
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitExports(ExportsTree exportsTree, P p) {
        return scanAndReduce(exportsTree.getModuleNames(), p, scan(exportsTree.getPackageName(), p));
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitExpressionStatement(ExpressionStatementTree expressionStatementTree, P p) {
        return scan(expressionStatementTree.getExpression(), p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitForLoop(ForLoopTree forLoopTree, P p) {
        return scanAndReduce(forLoopTree.getStatement(), p, scanAndReduce(forLoopTree.getUpdate(), p, scanAndReduce(forLoopTree.getCondition(), p, scan(forLoopTree.getInitializer(), p))));
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitIdentifier(IdentifierTree identifierTree, P p) {
        return null;
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitIf(IfTree ifTree, P p) {
        return scanAndReduce(ifTree.getElseStatement(), p, scanAndReduce(ifTree.getThenStatement(), p, scan(ifTree.getCondition(), p)));
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitImport(ImportTree importTree, P p) {
        return scan(importTree.getQualifiedIdentifier(), p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitInstanceOf(InstanceOfTree instanceOfTree, P p) {
        R rScan = scan(instanceOfTree.getExpression(), p);
        return instanceOfTree.getPattern() != null ? scanAndReduce(instanceOfTree.getPattern(), p, rScan) : scanAndReduce(instanceOfTree.getType(), p, rScan);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitIntersectionType(IntersectionTypeTree intersectionTypeTree, P p) {
        return scan(intersectionTypeTree.getBounds(), p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitLabeledStatement(LabeledStatementTree labeledStatementTree, P p) {
        return scan(labeledStatementTree.getStatement(), p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitLambdaExpression(LambdaExpressionTree lambdaExpressionTree, P p) {
        return scanAndReduce(lambdaExpressionTree.getBody(), p, scan(lambdaExpressionTree.getParameters(), p));
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitLiteral(LiteralTree literalTree, P p) {
        return null;
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitMemberReference(MemberReferenceTree memberReferenceTree, P p) {
        return scanAndReduce(memberReferenceTree.getTypeArguments(), p, scan(memberReferenceTree.getQualifierExpression(), p));
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitMemberSelect(MemberSelectTree memberSelectTree, P p) {
        return scan(memberSelectTree.getExpression(), p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitMethod(MethodTree methodTree, P p) {
        return scanAndReduce(methodTree.getDefaultValue(), p, scanAndReduce(methodTree.getBody(), p, scanAndReduce(methodTree.getThrows(), p, scanAndReduce(methodTree.getReceiverParameter(), p, scanAndReduce(methodTree.getParameters(), p, scanAndReduce(methodTree.getTypeParameters(), p, scanAndReduce(methodTree.getReturnType(), p, scan(methodTree.getModifiers(), p))))))));
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitMethodInvocation(MethodInvocationTree methodInvocationTree, P p) {
        return scanAndReduce(methodInvocationTree.getArguments(), p, scanAndReduce(methodInvocationTree.getMethodSelect(), p, scan(methodInvocationTree.getTypeArguments(), p)));
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitModifiers(ModifiersTree modifiersTree, P p) {
        return scan(modifiersTree.getAnnotations(), p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitModule(ModuleTree moduleTree, P p) {
        return scanAndReduce(moduleTree.getDirectives(), p, scanAndReduce(moduleTree.getName(), p, scan(moduleTree.getAnnotations(), p)));
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitNewArray(NewArrayTree newArrayTree, P p) {
        R rScanAndReduce = scanAndReduce(newArrayTree.getAnnotations(), p, scanAndReduce(newArrayTree.getInitializers(), p, scanAndReduce(newArrayTree.getDimensions(), p, scan(newArrayTree.getType(), p))));
        Iterator<? extends List<? extends AnnotationTree>> it = newArrayTree.getDimAnnotations().iterator();
        while (it.hasNext()) {
            rScanAndReduce = scanAndReduce(it.next(), p, rScanAndReduce);
        }
        return rScanAndReduce;
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitNewClass(NewClassTree newClassTree, P p) {
        return scanAndReduce(newClassTree.getClassBody(), p, scanAndReduce(newClassTree.getArguments(), p, scanAndReduce(newClassTree.getTypeArguments(), p, scanAndReduce(newClassTree.getIdentifier(), p, scan(newClassTree.getEnclosingExpression(), p)))));
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitOpens(OpensTree opensTree, P p) {
        return scanAndReduce(opensTree.getModuleNames(), p, scan(opensTree.getPackageName(), p));
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitOther(Tree tree, P p) {
        return null;
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitPackage(PackageTree packageTree, P p) {
        return scanAndReduce(packageTree.getPackageName(), p, scan(packageTree.getAnnotations(), p));
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitParameterizedType(ParameterizedTypeTree parameterizedTypeTree, P p) {
        return scanAndReduce(parameterizedTypeTree.getTypeArguments(), p, scan(parameterizedTypeTree.getType(), p));
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitParenthesized(ParenthesizedTree parenthesizedTree, P p) {
        return scan(parenthesizedTree.getExpression(), p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitPatternCaseLabel(PatternCaseLabelTree patternCaseLabelTree, P p) {
        return scan(patternCaseLabelTree.getPattern(), p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitPrimitiveType(PrimitiveTypeTree primitiveTypeTree, P p) {
        return null;
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitProvides(ProvidesTree providesTree, P p) {
        return scanAndReduce(providesTree.getImplementationNames(), p, scan(providesTree.getServiceName(), p));
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitRequires(RequiresTree requiresTree, P p) {
        return scan(requiresTree.getModuleName(), p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitReturn(ReturnTree returnTree, P p) {
        return scan(returnTree.getExpression(), p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitSwitch(SwitchTree switchTree, P p) {
        return scanAndReduce(switchTree.getCases(), p, scan(switchTree.getExpression(), p));
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitSwitchExpression(SwitchExpressionTree switchExpressionTree, P p) {
        return scanAndReduce(switchExpressionTree.getCases(), p, scan(switchExpressionTree.getExpression(), p));
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitSynchronized(SynchronizedTree synchronizedTree, P p) {
        return scanAndReduce(synchronizedTree.getBlock(), p, scan(synchronizedTree.getExpression(), p));
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitThrow(ThrowTree throwTree, P p) {
        return scan(throwTree.getExpression(), p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitTry(TryTree tryTree, P p) {
        return scanAndReduce(tryTree.getFinallyBlock(), p, scanAndReduce(tryTree.getCatches(), p, scanAndReduce(tryTree.getBlock(), p, scan(tryTree.getResources(), p))));
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitTypeCast(TypeCastTree typeCastTree, P p) {
        return scanAndReduce(typeCastTree.getExpression(), p, scan(typeCastTree.getType(), p));
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitTypeParameter(TypeParameterTree typeParameterTree, P p) {
        return scanAndReduce(typeParameterTree.getBounds(), p, scan(typeParameterTree.getAnnotations(), p));
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitUnary(UnaryTree unaryTree, P p) {
        return scan(unaryTree.getExpression(), p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitUnionType(UnionTypeTree unionTypeTree, P p) {
        return scan(unionTypeTree.getTypeAlternatives(), p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitUses(UsesTree usesTree, P p) {
        return scan(usesTree.getServiceName(), p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitVariable(VariableTree variableTree, P p) {
        return scanAndReduce(variableTree.getInitializer(), p, scanAndReduce(variableTree.getNameExpression(), p, scanAndReduce(variableTree.getType(), p, scan(variableTree.getModifiers(), p))));
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitWhileLoop(WhileLoopTree whileLoopTree, P p) {
        return scanAndReduce(whileLoopTree.getStatement(), p, scan(whileLoopTree.getCondition(), p));
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitWildcard(WildcardTree wildcardTree, P p) {
        return scan(wildcardTree.getBound(), p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitYield(YieldTree yieldTree, P p) {
        return scan(yieldTree.getValue(), p);
    }

    private R scanAndReduce(Iterable<? extends Tree> iterable, P p, R r) {
        return reduce(scan(iterable, p), r);
    }

    public R scan(Tree tree, P p) {
        if (tree == null) {
            return null;
        }
        return (R) tree.accept(this, p);
    }
}
