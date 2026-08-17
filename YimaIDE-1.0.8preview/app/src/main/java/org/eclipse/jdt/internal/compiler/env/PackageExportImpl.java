package org.eclipse.jdt.internal.compiler.env;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class PackageExportImpl implements IModule.IPackageExport {
    public char[][] exportedTo;
    public char[] pack;

    @Override // org.eclipse.jdt.internal.compiler.env.IModule.IPackageExport
    public char[] name() {
        return this.pack;
    }

    @Override // org.eclipse.jdt.internal.compiler.env.IModule.IPackageExport
    public char[][] targets() {
        return this.exportedTo;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.pack);
        sb.append(" to ");
        if (this.exportedTo != null) {
            for (int i = 0; i < this.exportedTo.length; i++) {
                if (i > 0) {
                    sb.append(", ");
                }
                sb.append(this.exportedTo[i]);
            }
        }
        sb.append(';');
        return sb.toString();
    }
}
