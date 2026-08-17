package org.bouncycastle.pqc.legacy.crypto.ntru;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.bouncycastle.pqc.legacy.math.ntru.polynomial.IntegerPolynomial;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class NTRUEncryptionPublicKeyParameters extends NTRUEncryptionKeyParameters {
    public IntegerPolynomial h;

    public NTRUEncryptionPublicKeyParameters(InputStream inputStream, NTRUEncryptionParameters nTRUEncryptionParameters) throws IOException {
        super(false, nTRUEncryptionParameters);
        this.h = IntegerPolynomial.fromBinary(inputStream, nTRUEncryptionParameters.N, nTRUEncryptionParameters.q);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof NTRUEncryptionPublicKeyParameters)) {
            return false;
        }
        NTRUEncryptionPublicKeyParameters nTRUEncryptionPublicKeyParameters = (NTRUEncryptionPublicKeyParameters) obj;
        IntegerPolynomial integerPolynomial = this.h;
        IntegerPolynomial integerPolynomial2 = nTRUEncryptionPublicKeyParameters.h;
        if (integerPolynomial == null) {
            if (integerPolynomial2 != null) {
                return false;
            }
        } else if (!integerPolynomial.equals(integerPolynomial2)) {
            return false;
        }
        NTRUEncryptionParameters nTRUEncryptionParameters = this.params;
        NTRUEncryptionParameters nTRUEncryptionParameters2 = nTRUEncryptionPublicKeyParameters.params;
        if (nTRUEncryptionParameters == null) {
            if (nTRUEncryptionParameters2 != null) {
                return false;
            }
        } else if (!nTRUEncryptionParameters.equals(nTRUEncryptionParameters2)) {
            return false;
        }
        return true;
    }

    public byte[] getEncoded() {
        return this.h.toBinary(this.params.q);
    }

    public int hashCode() {
        IntegerPolynomial integerPolynomial = this.h;
        int iHashCode = ((integerPolynomial == null ? 0 : integerPolynomial.hashCode()) + 31) * 31;
        NTRUEncryptionParameters nTRUEncryptionParameters = this.params;
        return iHashCode + (nTRUEncryptionParameters != null ? nTRUEncryptionParameters.hashCode() : 0);
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        outputStream.write(getEncoded());
    }

    public NTRUEncryptionPublicKeyParameters(IntegerPolynomial integerPolynomial, NTRUEncryptionParameters nTRUEncryptionParameters) {
        super(false, nTRUEncryptionParameters);
        this.h = integerPolynomial;
    }

    public NTRUEncryptionPublicKeyParameters(byte[] bArr, NTRUEncryptionParameters nTRUEncryptionParameters) {
        super(false, nTRUEncryptionParameters);
        this.h = IntegerPolynomial.fromBinary(bArr, nTRUEncryptionParameters.N, nTRUEncryptionParameters.q);
    }
}
