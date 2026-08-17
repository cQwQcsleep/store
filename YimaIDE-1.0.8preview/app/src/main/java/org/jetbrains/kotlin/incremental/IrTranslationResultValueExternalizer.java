package org.jetbrains.kotlin.incremental;

import com.intellij.util.io.DataExternalizer;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import kotlin.Metadata;
import org.jetbrains.kotlin.incremental.js.IrTranslationResultValue;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bÂ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0002H\u0016J\u0014\u0010\n\u001a\u00020\u0006*\u00020\b2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0016\u0010\r\u001a\u00020\u0006*\u00020\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0002J\f\u0010\u000e\u001a\u00020\f*\u00020\u000fH\u0002J\u000e\u0010\u0010\u001a\u0004\u0018\u00010\f*\u00020\u000fH\u0002J\u0010\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u000fH\u0016¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/incremental/IrTranslationResultValueExternalizer;", "Lcom/intellij/util/io/DataExternalizer;", "Lorg/jetbrains/kotlin/incremental/js/IrTranslationResultValue;", "<init>", "()V", "save", "", "output", "Ljava/io/DataOutput;", "value", "writeArray", "array", "", "writeOptionalArray", "readArray", "Ljava/io/DataInput;", "readOptionalArray", "read", "input", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
final class IrTranslationResultValueExternalizer implements DataExternalizer<IrTranslationResultValue> {
    public static final IrTranslationResultValueExternalizer INSTANCE = new IrTranslationResultValueExternalizer();

    private IrTranslationResultValueExternalizer() {
    }

    private final byte[] readArray(DataInput dataInput) throws IOException {
        byte[] bArr = new byte[dataInput.readInt()];
        dataInput.readFully(bArr);
        return bArr;
    }

    private final byte[] readOptionalArray(DataInput dataInput) throws IOException {
        int i = dataInput.readInt();
        if (i == -1) {
            return null;
        }
        byte[] bArr = new byte[i];
        dataInput.readFully(bArr);
        return bArr;
    }

    private final void writeArray(DataOutput dataOutput, byte[] bArr) throws IOException {
        dataOutput.writeInt(bArr.length);
        dataOutput.write(bArr);
    }

    private final void writeOptionalArray(DataOutput dataOutput, byte[] bArr) throws IOException {
        if (bArr != null) {
            writeArray(dataOutput, bArr);
        } else {
            dataOutput.writeInt(-1);
        }
    }

    public IrTranslationResultValue read(DataInput input) {
        input.getClass();
        return new IrTranslationResultValue(readArray(input), readArray(input), readArray(input), readArray(input), readArray(input), readArray(input), readArray(input), readArray(input), readOptionalArray(input), readOptionalArray(input));
    }

    public void save(DataOutput output, IrTranslationResultValue value) throws IOException {
        output.getClass();
        value.getClass();
        writeArray(output, value.getFileData());
        writeArray(output, value.getTypes());
        writeArray(output, value.getSignatures());
        writeArray(output, value.getStrings());
        writeArray(output, value.getDeclarations());
        writeArray(output, value.getBodies());
        writeArray(output, value.getFqn());
        writeArray(output, value.getFileMetadata());
        writeOptionalArray(output, value.getDebugInfo());
        writeOptionalArray(output, value.getFileEntries());
    }
}
