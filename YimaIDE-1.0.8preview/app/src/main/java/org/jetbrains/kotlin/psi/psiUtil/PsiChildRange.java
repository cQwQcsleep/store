package org.jetbrains.kotlin.psi.psiUtil;

import com.intellij.psi.PsiElement;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import org.jetbrains.kotlin.psi.psiUtil.PsiChildRange;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010(\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u0000 \u00192\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0019B\u001b\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00020\u000eH\u0096\u0082\u0004J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0002HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0002HÆ\u0003J!\u0010\u0011\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0002HÆ\u0001J\u0014\u0010\u0012\u001a\u00020\u000b2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J\n\u0010\u0017\u001a\u00020\u0018HÖ\u0081\u0004R\u0013\u0010\u0003\u001a\u0004\u0018\u00010\u0002¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0002¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\n\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b\n\u0010\f¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/kotlin/psi/psiUtil/PsiChildRange;", "Lkotlin/sequences/Sequence;", "Lcom/intellij/psi/PsiElement;", "first", "last", "<init>", "(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)V", "getFirst", "()Lcom/intellij/psi/PsiElement;", "getLast", "isEmpty", "", "()Z", "iterator", "", "component1", "component2", "copy", "equals", "other", "", "hashCode", "", "toString", "", "Companion", "org.jetbrains.kotlin:psi-api"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final /* data */ class PsiChildRange implements Sequence<PsiElement> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final PsiChildRange EMPTY = new PsiChildRange(null, null);
    private final PsiElement first;
    private final PsiElement last;

    public PsiChildRange(PsiElement psiElement, PsiElement psiElement2) {
        this.first = psiElement;
        this.last = psiElement2;
        if (psiElement == null) {
            return;
        }
        PsiElement parent = psiElement.getParent();
        psiElement2.getClass();
        Intrinsics.areEqual(parent, psiElement2.getParent());
    }

    public static boolean a(PsiElement psiElement, PsiElement psiElement2) {
        psiElement2.getClass();
        return !Intrinsics.areEqual(psiElement2, psiElement);
    }

    public static /* synthetic */ PsiChildRange copy$default(PsiChildRange psiChildRange, PsiElement psiElement, PsiElement psiElement2, int i, Object obj) {
        if ((i & 1) != 0) {
            psiElement = psiChildRange.first;
        }
        if ((i & 2) != 0) {
            psiElement2 = psiChildRange.last;
        }
        return psiChildRange.copy(psiElement, psiElement2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final PsiElement getFirst() {
        return this.first;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final PsiElement getLast() {
        return this.last;
    }

    public final PsiChildRange copy(PsiElement first, PsiElement last) {
        return new PsiChildRange(first, last);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PsiChildRange)) {
            return false;
        }
        PsiChildRange psiChildRange = (PsiChildRange) other;
        return Intrinsics.areEqual(this.first, psiChildRange.first) && Intrinsics.areEqual(this.last, psiChildRange.last);
    }

    public final PsiElement getFirst() {
        return this.first;
    }

    public final PsiElement getLast() {
        return this.last;
    }

    public int hashCode() {
        PsiElement psiElement = this.first;
        int iHashCode = (psiElement == null ? 0 : psiElement.hashCode()) * 31;
        PsiElement psiElement2 = this.last;
        return iHashCode + (psiElement2 != null ? psiElement2.hashCode() : 0);
    }

    public final boolean isEmpty() {
        return this.first == null;
    }

    public Iterator<PsiElement> iterator() {
        Sequence sequenceTakeWhile;
        if (this.first == null) {
            sequenceTakeWhile = SequencesKt.emptySequence();
        } else {
            PsiElement psiElement = this.last;
            psiElement.getClass();
            final PsiElement nextSibling = psiElement.getNextSibling();
            sequenceTakeWhile = SequencesKt.takeWhile(PsiUtilsKt.siblings$default(this.first, false, false, 3, null), new Function1() { // from class: djb
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(PsiChildRange.a(nextSibling, (PsiElement) obj));
                }
            });
        }
        return sequenceTakeWhile.iterator();
    }

    public String toString() {
        return "PsiChildRange(first=" + this.first + ", last=" + this.last + ')';
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/psi/psiUtil/PsiChildRange$Companion;", "", "<init>", "()V", "EMPTY", "Lorg/jetbrains/kotlin/psi/psiUtil/PsiChildRange;", "getEMPTY", "()Lorg/jetbrains/kotlin/psi/psiUtil/PsiChildRange;", "singleElement", "element", "Lcom/intellij/psi/PsiElement;", "org.jetbrains.kotlin:psi-api"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final PsiChildRange getEMPTY() {
            return PsiChildRange.EMPTY;
        }

        public final PsiChildRange singleElement(PsiElement element) {
            element.getClass();
            return new PsiChildRange(element, element);
        }

        private Companion() {
        }
    }
}
