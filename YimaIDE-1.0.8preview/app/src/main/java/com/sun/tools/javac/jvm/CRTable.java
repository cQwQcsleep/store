package com.sun.tools.javac.jvm;

import com.sun.tools.javac.resources.CompilerProperties;
import com.sun.tools.javac.tree.EndPosTable;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeInfo;
import com.sun.tools.javac.util.Assert;
import com.sun.tools.javac.util.ByteBuffer;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.ListBuffer;
import com.sun.tools.javac.util.Log;
import com.sun.tools.javac.util.Position;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class CRTable implements CRTFlags {
    private EndPosTable endPosTable;
    JCTree.JCMethodDecl methodTree;
    private final boolean crtDebug = false;
    private ListBuffer<CRTEntry> entries = new ListBuffer<>();
    private Map<Object, SourceRange> positions = new HashMap();

    public static class CRTEntry {
        int endPc;
        int flags;
        int startPc;
        Object tree;

        public CRTEntry(Object obj, int i, int i2, int i3) {
            this.tree = obj;
            this.flags = i;
            this.startPc = i2;
            this.endPc = i3;
        }
    }

    public CRTable(JCTree.JCMethodDecl jCMethodDecl, EndPosTable endPosTable) {
        this.methodTree = jCMethodDecl;
        this.endPosTable = endPosTable;
    }

    private int encodePosition(int i, Position.LineMap lineMap, Log log) {
        int lineNumber = lineMap.getLineNumber(i);
        int iEncodePosition = Position.encodePosition(lineNumber, lineMap.getColumnNumber(i));
        if (iEncodePosition == -1) {
            log.warning(i, CompilerProperties.Warnings.PositionOverflow(lineNumber));
        }
        return iEncodePosition;
    }

    private String getTypes(int i) {
        String strConcat = (i & 1) != 0 ? " CRT_STATEMENT" : "";
        if ((i & 2) != 0) {
            strConcat = strConcat.concat(" CRT_BLOCK");
        }
        if ((i & 4) != 0) {
            strConcat = strConcat.concat(" CRT_ASSIGNMENT");
        }
        if ((i & 8) != 0) {
            strConcat = strConcat.concat(" CRT_FLOW_CONTROLLER");
        }
        if ((i & 16) != 0) {
            strConcat = strConcat.concat(" CRT_FLOW_TARGET");
        }
        if ((i & 32) != 0) {
            strConcat = strConcat.concat(" CRT_INVOKE");
        }
        if ((i & 64) != 0) {
            strConcat = strConcat.concat(" CRT_CREATE");
        }
        if ((i & 128) != 0) {
            strConcat = strConcat.concat(" CRT_BRANCH_TRUE");
        }
        return (i & 256) != 0 ? strConcat.concat(" CRT_BRANCH_FALSE") : strConcat;
    }

    public int length() {
        return this.entries.length();
    }

    public void put(Object obj, int i, int i2, int i3) {
        this.entries.append(new CRTEntry(obj, i, i2, i3));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int writeCRT(ByteBuffer byteBuffer, Position.LineMap lineMap, Log log) {
        int iEncodePosition;
        int iEncodePosition2;
        new SourceComputer().csp(this.methodTree);
        int i = 0;
        for (List list = this.entries.toList(); list.nonEmpty(); list = list.tail) {
            CRTEntry cRTEntry = (CRTEntry) list.head;
            if (cRTEntry.startPc != cRTEntry.endPc) {
                SourceRange sourceRange = this.positions.get(cRTEntry.tree);
                Assert.checkNonNull(sourceRange, "CRT: tree source positions are undefined");
                int i2 = sourceRange.startPos;
                if (i2 != -1 && sourceRange.endPos != -1 && (iEncodePosition = encodePosition(i2, lineMap, log)) != -1 && (iEncodePosition2 = encodePosition(sourceRange.endPos, lineMap, log)) != -1) {
                    byteBuffer.appendChar(cRTEntry.startPc);
                    byteBuffer.appendChar(cRTEntry.endPc - 1);
                    byteBuffer.appendInt(iEncodePosition);
                    byteBuffer.appendInt(iEncodePosition2);
                    byteBuffer.appendChar(cRTEntry.flags);
                    i++;
                }
            }
        }
        return i;
    }

    public static class SourceRange {
        int endPos;
        int startPos;

        public SourceRange() {
            this.startPos = -1;
            this.endPos = -1;
        }

        public SourceRange mergeWith(SourceRange sourceRange) {
            if (sourceRange != null) {
                int i = this.startPos;
                int i2 = sourceRange.startPos;
                if (i == -1) {
                    this.startPos = i2;
                } else if (i2 != -1) {
                    if (i >= i2) {
                        i = i2;
                    }
                    this.startPos = i;
                }
                int i3 = this.endPos;
                int i4 = sourceRange.endPos;
                if (i3 == -1) {
                    this.endPos = i4;
                    return this;
                }
                if (i4 != -1) {
                    if (i3 <= i4) {
                        i3 = i4;
                    }
                    this.endPos = i3;
                }
            }
            return this;
        }

        public SourceRange(int i, int i2) {
            this.startPos = i;
            this.endPos = i2;
        }
    }

    public class SourceComputer extends JCTree.Visitor {
        SourceRange result;

        public SourceComputer() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        public SourceRange csp(List<? extends JCTree> list) {
            if (list == null || !list.nonEmpty()) {
                return null;
            }
            SourceRange sourceRange = new SourceRange();
            for (List list2 = list; list2.nonEmpty(); list2 = list2.tail) {
                sourceRange.mergeWith(csp((JCTree) list2.head));
            }
            CRTable.this.positions.put(list, sourceRange);
            return sourceRange;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public SourceRange cspCases(List<JCTree.JCCase> list) {
            if (list == null || !list.nonEmpty()) {
                return null;
            }
            SourceRange sourceRange = new SourceRange();
            for (List list2 = list; list2.nonEmpty(); list2 = list2.tail) {
                sourceRange.mergeWith(csp((JCTree) list2.head));
            }
            CRTable.this.positions.put(list, sourceRange);
            return sourceRange;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public SourceRange cspCatchers(List<JCTree.JCCatch> list) {
            if (list == null || !list.nonEmpty()) {
                return null;
            }
            SourceRange sourceRange = new SourceRange();
            for (List list2 = list; list2.nonEmpty(); list2 = list2.tail) {
                sourceRange.mergeWith(csp((JCTree) list2.head));
            }
            CRTable.this.positions.put(list, sourceRange);
            return sourceRange;
        }

        public int endPos(JCTree jCTree) {
            if (jCTree == null) {
                return -1;
            }
            return TreeInfo.getEndPos(jCTree, CRTable.this.endPosTable);
        }

        public int startPos(JCTree jCTree) {
            if (jCTree == null) {
                return -1;
            }
            return TreeInfo.getStartPos(jCTree);
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitApply(JCTree.JCMethodInvocation jCMethodInvocation) {
            SourceRange sourceRange = new SourceRange(startPos(jCMethodInvocation), endPos(jCMethodInvocation));
            sourceRange.mergeWith(csp(jCMethodInvocation.meth));
            sourceRange.mergeWith(csp(jCMethodInvocation.args));
            this.result = sourceRange;
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitAssert(JCTree.JCAssert jCAssert) {
            SourceRange sourceRange = new SourceRange(startPos(jCAssert), endPos(jCAssert));
            sourceRange.mergeWith(csp(jCAssert.cond));
            sourceRange.mergeWith(csp(jCAssert.detail));
            this.result = sourceRange;
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitAssign(JCTree.JCAssign jCAssign) {
            SourceRange sourceRange = new SourceRange(startPos(jCAssign), endPos(jCAssign));
            sourceRange.mergeWith(csp(jCAssign.lhs));
            sourceRange.mergeWith(csp(jCAssign.rhs));
            this.result = sourceRange;
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitAssignop(JCTree.JCAssignOp jCAssignOp) {
            SourceRange sourceRange = new SourceRange(startPos(jCAssignOp), endPos(jCAssignOp));
            sourceRange.mergeWith(csp(jCAssignOp.lhs));
            sourceRange.mergeWith(csp(jCAssignOp.rhs));
            this.result = sourceRange;
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitBinary(JCTree.JCBinary jCBinary) {
            SourceRange sourceRange = new SourceRange(startPos(jCBinary), endPos(jCBinary));
            sourceRange.mergeWith(csp(jCBinary.lhs));
            sourceRange.mergeWith(csp(jCBinary.rhs));
            this.result = sourceRange;
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitBlock(JCTree.JCBlock jCBlock) {
            SourceRange sourceRange = new SourceRange(startPos(jCBlock), endPos(jCBlock));
            csp(jCBlock.stats);
            this.result = sourceRange;
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitBreak(JCTree.JCBreak jCBreak) {
            this.result = new SourceRange(startPos(jCBreak), endPos(jCBreak));
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitCase(JCTree.JCCase jCCase) {
            SourceRange sourceRange = new SourceRange(startPos(jCCase), endPos(jCCase));
            sourceRange.mergeWith(csp(jCCase.labels));
            sourceRange.mergeWith(csp(jCCase.guard));
            sourceRange.mergeWith(csp(jCCase.stats));
            this.result = sourceRange;
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitCatch(JCTree.JCCatch jCCatch) {
            SourceRange sourceRange = new SourceRange(startPos(jCCatch), endPos(jCCatch));
            sourceRange.mergeWith(csp(jCCatch.param));
            sourceRange.mergeWith(csp(jCCatch.body));
            this.result = sourceRange;
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitConditional(JCTree.JCConditional jCConditional) {
            SourceRange sourceRange = new SourceRange(startPos(jCConditional), endPos(jCConditional));
            sourceRange.mergeWith(csp(jCConditional.cond));
            sourceRange.mergeWith(csp(jCConditional.truepart));
            sourceRange.mergeWith(csp(jCConditional.falsepart));
            this.result = sourceRange;
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitConstantCaseLabel(JCTree.JCConstantCaseLabel jCConstantCaseLabel) {
            SourceRange sourceRange = new SourceRange(startPos(jCConstantCaseLabel), endPos(jCConstantCaseLabel));
            sourceRange.mergeWith(csp(jCConstantCaseLabel.expr));
            this.result = sourceRange;
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitContinue(JCTree.JCContinue jCContinue) {
            this.result = new SourceRange(startPos(jCContinue), endPos(jCContinue));
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitDefaultCaseLabel(JCTree.JCDefaultCaseLabel jCDefaultCaseLabel) {
            this.result = null;
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitDoLoop(JCTree.JCDoWhileLoop jCDoWhileLoop) {
            SourceRange sourceRange = new SourceRange(startPos(jCDoWhileLoop), endPos(jCDoWhileLoop));
            sourceRange.mergeWith(csp(jCDoWhileLoop.body));
            sourceRange.mergeWith(csp(jCDoWhileLoop.cond));
            this.result = sourceRange;
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitErroneous(JCTree.JCErroneous jCErroneous) {
            this.result = null;
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitExec(JCTree.JCExpressionStatement jCExpressionStatement) {
            SourceRange sourceRange = new SourceRange(startPos(jCExpressionStatement), endPos(jCExpressionStatement));
            sourceRange.mergeWith(csp(jCExpressionStatement.expr));
            this.result = sourceRange;
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitForLoop(JCTree.JCForLoop jCForLoop) {
            SourceRange sourceRange = new SourceRange(startPos(jCForLoop), endPos(jCForLoop));
            sourceRange.mergeWith(csp(jCForLoop.init));
            sourceRange.mergeWith(csp(jCForLoop.cond));
            sourceRange.mergeWith(csp(jCForLoop.step));
            sourceRange.mergeWith(csp(jCForLoop.body));
            this.result = sourceRange;
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitForeachLoop(JCTree.JCEnhancedForLoop jCEnhancedForLoop) {
            SourceRange sourceRange = new SourceRange(startPos(jCEnhancedForLoop), endPos(jCEnhancedForLoop));
            sourceRange.mergeWith(csp(jCEnhancedForLoop.var));
            sourceRange.mergeWith(csp(jCEnhancedForLoop.expr));
            sourceRange.mergeWith(csp(jCEnhancedForLoop.body));
            this.result = sourceRange;
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitIdent(JCTree.JCIdent jCIdent) {
            this.result = new SourceRange(startPos(jCIdent), endPos(jCIdent));
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitIf(JCTree.JCIf jCIf) {
            SourceRange sourceRange = new SourceRange(startPos(jCIf), endPos(jCIf));
            sourceRange.mergeWith(csp(jCIf.cond));
            sourceRange.mergeWith(csp(jCIf.thenpart));
            sourceRange.mergeWith(csp(jCIf.elsepart));
            this.result = sourceRange;
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitIndexed(JCTree.JCArrayAccess jCArrayAccess) {
            SourceRange sourceRange = new SourceRange(startPos(jCArrayAccess), endPos(jCArrayAccess));
            sourceRange.mergeWith(csp(jCArrayAccess.indexed));
            sourceRange.mergeWith(csp(jCArrayAccess.index));
            this.result = sourceRange;
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitLabelled(JCTree.JCLabeledStatement jCLabeledStatement) {
            SourceRange sourceRange = new SourceRange(startPos(jCLabeledStatement), endPos(jCLabeledStatement));
            sourceRange.mergeWith(csp(jCLabeledStatement.body));
            this.result = sourceRange;
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitLetExpr(JCTree.LetExpr letExpr) {
            SourceRange sourceRange = new SourceRange(startPos(letExpr), endPos(letExpr));
            sourceRange.mergeWith(csp(letExpr.defs));
            sourceRange.mergeWith(csp(letExpr.expr));
            this.result = sourceRange;
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitLiteral(JCTree.JCLiteral jCLiteral) {
            this.result = new SourceRange(startPos(jCLiteral), endPos(jCLiteral));
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitMethodDef(JCTree.JCMethodDecl jCMethodDecl) {
            SourceRange sourceRange = new SourceRange(startPos(jCMethodDecl), endPos(jCMethodDecl));
            sourceRange.mergeWith(csp(jCMethodDecl.body));
            this.result = sourceRange;
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitNewArray(JCTree.JCNewArray jCNewArray) {
            SourceRange sourceRange = new SourceRange(startPos(jCNewArray), endPos(jCNewArray));
            sourceRange.mergeWith(csp(jCNewArray.elemtype));
            sourceRange.mergeWith(csp(jCNewArray.dims));
            sourceRange.mergeWith(csp(jCNewArray.elems));
            this.result = sourceRange;
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitNewClass(JCTree.JCNewClass jCNewClass) {
            SourceRange sourceRange = new SourceRange(startPos(jCNewClass), endPos(jCNewClass));
            sourceRange.mergeWith(csp(jCNewClass.encl));
            sourceRange.mergeWith(csp(jCNewClass.clazz));
            sourceRange.mergeWith(csp(jCNewClass.args));
            sourceRange.mergeWith(csp(jCNewClass.def));
            this.result = sourceRange;
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitParens(JCTree.JCParens jCParens) {
            SourceRange sourceRange = new SourceRange(startPos(jCParens), endPos(jCParens));
            sourceRange.mergeWith(csp(jCParens.expr));
            this.result = sourceRange;
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitPatternCaseLabel(JCTree.JCPatternCaseLabel jCPatternCaseLabel) {
            SourceRange sourceRange = new SourceRange(startPos(jCPatternCaseLabel), endPos(jCPatternCaseLabel));
            sourceRange.mergeWith(csp(jCPatternCaseLabel.pat));
            this.result = sourceRange;
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitReturn(JCTree.JCReturn jCReturn) {
            SourceRange sourceRange = new SourceRange(startPos(jCReturn), endPos(jCReturn));
            sourceRange.mergeWith(csp(jCReturn.expr));
            this.result = sourceRange;
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitSelect(JCTree.JCFieldAccess jCFieldAccess) {
            SourceRange sourceRange = new SourceRange(startPos(jCFieldAccess), endPos(jCFieldAccess));
            sourceRange.mergeWith(csp(jCFieldAccess.selected));
            this.result = sourceRange;
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitSkip(JCTree.JCSkip jCSkip) {
            this.result = new SourceRange(startPos(jCSkip), startPos(jCSkip));
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitSwitch(JCTree.JCSwitch jCSwitch) {
            SourceRange sourceRange = new SourceRange(startPos(jCSwitch), endPos(jCSwitch));
            sourceRange.mergeWith(csp(jCSwitch.selector));
            sourceRange.mergeWith(cspCases(jCSwitch.cases));
            this.result = sourceRange;
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitSwitchExpression(JCTree.JCSwitchExpression jCSwitchExpression) {
            SourceRange sourceRange = new SourceRange(startPos(jCSwitchExpression), endPos(jCSwitchExpression));
            sourceRange.mergeWith(csp(jCSwitchExpression.selector));
            sourceRange.mergeWith(cspCases(jCSwitchExpression.cases));
            this.result = sourceRange;
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitSynchronized(JCTree.JCSynchronized jCSynchronized) {
            SourceRange sourceRange = new SourceRange(startPos(jCSynchronized), endPos(jCSynchronized));
            sourceRange.mergeWith(csp(jCSynchronized.lock));
            sourceRange.mergeWith(csp(jCSynchronized.body));
            this.result = sourceRange;
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitThrow(JCTree.JCThrow jCThrow) {
            SourceRange sourceRange = new SourceRange(startPos(jCThrow), endPos(jCThrow));
            sourceRange.mergeWith(csp(jCThrow.expr));
            this.result = sourceRange;
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitTree(JCTree jCTree) {
            Assert.error();
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitTry(JCTree.JCTry jCTry) {
            SourceRange sourceRange = new SourceRange(startPos(jCTry), endPos(jCTry));
            sourceRange.mergeWith(csp(jCTry.resources));
            sourceRange.mergeWith(csp(jCTry.body));
            sourceRange.mergeWith(cspCatchers(jCTry.catchers));
            sourceRange.mergeWith(csp(jCTry.finalizer));
            this.result = sourceRange;
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitTypeApply(JCTree.JCTypeApply jCTypeApply) {
            SourceRange sourceRange = new SourceRange(startPos(jCTypeApply), endPos(jCTypeApply));
            sourceRange.mergeWith(csp(jCTypeApply.clazz));
            sourceRange.mergeWith(csp(jCTypeApply.arguments));
            this.result = sourceRange;
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitTypeArray(JCTree.JCArrayTypeTree jCArrayTypeTree) {
            SourceRange sourceRange = new SourceRange(startPos(jCArrayTypeTree), endPos(jCArrayTypeTree));
            sourceRange.mergeWith(csp(jCArrayTypeTree.elemtype));
            this.result = sourceRange;
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitTypeCast(JCTree.JCTypeCast jCTypeCast) {
            SourceRange sourceRange = new SourceRange(startPos(jCTypeCast), endPos(jCTypeCast));
            sourceRange.mergeWith(csp(jCTypeCast.clazz));
            sourceRange.mergeWith(csp(jCTypeCast.expr));
            this.result = sourceRange;
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitTypeIdent(JCTree.JCPrimitiveTypeTree jCPrimitiveTypeTree) {
            this.result = new SourceRange(startPos(jCPrimitiveTypeTree), endPos(jCPrimitiveTypeTree));
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitTypeParameter(JCTree.JCTypeParameter jCTypeParameter) {
            SourceRange sourceRange = new SourceRange(startPos(jCTypeParameter), endPos(jCTypeParameter));
            sourceRange.mergeWith(csp(jCTypeParameter.bounds));
            this.result = sourceRange;
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitTypeTest(JCTree.JCInstanceOf jCInstanceOf) {
            SourceRange sourceRange = new SourceRange(startPos(jCInstanceOf), endPos(jCInstanceOf));
            sourceRange.mergeWith(csp(jCInstanceOf.expr));
            sourceRange.mergeWith(csp(jCInstanceOf.pattern));
            this.result = sourceRange;
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitTypeUnion(JCTree.JCTypeUnion jCTypeUnion) {
            SourceRange sourceRange = new SourceRange(startPos(jCTypeUnion), endPos(jCTypeUnion));
            sourceRange.mergeWith(csp(jCTypeUnion.alternatives));
            this.result = sourceRange;
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitUnary(JCTree.JCUnary jCUnary) {
            SourceRange sourceRange = new SourceRange(startPos(jCUnary), endPos(jCUnary));
            sourceRange.mergeWith(csp(jCUnary.arg));
            this.result = sourceRange;
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitVarDef(JCTree.JCVariableDecl jCVariableDecl) {
            SourceRange sourceRange = new SourceRange(startPos(jCVariableDecl), endPos(jCVariableDecl));
            csp(jCVariableDecl.vartype);
            sourceRange.mergeWith(csp(jCVariableDecl.init));
            this.result = sourceRange;
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitWhileLoop(JCTree.JCWhileLoop jCWhileLoop) {
            SourceRange sourceRange = new SourceRange(startPos(jCWhileLoop), endPos(jCWhileLoop));
            sourceRange.mergeWith(csp(jCWhileLoop.cond));
            sourceRange.mergeWith(csp(jCWhileLoop.body));
            this.result = sourceRange;
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitWildcard(JCTree.JCWildcard jCWildcard) {
            this.result = null;
        }

        @Override // com.sun.tools.javac.tree.JCTree.Visitor
        public void visitYield(JCTree.JCYield jCYield) {
            SourceRange sourceRange = new SourceRange(startPos(jCYield), endPos(jCYield));
            sourceRange.mergeWith(csp(jCYield.value));
            this.result = sourceRange;
        }

        public SourceRange csp(JCTree jCTree) {
            if (jCTree == null) {
                return null;
            }
            jCTree.accept(this);
            if (this.result != null) {
                CRTable.this.positions.put(jCTree, this.result);
            }
            return this.result;
        }
    }
}
