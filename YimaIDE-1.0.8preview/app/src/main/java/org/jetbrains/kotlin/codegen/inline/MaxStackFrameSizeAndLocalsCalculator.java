package org.jetbrains.kotlin.codegen.inline;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.codegen.inline.MaxStackFrameSizeAndLocalsCalculator;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.utils.SmartIdentityTable;
import org.jetbrains.kotlin.utils.SmartList;
import org.jetbrains.org.objectweb.asm.Handle;
import org.jetbrains.org.objectweb.asm.Label;
import org.jetbrains.org.objectweb.asm.MethodVisitor;
import org.jetbrains.org.objectweb.asm.Type;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u0015\n\u0002\b\u0015\u0018\u0000 Q2\u00020\u0001:\u0002PQB+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\t\u0010\nJA\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u00032\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\u0006\u0010\u001b\u001a\u00020\u00032\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019H\u0016¢\u0006\u0002\u0010\u001cJ\u0010\u0010\u001d\u001a\u00020\u00152\u0006\u0010\u001e\u001a\u00020\u0003H\u0016J\u0018\u0010\u001f\u001a\u00020\u00152\u0006\u0010\u001e\u001a\u00020\u00032\u0006\u0010 \u001a\u00020\u0003H\u0016J\u0018\u0010!\u001a\u00020\u00152\u0006\u0010\u001e\u001a\u00020\u00032\u0006\u0010\"\u001a\u00020\u0003H\u0016J\u0018\u0010#\u001a\u00020\u00152\u0006\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u0006H\u0016J(\u0010$\u001a\u00020\u00152\u0006\u0010\u001e\u001a\u00020\u00032\u0006\u0010%\u001a\u00020\u00062\u0006\u0010&\u001a\u00020\u00062\u0006\u0010'\u001a\u00020\u0006H\u0016J0\u0010(\u001a\u00020\u00152\u0006\u0010\u001e\u001a\u00020\u00032\u0006\u0010%\u001a\u00020\u00062\u0006\u0010&\u001a\u00020\u00062\u0006\u0010'\u001a\u00020\u00062\u0006\u0010)\u001a\u00020*H\u0016J9\u0010+\u001a\u00020\u00152\u0006\u0010&\u001a\u00020\u00062\u0006\u0010'\u001a\u00020\u00062\u0006\u0010,\u001a\u00020-2\u0012\u0010.\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u001a0\u0019\"\u00020\u001aH\u0016¢\u0006\u0002\u0010/J\u0018\u00100\u001a\u00020\u00152\u0006\u0010\u001e\u001a\u00020\u00032\u0006\u00101\u001a\u00020\rH\u0016J\u0010\u00102\u001a\u00020\u00152\u0006\u00101\u001a\u00020\rH\u0016J\u0012\u00103\u001a\u00020\u00152\b\u00104\u001a\u0004\u0018\u00010\u001aH\u0016J9\u00105\u001a\u00020\u00152\u0006\u00106\u001a\u00020\u00032\u0006\u00107\u001a\u00020\u00032\u0006\u00108\u001a\u00020\r2\u0012\u00109\u001a\n\u0012\u0006\b\u0001\u0012\u00020\r0\u0019\"\u00020\rH\u0016¢\u0006\u0002\u0010:J-\u0010;\u001a\u00020\u00152\u0006\u00108\u001a\u00020\r2\u0006\u0010<\u001a\u00020=2\u000e\u00109\u001a\n\u0012\u0006\b\u0001\u0012\u00020\r0\u0019H\u0016¢\u0006\u0002\u0010>J%\u0010?\u001a\u00020\u00152\u0006\u00108\u001a\u00020\r2\u000e\u00109\u001a\n\u0012\u0006\b\u0001\u0012\u00020\r0\u0019H\u0002¢\u0006\u0002\u0010@J\u0018\u0010A\u001a\u00020\u00152\u0006\u0010'\u001a\u00020\u00062\u0006\u0010B\u001a\u00020\u0003H\u0016J\u0018\u0010C\u001a\u00020\u00152\u0006\u0010D\u001a\u00020\u00032\u0006\u0010E\u001a\u00020\u0003H\u0016J*\u0010F\u001a\u00020\u00152\u0006\u0010G\u001a\u00020\r2\u0006\u0010H\u001a\u00020\r2\u0006\u0010I\u001a\u00020\r2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0006H\u0016J\u0010\u0010J\u001a\u00020\u000e2\u0006\u00101\u001a\u00020\rH\u0002J\u0010\u0010K\u001a\u00020\u00152\u0006\u0010L\u001a\u00020\u0003H\u0002J\u0014\u0010M\u001a\u00020\u0015*\u00020\u000e2\u0006\u00101\u001a\u00020\rH\u0002J\u0014\u0010N\u001a\u00020\u0015*\u00020\u000e2\u0006\u0010O\u001a\u00020\u0003H\u0002R\u001a\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006R"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/MaxStackFrameSizeAndLocalsCalculator;", "Lorg/jetbrains/kotlin/codegen/inline/MaxLocalsCalculator;", "api", Argument.Delimiters.none, "access", "descriptor", Argument.Delimiters.none, "mv", "Lorg/jetbrains/org/objectweb/asm/MethodVisitor;", "<init>", "(IILjava/lang/String;Lorg/jetbrains/org/objectweb/asm/MethodVisitor;)V", "basicBlocks", "Lorg/jetbrains/kotlin/utils/SmartIdentityTable;", "Lorg/jetbrains/org/objectweb/asm/Label;", "Lorg/jetbrains/kotlin/codegen/inline/MaxStackFrameSizeAndLocalsCalculator$BasicBlock;", "stack", "Ljava/util/Stack;", "currentBlockStackDelta", "currentBlock", CoroutineCodegenUtilKt.CONTINUATION_RESULT_FIELD_NAME, "visitFrame", Argument.Delimiters.none, ModuleXmlParser.TYPE, "nLocal", "local", Argument.Delimiters.none, Argument.Delimiters.none, "nStack", "(II[Ljava/lang/Object;I[Ljava/lang/Object;)V", "visitInsn", "opcode", "visitIntInsn", "operand", "visitVarInsn", "var", "visitTypeInsn", "visitFieldInsn", "owner", ModuleXmlParser.NAME, "desc", "visitMethodInsn", "itf", Argument.Delimiters.none, "visitInvokeDynamicInsn", "bsm", "Lorg/jetbrains/org/objectweb/asm/Handle;", "bsmArgs", "(Ljava/lang/String;Ljava/lang/String;Lorg/jetbrains/org/objectweb/asm/Handle;[Ljava/lang/Object;)V", "visitJumpInsn", CoroutineCodegenUtilKt.COROUTINE_LABEL_FIELD_NAME, "visitLabel", "visitLdcInsn", "cst", "visitTableSwitchInsn", "min", "max", "dflt", "labels", "(IILorg/jetbrains/org/objectweb/asm/Label;[Lorg/jetbrains/org/objectweb/asm/Label;)V", "visitLookupSwitchInsn", "keys", Argument.Delimiters.none, "(Lorg/jetbrains/org/objectweb/asm/Label;[I[Lorg/jetbrains/org/objectweb/asm/Label;)V", "visitSwitchInsn", "(Lorg/jetbrains/org/objectweb/asm/Label;[Lorg/jetbrains/org/objectweb/asm/Label;)V", "visitMultiANewArrayInsn", "dims", "visitMaxs", "maxStack", "maxLocals", "visitTryCatchBlock", "start", "end", "handler", "getBasicBlockAt", "increaseStackSize", "variation", "addSuccessor", "fixInputStackSize", "value", "BasicBlock", "Companion", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class MaxStackFrameSizeAndLocalsCalculator extends MaxLocalsCalculator {
    private static final int[] FRAME_SIZE_CHANGE_BY_OPCODE;
    private final SmartIdentityTable<Label, BasicBlock> basicBlocks;
    private BasicBlock currentBlock;
    private int currentBlockStackDelta;
    private int result;
    private final Stack<BasicBlock> stack;

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u000b\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003R#\u0010\u0004\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00070\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000e¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/MaxStackFrameSizeAndLocalsCalculator$BasicBlock;", Argument.Delimiters.none, "<init>", "()V", "successors", Argument.Delimiters.none, "Lkotlin/Pair;", Argument.Delimiters.none, "getSuccessors", "()Ljava/util/List;", "inputStackSize", "getInputStackSize", "()I", "setInputStackSize", "(I)V", "maxStackDelta", "getMaxStackDelta", "setMaxStackDelta", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class BasicBlock {
        private int maxStackDelta;
        private final List<Pair<BasicBlock, Integer>> successors = new SmartList();
        private int inputStackSize = -1;

        public final int getInputStackSize() {
            return this.inputStackSize;
        }

        public final int getMaxStackDelta() {
            return this.maxStackDelta;
        }

        public final List<Pair<BasicBlock, Integer>> getSuccessors() {
            return this.successors;
        }

        public final void setInputStackSize(int i) {
            this.inputStackSize = i;
        }

        public final void setMaxStackDelta(int i) {
            this.maxStackDelta = i;
        }
    }

    static {
        int[] iArr = new int[202];
        for (int i = 0; i < 202; i++) {
            iArr[i] = "EFFFFFFFFGGFFFGGFFFEEFGFGFEEEEEEEEEEEEEEEEEEEEDEDEDDDDDCDCDEEEEEEEEEEEEEEEEEEEEBABABBBBDCFFFGGGEDCDCDCDCDCDCDCDCDCDCEEEEDDDDDDDCDCDCEFEFDDEEFFDEDEEEBDDBBDDDDDDCCCCCCCCEFEDDDCDCDEEEEEEEEEEFEEEEEEDDEEDDEE".charAt(i) - 'E';
        }
        FRAME_SIZE_CHANGE_BY_OPCODE = iArr;
    }

    public MaxStackFrameSizeAndLocalsCalculator(int i, int i2, String str, MethodVisitor methodVisitor) {
        super(i, i2, str, methodVisitor);
        this.basicBlocks = new SmartIdentityTable<>();
        this.stack = new Stack<>();
        BasicBlock basicBlock = new BasicBlock();
        fixInputStackSize(basicBlock, 0);
        this.currentBlock = basicBlock;
    }

    public static BasicBlock a() {
        return new BasicBlock();
    }

    private final void addSuccessor(BasicBlock basicBlock, Label label) {
        basicBlock.getSuccessors().add(TuplesKt.to(getBasicBlockAt(label), Integer.valueOf(this.currentBlockStackDelta)));
    }

    private final void fixInputStackSize(BasicBlock basicBlock, int i) {
        if (basicBlock.getInputStackSize() < 0) {
            basicBlock.setInputStackSize(i);
            this.stack.add(basicBlock);
        }
    }

    private final BasicBlock getBasicBlockAt(Label label) {
        return (BasicBlock) this.basicBlocks.getOrCreate(label, new Function0() { // from class: qv9
            public final Object invoke() {
                return MaxStackFrameSizeAndLocalsCalculator.a();
            }
        });
    }

    private final void increaseStackSize(int variation) {
        BasicBlock basicBlock = this.currentBlock;
        if (basicBlock != null) {
            int i = this.currentBlockStackDelta + variation;
            if (variation > 0 && basicBlock.getMaxStackDelta() < i) {
                basicBlock.setMaxStackDelta(i);
            }
            this.currentBlockStackDelta = i;
        }
    }

    private final void visitSwitchInsn(Label dflt, Label[] labels) {
        BasicBlock basicBlock = this.currentBlock;
        if (basicBlock != null) {
            this.currentBlockStackDelta--;
            addSuccessor(basicBlock, dflt);
            for (Label label : labels) {
                addSuccessor(basicBlock, label);
            }
        }
        this.currentBlock = null;
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0024  */
    /* JADX WARN: Code duplicated, block: B:6:0x001b  */
    public void visitFieldInsn(int opcode, String owner, String name, String desc) {
        owner.getClass();
        name.getClass();
        desc.getClass();
        int i = 0;
        char cCharAt = desc.charAt(0);
        switch (opcode) {
            case 178:
                if (cCharAt == 'D' || cCharAt == 'J') {
                    i = 2;
                } else {
                    i = 1;
                }
                break;
            case 179:
                if (cCharAt == 'D' || cCharAt == 'J') {
                    i = -2;
                } else {
                    i = -1;
                }
                break;
            case 180:
                if (cCharAt == 'D' || cCharAt == 'J') {
                    i = 1;
                }
                break;
            default:
                if (cCharAt == 'D' || cCharAt == 'J') {
                    i = -3;
                } else {
                    i = -2;
                }
                break;
        }
        increaseStackSize(i);
        super.visitFieldInsn(opcode, owner, name, desc);
    }

    public void visitFrame(int type, int nLocal, Object[] local, int nStack, Object[] stack) {
        local.getClass();
        stack.getClass();
        throw new AssertionError("We don't support visitFrame because currently nobody needs");
    }

    public void visitInsn(int opcode) {
        increaseStackSize(FRAME_SIZE_CHANGE_BY_OPCODE[opcode]);
        if ((opcode >= 172 && opcode <= 177) || opcode == 191) {
            this.currentBlock = null;
        }
        super.visitInsn(opcode);
    }

    public void visitIntInsn(int opcode, int operand) {
        if (opcode != 188) {
            increaseStackSize(1);
        }
        super.visitIntInsn(opcode, operand);
    }

    public void visitInvokeDynamicInsn(String name, String desc, Handle bsm, Object... bsmArgs) {
        name.getClass();
        desc.getClass();
        bsm.getClass();
        bsmArgs.getClass();
        int argumentsAndReturnSizes = Type.getArgumentsAndReturnSizes(desc);
        increaseStackSize(((argumentsAndReturnSizes & 3) - (argumentsAndReturnSizes >> 2)) + 1);
        super.visitInvokeDynamicInsn(name, desc, bsm, Arrays.copyOf(bsmArgs, bsmArgs.length));
    }

    public void visitJumpInsn(int opcode, Label label) {
        label.getClass();
        BasicBlock basicBlock = this.currentBlock;
        if (basicBlock != null) {
            this.currentBlockStackDelta += FRAME_SIZE_CHANGE_BY_OPCODE[opcode];
            addSuccessor(basicBlock, label);
            if (opcode == 167) {
                this.currentBlock = null;
            }
        }
        super.visitJumpInsn(opcode, label);
    }

    public void visitLabel(Label label) {
        label.getClass();
        BasicBlock basicBlock = this.currentBlock;
        if (basicBlock != null) {
            addSuccessor(basicBlock, label);
        }
        this.currentBlock = getBasicBlockAt(label);
        this.currentBlockStackDelta = 0;
        super.visitLabel(label);
    }

    public void visitLdcInsn(Object cst) {
        increaseStackSize(((cst instanceof Long) || (cst instanceof Double)) ? 2 : 1);
        super.visitLdcInsn(cst);
    }

    public void visitLookupSwitchInsn(Label dflt, int[] keys, Label[] labels) {
        dflt.getClass();
        keys.getClass();
        labels.getClass();
        visitSwitchInsn(dflt, labels);
        super.visitLookupSwitchInsn(dflt, keys, labels);
    }

    @Override // org.jetbrains.kotlin.codegen.inline.MaxLocalsCalculator
    public void visitMaxs(int maxStack, int maxLocals) {
        int iMax = this.result;
        CollectionsKt.reverse(this.stack);
        while (!this.stack.empty()) {
            BasicBlock basicBlockPop = this.stack.pop();
            int inputStackSize = basicBlockPop.getInputStackSize();
            iMax = Math.max(iMax, basicBlockPop.getMaxStackDelta() + inputStackSize);
            for (Pair<BasicBlock, Integer> pair : basicBlockPop.getSuccessors()) {
                fixInputStackSize((BasicBlock) pair.component1(), Math.max(0, ((Number) pair.component2()).intValue() + inputStackSize));
            }
        }
        this.result = iMax;
        super.visitMaxs(Math.max(maxStack, iMax), maxLocals);
    }

    public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
        owner.getClass();
        name.getClass();
        desc.getClass();
        int argumentsAndReturnSizes = Type.getArgumentsAndReturnSizes(desc);
        increaseStackSize(((argumentsAndReturnSizes & 3) - (argumentsAndReturnSizes >> 2)) + (opcode == 184 ? 1 : 0));
        super.visitMethodInsn(opcode, owner, name, desc, itf);
    }

    public void visitMultiANewArrayInsn(String desc, int dims) {
        desc.getClass();
        increaseStackSize(dims - 1);
        super.visitMultiANewArrayInsn(desc, dims);
    }

    public void visitTableSwitchInsn(int min, int max, Label dflt, Label... labels) {
        dflt.getClass();
        labels.getClass();
        visitSwitchInsn(dflt, labels);
        super.visitTableSwitchInsn(min, max, dflt, (Label[]) Arrays.copyOf(labels, labels.length));
    }

    public void visitTryCatchBlock(Label start, Label end, Label handler, String type) {
        start.getClass();
        end.getClass();
        handler.getClass();
        fixInputStackSize(getBasicBlockAt(handler), 1);
        super.visitTryCatchBlock(start, end, handler, type);
    }

    public void visitTypeInsn(int opcode, String type) {
        type.getClass();
        if (opcode == 187) {
            increaseStackSize(1);
        }
        super.visitTypeInsn(opcode, type);
    }

    @Override // org.jetbrains.kotlin.codegen.inline.MaxLocalsCalculator
    public void visitVarInsn(int opcode, int var) {
        increaseStackSize(FRAME_SIZE_CHANGE_BY_OPCODE[opcode]);
        super.visitVarInsn(opcode, var);
    }
}
