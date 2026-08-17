package com.intellij.util.indexing;

import androidx.collection.ScatterMapKt;
import androidx.compose.compiler.plugins.kotlin.lower.ComposableFunctionBodyTransformerKt;
import com.intellij.lang.FileASTNode;
import com.intellij.lang.Language;
import com.intellij.lang.LighterAST;
import com.intellij.lang.TreeBackedLighterAST;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.fileEditor.impl.LoadTextUtil;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.fileTypes.FileTypeRegistry;
import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.util.NotNullComputable;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.LanguageSubstitutors;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import com.intellij.util.indexing.FileContentImpl;
import java.io.IOException;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class FileContentImpl extends IndexedFileImpl implements PsiDependentFileContent {
    private static final Key<PsiFile> CACHED_PSI = Key.create("cached psi from content");
    private static final Key<LighterAST> LIGHTER_AST_NODE_KEY = Key.create("lighter.ast.node");
    private byte[] myCachedContentBytes;
    private Charset myCharset;
    private CharSequence myContentAsText;
    private final NotNullComputable<byte[]> myContentComputable;
    private byte[] myIndexedFileHash;
    private boolean myLighterASTShouldBeThreadSafe;
    private final boolean myTransientContent;

    /* JADX WARN: Code duplicated, block: B:11:0x001f  */
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str;
        int i2;
        if (i != 3 && i != 10 && i != 17 && i != 20 && i != 31 && i != 33) {
            switch (i) {
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                    str = "@NotNull method %s.%s must not return null";
                    break;
                default:
                    str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                    break;
            }
        } else {
            str = "@NotNull method %s.%s must not return null";
        }
        if (i != 3 && i != 10 && i != 17 && i != 20 && i != 31 && i != 33) {
            switch (i) {
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                    i2 = 2;
                    break;
                default:
                    i2 = 3;
                    break;
            }
        } else {
            i2 = 2;
        }
        Object[] objArr = new Object[i2];
        switch (i) {
            case 1:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[0] = "fileType";
                break;
            case 2:
            case 14:
            case 16:
                objArr[0] = "contentComputable";
                break;
            case 3:
            case 10:
            case 17:
            case 20:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case ComposableFunctionBodyTransformerKt.BITS_PER_INT /* 31 */:
            case 33:
                objArr[0] = "com/intellij/util/indexing/FileContentImpl";
                break;
            case 4:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[0] = "text";
                break;
            case 5:
                objArr[0] = "project";
                break;
            case 8:
            case 11:
            case 13:
            case 15:
            case 18:
            case 19:
            case 21:
            case 30:
            default:
                objArr[0] = "file";
                break;
            case 9:
                objArr[0] = "fileName";
                break;
            case 12:
                objArr[0] = "content";
                break;
            case 22:
                objArr[0] = "contentAsText";
                break;
            case 29:
                objArr[0] = "fileContentHash";
                break;
            case 32:
                objArr[0] = "indexedFile";
                break;
        }
        if (i == 3) {
            objArr[1] = "getLighterAST";
        } else if (i == 10) {
            objArr[1] = "createFileFromText";
        } else if (i == 17) {
            objArr[1] = "createByContent";
        } else if (i == 20) {
            objArr[1] = "createByFile";
        } else if (i == 31) {
            objArr[1] = "checkPsiProjectConsistency";
        } else if (i != 33) {
            switch (i) {
                case 23:
                    objArr[1] = "createByText";
                    break;
                case 24:
                    objArr[1] = "getCharset";
                    break;
                case 25:
                    objArr[1] = "getContent";
                    break;
                case 26:
                case 27:
                    objArr[1] = "getContentAsText";
                    break;
                case 28:
                    objArr[1] = "computeOriginalContent";
                    break;
                default:
                    objArr[1] = "com/intellij/util/indexing/FileContentImpl";
                    break;
            }
        } else {
            objArr[1] = "getFileTypeWithoutSubstitution";
        }
        switch (i) {
            case 3:
            case 10:
            case 17:
            case 20:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case ComposableFunctionBodyTransformerKt.BITS_PER_INT /* 31 */:
            case 33:
                break;
            case 4:
            case 5:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
            case 9:
                objArr[2] = "createFileFromText";
                break;
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
                objArr[2] = "createByContent";
                break;
            case 18:
            case 19:
                objArr[2] = "createByFile";
                break;
            case 21:
            case 22:
                objArr[2] = "createByText";
                break;
            case 29:
                objArr[2] = "setIndexedFileHash";
                break;
            case 30:
                objArr[2] = "checkPsiProjectConsistency";
                break;
            case 32:
                objArr[2] = "getFileTypeWithoutSubstitution";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i != 3 && i != 10 && i != 17 && i != 20 && i != 31 && i != 33) {
            switch (i) {
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                    break;
                default:
                    throw new IllegalArgumentException(str2);
            }
        }
        throw new IllegalStateException(str2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    private FileContentImpl(VirtualFile virtualFile, FileType fileType, CharSequence charSequence, NotNullComputable<byte[]> notNullComputable, boolean z) {
        super(virtualFile, fileType, null);
        if (virtualFile == null) {
            $$$reportNull$$$0(0);
        }
        if (fileType == null) {
            $$$reportNull$$$0(1);
        }
        if (notNullComputable == null) {
            $$$reportNull$$$0(2);
        }
        this.myContentAsText = charSequence;
        this.myContentComputable = notNullComputable;
        this.myTransientContent = z;
    }

    private PsiFile checkPsiProjectConsistency(PsiFile psiFile) {
        if (psiFile == null) {
            $$$reportNull$$$0(30);
        }
        if (!psiFile.getProject().equals(getProject())) {
            Logger.getInstance(FileContentImpl.class).error("psi file's project is not equal to file content's project");
        }
        return psiFile;
    }

    private byte[] computeOriginalContent() {
        byte[] bArr = (byte[]) this.myContentComputable.compute();
        if (bArr == null) {
            $$$reportNull$$$0(28);
        }
        return bArr;
    }

    public static FileContent createByContent(VirtualFile virtualFile, final byte[] bArr) {
        if (virtualFile == null) {
            $$$reportNull$$$0(11);
        }
        if (bArr == null) {
            $$$reportNull$$$0(12);
        }
        return new FileContentImpl(virtualFile, FileTypeRegistry.getInstance().getFileTypeByFile(virtualFile, bArr), null, new NotNullComputable() { // from class: ap4
            public final Object compute() {
                return FileContentImpl.yd(bArr);
            }
        }, false);
    }

    public static FileContent createByFile(VirtualFile virtualFile, Project project) throws IOException {
        if (virtualFile == null) {
            $$$reportNull$$$0(19);
        }
        FileContentImpl fileContentImplCreateByContent = createByContent(virtualFile, virtualFile.contentsToByteArray(false));
        if (project != null) {
            fileContentImplCreateByContent.setProject(project);
        }
        if (fileContentImplCreateByContent == null) {
            $$$reportNull$$$0(20);
        }
        return fileContentImplCreateByContent;
    }

    public static FileContent createByText(VirtualFile virtualFile, CharSequence charSequence, Project project) {
        if (virtualFile == null) {
            $$$reportNull$$$0(21);
        }
        if (charSequence == null) {
            $$$reportNull$$$0(22);
        }
        FileContentImpl fileContentImpl = new FileContentImpl(virtualFile, FileTypeRegistry.getInstance().getFileTypeByFile(virtualFile), charSequence, new NotNullComputable() { // from class: bp4
            public final Object compute() {
                return FileContentImpl.zd();
            }
        }, true);
        if (project != null) {
            fileContentImpl.setProject(project);
        }
        return fileContentImpl;
    }

    public static PsiFile createFileFromText(Project project, CharSequence charSequence, LanguageFileType languageFileType, VirtualFile virtualFile, String str) {
        if (project == null) {
            $$$reportNull$$$0(5);
        }
        if (charSequence == null) {
            $$$reportNull$$$0(6);
        }
        if (languageFileType == null) {
            $$$reportNull$$$0(7);
        }
        if (virtualFile == null) {
            $$$reportNull$$$0(8);
        }
        if (str == null) {
            $$$reportNull$$$0(9);
        }
        Language language = languageFileType.getLanguage();
        Language languageSubstituteLanguage = LanguageSubstitutors.getInstance().substituteLanguage(language, virtualFile, project);
        PsiFile psiFileCreateFileFromText = PsiFileFactory.getInstance(project).createFileFromText(str, languageSubstituteLanguage, charSequence, false, false, false, virtualFile);
        if (psiFileCreateFileFromText != null) {
            return psiFileCreateFileFromText;
        }
        co4.a("psiFile is null. language = ", language.getID(), ", substitutedLanguage = ", languageSubstituteLanguage.getID());
        return null;
    }

    public static FileType getFileTypeWithoutSubstitution(IndexedFile indexedFile) {
        if (indexedFile == null) {
            $$$reportNull$$$0(32);
        }
        FileType fileType = indexedFile.getFileType();
        if (fileType instanceof SubstitutedFileType) {
            fileType = ((SubstitutedFileType) fileType).getOriginalFileType();
        }
        if (fileType == null) {
            $$$reportNull$$$0(33);
        }
        return fileType;
    }

    public static /* synthetic */ byte[] yd(byte[] bArr) {
        return bArr;
    }

    public static /* synthetic */ byte[] zd() {
        throw new IllegalStateException("Content must be converted from 'contentAsText'");
    }

    public Charset getCharset() {
        Charset charset = this.myCharset;
        if (charset == null) {
            charset = this.myFile.getCharset();
            this.myCharset = charset;
        }
        if (charset == null) {
            $$$reportNull$$$0(24);
        }
        return charset;
    }

    public byte[] getContent() {
        if (this.myCachedContentBytes == null) {
            if (getFileTypeWithoutSubstitution(this).isBinary()) {
                this.myCachedContentBytes = computeOriginalContent();
            } else {
                this.myCachedContentBytes = getContentAsText().toString().getBytes(getCharset());
            }
        }
        byte[] bArr = this.myCachedContentBytes;
        if (bArr == null) {
            $$$reportNull$$$0(25);
        }
        return bArr;
    }

    public CharSequence getContentAsText() {
        FileType fileTypeWithoutSubstitution = getFileTypeWithoutSubstitution(this);
        if (fileTypeWithoutSubstitution.isBinary()) {
            o1c.a("Cannot obtain text for binary file type : ", fileTypeWithoutSubstitution.getDescription());
            return null;
        }
        CharSequence charSequence = (CharSequence) getUserData(IndexingDataKeys.FILE_TEXT_CONTENT_KEY);
        if (charSequence != null) {
            return charSequence;
        }
        if (this.myContentAsText == null) {
            this.myContentAsText = LoadTextUtil.getTextByBinaryPresentation(computeOriginalContent(), this.myFile, false, false);
        }
        CharSequence charSequence2 = this.myContentAsText;
        if (charSequence2 == null) {
            $$$reportNull$$$0(27);
        }
        return charSequence2;
    }

    public LighterAST getLighterAST() {
        Key<LighterAST> key = LIGHTER_AST_NODE_KEY;
        LighterAST treeBackedLighterAST = (LighterAST) getUserData(key);
        if (treeBackedLighterAST == null) {
            FileASTNode node = getPsiFile().getNode();
            treeBackedLighterAST = this.myLighterASTShouldBeThreadSafe ? new TreeBackedLighterAST(node) : node.getLighterAST();
            putUserData(key, treeBackedLighterAST);
        }
        if (treeBackedLighterAST == null) {
            $$$reportNull$$$0(3);
        }
        return treeBackedLighterAST;
    }

    public PsiFile getPsiFile() {
        Document cachedDocument;
        PsiFile psiFile;
        if (this.myTransientContent && (cachedDocument = FileDocumentManager.getInstance().getCachedDocument(getFile())) != null) {
            PsiDocumentManager psiDocumentManager = PsiDocumentManager.getInstance(getProject());
            if (psiDocumentManager.isUncommited(cachedDocument) && (psiFile = psiDocumentManager.getPsiFile(cachedDocument)) != null) {
                return checkPsiProjectConsistency(psiFile);
            }
        }
        PsiFile psiFile2 = (PsiFile) getUserData(IndexingDataKeys.PSI_FILE);
        if (psiFile2 != null) {
            return checkPsiProjectConsistency(psiFile2);
        }
        Key<PsiFile> key = CACHED_PSI;
        PsiFile psiFile3 = (PsiFile) getUserData(key);
        if (psiFile3 != null) {
            return checkPsiProjectConsistency(psiFile3);
        }
        PsiFile psiFileCreateFileFromText = createFileFromText(getContentAsText());
        psiFileCreateFileFromText.putUserData(IndexingDataKeys.VIRTUAL_FILE, getFile());
        putUserData(key, psiFileCreateFileFromText);
        return checkPsiProjectConsistency(psiFileCreateFileFromText);
    }

    @Override // com.intellij.util.indexing.IndexedFileImpl
    public String toString() {
        return "FileContentImpl(" + getFileName() + ")";
    }

    public static FileContent createByFile(VirtualFile virtualFile) throws IOException {
        if (virtualFile == null) {
            $$$reportNull$$$0(18);
        }
        return createByFile(virtualFile, null);
    }

    private PsiFile createFileFromText(CharSequence charSequence) {
        if (charSequence == null) {
            $$$reportNull$$$0(4);
        }
        Project project = getProject();
        LanguageFileType fileTypeWithoutSubstitution = getFileTypeWithoutSubstitution(this);
        if (fileTypeWithoutSubstitution instanceof LanguageFileType) {
            return createFileFromText(project, charSequence, fileTypeWithoutSubstitution, this.myFile, getFileName());
        }
        hih.a("PSI can be created only for a file with LanguageFileType but actual is ", fileTypeWithoutSubstitution.getClass(), ".\nPlease use a proper FileBasedIndexExtension#getInputFilter() implementation for the caller index");
        return null;
    }
}
