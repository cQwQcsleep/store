package net.schmizz.sshj.sftp;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public class RemoteResourceInfo {
    private final FileAttributes attrs;
    private final PathComponents comps;

    public RemoteResourceInfo(PathComponents pathComponents, FileAttributes fileAttributes) {
        this.comps = pathComponents;
        this.attrs = fileAttributes;
    }

    public boolean equals(Object obj) {
        return (obj instanceof RemoteResourceInfo) && this.comps.equals(((RemoteResourceInfo) obj).comps);
    }

    public FileAttributes getAttributes() {
        return this.attrs;
    }

    public String getName() {
        return this.comps.getName();
    }

    public String getParent() {
        return this.comps.getParent();
    }

    public String getPath() {
        return this.comps.getPath();
    }

    public int hashCode() {
        return this.comps.hashCode();
    }

    public boolean isDirectory() {
        return this.attrs.getType() == FileMode.Type.DIRECTORY;
    }

    public boolean isRegularFile() {
        return this.attrs.getType() == FileMode.Type.REGULAR;
    }

    public String toString() {
        return "[" + this.attrs.getType() + "] " + getPath();
    }
}
