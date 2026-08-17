package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.generic.ALOAD;
import com.sun.org.apache.bcel.internal.generic.BasicType;
import com.sun.org.apache.bcel.internal.generic.BranchHandle;
import com.sun.org.apache.bcel.internal.generic.BranchInstruction;
import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.IF_ICMPEQ;
import com.sun.org.apache.bcel.internal.generic.ILOAD;
import com.sun.org.apache.bcel.internal.generic.INVOKEINTERFACE;
import com.sun.org.apache.bcel.internal.generic.INVOKEVIRTUAL;
import com.sun.org.apache.bcel.internal.generic.InstructionHandle;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.bcel.internal.generic.PUSH;
import com.sun.org.apache.bcel.internal.generic.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class Whitespace extends TopLevelElement {
    public static final int PRESERVE_SPACE = 2;
    public static final int RULE_ALL = 3;
    public static final int RULE_ELEMENT = 1;
    public static final int RULE_NAMESPACE = 2;
    public static final int RULE_NONE = 0;
    public static final int STRIP_SPACE = 1;
    public static final int USE_PREDICATE = 0;
    private int _action;
    private String _elementList;
    private int _importPrecedence;

    public static final class WhitespaceRule {
        private final int _action;
        private String _element;
        private String _namespace;
        private int _priority;
        private int _type;

        public WhitespaceRule(int i, String str, int i2) {
            this._action = i;
            int iLastIndexOf = str.lastIndexOf(58);
            if (iLastIndexOf >= 0) {
                this._namespace = str.substring(0, iLastIndexOf);
                this._element = str.substring(iLastIndexOf + 1, str.length());
            } else {
                this._namespace = "";
                this._element = str;
            }
            this._priority = i2 << 2;
            if (!this._element.equals("*")) {
                this._type = 1;
            } else if (this._namespace == "") {
                this._type = 3;
                this._priority += 2;
            } else {
                this._type = 2;
                this._priority++;
            }
        }

        public int compareTo(WhitespaceRule whitespaceRule) {
            int i = this._priority;
            int i2 = whitespaceRule._priority;
            if (i < i2) {
                return -1;
            }
            return i > i2 ? 1 : 0;
        }

        public int getAction() {
            return this._action;
        }

        public String getElement() {
            return this._element;
        }

        public String getNamespace() {
            return this._namespace;
        }

        public int getPriority() {
            return this._priority;
        }

        public int getStrength() {
            return this._type;
        }
    }

    private static void compileDefault(int i, ClassGenerator classGenerator) {
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = new InstructionList();
        classGenerator.getParser().getXSLTC();
        BasicType basicType = Type.BOOLEAN;
        BasicType basicType2 = Type.INT;
        MethodGenerator methodGenerator = new MethodGenerator(17, basicType, new Type[]{Util.getJCRefType(Constants.DOM_INTF_SIG), basicType2, basicType2}, new String[]{Constants.DOM_PNAME, "node", "type"}, Constants.STRIP_SPACE, classGenerator.getClassName(), instructionList, constantPool);
        classGenerator.addInterface(Constants.STRIP_SPACE_INTF);
        if (i == 1) {
            instructionList.append(Constants.ICONST_1);
        } else {
            instructionList.append(Constants.ICONST_0);
        }
        instructionList.append(Constants.IRETURN);
        classGenerator.addMethod(methodGenerator);
    }

    private static void compilePredicate(List<WhitespaceRule> list, int i, ClassGenerator classGenerator) {
        int i2;
        int i3;
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = new InstructionList();
        XSLTC xsltc = classGenerator.getParser().getXSLTC();
        BasicType basicType = Type.BOOLEAN;
        BasicType basicType2 = Type.INT;
        int i4 = 1;
        MethodGenerator methodGenerator = new MethodGenerator(17, basicType, new Type[]{Util.getJCRefType(Constants.DOM_INTF_SIG), basicType2, basicType2}, new String[]{Constants.DOM_PNAME, "node", "type"}, Constants.STRIP_SPACE, classGenerator.getClassName(), instructionList, constantPool);
        classGenerator.addInterface(Constants.STRIP_SPACE_INTF);
        int localIndex = methodGenerator.getLocalIndex(Constants.DOM_PNAME);
        int localIndex2 = methodGenerator.getLocalIndex("node");
        int localIndex3 = methodGenerator.getLocalIndex("type");
        BranchHandle[] branchHandleArr = new BranchHandle[list.size()];
        BranchHandle[] branchHandleArr2 = new BranchHandle[list.size()];
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (i6 < list.size()) {
            WhitespaceRule whitespaceRule = list.get(i6);
            int i8 = i6;
            int iAddInterfaceMethodref = constantPool.addInterfaceMethodref(Constants.DOM_INTF, "getNamespaceName", "(I)Ljava/lang/String;");
            int iAddMethodref = constantPool.addMethodref("java/lang/String", "compareTo", Constants.STRING_TO_INT_SIG);
            if (whitespaceRule.getStrength() == 2) {
                instructionList.append(new ALOAD(localIndex));
                instructionList.append(new ILOAD(localIndex2));
                instructionList.append(new INVOKEINTERFACE(iAddInterfaceMethodref, 2));
                instructionList.append(new PUSH(constantPool, whitespaceRule.getNamespace()));
                instructionList.append(new INVOKEVIRTUAL(iAddMethodref));
                instructionList.append(Constants.ICONST_0);
                if (whitespaceRule.getAction() == 1) {
                    i3 = i7 + 1;
                    branchHandleArr[i7] = instructionList.append((BranchInstruction) new IF_ICMPEQ(null));
                    i7 = i3;
                } else {
                    i2 = i5 + 1;
                    branchHandleArr2[i5] = instructionList.append((BranchInstruction) new IF_ICMPEQ(null));
                    i5 = i2;
                }
            } else if (whitespaceRule.getStrength() == 1) {
                Parser parser = classGenerator.getParser();
                int iRegisterElement = xsltc.registerElement(whitespaceRule.getNamespace() != "" ? parser.getQName(whitespaceRule.getNamespace(), (String) null, whitespaceRule.getElement()) : parser.getQName(whitespaceRule.getElement()));
                instructionList.append(new ILOAD(localIndex3));
                instructionList.append(new PUSH(constantPool, iRegisterElement));
                if (whitespaceRule.getAction() == 1) {
                    i3 = i7 + 1;
                    branchHandleArr[i7] = instructionList.append((BranchInstruction) new IF_ICMPEQ(null));
                    i7 = i3;
                } else {
                    i2 = i5 + 1;
                    branchHandleArr2[i5] = instructionList.append((BranchInstruction) new IF_ICMPEQ(null));
                    i5 = i2;
                }
            }
            i6 = i8 + 1;
            i4 = 1;
        }
        if (i == i4) {
            compileStripSpace(branchHandleArr, i7, instructionList);
            compilePreserveSpace(branchHandleArr2, i5, instructionList);
        } else {
            compilePreserveSpace(branchHandleArr2, i5, instructionList);
            compileStripSpace(branchHandleArr, i7, instructionList);
        }
        classGenerator.addMethod(methodGenerator);
    }

    public static void compilePreserveSpace(BranchHandle[] branchHandleArr, int i, InstructionList instructionList) {
        InstructionHandle instructionHandleAppend = instructionList.append(Constants.ICONST_0);
        instructionList.append(Constants.IRETURN);
        for (int i2 = 0; i2 < i; i2++) {
            branchHandleArr[i2].setTarget(instructionHandleAppend);
        }
    }

    public static void compileStripSpace(BranchHandle[] branchHandleArr, int i, InstructionList instructionList) {
        InstructionHandle instructionHandleAppend = instructionList.append(Constants.ICONST_1);
        instructionList.append(Constants.IRETURN);
        for (int i2 = 0; i2 < i; i2++) {
            branchHandleArr[i2].setTarget(instructionHandleAppend);
        }
    }

    /* JADX WARN: Code duplicated, block: B:28:0x003f A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:33:0x0004 A[SYNTHETIC] */
    private static WhitespaceRule findContradictingRule(List<WhitespaceRule> list, WhitespaceRule whitespaceRule) {
        WhitespaceRule next;
        Iterator<WhitespaceRule> it = list.iterator();
        while (it.hasNext() && (next = it.next()) != whitespaceRule) {
            int strength = next.getStrength();
            if (strength != 1) {
                if (strength != 2) {
                    if (strength == 3) {
                        return next;
                    }
                } else if (whitespaceRule.getNamespace().equals(next.getNamespace())) {
                    return next;
                }
            } else if (!whitespaceRule.getElement().equals(next.getElement())) {
                continue;
            } else if (whitespaceRule.getNamespace().equals(next.getNamespace())) {
                return next;
            }
        }
        return null;
    }

    private static int partition(List<WhitespaceRule> list, int i, int i2) {
        WhitespaceRule whitespaceRule = list.get((i + i2) >>> 1);
        int i3 = i - 1;
        int i4 = i2 + 1;
        while (true) {
            i4--;
            if (whitespaceRule.compareTo(list.get(i4)) >= 0) {
                do {
                    i3++;
                } while (whitespaceRule.compareTo(list.get(i3)) > 0);
                if (i3 >= i4) {
                    return i4;
                }
                WhitespaceRule whitespaceRule2 = list.get(i3);
                list.set(i3, list.get(i4));
                list.set(i4, whitespaceRule2);
            }
        }
    }

    private static int prioritizeRules(List<WhitespaceRule> list) {
        int i = 0;
        quicksort(list, 0, list.size() - 1);
        boolean z = false;
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (list.get(i2).getAction() == 1) {
                z = true;
            }
        }
        int action = 2;
        if (!z) {
            list.clear();
            return 2;
        }
        while (i < list.size()) {
            WhitespaceRule whitespaceRule = list.get(i);
            if (findContradictingRule(list, whitespaceRule) != null) {
                list.remove(i);
            } else {
                if (whitespaceRule.getStrength() == 3) {
                    action = whitespaceRule.getAction();
                    for (int i3 = i; i3 < list.size(); i3++) {
                        list.remove(i3);
                    }
                }
                i++;
            }
        }
        if (!list.isEmpty()) {
            while (list.get(list.size() - 1).getAction() == action) {
                list.remove(list.size() - 1);
                if (list.size() <= 0) {
                    break;
                }
            }
        }
        return action;
    }

    private static void quicksort(List<WhitespaceRule> list, int i, int i2) {
        while (i < i2) {
            int iPartition = partition(list, i, i2);
            quicksort(list, i, iPartition);
            i = iPartition + 1;
        }
    }

    public static int translateRules(List<WhitespaceRule> list, ClassGenerator classGenerator) {
        int iPrioritizeRules = prioritizeRules(list);
        if (list.size() == 0) {
            compileDefault(iPrioritizeRules, classGenerator);
            return iPrioritizeRules;
        }
        compilePredicate(list, iPrioritizeRules, classGenerator);
        return 0;
    }

    public List<WhitespaceRule> getRules() {
        ArrayList arrayList = new ArrayList();
        StringTokenizer stringTokenizer = new StringTokenizer(this._elementList);
        while (stringTokenizer.hasMoreElements()) {
            arrayList.add(new WhitespaceRule(this._action, stringTokenizer.nextToken(), this._importPrecedence));
        }
        return arrayList;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void parseContents(Parser parser) {
        String strLookupNamespace;
        this._action = this._qname.getLocalPart().endsWith(com.sun.org.apache.xalan.internal.templates.Constants.ELEMNAME_STRIPSPACE_STRING) ? 1 : 2;
        this._importPrecedence = parser.getCurrentImportPrecedence();
        String attribute = getAttribute(com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_ELEMENTS);
        this._elementList = attribute;
        if (attribute == null || attribute.length() == 0) {
            reportError(this, parser, ErrorMsg.REQUIRED_ATTR_ERR, com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_ELEMENTS);
            return;
        }
        parser.getSymbolTable();
        StringTokenizer stringTokenizer = new StringTokenizer(this._elementList);
        StringBuffer stringBuffer = new StringBuffer("");
        while (stringTokenizer.hasMoreElements()) {
            String strNextToken = stringTokenizer.nextToken();
            int iIndexOf = strNextToken.indexOf(58);
            if (iIndexOf == -1 || (strLookupNamespace = lookupNamespace(strNextToken.substring(0, iIndexOf))) == null) {
                stringBuffer.append(strNextToken);
            } else {
                stringBuffer.append(strLookupNamespace);
                stringBuffer.append(':');
                stringBuffer.append(strNextToken.substring(iIndexOf + 1));
            }
            if (stringTokenizer.hasMoreElements()) {
                stringBuffer.append(" ");
            }
        }
        this._elementList = stringBuffer.toString();
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.TopLevelElement, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void translate(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.TopLevelElement, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type typeCheck(SymbolTable symbolTable) throws TypeCheckError {
        return com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Void;
    }
}
