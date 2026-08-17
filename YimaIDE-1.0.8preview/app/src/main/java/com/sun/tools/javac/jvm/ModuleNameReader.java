package com.sun.tools.javac.jvm;

import com.sun.tools.classfile.Attribute;
import com.sun.tools.javac.jvm.ModuleNameReader;
import com.sun.tools.javac.util.ByteBuffer;
import com.sun.tools.javac.util.Convert;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import javax.tools.JavaFileObject;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ModuleNameReader {
    private static final int INITIAL_BUFFER_SIZE = 65520;
    private int bp;
    private ByteBuffer buf = new ByteBuffer(65520);
    private PoolReader reader;

    public static class BadClassFile extends Exception {
        private static final long serialVersionUID = 0;

        public BadClassFile(String str) {
            super(str);
        }
    }

    public static /* synthetic */ String b(byte[] bArr, int i, int i2) {
        byte[] bArrInternalize = ClassFile.internalize(bArr, i, i2);
        return Convert.utf2string(bArrInternalize, 0, bArrInternalize.length, Convert.Validation.STRICT);
    }

    public void checkZero(int i, String str) throws BadClassFile {
        if (i == 0) {
            return;
        }
        throw new BadClassFile("invalid " + str + " for module: " + i);
    }

    public char nextChar() throws BadClassFile {
        try {
            char c = this.buf.getChar(this.bp);
            this.bp += 2;
            return c;
        } catch (ByteBuffer.UnderflowException e) {
            throw new BadClassFile("class file truncated at offset " + e.getLength());
        }
    }

    public int nextInt() throws BadClassFile {
        try {
            int i = this.buf.getInt(this.bp);
            this.bp += 4;
            return i;
        } catch (ByteBuffer.UnderflowException e) {
            throw new BadClassFile("class file truncated at offset " + e.getLength());
        }
    }

    public String readModuleName(InputStream inputStream) throws BadClassFile, IOException {
        this.bp = 0;
        this.buf.reset();
        this.buf.appendStream(inputStream);
        if (nextInt() != -889275714) {
            throw new BadClassFile("illegal.start.of.class.file");
        }
        nextChar();
        char cNextChar = nextChar();
        if (cNextChar < '5') {
            throw new BadClassFile("bad major version number for module: " + ((int) cNextChar));
        }
        PoolReader poolReader = new PoolReader(this.buf);
        this.reader = poolReader;
        this.bp = poolReader.readPool(this.buf, this.bp);
        char cNextChar2 = nextChar();
        if (cNextChar2 != 32768) {
            throw new BadClassFile("invalid access flags for module: 0x" + Integer.toHexString(cNextChar2));
        }
        nextChar();
        checkZero(nextChar(), "super_class");
        checkZero(nextChar(), "interface_count");
        checkZero(nextChar(), "fields_count");
        checkZero(nextChar(), "methods_count");
        char cNextChar3 = nextChar();
        for (int i = 0; i < cNextChar3; i++) {
            char cNextChar4 = nextChar();
            int iNextInt = nextInt();
            if (((String) this.reader.peekName(cNextChar4, utf8Mapper(false))).equals(Attribute.Module) && iNextInt > 2) {
                return (String) this.reader.peekModuleName(nextChar(), utf8Mapper(true));
            }
            this.bp += iNextInt;
        }
        throw new BadClassFile("no Module attribute");
    }

    public PoolReader.Utf8Mapper<String> utf8Mapper(boolean z) {
        return z ? new PoolReader.Utf8Mapper() { // from class: o5a
            @Override // com.sun.tools.javac.jvm.PoolReader.Utf8Mapper
            public final Object map(byte[] bArr, int i, int i2) {
                return ModuleNameReader.b(bArr, i, i2);
            }
        } : new PoolReader.Utf8Mapper() { // from class: p5a
            @Override // com.sun.tools.javac.jvm.PoolReader.Utf8Mapper
            public final Object map(byte[] bArr, int i, int i2) {
                return Convert.utf2string(bArr, i, i2, Convert.Validation.STRICT);
            }
        };
    }

    public String readModuleName(JavaFileObject javaFileObject) throws BadClassFile, IOException {
        InputStream inputStreamOpenInputStream = javaFileObject.openInputStream();
        try {
            String moduleName = readModuleName(inputStreamOpenInputStream);
            if (inputStreamOpenInputStream != null) {
                inputStreamOpenInputStream.close();
            }
            return moduleName;
        } catch (Throwable th) {
            if (inputStreamOpenInputStream != null) {
                try {
                    inputStreamOpenInputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public String readModuleName(Path path) throws BadClassFile, IOException {
        InputStream inputStreamNewInputStream = Files.newInputStream(path, new OpenOption[0]);
        try {
            String moduleName = readModuleName(inputStreamNewInputStream);
            if (inputStreamNewInputStream != null) {
                inputStreamNewInputStream.close();
            }
            return moduleName;
        } catch (Throwable th) {
            if (inputStreamNewInputStream != null) {
                try {
                    inputStreamNewInputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }
}
