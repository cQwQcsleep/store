package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.generic.BranchHandle;
import com.sun.org.apache.bcel.internal.generic.BranchInstruction;
import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.GOTO_W;
import com.sun.org.apache.bcel.internal.generic.IF_ICMPEQ;
import com.sun.org.apache.bcel.internal.generic.ILOAD;
import com.sun.org.apache.bcel.internal.generic.INVOKEINTERFACE;
import com.sun.org.apache.bcel.internal.generic.ISTORE;
import com.sun.org.apache.bcel.internal.generic.InstructionHandle;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.bcel.internal.generic.LocalVariableGen;
import com.sun.org.apache.bcel.internal.generic.PUSH;
import com.sun.org.apache.bcel.internal.generic.StackInstruction;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class AbsolutePathPattern extends LocationPathPattern {
    private final RelativePathPattern _left;

    public AbsolutePathPattern(RelativePathPattern relativePathPattern) {
        this._left = relativePathPattern;
        if (relativePathPattern != null) {
            relativePathPattern.setParent(this);
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.LocationPathPattern
    public StepPattern getKernelPattern() {
        RelativePathPattern relativePathPattern = this._left;
        if (relativePathPattern != null) {
            return relativePathPattern.getKernelPattern();
        }
        return null;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.LocationPathPattern
    public boolean isWildcard() {
        return false;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.LocationPathPattern
    public void reduceKernelPattern() {
        this._left.reduceKernelPattern();
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void setParser(Parser parser) {
        super.setParser(parser);
        RelativePathPattern relativePathPattern = this._left;
        if (relativePathPattern != null) {
            relativePathPattern.setParser(parser);
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.LocationPathPattern, com.sun.org.apache.xalan.internal.xsltc.compiler.Expression
    public String toString() {
        StringBuilder sb = new StringBuilder("absolutePathPattern(");
        RelativePathPattern relativePathPattern = this._left;
        sb.append(relativePathPattern != null ? relativePathPattern.toString() : ")");
        return sb.toString();
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.LocationPathPattern, com.sun.org.apache.xalan.internal.xsltc.compiler.Pattern, com.sun.org.apache.xalan.internal.xsltc.compiler.Expression, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void translate(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        RelativePathPattern relativePathPattern = this._left;
        if (relativePathPattern != null) {
            if (relativePathPattern instanceof StepPattern) {
                LocalVariableGen localVariableGenAddLocalVariable2 = methodGenerator.addLocalVariable2("apptmp", Util.getJCRefType("I"), null);
                instructionList.append(Constants.DUP);
                localVariableGenAddLocalVariable2.setStart(instructionList.append(new ISTORE(localVariableGenAddLocalVariable2.getIndex())));
                this._left.translate(classGenerator, methodGenerator);
                instructionList.append(methodGenerator.loadDOM());
                localVariableGenAddLocalVariable2.setEnd(instructionList.append(new ILOAD(localVariableGenAddLocalVariable2.getIndex())));
                methodGenerator.removeLocalVariable(localVariableGenAddLocalVariable2);
            } else {
                relativePathPattern.translate(classGenerator, methodGenerator);
            }
        }
        int iAddInterfaceMethodref = constantPool.addInterfaceMethodref(Constants.DOM_INTF, Constants.GET_PARENT, Constants.GET_PARENT_SIG);
        int iAddInterfaceMethodref2 = constantPool.addInterfaceMethodref(Constants.DOM_INTF, "getExpandedTypeID", Constants.GET_PARENT_SIG);
        InstructionHandle instructionHandleAppend = instructionList.append(methodGenerator.loadDOM());
        StackInstruction stackInstruction = Constants.SWAP;
        instructionList.append(stackInstruction);
        instructionList.append(new INVOKEINTERFACE(iAddInterfaceMethodref, 2));
        if (this._left instanceof AncestorPattern) {
            instructionList.append(methodGenerator.loadDOM());
            instructionList.append(stackInstruction);
        }
        instructionList.append(new INVOKEINTERFACE(iAddInterfaceMethodref2, 2));
        instructionList.append(new PUSH(constantPool, 9));
        BranchHandle branchHandleAppend = instructionList.append((BranchInstruction) new IF_ICMPEQ(null));
        this._falseList.add(instructionList.append((BranchInstruction) new GOTO_W(null)));
        branchHandleAppend.setTarget(instructionList.append(Constants.NOP));
        RelativePathPattern relativePathPattern2 = this._left;
        if (relativePathPattern2 != null) {
            relativePathPattern2.backPatchTrueList(instructionHandleAppend);
            RelativePathPattern relativePathPattern3 = this._left;
            if (relativePathPattern3 instanceof AncestorPattern) {
                this._falseList.backPatch(((AncestorPattern) relativePathPattern3).getLoopHandle());
            }
            this._falseList.append(this._left._falseList);
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.LocationPathPattern, com.sun.org.apache.xalan.internal.xsltc.compiler.Pattern, com.sun.org.apache.xalan.internal.xsltc.compiler.Expression, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public Type typeCheck(SymbolTable symbolTable) throws TypeCheckError {
        RelativePathPattern relativePathPattern = this._left;
        return relativePathPattern == null ? Type.Root : relativePathPattern.typeCheck(symbolTable);
    }
}
