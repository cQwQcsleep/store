package org.jetbrains.kotlin.fir.extensions;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirBackingField;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.FirEnumEntry;
import org.jetbrains.kotlin.fir.declarations.FirField;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u0000 +2\u00020\u0001:\u0002+,B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H&J\u0018\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0010\u001a\u00020\u0011H\u0014J.\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u00162\f\u0010\u0017\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u000fH\u0016J.\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u001b2\f\u0010\u0017\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u000fH\u0016J.\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u001c\u001a\u00020\u001d2\f\u0010\u0017\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u000fH\u0016J.\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u001e\u001a\u00020\u001f2\f\u0010\u0017\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u000fH\u0016J8\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010 \u001a\u00020!2\f\u0010\u0017\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00182\b\u0010\"\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0019\u001a\u00020\u000fH\u0016J.\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010#\u001a\u00020$2\f\u0010\u0017\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u000fH\u0016J.\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010%\u001a\u00020&2\f\u0010\u0017\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u000fH\u0016J.\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010'\u001a\u00020(2\f\u0010\u0017\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u000fH\u0016J.\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010)\u001a\u00020*2\f\u0010\u0017\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u000fH\u0016R\u0011\u0010\u0006\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0019\u0010\n\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006-"}, d2 = {"Lorg/jetbrains/kotlin/fir/extensions/FirStatusTransformerExtension;", "Lorg/jetbrains/kotlin/fir/extensions/FirExtension;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;)V", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/fir/extensions/FirExtensionPointName;", "getName", "()Lorg/jetbrains/kotlin/fir/extensions/FirExtensionPointName;", "extensionType", "Lkotlin/reflect/KClass;", "getExtensionType", "()Lkotlin/reflect/KClass;", "needTransformStatus", Argument.Delimiters.none, "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "transformStatus", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationStatus;", "status", "property", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "containingClass", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "isLocal", "function", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "regularClass", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "typeAlias", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeAlias;", "propertyAccessor", "Lorg/jetbrains/kotlin/fir/declarations/FirPropertyAccessor;", "containingProperty", "constructor", "Lorg/jetbrains/kotlin/fir/declarations/FirConstructor;", "field", "Lorg/jetbrains/kotlin/fir/declarations/FirField;", "backingField", "Lorg/jetbrains/kotlin/fir/declarations/FirBackingField;", "enumEntry", "Lorg/jetbrains/kotlin/fir/declarations/FirEnumEntry;", "Companion", "Factory", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirStatusTransformerExtension extends FirExtension {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final FirExtensionPointName NAME = new FirExtensionPointName("StatusTransformer");
    private final KClass<? extends FirExtension> extensionType;

    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bæ\u0080\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0003À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/extensions/FirStatusTransformerExtension$Factory;", "Lorg/jetbrains/kotlin/fir/extensions/FirExtension$Factory;", "Lorg/jetbrains/kotlin/fir/extensions/FirStatusTransformerExtension;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public interface Factory extends FirExtension.Factory<FirStatusTransformerExtension> {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirStatusTransformerExtension(FirSession firSession) {
        super(firSession);
        firSession.getClass();
        this.extensionType = Reflection.getOrCreateKotlinClass(FirStatusTransformerExtension.class);
    }

    @Override // org.jetbrains.kotlin.fir.extensions.FirExtension
    public final KClass<? extends FirExtension> getExtensionType() {
        return this.extensionType;
    }

    @Override // org.jetbrains.kotlin.fir.extensions.FirExtension
    public final FirExtensionPointName getName() {
        return NAME;
    }

    public abstract boolean needTransformStatus(FirDeclaration declaration);

    public FirDeclarationStatus transformStatus(FirDeclarationStatus status, FirProperty property, FirClassLikeSymbol<?> containingClass, boolean isLocal) {
        status.getClass();
        property.getClass();
        return transformStatus(status, property);
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/extensions/FirStatusTransformerExtension$Companion;", Argument.Delimiters.none, "<init>", "()V", "NAME", "Lorg/jetbrains/kotlin/fir/extensions/FirExtensionPointName;", "getNAME", "()Lorg/jetbrains/kotlin/fir/extensions/FirExtensionPointName;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final FirExtensionPointName getNAME() {
            return FirStatusTransformerExtension.NAME;
        }

        private Companion() {
        }
    }

    public FirDeclarationStatus transformStatus(FirDeclarationStatus status, FirDeclaration declaration) {
        status.getClass();
        declaration.getClass();
        return status;
    }

    public FirDeclarationStatus transformStatus(FirDeclarationStatus status, FirNamedFunction function, FirClassLikeSymbol<?> containingClass, boolean isLocal) {
        status.getClass();
        function.getClass();
        return transformStatus(status, function);
    }

    public FirDeclarationStatus transformStatus(FirDeclarationStatus status, FirRegularClass regularClass, FirClassLikeSymbol<?> containingClass, boolean isLocal) {
        status.getClass();
        regularClass.getClass();
        return transformStatus(status, regularClass);
    }

    public FirDeclarationStatus transformStatus(FirDeclarationStatus status, FirTypeAlias typeAlias, FirClassLikeSymbol<?> containingClass, boolean isLocal) {
        status.getClass();
        typeAlias.getClass();
        return transformStatus(status, typeAlias);
    }

    public FirDeclarationStatus transformStatus(FirDeclarationStatus status, FirPropertyAccessor propertyAccessor, FirClassLikeSymbol<?> containingClass, FirProperty containingProperty, boolean isLocal) {
        status.getClass();
        propertyAccessor.getClass();
        return transformStatus(status, propertyAccessor);
    }

    public FirDeclarationStatus transformStatus(FirDeclarationStatus status, FirConstructor constructor, FirClassLikeSymbol<?> containingClass, boolean isLocal) {
        status.getClass();
        constructor.getClass();
        return transformStatus(status, constructor);
    }

    public FirDeclarationStatus transformStatus(FirDeclarationStatus status, FirField field, FirClassLikeSymbol<?> containingClass, boolean isLocal) {
        status.getClass();
        field.getClass();
        return transformStatus(status, field);
    }

    public FirDeclarationStatus transformStatus(FirDeclarationStatus status, FirBackingField backingField, FirClassLikeSymbol<?> containingClass, boolean isLocal) {
        status.getClass();
        backingField.getClass();
        return transformStatus(status, backingField);
    }

    public FirDeclarationStatus transformStatus(FirDeclarationStatus status, FirEnumEntry enumEntry, FirClassLikeSymbol<?> containingClass, boolean isLocal) {
        status.getClass();
        enumEntry.getClass();
        return transformStatus(status, enumEntry);
    }
}
