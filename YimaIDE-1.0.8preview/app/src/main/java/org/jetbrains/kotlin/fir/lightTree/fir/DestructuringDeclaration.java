package org.jetbrains.kotlin.fir.lightTree.fir;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirGenerationKt;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.builder.AbstractRawFirBuilder;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirVariable;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirBlock;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.builder.FirBlockBuilder;
import org.jetbrains.kotlin.fir.lightTree.fir.DestructuringDeclarationKt;
import org.jetbrains.kotlin.name.SpecialNames;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BC\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u0006¢\u0006\u0004\b\u000e\u0010\u000fJ$\u0010\u0018\u001a\u00020\u00192\n\u0010\u001a\u001a\u0006\u0012\u0002\b\u00030\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\u000f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006HÆ\u0003J\t\u0010\"\u001a\u00020\tHÆ\u0003J\t\u0010#\u001a\u00020\u000bHÆ\u0003J\u000f\u0010$\u001a\b\u0012\u0004\u0012\u00020\r0\u0006HÆ\u0003JQ\u0010%\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u0006HÆ\u0001J\u0014\u0010&\u001a\u00020\u00032\b\u0010'\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010(\u001a\u00020)HÖ\u0081\u0004J\n\u0010*\u001a\u00020+HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0010R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0012¨\u0006,"}, d2 = {"Lorg/jetbrains/kotlin/fir/lightTree/fir/DestructuringDeclaration;", Argument.Delimiters.none, "isVar", Argument.Delimiters.none, "isNameBased", "entries", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/lightTree/fir/DestructuringEntry;", "initializer", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "annotations", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "<init>", "(ZZLjava/util/List;Lorg/jetbrains/kotlin/fir/expressions/FirExpression;Lorg/jetbrains/kotlin/KtSourceElement;Ljava/util/List;)V", "()Z", "getEntries", "()Ljava/util/List;", "getInitializer", "()Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "getAnnotations", "toFirDestructingDeclaration", "Lorg/jetbrains/kotlin/fir/expressions/FirBlock;", "builder", "Lorg/jetbrains/kotlin/fir/builder/AbstractRawFirBuilder;", "moduleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "tmpVariable", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin.fir:light-tree2fir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class DestructuringDeclaration {
    private final List<FirAnnotation> annotations;
    private final List<DestructuringEntry> entries;
    private final FirExpression initializer;
    private final boolean isNameBased;
    private final boolean isVar;
    private final KtSourceElement source;

    /* JADX WARN: Multi-variable type inference failed */
    public DestructuringDeclaration(boolean z, boolean z2, List<DestructuringEntry> list, FirExpression firExpression, KtSourceElement ktSourceElement, List<? extends FirAnnotation> list2) {
        list.getClass();
        firExpression.getClass();
        ktSourceElement.getClass();
        list2.getClass();
        this.isVar = z;
        this.isNameBased = z2;
        this.entries = list;
        this.initializer = firExpression;
        this.source = ktSourceElement;
        this.annotations = list2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ DestructuringDeclaration copy$default(DestructuringDeclaration destructuringDeclaration, boolean z, boolean z2, List list, FirExpression firExpression, KtSourceElement ktSourceElement, List list2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = destructuringDeclaration.isVar;
        }
        if ((i & 2) != 0) {
            z2 = destructuringDeclaration.isNameBased;
        }
        if ((i & 4) != 0) {
            list = destructuringDeclaration.entries;
        }
        if ((i & 8) != 0) {
            firExpression = destructuringDeclaration.initializer;
        }
        if ((i & 16) != 0) {
            ktSourceElement = destructuringDeclaration.source;
        }
        if ((i & 32) != 0) {
            list2 = destructuringDeclaration.annotations;
        }
        KtSourceElement ktSourceElement2 = ktSourceElement;
        List list3 = list2;
        return destructuringDeclaration.copy(z, z2, list, firExpression, ktSourceElement2, list3);
    }

    public static /* synthetic */ FirBlock toFirDestructingDeclaration$default(DestructuringDeclaration destructuringDeclaration, AbstractRawFirBuilder abstractRawFirBuilder, FirModuleData firModuleData, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = true;
        }
        return destructuringDeclaration.toFirDestructingDeclaration(abstractRawFirBuilder, firModuleData, z);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final boolean getIsVar() {
        return this.isVar;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getIsNameBased() {
        return this.isNameBased;
    }

    public final List<DestructuringEntry> component3() {
        return this.entries;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final FirExpression getInitializer() {
        return this.initializer;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final KtSourceElement getSource() {
        return this.source;
    }

    public final List<FirAnnotation> component6() {
        return this.annotations;
    }

    public final DestructuringDeclaration copy(boolean isVar, boolean isNameBased, List<DestructuringEntry> entries, FirExpression initializer, KtSourceElement source, List<? extends FirAnnotation> annotations) {
        entries.getClass();
        initializer.getClass();
        source.getClass();
        annotations.getClass();
        return new DestructuringDeclaration(isVar, isNameBased, entries, initializer, source, annotations);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DestructuringDeclaration)) {
            return false;
        }
        DestructuringDeclaration destructuringDeclaration = (DestructuringDeclaration) other;
        return this.isVar == destructuringDeclaration.isVar && this.isNameBased == destructuringDeclaration.isNameBased && Intrinsics.areEqual(this.entries, destructuringDeclaration.entries) && Intrinsics.areEqual(this.initializer, destructuringDeclaration.initializer) && Intrinsics.areEqual(this.source, destructuringDeclaration.source) && Intrinsics.areEqual(this.annotations, destructuringDeclaration.annotations);
    }

    public final List<FirAnnotation> getAnnotations() {
        return this.annotations;
    }

    public final List<DestructuringEntry> getEntries() {
        return this.entries;
    }

    public final FirExpression getInitializer() {
        return this.initializer;
    }

    public final KtSourceElement getSource() {
        return this.source;
    }

    public int hashCode() {
        return (((((((((Boolean.hashCode(this.isVar) * 31) + Boolean.hashCode(this.isNameBased)) * 31) + this.entries.hashCode()) * 31) + this.initializer.hashCode()) * 31) + this.source.hashCode()) * 31) + this.annotations.hashCode();
    }

    public final boolean isNameBased() {
        return this.isNameBased;
    }

    public final boolean isVar() {
        return this.isVar;
    }

    public final FirBlock toFirDestructingDeclaration(AbstractRawFirBuilder<?> builder, FirModuleData moduleData, boolean tmpVariable) {
        builder.getClass();
        moduleData.getClass();
        FirProperty firPropertyGenerateTemporaryVariable$default = FirGenerationKt.generateTemporaryVariable$default(moduleData, this.source, SpecialNames.DESTRUCT, this.initializer, null, this.annotations, null, 80, null);
        FirBlockBuilder firBlockBuilder = new FirBlockBuilder();
        firBlockBuilder.setSource(KtSourceElementKt.fakeElement$default(this.source, KtFakeSourceElementKind.DestructuringBlock.INSTANCE, null, 2, null));
        DestructuringDeclarationKt.addDestructuringStatements(builder, firBlockBuilder.getStatements(), moduleData, this, firPropertyGenerateTemporaryVariable$default, tmpVariable, false, (64 & 64) != 0 ? new Function1() { // from class: ap3
            public final Object invoke(Object obj) {
                return DestructuringDeclarationKt.a((FirVariable) obj);
            }
        } : null);
        return firBlockBuilder.mo288build();
    }

    public String toString() {
        return "DestructuringDeclaration(isVar=" + this.isVar + ", isNameBased=" + this.isNameBased + ", entries=" + this.entries + ", initializer=" + this.initializer + ", source=" + this.source + ", annotations=" + this.annotations + ')';
    }
}
