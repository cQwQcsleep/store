package org.jetbrains.kotlin;

import com.intellij.lang.LighterASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.util.diff.FlyweightCapableTreeStructure;
import defpackage.f2f;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.util.OperatorNameConventions;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000`\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\r\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u001a\n\u0010\u0006\u001a\u00020\u0001*\u00020\u0001\u001a\u0017\u0010\u0010\u001a\u00020\u0011*\u00020\b2\b\b\u0002\u0010\u0012\u001a\u00020\u0013H\u0086\b\u001a9\u0010\u0014\u001a\u00020\u0015*\u00020\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00160\u00182\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u001aH\u0086\b\u001a\u0016\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!\"\u0019\u0010\u0007\u001a\u0004\u0018\u00010\b*\u0004\u0018\u00010\t8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b\"\u0019\u0010\f\u001a\u0004\u0018\u00010\r*\u0004\u0018\u00010\u00018F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f¨\u0006\""}, d2 = {"fakeElement", "Lorg/jetbrains/kotlin/KtSourceElement;", "newKind", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", "offsetStrategy", "Lorg/jetbrains/kotlin/KtSourceElementOffsetStrategy;", "realElement", "psi", "Lcom/intellij/psi/PsiElement;", "Lorg/jetbrains/kotlin/AbstractKtSourceElement;", "getPsi", "(Lorg/jetbrains/kotlin/AbstractKtSourceElement;)Lcom/intellij/psi/PsiElement;", "text", Argument.Delimiters.none, "getText", "(Lorg/jetbrains/kotlin/KtSourceElement;)Ljava/lang/CharSequence;", "toKtPsiSourceElement", "Lorg/jetbrains/kotlin/KtPsiSourceElement;", "kind", "Lorg/jetbrains/kotlin/KtSourceElementKind;", "toKtLightSourceElement", "Lorg/jetbrains/kotlin/KtLightSourceElement;", "Lcom/intellij/lang/LighterASTNode;", "tree", "Lcom/intellij/util/diff/FlyweightCapableTreeStructure;", "startOffset", Argument.Delimiters.none, "endOffset", "sourceKindForIncOrDec", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$DesugaredIncrementOrDecrement;", "operation", "Lorg/jetbrains/kotlin/name/Name;", "isPrefix", Argument.Delimiters.none, "org.jetbrains.kotlin:frontend.common"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class KtSourceElementKt {
    public static final KtSourceElement fakeElement(KtSourceElement ktSourceElement, KtFakeSourceElementKind ktFakeSourceElementKind, KtSourceElementOffsetStrategy ktSourceElementOffsetStrategy) {
        Pair pair;
        ktSourceElement.getClass();
        ktFakeSourceElementKind.getClass();
        ktSourceElementOffsetStrategy.getClass();
        if (Intrinsics.areEqual(ktSourceElement.getKind(), ktFakeSourceElementKind)) {
            return ktSourceElement;
        }
        if (ktSourceElement instanceof KtLightSourceElement) {
            if (ktSourceElementOffsetStrategy instanceof KtSourceElementOffsetStrategy.Custom) {
                KtSourceElementOffsetStrategy.Custom custom = (KtSourceElementOffsetStrategy.Custom) ktSourceElementOffsetStrategy;
                pair = TuplesKt.to(Integer.valueOf(custom.getStartOffset()), Integer.valueOf(custom.getEndOffset()));
            } else {
                KtLightSourceElement ktLightSourceElement = (KtLightSourceElement) ktSourceElement;
                pair = TuplesKt.to(Integer.valueOf(ktLightSourceElement.getStartOffset()), Integer.valueOf(ktLightSourceElement.getEndOffset()));
            }
            KtLightSourceElement ktLightSourceElement2 = (KtLightSourceElement) ktSourceElement;
            return new KtLightSourceElement(ktLightSourceElement2.getLighterASTNode(), ((Number) pair.component1()).intValue(), ((Number) pair.component2()).intValue(), ktLightSourceElement2.getTreeStructure(), ktFakeSourceElementKind);
        }
        if (!(ktSourceElement instanceof KtPsiSourceElement)) {
            bu8.a();
            return null;
        }
        if (ktSourceElementOffsetStrategy instanceof KtSourceElementOffsetStrategy.Default) {
            return new KtFakePsiSourceElement(((KtPsiSourceElement) ktSourceElement).getPsi(), ktFakeSourceElementKind);
        }
        if (ktSourceElementOffsetStrategy instanceof KtSourceElementOffsetStrategy.Custom) {
            return new KtFakePsiSourceElementWithCustomOffsetStrategy(((KtPsiSourceElement) ktSourceElement).getPsi(), ktFakeSourceElementKind, (KtSourceElementOffsetStrategy.Custom) ktSourceElementOffsetStrategy);
        }
        bu8.a();
        return null;
    }

    public static /* synthetic */ KtSourceElement fakeElement$default(KtSourceElement ktSourceElement, KtFakeSourceElementKind ktFakeSourceElementKind, KtSourceElementOffsetStrategy ktSourceElementOffsetStrategy, int i, Object obj) {
        if ((i & 2) != 0) {
            ktSourceElementOffsetStrategy = KtSourceElementOffsetStrategy.Default.INSTANCE;
        }
        return fakeElement(ktSourceElement, ktFakeSourceElementKind, ktSourceElementOffsetStrategy);
    }

    public static final PsiElement getPsi(AbstractKtSourceElement abstractKtSourceElement) {
        KtPsiSourceElement ktPsiSourceElement = abstractKtSourceElement instanceof KtPsiSourceElement ? (KtPsiSourceElement) abstractKtSourceElement : null;
        if (ktPsiSourceElement != null) {
            return ktPsiSourceElement.getPsi();
        }
        return null;
    }

    public static final CharSequence getText(KtSourceElement ktSourceElement) {
        if (ktSourceElement instanceof KtPsiSourceElement) {
            return ((KtPsiSourceElement) ktSourceElement).getPsi().getText();
        }
        if (!(ktSourceElement instanceof KtLightSourceElement)) {
            return null;
        }
        KtLightSourceElement ktLightSourceElement = (KtLightSourceElement) ktSourceElement;
        return ktLightSourceElement.getTreeStructure().toString(ktLightSourceElement.getLighterASTNode());
    }

    public static final KtSourceElement realElement(KtSourceElement ktSourceElement) {
        ktSourceElement.getClass();
        if (ktSourceElement instanceof KtRealPsiSourceElement) {
            return ktSourceElement;
        }
        if (ktSourceElement instanceof KtLightSourceElement) {
            KtLightSourceElement ktLightSourceElement = (KtLightSourceElement) ktSourceElement;
            return new KtLightSourceElement(ktLightSourceElement.getLighterASTNode(), ktLightSourceElement.getStartOffset(), ktLightSourceElement.getEndOffset(), ktLightSourceElement.getTreeStructure(), KtRealSourceElementKind.INSTANCE);
        }
        if (ktSourceElement instanceof KtPsiSourceElement) {
            return new KtRealPsiSourceElement(((KtPsiSourceElement) ktSourceElement).getPsi());
        }
        bu8.a();
        return null;
    }

    public static final KtFakeSourceElementKind.DesugaredIncrementOrDecrement sourceKindForIncOrDec(Name name, boolean z) {
        name.getClass();
        if (Intrinsics.areEqual(name, OperatorNameConventions.INC)) {
            return z ? KtFakeSourceElementKind.DesugaredPrefixInc.INSTANCE : KtFakeSourceElementKind.DesugaredPostfixInc.INSTANCE;
        }
        if (Intrinsics.areEqual(name, OperatorNameConventions.DEC)) {
            return z ? KtFakeSourceElementKind.DesugaredPrefixDec.INSTANCE : KtFakeSourceElementKind.DesugaredPostfixDec.INSTANCE;
        }
        f2f.a("Unexpected operator: ", name.getIdentifier());
        return null;
    }

    public static final KtLightSourceElement toKtLightSourceElement(LighterASTNode lighterASTNode, FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure, KtSourceElementKind ktSourceElementKind, int i, int i2) {
        lighterASTNode.getClass();
        flyweightCapableTreeStructure.getClass();
        ktSourceElementKind.getClass();
        return new KtLightSourceElement(lighterASTNode, i, i2, flyweightCapableTreeStructure, ktSourceElementKind);
    }

    public static /* synthetic */ KtLightSourceElement toKtLightSourceElement$default(LighterASTNode lighterASTNode, FlyweightCapableTreeStructure flyweightCapableTreeStructure, KtSourceElementKind ktSourceElementKind, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            ktSourceElementKind = KtRealSourceElementKind.INSTANCE;
        }
        KtSourceElementKind ktSourceElementKind2 = ktSourceElementKind;
        if ((i3 & 4) != 0) {
            i = lighterASTNode.getStartOffset();
        }
        int i4 = i;
        if ((i3 & 8) != 0) {
            i2 = lighterASTNode.getEndOffset();
        }
        lighterASTNode.getClass();
        flyweightCapableTreeStructure.getClass();
        ktSourceElementKind2.getClass();
        return new KtLightSourceElement(lighterASTNode, i4, i2, flyweightCapableTreeStructure, ktSourceElementKind2);
    }

    public static final KtPsiSourceElement toKtPsiSourceElement(PsiElement psiElement, KtSourceElementKind ktSourceElementKind) {
        psiElement.getClass();
        ktSourceElementKind.getClass();
        if (ktSourceElementKind instanceof KtRealSourceElementKind) {
            return new KtRealPsiSourceElement(psiElement);
        }
        if (ktSourceElementKind instanceof KtFakeSourceElementKind) {
            return new KtFakePsiSourceElement(psiElement, (KtFakeSourceElementKind) ktSourceElementKind);
        }
        bu8.a();
        return null;
    }

    public static /* synthetic */ KtPsiSourceElement toKtPsiSourceElement$default(PsiElement psiElement, KtSourceElementKind ktSourceElementKind, int i, Object obj) {
        if ((i & 1) != 0) {
            ktSourceElementKind = KtRealSourceElementKind.INSTANCE;
        }
        psiElement.getClass();
        ktSourceElementKind.getClass();
        if (ktSourceElementKind instanceof KtRealSourceElementKind) {
            return new KtRealPsiSourceElement(psiElement);
        }
        if (ktSourceElementKind instanceof KtFakeSourceElementKind) {
            return new KtFakePsiSourceElement(psiElement, (KtFakeSourceElementKind) ktSourceElementKind);
        }
        bu8.a();
        return null;
    }
}
