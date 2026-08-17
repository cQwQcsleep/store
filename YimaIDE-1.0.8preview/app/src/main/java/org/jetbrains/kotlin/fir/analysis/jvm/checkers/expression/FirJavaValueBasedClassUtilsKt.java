package org.jetbrains.kotlin.fir.analysis.jvm.checkers.expression;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeFlexibleType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.name.ClassId;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u001d\u0010\u0002\u001a\u00020\u0003*\u00020\u0006H\u0000R\u00020\u0004j\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u0010\u0007\u001a\u001d\u0010\b\u001a\u00020\u0003*\u00020\u0006H\u0000R\u00020\u0004j\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u0010\u0007\u001a\u001d\u0010\t\u001a\u00020\u0003*\u00020\u0006H\u0000R\u00020\u0004j\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u0010\u0007\u001a\f\u0010\n\u001a\u00020\u0003*\u00020\u0006H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"jdkInternalValueBasedAnnotationClassId", "Lorg/jetbrains/kotlin/name/ClassId;", "isJavaValueBasedClass", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/SessionHolder;", "sessionHolder", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "(Lorg/jetbrains/kotlin/fir/SessionHolder;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)Z", "isJavaValueBasedClassAndWarningsEnabled", "isValueTypeAndWarningsEnabled", "isFlexiblePrimitive", "org.jetbrains.kotlin:checkers.jvm"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJavaValueBasedClassUtilsKt {
    private static final ClassId jdkInternalValueBasedAnnotationClassId = ClassId.Companion.fromString$default(ClassId.Companion, "jdk/internal/ValueBased", false, 2, (Object) null);

    public static final boolean isFlexiblePrimitive(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        if (!(coneKotlinType instanceof ConeFlexibleType)) {
            return false;
        }
        ConeFlexibleType coneFlexibleType = (ConeFlexibleType) coneKotlinType;
        return ConeBuiltinTypeUtilsKt.isPrimitiveOrNullablePrimitive(coneFlexibleType.getLowerBound()) && ConeBuiltinTypeUtilsKt.isPrimitiveOrNullablePrimitive(coneFlexibleType.getUpperBound());
    }

    public static final boolean isJavaValueBasedClass(SessionHolder sessionHolder, ConeKotlinType coneKotlinType) {
        sessionHolder.getClass();
        coneKotlinType.getClass();
        FirClassSymbol<?> classSymbol = ToSymbolUtilsKt.toClassSymbol(sessionHolder, coneKotlinType);
        if (classSymbol == null) {
            return false;
        }
        return FirAnnotationUtilsKt.hasAnnotation(classSymbol, jdkInternalValueBasedAnnotationClassId, sessionHolder.getSession());
    }

    public static final boolean isJavaValueBasedClassAndWarningsEnabled(SessionHolder sessionHolder, ConeKotlinType coneKotlinType) {
        sessionHolder.getClass();
        coneKotlinType.getClass();
        return LanguageVersionUtilsKt.enableWarningsForValueBasedJavaClasses(sessionHolder) && isJavaValueBasedClass(sessionHolder, coneKotlinType);
    }

    public static final boolean isValueTypeAndWarningsEnabled(SessionHolder sessionHolder, ConeKotlinType coneKotlinType) {
        sessionHolder.getClass();
        coneKotlinType.getClass();
        if (LanguageVersionUtilsKt.enableWarningsForIdentitySensitiveOperationsOnValueClassesAndPrimitives(sessionHolder) && (ConeBuiltinTypeUtilsKt.isPrimitiveOrNullablePrimitive(coneKotlinType) || FirHelpersKt.isValueClass(coneKotlinType, sessionHolder.getSession()) || isFlexiblePrimitive(coneKotlinType))) {
            return true;
        }
        return isJavaValueBasedClassAndWarningsEnabled(sessionHolder, coneKotlinType);
    }
}
