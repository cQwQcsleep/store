package org.jetbrains.kotlin.fir.analysis.checkers;

import com.intellij.lang.ASTNode;
import com.intellij.lang.LighterASTNode;
import com.intellij.util.diff.FlyweightCapableTreeStructure;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.lexer.KtModifierKeywordToken;
import org.jetbrains.kotlin.psi.KtModifierList;
import org.jetbrains.kotlin.util.LightTreeUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u000e\u000fB\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\t\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00062\u0006\u0010\n\u001a\u00020\u000bH\u0086\u0002J\u0011\u0010\f\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000bH\u0086\u0002R\u001c\u0010\u0004\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b\u0082\u0001\u0002\u0010\u0011¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/FirModifierList;", Argument.Delimiters.none, "<init>", "()V", "modifiers", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/FirModifier;", "getModifiers", "()Ljava/util/List;", "get", "token", "Lorg/jetbrains/kotlin/lexer/KtModifierKeywordToken;", "contains", Argument.Delimiters.none, "FirPsiModifierList", "FirLightModifierList", "Lorg/jetbrains/kotlin/fir/analysis/checkers/FirModifierList$FirLightModifierList;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/FirModifierList$FirPsiModifierList;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirModifierList {

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/FirModifierList$FirLightModifierList;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/FirModifierList;", "modifierList", "Lcom/intellij/lang/LighterASTNode;", "tree", "Lcom/intellij/util/diff/FlyweightCapableTreeStructure;", "offsetDelta", Argument.Delimiters.none, "<init>", "(Lcom/intellij/lang/LighterASTNode;Lcom/intellij/util/diff/FlyweightCapableTreeStructure;I)V", "getModifierList", "()Lcom/intellij/lang/LighterASTNode;", "getTree", "()Lcom/intellij/util/diff/FlyweightCapableTreeStructure;", "modifiers", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/FirModifier$FirLightModifier;", "getModifiers", "()Ljava/util/List;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class FirLightModifierList extends FirModifierList {
        private final LighterASTNode modifierList;
        private final int offsetDelta;
        private final FlyweightCapableTreeStructure<LighterASTNode> tree;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public FirLightModifierList(LighterASTNode lighterASTNode, FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure, int i) {
            super(null);
            lighterASTNode.getClass();
            flyweightCapableTreeStructure.getClass();
            this.modifierList = lighterASTNode;
            this.tree = flyweightCapableTreeStructure;
            this.offsetDelta = i;
        }

        public final LighterASTNode getModifierList() {
            return this.modifierList;
        }

        @Override // org.jetbrains.kotlin.fir.analysis.checkers.FirModifierList
        public List<FirModifier.FirLightModifier> getModifiers() {
            List children = LightTreeUtilsKt.getChildren(this.modifierList, this.tree);
            ArrayList<LighterASTNode> arrayList = new ArrayList();
            for (Object obj : children) {
                if (((LighterASTNode) obj).getTokenType() instanceof KtModifierKeywordToken) {
                    arrayList.add(obj);
                }
            }
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
            for (LighterASTNode lighterASTNode : arrayList) {
                KtModifierKeywordToken tokenType = lighterASTNode.getTokenType();
                tokenType.getClass();
                arrayList2.add(new FirModifier.FirLightModifier(lighterASTNode, tokenType, this.tree, this.offsetDelta));
            }
            return arrayList2;
        }

        public final FlyweightCapableTreeStructure<LighterASTNode> getTree() {
            return this.tree;
        }
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/FirModifierList$FirPsiModifierList;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/FirModifierList;", "modifierList", "Lorg/jetbrains/kotlin/psi/KtModifierList;", "<init>", "(Lorg/jetbrains/kotlin/psi/KtModifierList;)V", "getModifierList", "()Lorg/jetbrains/kotlin/psi/KtModifierList;", "modifiers", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/FirModifier$FirPsiModifier;", "getModifiers", "()Ljava/util/List;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class FirPsiModifierList extends FirModifierList {
        private final KtModifierList modifierList;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public FirPsiModifierList(KtModifierList ktModifierList) {
            super(null);
            ktModifierList.getClass();
            this.modifierList = ktModifierList;
        }

        public final KtModifierList getModifierList() {
            return this.modifierList;
        }

        @Override // org.jetbrains.kotlin.fir.analysis.checkers.FirModifierList
        public List<FirModifier.FirPsiModifier> getModifiers() {
            ASTNode[] children = this.modifierList.getNode().getChildren(FirKeywordUtilsKt.MODIFIER_KEYWORD_SET);
            children.getClass();
            ArrayList arrayList = new ArrayList(children.length);
            for (ASTNode aSTNode : children) {
                aSTNode.getClass();
                KtModifierKeywordToken elementType = aSTNode.getElementType();
                elementType.getClass();
                arrayList.add(new FirModifier.FirPsiModifier(aSTNode, elementType));
            }
            return arrayList;
        }
    }

    public /* synthetic */ FirModifierList(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public final boolean contains(KtModifierKeywordToken token) {
        token.getClass();
        List<FirModifier<?>> modifiers = getModifiers();
        if ((modifiers instanceof Collection) && modifiers.isEmpty()) {
            return false;
        }
        Iterator<T> it = modifiers.iterator();
        while (it.hasNext()) {
            if (Intrinsics.areEqual(((FirModifier) it.next()).getToken(), token)) {
                return true;
            }
        }
        return false;
    }

    public final FirModifier<?> get(KtModifierKeywordToken token) {
        Object next;
        token.getClass();
        Iterator<T> it = getModifiers().iterator();
        while (it.hasNext()) {
            next = it.next();
            if (Intrinsics.areEqual(((FirModifier) next).getToken(), token)) {
                return (FirModifier) next;
            }
        }
        next = null;
        return (FirModifier) next;
    }

    public abstract List<FirModifier<?>> getModifiers();

    private FirModifierList() {
    }
}
