package com.sun.tools.javac.comp;

import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.code.TypeTag;
import com.sun.tools.javac.code.Types;
import com.sun.tools.javac.jvm.PoolConstant;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeInfo;
import com.sun.tools.javac.tree.TreeScanner;
import com.sun.tools.javac.util.List;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class TreeDiffer extends TreeScanner {
    private Map<Symbol, Symbol> equiv;
    private JCTree parameter;
    private boolean result;
    final Types types;

    public TreeDiffer(Types types, Collection<? extends Symbol> collection, Collection<? extends Symbol> collection2) {
        this.equiv = new HashMap();
        this.equiv = equiv(collection, collection2);
        this.types = types;
    }

    private static Map<Symbol, Symbol> equiv(Collection<? extends Symbol> collection, Collection<? extends Symbol> collection2) {
        HashMap map = new HashMap();
        Iterator<? extends Symbol> it = collection2.iterator();
        for (Symbol symbol : collection) {
            if (!it.hasNext()) {
                break;
            }
            map.put(symbol, it.next());
        }
        return map;
    }

    private boolean scanDimAnnotations(List<List<JCTree.JCAnnotation>> list, List<List<JCTree.JCAnnotation>> list2) {
        if (list == null || list2 == null) {
            return list == null && list2 == null;
        }
        Iterator<List<JCTree.JCAnnotation>> it = list.iterator();
        Iterator<List<JCTree.JCAnnotation>> it2 = list2.iterator();
        while (it.hasNext() && it2.hasNext()) {
            if (!scan(it.next(), it2.next())) {
                return false;
            }
        }
        return (it.hasNext() || it2.hasNext()) ? false : true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private boolean scanSymbol(Symbol symbol, Symbol symbol2) {
        if (symbol instanceof PoolConstant.Dynamic) {
            PoolConstant.Dynamic dynamic = (PoolConstant.Dynamic) symbol;
            if (symbol2 instanceof PoolConstant.Dynamic) {
                return dynamic.bsmKey(this.types).equals(((PoolConstant.Dynamic) symbol2).bsmKey(this.types));
            }
        }
        return symbol == symbol2;
    }

    public boolean scan(JCTree jCTree, JCTree jCTree2) {
        Type type;
        if (jCTree == null || jCTree2 == null) {
            return jCTree == null && jCTree2 == null;
        }
        JCTree jCTreeSkipParens = TreeInfo.skipParens(jCTree);
        JCTree jCTreeSkipParens2 = TreeInfo.skipParens(jCTree2);
        Type type2 = jCTreeSkipParens.type;
        if (type2 != null && type2.constValue() != null && (type = jCTreeSkipParens2.type) != null && type.constValue() != null) {
            return Objects.equals(jCTreeSkipParens.type.constValue(), jCTreeSkipParens2.type.constValue());
        }
        if (jCTreeSkipParens.getTag() != jCTreeSkipParens2.getTag()) {
            return false;
        }
        JCTree jCTree3 = this.parameter;
        boolean z = this.result;
        try {
            this.parameter = jCTreeSkipParens2;
            jCTreeSkipParens.accept(this);
            return this.result;
        } finally {
            this.parameter = jCTree3;
            this.result = z;
        }
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitAnnotatedType(JCTree.JCAnnotatedType jCAnnotatedType) {
        JCTree.JCAnnotatedType jCAnnotatedType2 = (JCTree.JCAnnotatedType) this.parameter;
        this.result = scan(jCAnnotatedType.annotations, jCAnnotatedType2.annotations) && scan(jCAnnotatedType.underlyingType, jCAnnotatedType2.underlyingType);
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitAnnotation(JCTree.JCAnnotation jCAnnotation) {
        JCTree.JCAnnotation jCAnnotation2 = (JCTree.JCAnnotation) this.parameter;
        this.result = scan(jCAnnotation.annotationType, jCAnnotation2.annotationType) && scan(jCAnnotation.args, jCAnnotation2.args);
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitApply(JCTree.JCMethodInvocation jCMethodInvocation) {
        JCTree.JCMethodInvocation jCMethodInvocation2 = (JCTree.JCMethodInvocation) this.parameter;
        this.result = scan(jCMethodInvocation.typeargs, jCMethodInvocation2.typeargs) && scan(jCMethodInvocation.meth, jCMethodInvocation2.meth) && scan(jCMethodInvocation.args, jCMethodInvocation2.args) && jCMethodInvocation.polyKind == jCMethodInvocation2.polyKind;
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitAssert(JCTree.JCAssert jCAssert) {
        JCTree.JCAssert jCAssert2 = (JCTree.JCAssert) this.parameter;
        this.result = scan(jCAssert.cond, jCAssert2.cond) && scan(jCAssert.detail, jCAssert2.detail);
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitAssign(JCTree.JCAssign jCAssign) {
        JCTree.JCAssign jCAssign2 = (JCTree.JCAssign) this.parameter;
        this.result = scan(jCAssign.lhs, jCAssign2.lhs) && scan(jCAssign.rhs, jCAssign2.rhs);
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitAssignop(JCTree.JCAssignOp jCAssignOp) {
        JCTree.JCAssignOp jCAssignOp2 = (JCTree.JCAssignOp) this.parameter;
        this.result = scan(jCAssignOp.lhs, jCAssignOp2.lhs) && scan(jCAssignOp.rhs, jCAssignOp2.rhs) && jCAssignOp.operator == jCAssignOp2.operator;
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitBinary(JCTree.JCBinary jCBinary) {
        JCTree.JCBinary jCBinary2 = (JCTree.JCBinary) this.parameter;
        this.result = scan(jCBinary.lhs, jCBinary2.lhs) && scan(jCBinary.rhs, jCBinary2.rhs) && jCBinary.operator == jCBinary2.operator;
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitBindingPattern(JCTree.JCBindingPattern jCBindingPattern) {
        this.result = scan(jCBindingPattern.var, ((JCTree.JCBindingPattern) this.parameter).var);
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitBlock(JCTree.JCBlock jCBlock) {
        JCTree.JCBlock jCBlock2 = (JCTree.JCBlock) this.parameter;
        this.result = jCBlock.flags == jCBlock2.flags && scan(jCBlock.stats, jCBlock2.stats);
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitBreak(JCTree.JCBreak jCBreak) {
        this.result = jCBreak.label == ((JCTree.JCBreak) this.parameter).label;
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitCase(JCTree.JCCase jCCase) {
        JCTree.JCCase jCCase2 = (JCTree.JCCase) this.parameter;
        this.result = scan(jCCase.labels, jCCase2.labels) && scan(jCCase.guard, jCCase2.guard) && scan(jCCase.stats, jCCase2.stats);
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitCatch(JCTree.JCCatch jCCatch) {
        JCTree.JCCatch jCCatch2 = (JCTree.JCCatch) this.parameter;
        this.result = scan(jCCatch.param, jCCatch2.param) && scan(jCCatch.body, jCCatch2.body);
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitClassDef(JCTree.JCClassDecl jCClassDecl) {
        this.result = false;
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitConditional(JCTree.JCConditional jCConditional) {
        JCTree.JCConditional jCConditional2 = (JCTree.JCConditional) this.parameter;
        this.result = scan(jCConditional.cond, jCConditional2.cond) && scan(jCConditional.truepart, jCConditional2.truepart) && scan(jCConditional.falsepart, jCConditional2.falsepart);
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitConstantCaseLabel(JCTree.JCConstantCaseLabel jCConstantCaseLabel) {
        this.result = scan(jCConstantCaseLabel.expr, ((JCTree.JCConstantCaseLabel) this.parameter).expr);
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitContinue(JCTree.JCContinue jCContinue) {
        this.result = jCContinue.label == ((JCTree.JCContinue) this.parameter).label;
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitDefaultCaseLabel(JCTree.JCDefaultCaseLabel jCDefaultCaseLabel) {
        this.result = true;
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitDoLoop(JCTree.JCDoWhileLoop jCDoWhileLoop) {
        JCTree.JCDoWhileLoop jCDoWhileLoop2 = (JCTree.JCDoWhileLoop) this.parameter;
        this.result = scan(jCDoWhileLoop.body, jCDoWhileLoop2.body) && scan(jCDoWhileLoop.cond, jCDoWhileLoop2.cond);
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitErroneous(JCTree.JCErroneous jCErroneous) {
        this.result = scan(jCErroneous.errs, ((JCTree.JCErroneous) this.parameter).errs);
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitExec(JCTree.JCExpressionStatement jCExpressionStatement) {
        this.result = scan(jCExpressionStatement.expr, ((JCTree.JCExpressionStatement) this.parameter).expr);
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitExports(JCTree.JCExports jCExports) {
        JCTree.JCExports jCExports2 = (JCTree.JCExports) this.parameter;
        this.result = scan(jCExports.qualid, jCExports2.qualid) && scan(jCExports.moduleNames, jCExports2.moduleNames);
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitForLoop(JCTree.JCForLoop jCForLoop) {
        JCTree.JCForLoop jCForLoop2 = (JCTree.JCForLoop) this.parameter;
        this.result = scan(jCForLoop.init, jCForLoop2.init) && scan(jCForLoop.cond, jCForLoop2.cond) && scan(jCForLoop.step, jCForLoop2.step) && scan(jCForLoop.body, jCForLoop2.body);
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitForeachLoop(JCTree.JCEnhancedForLoop jCEnhancedForLoop) {
        JCTree.JCEnhancedForLoop jCEnhancedForLoop2 = (JCTree.JCEnhancedForLoop) this.parameter;
        this.result = scan(jCEnhancedForLoop.var, jCEnhancedForLoop2.var) && scan(jCEnhancedForLoop.expr, jCEnhancedForLoop2.expr) && scan(jCEnhancedForLoop.body, jCEnhancedForLoop2.body);
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitIdent(JCTree.JCIdent jCIdent) {
        JCTree.JCIdent jCIdent2 = (JCTree.JCIdent) this.parameter;
        Symbol symbol = jCIdent.sym;
        Symbol symbol2 = jCIdent2.sym;
        if (symbol == null || symbol2 == null || !Objects.equals(this.equiv.get(symbol), symbol2)) {
            this.result = scanSymbol(symbol, symbol2);
        } else {
            this.result = true;
        }
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitIf(JCTree.JCIf jCIf) {
        JCTree.JCIf jCIf2 = (JCTree.JCIf) this.parameter;
        this.result = scan(jCIf.cond, jCIf2.cond) && scan(jCIf.thenpart, jCIf2.thenpart) && scan(jCIf.elsepart, jCIf2.elsepart);
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitImport(JCTree.JCImport jCImport) {
        JCTree.JCImport jCImport2 = (JCTree.JCImport) this.parameter;
        this.result = jCImport.staticImport == jCImport2.staticImport && scan(jCImport.qualid, jCImport2.qualid);
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitIndexed(JCTree.JCArrayAccess jCArrayAccess) {
        JCTree.JCArrayAccess jCArrayAccess2 = (JCTree.JCArrayAccess) this.parameter;
        this.result = scan(jCArrayAccess.indexed, jCArrayAccess2.indexed) && scan(jCArrayAccess.index, jCArrayAccess2.index);
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitLabelled(JCTree.JCLabeledStatement jCLabeledStatement) {
        JCTree.JCLabeledStatement jCLabeledStatement2 = (JCTree.JCLabeledStatement) this.parameter;
        this.result = jCLabeledStatement.label == jCLabeledStatement2.label && scan(jCLabeledStatement.body, jCLabeledStatement2.body);
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitLambda(JCTree.JCLambda jCLambda) {
        JCTree.JCLambda jCLambda2 = (JCTree.JCLambda) this.parameter;
        this.result = scan(jCLambda.params, jCLambda2.params) && scan(jCLambda.body, jCLambda2.body) && jCLambda.paramKind == jCLambda2.paramKind;
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitLetExpr(JCTree.LetExpr letExpr) {
        JCTree.LetExpr letExpr2 = (JCTree.LetExpr) this.parameter;
        this.result = scan(letExpr.defs, letExpr2.defs) && scan(letExpr.expr, letExpr2.expr);
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitLiteral(JCTree.JCLiteral jCLiteral) {
        JCTree.JCLiteral jCLiteral2 = (JCTree.JCLiteral) this.parameter;
        this.result = jCLiteral.typetag == jCLiteral2.typetag && Objects.equals(jCLiteral.value, jCLiteral2.value);
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitMethodDef(JCTree.JCMethodDecl jCMethodDecl) {
        JCTree.JCMethodDecl jCMethodDecl2 = (JCTree.JCMethodDecl) this.parameter;
        this.result = scan(jCMethodDecl.mods, jCMethodDecl2.mods) && jCMethodDecl.name == jCMethodDecl2.name && scan(jCMethodDecl.restype, jCMethodDecl2.restype) && scan(jCMethodDecl.typarams, jCMethodDecl2.typarams) && scan(jCMethodDecl.recvparam, jCMethodDecl2.recvparam) && scan(jCMethodDecl.params, jCMethodDecl2.params) && scan(jCMethodDecl.thrown, jCMethodDecl2.thrown) && scan(jCMethodDecl.body, jCMethodDecl2.body) && scan(jCMethodDecl.defaultValue, jCMethodDecl2.defaultValue);
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitModifiers(JCTree.JCModifiers jCModifiers) {
        JCTree.JCModifiers jCModifiers2 = (JCTree.JCModifiers) this.parameter;
        this.result = jCModifiers.flags == jCModifiers2.flags && scan(jCModifiers.annotations, jCModifiers2.annotations);
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitModuleDef(JCTree.JCModuleDecl jCModuleDecl) {
        JCTree.JCModuleDecl jCModuleDecl2 = (JCTree.JCModuleDecl) this.parameter;
        this.result = scan(jCModuleDecl.mods, jCModuleDecl2.mods) && scan(jCModuleDecl.qualId, jCModuleDecl2.qualId) && scan(jCModuleDecl.directives, jCModuleDecl2.directives);
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitModuleImport(JCTree.JCModuleImport jCModuleImport) {
        this.result = scan(jCModuleImport.module, ((JCTree.JCModuleImport) this.parameter).module);
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitNewArray(JCTree.JCNewArray jCNewArray) {
        JCTree.JCNewArray jCNewArray2 = (JCTree.JCNewArray) this.parameter;
        this.result = scan(jCNewArray.elemtype, jCNewArray2.elemtype) && scan(jCNewArray.dims, jCNewArray2.dims) && scan(jCNewArray.annotations, jCNewArray2.annotations) && scanDimAnnotations(jCNewArray.dimAnnotations, jCNewArray2.dimAnnotations) && scan(jCNewArray.elems, jCNewArray2.elems);
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitNewClass(JCTree.JCNewClass jCNewClass) {
        JCTree.JCNewClass jCNewClass2 = (JCTree.JCNewClass) this.parameter;
        this.result = scan(jCNewClass.encl, jCNewClass2.encl) && scan(jCNewClass.typeargs, jCNewClass2.typeargs) && scan(jCNewClass.clazz, jCNewClass2.clazz) && scan(jCNewClass.args, jCNewClass2.args) && scan(jCNewClass.def, jCNewClass2.def) && jCNewClass.constructor == jCNewClass2.constructor;
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitOpens(JCTree.JCOpens jCOpens) {
        JCTree.JCOpens jCOpens2 = (JCTree.JCOpens) this.parameter;
        this.result = scan(jCOpens.qualid, jCOpens2.qualid) && scan(jCOpens.moduleNames, jCOpens2.moduleNames);
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitPackageDef(JCTree.JCPackageDecl jCPackageDecl) {
        JCTree.JCPackageDecl jCPackageDecl2 = (JCTree.JCPackageDecl) this.parameter;
        this.result = scan(jCPackageDecl.annotations, jCPackageDecl2.annotations) && scan(jCPackageDecl.pid, jCPackageDecl2.pid) && jCPackageDecl.packge == jCPackageDecl2.packge;
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitPatternCaseLabel(JCTree.JCPatternCaseLabel jCPatternCaseLabel) {
        this.result = scan(jCPatternCaseLabel.pat, ((JCTree.JCPatternCaseLabel) this.parameter).pat);
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitProvides(JCTree.JCProvides jCProvides) {
        JCTree.JCProvides jCProvides2 = (JCTree.JCProvides) this.parameter;
        this.result = scan(jCProvides.serviceName, jCProvides2.serviceName) && scan(jCProvides.implNames, jCProvides2.implNames);
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitRecordPattern(JCTree.JCRecordPattern jCRecordPattern) {
        JCTree.JCRecordPattern jCRecordPattern2 = (JCTree.JCRecordPattern) this.parameter;
        this.result = scan(jCRecordPattern.deconstructor, jCRecordPattern2.deconstructor) && scan(jCRecordPattern.nested, jCRecordPattern2.nested);
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitReference(JCTree.JCMemberReference jCMemberReference) {
        JCTree.JCMemberReference jCMemberReference2 = (JCTree.JCMemberReference) this.parameter;
        this.result = jCMemberReference.mode == jCMemberReference2.mode && jCMemberReference.kind == jCMemberReference2.kind && jCMemberReference.name == jCMemberReference2.name && scan(jCMemberReference.expr, jCMemberReference2.expr) && scan(jCMemberReference.typeargs, jCMemberReference2.typeargs);
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitRequires(JCTree.JCRequires jCRequires) {
        JCTree.JCRequires jCRequires2 = (JCTree.JCRequires) this.parameter;
        this.result = jCRequires.isTransitive == jCRequires2.isTransitive && jCRequires.isStaticPhase == jCRequires2.isStaticPhase && scan(jCRequires.moduleName, jCRequires2.moduleName);
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitReturn(JCTree.JCReturn jCReturn) {
        this.result = scan(jCReturn.expr, ((JCTree.JCReturn) this.parameter).expr);
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitSelect(JCTree.JCFieldAccess jCFieldAccess) {
        JCTree.JCFieldAccess jCFieldAccess2 = (JCTree.JCFieldAccess) this.parameter;
        this.result = scan(jCFieldAccess.selected, jCFieldAccess2.selected) && scanSymbol(jCFieldAccess.sym, jCFieldAccess2.sym);
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitSwitch(JCTree.JCSwitch jCSwitch) {
        JCTree.JCSwitch jCSwitch2 = (JCTree.JCSwitch) this.parameter;
        this.result = scan(jCSwitch.selector, jCSwitch2.selector) && scan(jCSwitch.cases, jCSwitch2.cases);
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitSwitchExpression(JCTree.JCSwitchExpression jCSwitchExpression) {
        JCTree.JCSwitchExpression jCSwitchExpression2 = (JCTree.JCSwitchExpression) this.parameter;
        this.result = scan(jCSwitchExpression.selector, jCSwitchExpression2.selector) && scan(jCSwitchExpression.cases, jCSwitchExpression2.cases);
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitSynchronized(JCTree.JCSynchronized jCSynchronized) {
        JCTree.JCSynchronized jCSynchronized2 = (JCTree.JCSynchronized) this.parameter;
        this.result = scan(jCSynchronized.lock, jCSynchronized2.lock) && scan(jCSynchronized.body, jCSynchronized2.body);
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitThrow(JCTree.JCThrow jCThrow) {
        this.result = scan(jCThrow.expr, ((JCTree.JCThrow) this.parameter).expr);
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTopLevel(JCTree.JCCompilationUnit jCCompilationUnit) {
        JCTree.JCCompilationUnit jCCompilationUnit2 = (JCTree.JCCompilationUnit) this.parameter;
        this.result = scan(jCCompilationUnit.defs, jCCompilationUnit2.defs) && jCCompilationUnit.modle == jCCompilationUnit2.modle && jCCompilationUnit.packge == jCCompilationUnit2.packge;
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTry(JCTree.JCTry jCTry) {
        JCTree.JCTry jCTry2 = (JCTree.JCTry) this.parameter;
        this.result = scan(jCTry.body, jCTry2.body) && scan(jCTry.catchers, jCTry2.catchers) && scan(jCTry.finalizer, jCTry2.finalizer) && scan(jCTry.resources, jCTry2.resources);
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTypeApply(JCTree.JCTypeApply jCTypeApply) {
        JCTree.JCTypeApply jCTypeApply2 = (JCTree.JCTypeApply) this.parameter;
        this.result = scan(jCTypeApply.clazz, jCTypeApply2.clazz) && scan(jCTypeApply.arguments, jCTypeApply2.arguments);
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTypeArray(JCTree.JCArrayTypeTree jCArrayTypeTree) {
        this.result = scan(jCArrayTypeTree.elemtype, ((JCTree.JCArrayTypeTree) this.parameter).elemtype);
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTypeBoundKind(JCTree.TypeBoundKind typeBoundKind) {
        this.result = typeBoundKind.kind == ((JCTree.TypeBoundKind) this.parameter).kind;
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTypeCast(JCTree.JCTypeCast jCTypeCast) {
        JCTree.JCTypeCast jCTypeCast2 = (JCTree.JCTypeCast) this.parameter;
        this.result = scan(jCTypeCast.clazz, jCTypeCast2.clazz) && scan(jCTypeCast.expr, jCTypeCast2.expr);
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTypeIdent(JCTree.JCPrimitiveTypeTree jCPrimitiveTypeTree) {
        this.result = jCPrimitiveTypeTree.typetag == ((JCTree.JCPrimitiveTypeTree) this.parameter).typetag;
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTypeIntersection(JCTree.JCTypeIntersection jCTypeIntersection) {
        this.result = scan(jCTypeIntersection.bounds, ((JCTree.JCTypeIntersection) this.parameter).bounds);
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTypeParameter(JCTree.JCTypeParameter jCTypeParameter) {
        JCTree.JCTypeParameter jCTypeParameter2 = (JCTree.JCTypeParameter) this.parameter;
        this.result = jCTypeParameter.name == jCTypeParameter2.name && scan(jCTypeParameter.bounds, jCTypeParameter2.bounds) && scan(jCTypeParameter.annotations, jCTypeParameter2.annotations);
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTypeTest(JCTree.JCInstanceOf jCInstanceOf) {
        JCTree.JCInstanceOf jCInstanceOf2 = (JCTree.JCInstanceOf) this.parameter;
        this.result = scan(jCInstanceOf.expr, jCInstanceOf2.expr) && scan(jCInstanceOf.pattern, jCInstanceOf2.pattern);
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitTypeUnion(JCTree.JCTypeUnion jCTypeUnion) {
        this.result = scan(jCTypeUnion.alternatives, ((JCTree.JCTypeUnion) this.parameter).alternatives);
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitUnary(JCTree.JCUnary jCUnary) {
        JCTree.JCUnary jCUnary2 = (JCTree.JCUnary) this.parameter;
        this.result = scan(jCUnary.arg, jCUnary2.arg) && jCUnary.operator == jCUnary2.operator;
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitUses(JCTree.JCUses jCUses) {
        this.result = scan(jCUses.qualid, ((JCTree.JCUses) this.parameter).qualid);
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitVarDef(JCTree.JCVariableDecl jCVariableDecl) {
        JCTree.JCVariableDecl jCVariableDecl2 = (JCTree.JCVariableDecl) this.parameter;
        this.result = scan(jCVariableDecl.mods, jCVariableDecl2.mods) && scan(jCVariableDecl.nameexpr, jCVariableDecl2.nameexpr) && scan(jCVariableDecl.vartype, jCVariableDecl2.vartype) && scan(jCVariableDecl.init, jCVariableDecl2.init);
        if (jCVariableDecl.sym.owner.type.hasTag(TypeTag.CLASS)) {
            this.result &= jCVariableDecl.name == jCVariableDecl2.name;
        }
        if (this.result) {
            this.equiv.put(jCVariableDecl.sym, jCVariableDecl2.sym);
        }
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitWhileLoop(JCTree.JCWhileLoop jCWhileLoop) {
        JCTree.JCWhileLoop jCWhileLoop2 = (JCTree.JCWhileLoop) this.parameter;
        this.result = scan(jCWhileLoop.cond, jCWhileLoop2.cond) && scan(jCWhileLoop.body, jCWhileLoop2.body);
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitWildcard(JCTree.JCWildcard jCWildcard) {
        JCTree.JCWildcard jCWildcard2 = (JCTree.JCWildcard) this.parameter;
        this.result = scan(jCWildcard.kind, jCWildcard2.kind) && scan(jCWildcard.inner, jCWildcard2.inner);
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitYield(JCTree.JCYield jCYield) {
        this.result = scan(jCYield.value, ((JCTree.JCYield) this.parameter).value);
    }

    private boolean scan(Iterable<? extends JCTree> iterable, Iterable<? extends JCTree> iterable2) {
        if (iterable == null || iterable2 == null) {
            return iterable == null && iterable2 == null;
        }
        Iterator<? extends JCTree> it = iterable.iterator();
        Iterator<? extends JCTree> it2 = iterable2.iterator();
        while (it.hasNext() && it2.hasNext()) {
            if (!scan(it.next(), it2.next())) {
                return false;
            }
        }
        return (it.hasNext() || it2.hasNext()) ? false : true;
    }
}
