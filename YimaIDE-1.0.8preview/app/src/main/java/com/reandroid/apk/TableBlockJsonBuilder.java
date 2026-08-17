package com.reandroid.apk;

import com.reandroid.arsc.chunk.PackageBlock;
import com.reandroid.arsc.chunk.StagedAlias;
import com.reandroid.arsc.chunk.TableBlock;
import com.reandroid.arsc.chunk.TypeBlock;
import com.reandroid.arsc.value.ResConfig;
import com.reandroid.json.JSONArray;
import com.reandroid.json.JSONObject;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class TableBlockJsonBuilder {
    private void loadType(PackageBlock packageBlock, File file) throws IOException {
        JSONObject jSONObject = new JSONObject(file);
        ResConfig resConfig = new ResConfig();
        resConfig.fromJson(jSONObject.getJSONObject(TypeBlock.NAME_config));
        packageBlock.getSpecTypePairArray().getOrCreate((byte) (jSONObject.getInt(TypeBlock.NAME_id) & 255), resConfig).fromJson(jSONObject);
    }

    private void scanPackageDirectory(TableBlock tableBlock, File file) throws IOException {
        File file2 = new File(file, PackageBlock.JSON_FILE_NAME);
        if (!file2.isFile()) {
            r8g.a("Invalid package directory! Package file missing: ", file2);
            return;
        }
        JSONObject jSONObject = new JSONObject(file2);
        PackageBlock orCreate = tableBlock.getPackageArray().getOrCreate(jSONObject.getInt(PackageBlock.NAME_package_id));
        orCreate.setName(jSONObject.optString(PackageBlock.NAME_package_name));
        String str = PackageBlock.NAME_staged_aliases;
        if (jSONObject.has(str)) {
            JSONArray jSONArray = jSONObject.getJSONArray(str);
            StagedAlias stagedAlias = new StagedAlias();
            stagedAlias.getStagedAliasEntryArray().fromJson(jSONArray);
            orCreate.getStagedAliasList().add(stagedAlias);
        }
        List listListFiles = ApkUtil.listFiles(file, ".json");
        listListFiles.remove(file2);
        Iterator it = listListFiles.iterator();
        while (it.hasNext()) {
            loadType(orCreate, (File) it.next());
        }
        orCreate.sortTypes();
    }

    public TableBlock scanDirectory(File file) throws IOException {
        if (!file.isDirectory()) {
            r8g.a("No such directory: ", file);
            return null;
        }
        List listListPackageDirectories = ApkUtil.listPackageDirectories(file);
        if (listListPackageDirectories.size() == 0) {
            r8g.a("No package sub directory found in : ", file);
            return null;
        }
        TableBlock tableBlock = new TableBlock();
        Iterator it = listListPackageDirectories.iterator();
        while (it.hasNext()) {
            scanPackageDirectory(tableBlock, (File) it.next());
        }
        tableBlock.sortPackages();
        tableBlock.refresh();
        return tableBlock;
    }
}
