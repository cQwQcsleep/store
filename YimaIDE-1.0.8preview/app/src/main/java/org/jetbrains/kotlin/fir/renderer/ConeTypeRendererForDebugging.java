package org.jetbrains.kotlin.fir.renderer;

import kotlin.Metadata;
import kotlin.UninitializedPropertyAccessException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.builtins.functions.FunctionTypeKind;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003B\u0015\b\u0016\u0012\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006¢\u0006\u0004\b\u0002\u0010\u0007J?\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0014\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\r2\u0017\u0010\u000f\u001a\u0013\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\t0\r¢\u0006\u0002\b\u0011H\u0016¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/renderer/ConeTypeRendererForDebugging;", "Lorg/jetbrains/kotlin/fir/renderer/ConeTypeRenderer;", "<init>", "()V", "builder", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "(Ljava/lang/StringBuilder;)V", "renderAsPossibleFunctionType", Argument.Delimiters.none, ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "functionClassKindExtractor", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/builtins/functions/FunctionTypeKind;", "renderType", "Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "Lkotlin/ExtensionFunctionType;", "org.jetbrains.kotlin:cones"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeTypeRendererForDebugging extends ConeTypeRenderer {
    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ConeTypeRendererForDebugging(StringBuilder sb) {
        this();
        sb.getClass();
        setBuilder(sb);
        setIdRenderer(new ConeIdRendererForDebugging());
        getIdRenderer().setBuilder(sb);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    @Override // org.jetbrains.kotlin.fir.renderer.ConeTypeRenderer
    public void renderAsPossibleFunctionType(ConeKotlinType type, Function1<? super ConeKotlinType, ? extends FunctionTypeKind> functionClassKindExtractor, Function1<? super ConeTypeProjection, Unit> renderType) throws UninitializedPropertyAccessException {
        type.getClass();
        functionClassKindExtractor.getClass();
        renderType.getClass();
        getBuilder().append("R|");
        super.renderAsPossibleFunctionType(type, functionClassKindExtractor, renderType);
        getBuilder().append("|");
    }

    public ConeTypeRendererForDebugging() {
        super(null, false, 3, null);
    }
}
