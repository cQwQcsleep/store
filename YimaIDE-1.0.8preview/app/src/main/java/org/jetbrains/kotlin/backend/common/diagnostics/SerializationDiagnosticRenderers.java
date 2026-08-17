package org.jetbrains.kotlin.backend.common.diagnostics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import org.jetbrains.kotlin.backend.common.diagnostics.ConflictingKlibSignaturesData;
import org.jetbrains.kotlin.backend.common.diagnostics.SerializationDiagnosticRenderers;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor;
import org.jetbrains.kotlin.diagnostics.rendering.CommonRenderers;
import org.jetbrains.kotlin.diagnostics.rendering.ContextIndependentParameterRenderer;
import org.jetbrains.kotlin.diagnostics.rendering.DiagnosticParameterRendererKt;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirDiagnosticRenderers;
import org.jetbrains.kotlin.ir.declarations.DeclarationSymbolOwner;
import org.jetbrains.kotlin.ir.declarations.IrDeclaration;
import org.jetbrains.kotlin.ir.declarations.IrField;
import org.jetbrains.kotlin.ir.declarations.IrMetadataSourceOwner;
import org.jetbrains.kotlin.ir.declarations.IrProperty;
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction;
import org.jetbrains.kotlin.ir.declarations.MetadataSource;
import org.jetbrains.kotlin.ir.descriptors.IrBasedDeclarationDescriptor;
import org.jetbrains.kotlin.ir.descriptors.IrBasedDescriptorsKt;
import org.jetbrains.kotlin.ir.util.IdSignatureRenderer;
import org.jetbrains.kotlin.ir.util.IdSignatureRendererKt;
import org.jetbrains.kotlin.mpp.DeclarationSymbolMarker;
import org.jetbrains.kotlin.renderer.DescriptorRenderer;
import org.jetbrains.kotlin.resolve.MemberComparator;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/diagnostics/SerializationDiagnosticRenderers;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "CONFLICTING_KLIB_SIGNATURES_DATA", "Lorg/jetbrains/kotlin/diagnostics/rendering/ContextIndependentParameterRenderer;", "Lorg/jetbrains/kotlin/backend/common/diagnostics/ConflictingKlibSignaturesData;", "getCONFLICTING_KLIB_SIGNATURES_DATA", "()Lorg/jetbrains/kotlin/diagnostics/rendering/ContextIndependentParameterRenderer;", "getDeclarationSymbol", "Lorg/jetbrains/kotlin/mpp/DeclarationSymbolMarker;", "descriptor", "Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptor;", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SerializationDiagnosticRenderers {
    private static final ContextIndependentParameterRenderer<ConflictingKlibSignaturesData> CONFLICTING_KLIB_SIGNATURES_DATA;
    public static final SerializationDiagnosticRenderers INSTANCE = new SerializationDiagnosticRenderers();

    static {
        MemberComparator memberComparator = MemberComparator.INSTANCE;
        memberComparator.getClass();
        CONFLICTING_KLIB_SIGNATURES_DATA = CommonRenderers.renderConflictingSignatureData("IR", memberComparator, DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: g7d
            public final Object invoke(Object obj) {
                return SerializationDiagnosticRenderers.d((DeclarationDescriptor) obj);
            }
        }), new Function2() { // from class: h7d
            public final Object invoke(Object obj, Object obj2) {
                return SerializationDiagnosticRenderers.b((StringBuilder) obj, (ConflictingKlibSignaturesData) obj2);
            }
        }, new Function1() { // from class: i7d
            public final Object invoke(Object obj) {
                return SerializationDiagnosticRenderers.a((ConflictingKlibSignaturesData) obj);
            }
        }, new Function1() { // from class: j7d
            public final Object invoke(Object obj) {
                return SerializationDiagnosticRenderers.c((ConflictingKlibSignaturesData) obj);
            }
        });
    }

    private SerializationDiagnosticRenderers() {
    }

    public static Collection a(ConflictingKlibSignaturesData conflictingKlibSignaturesData) {
        conflictingKlibSignaturesData.getClass();
        Collection<IrDeclaration> declarations = conflictingKlibSignaturesData.getDeclarations();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(declarations, 10));
        Iterator<T> it = declarations.iterator();
        while (it.hasNext()) {
            arrayList.add(IrBasedDescriptorsKt.toIrBasedDescriptor((IrDeclaration) it.next()));
        }
        return arrayList;
    }

    public static Unit b(StringBuilder sb, ConflictingKlibSignaturesData conflictingKlibSignaturesData) {
        sb.getClass();
        conflictingKlibSignaturesData.getClass();
        sb.append(IdSignatureRendererKt.render$default(conflictingKlibSignaturesData.getSignature(), (IdSignatureRenderer) null, 1, (Object) null));
        return Unit.INSTANCE;
    }

    public static String c(ConflictingKlibSignaturesData conflictingKlibSignaturesData) {
        conflictingKlibSignaturesData.getClass();
        Collection<IrDeclaration> declarations = conflictingKlibSignaturesData.getDeclarations();
        if ((declarations instanceof Collection) && declarations.isEmpty()) {
            return "functions";
        }
        Iterator<T> it = declarations.iterator();
        while (it.hasNext()) {
            if (!(((IrDeclaration) it.next()) instanceof IrSimpleFunction)) {
                Collection<IrDeclaration> declarations2 = conflictingKlibSignaturesData.getDeclarations();
                if ((declarations2 instanceof Collection) && declarations2.isEmpty()) {
                    return "properties";
                }
                Iterator<T> it2 = declarations2.iterator();
                while (it2.hasNext()) {
                    if (!(((IrDeclaration) it2.next()) instanceof IrProperty)) {
                        Collection<IrDeclaration> declarations3 = conflictingKlibSignaturesData.getDeclarations();
                        if ((declarations3 instanceof Collection) && declarations3.isEmpty()) {
                            return "fields";
                        }
                        Iterator<T> it3 = declarations3.iterator();
                        while (it3.hasNext()) {
                            if (!(((IrDeclaration) it3.next()) instanceof IrField)) {
                                return "declarations";
                            }
                        }
                        return "fields";
                    }
                }
                return "properties";
            }
        }
        return "functions";
    }

    public static String d(DeclarationDescriptor declarationDescriptor) {
        SerializationDiagnosticRenderers serializationDiagnosticRenderers = INSTANCE;
        declarationDescriptor.getClass();
        DeclarationSymbolMarker declarationSymbol = serializationDiagnosticRenderers.getDeclarationSymbol(declarationDescriptor);
        if (declarationSymbol != null) {
            String strRender = FirDiagnosticRenderers.INSTANCE.getSYMBOL_WITH_LOCATION().render(declarationSymbol);
            if (strRender.length() == 0) {
                strRender = null;
            }
            if (strRender != null) {
                return strRender;
            }
        }
        return DescriptorRenderer.WITHOUT_MODIFIERS.render(declarationDescriptor);
    }

    private final DeclarationSymbolMarker getDeclarationSymbol(DeclarationDescriptor descriptor) {
        IrBasedDeclarationDescriptor irBasedDeclarationDescriptor = descriptor instanceof IrBasedDeclarationDescriptor ? (IrBasedDeclarationDescriptor) descriptor : null;
        IrDeclaration owner = irBasedDeclarationDescriptor != null ? irBasedDeclarationDescriptor.getOwner() : null;
        IrMetadataSourceOwner irMetadataSourceOwner = owner instanceof IrMetadataSourceOwner ? (IrMetadataSourceOwner) owner : null;
        MetadataSource metadata = irMetadataSourceOwner != null ? irMetadataSourceOwner.getMetadata() : null;
        DeclarationSymbolOwner declarationSymbolOwner = metadata instanceof DeclarationSymbolOwner ? (DeclarationSymbolOwner) metadata : null;
        if (declarationSymbolOwner != null) {
            return declarationSymbolOwner.getSymbol();
        }
        return null;
    }

    public final ContextIndependentParameterRenderer<ConflictingKlibSignaturesData> getCONFLICTING_KLIB_SIGNATURES_DATA() {
        return CONFLICTING_KLIB_SIGNATURES_DATA;
    }
}
