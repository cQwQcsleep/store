package com.intellij.psi.stubs;

import com.intellij.util.io.AbstractStringEnumerator;
import com.intellij.util.io.DataInputOutputUtil;
import com.intellij.util.io.IOUtil;
import com.intellij.util.io.StringRef;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
public class StubInputStream extends DataInputStream {
    private final AbstractStringEnumerator myNameStorage;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = i != 2 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
        Object[] objArr = new Object[i != 2 ? 3 : 2];
        if (i == 1) {
            objArr[0] = "nameStorage";
        } else if (i != 2) {
            objArr[0] = "in";
        } else {
            objArr[0] = "com/intellij/psi/stubs/StubInputStream";
        }
        if (i != 2) {
            objArr[1] = "com/intellij/psi/stubs/StubInputStream";
        } else {
            objArr[1] = "readUTFFast";
        }
        if (i != 2) {
            objArr[2] = "<init>";
        }
        String str2 = String.format(str, objArr);
        if (i == 2) {
            throw new IllegalStateException(str2);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StubInputStream(InputStream inputStream, AbstractStringEnumerator abstractStringEnumerator) {
        super(inputStream);
        if (inputStream == null) {
            $$$reportNull$$$0(0);
        }
        if (abstractStringEnumerator == null) {
            $$$reportNull$$$0(1);
        }
        this.myNameStorage = abstractStringEnumerator;
    }

    public StringRef readName() throws IOException {
        return StringRef.fromStream(this, this.myNameStorage);
    }

    public String readNameString() throws IOException {
        return StringRef.stringFromStream(this, this.myNameStorage);
    }

    public String readUTFFast() throws IOException {
        String utf = IOUtil.readUTF(this);
        if (utf == null) {
            $$$reportNull$$$0(2);
        }
        return utf;
    }

    public int readVarInt() throws IOException {
        return DataInputOutputUtil.readINT(this);
    }
}
