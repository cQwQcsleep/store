package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.bcel.internal.generic.ANEWARRAY;
import com.sun.org.apache.bcel.internal.generic.CHECKCAST;
import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.DUP_X1;
import com.sun.org.apache.bcel.internal.generic.GETFIELD;
import com.sun.org.apache.bcel.internal.generic.ICONST;
import com.sun.org.apache.bcel.internal.generic.INVOKEINTERFACE;
import com.sun.org.apache.bcel.internal.generic.INVOKESPECIAL;
import com.sun.org.apache.bcel.internal.generic.INVOKEVIRTUAL;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.bcel.internal.generic.LocalVariableInstruction;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.bcel.internal.generic.NEWARRAY;
import com.sun.org.apache.bcel.internal.generic.PUSH;
import com.sun.org.apache.bcel.internal.generic.StackInstruction;
import com.sun.org.apache.bcel.internal.generic.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.AttributesImpl;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class SyntaxTreeNode implements Constants {
    protected static final int IndentIncrement = 4;
    protected AttributesImpl _attributes;
    private final List<SyntaxTreeNode> _contents;
    private int _line;
    protected SyntaxTreeNode _parent;
    private Parser _parser;
    private Map<String, String> _prefixMapping;
    protected QName _qname;
    private Stylesheet _stylesheet;
    private Template _template;
    protected static final SyntaxTreeNode Dummy = new AbsolutePathPattern(null);
    private static final char[] _spaces = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};

    public SyntaxTreeNode(String str, String str2, String str3) {
        this._contents = new ArrayList(2);
        this._attributes = null;
        this._prefixMapping = null;
        this._line = 0;
        setQName(str, str2, str3);
    }

    public static /* synthetic */ boolean a(SyntaxTreeNode syntaxTreeNode, SyntaxTreeNode syntaxTreeNode2) {
        return !syntaxTreeNode.isTextElement(syntaxTreeNode2, false);
    }

    private boolean isAdaptiveRTF(SyntaxTreeNode syntaxTreeNode) {
        Iterator<SyntaxTreeNode> it = syntaxTreeNode.getContents().iterator();
        while (it.hasNext()) {
            if (!isTextElement(it.next(), true)) {
                return false;
            }
        }
        return true;
    }

    private boolean isSimpleRTF(SyntaxTreeNode syntaxTreeNode) {
        return syntaxTreeNode.getContents().stream().noneMatch(new java.util.function.Predicate() { // from class: ezd
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return SyntaxTreeNode.a(this.b, (SyntaxTreeNode) obj);
            }
        });
    }

    private boolean isTextElement(SyntaxTreeNode syntaxTreeNode, boolean z) {
        if ((syntaxTreeNode instanceof ValueOf) || (syntaxTreeNode instanceof Number) || (syntaxTreeNode instanceof Text)) {
            return true;
        }
        if (syntaxTreeNode instanceof If) {
            return z ? isAdaptiveRTF(syntaxTreeNode) : isSimpleRTF(syntaxTreeNode);
        }
        if (!(syntaxTreeNode instanceof Choose)) {
            return z && ((syntaxTreeNode instanceof CallTemplate) || (syntaxTreeNode instanceof ApplyTemplates));
        }
        for (SyntaxTreeNode syntaxTreeNode2 : syntaxTreeNode.getContents()) {
            if (!(syntaxTreeNode2 instanceof Text)) {
                if ((syntaxTreeNode2 instanceof When) || (syntaxTreeNode2 instanceof Otherwise)) {
                    if (!z || !isAdaptiveRTF(syntaxTreeNode2)) {
                        if (z || !isSimpleRTF(syntaxTreeNode2)) {
                        }
                    }
                }
                return false;
            }
        }
        return true;
    }

    public void addAttribute(String str, String str2) {
        int index = this._attributes.getIndex(str);
        AttributesImpl attributesImpl = this._attributes;
        if (index != -1) {
            attributesImpl.setAttribute(index, "", Util.getLocalName(str), str, "CDATA", str2);
        } else {
            attributesImpl.addAttribute("", Util.getLocalName(str), str, "CDATA", str2);
        }
    }

    public final void addElement(SyntaxTreeNode syntaxTreeNode) {
        this._contents.add(syntaxTreeNode);
        syntaxTreeNode.setParent(this);
    }

    public void addPrefixMapping(String str, String str2) {
        if (this._prefixMapping == null) {
            this._prefixMapping = new HashMap();
        }
        this._prefixMapping.put(str, str2);
    }

    public void compileResultTree(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        int i;
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        Stylesheet stylesheet = classGenerator.getStylesheet();
        boolean zIsSimpleRTF = isSimpleRTF(this);
        boolean zIsAdaptiveRTF = !zIsSimpleRTF ? isAdaptiveRTF(this) : false;
        if (zIsSimpleRTF) {
            i = 0;
        } else {
            i = zIsAdaptiveRTF ? 1 : 2;
        }
        instructionList.append(methodGenerator.loadHandler());
        String dOMClass = classGenerator.getDOMClass();
        instructionList.append(methodGenerator.loadDOM());
        int iAddInterfaceMethodref = constantPool.addInterfaceMethodref(Constants.DOM_INTF, "getResultTreeFrag", "(IIZ)Lcom/sun/org/apache/xalan/internal/xsltc/DOM;");
        instructionList.append(new PUSH(constantPool, 32));
        instructionList.append(new PUSH(constantPool, i));
        instructionList.append(new PUSH(constantPool, stylesheet.callsNodeset()));
        instructionList.append(new INVOKEINTERFACE(iAddInterfaceMethodref, 4));
        StackInstruction stackInstruction = Constants.DUP;
        instructionList.append(stackInstruction);
        instructionList.append(new INVOKEINTERFACE(constantPool.addInterfaceMethodref(Constants.DOM_INTF, "getOutputDomBuilder", "()Lcom/sun/org/apache/xml/internal/serializer/SerializationHandler;"), 1));
        instructionList.append(stackInstruction);
        instructionList.append(methodGenerator.storeHandler());
        instructionList.append(methodGenerator.startDocument());
        translateContents(classGenerator, methodGenerator);
        instructionList.append(methodGenerator.loadHandler());
        instructionList.append(methodGenerator.endDocument());
        if (stylesheet.callsNodeset() && !dOMClass.equals(Constants.DOM_IMPL_CLASS)) {
            int iAddMethodref = constantPool.addMethodref(Constants.DOM_ADAPTER_CLASS, Const.CONSTRUCTOR_NAME, "(Lcom/sun/org/apache/xalan/internal/xsltc/DOM;[Ljava/lang/String;[Ljava/lang/String;[I[Ljava/lang/String;)V");
            instructionList.append(new NEW(constantPool.addClass(Constants.DOM_ADAPTER_CLASS)));
            instructionList.append(new DUP_X1());
            StackInstruction stackInstruction2 = Constants.SWAP;
            instructionList.append(stackInstruction2);
            if (stylesheet.callsNodeset()) {
                LocalVariableInstruction localVariableInstruction = Constants.ALOAD_0;
                instructionList.append(localVariableInstruction);
                instructionList.append(new GETFIELD(constantPool.addFieldref(Constants.TRANSLET_CLASS, Constants.NAMES_INDEX, "[Ljava/lang/String;")));
                instructionList.append(localVariableInstruction);
                instructionList.append(new GETFIELD(constantPool.addFieldref(Constants.TRANSLET_CLASS, Constants.URIS_INDEX, "[Ljava/lang/String;")));
                instructionList.append(localVariableInstruction);
                instructionList.append(new GETFIELD(constantPool.addFieldref(Constants.TRANSLET_CLASS, Constants.TYPES_INDEX, Constants.TYPES_INDEX_SIG)));
                instructionList.append(localVariableInstruction);
                instructionList.append(new GETFIELD(constantPool.addFieldref(Constants.TRANSLET_CLASS, Constants.NAMESPACE_INDEX, "[Ljava/lang/String;")));
                instructionList.append(new INVOKESPECIAL(iAddMethodref));
                instructionList.append(stackInstruction);
                instructionList.append(methodGenerator.loadDOM());
                instructionList.append(new CHECKCAST(constantPool.addClass(classGenerator.getDOMClass())));
                instructionList.append(stackInstruction2);
                instructionList.append(new INVOKEVIRTUAL(constantPool.addMethodref(Constants.MULTI_DOM_CLASS, "addDOMAdapter", "(Lcom/sun/org/apache/xalan/internal/xsltc/dom/DOMAdapter;)I")));
                instructionList.append(Constants.POP);
            } else {
                instructionList.append(new ICONST(0));
                instructionList.append(new ANEWARRAY(constantPool.addClass("java.lang.String")));
                instructionList.append(stackInstruction);
                instructionList.append(stackInstruction);
                instructionList.append(new ICONST(0));
                instructionList.append(new NEWARRAY(Type.INT));
                instructionList.append(stackInstruction2);
                instructionList.append(new INVOKESPECIAL(iAddMethodref));
            }
        }
        instructionList.append(Constants.SWAP);
        instructionList.append(methodGenerator.storeHandler());
    }

    public boolean contextDependent() {
        return true;
    }

    public boolean dependentContents() {
        Iterator<SyntaxTreeNode> it = this._contents.iterator();
        while (it.hasNext()) {
            if (it.next().contextDependent()) {
                return true;
            }
        }
        return false;
    }

    public void display(int i) {
        displayContents(i);
    }

    public void displayContents(int i) {
        Iterator<SyntaxTreeNode> it = this._contents.iterator();
        while (it.hasNext()) {
            it.next().display(i);
        }
    }

    public final SyntaxTreeNode elementAt(int i) {
        return this._contents.get(i);
    }

    public final int elementCount() {
        return this._contents.size();
    }

    public final Iterator<SyntaxTreeNode> elements() {
        return this._contents.iterator();
    }

    public String getAttribute(String str, String str2) {
        return getAttribute(str + ':' + str2);
    }

    public Attributes getAttributes() {
        return this._attributes;
    }

    public final List<SyntaxTreeNode> getContents() {
        return this._contents;
    }

    public int getImportPrecedence() {
        Stylesheet stylesheet = getStylesheet();
        if (stylesheet == null) {
            return Integer.MIN_VALUE;
        }
        return stylesheet.getImportPrecedence();
    }

    public final int getLineNumber() {
        int i = this._line;
        if (i > 0) {
            return i;
        }
        SyntaxTreeNode parent = getParent();
        if (parent != null) {
            return parent.getLineNumber();
        }
        return 0;
    }

    public final SyntaxTreeNode getParent() {
        return this._parent;
    }

    public final Parser getParser() {
        return this._parser;
    }

    public Map<String, String> getPrefixMapping() {
        return this._prefixMapping;
    }

    public QName getQName() {
        return this._qname;
    }

    public Stylesheet getStylesheet() {
        if (this._stylesheet == null) {
            SyntaxTreeNode parent = this;
            while (parent != null) {
                if (parent instanceof Stylesheet) {
                    return (Stylesheet) parent;
                }
                parent = parent.getParent();
            }
            this._stylesheet = (Stylesheet) parent;
        }
        return this._stylesheet;
    }

    public final SymbolTable getSymbolTable() {
        Parser parser = this._parser;
        if (parser == null) {
            return null;
        }
        return parser.getSymbolTable();
    }

    public Template getTemplate() {
        if (this._template == null) {
            SyntaxTreeNode parent = this;
            while (parent != null && !(parent instanceof Template)) {
                parent = parent.getParent();
            }
            this._template = (Template) parent;
        }
        return this._template;
    }

    public final XSLTC getXSLTC() {
        return this._parser.getXSLTC();
    }

    public boolean hasAttribute(String str) {
        AttributesImpl attributesImpl = this._attributes;
        return (attributesImpl == null || attributesImpl.getValue(str) == null) ? false : true;
    }

    public final boolean hasContents() {
        return elementCount() > 0;
    }

    public final void indent(int i) {
        System.out.print(new String(_spaces, 0, i));
    }

    public final boolean isDummy() {
        return this == Dummy;
    }

    public final SyntaxTreeNode lastChild() {
        if (this._contents.isEmpty()) {
            return null;
        }
        List<SyntaxTreeNode> list = this._contents;
        return list.get(list.size() - 1);
    }

    public String lookupNamespace(String str) {
        SyntaxTreeNode syntaxTreeNode;
        Map<String, String> map = this._prefixMapping;
        String str2 = map != null ? map.get(str) : null;
        if (str2 != null || (syntaxTreeNode = this._parent) == null) {
            return str2;
        }
        String strLookupNamespace = syntaxTreeNode.lookupNamespace(str);
        return (str == "" && strLookupNamespace == null) ? "" : strLookupNamespace;
    }

    public String lookupPrefix(String str) {
        Map<String, String> map = this._prefixMapping;
        String key = null;
        if (map == null || !map.containsValue(str)) {
            SyntaxTreeNode syntaxTreeNode = this._parent;
            if (syntaxTreeNode == null) {
                return null;
            }
            String strLookupPrefix = syntaxTreeNode.lookupPrefix(str);
            return (str == "" && strLookupPrefix == null) ? "" : strLookupPrefix;
        }
        for (Map.Entry<String, String> entry : this._prefixMapping.entrySet()) {
            key = entry.getKey();
            if (entry.getValue().equals(str)) {
                break;
            }
        }
        return key;
    }

    public boolean notTypeOf(Class<?> cls) {
        if (this._contents.size() <= 0) {
            return false;
        }
        Iterator<SyntaxTreeNode> it = this._contents.iterator();
        while (it.hasNext()) {
            if (!it.next().getClass().isAssignableFrom(cls)) {
                return true;
            }
        }
        return false;
    }

    public final void parseChildren(Parser parser) {
        ArrayList arrayList = null;
        for (SyntaxTreeNode syntaxTreeNode : this._contents) {
            parser.getSymbolTable().setCurrentNode(syntaxTreeNode);
            syntaxTreeNode.parseContents(parser);
            QName qNameUpdateScope = updateScope(parser, syntaxTreeNode);
            if (qNameUpdateScope != null) {
                if (arrayList == null) {
                    arrayList = new ArrayList(2);
                }
                arrayList.add(qNameUpdateScope);
            }
        }
        parser.getSymbolTable().setCurrentNode(this);
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                parser.removeVariable((QName) it.next());
            }
        }
    }

    public void parseContents(Parser parser) {
        parseChildren(parser);
    }

    public final void removeElement(SyntaxTreeNode syntaxTreeNode) {
        this._contents.remove(syntaxTreeNode);
        syntaxTreeNode.setParent(null);
    }

    public void reportError(SyntaxTreeNode syntaxTreeNode, Parser parser, String str, String str2) {
        parser.reportError(3, new ErrorMsg(str, (Object) str2, syntaxTreeNode));
    }

    public void reportWarning(SyntaxTreeNode syntaxTreeNode, Parser parser, String str, String str2) {
        parser.reportError(4, new ErrorMsg(str, (Object) str2, syntaxTreeNode));
    }

    public void setAttributes(AttributesImpl attributesImpl) {
        this._attributes = attributesImpl;
    }

    public final void setFirstElement(SyntaxTreeNode syntaxTreeNode) {
        this._contents.add(0, syntaxTreeNode);
        syntaxTreeNode.setParent(this);
    }

    public final void setLineNumber(int i) {
        this._line = i;
    }

    public void setParent(SyntaxTreeNode syntaxTreeNode) {
        if (this._parent == null) {
            this._parent = syntaxTreeNode;
        }
    }

    public void setParser(Parser parser) {
        this._parser = parser;
    }

    public void setPrefixMapping(Map<String, String> map) {
        this._prefixMapping = map;
    }

    public void setQName(String str, String str2, String str3) {
        this._qname = new QName(str, str2, str3);
    }

    public abstract void translate(ClassGenerator classGenerator, MethodGenerator methodGenerator);

    public void translateContents(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        int iElementCount = elementCount();
        for (SyntaxTreeNode syntaxTreeNode : this._contents) {
            methodGenerator.markChunkStart();
            syntaxTreeNode.translate(classGenerator, methodGenerator);
            methodGenerator.markChunkEnd();
        }
        for (int i = 0; i < iElementCount; i++) {
            if (this._contents.get(i) instanceof VariableBase) {
                ((VariableBase) this._contents.get(i)).unmapRegister(classGenerator, methodGenerator);
            }
        }
    }

    public abstract com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type typeCheck(SymbolTable symbolTable) throws TypeCheckError;

    public com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type typeCheckContents(SymbolTable symbolTable) throws TypeCheckError {
        Iterator<SyntaxTreeNode> it = this._contents.iterator();
        while (it.hasNext()) {
            it.next().typeCheck(symbolTable);
        }
        return com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Void;
    }

    public QName updateScope(Parser parser, SyntaxTreeNode syntaxTreeNode) {
        if (syntaxTreeNode instanceof Variable) {
            Variable variable = (Variable) syntaxTreeNode;
            parser.addVariable(variable);
            return variable.getName();
        }
        if (!(syntaxTreeNode instanceof Param)) {
            return null;
        }
        Param param = (Param) syntaxTreeNode;
        parser.addParameter(param);
        return param.getName();
    }

    public void setQName(QName qName) {
        this._qname = qName;
    }

    public SyntaxTreeNode(int i) {
        this._contents = new ArrayList(2);
        this._attributes = null;
        this._prefixMapping = null;
        this._line = i;
        this._qname = null;
    }

    public String getAttribute(String str) {
        String value;
        AttributesImpl attributesImpl = this._attributes;
        return (attributesImpl == null || (value = attributesImpl.getValue(str)) == null || value.equals("")) ? "" : value;
    }

    public SyntaxTreeNode() {
        this._contents = new ArrayList(2);
        this._attributes = null;
        this._prefixMapping = null;
        this._line = 0;
        this._qname = null;
    }
}
