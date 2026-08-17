package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.bcel.internal.generic.ANEWARRAY;
import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.FieldGen;
import com.sun.org.apache.bcel.internal.generic.GETFIELD;
import com.sun.org.apache.bcel.internal.generic.GETSTATIC;
import com.sun.org.apache.bcel.internal.generic.INVOKEINTERFACE;
import com.sun.org.apache.bcel.internal.generic.INVOKESPECIAL;
import com.sun.org.apache.bcel.internal.generic.INVOKESTATIC;
import com.sun.org.apache.bcel.internal.generic.INVOKEVIRTUAL;
import com.sun.org.apache.bcel.internal.generic.ISTORE;
import com.sun.org.apache.bcel.internal.generic.InstructionHandle;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.bcel.internal.generic.LocalVariableGen;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.bcel.internal.generic.NEWARRAY;
import com.sun.org.apache.bcel.internal.generic.PUSH;
import com.sun.org.apache.bcel.internal.generic.PUTFIELD;
import com.sun.org.apache.bcel.internal.generic.PUTSTATIC;
import com.sun.org.apache.bcel.internal.generic.TargetLostException;
import com.sun.org.apache.bcel.internal.generic.Type;
import com.sun.org.apache.bcel.internal.util.InstructionFinder;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;
import com.sun.org.apache.xml.internal.utils.SystemIDResolver;
import com.sun.org.apache.xpath.internal.compiler.Keywords;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class Stylesheet extends SyntaxTreeNode {
    public static final int HTML_OUTPUT = 2;
    public static final int TEXT_OUTPUT = 3;
    public static final int UNKNOWN_OUTPUT = 0;
    public static final int XML_OUTPUT = 1;
    private String _className;
    private Mode _defaultMode;
    private QName _name;
    private Stylesheet _parentStylesheet;
    private String _systemId;
    private String _version;
    private List<VariableBase> _globals = new ArrayList();
    private Boolean _hasLocalParams = null;
    private final List<Template> _templates = new ArrayList();
    private List<Template> _allValidTemplates = null;
    private int _nextModeSerial = 1;
    private final Map<String, Mode> _modes = new HashMap();
    private final Map<String, String> _extensions = new HashMap();
    public Stylesheet _importedFrom = null;
    public Stylesheet _includedFrom = null;
    private List<Stylesheet> _includedStylesheets = null;
    private int _importPrecedence = 1;
    private int _minimumDescendantPrecedence = -1;
    private Map<String, Key> _keys = new HashMap();
    private SourceLoader _loader = null;
    private boolean _numberFormattingUsed = false;
    private boolean _simplified = false;
    private boolean _multiDocument = false;
    private boolean _callsNodeset = false;
    private boolean _hasIdCall = false;
    private boolean _templateInlining = false;
    private Output _lastOutputElement = null;
    private Properties _outputProperties = null;
    private int _outputMethod = 0;

    private void addDOMField(ClassGenerator classGenerator) {
        classGenerator.addField(new FieldGen(1, Util.getJCRefType(Constants.DOM_INTF_SIG), Constants.DOM_FIELD, classGenerator.getConstantPool()).getField());
    }

    private void addStaticField(ClassGenerator classGenerator, String str, String str2) {
        classGenerator.addField(new FieldGen(12, Util.getJCRefType(str), str2, classGenerator.getConstantPool()).getField());
    }

    private void checkOutputMethod() {
        String outputMethod;
        Output output = this._lastOutputElement;
        if (output == null || (outputMethod = output.getOutputMethod()) == null) {
            return;
        }
        if (outputMethod.equals("xml")) {
            this._outputMethod = 1;
        } else if (outputMethod.equals("html")) {
            this._outputMethod = 2;
        } else if (outputMethod.equals("text")) {
            this._outputMethod = 3;
        }
    }

    private String compileBuildKeys(ClassGenerator classGenerator) {
        classGenerator.getConstantPool();
        Type[] typeArr = {Util.getJCRefType(Constants.DOM_INTF_SIG), Util.getJCRefType("Lcom/sun/org/apache/xml/internal/dtm/DTMAxisIterator;"), Util.getJCRefType("Lcom/sun/org/apache/xml/internal/serializer/SerializationHandler;"), Type.INT};
        String[] strArr = {Constants.DOCUMENT_PNAME, Constants.ITERATOR_PNAME, Constants.TRANSLET_OUTPUT_PNAME, Keywords.FUNC_CURRENT_STRING};
        InstructionList instructionList = new InstructionList();
        MethodGenerator methodGenerator = new MethodGenerator(1, Type.VOID, typeArr, strArr, "buildKeys", this._className, instructionList, classGenerator.getConstantPool());
        methodGenerator.addException("com.sun.org.apache.xalan.internal.xsltc.TransletException");
        Iterator<SyntaxTreeNode> itElements = elements();
        while (itElements.hasNext()) {
            SyntaxTreeNode next = itElements.next();
            if (next instanceof Key) {
                Key key = (Key) next;
                key.translate(classGenerator, methodGenerator);
                this._keys.put(key.getName(), key);
            }
        }
        instructionList.append(Constants.RETURN);
        methodGenerator.stripAttributes(true);
        methodGenerator.setMaxLocals();
        methodGenerator.setMaxStack();
        methodGenerator.removeNOPs();
        classGenerator.addMethod(methodGenerator.getMethod());
        return Constants.ATTR_SET_SIG;
    }

    private void compileConstructor(ClassGenerator classGenerator, Output output) {
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = new InstructionList();
        MethodGenerator methodGenerator = new MethodGenerator(1, Type.VOID, null, null, Const.CONSTRUCTOR_NAME, this._className, instructionList, constantPool);
        instructionList.append(classGenerator.loadTranslet());
        instructionList.append(new INVOKESPECIAL(constantPool.addMethodref(Constants.TRANSLET_CLASS, Const.CONSTRUCTOR_NAME, "()V")));
        methodGenerator.markChunkStart();
        instructionList.append(classGenerator.loadTranslet());
        instructionList.append(new GETSTATIC(constantPool.addFieldref(this._className, Constants.STATIC_NAMES_ARRAY_FIELD, "[Ljava/lang/String;")));
        instructionList.append(new PUTFIELD(constantPool.addFieldref(Constants.TRANSLET_CLASS, Constants.NAMES_INDEX, "[Ljava/lang/String;")));
        instructionList.append(classGenerator.loadTranslet());
        instructionList.append(new GETSTATIC(constantPool.addFieldref(this._className, Constants.STATIC_URIS_ARRAY_FIELD, "[Ljava/lang/String;")));
        instructionList.append(new PUTFIELD(constantPool.addFieldref(Constants.TRANSLET_CLASS, Constants.URIS_INDEX, "[Ljava/lang/String;")));
        methodGenerator.markChunkEnd();
        methodGenerator.markChunkStart();
        instructionList.append(classGenerator.loadTranslet());
        instructionList.append(new GETSTATIC(constantPool.addFieldref(this._className, Constants.STATIC_TYPES_ARRAY_FIELD, Constants.TYPES_INDEX_SIG)));
        instructionList.append(new PUTFIELD(constantPool.addFieldref(Constants.TRANSLET_CLASS, Constants.TYPES_INDEX, Constants.TYPES_INDEX_SIG)));
        methodGenerator.markChunkEnd();
        methodGenerator.markChunkStart();
        instructionList.append(classGenerator.loadTranslet());
        instructionList.append(new GETSTATIC(constantPool.addFieldref(this._className, Constants.STATIC_NAMESPACE_ARRAY_FIELD, "[Ljava/lang/String;")));
        instructionList.append(new PUTFIELD(constantPool.addFieldref(Constants.TRANSLET_CLASS, Constants.NAMESPACE_INDEX, "[Ljava/lang/String;")));
        methodGenerator.markChunkEnd();
        methodGenerator.markChunkStart();
        instructionList.append(classGenerator.loadTranslet());
        instructionList.append(new PUSH(constantPool, 101));
        instructionList.append(new PUTFIELD(constantPool.addFieldref(Constants.TRANSLET_CLASS, Constants.TRANSLET_VERSION_INDEX, "I")));
        methodGenerator.markChunkEnd();
        if (this._hasIdCall) {
            methodGenerator.markChunkStart();
            instructionList.append(classGenerator.loadTranslet());
            instructionList.append(new PUSH(constantPool, Boolean.TRUE));
            instructionList.append(new PUTFIELD(constantPool.addFieldref(Constants.TRANSLET_CLASS, Constants.HASIDCALL_INDEX, Constants.HASIDCALL_INDEX_SIG)));
            methodGenerator.markChunkEnd();
        }
        if (output != null) {
            methodGenerator.markChunkStart();
            output.translate(classGenerator, methodGenerator);
            methodGenerator.markChunkEnd();
        }
        if (this._numberFormattingUsed) {
            methodGenerator.markChunkStart();
            DecimalFormatting.translateDefaultDFS(classGenerator, methodGenerator);
            methodGenerator.markChunkEnd();
        }
        instructionList.append(Constants.RETURN);
        classGenerator.addMethod(methodGenerator);
    }

    private void compileModes(final ClassGenerator classGenerator) {
        this._defaultMode.compileApplyTemplates(classGenerator);
        this._modes.values().stream().forEach(new Consumer() { // from class: com.sun.org.apache.xalan.internal.xsltc.compiler.c
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((Mode) obj).compileApplyTemplates(classGenerator);
            }
        });
    }

    /* JADX WARN: Code duplicated, block: B:21:0x00b4  */
    /* JADX WARN: Code duplicated, block: B:22:0x00b7  */
    private void compileStaticInitializer(ClassGenerator classGenerator) {
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = new InstructionList();
        MethodGenerator methodGenerator = new MethodGenerator(9, Type.VOID, null, null, Const.STATIC_INITIALIZER_NAME, this._className, instructionList, constantPool);
        addStaticField(classGenerator, "[Ljava/lang/String;", Constants.STATIC_NAMES_ARRAY_FIELD);
        addStaticField(classGenerator, "[Ljava/lang/String;", Constants.STATIC_URIS_ARRAY_FIELD);
        addStaticField(classGenerator, Constants.TYPES_INDEX_SIG, Constants.STATIC_TYPES_ARRAY_FIELD);
        addStaticField(classGenerator, "[Ljava/lang/String;", Constants.STATIC_NAMESPACE_ARRAY_FIELD);
        int characterDataCount = getXSLTC().getCharacterDataCount();
        for (int i = 0; i < characterDataCount; i++) {
            addStaticField(classGenerator, Constants.STATIC_CHAR_DATA_FIELD_SIG, Constants.STATIC_CHAR_DATA_FIELD + i);
        }
        List<String> namesIndex = getXSLTC().getNamesIndex();
        int size = namesIndex.size();
        String[] strArr = new String[size];
        String[] strArr2 = new String[size];
        int[] iArr = new int[size];
        int i2 = 0;
        while (i2 < size) {
            List<String> list = namesIndex;
            String str = namesIndex.get(i2);
            int i3 = i2;
            int iLastIndexOf = str.lastIndexOf(58);
            if (iLastIndexOf > -1) {
                strArr2[i3] = str.substring(0, iLastIndexOf);
            }
            int i4 = iLastIndexOf + 1;
            MethodGenerator methodGenerator2 = methodGenerator;
            if (str.charAt(i4) == '@') {
                iArr[i3] = 2;
            } else {
                if (str.charAt(i4) == '?') {
                    iArr[i3] = 13;
                } else {
                    iArr[i3] = 1;
                }
                if (i4 == 0) {
                    strArr[i3] = str;
                } else {
                    strArr[i3] = str.substring(i4);
                }
                i2 = i3 + 1;
                namesIndex = list;
                methodGenerator = methodGenerator2;
            }
            i4 = iLastIndexOf + 2;
            if (i4 == 0) {
                strArr[i3] = str;
            } else {
                strArr[i3] = str.substring(i4);
            }
            i2 = i3 + 1;
            namesIndex = list;
            methodGenerator = methodGenerator2;
        }
        MethodGenerator methodGenerator3 = methodGenerator;
        methodGenerator3.markChunkStart();
        instructionList.append(new PUSH(constantPool, size));
        instructionList.append(new ANEWARRAY(constantPool.addClass("java.lang.String")));
        int iAddFieldref = constantPool.addFieldref(this._className, Constants.STATIC_NAMES_ARRAY_FIELD, "[Ljava/lang/String;");
        instructionList.append(new PUTSTATIC(iAddFieldref));
        methodGenerator3.markChunkEnd();
        for (int i5 = 0; i5 < size; i5++) {
            String str2 = strArr[i5];
            methodGenerator3.markChunkStart();
            instructionList.append(new GETSTATIC(iAddFieldref));
            instructionList.append(new PUSH(constantPool, i5));
            instructionList.append(new PUSH(constantPool, str2));
            instructionList.append(Constants.AASTORE);
            methodGenerator3.markChunkEnd();
        }
        methodGenerator3.markChunkStart();
        instructionList.append(new PUSH(constantPool, size));
        instructionList.append(new ANEWARRAY(constantPool.addClass("java.lang.String")));
        int iAddFieldref2 = constantPool.addFieldref(this._className, Constants.STATIC_URIS_ARRAY_FIELD, "[Ljava/lang/String;");
        instructionList.append(new PUTSTATIC(iAddFieldref2));
        methodGenerator3.markChunkEnd();
        for (int i6 = 0; i6 < size; i6++) {
            String str3 = strArr2[i6];
            methodGenerator3.markChunkStart();
            instructionList.append(new GETSTATIC(iAddFieldref2));
            instructionList.append(new PUSH(constantPool, i6));
            instructionList.append(new PUSH(constantPool, str3));
            instructionList.append(Constants.AASTORE);
            methodGenerator3.markChunkEnd();
        }
        methodGenerator3.markChunkStart();
        instructionList.append(new PUSH(constantPool, size));
        instructionList.append(new NEWARRAY(Type.INT));
        int iAddFieldref3 = constantPool.addFieldref(this._className, Constants.STATIC_TYPES_ARRAY_FIELD, Constants.TYPES_INDEX_SIG);
        instructionList.append(new PUTSTATIC(iAddFieldref3));
        methodGenerator3.markChunkEnd();
        for (int i7 = 0; i7 < size; i7++) {
            int i8 = iArr[i7];
            methodGenerator3.markChunkStart();
            instructionList.append(new GETSTATIC(iAddFieldref3));
            instructionList.append(new PUSH(constantPool, i7));
            instructionList.append(new PUSH(constantPool, i8));
            instructionList.append(Constants.IASTORE);
        }
        List<String> namespaceIndex = getXSLTC().getNamespaceIndex();
        methodGenerator3.markChunkStart();
        instructionList.append(new PUSH(constantPool, namespaceIndex.size()));
        instructionList.append(new ANEWARRAY(constantPool.addClass("java.lang.String")));
        int iAddFieldref4 = constantPool.addFieldref(this._className, Constants.STATIC_NAMESPACE_ARRAY_FIELD, "[Ljava/lang/String;");
        instructionList.append(new PUTSTATIC(iAddFieldref4));
        methodGenerator3.markChunkEnd();
        for (int i9 = 0; i9 < namespaceIndex.size(); i9++) {
            String str4 = namespaceIndex.get(i9);
            methodGenerator3.markChunkStart();
            instructionList.append(new GETSTATIC(iAddFieldref4));
            instructionList.append(new PUSH(constantPool, i9));
            instructionList.append(new PUSH(constantPool, str4));
            instructionList.append(Constants.AASTORE);
            methodGenerator3.markChunkEnd();
        }
        int characterDataCount2 = getXSLTC().getCharacterDataCount();
        int iAddMethodref = constantPool.addMethodref("java.lang.String", "toCharArray", "()[C");
        for (int i10 = 0; i10 < characterDataCount2; i10++) {
            methodGenerator3.markChunkStart();
            instructionList.append(new PUSH(constantPool, getXSLTC().getCharacterData(i10)));
            instructionList.append(new INVOKEVIRTUAL(iAddMethodref));
            instructionList.append(new PUTSTATIC(constantPool.addFieldref(this._className, Constants.STATIC_CHAR_DATA_FIELD + i10, Constants.STATIC_CHAR_DATA_FIELD_SIG)));
            methodGenerator3.markChunkEnd();
        }
        instructionList.append(Constants.RETURN);
        classGenerator.addMethod(methodGenerator3);
    }

    private String compileTopLevel(ClassGenerator classGenerator) {
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        Type[] typeArr = {Util.getJCRefType(Constants.DOM_INTF_SIG), Util.getJCRefType("Lcom/sun/org/apache/xml/internal/dtm/DTMAxisIterator;"), Util.getJCRefType("Lcom/sun/org/apache/xml/internal/serializer/SerializationHandler;")};
        String[] strArr = {Constants.DOCUMENT_PNAME, Constants.ITERATOR_PNAME, Constants.TRANSLET_OUTPUT_PNAME};
        InstructionList instructionList = new InstructionList();
        MethodGenerator methodGenerator = new MethodGenerator(1, Type.VOID, typeArr, strArr, "topLevel", this._className, instructionList, classGenerator.getConstantPool());
        methodGenerator.addException("com.sun.org.apache.xalan.internal.xsltc.TransletException");
        LocalVariableGen localVariableGenAddLocalVariable = methodGenerator.addLocalVariable(Keywords.FUNC_CURRENT_STRING, Type.INT, null, null);
        int iAddInterfaceMethodref = constantPool.addInterfaceMethodref(Constants.DOM_INTF, "setFilter", "(Lcom/sun/org/apache/xalan/internal/xsltc/StripFilter;)V");
        int iAddInterfaceMethodref2 = constantPool.addInterfaceMethodref(Constants.DOM_INTF, "getIterator", "()Lcom/sun/org/apache/xml/internal/dtm/DTMAxisIterator;");
        instructionList.append(methodGenerator.loadDOM());
        instructionList.append(new INVOKEINTERFACE(iAddInterfaceMethodref2, 1));
        instructionList.append(methodGenerator.nextNode());
        localVariableGenAddLocalVariable.setStart(instructionList.append(new ISTORE(localVariableGenAddLocalVariable.getIndex())));
        ArrayList arrayList = new ArrayList(this._globals);
        Iterator<SyntaxTreeNode> itElements = elements();
        while (itElements.hasNext()) {
            SyntaxTreeNode next = itElements.next();
            if (next instanceof Key) {
                arrayList.add(next);
            }
        }
        List<SyntaxTreeNode> listResolveDependencies = resolveDependencies(arrayList);
        int size = listResolveDependencies.size();
        for (int i = 0; i < size; i++) {
            TopLevelElement topLevelElement = (TopLevelElement) listResolveDependencies.get(i);
            topLevelElement.translate(classGenerator, methodGenerator);
            if (topLevelElement instanceof Key) {
                Key key = (Key) topLevelElement;
                this._keys.put(key.getName(), key);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator<SyntaxTreeNode> itElements2 = elements();
        while (itElements2.hasNext()) {
            SyntaxTreeNode next2 = itElements2.next();
            if (next2 instanceof DecimalFormatting) {
                ((DecimalFormatting) next2).translate(classGenerator, methodGenerator);
            } else if (next2 instanceof Whitespace) {
                arrayList2.addAll(((Whitespace) next2).getRules());
            }
        }
        if (arrayList2.size() > 0) {
            Whitespace.translateRules(arrayList2, classGenerator);
        }
        if (classGenerator.containsMethod(Constants.STRIP_SPACE, Constants.STRIP_SPACE_PARAMS) != null) {
            instructionList.append(methodGenerator.loadDOM());
            instructionList.append(classGenerator.loadTranslet());
            instructionList.append(new INVOKEINTERFACE(iAddInterfaceMethodref, 2));
        }
        instructionList.append(Constants.RETURN);
        classGenerator.addMethod(methodGenerator);
        return "(Lcom/sun/org/apache/xalan/internal/xsltc/DOM;Lcom/sun/org/apache/xml/internal/dtm/DTMAxisIterator;Lcom/sun/org/apache/xml/internal/serializer/SerializationHandler;)V";
    }

    private void compileTransform(ClassGenerator classGenerator) {
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        Type[] typeArr = {Util.getJCRefType(Constants.DOM_INTF_SIG), Util.getJCRefType("Lcom/sun/org/apache/xml/internal/dtm/DTMAxisIterator;"), Util.getJCRefType("Lcom/sun/org/apache/xml/internal/serializer/SerializationHandler;")};
        String[] strArr = {Constants.DOCUMENT_PNAME, Constants.ITERATOR_PNAME, Constants.TRANSLET_OUTPUT_PNAME};
        InstructionList instructionList = new InstructionList();
        MethodGenerator methodGenerator = new MethodGenerator(1, Type.VOID, typeArr, strArr, com.sun.org.apache.xalan.internal.templates.Constants.ELEMNAME_TRANSFORM_STRING, this._className, instructionList, classGenerator.getConstantPool());
        methodGenerator.addException("com.sun.org.apache.xalan.internal.xsltc.TransletException");
        instructionList.append(new INVOKESTATIC(constantPool.addMethodref(Constants.BASIS_LIBRARY_CLASS, "resetPrefixIndex", "()V")));
        LocalVariableGen localVariableGenAddLocalVariable = methodGenerator.addLocalVariable(Keywords.FUNC_CURRENT_STRING, Type.INT, null, null);
        int iAddMethodref = constantPool.addMethodref(getClassName(), Constants.APPLY_TEMPLATES, classGenerator.getApplyTemplatesSig());
        int iAddFieldref = constantPool.addFieldref(getClassName(), Constants.DOM_FIELD, Constants.DOM_INTF_SIG);
        instructionList.append(classGenerator.loadTranslet());
        if (isMultiDocument()) {
            instructionList.append(new NEW(constantPool.addClass(Constants.MULTI_DOM_CLASS)));
            instructionList.append(Constants.DUP);
        }
        instructionList.append(classGenerator.loadTranslet());
        instructionList.append(methodGenerator.loadDOM());
        instructionList.append(new INVOKEVIRTUAL(constantPool.addMethodref(Constants.TRANSLET_CLASS, "makeDOMAdapter", "(Lcom/sun/org/apache/xalan/internal/xsltc/DOM;)Lcom/sun/org/apache/xalan/internal/xsltc/dom/DOMAdapter;")));
        if (isMultiDocument()) {
            instructionList.append(new INVOKESPECIAL(constantPool.addMethodref(Constants.MULTI_DOM_CLASS, Const.CONSTRUCTOR_NAME, "(Lcom/sun/org/apache/xalan/internal/xsltc/DOM;)V")));
        }
        instructionList.append(new PUTFIELD(iAddFieldref));
        int iAddInterfaceMethodref = constantPool.addInterfaceMethodref(Constants.DOM_INTF, "getIterator", "()Lcom/sun/org/apache/xml/internal/dtm/DTMAxisIterator;");
        instructionList.append(methodGenerator.loadDOM());
        instructionList.append(new INVOKEINTERFACE(iAddInterfaceMethodref, 1));
        instructionList.append(methodGenerator.nextNode());
        localVariableGenAddLocalVariable.setStart(instructionList.append(new ISTORE(localVariableGenAddLocalVariable.getIndex())));
        instructionList.append(classGenerator.loadTranslet());
        instructionList.append(methodGenerator.loadHandler());
        instructionList.append(new INVOKEVIRTUAL(constantPool.addMethodref(Constants.TRANSLET_CLASS, "transferOutputSettings", "(Lcom/sun/org/apache/xml/internal/serializer/SerializationHandler;)V")));
        constantPool.addMethodref(getClassName(), "buildKeys", compileBuildKeys(classGenerator));
        Iterator<SyntaxTreeNode> itElements = elements();
        if (this._globals.size() > 0 || itElements.hasNext()) {
            int iAddMethodref2 = constantPool.addMethodref(getClassName(), "topLevel", compileTopLevel(classGenerator));
            instructionList.append(classGenerator.loadTranslet());
            instructionList.append(classGenerator.loadTranslet());
            instructionList.append(new GETFIELD(iAddFieldref));
            instructionList.append(methodGenerator.loadIterator());
            instructionList.append(methodGenerator.loadHandler());
            instructionList.append(new INVOKEVIRTUAL(iAddMethodref2));
        }
        instructionList.append(methodGenerator.loadHandler());
        instructionList.append(methodGenerator.startDocument());
        instructionList.append(classGenerator.loadTranslet());
        instructionList.append(classGenerator.loadTranslet());
        instructionList.append(new GETFIELD(iAddFieldref));
        instructionList.append(methodGenerator.loadIterator());
        instructionList.append(methodGenerator.loadHandler());
        instructionList.append(new INVOKEVIRTUAL(iAddMethodref));
        instructionList.append(methodGenerator.loadHandler());
        instructionList.append(methodGenerator.endDocument());
        instructionList.append(Constants.RETURN);
        classGenerator.addMethod(methodGenerator);
    }

    private void extensionURI(String str, SymbolTable symbolTable) {
        if (str != null) {
            StringTokenizer stringTokenizer = new StringTokenizer(str);
            while (stringTokenizer.hasMoreTokens()) {
                String strNextToken = stringTokenizer.nextToken();
                String strLookupNamespace = lookupNamespace(strNextToken);
                if (strLookupNamespace != null) {
                    this._extensions.put(strLookupNamespace, strNextToken);
                }
            }
        }
    }

    private QName makeStylesheetName(String str) {
        return getParser().getQName(str + getXSLTC().nextStylesheetSerial());
    }

    private void peepHoleOptimization(MethodGenerator methodGenerator) {
        InstructionList instructionList = methodGenerator.getInstructionList();
        Iterator<InstructionHandle[]> itSearch = new InstructionFinder(instructionList).search("`aload'`pop'`instruction'");
        while (itSearch.hasNext()) {
            InstructionHandle[] next = itSearch.next();
            try {
                instructionList.delete(next[0], next[1]);
            } catch (TargetLostException unused) {
            }
        }
    }

    private List<SyntaxTreeNode> resolveDependencies(List<SyntaxTreeNode> list) {
        ArrayList arrayList = new ArrayList();
        while (list.size() > 0) {
            int i = 0;
            boolean z = false;
            while (i < list.size()) {
                TopLevelElement topLevelElement = (TopLevelElement) list.get(i);
                List<SyntaxTreeNode> dependencies = topLevelElement.getDependencies();
                if (dependencies == null || arrayList.containsAll(dependencies)) {
                    arrayList.add(topLevelElement);
                    list.remove(i);
                    z = true;
                } else {
                    i++;
                }
            }
            if (!z) {
                getParser().reportError(3, new ErrorMsg(ErrorMsg.CIRCULAR_VARIABLE_ERR, (Object) list.toString(), (SyntaxTreeNode) this));
                break;
            }
        }
        return arrayList;
    }

    public void addIncludedStylesheet(Stylesheet stylesheet) {
        if (this._includedStylesheets == null) {
            this._includedStylesheets = new ArrayList();
        }
        this._includedStylesheets.add(stylesheet);
    }

    public int addParam(Param param) {
        this._globals.add(param);
        return this._globals.size() - 1;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void addPrefixMapping(String str, String str2) {
        if (str.equals("") && str2.equals("http://www.w3.org/1999/xhtml")) {
            return;
        }
        super.addPrefixMapping(str, str2);
    }

    public void addTemplate(Template template) {
        this._templates.add(template);
    }

    public int addVariable(Variable variable) {
        this._globals.add(variable);
        return this._globals.size() - 1;
    }

    public boolean callsNodeset() {
        return this._callsNodeset;
    }

    public boolean checkForLoop(String str) {
        String str2 = this._systemId;
        if (str2 != null && str2.equals(str)) {
            return true;
        }
        Stylesheet stylesheet = this._parentStylesheet;
        if (stylesheet != null) {
            return stylesheet.checkForLoop(str);
        }
        return false;
    }

    public void declareExtensionPrefixes(Parser parser) {
        extensionURI(getAttribute(com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_EXTENSIONELEMENTPREFIXES), parser.getSymbolTable());
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void display(int i) {
        indent(i);
        Util.println("Stylesheet");
        displayContents(i + 4);
    }

    public List<Template> getAllValidTemplates() {
        if (this._includedStylesheets == null) {
            return this._templates;
        }
        if (this._allValidTemplates == null) {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(this._templates);
            Iterator<Stylesheet> it = this._includedStylesheets.iterator();
            while (it.hasNext()) {
                arrayList.addAll(it.next().getAllValidTemplates());
            }
            if (this._parentStylesheet != null) {
                return arrayList;
            }
            this._allValidTemplates = arrayList;
        }
        return this._allValidTemplates;
    }

    public String getClassName() {
        return this._className;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public int getImportPrecedence() {
        return this._importPrecedence;
    }

    public Output getLastOutputElement() {
        return this._lastOutputElement;
    }

    public int getMinimumDescendantPrecedence() {
        if (this._minimumDescendantPrecedence == -1) {
            int importPrecedence = getImportPrecedence();
            List<Stylesheet> list = this._includedStylesheets;
            int size = list != null ? list.size() : 0;
            for (int i = 0; i < size; i++) {
                int minimumDescendantPrecedence = this._includedStylesheets.get(i).getMinimumDescendantPrecedence();
                if (minimumDescendantPrecedence < importPrecedence) {
                    importPrecedence = minimumDescendantPrecedence;
                }
            }
            this._minimumDescendantPrecedence = importPrecedence;
        }
        return this._minimumDescendantPrecedence;
    }

    public Mode getMode(QName qName) {
        if (qName == null) {
            if (this._defaultMode == null) {
                this._defaultMode = new Mode(null, this, "");
            }
            return this._defaultMode;
        }
        Mode mode = this._modes.get(qName.getStringRep());
        if (mode != null) {
            return mode;
        }
        int i = this._nextModeSerial;
        this._nextModeSerial = i + 1;
        String string = Integer.toString(i);
        Map<String, Mode> map = this._modes;
        String stringRep = qName.getStringRep();
        Mode mode2 = new Mode(qName, this, string);
        map.put(stringRep, mode2);
        return mode2;
    }

    public String getNamespace(String str) {
        return lookupNamespace(str);
    }

    public int getOutputMethod() {
        return this._outputMethod;
    }

    public Properties getOutputProperties() {
        return this._outputProperties;
    }

    public Stylesheet getParentStylesheet() {
        return this._parentStylesheet;
    }

    public SourceLoader getSourceLoader() {
        return this._loader;
    }

    public String getSystemId() {
        return this._systemId;
    }

    public boolean getTemplateInlining() {
        return this._templateInlining;
    }

    public List<Template> getTemplates() {
        return this._templates;
    }

    public boolean hasGlobals() {
        return this._globals.size() > 0;
    }

    public boolean hasLocalParams() {
        Boolean bool = this._hasLocalParams;
        if (bool != null) {
            return bool.booleanValue();
        }
        List<Template> allValidTemplates = getAllValidTemplates();
        int size = allValidTemplates.size();
        for (int i = 0; i < size; i++) {
            if (allValidTemplates.get(i).hasParams()) {
                this._hasLocalParams = Boolean.TRUE;
                return true;
            }
        }
        this._hasLocalParams = Boolean.FALSE;
        return false;
    }

    public boolean isExtension(String str) {
        return this._extensions.get(str) != null;
    }

    public boolean isMultiDocument() {
        return this._multiDocument;
    }

    public boolean isSimplified() {
        return this._simplified;
    }

    public void numberFormattingUsed() {
        this._numberFormattingUsed = true;
        Stylesheet parentStylesheet = getParentStylesheet();
        if (parentStylesheet != null) {
            parentStylesheet.numberFormattingUsed();
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void parseContents(Parser parser) {
        SymbolTable symbolTable = parser.getSymbolTable();
        addPrefixMapping("xml", "http://www.w3.org/XML/1998/namespace");
        if (symbolTable.addStylesheet(this._name, this) != null) {
            parser.reportError(3, new ErrorMsg(ErrorMsg.MULTIPLE_STYLESHEET_ERR, (SyntaxTreeNode) this));
        }
        if (!this._simplified) {
            parseOwnChildren(parser);
        } else {
            symbolTable.excludeURI("http://www.w3.org/1999/XSL/Transform");
            new Template().parseSimplified(this, parser);
        }
    }

    public final void parseOwnChildren(Parser parser) {
        SymbolTable symbolTable = parser.getSymbolTable();
        String attribute = getAttribute(com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_EXCLUDE_RESULT_PREFIXES);
        String attribute2 = getAttribute(com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_EXTENSIONELEMENTPREFIXES);
        symbolTable.pushExcludedNamespacesContext();
        symbolTable.excludeURI("http://www.w3.org/1999/XSL/Transform");
        symbolTable.excludeNamespaces(attribute);
        symbolTable.excludeNamespaces(attribute2);
        List<SyntaxTreeNode> contents = getContents();
        int size = contents.size();
        for (int i = 0; i < size; i++) {
            SyntaxTreeNode syntaxTreeNode = contents.get(i);
            if ((syntaxTreeNode instanceof VariableBase) || (syntaxTreeNode instanceof NamespaceAlias)) {
                parser.getSymbolTable().setCurrentNode(syntaxTreeNode);
                syntaxTreeNode.parseContents(parser);
            }
        }
        for (int i2 = 0; i2 < size; i2++) {
            SyntaxTreeNode syntaxTreeNode2 = contents.get(i2);
            if (!(syntaxTreeNode2 instanceof VariableBase) && !(syntaxTreeNode2 instanceof NamespaceAlias)) {
                parser.getSymbolTable().setCurrentNode(syntaxTreeNode2);
                syntaxTreeNode2.parseContents(parser);
            }
            if (!this._templateInlining && (syntaxTreeNode2 instanceof Template)) {
                Template template = (Template) syntaxTreeNode2;
                template.setName(parser.getQName("template$dot$" + template.getPosition()));
            }
        }
        symbolTable.popExcludedNamespacesContext();
    }

    public void processModes() {
        if (this._defaultMode == null) {
            this._defaultMode = new Mode(null, this, "");
        }
        this._defaultMode.processPatterns(this._keys);
        this._modes.values().stream().forEach(new Consumer() { // from class: com.sun.org.apache.xalan.internal.xsltc.compiler.d
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((Mode) obj).processPatterns(this.b._keys);
            }
        });
    }

    public void setCallsNodeset(boolean z) {
        if (z) {
            setMultiDocument(z);
        }
        this._callsNodeset = z;
    }

    public void setHasIdCall(boolean z) {
        this._hasIdCall = z;
    }

    public void setImportPrecedence(int i) {
        Stylesheet includedStylesheet;
        this._importPrecedence = i;
        Iterator<SyntaxTreeNode> itElements = elements();
        while (itElements.hasNext()) {
            SyntaxTreeNode next = itElements.next();
            if ((next instanceof Include) && (includedStylesheet = ((Include) next).getIncludedStylesheet()) != null && includedStylesheet._includedFrom == this) {
                includedStylesheet.setImportPrecedence(i);
            }
        }
        Stylesheet stylesheet = this._importedFrom;
        if (stylesheet != null) {
            if (stylesheet.getImportPrecedence() < i) {
                this._importedFrom.setImportPrecedence(getParser().getNextImportPrecedence());
            }
        } else {
            Stylesheet stylesheet2 = this._includedFrom;
            if (stylesheet2 == null || stylesheet2.getImportPrecedence() == i) {
                return;
            }
            this._includedFrom.setImportPrecedence(i);
        }
    }

    public void setImportingStylesheet(Stylesheet stylesheet) {
        this._importedFrom = stylesheet;
        stylesheet.addIncludedStylesheet(this);
    }

    public void setIncludingStylesheet(Stylesheet stylesheet) {
        this._includedFrom = stylesheet;
        stylesheet.addIncludedStylesheet(this);
    }

    public void setMultiDocument(boolean z) {
        this._multiDocument = z;
    }

    public void setOutputProperties(Properties properties) {
        this._outputProperties = properties;
    }

    public void setOutputProperty(String str, String str2) {
        if (this._outputProperties == null) {
            this._outputProperties = new Properties();
        }
        this._outputProperties.setProperty(str, str2);
    }

    public void setParentStylesheet(Stylesheet stylesheet) {
        this._parentStylesheet = stylesheet;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void setParser(Parser parser) {
        super.setParser(parser);
        this._name = makeStylesheetName("__stylesheet_");
    }

    public void setSimplified() {
        this._simplified = true;
    }

    public void setSourceLoader(SourceLoader sourceLoader) {
        this._loader = sourceLoader;
    }

    public void setSystemId(String str) {
        if (str != null) {
            this._systemId = SystemIDResolver.getAbsoluteURI(str);
        }
    }

    public void setTemplateInlining(boolean z) {
        this._templateInlining = z;
    }

    public void translate() {
        this._className = getXSLTC().getClassName();
        ClassGenerator classGenerator = new ClassGenerator(this._className, Constants.TRANSLET_CLASS, "", 33, null, this);
        addDOMField(classGenerator);
        compileTransform(classGenerator);
        Iterator<SyntaxTreeNode> itElements = elements();
        while (itElements.hasNext()) {
            SyntaxTreeNode next = itElements.next();
            if (next instanceof Template) {
                Template template = (Template) next;
                getMode(template.getModeName()).addTemplate(template);
            } else if (next instanceof AttributeSet) {
                ((AttributeSet) next).translate(classGenerator, null);
            } else if (next instanceof Output) {
                Output output = (Output) next;
                if (output.enabled()) {
                    this._lastOutputElement = output;
                }
            }
        }
        checkOutputMethod();
        processModes();
        compileModes(classGenerator);
        compileStaticInitializer(classGenerator);
        compileConstructor(classGenerator, this._lastOutputElement);
        if (getParser().errorsFound()) {
            return;
        }
        getXSLTC().dumpClass(classGenerator.getJavaClass());
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type typeCheck(SymbolTable symbolTable) throws TypeCheckError {
        int size = this._globals.size();
        for (int i = 0; i < size; i++) {
            this._globals.get(i).typeCheck(symbolTable);
        }
        return typeCheckContents(symbolTable);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void translate(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        translate();
    }
}
