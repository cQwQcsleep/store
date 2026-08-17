package com.sun.org.apache.xalan.internal.xsltc.compiler.util;

import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.bcel.internal.classfile.Field;
import com.sun.org.apache.bcel.internal.classfile.Method;
import com.sun.org.apache.bcel.internal.generic.ALOAD;
import com.sun.org.apache.bcel.internal.generic.ASTORE;
import com.sun.org.apache.bcel.internal.generic.BranchHandle;
import com.sun.org.apache.bcel.internal.generic.BranchInstruction;
import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.DLOAD;
import com.sun.org.apache.bcel.internal.generic.DSTORE;
import com.sun.org.apache.bcel.internal.generic.FLOAD;
import com.sun.org.apache.bcel.internal.generic.FSTORE;
import com.sun.org.apache.bcel.internal.generic.GETFIELD;
import com.sun.org.apache.bcel.internal.generic.GOTO;
import com.sun.org.apache.bcel.internal.generic.ICONST;
import com.sun.org.apache.bcel.internal.generic.ILOAD;
import com.sun.org.apache.bcel.internal.generic.INVOKEINTERFACE;
import com.sun.org.apache.bcel.internal.generic.INVOKESPECIAL;
import com.sun.org.apache.bcel.internal.generic.INVOKESTATIC;
import com.sun.org.apache.bcel.internal.generic.INVOKEVIRTUAL;
import com.sun.org.apache.bcel.internal.generic.ISTORE;
import com.sun.org.apache.bcel.internal.generic.IfInstruction;
import com.sun.org.apache.bcel.internal.generic.IndexedInstruction;
import com.sun.org.apache.bcel.internal.generic.Instruction;
import com.sun.org.apache.bcel.internal.generic.InstructionConst;
import com.sun.org.apache.bcel.internal.generic.InstructionHandle;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.bcel.internal.generic.InstructionTargeter;
import com.sun.org.apache.bcel.internal.generic.LLOAD;
import com.sun.org.apache.bcel.internal.generic.LSTORE;
import com.sun.org.apache.bcel.internal.generic.LocalVariableGen;
import com.sun.org.apache.bcel.internal.generic.LocalVariableInstruction;
import com.sun.org.apache.bcel.internal.generic.MethodGen;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.bcel.internal.generic.PUTFIELD;
import com.sun.org.apache.bcel.internal.generic.RET;
import com.sun.org.apache.bcel.internal.generic.Select;
import com.sun.org.apache.bcel.internal.generic.StackInstruction;
import com.sun.org.apache.bcel.internal.generic.TargetLostException;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Constants;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Pattern;
import com.sun.org.apache.xalan.internal.xsltc.compiler.XSLTC;
import com.sun.org.apache.xpath.internal.compiler.Keywords;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class MethodGenerator extends MethodGen implements Constants {
    private static final int DOM_INDEX = 1;
    private static final String END_ELEMENT_SIG = "(Ljava/lang/String;)V";
    private static final int HANDLER_INDEX = 3;
    protected static final int INVALID_INDEX = -1;
    private static final int ITERATOR_INDEX = 2;
    private static final int MAX_BRANCH_TARGET_OFFSET = 32767;
    private static final int MAX_METHOD_SIZE = 65535;
    private static final int MINIMUM_OUTLINEABLE_CHUNK_SIZE = 1000;
    private static final int MIN_BRANCH_TARGET_OFFSET = -32768;
    private static final String START_ELEMENT_SIG = "(Ljava/lang/String;)V";
    private static final int TARGET_METHOD_SIZE = 60000;
    private boolean _allocatorInit;
    private final Instruction _aloadDom;
    private final Instruction _aloadHandler;
    private final Instruction _aloadIterator;
    private final Instruction _astoreDom;
    private final Instruction _astoreHandler;
    private final Instruction _astoreIterator;
    private final Instruction _attribute;
    private final Instruction _endDocument;
    private final Instruction _endElement;
    private Instruction _iloadCurrent;
    private Instruction _istoreCurrent;
    private LocalVariableRegistry _localVariableRegistry;
    private final Instruction _namespace;
    private final Instruction _nextNode;
    private Map<Pattern, InstructionList> _preCompiled;
    private final Instruction _reset;
    private final Instruction _setStartNode;
    private SlotAllocator _slotAllocator;
    private final Instruction _startDocument;
    private final Instruction _startElement;
    private final Instruction _uniqueAttribute;
    private int m_openChunks;
    private int m_totalChunks;

    public class Chunk implements Comparable<Object> {
        private InstructionHandle m_end;
        private int m_size;
        private InstructionHandle m_start;

        public Chunk(InstructionHandle instructionHandle, InstructionHandle instructionHandle2) {
            this.m_start = instructionHandle;
            this.m_end = instructionHandle2;
            this.m_size = instructionHandle2.getPosition() - instructionHandle.getPosition();
        }

        @Override // java.lang.Comparable
        public int compareTo(Object obj) {
            return getChunkSize() - ((Chunk) obj).getChunkSize();
        }

        public InstructionHandle getChunkEnd() {
            return this.m_end;
        }

        public int getChunkSize() {
            return this.m_size;
        }

        public InstructionHandle getChunkStart() {
            return this.m_start;
        }

        public boolean isAdjacentTo(Chunk chunk) {
            return getChunkEnd().getNext() == chunk.getChunkStart();
        }
    }

    public class LocalVariableRegistry {
        protected List<Object> _variables = new ArrayList();
        protected Map<String, Object> _nameToLVGMap = new LinkedHashMap();

        public LocalVariableRegistry() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public LocalVariableGen[] getLocals() {
            ArrayList arrayList = new ArrayList();
            Iterator<Map.Entry<String, Object>> it = this._nameToLVGMap.entrySet().iterator();
            while (it.hasNext()) {
                Object value = it.next().getValue();
                if (value != null) {
                    if (value instanceof ArrayList) {
                        List list = (List) value;
                        for (int i = 0; i < list.size(); i++) {
                            arrayList.add((LocalVariableGen) list.get(i));
                        }
                    } else {
                        arrayList.add((LocalVariableGen) value);
                    }
                }
            }
            LocalVariableGen[] localVariableGenArr = new LocalVariableGen[arrayList.size()];
            arrayList.toArray(localVariableGenArr);
            return localVariableGenArr;
        }

        public LocalVariableGen lookUpByName(String str) {
            Object obj = this._nameToLVGMap.get(str);
            if (!(obj instanceof ArrayList)) {
                return (LocalVariableGen) obj;
            }
            List list = (List) obj;
            LocalVariableGen localVariableGen = null;
            for (int i = 0; i < list.size(); i++) {
                localVariableGen = (LocalVariableGen) list.get(i);
                if (localVariableGen.getName() == null) {
                    if (str == null) {
                        return localVariableGen;
                    }
                } else {
                    if (localVariableGen.getName().equals(str)) {
                        return localVariableGen;
                    }
                }
            }
            return localVariableGen;
        }

        public LocalVariableGen lookupRegisteredLocalVariable(int i, int i2) {
            List<Object> list = this._variables;
            Object obj = list != null ? list.get(i) : null;
            if (obj != null) {
                if (obj instanceof LocalVariableGen) {
                    LocalVariableGen localVariableGen = (LocalVariableGen) obj;
                    if (MethodGenerator.this.offsetInLocalVariableGenRange(localVariableGen, i2)) {
                        return localVariableGen;
                    }
                } else {
                    for (LocalVariableGen localVariableGen2 : (List) obj) {
                        if (MethodGenerator.this.offsetInLocalVariableGenRange(localVariableGen2, i2)) {
                            return localVariableGen2;
                        }
                    }
                }
            }
            return null;
        }

        public void registerByName(LocalVariableGen localVariableGen) {
            List list;
            Object obj = this._nameToLVGMap.get(localVariableGen.getName());
            if (obj == null) {
                this._nameToLVGMap.put(localVariableGen.getName(), localVariableGen);
                return;
            }
            if (obj instanceof ArrayList) {
                list = (List) obj;
                list.add(localVariableGen);
            } else {
                ArrayList arrayList = new ArrayList();
                arrayList.add((LocalVariableGen) obj);
                arrayList.add(localVariableGen);
                list = arrayList;
            }
            this._nameToLVGMap.put(localVariableGen.getName(), list);
        }

        public void registerLocalVariable(LocalVariableGen localVariableGen) {
            List<Object> list;
            int index = localVariableGen.getIndex();
            int size = this._variables.size();
            if (index >= size) {
                while (true) {
                    list = this._variables;
                    if (size >= index) {
                        break;
                    }
                    list.add(null);
                    size++;
                }
                list.add(localVariableGen);
            } else {
                Object obj = this._variables.get(index);
                if (obj == null) {
                    this._variables.set(index, localVariableGen);
                } else if (obj instanceof LocalVariableGen) {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add((LocalVariableGen) obj);
                    arrayList.add(localVariableGen);
                    this._variables.set(index, arrayList);
                } else {
                    ((List) obj).add(localVariableGen);
                }
            }
            registerByName(localVariableGen);
        }

        public void removeByNameTracking(LocalVariableGen localVariableGen) {
            Object obj = this._nameToLVGMap.get(localVariableGen.getName());
            if (!(obj instanceof ArrayList)) {
                this._nameToLVGMap.remove(localVariableGen.getName());
                return;
            }
            List list = (List) obj;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) == localVariableGen) {
                    list.remove(i);
                    return;
                }
            }
        }
    }

    public MethodGenerator(int i, com.sun.org.apache.bcel.internal.generic.Type type, com.sun.org.apache.bcel.internal.generic.Type[] typeArr, String[] strArr, String str, String str2, InstructionList instructionList, ConstantPoolGen constantPoolGen) {
        super(i, type, typeArr, strArr, str, str2, instructionList, constantPoolGen);
        this._allocatorInit = false;
        this._preCompiled = new HashMap();
        this.m_totalChunks = 0;
        this.m_openChunks = 0;
        this._astoreHandler = new ASTORE(3);
        this._aloadHandler = new ALOAD(3);
        this._astoreIterator = new ASTORE(2);
        this._aloadIterator = new ALOAD(2);
        this._aloadDom = new ALOAD(1);
        this._astoreDom = new ASTORE(1);
        this._startElement = new INVOKEINTERFACE(constantPoolGen.addInterfaceMethodref(Constants.TRANSLET_OUTPUT_INTERFACE, "startElement", "(Ljava/lang/String;)V"), 2);
        this._endElement = new INVOKEINTERFACE(constantPoolGen.addInterfaceMethodref(Constants.TRANSLET_OUTPUT_INTERFACE, "endElement", "(Ljava/lang/String;)V"), 2);
        this._attribute = new INVOKEINTERFACE(constantPoolGen.addInterfaceMethodref(Constants.TRANSLET_OUTPUT_INTERFACE, Constants.ADD_ATTRIBUTE, "(Ljava/lang/String;Ljava/lang/String;)V"), 3);
        this._uniqueAttribute = new INVOKEINTERFACE(constantPoolGen.addInterfaceMethodref(Constants.TRANSLET_OUTPUT_INTERFACE, "addUniqueAttribute", "(Ljava/lang/String;Ljava/lang/String;I)V"), 4);
        this._namespace = new INVOKEINTERFACE(constantPoolGen.addInterfaceMethodref(Constants.TRANSLET_OUTPUT_INTERFACE, "namespaceAfterStartElement", "(Ljava/lang/String;Ljava/lang/String;)V"), 3);
        this._startDocument = new INVOKEINTERFACE(constantPoolGen.addInterfaceMethodref(Constants.TRANSLET_OUTPUT_INTERFACE, "startDocument", "()V"), 1);
        this._endDocument = new INVOKEINTERFACE(constantPoolGen.addInterfaceMethodref(Constants.TRANSLET_OUTPUT_INTERFACE, "endDocument", "()V"), 1);
        this._setStartNode = new INVOKEINTERFACE(constantPoolGen.addInterfaceMethodref(Constants.NODE_ITERATOR, Constants.SET_START_NODE, "(I)Lcom/sun/org/apache/xml/internal/dtm/DTMAxisIterator;"), 2);
        this._reset = new INVOKEINTERFACE(constantPoolGen.addInterfaceMethodref(Constants.NODE_ITERATOR, Constants.RESET, "()Lcom/sun/org/apache/xml/internal/dtm/DTMAxisIterator;"), 1);
        this._nextNode = new INVOKEINTERFACE(constantPoolGen.addInterfaceMethodref(Constants.NODE_ITERATOR, Constants.NEXT, "()I"), 1);
        SlotAllocator slotAllocator = new SlotAllocator();
        this._slotAllocator = slotAllocator;
        slotAllocator.initialize(getLocalVariableRegistry().getLocals());
        this._allocatorInit = true;
    }

    private List<Chunk> getCandidateChunks(ClassGenerator classGenerator, int i) {
        InstructionHandle next;
        int size;
        Iterator<InstructionHandle> it = getInstructionList().iterator();
        ArrayList arrayList = new ArrayList();
        List arrayList2 = new ArrayList();
        Stack stack = new Stack();
        if (this.m_openChunks != 0) {
            throw new InternalError(new ErrorMsg(ErrorMsg.OUTLINE_ERR_UNBALANCED_MARKERS).toString());
        }
        boolean z = true;
        boolean z2 = false;
        do {
            List list = null;
            next = it.hasNext() ? it.next() : null;
            Instruction instruction = next != null ? next.getInstruction() : null;
            if (z) {
                arrayList2.add(next);
                z2 = true;
                z = false;
            }
            if (instruction instanceof OutlineableChunkStart) {
                if (z2) {
                    stack.push(arrayList2);
                    arrayList2 = new ArrayList();
                }
                arrayList2.add(next);
                z2 = true;
            } else if (next == null || (instruction instanceof OutlineableChunkEnd)) {
                if (!z2) {
                    list = arrayList2;
                    arrayList2 = (List) stack.pop();
                }
                if ((next != null ? next.getPosition() : i) - ((InstructionHandle) arrayList2.get(arrayList2.size() - 1)).getPosition() <= TARGET_METHOD_SIZE) {
                    arrayList2.add(next);
                } else {
                    if (!z2 && (size = list.size() / 2) > 0) {
                        Chunk[] chunkArr = new Chunk[size];
                        for (int i2 = 0; i2 < size; i2++) {
                            int i3 = i2 * 2;
                            chunkArr[i2] = new Chunk((InstructionHandle) list.get(i3), (InstructionHandle) list.get(i3 + 1));
                        }
                        for (Chunk chunk : mergeAdjacentChunks(chunkArr)) {
                            int chunkSize = chunk.getChunkSize();
                            if (chunkSize >= MINIMUM_OUTLINEABLE_CHUNK_SIZE && chunkSize <= TARGET_METHOD_SIZE) {
                                arrayList.add(chunk);
                            }
                        }
                    }
                    arrayList2.remove(arrayList2.size() - 1);
                }
                z2 = (arrayList2.size() & 1) == 1;
            }
        } while (next != null);
        return arrayList;
    }

    private LocalVariableRegistry getLocalVariableRegistry() {
        if (this._localVariableRegistry == null) {
            this._localVariableRegistry = new LocalVariableRegistry();
        }
        return this._localVariableRegistry;
    }

    private static Instruction loadLocal(int i, com.sun.org.apache.bcel.internal.generic.Type type) {
        if (type != com.sun.org.apache.bcel.internal.generic.Type.BOOLEAN && type != com.sun.org.apache.bcel.internal.generic.Type.INT && type != com.sun.org.apache.bcel.internal.generic.Type.SHORT) {
            if (type == com.sun.org.apache.bcel.internal.generic.Type.LONG) {
                return new LLOAD(i);
            }
            if (type != com.sun.org.apache.bcel.internal.generic.Type.BYTE && type != com.sun.org.apache.bcel.internal.generic.Type.CHAR) {
                if (type == com.sun.org.apache.bcel.internal.generic.Type.FLOAT) {
                    return new FLOAD(i);
                }
                return type == com.sun.org.apache.bcel.internal.generic.Type.DOUBLE ? new DLOAD(i) : new ALOAD(i);
            }
            return new ILOAD(i);
        }
        return new ILOAD(i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private List<Chunk> mergeAdjacentChunks(Chunk[] chunkArr) {
        int i;
        int[] iArr = new int[chunkArr.length];
        int[] iArr2 = new int[chunkArr.length];
        boolean[] zArr = new boolean[chunkArr.length];
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 1; i5 < chunkArr.length; i5++) {
            if (!chunkArr[i5 - 1].isAdjacentTo(chunkArr[i5])) {
                int i6 = i5 - i2;
                if (i3 < i6) {
                    i3 = i6;
                }
                if (i6 > 1) {
                    iArr2[i4] = i6;
                    iArr[i4] = i2;
                    i4++;
                }
                i2 = i5;
            }
        }
        if (chunkArr.length - i2 > 1) {
            int length = chunkArr.length - i2;
            if (i3 < length) {
                i3 = length;
            }
            iArr2[i4] = chunkArr.length - i2;
            iArr[i4] = i2;
            i4++;
        }
        for (int i7 = 1; i3 > i7; i7 = 1) {
            int i8 = 0;
            while (i8 < i4) {
                i = i7;
                int i9 = iArr[i8];
                int i10 = (iArr2[i8] + i9) - (i == true ? 1 : 0);
                boolean z = false;
                boolean z2 = i;
                while (true) {
                    int i11 = i9 + i3;
                    int i12 = i11 - 1;
                    if (i12 > i10 || z) {
                        break;
                    }
                    int chunkSize = 0;
                    for (int i13 = i9; i13 <= i12; i13++) {
                        chunkSize += chunkArr[i13].getChunkSize();
                    }
                    if (chunkSize <= TARGET_METHOD_SIZE) {
                        for (int i14 = i9; i14 <= i12; i14++) {
                            zArr[i14] = z2;
                        }
                        arrayList.add(new Chunk(chunkArr[i9].getChunkStart(), chunkArr[i12].getChunkEnd()));
                        iArr2[i8] = iArr[i8] - i9;
                        int i15 = i10 - i12;
                        if (i15 >= 2) {
                            iArr[i4] = i11;
                            iArr2[i4] = i15;
                            i4++;
                        }
                        z = true;
                    }
                    i9++;
                    z2 = true;
                }
                i8++;
                i = 1;
            }
            i = i7;
            i3--;
        }
        for (int i16 = 0; i16 < chunkArr.length; i16++) {
            if (!zArr[i16]) {
                arrayList.add(chunkArr[i16]);
            }
        }
        return arrayList;
    }

    /* JADX WARN: Code duplicated, block: B:107:0x03ba  */
    /* JADX WARN: Code duplicated, block: B:152:0x02ba A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:157:0x03bf A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:64:0x02a1  */
    /* JADX WARN: Code duplicated, block: B:65:0x02a7  */
    /* JADX WARN: Code duplicated, block: B:67:0x02ab  */
    /* JADX WARN: Code duplicated, block: B:94:0x038f  */
    /* JADX WARN: Code duplicated, block: B:97:0x0398  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v1 */
    /* JADX WARN: Type inference failed for: r10v2, types: [com.sun.org.apache.bcel.internal.generic.InstructionList] */
    /* JADX WARN: Type inference failed for: r10v23 */
    /* JADX WARN: Type inference failed for: r29v0, types: [com.sun.org.apache.bcel.internal.generic.InstructionList] */
    /* JADX WARN: Type inference failed for: r29v1 */
    /* JADX WARN: Type inference failed for: r29v2 */
    /* JADX WARN: Type inference failed for: r29v3 */
    /* JADX WARN: Type inference failed for: r29v4 */
    /* JADX WARN: Type inference failed for: r29v5 */
    /* JADX WARN: Type inference failed for: r29v6 */
    /* JADX WARN: Type inference failed for: r29v7 */
    /* JADX WARN: Type inference failed for: r29v8 */
    /* JADX WARN: Type inference failed for: r4v7, types: [com.sun.org.apache.bcel.internal.generic.Instruction] */
    private Method outline(InstructionHandle instructionHandle, InstructionHandle instructionHandle2, String str, ClassGenerator classGenerator) {
        InstructionHandle instructionHandleAppend;
        InstructionHandle instructionHandle3;
        HashMap map;
        InstructionHandle instructionHandle4;
        MethodGenerator methodGenerator;
        int index;
        int i;
        LocalVariableGen localVariableGen;
        LinkedHashMap linkedHashMap;
        InstructionList instructionList;
        InstructionList instructionList2;
        InstructionHandle next;
        ?? r29;
        if (getExceptionHandlers().length != 0) {
            throw new InternalError(new ErrorMsg(ErrorMsg.OUTLINE_ERR_TRY_CATCH).toString());
        }
        int position = instructionHandle.getPosition();
        int position2 = instructionHandle2.getPosition() + instructionHandle2.getInstruction().getLength();
        ConstantPoolGen constantPool = getConstantPool();
        InstructionList instructionList3 = new InstructionList();
        XSLTC xsltc = classGenerator.getParser().getXSLTC();
        String helperClassName = xsltc.getHelperClassName();
        com.sun.org.apache.bcel.internal.generic.Type[] typeArr = {new ObjectType(helperClassName).toJCType()};
        String[] strArr = {"copyLocals"};
        boolean z = (getAccessFlags() & 8) != 0;
        MethodGenerator methodGenerator2 = new MethodGenerator(z ? 26 : 18, com.sun.org.apache.bcel.internal.generic.Type.VOID, typeArr, strArr, str, getClassName(), instructionList3, constantPool);
        ?? r10 = instructionList3;
        MethodGenerator methodGenerator3 = this;
        ClassGenerator classGenerator2 = new ClassGenerator(helperClassName, Constants.OBJECT_CLASS, helperClassName + ".java", 49, null, classGenerator.getStylesheet()) { // from class: com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator.1
            @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator
            public boolean isExternal() {
                return true;
            }
        };
        ConstantPoolGen constantPool2 = classGenerator2.getConstantPool();
        classGenerator2.addEmptyConstructor(1);
        InstructionHandle next2 = instructionHandle2.getNext();
        InstructionList instructionList4 = new InstructionList();
        InstructionList instructionList5 = new InstructionList();
        InstructionList instructionList6 = new InstructionList();
        InstructionList instructionList7 = new InstructionList();
        InstructionHandle instructionHandleAppend2 = instructionList4.append(new NEW(constantPool.addClass(helperClassName)));
        StackInstruction stackInstruction = InstructionConst.DUP;
        instructionList4.append(stackInstruction);
        instructionList4.append(stackInstruction);
        InstructionHandle instructionHandle5 = instructionHandleAppend2;
        instructionList4.append(new INVOKESPECIAL(constantPool.addMethodref(helperClassName, Const.CONSTRUCTOR_NAME, "()V")));
        if (z) {
            instructionHandleAppend = instructionList5.append(new INVOKESTATIC(constantPool.addMethodref(classGenerator.getClassName(), str, methodGenerator2.getSignature())));
        } else {
            instructionList5.append(InstructionConst.THIS);
            instructionList5.append(InstructionConst.SWAP);
            instructionHandleAppend = instructionList5.append(new INVOKEVIRTUAL(constantPool.addMethodref(classGenerator.getClassName(), str, methodGenerator2.getSignature())));
        }
        HashMap map2 = new HashMap();
        HashMap map3 = new HashMap();
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        InstructionHandle instructionHandle6 = instructionHandleAppend;
        LinkedHashMap linkedHashMap3 = new LinkedHashMap();
        LinkedHashMap linkedHashMap4 = linkedHashMap2;
        InstructionHandle next3 = instructionHandle;
        InstructionList instructionList8 = instructionList5;
        boolean z2 = false;
        int i2 = 0;
        Object obj = null;
        InstructionHandle instructionHandle7 = null;
        while (next3 != next2) {
            InstructionHandle instructionHandle8 = next2;
            Instruction instruction = next3.getInstruction();
            InstructionList instructionList9 = instructionList7;
            if (instruction instanceof MarkerInstruction) {
                if (next3.hasTargeters()) {
                    if (instruction instanceof OutlineableChunkEnd) {
                        map2.put(next3, obj);
                    } else if (!z2) {
                        instructionHandle7 = next3;
                        z2 = true;
                    }
                }
                instructionList = instructionList8;
                instructionList2 = instructionList9;
                linkedHashMap = linkedHashMap4;
            } else {
                ?? Copy = instruction.copy();
                Object objAppend = Copy instanceof BranchInstruction ? r10.append((BranchInstruction) Copy) : r10.append(Copy);
                if ((Copy instanceof LocalVariableInstruction) || (Copy instanceof RET)) {
                    int index2 = ((IndexedInstruction) Copy).getIndex();
                    ?? r210 = r10;
                    LocalVariableGen localVariableGenLookupRegisteredLocalVariable = methodGenerator3.getLocalVariableRegistry().lookupRegisteredLocalVariable(index2, next3.getPosition());
                    if (map3.get(localVariableGenLookupRegisteredLocalVariable) == null) {
                        boolean zOffsetInLocalVariableGenRange = methodGenerator3.offsetInLocalVariableGenRange(localVariableGenLookupRegisteredLocalVariable, position != 0 ? position - 1 : 0);
                        boolean zOffsetInLocalVariableGenRange2 = methodGenerator3.offsetInLocalVariableGenRange(localVariableGenLookupRegisteredLocalVariable, position2 + 1);
                        if (zOffsetInLocalVariableGenRange || zOffsetInLocalVariableGenRange2) {
                            String name = localVariableGenLookupRegisteredLocalVariable.getName();
                            com.sun.org.apache.bcel.internal.generic.Type type = localVariableGenLookupRegisteredLocalVariable.getType();
                            objAppend = objAppend;
                            LocalVariableGen localVariableGenAddLocalVariable = methodGenerator2.addLocalVariable(name, type, null, null);
                            int index3 = localVariableGenAddLocalVariable.getIndex();
                            String signature = type.getSignature();
                            map3.put(localVariableGenLookupRegisteredLocalVariable, localVariableGenAddLocalVariable);
                            int i3 = i2 + 1;
                            String str2 = "field" + i3;
                            classGenerator2.addField(new Field(1, constantPool2.addUtf8(str2), constantPool2.addUtf8(signature), null, constantPool2.getConstantPool()));
                            int iAddFieldref = constantPool.addFieldref(helperClassName, str2, signature);
                            if (zOffsetInLocalVariableGenRange) {
                                instructionList4.append(InstructionConst.DUP);
                                InstructionHandle instructionHandleAppend3 = instructionList4.append(loadLocal(index2, type));
                                instructionList4.append(new PUTFIELD(iAddFieldref));
                                if (!zOffsetInLocalVariableGenRange2) {
                                    linkedHashMap3.put(localVariableGenLookupRegisteredLocalVariable, instructionHandleAppend3);
                                }
                                instructionList6.append(InstructionConst.ALOAD_1);
                                instructionList6.append(new GETFIELD(iAddFieldref));
                                instructionList6.append(storeLocal(index3, type));
                            }
                            if (zOffsetInLocalVariableGenRange2) {
                                instructionList2 = instructionList9;
                                instructionList2.append(InstructionConst.ALOAD_1);
                                instructionList2.append(loadLocal(index3, type));
                                instructionList2.append(new PUTFIELD(iAddFieldref));
                                instructionList = instructionList8;
                                instructionList.append(InstructionConst.DUP);
                                instructionList.append(new GETFIELD(iAddFieldref));
                                InstructionHandle instructionHandleAppend4 = instructionList.append(storeLocal(index2, type));
                                linkedHashMap = linkedHashMap4;
                                if (!zOffsetInLocalVariableGenRange) {
                                    linkedHashMap.put(localVariableGenLookupRegisteredLocalVariable, instructionHandleAppend4);
                                }
                            } else {
                                linkedHashMap = linkedHashMap4;
                                instructionList = instructionList8;
                                instructionList2 = instructionList9;
                            }
                            i2 = i3;
                            r10 = r210;
                        }
                        obj = objAppend;
                        if (next3.hasTargeters()) {
                            map2 = map2;
                            map2.put(next3, obj);
                        } else {
                            map2 = map2;
                        }
                        if (z2) {
                            next = instructionHandle7;
                            do {
                                map2.put(next, obj);
                                next = next.getNext();
                            } while (next != next3);
                            instructionHandle7 = next;
                            z2 = false;
                        }
                    }
                    r29 = r210;
                } else {
                    r29 = r10;
                }
                instructionList = instructionList8;
                instructionList2 = instructionList9;
                linkedHashMap = linkedHashMap4;
                r10 = r29;
                obj = objAppend;
                if (next3.hasTargeters()) {
                    map2 = map2;
                    map2.put(next3, obj);
                } else {
                    map2 = map2;
                }
                if (z2) {
                    next = instructionHandle7;
                    do {
                        map2.put(next, obj);
                        next = next.getNext();
                    } while (next != next3);
                    instructionHandle7 = next;
                    z2 = false;
                }
            }
            next3 = next3.getNext();
            linkedHashMap4 = linkedHashMap;
            instructionList8 = instructionList;
            instructionList7 = instructionList2;
            r10 = r10;
            methodGenerator2 = methodGenerator2;
            map3 = map3;
            map2 = map2;
            obj = obj;
            next2 = instructionHandle8;
            methodGenerator3 = this;
        }
        LinkedHashMap linkedHashMap5 = linkedHashMap4;
        ?? r211 = r10;
        MethodGenerator methodGenerator4 = methodGenerator2;
        HashMap map4 = map2;
        HashMap map5 = map3;
        InstructionList instructionList10 = instructionList7;
        InstructionList instructionList11 = instructionList8;
        InstructionHandle start = r211.getStart();
        InstructionHandle next4 = instructionHandle;
        while (start != null) {
            Instruction instruction2 = next4.getInstruction();
            Cloneable instruction3 = start.getInstruction();
            if (instruction2 instanceof BranchInstruction) {
                BranchInstruction branchInstruction = (BranchInstruction) instruction3;
                BranchInstruction branchInstruction2 = (BranchInstruction) instruction2;
                branchInstruction.setTarget((InstructionHandle) map4.get(branchInstruction2.getTarget()));
                if (branchInstruction2 instanceof Select) {
                    InstructionHandle[] targets = ((Select) branchInstruction2).getTargets();
                    InstructionHandle[] targets2 = ((Select) branchInstruction).getTargets();
                    for (int i4 = 0; i4 < targets.length; i4++) {
                        targets2[i4] = (InstructionHandle) map4.get(targets[i4]);
                    }
                }
            } else {
                if ((instruction2 instanceof LocalVariableInstruction) || (instruction2 instanceof RET)) {
                    IndexedInstruction indexedInstruction = (IndexedInstruction) instruction3;
                    LocalVariableGen localVariableGenLookupRegisteredLocalVariable2 = getLocalVariableRegistry().lookupRegisteredLocalVariable(indexedInstruction.getIndex(), next4.getPosition());
                    map = map5;
                    LocalVariableGen localVariableGen2 = (LocalVariableGen) map.get(localVariableGenLookupRegisteredLocalVariable2);
                    if (localVariableGen2 == null) {
                        methodGenerator = methodGenerator4;
                        LocalVariableGen localVariableGenAddLocalVariable2 = methodGenerator.addLocalVariable(localVariableGenLookupRegisteredLocalVariable2.getName(), localVariableGenLookupRegisteredLocalVariable2.getType(), null, null);
                        index = localVariableGenAddLocalVariable2.getIndex();
                        map.put(localVariableGenLookupRegisteredLocalVariable2, localVariableGenAddLocalVariable2);
                        InstructionHandle instructionHandle9 = instructionHandle6;
                        linkedHashMap5.put(localVariableGenLookupRegisteredLocalVariable2, instructionHandle9);
                        linkedHashMap3.put(localVariableGenLookupRegisteredLocalVariable2, instructionHandle9);
                        instructionHandle4 = instructionHandle9;
                    } else {
                        instructionHandle4 = instructionHandle6;
                        methodGenerator = methodGenerator4;
                        index = localVariableGen2.getIndex();
                    }
                    indexedInstruction.setIndex(index);
                }
                if (next4.hasTargeters()) {
                    for (InstructionTargeter instructionTargeter : next4.getTargeters()) {
                        if (!(instructionTargeter instanceof LocalVariableGen) && ((LocalVariableGen) instructionTargeter).getEnd() == next4 && (localVariableGen = (LocalVariableGen) map.get(instructionTargeter)) != null) {
                            methodGenerator.removeLocalVariable(localVariableGen);
                        }
                    }
                }
                if (!(instruction2 instanceof MarkerInstruction)) {
                    start = start.getNext();
                }
                next4 = next4.getNext();
                methodGenerator4 = methodGenerator;
                instructionHandle6 = instructionHandle4;
                map5 = map;
                classGenerator2 = classGenerator2;
                map4 = map4;
            }
            classGenerator2 = classGenerator2;
            map4 = map4;
            instructionHandle4 = instructionHandle6;
            methodGenerator = methodGenerator4;
            map = map5;
            if (next4.hasTargeters()) {
                while (i < r9.length) {
                    if (!(instructionTargeter instanceof LocalVariableGen)) {
                    }
                }
            }
            if (!(instruction2 instanceof MarkerInstruction)) {
                start = start.getNext();
            }
            next4 = next4.getNext();
            methodGenerator4 = methodGenerator;
            instructionHandle6 = instructionHandle4;
            map5 = map;
            classGenerator2 = classGenerator2;
            map4 = map4;
        }
        ClassGenerator classGenerator3 = classGenerator2;
        InstructionHandle instructionHandle10 = instructionHandle6;
        MethodGenerator methodGenerator5 = methodGenerator4;
        instructionList11.append(InstructionConst.POP);
        for (Map.Entry entry : linkedHashMap5.entrySet()) {
            ((LocalVariableGen) entry.getKey()).setStart((InstructionHandle) entry.getValue());
        }
        for (Map.Entry entry2 : linkedHashMap3.entrySet()) {
            ((LocalVariableGen) entry2.getKey()).setEnd((InstructionHandle) entry2.getValue());
        }
        xsltc.dumpClass(classGenerator3.getJavaClass());
        InstructionList instructionList12 = getInstructionList();
        instructionList12.insert(instructionHandle, instructionList4);
        instructionList12.insert(instructionHandle, instructionList11);
        r211.insert(instructionList6);
        r211.append(instructionList10);
        r211.append(InstructionConst.RETURN);
        try {
            instructionList12.delete(instructionHandle, instructionHandle2);
        } catch (TargetLostException e) {
            for (InstructionHandle instructionHandle11 : e.getTargets()) {
                InstructionTargeter[] targeters = instructionHandle11.getTargeters();
                int i5 = 0;
                while (i5 < targeters.length) {
                    InstructionTargeter instructionTargeter2 = targeters[i5];
                    if (instructionTargeter2 instanceof LocalVariableGen) {
                        LocalVariableGen localVariableGen3 = (LocalVariableGen) instructionTargeter2;
                        if (localVariableGen3.getStart() == instructionHandle11) {
                            localVariableGen3.setStart(instructionHandle10);
                        }
                        if (localVariableGen3.getEnd() == instructionHandle11) {
                            localVariableGen3.setEnd(instructionHandle10);
                        }
                        instructionHandle3 = instructionHandle5;
                    } else {
                        instructionHandle3 = instructionHandle5;
                        instructionTargeter2.updateTarget(instructionHandle11, instructionHandle3);
                    }
                    i5++;
                    instructionHandle5 = instructionHandle3;
                }
            }
        }
        for (String str3 : getExceptions()) {
            methodGenerator5.addException(str3);
        }
        return methodGenerator5.getThisMethod();
    }

    private static Instruction storeLocal(int i, com.sun.org.apache.bcel.internal.generic.Type type) {
        if (type != com.sun.org.apache.bcel.internal.generic.Type.BOOLEAN && type != com.sun.org.apache.bcel.internal.generic.Type.INT && type != com.sun.org.apache.bcel.internal.generic.Type.SHORT) {
            if (type == com.sun.org.apache.bcel.internal.generic.Type.LONG) {
                return new LSTORE(i);
            }
            if (type != com.sun.org.apache.bcel.internal.generic.Type.BYTE && type != com.sun.org.apache.bcel.internal.generic.Type.CHAR) {
                if (type == com.sun.org.apache.bcel.internal.generic.Type.FLOAT) {
                    return new FSTORE(i);
                }
                return type == com.sun.org.apache.bcel.internal.generic.Type.DOUBLE ? new DSTORE(i) : new ASTORE(i);
            }
            return new ISTORE(i);
        }
        return new ISTORE(i);
    }

    public void addInstructionList(Pattern pattern, InstructionList instructionList) {
        this._preCompiled.put(pattern, instructionList);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.MethodGen
    public LocalVariableGen addLocalVariable(String str, com.sun.org.apache.bcel.internal.generic.Type type, InstructionHandle instructionHandle, InstructionHandle instructionHandle2) {
        if (this._allocatorInit) {
            return addLocalVariable2(str, type, instructionHandle);
        }
        LocalVariableGen localVariableGenAddLocalVariable = super.addLocalVariable(str, type, instructionHandle, instructionHandle2);
        getLocalVariableRegistry().registerLocalVariable(localVariableGenAddLocalVariable);
        return localVariableGenAddLocalVariable;
    }

    public LocalVariableGen addLocalVariable2(String str, com.sun.org.apache.bcel.internal.generic.Type type, InstructionHandle instructionHandle) {
        LocalVariableGen localVariableGenAddLocalVariable = super.addLocalVariable(str, type, this._slotAllocator.allocateSlot(type), instructionHandle, null);
        getLocalVariableRegistry().registerLocalVariable(localVariableGenAddLocalVariable);
        return localVariableGenAddLocalVariable;
    }

    public final Instruction attribute() {
        return this._attribute;
    }

    public final Instruction endDocument() {
        return this._endDocument;
    }

    public final Instruction endElement() {
        return this._endElement;
    }

    public Method[] getGeneratedMethods(ClassGenerator classGenerator) {
        InstructionList instructionList = getInstructionList();
        InstructionHandle end = instructionList.getEnd();
        instructionList.setPositions();
        int position = end.getPosition() + end.getInstruction().getLength();
        if (position > 32767 && widenConditionalBranchTargetOffsets()) {
            instructionList.setPositions();
            InstructionHandle end2 = instructionList.getEnd();
            position = end2.getPosition() + end2.getInstruction().getLength();
        }
        return position > 65535 ? outlineChunks(classGenerator, position) : new Method[]{getThisMethod()};
    }

    public InstructionList getInstructionList(Pattern pattern) {
        return this._preCompiled.get(pattern);
    }

    public int getLocalIndex(String str) {
        return getLocalVariable(str).getIndex();
    }

    public LocalVariableGen getLocalVariable(String str) {
        return getLocalVariableRegistry().lookUpByName(str);
    }

    public Method getThisMethod() {
        stripAttributes(true);
        setMaxLocals();
        setMaxStack();
        removeNOPs();
        return getMethod();
    }

    public Instruction loadContextNode() {
        return loadCurrentNode();
    }

    public Instruction loadCurrentNode() {
        if (this._iloadCurrent == null) {
            int localIndex = getLocalIndex(Keywords.FUNC_CURRENT_STRING);
            if (localIndex > 0) {
                this._iloadCurrent = new ILOAD(localIndex);
            } else {
                this._iloadCurrent = new ICONST(0);
            }
        }
        return this._iloadCurrent;
    }

    public Instruction loadDOM() {
        return this._aloadDom;
    }

    public Instruction loadHandler() {
        return this._aloadHandler;
    }

    public Instruction loadIterator() {
        return this._aloadIterator;
    }

    public void markChunkEnd() {
        getInstructionList().append(OutlineableChunkEnd.OUTLINEABLECHUNKEND);
        int i = this.m_openChunks - 1;
        this.m_openChunks = i;
        if (i < 0) {
            throw new InternalError(new ErrorMsg(ErrorMsg.OUTLINE_ERR_UNBALANCED_MARKERS).toString());
        }
    }

    public void markChunkStart() {
        getInstructionList().append(OutlineableChunkStart.OUTLINEABLECHUNKSTART);
        this.m_totalChunks++;
        this.m_openChunks++;
    }

    public final Instruction namespace() {
        return this._namespace;
    }

    public final Instruction nextNode() {
        return this._nextNode;
    }

    public boolean offsetInLocalVariableGenRange(LocalVariableGen localVariableGen, int i) {
        InstructionHandle start = localVariableGen.getStart();
        InstructionHandle end = localVariableGen.getEnd();
        if (start == null) {
            start = getInstructionList().getStart();
        }
        if (end == null) {
            end = getInstructionList().getEnd();
        }
        return start.getPosition() <= i && end.getPosition() + end.getInstruction().getLength() >= i;
    }

    public Method[] outlineChunks(ClassGenerator classGenerator, int i) {
        ArrayList arrayList = new ArrayList();
        String name = getName();
        if (name.equals(Const.CONSTRUCTOR_NAME)) {
            name = "$lt$init$gt$";
        } else if (name.equals(Const.STATIC_INITIALIZER_NAME)) {
            name = "$lt$clinit$gt$";
        }
        int i2 = 0;
        do {
            List<Chunk> candidateChunks = getCandidateChunks(classGenerator, i);
            Collections.sort(candidateChunks);
            int size = candidateChunks.size() - 1;
            boolean z = false;
            while (size >= 0 && i > TARGET_METHOD_SIZE) {
                Chunk chunk = candidateChunks.get(size);
                arrayList.add(outline(chunk.getChunkStart(), chunk.getChunkEnd(), name + "$outline$" + i2, classGenerator));
                i2++;
                InstructionList instructionList = getInstructionList();
                InstructionHandle end = instructionList.getEnd();
                instructionList.setPositions();
                i = end.getPosition() + end.getInstruction().getLength();
                size--;
                z = true;
            }
            if (!z) {
                break;
            }
        } while (i > TARGET_METHOD_SIZE);
        if (i > 65535) {
            throw new InternalError(new ErrorMsg(ErrorMsg.OUTLINE_ERR_METHOD_TOO_BIG).toString());
        }
        Method[] methodArr = new Method[arrayList.size() + 1];
        arrayList.toArray(methodArr);
        methodArr[arrayList.size()] = getThisMethod();
        return methodArr;
    }

    @Override // com.sun.org.apache.bcel.internal.generic.MethodGen
    public void removeLocalVariable(LocalVariableGen localVariableGen) {
        this._slotAllocator.releaseSlot(localVariableGen);
        getLocalVariableRegistry().removeByNameTracking(localVariableGen);
        super.removeLocalVariable(localVariableGen);
    }

    public final Instruction reset() {
        return this._reset;
    }

    @Override // com.sun.org.apache.bcel.internal.generic.MethodGen
    public void setMaxLocals() {
        int maxLocals = super.getMaxLocals();
        LocalVariableGen[] localVariables = super.getLocalVariables();
        if (localVariables != null && localVariables.length > maxLocals) {
            maxLocals = localVariables.length;
        }
        if (maxLocals < 5) {
            maxLocals = 5;
        }
        super.setMaxLocals(maxLocals);
    }

    public final Instruction setStartNode() {
        return this._setStartNode;
    }

    public final Instruction startDocument() {
        return this._startDocument;
    }

    public final Instruction startElement() {
        return this._startElement;
    }

    public Instruction storeContextNode() {
        return storeCurrentNode();
    }

    public Instruction storeCurrentNode() {
        Instruction instruction = this._istoreCurrent;
        if (instruction != null) {
            return instruction;
        }
        ISTORE istore = new ISTORE(getLocalIndex(Keywords.FUNC_CURRENT_STRING));
        this._istoreCurrent = istore;
        return istore;
    }

    public Instruction storeDOM() {
        return this._astoreDom;
    }

    public Instruction storeHandler() {
        return this._astoreHandler;
    }

    public Instruction storeIterator() {
        return this._astoreIterator;
    }

    public final Instruction uniqueAttribute() {
        return this._uniqueAttribute;
    }

    /* JADX WARN: Code duplicated, block: B:15:0x002b  */
    public boolean widenConditionalBranchTargetOffsets() {
        InstructionList instructionList = getInstructionList();
        int i = 0;
        for (InstructionHandle start = instructionList.getStart(); start != null; start = start.getNext()) {
            short opcode = start.getInstruction().getOpcode();
            if (opcode == 170 || opcode == 171) {
                i += 3;
            } else if (opcode != 198 && opcode != 199) {
                switch (opcode) {
                    case 153:
                    case 154:
                    case 155:
                    case 156:
                    case 157:
                    case 158:
                    case 159:
                    case 160:
                    case 161:
                    case 162:
                    case 163:
                    case 164:
                    case 165:
                    case 166:
                        i += 5;
                        break;
                    case 167:
                    case 168:
                        i += 2;
                        break;
                }
            } else {
                i += 5;
            }
        }
        InstructionHandle start2 = instructionList.getStart();
        boolean z = false;
        while (start2 != null) {
            Instruction instruction = start2.getInstruction();
            if (instruction instanceof IfInstruction) {
                IfInstruction ifInstruction = (IfInstruction) instruction;
                BranchHandle branchHandle = (BranchHandle) start2;
                InstructionHandle target = ifInstruction.getTarget();
                int position = target.getPosition() - branchHandle.getPosition();
                if (position - i < MIN_BRANCH_TARGET_OFFSET || position + i > 32767) {
                    InstructionHandle next = branchHandle.getNext();
                    BranchHandle branchHandleAppend = instructionList.append((InstructionHandle) branchHandle, (BranchInstruction) ifInstruction.negate());
                    BranchHandle branchHandleAppend2 = instructionList.append((InstructionHandle) branchHandleAppend, (BranchInstruction) new GOTO(target));
                    if (next == null) {
                        next = instructionList.append(branchHandleAppend2, InstructionConst.NOP);
                    }
                    branchHandleAppend.updateTarget(target, next);
                    if (branchHandle.hasTargeters()) {
                        for (InstructionTargeter instructionTargeter : branchHandle.getTargeters()) {
                            if (instructionTargeter instanceof LocalVariableGen) {
                                LocalVariableGen localVariableGen = (LocalVariableGen) instructionTargeter;
                                if (localVariableGen.getStart() == branchHandle) {
                                    localVariableGen.setStart(branchHandleAppend);
                                } else if (localVariableGen.getEnd() == branchHandle) {
                                    localVariableGen.setEnd(branchHandleAppend2);
                                }
                            } else {
                                instructionTargeter.updateTarget(branchHandle, branchHandleAppend);
                            }
                        }
                    }
                    try {
                        instructionList.delete(branchHandle);
                        z = true;
                        start2 = branchHandleAppend2;
                    } catch (TargetLostException e) {
                        throw new InternalError(new ErrorMsg(ErrorMsg.OUTLINE_ERR_DELETED_TARGET, e.getMessage()).toString());
                    }
                }
            }
            start2 = start2.getNext();
        }
        return z;
    }
}
