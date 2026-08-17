package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.intellij.psi.PsiKeyword;
import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.bcel.internal.generic.BranchInstruction;
import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.IFEQ;
import com.sun.org.apache.bcel.internal.generic.INVOKEINTERFACE;
import com.sun.org.apache.bcel.internal.generic.INVOKESPECIAL;
import com.sun.org.apache.bcel.internal.generic.INVOKESTATIC;
import com.sun.org.apache.bcel.internal.generic.INVOKEVIRTUAL;
import com.sun.org.apache.bcel.internal.generic.InstructionConst;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.bcel.internal.generic.LDC;
import com.sun.org.apache.bcel.internal.generic.LocalVariableGen;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.bcel.internal.generic.PUSH;
import com.sun.org.apache.xalan.internal.utils.ObjectFactory;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.BooleanType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.IntType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MultiHashtable;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ObjectType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ReferenceType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import com.sun.org.apache.xml.internal.utils.LocaleUtility;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import jdk.xml.internal.JdkXmlFeatures;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
class FunctionCall extends Expression {
    private static final List<Expression> EMPTY_ARG_LIST = new ArrayList(0);
    protected static final String EXSLT_COMMON = "http://exslt.org/common";
    protected static final String EXSLT_DATETIME = "http://exslt.org/dates-and-times";
    protected static final String EXSLT_MATH = "http://exslt.org/math";
    protected static final String EXSLT_SETS = "http://exslt.org/sets";
    protected static final String EXSLT_STRINGS = "http://exslt.org/strings";
    private static final Map<String, String> EXTENSIONFUNCTION;
    private static final Map<String, String> EXTENSIONNAMESPACE;
    protected static final String EXT_XALAN = "http://xml.apache.org/xalan";
    protected static final String EXT_XSLTC = "http://xml.apache.org/xalan/xsltc";
    private static final Map<Class<?>, Type> JAVA2INTERNAL;
    protected static final String JAVA_EXT_XALAN = "http://xml.apache.org/xalan/java";
    protected static final String JAVA_EXT_XALAN_OLD = "http://xml.apache.org/xslt/java";
    protected static final String JAVA_EXT_XSLTC = "http://xml.apache.org/xalan/xsltc/java";
    protected static final int NAMESPACE_FORMAT_CLASS = 1;
    protected static final int NAMESPACE_FORMAT_CLASS_OR_PACKAGE = 3;
    protected static final int NAMESPACE_FORMAT_JAVA = 0;
    protected static final int NAMESPACE_FORMAT_PACKAGE = 2;
    protected static final String XALAN_CLASSPACKAGE_NAMESPACE = "xalan://";
    private static final MultiHashtable<Type, JavaType> _internal2Java;
    private final List<Expression> _arguments;
    private Constructor<?> _chosenConstructor;
    private Method _chosenMethod;
    private MethodType _chosenMethodType;
    private String _className;
    private Class<?> _clazz;
    private QName _fname;
    private boolean _isExtConstructor;
    private boolean _isStatic;
    private int _namespace_format;
    Expression _thisArgument;
    private boolean unresolvedExternal;

    public static class JavaType {
        public int distance;
        public Class<?> type;

        public JavaType(Class<?> cls, int i) {
            this.type = cls;
            this.distance = i;
        }

        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            return obj.getClass().isAssignableFrom(JavaType.class) ? ((JavaType) obj).type.equals(this.type) : obj.equals(this.type);
        }

