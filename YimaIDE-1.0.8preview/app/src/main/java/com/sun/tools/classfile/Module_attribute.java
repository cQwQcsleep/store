package com.sun.tools.classfile;

import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Module_attribute extends Attribute {
    public static final int ACC_MANDATED = 32768;
    public static final int ACC_OPEN = 32;
    public static final int ACC_STATIC_PHASE = 64;
    public static final int ACC_SYNTHETIC = 4096;
    public static final int ACC_TRANSITIVE = 32;
    public final ExportsEntry[] exports;
    public final int exports_count;
    public final int module_flags;
    public final int module_name;
    public final int module_version_index;
    public final OpensEntry[] opens;
    public final int opens_count;
    public final ProvidesEntry[] provides;
    public final int provides_count;
    public final RequiresEntry[] requires;
    public final int requires_count;
    public final int uses_count;
    public final int[] uses_index;

    public Module_attribute(ClassReader classReader, int i, int i2) throws IOException {
        super(i, i2);
        this.module_name = classReader.readUnsignedShort();
        this.module_flags = classReader.readUnsignedShort();
        this.module_version_index = classReader.readUnsignedShort();
        int unsignedShort = classReader.readUnsignedShort();
        this.requires_count = unsignedShort;
        this.requires = new RequiresEntry[unsignedShort];
        for (int i3 = 0; i3 < this.requires_count; i3++) {
            this.requires[i3] = new RequiresEntry(classReader);
        }
        int unsignedShort2 = classReader.readUnsignedShort();
        this.exports_count = unsignedShort2;
        this.exports = new ExportsEntry[unsignedShort2];
        for (int i4 = 0; i4 < this.exports_count; i4++) {
            this.exports[i4] = new ExportsEntry(classReader);
        }
        int unsignedShort3 = classReader.readUnsignedShort();
        this.opens_count = unsignedShort3;
        this.opens = new OpensEntry[unsignedShort3];
        for (int i5 = 0; i5 < this.opens_count; i5++) {
            this.opens[i5] = new OpensEntry(classReader);
        }
        int unsignedShort4 = classReader.readUnsignedShort();
        this.uses_count = unsignedShort4;
        this.uses_index = new int[unsignedShort4];
        for (int i6 = 0; i6 < this.uses_count; i6++) {
            this.uses_index[i6] = classReader.readUnsignedShort();
        }
        int unsignedShort5 = classReader.readUnsignedShort();
        this.provides_count = unsignedShort5;
        this.provides = new ProvidesEntry[unsignedShort5];
        for (int i7 = 0; i7 < this.provides_count; i7++) {
            this.provides[i7] = new ProvidesEntry(classReader);
        }
    }

    @Override // com.sun.tools.classfile.Attribute
    public <R, D> R accept(Attribute.Visitor<R, D> visitor, D d) {
        return visitor.visitModule(this, d);
    }

    public String getUses(int i, ConstantPool constantPool) throws ConstantPoolException {
        return constantPool.getClassInfo(this.uses_index[i]).getName();
    }

    public static class RequiresEntry {
        public static final int length = 4;
        public final int requires_flags;
        public final int requires_index;
        public final int requires_version_index;

        public RequiresEntry(ClassReader classReader) throws IOException {
            this.requires_index = classReader.readUnsignedShort();
            this.requires_flags = classReader.readUnsignedShort();
            this.requires_version_index = classReader.readUnsignedShort();
        }

        public String getRequires(ConstantPool constantPool) throws ConstantPoolException {
            return constantPool.getModuleInfo(this.requires_index).getName();
        }

        public RequiresEntry(int i, int i2, int i3) {
            this.requires_index = i;
            this.requires_flags = i2;
            this.requires_version_index = i3;
        }
    }

    public static class ProvidesEntry {
        public static final int length = 4;
        public final int provides_index;
        public final int with_count;
        public final int[] with_index;

        public ProvidesEntry(ClassReader classReader) throws IOException {
            this.provides_index = classReader.readUnsignedShort();
            int unsignedShort = classReader.readUnsignedShort();
            this.with_count = unsignedShort;
            this.with_index = new int[unsignedShort];
            for (int i = 0; i < this.with_count; i++) {
                this.with_index[i] = classReader.readUnsignedShort();
            }
        }

        public ProvidesEntry(int i, int[] iArr) {
            this.provides_index = i;
            this.with_count = iArr.length;
            this.with_index = iArr;
        }
    }

    public static class ExportsEntry {
        public final int exports_flags;
        public final int exports_index;
        public final int exports_to_count;
        public final int[] exports_to_index;

        public ExportsEntry(ClassReader classReader) throws IOException {
            this.exports_index = classReader.readUnsignedShort();
            this.exports_flags = classReader.readUnsignedShort();
            int unsignedShort = classReader.readUnsignedShort();
            this.exports_to_count = unsignedShort;
            this.exports_to_index = new int[unsignedShort];
            for (int i = 0; i < this.exports_to_count; i++) {
                this.exports_to_index[i] = classReader.readUnsignedShort();
            }
        }

        public int length() {
            return (this.exports_to_index.length * 2) + 4;
        }

        public ExportsEntry(int i, int i2, int[] iArr) {
            this.exports_index = i;
            this.exports_flags = i2;
            this.exports_to_count = iArr.length;
            this.exports_to_index = iArr;
        }
    }

    public static class OpensEntry {
        public final int opens_flags;
        public final int opens_index;
        public final int opens_to_count;
        public final int[] opens_to_index;

        public OpensEntry(ClassReader classReader) throws IOException {
            this.opens_index = classReader.readUnsignedShort();
            this.opens_flags = classReader.readUnsignedShort();
            int unsignedShort = classReader.readUnsignedShort();
            this.opens_to_count = unsignedShort;
            this.opens_to_index = new int[unsignedShort];
            for (int i = 0; i < this.opens_to_count; i++) {
                this.opens_to_index[i] = classReader.readUnsignedShort();
            }
        }

        public int length() {
            return (this.opens_to_index.length * 2) + 4;
        }

        public OpensEntry(int i, int i2, int[] iArr) {
            this.opens_index = i;
            this.opens_flags = i2;
            this.opens_to_count = iArr.length;
            this.opens_to_index = iArr;
        }
    }

    public Module_attribute(int i, int i2, int i3, int i4, RequiresEntry[] requiresEntryArr, ExportsEntry[] exportsEntryArr, OpensEntry[] opensEntryArr, int[] iArr, ProvidesEntry[] providesEntryArr) {
        super(i, 2);
        this.module_name = i2;
        this.module_flags = i3;
        this.module_version_index = i4;
        this.requires_count = requiresEntryArr.length;
        this.requires = requiresEntryArr;
        this.exports_count = exportsEntryArr.length;
        this.exports = exportsEntryArr;
        this.opens_count = opensEntryArr.length;
        this.opens = opensEntryArr;
        this.uses_count = iArr.length;
        this.uses_index = iArr;
        this.provides_count = providesEntryArr.length;
        this.provides = providesEntryArr;
    }
}
