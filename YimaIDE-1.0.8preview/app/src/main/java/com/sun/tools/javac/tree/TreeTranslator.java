package com.sun.tools.javac.tree;

import com.sun.tools.javac.util.List;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class TreeTranslator extends JCTree.Visitor {
    protected JCTree result;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v3, types: [A, com.sun.tools.javac.tree.JCTree] */
    public <T extends JCTree> List<T> translate(List<T> list) {
        if (list == null) {
            return null;
        }
        for (List list2 = list; list2.nonEmpty(); list2 = list2.tail) {
            list2.head = translate((JCTree) list2.head);
        }
        return list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v3, types: [A, com.sun.tools.javac.tree.JCTree] */
    public List<JCTree.JCAnnotation> translateAnnotations(List<JCTree.JCAnnotation> list) {
        for (List list2 = list; list2.nonEmpty(); list2 = list2.tail) {
            list2.head = translate((JCTree.JCAnnotation) list2.head);
        }
        return list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v3, types: [A, com.sun.tools.javac.tree.JCTree] */
    public List<JCTree.JCCase> translateCases(List<JCTree.JCCase> list) {
        for (List list2 = list; list2.nonEmpty(); list2 = list2.tail) {
            list2.head = translate((JCTree.JCCase) list2.head);
        }
        return list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v3, types: [A, com.sun.tools.javac.tree.JCTree] */
    public List<JCTree.JCCatch> translateCatchers(List<JCTree.JCCatch> list) {
        for (List list2 = list; list2.nonEmpty(); list2 = list2.tail) {
            list2.head = translate((JCTree.JCCatch) list2.head);
        }
        return list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v3, types: [A, com.sun.tools.javac.tree.JCTree] */
    public List<JCTree.JCTypeParameter> translateTypeParams(List<JCTree.JCTypeParameter> list) {
        for (List list2 = list; list2.nonEmpty(); list2 = list2.tail) {
            list2.head = translate((JCTree.JCTypeParameter) list2.head);
        }
        return list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v3, types: [A, com.sun.tools.javac.tree.JCTree] */
    public List<JCTree.JCVariableDecl> translateVarDefs(List<JCTree.JCVariableDecl> list) {
        for (List list2 = list; list2.nonEmpty(); list2 = list2.tail) {
            list2.head = translate((JCTree.JCVariableDecl) list2.head);
        }
        return list;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitAnnotatedType(JCTree.JCAnnotatedType jCAnnotatedType) {
        jCAnnotatedType.annotations = translate(jCAnnotatedType.annotations);
        jCAnnotatedType.underlyingType = (JCTree.JCExpression) translate(jCAnnotatedType.underlyingType);
        this.result = jCAnnotatedType;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitAnnotation(JCTree.JCAnnotation jCAnnotation) {
        jCAnnotation.annotationType = translate(jCAnnotation.annotationType);
        jCAnnotation.args = translate(jCAnnotation.args);
        this.result = jCAnnotation;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitAnyPattern(JCTree.JCAnyPattern jCAnyPattern) {
        this.result = jCAnyPattern;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitApply(JCTree.JCMethodInvocation jCMethodInvocation) {
        jCMethodInvocation.meth = (JCTree.JCExpression) translate(jCMethodInvocation.meth);
        jCMethodInvocation.args = translate(jCMethodInvocation.args);
        this.result = jCMethodInvocation;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitAssert(JCTree.JCAssert jCAssert) {
        jCAssert.cond = (JCTree.JCExpression) translate(jCAssert.cond);
        jCAssert.detail = (JCTree.JCExpression) translate(jCAssert.detail);
        this.result = jCAssert;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitAssign(JCTree.JCAssign jCAssign) {
        jCAssign.lhs = (JCTree.JCExpression) translate(jCAssign.lhs);
        jCAssign.rhs = (JCTree.JCExpression) translate(jCAssign.rhs);
        this.result = jCAssign;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitAssignop(JCTree.JCAssignOp jCAssignOp) {
        jCAssignOp.lhs = (JCTree.JCExpression) translate(jCAssignOp.lhs);
        jCAssignOp.rhs = (JCTree.JCExpression) translate(jCAssignOp.rhs);
        this.result = jCAssignOp;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitBinary(JCTree.JCBinary jCBinary) {
        jCBinary.lhs = (JCTree.JCExpression) translate(jCBinary.lhs);
        jCBinary.rhs = (JCTree.JCExpression) translate(jCBinary.rhs);
        this.result = jCBinary;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitBindingPattern(JCTree.JCBindingPattern jCBindingPattern) {
        jCBindingPattern.var = (JCTree.JCVariableDecl) translate(jCBindingPattern.var);
        this.result = jCBindingPattern;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitBlock(JCTree.JCBlock jCBlock) {
        jCBlock.stats = translate(jCBlock.stats);
        this.result = jCBlock;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitBreak(JCTree.JCBreak jCBreak) {
        this.result = jCBreak;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitCase(JCTree.JCCase jCCase) {
        jCCase.labels = translate(jCCase.labels);
        jCCase.guard = (JCTree.JCExpression) translate(jCCase.guard);
        jCCase.stats = translate(jCCase.stats);
        this.result = jCCase;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitCatch(JCTree.JCCatch jCCatch) {
        jCCatch.param = (JCTree.JCVariableDecl) translate(jCCatch.param);
        jCCatch.body = (JCTree.JCBlock) translate(jCCatch.body);
        this.result = jCCatch;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitClassDef(JCTree.JCClassDecl jCClassDecl) {
        jCClassDecl.mods = (JCTree.JCModifiers) translate(jCClassDecl.mods);
        jCClassDecl.typarams = translateTypeParams(jCClassDecl.typarams);
        jCClassDecl.extending = (JCTree.JCExpression) translate(jCClassDecl.extending);
        jCClassDecl.implementing = translate(jCClassDecl.implementing);
        jCClassDecl.defs = translate(jCClassDecl.defs);
        this.result = jCClassDecl;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitConditional(JCTree.JCConditional jCConditional) {
        jCConditional.cond = (JCTree.JCExpression) translate(jCConditional.cond);
        jCConditional.truepart = (JCTree.JCExpression) translate(jCConditional.truepart);
        jCConditional.falsepart = (JCTree.JCExpression) translate(jCConditional.falsepart);
        this.result = jCConditional;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitConstantCaseLabel(JCTree.JCConstantCaseLabel jCConstantCaseLabel) {
        jCConstantCaseLabel.expr = (JCTree.JCExpression) translate(jCConstantCaseLabel.expr);
        this.result = jCConstantCaseLabel;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitContinue(JCTree.JCContinue jCContinue) {
        this.result = jCContinue;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitDefaultCaseLabel(JCTree.JCDefaultCaseLabel jCDefaultCaseLabel) {
        this.result = jCDefaultCaseLabel;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitDoLoop(JCTree.JCDoWhileLoop jCDoWhileLoop) {
        jCDoWhileLoop.body = (JCTree.JCStatement) translate(jCDoWhileLoop.body);
        jCDoWhileLoop.cond = (JCTree.JCExpression) translate(jCDoWhileLoop.cond);
        this.result = jCDoWhileLoop;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitErroneous(JCTree.JCErroneous jCErroneous) {
        this.result = jCErroneous;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitExec(JCTree.JCExpressionStatement jCExpressionStatement) {
        jCExpressionStatement.expr = (JCTree.JCExpression) translate(jCExpressionStatement.expr);
        this.result = jCExpressionStatement;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitForLoop(JCTree.JCForLoop jCForLoop) {
        jCForLoop.init = translate(jCForLoop.init);
        jCForLoop.cond = (JCTree.JCExpression) translate(jCForLoop.cond);
        jCForLoop.step = translate(jCForLoop.step);
        jCForLoop.body = (JCTree.JCStatement) translate(jCForLoop.body);
        this.result = jCForLoop;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitForeachLoop(JCTree.JCEnhancedForLoop jCEnhancedForLoop) {
        jCEnhancedForLoop.var = (JCTree.JCVariableDecl) translate(jCEnhancedForLoop.var);
        jCEnhancedForLoop.expr = (JCTree.JCExpression) translate(jCEnhancedForLoop.expr);
        jCEnhancedForLoop.body = (JCTree.JCStatement) translate(jCEnhancedForLoop.body);
        this.result = jCEnhancedForLoop;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitIdent(JCTree.JCIdent jCIdent) {
        this.result = jCIdent;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitIf(JCTree.JCIf jCIf) {
        jCIf.cond = (JCTree.JCExpression) translate(jCIf.cond);
        jCIf.thenpart = (JCTree.JCStatement) translate(jCIf.thenpart);
        jCIf.elsepart = (JCTree.JCStatement) translate(jCIf.elsepart);
        this.result = jCIf;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitImport(JCTree.JCImport jCImport) {
        jCImport.qualid = (JCTree.JCFieldAccess) translate(jCImport.qualid);
        this.result = jCImport;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitIndexed(JCTree.JCArrayAccess jCArrayAccess) {
        jCArrayAccess.indexed = (JCTree.JCExpression) translate(jCArrayAccess.indexed);
        jCArrayAccess.index = (JCTree.JCExpression) translate(jCArrayAccess.index);
        this.result = jCArrayAccess;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitLabelled(JCTree.JCLabeledStatement jCLabeledStatement) {
        jCLabeledStatement.body = (JCTree.JCStatement) translate(jCLabeledStatement.body);
        this.result = jCLabeledStatement;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitLambda(JCTree.JCLambda jCLambda) {
        jCLambda.params = translate(jCLambda.params);
        jCLambda.body = translate(jCLambda.body);
        this.result = jCLambda;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitLetExpr(JCTree.LetExpr letExpr) {
        letExpr.defs = translate(letExpr.defs);
        letExpr.expr = (JCTree.JCExpression) translate(letExpr.expr);
        this.result = letExpr;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitLiteral(JCTree.JCLiteral jCLiteral) {
        this.result = jCLiteral;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitMethodDef(JCTree.JCMethodDecl jCMethodDecl) {
        jCMethodDecl.mods = (JCTree.JCModifiers) translate(jCMethodDecl.mods);
        jCMethodDecl.restype = (JCTree.JCExpression) translate(jCMethodDecl.restype);
        jCMethodDecl.typarams = translateTypeParams(jCMethodDecl.typarams);
        jCMethodDecl.recvparam = (JCTree.JCVariableDecl) translate(jCMethodDecl.recvparam);
        jCMethodDecl.params = translateVarDefs(jCMethodDecl.params);
        jCMethodDecl.thrown = translate(jCMethodDecl.thrown);
        jCMethodDecl.body = (JCTree.JCBlock) translate(jCMethodDecl.body);
        this.result = jCMethodDecl;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitModifiers(JCTree.JCModifiers jCModifiers) {
        jCModifiers.annotations = translateAnnotations(jCModifiers.annotations);
        this.result = jCModifiers;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitModuleImport(JCTree.JCModuleImport jCModuleImport) {
        jCModuleImport.module = (JCTree.JCExpression) translate(jCModuleImport.module);
        this.result = jCModuleImport;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitNewArray(JCTree.JCNewArray jCNewArray) {
        jCNewArray.annotations = translate(jCNewArray.annotations);
        List<List<JCTree.JCAnnotation>> listNil = List.nil();
        Iterator<List<JCTree.JCAnnotation>> it = jCNewArray.dimAnnotations.iterator();
        while (it.hasNext()) {
            listNil = listNil.append(translate(it.next()));
        }
        jCNewArray.dimAnnotations = listNil;
        jCNewArray.elemtype = (JCTree.JCExpression) translate(jCNewArray.elemtype);
        jCNewArray.dims = translate(jCNewArray.dims);
        jCNewArray.elems = translate(jCNewArray.elems);
        this.result = jCNewArray;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitNewClass(JCTree.JCNewClass jCNewClass) {
        jCNewClass.encl = (JCTree.JCExpression) translate(jCNewClass.encl);
        jCNewClass.clazz = (JCTree.JCExpression) translate(jCNewClass.clazz);
        jCNewClass.args = translate(jCNewClass.args);
        jCNewClass.def = (JCTree.JCClassDecl) translate(jCNewClass.def);
        this.result = jCNewClass;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitPackageDef(JCTree.JCPackageDecl jCPackageDecl) {
        jCPackageDecl.annotations = translate(jCPackageDecl.annotations);
        jCPackageDecl.pid = (JCTree.JCExpression) translate(jCPackageDecl.pid);
        this.result = jCPackageDecl;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitParens(JCTree.JCParens jCParens) {
        jCParens.expr = (JCTree.JCExpression) translate(jCParens.expr);
        this.result = jCParens;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitPatternCaseLabel(JCTree.JCPatternCaseLabel jCPatternCaseLabel) {
        jCPatternCaseLabel.pat = (JCTree.JCPattern) translate(jCPatternCaseLabel.pat);
        this.result = jCPatternCaseLabel;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitRecordPattern(JCTree.JCRecordPattern jCRecordPattern) {
        jCRecordPattern.deconstructor = (JCTree.JCExpression) translate(jCRecordPattern.deconstructor);
        jCRecordPattern.nested = translate(jCRecordPattern.nested);
        this.result = jCRecordPattern;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitReference(JCTree.JCMemberReference jCMemberReference) {
        jCMemberReference.expr = (JCTree.JCExpression) translate(jCMemberReference.expr);
        this.result = jCMemberReference;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitReturn(JCTree.JCReturn jCReturn) {
        jCReturn.expr = (JCTree.JCExpression) translate(jCReturn.expr);
        this.result = jCReturn;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitSelect(JCTree.JCFieldAccess jCFieldAccess) {
        jCFieldAccess.selected = (JCTree.JCExpression) translate(jCFieldAccess.selected);
        this.result = jCFieldAccess;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitSkip(JCTree.JCSkip jCSkip) {
        this.result = jCSkip;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitSwitch(JCTree.JCSwitch jCSwitch) {
        jCSwitch.selector = (JCTree.JCExpression) translate(jCSwitch.selector);
        jCSwitch.cases = translateCases(jCSwitch.cases);
        this.result = jCSwitch;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitSwitchExpression(JCTree.JCSwitchExpression jCSwitchExpression) {
        jCSwitchExpression.selector = (JCTree.JCExpression) translate(jCSwitchExpression.selector);
        jCSwitchExpression.cases = translateCases(jCSwitchExpression.cases);
        this.result = jCSwitchExpression;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitSynchronized(JCTree.JCSynchronized jCSynchronized) {
        jCSynchronized.lock = (JCTree.JCExpression) translate(jCSynchronized.lock);
        jCSynchronized.body = (JCTree.JCBlock) translate(jCSynchronized.body);
        this.result = jCSynchronized;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitThrow(JCTree.JCThrow jCThrow) {
        jCThrow.expr = (JCTree.JCExpression) translate(jCThrow.expr);
        this.result = jCThrow;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTopLevel(JCTree.JCCompilationUnit jCCompilationUnit) {
        jCCompilationUnit.defs = translate(jCCompilationUnit.defs);
        this.result = jCCompilationUnit;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTree(JCTree jCTree) {
        throw new AssertionError(jCTree);
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTry(JCTree.JCTry jCTry) {
        jCTry.resources = translate(jCTry.resources);
        jCTry.body = (JCTree.JCBlock) translate(jCTry.body);
        jCTry.catchers = translateCatchers(jCTry.catchers);
        jCTry.finalizer = (JCTree.JCBlock) translate(jCTry.finalizer);
        this.result = jCTry;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTypeApply(JCTree.JCTypeApply jCTypeApply) {
        jCTypeApply.clazz = (JCTree.JCExpression) translate(jCTypeApply.clazz);
        jCTypeApply.arguments = translate(jCTypeApply.arguments);
        this.result = jCTypeApply;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTypeArray(JCTree.JCArrayTypeTree jCArrayTypeTree) {
        jCArrayTypeTree.elemtype = (JCTree.JCExpression) translate(jCArrayTypeTree.elemtype);
        this.result = jCArrayTypeTree;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTypeBoundKind(JCTree.TypeBoundKind typeBoundKind) {
        this.result = typeBoundKind;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTypeCast(JCTree.JCTypeCast jCTypeCast) {
        jCTypeCast.clazz = translate(jCTypeCast.clazz);
        jCTypeCast.expr = (JCTree.JCExpression) translate(jCTypeCast.expr);
        this.result = jCTypeCast;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTypeIdent(JCTree.JCPrimitiveTypeTree jCPrimitiveTypeTree) {
        this.result = jCPrimitiveTypeTree;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTypeIntersection(JCTree.JCTypeIntersection jCTypeIntersection) {
        jCTypeIntersection.bounds = translate(jCTypeIntersection.bounds);
        this.result = jCTypeIntersection;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTypeParameter(JCTree.JCTypeParameter jCTypeParameter) {
        jCTypeParameter.annotations = translate(jCTypeParameter.annotations);
        jCTypeParameter.bounds = translate(jCTypeParameter.bounds);
        this.result = jCTypeParameter;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTypeTest(JCTree.JCInstanceOf jCInstanceOf) {
        jCInstanceOf.expr = (JCTree.JCExpression) translate(jCInstanceOf.expr);
        jCInstanceOf.pattern = translate(jCInstanceOf.pattern);
        this.result = jCInstanceOf;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTypeUnion(JCTree.JCTypeUnion jCTypeUnion) {
        jCTypeUnion.alternatives = translate(jCTypeUnion.alternatives);
        this.result = jCTypeUnion;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitUnary(JCTree.JCUnary jCUnary) {
        jCUnary.arg = (JCTree.JCExpression) translate(jCUnary.arg);
        this.result = jCUnary;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitVarDef(JCTree.JCVariableDecl jCVariableDecl) {
        jCVariableDecl.mods = (JCTree.JCModifiers) translate(jCVariableDecl.mods);
        jCVariableDecl.nameexpr = (JCTree.JCExpression) translate(jCVariableDecl.nameexpr);
        jCVariableDecl.vartype = (JCTree.JCExpression) translate(jCVariableDecl.vartype);
        jCVariableDecl.init = (JCTree.JCExpression) translate(jCVariableDecl.init);
        this.result = jCVariableDecl;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitWhileLoop(JCTree.JCWhileLoop jCWhileLoop) {
        jCWhileLoop.cond = (JCTree.JCExpression) translate(jCWhileLoop.cond);
        jCWhileLoop.body = (JCTree.JCStatement) translate(jCWhileLoop.body);
        this.result = jCWhileLoop;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitWildcard(JCTree.JCWildcard jCWildcard) {
        jCWildcard.kind = (JCTree.TypeBoundKind) translate(jCWildcard.kind);
        jCWildcard.inner = translate(jCWildcard.inner);
        this.result = jCWildcard;
    }

    @Override // com.sun.tools.javac.tree.JCTree.Visitor
    public void visitYield(JCTree.JCYield jCYield) {
        jCYield.value = (JCTree.JCExpression) translate(jCYield.value);
        this.result = jCYield;
    }

    public <T extends JCTree> T translate(T t) {
        if (t == null) {
            return null;
        }
        t.accept(this);
        T t2 = (T) this.result;
        this.result = null;
        return t2;
    }
}
