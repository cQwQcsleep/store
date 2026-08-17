package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.bcel.internal.generic.CHECKCAST;
import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.INVOKEINTERFACE;
import com.sun.org.apache.bcel.internal.generic.INVOKESPECIAL;
import com.sun.org.apache.bcel.internal.generic.INVOKEVIRTUAL;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.bcel.internal.generic.LocalVariableGen;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.bcel.internal.generic.PUSH;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.NodeSetType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ResultTreeType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;
import com.sun.org.apache.xml.internal.utils.XML11Char;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
class VariableBase extends TopLevelElement {
    protected String _escapedName;
    protected boolean _isLocal;
    protected com.sun.org.apache.bcel.internal.generic.Instruction _loadInstruction;
    protected LocalVariableGen _local;
    protected QName _name;
    protected Expression _select;
    protected com.sun.org.apache.bcel.internal.generic.Instruction _storeInstruction;
    protected Type _type;
    protected String select;
    protected List<VariableRefBase> _refs = new ArrayList(2);
    protected boolean _ignore = false;

    public void addReference(VariableRefBase variableRefBase) {
        this._refs.add(variableRefBase);
    }

    public void copyReferences(VariableBase variableBase) {
        int size = this._refs.size();
        for (int i = 0; i < size; i++) {
            variableBase.addReference(this._refs.get(i));
        }
    }

    public void disable() {
        this._ignore = true;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.TopLevelElement, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void display(int i) {
        indent(i);
        System.out.println("Variable " + this._name);
        if (this._select != null) {
            indent(i + 4);
            System.out.println("select " + this._select.toString());
        }
        displayContents(i + 4);
    }

    public String getEscapedName() {
        return this._escapedName;
    }

    public Expression getExpression() {
        return this._select;
    }

    public QName getName() {
        return this._name;
    }

    public Type getType() {
        return this._type;
    }

    public boolean isLocal() {
        return this._isLocal;
    }

    public com.sun.org.apache.bcel.internal.generic.Instruction loadInstruction() {
        if (this._loadInstruction == null) {
            this._loadInstruction = this._type.LOAD(this._local.getIndex());
        }
        return this._loadInstruction;
    }

    public void mapRegister(MethodGenerator methodGenerator) {
        if (this._local == null) {
            this._local = methodGenerator.addLocalVariable2(getEscapedName(), this._type.toJCType(), methodGenerator.getInstructionList().getEnd());
        }
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
        VariableBase variableBaseLookupVariable = parser.lookupVariable(this._name);
        if (variableBaseLookupVariable != null && variableBaseLookupVariable.getParent() == getParent()) {
            reportError(this, parser, ErrorMsg.VARIABLE_REDEF_ERR, attribute);
        }
        String attribute2 = getAttribute(com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_SELECT);
        this.select = attribute2;
        if (attribute2.length() > 0) {
            Expression expression = getParser().parseExpression(this, com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_SELECT, null);
            this._select = expression;
            if (expression.isDummy()) {
                reportError(this, parser, ErrorMsg.REQUIRED_ATTR_ERR, com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_SELECT);
                return;
            }
        }
        parseChildren(parser);
    }

    public void setName(QName qName) {
        this._name = qName;
        this._escapedName = Util.escape(qName.getStringRep());
    }

    public com.sun.org.apache.bcel.internal.generic.Instruction storeInstruction() {
        if (this._storeInstruction == null) {
            this._storeInstruction = this._type.STORE(this._local.getIndex());
        }
        return this._storeInstruction;
    }

    public String toString() {
        return "variable(" + this._name + ")";
    }

    public void translateValue(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        Expression expression = this._select;
        if (expression == null) {
            if (hasContents()) {
                compileResultTree(classGenerator, methodGenerator);
                return;
            } else {
                methodGenerator.getInstructionList().append(new PUSH(classGenerator.getConstantPool(), ""));
                return;
            }
        }
        expression.translate(classGenerator, methodGenerator);
        if (this._select.getType() instanceof NodeSetType) {
            ConstantPoolGen constantPool = classGenerator.getConstantPool();
            InstructionList instructionList = methodGenerator.getInstructionList();
            int iAddMethodref = constantPool.addMethodref(Constants.CACHED_NODE_LIST_ITERATOR_CLASS, Const.CONSTRUCTOR_NAME, "(Lcom/sun/org/apache/xml/internal/dtm/DTMAxisIterator;)V");
            instructionList.append(new NEW(constantPool.addClass(Constants.CACHED_NODE_LIST_ITERATOR_CLASS)));
            instructionList.append(Constants.DUP_X1);
            instructionList.append(Constants.SWAP);
            instructionList.append(new INVOKESPECIAL(iAddMethodref));
        }
        this._select.startIterator(classGenerator, methodGenerator);
    }

    public void unmapRegister(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        if (this._local != null) {
            if (this._type instanceof ResultTreeType) {
                ConstantPoolGen constantPool = classGenerator.getConstantPool();
                InstructionList instructionList = methodGenerator.getInstructionList();
                if (classGenerator.getStylesheet().callsNodeset() && classGenerator.getDOMClass().equals(Constants.MULTI_DOM_CLASS)) {
                    int iAddMethodref = constantPool.addMethodref(Constants.MULTI_DOM_CLASS, "removeDOMAdapter", "(Lcom/sun/org/apache/xalan/internal/xsltc/dom/DOMAdapter;)V");
                    instructionList.append(methodGenerator.loadDOM());
                    instructionList.append(new CHECKCAST(constantPool.addClass(Constants.MULTI_DOM_CLASS)));
                    instructionList.append(loadInstruction());
                    instructionList.append(new CHECKCAST(constantPool.addClass(Constants.DOM_ADAPTER_CLASS)));
                    instructionList.append(new INVOKEVIRTUAL(iAddMethodref));
                }
                int iAddInterfaceMethodref = constantPool.addInterfaceMethodref(Constants.DOM_IMPL_CLASS, "release", "()V");
                instructionList.append(loadInstruction());
                instructionList.append(new INVOKEINTERFACE(iAddInterfaceMethodref, 1));
            }
            this._local.setEnd(methodGenerator.getInstructionList().getEnd());
            methodGenerator.removeLocalVariable(this._local);
            this._refs = null;
            this._local = null;
        }
    }
}
