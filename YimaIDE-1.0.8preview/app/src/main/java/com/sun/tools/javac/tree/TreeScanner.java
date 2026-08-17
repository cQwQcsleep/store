package com.sun.tools.javac.tree;

import com.sun.tools.javac.util.Assert;
import com.sun.tools.javac.util.List;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class TreeScanner extends JCTree.Visitor {
    /* JADX WARN: Multi-variable type inference failed */
    public void scan(List<? extends JCTree> list) {
        if (list != null) {
            for (List list2 = list; list2.nonEmpty(); list2 = list2.tail) {
                scan((JCTree) list2.head);
            }
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitAnnotatedType(JCTree.JCAnnotatedType jCAnnotatedType) {
        scan(jCAnnotatedType.annotations);
        scan(jCAnnotatedType.underlyingType);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitAnnotation(JCTree.JCAnnotation jCAnnotation) {
        scan(jCAnnotation.annotationType);
        scan(jCAnnotation.args);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitAnyPattern(JCTree.JCAnyPattern jCAnyPattern) {
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitApply(JCTree.JCMethodInvocation jCMethodInvocation) {
        scan(jCMethodInvocation.typeargs);
        scan(jCMethodInvocation.meth);
        scan(jCMethodInvocation.args);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitAssert(JCTree.JCAssert jCAssert) {
        scan(jCAssert.cond);
        scan(jCAssert.detail);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitAssign(JCTree.JCAssign jCAssign) {
        scan(jCAssign.lhs);
        scan(jCAssign.rhs);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitAssignop(JCTree.JCAssignOp jCAssignOp) {
        scan(jCAssignOp.lhs);
        scan(jCAssignOp.rhs);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitBinary(JCTree.JCBinary jCBinary) {
        scan(jCBinary.lhs);
        scan(jCBinary.rhs);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitBindingPattern(JCTree.JCBindingPattern jCBindingPattern) {
        scan(jCBindingPattern.var);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitBlock(JCTree.JCBlock jCBlock) {
        scan(jCBlock.stats);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitBreak(JCTree.JCBreak jCBreak) {
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitCase(JCTree.JCCase jCCase) {
        scan(jCCase.labels);
        scan(jCCase.guard);
        scan(jCCase.stats);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitCatch(JCTree.JCCatch jCCatch) {
        scan(jCCatch.param);
        scan(jCCatch.body);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitClassDef(JCTree.JCClassDecl jCClassDecl) {
        scan(jCClassDecl.mods);
        scan(jCClassDecl.typarams);
        scan(jCClassDecl.extending);
        scan(jCClassDecl.implementing);
        scan(jCClassDecl.permitting);
        scan(jCClassDecl.defs);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitConditional(JCTree.JCConditional jCConditional) {
        scan(jCConditional.cond);
        scan(jCConditional.truepart);
        scan(jCConditional.falsepart);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitConstantCaseLabel(JCTree.JCConstantCaseLabel jCConstantCaseLabel) {
        scan(jCConstantCaseLabel.expr);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitContinue(JCTree.JCContinue jCContinue) {
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitDefaultCaseLabel(JCTree.JCDefaultCaseLabel jCDefaultCaseLabel) {
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitDoLoop(JCTree.JCDoWhileLoop jCDoWhileLoop) {
        scan(jCDoWhileLoop.body);
        scan(jCDoWhileLoop.cond);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitErroneous(JCTree.JCErroneous jCErroneous) {
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitExec(JCTree.JCExpressionStatement jCExpressionStatement) {
        scan(jCExpressionStatement.expr);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitExports(JCTree.JCExports jCExports) {
        scan(jCExports.qualid);
        scan(jCExports.moduleNames);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitForLoop(JCTree.JCForLoop jCForLoop) {
        scan(jCForLoop.init);
        scan(jCForLoop.cond);
        scan(jCForLoop.step);
        scan(jCForLoop.body);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitForeachLoop(JCTree.JCEnhancedForLoop jCEnhancedForLoop) {
        scan(jCEnhancedForLoop.var);
        scan(jCEnhancedForLoop.expr);
        scan(jCEnhancedForLoop.body);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitIdent(JCTree.JCIdent jCIdent) {
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitIf(JCTree.JCIf jCIf) {
        scan(jCIf.cond);
        scan(jCIf.thenpart);
        scan(jCIf.elsepart);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitImport(JCTree.JCImport jCImport) {
        scan(jCImport.qualid);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitIndexed(JCTree.JCArrayAccess jCArrayAccess) {
        scan(jCArrayAccess.indexed);
        scan(jCArrayAccess.index);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitLabelled(JCTree.JCLabeledStatement jCLabeledStatement) {
        scan(jCLabeledStatement.body);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitLambda(JCTree.JCLambda jCLambda) {
        scan(jCLambda.params);
        scan(jCLambda.body);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitLetExpr(JCTree.LetExpr letExpr) {
        scan(letExpr.defs);
        scan(letExpr.expr);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitLiteral(JCTree.JCLiteral jCLiteral) {
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitMethodDef(JCTree.JCMethodDecl jCMethodDecl) {
        scan(jCMethodDecl.mods);
        scan(jCMethodDecl.restype);
        scan(jCMethodDecl.typarams);
        scan(jCMethodDecl.recvparam);
        scan(jCMethodDecl.params);
        scan(jCMethodDecl.thrown);
        scan(jCMethodDecl.defaultValue);
        scan(jCMethodDecl.body);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitModifiers(JCTree.JCModifiers jCModifiers) {
        scan(jCModifiers.annotations);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitModuleDef(JCTree.JCModuleDecl jCModuleDecl) {
        scan(jCModuleDecl.mods);
        scan(jCModuleDecl.qualId);
        scan(jCModuleDecl.directives);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitModuleImport(JCTree.JCModuleImport jCModuleImport) {
        scan(jCModuleImport.module);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitNewArray(JCTree.JCNewArray jCNewArray) {
        scan(jCNewArray.annotations);
        scan(jCNewArray.elemtype);
        scan(jCNewArray.dims);
        Iterator<List<JCTree.JCAnnotation>> it = jCNewArray.dimAnnotations.iterator();
        while (it.hasNext()) {
            scan(it.next());
        }
        scan(jCNewArray.elems);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitNewClass(JCTree.JCNewClass jCNewClass) {
        scan(jCNewClass.encl);
        scan(jCNewClass.typeargs);
        scan(jCNewClass.clazz);
        scan(jCNewClass.args);
        scan(jCNewClass.def);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitOpens(JCTree.JCOpens jCOpens) {
        scan(jCOpens.qualid);
        scan(jCOpens.moduleNames);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitPackageDef(JCTree.JCPackageDecl jCPackageDecl) {
        scan(jCPackageDecl.annotations);
        scan(jCPackageDecl.pid);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitParens(JCTree.JCParens jCParens) {
        scan(jCParens.expr);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitPatternCaseLabel(JCTree.JCPatternCaseLabel jCPatternCaseLabel) {
        scan(jCPatternCaseLabel.pat);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitProvides(JCTree.JCProvides jCProvides) {
        scan(jCProvides.serviceName);
        scan(jCProvides.implNames);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitRecordPattern(JCTree.JCRecordPattern jCRecordPattern) {
        scan(jCRecordPattern.deconstructor);
        scan(jCRecordPattern.nested);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitReference(JCTree.JCMemberReference jCMemberReference) {
        scan(jCMemberReference.expr);
        scan(jCMemberReference.typeargs);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitRequires(JCTree.JCRequires jCRequires) {
        scan(jCRequires.moduleName);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitReturn(JCTree.JCReturn jCReturn) {
        scan(jCReturn.expr);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitSelect(JCTree.JCFieldAccess jCFieldAccess) {
        scan(jCFieldAccess.selected);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitSkip(JCTree.JCSkip jCSkip) {
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitSwitch(JCTree.JCSwitch jCSwitch) {
        scan(jCSwitch.selector);
        scan(jCSwitch.cases);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitSwitchExpression(JCTree.JCSwitchExpression jCSwitchExpression) {
        scan(jCSwitchExpression.selector);
        scan(jCSwitchExpression.cases);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitSynchronized(JCTree.JCSynchronized jCSynchronized) {
        scan(jCSynchronized.lock);
        scan(jCSynchronized.body);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitThrow(JCTree.JCThrow jCThrow) {
        scan(jCThrow.expr);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTopLevel(JCTree.JCCompilationUnit jCCompilationUnit) {
        scan(jCCompilationUnit.defs);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTree(JCTree jCTree) {
        Assert.error();
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTry(JCTree.JCTry jCTry) {
        scan(jCTry.resources);
        scan(jCTry.body);
        scan(jCTry.catchers);
        scan(jCTry.finalizer);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTypeApply(JCTree.JCTypeApply jCTypeApply) {
        scan(jCTypeApply.clazz);
        scan(jCTypeApply.arguments);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTypeArray(JCTree.JCArrayTypeTree jCArrayTypeTree) {
        scan(jCArrayTypeTree.elemtype);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTypeBoundKind(JCTree.TypeBoundKind typeBoundKind) {
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTypeCast(JCTree.JCTypeCast jCTypeCast) {
        scan(jCTypeCast.clazz);
        scan(jCTypeCast.expr);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTypeIdent(JCTree.JCPrimitiveTypeTree jCPrimitiveTypeTree) {
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTypeIntersection(JCTree.JCTypeIntersection jCTypeIntersection) {
        scan(jCTypeIntersection.bounds);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTypeParameter(JCTree.JCTypeParameter jCTypeParameter) {
        scan(jCTypeParameter.annotations);
        scan(jCTypeParameter.bounds);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTypeTest(JCTree.JCInstanceOf jCInstanceOf) {
        scan(jCInstanceOf.expr);
        scan(jCInstanceOf.pattern);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTypeUnion(JCTree.JCTypeUnion jCTypeUnion) {
        scan(jCTypeUnion.alternatives);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitUnary(JCTree.JCUnary jCUnary) {
        scan(jCUnary.arg);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitUses(JCTree.JCUses jCUses) {
        scan(jCUses.qualid);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitVarDef(JCTree.JCVariableDecl jCVariableDecl) {
        scan(jCVariableDecl.mods);
        scan(jCVariableDecl.vartype);
        scan(jCVariableDecl.nameexpr);
        scan(jCVariableDecl.init);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitWhileLoop(JCTree.JCWhileLoop jCWhileLoop) {
        scan(jCWhileLoop.cond);
        scan(jCWhileLoop.body);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitWildcard(JCTree.JCWildcard jCWildcard) {
        scan(jCWildcard.kind);
        JCTree jCTree = jCWildcard.inner;
        if (jCTree != null) {
            scan(jCTree);
        }
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitYield(JCTree.JCYield jCYield) {
        scan(jCYield.value);
    }

    public void scan(JCTree jCTree) {
        if (jCTree != null) {
            jCTree.accept(this);
        }
    }
}
