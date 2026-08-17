package org.jetbrains.kotlin.cfg;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiWhiteSpace;
import com.intellij.psi.util.PsiElementFilter;
import com.intellij.psi.util.PsiTreeUtil;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cfg.UnreachableCode;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.lexer.KtTokens;
import org.jetbrains.kotlin.psi.KtElement;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\b\bf\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bR\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0018\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\u0006R\u0018\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u0006ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\fÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/cfg/UnreachableCode;", "", "elements", "", "Lorg/jetbrains/kotlin/psi/KtElement;", "getElements", "()Ljava/util/Set;", "reachableElements", "getReachableElements", "unreachableElements", "getUnreachableElements", "Companion", "org.jetbrains.kotlin:frontend.common-psi"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface UnreachableCode {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J0\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\nJ\u001a\u0010\f\u001a\u00020\r*\u00020\b2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\b0\nH\u0002J.\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u0005*\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\nH\u0002J&\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u0005*\b\u0012\u0004\u0012\u00020\u00100\u00052\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\nH\u0002J\u0018\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005*\b\u0012\u0004\u0012\u00020\u00100\u0005H\u0002¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/cfg/UnreachableCode$Companion;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "getUnreachableTextRanges", "", "Lcom/intellij/openapi/util/TextRange;", CapturedVarsOptimizationMethodTransformerKt.REF_ELEMENT_FIELD, "Lorg/jetbrains/kotlin/psi/KtElement;", "reachableElements", "", "unreachableElements", "hasChildrenInSet", "", "set", "getLeavesOrReachableChildren", "Lcom/intellij/psi/PsiElement;", "removeReachableElementsWithMeaninglessSiblings", "mergeAdjacentTextRanges", "org.jetbrains.kotlin:frontend.common-psi"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }

        public static boolean a(KtElement ktElement, PsiElement psiElement) {
            psiElement.getClass();
            return !Intrinsics.areEqual(psiElement, ktElement);
        }

        private final List<PsiElement> getLeavesOrReachableChildren(KtElement ktElement, final Set<? extends KtElement> set, final Set<? extends KtElement> set2) {
            final ArrayList arrayList = new ArrayList();
            ktElement.acceptChildren(new PsiElementVisitor() { // from class: org.jetbrains.kotlin.cfg.UnreachableCode$Companion$getLeavesOrReachableChildren$1
                public void visitElement(PsiElement element) {
                    element.getClass();
                    if (!(element instanceof KtElement) || !set.contains(element) || UnreachableCode.Companion.$$INSTANCE.hasChildrenInSet((KtElement) element, set2)) {
                        PsiElement[] children = element.getChildren();
                        children.getClass();
                        if (children.length != 0) {
                            element.acceptChildren(this);
                            return;
                        }
                    }
                    arrayList.add(element);
                }
            });
            return arrayList;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean hasChildrenInSet(final KtElement ktElement, Set<? extends KtElement> set) {
            PsiElement[] psiElementArrCollectElements = PsiTreeUtil.collectElements(ktElement, new PsiElementFilter() { // from class: v0f
                public final boolean isAccepted(PsiElement psiElement) {
                    return UnreachableCode.Companion.a(ktElement, psiElement);
                }
            });
            psiElementArrCollectElements.getClass();
            for (PsiElement psiElement : psiElementArrCollectElements) {
                if (CollectionsKt.contains(set, psiElement)) {
                    return true;
                }
            }
            return false;
        }

        private final List<TextRange> mergeAdjacentTextRanges(List<? extends PsiElement> list) {
            ArrayList arrayList = new ArrayList();
            Iterator<T> it = list.iterator();
            TextRange textRangeUnion = null;
            while (it.hasNext()) {
                TextRange textRange = ((PsiElement) it.next()).getTextRange();
                textRange.getClass();
                if (textRangeUnion != null) {
                    if (textRangeUnion.getEndOffset() == textRange.getStartOffset()) {
                        textRangeUnion = textRangeUnion.union(textRange);
                    } else {
                        arrayList.add(textRangeUnion);
                    }
                }
                textRangeUnion = textRange;
            }
            if (textRangeUnion != null) {
                arrayList.add(textRangeUnion);
            }
            return arrayList;
        }

        private final List<PsiElement> removeReachableElementsWithMeaninglessSiblings(List<? extends PsiElement> list, Set<? extends KtElement> set) {
            HashSet hashSet = new HashSet();
            List<? extends PsiElement> list2 = list;
            int i = 0;
            for (PsiElement psiElement : list2) {
                int i2 = i + 1;
                if (CollectionsKt.contains(set, psiElement)) {
                    hashSet.add(psiElement);
                    removeReachableElementsWithMeaninglessSiblings$collectSiblingsIfMeaningless(list, hashSet, i, -1);
                    removeReachableElementsWithMeaninglessSiblings$collectSiblingsIfMeaningless(list, hashSet, i, 1);
                }
                i = i2;
            }
            ArrayList arrayList = new ArrayList();
            for (Object obj : list2) {
                if (!hashSet.contains((PsiElement) obj)) {
                    arrayList.add(obj);
                }
            }
            return arrayList;
        }

        private static final void removeReachableElementsWithMeaninglessSiblings$collectSiblingsIfMeaningless(List<? extends PsiElement> list, HashSet<PsiElement> hashSet, int i, int i2) {
            int i3 = i + i2;
            if (i3 < 0 || i3 >= list.size()) {
                return;
            }
            PsiElement psiElement = list.get(i3);
            if (removeReachableElementsWithMeaninglessSiblings$isMeaningless(psiElement)) {
                hashSet.add(psiElement);
                removeReachableElementsWithMeaninglessSiblings$collectSiblingsIfMeaningless(list, hashSet, i3, i2);
            }
        }

        private static final boolean removeReachableElementsWithMeaninglessSiblings$isMeaningless(PsiElement psiElement) {
            if (psiElement instanceof PsiWhiteSpace) {
                return true;
            }
            ASTNode node = psiElement.getNode();
            return Intrinsics.areEqual(node != null ? node.getElementType() : null, KtTokens.COMMA) || (psiElement instanceof PsiComment);
        }

        public final List<TextRange> getUnreachableTextRanges(KtElement element, Set<? extends KtElement> reachableElements, Set<? extends KtElement> unreachableElements) {
            element.getClass();
            reachableElements.getClass();
            unreachableElements.getClass();
            if (!hasChildrenInSet(element, reachableElements)) {
                TextRange textRange = element.getTextRange();
                textRange.getClass();
                return CollectionsKt.listOf(textRange);
            }
            List<TextRange> listMergeAdjacentTextRanges = mergeAdjacentTextRanges(removeReachableElementsWithMeaninglessSiblings(getLeavesOrReachableChildren(element, reachableElements, unreachableElements), reachableElements));
            if (!listMergeAdjacentTextRanges.isEmpty()) {
                return listMergeAdjacentTextRanges;
            }
            int endOffset = element.getTextRange().getEndOffset();
            return CollectionsKt.listOf(new TextRange(endOffset, endOffset));
        }
    }

    Set<KtElement> getElements();

    Set<KtElement> getReachableElements();

    Set<KtElement> getUnreachableElements();
}
