package org.jetbrains.kotlin.backend.common.linkage.issues;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.backend.common.linkage.issues.SignatureClashDetector;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.ir.IrDiagnosticReporter;
import org.jetbrains.kotlin.ir.IrElement;
import org.jetbrains.kotlin.ir.declarations.IrDeclaration;
import org.jetbrains.kotlin.ir.declarations.IrFile;
import org.jetbrains.kotlin.ir.util.IrUtilsKt;
import org.jetbrains.kotlin.utils.SmartSet;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00042\u00020\u0002B\u0007¢\u0006\u0004\b\u0005\u0010\u0006J\u001b\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00010\f2\u0006\u0010\r\u001a\u00028\u0000H\u0004¢\u0006\u0002\u0010\u000eJ\u001b\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00028\u00012\u0006\u0010\u0012\u001a\u00028\u0000¢\u0006\u0002\u0010\u0013J+\u0010\u0014\u001a\u00020\u00102\u0006\u0010\r\u001a\u00028\u00002\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00010\u00162\u0006\u0010\u0017\u001a\u00020\u0018H$¢\u0006\u0002\u0010\u0019J\u0010\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u0018H\u0016Jc\u0010\u001b\u001a\u00020\u0010\"\b\b\u0002\u0010\u001c*\u00020\u0002\"\b\b\u0003\u0010\u001d*\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u00182\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\u001c0\u001f2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u001d0\u00162\u0006\u0010 \u001a\u0002H\u001c2\u0014\u0010!\u001a\u0010\u0012\u0004\u0012\u0002H\u001d\u0012\u0006\u0012\u0004\u0018\u00010#0\"H\u0004¢\u0006\u0002\u0010$R6\u0010\u0007\u001a*\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\t0\bj\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\t`\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/linkage/issues/SignatureClashDetector;", "Signature", "", "Declaration", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclaration;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "declarationsBySignature", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "declarationsWithSignature", "", "signature", "(Ljava/lang/Object;)Ljava/util/Set;", "trackDeclaration", "", "declaration", "rawSignature", "(Lorg/jetbrains/kotlin/ir/declarations/IrDeclaration;Ljava/lang/Object;)V", "reportSignatureConflict", "declarations", "", "diagnosticReporter", "Lorg/jetbrains/kotlin/ir/IrDiagnosticReporter;", "(Ljava/lang/Object;Ljava/util/Collection;Lorg/jetbrains/kotlin/ir/IrDiagnosticReporter;)V", "reportErrorsTo", "reportSignatureClashTo", "Data", "ConflictingDeclaration", "diagnosticFactory", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory1;", "data", "reportOnIfSynthetic", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/ir/IrElement;", "(Lorg/jetbrains/kotlin/ir/IrDiagnosticReporter;Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory1;Ljava/util/Collection;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class SignatureClashDetector<Signature, Declaration extends IrDeclaration> {
    private final HashMap<Signature, Set<Declaration>> declarationsBySignature = new LinkedHashMap();

    public static Set a(Object obj) {
        obj.getClass();
        return SmartSet.Companion.create();
    }

    public static Set b(Function1 function1, Object obj) {
        return (Set) function1.invoke(obj);
    }

    public final Set<Declaration> declarationsWithSignature(Signature signature) {
        signature.getClass();
        Set<Declaration> set = this.declarationsBySignature.get(signature);
        return set == null ? SetsKt.emptySet() : set;
    }

    public void reportErrorsTo(IrDiagnosticReporter diagnosticReporter) {
        diagnosticReporter.getClass();
        for (Map.Entry<Signature, Set<Declaration>> entry : this.declarationsBySignature.entrySet()) {
            Signature key = entry.getKey();
            Set<Declaration> value = entry.getValue();
            if (value.size() > 1) {
                reportSignatureConflict(key, value, diagnosticReporter);
            }
        }
    }

    public final <Data, ConflictingDeclaration extends IrDeclaration> void reportSignatureClashTo(IrDiagnosticReporter diagnosticReporter, KtDiagnosticFactory1<Data> diagnosticFactory, Collection<? extends ConflictingDeclaration> declarations, Data data, Function1<? super ConflictingDeclaration, ? extends IrElement> reportOnIfSynthetic) {
        IrElement irElement;
        diagnosticReporter.getClass();
        diagnosticFactory.getClass();
        declarations.getClass();
        data.getClass();
        reportOnIfSynthetic.getClass();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator<T> it = declarations.iterator();
        while (it.hasNext()) {
            IrElement irElement2 = (IrDeclaration) it.next();
            IrFile fileOrNull = IrUtilsKt.getFileOrNull(irElement2);
            IrDiagnosticReporter.IrDiagnosticContext irDiagnosticContextAt = null;
            if (fileOrNull != null) {
                IrElement irElement3 = irElement2.getStartOffset() < 0 ? null : irElement2;
                if (irElement3 != null) {
                    irElement = irElement3;
                } else {
                    irElement = (IrElement) reportOnIfSynthetic.invoke(irElement2);
                    if (irElement != null) {
                    }
                }
                irDiagnosticContextAt = diagnosticReporter.at(IrUtilsKt.sourceElement(irElement), irElement, fileOrNull);
            }
            if (irDiagnosticContextAt != null) {
                linkedHashSet.add(irDiagnosticContextAt);
            }
        }
        linkedHashSet.isEmpty();
        declarations.isEmpty();
        Iterator it2 = linkedHashSet.iterator();
        while (it2.hasNext()) {
            ((IrDiagnosticReporter.IrDiagnosticContext) it2.next()).report(diagnosticFactory, data);
        }
    }

    public abstract void reportSignatureConflict(Signature signature, Collection<? extends Declaration> declarations, IrDiagnosticReporter diagnosticReporter);

    public final void trackDeclaration(Declaration declaration, Signature rawSignature) {
        declaration.getClass();
        rawSignature.getClass();
        HashMap<Signature, Set<Declaration>> map = this.declarationsBySignature;
        final Function1 function1 = new Function1() { // from class: zad
            public final Object invoke(Object obj) {
                return SignatureClashDetector.a(obj);
            }
        };
        map.computeIfAbsent(rawSignature, new Function() { // from class: abd
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return SignatureClashDetector.b(function1, obj);
            }
        }).add(declaration);
    }
}
