package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.generic.BranchHandle;
import com.sun.org.apache.bcel.internal.generic.BranchInstruction;
import com.sun.org.apache.bcel.internal.generic.GOTO;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class NotCall extends FunctionCall {
    public NotCall(QName qName, List<Expression> list) {
        super(qName, list);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.FunctionCall, com.sun.org.apache.xalan.internal.xsltc.compiler.Expression, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void translate(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        InstructionList instructionList = methodGenerator.getInstructionList();
        argument().translate(classGenerator, methodGenerator);
        instructionList.append(Constants.ICONST_1);
        instructionList.append(Constants.IXOR);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.FunctionCall, com.sun.org.apache.xalan.internal.xsltc.compiler.Expression
    public void translateDesynthesized(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        InstructionList instructionList = methodGenerator.getInstructionList();
        Expression expressionArgument = argument();
        expressionArgument.translateDesynthesized(classGenerator, methodGenerator);
        BranchHandle branchHandleAppend = instructionList.append((BranchInstruction) new GOTO(null));
        this._trueList = expressionArgument._falseList;
        FlowList flowList = expressionArgument._trueList;
        this._falseList = flowList;
        flowList.add(branchHandleAppend);
    }
}
