package org.jetbrains.kotlin.fir.lazy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.properties.ReadWriteProperty;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirEvaluatorResult;
import org.jetbrains.kotlin.fir.backend.Fir2IrComponents;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpressionEvaluator;
import org.jetbrains.kotlin.fir.expressions.builder.FirAnnotationArgumentMappingBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirAnnotationBuilder;
import org.jetbrains.kotlin.fir.lazy.AbstractFir2IrLazyDeclaration;
import org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolverKt;
import org.jetbrains.kotlin.ir.declarations.IrDeclaration;
import org.jetbrains.kotlin.ir.declarations.IrFactory;
import org.jetbrains.kotlin.ir.declarations.impl.IrFactoryImpl;
import org.jetbrains.kotlin.ir.declarations.lazy.IrLazyDeclarationBase;
import org.jetbrains.kotlin.ir.declarations.lazy.LazyUtilKt;
import org.jetbrains.kotlin.ir.expressions.IrAnnotation;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u0005J\u001c\u0010\r\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u000eH\u0016J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013H\u0002R\u0012\u0010\u0006\u001a\u00028\u0000X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0015À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/lazy/AbstractFir2IrLazyDeclaration;", "F", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclaration;", "Lorg/jetbrains/kotlin/ir/declarations/lazy/IrLazyDeclarationBase;", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;", "fir", "getFir", "()Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "factory", "Lorg/jetbrains/kotlin/ir/declarations/IrFactory;", "getFactory", "()Lorg/jetbrains/kotlin/ir/declarations/IrFactory;", "createLazyAnnotations", "Lkotlin/properties/ReadWriteProperty;", Argument.Delimiters.none, Argument.Delimiters.none, "Lorg/jetbrains/kotlin/ir/expressions/IrAnnotation;", "evaluateAnnotationArguments", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "annotation", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public interface AbstractFir2IrLazyDeclaration<F extends FirDeclaration> extends Fir2IrComponents, IrDeclaration, IrLazyDeclarationBase {
    static List a(AbstractFir2IrLazyDeclaration abstractFir2IrLazyDeclaration) {
        FirLazyDeclarationResolverKt.lazyResolveToPhase(abstractFir2IrLazyDeclaration.getFir(), FirResolvePhase.ANNOTATION_ARGUMENTS);
        List<FirAnnotation> annotations = abstractFir2IrLazyDeclaration.getFir().getAnnotations();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = annotations.iterator();
        while (it.hasNext()) {
            IrAnnotation irAnnotationConvertToIrAnnotation = abstractFir2IrLazyDeclaration.getCallGenerator().convertToIrAnnotation(abstractFir2IrLazyDeclaration.evaluateAnnotationArguments((FirAnnotation) it.next()));
            IrAnnotation irAnnotation = irAnnotationConvertToIrAnnotation instanceof IrAnnotation ? irAnnotationConvertToIrAnnotation : null;
            if (irAnnotation != null) {
                arrayList.add(irAnnotation);
            }
        }
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private default FirAnnotation evaluateAnnotationArguments(FirAnnotation annotation) {
        Map mapEvaluateAnnotationArguments$default = FirExpressionEvaluator.evaluateAnnotationArguments$default(FirExpressionEvaluator.INSTANCE, annotation, getSession(), null, 4, null);
        FirAnnotationBuilder firAnnotationBuilder = new FirAnnotationBuilder();
        firAnnotationBuilder.setSource(annotation.getSource());
        firAnnotationBuilder.setUseSiteTarget(annotation.getUseSiteTarget());
        firAnnotationBuilder.setAnnotationTypeRef(annotation.getAnnotationTypeRef());
        firAnnotationBuilder.setArgumentMapping(annotation.getArgumentMapping());
        firAnnotationBuilder.getTypeArguments().addAll(annotation.getTypeArguments());
        FirAnnotationArgumentMappingBuilder firAnnotationArgumentMappingBuilder = new FirAnnotationArgumentMappingBuilder();
        firAnnotationArgumentMappingBuilder.setSource(annotation.getArgumentMapping().getSource());
        for (Map.Entry<Name, FirExpression> entry : annotation.getArgumentMapping().getMapping().entrySet()) {
            Name key = entry.getKey();
            FirExpression value = entry.getValue();
            FirEvaluatorResult firEvaluatorResult = (FirEvaluatorResult) mapEvaluateAnnotationArguments$default.get(key);
            FirElement firElement = null;
            if (firEvaluatorResult != null) {
                if (firEvaluatorResult instanceof FirEvaluatorResult.CompileTimeException) {
                } else if (firEvaluatorResult instanceof FirEvaluatorResult.Evaluated) {
                    FirElement result = ((FirEvaluatorResult.Evaluated) firEvaluatorResult).getResult();
                    firElement = (FirExpression) (result instanceof FirExpression ? result : null);
                }
            }
            Map<Name, FirExpression> mapping = firAnnotationArgumentMappingBuilder.getMapping();
            if (firElement != null) {
                value = firElement;
            }
            mapping.put(key, value);
        }
        firAnnotationBuilder.setArgumentMapping(firAnnotationArgumentMappingBuilder.build());
        return firAnnotationBuilder.mo288build();
    }

    default ReadWriteProperty<Object, List<IrAnnotation>> createLazyAnnotations() {
        return LazyUtilKt.lazyVar(getLock(), new Function0() { // from class: im
            public final Object invoke() {
                return AbstractFir2IrLazyDeclaration.a(this.b);
            }
        });
    }

    default IrFactory getFactory() {
        return IrFactoryImpl.INSTANCE;
    }

    F getFir();
}
