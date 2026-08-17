package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.java_cup.internal.runtime.Symbol;
import com.sun.org.apache.xalan.internal.utils.ObjectFactory;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import com.sun.org.apache.xml.internal.serializer.utils.SystemIDResolver;
import com.sun.org.apache.xpath.internal.compiler.Keywords;
import com.sun.org.apache.xpath.internal.compiler.PsuedoNames;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Stack;
import java.util.StringTokenizer;
import javax.xml.XMLConstants;
import javax.xml.catalog.CatalogFeatures;
import jdk.xml.internal.ErrorHandlerProxy;
import jdk.xml.internal.JdkConstants;
import jdk.xml.internal.JdkXmlFeatures;
import jdk.xml.internal.JdkXmlUtils;
import jdk.xml.internal.SecuritySupport;
import jdk.xml.internal.XMLSecurityManager;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.AttributesImpl;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Parser implements Constants, ContentHandler {
    private static final String TRANSLET = "translet";
    private static final String XSL = "xsl";
    private int _currentImportPrecedence;
    private Stylesheet _currentStylesheet;
    private ArrayList<ErrorMsg> _errors;
    private QName _excludeResultPrefixes;
    private QName _extensionElementPrefixes;
    private boolean _hasUserErrListener;
    private Map<String, String[]> _instructionAttrs;
    private Map<String, String> _instructionClasses;
    private Map<String, Map<String, QName>> _namespaces;
    private Output _output;
    private boolean _overrideDefaultParser;
    private Map<String, QName> _qNames;
    private SyntaxTreeNode _root;
    private boolean _rootNamespaceDef;
    private SymbolTable _symbolTable;
    private String _target;
    private Template _template;
    private QName _useAttributeSets;
    private Map<String, Object> _variableScope;
    private ArrayList<ErrorMsg> _warnings;
    private XPathParser _xpathParser;
    private XSLTC _xsltc;
    private Locator _locator = null;
    private String _PImedia = null;
    private String _PItitle = null;
    private String _PIcharset = null;
    private int _templateIndex = 0;
    private boolean versionIsOne = true;
    private Stack<SyntaxTreeNode> _parentStack = null;
    private Map<String, String> _prefixMapping = null;

    public Parser(XSLTC xsltc, boolean z, boolean z2) {
        this._xsltc = xsltc;
        this._overrideDefaultParser = z;
        this._hasUserErrListener = z2;
    }

    private void addVariableOrParam(VariableBase variableBase) {
        Object obj = this._variableScope.get(variableBase.getName().getStringRep());
        if (obj == null) {
            this._variableScope.put(variableBase.getName().getStringRep(), variableBase);
            return;
        }
        if (obj instanceof Stack) {
            ((Stack) obj).push(variableBase);
        } else if (obj instanceof VariableBase) {
            Stack stack = new Stack();
            stack.push((VariableBase) obj);
            stack.push(variableBase);
            this._variableScope.put(variableBase.getName().getStringRep(), stack);
        }
    }

    private void checkForSuperfluousAttributes(SyntaxTreeNode syntaxTreeNode, Attributes attributes) {
        boolean z = syntaxTreeNode instanceof Stylesheet;
        String[] strArr = this._instructionAttrs.get(syntaxTreeNode.getQName().getStringRep());
        if (!this.versionIsOne || strArr == null) {
            return;
        }
        int length = attributes.getLength();
        for (int i = 0; i < length; i++) {
            String qName = attributes.getQName(i);
            if (z && qName.equals("version")) {
                this.versionIsOne = attributes.getValue(i).equals("1.0");
            }
            if (!qName.startsWith("xml") && qName.indexOf(58) <= 0) {
                int i2 = 0;
                while (i2 < strArr.length && !qName.equalsIgnoreCase(strArr[i2])) {
                    i2++;
                }
                if (i2 == strArr.length) {
                    ErrorMsg errorMsg = new ErrorMsg(ErrorMsg.ILLEGAL_ATTRIBUTE_ERR, (Object) qName, syntaxTreeNode);
                    errorMsg.setWarningError(true);
                    reportError(4, errorMsg);
                }
            }
        }
    }

    private SyntaxTreeNode findStylesheet(SyntaxTreeNode syntaxTreeNode, String str) {
        if (syntaxTreeNode == null) {
            return null;
        }
        if ((syntaxTreeNode instanceof Stylesheet) && syntaxTreeNode.getAttribute("id").equals(str)) {
            return syntaxTreeNode;
        }
        List<SyntaxTreeNode> contents = syntaxTreeNode.getContents();
        if (contents != null) {
            int size = contents.size();
            for (int i = 0; i < size; i++) {
                SyntaxTreeNode syntaxTreeNodeFindStylesheet = findStylesheet(contents.get(i), str);
                if (syntaxTreeNodeFindStylesheet != null) {
                    return syntaxTreeNodeFindStylesheet;
                }
            }
        }
        return null;
    }

    private int getLineNumber() {
        Locator locator = this._locator;
        if (locator != null) {
            return locator.getLineNumber();
        }
        return 0;
    }

    private SyntaxTreeNode getStylesheet(SyntaxTreeNode syntaxTreeNode) throws CompilerException {
        String str = this._target;
        if (str == null) {
            if (this._rootNamespaceDef) {
                return syntaxTreeNode;
            }
            throw new CompilerException(new ErrorMsg(ErrorMsg.MISSING_XSLT_URI_ERR).toString());
        }
        char cCharAt = str.charAt(0);
        String strConcat = this._target;
        if (cCharAt == '#') {
            SyntaxTreeNode syntaxTreeNodeFindStylesheet = findStylesheet(syntaxTreeNode, strConcat.substring(1));
            if (syntaxTreeNodeFindStylesheet != null) {
                return syntaxTreeNodeFindStylesheet;
            }
            throw new CompilerException(new ErrorMsg(ErrorMsg.MISSING_XSLT_TARGET_ERR, (Object) this._target, syntaxTreeNode).toString());
        }
        try {
            if (strConcat.indexOf(":") == -1) {
                strConcat = "file:".concat(strConcat);
            }
            String strCheckAccess = SecuritySupport.checkAccess(SystemIDResolver.getAbsoluteURI(strConcat), (String) this._xsltc.getProperty(XMLConstants.ACCESS_EXTERNAL_STYLESHEET), "all");
            if (strCheckAccess == null) {
                return loadExternalStylesheet(this._target);
            }
            throw new CompilerException(new ErrorMsg(ErrorMsg.ACCESSING_XSLT_TARGET_ERR, SecuritySupport.sanitizePath(this._target), strCheckAccess, syntaxTreeNode).toString());
        } catch (IOException e) {
            throw new CompilerException(e);
        }
    }

    private String getTokenValue(String str) {
        return str.substring(str.indexOf(34) + 1, str.lastIndexOf(34));
    }

    private void initAttrTable(String str, String[] strArr) {
        this._instructionAttrs.put(getQName("http://www.w3.org/1999/XSL/Transform", XSL, str).getStringRep(), strArr);
    }

    private void initExtClass(String str, String str2) {
        this._instructionClasses.put(getQName(Constants.TRANSLET_URI, "translet", str).getStringRep(), "com.sun.org.apache.xalan.internal.xsltc.compiler." + str2);
    }

    private void initExtClasses() {
        initExtClass(com.sun.org.apache.xalan.internal.templates.Constants.ELEMNAME_OUTPUT_STRING, "TransletOutput");
        initExtClass("http://xml.apache.org/xalan/redirect", "write", "TransletOutput");
    }

    private void initInstructionAttrs() {
        initAttrTable(com.sun.org.apache.xalan.internal.templates.Constants.ELEMNAME_TEMPLATE_STRING, new String[]{com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_MATCH, "name", com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_PRIORITY, com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_MODE});
        initAttrTable(com.sun.org.apache.xalan.internal.templates.Constants.ELEMNAME_STYLESHEET_STRING, new String[]{"id", "version", com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_EXTENSIONELEMENTPREFIXES, com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_EXCLUDE_RESULT_PREFIXES});
        initAttrTable(com.sun.org.apache.xalan.internal.templates.Constants.ELEMNAME_TRANSFORM_STRING, new String[]{"id", "version", com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_EXTENSIONELEMENTPREFIXES, com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_EXCLUDE_RESULT_PREFIXES});
        initAttrTable("text", new String[]{com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_DISABLE_OUTPUT_ESCAPING});
        initAttrTable("if", new String[]{com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_TEST});
        initAttrTable(com.sun.org.apache.xalan.internal.templates.Constants.ELEMNAME_CHOOSE_STRING, new String[0]);
        initAttrTable("when", new String[]{com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_TEST});
        initAttrTable(com.sun.org.apache.xalan.internal.templates.Constants.ELEMNAME_OTHERWISE_STRING, new String[0]);
        initAttrTable(com.sun.org.apache.xalan.internal.templates.Constants.ELEMNAME_FOREACH_STRING, new String[]{com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_SELECT});
        initAttrTable(com.sun.org.apache.xalan.internal.templates.Constants.ELEMNAME_MESSAGE_STRING, new String[]{com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_TERMINATE});
        initAttrTable("number", new String[]{com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_LEVEL, "count", com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_FROM, "value", com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_FORMAT, "lang", com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_LETTERVALUE, com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_GROUPINGSEPARATOR, com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_GROUPINGSIZE});
        initAttrTable(com.sun.org.apache.xalan.internal.templates.Constants.ELEMNAME_COMMENT_STRING, new String[0]);
        initAttrTable(com.sun.org.apache.xalan.internal.templates.Constants.ELEMNAME_COPY_STRING, new String[]{com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_USEATTRIBUTESETS});
        initAttrTable(com.sun.org.apache.xalan.internal.templates.Constants.ELEMNAME_COPY_OF_STRING, new String[]{com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_SELECT});
        initAttrTable(com.sun.org.apache.xalan.internal.templates.Constants.ELEMNAME_PARAMVARIABLE_STRING, new String[]{"name", com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_SELECT});
        initAttrTable(com.sun.org.apache.xalan.internal.templates.Constants.ELEMNAME_WITHPARAM_STRING, new String[]{"name", com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_SELECT});
        initAttrTable(com.sun.org.apache.xalan.internal.templates.Constants.ELEMNAME_VARIABLE_STRING, new String[]{"name", com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_SELECT});
        initAttrTable(com.sun.org.apache.xalan.internal.templates.Constants.ELEMNAME_OUTPUT_STRING, new String[]{com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_OUTPUT_METHOD, "version", "encoding", "omit-xml-declaration", com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_OUTPUT_STANDALONE, com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_OUTPUT_DOCTYPE_PUBLIC, com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_OUTPUT_DOCTYPE_SYSTEM, com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_OUTPUT_CDATA_SECTION_ELEMENTS, "indent", com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_OUTPUT_MEDIATYPE});
        initAttrTable(com.sun.org.apache.xalan.internal.templates.Constants.ELEMNAME_SORT_STRING, new String[]{com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_SELECT, com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_ORDER, com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_CASEORDER, "lang", com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_DATATYPE});
        initAttrTable("key", new String[]{"name", com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_MATCH, "use"});
        initAttrTable(com.sun.org.apache.xalan.internal.templates.Constants.ELEMNAME_FALLBACK_STRING, new String[0]);
        initAttrTable("attribute", new String[]{"name", com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_NAMESPACE});
        initAttrTable("attribute-set", new String[]{"name", com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_USEATTRIBUTESETS});
        initAttrTable(com.sun.org.apache.xalan.internal.templates.Constants.ELEMNAME_VALUEOF_STRING, new String[]{com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_SELECT, com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_DISABLE_OUTPUT_ESCAPING});
        initAttrTable("element", new String[]{"name", com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_NAMESPACE, com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_USEATTRIBUTESETS});
        initAttrTable(com.sun.org.apache.xalan.internal.templates.Constants.ELEMNAME_CALLTEMPLATE_STRING, new String[]{"name"});
        initAttrTable(com.sun.org.apache.xalan.internal.templates.Constants.ELEMNAME_APPLY_TEMPLATES_STRING, new String[]{com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_SELECT, com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_MODE});
        initAttrTable(com.sun.org.apache.xalan.internal.templates.Constants.ELEMNAME_APPLY_IMPORTS_STRING, new String[0]);
        initAttrTable(com.sun.org.apache.xalan.internal.templates.Constants.ELEMNAME_DECIMALFORMAT_STRING, new String[]{"name", com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_DECIMALSEPARATOR, com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_GROUPINGSEPARATOR, com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_INFINITY, com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_MINUSSIGN, "NaN", com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_PERCENT, com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_PERMILLE, com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_ZERODIGIT, com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_DIGIT, com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_PATTERNSEPARATOR});
        initAttrTable("import", new String[]{com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_HREF});
        initAttrTable(com.sun.org.apache.xalan.internal.templates.Constants.ELEMNAME_INCLUDE_STRING, new String[]{com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_HREF});
        initAttrTable(com.sun.org.apache.xalan.internal.templates.Constants.ELEMNAME_STRIPSPACE_STRING, new String[]{com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_ELEMENTS});
        initAttrTable(com.sun.org.apache.xalan.internal.templates.Constants.ELEMNAME_PRESERVESPACE_STRING, new String[]{com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_ELEMENTS});
        initAttrTable(com.sun.org.apache.xalan.internal.templates.Constants.ELEMNAME_PI_STRING, new String[]{"name"});
        initAttrTable(com.sun.org.apache.xalan.internal.templates.Constants.ELEMNAME_NSALIAS_STRING, new String[]{com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_STYLESHEET_PREFIX, com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_RESULT_PREFIX});
    }

    private void initStdClass(String str, String str2) {
        this._instructionClasses.put(getQName("http://www.w3.org/1999/XSL/Transform", XSL, str).getStringRep(), "com.sun.org.apache.xalan.internal.xsltc.compiler." + str2);
    }

    private void initStdClasses() {
        initStdClass(com.sun.org.apache.xalan.internal.templates.Constants.ELEMNAME_TEMPLATE_STRING, "Template");
        initStdClass(com.sun.org.apache.xalan.internal.templates.Constants.ELEMNAME_STYLESHEET_STRING, "Stylesheet");
        initStdClass(com.sun.org.apache.xalan.internal.templates.Constants.ELEMNAME_TRANSFORM_STRING, "Stylesheet");
        initStdClass("text", "Text");
        initStdClass("if", "If");
        initStdClass(com.sun.org.apache.xalan.internal.templates.Constants.ELEMNAME_CHOOSE_STRING, "Choose");
        initStdClass("when", "When");
        initStdClass(com.sun.org.apache.xalan.internal.templates.Constants.ELEMNAME_OTHERWISE_STRING, "Otherwise");
        initStdClass(com.sun.org.apache.xalan.internal.templates.Constants.ELEMNAME_FOREACH_STRING, "ForEach");
        initStdClass(com.sun.org.apache.xalan.internal.templates.Constants.ELEMNAME_MESSAGE_STRING, "Message");
        initStdClass("number", "Number");
        initStdClass(com.sun.org.apache.xalan.internal.templates.Constants.ELEMNAME_COMMENT_STRING, "Comment");
        initStdClass(com.sun.org.apache.xalan.internal.templates.Constants.ELEMNAME_COPY_STRING, "Copy");
        initStdClass(com.sun.org.apache.xalan.internal.templates.Constants.ELEMNAME_COPY_OF_STRING, "CopyOf");
        initStdClass(com.sun.org.apache.xalan.internal.templates.Constants.ELEMNAME_PARAMVARIABLE_STRING, "Param");
        initStdClass(com.sun.org.apache.xalan.internal.templates.Constants.ELEMNAME_WITHPARAM_STRING, "WithParam");
        initStdClass(com.sun.org.apache.xalan.internal.templates.Constants.ELEMNAME_VARIABLE_STRING, "Variable");
        initStdClass(com.sun.org.apache.xalan.internal.templates.Constants.ELEMNAME_OUTPUT_STRING, "Output");
        initStdClass(com.sun.org.apache.xalan.internal.templates.Constants.ELEMNAME_SORT_STRING, "Sort");
        initStdClass("key", "Key");
        initStdClass(com.sun.org.apache.xalan.internal.templates.Constants.ELEMNAME_FALLBACK_STRING, "Fallback");
        initStdClass("attribute", "XslAttribute");
        initStdClass("attribute-set", "AttributeSet");
        initStdClass(com.sun.org.apache.xalan.internal.templates.Constants.ELEMNAME_VALUEOF_STRING, "ValueOf");
        initStdClass("element", "XslElement");
        initStdClass(com.sun.org.apache.xalan.internal.templates.Constants.ELEMNAME_CALLTEMPLATE_STRING, "CallTemplate");
        initStdClass(com.sun.org.apache.xalan.internal.templates.Constants.ELEMNAME_APPLY_TEMPLATES_STRING, "ApplyTemplates");
        initStdClass(com.sun.org.apache.xalan.internal.templates.Constants.ELEMNAME_APPLY_IMPORTS_STRING, "ApplyImports");
        initStdClass(com.sun.org.apache.xalan.internal.templates.Constants.ELEMNAME_DECIMALFORMAT_STRING, "DecimalFormatting");
        initStdClass("import", "Import");
        initStdClass(com.sun.org.apache.xalan.internal.templates.Constants.ELEMNAME_INCLUDE_STRING, "Include");
        initStdClass(com.sun.org.apache.xalan.internal.templates.Constants.ELEMNAME_STRIPSPACE_STRING, "Whitespace");
        initStdClass(com.sun.org.apache.xalan.internal.templates.Constants.ELEMNAME_PRESERVESPACE_STRING, "Whitespace");
        initStdClass(com.sun.org.apache.xalan.internal.templates.Constants.ELEMNAME_PI_STRING, "ProcessingInstruction");
        initStdClass(com.sun.org.apache.xalan.internal.templates.Constants.ELEMNAME_NSALIAS_STRING, "NamespaceAlias");
    }

    private void initSymbolTable() {
        Type type = Type.Int;
        Type type2 = Type.Void;
        MethodType methodType = new MethodType(type, type2);
        Type type3 = Type.Real;
        new MethodType(type, type3);
        Type type4 = Type.String;
        MethodType methodType2 = new MethodType(type, type4);
        Type type5 = Type.NodeSet;
        MethodType methodType3 = new MethodType(type, type5);
        new MethodType(type3, type);
        MethodType methodType4 = new MethodType(type3, type2);
        MethodType methodType5 = new MethodType(type3, type3);
        MethodType methodType6 = new MethodType(type3, type5);
        Type type6 = Type.Reference;
        MethodType methodType7 = new MethodType(type3, type6);
        MethodType methodType8 = new MethodType(type, type);
        MethodType methodType9 = new MethodType(type5, type6);
        MethodType methodType10 = new MethodType(type5, type2);
        MethodType methodType11 = new MethodType(type5, type4);
        MethodType methodType12 = new MethodType(type5, type5);
        Type type7 = Type.Node;
        MethodType methodType13 = new MethodType(type7, type2);
        MethodType methodType14 = new MethodType(type4, type2);
        MethodType methodType15 = new MethodType(type4, type4);
        MethodType methodType16 = new MethodType(type4, type7);
        MethodType methodType17 = new MethodType(type4, type5);
        MethodType methodType18 = new MethodType(type4, type6);
        Type type8 = Type.Boolean;
        MethodType methodType19 = new MethodType(type8, type6);
        MethodType methodType20 = new MethodType(type8, type2);
        MethodType methodType21 = new MethodType(type8, type8);
        MethodType methodType22 = new MethodType(type8, type4);
        new MethodType(type5, Type.Object);
        MethodType methodType23 = new MethodType(type3, type3, type3);
        MethodType methodType24 = new MethodType(type, type, type);
        MethodType methodType25 = new MethodType(type8, type3, type3);
        MethodType methodType26 = new MethodType(type8, type, type);
        MethodType methodType27 = new MethodType(type4, type4, type4);
        MethodType methodType28 = new MethodType(type4, type3, type4);
        MethodType methodType29 = new MethodType(type4, type4, type3);
        MethodType methodType30 = new MethodType(type6, type4, type6);
        MethodType methodType31 = new MethodType(type5, type4, type4);
        MethodType methodType32 = new MethodType(type5, type4, type5);
        MethodType methodType33 = new MethodType(type8, type8, type8);
        MethodType methodType34 = new MethodType(type8, type4, type4);
        new MethodType(type4, type4, type5);
        MethodType methodType35 = new MethodType(type4, type3, type4, type4);
        MethodType methodType36 = new MethodType(type4, type4, type3, type3);
        MethodType methodType37 = new MethodType(type4, type4, type4, type4);
        this._symbolTable.addPrimop(Keywords.FUNC_CURRENT_STRING, methodType13);
        this._symbolTable.addPrimop(Keywords.FUNC_LAST_STRING, methodType);
        this._symbolTable.addPrimop(Keywords.FUNC_POSITION_STRING, methodType);
        this._symbolTable.addPrimop("true", methodType20);
        this._symbolTable.addPrimop("false", methodType20);
        this._symbolTable.addPrimop(Keywords.FUNC_NOT_STRING, methodType21);
        this._symbolTable.addPrimop("name", methodType14);
        this._symbolTable.addPrimop("name", methodType16);
        this._symbolTable.addPrimop(Keywords.FUNC_GENERATE_ID_STRING, methodType14);
        this._symbolTable.addPrimop(Keywords.FUNC_GENERATE_ID_STRING, methodType16);
        this._symbolTable.addPrimop(Keywords.FUNC_CEILING_STRING, methodType5);
        this._symbolTable.addPrimop(Keywords.FUNC_FLOOR_STRING, methodType5);
        this._symbolTable.addPrimop(Keywords.FUNC_ROUND_STRING, methodType5);
        this._symbolTable.addPrimop(Keywords.FUNC_CONTAINS_STRING, methodType34);
        this._symbolTable.addPrimop("number", methodType7);
        this._symbolTable.addPrimop("number", methodType4);
        this._symbolTable.addPrimop("boolean", methodType19);
        this._symbolTable.addPrimop("string", methodType18);
        this._symbolTable.addPrimop("string", methodType14);
        this._symbolTable.addPrimop(Keywords.FUNC_TRANSLATE_STRING, methodType37);
        this._symbolTable.addPrimop(Keywords.FUNC_STRING_LENGTH_STRING, methodType);
        this._symbolTable.addPrimop(Keywords.FUNC_STRING_LENGTH_STRING, methodType2);
        this._symbolTable.addPrimop(Keywords.FUNC_STARTS_WITH_STRING, methodType34);
        this._symbolTable.addPrimop("format-number", methodType28);
        this._symbolTable.addPrimop("format-number", methodType35);
        this._symbolTable.addPrimop(Keywords.FUNC_UNPARSED_ENTITY_URI_STRING, methodType15);
        this._symbolTable.addPrimop("key", methodType31);
        this._symbolTable.addPrimop("key", methodType32);
        this._symbolTable.addPrimop("id", methodType11);
        this._symbolTable.addPrimop("id", methodType12);
        this._symbolTable.addPrimop(Keywords.FUNC_NAMESPACE_STRING, methodType14);
        this._symbolTable.addPrimop(Keywords.FUNC_EXT_FUNCTION_AVAILABLE_STRING, methodType22);
        this._symbolTable.addPrimop(Keywords.FUNC_EXT_ELEM_AVAILABLE_STRING, methodType22);
        this._symbolTable.addPrimop(Constants.DOCUMENT_PNAME, methodType11);
        this._symbolTable.addPrimop(Constants.DOCUMENT_PNAME, methodType10);
        this._symbolTable.addPrimop("count", methodType3);
        this._symbolTable.addPrimop(Keywords.FUNC_SUM_STRING, methodType6);
        this._symbolTable.addPrimop(Keywords.FUNC_LOCAL_PART_STRING, methodType14);
        this._symbolTable.addPrimop(Keywords.FUNC_LOCAL_PART_STRING, methodType17);
        this._symbolTable.addPrimop(Keywords.FUNC_NAMESPACE_STRING, methodType14);
        this._symbolTable.addPrimop(Keywords.FUNC_NAMESPACE_STRING, methodType17);
        this._symbolTable.addPrimop(Keywords.FUNC_SUBSTRING_STRING, methodType29);
        this._symbolTable.addPrimop(Keywords.FUNC_SUBSTRING_STRING, methodType36);
        this._symbolTable.addPrimop(Keywords.FUNC_SUBSTRING_AFTER_STRING, methodType27);
        this._symbolTable.addPrimop(Keywords.FUNC_SUBSTRING_BEFORE_STRING, methodType27);
        this._symbolTable.addPrimop(Keywords.FUNC_NORMALIZE_SPACE_STRING, methodType14);
        this._symbolTable.addPrimop(Keywords.FUNC_NORMALIZE_SPACE_STRING, methodType15);
        this._symbolTable.addPrimop(Keywords.FUNC_SYSTEM_PROPERTY_STRING, methodType15);
        this._symbolTable.addPrimop("nodeset", methodType9);
        this._symbolTable.addPrimop("objectType", methodType18);
        this._symbolTable.addPrimop("cast", methodType30);
        this._symbolTable.addPrimop("+", methodType23);
        this._symbolTable.addPrimop("-", methodType23);
        this._symbolTable.addPrimop("*", methodType23);
        this._symbolTable.addPrimop(PsuedoNames.PSEUDONAME_ROOT, methodType23);
        this._symbolTable.addPrimop("%", methodType23);
        this._symbolTable.addPrimop("+", methodType24);
        this._symbolTable.addPrimop("-", methodType24);
        this._symbolTable.addPrimop("*", methodType24);
        this._symbolTable.addPrimop("<", methodType25);
        this._symbolTable.addPrimop("<=", methodType25);
        this._symbolTable.addPrimop(">", methodType25);
        this._symbolTable.addPrimop(">=", methodType25);
        this._symbolTable.addPrimop("<", methodType26);
        this._symbolTable.addPrimop("<=", methodType26);
        this._symbolTable.addPrimop(">", methodType26);
        this._symbolTable.addPrimop(">=", methodType26);
        this._symbolTable.addPrimop("<", methodType33);
        this._symbolTable.addPrimop("<=", methodType33);
        this._symbolTable.addPrimop(">", methodType33);
        this._symbolTable.addPrimop(">=", methodType33);
        this._symbolTable.addPrimop("or", methodType33);
        this._symbolTable.addPrimop("and", methodType33);
        this._symbolTable.addPrimop("u-", methodType5);
        this._symbolTable.addPrimop("u-", methodType8);
    }

    private SyntaxTreeNode loadExternalStylesheet(String str) throws CompilerException {
        InputSource inputSource;
        if (new File(str).exists()) {
            inputSource = new InputSource("file:" + str);
        } else {
            inputSource = new InputSource(str);
        }
        return parse(inputSource);
    }

    private SyntaxTreeNode parseTopLevel(SyntaxTreeNode syntaxTreeNode, String str, String str2) {
        SyntaxTreeNode syntaxTreeNode2;
        int lineNumber = getLineNumber();
        try {
            this._xpathParser.setScanner(new XPathLexer(new StringReader(str)));
            Symbol symbol = this._xpathParser.parse(str2, lineNumber);
            if (symbol == null || (syntaxTreeNode2 = (SyntaxTreeNode) symbol.value) == null) {
                reportError(3, new ErrorMsg(ErrorMsg.XPATH_PARSER_ERR, (Object) str2, syntaxTreeNode));
                SyntaxTreeNode syntaxTreeNode3 = SyntaxTreeNode.Dummy;
                syntaxTreeNode3.setParser(this);
                return syntaxTreeNode3;
            }
            syntaxTreeNode2.setParser(this);
            syntaxTreeNode2.setParent(syntaxTreeNode);
            syntaxTreeNode2.setLineNumber(lineNumber);
            return syntaxTreeNode2;
        } catch (Exception e) {
            if (ErrorMsg.XPATH_LIMIT.equals(e.getMessage())) {
                f63.a(ErrorMsg.XPATH_LIMIT);
                return null;
            }
            if (this._xsltc.debug()) {
                e.printStackTrace();
            }
            reportError(3, new ErrorMsg(ErrorMsg.XPATH_PARSER_ERR, (Object) str2, syntaxTreeNode));
        }
    }

    public void addParameter(Param param) {
        addVariableOrParam(param);
    }

    public void addVariable(Variable variable) {
        addVariableOrParam(variable);
    }

    @Override // org.xml.sax.ContentHandler
    public void characters(char[] cArr, int i, int i2) {
        String str = new String(cArr, i, i2);
        SyntaxTreeNode syntaxTreeNodePeek = this._parentStack.peek();
        if (str.length() == 0) {
            return;
        }
        if (syntaxTreeNodePeek instanceof Text) {
            ((Text) syntaxTreeNodePeek).setText(str);
            return;
        }
        if (syntaxTreeNodePeek instanceof Stylesheet) {
            return;
        }
        SyntaxTreeNode syntaxTreeNodeLastChild = syntaxTreeNodePeek.lastChild();
        if (syntaxTreeNodeLastChild != null && (syntaxTreeNodeLastChild instanceof Text)) {
            Text text = (Text) syntaxTreeNodeLastChild;
            if (!text.isTextElement() && (i2 > 1 || cArr[0] < 256)) {
                text.setText(str);
                return;
            }
        }
        syntaxTreeNodePeek.addElement(new Text(str));
    }

    public void createAST(Stylesheet stylesheet) {
        if (stylesheet != null) {
            try {
                stylesheet.parseContents(this);
                Iterator<SyntaxTreeNode> itElements = stylesheet.elements();
                while (itElements.hasNext()) {
                    if (itElements.next() instanceof Text) {
                        reportError(3, new ErrorMsg(ErrorMsg.ILLEGAL_TEXT_NODE_ERR, getLineNumber(), (Object) null));
                    }
                }
                if (errorsFound()) {
                    return;
                }
                stylesheet.typeCheck(this._symbolTable);
            } catch (TypeCheckError e) {
                reportError(3, new ErrorMsg(ErrorMsg.JAXP_COMPILE_ERR, (Throwable) e));
            }
        }
    }

    public boolean elementSupported(String str, String str2) {
        return this._instructionClasses.get(getQName(str, XSL, str2).getStringRep()) != null;
    }

    @Override // org.xml.sax.ContentHandler
    public void endDocument() {
    }

    @Override // org.xml.sax.ContentHandler
    public void endElement(String str, String str2, String str3) {
        this._parentStack.pop();
    }

    @Override // org.xml.sax.ContentHandler
    public void endPrefixMapping(String str) {
    }

    public boolean errorsFound() {
        return this._errors.size() > 0;
    }

    public boolean functionSupported(String str) {
        return this._symbolTable.lookupPrimop(str) != null;
    }

    public int getCurrentImportPrecedence() {
        return this._currentImportPrecedence;
    }

    public Stylesheet getCurrentStylesheet() {
        return this._currentStylesheet;
    }

    public SyntaxTreeNode getDocumentRoot() {
        return this._root;
    }

    public ArrayList<ErrorMsg> getErrors() {
        return this._errors;
    }

    public QName getExcludeResultPrefixes() {
        return this._excludeResultPrefixes;
    }

    public QName getExtensionElementPrefixes() {
        return this._extensionElementPrefixes;
    }

    public int getNextImportPrecedence() {
        int i = this._currentImportPrecedence + 1;
        this._currentImportPrecedence = i;
        return i;
    }

    public Output getOutput() {
        return this._output;
    }

    public Properties getOutputProperties() {
        return getTopLevelStylesheet().getOutputProperties();
    }

    public QName getQName(String str, String str2, String str3) {
        String str4;
        if (str == null || str.equals("")) {
            QName qName = this._qNames.get(str3);
            if (qName != null) {
                return qName;
            }
            QName qName2 = new QName(null, str2, str3);
            this._qNames.put(str3, qName2);
            return qName2;
        }
        Map<String, QName> map = this._namespaces.get(str);
        if (str2 == null || str2.length() == 0) {
            str4 = str3;
        } else {
            str4 = str2 + ':' + str3;
        }
        if (map == null) {
            QName qName3 = new QName(str, str2, str3);
            Map<String, Map<String, QName>> map2 = this._namespaces;
            HashMap map3 = new HashMap();
            map2.put(str, map3);
            map3.put(str4, qName3);
            return qName3;
        }
        QName qName4 = map.get(str4);
        if (qName4 != null) {
            return qName4;
        }
        QName qName5 = new QName(str, str2, str3);
        map.put(str4, qName5);
        return qName5;
    }

    public QName getQNameIgnoreDefaultNs(String str) {
        return getQName(str, true, true);
    }

    public QName getQNameSafe(String str) {
        int iLastIndexOf = str.lastIndexOf(58);
        String str2 = "";
        if (iLastIndexOf == -1) {
            return getQName(str.equals("xmlns") ? null : this._symbolTable.lookupNamespace(""), (String) null, str);
        }
        String strSubstring = str.substring(0, iLastIndexOf);
        String strSubstring2 = str.substring(iLastIndexOf + 1);
        if (strSubstring.equals("xmlns")) {
            str2 = null;
        } else {
            String strLookupNamespace = this._symbolTable.lookupNamespace(strSubstring);
            if (strLookupNamespace != null) {
                str2 = strLookupNamespace;
            }
        }
        return getQName(str2, strSubstring, strSubstring2);
    }

    public SymbolTable getSymbolTable() {
        return this._symbolTable;
    }

    public Template getTemplate() {
        return this._template;
    }

    public int getTemplateIndex() {
        int i = this._templateIndex;
        this._templateIndex = i + 1;
        return i;
    }

    public Stylesheet getTopLevelStylesheet() {
        return this._xsltc.getStylesheet();
    }

    public QName getUseAttributeSets() {
        return this._useAttributeSets;
    }

    public ArrayList<ErrorMsg> getWarnings() {
        return this._warnings;
    }

    public XSLTC getXSLTC() {
        return this._xsltc;
    }

    @Override // org.xml.sax.ContentHandler
    public void ignorableWhitespace(char[] cArr, int i, int i2) {
    }

    public void init() {
        this._qNames = new HashMap(512);
        this._namespaces = new HashMap();
        this._instructionClasses = new HashMap();
        this._instructionAttrs = new HashMap();
        this._variableScope = new HashMap();
        this._template = null;
        this._errors = new ArrayList<>();
        this._warnings = new ArrayList<>();
        this._symbolTable = new SymbolTable();
        this._xpathParser = new XPathParser(this);
        this._currentStylesheet = null;
        this._output = null;
        this._root = null;
        this._rootNamespaceDef = false;
        this._currentImportPrecedence = 1;
        initStdClasses();
        initInstructionAttrs();
        initExtClasses();
        initSymbolTable();
        this._useAttributeSets = getQName("http://www.w3.org/1999/XSL/Transform", XSL, com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_USEATTRIBUTESETS);
        this._excludeResultPrefixes = getQName("http://www.w3.org/1999/XSL/Transform", XSL, com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_EXCLUDE_RESULT_PREFIXES);
        this._extensionElementPrefixes = getQName("http://www.w3.org/1999/XSL/Transform", XSL, com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_EXTENSIONELEMENTPREFIXES);
    }

    public VariableBase lookupVariable(QName qName) {
        Object obj = this._variableScope.get(qName.getStringRep());
        if (obj instanceof VariableBase) {
            return (VariableBase) obj;
        }
        if (obj instanceof Stack) {
            return (VariableBase) ((Stack) obj).peek();
        }
        return null;
    }

    public SyntaxTreeNode makeInstance(String str, String str2, String str3, Attributes attributes) {
        SyntaxTreeNode syntaxTreeNode;
        UnsupportedElement unsupportedElement;
        QName qName = getQName(str, str2, str3);
        String str4 = this._instructionClasses.get(qName.getStringRep());
        SyntaxTreeNode syntaxTreeNode2 = null;
        syntaxTreeNode2 = null;
        syntaxTreeNode2 = null;
        syntaxTreeNode2 = null;
        syntaxTreeNode2 = null;
        syntaxTreeNode2 = null;
        if (str4 != null) {
            try {
                SyntaxTreeNode syntaxTreeNode3 = (SyntaxTreeNode) ObjectFactory.findProviderClass(str4, true).getDeclaredConstructor(null).newInstance(null);
                try {
                    syntaxTreeNode3.setQName(qName);
                    syntaxTreeNode3.setParser(this);
                    if (this._locator != null) {
                        syntaxTreeNode3.setLineNumber(getLineNumber());
                    }
                    if (syntaxTreeNode3 instanceof Stylesheet) {
                        this._xsltc.setStylesheet((Stylesheet) syntaxTreeNode3);
                    }
                    checkForSuperfluousAttributes(syntaxTreeNode3, attributes);
                    syntaxTreeNode = syntaxTreeNode3;
                } catch (ClassNotFoundException unused) {
                    syntaxTreeNode2 = syntaxTreeNode3;
                    reportError(3, new ErrorMsg(ErrorMsg.CLASS_NOT_FOUND_ERR, syntaxTreeNode2));
                    syntaxTreeNode = syntaxTreeNode2;
                } catch (Exception e) {
                    e = e;
                    syntaxTreeNode2 = syntaxTreeNode3;
                    reportError(2, new ErrorMsg(ErrorMsg.INTERNAL_ERR, (Object) e.getMessage(), syntaxTreeNode2));
                    syntaxTreeNode = syntaxTreeNode2;
                }
            } catch (ClassNotFoundException unused2) {
            } catch (Exception e2) {
                e = e2;
            }
        } else {
            if (str != null) {
                if (str.equals("http://www.w3.org/1999/XSL/Transform")) {
                    unsupportedElement = new UnsupportedElement(str, str2, str3, false);
                    ErrorMsg errorMsg = new ErrorMsg("UNSUPPORTED_XSL_ERR", getLineNumber(), str3);
                    unsupportedElement.setErrorMessage(errorMsg);
                    if (this.versionIsOne) {
                        syntaxTreeNode2 = unsupportedElement;
                        reportError(1, errorMsg);
                        syntaxTreeNode2 = unsupportedElement;
                    }
                } else if (str.equals(Constants.TRANSLET_URI)) {
                    UnsupportedElement unsupportedElement2 = new UnsupportedElement(str, str2, str3, true);
                    unsupportedElement2.setErrorMessage(new ErrorMsg("UNSUPPORTED_EXT_ERR", getLineNumber(), str3));
                    syntaxTreeNode2 = unsupportedElement2;
                } else {
                    Stylesheet stylesheet = this._xsltc.getStylesheet();
                    if (stylesheet != null && stylesheet.isExtension(str) && stylesheet != this._parentStack.peek()) {
                        UnsupportedElement unsupportedElement3 = new UnsupportedElement(str, str2, str3, true);
                        unsupportedElement3.setErrorMessage(new ErrorMsg("UNSUPPORTED_EXT_ERR", getLineNumber(), str2 + ":" + str3));
                        syntaxTreeNode2 = unsupportedElement3;
                    }
                }
            }
            if (syntaxTreeNode2 == null) {
                LiteralElement literalElement = new LiteralElement();
                literalElement.setLineNumber(getLineNumber());
                syntaxTreeNode = literalElement;
            } else {
                syntaxTreeNode = syntaxTreeNode2;
            }
        }
        if (syntaxTreeNode != null && (syntaxTreeNode instanceof LiteralElement)) {
            ((LiteralElement) syntaxTreeNode).setQName(qName);
        }
        return syntaxTreeNode;
    }

    public Stylesheet makeStylesheet(SyntaxTreeNode syntaxTreeNode) throws CompilerException {
        Stylesheet stylesheet;
        try {
            if (syntaxTreeNode instanceof Stylesheet) {
                stylesheet = (Stylesheet) syntaxTreeNode;
            } else {
                Stylesheet stylesheet2 = new Stylesheet();
                stylesheet2.setSimplified();
                stylesheet2.addElement(syntaxTreeNode);
                stylesheet2.setAttributes((AttributesImpl) syntaxTreeNode.getAttributes());
                if (syntaxTreeNode.lookupNamespace("") == null) {
                    syntaxTreeNode.addPrefixMapping("", "");
                }
                stylesheet = stylesheet2;
            }
            stylesheet.setParser(this);
            return stylesheet;
        } catch (ClassCastException unused) {
            throw new CompilerException(new ErrorMsg(ErrorMsg.NOT_STYLESHEET_ERR, syntaxTreeNode).toString());
        }
    }

    public SyntaxTreeNode parse(InputSource inputSource) {
        CatalogFeatures catalogFeatures;
        try {
            XMLReader xMLReader = JdkXmlUtils.getXMLReader(this._overrideDefaultParser, this._xsltc.isSecureProcessing());
            JdkXmlUtils.setXMLReaderPropertyIfSupport(xMLReader, "http://javax.xml.XMLConstants/property/accessExternalDTD", this._xsltc.getProperty("http://javax.xml.XMLConstants/property/accessExternalDTD"), true);
            boolean feature = this._xsltc.getFeature(JdkXmlFeatures.XmlFeature.USE_CATALOG);
            try {
                xMLReader.setFeature("http://javax.xml.XMLConstants/feature/useCatalog", feature);
                if (feature && (catalogFeatures = (CatalogFeatures) this._xsltc.getProperty(JdkXmlFeatures.CATALOG_FEATURES)) != null) {
                    for (CatalogFeatures.Feature feature2 : CatalogFeatures.Feature.values()) {
                        xMLReader.setProperty(feature2.getPropertyName(), catalogFeatures.get(feature2));
                    }
                }
            } catch (SAXNotRecognizedException | SAXNotSupportedException unused) {
            }
            String str = "";
            try {
                XMLSecurityManager xMLSecurityManager = (XMLSecurityManager) this._xsltc.getProperty("http://apache.org/xml/properties/security-manager");
                for (XMLSecurityManager.Limit limit : XMLSecurityManager.Limit.values()) {
                    if (limit.isSupported(XMLSecurityManager.Processor.PARSER)) {
                        xMLReader.setProperty(limit.apiProperty(), xMLSecurityManager.getLimitValueAsString(limit));
                    }
                }
                if (xMLSecurityManager.printEntityCountInfo()) {
                    str = JdkConstants.JDK_DEBUG_LIMIT;
                    xMLReader.setProperty(JdkConstants.JDK_DEBUG_LIMIT, JdkConstants.JDK_YES);
                }
            } catch (SAXException e) {
                XMLSecurityManager.printWarning(xMLReader.getClass().getName(), str, e);
            }
            JdkXmlUtils.setXMLReaderPropertyIfSupport(xMLReader, JdkConstants.CDATA_CHUNK_SIZE, this._xsltc.getProperty(JdkConstants.CDATA_CHUNK_SIZE), false);
            return parse(xMLReader, inputSource);
        } catch (SAXException e2) {
            reportError(3, new ErrorMsg(e2.getMessage()));
            return null;
        }
    }

    public Expression parseExpression(SyntaxTreeNode syntaxTreeNode, String str, String str2) {
        String attribute = syntaxTreeNode.getAttribute(str);
        if (attribute.length() != 0 || str2 == null) {
            str2 = attribute;
        }
        return (Expression) parseTopLevel(syntaxTreeNode, "<EXPRESSION>".concat(str2), str2);
    }

    public Pattern parsePattern(SyntaxTreeNode syntaxTreeNode, String str, String str2) {
        String attribute = syntaxTreeNode.getAttribute(str);
        if (attribute.length() != 0 || str2 == null) {
            str2 = attribute;
        }
        return (Pattern) parseTopLevel(syntaxTreeNode, "<PATTERN>".concat(str2), str2);
    }

    public void printErrors() {
        int size = this._errors.size();
        if (size > 0) {
            System.err.println(new ErrorMsg(ErrorMsg.COMPILER_ERROR_KEY));
            for (int i = 0; i < size; i++) {
                System.err.println("  " + this._errors.get(i));
            }
        }
    }

    public void printWarnings() {
        int size = this._warnings.size();
        if (size > 0) {
            System.err.println(new ErrorMsg(ErrorMsg.COMPILER_WARNING_KEY));
            for (int i = 0; i < size; i++) {
                System.err.println("  " + this._warnings.get(i));
            }
        }
    }

    @Override // org.xml.sax.ContentHandler
    public void processingInstruction(String str, String str2) {
        if (this._target == null && str.equals("xml-stylesheet")) {
            StringTokenizer stringTokenizer = new StringTokenizer(str2);
            String tokenValue = null;
            String tokenValue2 = null;
            String tokenValue3 = null;
            String tokenValue4 = null;
            while (stringTokenizer.hasMoreElements()) {
                String str3 = (String) stringTokenizer.nextElement();
                if (str3.startsWith(com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_HREF)) {
                    tokenValue4 = getTokenValue(str3);
                } else if (str3.startsWith("media")) {
                    tokenValue = getTokenValue(str3);
                } else if (str3.startsWith("title")) {
                    tokenValue2 = getTokenValue(str3);
                } else if (str3.startsWith("charset")) {
                    tokenValue3 = getTokenValue(str3);
                }
            }
            String str4 = this._PImedia;
            if (str4 == null || str4.equals(tokenValue)) {
                if (this._PItitle == null || this._PImedia.equals(tokenValue2)) {
                    if (this._PIcharset == null || this._PImedia.equals(tokenValue3)) {
                        this._target = tokenValue4;
                    }
                }
            }
        }
    }

    public void removeVariable(QName qName) {
        Object obj = this._variableScope.get(qName.getStringRep());
        if (obj instanceof Stack) {
            Stack stack = (Stack) obj;
            if (!stack.isEmpty()) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                return;
            }
        }
        this._variableScope.remove(qName.getStringRep());
    }

    public void reportError(int i, ErrorMsg errorMsg) {
        if (i == 0) {
            this._errors.add(errorMsg);
            return;
        }
        if (i == 1) {
            this._errors.add(errorMsg);
            return;
        }
        if (i == 2) {
            this._errors.add(errorMsg);
        } else if (i == 3) {
            this._errors.add(errorMsg);
        } else {
            if (i != 4) {
                return;
            }
            this._warnings.add(errorMsg);
        }
    }

    public void setCurrentStylesheet(Stylesheet stylesheet) {
        this._currentStylesheet = stylesheet;
    }

    @Override // org.xml.sax.ContentHandler
    public void setDocumentLocator(Locator locator) {
        this._locator = locator;
    }

    public void setOutput(Output output) {
        Output output2 = this._output;
        if (output2 == null) {
            this._output = output;
        } else {
            if (output2.getImportPrecedence() > output.getImportPrecedence()) {
                output.disable();
                return;
            }
            output.mergeOutput(this._output);
            this._output.disable();
            this._output = output;
        }
    }

    public void setPIParameters(String str, String str2, String str3) {
        this._PImedia = str;
        this._PItitle = str2;
        this._PIcharset = str3;
    }

    public void setTemplate(Template template) {
        this._template = template;
    }

    public void setXSLTC(XSLTC xsltc) {
        this._xsltc = xsltc;
    }

    @Override // org.xml.sax.ContentHandler
    public void skippedEntity(String str) {
    }

    @Override // org.xml.sax.ContentHandler
    public void startDocument() {
        this._root = null;
        this._target = null;
        this._prefixMapping = null;
        this._parentStack = new Stack<>();
    }

    @Override // org.xml.sax.ContentHandler
    public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
        int iLastIndexOf = str3.lastIndexOf(58);
        String strSubstring = iLastIndexOf == -1 ? null : str3.substring(0, iLastIndexOf);
        SyntaxTreeNode syntaxTreeNodeMakeInstance = makeInstance(str, strSubstring, str2, attributes);
        if (syntaxTreeNodeMakeInstance == null) {
            throw new SAXException(new ErrorMsg(ErrorMsg.ELEMENT_PARSE_ERR, strSubstring + ':' + str2).toString());
        }
        if (this._root == null) {
            Map<String, String> map = this._prefixMapping;
            if (map == null || !map.containsValue("http://www.w3.org/1999/XSL/Transform")) {
                this._rootNamespaceDef = false;
            } else {
                this._rootNamespaceDef = true;
            }
            this._root = syntaxTreeNodeMakeInstance;
        } else {
            SyntaxTreeNode syntaxTreeNodePeek = this._parentStack.peek();
            if (syntaxTreeNodeMakeInstance.getClass().isAssignableFrom(Import.class) && syntaxTreeNodePeek.notTypeOf(Import.class)) {
                throw new SAXException(new ErrorMsg(ErrorMsg.IMPORT_PRECEDE_OTHERS_ERR, strSubstring + ':' + str2).toString());
            }
            syntaxTreeNodePeek.addElement(syntaxTreeNodeMakeInstance);
            syntaxTreeNodeMakeInstance.setParent(syntaxTreeNodePeek);
        }
        syntaxTreeNodeMakeInstance.setAttributes(new AttributesImpl(attributes));
        syntaxTreeNodeMakeInstance.setPrefixMapping(this._prefixMapping);
        if (syntaxTreeNodeMakeInstance instanceof Stylesheet) {
            getSymbolTable().setCurrentNode(syntaxTreeNodeMakeInstance);
            ((Stylesheet) syntaxTreeNodeMakeInstance).declareExtensionPrefixes(this);
        }
        this._prefixMapping = null;
        this._parentStack.push(syntaxTreeNodeMakeInstance);
    }

    @Override // org.xml.sax.ContentHandler
    public void startPrefixMapping(String str, String str2) {
        if (this._prefixMapping == null) {
            this._prefixMapping = new HashMap();
        }
        this._prefixMapping.put(str, str2);
    }

    public Expression parseExpression(SyntaxTreeNode syntaxTreeNode, String str) {
        return (Expression) parseTopLevel(syntaxTreeNode, "<EXPRESSION>" + str, null);
    }

    public Pattern parsePattern(SyntaxTreeNode syntaxTreeNode, String str) {
        return (Pattern) parseTopLevel(syntaxTreeNode, "<PATTERN>" + str, str);
    }

    private void initExtClass(String str, String str2, String str3) {
        this._instructionClasses.put(getQName(str, "translet", str2).getStringRep(), "com.sun.org.apache.xalan.internal.xsltc.compiler." + str3);
    }

    public QName getQName(String str, boolean z) {
        return getQName(str, z, false);
    }

    private QName getQName(String str, boolean z, boolean z2) {
        int iLastIndexOf = str.lastIndexOf(58);
        String strLookupNamespace = null;
        if (iLastIndexOf != -1) {
            String strSubstring = str.substring(0, iLastIndexOf);
            String strSubstring2 = str.substring(iLastIndexOf + 1);
            if (!strSubstring.equals("xmlns") && (strLookupNamespace = this._symbolTable.lookupNamespace(strSubstring)) == null && z) {
                reportError(3, new ErrorMsg(ErrorMsg.NAMESPACE_UNDEF_ERR, getLineNumber(), strSubstring));
            }
            return getQName(strLookupNamespace, strSubstring, strSubstring2);
        }
        if (str.equals("xmlns")) {
            z2 = true;
        }
        return getQName(z2 ? null : this._symbolTable.lookupNamespace(""), (String) null, str);
    }

    public QName getQName(String str) {
        return getQName(str, true, false);
    }

    public QName getQName(String str, String str2) {
        return getQName(str + str2);
    }

    public QName getQName(QName qName, QName qName2) {
        return getQName(qName.toString() + qName2.toString());
    }

    public SyntaxTreeNode parse(XMLReader xMLReader, InputSource inputSource) {
        try {
            xMLReader.setContentHandler(this);
            if (this._hasUserErrListener) {
                xMLReader.setErrorHandler(new ErrorHandlerProxy());
            }
            xMLReader.parse(inputSource);
            return getStylesheet(this._root);
        } catch (CompilerException e) {
            if (this._xsltc.debug()) {
                e.printStackTrace();
            }
            reportError(3, new ErrorMsg(ErrorMsg.JAXP_COMPILE_ERR, (Throwable) e));
            return null;
        } catch (IOException e2) {
            if (this._xsltc.debug()) {
                e2.printStackTrace();
            }
            reportError(3, new ErrorMsg(ErrorMsg.JAXP_COMPILE_ERR, (Throwable) e2));
            return null;
        } catch (SAXException e3) {
            Exception exception = e3.getException();
            if (this._xsltc.debug()) {
                e3.printStackTrace();
                if (exception != null) {
                    exception.printStackTrace();
                }
            }
            reportError(3, new ErrorMsg(ErrorMsg.JAXP_COMPILE_ERR, (Throwable) e3));
            return null;
        } catch (Exception e4) {
            if (this._xsltc.debug()) {
                e4.printStackTrace();
            }
            reportError(3, new ErrorMsg(ErrorMsg.JAXP_COMPILE_ERR, (Throwable) e4));
            return null;
        }
    }
}
