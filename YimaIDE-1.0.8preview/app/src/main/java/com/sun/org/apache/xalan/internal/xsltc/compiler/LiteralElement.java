package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.bcel.internal.generic.PUSH;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;
import com.sun.org.apache.xml.internal.serializer.ElemDesc;
import com.sun.org.apache.xml.internal.serializer.ToHTMLStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class LiteralElement extends Instruction {
    private String _name;
    private LiteralElement _literalElemParent = null;
    private List<SyntaxTreeNode> _attributeElements = null;
    private Map<String, String> _accessedPrefixes = null;
    private boolean _allAttributesUnique = false;

    private String accessedNamespace(String str) {
        String strAccessedNamespace;
        LiteralElement literalElement = this._literalElemParent;
        if (literalElement != null && (strAccessedNamespace = literalElement.accessedNamespace(str)) != null) {
            return strAccessedNamespace;
        }
        Map<String, String> map = this._accessedPrefixes;
        if (map != null) {
            return map.get(str);
        }
        return null;
    }

    private boolean canProduceAttributeNodes(SyntaxTreeNode syntaxTreeNode, boolean z) {
        for (SyntaxTreeNode syntaxTreeNode2 : syntaxTreeNode.getContents()) {
            if (syntaxTreeNode2 instanceof Text) {
                if (!((Text) syntaxTreeNode2).isIgnore()) {
                    return false;
                }
            } else {
                if ((syntaxTreeNode2 instanceof LiteralElement) || (syntaxTreeNode2 instanceof ValueOf) || (syntaxTreeNode2 instanceof XslElement) || (syntaxTreeNode2 instanceof Comment) || (syntaxTreeNode2 instanceof Number) || (syntaxTreeNode2 instanceof ProcessingInstruction)) {
                    break;
                }
                if (syntaxTreeNode2 instanceof XslAttribute) {
                    if (!z) {
                        return true;
                    }
                } else {
                    if ((syntaxTreeNode2 instanceof CallTemplate) || (syntaxTreeNode2 instanceof ApplyTemplates) || (syntaxTreeNode2 instanceof Copy) || (syntaxTreeNode2 instanceof CopyOf) || (((syntaxTreeNode2 instanceof If) || (syntaxTreeNode2 instanceof ForEach)) && canProduceAttributeNodes(syntaxTreeNode2, false))) {
                        return true;
                    }
                    if (syntaxTreeNode2 instanceof Choose) {
                        for (SyntaxTreeNode syntaxTreeNode3 : syntaxTreeNode2.getContents()) {
                            if ((syntaxTreeNode3 instanceof When) || (syntaxTreeNode3 instanceof Otherwise)) {
                                if (canProduceAttributeNodes(syntaxTreeNode3, false)) {
                                    return true;
                                }
                            }
                        }
                    } else {
                        continue;
                    }
                }
            }
        }
        return false;
    }

    private boolean checkAttributesUnique() {
        if (canProduceAttributeNodes(this, true)) {
            return false;
        }
        List<SyntaxTreeNode> list = this._attributeElements;
        if (list != null) {
            int size = list.size();
            HashMap map = null;
            for (int i = 0; i < size; i++) {
                SyntaxTreeNode syntaxTreeNode = this._attributeElements.get(i);
                if (syntaxTreeNode instanceof UseAttributeSets) {
                    return false;
                }
                if (syntaxTreeNode instanceof XslAttribute) {
                    if (map == null) {
                        map = new HashMap();
                        for (int i2 = 0; i2 < i; i2++) {
                            SyntaxTreeNode syntaxTreeNode2 = this._attributeElements.get(i2);
                            if (syntaxTreeNode2 instanceof LiteralAttribute) {
                                LiteralAttribute literalAttribute = (LiteralAttribute) syntaxTreeNode2;
                                map.put(literalAttribute.getName(), literalAttribute);
                            }
                        }
                    }
                    XslAttribute xslAttribute = (XslAttribute) syntaxTreeNode;
                    AttributeValue name = xslAttribute.getName();
                    if (name instanceof AttributeValueTemplate) {
                        return false;
                    }
                    if (name instanceof SimpleAttributeValue) {
                        String string = ((SimpleAttributeValue) name).toString();
                        if (string != null && map.get(string) != null) {
                            return false;
                        }
                        if (string != null) {
                            map.put(string, xslAttribute);
                        }
                    } else {
                        continue;
                    }
                }
            }
        }
        return true;
    }

    private boolean isHTMLOutput() {
        return getStylesheet().getOutputMethod() == 2;
    }

    private String translateQName(QName qName, SymbolTable symbolTable) {
        String localPart = qName.getLocalPart();
        String prefix = qName.getPrefix();
        if (prefix == null) {
            prefix = "";
        } else if (prefix.equals("xmlns")) {
            return "xmlns";
        }
        String strLookupPrefixAlias = symbolTable.lookupPrefixAlias(prefix);
        if (strLookupPrefixAlias != null) {
            symbolTable.excludeNamespaces(prefix);
            prefix = strLookupPrefixAlias;
        }
        String strLookupNamespace = lookupNamespace(prefix);
        if (strLookupNamespace != null) {
            registerNamespace(prefix, strLookupNamespace, symbolTable, false);
            if (prefix != "") {
                return prefix + ":" + localPart;
            }
        }
        return localPart;
    }

    public void addAttribute(SyntaxTreeNode syntaxTreeNode) {
        if (this._attributeElements == null) {
            this._attributeElements = new ArrayList(2);
        }
        this._attributeElements.add(syntaxTreeNode);
    }

    public boolean allAttributesUnique() {
        return this._allAttributesUnique;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public boolean contextDependent() {
        return dependentContents();
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void display(int i) {
        indent(i);
        Util.println("LiteralElement name = " + this._name);
        displayContents(i + 4);
    }

    public ElemDesc getElemDesc() {
        if (isHTMLOutput()) {
            return ToHTMLStream.getElemDesc(this._name);
        }
        return null;
    }

    public QName getName() {
        return this._qname;
    }

    public Set<Map.Entry<String, String>> getNamespaceScope(SyntaxTreeNode syntaxTreeNode) {
        final LinkedHashMap linkedHashMap = new LinkedHashMap();
        while (syntaxTreeNode != null) {
            Map<String, String> prefixMapping = syntaxTreeNode.getPrefixMapping();
            if (prefixMapping != null) {
                prefixMapping.entrySet().stream().forEach(new Consumer() { // from class: com.sun.org.apache.xalan.internal.xsltc.compiler.a
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        Map.Entry entry = (Map.Entry) obj;
                        linkedHashMap.putIfAbsent((String) entry.getKey(), (String) entry.getValue());
                    }
                });
            }
            syntaxTreeNode = syntaxTreeNode.getParent();
        }
        return linkedHashMap.entrySet();
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void parseContents(Parser parser) {
        String strLookupNamespace;
        SymbolTable symbolTable = parser.getSymbolTable();
        symbolTable.setCurrentNode(this);
        SyntaxTreeNode parent = getParent();
        if (parent != null && (parent instanceof LiteralElement)) {
            this._literalElemParent = (LiteralElement) parent;
        }
        this._name = translateQName(this._qname, symbolTable);
        int length = this._attributes.getLength();
        for (int i = 0; i < length; i++) {
            QName qName = parser.getQName(this._attributes.getQName(i));
            String namespace = qName.getNamespace();
            String value = this._attributes.getValue(i);
            if (qName.equals(parser.getUseAttributeSets())) {
                if (!Util.isValidQNames(value)) {
                    parser.reportError(3, new ErrorMsg("INVALID_QNAME_ERR", (Object) value, (SyntaxTreeNode) this));
                }
                setFirstAttribute(new UseAttributeSets(value, parser));
            } else if (qName.equals(parser.getExtensionElementPrefixes())) {
                symbolTable.excludeNamespaces(value);
            } else if (qName.equals(parser.getExcludeResultPrefixes())) {
                symbolTable.excludeNamespaces(value);
            } else {
                String prefix = qName.getPrefix();
                if ((prefix == null || !prefix.equals("xmlns")) && ((prefix != null || !qName.getLocalPart().equals("xmlns")) && (namespace == null || !namespace.equals("http://www.w3.org/1999/XSL/Transform")))) {
                    LiteralAttribute literalAttribute = new LiteralAttribute(translateQName(qName, symbolTable), value, parser, this);
                    addAttribute(literalAttribute);
                    literalAttribute.setParent(this);
                    literalAttribute.parseContents(parser);
                }
            }
        }
        Iterator<Map.Entry<String, String>> it = getNamespaceScope(this).iterator();
        while (it.hasNext()) {
            String key = it.next().getKey();
            if (!key.equals("xml") && (strLookupNamespace = lookupNamespace(key)) != null && !symbolTable.isExcludedNamespace(strLookupNamespace)) {
                registerNamespace(key, strLookupNamespace, symbolTable, true);
            }
        }
        parseChildren(parser);
        for (int i2 = 0; i2 < length; i2++) {
            QName qName2 = parser.getQName(this._attributes.getQName(i2));
            String value2 = this._attributes.getValue(i2);
            if (qName2.equals(parser.getExtensionElementPrefixes())) {
                symbolTable.unExcludeNamespaces(value2);
            } else if (qName2.equals(parser.getExcludeResultPrefixes())) {
                symbolTable.unExcludeNamespaces(value2);
            }
        }
    }

    public void registerNamespace(String str, String str2, SymbolTable symbolTable, boolean z) {
        String str3;
        String strAccessedNamespace;
        LiteralElement literalElement = this._literalElemParent;
        if (literalElement == null || (strAccessedNamespace = literalElement.accessedNamespace(str)) == null || !strAccessedNamespace.equals(str2)) {
            Map<String, String> map = this._accessedPrefixes;
            if (map == null) {
                this._accessedPrefixes = new Hashtable();
            } else if (!z && (str3 = map.get(str)) != null) {
                if (str3.equals(str2)) {
                    return;
                } else {
                    str = symbolTable.generateNamespacePrefix();
                }
            }
            if (str.equals("xml")) {
                return;
            }
            this._accessedPrefixes.put(str, str2);
        }
    }

    public void setFirstAttribute(SyntaxTreeNode syntaxTreeNode) {
        if (this._attributeElements == null) {
            this._attributeElements = new ArrayList(2);
        }
        this._attributeElements.add(0, syntaxTreeNode);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Instruction, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void translate(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        this._allAttributesUnique = checkAttributesUnique();
        instructionList.append(methodGenerator.loadHandler());
        instructionList.append(new PUSH(constantPool, this._name));
        instructionList.append(Constants.DUP2);
        instructionList.append(methodGenerator.startElement());
        for (int i = 0; i < elementCount(); i++) {
            SyntaxTreeNode syntaxTreeNodeElementAt = elementAt(i);
            if (syntaxTreeNodeElementAt instanceof Variable) {
                syntaxTreeNodeElementAt.translate(classGenerator, methodGenerator);
            }
        }
        Map<String, String> map = this._accessedPrefixes;
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                instructionList.append(methodGenerator.loadHandler());
                instructionList.append(new PUSH(constantPool, key));
                instructionList.append(new PUSH(constantPool, value));
                instructionList.append(methodGenerator.namespace());
            }
        }
        List<SyntaxTreeNode> list = this._attributeElements;
        if (list != null) {
            for (SyntaxTreeNode syntaxTreeNode : list) {
                if (!(syntaxTreeNode instanceof XslAttribute)) {
                    syntaxTreeNode.translate(classGenerator, methodGenerator);
                }
            }
        }
        translateContents(classGenerator, methodGenerator);
        instructionList.append(methodGenerator.endElement());
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Instruction, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public Type typeCheck(SymbolTable symbolTable) throws TypeCheckError {
        List<SyntaxTreeNode> list = this._attributeElements;
        if (list != null) {
            Iterator<SyntaxTreeNode> it = list.iterator();
            while (it.hasNext()) {
                it.next().typeCheck(symbolTable);
            }
        }
        typeCheckContents(symbolTable);
        return Type.Void;
    }
}
