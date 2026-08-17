package com.sun.org.apache.bcel.internal.classfile;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class AccessFlags {

    @java.lang.Deprecated
    protected int access_flags;

    public AccessFlags(int i) {
        this.access_flags = i;
    }

    private void setFlag(int i, boolean z) {
        int i2 = this.access_flags;
        if ((i2 & i) != 0) {
            if (z) {
                return;
            }
            this.access_flags = i ^ i2;
        } else if (z) {
            this.access_flags = i | i2;
        }
    }

    public final int getAccessFlags() {
        return this.access_flags;
    }

    public final int getModifiers() {
        return this.access_flags;
    }

    public final boolean isAbstract() {
        return (this.access_flags & 1024) != 0;
    }

    public final boolean isAnnotation() {
        return (this.access_flags & 8192) != 0;
    }

    public final boolean isEnum() {
        return (this.access_flags & 16384) != 0;
    }

    public final boolean isFinal() {
        return (this.access_flags & 16) != 0;
    }

    public final boolean isInterface() {
        return (this.access_flags & 512) != 0;
    }

    public final boolean isNative() {
        return (this.access_flags & 256) != 0;
    }

    public final boolean isPrivate() {
        return (this.access_flags & 2) != 0;
    }

    public final boolean isProtected() {
        return (this.access_flags & 4) != 0;
    }

    public final boolean isPublic() {
        return (this.access_flags & 1) != 0;
    }

    public final boolean isStatic() {
        return (this.access_flags & 8) != 0;
    }

    public final boolean isStrictfp() {
        return (this.access_flags & 2048) != 0;
    }

    public final boolean isSynchronized() {
        return (this.access_flags & 32) != 0;
    }

    public final boolean isSynthetic() {
        return (this.access_flags & 4096) != 0;
    }

    public final boolean isTransient() {
        return (this.access_flags & 128) != 0;
    }

    public final boolean isVarArgs() {
        return (this.access_flags & 128) != 0;
    }

    public final boolean isVolatile() {
        return (this.access_flags & 64) != 0;
    }

    public final void setAccessFlags(int i) {
        this.access_flags = i;
    }

    public final void setModifiers(int i) {
        setAccessFlags(i);
    }

    public AccessFlags() {
    }

    public final void isPublic(boolean z) {
        setFlag(1, z);
    }

    public final void isAbstract(boolean z) {
        setFlag(1024, z);
    }

    public final void isAnnotation(boolean z) {
        setFlag(8192, z);
    }

    public final void isEnum(boolean z) {
        setFlag(16384, z);
    }

    public final void isFinal(boolean z) {
        setFlag(16, z);
    }

    public final void isInterface(boolean z) {
        setFlag(512, z);
    }

    public final void isNative(boolean z) {
        setFlag(256, z);
    }

    public final void isPrivate(boolean z) {
        setFlag(2, z);
    }

    public final void isProtected(boolean z) {
        setFlag(4, z);
    }

    public final void isStatic(boolean z) {
        setFlag(8, z);
    }

    public final void isStrictfp(boolean z) {
        setFlag(2048, z);
    }

    public final void isSynchronized(boolean z) {
        setFlag(32, z);
    }

    public final void isSynthetic(boolean z) {
        setFlag(4096, z);
    }

    public final void isTransient(boolean z) {
        setFlag(128, z);
    }

    public final void isVarArgs(boolean z) {
        setFlag(128, z);
    }

    public final void isVolatile(boolean z) {
        setFlag(64, z);
    }
}
