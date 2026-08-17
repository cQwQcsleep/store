package org.jetbrains.kotlin.fir.renderer;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.UninitializedPropertyAccessException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.arguments.PreprocessCommandLineArgumentsKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.annotations.AnnotationUseSiteTarget;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirAnnotationCall;
import org.jetbrains.kotlin.fir.expressions.FirArgumentList;
import org.jetbrains.kotlin.fir.expressions.FirCall;
import org.jetbrains.kotlin.fir.expressions.impl.FirResolvedArgumentList;
import org.jetbrains.kotlin.fir.references.FirErrorNamedReference;
import org.jetbrains.kotlin.fir.references.FirReference;
import org.jetbrains.kotlin.fir.references.FirResolvedNamedReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0016\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001cJ'\u0010\u001d\u001a\u00020\u00182\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020 0\u001f2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0000¢\u0006\u0002\b!J!\u0010\"\u001a\u00020\u00182\u0006\u0010#\u001a\u00020 2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0000¢\u0006\u0002\b$J\u0010\u0010%\u001a\u00020\u00182\u0006\u0010&\u001a\u00020\u001cH\u0014R\u001a\u0010\u0004\u001a\u00020\u0005X\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0018\u0010\n\u001a\u00060\u000bR\u00020\f8DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\u00108DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0013\u001a\u0004\u0018\u00010\u00148DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016¨\u0006'"}, d2 = {"Lorg/jetbrains/kotlin/fir/renderer/FirAnnotationRenderer;", Argument.Delimiters.none, "<init>", "()V", "components", "Lorg/jetbrains/kotlin/fir/renderer/FirRendererComponents;", "getComponents$org_jetbrains_kotlin_tree", "()Lorg/jetbrains/kotlin/fir/renderer/FirRendererComponents;", "setComponents$org_jetbrains_kotlin_tree", "(Lorg/jetbrains/kotlin/fir/renderer/FirRendererComponents;)V", "visitor", "Lorg/jetbrains/kotlin/fir/renderer/FirRenderer$Visitor;", "Lorg/jetbrains/kotlin/fir/renderer/FirRenderer;", "getVisitor", "()Lorg/jetbrains/kotlin/fir/renderer/FirRenderer$Visitor;", "printer", "Lorg/jetbrains/kotlin/fir/renderer/FirPrinter;", "getPrinter", "()Lorg/jetbrains/kotlin/fir/renderer/FirPrinter;", "callArgumentsRenderer", "Lorg/jetbrains/kotlin/fir/renderer/FirCallArgumentsRenderer;", "getCallArgumentsRenderer", "()Lorg/jetbrains/kotlin/fir/renderer/FirCallArgumentsRenderer;", "render", Argument.Delimiters.none, "annotationContainer", "Lorg/jetbrains/kotlin/fir/FirAnnotationContainer;", "explicitAnnotationUseSiteTarget", "Lorg/jetbrains/kotlin/descriptors/annotations/AnnotationUseSiteTarget;", "renderAnnotations", "annotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "renderAnnotations$org_jetbrains_kotlin_tree", "renderAnnotation", "annotation", "renderAnnotation$org_jetbrains_kotlin_tree", "renderUseSiteTarget", "it", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class FirAnnotationRenderer {
    public FirRendererComponents components;

    public static /* synthetic */ void render$default(FirAnnotationRenderer firAnnotationRenderer, FirAnnotationContainer firAnnotationContainer, AnnotationUseSiteTarget annotationUseSiteTarget, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: render");
            return;
        }
        if ((i & 2) != 0) {
            annotationUseSiteTarget = null;
        }
        firAnnotationRenderer.render(firAnnotationContainer, annotationUseSiteTarget);
    }

    public static /* synthetic */ void renderAnnotation$org_jetbrains_kotlin_tree$default(FirAnnotationRenderer firAnnotationRenderer, FirAnnotation firAnnotation, AnnotationUseSiteTarget annotationUseSiteTarget, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: renderAnnotation");
            return;
        }
        if ((i & 2) != 0) {
            annotationUseSiteTarget = null;
        }
        firAnnotationRenderer.renderAnnotation$org_jetbrains_kotlin_tree(firAnnotation, annotationUseSiteTarget);
    }

    public static /* synthetic */ void renderAnnotations$org_jetbrains_kotlin_tree$default(FirAnnotationRenderer firAnnotationRenderer, List list, AnnotationUseSiteTarget annotationUseSiteTarget, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: renderAnnotations");
            return;
        }
        if ((i & 2) != 0) {
            annotationUseSiteTarget = null;
        }
        firAnnotationRenderer.renderAnnotations$org_jetbrains_kotlin_tree(list, annotationUseSiteTarget);
    }

    public final FirCallArgumentsRenderer getCallArgumentsRenderer() {
        return getComponents$org_jetbrains_kotlin_tree().getCallArgumentsRenderer();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final FirRendererComponents getComponents$org_jetbrains_kotlin_tree() throws UninitializedPropertyAccessException {
        FirRendererComponents firRendererComponents = this.components;
        if (firRendererComponents != null) {
            return firRendererComponents;
        }
        Intrinsics.throwUninitializedPropertyAccessException("components");
        return null;
    }

    public final FirPrinter getPrinter() {
        return getComponents$org_jetbrains_kotlin_tree().getPrinter();
    }

    public final FirRenderer.Visitor getVisitor() {
        return getComponents$org_jetbrains_kotlin_tree().getVisitor();
    }

    public final void render(FirAnnotationContainer annotationContainer, AnnotationUseSiteTarget explicitAnnotationUseSiteTarget) {
        List<FirAnnotation> klibFileAnnotations;
        annotationContainer.getClass();
        FirDeclaration firDeclaration = annotationContainer instanceof FirDeclaration ? (FirDeclaration) annotationContainer : null;
        if (firDeclaration != null && (klibFileAnnotations = DeclarationAttributesKt.getKlibFileAnnotations(firDeclaration)) != null) {
            renderAnnotations$org_jetbrains_kotlin_tree$default(this, klibFileAnnotations, null, 2, null);
        }
        renderAnnotations$org_jetbrains_kotlin_tree(annotationContainer.getAnnotations(), explicitAnnotationUseSiteTarget);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void renderAnnotation$org_jetbrains_kotlin_tree(FirAnnotation annotation, AnnotationUseSiteTarget explicitAnnotationUseSiteTarget) {
        annotation.getClass();
        getPrinter().print(PreprocessCommandLineArgumentsKt.ARGFILE_ARGUMENT);
        if (explicitAnnotationUseSiteTarget == null) {
            explicitAnnotationUseSiteTarget = annotation.getUseSiteTarget();
        }
        if (explicitAnnotationUseSiteTarget != null) {
            renderUseSiteTarget(explicitAnnotationUseSiteTarget);
            getPrinter().print(":");
        }
        annotation.getAnnotationTypeRef().accept(getVisitor());
        if (annotation instanceof FirAnnotationCall) {
            FirResolvePhaseRenderer resolvePhaseRenderer = getComponents$org_jetbrains_kotlin_tree().getResolvePhaseRenderer();
            if (resolvePhaseRenderer != null) {
                resolvePhaseRenderer.render((FirAnnotationCall) annotation);
            }
            FirAnnotationCall firAnnotationCall = (FirAnnotationCall) annotation;
            FirReference calleeReference = firAnnotationCall.getCalleeReference();
            if ((calleeReference instanceof FirResolvedNamedReference) || (calleeReference instanceof FirErrorNamedReference)) {
                FirArgumentList argumentList = firAnnotationCall.getArgumentList();
                if (argumentList instanceof FirResolvedArgumentList) {
                    FirCallArgumentsRenderer callArgumentsRenderer = getCallArgumentsRenderer();
                    if (callArgumentsRenderer != null) {
                        callArgumentsRenderer.renderArgumentsWithEvaluated((FirResolvedArgumentList) argumentList, firAnnotationCall.getArgumentMapping());
                    }
                } else {
                    FirCallArgumentsRenderer callArgumentsRenderer2 = getCallArgumentsRenderer();
                    if (callArgumentsRenderer2 != null) {
                        callArgumentsRenderer2.renderArgumentMapping(firAnnotationCall.getArgumentMapping());
                    }
                }
            } else {
                getVisitor().visitCall((FirCall) annotation);
            }
        } else {
            FirCallArgumentsRenderer callArgumentsRenderer3 = getCallArgumentsRenderer();
            if (callArgumentsRenderer3 != null) {
                callArgumentsRenderer3.renderArgumentMapping(annotation.getArgumentMapping());
            }
        }
        if (annotation.getUseSiteTarget() == AnnotationUseSiteTarget.FILE) {
            getPrinter().println(new Object[0]);
        } else {
            getPrinter().print(Argument.Delimiters.space);
        }
    }

    public final void renderAnnotations$org_jetbrains_kotlin_tree(List<? extends FirAnnotation> annotations, AnnotationUseSiteTarget explicitAnnotationUseSiteTarget) {
        annotations.getClass();
        Iterator<? extends FirAnnotation> it = annotations.iterator();
        while (it.hasNext()) {
            renderAnnotation$org_jetbrains_kotlin_tree(it.next(), explicitAnnotationUseSiteTarget);
        }
    }

    public void renderUseSiteTarget(AnnotationUseSiteTarget it) {
        it.getClass();
        getPrinter().print(it.name());
    }

    public final void setComponents$org_jetbrains_kotlin_tree(FirRendererComponents firRendererComponents) {
        firRendererComponents.getClass();
        this.components = firRendererComponents;
    }
}
