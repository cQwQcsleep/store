package com.android.tools.r8.dex;

import com.android.tools.r8.ProgramResource;
import com.android.tools.r8.internal.C0613Ke;
import com.android.tools.r8.internal.C3043xe0;
import com.android.tools.r8.internal.EnumC1095am;
import com.android.tools.r8.internal.Wf0;
import com.android.tools.r8.origin.Origin;
import defpackage.n33;
import java.nio.BufferUnderflowException;
import java.nio.ByteOrder;
import java.util.Optional;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class D extends AbstractC0150m {
    public final EnumC1095am d;

    public D(ProgramResource programResource) {
        super(programResource.getOrigin(), C3043xe0.a(programResource.getByteStream()));
        this.d = a(this.b, 0);
    }

    public final EnumC1095am a(C0155s c0155s, int i) {
        try {
            c0155s.e();
            c0155s.o();
            byte[] bArr = C0156t.a;
            int length = bArr.length;
            int i2 = 0;
            while (i2 < length) {
                byte b = bArr[i2];
                int i3 = i + 1;
                byte bA = c0155s.a(i);
                if (bA != b) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Dex file has invalid header, expected " + ((int) b) + " got " + ((int) bA) + ". Next bytes are ");
                    for (int i4 = 0; i4 < 10; i4++) {
                        if (c0155s.l()) {
                            sb.append(Wf0.a((int) c0155s.e(), 2));
                            sb.append(",");
                        }
                    }
                    throw new C0613Ke(sb.toString(), this.a);
                }
                i2++;
                i = i3;
            }
            char cA = (char) c0155s.a(i);
            int i5 = i + 2;
            char cA2 = (char) c0155s.a(i + 1);
            int i6 = i + 3;
            char cA3 = (char) c0155s.a(i5);
            Optional<EnumC1095am> optionalA = EnumC1095am.a(cA, cA2, cA3);
            if (optionalA.isPresent()) {
                if (c0155s.a(i6) == 0) {
                    return optionalA.get();
                }
                throw new C0613Ke("Dex file has invalid header", this.a);
            }
            throw new C0613Ke("Unsupported DEX file version: " + cA + cA2 + cA3, this.a);
        } catch (BufferUnderflowException unused) {
            throw new C0613Ke("Dex file is empty", this.a);
        }
    }

    public final EnumC1095am i() {
        return this.d;
    }

    public final void j() {
        this.b.a(ByteOrder.LITTLE_ENDIAN);
        int iB = this.b.b(40);
        if (iB == 2018915346) {
            this.b.a(ByteOrder.BIG_ENDIAN);
        } else {
            if (iB == 305419896) {
                return;
            }
            n33.a("Unable to determine endianess for reading dex file.");
        }
    }

    public D(Origin origin, byte[] bArr) {
        super(origin, bArr);
        this.d = a(this.b, 0);
    }

    public D(Origin origin, byte[] bArr, int i) {
        super(origin, bArr);
        this.d = a(this.b, i);
    }
}