        public int hashCode() {
            return Objects.hashCode(this.type);
        }
    }

    static {
        MultiHashtable<Type, JavaType> multiHashtable = new MultiHashtable<>();
        _internal2Java = multiHashtable;
        Type type = Type.Boolean;
        Class cls = Boolean.TYPE;
        multiHashtable.put(type, new JavaType(cls, 0));
        multiHashtable.put(type, new JavaType(Boolean.class, 1));
        multiHashtable.put(type, new JavaType(Object.class, 2));
        Type type2 = Type.Real;
        Class cls2 = Double.TYPE;
        multiHashtable.put(type2, new JavaType(cls2, 0));
        multiHashtable.put(type2, new JavaType(Double.class, 1));
        Class cls3 = Float.TYPE;
        multiHashtable.put(type2, new JavaType(cls3, 2));
        Class cls4 = Long.TYPE;
        multiHashtable.put(type2, new JavaType(cls4, 3));
        Class cls5 = Integer.TYPE;
        multiHashtable.put(type2, new JavaType(cls5, 4));
        Class cls6 = Short.TYPE;
        multiHashtable.put(type2, new JavaType(cls6, 5));
        Class cls7 = Byte.TYPE;
        multiHashtable.put(type2, new JavaType(cls7, 6));
        Class cls8 = Character.TYPE;
        multiHashtable.put(type2, new JavaType(cls8, 7));
        multiHashtable.put(type2, new JavaType(Object.class, 8));
        Type type3 = Type.Int;
        multiHashtable.put(type3, new JavaType(cls2, 0));
        multiHashtable.put(type3, new JavaType(Double.class, 1));
        multiHashtable.put(type3, new JavaType(cls3, 2));
        multiHashtable.put(type3, new JavaType(cls4, 3));
        multiHashtable.put(type3, new JavaType(cls5, 4));
        multiHashtable.put(type3, new JavaType(cls6, 5));
        multiHashtable.put(type3, new JavaType(cls7, 6));
        multiHashtable.put(type3, new JavaType(cls8, 7));
        multiHashtable.put(type3, new JavaType(Object.class, 8));
        Type type4 = Type.String;
        multiHashtable.put(type4, new JavaType(String.class, 0));
        multiHashtable.put(type4, new JavaType(Object.class, 1));
        Type type5 = Type.NodeSet;
        multiHashtable.put(type5, new JavaType(NodeList.class, 0));
        multiHashtable.put(type5, new JavaType(Node.class, 1));
        multiHashtable.put(type5, new JavaType(Object.class, 2));
        multiHashtable.put(type5, new JavaType(String.class, 3));
        Type type6 = Type.Node;
        multiHashtable.put(type6, new JavaType(NodeList.class, 0));
        multiHashtable.put(type6, new JavaType(Node.class, 1));
        multiHashtable.put(type6, new JavaType(Object.class, 2));
        multiHashtable.put(type6, new JavaType(String.class, 3));
        Type type7 = Type.ResultTree;
        multiHashtable.put(type7, new JavaType(NodeList.class, 0));
        multiHashtable.put(type7, new JavaType(Node.class, 1));
        multiHashtable.put(type7, new JavaType(Object.class, 2));
        multiHashtable.put(type7, new JavaType(String.class, 3));
        Type type8 = Type.Reference;
        multiHashtable.put(type8, new JavaType(Object.class, 0));
        multiHashtable.makeUnmodifiable();
        HashMap map = new HashMap();
        HashMap map2 = new HashMap();
        HashMap map3 = new HashMap();
        map.put(cls, type);
        map.put(Void.TYPE, Type.Void);
        map.put(cls8, type2);
        map.put(cls7, type2);
        map.put(cls6, type2);
        map.put(cls5, type2);
        map.put(cls4, type2);
        map.put(cls3, type2);
        map.put(cls2, type2);
        map.put(String.class, type4);
        map.put(Object.class, type8);
        map.put(NodeList.class, type5);
        map.put(Node.class, type5);
        map2.put("http://xml.apache.org/xalan", "com.sun.org.apache.xalan.internal.lib.Extensions");
        map2.put("http://exslt.org/common", "com.sun.org.apache.xalan.internal.lib.ExsltCommon");
        map2.put("http://exslt.org/math", "com.sun.org.apache.xalan.internal.lib.ExsltMath");
        map2.put("http://exslt.org/sets", "com.sun.org.apache.xalan.internal.lib.ExsltSets");
        map2.put("http://exslt.org/dates-and-times", "com.sun.org.apache.xalan.internal.lib.ExsltDatetime");
        map2.put("http://exslt.org/strings", "com.sun.org.apache.xalan.internal.lib.ExsltStrings");
        map3.put("http://exslt.org/common:nodeSet", "nodeset");
        map3.put("http://exslt.org/common:objectType", "objectType");
        map3.put("http://xml.apache.org/xalan:nodeset", "nodeset");
        JAVA2INTERNAL = Collections.unmodifiableMap(map);
        EXTENSIONNAMESPACE = Collections.unmodifiableMap(map2);
        EXTENSIONFUNCTION = Collections.unmodifiableMap(map3);
    }

    public FunctionCall(QName qName, List<Expression> list) {
        this._namespace_format = 0;
        this._thisArgument = null;
        this._isExtConstructor = false;
        this._isStatic = false;
        this._fname = qName;
        this._arguments = list;
        this._type = null;
    }

    private List<Constructor<?>> findConstructors() {
        int size = this._arguments.size();
        ArrayList arrayList = null;
        try {
            if (this._clazz == null) {
                Class<?> clsFindProviderClass = ObjectFactory.findProviderClass(this._className, true);
                this._clazz = clsFindProviderClass;
                if (clsFindProviderClass == null) {
                    getParser().reportError(3, new ErrorMsg(ErrorMsg.CLASS_NOT_FOUND_ERR, this._className));
                }
            }
            for (Constructor<?> constructor : this._clazz.getConstructors()) {
                if (Modifier.isPublic(constructor.getModifiers()) && constructor.getParameterTypes().length == size) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(constructor);
                }
            }
            return arrayList;
        } catch (ClassNotFoundException unused) {
            getParser().reportError(3, new ErrorMsg(ErrorMsg.CLASS_NOT_FOUND_ERR, this._className));
            return arrayList;
        }
    }

    private List<Method> findMethods() {
        String namespace = this._fname.getNamespace();
        String str = this._className;
        ArrayList arrayList = null;
        if (str != null && str.length() > 0) {
            int size = this._arguments.size();
            try {
                if (this._clazz == null) {
                    boolean zIsSecureProcessing = getXSLTC().isSecureProcessing();
                    boolean feature = getXSLTC().getFeature(JdkXmlFeatures.XmlFeature.ENABLE_EXTENSION_FUNCTION);
                    if (namespace != null && zIsSecureProcessing && feature && (namespace.startsWith("http://xml.apache.org/xalan/java") || namespace.startsWith(JAVA_EXT_XSLTC) || namespace.startsWith("http://xml.apache.org/xslt/java") || namespace.startsWith(XALAN_CLASSPACKAGE_NAMESPACE))) {
                        this._clazz = getXSLTC().loadExternalFunction(this._className);
                    } else {
                        this._clazz = ObjectFactory.findProviderClass(this._className, true);
                    }
                    if (this._clazz == null) {
                        getParser().reportError(3, new ErrorMsg(ErrorMsg.CLASS_NOT_FOUND_ERR, this._className));
                    }
                }
                String localPart = this._fname.getLocalPart();
                Method[] methods = this._clazz.getMethods();
                for (int i = 0; i < methods.length; i++) {
                    if (Modifier.isPublic(methods[i].getModifiers()) && methods[i].getName().equals(localPart) && methods[i].getParameterTypes().length == size) {
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                        }
                        arrayList.add(methods[i]);
                    }
                }
                return arrayList;
            } catch (ClassNotFoundException unused) {
                getParser().reportError(3, new ErrorMsg(ErrorMsg.CLASS_NOT_FOUND_ERR, this._className));
            }
        }
        return arrayList;
    }

    private void generateAddReads(ClassGenerator classGenerator, MethodGenerator methodGenerator, String str) {
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        methodGenerator.markChunkStart();
        int iAddMethodref = constantPool.addMethodref(Constants.CLASS_CLASS, Constants.GET_MODULE, Constants.GET_MODULE_SIG);
        int iAddMethodref2 = constantPool.addMethodref(Constants.CLASS_CLASS, Constants.FOR_NAME, Constants.FOR_NAME_SIG);
        instructionList.append(new LDC(constantPool.addString(classGenerator.getClassName())));
        instructionList.append(new INVOKESTATIC(iAddMethodref2));
        instructionList.append(new INVOKEVIRTUAL(iAddMethodref));
        instructionList.append(new LDC(constantPool.addString(str)));
        instructionList.append(new INVOKESTATIC(iAddMethodref2));
        instructionList.append(new INVOKEVIRTUAL(iAddMethodref));
        instructionList.append(new INVOKEVIRTUAL(constantPool.addMethodref(Constants.MODULE_CLASS, Constants.ADD_READS, Constants.ADD_READS_SIG)));
        instructionList.append(InstructionConst.POP);
        methodGenerator.markChunkEnd();
    }

    private String getMethodSignature(List<Type> list) {
        StringBuffer stringBuffer = new StringBuffer(this._className);
        stringBuffer.append('.');
        stringBuffer.append(this._fname.getLocalPart());
        stringBuffer.append('(');
        int size = list.size();
        for (int i = 0; i < size; i++) {
            stringBuffer.append(list.get(i).toString());
            if (i < size - 1) {
                stringBuffer.append(", ");
            }
        }
        stringBuffer.append(')');
        return stringBuffer.toString();
    }

    public static final String getSignature(Class<?> cls) {
        if (cls.isArray()) {
            StringBuffer stringBuffer = new StringBuffer();
            while (cls.isArray()) {
                stringBuffer.append("[");
                cls = cls.getComponentType();
            }
            stringBuffer.append(getSignature(cls));
            return stringBuffer.toString();
        }
        if (!cls.isPrimitive()) {
            return "L" + cls.getName().replace('.', '/') + ';';
        }
        if (cls == Integer.TYPE) {
            return "I";
        }
        if (cls == Byte.TYPE) {
            return "B";
        }
        if (cls == Long.TYPE) {
            return "J";
        }
        if (cls == Float.TYPE) {
            return "F";
        }
        if (cls == Double.TYPE) {
            return "D";
        }
        if (cls == Short.TYPE) {
            return "S";
        }
        if (cls == Character.TYPE) {
            return "C";
        }
        if (cls == Boolean.TYPE) {
            return Constants.HASIDCALL_INDEX_SIG;
        }
        if (cls == Void.TYPE) {
            return "V";
        }
        throw new Error(new ErrorMsg(ErrorMsg.UNKNOWN_SIG_TYPE_ERR, cls.toString()).toString());
    }

    public static String replaceDash(String str) {
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < str.length(); i++) {
            if (i > 0 && str.charAt(i - 1) == '-') {
                sb.append(Character.toUpperCase(str.charAt(i)));
            } else if (str.charAt(i) != '-') {
                sb.append(str.charAt(i));
            }
        }
        return sb.toString();
    }

    private void translateUnallowedExtension(ConstantPoolGen constantPoolGen, InstructionList instructionList) {
        int iAddMethodref = constantPoolGen.addMethodref(Constants.BASIS_LIBRARY_CLASS, "unallowed_extension_functionF", "(Ljava/lang/String;)V");
        instructionList.append(new PUSH(constantPoolGen, this._fname.toString()));
        instructionList.append(new INVOKESTATIC(iAddMethodref));
    }

    public final Expression argument(int i) {
        return this._arguments.get(i);
    }

    public final int argumentCount() {
        return this._arguments.size();
    }

    public String getClassNameFromUri(String str) {
        String str2 = EXTENSIONNAMESPACE.get(str);
        if (str2 != null) {
            return str2;
        }
        if (str.startsWith(JAVA_EXT_XSLTC)) {
            return str.length() > 39 ? str.substring(39) : "";
        }
        if (str.startsWith("http://xml.apache.org/xalan/java")) {
            return str.length() > 33 ? str.substring(33) : "";
        }
        if (str.startsWith("http://xml.apache.org/xslt/java")) {
            return str.length() > 32 ? str.substring(32) : "";
        }
        int iLastIndexOf = str.lastIndexOf(47);
        return iLastIndexOf > 0 ? str.substring(iLastIndexOf + 1) : str;
    }

    public String getName() {
        return this._fname.toString();
    }

    public boolean isExtension() {
        String namespace = this._fname.getNamespace();
        return namespace != null && namespace.equals("http://xml.apache.org/xalan/xsltc");
    }

    public boolean isStandard() {
        String namespace = this._fname.getNamespace();
        return namespace == null || namespace.equals("");
    }

    public final void setArgument(int i, Expression expression) {
        this._arguments.set(i, expression);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void setParser(Parser parser) {
        super.setParser(parser);
        List<Expression> list = this._arguments;
        if (list != null) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                Expression expression = this._arguments.get(i);
                expression.setParser(parser);
                expression.setParent(this);
            }
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Expression
    public String toString() {
        return "funcall(" + this._fname + ", " + this._arguments + ')';
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Expression, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void translate(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        String str;
        int iArgumentCount = argumentCount();
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        boolean zIsSecureProcessing = classGenerator.getParser().getXSLTC().isSecureProcessing();
        boolean feature = classGenerator.getParser().getXSLTC().getFeature(JdkXmlFeatures.XmlFeature.ENABLE_EXTENSION_FUNCTION);
        int i = 0;
        if (isStandard() || isExtension()) {
            while (i < iArgumentCount) {
                Expression expressionArgument = argument(i);
                expressionArgument.translate(classGenerator, methodGenerator);
                expressionArgument.startIterator(classGenerator, methodGenerator);
                i++;
            }
            String str2 = this._fname.toString().replace(LocaleUtility.IETF_SEPARATOR, '_') + "F";
            if (str2.equals("sumF")) {
                instructionList.append(methodGenerator.loadDOM());
                str = Constants.DOM_INTF_SIG;
            } else if (str2.equals("normalize_spaceF") && this._chosenMethodType.toSignature("").equals("()Ljava/lang/String;")) {
                instructionList.append(methodGenerator.loadContextNode());
                instructionList.append(methodGenerator.loadDOM());
                str = "ILcom/sun/org/apache/xalan/internal/xsltc/DOM;";
            } else {
                str = "";
            }
            instructionList.append(new INVOKESTATIC(constantPool.addMethodref(Constants.BASIS_LIBRARY_CLASS, str2, this._chosenMethodType.toSignature(str))));
            return;
        }
        if (this.unresolvedExternal) {
            int iAddMethodref = constantPool.addMethodref(Constants.BASIS_LIBRARY_CLASS, "unresolved_externalF", "(Ljava/lang/String;)V");
            instructionList.append(new PUSH(constantPool, this._fname.toString()));
            instructionList.append(new INVOKESTATIC(iAddMethodref));
            return;
        }
        if (!this._isExtConstructor) {
            if (zIsSecureProcessing && !feature) {
                translateUnallowedExtension(constantPool, instructionList);
            }
            String name = this._chosenMethod.getDeclaringClass().getName();
            Class<?>[] parameterTypes = this._chosenMethod.getParameterTypes();
            generateAddReads(classGenerator, methodGenerator, name);
            Expression expression = this._thisArgument;
            if (expression != null) {
                expression.translate(classGenerator, methodGenerator);
            }
            for (int i2 = 0; i2 < iArgumentCount; i2++) {
                Expression expressionArgument2 = argument(i2);
                expressionArgument2.translate(classGenerator, methodGenerator);
                expressionArgument2.startIterator(classGenerator, methodGenerator);
                expressionArgument2.getType().translateTo(classGenerator, methodGenerator, parameterTypes[i2]);
            }
            StringBuffer stringBuffer = new StringBuffer("(");
            while (i < parameterTypes.length) {
                stringBuffer.append(getSignature(parameterTypes[i]));
                i++;
            }
            stringBuffer.append(')');
            stringBuffer.append(getSignature(this._chosenMethod.getReturnType()));
            if (this._thisArgument == null || !this._clazz.isInterface()) {
                int iAddMethodref2 = constantPool.addMethodref(name, this._fname.getLocalPart(), stringBuffer.toString());
                instructionList.append(this._thisArgument != null ? new INVOKEVIRTUAL(iAddMethodref2) : new INVOKESTATIC(iAddMethodref2));
            } else {
                instructionList.append(new INVOKEINTERFACE(constantPool.addInterfaceMethodref(name, this._fname.getLocalPart(), stringBuffer.toString()), iArgumentCount + 1));
            }
            this._type.translateFrom(classGenerator, methodGenerator, this._chosenMethod.getReturnType());
            return;
        }
        if (zIsSecureProcessing && !feature) {
            translateUnallowedExtension(constantPool, instructionList);
        }
        String name2 = this._chosenConstructor.getDeclaringClass().getName();
        generateAddReads(classGenerator, methodGenerator, name2);
        Class<?>[] parameterTypes2 = this._chosenConstructor.getParameterTypes();
        LocalVariableGen[] localVariableGenArr = new LocalVariableGen[iArgumentCount];
        for (int i3 = 0; i3 < iArgumentCount; i3++) {
            Expression expressionArgument3 = argument(i3);
            Type type = expressionArgument3.getType();
            expressionArgument3.translate(classGenerator, methodGenerator);
            expressionArgument3.startIterator(classGenerator, methodGenerator);
            type.translateTo(classGenerator, methodGenerator, parameterTypes2[i3]);
            LocalVariableGen localVariableGenAddLocalVariable = methodGenerator.addLocalVariable("function_call_tmp" + i3, type.toJCType(), null, null);
            localVariableGenArr[i3] = localVariableGenAddLocalVariable;
            localVariableGenAddLocalVariable.setStart(instructionList.append(type.STORE(localVariableGenAddLocalVariable.getIndex())));
        }
        instructionList.append(new NEW(constantPool.addClass(this._className)));
        instructionList.append(InstructionConst.DUP);
        for (int i4 = 0; i4 < iArgumentCount; i4++) {
            localVariableGenArr[i4].setEnd(instructionList.append(argument(i4).getType().LOAD(localVariableGenArr[i4].getIndex())));
        }
        StringBuffer stringBuffer2 = new StringBuffer("(");
        while (i < parameterTypes2.length) {
            stringBuffer2.append(getSignature(parameterTypes2[i]));
            i++;
        }
        stringBuffer2.append(")V");
        instructionList.append(new INVOKESPECIAL(constantPool.addMethodref(name2, Const.CONSTRUCTOR_NAME, stringBuffer2.toString())));
        Type.Object.translateFrom(classGenerator, methodGenerator, this._chosenConstructor.getDeclaringClass());
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Expression
    public void translateDesynthesized(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        Type typeResultType = Type.Boolean;
        MethodType methodType = this._chosenMethodType;
        if (methodType != null) {
            typeResultType = methodType.resultType();
        }
        InstructionList instructionList = methodGenerator.getInstructionList();
        translate(classGenerator, methodGenerator);
        if ((typeResultType instanceof BooleanType) || (typeResultType instanceof IntType)) {
            this._falseList.add(instructionList.append((BranchInstruction) new IFEQ(null)));
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Expression, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public Type typeCheck(SymbolTable symbolTable) throws TypeCheckError {
        Type type = this._type;
        if (type != null) {
            return type;
        }
        String namespace = this._fname.getNamespace();
        String localPart = this._fname.getLocalPart();
        if (isExtension()) {
            this._fname = new QName(null, null, localPart);
            return typeCheckStandard(symbolTable);
        }
        if (isStandard()) {
            return typeCheckStandard(symbolTable);
        }
        try {
            this._className = getClassNameFromUri(namespace);
            int iLastIndexOf = localPart.lastIndexOf(46);
            if (iLastIndexOf > 0) {
                this._isStatic = true;
                String str = this._className;
                if (str == null || str.length() <= 0) {
                    this._namespace_format = 0;
                    this._className = localPart.substring(0, iLastIndexOf);
                } else {
                    this._namespace_format = 2;
                    this._className += com.sun.org.apache.xalan.internal.templates.Constants.ATTRVAL_THIS + localPart.substring(0, iLastIndexOf);
                }
                this._fname = new QName(namespace, null, localPart.substring(iLastIndexOf + 1));
            } else {
                String str2 = this._className;
                if (str2 == null || str2.length() <= 0) {
                    this._namespace_format = 0;
                } else {
                    try {
                        this._clazz = ObjectFactory.findProviderClass(this._className, true);
                        this._namespace_format = 1;
                    } catch (ClassNotFoundException unused) {
                        this._namespace_format = 2;
                    }
                }
                if (localPart.indexOf(45) > 0) {
                    localPart = replaceDash(localPart);
                }
                String str3 = EXTENSIONFUNCTION.get(namespace + ":" + localPart);
                if (str3 != null) {
                    this._fname = new QName(null, null, str3);
                    return typeCheckStandard(symbolTable);
                }
                this._fname = new QName(namespace, null, localPart);
            }
            return typeCheckExternal(symbolTable);
        } catch (TypeCheckError e) {
            ErrorMsg errorMsg = e.getErrorMsg();
            if (errorMsg == null) {
                errorMsg = new ErrorMsg(ErrorMsg.METHOD_NOT_FOUND_ERR, this._fname.getLocalPart());
            }
            getParser().reportError(3, errorMsg);
            Type type2 = Type.Void;
            this._type = type2;
            return type2;
        }
    }

    public List<Type> typeCheckArgs(SymbolTable symbolTable) throws TypeCheckError {
        ArrayList arrayList = new ArrayList();
        Iterator<Expression> it = this._arguments.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().typeCheck(symbolTable));
        }
        return arrayList;
    }

    public Type typeCheckConstructor(SymbolTable symbolTable) throws TypeCheckError {
        List<Constructor<?>> listFindConstructors = findConstructors();
        if (listFindConstructors == null) {
            throw new TypeCheckError(ErrorMsg.CONSTRUCTOR_NOT_FOUND, this._className);
        }
        int size = listFindConstructors.size();
        int size2 = this._arguments.size();
        List<Type> listTypeCheckArgs = typeCheckArgs(symbolTable);
        this._type = null;
        int i = Integer.MAX_VALUE;
        for (int i2 = 0; i2 < size; i2++) {
            Constructor<?> constructor = listFindConstructors.get(i2);
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            int i3 = 0;
            int i4 = 0;
            while (i3 < size2) {
                Class<?> cls = parameterTypes[i3];
                Type type = listTypeCheckArgs.get(i3);
                JavaType javaTypeMaps = _internal2Java.maps(type, new JavaType(cls, 0));
                if (javaTypeMaps == null) {
                    if (type instanceof ObjectType) {
                        ObjectType objectType = (ObjectType) type;
                        if (objectType.getJavaClass() == cls) {
                            continue;
                        } else if (cls.isAssignableFrom(objectType.getJavaClass())) {
                            i4++;
                        }
                    }
                    i4 = Integer.MAX_VALUE;
                    break;
                }
                i4 += javaTypeMaps.distance;
                i3++;
            }
            if (i3 == size2 && i4 < i) {
                this._chosenConstructor = constructor;
                this._isExtConstructor = true;
                Class<?> cls2 = this._clazz;
                this._type = cls2 != null ? Type.newObjectType(cls2) : Type.newObjectType(this._className);
                i = i4;
            }
        }
        Type type2 = this._type;
        if (type2 != null) {
            return type2;
        }
        throw new TypeCheckError(ErrorMsg.ARGUMENT_CONVERSION_ERR, getMethodSignature(listTypeCheckArgs));
    }

    public Type typeCheckExternal(SymbolTable symbolTable) throws TypeCheckError {
        int i;
        Class<?> cls;
        int size = this._arguments.size();
        String localPart = this._fname.getLocalPart();
        if (this._fname.getLocalPart().equals(PsiKeyword.NEW)) {
            return typeCheckConstructor(symbolTable);
        }
        if (size == 0) {
            this._isStatic = true;
        }
        if (!this._isStatic) {
            int i2 = this._namespace_format;
            boolean z = i2 == 0 || i2 == 2;
            Type typeTypeCheck = this._arguments.get(0).typeCheck(symbolTable);
            if (this._namespace_format == 1 && (typeTypeCheck instanceof ObjectType) && (cls = this._clazz) != null && cls.isAssignableFrom(((ObjectType) typeTypeCheck).getJavaClass())) {
                z = true;
            }
            if (z) {
                this._thisArgument = this._arguments.get(0);
                this._arguments.remove(0);
                size--;
                if (!(typeTypeCheck instanceof ObjectType)) {
                    throw new TypeCheckError(ErrorMsg.NO_JAVA_FUNCT_THIS_REF, localPart);
                }
                this._className = ((ObjectType) typeTypeCheck).getJavaClassName();
            }
        } else if (this._className.length() == 0) {
            Parser parser = getParser();
            if (parser != null) {
                reportWarning(this, parser, ErrorMsg.FUNCTION_RESOLVE_ERR, this._fname.toString());
            }
            this.unresolvedExternal = true;
            Type type = Type.Int;
            this._type = type;
            return type;
        }
        List<Method> listFindMethods = findMethods();
        if (listFindMethods == null) {
            throw new TypeCheckError(ErrorMsg.METHOD_NOT_FOUND_ERR, this._className + com.sun.org.apache.xalan.internal.templates.Constants.ATTRVAL_THIS + localPart);
        }
        int size2 = listFindMethods.size();
        List<Type> listTypeCheckArgs = typeCheckArgs(symbolTable);
        this._type = null;
        int i3 = 0;
        int i4 = Integer.MAX_VALUE;
        while (i3 < size2) {
            Method method = listFindMethods.get(i3);
            Class<?>[] parameterTypes = method.getParameterTypes();
            int i5 = 0;
            int i6 = 0;
            while (true) {
                if (i5 >= size) {
                    i = size2;
                    break;
                }
                Class<?> cls2 = parameterTypes[i5];
                Type type2 = listTypeCheckArgs.get(i5);
                i = size2;
                JavaType javaTypeMaps = _internal2Java.maps(type2, new JavaType(cls2, 0));
                if (javaTypeMaps != null) {
                    i6 += javaTypeMaps.distance;
                } else {
                    if (!(type2 instanceof ReferenceType)) {
                        if (type2 instanceof ObjectType) {
                            ObjectType objectType = (ObjectType) type2;
                            if (cls2.getName().equals(objectType.getJavaClassName())) {
                                continue;
                            } else {
                                if (!cls2.isAssignableFrom(objectType.getJavaClass())) {
                                }
                                i6++;
                            }
                        }
                        i6 = Integer.MAX_VALUE;
                        break;
                    }
                    i6++;
                }
                i5++;
                size2 = i;
            }
            if (i5 == size) {
                Class<?> returnType = method.getReturnType();
                Type type3 = JAVA2INTERNAL.get(returnType);
                this._type = type3;
                if (type3 == null) {
                    this._type = Type.newObjectType(returnType);
                }
                if (this._type != null && i6 < i4) {
                    this._chosenMethod = method;
                    i4 = i6;
                }
            }
            i3++;
            size2 = i;
        }
        Method method2 = this._chosenMethod;
        if (method2 != null && this._thisArgument == null && !Modifier.isStatic(method2.getModifiers())) {
            throw new TypeCheckError(ErrorMsg.NO_JAVA_FUNCT_THIS_REF, getMethodSignature(listTypeCheckArgs));
        }
        Type type4 = this._type;
        if (type4 == null) {
            throw new TypeCheckError(ErrorMsg.ARGUMENT_CONVERSION_ERR, getMethodSignature(listTypeCheckArgs));
        }
        if (type4 == Type.NodeSet) {
            getXSLTC().setMultiDocument(true);
        }
        return this._type;
    }

    public Type typeCheckStandard(SymbolTable symbolTable) throws TypeCheckError {
        this._fname.clearNamespace();
        int size = this._arguments.size();
        MethodType methodTypeLookupPrimop = lookupPrimop(symbolTable, this._fname.getLocalPart(), new MethodType(Type.Void, typeCheckArgs(symbolTable)));
        if (methodTypeLookupPrimop == null) {
            throw new TypeCheckError(this);
        }
        for (int i = 0; i < size; i++) {
            Type type = methodTypeLookupPrimop.argsType().get(i);
            Expression expression = this._arguments.get(i);
            if (!type.identicalTo(expression.getType())) {
                try {
                    this._arguments.set(i, new CastExpr(expression, type));
                } catch (TypeCheckError unused) {
                    throw new TypeCheckError(this);
                }
            }
        }
        this._chosenMethodType = methodTypeLookupPrimop;
        Type typeResultType = methodTypeLookupPrimop.resultType();
        this._type = typeResultType;
        return typeResultType;
    }

    public final Expression argument() {
        return argument(0);
    }

    public FunctionCall(QName qName) {
        this(qName, EMPTY_ARG_LIST);
    }

    public static final String getSignature(Method method) {
        StringBuffer stringBuffer = new StringBuffer("(");
        for (Class<?> cls : method.getParameterTypes()) {
            stringBuffer.append(getSignature(cls));
        }
        stringBuffer.append(')');
        stringBuffer.append(getSignature(method.getReturnType()));
        return stringBuffer.toString();
    }

    public static final String getSignature(Constructor<?> constructor) {
        StringBuffer stringBuffer = new StringBuffer("(");
        for (Class<?> cls : constructor.getParameterTypes()) {
            stringBuffer.append(getSignature(cls));
        }
        stringBuffer.append(")V");
        return stringBuffer.toString();
    }
}
