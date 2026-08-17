package org.jetbrains.kotlin.fir.contracts;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.description.KtContractDescriptionElement;
import org.jetbrains.kotlin.contracts.description.KtEffectDeclaration;
import org.jetbrains.kotlin.fir.contracts.builder.FirContractElementDeclarationBuilder;
import org.jetbrains.kotlin.fir.contracts.builder.FirEffectDeclarationBuilder;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a&\u0010\u0006\u001a\u00020\u0002*\u0012\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007j\u0002`\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u001a&\u0010\u0006\u001a\u00020\r*\u0012\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u000ej\u0002`\u000f2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\"\u001d\u0010\u0000\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0010"}, d2 = {"effects", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/contracts/FirEffectDeclaration;", "Lorg/jetbrains/kotlin/fir/contracts/FirContractDescription;", "getEffects", "(Lorg/jetbrains/kotlin/fir/contracts/FirContractDescription;)Ljava/util/List;", "toFirElement", "Lorg/jetbrains/kotlin/contracts/description/KtEffectDeclaration;", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "Lorg/jetbrains/kotlin/fir/contracts/description/ConeEffectDeclaration;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "Lorg/jetbrains/kotlin/fir/contracts/FirContractElementDeclaration;", "Lorg/jetbrains/kotlin/contracts/description/KtContractDescriptionElement;", "Lorg/jetbrains/kotlin/fir/contracts/description/ConeContractDescriptionElement;", "org.jetbrains.kotlin:tree"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ContractUtilsKt {
    public static final List<FirEffectDeclaration> getEffects(FirContractDescription firContractDescription) {
        firContractDescription.getClass();
        FirResolvedContractDescription firResolvedContractDescription = firContractDescription instanceof FirResolvedContractDescription ? (FirResolvedContractDescription) firContractDescription : null;
        if (firResolvedContractDescription != null) {
            return firResolvedContractDescription.getEffects();
        }
        return null;
    }

    public static final FirEffectDeclaration toFirElement(KtEffectDeclaration<ConeKotlinType, ConeDiagnostic> ktEffectDeclaration, KtSourceElement ktSourceElement) {
        ktEffectDeclaration.getClass();
        FirEffectDeclarationBuilder firEffectDeclarationBuilder = new FirEffectDeclarationBuilder();
        if (ktSourceElement != null) {
            firEffectDeclarationBuilder.setSource(ktSourceElement);
        }
        firEffectDeclarationBuilder.setEffect(ktEffectDeclaration);
        return firEffectDeclarationBuilder.build();
    }

    public static /* synthetic */ FirEffectDeclaration toFirElement$default(KtEffectDeclaration ktEffectDeclaration, KtSourceElement ktSourceElement, int i, Object obj) {
        if ((i & 1) != 0) {
            ktSourceElement = null;
        }
        return toFirElement((KtEffectDeclaration<ConeKotlinType, ConeDiagnostic>) ktEffectDeclaration, ktSourceElement);
    }

    public static /* synthetic */ FirContractElementDeclaration toFirElement$default(KtContractDescriptionElement ktContractDescriptionElement, KtSourceElement ktSourceElement, int i, Object obj) {
        if ((i & 1) != 0) {
            ktSourceElement = null;
        }
        return toFirElement((KtContractDescriptionElement<ConeKotlinType, ConeDiagnostic>) ktContractDescriptionElement, ktSourceElement);
    }

    public static final FirContractElementDeclaration toFirElement(KtContractDescriptionElement<ConeKotlinType, ConeDiagnostic> ktContractDescriptionElement, KtSourceElement ktSourceElement) {
        ktContractDescriptionElement.getClass();
        FirContractElementDeclarationBuilder firContractElementDeclarationBuilder = new FirContractElementDeclarationBuilder();
        if (ktSourceElement != null) {
            firContractElementDeclarationBuilder.setSource(ktSourceElement);
        }
        firContractElementDeclarationBuilder.setEffect(ktContractDescriptionElement);
        return firContractElementDeclarationBuilder.build();
    }
}
