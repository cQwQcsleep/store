package org.jetbrains.kotlin.fir.backend;

import com.intellij.psi.PsiElement;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirCodeFragment;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirField;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirReplSnippet;
import org.jetbrains.kotlin.fir.declarations.FirScript;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.ir.declarations.DeclarationSymbolOwner;
import org.jetbrains.kotlin.ir.declarations.MetadataSource;
import org.jetbrains.kotlin.mpp.DeclarationSymbolMarker;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.SpecialNames;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u00012\u00020\u0002:\t\u0015\u0016\u0017\u0018\u0019\u001a\u001b\u001c\u001dB\t\b\u0004¢\u0006\u0004\b\u0003\u0010\u0004R\u0012\u0010\u0005\u001a\u00020\u0006X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\t\u001a\u0004\u0018\u00010\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\r\u001a\u0004\u0018\u00010\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\u00128VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014\u0082\u0001\t\u001e\u001f !\"#$%&¨\u0006'"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/FirMetadataSource;", "Lorg/jetbrains/kotlin/ir/declarations/MetadataSource;", "Lorg/jetbrains/kotlin/ir/declarations/DeclarationSymbolOwner;", "<init>", "()V", "fir", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "getFir", "()Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "getName", "()Lorg/jetbrains/kotlin/name/Name;", "symbol", "Lorg/jetbrains/kotlin/mpp/DeclarationSymbolMarker;", "getSymbol", "()Lorg/jetbrains/kotlin/mpp/DeclarationSymbolMarker;", "File", "Class", "Function", "Property", "Field", "Script", "CodeFragment", "ReplSnippet", "TypeAlias", "Lorg/jetbrains/kotlin/fir/backend/FirMetadataSource$Class;", "Lorg/jetbrains/kotlin/fir/backend/FirMetadataSource$CodeFragment;", "Lorg/jetbrains/kotlin/fir/backend/FirMetadataSource$Field;", "Lorg/jetbrains/kotlin/fir/backend/FirMetadataSource$File;", "Lorg/jetbrains/kotlin/fir/backend/FirMetadataSource$Function;", "Lorg/jetbrains/kotlin/fir/backend/FirMetadataSource$Property;", "Lorg/jetbrains/kotlin/fir/backend/FirMetadataSource$ReplSnippet;", "Lorg/jetbrains/kotlin/fir/backend/FirMetadataSource$Script;", "Lorg/jetbrains/kotlin/fir/backend/FirMetadataSource$TypeAlias;", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirMetadataSource implements DeclarationSymbolOwner, MetadataSource {
    public /* synthetic */ FirMetadataSource(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public abstract FirDeclaration getFir();

    public Name getName() {
        FirDeclaration fir = getFir();
        if (fir instanceof FirConstructor) {
            return SpecialNames.INIT;
        }
        if (fir instanceof FirNamedFunction) {
            return ((FirNamedFunction) fir).getName();
        }
        if (fir instanceof FirRegularClass) {
            return ((FirRegularClass) fir).getName();
        }
        if (fir instanceof FirProperty) {
            return ((FirProperty) fir).getName();
        }
        return null;
    }

    public KtSourceElement getSource() {
        return getFir().getSource();
    }

    public DeclarationSymbolMarker getSymbol() {
        return getFir().getSymbol();
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\f\u0010\r\u001a\u0006\u0012\u0002\b\u00030\u000eH\u0016R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/FirMetadataSource$Class;", "Lorg/jetbrains/kotlin/fir/backend/FirMetadataSource;", "Lorg/jetbrains/kotlin/ir/declarations/MetadataSource$Class;", "fir", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "<init>", "(Lorg/jetbrains/kotlin/fir/declarations/FirClass;)V", "getFir", "()Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "recordLocalClassType", Argument.Delimiters.none, ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/name/FqName;", "asFirSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Class extends FirMetadataSource implements MetadataSource.Class {
        private final FirClass fir;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Class(FirClass firClass) {
            super(null);
            firClass.getClass();
            this.fir = firClass;
        }

        public FirClassSymbol<?> asFirSymbol() {
            return getFir().getSymbol();
        }

        public void recordLocalClassType(FqName type) {
            type.getClass();
            if (getFir().getIsLocal()) {
                ClassMembersKt.setLocalClassJvmType(getFir(), type);
            } else {
                dt1.a("Local class type should be recorded only for local classes, but got ", UtilsKt.render(getFir()));
            }
        }

        @Override // org.jetbrains.kotlin.fir.backend.FirMetadataSource
        public FirClass getFir() {
            return this.fir;
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/FirMetadataSource$CodeFragment;", "Lorg/jetbrains/kotlin/fir/backend/FirMetadataSource;", "Lorg/jetbrains/kotlin/ir/declarations/MetadataSource$CodeFragment;", "fir", "Lorg/jetbrains/kotlin/fir/declarations/FirCodeFragment;", "<init>", "(Lorg/jetbrains/kotlin/fir/declarations/FirCodeFragment;)V", "getFir", "()Lorg/jetbrains/kotlin/fir/declarations/FirCodeFragment;", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class CodeFragment extends FirMetadataSource implements MetadataSource.CodeFragment {
        private final FirCodeFragment fir;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public CodeFragment(FirCodeFragment firCodeFragment) {
            super(null);
            firCodeFragment.getClass();
            this.fir = firCodeFragment;
        }

        @Override // org.jetbrains.kotlin.fir.backend.FirMetadataSource
        public FirCodeFragment getFir() {
            return this.fir;
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u000bR\u0016\u0010\f\u001a\u0004\u0018\u00010\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/FirMetadataSource$Field;", "Lorg/jetbrains/kotlin/fir/backend/FirMetadataSource;", "Lorg/jetbrains/kotlin/ir/declarations/MetadataSource$Property;", "fir", "Lorg/jetbrains/kotlin/fir/declarations/FirField;", "<init>", "(Lorg/jetbrains/kotlin/fir/declarations/FirField;)V", "getFir", "()Lorg/jetbrains/kotlin/fir/declarations/FirField;", "isConst", Argument.Delimiters.none, "()Z", "psi", "Lcom/intellij/psi/PsiElement;", "getPsi", "()Lcom/intellij/psi/PsiElement;", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Field extends FirMetadataSource implements MetadataSource.Property {
        private final FirField fir;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Field(FirField firField) {
            super(null);
            firField.getClass();
            this.fir = firField;
        }

        public PsiElement getPsi() {
            return UtilsKt.getPsi(getFir());
        }

        public boolean isConst() {
            return getFir().getStatus().isConst();
        }

        @Override // org.jetbrains.kotlin.fir.backend.FirMetadataSource
        public FirField getFir() {
            return this.fir;
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/FirMetadataSource$File;", "Lorg/jetbrains/kotlin/fir/backend/FirMetadataSource;", "Lorg/jetbrains/kotlin/ir/declarations/MetadataSource$File;", "fir", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "<init>", "(Lorg/jetbrains/kotlin/fir/declarations/FirFile;)V", "getFir", "()Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class File extends FirMetadataSource implements MetadataSource.File {
        private final FirFile fir;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public File(FirFile firFile) {
            super(null);
            firFile.getClass();
            this.fir = firFile;
        }

        @Override // org.jetbrains.kotlin.fir.backend.FirMetadataSource
        public FirFile getFir() {
            return this.fir;
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/FirMetadataSource$Function;", "Lorg/jetbrains/kotlin/fir/backend/FirMetadataSource;", "Lorg/jetbrains/kotlin/ir/declarations/MetadataSource$Function;", "fir", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "<init>", "(Lorg/jetbrains/kotlin/fir/declarations/FirFunction;)V", "getFir", "()Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Function extends FirMetadataSource implements MetadataSource.Function {
        private final FirFunction fir;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Function(FirFunction firFunction) {
            super(null);
            firFunction.getClass();
            this.fir = firFunction;
        }

        @Override // org.jetbrains.kotlin.fir.backend.FirMetadataSource
        public FirFunction getFir() {
            return this.fir;
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u000bR\u0016\u0010\f\u001a\u0004\u0018\u00010\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/FirMetadataSource$Property;", "Lorg/jetbrains/kotlin/fir/backend/FirMetadataSource;", "Lorg/jetbrains/kotlin/ir/declarations/MetadataSource$Property;", "fir", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "<init>", "(Lorg/jetbrains/kotlin/fir/declarations/FirProperty;)V", "getFir", "()Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "isConst", Argument.Delimiters.none, "()Z", "psi", "Lcom/intellij/psi/PsiElement;", "getPsi", "()Lcom/intellij/psi/PsiElement;", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Property extends FirMetadataSource implements MetadataSource.Property {
        private final FirProperty fir;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Property(FirProperty firProperty) {
            super(null);
            firProperty.getClass();
            this.fir = firProperty;
        }

        public PsiElement getPsi() {
            return UtilsKt.getPsi(getFir());
        }

        public boolean isConst() {
            return getFir().getStatus().isConst();
        }

        @Override // org.jetbrains.kotlin.fir.backend.FirMetadataSource
        public FirProperty getFir() {
            return this.fir;
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/FirMetadataSource$ReplSnippet;", "Lorg/jetbrains/kotlin/fir/backend/FirMetadataSource;", "Lorg/jetbrains/kotlin/ir/declarations/MetadataSource$ReplSnippet;", "fir", "Lorg/jetbrains/kotlin/fir/declarations/FirReplSnippet;", "<init>", "(Lorg/jetbrains/kotlin/fir/declarations/FirReplSnippet;)V", "getFir", "()Lorg/jetbrains/kotlin/fir/declarations/FirReplSnippet;", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class ReplSnippet extends FirMetadataSource implements MetadataSource.ReplSnippet {
        private final FirReplSnippet fir;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ReplSnippet(FirReplSnippet firReplSnippet) {
            super(null);
            firReplSnippet.getClass();
            this.fir = firReplSnippet;
        }

        @Override // org.jetbrains.kotlin.fir.backend.FirMetadataSource
        public FirReplSnippet getFir() {
            return this.fir;
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/FirMetadataSource$Script;", "Lorg/jetbrains/kotlin/fir/backend/FirMetadataSource;", "Lorg/jetbrains/kotlin/ir/declarations/MetadataSource$Script;", "fir", "Lorg/jetbrains/kotlin/fir/declarations/FirScript;", "<init>", "(Lorg/jetbrains/kotlin/fir/declarations/FirScript;)V", "getFir", "()Lorg/jetbrains/kotlin/fir/declarations/FirScript;", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Script extends FirMetadataSource implements MetadataSource.Script {
        private final FirScript fir;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Script(FirScript firScript) {
            super(null);
            firScript.getClass();
            this.fir = firScript;
        }

        @Override // org.jetbrains.kotlin.fir.backend.FirMetadataSource
        public FirScript getFir() {
            return this.fir;
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/FirMetadataSource$TypeAlias;", "Lorg/jetbrains/kotlin/fir/backend/FirMetadataSource;", "fir", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeAlias;", "<init>", "(Lorg/jetbrains/kotlin/fir/declarations/FirTypeAlias;)V", "getFir", "()Lorg/jetbrains/kotlin/fir/declarations/FirTypeAlias;", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class TypeAlias extends FirMetadataSource {
        private final FirTypeAlias fir;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public TypeAlias(FirTypeAlias firTypeAlias) {
            super(null);
            firTypeAlias.getClass();
            this.fir = firTypeAlias;
        }

        @Override // org.jetbrains.kotlin.fir.backend.FirMetadataSource
        public FirTypeAlias getFir() {
            return this.fir;
        }
    }

    private FirMetadataSource() {
    }
}
