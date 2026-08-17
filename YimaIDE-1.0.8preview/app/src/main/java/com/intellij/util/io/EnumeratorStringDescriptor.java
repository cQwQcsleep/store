package com.intellij.util.io;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public class EnumeratorStringDescriptor implements KeyDescriptor<String> {
    public static final EnumeratorStringDescriptor INSTANCE = new EnumeratorStringDescriptorImpl();

    public static final class EnumeratorStringDescriptorImpl extends EnumeratorStringDescriptor implements DifferentSerializableBytesImplyNonEqualityPolicy {
        private EnumeratorStringDescriptorImpl() {
        }

        @Override // com.intellij.util.io.EnumeratorStringDescriptor, com.intellij.util.io.KeyDescriptor, com.intellij.util.containers.hash.EqualityPolicy
        public /* bridge */ /* synthetic */ int getHashCode(Object obj) {
            return super.getHashCode((String) obj);
        }

        @Override // com.intellij.util.io.EnumeratorStringDescriptor, com.intellij.util.containers.hash.EqualityPolicy
        public /* bridge */ /* synthetic */ boolean isEqual(Object obj, Object obj2) {
            return super.isEqual((String) obj, (String) obj2);
        }

        @Override // com.intellij.util.io.EnumeratorStringDescriptor, com.intellij.util.io.DataExternalizer
        public /* bridge */ /* synthetic */ Object read(DataInput dataInput) throws IOException {
            return super.read(dataInput);
        }

        @Override // com.intellij.util.io.EnumeratorStringDescriptor, com.intellij.util.io.KeyDescriptor, com.intellij.util.io.DataExternalizer
        public /* bridge */ /* synthetic */ void save(DataOutput dataOutput, Object obj) throws IOException {
            super.save(dataOutput, (String) obj);
        }
    }

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        Object[] objArr = new Object[3];
        if (i != 1) {
            objArr[0] = "storage";
        } else {
            objArr[0] = "value";
        }
        objArr[1] = "com/intellij/util/io/EnumeratorStringDescriptor";
        if (i != 2) {
            objArr[2] = "save";
        } else {
            objArr[2] = "read";
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    @Override // com.intellij.util.io.DataExternalizer
    public String read(DataInput dataInput) throws IOException {
        if (dataInput == null) {
            $$$reportNull$$$0(2);
        }
        return IOUtil.readUTF(dataInput);
    }

    @Override // com.intellij.util.io.KeyDescriptor, com.intellij.util.io.DataExternalizer
    public void save(DataOutput dataOutput, String str) throws IOException {
        if (dataOutput == null) {
            $$$reportNull$$$0(0);
        }
        if (str == null) {
            $$$reportNull$$$0(1);
        }
        IOUtil.writeUTF(dataOutput, str);
    }

    @Override // com.intellij.util.io.KeyDescriptor, com.intellij.util.containers.hash.EqualityPolicy
    public int getHashCode(String str) {
        return str.hashCode();
    }

    @Override // com.intellij.util.containers.hash.EqualityPolicy
    public boolean isEqual(String str, String str2) {
        return str.equals(str2);
    }
}
