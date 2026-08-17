package org.jetbrains.org.objectweb.asm.tree;

import org.jetbrains.org.objectweb.asm.MethodVisitor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
public class LocalVariableNode {
    public String desc;
    public LabelNode end;
    public int index;
    public String name;
    public String signature;
    public LabelNode start;

    public LocalVariableNode(String str, String str2, String str3, LabelNode labelNode, LabelNode labelNode2, int i) {
        this.name = str;
        this.desc = str2;
        this.signature = str3;
        this.start = labelNode;
        this.end = labelNode2;
        this.index = i;
    }

    public void accept(MethodVisitor methodVisitor) {
        methodVisitor.visitLocalVariable(this.name, this.desc, this.signature, this.start.getLabel(), this.end.getLabel(), this.index);
    }
}
