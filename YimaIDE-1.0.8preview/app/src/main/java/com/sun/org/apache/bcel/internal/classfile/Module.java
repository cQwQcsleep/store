package com.sun.org.apache.bcel.internal.classfile;

import com.sun.org.apache.bcel.internal.Const;
import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.function.IntFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class Module extends Attribute {
    public static final String EXTENSION = ".jmod";
    private ModuleExports[] exportsTable;
    private final int moduleFlags;
    private final int moduleNameIndex;
    private final int moduleVersionIndex;
    private ModuleOpens[] opensTable;
    private ModuleProvides[] providesTable;
    private ModuleRequires[] requiresTable;
    private final int usesCount;
    private final int[] usesIndex;

    public Module(int i, int i2, DataInput dataInput, ConstantPool constantPool) throws IOException {
        super(Const.ATTR_MODULE, i, i2, constantPool);
        this.moduleNameIndex = dataInput.readUnsignedShort();
        this.moduleFlags = dataInput.readUnsignedShort();
        this.moduleVersionIndex = dataInput.readUnsignedShort();
        int unsignedShort = dataInput.readUnsignedShort();
        this.requiresTable = new ModuleRequires[unsignedShort];
        for (int i3 = 0; i3 < unsignedShort; i3++) {
            this.requiresTable[i3] = new ModuleRequires(dataInput);
        }
        int unsignedShort2 = dataInput.readUnsignedShort();
        this.exportsTable = new ModuleExports[unsignedShort2];
        for (int i4 = 0; i4 < unsignedShort2; i4++) {
            this.exportsTable[i4] = new ModuleExports(dataInput);
        }
        int unsignedShort3 = dataInput.readUnsignedShort();
        this.opensTable = new ModuleOpens[unsignedShort3];
        for (int i5 = 0; i5 < unsignedShort3; i5++) {
            this.opensTable[i5] = new ModuleOpens(dataInput);
        }
        int unsignedShort4 = dataInput.readUnsignedShort();
        this.usesCount = unsignedShort4;
        this.usesIndex = new int[unsignedShort4];
        for (int i6 = 0; i6 < this.usesCount; i6++) {
            this.usesIndex[i6] = dataInput.readUnsignedShort();
        }
        int unsignedShort5 = dataInput.readUnsignedShort();
        this.providesTable = new ModuleProvides[unsignedShort5];
        for (int i7 = 0; i7 < unsignedShort5; i7++) {
            this.providesTable[i7] = new ModuleProvides(dataInput);
        }
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute, com.sun.org.apache.bcel.internal.classfile.Node
    public void accept(Visitor visitor) {
        visitor.visitModule(this);
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute
    public Attribute copy(ConstantPool constantPool) {
        Module module = (Module) clone();
        ModuleRequires[] moduleRequiresArr = new ModuleRequires[this.requiresTable.length];
        module.requiresTable = moduleRequiresArr;
        Arrays.setAll(moduleRequiresArr, new IntFunction() { // from class: k4a
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return this.b.requiresTable[i].copy();
            }
        });
        ModuleExports[] moduleExportsArr = new ModuleExports[this.exportsTable.length];
        module.exportsTable = moduleExportsArr;
        Arrays.setAll(moduleExportsArr, new IntFunction() { // from class: l4a
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return this.b.exportsTable[i].copy();
            }
        });
        ModuleOpens[] moduleOpensArr = new ModuleOpens[this.opensTable.length];
        module.opensTable = moduleOpensArr;
        Arrays.setAll(moduleOpensArr, new IntFunction() { // from class: m4a
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return this.b.opensTable[i].copy();
            }
        });
        ModuleProvides[] moduleProvidesArr = new ModuleProvides[this.providesTable.length];
        module.providesTable = moduleProvidesArr;
        Arrays.setAll(moduleProvidesArr, new IntFunction() { // from class: n4a
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return this.b.providesTable[i].copy();
            }
        });
        module.setConstantPool(constantPool);
        return module;
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute
    public void dump(DataOutputStream dataOutputStream) throws IOException {
        super.dump(dataOutputStream);
        dataOutputStream.writeShort(this.moduleNameIndex);
        dataOutputStream.writeShort(this.moduleFlags);
        dataOutputStream.writeShort(this.moduleVersionIndex);
        dataOutputStream.writeShort(this.requiresTable.length);
        for (ModuleRequires moduleRequires : this.requiresTable) {
            moduleRequires.dump(dataOutputStream);
        }
        dataOutputStream.writeShort(this.exportsTable.length);
        for (ModuleExports moduleExports : this.exportsTable) {
            moduleExports.dump(dataOutputStream);
        }
        dataOutputStream.writeShort(this.opensTable.length);
        for (ModuleOpens moduleOpens : this.opensTable) {
            moduleOpens.dump(dataOutputStream);
        }
        dataOutputStream.writeShort(this.usesIndex.length);
        for (int i : this.usesIndex) {
            dataOutputStream.writeShort(i);
        }
        dataOutputStream.writeShort(this.providesTable.length);
        for (ModuleProvides moduleProvides : this.providesTable) {
            moduleProvides.dump(dataOutputStream);
        }
    }

    public ModuleExports[] getExportsTable() {
        return this.exportsTable;
    }

    public ModuleOpens[] getOpensTable() {
        return this.opensTable;
    }

    public ModuleProvides[] getProvidesTable() {
        return this.providesTable;
    }

    public ModuleRequires[] getRequiresTable() {
        return this.requiresTable;
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute
    public String toString() {
        ConstantPool constantPool = super.getConstantPool();
        StringBuilder sb = new StringBuilder();
        sb.append("Module:\n  name:    ");
        sb.append(Utility.pathToPackage(constantPool.getConstantString(this.moduleNameIndex, (byte) 19)));
        sb.append("\n  flags:   ");
        sb.append(String.format("%04x", Integer.valueOf(this.moduleFlags)));
        sb.append("\n  version: ");
        int i = this.moduleVersionIndex;
        sb.append(i == 0 ? "0" : constantPool.getConstantString(i, (byte) 1));
        sb.append("\n  requires(");
        sb.append(this.requiresTable.length);
        sb.append("):\n");
        for (ModuleRequires moduleRequires : this.requiresTable) {
            sb.append("    ");
            sb.append(moduleRequires.toString(constantPool));
            sb.append("\n");
        }
        sb.append("  exports(");
        sb.append(this.exportsTable.length);
        sb.append("):\n");
        for (ModuleExports moduleExports : this.exportsTable) {
            sb.append("    ");
            sb.append(moduleExports.toString(constantPool));
            sb.append("\n");
        }
        sb.append("  opens(");
        sb.append(this.opensTable.length);
        sb.append("):\n");
        for (ModuleOpens moduleOpens : this.opensTable) {
            sb.append("    ");
            sb.append(moduleOpens.toString(constantPool));
            sb.append("\n");
        }
        sb.append("  uses(");
        sb.append(this.usesIndex.length);
        sb.append("):\n");
        for (int i2 : this.usesIndex) {
            String constantString = constantPool.getConstantString(i2, (byte) 7);
            sb.append("    ");
            sb.append(Utility.compactClassName(constantString, false));
            sb.append("\n");
        }
        sb.append("  provides(");
        sb.append(this.providesTable.length);
        sb.append("):\n");
        for (ModuleProvides moduleProvides : this.providesTable) {
            sb.append("    ");
            sb.append(moduleProvides.toString(constantPool));
            sb.append("\n");
        }
        return sb.substring(0, sb.length() - 1);
    }
}
