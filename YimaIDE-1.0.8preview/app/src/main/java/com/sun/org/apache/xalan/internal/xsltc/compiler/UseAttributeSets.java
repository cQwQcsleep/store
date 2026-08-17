package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.INVOKESPECIAL;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class UseAttributeSets extends Instruction {
    private static final String ATTR_SET_NOT_FOUND = "";
    private final List<QName> _sets = new ArrayList(2);

    public UseAttributeSets(String str, Parser parser) {
        setParser(parser);
        addAttributeSets(str);
    }

    public void addAttributeSets(String str) {
        if (str == null || str.equals("")) {
            return;
        }
        StringTokenizer stringTokenizer = new StringTokenizer(str);
        while (stringTokenizer.hasMoreTokens()) {
            this._sets.add(getParser().getQNameIgnoreDefaultNs(stringTokenizer.nextToken()));
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Instruction, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void translate(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        SymbolTable symbolTable = getParser().getSymbolTable();
        for (QName qName : this._sets) {
            AttributeSet attributeSetLookupAttributeSet = symbolTable.lookupAttributeSet(qName);
            if (attributeSetLookupAttributeSet != null) {
                String methodName = attributeSetLookupAttributeSet.getMethodName();
                instructionList.append(classGenerator.loadTranslet());
                instructionList.append(methodGenerator.loadDOM());
                instructionList.append(methodGenerator.loadIterator());
                instructionList.append(methodGenerator.loadHandler());
                instructionList.append(methodGenerator.loadCurrentNode());
                instructionList.append(new INVOKESPECIAL(constantPool.addMethodref(classGenerator.getClassName(), methodName, Constants.ATTR_SET_SIG)));
            } else {
                reportError(this, getParser(), ErrorMsg.ATTRIBSET_UNDEF_ERR, qName.toString());
            }
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Instruction, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public Type typeCheck(SymbolTable symbolTable) throws TypeCheckError {
        return Type.Void;
    }
}
