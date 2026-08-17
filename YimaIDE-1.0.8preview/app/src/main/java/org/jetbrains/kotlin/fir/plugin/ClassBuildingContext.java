package org.jetbrains.kotlin.fir.plugin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.GeneratedDeclarationKey;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirModuleDataKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOriginKt;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.builder.FirOuterClassTypeParameterRefBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirRegularClassBuilder;
import org.jetbrains.kotlin.fir.plugin.ClassBuildingContext;
import org.jetbrains.kotlin.fir.scopes.FirKotlinScopeProviderKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.builder.FirResolvedTypeRefBuilder;
import org.jetbrains.kotlin.name.ClassId;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B5\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0002\b\u0003\u0018\u00010\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eJ\u000e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0014J \u0010\u0015\u001a\u00020\u00162\u0018\u0010\u0018\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u0012\u0012\u0004\u0012\u00020\u00140\u0011J\b\u0010\u0019\u001a\u00020\u0002H\u0016R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010\u000f\u001a\u001a\u0012\u0016\u0012\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u0012\u0012\u0004\u0012\u00020\u00140\u00110\u0010X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/kotlin/fir/plugin/ClassBuildingContext;", "Lorg/jetbrains/kotlin/fir/plugin/DeclarationBuildingContext;", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "key", "Lorg/jetbrains/kotlin/GeneratedDeclarationKey;", "owner", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "classKind", "Lorg/jetbrains/kotlin/descriptors/ClassKind;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/GeneratedDeclarationKey;Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;Lorg/jetbrains/kotlin/name/ClassId;Lorg/jetbrains/kotlin/descriptors/ClassKind;)V", "superTypeProviders", Argument.Delimiters.none, "Lkotlin/Function1;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameterRef;", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "superType", Argument.Delimiters.none, ModuleXmlParser.TYPE, "typeProvider", "build", "org.jetbrains.kotlin:plugin-utils"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ClassBuildingContext extends DeclarationBuildingContext<FirRegularClass> {
    private final ClassId classId;
    private final ClassKind classKind;
    private final List<Function1<List<? extends FirTypeParameterRef>, ConeKotlinType>> superTypeProviders;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ClassBuildingContext(FirSession firSession, GeneratedDeclarationKey generatedDeclarationKey, FirClassSymbol<?> firClassSymbol, ClassId classId, ClassKind classKind) {
        super(firSession, generatedDeclarationKey, firClassSymbol, null);
        firSession.getClass();
        generatedDeclarationKey.getClass();
        classId.getClass();
        classKind.getClass();
        this.classId = classId;
        this.classKind = classKind;
        this.superTypeProviders = new ArrayList();
    }

    public static ConeKotlinType c(ConeKotlinType coneKotlinType, List list) {
        list.getClass();
        return coneKotlinType;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.plugin.DeclarationBuildingContext
    public FirRegularClass build() {
        FirRegularClassBuilder firRegularClassBuilder = new FirRegularClassBuilder();
        firRegularClassBuilder.setResolvePhase(FirResolvePhase.BODY_RESOLVE);
        firRegularClassBuilder.setModuleData(FirModuleDataKt.getModuleData(getSession()));
        firRegularClassBuilder.setOrigin(FirDeclarationOriginKt.getOrigin(getKey()));
        firRegularClassBuilder.setClassKind(this.classKind);
        firRegularClassBuilder.setScopeProvider(FirKotlinScopeProviderKt.getKotlinScopeProvider(getSession()));
        firRegularClassBuilder.setStatus(generateStatus());
        firRegularClassBuilder.setSource(getSourceForFirDeclaration());
        firRegularClassBuilder.setName(this.classId.getShortClassName());
        firRegularClassBuilder.setSymbol(new FirRegularClassSymbol(this.classId));
        if (firRegularClassBuilder.getStatus().isInner()) {
            if (getOwner() == null) {
                w01.a("Inner class must have owner");
                return null;
            }
            List<FirTypeParameterSymbol> typeParameterSymbols = getOwner().getTypeParameterSymbols();
            List<FirTypeParameterRef> typeParameters = firRegularClassBuilder.getTypeParameters();
            for (FirTypeParameterSymbol firTypeParameterSymbol : typeParameterSymbols) {
                FirOuterClassTypeParameterRefBuilder firOuterClassTypeParameterRefBuilder = new FirOuterClassTypeParameterRefBuilder();
                firOuterClassTypeParameterRefBuilder.setSymbol(firTypeParameterSymbol);
                typeParameters.add(firOuterClassTypeParameterRefBuilder.build());
            }
        }
        List<DeclarationBuildingContext.TypeParameterData> typeParameters2 = getTypeParameters();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(typeParameters2, 10));
        Iterator<T> it = typeParameters2.iterator();
        while (it.hasNext()) {
            arrayList.add(generateTypeParameter((DeclarationBuildingContext.TypeParameterData) it.next(), firRegularClassBuilder.getSymbol()));
        }
        CollectionsKt.addAll(firRegularClassBuilder.getTypeParameters(), arrayList);
        initTypeParameterBounds(firRegularClassBuilder.getTypeParameters(), arrayList);
        if (this.superTypeProviders.isEmpty()) {
            firRegularClassBuilder.getSuperTypeRefs().add(getSession().getBuiltinTypes().getAnyType());
        } else {
            List<Function1<List<? extends FirTypeParameterRef>, ConeKotlinType>> list = this.superTypeProviders;
            List<FirTypeRef> superTypeRefs = firRegularClassBuilder.getSuperTypeRefs();
            Iterator<T> it2 = list.iterator();
            while (it2.hasNext()) {
                Function1 function1 = (Function1) it2.next();
                FirResolvedTypeRefBuilder firResolvedTypeRefBuilder = new FirResolvedTypeRefBuilder();
                firResolvedTypeRefBuilder.setConeType((ConeKotlinType) function1.invoke(firRegularClassBuilder.getTypeParameters()));
                superTypeRefs.add(firResolvedTypeRefBuilder.build());
            }
        }
        FirRegularClass firRegularClassMo288build = firRegularClassBuilder.mo288build();
        FirClassSymbol<?> owner = getOwner();
        if (owner != null && ((FirClassLikeDeclaration) owner.getFir()).getIsLocal()) {
            ClassMembersKt.setContainingClassForLocalAttr(firRegularClassMo288build, getOwner().getLookupTag());
        }
        return firRegularClassMo288build;
    }

    public final void superType(final ConeKotlinType type) {
        type.getClass();
        this.superTypeProviders.add(new Function1() { // from class: ht1
            public final Object invoke(Object obj) {
                return ClassBuildingContext.c(type, (List) obj);
            }
        });
    }

    public final void superType(Function1<? super List<? extends FirTypeParameterRef>, ? extends ConeKotlinType> typeProvider) {
        typeProvider.getClass();
        this.superTypeProviders.add(typeProvider);
    }
}
