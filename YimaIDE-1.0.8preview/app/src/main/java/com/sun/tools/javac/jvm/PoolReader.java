package com.sun.tools.javac.jvm;

import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.Symtab;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.resources.CompilerProperties;
import com.sun.tools.javac.util.ByteBuffer;
import com.sun.tools.javac.util.Convert;
import com.sun.tools.javac.util.InvalidUtfException;
import com.sun.tools.javac.util.Name;
import com.sun.tools.javac.util.Names;
import defpackage.ww1;
import java.util.BitSet;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class PoolReader {
    private static final BitSet classCP;
    private static final BitSet constantCP;
    private static final BitSet moduleCP;
    private static final BitSet nameAndTypeCP;
    private static final BitSet packageCP;
    private static final BitSet utf8CP;
    private final ByteBuffer buf;
    private final Names names;
    private ImmutablePoolHelper pool;
    private final ClassReader reader;
    private final Symtab syms;
    private final Convert.Validation utf8validation;

    public class ImmutablePoolHelper {
        final int[] offsets;
        final ByteBuffer poolbuf;
        final Object[] values;

        public ImmutablePoolHelper(ByteBuffer byteBuffer, int[] iArr) {
            this.offsets = iArr;
            this.values = new Object[iArr.length];
            this.poolbuf = byteBuffer;
        }

        private int checkIndex(int i) {
            if (i <= 0 || i >= this.offsets.length) {
                throw PoolReader.this.reader.badClassFile("bad.const.pool.index", PoolReader.this.reader.currentClassFile, Integer.valueOf(i), Integer.valueOf(this.offsets.length));
            }
            return i;
        }

        public int offset(int i) {
            return this.offsets[checkIndex(i)];
        }

        public <P> P readIfNeeded(int i, BitSet bitSet) {
            P p = (P) this.values[checkIndex(i)];
            if (p != null) {
                return p;
            }
            int iTag = tag(i);
            boolean z = bitSet.get(iTag);
            PoolReader poolReader = PoolReader.this;
            if (!z) {
                throw poolReader.reader.badClassFile("unexpected.const.pool.tag.at", Integer.valueOf(tag(i)), Integer.valueOf(offset(i)));
            }
            try {
                P p2 = (P) poolReader.resolve(this.poolbuf, iTag, offset(i));
                this.values[i] = p2;
                return p2;
            } catch (ByteBuffer.UnderflowException e) {
                throw PoolReader.this.reader.badClassFile(CompilerProperties.Fragments.BadClassTruncatedAtOffset(e.getLength()));
            } catch (InvalidUtfException e2) {
                throw PoolReader.this.reader.badClassFile(CompilerProperties.Fragments.BadUtf8ByteSequenceAt(e2.getOffset()));
            }
        }

        public int tag(int i) {
            return this.poolbuf.elems[offset(i) - 1];
        }
    }

    public interface Utf8Mapper<X> {
        X map(byte[] bArr, int i, int i2) throws InvalidUtfException;
    }

    static {
        BitSet bitSet = new BitSet();
        classCP = bitSet;
        BitSet bitSet2 = new BitSet();
        constantCP = bitSet2;
        BitSet bitSet3 = new BitSet();
        moduleCP = bitSet3;
        BitSet bitSet4 = new BitSet();
        packageCP = bitSet4;
        BitSet bitSet5 = new BitSet();
        utf8CP = bitSet5;
        BitSet bitSet6 = new BitSet();
        nameAndTypeCP = bitSet6;
        bitSet.set(7);
        bitSet2.set(3, 9);
        bitSet3.set(19);
        bitSet4.set(20);
        bitSet5.set(1);
        bitSet6.set(12);
    }

    public PoolReader(ClassReader classReader, ByteBuffer byteBuffer, Names names, Symtab symtab) {
        this.reader = classReader;
        this.buf = byteBuffer;
        this.names = names;
        this.syms = symtab;
        this.utf8validation = classReader != null ? classReader.utf8validation : Convert.Validation.NONE;
    }

    private <Z> Z getUtf8(int i, Utf8Mapper<Z> utf8Mapper) throws ByteBuffer.UnderflowException, InvalidUtfException {
        int iTag = this.pool.tag(i);
        int iOffset = this.pool.offset(i);
        if (iTag != 1) {
            throw this.reader.badClassFile("unexpected.const.pool.tag.at", Integer.toString(iTag), Integer.toString(iOffset - 1));
        }
        char c = this.pool.poolbuf.getChar(iOffset);
        int i2 = iOffset + 2;
        this.pool.poolbuf.verifyRange(i2, c);
        return utf8Mapper.map(this.pool.poolbuf.elems, i2, c);
    }

    private <Z> Z peekItemName(int i, Utf8Mapper<Z> utf8Mapper) {
        try {
            return (Z) peekName(this.buf.getChar(this.pool.offset(i)), utf8Mapper);
        } catch (ByteBuffer.UnderflowException e) {
            throw this.reader.badClassFile(CompilerProperties.Fragments.BadClassTruncatedAtOffset(e.getLength()));
        }
    }

    private int readPoolInternal(ByteBuffer byteBuffer, int i) throws ByteBuffer.UnderflowException {
        int i2 = byteBuffer.getChar(i);
        int i3 = i + 2;
        int[] iArr = new int[i2];
        int iSizeof = 1;
        while (iSizeof < i2) {
            int i4 = i3 + 1;
            byte b = byteBuffer.getByte(i3);
            iArr[iSizeof] = i4;
            switch (b) {
                case 1:
                    i3 = i4 + byteBuffer.getChar(i4) + 2;
                    break;
                case 2:
                case 13:
                case 14:
                default:
                    throw this.reader.badClassFile("bad.const.pool.tag.at", Byte.toString(b), Integer.toString(i3));
                case 3:
                case 4:
                case 9:
                case 10:
                case 11:
                case 12:
                case 17:
                case 18:
                    i3 += 5;
                    break;
                case 5:
                case 6:
                    i3 += 9;
                    break;
                case 7:
                case 8:
                case 16:
                case 19:
                case 20:
                    i3 += 3;
                    break;
                case 15:
                    i3 += 4;
                    break;
            }
            iSizeof += sizeof(b);
        }
        this.pool = new ImmutablePoolHelper(byteBuffer, iArr);
        return i3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Object resolve(ByteBuffer byteBuffer, int i, int i2) throws ByteBuffer.UnderflowException, InvalidUtfException {
        if (i == 1) {
            char c = byteBuffer.getChar(i2);
            try {
                return this.names.fromUtf(byteBuffer.elems, i2 + 2, c, this.utf8validation);
            } catch (InvalidUtfException e) {
                ClassReader classReader = this.reader;
                if (classReader != null && !classReader.warnOnIllegalUtf8) {
                    throw e;
                }
                if (classReader != null) {
                    classReader.log.warning(CompilerProperties.Warnings.InvalidUtf8InClassfile(classReader.currentClassFile, CompilerProperties.Fragments.BadUtf8ByteSequenceAt(e.getOffset())));
                }
                return this.names.fromUtfLax(byteBuffer.elems, i2 + 2, c);
            }
        }
        if (i == 12) {
            return new PoolConstant.NameAndType(getName(byteBuffer.getChar(i2)), getType(byteBuffer.getChar(i2 + 2)));
        }
        if (i == 19) {
            return this.syms.enterModule(getName(byteBuffer.getChar(i2)));
        }
        if (i == 20) {
            return this.syms.enterPackage(this.reader.currentModule, ClassFile.internalize(getName(byteBuffer.getChar(i2))));
        }
        switch (i) {
            case 3:
                return Integer.valueOf(byteBuffer.getInt(i2));
            case 4:
                return Float.valueOf(byteBuffer.getFloat(i2));
            case 5:
                return Long.valueOf(byteBuffer.getLong(i2));
            case 6:
                return Double.valueOf(byteBuffer.getDouble(i2));
            case 7:
                return this.syms.enterClass(this.reader.currentModule, ClassFile.internalize(getName(byteBuffer.getChar(i2))));
            case 8:
                return getName(byteBuffer.getChar(i2)).toString();
            default:
                throw this.reader.badClassFile("unexpected.const.pool.tag.at", Integer.toString(i), Integer.toString(i2 - 1));
        }
    }

    private int sizeof(int i) {
        return (i == 5 || i == 6) ? 2 : 1;
    }

    public Symbol.ClassSymbol getClass(int i) {
        return (Symbol.ClassSymbol) this.pool.readIfNeeded(i, classCP);
    }

    public Object getConstant(int i) {
        return this.pool.readIfNeeded(i, constantCP);
    }

    public Symbol.ModuleSymbol getModule(int i) {
        return (Symbol.ModuleSymbol) this.pool.readIfNeeded(i, moduleCP);
    }

    public Name getName(int i) {
        return (Name) this.pool.readIfNeeded(i, utf8CP);
    }

    public PoolConstant.NameAndType getNameAndType(int i) {
        return (PoolConstant.NameAndType) this.pool.readIfNeeded(i, nameAndTypeCP);
    }

    public Symbol.PackageSymbol getPackage(int i) {
        return (Symbol.PackageSymbol) this.pool.readIfNeeded(i, packageCP);
    }

    public Type getType(int i) {
        Name name = getName(i);
        ClassReader classReader = this.reader;
        Objects.requireNonNull(classReader);
        return (Type) name.map(new ww1(classReader));
    }

    public boolean hasTag(int i, int i2) {
        return this.pool.tag(i) == i2;
    }

    public <Z> Z peekClassName(int i, Utf8Mapper<Z> utf8Mapper) {
        return (Z) peekItemName(i, utf8Mapper);
    }

    public <Z> Z peekModuleName(int i, Utf8Mapper<Z> utf8Mapper) {
        return (Z) peekItemName(i, utf8Mapper);
    }

    public <Z> Z peekName(int i, Utf8Mapper<Z> utf8Mapper) {
        try {
            return (Z) getUtf8(i, utf8Mapper);
        } catch (ByteBuffer.UnderflowException e) {
            throw this.reader.badClassFile(CompilerProperties.Fragments.BadClassTruncatedAtOffset(e.getLength()));
        } catch (InvalidUtfException e2) {
            throw this.reader.badClassFile(CompilerProperties.Fragments.BadUtf8ByteSequenceAt(e2.getOffset()));
        }
    }

    public <Z> Z peekPackageName(int i, Utf8Mapper<Z> utf8Mapper) {
        return (Z) peekItemName(i, utf8Mapper);
    }

    public int readPool(ByteBuffer byteBuffer, int i) {
        try {
            return readPoolInternal(byteBuffer, i);
        } catch (ByteBuffer.UnderflowException e) {
            throw this.reader.badClassFile(CompilerProperties.Fragments.BadClassTruncatedAtOffset(e.getLength()));
        }
    }

    public PoolReader(ClassReader classReader, Names names, Symtab symtab) {
        this(classReader, classReader.buf, names, symtab);
    }

    public PoolReader(ByteBuffer byteBuffer) {
        this(null, byteBuffer, null, null);
    }
}
