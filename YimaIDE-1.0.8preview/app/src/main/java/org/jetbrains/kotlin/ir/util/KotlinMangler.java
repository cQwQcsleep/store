package org.jetbrains.kotlin.ir.util;

import kotlin.Metadata;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor;
import org.jetbrains.kotlin.ir.declarations.IrDeclaration;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000e\bf\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002:\u0002\u0015\u0016J\u0019\u0010\b\u001a\u00020\t*\u00028\u00002\u0006\u0010\n\u001a\u00020\tH&¢\u0006\u0002\u0010\u000bJ\u0019\u0010\f\u001a\u00020\u0005*\u00028\u00002\u0006\u0010\n\u001a\u00020\tH&¢\u0006\u0002\u0010\rJ\u0019\u0010\u000e\u001a\u00020\u0004*\u00028\u00002\u0006\u0010\n\u001a\u00020\tH\u0016¢\u0006\u0002\u0010\u000fJ\u0011\u0010\u0010\u001a\u00020\t*\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0011R\u0016\u0010\u0003\u001a\u00020\u0004*\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0012\u0010\u0012\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0017À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/ir/util/KotlinMangler;", "D", "", "hashMangle", "", "", "getHashMangle", "(Ljava/lang/String;)J", "isExported", "", "compatibleMode", "(Ljava/lang/Object;Z)Z", "signatureString", "(Ljava/lang/Object;Z)Ljava/lang/String;", "signatureMangle", "(Ljava/lang/Object;Z)J", "isPlatformSpecificExport", "(Ljava/lang/Object;)Z", "manglerName", "getManglerName", "()Ljava/lang/String;", "DescriptorMangler", "IrMangler", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface KotlinMangler<D> {

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0007À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/ir/util/KotlinMangler$DescriptorMangler;", "Lorg/jetbrains/kotlin/ir/util/KotlinMangler;", "Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptor;", "manglerName", "", "getManglerName", "()Ljava/lang/String;", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public interface DescriptorMangler extends KotlinMangler<DeclarationDescriptor> {
        @Override // org.jetbrains.kotlin.ir.util.KotlinMangler
        default String getManglerName() {
            return "Descriptor";
        }
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\bf\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0014\u0010\u0003\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H&R\u0014\u0010\u0007\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\nÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/ir/util/KotlinMangler$IrMangler;", "Lorg/jetbrains/kotlin/ir/util/KotlinMangler;", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclaration;", "mangleString", "", "compatibleMode", "", "manglerName", "getManglerName", "()Ljava/lang/String;", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public interface IrMangler extends KotlinMangler<IrDeclaration> {
        @Override // org.jetbrains.kotlin.ir.util.KotlinMangler
        default String getManglerName() {
            return "Ir";
        }

        String mangleString(IrDeclaration irDeclaration, boolean z);
    }

    long getHashMangle(String str);

    String getManglerName();

    boolean isExported(D d, boolean z);

    default boolean isPlatformSpecificExport(D d) {
        d.getClass();
        return false;
    }

    default long signatureMangle(D d, boolean z) {
        d.getClass();
        return getHashMangle(signatureString(d, z));
    }

    String signatureString(D d, boolean z);
}
