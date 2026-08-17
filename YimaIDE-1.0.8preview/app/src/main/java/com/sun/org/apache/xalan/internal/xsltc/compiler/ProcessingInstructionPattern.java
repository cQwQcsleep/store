package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.generic.BranchHandle;
import com.sun.org.apache.bcel.internal.generic.BranchInstruction;
import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.GOTO;
import com.sun.org.apache.bcel.internal.generic.IFEQ;
import com.sun.org.apache.bcel.internal.generic.IF_ICMPEQ;
import com.sun.org.apache.bcel.internal.generic.INVOKEINTERFACE;
import com.sun.org.apache.bcel.internal.generic.INVOKEVIRTUAL;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.bcel.internal.generic.PUSH;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import com.sun.org.apache.xpath.internal.XPath;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class ProcessingInstructionPattern extends StepPattern {
    private String _name;
    private boolean _typeChecked;

    public ProcessingInstructionPattern(String str) {
        super(3, 7, null);
        this._typeChecked = false;
        this._name = str;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.StepPattern, com.sun.org.apache.xalan.internal.xsltc.compiler.LocationPathPattern
    public double getDefaultPriority() {
        if (this._name != null) {
            return XPath.MATCH_SCORE_QNAME;
        }
        return -0.5d;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.StepPattern, com.sun.org.apache.xalan.internal.xsltc.compiler.LocationPathPattern
    public boolean isWildcard() {
        return false;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.StepPattern, com.sun.org.apache.xalan.internal.xsltc.compiler.LocationPathPattern
    public void reduceKernelPattern() {
        this._typeChecked = true;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.StepPattern, com.sun.org.apache.xalan.internal.xsltc.compiler.LocationPathPattern, com.sun.org.apache.xalan.internal.xsltc.compiler.Expression
    public String toString() {
        List<Predicate> list = this._predicates;
        String str = this._name;
        if (list == null) {
            return "processing-instruction(" + str + ")";
        }
        return "processing-instruction(" + str + ")" + this._predicates;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.StepPattern, com.sun.org.apache.xalan.internal.xsltc.compiler.LocationPathPattern, com.sun.org.apache.xalan.internal.xsltc.compiler.Pattern, com.sun.org.apache.xalan.internal.xsltc.compiler.Expression, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void translate(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        int iAddInterfaceMethodref = constantPool.addInterfaceMethodref(Constants.DOM_INTF, "getNodeName", "(I)Ljava/lang/String;");
        int iAddMethodref = constantPool.addMethodref("java.lang.String", "equals", "(Ljava/lang/Object;)Z");
        instructionList.append(methodGenerator.loadCurrentNode());
        instructionList.append(Constants.SWAP);
        instructionList.append(methodGenerator.storeCurrentNode());
        if (!this._typeChecked) {
            instructionList.append(methodGenerator.loadCurrentNode());
            int iAddInterfaceMethodref2 = constantPool.addInterfaceMethodref(Constants.DOM_INTF, "getExpandedTypeID", Constants.GET_PARENT_SIG);
            instructionList.append(methodGenerator.loadDOM());
            instructionList.append(methodGenerator.loadCurrentNode());
            instructionList.append(new INVOKEINTERFACE(iAddInterfaceMethodref2, 2));
            instructionList.append(new PUSH(constantPool, 7));
            this._falseList.add(instructionList.append((BranchInstruction) new IF_ICMPEQ(null)));
        }
        instructionList.append(new PUSH(constantPool, this._name));
        instructionList.append(methodGenerator.loadDOM());
        instructionList.append(methodGenerator.loadCurrentNode());
        instructionList.append(new INVOKEINTERFACE(iAddInterfaceMethodref, 2));
        instructionList.append(new INVOKEVIRTUAL(iAddMethodref));
        this._falseList.add(instructionList.append((BranchInstruction) new IFEQ(null)));
        if (hasPredicates()) {
            int size = this._predicates.size();
            for (int i = 0; i < size; i++) {
                Expression expr = this._predicates.get(i).getExpr();
                expr.translateDesynthesized(classGenerator, methodGenerator);
                this._trueList.append(expr._trueList);
                this._falseList.append(expr._falseList);
            }
        }
        backPatchTrueList(instructionList.append(methodGenerator.storeCurrentNode()));
        BranchHandle branchHandleAppend = instructionList.append((BranchInstruction) new GOTO(null));
        backPatchFalseList(instructionList.append(methodGenerator.storeCurrentNode()));
        this._falseList.add(instructionList.append((BranchInstruction) new GOTO(null)));
        branchHandleAppend.setTarget(instructionList.append(Constants.NOP));
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.StepPattern, com.sun.org.apache.xalan.internal.xsltc.compiler.LocationPathPattern, com.sun.org.apache.xalan.internal.xsltc.compiler.Pattern, com.sun.org.apache.xalan.internal.xsltc.compiler.Expression, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public Type typeCheck(SymbolTable symbolTable) throws TypeCheckError {
        if (hasPredicates()) {
            int size = this._predicates.size();
            for (int i = 0; i < size; i++) {
                this._predicates.get(i).typeCheck(symbolTable);
            }
        }
        return Type.NodeSet;
    }
}
