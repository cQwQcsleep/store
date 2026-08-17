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

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SimpleTreeVisitor<R, P> implements TreeVisitor<R, P> {
    protected final R DEFAULT_VALUE;

    public SimpleTreeVisitor() {
        this.DEFAULT_VALUE = null;
    }

    public R defaultAction(Tree tree, P p) {
        return this.DEFAULT_VALUE;
    }

    public final R visit(Iterable<? extends Tree> iterable, P p) {
        R rVisit = null;
        if (iterable != null) {
            Iterator<? extends Tree> it = iterable.iterator();
            while (it.hasNext()) {
                rVisit = visit(it.next(), p);
            }
        }
        return rVisit;
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitAnnotatedType(AnnotatedTypeTree annotatedTypeTree, P p) {
        return defaultAction(annotatedTypeTree, p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitAnnotation(AnnotationTree annotationTree, P p) {
        return defaultAction(annotationTree, p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitAnyPattern(AnyPatternTree anyPatternTree, P p) {
        return defaultAction(anyPatternTree, p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitArrayAccess(ArrayAccessTree arrayAccessTree, P p) {
        return defaultAction(arrayAccessTree, p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitArrayType(ArrayTypeTree arrayTypeTree, P p) {
        return defaultAction(arrayTypeTree, p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitAssert(AssertTree assertTree, P p) {
        return defaultAction(assertTree, p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitAssignment(AssignmentTree assignmentTree, P p) {
        return defaultAction(assignmentTree, p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitBinary(BinaryTree binaryTree, P p) {
        return defaultAction(binaryTree, p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitBindingPattern(BindingPatternTree bindingPatternTree, P p) {
        return defaultAction(bindingPatternTree, p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitBlock(BlockTree blockTree, P p) {
        return defaultAction(blockTree, p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitBreak(BreakTree breakTree, P p) {
        return defaultAction(breakTree, p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitCase(CaseTree caseTree, P p) {
        return defaultAction(caseTree, p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitCatch(CatchTree catchTree, P p) {
        return defaultAction(catchTree, p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitClass(ClassTree classTree, P p) {
        return defaultAction(classTree, p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitCompilationUnit(CompilationUnitTree compilationUnitTree, P p) {
        return defaultAction(compilationUnitTree, p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitCompoundAssignment(CompoundAssignmentTree compoundAssignmentTree, P p) {
        return defaultAction(compoundAssignmentTree, p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitConditionalExpression(ConditionalExpressionTree conditionalExpressionTree, P p) {
        return defaultAction(conditionalExpressionTree, p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitConstantCaseLabel(ConstantCaseLabelTree constantCaseLabelTree, P p) {
        return defaultAction(constantCaseLabelTree, p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitContinue(ContinueTree continueTree, P p) {
        return defaultAction(continueTree, p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitDeconstructionPattern(DeconstructionPatternTree deconstructionPatternTree, P p) {
        return defaultAction(deconstructionPatternTree, p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitDefaultCaseLabel(DefaultCaseLabelTree defaultCaseLabelTree, P p) {
        return defaultAction(defaultCaseLabelTree, p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitDoWhileLoop(DoWhileLoopTree doWhileLoopTree, P p) {
        return defaultAction(doWhileLoopTree, p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitEmptyStatement(EmptyStatementTree emptyStatementTree, P p) {
        return defaultAction(emptyStatementTree, p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitEnhancedForLoop(EnhancedForLoopTree enhancedForLoopTree, P p) {
        return defaultAction(enhancedForLoopTree, p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitErroneous(ErroneousTree erroneousTree, P p) {
        return defaultAction(erroneousTree, p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitExports(ExportsTree exportsTree, P p) {
        return defaultAction(exportsTree, p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitExpressionStatement(ExpressionStatementTree expressionStatementTree, P p) {
        return defaultAction(expressionStatementTree, p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitForLoop(ForLoopTree forLoopTree, P p) {
        return defaultAction(forLoopTree, p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitIdentifier(IdentifierTree identifierTree, P p) {
        return defaultAction(identifierTree, p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitIf(IfTree ifTree, P p) {
        return defaultAction(ifTree, p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitImport(ImportTree importTree, P p) {
        return defaultAction(importTree, p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitInstanceOf(InstanceOfTree instanceOfTree, P p) {
        return defaultAction(instanceOfTree, p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitIntersectionType(IntersectionTypeTree intersectionTypeTree, P p) {
        return defaultAction(intersectionTypeTree, p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitLabeledStatement(LabeledStatementTree labeledStatementTree, P p) {
        return defaultAction(labeledStatementTree, p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitLambdaExpression(LambdaExpressionTree lambdaExpressionTree, P p) {
        return defaultAction(lambdaExpressionTree, p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitLiteral(LiteralTree literalTree, P p) {
        return defaultAction(literalTree, p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitMemberReference(MemberReferenceTree memberReferenceTree, P p) {
        return defaultAction(memberReferenceTree, p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitMemberSelect(MemberSelectTree memberSelectTree, P p) {
        return defaultAction(memberSelectTree, p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitMethod(MethodTree methodTree, P p) {
        return defaultAction(methodTree, p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitMethodInvocation(MethodInvocationTree methodInvocationTree, P p) {
        return defaultAction(methodInvocationTree, p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitModifiers(ModifiersTree modifiersTree, P p) {
        return defaultAction(modifiersTree, p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitModule(ModuleTree moduleTree, P p) {
        return defaultAction(moduleTree, p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitNewArray(NewArrayTree newArrayTree, P p) {
        return defaultAction(newArrayTree, p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitNewClass(NewClassTree newClassTree, P p) {
        return defaultAction(newClassTree, p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitOpens(OpensTree opensTree, P p) {
        return defaultAction(opensTree, p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitOther(Tree tree, P p) {
        return defaultAction(tree, p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitPackage(PackageTree packageTree, P p) {
        return defaultAction(packageTree, p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitParameterizedType(ParameterizedTypeTree parameterizedTypeTree, P p) {
        return defaultAction(parameterizedTypeTree, p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitParenthesized(ParenthesizedTree parenthesizedTree, P p) {
        return defaultAction(parenthesizedTree, p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitPatternCaseLabel(PatternCaseLabelTree patternCaseLabelTree, P p) {
        return defaultAction(patternCaseLabelTree, p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitPrimitiveType(PrimitiveTypeTree primitiveTypeTree, P p) {
        return defaultAction(primitiveTypeTree, p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitProvides(ProvidesTree providesTree, P p) {
        return defaultAction(providesTree, p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitRequires(RequiresTree requiresTree, P p) {
        return defaultAction(requiresTree, p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitReturn(ReturnTree returnTree, P p) {
        return defaultAction(returnTree, p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitSwitch(SwitchTree switchTree, P p) {
        return defaultAction(switchTree, p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitSwitchExpression(SwitchExpressionTree switchExpressionTree, P p) {
        return defaultAction(switchExpressionTree, p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitSynchronized(SynchronizedTree synchronizedTree, P p) {
        return defaultAction(synchronizedTree, p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitThrow(ThrowTree throwTree, P p) {
        return defaultAction(throwTree, p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitTry(TryTree tryTree, P p) {
        return defaultAction(tryTree, p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitTypeCast(TypeCastTree typeCastTree, P p) {
        return defaultAction(typeCastTree, p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitTypeParameter(TypeParameterTree typeParameterTree, P p) {
        return defaultAction(typeParameterTree, p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitUnary(UnaryTree unaryTree, P p) {
        return defaultAction(unaryTree, p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitUnionType(UnionTypeTree unionTypeTree, P p) {
        return defaultAction(unionTypeTree, p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitUses(UsesTree usesTree, P p) {
        return defaultAction(usesTree, p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitVariable(VariableTree variableTree, P p) {
        return defaultAction(variableTree, p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitWhileLoop(WhileLoopTree whileLoopTree, P p) {
        return defaultAction(whileLoopTree, p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitWildcard(WildcardTree wildcardTree, P p) {
        return defaultAction(wildcardTree, p);
    }

    @Override // com.sun.source.tree.TreeVisitor
    public R visitYield(YieldTree yieldTree, P p) {
        return defaultAction(yieldTree, p);
    }

    public SimpleTreeVisitor(R r) {
        this.DEFAULT_VALUE = r;
    }

    public final R visit(Tree tree, P p) {
        if (tree == null) {
            return null;
        }
        return (R) tree.accept(this, p);
    }
}
