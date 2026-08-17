package com.intellij.psi.stubs;

import com.intellij.util.io.AbstractStringEnumerator;
import com.intellij.util.io.DataInputOutputUtil;
import com.intellij.util.io.DataOutputStream;
import com.intellij.util.io.IOUtil;
import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
public class StubOutputStream extends DataOutputStream {
    private final AbstractStringEnumerator myNameStorage;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        Object[] objArr = new Object[3];
        if (i == 1) {
            objArr[0] = "nameStorage";
        } else if (i != 2) {
            objArr[0] = "out";
        } else {
            objArr[0] = "arg";
        }
        objArr[1] = "com/intellij/psi/stubs/StubOutputStream";
        if (i != 2) {
            objArr[2] = "<init>";
        } else {
            objArr[2] = "writeUTFFast";
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StubOutputStream(OutputStream outputStream, AbstractStringEnumerator abstractStringEnumerator) {
        super(outputStream);
        if (outputStream == null) {
            $$$reportNull$$$0(0);
        }
        if (abstractStringEnumerator == null) {
            $$$reportNull$$$0(1);
        }
        this.myNameStorage = abstractStringEnumerator;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void writeName(String str) throws IOException {
        DataInputOutputUtil.writeINT(this, str != null ? this.myNameStorage.enumerate(str) : 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void writeUTFFast(String str) throws IOException {
        if (str == null) {
            $$$reportNull$$$0(2);
        }
        IOUtil.writeUTF(this, str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void writeVarInt(int i) throws IOException {
        DataInputOutputUtil.writeINT(this, i);
    }
}
