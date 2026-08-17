package com.sun.tools.javac.jvm;

import com.sun.org.apache.xalan.internal.templates.Constants;
import com.sun.tools.javac.code.Attribute;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.Symtab;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.code.TypeAnnotationPosition;
import com.sun.tools.javac.code.TypeTag;
import com.sun.tools.javac.code.Types;
import com.sun.tools.javac.jvm.Code;
import com.sun.tools.javac.resources.CompilerProperties;
import com.sun.tools.javac.util.ArrayUtils;
import com.sun.tools.javac.util.Assert;
import com.sun.tools.javac.util.Bits;
import com.sun.tools.javac.util.JCDiagnostic;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.ListBuffer;
import com.sun.tools.javac.util.Log;
import com.sun.tools.javac.util.Name;
import com.sun.tools.javac.util.Names;
import com.sun.tools.javac.util.Position;
import defpackage.s22;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.ToIntBiFunction;
import java.util.function.ToIntFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Code {
    static final Type jsrReturnValue = new Type.JCPrimitiveType(TypeTag.INT, null);
    public CRTable crt;
    public final boolean debugCode;
    public boolean fatcode;
    boolean lineDebugInfo;
    Position.LineMap lineMap;
    LocalVar[] lvar;
    final Symbol.MethodSymbol meth;
    public final boolean needStackMap;
    final PoolWriter poolWriter;
    StackMapFormat stackMap;
    State state;
    final Symtab syms;
    final Types types;
    LocalVar[] varBuffer;
    int varBufferSize;
    boolean varDebugInfo;
    public int max_stack = 0;
    public int max_locals = 0;
    public byte[] code = new byte[64];
    public int cp = 0;
    ListBuffer<char[]> catchInfo = new ListBuffer<>();
    List<char[]> lineInfo = List.nil();
    private boolean alive = true;
    private boolean fixedPc = false;
    public int nextreg = 0;
    Chain pendingJumps = null;
    int pendingStatPos = -1;
    boolean pendingStackMap = false;
    private int letExprStackPos = 0;
    StackMapFrame[] stackMapBuffer = null;
    ClassWriter.StackMapTableFrame[] stackMapTableBuffer = null;
    int stackMapBufferSize = 0;
    int lastStackMapPC = -1;
    StackMapFrame lastFrame = null;
    StackMapFrame frameBeforeLast = null;

    /* JADX INFO: renamed from: com.sun.tools.javac.jvm.Code$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$javac$code$TypeTag;

        static {
            int[] iArr = new int[TypeTag.values().length];
            $SwitchMap$com$sun$tools$javac$code$TypeTag = iArr;
            try {
                iArr[TypeTag.BYTE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.SHORT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.CHAR.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.INT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.LONG.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.FLOAT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.DOUBLE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.BOOLEAN.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.VOID.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.CLASS.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.ARRAY.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.METHOD.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.BOT.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.TYPEVAR.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.UNINITIALIZED_THIS.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.UNINITIALIZED_OBJECT.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
        }
    }

    public static class Chain {
        public final Chain next;
        public final int pc;
        State state;

        public Chain(int i, Chain chain, State state) {
            this.pc = i;
            this.next = chain;
            this.state = state;
        }
    }

    public static class Mneumonics {
        private static final String[] mnem = {"nop", "aconst_null", "iconst_m1", "iconst_0", "iconst_1", "iconst_2", "iconst_3", "iconst_4", "iconst_5", "lconst_0", "lconst_1", "fconst_0", "fconst_1", "fconst_2", "dconst_0", "dconst_1", "bipush", "sipush", "ldc1", "ldc2", "ldc2w", "iload", "lload", "fload", "dload", "aload", "iload_0", "iload_1", "iload_2", "iload_3", "lload_0", "lload_1", "lload_2", "lload_3", "fload_0", "fload_1", "fload_2", "fload_3", "dload_0", "dload_1", "dload_2", "dload_3", "aload_0", "aload_1", "aload_2", "aload_3", "iaload", "laload", "faload", "daload", "aaload", "baload", "caload", "saload", "istore", "lstore", "fstore", "dstore", "astore", "istore_0", "istore_1", "istore_2", "istore_3", "lstore_0", "lstore_1", "lstore_2", "lstore_3", "fstore_0", "fstore_1", "fstore_2", "fstore_3", "dstore_0", "dstore_1", "dstore_2", "dstore_3", "astore_0", "astore_1", "astore_2", "astore_3", "iastore", "lastore", "fastore", "dastore", "aastore", "bastore", "castore", "sastore", "pop", "pop2", "dup", "dup_x1", "dup_x2", "dup2", "dup2_x1", "dup2_x2", "swap", "iadd", "ladd", "fadd", "dadd", "isub", "lsub", "fsub", "dsub", "imul", "lmul", "fmul", "dmul", "idiv", "ldiv", "fdiv", "ddiv", "imod", "lmod", "fmod", "dmod", "ineg", "lneg", "fneg", "dneg", "ishl", "lshl", "ishr", "lshr", "iushr", "lushr", "iand", "land", "ior", "lor", "ixor", "lxor", "iinc", "i2l", "i2f", "i2d", "l2i", "l2f", "l2d", "f2i", "f2l", "f2d", "d2i", "d2l", "d2f", "int2byte", "int2char", "int2short", "lcmp", "fcmpl", "fcmpg", "dcmpl", "dcmpg", "ifeq", "ifne", "iflt", "ifge", "ifgt", "ifle", "if_icmpeq", "if_icmpne", "if_icmplt", "if_icmpge", "if_icmpgt", "if_icmple", "if_acmpeq", "if_acmpne", "goto_", "jsr", "ret", "tableswitch", "lookupswitch", "ireturn", "lreturn", "freturn", "dreturn", "areturn", "return_", "getstatic", "putstatic", "getfield", "putfield", "invokevirtual", "invokespecial", "invokestatic", "invokeinterface", "invokedynamic", "new_", "newarray", "anewarray", "arraylength", "athrow", "checkcast", "instanceof_", "monitorenter", "monitorexit", "wide", "multianewarray", "if_acmp_null", "if_acmp_nonnull", "goto_w", "jsr_w", "breakpoint"};

        private Mneumonics() {
        }
    }

    public static class StackMapFrame {
        Type[] locals;
        int pc;
        Type[] stack;
    }

    public Code(Symbol.MethodSymbol methodSymbol, boolean z, Position.LineMap lineMap, boolean z2, StackMapFormat stackMapFormat, boolean z3, CRTable cRTable, Symtab symtab, Types types, PoolWriter poolWriter) {
        this.meth = methodSymbol;
        this.fatcode = z;
        this.lineMap = lineMap;
        this.lineDebugInfo = lineMap != null;
        this.varDebugInfo = z2;
        this.crt = cRTable;
        this.syms = symtab;
        this.types = types;
        this.poolWriter = poolWriter;
        this.debugCode = z3;
        this.stackMap = stackMapFormat;
        int iOrdinal = stackMapFormat.ordinal();
        if (iOrdinal == 1 || iOrdinal == 2) {
            this.needStackMap = true;
        } else {
            this.needStackMap = false;
        }
        this.state = new State();
        this.lvar = new LocalVar[20];
    }

    private void addLocalVar(Symbol.VarSymbol varSymbol) {
        int i = varSymbol.adr;
        LocalVar[] localVarArr = (LocalVar[]) ArrayUtils.ensureCapacity(this.lvar, i + 1);
        this.lvar = localVarArr;
        Assert.checkNull(localVarArr[i]);
        if (this.pendingJumps != null) {
            resolvePending();
        }
        this.lvar[i] = new LocalVar(varSymbol);
        this.state.defined.excl(i);
    }

    private int[] appendArray(int[] iArr, int[] iArr2) {
        if (iArr == null || iArr.length == 0) {
            return iArr2;
        }
        int[] iArr3 = new int[iArr.length + iArr2.length];
        System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
        System.arraycopy(iArr2, 0, iArr3, iArr.length, iArr2.length);
        return iArr3;
    }

    public static int arraycode(Type type) {
        switch (AnonymousClass1.$SwitchMap$com$sun$tools$javac$code$TypeTag[type.getTag().ordinal()]) {
            case 1:
                return 8;
            case 2:
                return 9;
            case 3:
                return 5;
            case 4:
                return 10;
            case 5:
                return 11;
            case 6:
                return 6;
            case 7:
                return 7;
            case 8:
                return 4;
            case 9:
            default:
                s22.a("arraycode ", type);
                return 0;
            case 10:
                return 0;
            case 11:
                return 1;
        }
    }

    public static /* synthetic */ boolean d(LocalVar.Range range) {
        return range.closed() && range.length > 0;
    }

    public static /* synthetic */ LocalVar.Range[] e(int i) {
        return new LocalVar.Range[i];
    }

    private void emit1(int i) {
        if (this.alive) {
            byte[] bArrEnsureCapacity = ArrayUtils.ensureCapacity(this.code, this.cp);
            this.code = bArrEnsureCapacity;
            int i2 = this.cp;
            this.cp = i2 + 1;
            bArrEnsureCapacity[i2] = (byte) i;
        }
    }

    private void emit2(int i) {
        if (this.alive) {
            int i2 = this.cp;
            int i3 = i2 + 2;
            byte[] bArr = this.code;
            if (i3 > bArr.length) {
                emit1(i >> 8);
                emit1(i);
                return;
            }
            int i4 = i2 + 1;
            this.cp = i4;
            bArr[i2] = (byte) (i >> 8);
            this.cp = i2 + 2;
            bArr[i4] = (byte) i;
        }
    }

    private void emitop(int i) {
        if (this.pendingJumps != null) {
            resolvePending();
        }
        if (this.alive) {
            if (this.pendingStatPos != -1) {
                markStatBegin();
            }
            if (this.pendingStackMap) {
                this.pendingStackMap = false;
                emitStackMap();
            }
            if (this.debugCode) {
                System.err.println("emit@" + this.cp + " stack=" + this.state.stacksize + ": " + mnem(i));
            }
            emit1(i);
        }
    }

    private void endScope(int i) {
        char cCurCP;
        LocalVar localVar = this.lvar[i];
        if (localVar != null) {
            if (localVar.isLastRangeInitialized() && (cCurCP = (char) (curCP() - localVar.lastRange().start_pc)) < 65535) {
                localVar.closeRange(cCurCP);
                putVar(localVar);
                fillLocalVarPosition(localVar);
            }
            this.lvar[i] = null;
        }
        this.state.defined.excl(i);
    }

    private void fillLocalVarPosition(final LocalVar localVar) {
        Symbol.VarSymbol varSymbol;
        if (localVar == null || (varSymbol = localVar.sym) == null || varSymbol.isExceptionParameter() || !localVar.sym.hasTypeAnnotations()) {
            return;
        }
        LocalVar.Range[] rangeArr = (LocalVar.Range[]) localVar.aliveRanges.stream().filter(new Predicate() { // from class: com.sun.tools.javac.jvm.l
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return Code.d((Code.LocalVar.Range) obj);
            }
        }).toArray(new IntFunction() { // from class: v22
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return Code.e(i);
            }
        });
        if (rangeArr.length == 0) {
            return;
        }
        int[] array = Arrays.stream(rangeArr).mapToInt(new ToIntFunction() { // from class: com.sun.tools.javac.jvm.m
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((Code.LocalVar.Range) obj).start_pc;
            }
        }).toArray();
        int[] array2 = Arrays.stream(rangeArr).mapToInt(new ToIntFunction() { // from class: com.sun.tools.javac.jvm.n
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((Code.LocalVar.Range) obj).length;
            }
        }).toArray();
        int[] array3 = Arrays.stream(rangeArr).mapToInt(new ToIntFunction() { // from class: com.sun.tools.javac.jvm.o
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return localVar.reg;
            }
        }).toArray();
        Iterator<Attribute.TypeCompound> it = localVar.sym.getRawTypeAttributes().iterator();
        while (it.hasNext()) {
            TypeAnnotationPosition typeAnnotationPosition = it.next().position;
            typeAnnotationPosition.lvarOffset = appendArray(typeAnnotationPosition.lvarOffset, array);
            typeAnnotationPosition.lvarLength = appendArray(typeAnnotationPosition.lvarLength, array2);
            typeAnnotationPosition.lvarIndex = appendArray(typeAnnotationPosition.lvarIndex, array3);
            typeAnnotationPosition.isValidOffset = true;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private int findExceptionIndex(TypeAnnotationPosition typeAnnotationPosition) {
        int catchType = typeAnnotationPosition.getCatchType();
        int startPos = typeAnnotationPosition.getStartPos();
        int length = this.catchInfo.length();
        List list = this.catchInfo.toList();
        for (int i = 0; i < length; i++) {
            char[] cArr = (char[]) list.head;
            list = list.tail;
            char c = cArr[3];
            char c2 = cArr[0];
            if (catchType == c && c2 == startPos) {
                return i;
            }
        }
        return -1;
    }

    private int get1(int i) {
        return this.code[i] & 255;
    }

    private int get2(int i) {
        return get1(i + 1) | (get1(i) << 8);
    }

    private int getLocalsSize() {
        LocalVar localVar;
        for (int i = this.max_locals - 1; i >= 0; i--) {
            if (this.state.defined.isMember(i) && (localVar = this.lvar[i]) != null) {
                return i + width(localVar.sym.erasure(this.types));
            }
        }
        return 0;
    }

    public static Chain mergeChains(Chain chain, Chain chain2) {
        if (chain2 == null) {
            return chain;
        }
        if (chain == null) {
            return chain2;
        }
        State state = chain.state;
        int i = state.stacksize;
        State state2 = chain2.state;
        Assert.check(i == state2.stacksize && state.nlocks == state2.nlocks);
        int i2 = chain.pc;
        int i3 = chain2.pc;
        return i2 < i3 ? new Chain(i3, mergeChains(chain, chain2.next), chain2.state) : new Chain(i2, mergeChains(chain.next, chain2), chain.state);
    }

    public static String mnem(int i) {
        return Mneumonics.mnem[i];
    }

    public static int negate(int i) {
        if (i == 198) {
            return 199;
        }
        if (i == 199) {
            return 198;
        }
        return ((i + 1) ^ 1) - 1;
    }

    private int newLocal(int i) {
        int i2 = this.nextreg;
        int iWidth = width(i) + i2;
        this.nextreg = iWidth;
        if (iWidth > this.max_locals) {
            this.max_locals = iWidth;
        }
        return i2;
    }

    private void put1(int i, int i2) {
        this.code[i] = (byte) i2;
    }

    private void put2(int i, int i2) {
        put1(i, i2 >> 8);
        put1(i + 1, i2);
    }

    public static int truncate(int i) {
        if (i == 5 || i == 6 || i == 7) {
            return 0;
        }
        return i;
    }

    public static int typecode(Type type) {
        switch (AnonymousClass1.$SwitchMap$com$sun$tools$javac$code$TypeTag[type.getTag().ordinal()]) {
            case 1:
                return 5;
            case 2:
                return 7;
            case 3:
                return 6;
            case 4:
                return 0;
            case 5:
                return 1;
            case 6:
                return 2;
            case 7:
                return 3;
            case 8:
                return 5;
            case 9:
                return 8;
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
                return 4;
            default:
                pe1.a("typecode ", type.getTag());
                return 0;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static int width(List<Type> list) {
        int iWidth = 0;
        for (List list2 = list; list2.nonEmpty(); list2 = list2.tail) {
            iWidth += width((Type) list2.head);
        }
        return iWidth;
    }

    public void addCatch(char c, char c2, char c3, char c4) {
        this.catchInfo.append(new char[]{c, c2, c3, c4});
    }

    public void addLineNumber(char c, char c2) {
        if (this.lineDebugInfo) {
            if (this.lineInfo.nonEmpty()) {
                List<char[]> list = this.lineInfo;
                if (list.head[0] == c) {
                    this.lineInfo = list.tail;
                }
            }
            if (this.lineInfo.isEmpty() || this.lineInfo.head[1] != c2) {
                this.lineInfo = this.lineInfo.prepend(new char[]{c, c2});
            }
        }
    }

    public void adjustAliveRanges(int i, int i2) {
        for (LocalVar localVar : this.lvar) {
            if (localVar != null) {
                for (LocalVar.Range range : localVar.aliveRanges) {
                    if (range.closed()) {
                        char c = range.start_pc;
                        char c2 = range.length;
                        if (c + c2 >= i) {
                            range.length = (char) (c2 + ((char) i2));
                        }
                    }
                }
            }
        }
    }

    public void align(int i) {
        if (this.alive) {
            while (this.cp % i != 0) {
                emitop0(0);
            }
        }
    }

    public Chain branch(int i) {
        Chain chain = null;
        if (i == 167) {
            Chain chain2 = this.pendingJumps;
            this.pendingJumps = null;
            chain = chain2;
        }
        if (i == 168 || !isAlive()) {
            return chain;
        }
        Chain chain3 = new Chain(emitJump(i), chain, this.state.dup());
        this.fixedPc = this.fatcode;
        if (i == 167) {
            this.alive = false;
        }
        return chain3;
    }

    public boolean checkLimits(JCDiagnostic.DiagnosticPosition diagnosticPosition, Log log) {
        if (this.cp > 65535) {
            log.error(diagnosticPosition, CompilerProperties.Errors.LimitCode);
            return true;
        }
        if (this.max_locals > 65535) {
            log.error(diagnosticPosition, CompilerProperties.Errors.LimitLocals);
            return true;
        }
        if (this.max_stack <= 65535) {
            return false;
        }
        log.error(diagnosticPosition, CompilerProperties.Errors.LimitStack);
        return true;
    }

    public void compressCatchTable() {
        ListBuffer<char[]> listBuffer = new ListBuffer<>();
        List listNil = List.nil();
        Iterator<char[]> it = this.catchInfo.iterator();
        while (it.hasNext()) {
            listNil = listNil.prepend(Integer.valueOf(it.next()[2]));
        }
        for (char[] cArr : this.catchInfo) {
            char c = cArr[0];
            char c2 = cArr[1];
            if (c != c2 && (c != c2 - 1 || !listNil.contains(Integer.valueOf(c)))) {
                listBuffer.append(cArr);
            }
        }
        this.catchInfo = listBuffer;
    }

    public int curCP() {
        if (this.pendingJumps != null) {
            resolvePending();
        }
        if (this.pendingStatPos != -1) {
            markStatBegin();
        }
        this.fixedPc = true;
        return this.cp;
    }

    public void emit4(int i) {
        if (this.alive) {
            int i2 = this.cp;
            int i3 = i2 + 4;
            byte[] bArr = this.code;
            if (i3 > bArr.length) {
                emit1(i >> 24);
                emit1(i >> 16);
                emit1(i >> 8);
                emit1(i);
                return;
            }
            int i4 = i2 + 1;
            this.cp = i4;
            bArr[i2] = (byte) (i >> 24);
            int i5 = i2 + 2;
            this.cp = i5;
            bArr[i4] = (byte) (i >> 16);
            int i6 = i2 + 3;
            this.cp = i6;
            bArr[i5] = (byte) (i >> 8);
            this.cp = i2 + 4;
            bArr[i6] = (byte) i;
        }
    }

    public void emitAnewarray(int i, Type type) {
        emitop(189);
        if (this.alive) {
            emit2(i);
            this.state.pop(1);
            this.state.push(type);
        }
    }

    public void emitCLDCStackMap(int i, int i2) {
        State state;
        LocalVar localVar;
        if (this.lastStackMapPC == i) {
            StackMapFrame[] stackMapFrameArr = this.stackMapBuffer;
            int i3 = this.stackMapBufferSize - 1;
            this.stackMapBufferSize = i3;
            stackMapFrameArr[i3] = null;
        }
        this.lastStackMapPC = i;
        StackMapFrame[] stackMapFrameArr2 = this.stackMapBuffer;
        if (stackMapFrameArr2 == null) {
            this.stackMapBuffer = new StackMapFrame[20];
        } else {
            this.stackMapBuffer = (StackMapFrame[]) ArrayUtils.ensureCapacity(stackMapFrameArr2, this.stackMapBufferSize);
        }
        StackMapFrame[] stackMapFrameArr3 = this.stackMapBuffer;
        int i4 = this.stackMapBufferSize;
        this.stackMapBufferSize = i4 + 1;
        StackMapFrame stackMapFrame = new StackMapFrame();
        stackMapFrameArr3[i4] = stackMapFrame;
        stackMapFrame.pc = i;
        stackMapFrame.locals = new Type[i2];
        int i5 = 0;
        int i6 = 0;
        while (true) {
            state = this.state;
            if (i6 >= i2) {
                break;
            }
            if (state.defined.isMember(i6) && (localVar = this.lvar[i6]) != null) {
                Type typeErasure = localVar.sym.type;
                if (!(typeErasure instanceof UninitializedType)) {
                    typeErasure = this.types.erasure(typeErasure);
                }
                stackMapFrame.locals[i6] = typeErasure;
            }
            i6++;
        }
        stackMapFrame.stack = new Type[state.stacksize];
        while (true) {
            State state2 = this.state;
            if (i5 >= state2.stacksize) {
                return;
            }
            stackMapFrame.stack[i5] = state2.stack[i5];
            i5++;
        }
    }

    public void emitInvokedynamic(Symbol.DynamicMethodSymbol dynamicMethodSymbol, Type type) {
        int iWidth = width(type.mo71getParameterTypes());
        emitop(186);
        if (this.alive) {
            emit2(this.poolWriter.putDynamic(dynamicMethodSymbol));
            emit2(0);
            this.state.pop(iWidth);
            this.state.push(type.mo73getReturnType());
        }
    }

    public void emitInvokeinterface(Symbol symbol, Type type) {
        int iWidth = width(type.mo71getParameterTypes());
        emitop(185);
        if (this.alive) {
            emit2(this.poolWriter.putMember(symbol));
            int i = iWidth + 1;
            emit1(i);
            emit1(0);
            this.state.pop(i);
            this.state.push(type.mo73getReturnType());
        }
    }

    public void emitInvokespecial(Symbol symbol, Type type) {
        int iWidth = width(type.mo71getParameterTypes());
        emitop(183);
        if (this.alive) {
            emit2(this.poolWriter.putMember(symbol));
            this.state.pop(iWidth);
            if (symbol.isConstructor()) {
                State state = this.state;
                state.markInitialized((UninitializedType) state.peek());
            }
            this.state.pop(1);
            this.state.push(type.mo73getReturnType());
        }
    }

    public void emitInvokestatic(Symbol symbol, Type type) {
        int iWidth = width(type.mo71getParameterTypes());
        emitop(184);
        if (this.alive) {
            emit2(this.poolWriter.putMember(symbol));
            this.state.pop(iWidth);
            this.state.push(type.mo73getReturnType());
        }
    }

    public void emitInvokevirtual(Symbol symbol, Type type) {
        int iWidth = width(type.mo71getParameterTypes());
        emitop(182);
        if (this.alive) {
            emit2(this.poolWriter.putMember(symbol));
            this.state.pop(iWidth + 1);
            this.state.push(type.mo73getReturnType());
        }
    }

    public int emitJump(int i) {
        if (!this.fatcode) {
            emitop2(i, 0);
            return this.cp - 3;
        }
        if (i == 167 || i == 168) {
            emitop4(i + 33, 0);
        } else {
            emitop2(negate(i), 8);
            emitop4(200, 0);
            this.alive = true;
            this.pendingStackMap = this.needStackMap;
        }
        return this.cp - 5;
    }

    public void emitLdc(PoolConstant.LoadableConstant loadableConstant) {
        int iPutConstant = this.poolWriter.putConstant(loadableConstant);
        Type typeConstantType = this.types.constantType(loadableConstant);
        if (typeConstantType.hasTag(TypeTag.LONG) || typeConstantType.hasTag(TypeTag.DOUBLE)) {
            emitop2(20, iPutConstant, loadableConstant);
        } else if (iPutConstant <= 255) {
            emitop1(18, iPutConstant, loadableConstant);
        } else {
            emitop2(19, iPutConstant, loadableConstant);
        }
    }

    public void emitMultianewarray(int i, int i2, Type type) {
        emitop(197);
        if (this.alive) {
            emit2(i2);
            emit1(i);
            this.state.pop(i);
            this.state.push(type);
        }
    }

    public void emitNewarray(int i, Type type) {
        emitop(188);
        if (this.alive) {
            emit1(i);
            this.state.pop(1);
            this.state.push(type);
        }
    }

    public void emitStackMap() {
        int iCurCP = curCP();
        if (this.needStackMap) {
            int iOrdinal = this.stackMap.ordinal();
            if (iOrdinal == 1) {
                emitCLDCStackMap(iCurCP, getLocalsSize());
            } else {
                if (iOrdinal != 2) {
                    x01.a("Should have chosen a stackmap format");
                    return;
                }
                emitStackMapFrame(iCurCP, getLocalsSize());
            }
            if (this.debugCode) {
                this.state.dump(iCurCP);
            }
        }
    }

    public void emitStackMapFrame(int i, int i2) {
        LocalVar localVar;
        StackMapFrame stackMapFrame = this.lastFrame;
        if (stackMapFrame == null) {
            this.lastFrame = getInitialFrame();
        } else if (stackMapFrame.pc == i) {
            ClassWriter.StackMapTableFrame[] stackMapTableFrameArr = this.stackMapTableBuffer;
            int i3 = this.stackMapBufferSize - 1;
            this.stackMapBufferSize = i3;
            stackMapTableFrameArr[i3] = null;
            this.lastFrame = this.frameBeforeLast;
            this.frameBeforeLast = null;
        }
        StackMapFrame stackMapFrame2 = new StackMapFrame();
        stackMapFrame2.pc = i;
        Type[] typeArr = new Type[i2];
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        while (i5 < i2) {
            if (this.state.defined.isMember(i5) && (localVar = this.lvar[i5]) != null) {
                Type typeErasure = localVar.sym.type;
                if (!(typeErasure instanceof UninitializedType)) {
                    typeErasure = this.types.erasure(typeErasure);
                }
                typeArr[i5] = typeErasure;
                if (width(typeErasure) > 1) {
                    i5++;
                }
            }
            i5++;
            i6++;
        }
        stackMapFrame2.locals = new Type[i6];
        int i7 = 0;
        int i8 = 0;
        while (i7 < i2) {
            Assert.check(i8 < i6);
            stackMapFrame2.locals[i8] = typeArr[i7];
            if (width(typeArr[i7]) > 1) {
                i7++;
            }
            i7++;
            i8++;
        }
        int i9 = 0;
        int i10 = 0;
        while (true) {
            State state = this.state;
            if (i9 >= state.stacksize) {
                break;
            }
            if (state.stack[i9] != null) {
                i10++;
            }
            i9++;
        }
        stackMapFrame2.stack = new Type[i10];
        int i11 = 0;
        while (true) {
            State state2 = this.state;
            if (i4 >= state2.stacksize) {
                break;
            }
            Type type = state2.stack[i4];
            if (type != null) {
                stackMapFrame2.stack[i11] = this.types.erasure(type);
                i11++;
            }
            i4++;
        }
        ClassWriter.StackMapTableFrame[] stackMapTableFrameArr2 = this.stackMapTableBuffer;
        if (stackMapTableFrameArr2 == null) {
            this.stackMapTableBuffer = new ClassWriter.StackMapTableFrame[20];
        } else {
            this.stackMapTableBuffer = (ClassWriter.StackMapTableFrame[]) ArrayUtils.ensureCapacity(stackMapTableFrameArr2, this.stackMapBufferSize);
        }
        ClassWriter.StackMapTableFrame[] stackMapTableFrameArr3 = this.stackMapTableBuffer;
        int i12 = this.stackMapBufferSize;
        this.stackMapBufferSize = i12 + 1;
        StackMapFrame stackMapFrame3 = this.lastFrame;
        stackMapTableFrameArr3[i12] = ClassWriter.StackMapTableFrame.getInstance(stackMapFrame2, stackMapFrame3.pc, stackMapFrame3.locals, this.types);
        this.frameBeforeLast = this.lastFrame;
        this.lastFrame = stackMapFrame2;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:100:0x043d  */
    /* JADX WARN: Code duplicated, block: B:97:0x041f  */
    /* JADX WARN: Code duplicated, block: B:98:0x0429  */
    /* JADX WARN: Code duplicated, block: B:99:0x0433  */
    public void emitop0(int i) {
        emitop(i);
        if (this.alive) {
            if (i == 167) {
                markDead();
            } else if (i == 190) {
                this.state.pop(1);
                this.state.push(this.syms.intType);
            } else {
                if (i != 191) {
                    switch (i) {
                        case 0:
                            break;
                        case 1:
                            this.state.push(this.syms.botType);
                            break;
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                        case 6:
                        case 7:
                        case 8:
                            this.state.push(this.syms.intType);
                            break;
                        case 9:
                        case 10:
                            this.state.push(this.syms.longType);
                            break;
                        case 11:
                        case 12:
                        case 13:
                            this.state.push(this.syms.floatType);
                            break;
                        case 14:
                        case 15:
                            this.state.push(this.syms.doubleType);
                            break;
                        default:
                            switch (i) {
                                case 26:
                                case 27:
                                case 28:
                                case 29:
                                    this.state.push(this.syms.intType);
                                    break;
                                case 30:
                                case 31:
                                case 32:
                                case 33:
                                    this.state.push(this.syms.longType);
                                    break;
                                case 34:
                                case 35:
                                case 36:
                                case 37:
                                    this.state.push(this.syms.floatType);
                                    break;
                                case 38:
                                case 39:
                                case 40:
                                case 41:
                                    this.state.push(this.syms.doubleType);
                                    break;
                                case 42:
                                    this.state.push(this.lvar[0].sym.type);
                                    break;
                                case 43:
                                    this.state.push(this.lvar[1].sym.type);
                                    break;
                                case 44:
                                    this.state.push(this.lvar[2].sym.type);
                                    break;
                                case 45:
                                    this.state.push(this.lvar[3].sym.type);
                                    break;
                                case 46:
                                case 51:
                                case 52:
                                case 53:
                                    this.state.pop(2);
                                    this.state.push(this.syms.intType);
                                    break;
                                case 47:
                                    this.state.pop(2);
                                    this.state.push(this.syms.longType);
                                    break;
                                case 48:
                                    this.state.pop(2);
                                    this.state.push(this.syms.floatType);
                                    break;
                                case 49:
                                    this.state.pop(2);
                                    this.state.push(this.syms.doubleType);
                                    break;
                                case 50:
                                    this.state.pop(1);
                                    State state = this.state;
                                    Type type = state.stack[state.stacksize - 1];
                                    Assert.check(!type.hasTag(TypeTag.BOT));
                                    this.state.pop(1);
                                    State state2 = this.state;
                                    Types types = this.types;
                                    state2.push(types.erasure(types.elemtype(type)));
                                    break;
                                default:
                                    switch (i) {
                                        case 59:
                                        case 60:
                                        case 61:
                                        case 62:
                                        case 67:
                                        case 68:
                                        case 69:
                                        case 70:
                                        case 75:
                                        case 76:
                                        case 77:
                                        case 78:
                                        case 87:
                                        case 121:
                                        case 123:
                                        case 125:
                                            this.state.pop(1);
                                            break;
                                        case 63:
                                        case 64:
                                        case 65:
                                        case 66:
                                        case 71:
                                        case 72:
                                        case 73:
                                        case 74:
                                        case 88:
                                            this.state.pop(2);
                                            break;
                                        case 79:
                                        case 81:
                                        case 85:
                                        case 86:
                                            this.state.pop(3);
                                            break;
                                        case 80:
                                        case 82:
                                            this.state.pop(4);
                                            break;
                                        case 83:
                                            this.state.pop(3);
                                            break;
                                        case 84:
                                            this.state.pop(3);
                                            break;
                                        case 89:
                                            State state3 = this.state;
                                            state3.push(state3.stack[state3.stacksize - 1]);
                                            break;
                                        case 90:
                                            Type typePop1 = this.state.pop1();
                                            Type typePop2 = this.state.pop1();
                                            this.state.push(typePop1);
                                            this.state.push(typePop2);
                                            this.state.push(typePop1);
                                            break;
                                        case 91:
                                            Type typePop3 = this.state.pop1();
                                            State state4 = this.state;
                                            if (state4.stack[state4.stacksize - 1] == null) {
                                                Type typePop4 = state4.pop2();
                                                this.state.push(typePop3);
                                                this.state.push(typePop4);
                                                this.state.push(typePop3);
                                            } else {
                                                Type typePop5 = state4.pop1();
                                                Type typePop6 = this.state.pop1();
                                                this.state.push(typePop3);
                                                this.state.push(typePop6);
                                                this.state.push(typePop5);
                                                this.state.push(typePop3);
                                            }
                                            break;
                                        case 92:
                                            State state5 = this.state;
                                            if (state5.stack[state5.stacksize - 1] == null) {
                                                Type typePop7 = state5.pop2();
                                                this.state.push(typePop7);
                                                this.state.push(typePop7);
                                            } else {
                                                Type typePop8 = state5.pop1();
                                                Type typePop9 = this.state.pop1();
                                                this.state.push(typePop9);
                                                this.state.push(typePop8);
                                                this.state.push(typePop9);
                                                this.state.push(typePop8);
                                            }
                                            break;
                                        case 93:
                                            State state6 = this.state;
                                            if (state6.stack[state6.stacksize - 1] == null) {
                                                Type typePop10 = state6.pop2();
                                                Type typePop11 = this.state.pop1();
                                                this.state.push(typePop10);
                                                this.state.push(typePop11);
                                                this.state.push(typePop10);
                                            } else {
                                                Type typePop12 = state6.pop1();
                                                Type typePop13 = this.state.pop1();
                                                Type typePop14 = this.state.pop1();
                                                this.state.push(typePop13);
                                                this.state.push(typePop12);
                                                this.state.push(typePop14);
                                                this.state.push(typePop13);
                                                this.state.push(typePop12);
                                            }
                                            break;
                                        case 94:
                                            State state7 = this.state;
                                            if (state7.stack[state7.stacksize - 1] == null) {
                                                Type typePop15 = state7.pop2();
                                                State state8 = this.state;
                                                if (state8.stack[state8.stacksize - 1] == null) {
                                                    Type typePop16 = state8.pop2();
                                                    this.state.push(typePop15);
                                                    this.state.push(typePop16);
                                                    this.state.push(typePop15);
                                                } else {
                                                    Type typePop17 = state8.pop1();
                                                    Type typePop18 = this.state.pop1();
                                                    this.state.push(typePop15);
                                                    this.state.push(typePop18);
                                                    this.state.push(typePop17);
                                                    this.state.push(typePop15);
                                                }
                                            } else {
                                                Type typePop19 = state7.pop1();
                                                Type typePop20 = this.state.pop1();
                                                State state9 = this.state;
                                                if (state9.stack[state9.stacksize - 1] == null) {
                                                    Type typePop21 = state9.pop2();
                                                    this.state.push(typePop20);
                                                    this.state.push(typePop19);
                                                    this.state.push(typePop21);
                                                    this.state.push(typePop20);
                                                    this.state.push(typePop19);
                                                } else {
                                                    Type typePop22 = state9.pop1();
                                                    Type typePop23 = this.state.pop1();
                                                    this.state.push(typePop20);
                                                    this.state.push(typePop19);
                                                    this.state.push(typePop23);
                                                    this.state.push(typePop22);
                                                    this.state.push(typePop20);
                                                    this.state.push(typePop19);
                                                }
                                            }
                                            break;
                                        case 95:
                                            Type typePop24 = this.state.pop1();
                                            Type typePop25 = this.state.pop1();
                                            this.state.push(typePop24);
                                            this.state.push(typePop25);
                                            break;
                                        case 96:
                                        case 100:
                                        case 104:
                                        case 108:
                                        case 112:
                                        case 120:
                                        case 122:
                                        case 124:
                                        case 126:
                                        case 128:
                                        case 130:
                                            this.state.pop(1);
                                            break;
                                        case 97:
                                        case 101:
                                        case 105:
                                        case 109:
                                        case 113:
                                        case 127:
                                        case 129:
                                        case 131:
                                            this.state.pop(2);
                                            break;
                                        case 98:
                                        case 102:
                                        case 106:
                                        case 110:
                                        case 114:
                                            this.state.pop(1);
                                            break;
                                        case 99:
                                        case 103:
                                        case 107:
                                        case 111:
                                        case 115:
                                            this.state.pop(2);
                                            break;
                                        case 116:
                                        case 117:
                                        case 118:
                                        case 119:
                                            break;
                                        default:
                                            switch (i) {
                                                case 133:
                                                    this.state.pop(1);
                                                    this.state.push(this.syms.longType);
                                                    break;
                                                case 134:
                                                    this.state.pop(1);
                                                    this.state.push(this.syms.floatType);
                                                    break;
                                                case 135:
                                                    this.state.pop(1);
                                                    this.state.push(this.syms.doubleType);
                                                    break;
                                                case 136:
                                                    this.state.pop(2);
                                                    this.state.push(this.syms.intType);
                                                    break;
                                                case 137:
                                                    this.state.pop(2);
                                                    this.state.push(this.syms.floatType);
                                                    break;
                                                case 138:
                                                    this.state.pop(2);
                                                    this.state.push(this.syms.doubleType);
                                                    break;
                                                case 139:
                                                    this.state.pop(1);
                                                    this.state.push(this.syms.intType);
                                                    break;
                                                case 140:
                                                    this.state.pop(1);
                                                    this.state.push(this.syms.longType);
                                                    break;
                                                case 141:
                                                    this.state.pop(1);
                                                    this.state.push(this.syms.doubleType);
                                                    break;
                                                case 142:
                                                    this.state.pop(2);
                                                    this.state.push(this.syms.intType);
                                                    break;
                                                case 143:
                                                    this.state.pop(2);
                                                    this.state.push(this.syms.longType);
                                                    break;
                                                case 144:
                                                    this.state.pop(2);
                                                    this.state.push(this.syms.floatType);
                                                    break;
                                                case 145:
                                                case 146:
                                                case 147:
                                                    break;
                                                case 148:
                                                    this.state.pop(4);
                                                    this.state.push(this.syms.intType);
                                                    break;
                                                case 149:
                                                case 150:
                                                    this.state.pop(2);
                                                    this.state.push(this.syms.intType);
                                                    break;
                                                case 151:
                                                case 152:
                                                    this.state.pop(4);
                                                    this.state.push(this.syms.intType);
                                                    break;
                                                default:
                                                    switch (i) {
                                                        case 169:
                                                            markDead();
                                                            break;
                                                        case 170:
                                                        case 171:
                                                            this.state.pop(1);
                                                            break;
                                                        case 172:
                                                        case 174:
                                                        case 176:
                                                            Assert.check(this.state.nlocks == 0);
                                                            this.state.pop(1);
                                                            markDead();
                                                            break;
                                                        case 173:
                                                        case 175:
                                                            Assert.check(this.state.nlocks == 0);
                                                            this.state.pop(2);
                                                            markDead();
                                                            break;
                                                        case 177:
                                                            Assert.check(this.state.nlocks == 0);
                                                            markDead();
                                                            break;
                                                        default:
                                                            switch (i) {
                                                                case 194:
                                                                case 195:
                                                                    this.state.pop(1);
                                                                    break;
                                                                case 196:
                                                                    break;
                                                                default:
                                                                    x01.a(mnem(i));
                                                                    break;
                                                            }
                                                            break;
                                                    }
                                                    break;
                                            }
                                            break;
                                    }
                                    break;
                            }
                            break;
                    }
                }
                State state10 = this.state;
                state10.pop(state10.stacksize);
                markDead();
            }
            postop();
        }
    }

    public void emitop1(int i, int i2, PoolConstant poolConstant) {
        emitop(i);
        if (this.alive) {
            emit1(i2);
            if (i == 16) {
                this.state.push(this.syms.intType);
            } else {
                if (i != 18) {
                    x01.a(mnem(i));
                    return;
                }
                this.state.push(this.types.constantType((PoolConstant.LoadableConstant) poolConstant));
            }
            postop();
        }
    }

    public void emitop1w(int i, int i2) {
        if (i2 > 255) {
            emitop(196);
            emitop(i);
            emit2(i2);
        } else {
            emitop(i);
            emit1(i2);
        }
        if (this.alive) {
            if (i != 169) {
                switch (i) {
                    case 21:
                        this.state.push(this.syms.intType);
                        break;
                    case 22:
                        this.state.push(this.syms.longType);
                        break;
                    case 23:
                        this.state.push(this.syms.floatType);
                        break;
                    case 24:
                        this.state.push(this.syms.doubleType);
                        break;
                    case 25:
                        this.state.push(this.lvar[i2].sym.type);
                        break;
                    default:
                        switch (i) {
                            case 54:
                            case 56:
                            case 58:
                                this.state.pop(1);
                                break;
                            case 55:
                            case 57:
                                this.state.pop(2);
                                break;
                            default:
                                x01.a(mnem(i));
                                return;
                        }
                        break;
                }
            } else {
                markDead();
            }
            postop();
        }
    }

    public void emitop2(int i, int i2, PoolConstant poolConstant) {
        emitop(i);
        if (this.alive) {
            emit2(i2);
            if (i == 17) {
                this.state.push(this.syms.intType);
                return;
            }
            if (i == 187) {
                this.state.push(UninitializedType.uninitializedObject(((Type) poolConstant).tsym.erasure(this.types), this.cp - 3));
                return;
            }
            if (i == 19 || i == 20) {
                this.state.push(this.types.constantType((PoolConstant.LoadableConstant) poolConstant));
                return;
            }
            if (i == 192) {
                this.state.pop(1);
                this.state.push(this.types.erasure((Type) poolConstant));
                return;
            }
            if (i == 193) {
                this.state.pop(1);
                this.state.push(this.syms.intType);
                return;
            }
            if (i != 198 && i != 199) {
                switch (i) {
                    case 153:
                    case 154:
                    case 155:
                    case 156:
                    case 157:
                    case 158:
                        break;
                    case 159:
                    case 160:
                    case 161:
                    case 162:
                    case 163:
                    case 164:
                    case 165:
                    case 166:
                        this.state.pop(2);
                        break;
                    case 167:
                        markDead();
                        break;
                    case 168:
                        break;
                    default:
                        switch (i) {
                            case 178:
                                this.state.push(((Symbol) poolConstant).erasure(this.types));
                                break;
                            case 179:
                                this.state.pop(((Symbol) poolConstant).erasure(this.types));
                                break;
                            case 180:
                                this.state.pop(1);
                                this.state.push(((Symbol) poolConstant).erasure(this.types));
                                break;
                            case 181:
                                this.state.pop(((Symbol) poolConstant).erasure(this.types));
                                this.state.pop(1);
                                break;
                            default:
                                x01.a(mnem(i));
                                break;
                        }
                        break;
                }
                return;
            }
            this.state.pop(1);
        }
    }

    public void emitop4(int i, int i2) {
        emitop(i);
        if (this.alive) {
            emit4(i2);
            if (i == 200) {
                markDead();
            } else {
                if (i == 201) {
                    return;
                }
                x01.a(mnem(i));
            }
        }
    }

    public void endScopes(int i) {
        int i2 = this.nextreg;
        this.nextreg = i;
        while (i < i2) {
            endScope(i);
            i++;
        }
    }

    public int entryPoint(State state, Type type) {
        int iCurCP = curCP();
        this.alive = true;
        State stateDup = state.dup();
        setDefined(stateDup.defined);
        this.state = stateDup;
        Assert.check(state.stacksize <= this.max_stack);
        this.state.push(type);
        if (this.debugCode) {
            System.err.println("entry point " + state);
        }
        this.pendingStackMap = this.needStackMap;
        return iCurCP;
    }

    public void fillExceptionParameterPositions() {
        Symbol.VarSymbol varSymbol;
        for (int i = 0; i < this.varBufferSize; i++) {
            LocalVar localVar = this.varBuffer[i];
            if (localVar != null && (varSymbol = localVar.sym) != null && varSymbol.hasTypeAnnotations() && localVar.sym.isExceptionParameter()) {
                for (Attribute.TypeCompound typeCompound : localVar.sym.getRawTypeAttributes()) {
                    TypeAnnotationPosition typeAnnotationPosition = typeCompound.position;
                    if (typeAnnotationPosition.hasCatchType()) {
                        int iFindExceptionIndex = findExceptionIndex(typeAnnotationPosition);
                        if (iFindExceptionIndex == -1) {
                            Assert.error("Could not find exception index for type annotation " + typeCompound + " on exception parameter");
                        }
                        typeAnnotationPosition.setExceptionIndex(iFindExceptionIndex);
                    }
                }
            }
        }
    }

    public int get4(int i) {
        return get1(i + 3) | (get1(i) << 24) | (get1(i + 1) << 16) | (get1(i + 2) << 8);
    }

    public StackMapFrame getInitialFrame() {
        StackMapFrame stackMapFrame = new StackMapFrame();
        List<Type> list = ((Type.MethodType) this.meth.externalType(this.types)).argtypes;
        int length = list.length();
        int i = 0;
        if (this.meth.isStatic()) {
            stackMapFrame.locals = new Type[length];
        } else {
            Symbol.MethodSymbol methodSymbol = this.meth;
            Type type = methodSymbol.owner.type;
            stackMapFrame.locals = new Type[length + 1];
            if (!methodSymbol.isConstructor() || type == this.syms.objectType) {
                stackMapFrame.locals[0] = this.types.erasure(type);
            } else {
                stackMapFrame.locals[0] = UninitializedType.uninitializedThis(type);
            }
            i = 1;
        }
        Iterator<Type> it = list.iterator();
        while (it.hasNext()) {
            stackMapFrame.locals[i] = this.types.erasure(it.next());
            i++;
        }
        stackMapFrame.pc = -1;
        stackMapFrame.stack = null;
        return stackMapFrame;
    }

    public int getLVTSize() {
        int size = this.varBufferSize;
        for (int i = 0; i < this.varBufferSize; i++) {
            size += this.varBuffer[i].aliveRanges.size() - 1;
        }
        return size;
    }

    public boolean isAlive() {
        return this.alive || this.pendingJumps != null;
    }

    public boolean isStatementStart() {
        return !this.alive || this.state.stacksize == this.letExprStackPos;
    }

    public void markDead() {
        this.alive = false;
    }

    public void markStatBegin() {
        if (this.alive && this.lineDebugInfo) {
            int lineNumber = this.lineMap.getLineNumber(this.pendingStatPos);
            int i = this.cp;
            char c = (char) i;
            char c2 = (char) lineNumber;
            if (c == i && c2 == lineNumber) {
                addLineNumber(c, c2);
            }
        }
        this.pendingStatPos = -1;
    }

    public void newRegSegment() {
        this.nextreg = this.max_locals;
    }

    public void postop() {
        Assert.check(this.alive || isStatementStart());
    }

    public void put4(int i, int i2) {
        put1(i, i2 >> 24);
        put1(i + 1, i2 >> 16);
        put1(i + 2, i2 >> 8);
        put1(i + 3, i2);
    }

    public void putVar(LocalVar localVar) {
        if (this.varDebugInfo || (localVar.sym.isExceptionParameter() && localVar.sym.hasTypeAnnotations())) {
            if ((localVar.sym.flags() & 4096) == 0 || !((localVar.sym.owner.flags() & 562949953421312L) == 0 || (localVar.sym.flags() & 8589934592L) == 0)) {
                Name name = localVar.sym.name;
                if (name == name.table.names.empty) {
                    return;
                }
                LocalVar[] localVarArr = this.varBuffer;
                if (localVarArr == null) {
                    this.varBuffer = new LocalVar[20];
                } else {
                    this.varBuffer = (LocalVar[]) ArrayUtils.ensureCapacity(localVarArr, this.varBufferSize);
                }
                LocalVar[] localVarArr2 = this.varBuffer;
                int i = this.varBufferSize;
                this.varBufferSize = i + 1;
                localVarArr2[i] = localVar;
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:55:0x00a6  */
    public void resolve(Chain chain, int i) {
        boolean z;
        int i2;
        State stateJoin = this.state;
        boolean z2 = false;
        while (chain != null) {
            Assert.check(this.state != chain.state && (i > chain.pc || isStatementStart()));
            int i3 = this.cp;
            if (i >= i3) {
                i = i3;
            } else if (get1(i) == 167) {
                i += this.fatcode ? get4(i + 1) : get2(i + 1);
            }
            if (get1(chain.pc) == 167 && chain.pc + 3 == i && i == (i2 = this.cp) && !this.fixedPc) {
                if (this.varDebugInfo) {
                    adjustAliveRanges(i2, -3);
                }
                this.cp -= 3;
                i -= 3;
                if (chain.next == null) {
                    this.alive = true;
                    break;
                }
            } else {
                boolean z3 = this.fatcode;
                int i4 = chain.pc;
                if (z3) {
                    put4(i4 + 1, i - i4);
                } else if (i - i4 < -32768 || i - i4 > 32767) {
                    this.fatcode = true;
                } else {
                    put2(i4 + 1, i - i4);
                }
                if (this.alive) {
                    State state = chain.state;
                    if (state.stacksize == stateJoin.stacksize && state.nlocks == stateJoin.nlocks) {
                        z = true;
                    } else {
                        z = false;
                    }
                } else {
                    z = true;
                }
                Assert.check(z);
            }
            this.fixedPc = true;
            if (this.cp == i) {
                if (this.debugCode) {
                    System.err.println("resolving chain state=" + chain.state);
                }
                boolean z4 = this.alive;
                State state2 = chain.state;
                if (z4) {
                    stateJoin = state2.join(stateJoin);
                    z2 = true;
                } else {
                    this.alive = true;
                    z2 = true;
                    stateJoin = state2;
                }
            }
            chain = chain.next;
        }
        Assert.check((z2 && this.state == stateJoin) ? false : true);
        if (this.state != stateJoin) {
            setDefined(stateJoin.defined);
            this.state = stateJoin;
            this.pendingStackMap = this.needStackMap;
        }
    }

    public void resolvePending() {
        Chain chain = this.pendingJumps;
        this.pendingJumps = null;
        resolve(chain, this.cp);
    }

    public void setDefined(Bits bits) {
        if (!this.alive || bits == this.state.defined) {
            return;
        }
        Bits bitsXorSet = new Bits(this.state.defined).xorSet(bits);
        for (int iNextBit = bitsXorSet.nextBit(0); iNextBit >= 0; iNextBit = bitsXorSet.nextBit(iNextBit + 1)) {
            int i = this.nextreg;
            State state = this.state;
            if (iNextBit >= i) {
                state.defined.excl(iNextBit);
            } else if (state.defined.isMember(iNextBit)) {
                setUndefined(iNextBit);
            } else {
                setDefined(iNextBit);
            }
        }
    }

    public int setLetExprStackPos(int i) {
        int i2 = this.letExprStackPos;
        this.letExprStackPos = i;
        return i2;
    }

    public void setUndefined(int i) {
        LocalVar localVar;
        this.state.defined.excl(i);
        LocalVar[] localVarArr = this.lvar;
        if (i >= localVarArr.length || (localVar = localVarArr[i]) == null || !localVar.isLastRangeInitialized()) {
            return;
        }
        LocalVar localVar2 = this.lvar[i];
        char cCurCP = (char) (curCP() - localVar2.lastRange().start_pc);
        if (cCurCP >= 65535) {
            localVar2.removeLastRange();
            return;
        }
        this.lvar[i] = localVar2.dup();
        localVar2.closeRange(cCurCP);
        putVar(localVar2);
        fillLocalVarPosition(localVar2);
    }

    public void statBegin(int i) {
        if (i != -1) {
            this.pendingStatPos = i;
        }
    }

    public enum StackMapFormat {
        NONE,
        CLDC { // from class: com.sun.tools.javac.jvm.Code.StackMapFormat.1
            @Override // com.sun.tools.javac.jvm.Code.StackMapFormat
            public Name getAttributeName(Names names) {
                return names.StackMap;
            }
        },
        JSR202 { // from class: com.sun.tools.javac.jvm.Code.StackMapFormat.2
            @Override // com.sun.tools.javac.jvm.Code.StackMapFormat
            public Name getAttributeName(Names names) {
                return names.StackMapTable;
            }
        };

        public Name getAttributeName(Names names) {
            return names.empty;
        }

        /* synthetic */ StackMapFormat(AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    public static class LocalVar {
        java.util.List<Range> aliveRanges = new ArrayList();
        final char reg;
        final Symbol.VarSymbol sym;

        public LocalVar(Symbol.VarSymbol varSymbol) {
            this.sym = varSymbol;
            this.reg = (char) varSymbol.adr;
        }

        public void closeRange(char c) {
            if (!isLastRangeInitialized() || c <= 0) {
                removeLastRange();
                return;
            }
            Range rangeLastRange = lastRange();
            if (rangeLastRange == null || rangeLastRange.length != 65535) {
                return;
            }
            rangeLastRange.length = c;
        }

        public LocalVar dup() {
            return new LocalVar(this.sym);
        }

        public Range firstRange() {
            if (this.aliveRanges.isEmpty()) {
                return null;
            }
            return this.aliveRanges.get(0);
        }

        public Range getWidestRange() {
            if (this.aliveRanges.isEmpty()) {
                return new Range();
            }
            Range rangeFirstRange = firstRange();
            Range rangeLastRange = lastRange();
            char c = rangeLastRange.length;
            char c2 = rangeLastRange.start_pc;
            char c3 = rangeFirstRange.start_pc;
            return new Range(c3, (char) (c + (c2 - c3)));
        }

        public boolean hasOpenRange() {
            return !this.aliveRanges.isEmpty() && lastRange().length == 65535;
        }

        public boolean isLastRangeInitialized() {
            return (this.aliveRanges.isEmpty() || lastRange().start_pc == 65535) ? false : true;
        }

        public Range lastRange() {
            if (this.aliveRanges.isEmpty()) {
                return null;
            }
            java.util.List<Range> list = this.aliveRanges;
            return list.get(list.size() - 1);
        }

        public void openRange(char c) {
            if (hasOpenRange()) {
                return;
            }
            this.aliveRanges.add(new Range(c));
        }

        public void removeLastRange() {
            Range rangeLastRange = lastRange();
            if (rangeLastRange != null) {
                this.aliveRanges.remove(rangeLastRange);
            }
        }

        public String toString() {
            if (this.aliveRanges == null) {
                return "empty local var";
            }
            StringBuilder sb = new StringBuilder();
            sb.append(this.sym);
            sb.append(" in register ");
            sb.append((int) this.reg);
            sb.append(" \n");
            for (Range range : this.aliveRanges) {
                sb.append(" starts at pc=");
                sb.append(Integer.toString(range.start_pc));
                sb.append(" length=");
                sb.append(Integer.toString(range.length));
                sb.append("\n");
            }
            return sb.toString();
        }

        public class Range {
            char length;
            char start_pc;

            public Range() {
                this.start_pc = (char) 65535;
                this.length = (char) 65535;
            }

            public boolean closed() {
                return (this.start_pc == 65535 || this.length == 65535) ? false : true;
            }

            public String toString() {
                return "startpc = " + ((int) this.start_pc) + " length " + ((int) this.length);
            }

            public Range(char c) {
                this.length = (char) 65535;
                this.start_pc = c;
            }

            public Range(char c, char c2) {
                this.start_pc = c;
                this.length = c2;
            }
        }
    }

    private int newLocal(Type type) {
        return newLocal(typecode(type));
    }

    public int newLocal(Symbol.VarSymbol varSymbol) {
        int iNewLocal = newLocal(varSymbol.erasure(this.types));
        varSymbol.adr = iNewLocal;
        addLocalVar(varSymbol);
        return iNewLocal;
    }

    public static int width(Type type) {
        if (type == null) {
            return 1;
        }
        return width(typecode(type));
    }

    public static int width(int i) {
        if (i == 1 || i == 3) {
            return 2;
        }
        return i != 8 ? 1 : 0;
    }

    public class State implements Cloneable {
        int[] locks;
        int nlocks;
        int stacksize;
        Bits defined = new Bits();
        Type[] stack = new Type[16];

        public State() {
        }

        private Type commonSuperClass(Type type, Type type2) {
            if (type != type2) {
                if (Code.this.types.isSubtype(type, type2)) {
                    return type2;
                }
                if (!Code.this.types.isSubtype(type2, type)) {
                    Type typeErasedSuper = erasedSuper(type, type2);
                    if (typeErasedSuper != null && !typeErasedSuper.hasTag(TypeTag.BOT)) {
                        return typeErasedSuper;
                    }
                    throw Assert.error("Cannot find a common super class of: " + type + " and " + type2);
                }
            }
            return type;
        }

        private Type erasedSuper(Type type, Type type2) {
            List<Type> listErasedSupertypes;
            List<Type> listErasedSupertypes2;
            TypeTag typeTag = TypeTag.ARRAY;
            if (type.hasTag(typeTag) && type2.hasTag(typeTag)) {
                Type typeElemtype = Code.this.types.elemtype(type);
                Type typeElemtype2 = Code.this.types.elemtype(type2);
                if (typeElemtype.isPrimitive() || typeElemtype2.isPrimitive()) {
                    return typeElemtype.tsym == typeElemtype2.tsym ? type : Code.this.syms.serializableType;
                }
                return new Type.ArrayType(erasedSuper(typeElemtype, typeElemtype2), Code.this.syms.arrayClass);
            }
            Type typeSkipTypeVars = Code.this.types.skipTypeVars(type, false);
            Type typeSkipTypeVars2 = Code.this.types.skipTypeVars(type2, false);
            Types types = Code.this.types;
            boolean zHasTag = typeSkipTypeVars.hasTag(typeTag);
            Code code = Code.this;
            if (zHasTag) {
                Symtab symtab = code.syms;
                listErasedSupertypes = List.of(symtab.serializableType, symtab.cloneableType, symtab.objectType);
            } else {
                listErasedSupertypes = code.types.erasedSupertypes(typeSkipTypeVars);
            }
            boolean zHasTag2 = typeSkipTypeVars2.hasTag(typeTag);
            Code code2 = Code.this;
            if (zHasTag2) {
                Symtab symtab2 = code2.syms;
                listErasedSupertypes2 = List.of(symtab2.serializableType, symtab2.cloneableType, symtab2.objectType);
            } else {
                listErasedSupertypes2 = code2.types.erasedSupertypes(typeSkipTypeVars2);
            }
            return types.closureMin(types.intersect(listErasedSupertypes, listErasedSupertypes2)).head;
        }

        public void dump(int i) {
            System.err.print("stackMap for " + Code.this.meth.owner + Constants.ATTRVAL_THIS + Code.this.meth);
            if (i == -1) {
                System.out.println();
            } else {
                System.out.println(" at " + i);
            }
            System.err.println(" stack (from bottom):");
            for (int i2 = 0; i2 < this.stacksize; i2++) {
                System.err.println("  " + i2 + ": " + this.stack[i2]);
            }
            int i3 = Code.this.max_locals - 1;
            while (true) {
                if (i3 < 0) {
                    i3 = 0;
                    break;
                } else if (this.defined.isMember(i3)) {
                    break;
                } else {
                    i3--;
                }
            }
            if (i3 >= 0) {
                System.err.println(" locals:");
            }
            for (int i4 = 0; i4 <= i3; i4++) {
                System.err.print("  " + i4 + ": ");
                if (this.defined.isMember(i4)) {
                    LocalVar localVar = Code.this.lvar[i4];
                    if (localVar == null) {
                        System.err.println("(none)");
                    } else if (localVar.sym == null) {
                        System.err.println("UNKNOWN!");
                    } else {
                        System.err.println("" + localVar.sym + " of type " + localVar.sym.erasure(Code.this.types));
                    }
                } else {
                    System.err.println("undefined");
                }
            }
            if (this.nlocks != 0) {
                System.err.print(" locks:");
                for (int i5 = 0; i5 < this.nlocks; i5++) {
                    System.err.print(" " + this.locks[i5]);
                }
                System.err.println();
            }
        }

        public State dup() {
            try {
                State state = (State) super.clone();
                state.defined = new Bits(this.defined);
                state.stack = (Type[]) this.stack.clone();
                int[] iArr = this.locks;
                if (iArr != null) {
                    state.locks = (int[]) iArr.clone();
                }
                if (Code.this.debugCode) {
                    System.err.println("duping state " + this);
                    dump();
                }
                return state;
            } catch (CloneNotSupportedException e) {
                x01.a(e);
                return null;
            }
        }

        public void forceStackTop(Type type) {
            if (Code.this.alive) {
                int i = AnonymousClass1.$SwitchMap$com$sun$tools$javac$code$TypeTag[type.getTag().ordinal()];
                if (i == 10 || i == 11) {
                    int iWidth = Code.width(type);
                    Type type2 = this.stack[this.stacksize - iWidth];
                    Types types = Code.this.types;
                    Assert.check(types.isSubtype(types.erasure(type2), Code.this.types.erasure(type)));
                    this.stack[this.stacksize - iWidth] = type;
                }
            }
        }

        public State join(State state) {
            this.defined.andSet(state.defined);
            int i = 0;
            Assert.check(this.stacksize == state.stacksize && this.nlocks == state.nlocks);
            while (i < this.stacksize) {
                Type typeCommonSuperClass = commonSuperClass(this.stack[i], state.stack[i]);
                int iWidth = Code.width(typeCommonSuperClass);
                Type[] typeArr = this.stack;
                typeArr[i] = typeCommonSuperClass;
                if (iWidth == 2) {
                    Assert.checkNull(typeArr[i + 1]);
                }
                i += iWidth;
            }
            return this;
        }

        public void lock(int i) {
            int[] iArr = this.locks;
            if (iArr == null) {
                this.locks = new int[20];
            } else {
                this.locks = ArrayUtils.ensureCapacity(iArr, this.nlocks);
            }
            int[] iArr2 = this.locks;
            int i2 = this.nlocks;
            iArr2[i2] = i;
            this.nlocks = i2 + 1;
        }

        public void markInitialized(UninitializedType uninitializedType) {
            Type typeInitializedType = uninitializedType.initializedType();
            int i = 0;
            for (int i2 = 0; i2 < this.stacksize; i2++) {
                Type[] typeArr = this.stack;
                if (typeArr[i2] == uninitializedType) {
                    typeArr[i2] = typeInitializedType;
                }
            }
            while (true) {
                LocalVar[] localVarArr = Code.this.lvar;
                if (i >= localVarArr.length) {
                    return;
                }
                LocalVar localVar = localVarArr[i];
                if (localVar != null) {
                    Symbol.VarSymbol varSymbol = localVar.sym;
                    if (varSymbol.type == uninitializedType) {
                        Symbol.VarSymbol varSymbolClone = varSymbol.clone(varSymbol.owner);
                        varSymbolClone.type = typeInitializedType;
                        LocalVar[] localVarArr2 = Code.this.lvar;
                        LocalVar localVar2 = new LocalVar(varSymbolClone);
                        localVarArr2[i] = localVar2;
                        localVar2.aliveRanges = localVar.aliveRanges;
                    }
                }
                i++;
            }
        }

        public Type peek() {
            return this.stack[this.stacksize - 1];
        }

        public void pop(int i) {
            if (Code.this.debugCode) {
                System.err.println("   popping " + i);
            }
            while (i > 0) {
                Type[] typeArr = this.stack;
                int i2 = this.stacksize - 1;
                this.stacksize = i2;
                typeArr[i2] = null;
                i--;
            }
        }

        public Type pop1() {
            if (Code.this.debugCode) {
                System.err.println("   popping 1");
            }
            int i = this.stacksize - 1;
            this.stacksize = i;
            Type[] typeArr = this.stack;
            Type type = typeArr[i];
            typeArr[i] = null;
            Assert.check(type != null && Code.width(type) == 1);
            return type;
        }

        public Type pop2() {
            if (Code.this.debugCode) {
                System.err.println("   popping 2");
            }
            int i = this.stacksize;
            int i2 = i - 2;
            this.stacksize = i2;
            Type[] typeArr = this.stack;
            Type type = typeArr[i2];
            typeArr[i2] = null;
            Assert.check(typeArr[i - 1] == null && type != null && Code.width(type) == 2);
            return type;
        }

        public void push(Type type) {
            if (Code.this.debugCode) {
                System.err.println("   pushing " + type);
            }
            int i = AnonymousClass1.$SwitchMap$com$sun$tools$javac$code$TypeTag[type.getTag().ordinal()];
            if (i == 1 || i == 2 || i == 3 || i == 8) {
                type = Code.this.syms.intType;
            } else if (i == 9) {
                return;
            }
            Type[] typeArr = (Type[]) ArrayUtils.ensureCapacity(this.stack, this.stacksize + 2);
            this.stack = typeArr;
            int i2 = this.stacksize;
            this.stacksize = i2 + 1;
            typeArr[i2] = type;
            int iWidth = Code.width(type);
            if (iWidth != 1) {
                if (iWidth != 2) {
                    x01.a(type);
                    return;
                }
                Type[] typeArr2 = this.stack;
                int i3 = this.stacksize;
                this.stacksize = i3 + 1;
                typeArr2[i3] = null;
            }
            int i4 = this.stacksize;
            Code code = Code.this;
            if (i4 > code.max_stack) {
                code.max_stack = i4;
            }
        }

        public void unlock(int i) {
            int i2 = this.nlocks - 1;
            this.nlocks = i2;
            Assert.check(this.locks[i2] == i);
            this.locks[this.nlocks] = -1;
        }

        public void pop(Type type) {
            pop(Code.width(type));
        }

        public void dump() {
            dump(-1);
        }
    }

    public void emitop1(int i, int i2) {
        emitop1(i, i2, null);
    }

    public int entryPoint(State state) {
        int iCurCP = curCP();
        this.alive = true;
        State stateDup = state.dup();
        setDefined(stateDup.defined);
        this.state = stateDup;
        Assert.check(state.stacksize <= this.max_stack);
        if (this.debugCode) {
            System.err.println("entry point " + state);
        }
        this.pendingStackMap = this.needStackMap;
        return iCurCP;
    }

    public void setDefined(int i) {
        LocalVar localVar = this.lvar[i];
        State state = this.state;
        if (localVar == null) {
            state.defined.excl(i);
            return;
        }
        state.defined.incl(i);
        int i2 = this.cp;
        if (i2 < 65535) {
            localVar.openRange((char) i2);
        }
    }

    public int entryPoint() {
        int iCurCP = curCP();
        this.alive = true;
        this.pendingStackMap = this.needStackMap;
        return iCurCP;
    }

    public void emitop1w(int i, int i2, int i3) {
        if (i2 <= 255 && i3 >= -128 && i3 <= 127) {
            emitop(i);
            emit1(i2);
            emit1(i3);
        } else {
            emitop(196);
            emitop(i);
            emit2(i2);
            emit2(i3);
        }
        if (this.alive && i != 132) {
            x01.a(mnem(i));
        }
    }

    public void emitop2(int i, int i2) {
        emitop2(i, i2, (PoolConstant) null);
    }

    public <P extends PoolConstant> void emitop2(int i, P p, ToIntBiFunction<PoolWriter, P> toIntBiFunction) {
        emitop2(i, toIntBiFunction.applyAsInt(this.poolWriter, p), p);
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0019  */
    public void resolve(Chain chain) {
        boolean z;
        if (!this.alive || chain == null) {
            z = true;
        } else {
            State state = this.state;
            int i = state.stacksize;
            State state2 = chain.state;
            if (i == state2.stacksize && state.nlocks == state2.nlocks) {
                z = true;
            } else {
                z = false;
            }
        }
        Assert.check(z);
        this.pendingJumps = mergeChains(chain, this.pendingJumps);
    }
}
