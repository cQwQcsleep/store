package org.jetbrains.kotlin.fir.backend.jvm;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.backend.jvm.JvmIrAttributesKt;
import org.jetbrains.kotlin.backend.jvm.mapping.IrTypeMapper;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.backend.Fir2IrComponents;
import org.jetbrains.kotlin.fir.backend.jvm.FirJvmElementAwareStringTable;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationUtilKt;
import org.jetbrains.kotlin.fir.serialization.FirElementAwareStringTable;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.ir.IrElement;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationBase;
import org.jetbrains.kotlin.metadata.jvm.deserialization.JvmNameResolver;
import org.jetbrains.kotlin.metadata.jvm.serialization.JvmStringTable;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.StandardClassIds;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B#\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\f\u0010\u000b\u001a\u00020\f*\u00020\u000fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/jvm/FirJvmElementAwareStringTable;", "Lorg/jetbrains/kotlin/metadata/jvm/serialization/JvmStringTable;", "Lorg/jetbrains/kotlin/fir/serialization/FirElementAwareStringTable;", "typeMapper", "Lorg/jetbrains/kotlin/backend/jvm/mapping/IrTypeMapper;", "components", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;", "nameResolver", "Lorg/jetbrains/kotlin/metadata/jvm/deserialization/JvmNameResolver;", "<init>", "(Lorg/jetbrains/kotlin/backend/jvm/mapping/IrTypeMapper;Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;Lorg/jetbrains/kotlin/metadata/jvm/deserialization/JvmNameResolver;)V", "getLocalClassLikeDeclarationIdReplacement", "Lorg/jetbrains/kotlin/name/ClassId;", "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationBase;", "org.jetbrains.kotlin:jvm-backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJvmElementAwareStringTable extends JvmStringTable implements FirElementAwareStringTable {
    private final Fir2IrComponents components;
    private final IrTypeMapper typeMapper;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirJvmElementAwareStringTable(IrTypeMapper irTypeMapper, Fir2IrComponents fir2IrComponents, JvmNameResolver jvmNameResolver) {
        super(jvmNameResolver);
        irTypeMapper.getClass();
        fir2IrComponents.getClass();
        this.typeMapper = irTypeMapper;
        this.components = fir2IrComponents;
    }

    public static IrDeclarationBase a(IrDeclarationBase irDeclarationBase) {
        irDeclarationBase.getClass();
        IrClass parent = irDeclarationBase.getParent();
        if (parent instanceof IrClass) {
            return parent;
        }
        return null;
    }

    private final ClassId getLocalClassLikeDeclarationIdReplacement(IrDeclarationBase irDeclarationBase) {
        String strReplace$default = StringsKt.replace$default(this.typeMapper.classLikeDeclarationInternalName(irDeclarationBase), '/', '.', false, 4, (Object) null);
        if (JvmIrAttributesKt.isEnclosedInConstructor(irDeclarationBase)) {
            FqName fqName = new FqName(strReplace$default);
            return new ClassId(fqName.parent(), FqName.Companion.topLevel(fqName.shortName()), true);
        }
        String strReplace$default2 = StringsKt.replace$default(this.typeMapper.classLikeDeclarationInternalName((IrDeclarationBase) SequencesKt.last(SequencesKt.generateSequence(irDeclarationBase, new Function1() { // from class: u95
            public final Object invoke(Object obj) {
                return FirJvmElementAwareStringTable.a((IrDeclarationBase) obj);
            }
        }))), '/', '.', false, 4, (Object) null);
        FqName fqName2 = new FqName(strReplace$default2);
        ClassId classId = new ClassId(fqName2.parent(), FqName.Companion.topLevel(fqName2.shortName()), true);
        if (strReplace$default.length() == strReplace$default2.length()) {
            return classId;
        }
        Iterator it = StringsKt.split$default(strReplace$default.substring(strReplace$default2.length() + 1), new char[]{'$'}, false, 0, 6, (Object) null).iterator();
        while (it.hasNext()) {
            Name nameIdentifier = Name.identifier((String) it.next());
            nameIdentifier.getClass();
            classId = classId.createNestedClassId(nameIdentifier);
        }
        return classId;
    }

    public /* synthetic */ FirJvmElementAwareStringTable(IrTypeMapper irTypeMapper, Fir2IrComponents fir2IrComponents, JvmNameResolver jvmNameResolver, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(irTypeMapper, fir2IrComponents, (i & 4) != 0 ? null : jvmNameResolver);
    }

    @Override // org.jetbrains.kotlin.fir.serialization.FirElementAwareStringTable
    public ClassId getLocalClassLikeDeclarationIdReplacement(FirClassLikeDeclaration declaration) {
        IrClass irClass;
        ConeClassLikeLookupTag lookupTag;
        ClassId classId;
        declaration.getClass();
        if (this.components.getConfiguration().getSkipBodies()) {
            if (declaration instanceof FirClass) {
                ConeClassLikeType coneClassLikeType = (ConeClassLikeType) CollectionsKt.firstOrNull(FirDeclarationUtilKt.getSuperConeTypes((FirClass) declaration));
                return (coneClassLikeType == null || (lookupTag = coneClassLikeType.getLookupTag()) == null || (classId = lookupTag.getClassId()) == null) ? StandardClassIds.INSTANCE.getAny() : classId;
            }
            if (declaration instanceof FirTypeAlias) {
                return StandardClassIds.INSTANCE.getAny();
            }
            bu8.a();
            return null;
        }
        if (declaration instanceof FirClass) {
            irClass = this.components.getClassifierStorage().getIrClass((FirClass) declaration);
        } else {
            if (!(declaration instanceof FirTypeAlias)) {
                bu8.a();
                return null;
            }
            irClass = (IrElement) this.components.getClassifierStorage().getIrTypeAliasSymbol(((FirTypeAlias) declaration).getSymbol()).getOwner();
        }
        return getLocalClassLikeDeclarationIdReplacement((IrDeclarationBase) irClass);
    }
}
