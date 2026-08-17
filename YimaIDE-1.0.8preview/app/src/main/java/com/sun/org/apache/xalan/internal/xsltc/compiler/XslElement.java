package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.generic.ALOAD;
import com.sun.org.apache.bcel.internal.generic.ASTORE;
import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.INVOKESTATIC;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.bcel.internal.generic.LocalVariableGen;
import com.sun.org.apache.bcel.internal.generic.PUSH;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;
import com.sun.org.apache.xml.internal.utils.XML11Char;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class XslElement extends Instruction {
    private boolean _ignore = false;
    private boolean _isLiteralName = true;
    private AttributeValueTemplate _name;
    private AttributeValueTemplate _namespace;
    private String _prefix;

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void display(int i) {
        indent(i);
        Util.println("Element " + this._name);
        displayContents(i + 4);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void parseContents(Parser parser) {
        SymbolTable symbolTable = parser.getSymbolTable();
        String attribute = getAttribute("name");
        if (attribute == "") {
            parser.reportError(4, new ErrorMsg(ErrorMsg.ILLEGAL_ELEM_NAME_ERR, (Object) attribute, (SyntaxTreeNode) this));
            parseChildren(parser);
            this._ignore = true;
            return;
        }
        String attribute2 = getAttribute(com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_NAMESPACE);
        boolean zIsLiteral = Util.isLiteral(attribute);
        this._isLiteralName = zIsLiteral;
        if (!zIsLiteral) {
            this._namespace = attribute2 == "" ? null : new AttributeValueTemplate(attribute2, parser, this);
        } else {
            if (!XML11Char.isXML11ValidQName(attribute)) {
                parser.reportError(4, new ErrorMsg(ErrorMsg.ILLEGAL_ELEM_NAME_ERR, (Object) attribute, (SyntaxTreeNode) this));
                parseChildren(parser);
                this._ignore = true;
                return;
            }
            QName qNameSafe = parser.getQNameSafe(attribute);
            String prefix = qNameSafe.getPrefix();
            String localPart = qNameSafe.getLocalPart();
            if (prefix == null) {
                prefix = "";
            }
            if (hasAttribute(com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_NAMESPACE)) {
                if (prefix == "") {
                    if (Util.isLiteral(attribute2) && (prefix = lookupPrefix(attribute2)) == null) {
                        prefix = symbolTable.generateNamespacePrefix();
                    }
                    StringBuffer stringBuffer = new StringBuffer(prefix);
                    if (prefix != "") {
                        stringBuffer.append(':');
                    }
                    stringBuffer.append(localPart);
                    attribute = stringBuffer.toString();
                }
                this._prefix = prefix;
                this._namespace = new AttributeValueTemplate(attribute2, parser, this);
            } else {
                String strLookupNamespace = lookupNamespace(prefix);
                if (strLookupNamespace == null) {
                    parser.reportError(4, new ErrorMsg(ErrorMsg.NAMESPACE_UNDEF_ERR, (Object) prefix, (SyntaxTreeNode) this));
                    parseChildren(parser);
                    this._ignore = true;
                    return;
                }
                this._prefix = prefix;
                this._namespace = new AttributeValueTemplate(strLookupNamespace, parser, this);
            }
        }
        this._name = new AttributeValueTemplate(attribute, parser, this);
        String attribute3 = getAttribute(com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_USEATTRIBUTESETS);
        if (attribute3.length() > 0) {
            if (!Util.isValidQNames(attribute3)) {
                parser.reportError(3, new ErrorMsg("INVALID_QNAME_ERR", (Object) attribute3, (SyntaxTreeNode) this));
            }
            setFirstElement(new UseAttributeSets(attribute3, parser));
        }
        parseChildren(parser);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Instruction, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void translate(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        if (this._isLiteralName) {
            translateLiteral(classGenerator, methodGenerator);
            return;
        }
        if (!this._ignore) {
            LocalVariableGen localVariableGenAddLocalVariable2 = methodGenerator.addLocalVariable2("nameValue", Util.getJCRefType(Constants.STRING_SIG), null);
            this._name.translate(classGenerator, methodGenerator);
            localVariableGenAddLocalVariable2.setStart(instructionList.append(new ASTORE(localVariableGenAddLocalVariable2.getIndex())));
            instructionList.append(new ALOAD(localVariableGenAddLocalVariable2.getIndex()));
            instructionList.append(new INVOKESTATIC(constantPool.addMethodref(Constants.BASIS_LIBRARY_CLASS, "checkQName", "(Ljava/lang/String;)V")));
            instructionList.append(methodGenerator.loadHandler());
            localVariableGenAddLocalVariable2.setEnd(instructionList.append(new ALOAD(localVariableGenAddLocalVariable2.getIndex())));
            AttributeValueTemplate attributeValueTemplate = this._namespace;
            if (attributeValueTemplate != null) {
                attributeValueTemplate.translate(classGenerator, methodGenerator);
            } else {
                instructionList.append(Constants.ACONST_NULL);
            }
            instructionList.append(methodGenerator.loadHandler());
            instructionList.append(methodGenerator.loadDOM());
            instructionList.append(methodGenerator.loadCurrentNode());
            instructionList.append(new INVOKESTATIC(constantPool.addMethodref(Constants.BASIS_LIBRARY_CLASS, "startXslElement", "(Ljava/lang/String;Ljava/lang/String;Lcom/sun/org/apache/xml/internal/serializer/SerializationHandler;Lcom/sun/org/apache/xalan/internal/xsltc/DOM;I)Ljava/lang/String;")));
        }
        translateContents(classGenerator, methodGenerator);
        if (this._ignore) {
            return;
        }
        instructionList.append(methodGenerator.endElement());
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void translateContents(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        int iElementCount = elementCount();
        for (int i = 0; i < iElementCount; i++) {
            SyntaxTreeNode syntaxTreeNode = getContents().get(i);
            if (!this._ignore || !(syntaxTreeNode instanceof XslAttribute)) {
                syntaxTreeNode.translate(classGenerator, methodGenerator);
            }
        }
    }

    public void translateLiteral(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        if (!this._ignore) {
            instructionList.append(methodGenerator.loadHandler());
            this._name.translate(classGenerator, methodGenerator);
            instructionList.append(Constants.DUP2);
            instructionList.append(methodGenerator.startElement());
            if (this._namespace != null) {
                instructionList.append(methodGenerator.loadHandler());
                instructionList.append(new PUSH(constantPool, this._prefix));
                this._namespace.translate(classGenerator, methodGenerator);
                instructionList.append(methodGenerator.namespace());
            }
        }
        translateContents(classGenerator, methodGenerator);
        if (this._ignore) {
            return;
        }
        instructionList.append(methodGenerator.endElement());
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Instruction, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public Type typeCheck(SymbolTable symbolTable) throws TypeCheckError {
        if (!this._ignore) {
            this._name.typeCheck(symbolTable);
            AttributeValueTemplate attributeValueTemplate = this._namespace;
            if (attributeValueTemplate != null) {
                attributeValueTemplate.typeCheck(symbolTable);
            }
        }
        typeCheckContents(symbolTable);
        return Type.Void;
    }
}
