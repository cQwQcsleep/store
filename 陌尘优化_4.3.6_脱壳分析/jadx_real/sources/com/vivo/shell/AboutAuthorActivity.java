package com.vivo.shell;

import Ark.VMProtect;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import obfuse.NPStringFog;

/* loaded from: /workspace/unpacked/classes.dex */
public class AboutAuthorActivity extends Activity {
    static {
        VMProtect.ArkSafeVM(1055330058);
    }

    public void Back(View view) {
        finish();
    }

    public void ContactAuthor(View view) {
        Intent intent = new Intent(NPStringFog.decode("0F1E09130108034B1B0004080F1A4F060606071F034F38282232"));
        intent.setData(Uri.parse(NPStringFog.decode("03011C5B414E0404000A5F1E09011638150102130C130A5E141711310414110B5C0E0B060B0203000247120C1C53435F585B555350475F46")));
        intent.addFlags(268435456);
        startActivity(intent);
    }

    public void ContactQQ(View view) {
        Intent intent = new Intent(NPStringFog.decode("0F1E09130108034B1B0004080F1A4F060606071F034F38282232"));
        intent.setData(Uri.parse(NPStringFog.decode("03011C5B414E0404000A5F1E09011638150102130C130A5E141711310414110B5C0E0B060B0203000247120C1C53435C545E545455435D47")));
        intent.addFlags(268435456);
        startActivity(intent);
    }

    public void OnNewCardClick(View view) {
        Intent intent = new Intent(NPStringFog.decode("0F1E09130108034B1B0004080F1A4F060606071F034F38282232"));
        intent.setData(Uri.parse(NPStringFog.decode("03011C001E085D4A5D0D111F0541120F0A0531001E0D0D0015014D1D020E3E1A1817004F071E19041C0F06095418151F12070E0958434805040F5350575341574554565E584106131C14321517110258151C1F1811")));
        intent.addFlags(268435456);
        startActivity(intent);
    }

    @Override // android.app.Activity
    protected native void onCreate(Bundle bundle);

    public void reward(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        SpannableString spannableString = new SpannableString(NPStringFog.decode("86C5F389DBEE81F1DD88FCEC"));
        spannableString.setSpan(new AbsoluteSizeSpan((int) (24.0f * getResources().getDisplayMetrics().scaledDensity)), 0, spannableString.length(), 33);
        spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(2130771970)), 0, spannableString.length(), 33);
        AlertDialog.Builder title = builder.setTitle(spannableString);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(1);
        int i = (int) (12.0f * getResources().getDisplayMetrics().density);
        linearLayout.setPadding(i, i, i, i);
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(2130837520);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams((int) (320.0f * getResources().getDisplayMetrics().density), (int) (320.0f * getResources().getDisplayMetrics().density));
        layoutParams.gravity = 17;
        linearLayout.addView(imageView, layoutParams);
        AlertDialog alertDialogCreate = title.setView(linearLayout).setNegativeButton(NPStringFog.decode("8BFFFB87D8E9"), (DialogInterface.OnClickListener) null).setCancelable(false).create();
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(getResources().getColor(2130771969));
        gradientDrawable.setCornerRadius(30.0f * getResources().getDisplayMetrics().density);
        alertDialogCreate.show();
        Window window = alertDialogCreate.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(gradientDrawable);
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.85f);
            window.setAttributes(attributes);
        }
        Button button = alertDialogCreate.getButton(-2);
        if (button != null) {
            button.setTextColor(-16744449);
        }
    }
}
