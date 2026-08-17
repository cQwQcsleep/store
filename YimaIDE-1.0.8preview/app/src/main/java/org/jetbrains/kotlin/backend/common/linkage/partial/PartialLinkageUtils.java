package org.jetbrains.kotlin.backend.common.linkage.partial;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import org.jetbrains.kotlin.backend.common.linkage.partial.PartialLinkageUtils;
import org.jetbrains.kotlin.backend.common.serialization.mangle.MangleConstant;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.descriptors.DescriptorVisibilities;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationWithName;
import org.jetbrains.kotlin.ir.declarations.IrPackageFragment;
import org.jetbrains.kotlin.ir.declarations.lazy.IrLazyDeclarationBase;
import org.jetbrains.kotlin.ir.util.IdSignature;
import org.jetbrains.kotlin.ir.util.IrUtilsKt;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001:\u0001\u0010B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\b\u001a\u0004\u0018\u00010\t*\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\n\u0010\r\u001a\u00020\u000e*\u00020\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/linkage/partial/PartialLinkageUtils;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "UNKNOWN_NAME", "Lorg/jetbrains/kotlin/name/Name;", "getUNKNOWN_NAME", "()Lorg/jetbrains/kotlin/name/Name;", "guessName", "", "Lorg/jetbrains/kotlin/ir/util/IdSignature;", "nameSegmentsToPickUp", "", "isEffectivelyMissingLazyIrDeclaration", "", "Lorg/jetbrains/kotlin/ir/declarations/lazy/IrLazyDeclarationBase;", "DeclarationId", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PartialLinkageUtils {
    public static final PartialLinkageUtils INSTANCE = new PartialLinkageUtils();
    private static final Name UNKNOWN_NAME;

    static {
        Name nameIdentifier = Name.identifier("<unknown name>");
        nameIdentifier.getClass();
        UNKNOWN_NAME = nameIdentifier;
    }

    private PartialLinkageUtils() {
    }

    public static IrClass a(IrClass irClass) {
        irClass.getClass();
        return IrUtilsKt.getParentClassOrNull(irClass);
    }

    public final Name getUNKNOWN_NAME() {
        return UNKNOWN_NAME;
    }

    public final String guessName(IdSignature idSignature, int i) {
        idSignature.getClass();
        if (idSignature instanceof IdSignature.CommonSignature) {
            return i == 1 ? ((IdSignature.CommonSignature) idSignature).getShortName() : CollectionsKt.joinToString$default(CollectionsKt.takeLast(((IdSignature.CommonSignature) idSignature).getNameSegments(), i), ".", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
        }
        if (idSignature instanceof IdSignature.CompositeSignature) {
            return guessName(((IdSignature.CompositeSignature) idSignature).getInner(), i);
        }
        if (idSignature instanceof IdSignature.AccessorSignature) {
            return guessName(((IdSignature.AccessorSignature) idSignature).getAccessorSignature(), i);
        }
        if (!(idSignature instanceof IdSignature.LocalSignature)) {
            return null;
        }
        IdSignature.LocalSignature localSignature = (IdSignature.LocalSignature) idSignature;
        if (!Intrinsics.areEqual(localSignature.getLocalFqn(), MangleConstant.TYPE_PARAMETER_MARKER_NAME) && !Intrinsics.areEqual(localSignature.getLocalFqn(), MangleConstant.TYPE_PARAMETER_MARKER_NAME_SETTER)) {
            return null;
        }
        return "#" + localSignature.index();
    }

    public final boolean isEffectivelyMissingLazyIrDeclaration(IrLazyDeclarationBase irLazyDeclarationBase) {
        irLazyDeclarationBase.getClass();
        IrClass parentClassOrNull = irLazyDeclarationBase instanceof IrClass ? (IrClass) irLazyDeclarationBase : null;
        if (parentClassOrNull == null && (parentClassOrNull = IrUtilsKt.getParentClassOrNull(irLazyDeclarationBase)) == null) {
            return false;
        }
        return Intrinsics.areEqual(((IrClass) SequencesKt.last(SequencesKt.generateSequence(parentClassOrNull, new Function1() { // from class: hza
            public final Object invoke(Object obj) {
                return PartialLinkageUtils.a((IrClass) obj);
            }
        }))).getVisibility(), DescriptorVisibilities.PRIVATE);
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\n\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u0003H\u0002J\n\u0010\f\u001a\u00020\u0003H\u0096\u0080\u0004J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/linkage/partial/PartialLinkageUtils$DeclarationId;", "", "packageFqName", "", "declarationRelativeFqName", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Ljava/lang/String;Ljava/lang/String;)V", "getPackageFqName", "()Ljava/lang/String;", "getDeclarationRelativeFqName", "createNested", "name", "toString", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "Companion", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class DeclarationId {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private final String declarationRelativeFqName;
        private final String packageFqName;

        public DeclarationId(String str, String str2) {
            str.getClass();
            str2.getClass();
            this.packageFqName = str;
            this.declarationRelativeFqName = str2;
        }

        public static /* synthetic */ DeclarationId copy$default(DeclarationId declarationId, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = declarationId.packageFqName;
            }
            if ((i & 2) != 0) {
                str2 = declarationId.declarationRelativeFqName;
            }
            return declarationId.copy(str, str2);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final DeclarationId createNested(String name) {
            String str = this.packageFqName;
            if (this.declarationRelativeFqName.length() > 0) {
                name = this.declarationRelativeFqName + '.' + name;
            }
            return new DeclarationId(str, name);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getPackageFqName() {
            return this.packageFqName;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getDeclarationRelativeFqName() {
            return this.declarationRelativeFqName;
        }

        public final DeclarationId copy(String packageFqName, String declarationRelativeFqName) {
            packageFqName.getClass();
            declarationRelativeFqName.getClass();
            return new DeclarationId(packageFqName, declarationRelativeFqName);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DeclarationId)) {
                return false;
            }
            DeclarationId declarationId = (DeclarationId) other;
            return Intrinsics.areEqual(this.packageFqName, declarationId.packageFqName) && Intrinsics.areEqual(this.declarationRelativeFqName, declarationId.declarationRelativeFqName);
        }

        public final String getDeclarationRelativeFqName() {
            return this.declarationRelativeFqName;
        }

        public final String getPackageFqName() {
            return this.packageFqName;
        }

        public int hashCode() {
            return (this.packageFqName.hashCode() * 31) + this.declarationRelativeFqName.hashCode();
        }

        public String toString() {
            return this.packageFqName + '/' + this.declarationRelativeFqName;
        }

        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0004\u001a\u0004\u0018\u00010\u0005*\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/linkage/partial/PartialLinkageUtils$DeclarationId$Companion;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "declarationId", "Lorg/jetbrains/kotlin/backend/common/linkage/partial/PartialLinkageUtils$DeclarationId;", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationWithName;", "getDeclarationId", "(Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationWithName;)Lorg/jetbrains/kotlin/backend/common/linkage/partial/PartialLinkageUtils$DeclarationId;", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            public final DeclarationId getDeclarationId(IrDeclarationWithName irDeclarationWithName) {
                DeclarationId declarationId;
                irDeclarationWithName.getClass();
                IrPackageFragment parent = irDeclarationWithName.getParent();
                if (parent instanceof IrPackageFragment) {
                    String strAsString = parent.getPackageFqName().asString();
                    String strAsString2 = irDeclarationWithName.getName().asString();
                    strAsString2.getClass();
                    return new DeclarationId(strAsString, strAsString2);
                }
                if (!(parent instanceof IrDeclarationWithName) || (declarationId = getDeclarationId((IrDeclarationWithName) parent)) == null) {
                    return null;
                }
                String strAsString3 = irDeclarationWithName.getName().asString();
                strAsString3.getClass();
                return declarationId.createNested(strAsString3);
            }

            private Companion() {
            }
        }
    }
}
