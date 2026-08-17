package org.jetbrains.kotlin.fir.lightTree.fir;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.descriptors.Visibility;
import org.jetbrains.kotlin.fir.builder.CompanionBlockCollector;
import org.jetbrains.kotlin.fir.declarations.builder.FirClassBuilder;
import org.jetbrains.kotlin.fir.lightTree.fir.modifier.ModifierList;
import org.jetbrains.kotlin.fir.types.FirTypeRef;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001BU\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\f\u0012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f\u0012\u0006\u0010\u0011\u001a\u00020\u0012¢\u0006\u0004\b\u0013\u0010\u0014J\u0006\u0010#\u001a\u00020\tJ\b\u0010$\u001a\u00020\tH\u0002J\u0006\u0010%\u001a\u00020\tJ\u0006\u0010&\u001a\u00020\tJ\u0006\u0010'\u001a\u00020\tJ\u0006\u0010(\u001a\u00020\tJ\u0006\u0010)\u001a\u00020\tJ\u0006\u0010*\u001a\u00020+R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\n\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001aR\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\r\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001dR\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"¨\u0006,"}, d2 = {"Lorg/jetbrains/kotlin/fir/lightTree/fir/ClassWrapper;", Argument.Delimiters.none, "modifiers", "Lorg/jetbrains/kotlin/fir/lightTree/fir/modifier/ModifierList;", "classKind", "Lorg/jetbrains/kotlin/descriptors/ClassKind;", "classBuilder", "Lorg/jetbrains/kotlin/fir/declarations/builder/FirClassBuilder;", "hasSecondaryConstructor", Argument.Delimiters.none, "hasDefaultConstructor", "delegatedSelfTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "delegatedSuperTypeRef", "delegatedSuperCalls", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/lightTree/fir/DelegatedConstructorWrapper;", "companionBlockCollector", "Lorg/jetbrains/kotlin/fir/builder/CompanionBlockCollector;", "<init>", "(Lorg/jetbrains/kotlin/fir/lightTree/fir/modifier/ModifierList;Lorg/jetbrains/kotlin/descriptors/ClassKind;Lorg/jetbrains/kotlin/fir/declarations/builder/FirClassBuilder;ZZLorg/jetbrains/kotlin/fir/types/FirTypeRef;Lorg/jetbrains/kotlin/fir/types/FirTypeRef;Ljava/util/List;Lorg/jetbrains/kotlin/fir/builder/CompanionBlockCollector;)V", "getModifiers", "()Lorg/jetbrains/kotlin/fir/lightTree/fir/modifier/ModifierList;", "getClassBuilder", "()Lorg/jetbrains/kotlin/fir/declarations/builder/FirClassBuilder;", "getHasSecondaryConstructor", "()Z", "getHasDefaultConstructor", "getDelegatedSelfTypeRef", "()Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "getDelegatedSuperTypeRef", "getDelegatedSuperCalls", "()Ljava/util/List;", "getCompanionBlockCollector", "()Lorg/jetbrains/kotlin/fir/builder/CompanionBlockCollector;", "isEnumEntry", "isObject", "isSealed", "isEnum", "isInterface", "isInner", "hasExpect", "defaultConstructorVisibility", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "org.jetbrains.kotlin.fir:light-tree2fir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ClassWrapper {
    private final FirClassBuilder classBuilder;
    private final ClassKind classKind;
    private final CompanionBlockCollector companionBlockCollector;
    private final FirTypeRef delegatedSelfTypeRef;
    private final List<DelegatedConstructorWrapper> delegatedSuperCalls;
    private final FirTypeRef delegatedSuperTypeRef;
    private final boolean hasDefaultConstructor;
    private final boolean hasSecondaryConstructor;
    private final ModifierList modifiers;

    public ClassWrapper(ModifierList modifierList, ClassKind classKind, FirClassBuilder firClassBuilder, boolean z, boolean z2, FirTypeRef firTypeRef, FirTypeRef firTypeRef2, List<DelegatedConstructorWrapper> list, CompanionBlockCollector companionBlockCollector) {
        modifierList.getClass();
        classKind.getClass();
        firClassBuilder.getClass();
        firTypeRef.getClass();
        firTypeRef2.getClass();
        list.getClass();
        companionBlockCollector.getClass();
        this.modifiers = modifierList;
        this.classKind = classKind;
        this.classBuilder = firClassBuilder;
        this.hasSecondaryConstructor = z;
        this.hasDefaultConstructor = z2;
        this.delegatedSelfTypeRef = firTypeRef;
        this.delegatedSuperTypeRef = firTypeRef2;
        this.delegatedSuperCalls = list;
        this.companionBlockCollector = companionBlockCollector;
    }

    private final boolean isObject() {
        return this.classKind == ClassKind.OBJECT;
    }

    public final Visibility defaultConstructorVisibility() {
        if (isObject() || isEnum() || isEnumEntry()) {
            return Visibilities.Private.INSTANCE;
        }
        return isSealed() ? Visibilities.Protected.INSTANCE : Visibilities.Unknown.INSTANCE;
    }

    public final FirClassBuilder getClassBuilder() {
        return this.classBuilder;
    }

    public final CompanionBlockCollector getCompanionBlockCollector() {
        return this.companionBlockCollector;
    }

    public final FirTypeRef getDelegatedSelfTypeRef() {
        return this.delegatedSelfTypeRef;
    }

    public final List<DelegatedConstructorWrapper> getDelegatedSuperCalls() {
        return this.delegatedSuperCalls;
    }

    public final FirTypeRef getDelegatedSuperTypeRef() {
        return this.delegatedSuperTypeRef;
    }

    public final boolean getHasDefaultConstructor() {
        return this.hasDefaultConstructor;
    }

    public final boolean getHasSecondaryConstructor() {
        return this.hasSecondaryConstructor;
    }

    public final ModifierList getModifiers() {
        return this.modifiers;
    }

    public final boolean hasExpect() {
        return this.modifiers.hasExpect();
    }

    public final boolean isEnum() {
        return this.modifiers.isEnum();
    }

    public final boolean isEnumEntry() {
        return this.classKind == ClassKind.ENUM_ENTRY;
    }

    public final boolean isInner() {
        return this.modifiers.isInner();
    }

    public final boolean isInterface() {
        return this.classKind == ClassKind.INTERFACE;
    }

    public final boolean isSealed() {
        return this.modifiers.hasModality(Modality.SEALED);
    }
}
