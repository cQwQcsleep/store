package com.reandroid.dex.ins;

import com.intellij.psi.PsiKeyword;
import com.reandroid.arsc.base.BlockCreator;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.dex.common.OperandType;
import com.reandroid.dex.common.RegisterFormat;
import com.reandroid.dex.id.CallSiteId;
import com.reandroid.dex.id.FieldId;
import com.reandroid.dex.id.IdItem;
import com.reandroid.dex.id.MethodId;
import com.reandroid.dex.id.StringId;
import com.reandroid.dex.id.TypeId;
import com.reandroid.dex.ins.Ins;
import com.reandroid.dex.sections.SectionType;
import com.reandroid.dex.smali.SmaliFormat;
import com.reandroid.dex.smali.SmaliReader;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.utils.StringsUtil;
import com.reandroid.utils.collection.ArrayIterator;
import com.reandroid.utils.collection.CombiningIterator;
import com.sun.jna.platform.win32.WinError;
import com.sun.org.apache.bcel.internal.Const;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Opcode<T extends Ins> implements BlockCreator<T>, SmaliFormat {
    public static final Opcode<Ins23x> ADD_DOUBLE;
    public static final Opcode<Ins12x> ADD_DOUBLE_2ADDR;
    public static final Opcode<Ins23x> ADD_FLOAT;
    public static final Opcode<Ins12x> ADD_FLOAT_2ADDR;
    public static final Opcode<Ins23x> ADD_INT;
    public static final Opcode<Ins12x> ADD_INT_2ADDR;
    public static final Opcode<Ins22s> ADD_INT_LIT16;
    public static final Opcode<Ins22b> ADD_INT_LIT8;
    public static final Opcode<Ins23x> ADD_LONG;
    public static final Opcode<Ins12x> ADD_LONG_2ADDR;
    public static final Opcode<Ins23x> AGET;
    public static final Opcode<Ins23x> AGET_BOOLEAN;
    public static final Opcode<Ins23x> AGET_BYTE;
    public static final Opcode<Ins23x> AGET_CHAR;
    public static final Opcode<Ins23x> AGET_OBJECT;
    public static final Opcode<Ins23x> AGET_SHORT;
    public static final Opcode<Ins23x> AGET_WIDE;
    public static final Opcode<Ins23x> AND_INT;
    public static final Opcode<Ins12x> AND_INT_2ADDR;
    public static final Opcode<Ins22s> AND_INT_LIT16;
    public static final Opcode<Ins22b> AND_INT_LIT8;
    public static final Opcode<Ins23x> AND_LONG;
    public static final Opcode<Ins12x> AND_LONG_2ADDR;
    public static final Opcode<Ins23x> APUT;
    public static final Opcode<Ins23x> APUT_BOOLEAN;
    public static final Opcode<Ins23x> APUT_BYTE;
    public static final Opcode<Ins23x> APUT_CHAR;
    public static final Opcode<Ins23x> APUT_OBJECT;
    public static final Opcode<Ins23x> APUT_SHORT;
    public static final Opcode<Ins23x> APUT_WIDE;
    public static final Opcode<Ins12x> ARRAY_LENGTH;
    public static final Opcode<InsArrayData> ARRAY_PAYLOAD;
    public static final Opcode<Ins21c> CHECK_CAST;
    public static final Opcode<Ins23x> CMPG_DOUBLE;
    public static final Opcode<Ins23x> CMPG_FLOAT;
    public static final Opcode<Ins23x> CMPL_DOUBLE;
    public static final Opcode<Ins23x> CMPL_FLOAT;
    public static final Opcode<Ins23x> CMP_LONG;
    public static final Opcode<InsConst> CONST;
    public static final Opcode<InsConst16> CONST_16;
    public static final Opcode<InsConst4> CONST_4;
    public static final Opcode<Ins21c> CONST_CLASS;
    public static final Opcode<InsConst16High> CONST_HIGH16;
    public static final Opcode<Ins21c> CONST_METHOD_HANDLE;
    public static final Opcode<Ins21c> CONST_METHOD_TYPE;
    public static final Opcode<InsConstString> CONST_STRING;
    public static final Opcode<InsConstStringJumbo> CONST_STRING_JUMBO;
    public static final Opcode<InsConstWide> CONST_WIDE;
    public static final Opcode<InsConstWide16> CONST_WIDE_16;
    public static final Opcode<InsConstWide32> CONST_WIDE_32;
    public static final Opcode<InsConstWideHigh16> CONST_WIDE_HIGH16;
    public static final Opcode<Ins23x> DIV_DOUBLE;
    public static final Opcode<Ins12x> DIV_DOUBLE_2ADDR;
    public static final Opcode<Ins23x> DIV_FLOAT;
    public static final Opcode<Ins12x> DIV_FLOAT_2ADDR;
    public static final Opcode<Ins23x> DIV_INT;
    public static final Opcode<Ins12x> DIV_INT_2ADDR;
    public static final Opcode<Ins22s> DIV_INT_LIT16;
    public static final Opcode<Ins22b> DIV_INT_LIT8;
    public static final Opcode<Ins23x> DIV_LONG;
    public static final Opcode<Ins12x> DIV_LONG_2ADDR;
    public static final Opcode<Ins12x> DOUBLE_TO_FLOAT;
    public static final Opcode<Ins12x> DOUBLE_TO_INT;
    public static final Opcode<Ins12x> DOUBLE_TO_LONG;
    public static final Opcode<Ins35mi> EXECUTE_INLINE;
    public static final Opcode<Ins3rmi> EXECUTE_INLINE_RANGE;
    public static final Opcode<Ins35c> FILLED_NEW_ARRAY;
    public static final Opcode<Ins3rc> FILLED_NEW_ARRAY_RANGE;
    public static final Opcode<InsFillArrayData> FILL_ARRAY_DATA;
    public static final Opcode<Ins12x> FLOAT_TO_DOUBLE;
    public static final Opcode<Ins12x> FLOAT_TO_INT;
    public static final Opcode<Ins12x> FLOAT_TO_LONG;
    public static final Opcode<InsGoto> GOTO;
    public static final Opcode<InsGoto> GOTO_16;
    public static final Opcode<InsGoto> GOTO_32;
    public static final Opcode<Ins22t> IF_EQ;
    public static final Opcode<Ins21t> IF_EQZ;
    public static final Opcode<Ins22t> IF_GE;
    public static final Opcode<Ins21t> IF_GEZ;
    public static final Opcode<Ins22t> IF_GT;
    public static final Opcode<Ins21t> IF_GTZ;
    public static final Opcode<Ins22t> IF_LE;
    public static final Opcode<Ins21t> IF_LEZ;
    public static final Opcode<Ins22t> IF_LT;
    public static final Opcode<Ins21t> IF_LTZ;
    public static final Opcode<Ins22t> IF_NE;
    public static final Opcode<Ins21t> IF_NEZ;
    public static final Opcode<Ins22c> IGET;
    public static final Opcode<Ins22c> IGET_BOOLEAN;
    public static final Opcode<Ins22cs> IGET_BOOLEAN_QUICK;
    public static final Opcode<Ins22c> IGET_BYTE;
    public static final Opcode<Ins22cs> IGET_BYTE_QUICK;
    public static final Opcode<Ins22c> IGET_CHAR;
    public static final Opcode<Ins22cs> IGET_CHAR_QUICK;
    public static final Opcode<Ins22c> IGET_OBJECT;
    public static final Opcode<Ins22cs> IGET_OBJECT_QUICK;
    public static final Opcode<Ins22c> IGET_OBJECT_VOLATILE;
    public static final Opcode<Ins22cs> IGET_QUICK;
    public static final Opcode<Ins22c> IGET_SHORT;
    public static final Opcode<Ins22cs> IGET_SHORT_QUICK;
    public static final Opcode<Ins22c> IGET_VOLATILE;
    public static final Opcode<Ins22c> IGET_WIDE;
    public static final Opcode<Ins22cs> IGET_WIDE_QUICK;
    public static final Opcode<Ins22c> IGET_WIDE_VOLATILE;
    public static final Opcode<Ins22c> INSTANCE_OF;
    public static final Opcode<Ins12x> INT_TO_BYTE;
    public static final Opcode<Ins12x> INT_TO_CHAR;
    public static final Opcode<Ins12x> INT_TO_DOUBLE;
    public static final Opcode<Ins12x> INT_TO_FLOAT;
    public static final Opcode<Ins12x> INT_TO_LONG;
    public static final Opcode<Ins12x> INT_TO_SHORT;
    public static final Opcode<Ins35c> INVOKE_CUSTOM;
    public static final Opcode<Ins3rc> INVOKE_CUSTOM_RANGE;
    public static final Opcode<Ins35c> INVOKE_DIRECT;
    public static final Opcode<Ins35c> INVOKE_DIRECT_EMPTY;
    public static final Opcode<Ins3rc> INVOKE_DIRECT_RANGE;
    public static final Opcode<Ins35c> INVOKE_INTERFACE;
    public static final Opcode<Ins3rc> INVOKE_INTERFACE_RANGE;
    public static final Opcode<Ins3rc> INVOKE_OBJECT_INIT_RANGE;
    public static final Opcode<Ins45cc> INVOKE_POLYMORPHIC;
    public static final Opcode<Ins4rcc> INVOKE_POLYMORPHIC_RANGE;
    public static final Opcode<Ins35c> INVOKE_STATIC;
    public static final Opcode<Ins3rc> INVOKE_STATIC_RANGE;
    public static final Opcode<Ins35c> INVOKE_SUPER;
    public static final Opcode<Ins35ms> INVOKE_SUPER_QUICK;
    public static final Opcode<Ins3rms> INVOKE_SUPER_QUICK_RANGE;
    public static final Opcode<Ins3rc> INVOKE_SUPER_RANGE;
    public static final Opcode<Ins35c> INVOKE_VIRTUAL;
    public static final Opcode<Ins35ms> INVOKE_VIRTUAL_QUICK;
    public static final Opcode<Ins3rms> INVOKE_VIRTUAL_QUICK_RANGE;
    public static final Opcode<Ins3rc> INVOKE_VIRTUAL_RANGE;
    public static final Opcode<Ins22c> IPUT;
    public static final Opcode<Ins22c> IPUT_BOOLEAN;
    public static final Opcode<Ins22cs> IPUT_BOOLEAN_QUICK;
    public static final Opcode<Ins22c> IPUT_BYTE;
    public static final Opcode<Ins22cs> IPUT_BYTE_QUICK;
    public static final Opcode<Ins22c> IPUT_CHAR;
    public static final Opcode<Ins22cs> IPUT_CHAR_QUICK;
    public static final Opcode<Ins22c> IPUT_OBJECT;
    public static final Opcode<Ins22cs> IPUT_OBJECT_QUICK;
    public static final Opcode<Ins22c> IPUT_OBJECT_VOLATILE;
    public static final Opcode<Ins22cs> IPUT_QUICK;
    public static final Opcode<Ins22c> IPUT_SHORT;
    public static final Opcode<Ins22cs> IPUT_SHORT_QUICK;
    public static final Opcode<Ins22c> IPUT_VOLATILE;
    public static final Opcode<Ins22c> IPUT_WIDE;
    public static final Opcode<Ins22cs> IPUT_WIDE_QUICK;
    public static final Opcode<Ins22c> IPUT_WIDE_VOLATILE;
    public static final Opcode<Ins12x> LONG_TO_DOUBLE;
    public static final Opcode<Ins12x> LONG_TO_FLOAT;
    public static final Opcode<Ins12x> LONG_TO_INT;
    public static final Opcode<Ins11x> MONITOR_ENTER;
    public static final Opcode<Ins11x> MONITOR_EXIT;
    public static final Opcode<Ins12x> MOVE;
    public static final Opcode<Ins32x> MOVE_16;
    public static final Opcode<Ins11x> MOVE_EXCEPTION;
    public static final Opcode<Ins22x> MOVE_FROM16;
    public static final Opcode<Ins12x> MOVE_OBJECT;
    public static final Opcode<Ins32x> MOVE_OBJECT_16;
    public static final Opcode<Ins22x> MOVE_OBJECT_FROM16;
    public static final Opcode<Ins11x> MOVE_RESULT;
    public static final Opcode<Ins11x> MOVE_RESULT_OBJECT;
    public static final Opcode<Ins11x> MOVE_RESULT_WIDE;
    public static final Opcode<Ins12x> MOVE_WIDE;
    public static final Opcode<Ins32x> MOVE_WIDE_16;
    public static final Opcode<Ins22x> MOVE_WIDE_FROM16;
    public static final Opcode<Ins23x> MUL_DOUBLE;
    public static final Opcode<Ins12x> MUL_DOUBLE_2ADDR;
    public static final Opcode<Ins23x> MUL_FLOAT;
    public static final Opcode<Ins12x> MUL_FLOAT_2ADDR;
    public static final Opcode<Ins23x> MUL_INT;
    public static final Opcode<Ins12x> MUL_INT_2ADDR;
    public static final Opcode<Ins22s> MUL_INT_LIT16;
    public static final Opcode<Ins22b> MUL_INT_LIT8;
    public static final Opcode<Ins23x> MUL_LONG;
    public static final Opcode<Ins12x> MUL_LONG_2ADDR;
    private static final Map<String, Opcode<?>> NAME_MAP;
    public static final Opcode<Ins12x> NEG_DOUBLE;
    public static final Opcode<Ins12x> NEG_FLOAT;
    public static final Opcode<Ins12x> NEG_INT;
    public static final Opcode<Ins12x> NEG_LONG;
    public static final Opcode<Ins22c> NEW_ARRAY;
    public static final Opcode<Ins21c> NEW_INSTANCE;
    public static final Opcode<InsNop> NOP;
    public static final Opcode<Ins12x> NOT_INT;
    public static final Opcode<Ins12x> NOT_LONG;
    public static final Opcode<Ins23x> OR_INT;
    public static final Opcode<Ins12x> OR_INT_2ADDR;
    public static final Opcode<Ins22s> OR_INT_LIT16;
    public static final Opcode<Ins22b> OR_INT_LIT8;
    public static final Opcode<Ins23x> OR_LONG;
    public static final Opcode<Ins12x> OR_LONG_2ADDR;
    public static final Opcode<InsPackedSwitch> PACKED_SWITCH;
    public static final Opcode<InsPackedSwitchData> PACKED_SWITCH_PAYLOAD;
    private static final Opcode<?>[] PAYLOADS;
    public static final Opcode<Ins23x> REM_DOUBLE;
    public static final Opcode<Ins12x> REM_DOUBLE_2ADDR;
    public static final Opcode<Ins23x> REM_FLOAT;
    public static final Opcode<Ins12x> REM_FLOAT_2ADDR;
    public static final Opcode<Ins23x> REM_INT;
    public static final Opcode<Ins12x> REM_INT_2ADDR;
    public static final Opcode<Ins22s> REM_INT_LIT16;
    public static final Opcode<Ins22b> REM_INT_LIT8;
    public static final Opcode<Ins23x> REM_LONG;
    public static final Opcode<Ins12x> REM_LONG_2ADDR;
    public static final Opcode<Ins11x> RETURN;
    public static final Opcode<Ins11x> RETURN_OBJECT;
    public static final Opcode<Ins10x> RETURN_VOID;
    public static final Opcode<Ins10x> RETURN_VOID_BARRIER;
    public static final Opcode<Ins10x> RETURN_VOID_NO_BARRIER;
    public static final Opcode<Ins11x> RETURN_WIDE;
    public static final Opcode<Ins22s> RSUB_INT;
    public static final Opcode<Ins22b> RSUB_INT_LIT8;
    public static final Opcode<Ins21c> SGET;
    public static final Opcode<Ins21c> SGET_BOOLEAN;
    public static final Opcode<Ins21c> SGET_BYTE;
    public static final Opcode<Ins21c> SGET_CHAR;
    public static final Opcode<Ins21c> SGET_OBJECT;
    public static final Opcode<Ins21c> SGET_OBJECT_VOLATILE;
    public static final Opcode<Ins21c> SGET_SHORT;
    public static final Opcode<Ins21c> SGET_VOLATILE;
    public static final Opcode<Ins21c> SGET_WIDE;
    public static final Opcode<Ins21c> SGET_WIDE_VOLATILE;
    public static final Opcode<Ins23x> SHL_INT;
    public static final Opcode<Ins12x> SHL_INT_2ADDR;
    public static final Opcode<Ins22b> SHL_INT_LIT8;
    public static final Opcode<Ins23x> SHL_LONG;
    public static final Opcode<Ins12x> SHL_LONG_2ADDR;
    public static final Opcode<Ins23x> SHR_INT;
    public static final Opcode<Ins12x> SHR_INT_2ADDR;
    public static final Opcode<Ins22b> SHR_INT_LIT8;
    public static final Opcode<Ins23x> SHR_LONG;
    public static final Opcode<Ins12x> SHR_LONG_2ADDR;
    public static final Opcode<InsSparseSwitch> SPARSE_SWITCH;
    public static final Opcode<InsSparseSwitchData> SPARSE_SWITCH_PAYLOAD;
    public static final Opcode<Ins21c> SPUT;
    public static final Opcode<Ins21c> SPUT_BOOLEAN;
    public static final Opcode<Ins21c> SPUT_BYTE;
    public static final Opcode<Ins21c> SPUT_CHAR;
    public static final Opcode<Ins21c> SPUT_OBJECT;
    public static final Opcode<Ins21c> SPUT_OBJECT_VOLATILE;
    public static final Opcode<Ins21c> SPUT_SHORT;
    public static final Opcode<Ins21c> SPUT_VOLATILE;
    public static final Opcode<Ins21c> SPUT_WIDE;
    public static final Opcode<Ins21c> SPUT_WIDE_VOLATILE;
    public static final Opcode<Ins23x> SUB_DOUBLE;
    public static final Opcode<Ins12x> SUB_DOUBLE_2ADDR;
    public static final Opcode<Ins23x> SUB_FLOAT;
    public static final Opcode<Ins12x> SUB_FLOAT_2ADDR;
    public static final Opcode<Ins23x> SUB_INT;
    public static final Opcode<Ins12x> SUB_INT_2ADDR;
    public static final Opcode<Ins23x> SUB_LONG;
    public static final Opcode<Ins12x> SUB_LONG_2ADDR;
    public static final Opcode<Ins11x> THROW;
    public static final Opcode<Ins20bc> THROW_VERIFICATION_ERROR;
    public static final Opcode<Ins23x> USHR_INT;
    public static final Opcode<Ins12x> USHR_INT_2ADDR;
    public static final Opcode<Ins22b> USHR_INT_LIT8;
    public static final Opcode<Ins23x> USHR_LONG;
    public static final Opcode<Ins12x> USHR_LONG_2ADDR;
    private static final Opcode<?>[] VALUES;
    private static final Opcode<?>[] VALUES_2;
    private static final Opcode<?>[] VALUES_3;
    public static final Opcode<Ins23x> XOR_INT;
    public static final Opcode<Ins12x> XOR_INT_2ADDR;
    public static final Opcode<Ins22s> XOR_INT_LIT16;
    public static final Opcode<Ins22b> XOR_INT_LIT8;
    public static final Opcode<Ins23x> XOR_LONG;
    public static final Opcode<Ins12x> XOR_LONG_2ADDR;
    private final InsCreator<T> creator;
    private final String name;
    private final SectionType<? extends IdItem> sectionType;
    private final int size;
    private final int value;

    public static class Ins10xCreator extends InsCreator<Ins10x> {
        public Ins10xCreator(int i) {
            super(i);
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public OperandType getOperandType() {
            return OperandType.NONE;
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public RegisterFormat getRegisterFormat() {
            return RegisterFormat.NONE;
        }

        public Ins10x newInstance() {
            return new Ins10x(getOpcode());
        }
    }

    public static class Ins11xCreator extends InsCreator<Ins11x> {
        public Ins11xCreator(int i) {
            super(i);
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public OperandType getOperandType() {
            return OperandType.NONE;
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public RegisterFormat getRegisterFormat() {
            return RegisterFormat.READ;
        }

        public Ins11x newInstance() {
            return new Ins11x(getOpcode());
        }
    }

    public static class Ins11xWriteCreator extends Ins11xCreator {
        public Ins11xWriteCreator(int i) {
            super(i);
        }

        @Override // com.reandroid.dex.ins.Opcode.Ins11xCreator, com.reandroid.dex.ins.Opcode.InsCreator
        public RegisterFormat getRegisterFormat() {
            return RegisterFormat.WRITE;
        }
    }

    public static class Ins12xCreator extends InsCreator<Ins12x> {
        public Ins12xCreator(int i) {
            super(i);
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public OperandType getOperandType() {
            return OperandType.NONE;
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public RegisterFormat getRegisterFormat() {
            return RegisterFormat.WRITE_READ;
        }

        public Ins12x newInstance() {
            return new Ins12x(getOpcode());
        }
    }

    public static class Ins20bcCreator extends InsCreator<Ins20bc> {
        public Ins20bcCreator(int i) {
            super(i);
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public OperandType getOperandType() {
            return OperandType.NONE;
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public RegisterFormat getRegisterFormat() {
            return RegisterFormat.NONE;
        }

        public Ins20bc newInstance() {
            return new Ins20bc(getOpcode());
        }
    }

    public static class Ins21cCreator extends InsCreator<Ins21c> {
        public Ins21cCreator(int i) {
            super(i);
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public OperandType getOperandType() {
            return OperandType.KEY;
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public RegisterFormat getRegisterFormat() {
            return RegisterFormat.WRITE;
        }

        public Ins21c newInstance() {
            return new Ins21c(getOpcode());
        }
    }

    public static class Ins21cPutCreator extends Ins21cCreator {
        public Ins21cPutCreator(int i) {
            super(i);
        }

        @Override // com.reandroid.dex.ins.Opcode.Ins21cCreator, com.reandroid.dex.ins.Opcode.InsCreator
        public RegisterFormat getRegisterFormat() {
            return RegisterFormat.READ;
        }
    }

    public static class Ins21sCreator extends InsCreator<Ins21s> {
        public Ins21sCreator(int i) {
            super(i);
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public OperandType getOperandType() {
            return OperandType.HEX;
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public RegisterFormat getRegisterFormat() {
            return RegisterFormat.WRITE;
        }

        public Ins21s newInstance() {
            return new Ins21s(getOpcode());
        }
    }

    public static class Ins21tCreator extends InsCreator<Ins21t> {
        public Ins21tCreator(int i) {
            super(i);
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public OperandType getOperandType() {
            return OperandType.LABEL;
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public RegisterFormat getRegisterFormat() {
            return RegisterFormat.READ;
        }

        public Ins21t newInstance() {
            return new Ins21t(getOpcode());
        }
    }

    public static class Ins22bCreator extends InsCreator<Ins22b> {
        public Ins22bCreator(int i) {
            super(i);
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public OperandType getOperandType() {
            return OperandType.HEX;
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public RegisterFormat getRegisterFormat() {
            return RegisterFormat.WRITE_READ;
        }

        public Ins22b newInstance() {
            return new Ins22b(getOpcode());
        }
    }

    public static class Ins22cCreator extends InsCreator<Ins22c> {
        public Ins22cCreator(int i) {
            super(i);
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public OperandType getOperandType() {
            return OperandType.KEY;
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public RegisterFormat getRegisterFormat() {
            return RegisterFormat.WRITE_READ;
        }

        public Ins22c newInstance() {
            return new Ins22c(getOpcode());
        }
    }

    public static class Ins22cPutCreator extends Ins22cCreator {
        public Ins22cPutCreator(int i) {
            super(i);
        }

        @Override // com.reandroid.dex.ins.Opcode.Ins22cCreator, com.reandroid.dex.ins.Opcode.InsCreator
        public OperandType getOperandType() {
            return OperandType.KEY;
        }

        @Override // com.reandroid.dex.ins.Opcode.Ins22cCreator, com.reandroid.dex.ins.Opcode.InsCreator
        public RegisterFormat getRegisterFormat() {
            return RegisterFormat.READ_READ;
        }
    }

    public static class Ins22csCreator extends InsCreator<Ins22cs> {
        public Ins22csCreator(int i) {
            super(i);
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public OperandType getOperandType() {
            return OperandType.KEY;
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public RegisterFormat getRegisterFormat() {
            return RegisterFormat.WRITE_READ;
        }

        public Ins22cs newInstance() {
            return new Ins22cs(getOpcode());
        }
    }

    public static class Ins22csPutCreator extends Ins22csCreator {
        public Ins22csPutCreator(int i) {
            super(i);
        }

        @Override // com.reandroid.dex.ins.Opcode.Ins22csCreator, com.reandroid.dex.ins.Opcode.InsCreator
        public RegisterFormat getRegisterFormat() {
            return RegisterFormat.READ_READ;
        }
    }

    public static class Ins22sCreator extends InsCreator<Ins22s> {
        public Ins22sCreator(int i) {
            super(i);
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public OperandType getOperandType() {
            return OperandType.HEX;
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public RegisterFormat getRegisterFormat() {
            return RegisterFormat.WRITE_READ;
        }

        public Ins22s newInstance() {
            return new Ins22s(getOpcode());
        }
    }

    public static class Ins22tCreator extends InsCreator<Ins22t> {
        public Ins22tCreator(int i) {
            super(i);
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public OperandType getOperandType() {
            return OperandType.LABEL;
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public RegisterFormat getRegisterFormat() {
            return RegisterFormat.WRITE_READ;
        }

        public Ins22t newInstance() {
            return new Ins22t(getOpcode());
        }
    }

    public static class Ins22xCreator extends InsCreator<Ins22x> {
        public Ins22xCreator(int i) {
            super(i);
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public OperandType getOperandType() {
            return OperandType.NONE;
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public RegisterFormat getRegisterFormat() {
            return RegisterFormat.WRITE_READ;
        }

        public Ins22x newInstance() {
            return new Ins22x(getOpcode());
        }
    }

    public static class Ins23xAputCreator extends Ins23xCreator {
        public Ins23xAputCreator(int i) {
            super(i);
        }

        @Override // com.reandroid.dex.ins.Opcode.Ins23xCreator, com.reandroid.dex.ins.Opcode.InsCreator
        public RegisterFormat getRegisterFormat() {
            return RegisterFormat.READ_READ_READ;
        }
    }

    public static class Ins23xCreator extends InsCreator<Ins23x> {
        public Ins23xCreator(int i) {
            super(i);
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public OperandType getOperandType() {
            return OperandType.NONE;
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public RegisterFormat getRegisterFormat() {
            return RegisterFormat.WRITE_READ_READ;
        }

        public Ins23x newInstance() {
            return new Ins23x(getOpcode());
        }
    }

    public static class Ins31iCreator extends InsCreator<Ins31i> {
        public Ins31iCreator(int i) {
            super(i);
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public OperandType getOperandType() {
            return OperandType.HEX;
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public RegisterFormat getRegisterFormat() {
            return RegisterFormat.WRITE;
        }

        public Ins31i newInstance() {
            return new Ins31i(getOpcode());
        }
    }

    public static class Ins32xCreator extends InsCreator<Ins32x> {
        public Ins32xCreator(int i) {
            super(i);
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public OperandType getOperandType() {
            return OperandType.NONE;
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public RegisterFormat getRegisterFormat() {
            return RegisterFormat.WRITE_READ;
        }

        public Ins32x newInstance() {
            return new Ins32x(getOpcode());
        }
    }

    public static class Ins35cCreator extends InsCreator<Ins35c> {
        public Ins35cCreator(int i) {
            super(i);
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public OperandType getOperandType() {
            return OperandType.KEY;
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public RegisterFormat getRegisterFormat() {
            return RegisterFormat.OUT;
        }

        public Ins35c newInstance() {
            return new Ins35c(getOpcode());
        }
    }

    public static class Ins35miCreator extends InsCreator<Ins35mi> {
        public Ins35miCreator(int i) {
            super(i);
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public OperandType getOperandType() {
            return OperandType.NONE;
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public RegisterFormat getRegisterFormat() {
            return RegisterFormat.WRITE_READ;
        }

        public Ins35mi newInstance() {
            return new Ins35mi(getOpcode());
        }
    }

    public static class Ins35msCreator extends InsCreator<Ins35ms> {
        public Ins35msCreator(int i) {
            super(i);
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public OperandType getOperandType() {
            return OperandType.KEY;
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public RegisterFormat getRegisterFormat() {
            return RegisterFormat.OUT;
        }

        public Ins35ms newInstance() {
            return new Ins35ms(getOpcode());
        }
    }

    public static class Ins3rcCreator extends InsCreator<Ins3rc> {
        public Ins3rcCreator(int i) {
            super(i);
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public OperandType getOperandType() {
            return OperandType.KEY;
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public RegisterFormat getRegisterFormat() {
            return RegisterFormat.OUT_RANGE;
        }

        public Ins3rc newInstance() {
            return new Ins3rc(getOpcode());
        }
    }

    public static class Ins3rmiCreator extends InsCreator<Ins3rmi> {
        public Ins3rmiCreator(int i) {
            super(i);
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public OperandType getOperandType() {
            return OperandType.NONE;
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public RegisterFormat getRegisterFormat() {
            return RegisterFormat.WRITE_READ;
        }

        public Ins3rmi newInstance() {
            return new Ins3rmi(getOpcode());
        }
    }

    public static class Ins3rmsCreator extends InsCreator<Ins3rms> {
        public Ins3rmsCreator(int i) {
            super(i);
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public OperandType getOperandType() {
            return OperandType.KEY;
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public RegisterFormat getRegisterFormat() {
            return RegisterFormat.OUT_RANGE;
        }

        public Ins3rms newInstance() {
            return new Ins3rms(getOpcode());
        }
    }

    public static class Ins45ccCreator extends InsCreator<Ins45cc> {
        public Ins45ccCreator(int i) {
            super(i);
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public OperandType getOperandType() {
            return OperandType.DUAL_KEY;
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public RegisterFormat getRegisterFormat() {
            return RegisterFormat.OUT;
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public SectionType<? extends IdItem> getSectionType2() {
            return SectionType.PROTO_ID;
        }

        public Ins45cc newInstance() {
            return new Ins45cc(getOpcode());
        }
    }

    public static class Ins4rccCreator extends InsCreator<Ins4rcc> {
        public Ins4rccCreator(int i) {
            super(i);
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public OperandType getOperandType() {
            return OperandType.DUAL_KEY;
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public RegisterFormat getRegisterFormat() {
            return RegisterFormat.OUT_RANGE;
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public SectionType<? extends IdItem> getSectionType2() {
            return SectionType.PROTO_ID;
        }

        public Ins4rcc newInstance() {
            return new Ins4rcc(getOpcode());
        }
    }

    public static class InsArrayDataCreator extends InsCreator<InsArrayData> {
        public InsArrayDataCreator(int i) {
            super(i);
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public OperandType getOperandType() {
            return OperandType.DECIMAL;
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public RegisterFormat getRegisterFormat() {
            return RegisterFormat.NONE;
        }

        public InsArrayData newInstance() {
            return new InsArrayData();
        }
    }

    public static class InsConst16Creator extends InsCreator<InsConst16> {
        public InsConst16Creator(int i) {
            super(i);
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public Opcode<InsConst16> getOpcode() {
            return Opcode.CONST_16;
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public OperandType getOperandType() {
            return OperandType.HEX;
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public RegisterFormat getRegisterFormat() {
            return RegisterFormat.WRITE;
        }

        public InsConst16 newInstance() {
            return new InsConst16();
        }
    }

    public static class InsConst16HighCreator extends InsCreator<InsConst16High> {
        public InsConst16HighCreator(int i) {
            super(i);
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public OperandType getOperandType() {
            return OperandType.HEX;
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public RegisterFormat getRegisterFormat() {
            return RegisterFormat.WRITE;
        }

        public InsConst16High newInstance() {
            return new InsConst16High();
        }
    }

    public static class InsConst4Creator extends InsCreator<InsConst4> {
        public InsConst4Creator(int i) {
            super(i);
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public OperandType getOperandType() {
            return OperandType.HEX;
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public RegisterFormat getRegisterFormat() {
            return RegisterFormat.WRITE;
        }

        public InsConst4 newInstance() {
            return new InsConst4();
        }
    }

    public static class InsConstCreator extends InsCreator<InsConst> {
        public InsConstCreator(int i) {
            super(i);
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public OperandType getOperandType() {
            return OperandType.HEX;
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public RegisterFormat getRegisterFormat() {
            return RegisterFormat.WRITE;
        }

        public InsConst newInstance() {
            return new InsConst();
        }
    }

    public static class InsConstStringCreator extends InsCreator<InsConstString> {
        public InsConstStringCreator(int i) {
            super(i);
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public OperandType getOperandType() {
            return OperandType.KEY;
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public RegisterFormat getRegisterFormat() {
            return RegisterFormat.WRITE;
        }

        public InsConstString newInstance() {
            return new InsConstString();
        }
    }

    public static class InsConstStringJumboCreator extends InsCreator<InsConstStringJumbo> {
        public InsConstStringJumboCreator(int i) {
            super(i);
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public OperandType getOperandType() {
            return OperandType.KEY;
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public RegisterFormat getRegisterFormat() {
            return RegisterFormat.WRITE;
        }

        public InsConstStringJumbo newInstance() {
            return new InsConstStringJumbo();
        }
    }

    public static class InsConstWide16Creator extends InsCreator<InsConstWide16> {
        public InsConstWide16Creator(int i) {
            super(i);
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public Opcode<?> getOpcode() {
            return Opcode.CONST_WIDE_16;
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public OperandType getOperandType() {
            return OperandType.HEX;
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public RegisterFormat getRegisterFormat() {
            return RegisterFormat.WRITE;
        }

        public InsConstWide16 newInstance() {
            return new InsConstWide16();
        }
    }

    public static class InsConstWide32Creator extends InsCreator<InsConstWide32> {
        public InsConstWide32Creator(int i) {
            super(i);
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public Opcode<InsConstWide32> getOpcode() {
            return Opcode.CONST_WIDE_32;
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public OperandType getOperandType() {
            return OperandType.HEX;
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public RegisterFormat getRegisterFormat() {
            return RegisterFormat.WRITE;
        }

        public InsConstWide32 newInstance() {
            return new InsConstWide32();
        }
    }

    public static class InsConstWideCreator extends InsCreator<InsConstWide> {
        public InsConstWideCreator(int i) {
            super(i);
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public OperandType getOperandType() {
            return OperandType.HEX;
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public RegisterFormat getRegisterFormat() {
            return RegisterFormat.WRITE;
        }

        public InsConstWide newInstance() {
            return new InsConstWide();
        }
    }

    public static class InsConstWideHigh16Creator extends InsCreator<InsConstWideHigh16> {
        public InsConstWideHigh16Creator(int i) {
            super(i);
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public Opcode<InsConstWideHigh16> getOpcode() {
            return Opcode.CONST_WIDE_HIGH16;
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public OperandType getOperandType() {
            return OperandType.HEX;
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public RegisterFormat getRegisterFormat() {
            return RegisterFormat.WRITE;
        }

        public InsConstWideHigh16 newInstance() {
            return new InsConstWideHigh16();
        }
    }

    public static abstract class InsCreator<T extends Ins> implements BlockCreator<T> {
        private Opcode<?> opcode;
        private final int opcodeValue;

        public InsCreator(int i) {
            this.opcodeValue = i;
        }

        public Opcode<?> getOpcode() {
            if (this.opcode == null) {
                this.opcode = Opcode.valueOf(this.opcodeValue);
            }
            return this.opcode;
        }

        public abstract OperandType getOperandType();

        public abstract RegisterFormat getRegisterFormat();

        public SectionType<? extends IdItem> getSectionType2() {
            return null;
        }
    }

    public static class InsFillArrayDataCreator extends InsCreator<InsFillArrayData> {
        public InsFillArrayDataCreator(int i) {
            super(i);
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public OperandType getOperandType() {
            return OperandType.LABEL;
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public RegisterFormat getRegisterFormat() {
            return RegisterFormat.READ;
        }

        public InsFillArrayData newInstance() {
            return new InsFillArrayData();
        }
    }

    public static class InsGotoCreator extends InsCreator<InsGoto> {
        public InsGotoCreator(int i) {
            super(i);
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public OperandType getOperandType() {
            return OperandType.LABEL;
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public RegisterFormat getRegisterFormat() {
            return RegisterFormat.NONE;
        }

        public InsGoto newInstance() {
            return new InsGoto(getOpcode());
        }
    }

    public static class InsNopCreator extends InsCreator<InsNop> {
        public InsNopCreator(int i) {
            super(i);
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public OperandType getOperandType() {
            return OperandType.NONE;
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public RegisterFormat getRegisterFormat() {
            return RegisterFormat.NONE;
        }

        public InsNop newInstance() {
            return new InsNop();
        }
    }

    public static class InsPackedSwitchCreator extends InsCreator<InsPackedSwitch> {
        public InsPackedSwitchCreator(int i) {
            super(i);
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public OperandType getOperandType() {
            return OperandType.LABEL;
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public RegisterFormat getRegisterFormat() {
            return RegisterFormat.READ;
        }

        public InsPackedSwitch newInstance() {
            return new InsPackedSwitch();
        }
    }

    public static class InsPackedSwitchDataCreator extends InsCreator<InsPackedSwitchData> {
        public InsPackedSwitchDataCreator(int i) {
            super(i);
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public OperandType getOperandType() {
            return OperandType.HEX;
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public RegisterFormat getRegisterFormat() {
            return RegisterFormat.NONE;
        }

        public InsPackedSwitchData newInstance() {
            return new InsPackedSwitchData();
        }
    }

    public static class InsSparseSwitchCreator extends InsCreator<InsSparseSwitch> {
        public InsSparseSwitchCreator(int i) {
            super(i);
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public OperandType getOperandType() {
            return OperandType.LABEL;
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public RegisterFormat getRegisterFormat() {
            return RegisterFormat.READ;
        }

        public InsSparseSwitch newInstance() {
            return new InsSparseSwitch();
        }
    }

    public static class InsSparseSwitchDataCreator extends InsCreator<InsSparseSwitchData> {
        public InsSparseSwitchDataCreator(int i) {
            super(i);
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public OperandType getOperandType() {
            return OperandType.NONE;
        }

        @Override // com.reandroid.dex.ins.Opcode.InsCreator
        public RegisterFormat getRegisterFormat() {
            return RegisterFormat.NONE;
        }

        public InsSparseSwitchData newInstance() {
            return new InsSparseSwitchData();
        }
    }

    static {
        Opcode[] opcodeArr = new Opcode[256];
        VALUES = opcodeArr;
        PAYLOADS = new Opcode[]{opcode, opcode, opcode};
        VALUES_2 = new Opcode[]{opcode, opcode, opcode, opcode, opcode, opcode, opcode, opcode, opcode, opcode, opcode, opcode};
        VALUES_3 = new Opcode[]{opcode};
        HashMap map = new HashMap();
        NAME_MAP = map;
        Opcode<InsNop> opcode = new Opcode<>(0, 2, "nop", new InsNopCreator(0));
        NOP = opcode;
        opcodeArr[0] = opcode;
        Opcode<Ins12x> opcode2 = new Opcode<>(1, 2, "move", new Ins12xCreator(1));
        MOVE = opcode2;
        opcodeArr[1] = opcode2;
        Opcode<Ins22x> opcode3 = new Opcode<>(2, 4, "move/from16", new Ins22xCreator(2));
        MOVE_FROM16 = opcode3;
        opcodeArr[2] = opcode3;
        Opcode<Ins32x> opcode4 = new Opcode<>(3, 6, "move/16", new Ins32xCreator(3));
        MOVE_16 = opcode4;
        opcodeArr[3] = opcode4;
        Opcode<Ins12x> opcode5 = new Opcode<>(4, 2, "move-wide", new Ins12xCreator(4));
        MOVE_WIDE = opcode5;
        opcodeArr[4] = opcode5;
        Opcode<Ins22x> opcode6 = new Opcode<>(5, 4, "move-wide/from16", new Ins22xCreator(5));
        MOVE_WIDE_FROM16 = opcode6;
        opcodeArr[5] = opcode6;
        Opcode<Ins32x> opcode7 = new Opcode<>(6, 6, "move-wide/16", new Ins32xCreator(6));
        MOVE_WIDE_16 = opcode7;
        opcodeArr[6] = opcode7;
        Opcode<Ins12x> opcode8 = new Opcode<>(7, 2, "move-object", new Ins12xCreator(7));
        MOVE_OBJECT = opcode8;
        opcodeArr[7] = opcode8;
        Opcode<Ins22x> opcode9 = new Opcode<>(8, 4, "move-object/from16", new Ins22xCreator(8));
        MOVE_OBJECT_FROM16 = opcode9;
        opcodeArr[8] = opcode9;
        Opcode<Ins32x> opcode10 = new Opcode<>(9, 6, "move-object/16", new Ins32xCreator(9));
        MOVE_OBJECT_16 = opcode10;
        opcodeArr[9] = opcode10;
        Opcode<Ins11x> opcode11 = new Opcode<>(10, 2, "move-result", new Ins11xWriteCreator(10));
        MOVE_RESULT = opcode11;
        opcodeArr[10] = opcode11;
        Opcode<Ins11x> opcode12 = new Opcode<>(11, 2, "move-result-wide", new Ins11xWriteCreator(11));
        MOVE_RESULT_WIDE = opcode12;
        opcodeArr[11] = opcode12;
        Opcode<Ins11x> opcode13 = new Opcode<>(12, 2, "move-result-object", new Ins11xWriteCreator(12));
        MOVE_RESULT_OBJECT = opcode13;
        opcodeArr[12] = opcode13;
        Opcode<Ins11x> opcode14 = new Opcode<>(13, 2, "move-exception", new Ins11xWriteCreator(13));
        MOVE_EXCEPTION = opcode14;
        opcodeArr[13] = opcode14;
        Opcode<Ins10x> opcode15 = new Opcode<>(14, 2, "return-void", new Ins10xCreator(14));
        RETURN_VOID = opcode15;
        opcodeArr[14] = opcode15;
        Opcode<Ins11x> opcode16 = new Opcode<>(15, 2, PsiKeyword.RETURN, new Ins11xCreator(15));
        RETURN = opcode16;
        opcodeArr[15] = opcode16;
        Opcode<Ins11x> opcode17 = new Opcode<>(16, 2, "return-wide", new Ins11xCreator(16));
        RETURN_WIDE = opcode17;
        opcodeArr[16] = opcode17;
        Opcode<Ins11x> opcode18 = new Opcode<>(17, 2, "return-object", new Ins11xCreator(17));
        RETURN_OBJECT = opcode18;
        opcodeArr[17] = opcode18;
        Opcode<InsConst4> opcode19 = new Opcode<>(18, 2, "const/4", new InsConst4Creator(18));
        CONST_4 = opcode19;
        opcodeArr[18] = opcode19;
        Opcode<InsConst16> opcode20 = new Opcode<>(19, 4, "const/16", new InsConst16Creator(19));
        CONST_16 = opcode20;
        opcodeArr[19] = opcode20;
        Opcode<InsConst> opcode21 = new Opcode<>(20, 6, PsiKeyword.CONST, new InsConstCreator(20));
        CONST = opcode21;
        opcodeArr[20] = opcode21;
        Opcode<InsConst16High> opcode22 = new Opcode<>(21, 4, "const/high16", new InsConst16HighCreator(21));
        CONST_HIGH16 = opcode22;
        opcodeArr[21] = opcode22;
        Opcode<InsConstWide16> opcode23 = new Opcode<>(22, 4, "const-wide/16", new InsConstWide16Creator(22));
        CONST_WIDE_16 = opcode23;
        opcodeArr[22] = opcode23;
        Opcode<InsConstWide32> opcode24 = new Opcode<>(23, 6, "const-wide/32", new InsConstWide32Creator(23));
        CONST_WIDE_32 = opcode24;
        opcodeArr[23] = opcode24;
        Opcode<InsConstWide> opcode25 = new Opcode<>(24, 10, "const-wide", new InsConstWideCreator(24));
        CONST_WIDE = opcode25;
        opcodeArr[24] = opcode25;
        Opcode<InsConstWideHigh16> opcode26 = new Opcode<>(25, 4, "const-wide/high16", new InsConstWideHigh16Creator(25));
        CONST_WIDE_HIGH16 = opcode26;
        opcodeArr[25] = opcode26;
        SectionType<StringId> sectionType = SectionType.STRING_ID;
        Opcode<InsConstString> opcode27 = new Opcode<>(26, 4, "const-string", sectionType, new InsConstStringCreator(26));
        CONST_STRING = opcode27;
        opcodeArr[26] = opcode27;
        Opcode<InsConstStringJumbo> opcode28 = new Opcode<>(27, 6, "const-string/jumbo", sectionType, new InsConstStringJumboCreator(27));
        CONST_STRING_JUMBO = opcode28;
        opcodeArr[27] = opcode28;
        SectionType<TypeId> sectionType2 = SectionType.TYPE_ID;
        Opcode<Ins21c> opcode29 = new Opcode<>(28, 4, "const-class", sectionType2, new Ins21cCreator(28));
        CONST_CLASS = opcode29;
        opcodeArr[28] = opcode29;
        Opcode<Ins11x> opcode30 = new Opcode<>(29, 2, "monitor-enter", new Ins11xCreator(29));
        MONITOR_ENTER = opcode30;
        opcodeArr[29] = opcode30;
        Opcode<Ins11x> opcode31 = new Opcode<>(30, 2, "monitor-exit", new Ins11xCreator(30));
        MONITOR_EXIT = opcode31;
        opcodeArr[30] = opcode31;
        Opcode<Ins21c> opcode32 = new Opcode<>(31, 4, "check-cast", sectionType2, new Ins21cCreator(31));
        CHECK_CAST = opcode32;
        opcodeArr[31] = opcode32;
        Opcode<Ins22c> opcode33 = new Opcode<>(32, 4, "instance-of", sectionType2, new Ins22cCreator(32));
        INSTANCE_OF = opcode33;
        opcodeArr[32] = opcode33;
        Opcode<Ins12x> opcode34 = new Opcode<>(33, 2, "array-length", new Ins12xCreator(33));
        ARRAY_LENGTH = opcode34;
        opcodeArr[33] = opcode34;
        Opcode<Ins21c> opcode35 = new Opcode<>(34, 4, "new-instance", sectionType2, new Ins21cCreator(34));
        NEW_INSTANCE = opcode35;
        opcodeArr[34] = opcode35;
        Opcode<Ins22c> opcode36 = new Opcode<>(35, 4, "new-array", sectionType2, new Ins22cCreator(35));
        NEW_ARRAY = opcode36;
        opcodeArr[35] = opcode36;
        Opcode<Ins35c> opcode37 = new Opcode<>(36, 6, "filled-new-array", sectionType2, new Ins35cCreator(36));
        FILLED_NEW_ARRAY = opcode37;
        opcodeArr[36] = opcode37;
        Opcode<Ins3rc> opcode38 = new Opcode<>(37, 6, "filled-new-array/range", sectionType2, new Ins3rcCreator(37));
        FILLED_NEW_ARRAY_RANGE = opcode38;
        opcodeArr[37] = opcode38;
        Opcode<InsFillArrayData> opcode39 = new Opcode<>(38, 6, "fill-array-data", new InsFillArrayDataCreator(38));
        FILL_ARRAY_DATA = opcode39;
        opcodeArr[38] = opcode39;
        Opcode<Ins11x> opcode40 = new Opcode<>(39, 2, PsiKeyword.THROW, new Ins11xCreator(39));
        THROW = opcode40;
        opcodeArr[39] = opcode40;
        Opcode<InsGoto> opcode41 = new Opcode<>(40, 2, PsiKeyword.GOTO, new InsGotoCreator(40));
        GOTO = opcode41;
        opcodeArr[40] = opcode41;
        Opcode<InsGoto> opcode42 = new Opcode<>(41, 4, "goto/16", new InsGotoCreator(41));
        GOTO_16 = opcode42;
        opcodeArr[41] = opcode42;
        Opcode<InsGoto> opcode43 = new Opcode<>(42, 6, "goto/32", new InsGotoCreator(42));
        GOTO_32 = opcode43;
        opcodeArr[42] = opcode43;
        Opcode<InsPackedSwitch> opcode44 = new Opcode<>(43, 6, "packed-switch", new InsPackedSwitchCreator(43));
        PACKED_SWITCH = opcode44;
        opcodeArr[43] = opcode44;
        Opcode<InsSparseSwitch> opcode45 = new Opcode<>(44, 6, "sparse-switch", new InsSparseSwitchCreator(44));
        SPARSE_SWITCH = opcode45;
        opcodeArr[44] = opcode45;
        Opcode<Ins23x> opcode46 = new Opcode<>(45, 4, "cmpl-float", new Ins23xCreator(45));
        CMPL_FLOAT = opcode46;
        opcodeArr[45] = opcode46;
        Opcode<Ins23x> opcode47 = new Opcode<>(46, 4, "cmpg-float", new Ins23xCreator(46));
        CMPG_FLOAT = opcode47;
        opcodeArr[46] = opcode47;
        Opcode<Ins23x> opcode48 = new Opcode<>(47, 4, "cmpl-double", new Ins23xCreator(47));
        CMPL_DOUBLE = opcode48;
        opcodeArr[47] = opcode48;
        Opcode<Ins23x> opcode49 = new Opcode<>(48, 4, "cmpg-double", new Ins23xCreator(48));
        CMPG_DOUBLE = opcode49;
        opcodeArr[48] = opcode49;
        Opcode<Ins23x> opcode50 = new Opcode<>(49, 4, "cmp-long", new Ins23xCreator(49));
        CMP_LONG = opcode50;
        opcodeArr[49] = opcode50;
        Opcode<Ins22t> opcode51 = new Opcode<>(50, 4, "if-eq", new Ins22tCreator(50));
        IF_EQ = opcode51;
        opcodeArr[50] = opcode51;
        Opcode<Ins22t> opcode52 = new Opcode<>(51, 4, "if-ne", new Ins22tCreator(51));
        IF_NE = opcode52;
        opcodeArr[51] = opcode52;
        Opcode<Ins22t> opcode53 = new Opcode<>(52, 4, "if-lt", new Ins22tCreator(52));
        IF_LT = opcode53;
        opcodeArr[52] = opcode53;
        Opcode<Ins22t> opcode54 = new Opcode<>(53, 4, "if-ge", new Ins22tCreator(53));
        IF_GE = opcode54;
        opcodeArr[53] = opcode54;
        Opcode<Ins22t> opcode55 = new Opcode<>(54, 4, "if-gt", new Ins22tCreator(54));
        IF_GT = opcode55;
        opcodeArr[54] = opcode55;
        Opcode<Ins22t> opcode56 = new Opcode<>(55, 4, "if-le", new Ins22tCreator(55));
        IF_LE = opcode56;
        opcodeArr[55] = opcode56;
        Opcode<Ins21t> opcode57 = new Opcode<>(56, 4, "if-eqz", new Ins21tCreator(56));
        IF_EQZ = opcode57;
        opcodeArr[56] = opcode57;
        Opcode<Ins21t> opcode58 = new Opcode<>(57, 4, "if-nez", new Ins21tCreator(57));
        IF_NEZ = opcode58;
        opcodeArr[57] = opcode58;
        Opcode<Ins21t> opcode59 = new Opcode<>(58, 4, "if-ltz", new Ins21tCreator(58));
        IF_LTZ = opcode59;
        opcodeArr[58] = opcode59;
        Opcode<Ins21t> opcode60 = new Opcode<>(59, 4, "if-gez", new Ins21tCreator(59));
        IF_GEZ = opcode60;
        opcodeArr[59] = opcode60;
        Opcode<Ins21t> opcode61 = new Opcode<>(60, 4, "if-gtz", new Ins21tCreator(60));
        IF_GTZ = opcode61;
        opcodeArr[60] = opcode61;
        Opcode<Ins21t> opcode62 = new Opcode<>(61, 4, "if-lez", new Ins21tCreator(61));
        IF_LEZ = opcode62;
        opcodeArr[61] = opcode62;
        Opcode<Ins23x> opcode63 = new Opcode<>(68, 4, "aget", new Ins23xCreator(68));
        AGET = opcode63;
        opcodeArr[68] = opcode63;
        Opcode<Ins23x> opcode64 = new Opcode<>(69, 4, "aget-wide", new Ins23xCreator(69));
        AGET_WIDE = opcode64;
        opcodeArr[69] = opcode64;
        Opcode<Ins23x> opcode65 = new Opcode<>(70, 4, "aget-object", new Ins23xCreator(70));
        AGET_OBJECT = opcode65;
        opcodeArr[70] = opcode65;
        Opcode<Ins23x> opcode66 = new Opcode<>(71, 4, "aget-boolean", new Ins23xCreator(71));
        AGET_BOOLEAN = opcode66;
        opcodeArr[71] = opcode66;
        Opcode<Ins23x> opcode67 = new Opcode<>(72, 4, "aget-byte", new Ins23xCreator(72));
        AGET_BYTE = opcode67;
        opcodeArr[72] = opcode67;
        Opcode<Ins23x> opcode68 = new Opcode<>(73, 4, "aget-char", new Ins23xCreator(73));
        AGET_CHAR = opcode68;
        opcodeArr[73] = opcode68;
        Opcode<Ins23x> opcode69 = new Opcode<>(74, 4, "aget-short", new Ins23xCreator(74));
        AGET_SHORT = opcode69;
        opcodeArr[74] = opcode69;
        Opcode<Ins23x> opcode70 = new Opcode<>(75, 4, "aput", new Ins23xAputCreator(75));
        APUT = opcode70;
        opcodeArr[75] = opcode70;
        Opcode<Ins23x> opcode71 = new Opcode<>(76, 4, "aput-wide", new Ins23xAputCreator(76));
        APUT_WIDE = opcode71;
        opcodeArr[76] = opcode71;
        Opcode<Ins23x> opcode72 = new Opcode<>(77, 4, "aput-object", new Ins23xAputCreator(77));
        APUT_OBJECT = opcode72;
        opcodeArr[77] = opcode72;
        Opcode<Ins23x> opcode73 = new Opcode<>(78, 4, "aput-boolean", new Ins23xAputCreator(78));
        APUT_BOOLEAN = opcode73;
        opcodeArr[78] = opcode73;
        Opcode<Ins23x> opcode74 = new Opcode<>(79, 4, "aput-byte", new Ins23xAputCreator(79));
        APUT_BYTE = opcode74;
        opcodeArr[79] = opcode74;
        Opcode<Ins23x> opcode75 = new Opcode<>(80, 4, "aput-char", new Ins23xAputCreator(80));
        APUT_CHAR = opcode75;
        opcodeArr[80] = opcode75;
        Opcode<Ins23x> opcode76 = new Opcode<>(81, 4, "aput-short", new Ins23xAputCreator(81));
        APUT_SHORT = opcode76;
        opcodeArr[81] = opcode76;
        SectionType<FieldId> sectionType3 = SectionType.FIELD_ID;
        Opcode<Ins22c> opcode77 = new Opcode<>(82, 4, "iget", sectionType3, new Ins22cCreator(82));
        IGET = opcode77;
        opcodeArr[82] = opcode77;
        Opcode<Ins22c> opcode78 = new Opcode<>(83, 4, "iget-wide", sectionType3, new Ins22cCreator(83));
        IGET_WIDE = opcode78;
        opcodeArr[83] = opcode78;
        Opcode<Ins22c> opcode79 = new Opcode<>(84, 4, "iget-object", sectionType3, new Ins22cCreator(84));
        IGET_OBJECT = opcode79;
        opcodeArr[84] = opcode79;
        Opcode<Ins22c> opcode80 = new Opcode<>(85, 4, "iget-boolean", sectionType3, new Ins22cCreator(85));
        IGET_BOOLEAN = opcode80;
        opcodeArr[85] = opcode80;
        Opcode<Ins22c> opcode81 = new Opcode<>(86, 4, "iget-byte", sectionType3, new Ins22cCreator(86));
        IGET_BYTE = opcode81;
        opcodeArr[86] = opcode81;
        Opcode<Ins22c> opcode82 = new Opcode<>(87, 4, "iget-char", sectionType3, new Ins22cCreator(87));
        IGET_CHAR = opcode82;
        opcodeArr[87] = opcode82;
        Opcode<Ins22c> opcode83 = new Opcode<>(88, 4, "iget-short", sectionType3, new Ins22cCreator(88));
        IGET_SHORT = opcode83;
        opcodeArr[88] = opcode83;
        Opcode<Ins22c> opcode84 = new Opcode<>(89, 4, "iput", sectionType3, new Ins22cPutCreator(89));
        IPUT = opcode84;
        opcodeArr[89] = opcode84;
        Opcode<Ins22c> opcode85 = new Opcode<>(90, 4, "iput-wide", sectionType3, new Ins22cPutCreator(90));
        IPUT_WIDE = opcode85;
        opcodeArr[90] = opcode85;
        Opcode<Ins22c> opcode86 = new Opcode<>(91, 4, "iput-object", sectionType3, new Ins22cPutCreator(91));
        IPUT_OBJECT = opcode86;
        opcodeArr[91] = opcode86;
        Opcode<Ins22c> opcode87 = new Opcode<>(92, 4, "iput-boolean", sectionType3, new Ins22cPutCreator(92));
        IPUT_BOOLEAN = opcode87;
        opcodeArr[92] = opcode87;
        Opcode<Ins22c> opcode88 = new Opcode<>(93, 4, "iput-byte", sectionType3, new Ins22cPutCreator(93));
        IPUT_BYTE = opcode88;
        opcodeArr[93] = opcode88;
        Opcode<Ins22c> opcode89 = new Opcode<>(94, 4, "iput-char", sectionType3, new Ins22cPutCreator(94));
        IPUT_CHAR = opcode89;
        opcodeArr[94] = opcode89;
        Opcode<Ins22c> opcode90 = new Opcode<>(95, 4, "iput-short", sectionType3, new Ins22cPutCreator(95));
        IPUT_SHORT = opcode90;
        opcodeArr[95] = opcode90;
        Opcode<Ins21c> opcode91 = new Opcode<>(96, 4, "sget", sectionType3, new Ins21cCreator(96));
        SGET = opcode91;
        opcodeArr[96] = opcode91;
        Opcode<Ins21c> opcode92 = new Opcode<>(97, 4, "sget-wide", sectionType3, new Ins21cCreator(97));
        SGET_WIDE = opcode92;
        opcodeArr[97] = opcode92;
        Opcode<Ins21c> opcode93 = new Opcode<>(98, 4, "sget-object", sectionType3, new Ins21cCreator(98));
        SGET_OBJECT = opcode93;
        opcodeArr[98] = opcode93;
        Opcode<Ins21c> opcode94 = new Opcode<>(99, 4, "sget-boolean", sectionType3, new Ins21cCreator(99));
        SGET_BOOLEAN = opcode94;
        opcodeArr[99] = opcode94;
        Opcode<Ins21c> opcode95 = new Opcode<>(100, 4, "sget-byte", sectionType3, new Ins21cCreator(100));
        SGET_BYTE = opcode95;
        opcodeArr[100] = opcode95;
        Opcode<Ins21c> opcode96 = new Opcode<>(101, 4, "sget-char", sectionType3, new Ins21cCreator(101));
        SGET_CHAR = opcode96;
        opcodeArr[101] = opcode96;
        Opcode<Ins21c> opcode97 = new Opcode<>(102, 4, "sget-short", sectionType3, new Ins21cCreator(102));
        SGET_SHORT = opcode97;
        opcodeArr[102] = opcode97;
        Opcode<Ins21c> opcode98 = new Opcode<>(103, 4, "sput", sectionType3, new Ins21cPutCreator(103));
        SPUT = opcode98;
        opcodeArr[103] = opcode98;
        Opcode<Ins21c> opcode99 = new Opcode<>(104, 4, "sput-wide", sectionType3, new Ins21cPutCreator(104));
        SPUT_WIDE = opcode99;
        opcodeArr[104] = opcode99;
        Opcode<Ins21c> opcode100 = new Opcode<>(105, 4, "sput-object", sectionType3, new Ins21cPutCreator(105));
        SPUT_OBJECT = opcode100;
        opcodeArr[105] = opcode100;
        Opcode<Ins21c> opcode101 = new Opcode<>(106, 4, "sput-boolean", sectionType3, new Ins21cPutCreator(106));
        SPUT_BOOLEAN = opcode101;
        opcodeArr[106] = opcode101;
        Opcode<Ins21c> opcode102 = new Opcode<>(107, 4, "sput-byte", sectionType3, new Ins21cPutCreator(107));
        SPUT_BYTE = opcode102;
        opcodeArr[107] = opcode102;
        Opcode<Ins21c> opcode103 = new Opcode<>(108, 4, "sput-char", sectionType3, new Ins21cPutCreator(108));
        SPUT_CHAR = opcode103;
        opcodeArr[108] = opcode103;
        Opcode<Ins21c> opcode104 = new Opcode<>(109, 4, "sput-short", sectionType3, new Ins21cPutCreator(109));
        SPUT_SHORT = opcode104;
        opcodeArr[109] = opcode104;
        SectionType<MethodId> sectionType4 = SectionType.METHOD_ID;
        Opcode<Ins35c> opcode105 = new Opcode<>(110, 6, "invoke-virtual", sectionType4, new Ins35cCreator(110));
        INVOKE_VIRTUAL = opcode105;
        opcodeArr[110] = opcode105;
        Opcode<Ins35c> opcode106 = new Opcode<>(111, 6, "invoke-super", sectionType4, new Ins35cCreator(111));
        INVOKE_SUPER = opcode106;
        opcodeArr[111] = opcode106;
        Opcode<Ins35c> opcode107 = new Opcode<>(112, 6, "invoke-direct", sectionType4, new Ins35cCreator(112));
        INVOKE_DIRECT = opcode107;
        opcodeArr[112] = opcode107;
        Opcode<Ins35c> opcode108 = new Opcode<>(113, 6, "invoke-static", sectionType4, new Ins35cCreator(113));
        INVOKE_STATIC = opcode108;
        opcodeArr[113] = opcode108;
        Opcode<Ins35c> opcode109 = new Opcode<>(114, 6, "invoke-interface", sectionType4, new Ins35cCreator(114));
        INVOKE_INTERFACE = opcode109;
        opcodeArr[114] = opcode109;
        Opcode<Ins10x> opcode110 = new Opcode<>(115, 2, "return-void-no-barrier", new Ins10xCreator(115));
        RETURN_VOID_NO_BARRIER = opcode110;
        opcodeArr[115] = opcode110;
        Opcode<Ins3rc> opcode111 = new Opcode<>(116, 6, "invoke-virtual/range", sectionType4, new Ins3rcCreator(116));
        INVOKE_VIRTUAL_RANGE = opcode111;
        opcodeArr[116] = opcode111;
        Opcode<Ins3rc> opcode112 = new Opcode<>(117, 6, "invoke-super/range", sectionType4, new Ins3rcCreator(117));
        INVOKE_SUPER_RANGE = opcode112;
        opcodeArr[117] = opcode112;
        Opcode<Ins3rc> opcode113 = new Opcode<>(118, 6, "invoke-direct/range", sectionType4, new Ins3rcCreator(118));
        INVOKE_DIRECT_RANGE = opcode113;
        opcodeArr[118] = opcode113;
        Opcode<Ins3rc> opcode114 = new Opcode<>(119, 6, "invoke-static/range", sectionType4, new Ins3rcCreator(119));
        INVOKE_STATIC_RANGE = opcode114;
        opcodeArr[119] = opcode114;
        Opcode<Ins3rc> opcode115 = new Opcode<>(120, 6, "invoke-interface/range", sectionType4, new Ins3rcCreator(120));
        INVOKE_INTERFACE_RANGE = opcode115;
        opcodeArr[120] = opcode115;
        Opcode<Ins12x> opcode116 = new Opcode<>(123, 2, "neg-int", new Ins12xCreator(123));
        NEG_INT = opcode116;
        opcodeArr[123] = opcode116;
        Opcode<Ins12x> opcode117 = new Opcode<>(124, 2, "not-int", new Ins12xCreator(124));
        NOT_INT = opcode117;
        opcodeArr[124] = opcode117;
        Opcode<Ins12x> opcode118 = new Opcode<>(125, 2, "neg-long", new Ins12xCreator(125));
        NEG_LONG = opcode118;
        opcodeArr[125] = opcode118;
        Opcode<Ins12x> opcode119 = new Opcode<>(126, 2, "not-long", new Ins12xCreator(126));
        NOT_LONG = opcode119;
        opcodeArr[126] = opcode119;
        Opcode<Ins12x> opcode120 = new Opcode<>(127, 2, "neg-float", new Ins12xCreator(127));
        NEG_FLOAT = opcode120;
        opcodeArr[127] = opcode120;
        Opcode<Ins12x> opcode121 = new Opcode<>(128, 2, "neg-double", new Ins12xCreator(128));
        NEG_DOUBLE = opcode121;
        opcodeArr[128] = opcode121;
        Opcode<Ins12x> opcode122 = new Opcode<>(129, 2, "int-to-long", new Ins12xCreator(129));
        INT_TO_LONG = opcode122;
        opcodeArr[129] = opcode122;
        Opcode<Ins12x> opcode123 = new Opcode<>(130, 2, "int-to-float", new Ins12xCreator(130));
        INT_TO_FLOAT = opcode123;
        opcodeArr[130] = opcode123;
        Opcode<Ins12x> opcode124 = new Opcode<>(131, 2, "int-to-double", new Ins12xCreator(131));
        INT_TO_DOUBLE = opcode124;
        opcodeArr[131] = opcode124;
        Opcode<Ins12x> opcode125 = new Opcode<>(132, 2, "long-to-int", new Ins12xCreator(132));
        LONG_TO_INT = opcode125;
        opcodeArr[132] = opcode125;
        Opcode<Ins12x> opcode126 = new Opcode<>(133, 2, "long-to-float", new Ins12xCreator(133));
        LONG_TO_FLOAT = opcode126;
        opcodeArr[133] = opcode126;
        Opcode<Ins12x> opcode127 = new Opcode<>(134, 2, "long-to-double", new Ins12xCreator(134));
        LONG_TO_DOUBLE = opcode127;
        opcodeArr[134] = opcode127;
        Opcode<Ins12x> opcode128 = new Opcode<>(135, 2, "float-to-int", new Ins12xCreator(135));
        FLOAT_TO_INT = opcode128;
        opcodeArr[135] = opcode128;
        Opcode<Ins12x> opcode129 = new Opcode<>(136, 2, "float-to-long", new Ins12xCreator(136));
        FLOAT_TO_LONG = opcode129;
        opcodeArr[136] = opcode129;
        Opcode<Ins12x> opcode130 = new Opcode<>(137, 2, "float-to-double", new Ins12xCreator(137));
        FLOAT_TO_DOUBLE = opcode130;
        opcodeArr[137] = opcode130;
        Opcode<Ins12x> opcode131 = new Opcode<>(138, 2, "double-to-int", new Ins12xCreator(138));
        DOUBLE_TO_INT = opcode131;
        opcodeArr[138] = opcode131;
        Opcode<Ins12x> opcode132 = new Opcode<>(139, 2, "double-to-long", new Ins12xCreator(139));
        DOUBLE_TO_LONG = opcode132;
        opcodeArr[139] = opcode132;
        Opcode<Ins12x> opcode133 = new Opcode<>(140, 2, "double-to-float", new Ins12xCreator(140));
        DOUBLE_TO_FLOAT = opcode133;
        opcodeArr[140] = opcode133;
        Opcode<Ins12x> opcode134 = new Opcode<>(141, 2, "int-to-byte", new Ins12xCreator(141));
        INT_TO_BYTE = opcode134;
        opcodeArr[141] = opcode134;
        Opcode<Ins12x> opcode135 = new Opcode<>(142, 2, "int-to-char", new Ins12xCreator(142));
        INT_TO_CHAR = opcode135;
        opcodeArr[142] = opcode135;
        Opcode<Ins12x> opcode136 = new Opcode<>(143, 2, "int-to-short", new Ins12xCreator(143));
        INT_TO_SHORT = opcode136;
        opcodeArr[143] = opcode136;
        Opcode<Ins23x> opcode137 = new Opcode<>(144, 4, "add-int", new Ins23xCreator(144));
        ADD_INT = opcode137;
        opcodeArr[144] = opcode137;
        Opcode<Ins23x> opcode138 = new Opcode<>(145, 4, "sub-int", new Ins23xCreator(145));
        SUB_INT = opcode138;
        opcodeArr[145] = opcode138;
        Opcode<Ins23x> opcode139 = new Opcode<>(146, 4, "mul-int", new Ins23xCreator(146));
        MUL_INT = opcode139;
        opcodeArr[146] = opcode139;
        Opcode<Ins23x> opcode140 = new Opcode<>(147, 4, "div-int", new Ins23xCreator(147));
        DIV_INT = opcode140;
        opcodeArr[147] = opcode140;
        Opcode<Ins23x> opcode141 = new Opcode<>(148, 4, "rem-int", new Ins23xCreator(148));
        REM_INT = opcode141;
        opcodeArr[148] = opcode141;
        Opcode<Ins23x> opcode142 = new Opcode<>(149, 4, "and-int", new Ins23xCreator(149));
        AND_INT = opcode142;
        opcodeArr[149] = opcode142;
        Opcode<Ins23x> opcode143 = new Opcode<>(150, 4, "or-int", new Ins23xCreator(150));
        OR_INT = opcode143;
        opcodeArr[150] = opcode143;
        Opcode<Ins23x> opcode144 = new Opcode<>(151, 4, "xor-int", new Ins23xCreator(151));
        XOR_INT = opcode144;
        opcodeArr[151] = opcode144;
        Opcode<Ins23x> opcode145 = new Opcode<>(152, 4, "shl-int", new Ins23xCreator(152));
        SHL_INT = opcode145;
        opcodeArr[152] = opcode145;
        Opcode<Ins23x> opcode146 = new Opcode<>(153, 4, "shr-int", new Ins23xCreator(153));
        SHR_INT = opcode146;
        opcodeArr[153] = opcode146;
        Opcode<Ins23x> opcode147 = new Opcode<>(154, 4, "ushr-int", new Ins23xCreator(154));
        USHR_INT = opcode147;
        opcodeArr[154] = opcode147;
        Opcode<Ins23x> opcode148 = new Opcode<>(155, 4, "add-long", new Ins23xCreator(155));
        ADD_LONG = opcode148;
        opcodeArr[155] = opcode148;
        Opcode<Ins23x> opcode149 = new Opcode<>(156, 4, "sub-long", new Ins23xCreator(156));
        SUB_LONG = opcode149;
        opcodeArr[156] = opcode149;
        Opcode<Ins23x> opcode150 = new Opcode<>(157, 4, "mul-long", new Ins23xCreator(157));
        MUL_LONG = opcode150;
        opcodeArr[157] = opcode150;
        Opcode<Ins23x> opcode151 = new Opcode<>(158, 4, "div-long", new Ins23xCreator(158));
        DIV_LONG = opcode151;
        opcodeArr[158] = opcode151;
        Opcode<Ins23x> opcode152 = new Opcode<>(159, 4, "rem-long", new Ins23xCreator(159));
        REM_LONG = opcode152;
        opcodeArr[159] = opcode152;
        Opcode<Ins23x> opcode153 = new Opcode<>(160, 4, "and-long", new Ins23xCreator(160));
        AND_LONG = opcode153;
        opcodeArr[160] = opcode153;
        Opcode<Ins23x> opcode154 = new Opcode<>(161, 4, "or-long", new Ins23xCreator(161));
        OR_LONG = opcode154;
        opcodeArr[161] = opcode154;
        Opcode<Ins23x> opcode155 = new Opcode<>(162, 4, "xor-long", new Ins23xCreator(162));
        XOR_LONG = opcode155;
        opcodeArr[162] = opcode155;
        Opcode<Ins23x> opcode156 = new Opcode<>(163, 4, "shl-long", new Ins23xCreator(163));
        SHL_LONG = opcode156;
        opcodeArr[163] = opcode156;
        Opcode<Ins23x> opcode157 = new Opcode<>(164, 4, "shr-long", new Ins23xCreator(164));
        SHR_LONG = opcode157;
        opcodeArr[164] = opcode157;
        Opcode<Ins23x> opcode158 = new Opcode<>(165, 4, "ushr-long", new Ins23xCreator(165));
        USHR_LONG = opcode158;
        opcodeArr[165] = opcode158;
        Opcode<Ins23x> opcode159 = new Opcode<>(166, 4, "add-float", new Ins23xCreator(166));
        ADD_FLOAT = opcode159;
        opcodeArr[166] = opcode159;
        Opcode<Ins23x> opcode160 = new Opcode<>(167, 4, "sub-float", new Ins23xCreator(167));
        SUB_FLOAT = opcode160;
        opcodeArr[167] = opcode160;
        Opcode<Ins23x> opcode161 = new Opcode<>(168, 4, "mul-float", new Ins23xCreator(168));
        MUL_FLOAT = opcode161;
        opcodeArr[168] = opcode161;
        Opcode<Ins23x> opcode162 = new Opcode<>(169, 4, "div-float", new Ins23xCreator(169));
        DIV_FLOAT = opcode162;
        opcodeArr[169] = opcode162;
        Opcode<Ins23x> opcode163 = new Opcode<>(170, 4, "rem-float", new Ins23xCreator(170));
        REM_FLOAT = opcode163;
        opcodeArr[170] = opcode163;
        Opcode<Ins23x> opcode164 = new Opcode<>(171, 4, "add-double", new Ins23xCreator(171));
        ADD_DOUBLE = opcode164;
        opcodeArr[171] = opcode164;
        Opcode<Ins23x> opcode165 = new Opcode<>(172, 4, "sub-double", new Ins23xCreator(172));
        SUB_DOUBLE = opcode165;
        opcodeArr[172] = opcode165;
        Opcode<Ins23x> opcode166 = new Opcode<>(173, 4, "mul-double", new Ins23xCreator(173));
        MUL_DOUBLE = opcode166;
        opcodeArr[173] = opcode166;
        Opcode<Ins23x> opcode167 = new Opcode<>(174, 4, "div-double", new Ins23xCreator(174));
        DIV_DOUBLE = opcode167;
        opcodeArr[174] = opcode167;
        Opcode<Ins23x> opcode168 = new Opcode<>(175, 4, "rem-double", new Ins23xCreator(175));
        REM_DOUBLE = opcode168;
        opcodeArr[175] = opcode168;
        Opcode<Ins12x> opcode169 = new Opcode<>(176, 2, "add-int/2addr", new Ins12xCreator(176));
        ADD_INT_2ADDR = opcode169;
        opcodeArr[176] = opcode169;
        Opcode<Ins12x> opcode170 = new Opcode<>(177, 2, "sub-int/2addr", new Ins12xCreator(177));
        SUB_INT_2ADDR = opcode170;
        opcodeArr[177] = opcode170;
        Opcode<Ins12x> opcode171 = new Opcode<>(178, 2, "mul-int/2addr", new Ins12xCreator(178));
        MUL_INT_2ADDR = opcode171;
        opcodeArr[178] = opcode171;
        Opcode<Ins12x> opcode172 = new Opcode<>(179, 2, "div-int/2addr", new Ins12xCreator(179));
        DIV_INT_2ADDR = opcode172;
        opcodeArr[179] = opcode172;
        Opcode<Ins12x> opcode173 = new Opcode<>(180, 2, "rem-int/2addr", new Ins12xCreator(180));
        REM_INT_2ADDR = opcode173;
        opcodeArr[180] = opcode173;
        Opcode<Ins12x> opcode174 = new Opcode<>(181, 2, "and-int/2addr", new Ins12xCreator(181));
        AND_INT_2ADDR = opcode174;
        opcodeArr[181] = opcode174;
        Opcode<Ins12x> opcode175 = new Opcode<>(182, 2, "or-int/2addr", new Ins12xCreator(182));
        OR_INT_2ADDR = opcode175;
        opcodeArr[182] = opcode175;
        Opcode<Ins12x> opcode176 = new Opcode<>(183, 2, "xor-int/2addr", new Ins12xCreator(183));
        XOR_INT_2ADDR = opcode176;
        opcodeArr[183] = opcode176;
        Opcode<Ins12x> opcode177 = new Opcode<>(184, 2, "shl-int/2addr", new Ins12xCreator(184));
        SHL_INT_2ADDR = opcode177;
        opcodeArr[184] = opcode177;
        Opcode<Ins12x> opcode178 = new Opcode<>(185, 2, "shr-int/2addr", new Ins12xCreator(185));
        SHR_INT_2ADDR = opcode178;
        opcodeArr[185] = opcode178;
        Opcode<Ins12x> opcode179 = new Opcode<>(186, 2, "ushr-int/2addr", new Ins12xCreator(186));
        USHR_INT_2ADDR = opcode179;
        opcodeArr[186] = opcode179;
        Opcode<Ins12x> opcode180 = new Opcode<>(187, 2, "add-long/2addr", new Ins12xCreator(187));
        ADD_LONG_2ADDR = opcode180;
        opcodeArr[187] = opcode180;
        Opcode<Ins12x> opcode181 = new Opcode<>(188, 2, "sub-long/2addr", new Ins12xCreator(188));
        SUB_LONG_2ADDR = opcode181;
        opcodeArr[188] = opcode181;
        Opcode<Ins12x> opcode182 = new Opcode<>(189, 2, "mul-long/2addr", new Ins12xCreator(189));
        MUL_LONG_2ADDR = opcode182;
        opcodeArr[189] = opcode182;
        Opcode<Ins12x> opcode183 = new Opcode<>(190, 2, "div-long/2addr", new Ins12xCreator(190));
        DIV_LONG_2ADDR = opcode183;
        opcodeArr[190] = opcode183;
        Opcode<Ins12x> opcode184 = new Opcode<>(191, 2, "rem-long/2addr", new Ins12xCreator(191));
        REM_LONG_2ADDR = opcode184;
        opcodeArr[191] = opcode184;
        Opcode<Ins12x> opcode185 = new Opcode<>(192, 2, "and-long/2addr", new Ins12xCreator(192));
        AND_LONG_2ADDR = opcode185;
        opcodeArr[192] = opcode185;
        Opcode<Ins12x> opcode186 = new Opcode<>(193, 2, "or-long/2addr", new Ins12xCreator(193));
        OR_LONG_2ADDR = opcode186;
        opcodeArr[193] = opcode186;
        Opcode<Ins12x> opcode187 = new Opcode<>(194, 2, "xor-long/2addr", new Ins12xCreator(194));
        XOR_LONG_2ADDR = opcode187;
        opcodeArr[194] = opcode187;
        Opcode<Ins12x> opcode188 = new Opcode<>(195, 2, "shl-long/2addr", new Ins12xCreator(195));
        SHL_LONG_2ADDR = opcode188;
        opcodeArr[195] = opcode188;
        Opcode<Ins12x> opcode189 = new Opcode<>(196, 2, "shr-long/2addr", new Ins12xCreator(196));
        SHR_LONG_2ADDR = opcode189;
        opcodeArr[196] = opcode189;
        Opcode<Ins12x> opcode190 = new Opcode<>(197, 2, "ushr-long/2addr", new Ins12xCreator(197));
        USHR_LONG_2ADDR = opcode190;
        opcodeArr[197] = opcode190;
        Opcode<Ins12x> opcode191 = new Opcode<>(198, 2, "add-float/2addr", new Ins12xCreator(198));
        ADD_FLOAT_2ADDR = opcode191;
        opcodeArr[198] = opcode191;
        Opcode<Ins12x> opcode192 = new Opcode<>(199, 2, "sub-float/2addr", new Ins12xCreator(199));
        SUB_FLOAT_2ADDR = opcode192;
        opcodeArr[199] = opcode192;
        Opcode<Ins12x> opcode193 = new Opcode<>(200, 2, "mul-float/2addr", new Ins12xCreator(200));
        MUL_FLOAT_2ADDR = opcode193;
        opcodeArr[200] = opcode193;
        Opcode<Ins12x> opcode194 = new Opcode<>(201, 2, "div-float/2addr", new Ins12xCreator(201));
        DIV_FLOAT_2ADDR = opcode194;
        opcodeArr[201] = opcode194;
        Opcode<Ins12x> opcode195 = new Opcode<>(202, 2, "rem-float/2addr", new Ins12xCreator(202));
        REM_FLOAT_2ADDR = opcode195;
        opcodeArr[202] = opcode195;
        Opcode<Ins12x> opcode196 = new Opcode<>(203, 2, "add-double/2addr", new Ins12xCreator(203));
        ADD_DOUBLE_2ADDR = opcode196;
        opcodeArr[203] = opcode196;
        Opcode<Ins12x> opcode197 = new Opcode<>(204, 2, "sub-double/2addr", new Ins12xCreator(204));
        SUB_DOUBLE_2ADDR = opcode197;
        opcodeArr[204] = opcode197;
        Opcode<Ins12x> opcode198 = new Opcode<>(WinError.ERROR_NO_SIGNAL_SENT, 2, "mul-double/2addr", new Ins12xCreator(WinError.ERROR_NO_SIGNAL_SENT));
        MUL_DOUBLE_2ADDR = opcode198;
        opcodeArr[205] = opcode198;
        Opcode<Ins12x> opcode199 = new Opcode<>(WinError.ERROR_FILENAME_EXCED_RANGE, 2, "div-double/2addr", new Ins12xCreator(WinError.ERROR_FILENAME_EXCED_RANGE));
        DIV_DOUBLE_2ADDR = opcode199;
        opcodeArr[206] = opcode199;
        Opcode<Ins12x> opcode200 = new Opcode<>(WinError.ERROR_RING2_STACK_IN_USE, 2, "rem-double/2addr", new Ins12xCreator(WinError.ERROR_RING2_STACK_IN_USE));
        REM_DOUBLE_2ADDR = opcode200;
        opcodeArr[207] = opcode200;
        Opcode<Ins22s> opcode201 = new Opcode<>(WinError.ERROR_META_EXPANSION_TOO_LONG, 4, "add-int/lit16", new Ins22sCreator(WinError.ERROR_META_EXPANSION_TOO_LONG));
        ADD_INT_LIT16 = opcode201;
        opcodeArr[208] = opcode201;
        Opcode<Ins22s> opcode202 = new Opcode<>(WinError.ERROR_INVALID_SIGNAL_NUMBER, 4, "rsub-int", new Ins22sCreator(WinError.ERROR_INVALID_SIGNAL_NUMBER));
        RSUB_INT = opcode202;
        opcodeArr[209] = opcode202;
        Opcode<Ins22s> opcode203 = new Opcode<>(WinError.ERROR_THREAD_1_INACTIVE, 4, "mul-int/lit16", new Ins22sCreator(WinError.ERROR_THREAD_1_INACTIVE));
        MUL_INT_LIT16 = opcode203;
        opcodeArr[210] = opcode203;
        Opcode<Ins22s> opcode204 = new Opcode<>(211, 4, "div-int/lit16", new Ins22sCreator(211));
        DIV_INT_LIT16 = opcode204;
        opcodeArr[211] = opcode204;
        Opcode<Ins22s> opcode205 = new Opcode<>(WinError.ERROR_LOCKED, 4, "rem-int/lit16", new Ins22sCreator(WinError.ERROR_LOCKED));
        REM_INT_LIT16 = opcode205;
        opcodeArr[212] = opcode205;
        Opcode<Ins22s> opcode206 = new Opcode<>(213, 4, "and-int/lit16", new Ins22sCreator(213));
        AND_INT_LIT16 = opcode206;
        opcodeArr[213] = opcode206;
        Opcode<Ins22s> opcode207 = new Opcode<>(WinError.ERROR_TOO_MANY_MODULES, 4, "or-int/lit16", new Ins22sCreator(WinError.ERROR_TOO_MANY_MODULES));
        OR_INT_LIT16 = opcode207;
        opcodeArr[214] = opcode207;
        Opcode<Ins22s> opcode208 = new Opcode<>(WinError.ERROR_NESTING_NOT_ALLOWED, 4, "xor-int/lit16", new Ins22sCreator(WinError.ERROR_NESTING_NOT_ALLOWED));
        XOR_INT_LIT16 = opcode208;
        opcodeArr[215] = opcode208;
        Opcode<Ins22b> opcode209 = new Opcode<>(WinError.ERROR_EXE_MACHINE_TYPE_MISMATCH, 4, "add-int/lit8", new Ins22bCreator(WinError.ERROR_EXE_MACHINE_TYPE_MISMATCH));
        ADD_INT_LIT8 = opcode209;
        opcodeArr[216] = opcode209;
        Opcode<Ins22b> opcode210 = new Opcode<>(WinError.ERROR_EXE_CANNOT_MODIFY_SIGNED_BINARY, 4, "rsub-int/lit8", new Ins22bCreator(WinError.ERROR_EXE_CANNOT_MODIFY_SIGNED_BINARY));
        RSUB_INT_LIT8 = opcode210;
        opcodeArr[217] = opcode210;
        Opcode<Ins22b> opcode211 = new Opcode<>(WinError.ERROR_EXE_CANNOT_MODIFY_STRONG_SIGNED_BINARY, 4, "mul-int/lit8", new Ins22bCreator(WinError.ERROR_EXE_CANNOT_MODIFY_STRONG_SIGNED_BINARY));
        MUL_INT_LIT8 = opcode211;
        opcodeArr[218] = opcode211;
        Opcode<Ins22b> opcode212 = new Opcode<>(219, 4, "div-int/lit8", new Ins22bCreator(219));
        DIV_INT_LIT8 = opcode212;
        opcodeArr[219] = opcode212;
        Opcode<Ins22b> opcode213 = new Opcode<>(WinError.ERROR_FILE_CHECKED_OUT, 4, "rem-int/lit8", new Ins22bCreator(WinError.ERROR_FILE_CHECKED_OUT));
        REM_INT_LIT8 = opcode213;
        opcodeArr[220] = opcode213;
        Opcode<Ins22b> opcode214 = new Opcode<>(WinError.ERROR_CHECKOUT_REQUIRED, 4, "and-int/lit8", new Ins22bCreator(WinError.ERROR_CHECKOUT_REQUIRED));
        AND_INT_LIT8 = opcode214;
        opcodeArr[221] = opcode214;
        Opcode<Ins22b> opcode215 = new Opcode<>(WinError.ERROR_BAD_FILE_TYPE, 4, "or-int/lit8", new Ins22bCreator(WinError.ERROR_BAD_FILE_TYPE));
        OR_INT_LIT8 = opcode215;
        opcodeArr[222] = opcode215;
        Opcode<Ins22b> opcode216 = new Opcode<>(WinError.ERROR_FILE_TOO_LARGE, 4, "xor-int/lit8", new Ins22bCreator(WinError.ERROR_FILE_TOO_LARGE));
        XOR_INT_LIT8 = opcode216;
        opcodeArr[223] = opcode216;
        Opcode<Ins22b> opcode217 = new Opcode<>(WinError.ERROR_FORMS_AUTH_REQUIRED, 4, "shl-int/lit8", new Ins22bCreator(WinError.ERROR_FORMS_AUTH_REQUIRED));
        SHL_INT_LIT8 = opcode217;
        opcodeArr[224] = opcode217;
        Opcode<Ins22b> opcode218 = new Opcode<>(WinError.ERROR_VIRUS_INFECTED, 4, "shr-int/lit8", new Ins22bCreator(WinError.ERROR_VIRUS_INFECTED));
        SHR_INT_LIT8 = opcode218;
        opcodeArr[225] = opcode218;
        Opcode<Ins22b> opcode219 = new Opcode<>(WinError.ERROR_VIRUS_DELETED, 4, "ushr-int/lit8", new Ins22bCreator(WinError.ERROR_VIRUS_DELETED));
        USHR_INT_LIT8 = opcode219;
        opcodeArr[226] = opcode219;
        Opcode<Ins22c> opcode220 = new Opcode<>(227, 4, "iget-volatile", sectionType3, new Ins22cCreator(227));
        IGET_VOLATILE = opcode220;
        opcodeArr[227] = opcode220;
        Opcode<Ins22c> opcode221 = new Opcode<>(228, 4, "iput-volatile", sectionType3, new Ins22cPutCreator(228));
        IPUT_VOLATILE = opcode221;
        opcodeArr[228] = opcode221;
        Opcode<Ins21c> opcode222 = new Opcode<>(WinError.ERROR_PIPE_LOCAL, 4, "sget-volatile", sectionType3, new Ins21cCreator(WinError.ERROR_PIPE_LOCAL));
        SGET_VOLATILE = opcode222;
        opcodeArr[229] = opcode222;
        Opcode<Ins21c> opcode223 = new Opcode<>(WinError.ERROR_BAD_PIPE, 4, "sput-volatile", sectionType3, new Ins21cPutCreator(WinError.ERROR_BAD_PIPE));
        SPUT_VOLATILE = opcode223;
        opcodeArr[230] = opcode223;
        Opcode<Ins22c> opcode224 = new Opcode<>(WinError.ERROR_PIPE_BUSY, 4, "iget-object-volatile", sectionType3, new Ins22cCreator(WinError.ERROR_PIPE_BUSY));
        IGET_OBJECT_VOLATILE = opcode224;
        opcodeArr[231] = opcode224;
        Opcode<Ins22c> opcode225 = new Opcode<>(WinError.ERROR_NO_DATA, 4, "iget-wide-volatile", sectionType3, new Ins22cCreator(WinError.ERROR_NO_DATA));
        IGET_WIDE_VOLATILE = opcode225;
        opcodeArr[232] = opcode225;
        Opcode<Ins22c> opcode226 = new Opcode<>(WinError.ERROR_PIPE_NOT_CONNECTED, 4, "iput-wide-volatile", sectionType3, new Ins22cPutCreator(WinError.ERROR_PIPE_NOT_CONNECTED));
        IPUT_WIDE_VOLATILE = opcode226;
        opcodeArr[233] = opcode226;
        Opcode<Ins21c> opcode227 = new Opcode<>(WinError.ERROR_MORE_DATA, 4, "sget-wide-volatile", sectionType3, new Ins21cCreator(WinError.ERROR_MORE_DATA));
        SGET_WIDE_VOLATILE = opcode227;
        opcodeArr[234] = opcode227;
        Opcode<Ins21c> opcode228 = new Opcode<>(235, 4, "sput-wide-volatile", sectionType3, new Ins21cPutCreator(235));
        SPUT_WIDE_VOLATILE = opcode228;
        opcodeArr[235] = opcode228;
        Opcode<Ins22cs> opcode229 = new Opcode<>(236, 4, "iput-byte-quick", sectionType3, new Ins22csPutCreator(236));
        IPUT_BYTE_QUICK = opcode229;
        opcodeArr[236] = opcode229;
        Opcode<Ins20bc> opcode230 = new Opcode<>(237, 4, "throw-verification-error", new Ins20bcCreator(237));
        THROW_VERIFICATION_ERROR = opcode230;
        opcodeArr[237] = opcode230;
        Opcode<Ins35mi> opcode231 = new Opcode<>(238, 6, "execute-inline", new Ins35miCreator(238));
        EXECUTE_INLINE = opcode231;
        opcodeArr[238] = opcode231;
        Opcode<Ins3rmi> opcode232 = new Opcode<>(239, 6, "execute-inline/range", new Ins3rmiCreator(239));
        EXECUTE_INLINE_RANGE = opcode232;
        opcodeArr[239] = opcode232;
        Opcode<Ins35c> opcode233 = new Opcode<>(240, 6, "invoke-direct-empty", sectionType4, new Ins35cCreator(240));
        INVOKE_DIRECT_EMPTY = opcode233;
        opcodeArr[240] = opcode233;
        Opcode<Ins10x> opcode234 = new Opcode<>(241, 2, "return-void-barrier", new Ins10xCreator(241));
        RETURN_VOID_BARRIER = opcode234;
        opcodeArr[241] = opcode234;
        Opcode<Ins22cs> opcode235 = new Opcode<>(242, 4, "iget-quick", sectionType3, new Ins22csCreator(242));
        IGET_QUICK = opcode235;
        opcodeArr[242] = opcode235;
        Opcode<Ins22cs> opcode236 = new Opcode<>(243, 4, "iget-wide-quick", sectionType3, new Ins22csCreator(243));
        IGET_WIDE_QUICK = opcode236;
        opcodeArr[243] = opcode236;
        Opcode<Ins22cs> opcode237 = new Opcode<>(244, 4, "iget-object-quick", sectionType3, new Ins22csCreator(244));
        IGET_OBJECT_QUICK = opcode237;
        opcodeArr[244] = opcode237;
        Opcode<Ins22cs> opcode238 = new Opcode<>(245, 4, "iput-quick", sectionType3, new Ins22csPutCreator(245));
        IPUT_QUICK = opcode238;
        opcodeArr[245] = opcode238;
        Opcode<Ins22cs> opcode239 = new Opcode<>(246, 4, "iput-wide-quick", sectionType3, new Ins22csPutCreator(246));
        IPUT_WIDE_QUICK = opcode239;
        opcodeArr[246] = opcode239;
        Opcode<Ins22cs> opcode240 = new Opcode<>(Const.SAME_LOCALS_1_STACK_ITEM_FRAME_EXTENDED, 4, "iput-object-quick", sectionType3, new Ins22csPutCreator(Const.SAME_LOCALS_1_STACK_ITEM_FRAME_EXTENDED));
        IPUT_OBJECT_QUICK = opcode240;
        opcodeArr[247] = opcode240;
        Opcode<Ins35ms> opcode241 = new Opcode<>(Const.CHOP_FRAME, 6, "invoke-virtual-quick", sectionType4, new Ins35msCreator(Const.CHOP_FRAME));
        INVOKE_VIRTUAL_QUICK = opcode241;
        opcodeArr[248] = opcode241;
        Opcode<Ins3rms> opcode242 = new Opcode<>(249, 6, "invoke-virtual-quick/range", sectionType4, new Ins3rmsCreator(249));
        INVOKE_VIRTUAL_QUICK_RANGE = opcode242;
        opcodeArr[249] = opcode242;
        Opcode<Ins45cc> opcode243 = new Opcode<>(Const.CHOP_FRAME_MAX, 8, "invoke-polymorphic", sectionType4, new Ins45ccCreator(Const.CHOP_FRAME_MAX));
        INVOKE_POLYMORPHIC = opcode243;
        opcodeArr[250] = opcode243;
        Opcode<Ins4rcc> opcode244 = new Opcode<>(Const.SAME_FRAME_EXTENDED, 8, "invoke-polymorphic/range", sectionType4, new Ins4rccCreator(Const.SAME_FRAME_EXTENDED));
        INVOKE_POLYMORPHIC_RANGE = opcode244;
        opcodeArr[251] = opcode244;
        SectionType<CallSiteId> sectionType5 = SectionType.CALL_SITE_ID;
        Opcode<Ins35c> opcode245 = new Opcode<>(Const.APPEND_FRAME, 6, "invoke-custom", sectionType5, new Ins35cCreator(Const.APPEND_FRAME));
        INVOKE_CUSTOM = opcode245;
        opcodeArr[252] = opcode245;
        Opcode<Ins3rc> opcode246 = new Opcode<>(253, 6, "invoke-custom/range", sectionType5, new Ins3rcCreator(253));
        INVOKE_CUSTOM_RANGE = opcode246;
        opcodeArr[253] = opcode246;
        Opcode<Ins21c> opcode247 = new Opcode<>(254, 4, "const-method-handle", SectionType.METHOD_HANDLE, new Ins21cCreator(254));
        CONST_METHOD_HANDLE = opcode247;
        opcodeArr[254] = opcode247;
        Opcode<Ins21c> opcode248 = new Opcode<>(255, 4, "const-method-type", SectionType.PROTO_ID, new Ins21cCreator(255));
        CONST_METHOD_TYPE = opcode248;
        opcodeArr[255] = opcode248;
        Opcode<InsPackedSwitchData> opcode249 = new Opcode<>(256, -1, "packed-switch-payload", new InsPackedSwitchDataCreator(256));
        PACKED_SWITCH_PAYLOAD = opcode249;
        Opcode<InsSparseSwitchData> opcode250 = new Opcode<>(512, -1, "sparse-switch-payload", new InsSparseSwitchDataCreator(512));
        SPARSE_SWITCH_PAYLOAD = opcode250;
        Opcode<InsArrayData> opcode251 = new Opcode<>(768, -1, "array-data", new InsArrayDataCreator(768));
        ARRAY_PAYLOAD = opcode251;
        Opcode<Ins22cs> opcode252 = new Opcode<>(235, 4, "iput-boolean-quick", sectionType3, new Ins22csPutCreator(235));
        IPUT_BOOLEAN_QUICK = opcode252;
        Opcode<Ins22cs> opcode253 = new Opcode<>(237, 4, "iput-char-quick", sectionType3, new Ins22csPutCreator(237));
        IPUT_CHAR_QUICK = opcode253;
        Opcode<Ins22cs> opcode254 = new Opcode<>(238, 4, "iput-short-quick", sectionType3, new Ins22csPutCreator(238));
        IPUT_SHORT_QUICK = opcode254;
        Opcode<Ins22cs> opcode255 = new Opcode<>(239, 4, "iget-boolean-quick", sectionType3, new Ins22csCreator(239));
        IGET_BOOLEAN_QUICK = opcode255;
        Opcode<Ins3rc> opcode256 = new Opcode<>(240, 6, "invoke-object-init/range", sectionType4, new Ins3rcCreator(240));
        INVOKE_OBJECT_INIT_RANGE = opcode256;
        Opcode<Ins22cs> opcode257 = new Opcode<>(241, 4, "iget-char-quick", sectionType3, new Ins22csCreator(241));
        IGET_CHAR_QUICK = opcode257;
        Opcode<Ins22cs> opcode258 = new Opcode<>(242, 4, "iget-short-quick", sectionType3, new Ins22csCreator(242));
        IGET_SHORT_QUICK = opcode258;
        Opcode<Ins35ms> opcode259 = new Opcode<>(Const.CHOP_FRAME_MAX, 6, "invoke-super-quick", sectionType4, new Ins35msCreator(Const.CHOP_FRAME_MAX));
        INVOKE_SUPER_QUICK = opcode259;
        Opcode<Ins3rms> opcode260 = new Opcode<>(Const.SAME_FRAME_EXTENDED, 6, "invoke-super-quick/range", sectionType4, new Ins3rmsCreator(Const.SAME_FRAME_EXTENDED));
        INVOKE_SUPER_QUICK_RANGE = opcode260;
        Opcode<Ins22c> opcode261 = new Opcode<>(Const.APPEND_FRAME, 4, "iput-object-volatile", sectionType3, new Ins22cCreator(Const.APPEND_FRAME));
        IPUT_OBJECT_VOLATILE = opcode261;
        Opcode<Ins21c> opcode262 = new Opcode<>(253, 4, "sget-object-volatile", sectionType3, new Ins21cCreator(253));
        SGET_OBJECT_VOLATILE = opcode262;
        Opcode<Ins21c> opcode263 = new Opcode<>(254, 4, "sput-object-volatile", sectionType3, new Ins21cPutCreator(254));
        SPUT_OBJECT_VOLATILE = opcode263;
        Opcode<Ins22cs> opcode264 = new Opcode<>(240, 4, "iget-byte-quick", sectionType3, new Ins22csCreator(240));
        IGET_BYTE_QUICK = opcode264;
        for (int i = 0; i < 256; i++) {
            Opcode opcode265 = opcodeArr[i];
            if (opcode265 != null) {
                map.put(opcode265.name, opcode265);
            }
        }
        for (Opcode<?> opcode266 : PAYLOADS) {
            map.put(((Opcode) opcode266).name, opcode266);
        }
        for (Opcode<?> opcode267 : VALUES_2) {
            map.put(((Opcode) opcode267).name, opcode267);
        }
        for (Opcode<?> opcode268 : VALUES_3) {
            map.put(((Opcode) opcode268).name, opcode268);
        }
    }

    private Opcode(int i, int i2, String str, SectionType<? extends IdItem> sectionType, InsCreator<T> insCreator) {
        this.value = i;
        this.size = i2;
        this.name = str;
        this.sectionType = sectionType;
        this.creator = insCreator;
    }

    public static boolean isPrefix(byte b) {
        switch (b) {
            case 97:
            case 99:
            case 100:
            case 101:
            case 102:
            case 103:
            case 105:
            case 108:
            case 109:
            case 110:
            case 111:
            case 112:
            case 114:
            case 115:
            case 116:
            case 117:
            case 120:
                return true;
            case 98:
            case 104:
            case 106:
            case 107:
            case 113:
            case 118:
            case 119:
            default:
                return false;
        }
    }

    public static Opcode<?> parse(int i, String str) {
        int iIndexOf;
        int iSkipWhitespace = StringsUtil.skipWhitespace(i, str);
        if (iSkipWhitespace != str.length() && (iIndexOf = str.indexOf(32, iSkipWhitespace + 1)) >= iSkipWhitespace) {
            return valueOf(str.substring(iSkipWhitespace, iIndexOf));
        }
        return null;
    }

    public static Opcode<?> parseSmali(SmaliReader smaliReader, boolean z) {
        smaliReader.skipWhitespaces();
        if (!isPrefix(smaliReader.get())) {
            return null;
        }
        int iIndexOfWhiteSpaceOrComment = smaliReader.indexOfWhiteSpaceOrComment() - smaliReader.position();
        return valueOf(z ? smaliReader.readString(iIndexOfWhiteSpaceOrComment) : smaliReader.getString(iIndexOfWhiteSpaceOrComment));
    }

    public static Opcode<?> read(BlockReader blockReader) throws IOException {
        int i = blockReader.read();
        if (i == 0) {
            i = blockReader.read() << 8;
            blockReader.offset(-1);
        }
        blockReader.offset(-1);
        return valueOf(i);
    }

    public static Opcode<?> valueOf(int i) {
        if (i <= 255) {
            return VALUES[i];
        }
        for (Opcode<?> opcode : PAYLOADS) {
            if (i == ((Opcode) opcode).value) {
                return opcode;
            }
        }
        return null;
    }

    public static Iterator<Opcode<?>> values() {
        return CombiningIterator.four(new ArrayIterator(VALUES), new ArrayIterator(PAYLOADS), new ArrayIterator(VALUES_2), new ArrayIterator(VALUES_3));
    }

    @Override // com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        smaliWriter.append((CharSequence) getName());
        smaliWriter.append(' ');
    }

    public String getName() {
        return this.name;
    }

    public OperandType getOperandType() {
        return this.creator.getOperandType();
    }

    public RegisterFormat getRegisterFormat() {
        return this.creator.getRegisterFormat();
    }

    public SectionType<? extends IdItem> getSectionType() {
        return this.sectionType;
    }

    public SectionType<? extends IdItem> getSectionType2() {
        return this.creator.getSectionType2();
    }

    public int getValue() {
        return this.value;
    }

    public boolean hasOutRegisters() {
        SectionType<? extends IdItem> sectionType = getSectionType();
        return sectionType == SectionType.METHOD_ID || sectionType == SectionType.CALL_SITE_ID || this == FILLED_NEW_ARRAY || this == FILLED_NEW_ARRAY_RANGE;
    }

    public boolean isFieldAccess() {
        return getSectionType() == SectionType.FIELD_ID;
    }

    public boolean isFieldAccessInstance() {
        return getSectionType() == SectionType.FIELD_ID && getName().charAt(0) == 'i';
    }

    public boolean isFieldAccessStatic() {
        return getSectionType() == SectionType.FIELD_ID && getName().charAt(0) == 's';
    }

    public boolean isFieldGet() {
        return getSectionType() == SectionType.FIELD_ID && getName().charAt(1) == 'g';
    }

    public boolean isFieldInstanceGet() {
        if (getSectionType() != SectionType.FIELD_ID) {
            return false;
        }
        String name = getName();
        return name.charAt(0) == 'i' && name.charAt(1) == 'g';
    }

    public boolean isFieldInstancePut() {
        if (getSectionType() != SectionType.FIELD_ID) {
            return false;
        }
        String name = getName();
        return name.charAt(0) == 'i' && name.charAt(1) == 'p';
    }

    public boolean isFieldPut() {
        return getSectionType() == SectionType.FIELD_ID && getName().charAt(1) == 'p';
    }

    public boolean isFieldStaticGet() {
        if (getSectionType() != SectionType.FIELD_ID) {
            return false;
        }
        String name = getName();
        return name.charAt(0) == 's' && name.charAt(1) == 'g';
    }

    public boolean isFieldStaticPut() {
        if (getSectionType() != SectionType.FIELD_ID) {
            return false;
        }
        String name = getName();
        return name.charAt(0) == 's' && name.charAt(1) == 'p';
    }

    public boolean isMethodInvoke() {
        return getSectionType() == SectionType.METHOD_ID;
    }

    public boolean isMethodInvokeStatic() {
        return getSectionType() == SectionType.METHOD_ID && getName().charAt(8) == 't';
    }

    public boolean isMoveResultValue() {
        String name = getName();
        return name.length() >= 6 && name.charAt(0) == 'm' && name.charAt(5) == 'r';
    }

    public boolean isMover() {
        return this == MOVE || this == MOVE_16 || this == MOVE_FROM16 || this == MOVE_OBJECT || this == MOVE_OBJECT_16 || this == MOVE_OBJECT_FROM16 || this == MOVE_WIDE || this == MOVE_WIDE_16 || this == MOVE_WIDE_FROM16;
    }

    public boolean isReturning() {
        String name = getName();
        return name.charAt(0) == 'r' && name.charAt(2) == 't';
    }

    public T newInstance() {
        return this.creator.newInstance();
    }

    public int size() {
        return this.size;
    }

    public String toString() {
        return getName();
    }

    private Opcode(int i, int i2, String str, InsCreator<T> insCreator) {
        this(i, i2, str, null, insCreator);
    }

    public static Opcode<?> valueOf(String str) {
        return NAME_MAP.get(str);
    }

    public static Opcode<?> parse(String str) {
        return parse(0, str);
    }
}
