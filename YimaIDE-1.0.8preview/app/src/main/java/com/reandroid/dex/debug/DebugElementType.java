package com.reandroid.dex.debug;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.base.BlockCreator;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.dex.debug.DebugAdvanceLine;
import com.reandroid.dex.debug.DebugAdvancePc;
import com.reandroid.dex.debug.DebugElement;
import com.reandroid.dex.debug.DebugEndLocal;
import com.reandroid.dex.debug.DebugEndSequence;
import com.reandroid.dex.debug.DebugEpilogue;
import com.reandroid.dex.debug.DebugLineNumber;
import com.reandroid.dex.debug.DebugPrologue;
import com.reandroid.dex.debug.DebugRestartLocal;
import com.reandroid.dex.debug.DebugSetSourceFile;
import com.reandroid.dex.debug.DebugStartLocal;
import com.reandroid.dex.debug.DebugStartLocalExtended;
import com.reandroid.dex.smali.SmaliDirective;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DebugElementType<T extends DebugElement> implements BlockCreator<T> {
    public static final DebugElementType<DebugAdvanceLine> ADVANCE_LINE;
    public static final DebugElementType<DebugAdvancePc> ADVANCE_PC;
    public static final DebugElementType<DebugEndLocal> END_LOCAL;
    public static final DebugElementType<DebugEndSequence> END_SEQUENCE;
    public static final DebugElementType<DebugEpilogue> EPILOGUE;
    public static final DebugElementType<DebugLineNumber> LINE_NUMBER;
    public static final DebugElementType<DebugPrologue> PROLOGUE;
    public static final DebugElementType<DebugRestartLocal> RESTART_LOCAL;
    public static final DebugElementType<DebugSetSourceFile> SET_SOURCE_FILE;
    public static final DebugElementType<DebugStartLocal> START_LOCAL;
    public static final DebugElementType<DebugStartLocalExtended> START_LOCAL_EXTENDED;
    public static final DebugElementType<?>[] VALUES;
    private final BlockCreator<T> creator;
    private final SmaliDirective directive;
    private final int flag;
    private final String name;

    static {
        VALUES = new DebugElementType[]{debugElementType, debugElementType, debugElementType, debugElementType, debugElementType, debugElementType, debugElementType, debugElementType, debugElementType, debugElementType, debugElementType};
        DebugElementType<DebugEndSequence> debugElementType = new DebugElementType<>("END_SEQUENCE", 0, new BlockCreator() { // from class: lb3
            public final Block newInstance() {
                return DebugEndSequence.INSTANCE;
            }
        });
        END_SEQUENCE = debugElementType;
        DebugElementType<DebugAdvancePc> debugElementType2 = new DebugElementType<>("ADVANCE_PC", 1, new BlockCreator() { // from class: ob3
            public final Block newInstance() {
                return new DebugAdvancePc();
            }
        });
        ADVANCE_PC = debugElementType2;
        DebugElementType<DebugAdvanceLine> debugElementType3 = new DebugElementType<>("ADVANCE_LINE", 2, new BlockCreator() { // from class: pb3
            public final Block newInstance() {
                return new DebugAdvanceLine();
            }
        });
        ADVANCE_LINE = debugElementType3;
        SmaliDirective smaliDirective = SmaliDirective.LOCAL;
        DebugElementType<DebugStartLocal> debugElementType4 = new DebugElementType<>("START_LOCAL", smaliDirective, 3, new BlockCreator() { // from class: qb3
            public final Block newInstance() {
                return new DebugStartLocal();
            }
        });
        START_LOCAL = debugElementType4;
        DebugElementType<DebugStartLocalExtended> debugElementType5 = new DebugElementType<>("START_LOCAL_EXTENDED", smaliDirective, 4, new BlockCreator() { // from class: rb3
            public final Block newInstance() {
                return new DebugStartLocalExtended();
            }
        });
        START_LOCAL_EXTENDED = debugElementType5;
        DebugElementType<DebugEndLocal> debugElementType6 = new DebugElementType<>("END_LOCAL", SmaliDirective.END_LOCAL, 5, new BlockCreator() { // from class: sb3
            public final Block newInstance() {
                return new DebugEndLocal();
            }
        });
        END_LOCAL = debugElementType6;
        DebugElementType<DebugRestartLocal> debugElementType7 = new DebugElementType<>("RESTART_LOCAL", SmaliDirective.RESTART_LOCAL, 6, new BlockCreator() { // from class: tb3
            public final Block newInstance() {
                return new DebugRestartLocal();
            }
        });
        RESTART_LOCAL = debugElementType7;
        DebugElementType<DebugPrologue> debugElementType8 = new DebugElementType<>("PROLOGUE", SmaliDirective.PROLOGUE, 7, new BlockCreator() { // from class: ub3
            public final Block newInstance() {
                return new DebugPrologue();
            }
        });
        PROLOGUE = debugElementType8;
        DebugElementType<DebugEpilogue> debugElementType9 = new DebugElementType<>("EPILOGUE", SmaliDirective.EPILOGUE, 8, new BlockCreator() { // from class: vb3
            public final Block newInstance() {
                return new DebugEpilogue();
            }
        });
        EPILOGUE = debugElementType9;
        DebugElementType<DebugSetSourceFile> debugElementType10 = new DebugElementType<>("SET_SOURCE_FILE", SmaliDirective.SET_SOURCE_FILE, 9, new BlockCreator() { // from class: mb3
            public final Block newInstance() {
                return new DebugSetSourceFile();
            }
        });
        SET_SOURCE_FILE = debugElementType10;
        DebugElementType<DebugLineNumber> debugElementType11 = new DebugElementType<>("LINE_NUMBER", SmaliDirective.LINE, 10, new BlockCreator() { // from class: nb3
            public final Block newInstance() {
                return new DebugLineNumber();
            }
        });
        LINE_NUMBER = debugElementType11;
    }

    private DebugElementType(String str, SmaliDirective smaliDirective, int i, BlockCreator<T> blockCreator) {
        this.name = str;
        this.directive = smaliDirective;
        this.flag = i;
        this.creator = blockCreator;
    }

    public static DebugElementType<?> fromFlag(int i) {
        int i2 = i & 255;
        if (i2 > 10) {
            i2 = 10;
        }
        return VALUES[i2];
    }

    public static DebugElementType<?> readFlag(BlockReader blockReader) throws IOException {
        int i = blockReader.read();
        blockReader.offset(-1);
        return fromFlag(i);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.flag == ((DebugElementType) obj).flag;
    }

    public int getFlag() {
        return this.flag;
    }

    public String getName() {
        return this.name;
    }

    public SmaliDirective getSmaliDirective() {
        return this.directive;
    }

    public int hashCode() {
        return this.flag;
    }

    public boolean is(DebugElementType<?> debugElementType) {
        return debugElementType == this;
    }

    public T newInstance() {
        return this.creator.newInstance();
    }

    public String toString() {
        SmaliDirective smaliDirective = this.directive;
        return smaliDirective != null ? smaliDirective.toString() : this.name;
    }

    private DebugElementType(String str, int i, BlockCreator<T> blockCreator) {
        this(str, null, i, blockCreator);
    }
}
