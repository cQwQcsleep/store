package org.jetbrains.kotlin.fir.serialization;

import com.intellij.lang.LighterASTNode;
import com.intellij.openapi.util.Ref;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.providers.FirProvider;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.types.ConeFlexibleType;
import org.jetbrains.kotlin.lexer.KtTokens;
import org.jetbrains.kotlin.library.metadata.KlibMetadataProtoBuf;
import org.jetbrains.kotlin.library.metadata.KlibMetadataSerializerProtocol;
import org.jetbrains.kotlin.metadata.ProtoBuf;
import org.jetbrains.kotlin.metadata.deserialization.BinaryVersion;
import org.jetbrains.kotlin.metadata.serialization.MutableVersionRequirementTable;
import org.jetbrains.kotlin.protobuf.GeneratedMessageLite;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000¦\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\u000e\u0010\u000fJ\b\u0010\u0018\u001a\u00020\u000bH\u0016J \u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001eH\u0016J(\u0010 \u001a\u00020\u001a2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(H\u0016J \u0010)\u001a\u00020\u001a2\u0006\u0010*\u001a\u00020+2\u0006\u0010#\u001a\u00020,2\u0006\u0010'\u001a\u00020(H\u0016J*\u0010-\u001a\u00020\u001a2\u0006\u0010.\u001a\u00020/2\u0006\u0010#\u001a\u0002002\b\u0010%\u001a\u0004\u0018\u00010&2\u0006\u0010'\u001a\u00020(H\u0016J*\u00101\u001a\u00020\u001a2\u0006\u00102\u001a\u0002032\u0006\u0010#\u001a\u0002042\b\u0010%\u001a\u0004\u0018\u00010&2\u0006\u0010'\u001a\u00020(H\u0016JZ\u00105\u001a\u00020\u001a\"\u000e\b\u0000\u00106*\b\u0012\u0004\u0012\u0002H607\"\u0014\b\u0001\u00108*\u000e\u0012\u0004\u0012\u0002H6\u0012\u0004\u0012\u0002H809*\u00020:2\u0012\u0010#\u001a\u000e\u0012\u0004\u0012\u0002H6\u0012\u0004\u0012\u0002H8092\u0012\u0010;\u001a\u000e\u0012\u0004\u0012\u0002H6\u0012\u0004\u0012\u00020=0<H\u0002J\u000e\u0010>\u001a\u0004\u0018\u00010=*\u00020:H\u0002JZ\u0010?\u001a\u00020\u001a\"\u000e\b\u0000\u00106*\b\u0012\u0004\u0012\u0002H607\"\u0014\b\u0001\u00108*\u000e\u0012\u0004\u0012\u0002H6\u0012\u0004\u0012\u0002H809*\u00020:2\u0012\u0010#\u001a\u000e\u0012\u0004\u0012\u0002H6\u0012\u0004\u0012\u0002H8092\u0012\u0010;\u001a\u000e\u0012\u0004\u0012\u0002H6\u0012\u0004\u0012\u00020@0<H\u0002J\u0017\u0010A\u001a\u0004\u0018\u00010@2\u0006\u0010B\u001a\u00020:H\u0002¢\u0006\u0002\u0010CR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\u0004\u0018\u00010\rX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006D"}, d2 = {"Lorg/jetbrains/kotlin/fir/serialization/FirKLibSerializerExtension;", "Lorg/jetbrains/kotlin/fir/serialization/FirSerializerExtensionBase;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "firProvider", "Lorg/jetbrains/kotlin/fir/resolve/providers/FirProvider;", "metadataVersion", "Lorg/jetbrains/kotlin/metadata/deserialization/BinaryVersion;", "exportKDoc", Argument.Delimiters.none, "additionalMetadataProvider", "Lorg/jetbrains/kotlin/fir/serialization/FirAdditionalMetadataProvider;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;Lorg/jetbrains/kotlin/fir/resolve/providers/FirProvider;Lorg/jetbrains/kotlin/metadata/deserialization/BinaryVersion;ZLorg/jetbrains/kotlin/fir/serialization/FirAdditionalMetadataProvider;)V", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "getScopeSession", "()Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "getMetadataVersion", "()Lorg/jetbrains/kotlin/metadata/deserialization/BinaryVersion;", "getAdditionalMetadataProvider", "()Lorg/jetbrains/kotlin/fir/serialization/FirAdditionalMetadataProvider;", "shouldUseTypeTable", "serializeFlexibleType", Argument.Delimiters.none, ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/fir/types/ConeFlexibleType;", "lowerProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Type$Builder;", "upperProto", "serializeClass", "klass", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "proto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Class$Builder;", "versionRequirementTable", "Lorg/jetbrains/kotlin/metadata/serialization/MutableVersionRequirementTable;", "childSerializer", "Lorg/jetbrains/kotlin/fir/serialization/FirElementSerializer;", "serializeConstructor", "constructor", "Lorg/jetbrains/kotlin/fir/declarations/FirConstructor;", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Constructor$Builder;", "serializeProperty", "property", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Property$Builder;", "serializeFunction", "function", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Function$Builder;", "setKDoc", "MessageType", "Lorg/jetbrains/kotlin/protobuf/GeneratedMessageLite$ExtendableMessage;", "BuilderType", "Lorg/jetbrains/kotlin/protobuf/GeneratedMessageLite$ExtendableBuilder;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "extension", "Lorg/jetbrains/kotlin/protobuf/GeneratedMessageLite$GeneratedExtension;", Argument.Delimiters.none, "findKDocString", "setFileId", Argument.Delimiters.none, "declarationFileId", "declaration", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)Ljava/lang/Integer;", "org.jetbrains.kotlin:fir-serialization"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirKLibSerializerExtension extends FirSerializerExtensionBase {
    private final FirAdditionalMetadataProvider additionalMetadataProvider;
    private final boolean exportKDoc;
    private final FirProvider firProvider;
    private final BinaryVersion metadataVersion;
    private final ScopeSession scopeSession;
    private final FirSession session;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirKLibSerializerExtension(FirSession firSession, ScopeSession scopeSession, FirProvider firProvider, BinaryVersion binaryVersion, boolean z, FirAdditionalMetadataProvider firAdditionalMetadataProvider) {
        super(KlibMetadataSerializerProtocol.INSTANCE, LanguageFeature.KlibAnnotationsInMetadata);
        firSession.getClass();
        scopeSession.getClass();
        firProvider.getClass();
        binaryVersion.getClass();
        this.session = firSession;
        this.scopeSession = scopeSession;
        this.firProvider = firProvider;
        this.metadataVersion = binaryVersion;
        this.exportKDoc = z;
        this.additionalMetadataProvider = firAdditionalMetadataProvider;
    }

    private final Integer declarationFileId(FirDeclaration declaration) {
        FirFile firClassifierContainerFileIfAny;
        FirBasedSymbol<FirDeclaration> symbol = declaration.getSymbol();
        if (symbol instanceof FirCallableSymbol) {
            firClassifierContainerFileIfAny = this.firProvider.getFirCallableContainerFile((FirCallableSymbol) symbol);
        } else {
            firClassifierContainerFileIfAny = symbol instanceof FirClassLikeSymbol ? this.firProvider.getFirClassifierContainerFileIfAny((FirClassLikeSymbol<?>) symbol) : null;
        }
        if (firClassifierContainerFileIfAny == null) {
            return null;
        }
        return Integer.valueOf(getStringTable().getStringIndex(firClassifierContainerFileIfAny.getName()));
    }

    private final String findKDocString(FirDeclaration firDeclaration) {
        KtSourceElement source = firDeclaration.getSource();
        if (source != null) {
            Ref ref = new Ref();
            source.getTreeStructure().getChildren(source.getLighterASTNode(), ref);
            Object obj = ref.get();
            obj.getClass();
            Object[] objArr = (Object[]) obj;
            int length = objArr.length;
            int i = 0;
            Object obj2 = null;
            boolean z = false;
            while (true) {
                if (i >= length) {
                    if (!z) {
                        break;
                    }
                    break;
                }
                Object obj3 = objArr[i];
                LighterASTNode lighterASTNode = (LighterASTNode) obj3;
                if (Intrinsics.areEqual(lighterASTNode != null ? lighterASTNode.getTokenType() : null, KtTokens.DOC_COMMENT)) {
                    if (!z) {
                        z = true;
                        obj2 = obj3;
                    }
                }
                i++;
                obj2 = null;
                break;
            }
            LighterASTNode lighterASTNode2 = (LighterASTNode) obj2;
            if (lighterASTNode2 != null) {
                return lighterASTNode2.toString();
            }
        }
        return null;
    }

    private final <MessageType extends GeneratedMessageLite.ExtendableMessage<MessageType>, BuilderType extends GeneratedMessageLite.ExtendableBuilder<MessageType, BuilderType>> void setFileId(FirDeclaration firDeclaration, GeneratedMessageLite.ExtendableBuilder<MessageType, BuilderType> extendableBuilder, GeneratedMessageLite.GeneratedExtension<MessageType, Integer> generatedExtension) {
        Integer numDeclarationFileId = declarationFileId(firDeclaration);
        if (numDeclarationFileId != null) {
            extendableBuilder.setExtension(generatedExtension, Integer.valueOf(numDeclarationFileId.intValue()));
        }
    }

    private final <MessageType extends GeneratedMessageLite.ExtendableMessage<MessageType>, BuilderType extends GeneratedMessageLite.ExtendableBuilder<MessageType, BuilderType>> void setKDoc(FirDeclaration firDeclaration, GeneratedMessageLite.ExtendableBuilder<MessageType, BuilderType> extendableBuilder, GeneratedMessageLite.GeneratedExtension<MessageType, String> generatedExtension) {
        String strFindKDocString;
        if (!this.exportKDoc || (strFindKDocString = findKDocString(firDeclaration)) == null) {
            return;
        }
        extendableBuilder.setExtension(generatedExtension, strFindKDocString);
    }

    @Override // org.jetbrains.kotlin.fir.serialization.FirSerializerExtension
    public FirAdditionalMetadataProvider getAdditionalMetadataProvider() {
        return this.additionalMetadataProvider;
    }

    @Override // org.jetbrains.kotlin.fir.serialization.FirSerializerExtension
    public BinaryVersion getMetadataVersion() {
        return this.metadataVersion;
    }

    @Override // org.jetbrains.kotlin.fir.ScopeSessionHolder
    public ScopeSession getScopeSession() {
        return this.scopeSession;
    }

    @Override // org.jetbrains.kotlin.fir.SessionHolder
    public FirSession getSession() {
        return this.session;
    }

    @Override // org.jetbrains.kotlin.fir.serialization.FirSerializerExtensionBase, org.jetbrains.kotlin.fir.serialization.FirSerializerExtension
    public void serializeClass(FirClass klass, ProtoBuf.Class.Builder proto, MutableVersionRequirementTable versionRequirementTable, FirElementSerializer childSerializer) {
        klass.getClass();
        proto.getClass();
        versionRequirementTable.getClass();
        childSerializer.getClass();
        GeneratedMessageLite.GeneratedExtension generatedExtension = KlibMetadataProtoBuf.classFile;
        generatedExtension.getClass();
        setFileId(klass, proto, generatedExtension);
        GeneratedMessageLite.GeneratedExtension generatedExtension2 = KlibMetadataProtoBuf.classKdoc;
        generatedExtension2.getClass();
        setKDoc(klass, proto, generatedExtension2);
        super.serializeClass(klass, proto, versionRequirementTable, childSerializer);
        ProtoBuf.TypeTable typeTableSerialize = childSerializer.getTypeTable().serialize();
        if (typeTableSerialize != null) {
            proto.mergeTypeTable(typeTableSerialize);
        }
    }

    @Override // org.jetbrains.kotlin.fir.serialization.FirSerializerExtensionBase, org.jetbrains.kotlin.fir.serialization.FirSerializerExtension
    public void serializeConstructor(FirConstructor constructor, ProtoBuf.Constructor.Builder proto, FirElementSerializer childSerializer) {
        constructor.getClass();
        proto.getClass();
        childSerializer.getClass();
        GeneratedMessageLite.GeneratedExtension generatedExtension = KlibMetadataProtoBuf.constructorKdoc;
        generatedExtension.getClass();
        setKDoc(constructor, proto, generatedExtension);
        super.serializeConstructor(constructor, proto, childSerializer);
    }

    @Override // org.jetbrains.kotlin.fir.serialization.FirSerializerExtension
    public void serializeFlexibleType(ConeFlexibleType type, ProtoBuf.Type.Builder lowerProto, ProtoBuf.Type.Builder upperProto) {
        type.getClass();
        lowerProto.getClass();
        upperProto.getClass();
        lowerProto.setFlexibleTypeCapabilitiesId(getStringTable().getStringIndex("kotlin.DynamicType"));
    }

    @Override // org.jetbrains.kotlin.fir.serialization.FirSerializerExtensionBase, org.jetbrains.kotlin.fir.serialization.FirSerializerExtension
    public void serializeFunction(FirFunction function, ProtoBuf.Function.Builder proto, MutableVersionRequirementTable versionRequirementTable, FirElementSerializer childSerializer) {
        function.getClass();
        proto.getClass();
        childSerializer.getClass();
        GeneratedMessageLite.GeneratedExtension generatedExtension = KlibMetadataProtoBuf.functionFile;
        generatedExtension.getClass();
        setFileId(function, proto, generatedExtension);
        GeneratedMessageLite.GeneratedExtension generatedExtension2 = KlibMetadataProtoBuf.functionKdoc;
        generatedExtension2.getClass();
        setKDoc(function, proto, generatedExtension2);
        super.serializeFunction(function, proto, versionRequirementTable, childSerializer);
    }

    @Override // org.jetbrains.kotlin.fir.serialization.FirSerializerExtensionBase, org.jetbrains.kotlin.fir.serialization.FirSerializerExtension
    public void serializeProperty(FirProperty property, ProtoBuf.Property.Builder proto, MutableVersionRequirementTable versionRequirementTable, FirElementSerializer childSerializer) {
        property.getClass();
        proto.getClass();
        childSerializer.getClass();
        GeneratedMessageLite.GeneratedExtension generatedExtension = KlibMetadataProtoBuf.propertyFile;
        generatedExtension.getClass();
        setFileId(property, proto, generatedExtension);
        GeneratedMessageLite.GeneratedExtension generatedExtension2 = KlibMetadataProtoBuf.propertyKdoc;
        generatedExtension2.getClass();
        setKDoc(property, proto, generatedExtension2);
        super.serializeProperty(property, proto, versionRequirementTable, childSerializer);
    }

    @Override // org.jetbrains.kotlin.fir.serialization.FirSerializerExtension
    public boolean shouldUseTypeTable() {
        return true;
    }
}
