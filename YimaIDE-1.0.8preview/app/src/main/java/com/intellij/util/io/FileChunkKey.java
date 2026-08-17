package com.intellij.util.io;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
final class FileChunkKey<OwnerType> implements Comparable<FileChunkKey<OwnerType>> {
    private final long offset;
    private final OwnerType owner;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "o", "com/intellij/util/io/FileChunkKey", "compareTo"));
    }

    public FileChunkKey(OwnerType ownertype, long j) {
        this.owner = ownertype;
        this.offset = j;
    }

    @Override // java.lang.Comparable
    public int compareTo(FileChunkKey<OwnerType> fileChunkKey) {
        if (fileChunkKey == null) {
            $$$reportNull$$$0(0);
        }
        OwnerType ownertype = this.owner;
        if (ownertype != fileChunkKey.owner) {
            return ownertype.hashCode() - fileChunkKey.owner.hashCode();
        }
        long j = this.offset;
        long j2 = fileChunkKey.offset;
        if (j == j2) {
            return 0;
        }
        return j - j2 < 0 ? -1 : 1;
    }

    public boolean equals(Object obj) {
        if (FileChunkKey.class != obj.getClass()) {
            return false;
        }
        FileChunkKey fileChunkKey = (FileChunkKey) obj;
        return fileChunkKey.owner == this.owner && fileChunkKey.offset == this.offset;
    }

    public OwnerType getOwner() {
        return this.owner;
    }

    public int hashCode() {
        return (int) (((long) (this.owner.hashCode() * 31)) + this.offset);
    }
}
