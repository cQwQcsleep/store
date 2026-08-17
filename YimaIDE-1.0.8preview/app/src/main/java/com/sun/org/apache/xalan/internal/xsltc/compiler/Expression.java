package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.generic.BranchHandle;
import com.sun.org.apache.bcel.internal.generic.BranchInstruction;
import com.sun.org.apache.bcel.internal.generic.GOTO_W;
import com.sun.org.apache.bcel.internal.generic.IFEQ;
import com.sun.org.apache.bcel.internal.generic.InstructionHandle;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.BooleanType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.NodeSetType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
abstract class Expression extends SyntaxTreeNode {
    protected Type _type;
    protected FlowList _trueList = new FlowList();
    protected FlowList _falseList = new FlowList();

    public void backPatchFalseList(InstructionHandle instructionHandle) {
        this._falseList.backPatch(instructionHandle);
    }

    public void backPatchTrueList(InstructionHandle instructionHandle) {
        this._trueList.backPatch(instructionHandle);
    }

    public final InstructionList compile(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        InstructionList instructionList = methodGenerator.getInstructionList();
        InstructionList instructionList2 = new InstructionList();
        methodGenerator.setInstructionList(instructionList2);
        translate(classGenerator, methodGenerator);
        methodGenerator.setInstructionList(instructionList);
        return instructionList2;
    }

    public void desynthesize(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        this._falseList.add(methodGenerator.getInstructionList().append((BranchInstruction) new IFEQ(null)));
    }

    public Object evaluateAtCompileTime() {
        return null;
    }

    public FlowList getFalseList() {
        return this._falseList;
    }

    public FlowList getTrueList() {
        return this._trueList;
    }

    public Type getType() {
        return this._type;
    }

    public boolean hasLastCall() {
        return false;
    }

    public boolean hasPositionCall() {
        return false;
    }

    public MethodType lookupPrimop(SymbolTable symbolTable, String str, MethodType methodType) {
        List<MethodType> listLookupPrimop = symbolTable.lookupPrimop(str);
        MethodType methodType2 = null;
        if (listLookupPrimop != null) {
            int size = listLookupPrimop.size();
            int i = Integer.MAX_VALUE;
            for (int i2 = 0; i2 < size; i2++) {
                MethodType methodType3 = listLookupPrimop.get(i2);
                if (methodType3.argsCount() == methodType.argsCount()) {
                    if (methodType2 == null) {
                        methodType2 = methodType3;
                    }
                    int iDistanceTo = methodType.distanceTo(methodType3);
                    if (iDistanceTo < i) {
                        methodType2 = methodType3;
                        i = iDistanceTo;
                    }
                }
            }
        }
        return methodType2;
    }

    public void startIterator(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        if (this._type instanceof NodeSetType) {
            if (this instanceof CastExpr) {
                this = ((CastExpr) this).getExpr();
            }
            if (this instanceof VariableRefBase) {
                return;
            }
            InstructionList instructionList = methodGenerator.getInstructionList();
            instructionList.append(methodGenerator.loadContextNode());
            instructionList.append(methodGenerator.setStartNode());
        }
    }

    public void synthesize(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        this._trueList.backPatch(instructionList.append(Constants.ICONST_1));
        BranchHandle branchHandleAppend = instructionList.append((BranchInstruction) new GOTO_W(null));
        this._falseList.backPatch(instructionList.append(Constants.ICONST_0));
        branchHandleAppend.setTarget(instructionList.append(Constants.NOP));
    }

    public abstract String toString();

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void translate(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        getParser().reportError(2, new ErrorMsg(ErrorMsg.NOT_IMPLEMENTED_ERR, (Object) getClass(), (SyntaxTreeNode) this));
    }

    public void translateDesynthesized(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        translate(classGenerator, methodGenerator);
        if (this._type instanceof BooleanType) {
            desynthesize(classGenerator, methodGenerator);
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public Type typeCheck(SymbolTable symbolTable) throws TypeCheckError {
        return typeCheckContents(symbolTable);
    }
}
