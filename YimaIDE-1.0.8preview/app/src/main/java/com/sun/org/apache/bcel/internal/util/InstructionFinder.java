package com.sun.org.apache.bcel.internal.util;

import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.bcel.internal.generic.InstructionHandle;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.bcel.internal.util.InstructionFinder;
import com.sun.org.apache.xalan.internal.templates.Constants;
import defpackage.aca;
import defpackage.yz0;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class InstructionFinder {
    private static final int NO_OPCODES = 256;
    private static final int OFFSET = 32767;
    private static final Map<String, String> map;
    private InstructionHandle[] handles;
    private final InstructionList il;
    private String ilString;

    public interface CodeConstraint {
        boolean checkCode(InstructionHandle[] instructionHandleArr);
    }

    static {
        HashMap map2 = new HashMap();
        map = map2;
        map2.put("arithmeticinstruction", "(irem|lrem|iand|ior|ineg|isub|lneg|fneg|fmul|ldiv|fadd|lxor|frem|idiv|land|ixor|ishr|fsub|lshl|fdiv|iadd|lor|dmul|lsub|ishl|imul|lmul|lushr|dneg|iushr|lshr|ddiv|drem|dadd|ladd|dsub)");
        map2.put("invokeinstruction", "(invokevirtual|invokeinterface|invokestatic|invokespecial|invokedynamic)");
        map2.put("arrayinstruction", "(baload|aastore|saload|caload|fastore|lastore|iaload|castore|iastore|aaload|bastore|sastore|faload|laload|daload|dastore)");
        map2.put("gotoinstruction", "(goto|goto_w)");
        map2.put("conversioninstruction", "(d2l|l2d|i2s|d2i|l2i|i2b|l2f|d2f|f2i|i2d|i2l|f2d|i2c|f2l|i2f)");
        map2.put("localvariableinstruction", "(fstore|iinc|lload|dstore|dload|iload|aload|astore|istore|fload|lstore)");
        map2.put("loadinstruction", "(fload|dload|lload|iload|aload)");
        map2.put("fieldinstruction", "(getfield|putstatic|getstatic|putfield)");
        map2.put("cpinstruction", "(ldc2_w|invokeinterface|invokedynamic|multianewarray|putstatic|instanceof|getstatic|checkcast|getfield|invokespecial|ldc_w|invokestatic|invokevirtual|putfield|ldc|new|anewarray)");
        map2.put("stackinstruction", "(dup2|swap|dup2_x2|pop|pop2|dup|dup2_x1|dup_x2|dup_x1)");
        map2.put("branchinstruction", "(ifle|if_acmpne|if_icmpeq|if_acmpeq|ifnonnull|goto_w|iflt|ifnull|if_icmpne|tableswitch|if_icmple|ifeq|if_icmplt|jsr_w|if_icmpgt|ifgt|jsr|goto|ifne|ifge|lookupswitch|if_icmpge)");
        map2.put("returninstruction", "(lreturn|ireturn|freturn|dreturn|areturn|return)");
        map2.put("storeinstruction", "(istore|fstore|dstore|astore|lstore)");
        map2.put(Constants.ATTRNAME_SELECT, "(tableswitch|lookupswitch)");
        map2.put("ifinstruction", "(ifeq|ifgt|if_icmpne|if_icmpeq|ifge|ifnull|ifne|if_icmple|if_icmpge|if_acmpeq|if_icmplt|if_acmpne|ifnonnull|iflt|if_icmpgt|ifle)");
        map2.put("jsrinstruction", "(jsr|jsr_w)");
        map2.put("variablelengthinstruction", "(tableswitch|jsr|goto|lookupswitch)");
        map2.put("unconditionalbranch", "(goto|jsr|jsr_w|athrow|goto_w)");
        map2.put("constantpushinstruction", "(dconst|bipush|sipush|fconst|iconst|lconst)");
        map2.put("typedinstruction", "(imul|lsub|aload|fload|lor|new|aaload|fcmpg|iand|iaload|lrem|idiv|d2l|isub|dcmpg|dastore|ret|f2d|f2i|drem|iinc|i2c|checkcast|frem|lreturn|astore|lushr|daload|dneg|fastore|istore|lshl|ldiv|lstore|areturn|ishr|ldc_w|invokeinterface|invokedynamic|aastore|lxor|ishl|l2d|i2f|return|faload|sipush|iushr|caload|instanceof|invokespecial|putfield|fmul|ireturn|laload|d2f|lneg|ixor|i2l|fdiv|lastore|multianewarray|i2b|getstatic|i2d|putstatic|fcmpl|saload|ladd|irem|dload|jsr_w|dconst|dcmpl|fsub|freturn|ldc|aconst_null|castore|lmul|ldc2_w|dadd|iconst|f2l|ddiv|dstore|land|jsr|anewarray|dmul|bipush|dsub|sastore|d2i|i2s|lshr|iadd|l2i|lload|bastore|fstore|fneg|iload|fadd|baload|fconst|ior|ineg|dreturn|l2f|lconst|getfield|invokevirtual|invokestatic|iastore)");
        map2.put("popinstruction", "(fstore|dstore|pop|pop2|astore|putstatic|istore|lstore)");
        map2.put("allocationinstruction", "(multianewarray|new|anewarray|newarray)");
        map2.put("indexedinstruction", "(lload|lstore|fload|ldc2_w|invokeinterface|invokedynamic|multianewarray|astore|dload|putstatic|instanceof|getstatic|checkcast|getfield|invokespecial|dstore|istore|iinc|ldc_w|ret|fstore|invokestatic|iload|putfield|invokevirtual|ldc|new|aload|anewarray)");
        map2.put("pushinstruction", "(dup|lload|dup2|bipush|fload|ldc2_w|sipush|lconst|fconst|dload|getstatic|ldc_w|aconst_null|dconst|iload|ldc|iconst|aload)");
        map2.put("stackproducer", "(imul|lsub|aload|fload|lor|new|aaload|fcmpg|iand|iaload|lrem|idiv|d2l|isub|dcmpg|dup|f2d|f2i|drem|i2c|checkcast|frem|lushr|daload|dneg|lshl|ldiv|ishr|ldc_w|invokeinterface|invokedynamic|lxor|ishl|l2d|i2f|faload|sipush|iushr|caload|instanceof|invokespecial|fmul|laload|d2f|lneg|ixor|i2l|fdiv|getstatic|i2b|swap|i2d|dup2|fcmpl|saload|ladd|irem|dload|jsr_w|dconst|dcmpl|fsub|ldc|arraylength|aconst_null|tableswitch|lmul|ldc2_w|iconst|dadd|f2l|ddiv|land|jsr|anewarray|dmul|bipush|dsub|d2i|newarray|i2s|lshr|iadd|lload|l2i|fneg|iload|fadd|baload|fconst|lookupswitch|ior|ineg|lconst|l2f|getfield|invokevirtual|invokestatic)");
        map2.put("stackconsumer", "(imul|lsub|lor|iflt|fcmpg|if_icmpgt|iand|ifeq|if_icmplt|lrem|ifnonnull|idiv|d2l|isub|dcmpg|dastore|if_icmpeq|f2d|f2i|drem|i2c|checkcast|frem|lreturn|astore|lushr|pop2|monitorexit|dneg|fastore|istore|lshl|ldiv|lstore|areturn|if_icmpge|ishr|monitorenter|invokeinterface|invokedynamic|aastore|lxor|ishl|l2d|i2f|return|iushr|instanceof|invokespecial|fmul|ireturn|d2f|lneg|ixor|pop|i2l|ifnull|fdiv|lastore|i2b|if_acmpeq|ifge|swap|i2d|putstatic|fcmpl|ladd|irem|dcmpl|fsub|freturn|ifgt|castore|lmul|dadd|f2l|ddiv|dstore|land|if_icmpne|if_acmpne|dmul|dsub|sastore|ifle|d2i|i2s|lshr|iadd|l2i|bastore|fstore|fneg|fadd|ior|ineg|ifne|dreturn|l2f|if_icmple|getfield|invokevirtual|invokestatic|iastore)");
        map2.put("exceptionthrower", "(irem|lrem|laload|putstatic|baload|dastore|areturn|getstatic|ldiv|anewarray|iastore|castore|idiv|saload|lastore|fastore|putfield|lreturn|caload|getfield|return|aastore|freturn|newarray|instanceof|multianewarray|athrow|faload|iaload|aaload|dreturn|monitorenter|checkcast|bastore|arraylength|new|invokevirtual|sastore|ldc_w|ireturn|invokespecial|monitorexit|invokeinterface|invokedynamic|ldc|invokestatic|daload)");
        map2.put("loadclass", "(multianewarray|invokeinterface|invokedynamic|instanceof|invokespecial|putfield|checkcast|putstatic|invokevirtual|new|getstatic|invokestatic|getfield|anewarray)");
        map2.put("instructiontargeter", "(ifle|if_acmpne|if_icmpeq|if_acmpeq|ifnonnull|goto_w|iflt|ifnull|if_icmpne|tableswitch|if_icmple|ifeq|if_icmplt|jsr_w|if_icmpgt|ifgt|jsr|goto|ifne|ifge|lookupswitch|if_icmpge)");
        map2.put("if_icmp", "(if_icmpne|if_icmpeq|if_icmple|if_icmpge|if_icmplt|if_icmpgt)");
        map2.put("if_acmp", "(if_acmpeq|if_acmpne)");
        map2.put("if", "(ifeq|ifne|iflt|ifge|ifgt|ifle)");
        map2.put("iconst", precompile((short) 3, (short) 8, (short) 2));
        map2.put("lconst", new String(new char[]{'(', makeChar((short) 9), '|', makeChar((short) 10), ')'}));
        map2.put("dconst", new String(new char[]{'(', makeChar((short) 14), '|', makeChar((short) 15), ')'}));
        map2.put("fconst", new String(new char[]{'(', makeChar((short) 11), '|', makeChar((short) 12), '|', makeChar((short) 13), ')'}));
        map2.put("lload", precompile((short) 30, (short) 33, (short) 22));
        map2.put("iload", precompile((short) 26, (short) 29, (short) 21));
        map2.put("dload", precompile((short) 38, (short) 41, (short) 24));
        map2.put("fload", precompile((short) 34, (short) 37, (short) 23));
        map2.put("aload", precompile((short) 42, (short) 45, (short) 25));
        map2.put("lstore", precompile((short) 63, (short) 66, (short) 55));
        map2.put("istore", precompile((short) 59, (short) 62, (short) 54));
        map2.put("dstore", precompile((short) 71, (short) 74, (short) 57));
        map2.put("fstore", precompile((short) 67, (short) 70, (short) 56));
        map2.put("astore", precompile((short) 75, (short) 78, (short) 58));
        map2.forEach(new BiConsumer() { // from class: er6
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                InstructionFinder.a((String) obj, (String) obj2);
            }
        });
        StringBuilder sb = new StringBuilder("(");
        for (short s = 0; s < 256; s = (short) (s + 1)) {
            if (Const.getNoOfOperands(s) != -1) {
                sb.append(makeChar(s));
                if (s < 255) {
                    sb.append('|');
                }
            }
        }
        sb.append(')');
        map.put("instruction", sb.toString());
    }

    public InstructionFinder(InstructionList instructionList) {
        this.il = instructionList;
        reread();
    }

    public static /* synthetic */ void a(String str, String str2) {
        if (str2.charAt(1) < 32767) {
            map.put(str, compilePattern(str2));
        }
    }

    private static String compilePattern(String str) {
        String lowerCase = str.toLowerCase(Locale.ENGLISH);
        StringBuilder sb = new StringBuilder();
        int length = str.length();
        int i = 0;
        while (i < length) {
            char cCharAt = lowerCase.charAt(i);
            if (Character.isLetterOrDigit(cCharAt)) {
                StringBuilder sb2 = new StringBuilder();
                while (true) {
                    if ((!Character.isLetterOrDigit(cCharAt) && cCharAt != '_') || i >= length) {
                        break;
                    }
                    sb2.append(cCharAt);
                    i++;
                    if (i >= length) {
                        break;
                    }
                    cCharAt = lowerCase.charAt(i);
                }
                i--;
                sb.append(mapName(sb2.toString()));
            } else if (!Character.isWhitespace(cCharAt)) {
                sb.append(cCharAt);
            }
            i++;
        }
        return sb.toString();
    }

    private InstructionHandle[] getMatch(int i, int i2) {
        return (InstructionHandle[]) Arrays.copyOfRange(this.handles, i, i2 + i);
    }

    private static char makeChar(short s) {
        return (char) (s + Short.MAX_VALUE);
    }

    private static String mapName(String str) {
        String str2 = map.get(str);
        if (str2 != null) {
            return str2;
        }
        for (short s = 0; s < 256; s = (short) (s + 1)) {
            if (str.equals(Const.getOpcodeName(s))) {
                return String.valueOf(makeChar(s));
            }
        }
        aca.a("Instruction unknown: ", str);
        return null;
    }

    private static String precompile(short s, short s2, short s3) {
        StringBuilder sb = new StringBuilder("(");
        while (s <= s2) {
            sb.append(makeChar(s));
            sb.append('|');
            s = (short) (s + 1);
        }
        sb.append(makeChar(s3));
        sb.append(")");
        return sb.toString();
    }

    public final InstructionList getInstructionList() {
        return this.il;
    }

    public final void reread() {
        int length = this.il.getLength();
        char[] cArr = new char[length];
        this.handles = this.il.getInstructionHandles();
        for (int i = 0; i < length; i++) {
            cArr[i] = makeChar(this.handles[i].getInstruction().getOpcode());
        }
        this.ilString = new String(cArr);
    }

    public final Iterator<InstructionHandle[]> search(String str, InstructionHandle instructionHandle, CodeConstraint codeConstraint) {
        String strCompilePattern = compilePattern(str);
        int i = 0;
        while (true) {
            InstructionHandle[] instructionHandleArr = this.handles;
            if (i >= instructionHandleArr.length) {
                i = -1;
                break;
            }
            if (instructionHandleArr[i] == instructionHandle) {
                break;
            }
            i++;
        }
        if (i == -1) {
            yz0.a("Instruction handle ", instructionHandle, " not found in instruction list.");
            return null;
        }
        Pattern patternCompile = Pattern.compile(strCompilePattern);
        ArrayList arrayList = new ArrayList();
        Matcher matcher = patternCompile.matcher(this.ilString);
        while (i < this.ilString.length() && matcher.find(i)) {
            int iStart = matcher.start();
            int iEnd = matcher.end();
            InstructionHandle[] match = getMatch(iStart, iEnd - iStart);
            if (codeConstraint == null || codeConstraint.checkCode(match)) {
                arrayList.add(match);
            }
            i = iEnd;
        }
        return arrayList.iterator();
    }

    public final Iterator<InstructionHandle[]> search(String str, CodeConstraint codeConstraint) {
        return search(str, this.il.getStart(), codeConstraint);
    }

    public final Iterator<InstructionHandle[]> search(String str, InstructionHandle instructionHandle) {
        return search(str, instructionHandle, null);
    }

    public final Iterator<InstructionHandle[]> search(String str) {
        return search(str, this.il.getStart(), null);
    }
}
