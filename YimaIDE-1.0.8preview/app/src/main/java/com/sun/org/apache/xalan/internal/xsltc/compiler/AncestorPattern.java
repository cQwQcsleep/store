package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.generic.BranchHandle;
import com.sun.org.apache.bcel.internal.generic.BranchInstruction;
import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.GOTO;
import com.sun.org.apache.bcel.internal.generic.IFLT;
import com.sun.org.apache.bcel.internal.generic.ILOAD;
import com.sun.org.apache.bcel.internal.generic.INVOKEINTERFACE;
import com.sun.org.apache.bcel.internal.generic.ISTORE;
import com.sun.org.apache.bcel.internal.generic.InstructionHandle;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.bcel.internal.generic.LocalVariableGen;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class AncestorPattern extends RelativePathPattern {
    private final Pattern _left;
    private InstructionHandle _loop;
    private final RelativePathPattern _right;

    public AncestorPattern(Pattern pattern, RelativePathPattern relativePathPattern) {
        this._left = pattern;
        this._right = relativePathPattern;
        relativePathPattern.setParent(this);
        if (pattern != null) {
            pattern.setParent(this);
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.LocationPathPattern
    public StepPattern getKernelPattern() {
        return this._right.getKernelPattern();
    }

    public InstructionHandle getLoopHandle() {
        return this._loop;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.LocationPathPattern
    public boolean isWildcard() {
        return false;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.LocationPathPattern
    public void reduceKernelPattern() {
        this._right.reduceKernelPattern();
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void setParser(Parser parser) {
        super.setParser(parser);
        Pattern pattern = this._left;
        if (pattern != null) {
            pattern.setParser(parser);
        }
        this._right.setParser(parser);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.LocationPathPattern, com.sun.org.apache.xalan.internal.xsltc.compiler.Expression
    public String toString() {
        return "AncestorPattern(" + this._left + ", " + this._right + ')';
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.LocationPathPattern, com.sun.org.apache.xalan.internal.xsltc.compiler.Pattern, com.sun.org.apache.xalan.internal.xsltc.compiler.Expression, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void translate(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        LocalVariableGen localVariableGenAddLocalVariable2 = methodGenerator.addLocalVariable2("app", Util.getJCRefType("I"), instructionList.getEnd());
        ILOAD iload = new ILOAD(localVariableGenAddLocalVariable2.getIndex());
        ISTORE istore = new ISTORE(localVariableGenAddLocalVariable2.getIndex());
        RelativePathPattern relativePathPattern = this._right;
        if (relativePathPattern instanceof StepPattern) {
            instructionList.append(Constants.DUP);
            instructionList.append(istore);
            this._right.translate(classGenerator, methodGenerator);
            instructionList.append(methodGenerator.loadDOM());
            instructionList.append(iload);
        } else {
            relativePathPattern.translate(classGenerator, methodGenerator);
            if (this._right instanceof AncestorPattern) {
                instructionList.append(methodGenerator.loadDOM());
                instructionList.append(Constants.SWAP);
            }
        }
        if (this._left != null) {
            InstructionHandle instructionHandleAppend = instructionList.append(new INVOKEINTERFACE(constantPool.addInterfaceMethodref(Constants.DOM_INTF, Constants.GET_PARENT, Constants.GET_PARENT_SIG), 2));
            instructionList.append(Constants.DUP);
            instructionList.append(istore);
            this._falseList.add(instructionList.append((BranchInstruction) new IFLT(null)));
            instructionList.append(iload);
            this._left.translate(classGenerator, methodGenerator);
            SyntaxTreeNode parent = getParent();
            if (parent != null && !(parent instanceof Instruction) && !(parent instanceof TopLevelElement)) {
                instructionList.append(iload);
            }
            BranchHandle branchHandleAppend = instructionList.append((BranchInstruction) new GOTO(null));
            this._loop = instructionList.append(methodGenerator.loadDOM());
            instructionList.append(iload);
            localVariableGenAddLocalVariable2.setEnd(this._loop);
            instructionList.append((BranchInstruction) new GOTO(instructionHandleAppend));
            branchHandleAppend.setTarget(instructionList.append(Constants.NOP));
            this._left.backPatchFalseList(this._loop);
            this._trueList.append(this._left._trueList);
        } else {
            instructionList.append(Constants.POP2);
        }
        RelativePathPattern relativePathPattern2 = this._right;
        if (relativePathPattern2 instanceof AncestorPattern) {
            this._falseList.backPatch(((AncestorPattern) relativePathPattern2).getLoopHandle());
        }
        this._trueList.append(this._right._trueList);
        this._falseList.append(this._right._falseList);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.LocationPathPattern, com.sun.org.apache.xalan.internal.xsltc.compiler.Pattern, com.sun.org.apache.xalan.internal.xsltc.compiler.Expression, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public Type typeCheck(SymbolTable symbolTable) throws TypeCheckError {
        Pattern pattern = this._left;
        if (pattern != null) {
            pattern.typeCheck(symbolTable);
        }
        return this._right.typeCheck(symbolTable);
    }

    public AncestorPattern(RelativePathPattern relativePathPattern) {
        this(null, relativePathPattern);
    }
}
