package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.generic.BranchHandle;
import com.sun.org.apache.bcel.internal.generic.BranchInstruction;
import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.GOTO;
import com.sun.org.apache.bcel.internal.generic.IFEQ;
import com.sun.org.apache.bcel.internal.generic.IFGE;
import com.sun.org.apache.bcel.internal.generic.IFGT;
import com.sun.org.apache.bcel.internal.generic.ILOAD;
import com.sun.org.apache.bcel.internal.generic.INVOKEINTERFACE;
import com.sun.org.apache.bcel.internal.generic.INVOKEVIRTUAL;
import com.sun.org.apache.bcel.internal.generic.ISTORE;
import com.sun.org.apache.bcel.internal.generic.InstructionHandle;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.bcel.internal.generic.LocalVariableGen;
import com.sun.org.apache.bcel.internal.generic.PUSH;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.NodeSetType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.StringType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;
import com.sun.org.apache.xml.internal.utils.XML11Char;
import com.sun.org.apache.xpath.internal.compiler.Keywords;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class Key extends TopLevelElement {
    private Pattern _match;
    private QName _name;
    private Expression _use;
    private Type _useType;

    public String getName() {
        return this._name.toString();
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void parseContents(Parser parser) {
        String attribute = getAttribute("name");
        if (!XML11Char.isXML11ValidQName(attribute)) {
            parser.reportError(3, new ErrorMsg("INVALID_QNAME_ERR", (Object) attribute, (SyntaxTreeNode) this));
        }
        this._name = parser.getQNameIgnoreDefaultNs(attribute);
        getSymbolTable().addKey(this._name, this);
        this._match = parser.parsePattern(this, com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_MATCH, null);
        this._use = parser.parseExpression(this, "use", null);
        if (this._name == null) {
            reportError(this, parser, ErrorMsg.REQUIRED_ATTR_ERR, "name");
        } else if (this._match.isDummy()) {
            reportError(this, parser, ErrorMsg.REQUIRED_ATTR_ERR, com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_MATCH);
        } else if (this._use.isDummy()) {
            reportError(this, parser, ErrorMsg.REQUIRED_ATTR_ERR, "use");
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.TopLevelElement, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void translate(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        methodGenerator.getLocalIndex(Keywords.FUNC_CURRENT_STRING);
        int iAddMethodref = constantPool.addMethodref(Constants.TRANSLET_CLASS, "buildKeyIndex", "(Ljava/lang/String;ILjava/lang/String;)V");
        int iAddMethodref2 = constantPool.addMethodref(Constants.TRANSLET_CLASS, "setKeyIndexDom", "(Ljava/lang/String;Lcom/sun/org/apache/xalan/internal/xsltc/DOM;)V");
        constantPool.addInterfaceMethodref(Constants.DOM_INTF, "getNodeIdent", Constants.GET_PARENT_SIG);
        int iAddInterfaceMethodref = constantPool.addInterfaceMethodref(Constants.DOM_INTF, "getAxisIterator", "(I)Lcom/sun/org/apache/xml/internal/dtm/DTMAxisIterator;");
        instructionList.append(methodGenerator.loadCurrentNode());
        instructionList.append(methodGenerator.loadIterator());
        instructionList.append(methodGenerator.loadDOM());
        instructionList.append(new PUSH(constantPool, 4));
        instructionList.append(new INVOKEINTERFACE(iAddInterfaceMethodref, 2));
        instructionList.append(methodGenerator.loadCurrentNode());
        instructionList.append(methodGenerator.setStartNode());
        instructionList.append(methodGenerator.storeIterator());
        BranchHandle branchHandleAppend = instructionList.append((BranchInstruction) new GOTO(null));
        com.sun.org.apache.bcel.internal.generic.Instruction instruction = Constants.NOP;
        InstructionHandle instructionHandleAppend = instructionList.append(instruction);
        instructionList.append(methodGenerator.loadCurrentNode());
        this._match.translate(classGenerator, methodGenerator);
        this._match.synthesize(classGenerator, methodGenerator);
        BranchHandle branchHandleAppend2 = instructionList.append((BranchInstruction) new IFEQ(null));
        if (this._useType instanceof NodeSetType) {
            instructionList.append(methodGenerator.loadCurrentNode());
            traverseNodeSet(classGenerator, methodGenerator, iAddMethodref);
        } else {
            instructionList.append(classGenerator.loadTranslet());
            instructionList.append(Constants.DUP);
            instructionList.append(new PUSH(constantPool, this._name.toString()));
            instructionList.append(Constants.DUP_X1);
            instructionList.append(methodGenerator.loadCurrentNode());
            this._use.translate(classGenerator, methodGenerator);
            instructionList.append(new INVOKEVIRTUAL(iAddMethodref));
            instructionList.append(methodGenerator.loadDOM());
            instructionList.append(new INVOKEVIRTUAL(iAddMethodref2));
        }
        InstructionHandle instructionHandleAppend2 = instructionList.append(instruction);
        instructionList.append(methodGenerator.loadIterator());
        instructionList.append(methodGenerator.nextNode());
        instructionList.append(Constants.DUP);
        instructionList.append(methodGenerator.storeCurrentNode());
        instructionList.append((BranchInstruction) new IFGT(instructionHandleAppend));
        instructionList.append(methodGenerator.storeIterator());
        instructionList.append(methodGenerator.storeCurrentNode());
        branchHandleAppend.setTarget(instructionHandleAppend2);
        branchHandleAppend2.setTarget(instructionHandleAppend2);
    }

    public void traverseNodeSet(ClassGenerator classGenerator, MethodGenerator methodGenerator, int i) {
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        int iAddInterfaceMethodref = constantPool.addInterfaceMethodref(Constants.DOM_INTF, Constants.GET_NODE_VALUE, "(I)Ljava/lang/String;");
        constantPool.addInterfaceMethodref(Constants.DOM_INTF, "getNodeIdent", Constants.GET_PARENT_SIG);
        int iAddMethodref = constantPool.addMethodref(Constants.TRANSLET_CLASS, "setKeyIndexDom", "(Ljava/lang/String;Lcom/sun/org/apache/xalan/internal/xsltc/DOM;)V");
        LocalVariableGen localVariableGenAddLocalVariable = methodGenerator.addLocalVariable("parentNode", Util.getJCRefType("I"), null, null);
        localVariableGenAddLocalVariable.setStart(instructionList.append(new ISTORE(localVariableGenAddLocalVariable.getIndex())));
        instructionList.append(methodGenerator.loadCurrentNode());
        instructionList.append(methodGenerator.loadIterator());
        this._use.translate(classGenerator, methodGenerator);
        this._use.startIterator(classGenerator, methodGenerator);
        instructionList.append(methodGenerator.storeIterator());
        BranchHandle branchHandleAppend = instructionList.append((BranchInstruction) new GOTO(null));
        InstructionHandle instructionHandleAppend = instructionList.append(Constants.NOP);
        instructionList.append(classGenerator.loadTranslet());
        instructionList.append(new PUSH(constantPool, this._name.toString()));
        localVariableGenAddLocalVariable.setEnd(instructionList.append(new ILOAD(localVariableGenAddLocalVariable.getIndex())));
        instructionList.append(methodGenerator.loadDOM());
        instructionList.append(methodGenerator.loadCurrentNode());
        instructionList.append(new INVOKEINTERFACE(iAddInterfaceMethodref, 2));
        instructionList.append(new INVOKEVIRTUAL(i));
        instructionList.append(classGenerator.loadTranslet());
        instructionList.append(new PUSH(constantPool, getName()));
        instructionList.append(methodGenerator.loadDOM());
        instructionList.append(new INVOKEVIRTUAL(iAddMethodref));
        branchHandleAppend.setTarget(instructionList.append(methodGenerator.loadIterator()));
        instructionList.append(methodGenerator.nextNode());
        instructionList.append(Constants.DUP);
        instructionList.append(methodGenerator.storeCurrentNode());
        instructionList.append((BranchInstruction) new IFGE(instructionHandleAppend));
        instructionList.append(methodGenerator.storeIterator());
        instructionList.append(methodGenerator.storeCurrentNode());
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.TopLevelElement, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public Type typeCheck(SymbolTable symbolTable) throws TypeCheckError {
        this._match.typeCheck(symbolTable);
        Type typeTypeCheck = this._use.typeCheck(symbolTable);
        this._useType = typeTypeCheck;
        if (!(typeTypeCheck instanceof StringType) && !(typeTypeCheck instanceof NodeSetType)) {
            this._use = new CastExpr(this._use, Type.String);
        }
        return Type.Void;
    }
}
