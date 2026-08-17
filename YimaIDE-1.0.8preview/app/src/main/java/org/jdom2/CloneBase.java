package org.jdom2;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
class CloneBase implements Cloneable {
    @Override // 
    public CloneBase clone() {
        try {
            return (CloneBase) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new IllegalStateException(String.format("Unable to clone class %s which should always support it.", getClass().getName()), e);
        }
    }
}
