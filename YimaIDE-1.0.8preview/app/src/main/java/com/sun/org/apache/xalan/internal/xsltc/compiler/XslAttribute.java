package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.generic.ALOAD;
import com.sun.org.apache.bcel.internal.generic.ASTORE;
import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.GETFIELD;
import com.sun.org.apache.bcel.internal.generic.INVOKESTATIC;
import com.sun.org.apache.bcel.internal.generic.INVOKEVIRTUAL;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.bcel.internal.generic.LocalVariableGen;
import com.sun.org.apache.bcel.internal.generic.PUSH;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;
import com.sun.org.apache.xml.internal.serializer.ElemDesc;
import com.sun.org.apache.xml.internal.utils.XML11Char;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class XslAttribute extends Instruction {
    private AttributeValue _name;
    private String _prefix;
    private AttributeValueTemplate _namespace = null;
    private boolean _ignore = false;
    private boolean _isLiteral = false;

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void display(int i) {
        indent(i);
        Util.println("Attribute " + this._name);
        displayContents(i + 4);
    }

    public AttributeValue getName() {
        return this._name;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void parseContents(Parser parser) {
        boolean z;
        SyntaxTreeNode syntaxTreeNode;
        SymbolTable symbolTable = parser.getSymbolTable();
        String attribute = getAttribute("name");
        String attribute2 = getAttribute(com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_NAMESPACE);
        QName qName = parser.getQName(attribute, false);
        String prefix = qName.getPrefix();
        if ((prefix != null && prefix.equals("xmlns")) || attribute.equals("xmlns")) {
            reportError(this, parser, ErrorMsg.ILLEGAL_ATTR_NAME_ERR, attribute);
            return;
        }
        boolean zIsLiteral = Util.isLiteral(attribute);
        this._isLiteral = zIsLiteral;
        if (zIsLiteral && !XML11Char.isXML11ValidQName(attribute)) {
            reportError(this, parser, ErrorMsg.ILLEGAL_ATTR_NAME_ERR, attribute);
            return;
        }
        SyntaxTreeNode parent = getParent();
        List<SyntaxTreeNode> contents = parent.getContents();
        for (int i = 0; i < parent.elementCount() && (syntaxTreeNode = contents.get(i)) != this; i++) {
            if (!(syntaxTreeNode instanceof XslAttribute) && !(syntaxTreeNode instanceof UseAttributeSets) && !(syntaxTreeNode instanceof LiteralAttribute) && !(syntaxTreeNode instanceof Text) && !(syntaxTreeNode instanceof If) && !(syntaxTreeNode instanceof Choose) && !(syntaxTreeNode instanceof CopyOf) && !(syntaxTreeNode instanceof VariableBase)) {
                reportWarning(this, parser, "STRAY_ATTRIBUTE_ERR", attribute);
            }
        }
        if (attribute2 != null && attribute2 != "") {
            this._prefix = lookupPrefix(attribute2);
            this._namespace = new AttributeValueTemplate(attribute2, parser, this);
        } else if (prefix != null && prefix != "") {
            this._prefix = prefix;
            attribute2 = lookupNamespace(prefix);
            if (attribute2 != null) {
                this._namespace = new AttributeValueTemplate(attribute2, parser, this);
            }
        }
        if (this._namespace != null) {
            String str = this._prefix;
            if (str == null || str == "") {
                if (prefix != null) {
                    this._prefix = prefix;
                } else {
                    this._prefix = symbolTable.generateNamespacePrefix();
                    z = true;
                }
                String str2 = this._prefix + ":" + qName.getLocalPart();
                if ((parent instanceof LiteralElement) && !z) {
                    ((LiteralElement) parent).registerNamespace(this._prefix, attribute2, symbolTable, false);
                }
                attribute = str2;
            } else if (prefix != null && !prefix.equals(str)) {
                this._prefix = prefix;
            }
            z = false;
            String str3 = this._prefix + ":" + qName.getLocalPart();
            if (parent instanceof LiteralElement) {
                ((LiteralElement) parent).registerNamespace(this._prefix, attribute2, symbolTable, false);
            }
            attribute = str3;
        }
        if (parent instanceof LiteralElement) {
            ((LiteralElement) parent).addAttribute(this);
        }
        this._name = AttributeValue.create(this, attribute, parser);
        parseChildren(parser);
    }

    /* JADX WARN: Code duplicated, block: B:33:0x0143  */
    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Instruction, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void translate(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        if (this._ignore) {
            return;
        }
        this._ignore = true;
        if (this._namespace != null) {
            instructionList.append(methodGenerator.loadHandler());
            instructionList.append(new PUSH(constantPool, this._prefix));
            this._namespace.translate(classGenerator, methodGenerator);
            instructionList.append(methodGenerator.namespace());
        }
        if (this._isLiteral) {
            instructionList.append(methodGenerator.loadHandler());
            instructionList.append(Constants.DUP);
            this._name.translate(classGenerator, methodGenerator);
        } else {
            LocalVariableGen localVariableGenAddLocalVariable2 = methodGenerator.addLocalVariable2("nameValue", Util.getJCRefType(Constants.STRING_SIG), null);
            this._name.translate(classGenerator, methodGenerator);
            localVariableGenAddLocalVariable2.setStart(instructionList.append(new ASTORE(localVariableGenAddLocalVariable2.getIndex())));
            instructionList.append(new ALOAD(localVariableGenAddLocalVariable2.getIndex()));
            instructionList.append(new INVOKESTATIC(constantPool.addMethodref(Constants.BASIS_LIBRARY_CLASS, "checkAttribQName", "(Ljava/lang/String;)V")));
            instructionList.append(methodGenerator.loadHandler());
            instructionList.append(Constants.DUP);
            localVariableGenAddLocalVariable2.setEnd(instructionList.append(new ALOAD(localVariableGenAddLocalVariable2.getIndex())));
        }
        int i = 0;
        if (elementCount() == 1 && (elementAt(0) instanceof Text)) {
            instructionList.append(new PUSH(constantPool, ((Text) elementAt(0)).getText()));
        } else {
            instructionList.append(classGenerator.loadTranslet());
            instructionList.append(new GETFIELD(constantPool.addFieldref(Constants.TRANSLET_CLASS, "stringValueHandler", Constants.STRING_VALUE_HANDLER_SIG)));
            instructionList.append(Constants.DUP);
            instructionList.append(methodGenerator.storeHandler());
            translateContents(classGenerator, methodGenerator);
            instructionList.append(new INVOKEVIRTUAL(constantPool.addMethodref(Constants.STRING_VALUE_HANDLER, "getValue", "()Ljava/lang/String;")));
        }
        SyntaxTreeNode parent = getParent();
        if (parent instanceof LiteralElement) {
            LiteralElement literalElement = (LiteralElement) parent;
            if (literalElement.allAttributesUnique()) {
                ElemDesc elemDesc = literalElement.getElemDesc();
                if (elemDesc != null) {
                    AttributeValue attributeValue = this._name;
                    if (attributeValue instanceof SimpleAttributeValue) {
                        String string = ((SimpleAttributeValue) attributeValue).toString();
                        if (elemDesc.isAttrFlagSet(string, 4)) {
                            i = 2;
                        } else if (elemDesc.isAttrFlagSet(string, 2)) {
                            i = 4;
                        }
                    }
                }
                instructionList.append(new PUSH(constantPool, i));
                instructionList.append(methodGenerator.uniqueAttribute());
            } else {
                instructionList.append(methodGenerator.attribute());
            }
        } else {
            instructionList.append(methodGenerator.attribute());
        }
        instructionList.append(methodGenerator.storeHandler());
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Instruction, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public Type typeCheck(SymbolTable symbolTable) throws TypeCheckError {
        if (!this._ignore) {
            this._name.typeCheck(symbolTable);
            AttributeValueTemplate attributeValueTemplate = this._namespace;
            if (attributeValueTemplate != null) {
                attributeValueTemplate.typeCheck(symbolTable);
            }
            typeCheckContents(symbolTable);
        }
        return Type.Void;
    }
}
