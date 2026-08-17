package org.jetbrains.kotlin.fir.java;

import com.intellij.psi.PsiField;
import com.intellij.psi.impl.cache.TypeInfo;
import com.intellij.psi.impl.compiled.ClsClassImpl;
import com.intellij.psi.impl.java.stubs.JavaFieldStubElementType;
import com.intellij.psi.impl.java.stubs.PsiFieldStub;
import com.intellij.psi.impl.java.stubs.impl.PsiClassStubImpl;
import com.intellij.psi.stubs.StubBase;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirEvaluatorResult;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.expressions.FirExpressionEvaluator;
import org.jetbrains.kotlin.fir.expressions.FirLiteralExpression;
import org.jetbrains.kotlin.fir.java.FirJavaElementFinder$buildFieldStubForConst$psiField$1;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.load.java.structure.impl.NotEvaluatedConstAware;
import org.jetbrains.kotlin.util.capitalizeDecapitalize.CapitalizeDecapitalizeKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00005\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u0004J\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u00062\u0006\u0010\f\u001a\u00020\rJ\b\u0010\u000e\u001a\u00020\u0006H\u0016J\n\u0010\u000f\u001a\u0004\u0018\u00010\u0006H\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u0013H\u0016J\b\u0010\u0015\u001a\u00020\u0013H\u0016R\u001d\u0010\u0005\u001a\u0004\u0018\u00010\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b¨\u0006\u0016"}, d2 = {"org/jetbrains/kotlin/fir/java/FirJavaElementFinder$buildFieldStubForConst$psiField$1", "Lcom/intellij/psi/stubs/StubBase;", "Lcom/intellij/psi/PsiField;", "Lcom/intellij/psi/impl/java/stubs/PsiFieldStub;", "Lorg/jetbrains/kotlin/load/java/structure/impl/NotEvaluatedConstAware;", "lazyInitializerText", Argument.Delimiters.none, "getLazyInitializerText", "()Ljava/lang/String;", "lazyInitializerText$delegate", "Lkotlin/Lazy;", "transformJavaFieldAndGetResultAsString", "firProperty", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "getName", "getInitializerText", "getType", "Lcom/intellij/psi/impl/cache/TypeInfo;", "isDeprecated", Argument.Delimiters.none, "isEnumConstant", "isNotYetComputed", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJavaElementFinder$buildFieldStubForConst$psiField$1 extends StubBase<PsiField> implements PsiFieldStub, NotEvaluatedConstAware {
    final /* synthetic */ FirProperty $firProperty;

    /* JADX INFO: renamed from: lazyInitializerText$delegate, reason: from kotlin metadata */
    private final Lazy lazyInitializerText;
    final /* synthetic */ FirJavaElementFinder this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirJavaElementFinder$buildFieldStubForConst$psiField$1(PsiClassStubImpl<ClsClassImpl> psiClassStubImpl, final FirProperty firProperty, FirJavaElementFinder firJavaElementFinder, JavaFieldStubElementType javaFieldStubElementType) {
        super(psiClassStubImpl, javaFieldStubElementType);
        this.$firProperty = firProperty;
        this.this$0 = firJavaElementFinder;
        this.lazyInitializerText = LazyKt.lazy(new Function0() { // from class: d95
            public final Object invoke() {
                return FirJavaElementFinder$buildFieldStubForConst$psiField$1.yd(this.b, firProperty);
            }
        });
    }

    private final String getLazyInitializerText() {
        return (String) this.lazyInitializerText.getValue();
    }

    private static final String transformJavaFieldAndGetResultAsString$asString(FirLiteralExpression firLiteralExpression) {
        Object value = firLiteralExpression.getValue();
        if (value instanceof Character) {
            return String.valueOf((int) ((Character) value).charValue());
        }
        if (!(value instanceof String)) {
            return String.valueOf(value);
        }
        return "\"" + ((String) value) + '\"';
    }

    public static String yd(FirJavaElementFinder$buildFieldStubForConst$psiField$1 firJavaElementFinder$buildFieldStubForConst$psiField$1, FirProperty firProperty) {
        return firJavaElementFinder$buildFieldStubForConst$psiField$1.transformJavaFieldAndGetResultAsString(firProperty);
    }

    public String getInitializerText() {
        return getLazyInitializerText();
    }

    public String getName() {
        String identifier = this.$firProperty.getName().getIdentifier();
        identifier.getClass();
        return identifier;
    }

    public TypeInfo getType() {
        ConeKotlinType coneType = this.$firProperty.getReturnTypeRef().getConeType();
        if (coneType == null) {
            x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.types.ConeClassLikeType");
            return null;
        }
        ConeClassLikeType coneClassLikeType = (ConeClassLikeType) coneType;
        if (ConeBuiltinTypeUtilsKt.isString(coneClassLikeType)) {
            TypeInfo typeInfoFromString = TypeInfo.fromString("java.lang.String");
            typeInfoFromString.getClass();
            return typeInfoFromString;
        }
        TypeInfo typeInfoFromString2 = TypeInfo.fromString(CapitalizeDecapitalizeKt.toLowerCaseAsciiOnly(coneClassLikeType.getLookupTag().getClassId().getRelativeClassName().asString()));
        typeInfoFromString2.getClass();
        return typeInfoFromString2;
    }

    public boolean isDeprecated() {
        return false;
    }

    public boolean isEnumConstant() {
        return false;
    }

    public boolean isNotYetComputed() {
        return true;
    }

    /* JADX WARN: Code duplicated, block: B:15:0x0031  */
    public final String transformJavaFieldAndGetResultAsString(FirProperty firProperty) {
        FirLiteralExpression firLiteralExpression;
        firProperty.getClass();
        FirEvaluatorResult firEvaluatorResultEvaluatePropertyInitializer$default = FirExpressionEvaluator.evaluatePropertyInitializer$default(FirExpressionEvaluator.INSTANCE, firProperty, this.this$0.session, null, 4, null);
        if (firEvaluatorResultEvaluatePropertyInitializer$default != null) {
            if (firEvaluatorResultEvaluatePropertyInitializer$default instanceof FirEvaluatorResult.CompileTimeException) {
            } else {
                if (firEvaluatorResultEvaluatePropertyInitializer$default instanceof FirEvaluatorResult.Evaluated) {
                    FirElement result = ((FirEvaluatorResult.Evaluated) firEvaluatorResultEvaluatePropertyInitializer$default).getResult();
                    if (!(result instanceof FirLiteralExpression)) {
                        result = null;
                    }
                    firLiteralExpression = (FirLiteralExpression) result;
                }
                if (firLiteralExpression != null) {
                    return transformJavaFieldAndGetResultAsString$asString(firLiteralExpression);
                }
            }
            firLiteralExpression = null;
            if (firLiteralExpression != null) {
                return transformJavaFieldAndGetResultAsString$asString(firLiteralExpression);
            }
        }
        return null;
    }
}
