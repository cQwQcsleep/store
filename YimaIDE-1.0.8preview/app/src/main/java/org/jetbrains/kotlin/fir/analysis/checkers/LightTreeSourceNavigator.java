package org.jetbrains.kotlin.fir.analysis.checkers;

import com.intellij.lang.LighterASTNode;
import com.intellij.openapi.util.Ref;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.diff.FlyweightCapableTreeStructure;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtNodeTypes;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategiesKt;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirEnumEntry;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.lexer.KtTokens;
import org.jetbrains.kotlin.psi.stubs.elements.KtDotQualifiedExpressionElementType;
import org.jetbrains.kotlin.psi.stubs.elements.KtNameReferenceExpressionElementType;
import org.jetbrains.kotlin.psi.stubs.elements.KtTypeProjectionElementType;
import org.jetbrains.kotlin.util.LightTreeUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0012\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J-\u0010\u0004\u001a\u0004\u0018\u0001H\u0005\"\u0004\b\u0000\u0010\u0005*\u00020\u00062\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u0002H\u00050\bH\u0002¢\u0006\u0002\u0010\nJ\f\u0010\u000b\u001a\u00020\f*\u00020\rH\u0016J\f\u0010\u000e\u001a\u00020\f*\u00020\rH\u0016J\u000e\u0010\u000f\u001a\u0004\u0018\u00010\u0010*\u00020\tH\u0016J\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0010*\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u0013H\u0002J\u000e\u0010\u0014\u001a\u0004\u0018\u00010\u0015*\u00020\u0016H\u0016J\f\u0010\u0017\u001a\u00020\f*\u00020\u0018H\u0016J\f\u0010\u0019\u001a\u00020\f*\u00020\rH\u0016J0\u0010\u001a\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u001b\u001a\u00020\t2\u0006\u0010\u001c\u001a\u00020\u00112\u0014\u0010\u001d\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u001f0\u001eH\u0002J\u0010\u0010 \u001a\u0004\u0018\u00010\u0011*\u0004\u0018\u00010\tH\u0002J\u0013\u0010!\u001a\u0004\u0018\u00010\f*\u00020\"H\u0016¢\u0006\u0002\u0010#J\u0013\u0010$\u001a\u0004\u0018\u00010\f*\u00020\"H\u0016¢\u0006\u0002\u0010#¨\u0006%"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/LightTreeSourceNavigator;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/SourceNavigator;", "<init>", "()V", "withSource", "T", "Lorg/jetbrains/kotlin/fir/FirElement;", "f", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/KtSourceElement;", "(Lorg/jetbrains/kotlin/fir/FirElement;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "isInConstructorCallee", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "isInTypeConstraint", "getRawIdentifier", Argument.Delimiters.none, "Lcom/intellij/lang/LighterASTNode;", "treeStructure", "Lcom/intellij/util/diff/FlyweightCapableTreeStructure;", "getRawName", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "isCatchElementParameter", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirValueParameterSymbol;", "isRedundantNullable", "getNullableChild", "source", "node", "ref", "Lcom/intellij/openapi/util/Ref;", Argument.Delimiters.none, "getParentOfParent", "hasBody", "Lorg/jetbrains/kotlin/fir/declarations/FirEnumEntry;", "(Lorg/jetbrains/kotlin/fir/declarations/FirEnumEntry;)Ljava/lang/Boolean;", "hasInitializer", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
class LightTreeSourceNavigator implements SourceNavigator {
    public static boolean a(KtSourceElement ktSourceElement) {
        ktSourceElement.getClass();
        LighterASTNode lighterASTNode = (LighterASTNode) ktSourceElement.getTreeStructure().getParent(ktSourceElement.getLighterASTNode());
        return Intrinsics.areEqual(lighterASTNode != null ? lighterASTNode.getTokenType() : null, KtNodeTypes.CONSTRUCTOR_CALLEE);
    }

    private final LighterASTNode getNullableChild(KtSourceElement source, LighterASTNode node, Ref<LighterASTNode[]> ref) {
        source.getTreeStructure().getChildren(node, ref);
        Object obj = ref.get();
        obj.getClass();
        LighterASTNode lighterASTNode = (LighterASTNode) ArraysKt.firstOrNull((Object[]) obj);
        if (lighterASTNode != null && Intrinsics.areEqual(lighterASTNode.getTokenType(), KtNodeTypes.NULLABLE_TYPE)) {
            return lighterASTNode;
        }
        return null;
    }

    private final LighterASTNode getParentOfParent(KtSourceElement ktSourceElement) {
        if (ktSourceElement == null) {
            return null;
        }
        Object parent = ktSourceElement.getTreeStructure().getParent(ktSourceElement.getLighterASTNode());
        LighterASTNode lighterASTNode = (LighterASTNode) parent;
        if (lighterASTNode != null) {
            parent = ktSourceElement.getTreeStructure().getParent(lighterASTNode);
        }
        return (LighterASTNode) parent;
    }

