package org.jetbrains.kotlin.fir.renderer;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Map;
import kotlin.Metadata;
import kotlin.UninitializedPropertyAccessException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SpreadBuilder;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.builtins.StandardNames;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.renderer.ConeTypeRendererForReadability;
import org.jetbrains.kotlin.fir.types.ConeClassifierLookupTag;
import org.jetbrains.kotlin.fir.types.ConeDefinitelyNotNullType;
import org.jetbrains.kotlin.fir.types.ConeFlexibleType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeRigidType;
import org.jetbrains.kotlin.fir.types.ConeSimpleKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeConstructorMarker;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.renderer.RenderingUtilsKt;
import org.jetbrains.kotlin.types.model.TypeConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B/\u0012\u0018\b\u0002\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0018\u00010\u0003\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0004\b\t\u0010\nB=\b\u0016\u0012\n\u0010\u000b\u001a\u00060\fj\u0002`\r\u0012\u0018\b\u0002\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0018\u00010\u0003\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0004\b\t\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0014J\u0010\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\b\u0010\u0016\u001a\u00020\u0000H\u0002J\u001a\u0010\u0017\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u0005H\u0002J\u0018\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0005H\u0014J\u0010\u0010\u001e\u001a\u00020\u00052\u0006\u0010\u001f\u001a\u00020 H\u0002J\u0010\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020 H\u0014J \u0010$\u001a\u00020\u00052\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\u00052\u0006\u0010(\u001a\u00020\u0005H\u0016R\u001e\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lorg/jetbrains/kotlin/fir/renderer/ConeTypeRendererForReadability;", "Lorg/jetbrains/kotlin/fir/renderer/ConeTypeRendererForDebugInfo;", "preRenderedConstructors", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/types/model/TypeConstructorMarker;", Argument.Delimiters.none, "idRendererCreator", "Lkotlin/Function0;", "Lorg/jetbrains/kotlin/fir/renderer/ConeIdRenderer;", "<init>", "(Ljava/util/Map;Lkotlin/jvm/functions/Function0;)V", "builder", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "(Ljava/lang/StringBuilder;Ljava/util/Map;Lkotlin/jvm/functions/Function0;)V", "render", Argument.Delimiters.none, "flexibleType", "Lorg/jetbrains/kotlin/fir/types/ConeFlexibleType;", "renderBound", "bound", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "newRenderer", "renderFlexibleTypeCompact", "lowerRendered", "upperRendered", "renderSimpleType", ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/fir/types/ConeSimpleKotlinType;", "nullabilityMarker", "renderTypeArgument", "typeArgument", "Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "couldBenefitFromParenthesizing", Argument.Delimiters.none, "projection", "renderDiagnostic", "diagnostic", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "prefix", "suffix", "org.jetbrains.kotlin:cones"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class ConeTypeRendererForReadability extends ConeTypeRendererForDebugInfo {
    private final Function0<ConeIdRenderer> idRendererCreator;
    private final Map<TypeConstructorMarker, String> preRenderedConstructors;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ConeTypeRendererForReadability(StringBuilder sb, Map<TypeConstructorMarker, String> map, Function0<? extends ConeIdRenderer> function0) {
        this(map, function0);
        sb.getClass();
        function0.getClass();
        setBuilder(sb);
        setIdRenderer((ConeIdRenderer) function0.invoke());
        getIdRenderer().setBuilder(sb);
    }

    public static String b(String str) {
        String str2 = StandardNames.BUILT_INS_PACKAGE_FQ_NAME.asString() + '.';
        if (!StringsKt.startsWith$default(str, str2, false, 2, (Object) null)) {
            str2 = null;
        }
        return str2 == null ? Argument.Delimiters.none : str2;
    }

    public static String c(String str) {
        String str2 = StandardNames.COLLECTIONS_PACKAGE_FQ_NAME.asString() + '.';
        if (!StringsKt.startsWith$default(str, str2, false, 2, (Object) null)) {
            str2 = null;
        }
        return str2 == null ? Argument.Delimiters.none : str2;
    }

    private final ConeTypeRendererForReadability newRenderer() {
        return new ConeTypeRendererForReadability(new StringBuilder(), this.preRenderedConstructors, this.idRendererCreator);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    private final String renderBound(ConeKotlinType bound) throws UninitializedPropertyAccessException {
        ConeTypeRendererForReadability coneTypeRendererForReadabilityNewRenderer = newRenderer();
        ConeTypeRenderer.render$default(coneTypeRendererForReadabilityNewRenderer, bound, null, 2, null);
        return coneTypeRendererForReadabilityNewRenderer.getBuilder().toString();
    }

    private final String renderFlexibleTypeCompact(final String lowerRendered, String upperRendered) {
        if (Intrinsics.areEqual(lowerRendered, StringsKt.replace$default(upperRendered, "?", Argument.Delimiters.none, false, 4, (Object) null))) {
            return StringsKt.replace$default(upperRendered, "?", "!", false, 4, (Object) null);
        }
        if (StringsKt.endsWith$default(upperRendered, "?", false, 2, (Object) null)) {
            if (Intrinsics.areEqual(lowerRendered + '?', upperRendered)) {
                return lowerRendered + '!';
            }
        }
        if (!Intrinsics.areEqual("(" + lowerRendered + ")?", upperRendered)) {
            return RenderingUtilsKt.renderFlexibleMutabilityOrArrayElementVarianceType$default(lowerRendered, upperRendered, new Function0() { // from class: oq2
                public final Object invoke() {
                    return ConeTypeRendererForReadability.c(lowerRendered);
                }
            }, new Function0() { // from class: pq2
                public final Object invoke() {
                    return ConeTypeRendererForReadability.b(lowerRendered);
                }
            }, (Function1) null, 16, (Object) null);
        }
        return "(" + lowerRendered + ")!";
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    private final String renderTypeArgument(ConeTypeProjection typeArgument) throws UninitializedPropertyAccessException {
        ConeTypeRendererForReadability coneTypeRendererForReadabilityNewRenderer = newRenderer();
        coneTypeRendererForReadabilityNewRenderer.render(typeArgument);
        return coneTypeRendererForReadabilityNewRenderer.getBuilder().toString();
    }

    @Override // org.jetbrains.kotlin.fir.renderer.ConeTypeRenderer
    public boolean couldBenefitFromParenthesizing(ConeTypeProjection projection) {
        Map<TypeConstructorMarker, String> map;
        String str;
        projection.getClass();
        ConeKotlinType coneKotlinType = projection instanceof ConeKotlinType ? (ConeKotlinType) projection : null;
        ConeClassifierLookupTag lookupTagIfAny = coneKotlinType != null ? ConeTypeUtilsKt.getLookupTagIfAny(coneKotlinType) : null;
        if (lookupTagIfAny == null || (map = this.preRenderedConstructors) == null || (str = map.get(lookupTagIfAny)) == null || !StringsKt.contains$default(str, Argument.Delimiters.space, false, 2, (Object) null)) {
            return super.couldBenefitFromParenthesizing(projection);
        }
        return true;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    @Override // org.jetbrains.kotlin.fir.renderer.ConeTypeRendererForDebugInfo, org.jetbrains.kotlin.fir.renderer.ConeTypeRenderer
    public void render(ConeFlexibleType flexibleType) throws UninitializedPropertyAccessException {
        flexibleType.getClass();
        ConeRigidType lowerBound = flexibleType.getLowerBound();
        String strRenderBound = renderBound(lowerBound);
        String strRenderBound2 = renderBound(flexibleType.getUpperBound());
        String strRenderFlexibleTypeCompact = renderFlexibleTypeCompact(strRenderBound, strRenderBound2);
        if (strRenderFlexibleTypeCompact == null) {
            strRenderFlexibleTypeCompact = lowerBound instanceof ConeDefinitelyNotNullType ? renderFlexibleTypeCompact(renderBound(((ConeDefinitelyNotNullType) lowerBound).getOriginal()), strRenderBound2) : null;
            if (strRenderFlexibleTypeCompact == null) {
                strRenderFlexibleTypeCompact = "(" + strRenderBound + ".." + strRenderBound2 + ')';
            }
        }
        getBuilder().append(strRenderFlexibleTypeCompact);
    }

    @Override // org.jetbrains.kotlin.fir.renderer.ConeTypeRenderer
    public String renderDiagnostic(ConeDiagnostic diagnostic, String prefix, String suffix) {
        diagnostic.getClass();
        prefix.getClass();
        suffix.getClass();
        return "??? (" + diagnostic.getReadableDescriptionAsTypeConstructor() + ')';
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    @Override // org.jetbrains.kotlin.fir.renderer.ConeTypeRenderer
    public void renderSimpleType(ConeSimpleKotlinType type, String nullabilityMarker) throws UninitializedPropertyAccessException {
        String str;
        type.getClass();
        nullabilityMarker.getClass();
        ConeTypeConstructorMarker constructor = ConeTypeUtilsKt.getConstructor(type);
        Map<TypeConstructorMarker, String> map = this.preRenderedConstructors;
        if (map == null || (str = map.get(constructor)) == null) {
            super.renderSimpleType(type, nullabilityMarker);
            return;
        }
        SpreadBuilder spreadBuilder = new SpreadBuilder(2);
        ConeTypeProjection[] typeArguments = type.getTypeArguments();
        ArrayList arrayList = new ArrayList(typeArguments.length);
        for (ConeTypeProjection coneTypeProjection : typeArguments) {
            arrayList.add(renderTypeArgument(coneTypeProjection));
        }
        spreadBuilder.addSpread(arrayList.toArray(new String[0]));
        spreadBuilder.add(nullabilityMarker);
        getBuilder().append(MessageFormat.format(str, spreadBuilder.toArray(new Object[spreadBuilder.size()])));
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public ConeTypeRendererForReadability(Map<TypeConstructorMarker, String> map, Function0<? extends ConeIdRenderer> function0) {
        super(false, (ConeAttributeRenderer) ConeAttributeRenderer.None.INSTANCE, 1, (DefaultConstructorMarker) null);
        function0.getClass();
        this.preRenderedConstructors = map;
        this.idRendererCreator = function0;
    }

    public /* synthetic */ ConeTypeRendererForReadability(StringBuilder sb, Map map, Function0 function0, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(sb, (i & 2) != 0 ? null : map, function0);
    }

    public /* synthetic */ ConeTypeRendererForReadability(Map map, Function0 function0, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : map, function0);
    }
}
