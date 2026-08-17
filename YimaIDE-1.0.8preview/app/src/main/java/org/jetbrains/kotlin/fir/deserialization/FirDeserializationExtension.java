package org.jetbrains.kotlin.fir.deserialization;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirComposableSessionComponent;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.SessionConfiguration;
import org.jetbrains.kotlin.fir.declarations.builder.FirRegularClassBuilder;
import org.jetbrains.kotlin.metadata.ProtoBuf;
import org.jetbrains.kotlin.metadata.deserialization.NameResolver;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.serialization.SerializerExtensionProtocol;
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedContainerSource;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001&B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J$\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\rH\u0016J\u0014\u0010\u000e\u001a\u00020\u000f*\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u001a\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0017\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0016¢\u0006\u0002\u0010\u001dJ\u0012\u0010\u001e\u001a\u00020\u001a2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016J\u001a\u0010!\u001a\u00020\"2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00000$H\u0007b\u0002\b%R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\u001f\u001a\u00020\u001a8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010 ¨\u0006'"}, d2 = {"Lorg/jetbrains/kotlin/fir/deserialization/FirDeserializationExtension;", "Lorg/jetbrains/kotlin/fir/FirComposableSessionComponent;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;)V", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "createConstDeserializer", "Lorg/jetbrains/kotlin/fir/deserialization/FirConstDeserializer;", "containerSource", "Lorg/jetbrains/kotlin/serialization/deserialization/descriptors/DeserializedContainerSource;", "serializerExtensionProtocol", "Lorg/jetbrains/kotlin/serialization/SerializerExtensionProtocol;", "configureDeserializedClass", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/builder/FirRegularClassBuilder;", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "loadModuleName", Argument.Delimiters.none, "classProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Class;", "nameResolver", "Lorg/jetbrains/kotlin/metadata/deserialization/NameResolver;", "loadHasBackingFieldFlag", Argument.Delimiters.none, "propertyProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Property;", "(Lorg/jetbrains/kotlin/metadata/ProtoBuf$Property;)Ljava/lang/Boolean;", "isMaybeMultiFieldValueClass", "isLoadingOfAnnotationsOnAnnotationPropertiesEnabled", "()Z", "createComposed", "Lorg/jetbrains/kotlin/fir/deserialization/FirDeserializationExtension$Composed;", "components", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/SessionConfiguration;", "Composed", "org.jetbrains.kotlin:fir-deserialization"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirDeserializationExtension implements FirComposableSessionComponent<FirDeserializationExtension> {
    private final FirSession session;

    @Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00010\u0002B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bJ$\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0014\u0010\u0011\u001a\u00020\u0012*\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u001a\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u0017\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0016¢\u0006\u0002\u0010 J\u0012\u0010!\u001a\u00020\u001d2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016R\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\"\u001a\u00020\u001d8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010#¨\u0006$"}, d2 = {"Lorg/jetbrains/kotlin/fir/deserialization/FirDeserializationExtension$Composed;", "Lorg/jetbrains/kotlin/fir/deserialization/FirDeserializationExtension;", "Lorg/jetbrains/kotlin/fir/FirComposableSessionComponent$Composed;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "components", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Ljava/util/List;)V", "getComponents", "()Ljava/util/List;", "createConstDeserializer", "Lorg/jetbrains/kotlin/fir/deserialization/FirConstDeserializer;", "containerSource", "Lorg/jetbrains/kotlin/serialization/deserialization/descriptors/DeserializedContainerSource;", "serializerExtensionProtocol", "Lorg/jetbrains/kotlin/serialization/SerializerExtensionProtocol;", "configureDeserializedClass", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/builder/FirRegularClassBuilder;", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "loadModuleName", Argument.Delimiters.none, "classProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Class;", "nameResolver", "Lorg/jetbrains/kotlin/metadata/deserialization/NameResolver;", "loadHasBackingFieldFlag", Argument.Delimiters.none, "propertyProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Property;", "(Lorg/jetbrains/kotlin/metadata/ProtoBuf$Property;)Ljava/lang/Boolean;", "isMaybeMultiFieldValueClass", "isLoadingOfAnnotationsOnAnnotationPropertiesEnabled", "()Z", "org.jetbrains.kotlin:fir-deserialization"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Composed extends FirDeserializationExtension implements FirComposableSessionComponent.Composed<FirDeserializationExtension> {
        private final List<FirDeserializationExtension> components;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public Composed(FirSession firSession, List<? extends FirDeserializationExtension> list) {
            super(firSession);
            firSession.getClass();
            list.getClass();
            this.components = list;
        }

        @Override // org.jetbrains.kotlin.fir.deserialization.FirDeserializationExtension
        public void configureDeserializedClass(FirRegularClassBuilder firRegularClassBuilder, ClassId classId) {
            firRegularClassBuilder.getClass();
            classId.getClass();
            Iterator<T> it = getComponents().iterator();
            while (it.hasNext()) {
                ((FirDeserializationExtension) it.next()).configureDeserializedClass(firRegularClassBuilder, classId);
            }
        }

        @Override // org.jetbrains.kotlin.fir.deserialization.FirDeserializationExtension
        public FirConstDeserializer createConstDeserializer(DeserializedContainerSource containerSource, FirSession session, SerializerExtensionProtocol serializerExtensionProtocol) {
            session.getClass();
            serializerExtensionProtocol.getClass();
            Iterator<T> it = getComponents().iterator();
            while (it.hasNext()) {
                FirConstDeserializer firConstDeserializerCreateConstDeserializer = ((FirDeserializationExtension) it.next()).createConstDeserializer(containerSource, session, serializerExtensionProtocol);
                if (firConstDeserializerCreateConstDeserializer != null) {
                    return firConstDeserializerCreateConstDeserializer;
                }
            }
            return null;
        }

        @Override // org.jetbrains.kotlin.fir.FirComposableSessionComponent
        public List<FirDeserializationExtension> getComponents() {
            return this.components;
        }

        @Override // org.jetbrains.kotlin.fir.deserialization.FirDeserializationExtension
        public boolean isLoadingOfAnnotationsOnAnnotationPropertiesEnabled() {
            List<FirDeserializationExtension> components = getComponents();
            if ((components instanceof Collection) && components.isEmpty()) {
                return true;
            }
            Iterator<T> it = components.iterator();
            while (it.hasNext()) {
                if (!((FirDeserializationExtension) it.next()).isLoadingOfAnnotationsOnAnnotationPropertiesEnabled()) {
                    return false;
                }
            }
            return true;
        }

        @Override // org.jetbrains.kotlin.fir.deserialization.FirDeserializationExtension
        public boolean isMaybeMultiFieldValueClass(DeserializedContainerSource containerSource) {
            List<FirDeserializationExtension> components = getComponents();
            if ((components instanceof Collection) && components.isEmpty()) {
                return false;
            }
            Iterator<T> it = components.iterator();
            while (it.hasNext()) {
                if (((FirDeserializationExtension) it.next()).isMaybeMultiFieldValueClass(containerSource)) {
                    return true;
                }
            }
            return false;
        }

        @Override // org.jetbrains.kotlin.fir.deserialization.FirDeserializationExtension
        public Boolean loadHasBackingFieldFlag(ProtoBuf.Property propertyProto) {
            propertyProto.getClass();
            Iterator<T> it = getComponents().iterator();
            while (it.hasNext()) {
                Boolean boolLoadHasBackingFieldFlag = ((FirDeserializationExtension) it.next()).loadHasBackingFieldFlag(propertyProto);
                if (boolLoadHasBackingFieldFlag != null) {
                    return boolLoadHasBackingFieldFlag;
                }
            }
            return null;
        }

        @Override // org.jetbrains.kotlin.fir.deserialization.FirDeserializationExtension
        public String loadModuleName(ProtoBuf.Class classProto, NameResolver nameResolver) {
            classProto.getClass();
            nameResolver.getClass();
            Iterator<T> it = getComponents().iterator();
            while (it.hasNext()) {
                String strLoadModuleName = ((FirDeserializationExtension) it.next()).loadModuleName(classProto, nameResolver);
                if (strLoadModuleName != null) {
                    return strLoadModuleName;
                }
            }
            return null;
        }
    }

    public FirDeserializationExtension(FirSession firSession) {
        firSession.getClass();
        this.session = firSession;
    }

    public void configureDeserializedClass(FirRegularClassBuilder firRegularClassBuilder, ClassId classId) {
        firRegularClassBuilder.getClass();
        classId.getClass();
    }

    @Override // org.jetbrains.kotlin.fir.FirComposableSessionComponent
    @SessionConfiguration
    public final Composed createComposed(List<? extends FirDeserializationExtension> components) {
        components.getClass();
        return new Composed(this.session, components);
    }

    public FirConstDeserializer createConstDeserializer(DeserializedContainerSource containerSource, FirSession session, SerializerExtensionProtocol serializerExtensionProtocol) {
        session.getClass();
        serializerExtensionProtocol.getClass();
        return null;
    }

    public final FirSession getSession() {
        return this.session;
    }

    public boolean isLoadingOfAnnotationsOnAnnotationPropertiesEnabled() {
        return true;
    }

    public boolean isMaybeMultiFieldValueClass(DeserializedContainerSource containerSource) {
        return false;
    }

    public Boolean loadHasBackingFieldFlag(ProtoBuf.Property propertyProto) {
        propertyProto.getClass();
        return null;
    }

    public String loadModuleName(ProtoBuf.Class classProto, NameResolver nameResolver) {
        classProto.getClass();
        nameResolver.getClass();
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.FirComposableSessionComponent
    @SessionConfiguration
    public /* bridge */ /* synthetic */ FirComposableSessionComponent.Composed createComposed(List list) {
        return createComposed((List<? extends FirDeserializationExtension>) list);
    }
}