    private final CharSequence getRawIdentifier(LighterASTNode lighterASTNode, FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure) {
        IElementType tokenType = lighterASTNode.getTokenType();
        if ((tokenType instanceof KtNameReferenceExpressionElementType) || Intrinsics.areEqual(tokenType, KtTokens.IDENTIFIER)) {
            return lighterASTNode.toString();
        }
        if (tokenType instanceof KtTypeProjectionElementType) {
            return CollectionsKt.last(LightTreeUtilsKt.getChildren(lighterASTNode, flyweightCapableTreeStructure)).toString();
        }
        if ((tokenType instanceof KtDotQualifiedExpressionElementType) || Intrinsics.areEqual(tokenType, KtTokens.SAFE_ACCESS)) {
            return getRawIdentifier((LighterASTNode) CollectionsKt.last(LightTreeUtilsKt.getChildren(lighterASTNode, flyweightCapableTreeStructure)), flyweightCapableTreeStructure);
        }
        return null;
    }

    private final <T> T withSource(FirElement firElement, Function1<? super KtSourceElement, ? extends T> function1) {
        KtSourceElement source = firElement.getSource();
        if (source != null) {
            return (T) function1.invoke(source);
        }
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.SourceNavigator
    public String getRawName(FirDeclaration firDeclaration) {
        LighterASTNode lighterASTNodeNameIdentifier;
        firDeclaration.getClass();
        KtSourceElement source = firDeclaration.getSource();
        if (source == null || (lighterASTNodeNameIdentifier = LightTreePositioningStrategiesKt.nameIdentifier(source.getTreeStructure(), source.getLighterASTNode())) == null) {
            return null;
        }
        return lighterASTNodeNameIdentifier.toString();
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.SourceNavigator
    public Boolean hasBody(FirEnumEntry firEnumEntry) {
        firEnumEntry.getClass();
        KtSourceElement source = firEnumEntry.getSource();
        if (source == null) {
            return null;
        }
        List children = LightTreeUtilsKt.getChildren(source.getLighterASTNode(), source.getTreeStructure());
        boolean z = false;
        if (!(children instanceof Collection) || !children.isEmpty()) {
            Iterator it = children.iterator();
            while (it.hasNext()) {
                if (Intrinsics.areEqual(((LighterASTNode) it.next()).getTokenType(), KtNodeTypes.CLASS_BODY)) {
                    z = true;
                    break;
                }
            }
        }
        return Boolean.valueOf(z);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.SourceNavigator
    public Boolean hasInitializer(FirEnumEntry firEnumEntry) {
        firEnumEntry.getClass();
        KtSourceElement source = firEnumEntry.getSource();
        if (source == null) {
            return null;
        }
        List children = LightTreeUtilsKt.getChildren(source.getLighterASTNode(), source.getTreeStructure());
        boolean z = false;
        if (!(children instanceof Collection) || !children.isEmpty()) {
            Iterator it = children.iterator();
            while (it.hasNext()) {
                if (Intrinsics.areEqual(((LighterASTNode) it.next()).getTokenType(), KtNodeTypes.INITIALIZER_LIST)) {
                    z = true;
                    break;
                }
            }
        }
        return Boolean.valueOf(z);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.SourceNavigator
    public boolean isCatchElementParameter(FirValueParameterSymbol firValueParameterSymbol) {
        LighterASTNode parentOfParent;
        firValueParameterSymbol.getClass();
        KtSourceElement source = firValueParameterSymbol.getSource();
        return Intrinsics.areEqual((source == null || (parentOfParent = getParentOfParent(source)) == null) ? null : parentOfParent.getTokenType(), KtNodeTypes.CATCH);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.SourceNavigator
    public boolean isInConstructorCallee(FirTypeRef firTypeRef) {
        firTypeRef.getClass();
        Boolean bool = (Boolean) withSource(firTypeRef, new Function1() { // from class: org.jetbrains.kotlin.fir.analysis.checkers.h
            public final Object invoke(Object obj) {
                return Boolean.valueOf(LightTreeSourceNavigator.a((KtSourceElement) obj));
            }
        });
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.SourceNavigator
    public boolean isInTypeConstraint(FirTypeRef firTypeRef) {
        Object next;
        LighterASTNode lighterASTNode;
        firTypeRef.getClass();
        KtSourceElement source = firTypeRef.getSource();
        if (source == null) {
            return false;
        }
        Iterator it = LightTreePositioningStrategiesKt.getAncestors(source.getTreeStructure(), source.getLighterASTNode()).iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            lighterASTNode = (LighterASTNode) next;
            if (Intrinsics.areEqual(lighterASTNode.getTokenType(), KtNodeTypes.TYPE_CONSTRAINT)) {
                break;
            }
        } while (!Intrinsics.areEqual(lighterASTNode.getTokenType(), KtNodeTypes.TYPE_PARAMETER));
        LighterASTNode lighterASTNode2 = (LighterASTNode) next;
        return Intrinsics.areEqual(lighterASTNode2 != null ? lighterASTNode2.getTokenType() : null, KtNodeTypes.TYPE_CONSTRAINT);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.SourceNavigator
    public boolean isRedundantNullable(FirTypeRef firTypeRef) {
        Ref<LighterASTNode[]> ref;
        LighterASTNode nullableChild;
        firTypeRef.getClass();
        KtSourceElement source = firTypeRef.getSource();
        return (source == null || (nullableChild = getNullableChild(source, source.getLighterASTNode(), (ref = new Ref<>()))) == null || getNullableChild(source, nullableChild, ref) == null) ? false : true;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.SourceNavigator
    public CharSequence getRawIdentifier(KtSourceElement ktSourceElement) {
        ktSourceElement.getClass();
        return getRawIdentifier(ktSourceElement.getLighterASTNode(), ktSourceElement.getTreeStructure());
    }
}
