package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class SymbolTable {
    private final Map<String, Stylesheet> _stylesheets = new HashMap();
    private final Map<String, List<MethodType>> _primops = new HashMap();
    private Map<String, VariableBase> _variables = null;
    private Map<String, Template> _templates = null;
    private Map<String, AttributeSet> _attributeSets = null;
    private Map<String, String> _aliases = null;
    private Map<String, Integer> _excludedURI = null;
    private Stack<Map<String, Integer>> _excludedURIStack = null;
    private Map<String, DecimalFormatting> _decimalFormats = null;
    private Map<String, Key> _keys = null;
    private int _nsCounter = 0;
    private SyntaxTreeNode _current = null;

    public AttributeSet addAttributeSet(AttributeSet attributeSet) {
        if (this._attributeSets == null) {
            this._attributeSets = new HashMap();
        }
        return this._attributeSets.put(attributeSet.getName().getStringRep(), attributeSet);
    }

    public void addDecimalFormatting(QName qName, DecimalFormatting decimalFormatting) {
        if (this._decimalFormats == null) {
            this._decimalFormats = new HashMap();
        }
        this._decimalFormats.put(qName.getStringRep(), decimalFormatting);
    }

    public void addKey(QName qName, Key key) {
        if (this._keys == null) {
            this._keys = new HashMap();
        }
        this._keys.put(qName.getStringRep(), key);
    }

    public Param addParam(Param param) {
        if (this._variables == null) {
            this._variables = new HashMap();
        }
        VariableBase variableBasePut = this._variables.put(param.getName().getStringRep(), param);
        if (variableBasePut instanceof Param) {
            return (Param) variableBasePut;
        }
        return null;
    }

    public void addPrefixAlias(String str, String str2) {
        if (this._aliases == null) {
            this._aliases = new HashMap();
        }
        this._aliases.put(str, str2);
    }

    public void addPrimop(String str, MethodType methodType) {
        List<MethodType> arrayList = this._primops.get(str);
        if (arrayList == null) {
            Map<String, List<MethodType>> map = this._primops;
            arrayList = new ArrayList<>();
            map.put(str, arrayList);
        }
        arrayList.add(methodType);
    }

    public Stylesheet addStylesheet(QName qName, Stylesheet stylesheet) {
        return this._stylesheets.put(qName.getStringRep(), stylesheet);
    }

    public Template addTemplate(Template template) {
        QName name = template.getName();
        if (this._templates == null) {
            this._templates = new HashMap();
        }
        return this._templates.put(name.getStringRep(), template);
    }

    public Variable addVariable(Variable variable) {
        if (this._variables == null) {
            this._variables = new HashMap();
        }
        VariableBase variableBasePut = this._variables.put(variable.getName().getStringRep(), variable);
        if (variableBasePut instanceof Variable) {
            return (Variable) variableBasePut;
        }
        return null;
    }

    public void excludeNamespaces(String str) {
        if (str != null) {
            StringTokenizer stringTokenizer = new StringTokenizer(str);
            while (stringTokenizer.hasMoreTokens()) {
                String strNextToken = stringTokenizer.nextToken();
                String strLookupNamespace = strNextToken.equals("#default") ? lookupNamespace("") : lookupNamespace(strNextToken);
                if (strLookupNamespace != null) {
                    excludeURI(strLookupNamespace);
                }
            }
        }
    }

    public void excludeURI(String str) {
        if (str == null) {
            return;
        }
        if (this._excludedURI == null) {
            this._excludedURI = new HashMap();
        }
        Integer num = this._excludedURI.get(str);
        this._excludedURI.put(str, num == null ? 1 : Integer.valueOf(num.intValue() + 1));
    }

    public String generateNamespacePrefix() {
        StringBuilder sb = new StringBuilder(com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_NS);
        int i = this._nsCounter;
        this._nsCounter = i + 1;
        sb.append(i);
        return sb.toString();
    }

    public DecimalFormatting getDecimalFormatting(QName qName) {
        Map<String, DecimalFormatting> map = this._decimalFormats;
        if (map == null) {
            return null;
        }
        return map.get(qName.getStringRep());
    }

    public Key getKey(QName qName) {
        Map<String, Key> map = this._keys;
        if (map == null) {
            return null;
        }
        return map.get(qName.getStringRep());
    }

    public boolean isExcludedNamespace(String str) {
        Map<String, Integer> map;
        Integer num;
        return (str == null || (map = this._excludedURI) == null || (num = map.get(str)) == null || num.intValue() <= 0) ? false : true;
    }

    public AttributeSet lookupAttributeSet(QName qName) {
        Map<String, AttributeSet> map = this._attributeSets;
        if (map == null) {
            return null;
        }
        return map.get(qName.getStringRep());
    }

    public SyntaxTreeNode lookupName(QName qName) {
        if (this._variables == null) {
            return null;
        }
        return this._variables.get(qName.getStringRep());
    }

    public String lookupNamespace(String str) {
        SyntaxTreeNode syntaxTreeNode = this._current;
        return syntaxTreeNode == null ? "" : syntaxTreeNode.lookupNamespace(str);
    }

    public Param lookupParam(QName qName) {
        if (this._variables == null) {
            return null;
        }
        VariableBase variableBase = this._variables.get(qName.getStringRep());
        if (variableBase instanceof Param) {
            return (Param) variableBase;
        }
        return null;
    }

    public String lookupPrefixAlias(String str) {
        Map<String, String> map = this._aliases;
        if (map == null) {
            return null;
        }
        return map.get(str);
    }

    public List<MethodType> lookupPrimop(String str) {
        return this._primops.get(str);
    }

    public Stylesheet lookupStylesheet(QName qName) {
        return this._stylesheets.get(qName.getStringRep());
    }

    public Template lookupTemplate(QName qName) {
        Map<String, Template> map = this._templates;
        if (map == null) {
            return null;
        }
        return map.get(qName.getStringRep());
    }

    public Variable lookupVariable(QName qName) {
        if (this._variables == null) {
            return null;
        }
        VariableBase variableBase = this._variables.get(qName.getStringRep());
        if (variableBase instanceof Variable) {
            return (Variable) variableBase;
        }
        return null;
    }

    public void popExcludedNamespacesContext() {
        this._excludedURI = this._excludedURIStack.pop();
        if (this._excludedURIStack.isEmpty()) {
            this._excludedURIStack = null;
        }
    }

    public void pushExcludedNamespacesContext() {
        if (this._excludedURIStack == null) {
            this._excludedURIStack = new Stack<>();
        }
        this._excludedURIStack.push(this._excludedURI);
        this._excludedURI = null;
    }

    public void setCurrentNode(SyntaxTreeNode syntaxTreeNode) {
        this._current = syntaxTreeNode;
    }

    public void unExcludeNamespaces(String str) {
        if (this._excludedURI == null || str == null) {
            return;
        }
        StringTokenizer stringTokenizer = new StringTokenizer(str);
        while (stringTokenizer.hasMoreTokens()) {
            String strNextToken = stringTokenizer.nextToken();
            String strLookupNamespace = strNextToken.equals("#default") ? lookupNamespace("") : lookupNamespace(strNextToken);
            Integer num = this._excludedURI.get(strLookupNamespace);
            if (num != null) {
                this._excludedURI.put(strLookupNamespace, Integer.valueOf(num.intValue() - 1));
            }
        }
    }
}
