package org.jetbrains.kotlin.fir.deserialization;

import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationAttributes;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationDataRegistry;
import org.jetbrains.kotlin.fir.declarations.builder.FirDeclarationBuilder;
import org.jetbrains.kotlin.util.NullableArrayMapAccessor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0014\u0010\u0017\u001a\u00020\u0018*\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001\"!\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00028FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0003\u0010\u0004\"!\u0010\u0007\u001a\u0004\u0018\u00010\b*\u00020\t8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000b\"\u0015\u0010\u000e\u001a\u00020\b*\u00020\t8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u000b\"3\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00112\b\u0010\u0010\u001a\u0004\u0018\u00010\u00018B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0003\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006\u001b"}, d2 = {"kdocText", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "getKdocText", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)Ljava/lang/String;", "kdocText$delegate", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationDataRegistry$DeclarationDataAccessor;", "kdocDeserializer", "Lorg/jetbrains/kotlin/fir/deserialization/FirKDocDeserializer;", "Lorg/jetbrains/kotlin/fir/FirSession;", "getKdocDeserializer", "(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/deserialization/FirKDocDeserializer;", "kdocDeserializer$delegate", "Lorg/jetbrains/kotlin/util/NullableArrayMapAccessor;", "effectiveKdocDeserializer", "getEffectiveKdocDeserializer", "<set-?>", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationAttributes;", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationAttributes;)Ljava/lang/String;", "setKdocText", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationAttributes;Ljava/lang/String;)V", "kdocText$delegate$1", "Lkotlin/properties/ReadWriteProperty;", "applyKDoc", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/builder/FirDeclarationBuilder;", "text", "org.jetbrains.kotlin:fir-deserialization"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirKDocDeserializerKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new PropertyReference1Impl<>(FirKDocDeserializerKt.class, "kdocText", "getKdocText(Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)Ljava/lang/String;", 1), new PropertyReference1Impl<>(FirKDocDeserializerKt.class, "kdocDeserializer", "getKdocDeserializer(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/deserialization/FirKDocDeserializer;", 1), new MutablePropertyReference1Impl<>(FirKDocDeserializerKt.class, "kdocText", "getKdocText(Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationAttributes;)Ljava/lang/String;", 1)};
    private static final NullableArrayMapAccessor kdocDeserializer$delegate;
    private static final FirDeclarationDataRegistry.DeclarationDataAccessor kdocText$delegate;
    private static final ReadWriteProperty kdocText$delegate$1;

    static {
        FirDeclarationDataRegistry firDeclarationDataRegistry = FirDeclarationDataRegistry.INSTANCE;
        KDocTextKey kDocTextKey = KDocTextKey.INSTANCE;
        kdocText$delegate = firDeclarationDataRegistry.data(kDocTextKey);
        kdocDeserializer$delegate = FirSession.INSTANCE.generateNullableAccessor(Reflection.getOrCreateKotlinClass(FirKDocDeserializer.class));
        kdocText$delegate$1 = firDeclarationDataRegistry.attributesAccessor(kDocTextKey);
    }

    public static final void applyKDoc(FirDeclarationBuilder firDeclarationBuilder, String str) {
        firDeclarationBuilder.getClass();
        if (str != null) {
            setKdocText(firDeclarationBuilder.getAttributes(), str);
        }
    }

    public static final FirKDocDeserializer getEffectiveKdocDeserializer(FirSession firSession) {
        firSession.getClass();
        FirKDocDeserializer kdocDeserializer = getKdocDeserializer(firSession);
        return kdocDeserializer == null ? FirKDocDeserializer.Empty.INSTANCE : kdocDeserializer;
    }

    public static final FirKDocDeserializer getKdocDeserializer(FirSession firSession) {
        firSession.getClass();
        return (FirKDocDeserializer) kdocDeserializer$delegate.getValue(firSession, $$delegatedProperties[1]);
    }

    public static final String getKdocText(FirDeclaration firDeclaration) {
        firDeclaration.getClass();
        return (String) kdocText$delegate.getValue(firDeclaration, $$delegatedProperties[0]);
    }

    private static final void setKdocText(FirDeclarationAttributes firDeclarationAttributes, String str) {
        kdocText$delegate$1.setValue(firDeclarationAttributes, $$delegatedProperties[2], str);
    }
}
