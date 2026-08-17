package com.sun.org.apache.bcel.internal.generic;

import com.intellij.psi.PsiKeyword;
import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.bcel.internal.classfile.AnnotationEntry;
import com.sun.org.apache.bcel.internal.classfile.Annotations;
import com.sun.org.apache.bcel.internal.classfile.Attribute;
import com.sun.org.apache.bcel.internal.classfile.Code;
import com.sun.org.apache.bcel.internal.classfile.CodeException;
import com.sun.org.apache.bcel.internal.classfile.ExceptionTable;
import com.sun.org.apache.bcel.internal.classfile.LineNumber;
import com.sun.org.apache.bcel.internal.classfile.LineNumberTable;
import com.sun.org.apache.bcel.internal.classfile.LocalVariable;
import com.sun.org.apache.bcel.internal.classfile.LocalVariableTable;
import com.sun.org.apache.bcel.internal.classfile.LocalVariableTypeTable;
import com.sun.org.apache.bcel.internal.classfile.Method;
import com.sun.org.apache.bcel.internal.classfile.ParameterAnnotationEntry;
import com.sun.org.apache.bcel.internal.classfile.ParameterAnnotations;
import com.sun.org.apache.bcel.internal.classfile.RuntimeVisibleParameterAnnotations;
import com.sun.org.apache.bcel.internal.classfile.Utility;
import com.sun.org.apache.bcel.internal.generic.LocalVariableGen;
import com.sun.org.apache.bcel.internal.generic.MethodGen;
import com.sun.org.apache.bcel.internal.util.BCELComparator;
import com.sun.org.apache.xalan.internal.templates.Constants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Stack;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.ToIntFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class MethodGen extends FieldGenOrMethodGen {
    private static BCELComparator bcelComparator = new BCELComparator() { // from class: com.sun.org.apache.bcel.internal.generic.MethodGen.1
        @Override // com.sun.org.apache.bcel.internal.util.BCELComparator
        public boolean equals(Object obj, Object obj2) {
            FieldGenOrMethodGen fieldGenOrMethodGen = (FieldGenOrMethodGen) obj;
            FieldGenOrMethodGen fieldGenOrMethodGen2 = (FieldGenOrMethodGen) obj2;
            return Objects.equals(fieldGenOrMethodGen.getName(), fieldGenOrMethodGen2.getName()) && Objects.equals(fieldGenOrMethodGen.getSignature(), fieldGenOrMethodGen2.getSignature());
        }

        @Override // com.sun.org.apache.bcel.internal.util.BCELComparator
        public int hashCode(Object obj) {
            FieldGenOrMethodGen fieldGenOrMethodGen = (FieldGenOrMethodGen) obj;
            return fieldGenOrMethodGen.getSignature().hashCode() ^ fieldGenOrMethodGen.getName().hashCode();
        }
    };
    private String[] argNames;
    private Type[] argTypes;
    private String className;
    private final List<Attribute> codeAttrsList;
    private final List<CodeExceptionGen> exceptionList;
    private boolean hasParameterAnnotations;
    private boolean haveUnpackedParameterAnnotations;
    private InstructionList il;
    private final List<LineNumberGen> lineNumberList;
    private LocalVariableTypeTable localVariableTypeTable;
    private int maxLocals;
    private int maxStack;
    private List<MethodObserver> observers;
    private List<AnnotationEntryGen>[] paramAnnotations;
    private boolean stripAttributes;
    private final List<String> throwsList;
    private final List<LocalVariableGen> variableList;

    public static final class BranchStack {
        private final Stack<BranchTarget> branchTargets = new Stack<>();
        private final HashMap<InstructionHandle, BranchTarget> visitedTargets = new HashMap<>();

        private BranchTarget visit(InstructionHandle instructionHandle, int i) {
            BranchTarget branchTarget = new BranchTarget(instructionHandle, i);
            this.visitedTargets.put(instructionHandle, branchTarget);
            return branchTarget;
        }

        private boolean visited(InstructionHandle instructionHandle) {
            return this.visitedTargets.get(instructionHandle) != null;
        }

        public BranchTarget pop() {
            if (this.branchTargets.empty()) {
                return null;
            }
            return this.branchTargets.pop();
        }

        public void push(InstructionHandle instructionHandle, int i) {
            if (visited(instructionHandle)) {
                return;
            }
            this.branchTargets.push(visit(instructionHandle, i));
        }
    }

    public static final class BranchTarget {
        final int stackDepth;
        final InstructionHandle target;

        public BranchTarget(InstructionHandle instructionHandle, int i) {
            this.target = instructionHandle;
            this.stackDepth = i;
        }
    }

    public MethodGen(Method method, String str, final ConstantPoolGen constantPoolGen) {
        this(method.getAccessFlags(), Type.getReturnType(method.getSignature()), Type.getArgumentTypes(method.getSignature()), null, method.getName(), str, (method.getAccessFlags() & 1280) == 0 ? new InstructionList(getByteCodes(method)) : null, constantPoolGen);
        for (Attribute attribute : method.getAttributes()) {
            if (attribute instanceof Code) {
                Code code = (Code) attribute;
                setMaxStack(code.getMaxStack());
                setMaxLocals(code.getMaxLocals());
                CodeException[] exceptionTable = code.getExceptionTable();
                if (exceptionTable != null) {
                    for (CodeException codeException : exceptionTable) {
                        int catchType = codeException.getCatchType();
                        ObjectType objectType = catchType > 0 ? ObjectType.getInstance(method.getConstantPool().getConstantString(catchType, (byte) 7)) : null;
                        int endPC = codeException.getEndPC();
                        int length = getByteCodes(method).length;
                        InstructionList instructionList = this.il;
                        addExceptionHandler(this.il.findHandle(codeException.getStartPC()), length == endPC ? instructionList.getEnd() : instructionList.findHandle(endPC).getPrev(), this.il.findHandle(codeException.getHandlerPC()), objectType);
                    }
                }
                for (Attribute attribute2 : code.getAttributes()) {
                    if (attribute2 instanceof LineNumberTable) {
                        ((LineNumberTable) attribute2).forEach(new Consumer() { // from class: f1a
                            @Override // java.util.function.Consumer
                            public final void accept(Object obj) {
                                MethodGen.g(this.b, (LineNumber) obj);
                            }
                        });
                    } else if (attribute2 instanceof LocalVariableTable) {
                        updateLocalVariableTable((LocalVariableTable) attribute2);
                    } else if (attribute2 instanceof LocalVariableTypeTable) {
                        this.localVariableTypeTable = (LocalVariableTypeTable) attribute2.copy(constantPoolGen.getConstantPool());
                    } else {
                        addCodeAttribute(attribute2);
                    }
                }
            } else {
                if (attribute instanceof ExceptionTable) {
                    Collections.addAll(this.throwsList, ((ExceptionTable) attribute).getExceptionNames());
                } else if (attribute instanceof Annotations) {
                    ((Annotations) attribute).forEach(new Consumer() { // from class: g1a
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            MethodGen.d(this.b, constantPoolGen, (AnnotationEntry) obj);
                        }
                    });
                } else {
                    addAttribute(attribute);
                }
            }
        }
    }

    private Attribute[] addRuntimeAnnotationsAsAttribute(ConstantPoolGen constantPoolGen) {
        Attribute[] annotationAttributes = AnnotationEntryGen.getAnnotationAttributes(constantPoolGen, super.getAnnotationEntries());
        addAll(annotationAttributes);
        return annotationAttributes;
    }

    private Attribute[] addRuntimeParameterAnnotationsAsAttribute(ConstantPoolGen constantPoolGen) {
        if (!this.hasParameterAnnotations) {
            return Attribute.EMPTY_ARRAY;
        }
        Attribute[] parameterAnnotationAttributes = AnnotationEntryGen.getParameterAnnotationAttributes(constantPoolGen, this.paramAnnotations);
        addAll(parameterAnnotationAttributes);
        return parameterAnnotationAttributes;
    }

    private void adjustLocalVariableTypeTable(LocalVariableTable localVariableTable) {
        LocalVariable[] localVariableTable2 = localVariableTable.getLocalVariableTable();
        for (LocalVariable localVariable : this.localVariableTypeTable.getLocalVariableTypeTable()) {
            for (LocalVariable localVariable2 : localVariableTable2) {
                if (localVariable.getName().equals(localVariable2.getName()) && localVariable.getIndex() == localVariable2.getOrigIndex()) {
                    localVariable.setLength(localVariable2.getLength());
                    localVariable.setStartPC(localVariable2.getStartPC());
                    localVariable.setIndex(localVariable2.getIndex());
                    break;
                }
            }
        }
    }

    public static /* synthetic */ List b(int i) {
        return new ArrayList();
    }

    public static /* synthetic */ void d(MethodGen methodGen, ConstantPoolGen constantPoolGen, AnnotationEntry annotationEntry) {
        methodGen.getClass();
        methodGen.addAnnotationEntry(new AnnotationEntryGen(annotationEntry, constantPoolGen, false));
    }

    private void ensureExistingParameterAnnotationsUnpacked() {
        if (this.haveUnpackedParameterAnnotations) {
            return;
        }
        Attribute attribute = null;
        Attribute attribute2 = null;
        for (Attribute attribute3 : getAttributes()) {
            if (attribute3 instanceof ParameterAnnotations) {
                if (!this.hasParameterAnnotations) {
                    List<AnnotationEntryGen>[] listArr = new List[this.argTypes.length];
                    this.paramAnnotations = listArr;
                    Arrays.setAll(listArr, new IntFunction() { // from class: h1a
                        @Override // java.util.function.IntFunction
                        public final Object apply(int i) {
                            return MethodGen.b(i);
                        }
                    });
                }
                this.hasParameterAnnotations = true;
                ParameterAnnotations parameterAnnotations = (ParameterAnnotations) attribute3;
                if (parameterAnnotations instanceof RuntimeVisibleParameterAnnotations) {
                    attribute = parameterAnnotations;
                } else {
                    attribute2 = parameterAnnotations;
                }
                ParameterAnnotationEntry[] parameterAnnotationEntries = parameterAnnotations.getParameterAnnotationEntries();
                for (int i = 0; i < parameterAnnotationEntries.length; i++) {
                    this.paramAnnotations[i].addAll(makeMutableVersion(parameterAnnotations.getParameterAnnotationEntries()[i].getAnnotationEntries()));
                }
            }
        }
        if (attribute != null) {
            removeAttribute(attribute);
        }
        if (attribute2 != null) {
            removeAttribute(attribute2);
        }
        this.haveUnpackedParameterAnnotations = true;
    }

    public static /* synthetic */ void g(MethodGen methodGen, LineNumber lineNumber) {
        InstructionHandle instructionHandleFindHandle = methodGen.il.findHandle(lineNumber.getStartPC());
        if (instructionHandleFindHandle != null) {
            methodGen.addLineNumber(instructionHandleFindHandle, lineNumber.getLineNumber());
        }
    }

    private static byte[] getByteCodes(Method method) {
        Code code = method.getCode();
        if (code != null) {
            return code.getCode();
        }
        throw new IllegalStateException(String.format("The method '%s' has no code.", method));
    }

    private CodeException[] getCodeExceptions() {
        CodeException[] codeExceptionArr = new CodeException[this.exceptionList.size()];
        Arrays.setAll(codeExceptionArr, new IntFunction() { // from class: c1a
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                MethodGen methodGen = this.b;
                return methodGen.exceptionList.get(i).getCodeException(super/*com.sun.org.apache.bcel.internal.generic.FieldGenOrMethodGen*/.getConstantPool());
            }
        });
        return codeExceptionArr;
    }

    public static BCELComparator getComparator() {
        return bcelComparator;
    }

    private ExceptionTable getExceptionTable(final ConstantPoolGen constantPoolGen) {
        int size = this.throwsList.size();
        int[] iArr = new int[size];
        Arrays.setAll(iArr, new IntUnaryOperator() { // from class: e1a
            @Override // java.util.function.IntUnaryOperator
            public final int applyAsInt(int i) {
                return constantPoolGen.addClass(this.b.throwsList.get(i));
            }
        });
        return new ExceptionTable(constantPoolGen.addUtf8(com.sun.tools.classfile.Attribute.Exceptions), (size * 2) + 2, iArr, constantPoolGen.getConstantPool());
    }

    public static int getMaxStack(ConstantPoolGen constantPoolGen, InstructionList instructionList, CodeExceptionGen[] codeExceptionGenArr) {
        BranchTarget branchTargetPop;
        BranchStack branchStack = new BranchStack();
        for (CodeExceptionGen codeExceptionGen : codeExceptionGenArr) {
            InstructionHandle handlerPC = codeExceptionGen.getHandlerPC();
            if (handlerPC != null) {
                branchStack.push(handlerPC, 1);
            }
        }
        InstructionHandle start = instructionList.getStart();
        int i = 0;
        int iProduceStack = 0;
        while (start != null) {
            Instruction instruction = start.getInstruction();
            short opcode = instruction.getOpcode();
            iProduceStack += instruction.produceStack(constantPoolGen) - instruction.consumeStack(constantPoolGen);
            if (iProduceStack > i) {
                i = iProduceStack;
            }
            if (instruction instanceof BranchInstruction) {
                BranchInstruction branchInstruction = (BranchInstruction) instruction;
                if (instruction instanceof Select) {
                    for (InstructionHandle instructionHandle : ((Select) branchInstruction).getTargets()) {
                        branchStack.push(instructionHandle, iProduceStack);
                    }
                } else {
                    if (!(branchInstruction instanceof IfInstruction)) {
                        if (opcode == 168 || opcode == 201) {
                            branchStack.push(start.getNext(), iProduceStack - 1);
                        }
                    }
                    branchStack.push(branchInstruction.getTarget(), iProduceStack);
                }
                start = null;
                branchStack.push(branchInstruction.getTarget(), iProduceStack);
            } else if (opcode == 191 || opcode == 169 || (opcode >= 172 && opcode <= 177)) {
                start = null;
            }
            if (start != null) {
                start = start.getNext();
            }
            if (start == null && (branchTargetPop = branchStack.pop()) != null) {
                start = branchTargetPop.target;
                iProduceStack = branchTargetPop.stackDepth;
            }
        }
        return i;
    }

    private List<AnnotationEntryGen> makeMutableVersion(AnnotationEntry[] annotationEntryArr) {
        ArrayList arrayList = new ArrayList();
        for (AnnotationEntry annotationEntry : annotationEntryArr) {
            arrayList.add(new AnnotationEntryGen(annotationEntry, getConstantPool(), false));
        }
        return arrayList;
    }

    public static void setComparator(BCELComparator bCELComparator) {
        bcelComparator = bCELComparator;
    }

    private void updateLocalVariableTable(LocalVariableTable localVariableTable) {
        removeLocalVariables();
        for (LocalVariable localVariable : localVariableTable.getLocalVariableTable()) {
            InstructionHandle instructionHandleFindHandle = this.il.findHandle(localVariable.getStartPC());
            InstructionHandle instructionHandleFindHandle2 = this.il.findHandle(localVariable.getStartPC() + localVariable.getLength());
            if (instructionHandleFindHandle == null) {
                instructionHandleFindHandle = this.il.getStart();
            }
            addLocalVariable(localVariable.getName(), Type.getType(localVariable.getSignature()), localVariable.getIndex(), instructionHandleFindHandle, instructionHandleFindHandle2, localVariable.getOrigIndex());
        }
    }

    public void addAnnotationsAsAttribute(ConstantPoolGen constantPoolGen) {
        addAll(AnnotationEntryGen.getAnnotationAttributes(constantPoolGen, super.getAnnotationEntries()));
    }

    public void addCodeAttribute(Attribute attribute) {
        this.codeAttrsList.add(attribute);
    }

    public void addException(String str) {
        this.throwsList.add(str);
    }

    public CodeExceptionGen addExceptionHandler(InstructionHandle instructionHandle, InstructionHandle instructionHandle2, InstructionHandle instructionHandle3, ObjectType objectType) {
        if (instructionHandle == null || instructionHandle2 == null || instructionHandle3 == null) {
            throw new ClassGenException("Exception handler target is null instruction");
        }
        CodeExceptionGen codeExceptionGen = new CodeExceptionGen(instructionHandle, instructionHandle2, instructionHandle3, objectType);
        this.exceptionList.add(codeExceptionGen);
        return codeExceptionGen;
    }

    public LineNumberGen addLineNumber(InstructionHandle instructionHandle, int i) {
        LineNumberGen lineNumberGen = new LineNumberGen(instructionHandle, i);
        this.lineNumberList.add(lineNumberGen);
        return lineNumberGen;
    }

    public LocalVariableGen addLocalVariable(String str, Type type, int i, InstructionHandle instructionHandle, InstructionHandle instructionHandle2, int i2) {
        if (type.getType() == 16) {
            kg9.a("Can not use ", type, " as type for local variable");
            return null;
        }
        int size = type.getSize() + i;
        if (size > this.maxLocals) {
            this.maxLocals = size;
        }
        LocalVariableGen localVariableGen = new LocalVariableGen(i, str, type, instructionHandle, instructionHandle2, i2);
        int iIndexOf = this.variableList.indexOf(localVariableGen);
        List<LocalVariableGen> list = this.variableList;
        if (iIndexOf >= 0) {
            list.set(iIndexOf, localVariableGen);
            return localVariableGen;
        }
        list.add(localVariableGen);
        return localVariableGen;
    }

    public void addObserver(MethodObserver methodObserver) {
        if (this.observers == null) {
            this.observers = new ArrayList();
        }
        this.observers.add(methodObserver);
    }

    public void addParameterAnnotation(int i, AnnotationEntryGen annotationEntryGen) {
        ensureExistingParameterAnnotationsUnpacked();
        if (!this.hasParameterAnnotations) {
            this.paramAnnotations = new List[this.argTypes.length];
            this.hasParameterAnnotations = true;
        }
        List<AnnotationEntryGen> list = this.paramAnnotations[i];
        if (list != null) {
            list.add(annotationEntryGen);
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(annotationEntryGen);
        this.paramAnnotations[i] = arrayList;
    }

    public void addParameterAnnotationsAsAttribute(ConstantPoolGen constantPoolGen) {
        Attribute[] parameterAnnotationAttributes;
        if (this.hasParameterAnnotations && (parameterAnnotationAttributes = AnnotationEntryGen.getParameterAnnotationAttributes(constantPoolGen, this.paramAnnotations)) != null) {
            addAll(parameterAnnotationAttributes);
        }
    }

    public MethodGen copy(String str, ConstantPoolGen constantPoolGen) {
        MethodGen methodGen = new MethodGen(((MethodGen) clone()).getMethod(), str, super.getConstantPool());
        if (super.getConstantPool() != constantPoolGen) {
            methodGen.setConstantPool(constantPoolGen);
            methodGen.getInstructionList().replaceConstantPool(super.getConstantPool(), constantPoolGen);
        }
        return methodGen;
    }

    public boolean equals(Object obj) {
        return bcelComparator.equals(this, obj);
    }

    public List<AnnotationEntryGen> getAnnotationsOnParameter(int i) {
        ensureExistingParameterAnnotationsUnpacked();
        if (!this.hasParameterAnnotations || i > this.argTypes.length) {
            return null;
        }
        return this.paramAnnotations[i];
    }

    public String getArgumentName(int i) {
        return this.argNames[i];
    }

    public String[] getArgumentNames() {
        return (String[]) this.argNames.clone();
    }

    public Type getArgumentType(int i) {
        return this.argTypes[i];
    }

    public Type[] getArgumentTypes() {
        return (Type[]) this.argTypes.clone();
    }

    public String getClassName() {
        return this.className;
    }

    public Attribute[] getCodeAttributes() {
        return (Attribute[]) this.codeAttrsList.toArray(Attribute.EMPTY_ARRAY);
    }

    public CodeExceptionGen[] getExceptionHandlers() {
        return (CodeExceptionGen[]) this.exceptionList.toArray(CodeExceptionGen.EMPTY_ARRAY);
    }

    public String[] getExceptions() {
        return (String[]) this.throwsList.toArray(Const.EMPTY_STRING_ARRAY);
    }

    public InstructionList getInstructionList() {
        return this.il;
    }

    public LineNumberTable getLineNumberTable(ConstantPoolGen constantPoolGen) {
        int size = this.lineNumberList.size();
        LineNumber[] lineNumberArr = new LineNumber[size];
        Arrays.setAll(lineNumberArr, new IntFunction() { // from class: b1a
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return this.b.lineNumberList.get(i).getLineNumber();
            }
        });
        return new LineNumberTable(constantPoolGen.addUtf8(com.sun.tools.classfile.Attribute.LineNumberTable), (size * 4) + 2, lineNumberArr, constantPoolGen.getConstantPool());
    }

    public LineNumberGen[] getLineNumbers() {
        return (LineNumberGen[]) this.lineNumberList.toArray(LineNumberGen.EMPTY_ARRAY);
    }

    public LocalVariableTable getLocalVariableTable(final ConstantPoolGen constantPoolGen) {
        final LocalVariableGen[] localVariables = getLocalVariables();
        int length = localVariables.length;
        LocalVariable[] localVariableArr = new LocalVariable[length];
        Arrays.setAll(localVariableArr, new IntFunction() { // from class: i1a
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return localVariables[i].getLocalVariable(constantPoolGen);
            }
        });
        return new LocalVariableTable(constantPoolGen.addUtf8(com.sun.tools.classfile.Attribute.LocalVariableTable), (length * 10) + 2, localVariableArr, constantPoolGen.getConstantPool());
    }

    public LocalVariableTypeTable getLocalVariableTypeTable() {
        return this.localVariableTypeTable;
    }

    public LocalVariableGen[] getLocalVariables() {
        InstructionList instructionList;
        InstructionList instructionList2;
        int size = this.variableList.size();
        LocalVariableGen[] localVariableGenArr = new LocalVariableGen[size];
        this.variableList.toArray(localVariableGenArr);
        for (int i = 0; i < size; i++) {
            if (localVariableGenArr[i].getStart() == null && (instructionList2 = this.il) != null) {
                localVariableGenArr[i].setStart(instructionList2.getStart());
            }
            if (localVariableGenArr[i].getEnd() == null && (instructionList = this.il) != null) {
                localVariableGenArr[i].setEnd(instructionList.getEnd());
            }
        }
        if (size > 1) {
            Arrays.sort(localVariableGenArr, Comparator.comparingInt(new ToIntFunction() { // from class: d1a
                @Override // java.util.function.ToIntFunction
                public final int applyAsInt(Object obj) {
                    return ((LocalVariableGen) obj).getIndex();
                }
            }));
        }
        return localVariableGenArr;
    }

    public int getMaxLocals() {
        return this.maxLocals;
    }

    public Method getMethod() {
        LocalVariableTable localVariableTable;
        Attribute lineNumberTable;
        Attribute attribute;
        Attribute attribute2;
        String signature = getSignature();
        ConstantPoolGen constantPool = super.getConstantPool();
        int iAddUtf8 = constantPool.addUtf8(super.getName());
        int iAddUtf9 = constantPool.addUtf8(signature);
        InstructionList instructionList = this.il;
        byte[] byteCode = instructionList != null ? instructionList.getByteCode() : null;
        if (this.variableList.isEmpty() || this.stripAttributes) {
            localVariableTable = null;
        } else {
            updateLocalVariableTable(getLocalVariableTable(constantPool));
            localVariableTable = getLocalVariableTable(constantPool);
            addCodeAttribute(localVariableTable);
        }
        if (this.localVariableTypeTable != null) {
            if (localVariableTable != null) {
                adjustLocalVariableTypeTable(localVariableTable);
            }
            addCodeAttribute(this.localVariableTypeTable);
        }
        if (this.lineNumberList.isEmpty() || this.stripAttributes) {
            lineNumberTable = null;
        } else {
            lineNumberTable = getLineNumberTable(constantPool);
            addCodeAttribute(lineNumberTable);
        }
        Attribute[] codeAttributes = getCodeAttributes();
        int length = 0;
        for (Attribute attribute3 : codeAttributes) {
            length += attribute3.getLength() + 6;
        }
        CodeException[] codeExceptions = getCodeExceptions();
        int length2 = codeExceptions.length * 8;
        if (byteCode == null || isAbstract() || isNative()) {
            attribute = null;
        } else {
            for (Attribute attribute4 : getAttributes()) {
                if (attribute4 instanceof Code) {
                    removeAttribute(attribute4);
                }
            }
            Attribute code = new Code(constantPool.addUtf8(com.sun.tools.classfile.Attribute.Code), length + byteCode.length + 10 + length2 + 2, this.maxStack, this.maxLocals, byteCode, codeExceptions, codeAttributes, constantPool.getConstantPool());
            addAttribute(code);
            attribute = code;
        }
        Attribute[] attributeArrAddRuntimeAnnotationsAsAttribute = addRuntimeAnnotationsAsAttribute(constantPool);
        Attribute[] attributeArrAddRuntimeParameterAnnotationsAsAttribute = addRuntimeParameterAnnotationsAsAttribute(constantPool);
        if (this.throwsList.isEmpty()) {
            attribute2 = null;
        } else {
            Attribute exceptionTable = getExceptionTable(constantPool);
            addAttribute(exceptionTable);
            attribute2 = exceptionTable;
        }
        Attribute attribute5 = lineNumberTable;
        Method method = new Method(super.getAccessFlags(), iAddUtf8, iAddUtf9, getAttributes(), constantPool.getConstantPool());
        if (localVariableTable != null) {
            removeCodeAttribute(localVariableTable);
        }
        Attribute attribute6 = this.localVariableTypeTable;
        if (attribute6 != null) {
            removeCodeAttribute(attribute6);
        }
        if (attribute5 != null) {
            removeCodeAttribute(attribute5);
        }
        if (attribute != null) {
            removeAttribute(attribute);
        }
        if (attribute2 != null) {
            removeAttribute(attribute2);
        }
        removeRuntimeAttributes(attributeArrAddRuntimeAnnotationsAsAttribute);
        removeRuntimeAttributes(attributeArrAddRuntimeParameterAnnotationsAsAttribute);
        return method;
    }

    public Type getReturnType() {
        return getType();
    }

    @Override // com.sun.org.apache.bcel.internal.generic.FieldGenOrMethodGen
    public String getSignature() {
        return Type.getMethodSignature(super.getType(), this.argTypes);
    }

    public int hashCode() {
        return bcelComparator.hashCode(this);
    }

    public void removeCodeAttribute(Attribute attribute) {
        this.codeAttrsList.remove(attribute);
    }

    public void removeCodeAttributes() {
        this.localVariableTypeTable = null;
        this.codeAttrsList.clear();
    }

    public void removeException(String str) {
        this.throwsList.remove(str);
    }

    public void removeExceptionHandler(CodeExceptionGen codeExceptionGen) {
        this.exceptionList.remove(codeExceptionGen);
    }

    public void removeExceptionHandlers() {
        this.exceptionList.clear();
    }

    public void removeExceptions() {
        this.throwsList.clear();
    }

    public void removeLineNumber(LineNumberGen lineNumberGen) {
        this.lineNumberList.remove(lineNumberGen);
    }

    public void removeLineNumbers() {
        this.lineNumberList.clear();
    }

    public void removeLocalVariable(LocalVariableGen localVariableGen) {
        this.variableList.remove(localVariableGen);
    }

    public void removeLocalVariableTypeTable() {
        this.localVariableTypeTable = null;
    }

    public void removeLocalVariables() {
        this.variableList.clear();
    }

    public void removeNOPs() {
        InstructionList instructionList = this.il;
        if (instructionList != null) {
            InstructionHandle start = instructionList.getStart();
            while (start != null) {
                InstructionHandle next = start.getNext();
                if (next != null && (start.getInstruction() instanceof NOP)) {
                    try {
                        this.il.delete(start);
                    } catch (TargetLostException e) {
                        for (InstructionHandle instructionHandle : e.getTargets()) {
                            for (InstructionTargeter instructionTargeter : instructionHandle.getTargeters()) {
                                instructionTargeter.updateTarget(instructionHandle, next);
                            }
                        }
                    }
                }
                start = next;
            }
        }
    }

    public void removeObserver(MethodObserver methodObserver) {
        List<MethodObserver> list = this.observers;
        if (list != null) {
            list.remove(methodObserver);
        }
    }

    public void removeRuntimeAttributes(Attribute[] attributeArr) {
        for (Attribute attribute : attributeArr) {
            removeAttribute(attribute);
        }
    }

    public void setArgumentName(int i, String str) {
        this.argNames[i] = str;
    }

    public void setArgumentNames(String[] strArr) {
        this.argNames = strArr;
    }

    public void setArgumentType(int i, Type type) {
        this.argTypes[i] = type;
    }

    public void setArgumentTypes(Type[] typeArr) {
        this.argTypes = typeArr;
    }

    public void setClassName(String str) {
        this.className = str;
    }

    public void setInstructionList(InstructionList instructionList) {
        this.il = instructionList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setMaxLocals() {
        int index;
        if (this.il == null) {
            this.maxLocals = 0;
            return;
        }
        int size = !isStatic() ? 1 : 0;
        Type[] typeArr = this.argTypes;
        if (typeArr != null) {
            for (Type type : typeArr) {
                size += type.getSize();
            }
        }
        for (InstructionHandle start = this.il.getStart(); start != null; start = start.getNext()) {
            Instruction instruction = start.getInstruction();
            if (((instruction instanceof LocalVariableInstruction) || (instruction instanceof RET) || (instruction instanceof IINC)) && (index = ((IndexedInstruction) instruction).getIndex() + ((TypedInstruction) instruction).getType(super.getConstantPool()).getSize()) > size) {
                size = index;
            }
        }
        this.maxLocals = size;
    }

    public void setMaxStack() {
        if (this.il != null) {
            this.maxStack = getMaxStack(super.getConstantPool(), this.il, getExceptionHandlers());
        } else {
            this.maxStack = 0;
        }
    }

    public void setReturnType(Type type) {
        setType(type);
    }

    public void stripAttributes(boolean z) {
        this.stripAttributes = z;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(Utility.methodSignatureToString(Type.getMethodSignature(super.getType(), this.argTypes), super.getName(), Utility.accessToString(super.getAccessFlags()), true, getLocalVariableTable(super.getConstantPool())));
        for (Attribute attribute : getAttributes()) {
            if (!(attribute instanceof Code) && !(attribute instanceof ExceptionTable)) {
                sb.append(" [");
                sb.append(attribute);
                sb.append("]");
            }
        }
        if (!this.throwsList.isEmpty()) {
            for (String str : this.throwsList) {
                sb.append("\n\t\tthrows ");
                sb.append(str);
            }
        }
        return sb.toString();
    }

    public void update() {
        List<MethodObserver> list = this.observers;
        if (list != null) {
            Iterator<MethodObserver> it = list.iterator();
            while (it.hasNext()) {
                it.next().notify(this);
            }
        }
    }

    public void setMaxStack(int i) {
        this.maxStack = i;
    }

    public LocalVariableGen addLocalVariable(String str, Type type, int i, InstructionHandle instructionHandle, InstructionHandle instructionHandle2) {
        return addLocalVariable(str, type, i, instructionHandle, instructionHandle2, i);
    }

    public LocalVariableGen addLocalVariable(String str, Type type, InstructionHandle instructionHandle, InstructionHandle instructionHandle2) {
        return addLocalVariable(str, type, this.maxLocals, instructionHandle, instructionHandle2);
    }

    public void setMaxLocals(int i) {
        this.maxLocals = i;
    }

    public int getMaxStack() {
        return this.maxStack;
    }

    public MethodGen(int i, Type type, Type[] typeArr, String[] strArr, String str, String str2, InstructionList instructionList, ConstantPoolGen constantPoolGen) {
        InstructionHandle start;
        super(i);
        this.variableList = new ArrayList();
        this.lineNumberList = new ArrayList();
        this.exceptionList = new ArrayList();
        this.throwsList = new ArrayList();
        this.codeAttrsList = new ArrayList();
        setType(type);
        setArgumentTypes(typeArr);
        setArgumentNames(strArr);
        setName(str);
        setClassName(str2);
        setInstructionList(instructionList);
        setConstantPool(constantPoolGen);
        boolean z = isAbstract() || isNative();
        if (z) {
            start = null;
        } else {
            start = instructionList.getStart();
            if (!isStatic() && str2 != null) {
                addLocalVariable(PsiKeyword.THIS, ObjectType.getInstance(str2), start, null);
            }
        }
        if (typeArr != null) {
            int length = typeArr.length;
            for (Type type2 : typeArr) {
                if (Type.VOID == type2) {
                    throw new ClassGenException("'void' is an illegal argument type for a method");
                }
            }
            if (strArr != null) {
                if (length != strArr.length) {
                    throw new ClassGenException("Mismatch in argument array lengths: " + length + " vs. " + strArr.length);
                }
            } else {
                strArr = new String[length];
                for (int i2 = 0; i2 < length; i2++) {
                    strArr[i2] = Constants.ELEMNAME_ARG_STRING + i2;
                }
                setArgumentNames(strArr);
            }
            if (z) {
                return;
            }
            for (int i3 = 0; i3 < length; i3++) {
                addLocalVariable(strArr[i3], typeArr[i3], start, null);
            }
        }
    }
}
