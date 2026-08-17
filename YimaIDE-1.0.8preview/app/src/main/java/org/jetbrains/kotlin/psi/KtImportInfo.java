package org.jetbrains.kotlin.psi;

import kotlin.Metadata;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001:\u0001\u0016J\n\u0010\u0015\u001a\u0004\u0018\u00010\u000eH\u0002R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0004R\u0014\u0010\u0005\u001a\u0004\u0018\u00010\u0006X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u0004\u0018\u00010\nX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u0004\u0018\u00010\u000eX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0011\u001a\u0004\u0018\u00010\u00128VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0017À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/psi/KtImportInfo;", "", "isAllUnder", "", "()Z", "importContent", "Lorg/jetbrains/kotlin/psi/KtImportInfo$ImportContent;", "getImportContent", "()Lorg/jetbrains/kotlin/psi/KtImportInfo$ImportContent;", "importedFqName", "Lorg/jetbrains/kotlin/name/FqName;", "getImportedFqName", "()Lorg/jetbrains/kotlin/name/FqName;", "aliasName", "", "getAliasName", "()Ljava/lang/String;", "importedName", "Lorg/jetbrains/kotlin/name/Name;", "getImportedName", "()Lorg/jetbrains/kotlin/name/Name;", "computeNameAsString", "ImportContent", "org.jetbrains.kotlin:psi-api"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public interface KtImportInfo {
    private default String computeNameAsString() {
        Name nameShortName;
        if (isAllUnder()) {
            return null;
        }
        String aliasName = getAliasName();
        if (aliasName != null) {
            return aliasName;
        }
        ImportContent.ExpressionBased expressionBasedMo146getImportContent = mo146getImportContent();
        if (expressionBasedMo146getImportContent instanceof ImportContent.ExpressionBased) {
            KtSimpleNameExpression lastReference = KtPsiUtil.getLastReference(expressionBasedMo146getImportContent.getExpression());
            if (lastReference != null) {
                return lastReference.getReferencedName();
            }
            return null;
        }
        if (!(expressionBasedMo146getImportContent instanceof ImportContent.FqNameBased)) {
            if (expressionBasedMo146getImportContent == null) {
                return null;
            }
            bu8.a();
            return null;
        }
        FqName fqName = ((ImportContent.FqNameBased) expressionBasedMo146getImportContent).getFqName();
        if (fqName.isRoot()) {
            fqName = null;
        }
        if (fqName == null || (nameShortName = fqName.shortName()) == null) {
            return null;
        }
        return nameShortName.asString();
    }

    String getAliasName();

    /* JADX INFO: renamed from: getImportContent */
    ImportContent mo146getImportContent();

    FqName getImportedFqName();

    default Name getImportedName() {
        String strComputeNameAsString = computeNameAsString();
        if (strComputeNameAsString != null) {
            if (strComputeNameAsString.length() <= 0) {
                strComputeNameAsString = null;
            }
            if (strComputeNameAsString != null) {
                return Name.identifier(strComputeNameAsString);
            }
        }
        return null;
    }

    boolean isAllUnder();
}
