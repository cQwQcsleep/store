package org.jetbrains.kotlin.extensions;

import kotlin.Metadata;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor;
import org.jetbrains.kotlin.descriptors.FunctionDescriptor;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.psi.KtModifierListOwner;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fJ6\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u00072\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000eH\u0016ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0010À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/extensions/DeclarationAttributeAltererExtension;", "", "refineDeclarationModality", "Lorg/jetbrains/kotlin/descriptors/Modality;", "modifierListOwner", "Lorg/jetbrains/kotlin/psi/KtModifierListOwner;", "declaration", "Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptor;", "containingDeclaration", "currentModality", "isImplicitModality", "", "shouldConvertFirstSAMParameterToReceiver", "function", "Lorg/jetbrains/kotlin/descriptors/FunctionDescriptor;", "Companion", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public interface DeclarationAttributeAltererExtension {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lorg/jetbrains/kotlin/extensions/DeclarationAttributeAltererExtension$Companion;", "Lorg/jetbrains/kotlin/extensions/ProjectExtensionDescriptor;", "Lorg/jetbrains/kotlin/extensions/DeclarationAttributeAltererExtension;", "<init>", "()V", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
    public static final class Companion extends ProjectExtensionDescriptor<DeclarationAttributeAltererExtension> {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
            super("org.jetbrains.kotlin.declarationAttributeAltererExtension", DeclarationAttributeAltererExtension.class);
        }
    }

    default Modality refineDeclarationModality(KtModifierListOwner modifierListOwner, DeclarationDescriptor declaration, DeclarationDescriptor containingDeclaration, Modality currentModality, boolean isImplicitModality) {
        modifierListOwner.getClass();
        currentModality.getClass();
        return null;
    }

    default boolean shouldConvertFirstSAMParameterToReceiver(FunctionDescriptor function) {
        function.getClass();
        return false;
    }
}
