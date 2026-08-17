package org.jetbrains.kotlin.incremental.storage;

import com.intellij.util.containers.hash.EqualityPolicy;
import com.intellij.util.io.KeyDescriptor;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.metadata.jvm.deserialization.JvmProtoBufUtil;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\b\u0012\u0004\u0012\u00020\u00020\u0003B\u0011\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\nH\u0016J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0002H\u0016J\u0019\u0010\u0010\u001a\u00020\u00112\u000e\u0010\u0012\u001a\n \u0013*\u0004\u0018\u00010\u00020\u0002H\u0096\u0001J)\u0010\u0014\u001a\u00020\u00052\u000e\u0010\u0012\u001a\n \u0013*\u0004\u0018\u00010\u00020\u00022\u000e\u0010\u0015\u001a\n \u0013*\u0004\u0018\u00010\u00020\u0002H\u0096\u0001R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/incremental/storage/LookupSymbolKeyDescriptor;", "Lcom/intellij/util/io/KeyDescriptor;", "Lorg/jetbrains/kotlin/incremental/storage/LookupSymbolKey;", "Lcom/intellij/util/containers/hash/EqualityPolicy;", "storeFullFqNames", "", "<init>", "(Z)V", "read", "input", "Ljava/io/DataInput;", "save", "", "output", "Ljava/io/DataOutput;", "value", "getHashCode", "", "p0", JvmProtoBufUtil.PLATFORM_TYPE_ID, "isEqual", "p1", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class LookupSymbolKeyDescriptor implements EqualityPolicy<LookupSymbolKey>, KeyDescriptor<LookupSymbolKey> {
    private final /* synthetic */ DefaultEqualityPolicy<LookupSymbolKey> $$delegate_0;
    private final boolean storeFullFqNames;

    public LookupSymbolKeyDescriptor(boolean z) {
        this.$$delegate_0 = new DefaultEqualityPolicy<>();
        this.storeFullFqNames = z;
    }

    public int getHashCode(LookupSymbolKey p0) {
        return this.$$delegate_0.getHashCode(p0);
    }

    public LookupSymbolKey read(DataInput input) throws IOException {
        input.getClass();
        byte b = input.readByte();
        if (b == 0) {
            String utf = input.readUTF();
            String utf2 = input.readUTF();
            return new LookupSymbolKey(utf.hashCode(), utf2.hashCode(), utf, utf2);
        }
        if (b == 1) {
            return new LookupSymbolKey(input.readInt(), input.readInt(), "", "");
        }
        a3d.a("Unexpected byte value for storeFullFqNames: ", b);
        return null;
    }

    public void save(DataOutput output, LookupSymbolKey value) throws IOException {
        output.getClass();
        value.getClass();
        output.writeByte(!this.storeFullFqNames ? 1 : 0);
        if (this.storeFullFqNames) {
            output.writeUTF(value.getName());
            output.writeUTF(value.getScope());
        } else {
            output.writeInt(value.getNameHash());
            output.writeInt(value.getScopeHash());
        }
    }

    public boolean isEqual(LookupSymbolKey p0, LookupSymbolKey p1) {
        return this.$$delegate_0.isEqual(p0, p1);
    }

    public LookupSymbolKeyDescriptor() {
        this(false, 1, null);
    }

    public /* synthetic */ LookupSymbolKeyDescriptor(boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z);
    }
}
