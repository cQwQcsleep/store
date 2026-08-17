package org.jetbrains.kotlin.fir.analysis;

import com.intellij.lang.LighterASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import com.intellij.util.diff.FlyweightCapableTreeStructure;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import org.jetbrains.kotlin.KtLightSourceElement;
import org.jetbrains.kotlin.KtNodeTypes;
import org.jetbrains.kotlin.KtPsiSourceElement;
import org.jetbrains.kotlin.KtRealPsiSourceElement;
import org.jetbrains.kotlin.KtRealSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirImport;
import org.jetbrains.kotlin.psi.psiUtil.PsiUtilsKt;
import org.jetbrains.kotlin.util.LightTreeUtilsKt;
import org.jetbrains.kotlin.utils.addToStdlib.AddToStdlibKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000`\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a2\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b\u001a2\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00012\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b\u001a8\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\u000b2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b\u001aD\u0010\f\u001a\u00020\r*\u00020\u00012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\u000b2\u0006\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\r0\u000fH\u0086\bø\u0001\u0000\u001aD\u0010\f\u001a\u00020\r*\u00020\u00102\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\u000b2\u0006\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\r0\u000fH\u0086\bø\u0001\u0000\u001aR\u0010\f\u001a\u00020\r*\u00020\u00112\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\u000b2\u0006\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u00132\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\r0\u000fH\u0086\bø\u0001\u0000\u001a\u0081\u0001\u0010\f\u001a\u00020\r\"\u0004\b\u0000\u0010\u00142\u0006\u0010\u0015\u001a\u0002H\u00142\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\u000b2\u0006\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u0002H\u0014\u0012\u0004\u0012\u00020\u00030\u000f2\u0018\u0010\u0017\u001a\u0014\u0012\u0004\u0012\u0002H\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00140\u00180\u000f2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u0002H\u0014\u0012\u0004\u0012\u00020\r0\u000fH\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\u0019\u001a\u0014\u0010\u001a\u001a\u00020\u001b*\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0011H\u0000\u001a\u0014\u0010\u001f\u001a\u0004\u0018\u00010\u0001*\u00020 2\u0006\u0010!\u001a\u00020\u0005\u001a\f\u0010\"\u001a\u0004\u0018\u00010\u0001*\u00020 \"\u0013\u0010\u001d\u001a\u00070\n¢\u0006\u0002\b\u001eX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006#"}, d2 = {"getChild", "Lorg/jetbrains/kotlin/KtSourceElement;", ModuleXmlParser.TYPE, "Lcom/intellij/psi/tree/IElementType;", "index", Argument.Delimiters.none, "depth", "reverse", Argument.Delimiters.none, "types", "Lcom/intellij/psi/tree/TokenSet;", Argument.Delimiters.none, "forEachChildOfType", Argument.Delimiters.none, "processChild", "Lkotlin/Function1;", "Lcom/intellij/psi/PsiElement;", "Lcom/intellij/lang/LighterASTNode;", "treeStructure", "Lcom/intellij/util/diff/FlyweightCapableTreeStructure;", "T", "root", "getElementType", "getChildren", Argument.Delimiters.none, "(Ljava/lang/Object;Ljava/util/Set;IZLkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "buildChildSourceElement", "Lorg/jetbrains/kotlin/KtLightSourceElement;", "childNode", "IMPORT_PARENT_TOKEN_TYPES", "Lorg/jetbrains/annotations/NotNull;", "getSourceForImportSegment", "Lorg/jetbrains/kotlin/fir/declarations/FirImport;", "indexFromLast", "getLastImportedFqNameSegmentSource", "org.jetbrains.kotlin:checkers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirSourceUtilsKt {
    private static final TokenSet IMPORT_PARENT_TOKEN_TYPES;

    static {
        TokenSet tokenSetCreate = TokenSet.create(new IElementType[]{KtNodeTypes.DOT_QUALIFIED_EXPRESSION, KtNodeTypes.REFERENCE_EXPRESSION});
        tokenSetCreate.getClass();
        IMPORT_PARENT_TOKEN_TYPES = tokenSetCreate;
    }

    public static final KtLightSourceElement buildChildSourceElement(KtLightSourceElement ktLightSourceElement, LighterASTNode lighterASTNode) {
        ktLightSourceElement.getClass();
        lighterASTNode.getClass();
        int startOffset = ktLightSourceElement.getStartOffset() - ktLightSourceElement.getLighterASTNode().getStartOffset();
        return new KtLightSourceElement(lighterASTNode, lighterASTNode.getStartOffset() + startOffset, lighterASTNode.getEndOffset() + startOffset, ktLightSourceElement.getTreeStructure(), KtRealSourceElementKind.INSTANCE);
    }

    public static final void forEachChildOfType(KtSourceElement ktSourceElement, Set<? extends IElementType> set, int i, boolean z, Function1<? super KtSourceElement, Unit> function1) {
        ktSourceElement.getClass();
        set.getClass();
        function1.getClass();
        if (ktSourceElement instanceof KtPsiSourceElement) {
            List listMutableListOf = CollectionsKt.mutableListOf(new Pair[]{TuplesKt.to(((KtPsiSourceElement) ktSourceElement).getPsi(), 0)});
            while (true) {
                List list = listMutableListOf;
                if (list.isEmpty()) {
                    return;
                }
                Pair pair = (Pair) AddToStdlibKt.popLast(listMutableListOf);
                Object objComponent1 = pair.component1();
                int iIntValue = ((Number) pair.component2()).intValue();
                if (iIntValue != 0) {
                    PsiElement psiElement = (PsiElement) objComponent1;
                    IElementType elementType = psiElement.getNode().getElementType();
                    elementType.getClass();
                    if (set.contains(elementType)) {
                        if (KtRealSourceElementKind.INSTANCE == null) {
                            bu8.a();
                            return;
                        }
                        function1.invoke(new KtRealPsiSourceElement(psiElement));
                    }
                }
                if (iIntValue != i) {
                    List list2 = SequencesKt.toList(PsiUtilsKt.getAllChildren((PsiElement) objComponent1));
                    if (!z) {
                        list2 = CollectionsKt.asReversed(list2);
                    }
                    Iterator it = list2.iterator();
                    while (it.hasNext()) {
                        list.add(TuplesKt.to(it.next(), Integer.valueOf(iIntValue + 1)));
                    }
                }
            }
        } else {
            if (!(ktSourceElement instanceof KtLightSourceElement)) {
                bu8.a();
                return;
            }
            KtLightSourceElement ktLightSourceElement = (KtLightSourceElement) ktSourceElement;
            LighterASTNode lighterASTNode = ktLightSourceElement.getLighterASTNode();
            FlyweightCapableTreeStructure treeStructure = ktLightSourceElement.getTreeStructure();
            List listMutableListOf2 = CollectionsKt.mutableListOf(new Pair[]{TuplesKt.to(lighterASTNode, 0)});
            while (true) {
                List list3 = listMutableListOf2;
                if (list3.isEmpty()) {
                    return;
                }
                Pair pair2 = (Pair) AddToStdlibKt.popLast(listMutableListOf2);
                Object objComponent2 = pair2.component1();
                int iIntValue2 = ((Number) pair2.component2()).intValue();
                if (iIntValue2 != 0) {
                    LighterASTNode lighterASTNode2 = (LighterASTNode) objComponent2;
                    IElementType tokenType = lighterASTNode2.getTokenType();
                    tokenType.getClass();
                    if (set.contains(tokenType)) {
                        function1.invoke(new KtLightSourceElement(lighterASTNode2, lighterASTNode2.getStartOffset(), lighterASTNode2.getEndOffset(), ktLightSourceElement.getTreeStructure(), KtRealSourceElementKind.INSTANCE));
                    }
                }
                if (iIntValue2 != i) {
                    List children = LightTreeUtilsKt.getChildren((LighterASTNode) objComponent2, treeStructure);
                    if (!z) {
                        children = CollectionsKt.asReversed(children);
                    }
                    Iterator it2 = children.iterator();
                    while (it2.hasNext()) {
                        list3.add(TuplesKt.to(it2.next(), Integer.valueOf(iIntValue2 + 1)));
                    }
                }
            }
        }
    }

    public static /* synthetic */ void forEachChildOfType$default(KtSourceElement ktSourceElement, Set set, int i, boolean z, Function1 function1, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            z = false;
        }
        ktSourceElement.getClass();
        set.getClass();
        function1.getClass();
        if (ktSourceElement instanceof KtPsiSourceElement) {
            List listMutableListOf = CollectionsKt.mutableListOf(new Pair[]{TuplesKt.to(((KtPsiSourceElement) ktSourceElement).getPsi(), 0)});
            while (true) {
                List list = listMutableListOf;
                if (list.isEmpty()) {
                    return;
                }
                Pair pair = (Pair) AddToStdlibKt.popLast(listMutableListOf);
                Object objComponent1 = pair.component1();
                int iIntValue = ((Number) pair.component2()).intValue();
                if (iIntValue != 0) {
                    PsiElement psiElement = (PsiElement) objComponent1;
                    IElementType elementType = psiElement.getNode().getElementType();
                    elementType.getClass();
                    if (set.contains(elementType)) {
                        if (KtRealSourceElementKind.INSTANCE == null) {
                            bu8.a();
                            return;
                        }
                        function1.invoke(new KtRealPsiSourceElement(psiElement));
                    }
                }
                if (iIntValue != i) {
                    List list2 = SequencesKt.toList(PsiUtilsKt.getAllChildren((PsiElement) objComponent1));
                    if (!z) {
                        list2 = CollectionsKt.asReversed(list2);
                    }
                    Iterator it = list2.iterator();
                    while (it.hasNext()) {
                        list.add(TuplesKt.to(it.next(), Integer.valueOf(iIntValue + 1)));
                    }
                }
            }
        } else {
            if (!(ktSourceElement instanceof KtLightSourceElement)) {
                bu8.a();
                return;
            }
            KtLightSourceElement ktLightSourceElement = (KtLightSourceElement) ktSourceElement;
            LighterASTNode lighterASTNode = ktLightSourceElement.getLighterASTNode();
            FlyweightCapableTreeStructure treeStructure = ktLightSourceElement.getTreeStructure();
            List listMutableListOf2 = CollectionsKt.mutableListOf(new Pair[]{TuplesKt.to(lighterASTNode, 0)});
            while (true) {
                List list3 = listMutableListOf2;
                if (list3.isEmpty()) {
                    return;
                }
                Pair pair2 = (Pair) AddToStdlibKt.popLast(listMutableListOf2);
                Object objComponent2 = pair2.component1();
                int iIntValue2 = ((Number) pair2.component2()).intValue();
                if (iIntValue2 != 0) {
                    LighterASTNode lighterASTNode2 = (LighterASTNode) objComponent2;
                    IElementType tokenType = lighterASTNode2.getTokenType();
                    tokenType.getClass();
                    if (set.contains(tokenType)) {
                        function1.invoke(new KtLightSourceElement(lighterASTNode2, lighterASTNode2.getStartOffset(), lighterASTNode2.getEndOffset(), ktLightSourceElement.getTreeStructure(), KtRealSourceElementKind.INSTANCE));
                    }
                }
                if (iIntValue2 != i) {
                    List children = LightTreeUtilsKt.getChildren((LighterASTNode) objComponent2, treeStructure);
                    if (!z) {
                        children = CollectionsKt.asReversed(children);
                    }
                    Iterator it2 = children.iterator();
                    while (it2.hasNext()) {
                        list3.add(TuplesKt.to(it2.next(), Integer.valueOf(iIntValue2 + 1)));
                    }
                }
            }
        }
    }

    public static final KtSourceElement getChild(KtSourceElement ktSourceElement, Set<? extends IElementType> set, int i, int i2, boolean z) {
        ktSourceElement.getClass();
        set.getClass();
        if (ktSourceElement instanceof KtPsiSourceElement) {
            List listMutableListOf = CollectionsKt.mutableListOf(new Pair[]{TuplesKt.to(((KtPsiSourceElement) ktSourceElement).getPsi(), 0)});
            int i3 = i;
            while (true) {
                List list = listMutableListOf;
                if (list.isEmpty()) {
                    break;
                }
                Pair pair = (Pair) AddToStdlibKt.popLast(listMutableListOf);
                Object objComponent1 = pair.component1();
                int iIntValue = ((Number) pair.component2()).intValue();
                if (iIntValue != 0) {
                    PsiElement psiElement = (PsiElement) objComponent1;
                    IElementType elementType = psiElement.getNode().getElementType();
                    elementType.getClass();
                    if (set.contains(elementType)) {
                        if (KtRealSourceElementKind.INSTANCE == null) {
                            bu8.a();
                            return null;
                        }
                        KtRealPsiSourceElement ktRealPsiSourceElement = new KtRealPsiSourceElement(psiElement);
                        int i4 = i3 - 1;
                        if (i3 == 0) {
                            return ktRealPsiSourceElement;
                        }
                        i3 = i4;
                    }
                }
                if (iIntValue != i2) {
                    List list2 = SequencesKt.toList(PsiUtilsKt.getAllChildren((PsiElement) objComponent1));
                    if (!z) {
                        list2 = CollectionsKt.asReversed(list2);
                    }
                    Iterator it = list2.iterator();
                    while (it.hasNext()) {
                        list.add(TuplesKt.to(it.next(), Integer.valueOf(iIntValue + 1)));
                    }
                }
            }
        } else {
            if (!(ktSourceElement instanceof KtLightSourceElement)) {
                bu8.a();
                return null;
            }
            KtLightSourceElement ktLightSourceElement = (KtLightSourceElement) ktSourceElement;
            LighterASTNode lighterASTNode = ktLightSourceElement.getLighterASTNode();
            FlyweightCapableTreeStructure treeStructure = ktLightSourceElement.getTreeStructure();
            List listMutableListOf2 = CollectionsKt.mutableListOf(new Pair[]{TuplesKt.to(lighterASTNode, 0)});
            int i5 = i;
            while (true) {
                List list3 = listMutableListOf2;
                if (list3.isEmpty()) {
                    break;
                }
                Pair pair2 = (Pair) AddToStdlibKt.popLast(listMutableListOf2);
                Object objComponent2 = pair2.component1();
                int iIntValue2 = ((Number) pair2.component2()).intValue();
                if (iIntValue2 != 0) {
                    LighterASTNode lighterASTNode2 = (LighterASTNode) objComponent2;
                    IElementType tokenType = lighterASTNode2.getTokenType();
                    tokenType.getClass();
                    if (set.contains(tokenType)) {
                        KtLightSourceElement ktLightSourceElement2 = new KtLightSourceElement(lighterASTNode2, lighterASTNode2.getStartOffset(), lighterASTNode2.getEndOffset(), ktLightSourceElement.getTreeStructure(), KtRealSourceElementKind.INSTANCE);
                        int i6 = i5 - 1;
                        if (i5 == 0) {
                            return ktLightSourceElement2;
                        }
                        i5 = i6;
                    }
                }
                if (iIntValue2 != i2) {
                    List children = LightTreeUtilsKt.getChildren((LighterASTNode) objComponent2, treeStructure);
                    if (!z) {
                        children = CollectionsKt.asReversed(children);
                    }
                    Iterator it2 = children.iterator();
                    while (it2.hasNext()) {
                        list3.add(TuplesKt.to(it2.next(), Integer.valueOf(iIntValue2 + 1)));
                    }
                }
            }
        }
        return null;
    }

    public static /* synthetic */ KtSourceElement getChild$default(KtSourceElement ktSourceElement, IElementType iElementType, int i, int i2, boolean z, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = -1;
        }
        if ((i3 & 8) != 0) {
            z = false;
        }
        return getChild(ktSourceElement, iElementType, i, i2, z);
    }

    public static final KtSourceElement getLastImportedFqNameSegmentSource(FirImport firImport) {
        firImport.getClass();
        KtSourceElement source = firImport.getSource();
        if (source == null) {
            return null;
        }
        IElementType iElementType = KtNodeTypes.REFERENCE_EXPRESSION;
        iElementType.getClass();
        return getChild$default(source, iElementType, 0, 0, true, 6, (Object) null);
    }

    public static final KtSourceElement getSourceForImportSegment(FirImport firImport, int i) {
        firImport.getClass();
        KtSourceElement source = firImport.getSource();
        if (source == null) {
            return null;
        }
        int i2 = i + 1;
        KtSourceElement child$default = source;
        for (int i3 = 0; i3 < i2; i3++) {
            child$default = getChild$default(child$default, IMPORT_PARENT_TOKEN_TYPES, 0, 1, false, 10, (Object) null);
            if (child$default == null) {
                return null;
            }
        }
        IElementType elementType = child$default.getElementType();
        IElementType iElementType = KtNodeTypes.REFERENCE_EXPRESSION;
        KtSourceElement ktSourceElement = Intrinsics.areEqual(elementType, iElementType) ? child$default : null;
        if (ktSourceElement != null) {
            return ktSourceElement;
        }
        iElementType.getClass();
        return getChild$default(child$default, iElementType, 0, 1, true, 2, (Object) null);
    }

    public static /* synthetic */ KtSourceElement getChild$default(KtSourceElement ktSourceElement, TokenSet tokenSet, int i, int i2, boolean z, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = -1;
        }
        if ((i3 & 8) != 0) {
            z = false;
        }
        return getChild(ktSourceElement, tokenSet, i, i2, z);
    }

    public static /* synthetic */ KtSourceElement getChild$default(KtSourceElement ktSourceElement, Set set, int i, int i2, boolean z, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = -1;
        }
        if ((i3 & 8) != 0) {
            z = false;
        }
        return getChild(ktSourceElement, (Set<? extends IElementType>) set, i, i2, z);
    }

    public static final <T> void forEachChildOfType(T t, Set<? extends IElementType> set, int i, boolean z, Function1<? super T, ? extends IElementType> function1, Function1<? super T, ? extends List<? extends T>> function2, Function1<? super T, Unit> function3) {
        set.getClass();
        function1.getClass();
        function2.getClass();
        function3.getClass();
        List listMutableListOf = CollectionsKt.mutableListOf(new Pair[]{TuplesKt.to(t, 0)});
        while (true) {
            List list = listMutableListOf;
            if (list.isEmpty()) {
                return;
            }
            Pair pair = (Pair) AddToStdlibKt.popLast(listMutableListOf);
            Object objComponent1 = pair.component1();
            int iIntValue = ((Number) pair.component2()).intValue();
            if (iIntValue != 0 && set.contains(function1.invoke(objComponent1))) {
                function3.invoke(objComponent1);
            }
            if (iIntValue != i) {
                Object objInvoke = function2.invoke(objComponent1);
                if (!z) {
                    objInvoke = CollectionsKt.asReversed((List) objInvoke);
                }
                Iterator<T> it = ((Iterable) objInvoke).iterator();
                while (it.hasNext()) {
                    list.add(TuplesKt.to(it.next(), Integer.valueOf(iIntValue + 1)));
                }
            }
        }
    }

    public static /* synthetic */ void forEachChildOfType$default(LighterASTNode lighterASTNode, Set set, int i, boolean z, FlyweightCapableTreeStructure flyweightCapableTreeStructure, Function1 function1, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            z = false;
        }
        lighterASTNode.getClass();
        set.getClass();
        flyweightCapableTreeStructure.getClass();
        function1.getClass();
        List listMutableListOf = CollectionsKt.mutableListOf(new Pair[]{TuplesKt.to(lighterASTNode, 0)});
        while (true) {
            List list = listMutableListOf;
            if (list.isEmpty()) {
                return;
            }
            Pair pair = (Pair) AddToStdlibKt.popLast(listMutableListOf);
            Object objComponent1 = pair.component1();
            int iIntValue = ((Number) pair.component2()).intValue();
            if (iIntValue != 0) {
                IElementType tokenType = ((LighterASTNode) objComponent1).getTokenType();
                tokenType.getClass();
                if (set.contains(tokenType)) {
                    function1.invoke(objComponent1);
                }
            }
            if (iIntValue != i) {
                List children = LightTreeUtilsKt.getChildren((LighterASTNode) objComponent1, flyweightCapableTreeStructure);
                if (!z) {
                    children = CollectionsKt.asReversed(children);
                }
                Iterator it = children.iterator();
                while (it.hasNext()) {
                    list.add(TuplesKt.to(it.next(), Integer.valueOf(iIntValue + 1)));
                }
            }
        }
    }

    public static final void forEachChildOfType(PsiElement psiElement, Set<? extends IElementType> set, int i, boolean z, Function1<? super PsiElement, Unit> function1) {
        psiElement.getClass();
        set.getClass();
        function1.getClass();
        List listMutableListOf = CollectionsKt.mutableListOf(new Pair[]{TuplesKt.to(psiElement, 0)});
        while (true) {
            List list = listMutableListOf;
            if (list.isEmpty()) {
                return;
            }
            Pair pair = (Pair) AddToStdlibKt.popLast(listMutableListOf);
            Object objComponent1 = pair.component1();
            int iIntValue = ((Number) pair.component2()).intValue();
            if (iIntValue != 0) {
                IElementType elementType = ((PsiElement) objComponent1).getNode().getElementType();
                elementType.getClass();
                if (set.contains(elementType)) {
                    function1.invoke(objComponent1);
                }
            }
            if (iIntValue != i) {
                List list2 = SequencesKt.toList(PsiUtilsKt.getAllChildren((PsiElement) objComponent1));
                if (!z) {
                    list2 = CollectionsKt.asReversed(list2);
                }
                Iterator it = list2.iterator();
                while (it.hasNext()) {
                    list.add(TuplesKt.to(it.next(), Integer.valueOf(iIntValue + 1)));
                }
            }
        }
    }

    public static final KtSourceElement getChild(KtSourceElement ktSourceElement, TokenSet tokenSet, int i, int i2, boolean z) {
        ktSourceElement.getClass();
        tokenSet.getClass();
        IElementType[] types = tokenSet.getTypes();
        types.getClass();
        return getChild(ktSourceElement, (Set<? extends IElementType>) ArraysKt.toSet(types), i, i2, z);
    }

    public static final KtSourceElement getChild(KtSourceElement ktSourceElement, IElementType iElementType, int i, int i2, boolean z) {
        ktSourceElement.getClass();
        iElementType.getClass();
        return getChild(ktSourceElement, (Set<? extends IElementType>) SetsKt.setOf(iElementType), i, i2, z);
    }

    public static /* synthetic */ void forEachChildOfType$default(Object obj, Set set, int i, boolean z, Function1 function1, Function1 function2, Function1 function3, int i2, Object obj2) {
        if ((i2 & 8) != 0) {
            z = false;
        }
        set.getClass();
        function1.getClass();
        function2.getClass();
        function3.getClass();
        List listMutableListOf = CollectionsKt.mutableListOf(new Pair[]{TuplesKt.to(obj, 0)});
        while (true) {
            List list = listMutableListOf;
            if (list.isEmpty()) {
                return;
            }
            Pair pair = (Pair) AddToStdlibKt.popLast(listMutableListOf);
            Object objComponent1 = pair.component1();
            int iIntValue = ((Number) pair.component2()).intValue();
            if (iIntValue != 0 && set.contains(function1.invoke(objComponent1))) {
                function3.invoke(objComponent1);
            }
            if (iIntValue != i) {
                Object objInvoke = function2.invoke(objComponent1);
                if (!z) {
                    objInvoke = CollectionsKt.asReversed((List) objInvoke);
                }
                Iterator it = ((Iterable) objInvoke).iterator();
                while (it.hasNext()) {
                    list.add(TuplesKt.to(it.next(), Integer.valueOf(iIntValue + 1)));
                }
            }
        }
    }

    public static final void forEachChildOfType(LighterASTNode lighterASTNode, Set<? extends IElementType> set, int i, boolean z, FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure, Function1<? super LighterASTNode, Unit> function1) {
        lighterASTNode.getClass();
        set.getClass();
        flyweightCapableTreeStructure.getClass();
        function1.getClass();
        List listMutableListOf = CollectionsKt.mutableListOf(new Pair[]{TuplesKt.to(lighterASTNode, 0)});
        while (true) {
            List list = listMutableListOf;
            if (list.isEmpty()) {
                return;
            }
            Pair pair = (Pair) AddToStdlibKt.popLast(listMutableListOf);
            Object objComponent1 = pair.component1();
            int iIntValue = ((Number) pair.component2()).intValue();
            if (iIntValue != 0) {
                IElementType tokenType = ((LighterASTNode) objComponent1).getTokenType();
                tokenType.getClass();
                if (set.contains(tokenType)) {
                    function1.invoke(objComponent1);
                }
            }
            if (iIntValue != i) {
                List children = LightTreeUtilsKt.getChildren((LighterASTNode) objComponent1, flyweightCapableTreeStructure);
                if (!z) {
                    children = CollectionsKt.asReversed(children);
                }
                Iterator it = children.iterator();
                while (it.hasNext()) {
                    list.add(TuplesKt.to(it.next(), Integer.valueOf(iIntValue + 1)));
                }
            }
        }
    }

    public static /* synthetic */ void forEachChildOfType$default(PsiElement psiElement, Set set, int i, boolean z, Function1 function1, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            z = false;
        }
        psiElement.getClass();
        set.getClass();
        function1.getClass();
        List listMutableListOf = CollectionsKt.mutableListOf(new Pair[]{TuplesKt.to(psiElement, 0)});
        while (true) {
            List list = listMutableListOf;
            if (list.isEmpty()) {
                return;
            }
            Pair pair = (Pair) AddToStdlibKt.popLast(listMutableListOf);
            Object objComponent1 = pair.component1();
            int iIntValue = ((Number) pair.component2()).intValue();
            if (iIntValue != 0) {
                IElementType elementType = ((PsiElement) objComponent1).getNode().getElementType();
                elementType.getClass();
                if (set.contains(elementType)) {
                    function1.invoke(objComponent1);
                }
            }
            if (iIntValue != i) {
                List list2 = SequencesKt.toList(PsiUtilsKt.getAllChildren((PsiElement) objComponent1));
                if (!z) {
                    list2 = CollectionsKt.asReversed(list2);
                }
                Iterator it = list2.iterator();
                while (it.hasNext()) {
                    list.add(TuplesKt.to(it.next(), Integer.valueOf(iIntValue + 1)));
                }
            }
        }
    }
}
