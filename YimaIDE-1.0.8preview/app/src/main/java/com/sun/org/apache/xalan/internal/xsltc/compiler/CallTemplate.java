package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.INVOKEVIRTUAL;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;
import com.sun.org.apache.xml.internal.utils.XML11Char;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class CallTemplate extends Instruction {
    private QName _name;
    private SyntaxTreeNode[] _parameters = null;
    private Template _calleeTemplate = null;

    private void buildParameterList() {
        List<Param> parameters = this._calleeTemplate.getParameters();
        int size = parameters.size();
        this._parameters = new SyntaxTreeNode[size];
        for (int i = 0; i < size; i++) {
            this._parameters[i] = parameters.get(i);
        }
        int iElementCount = elementCount();
        for (int i2 = 0; i2 < iElementCount; i2++) {
            SyntaxTreeNode syntaxTreeNodeElementAt = elementAt(i2);
            if (syntaxTreeNodeElementAt instanceof WithParam) {
                WithParam withParam = (WithParam) syntaxTreeNodeElementAt;
                QName name = withParam.getName();
                for (int i3 = 0; i3 < size; i3++) {
                    SyntaxTreeNode syntaxTreeNode = this._parameters[i3];
                    if ((syntaxTreeNode instanceof Param) && ((Param) syntaxTreeNode).getName().equals(name)) {
                        withParam.setDoParameterOptimization(true);
                        this._parameters[i3] = withParam;
                        break;
                    } else {
                        if ((syntaxTreeNode instanceof WithParam) && ((WithParam) syntaxTreeNode).getName().equals(name)) {
                            withParam.setDoParameterOptimization(true);
                            this._parameters[i3] = withParam;
                            break;
                        }
                    }
                }
            }
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void display(int i) {
        indent(i);
        System.out.print("CallTemplate");
        Util.println(" name " + this._name);
        displayContents(i + 4);
    }

    public Template getCalleeTemplate() {
        Template templateLookupTemplate = getXSLTC().getParser().getSymbolTable().lookupTemplate(this._name);
        if (templateLookupTemplate.isSimpleNamedTemplate()) {
            return templateLookupTemplate;
        }
        return null;
    }

    public boolean hasWithParams() {
        return elementCount() > 0;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void parseContents(Parser parser) {
        String attribute = getAttribute("name");
        if (attribute.length() > 0) {
            if (!XML11Char.isXML11ValidQName(attribute)) {
                parser.reportError(3, new ErrorMsg("INVALID_QNAME_ERR", (Object) attribute, (SyntaxTreeNode) this));
            }
            this._name = parser.getQNameIgnoreDefaultNs(attribute);
        } else {
            reportError(this, parser, ErrorMsg.REQUIRED_ATTR_ERR, "name");
        }
        parseChildren(parser);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Instruction, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void translate(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        Stylesheet stylesheet = classGenerator.getStylesheet();
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        if (stylesheet.hasLocalParams() || hasContents()) {
            Template calleeTemplate = getCalleeTemplate();
            this._calleeTemplate = calleeTemplate;
            if (calleeTemplate != null) {
                buildParameterList();
            } else {
                int iAddMethodref = constantPool.addMethodref(Constants.TRANSLET_CLASS, Constants.PUSH_PARAM_FRAME, "()V");
                instructionList.append(classGenerator.loadTranslet());
                instructionList.append(new INVOKEVIRTUAL(iAddMethodref));
                translateContents(classGenerator, methodGenerator);
            }
        }
        String className = stylesheet.getClassName();
        String strEscape = Util.escape(this._name.toString());
        instructionList.append(classGenerator.loadTranslet());
        instructionList.append(methodGenerator.loadDOM());
        instructionList.append(methodGenerator.loadIterator());
        instructionList.append(methodGenerator.loadHandler());
        instructionList.append(methodGenerator.loadCurrentNode());
        StringBuffer stringBuffer = new StringBuffer("(Lcom/sun/org/apache/xalan/internal/xsltc/DOM;Lcom/sun/org/apache/xml/internal/dtm/DTMAxisIterator;Lcom/sun/org/apache/xml/internal/serializer/SerializationHandler;I");
        int i = 0;
        if (this._calleeTemplate != null) {
            int length = this._parameters.length;
            for (int i2 = 0; i2 < length; i2++) {
                SyntaxTreeNode syntaxTreeNode = this._parameters[i2];
                stringBuffer.append(Constants.OBJECT_SIG);
                if (syntaxTreeNode instanceof Param) {
                    instructionList.append(Constants.ACONST_NULL);
                } else {
                    syntaxTreeNode.translate(classGenerator, methodGenerator);
                }
            }
        }
        stringBuffer.append(")V");
        instructionList.append(new INVOKEVIRTUAL(constantPool.addMethodref(className, strEscape, stringBuffer.toString())));
        if (this._parameters != null) {
            while (true) {
                SyntaxTreeNode[] syntaxTreeNodeArr = this._parameters;
                if (i >= syntaxTreeNodeArr.length) {
                    break;
                }
                SyntaxTreeNode syntaxTreeNode2 = syntaxTreeNodeArr[i];
                if (syntaxTreeNode2 instanceof WithParam) {
                    ((WithParam) syntaxTreeNode2).releaseResultTree(classGenerator, methodGenerator);
                }
                i++;
            }
        }
        if (this._calleeTemplate == null) {
            if (stylesheet.hasLocalParams() || hasContents()) {
                int iAddMethodref2 = constantPool.addMethodref(Constants.TRANSLET_CLASS, Constants.POP_PARAM_FRAME, "()V");
                instructionList.append(classGenerator.loadTranslet());
                instructionList.append(new INVOKEVIRTUAL(iAddMethodref2));
            }
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Instruction, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public Type typeCheck(SymbolTable symbolTable) throws TypeCheckError {
        if (symbolTable.lookupTemplate(this._name) == null) {
            throw new TypeCheckError(new ErrorMsg(ErrorMsg.TEMPLATE_UNDEF_ERR, (Object) this._name, (SyntaxTreeNode) this));
        }
        typeCheckContents(symbolTable);
        return Type.Void;
    }
}
