package org.jetbrains.kotlin.codegen;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.sequences.Sequence;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.InsnList;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010(\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0005\u0010\u0006B\u0011\b\u0016\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\u0005\u0010\tJ\u0010\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00020\u000eH\u0096\u0082\u0004R\u0011\u0010\u0003\u001a\u00020\u0002¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0002¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/codegen/InsnSequence;", "Lkotlin/sequences/Sequence;", "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "from", "to", "<init>", "(Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;)V", "insnList", "Lorg/jetbrains/org/objectweb/asm/tree/InsnList;", "(Lorg/jetbrains/org/objectweb/asm/tree/InsnList;)V", "getFrom", "()Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "getTo", "iterator", Argument.Delimiters.none, "org.jetbrains.kotlin:backend.common.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class InsnSequence implements Sequence<AbstractInsnNode> {
    private final AbstractInsnNode from;
    private final AbstractInsnNode to;

    /* JADX INFO: renamed from: org.jetbrains.kotlin.codegen.InsnSequence$iterator$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0010(\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\n\u0010\b\u001a\u00020\u0002H\u0096\u0082\u0004J\n\u0010\t\u001a\u00020\nH\u0096\u0082\u0004R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0002X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007¨\u0006\u000b"}, d2 = {"org/jetbrains/kotlin/codegen/InsnSequence$iterator$1", Argument.Delimiters.none, "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "current", "getCurrent", "()Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "setCurrent", "(Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;)V", "next", "hasNext", Argument.Delimiters.none, "org.jetbrains.kotlin:backend.common.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class AnonymousClass1 implements Iterator<AbstractInsnNode>, KMappedMarker {
        private AbstractInsnNode current;

        public AnonymousClass1() {
            this.current = InsnSequence.this.getFrom();
        }

        public final AbstractInsnNode getCurrent() {
            return this.current;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return !Intrinsics.areEqual(this.current, InsnSequence.this.getTo());
        }

        @Override // java.util.Iterator
        public AbstractInsnNode next() {
            AbstractInsnNode abstractInsnNode = this.current;
            abstractInsnNode.getClass();
            this.current = abstractInsnNode.getNext();
            abstractInsnNode.getClass();
            return abstractInsnNode;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        public final void setCurrent(AbstractInsnNode abstractInsnNode) {
            this.current = abstractInsnNode;
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public InsnSequence(InsnList insnList) {
        insnList.getClass();
        AbstractInsnNode first = insnList.getFirst();
        first.getClass();
        this(first, null);
    }

    public final AbstractInsnNode getFrom() {
        return this.from;
    }

    public final AbstractInsnNode getTo() {
        return this.to;
    }

    public Iterator<AbstractInsnNode> iterator() {
        return new AnonymousClass1();
    }

    public InsnSequence(AbstractInsnNode abstractInsnNode, AbstractInsnNode abstractInsnNode2) {
        abstractInsnNode.getClass();
        this.from = abstractInsnNode;
        this.to = abstractInsnNode2;
    }
}
