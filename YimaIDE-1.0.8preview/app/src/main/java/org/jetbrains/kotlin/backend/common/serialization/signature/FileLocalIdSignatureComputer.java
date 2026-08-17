package org.jetbrains.kotlin.backend.common.serialization.signature;

import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.declarations.IrAnonymousInitializer;
import org.jetbrains.kotlin.ir.declarations.IrDeclaration;
import org.jetbrains.kotlin.ir.declarations.IrField;
import org.jetbrains.kotlin.ir.declarations.IrLocalDelegatedProperty;
import org.jetbrains.kotlin.ir.declarations.IrOverridableDeclaration;
import org.jetbrains.kotlin.ir.declarations.IrPackageFragment;
import org.jetbrains.kotlin.ir.declarations.IrProperty;
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction;
import org.jetbrains.kotlin.ir.declarations.IrValueDeclaration;
import org.jetbrains.kotlin.ir.symbols.IrPropertySymbol;
import org.jetbrains.kotlin.ir.util.DumpIrTreeOptions;
import org.jetbrains.kotlin.ir.util.IdSignature;
import org.jetbrains.kotlin.ir.util.KotlinMangler;
import org.jetbrains.kotlin.ir.util.RenderIrElementKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aBG\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00126\u0010\u0004\u001a2\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f0\u0005¢\u0006\u0004\b\r\u0010\u000eJ\u0018\u0010\u0015\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\nH\u0002J\u0016\u0010\u0016\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\nJ\u0006\u0010\u0017\u001a\u00020\fJ\u0018\u0010\u0018\u001a\u00020\u0012*\u0006\u0012\u0002\b\u00030\u00192\u0006\u0010\u000b\u001a\u00020\nH\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R>\u0010\u0004\u001a2\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/serialization/signature/FileLocalIdSignatureComputer;", "", "mangler", "Lorg/jetbrains/kotlin/ir/util/KotlinMangler$IrMangler;", "signatureByDeclaration", "Lkotlin/Function2;", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclaration;", "Lkotlin/ParameterName;", "name", "declaration", "", "compatibleMode", "Lorg/jetbrains/kotlin/ir/util/IdSignature;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/ir/util/KotlinMangler$IrMangler;Lkotlin/jvm/functions/Function2;)V", "getMangler", "()Lorg/jetbrains/kotlin/ir/util/KotlinMangler$IrMangler;", "localIndex", "", "scopeIndex", "", "computeContainerIdSignature", "computeFileLocalIdSignature", "generateScopeLocalSignature", "stableIndexForFakeOverride", "Lorg/jetbrains/kotlin/ir/declarations/IrOverridableDeclaration;", "Companion", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class FileLocalIdSignatureComputer {
    private long localIndex;
    private final KotlinMangler.IrMangler mangler;
    private int scopeIndex;
    private final Function2<IrDeclaration, Boolean, IdSignature> signatureByDeclaration;

    public FileLocalIdSignatureComputer(KotlinMangler.IrMangler irMangler, Function2<? super IrDeclaration, ? super Boolean, ? extends IdSignature> function2) {
        irMangler.getClass();
        function2.getClass();
        this.mangler = irMangler;
        this.signatureByDeclaration = function2;
    }

    private final IdSignature computeContainerIdSignature(IrDeclaration declaration, boolean compatibleMode) {
        IrPropertySymbol correspondingPropertySymbol;
        if (declaration instanceof IrSimpleFunction) {
            correspondingPropertySymbol = ((IrSimpleFunction) declaration).getCorrespondingPropertySymbol();
        } else {
            correspondingPropertySymbol = declaration instanceof IrField ? ((IrField) declaration).getCorrespondingPropertySymbol() : null;
        }
        if (correspondingPropertySymbol != null) {
            return (IdSignature) this.signatureByDeclaration.invoke(correspondingPropertySymbol.getOwner(), Boolean.valueOf(compatibleMode));
        }
        IrPackageFragment parent = declaration.getParent();
        if (parent instanceof IrPackageFragment) {
            return new IdSignature.CommonSignature(parent.getPackageFqName().asString(), "", (Long) null, 0L, (String) null);
        }
        if (parent instanceof IrDeclaration) {
            return (IdSignature) this.signatureByDeclaration.invoke(parent, Boolean.valueOf(compatibleMode));
        }
        f2f.a("Unexpected container ", RenderIrElementKt.render$default(parent, (DumpIrTreeOptions) null, 1, (Object) null));
        return null;
    }

    private final long stableIndexForFakeOverride(IrOverridableDeclaration<?> irOverridableDeclaration, boolean z) {
        return this.mangler.signatureMangle(irOverridableDeclaration, z);
    }

    public final IdSignature computeFileLocalIdSignature(IrDeclaration declaration, boolean compatibleMode) {
        long jStableIndexForFakeOverride;
        long jStableIndexForFakeOverride2;
        declaration.getClass();
        if (!(declaration instanceof IrValueDeclaration) && !(declaration instanceof IrAnonymousInitializer) && !(declaration instanceof IrLocalDelegatedProperty)) {
            if (declaration instanceof IrSimpleFunction) {
                IdSignature idSignatureComputeContainerIdSignature = computeContainerIdSignature(declaration, compatibleMode);
                if (((IrSimpleFunction) declaration).isFakeOverride()) {
                    jStableIndexForFakeOverride2 = stableIndexForFakeOverride((IrOverridableDeclaration) declaration, compatibleMode);
                } else {
                    long j = this.localIndex + 1;
                    this.localIndex = j;
                    jStableIndexForFakeOverride2 = j;
                }
                return new IdSignature.FileLocalSignature(idSignatureComputeContainerIdSignature, jStableIndexForFakeOverride2);
            }
            if (!(declaration instanceof IrProperty)) {
                IdSignature idSignatureComputeContainerIdSignature2 = computeContainerIdSignature(declaration, compatibleMode);
                long j2 = this.localIndex + 1;
                this.localIndex = j2;
                return new IdSignature.FileLocalSignature(idSignatureComputeContainerIdSignature2, j2);
            }
            IdSignature idSignatureComputeContainerIdSignature3 = computeContainerIdSignature(declaration, compatibleMode);
            if (((IrProperty) declaration).isFakeOverride()) {
                jStableIndexForFakeOverride = stableIndexForFakeOverride((IrOverridableDeclaration) declaration, compatibleMode);
            } else {
                long j3 = this.localIndex + 1;
                this.localIndex = j3;
                jStableIndexForFakeOverride = j3;
            }
            return new IdSignature.FileLocalSignature(idSignatureComputeContainerIdSignature3, jStableIndexForFakeOverride);
        }
        return generateScopeLocalSignature();
    }

    public final IdSignature generateScopeLocalSignature() {
        int i = this.scopeIndex;
        this.scopeIndex = i + 1;
        return new IdSignature.ScopeLocalDeclaration(i);
    }

    public final KotlinMangler.IrMangler getMangler() {
        return this.mangler;
    }
}
