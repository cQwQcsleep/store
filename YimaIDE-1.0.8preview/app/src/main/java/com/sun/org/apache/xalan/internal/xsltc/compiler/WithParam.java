package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.generic.ALOAD;
import com.sun.org.apache.bcel.internal.generic.ASTORE;
import com.sun.org.apache.bcel.internal.generic.CHECKCAST;
import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.INVOKEINTERFACE;
import com.sun.org.apache.bcel.internal.generic.INVOKEVIRTUAL;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.bcel.internal.generic.LocalVariableGen;
import com.sun.org.apache.bcel.internal.generic.PUSH;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ReferenceType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;
import com.sun.org.apache.xml.internal.utils.XML11Char;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class WithParam extends Instruction {
    private boolean _doParameterOptimization = false;
    private LocalVariableGen _domAdapter;
    protected String _escapedName;
    private QName _name;
    private Expression _select;

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void display(int i) {
        indent(i);
        Util.println("with-param " + this._name);
        if (this._select != null) {
            indent(i + 4);
            Util.println("select " + this._select.toString());
        }
        displayContents(i + 4);
    }

    public String getEscapedName() {
        return this._escapedName;
    }

    public QName getName() {
        return this._name;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void parseContents(Parser parser) {
        String attribute = getAttribute("name");
        if (attribute.length() > 0) {
            if (!XML11Char.isXML11ValidQName(attribute)) {
                parser.reportError(3, new ErrorMsg("INVALID_QNAME_ERR", (Object) attribute, (SyntaxTreeNode) this));
            }
            setName(parser.getQNameIgnoreDefaultNs(attribute));
        } else {
            reportError(this, parser, ErrorMsg.REQUIRED_ATTR_ERR, "name");
        }
        if (getAttribute(com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_SELECT).length() > 0) {
            this._select = parser.parseExpression(this, com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_SELECT, null);
        }
        parseChildren(parser);
    }

    public void releaseResultTree(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        if (this._domAdapter != null) {
            ConstantPoolGen constantPool = classGenerator.getConstantPool();
            InstructionList instructionList = methodGenerator.getInstructionList();
            if (classGenerator.getStylesheet().callsNodeset() && classGenerator.getDOMClass().equals(Constants.MULTI_DOM_CLASS)) {
                int iAddMethodref = constantPool.addMethodref(Constants.MULTI_DOM_CLASS, "removeDOMAdapter", "(Lcom/sun/org/apache/xalan/internal/xsltc/dom/DOMAdapter;)V");
                instructionList.append(methodGenerator.loadDOM());
                instructionList.append(new CHECKCAST(constantPool.addClass(Constants.MULTI_DOM_CLASS)));
                instructionList.append(new ALOAD(this._domAdapter.getIndex()));
                instructionList.append(new CHECKCAST(constantPool.addClass(Constants.DOM_ADAPTER_CLASS)));
                instructionList.append(new INVOKEVIRTUAL(iAddMethodref));
            }
            int iAddInterfaceMethodref = constantPool.addInterfaceMethodref(Constants.DOM_IMPL_CLASS, "release", "()V");
            instructionList.append(new ALOAD(this._domAdapter.getIndex()));
            instructionList.append(new INVOKEINTERFACE(iAddInterfaceMethodref, 1));
            this._domAdapter.setEnd(instructionList.getEnd());
            methodGenerator.removeLocalVariable(this._domAdapter);
            this._domAdapter = null;
        }
    }

    public void setDoParameterOptimization(boolean z) {
        this._doParameterOptimization = z;
    }

    public void setName(QName qName) {
        this._name = qName;
        this._escapedName = Util.escape(qName.getStringRep());
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Instruction, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void translate(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        if (this._doParameterOptimization) {
            translateValue(classGenerator, methodGenerator);
            return;
        }
        String strEscape = Util.escape(getEscapedName());
        instructionList.append(classGenerator.loadTranslet());
        instructionList.append(new PUSH(constantPool, strEscape));
        translateValue(classGenerator, methodGenerator);
        instructionList.append(new PUSH(constantPool, false));
        instructionList.append(new INVOKEVIRTUAL(constantPool.addMethodref(Constants.TRANSLET_CLASS, Constants.ADD_PARAMETER, Constants.ADD_PARAMETER_SIG)));
        instructionList.append(Constants.POP);
    }

    public void translateValue(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        Expression expression = this._select;
        if (expression != null) {
            expression.translate(classGenerator, methodGenerator);
            this._select.startIterator(classGenerator, methodGenerator);
            return;
        }
        if (!hasContents()) {
            methodGenerator.getInstructionList().append(new PUSH(classGenerator.getConstantPool(), ""));
            return;
        }
        InstructionList instructionList = methodGenerator.getInstructionList();
        compileResultTree(classGenerator, methodGenerator);
        this._domAdapter = methodGenerator.addLocalVariable2("@" + this._escapedName, Type.ResultTree.toJCType(), instructionList.getEnd());
        instructionList.append(Constants.DUP);
        instructionList.append(new ASTORE(this._domAdapter.getIndex()));
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Instruction, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public Type typeCheck(SymbolTable symbolTable) throws TypeCheckError {
        Expression expression = this._select;
        if (expression == null) {
            typeCheckContents(symbolTable);
        } else if (!(expression.typeCheck(symbolTable) instanceof ReferenceType)) {
            this._select = new CastExpr(this._select, Type.Reference);
        }
        return Type.Void;
    }
}
