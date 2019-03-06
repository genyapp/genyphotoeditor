package com.apkgeny.genyphotoeditor;

import android.Manifest;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.listener.single.BasePermissionListener;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    //define menu
    HorizontalScrollView menuUtama, menuOrientations, menuEffecs;
    //define aset menuUtama
    ImageButton btnOrientations, btnEffects, btnBrightness, btnContrast, btnAutolevel, btnInvers, btnHequalization;
    //define aset menuOrientations
    ImageButton btnBack1, btnRotate90, btnRotate180, btnFliphorizontal, btnFlipvertical;
    //define aset menuEffects
    ImageButton btnBack2, btnRed, btnGreen, btnBlue, btnGrayscaler, btnGrayscaleg, btnGrayscaleb, btnGrayscale, btnBlackwhite128, btnBlackwhiterata, btnBlackwhiteuser, btnKuantisasi16, btnKuantisasiuser, btnSephia;
    //define aset menuBrightness
    HorizontalScrollView menuBrightness;
    ImageButton btnBack3;
    Button btnGobrightness;
    EditText editBrightness;
    //define aset menuContrast
    HorizontalScrollView menuContrast;
    ImageButton btnBack4;
    Button btnGocontrast;
    EditText editContrast;
    //define aset menuBlackwhiteuser
    HorizontalScrollView menuBlackwhiteuser;
    ImageButton btnBack5;
    Button btnGoblackwhiteuser;
    EditText editBlackwhiteuser;
    //define aset menuKuantisasiuser
    HorizontalScrollView menuKuantisasiuser;
    ImageButton btnBack6;
    Button btnGokuantisasiuser;
    EditText editKuantisasiuser;

    ImageButton btnLoad;
    ImageButton btnSave;
    ImageView image;
    Bitmap objBitmap1;
    Bitmap objBitmaptemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Dexter.withActivity(this)
                .withPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener( new BasePermissionListener())
                .check();

        //define menu
        menuUtama = (HorizontalScrollView)this.findViewById(R.id.menu_utama);
        menuOrientations = (HorizontalScrollView)this.findViewById(R.id.menu_orientations);
        menuEffecs = (HorizontalScrollView)this.findViewById(R.id.menu_effecs);
        //define button menuUtama
        btnOrientations = (ImageButton)findViewById(R.id.btn_orientations);
        btnEffects = (ImageButton)findViewById(R.id.btn_effects);
        btnBrightness = (ImageButton)findViewById(R.id.btn_brightness);
        btnContrast = (ImageButton)findViewById(R.id.btn_contrast);
        btnAutolevel = (ImageButton)findViewById(R.id.btn_autolevel);
        btnInvers = (ImageButton)findViewById(R.id.btn_invers);
        btnHequalization = (ImageButton)findViewById(R.id.btn_hequalization);
        //define button menuOrientations
        btnBack1 = (ImageButton)findViewById(R.id.btn_back);
        btnRotate90 = (ImageButton)findViewById(R.id.btn_rotate90);
        btnRotate180 = (ImageButton)findViewById(R.id.btn_rotate180);
        btnFliphorizontal = (ImageButton)findViewById(R.id.btn_fliphorizontal);
        btnFlipvertical = (ImageButton)findViewById(R.id.btn_flipvertical);
        //define button menuEffects
        btnBack2 = (ImageButton)findViewById(R.id.btn_back2);
        btnRed = (ImageButton)findViewById(R.id.btn_red);
        btnGreen = (ImageButton)findViewById(R.id.btn_green);
        btnBlue = (ImageButton)findViewById(R.id.btn_blue);
        btnGrayscaler = (ImageButton)findViewById(R.id.btn_grayscaler);
        btnGrayscaleg = (ImageButton)findViewById(R.id.btn_grayscaleg);
        btnGrayscaleb = (ImageButton)findViewById(R.id.btn_grayscaleb);
        btnGrayscale = (ImageButton)findViewById(R.id.btn_grayscale);
        btnBlackwhite128 = (ImageButton)findViewById(R.id.btn_blackwhite128);
        btnBlackwhiterata = (ImageButton)findViewById(R.id.btn_blackwhiterata);
        btnBlackwhiteuser = (ImageButton)findViewById(R.id.btn_blackwhiteuser);
        btnKuantisasi16 = (ImageButton)findViewById(R.id.btn_kuantisasi16);
        btnKuantisasiuser = (ImageButton)findViewById(R.id.btn_kuantisasiuser);
        btnSephia = (ImageButton)findViewById(R.id.btn_sephia);

        //define aset menuBrightness
        menuBrightness = (HorizontalScrollView)this.findViewById(R.id.menu_brightness);
        btnBack3 = (ImageButton)findViewById(R.id.btn_back3);
        btnGobrightness = (Button)findViewById(R.id.btn_gobrightness);
        editBrightness = (EditText)findViewById(R.id.edit_brightness);

        //define aset menuContrast
        menuContrast = (HorizontalScrollView)this.findViewById(R.id.menu_contrast);
        btnBack4 = (ImageButton)findViewById(R.id.btn_back4);
        btnGocontrast = (Button)findViewById(R.id.btn_gocontrast);
        editContrast = (EditText)findViewById(R.id.edit_contrast);

        //define aset menuBlackwhiteuser
        menuBlackwhiteuser = (HorizontalScrollView)this.findViewById(R.id.menu_blackwhiteuser);
        btnBack5 = (ImageButton)findViewById(R.id.btn_back5);
        btnGoblackwhiteuser = (Button)findViewById(R.id.btn_goblackwhiteuser);
        editBlackwhiteuser = (EditText)findViewById(R.id.edit_blackwhiteuser);

        //define aset menuBlackwhiteuser
        menuKuantisasiuser = (HorizontalScrollView)this.findViewById(R.id.menu_kuantisasiuser);
        btnBack6 = (ImageButton)findViewById(R.id.btn_back6);
        btnGokuantisasiuser = (Button)findViewById(R.id.btn_gokuantisasiuser);
        editKuantisasiuser = (EditText)findViewById(R.id.edit_kuantisasiuser);

        //define menu atas dan gambar
        image = (ImageView)findViewById(R.id.main_image);
        btnLoad = (ImageButton)findViewById(R.id.btn_load);
        btnSave = (ImageButton)findViewById(R.id.btn_save);

        //awal program jalan, sembunyikan menu selain menu utama
        menuOrientations.setVisibility(HorizontalScrollView.GONE);
        menuEffecs.setVisibility(HorizontalScrollView.GONE);
        menuBrightness.setVisibility(HorizontalScrollView.GONE);
        menuContrast.setVisibility(HorizontalScrollView.GONE);
        menuBlackwhiteuser.setVisibility(HorizontalScrollView.GONE);
        menuKuantisasiuser.setVisibility(HorizontalScrollView.GONE);



        //ACTION
        //Action Menu Atas
        btnLoad.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, 1);
            }
        });
        btnSave.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String root = Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_PICTURES).toString();
                File myDir = new File(root + "/geny_images");
                myDir.mkdirs();
                Random generator = new Random();

                int n = 10000;
                n = generator.nextInt(n);
                String fname = "Geny-"+ n +".jpg";
                File file = new File (myDir, fname);
                if (file.exists ()) file.delete ();
                try {
                    FileOutputStream out = new FileOutputStream(file);
                    objBitmaptemp.compress(Bitmap.CompressFormat.JPEG, 100, out);
                    // sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED,
                    //     Uri.parse("file://"+ Environment.getExternalStorageDirectory())));
                    out.flush();
                    out.close();
                    Toast.makeText(MainActivity.this,
                            "Image Saved to "+root+"/geny_images/"+fname, Toast.LENGTH_LONG).show();


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        //End Action Menu Atas


        //Action Menu Utama
        btnOrientations.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                menuUtama.setVisibility(HorizontalScrollView.GONE);
                menuOrientations.setVisibility(HorizontalScrollView.VISIBLE);
            }
        });
        btnEffects.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                menuUtama.setVisibility(HorizontalScrollView.GONE);
                menuEffecs.setVisibility(HorizontalScrollView.VISIBLE);
            }
        });
        btnAutolevel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap objBitmap2 = Bitmap.createBitmap(objBitmap1.getWidth(), objBitmap1.getHeight(), Config.RGB_565);
                Canvas c = new Canvas(objBitmap2);
                c.drawBitmap(objBitmap1, 0, 0, new Paint());
                for (int x = 0; x < objBitmap1.getWidth(); x++)
                    for (int y = 0; y < objBitmap1.getHeight(); y++)
                    {
                        int w = objBitmap1.getPixel(x, y);
                        int xg = (int)((Color.red(w) + Color.green(w) + Color.blue(w)) / 3);
                        int xi = 255 - xg;
                        int new_w = Color.rgb(xi, xi, xi);//java
                        //Color new_w = Color.FromArgb(xi, xi, xi);//C#
                        objBitmap2.setPixel(x, y, new_w);
                    }
                image.setImageBitmap(objBitmap2);
                objBitmaptemp=objBitmap2.copy(objBitmap2.getConfig(), true);
            }
        });
        btnInvers.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap objBitmap2 = Bitmap.createBitmap(objBitmap1.getWidth(), objBitmap1.getHeight(), Config.RGB_565);
                Canvas c = new Canvas(objBitmap2);
                c.drawBitmap(objBitmap1, 0, 0, new Paint());
                for (int x = 0; x < objBitmap1.getWidth(); x++)
                    for (int y = 0; y < objBitmap1.getHeight(); y++)
                    {
                        int w = objBitmap1.getPixel(x, y);
                        int xg = (int)((Color.red(w) + Color.green(w) + Color.blue(w)) / 3);
                        int xi = 255 - xg;
                        int new_w = Color.rgb(xi, xi, xi);//java
                        //Color new_w = Color.FromArgb(xi, xi, xi);//C#
                        objBitmap2.setPixel(x, y, new_w);
                    }
                image.setImageBitmap(objBitmap2);
                objBitmaptemp=objBitmap2.copy(objBitmap2.getConfig(), true);
            }
        });
        btnHequalization.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap objBitmap2 = Bitmap.createBitmap(objBitmap1.getWidth(), objBitmap1.getHeight(), Config.RGB_565);
                Canvas c = new Canvas(objBitmap2);
                c.drawBitmap(objBitmap1, 0, 0, new Paint());
                float[] h = new float[256];
                float[] cc = new float[256];
                int i;
                for (i = 0; i < 256; i++) h[i]=0;
                for (int x = 0; x < objBitmap1.getWidth(); x++)
                    for (int y = 0; y < objBitmap1.getHeight(); y++)
                    {
                        int w = objBitmap1.getPixel(x, y);
                        int xg = (int)((Color.red(w) + Color.green(w) + Color.blue(w)) / 3);
                        h[xg] = h[xg] + 1;
                    }
                cc[0] = h[0];
                for(i=1;i<256;i++) cc[i]=cc[i-1]+h[i];
                int nx = objBitmap1.getWidth();
                int ny = objBitmap1.getHeight();
                for (int x = 0; x < objBitmap1.getWidth(); x++)
                    for (int y = 0; y < objBitmap1.getHeight(); y++)
                    {
                        int w = objBitmap1.getPixel(x, y);
                        int xg = (int)((Color.red(w) + Color.green(w) + Color.blue(w)) / 3);
                        int xb = (int)(255 * cc[xg] / nx / ny);
                        int new_w = Color.rgb(xg, xg, xg);//java
                        //Color new_w = Color.FromArgb(xi, xi, xi);//C#
                        objBitmap2.setPixel(x, y, new_w);
                    }
                image.setImageBitmap(objBitmap2);
                objBitmaptemp=objBitmap2.copy(objBitmap2.getConfig(), true);
            }
        });
        //End Action Menu Utama


        //Action Menu Orientations
        btnBack1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                menuOrientations.setVisibility(LinearLayout.GONE);
                menuUtama.setVisibility(LinearLayout.VISIBLE);
            }
        });
        btnRotate90.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap objBitmap2 = Bitmap.createBitmap(objBitmap1.getHeight(), objBitmap1.getWidth(), Config.RGB_565);
                Canvas c = new Canvas(objBitmap2);
                c.drawBitmap(objBitmap1, 0, 0, new Paint());
                for (int x = 0; x < objBitmap1.getWidth(); x++)
                    for (int y = 0; y < objBitmap1.getHeight(); y++)
                    {
                        int w = objBitmap1.getPixel(x, y);
                        objBitmap2.setPixel(objBitmap1.getHeight() - 1 - y, x, w);
                    }
                image.setImageBitmap(objBitmap2);
                objBitmap1=objBitmap2;
                objBitmaptemp=objBitmap2.copy(objBitmap2.getConfig(), true);
            }
        });
        btnRotate180.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap objBitmap2 = Bitmap.createBitmap(objBitmap1.getWidth(), objBitmap1.getHeight(), Config.RGB_565);
                Canvas c = new Canvas(objBitmap2);
                c.drawBitmap(objBitmap1, 0, 0, new Paint());
                for (int x = 0; x < objBitmap1.getWidth(); x++)
                    for (int y = 0; y < objBitmap1.getHeight(); y++)
                    {
                        int w = objBitmap1.getPixel(x, y);
                        objBitmap2.setPixel(objBitmap1.getWidth() - 1 - x, objBitmap1.getHeight() - 1 - y, w);
                    }
                image.setImageBitmap(objBitmap2);
                objBitmap1=objBitmap2;
                objBitmaptemp=objBitmap2.copy(objBitmap2.getConfig(), true);
            }
        });
        btnFliphorizontal.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap objBitmap2 = Bitmap.createBitmap(objBitmap1.getWidth(), objBitmap1.getHeight(), Config.RGB_565);
                Canvas c = new Canvas(objBitmap2);
                c.drawBitmap(objBitmap1, 0, 0, new Paint());
                for (int x = 0; x < objBitmap1.getWidth(); x++)
                    for (int y = 0; y < objBitmap1.getHeight(); y++)
                    {
                        int w = objBitmap1.getPixel(x, y);
                        objBitmap2.setPixel(objBitmap1.getWidth() - 1 - x, y, w);
                    }
                image.setImageBitmap(objBitmap2);
                objBitmap1=objBitmap2;
                objBitmaptemp=objBitmap2.copy(objBitmap2.getConfig(), true);
            }
        });
        btnFlipvertical.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap objBitmap2 = Bitmap.createBitmap(objBitmap1.getWidth(), objBitmap1.getHeight(), Config.RGB_565);
                Canvas c = new Canvas(objBitmap2);
                c.drawBitmap(objBitmap1, 0, 0, new Paint());
                for (int x = 0; x < objBitmap1.getWidth(); x++)
                    for (int y = 0; y < objBitmap1.getHeight(); y++)
                    {
                        int w = objBitmap1.getPixel(x, y);
                        objBitmap2.setPixel(x, objBitmap1.getHeight() - 1 - y, w);
                    }
                image.setImageBitmap(objBitmap2);
                objBitmap1=objBitmap2;
                objBitmaptemp=objBitmap2.copy(objBitmap2.getConfig(), true);
            }
        });
        //End Action Menu Orientations


        //Action Menu Effects
        btnBack2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                menuEffecs.setVisibility(LinearLayout.GONE);
                menuUtama.setVisibility(LinearLayout.VISIBLE);
            }
        });
        btnRed.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap objBitmap2 = Bitmap.createBitmap(objBitmap1.getWidth(), objBitmap1.getHeight(), Config.RGB_565);
                Canvas c = new Canvas(objBitmap2);
                c.drawBitmap(objBitmap1, 0, 0, new Paint());
                for (int x = 0; x < objBitmap1.getWidth(); x++)
                    for (int y = 0; y < objBitmap1.getHeight(); y++)
                    {
                        int w = objBitmap1.getPixel(x, y);
                        int xr = Color.red(w);
                        int new_w = Color.rgb(xr, 0, 0);//java
                        //Color new_w = Color.FromArgb(xi, xi, xi);//C#
                        objBitmap2.setPixel(x, y, new_w);
                    }
                image.setImageBitmap(objBitmap2);
                objBitmaptemp=objBitmap2.copy(objBitmap2.getConfig(), true);
            }
        });
        btnGreen.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap objBitmap2 = Bitmap.createBitmap(objBitmap1.getWidth(), objBitmap1.getHeight(), Config.RGB_565);
                Canvas c = new Canvas(objBitmap2);
                c.drawBitmap(objBitmap1, 0, 0, new Paint());
                for (int x = 0; x < objBitmap1.getWidth(); x++)
                    for (int y = 0; y < objBitmap1.getHeight(); y++)
                    {
                        int w = objBitmap1.getPixel(x, y);
                        int xg = Color.green(w);
                        int new_w = Color.rgb(0, xg, 0);//java
                        //Color new_w = Color.FromArgb(xi, xi, xi);//C#
                        objBitmap2.setPixel(x, y, new_w);
                    }
                image.setImageBitmap(objBitmap2);
                objBitmaptemp=objBitmap2.copy(objBitmap2.getConfig(), true);
            }
        });
        btnBlue.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap objBitmap2 = Bitmap.createBitmap(objBitmap1.getWidth(), objBitmap1.getHeight(), Config.RGB_565);
                Canvas c = new Canvas(objBitmap2);
                c.drawBitmap(objBitmap1, 0, 0, new Paint());
                for (int x = 0; x < objBitmap1.getWidth(); x++)
                    for (int y = 0; y < objBitmap1.getHeight(); y++)
                    {
                        int w = objBitmap1.getPixel(x, y);
                        int xb = Color.blue(w);
                        int new_w = Color.rgb(0, 0, xb);//java
                        //Color new_w = Color.FromArgb(xi, xi, xi);//C#
                        objBitmap2.setPixel(x, y, new_w);
                    }
                image.setImageBitmap(objBitmap2);
                objBitmaptemp=objBitmap2.copy(objBitmap2.getConfig(), true);
            }
        });
        btnGrayscaler.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap objBitmap2 = Bitmap.createBitmap(objBitmap1.getWidth(), objBitmap1.getHeight(), Config.RGB_565);
                Canvas c = new Canvas(objBitmap2);
                c.drawBitmap(objBitmap1, 0, 0, new Paint());
                for (int x = 0; x < objBitmap1.getWidth(); x++)
                    for (int y = 0; y < objBitmap1.getHeight(); y++)
                    {
                        int w = objBitmap1.getPixel(x, y);
                        int xr = Color.red(w);
                        int new_w = Color.rgb(xr, xr, xr);//java
                        //Color new_w = Color.FromArgb(xi, xi, xi);//C#
                        objBitmap2.setPixel(x, y, new_w);
                    }
                image.setImageBitmap(objBitmap2);
                objBitmaptemp=objBitmap2.copy(objBitmap2.getConfig(), true);
            }
        });
        btnGrayscaleg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap objBitmap2 = Bitmap.createBitmap(objBitmap1.getWidth(), objBitmap1.getHeight(), Config.RGB_565);
                Canvas c = new Canvas(objBitmap2);
                c.drawBitmap(objBitmap1, 0, 0, new Paint());
                for (int x = 0; x < objBitmap1.getWidth(); x++)
                    for (int y = 0; y < objBitmap1.getHeight(); y++)
                    {
                        int w = objBitmap1.getPixel(x, y);
                        int xg = Color.green(w);
                        int new_w = Color.rgb(xg, xg, xg);//java
                        //Color new_w = Color.FromArgb(xi, xi, xi);//C#
                        objBitmap2.setPixel(x, y, new_w);
                    }
                image.setImageBitmap(objBitmap2);
                objBitmaptemp=objBitmap2.copy(objBitmap2.getConfig(), true);
            }
        });
        btnGrayscaleb.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap objBitmap2 = Bitmap.createBitmap(objBitmap1.getWidth(), objBitmap1.getHeight(), Config.RGB_565);
                Canvas c = new Canvas(objBitmap2);
                c.drawBitmap(objBitmap1, 0, 0, new Paint());
                for (int x = 0; x < objBitmap1.getWidth(); x++)
                    for (int y = 0; y < objBitmap1.getHeight(); y++)
                    {
                        int w = objBitmap1.getPixel(x, y);
                        int xb = Color.blue(w);
                        int new_w = Color.rgb(xb, xb, xb);//java
                        //Color new_w = Color.FromArgb(xi, xi, xi);//C#
                        objBitmap2.setPixel(x, y, new_w);
                    }
                image.setImageBitmap(objBitmap2);
                objBitmaptemp=objBitmap2.copy(objBitmap2.getConfig(), true);
            }
        });
        btnGrayscale.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap objBitmap2 = Bitmap.createBitmap(objBitmap1.getWidth(), objBitmap1.getHeight(), Config.RGB_565);
                Canvas c = new Canvas(objBitmap2);
                c.drawBitmap(objBitmap1, 0, 0, new Paint());
                for (int x = 0; x < objBitmap1.getWidth(); x++)
                    for (int y = 0; y < objBitmap1.getHeight(); y++)
                    {
                        int w = objBitmap1.getPixel(x, y);
                        int xg = (int)((Color.red(w) + Color.green(w) + Color.blue(w)) / 3);
                        int new_w = Color.rgb(xg, xg, xg);//java
                        //Color new_w = Color.FromArgb(xi, xi, xi);//C#
                        objBitmap2.setPixel(x, y, new_w);
                    }
                image.setImageBitmap(objBitmap2);
                objBitmaptemp=objBitmap2.copy(objBitmap2.getConfig(), true);
            }
        });
        btnBlackwhite128.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap objBitmap2 = Bitmap.createBitmap(objBitmap1.getWidth(), objBitmap1.getHeight(), Config.RGB_565);
                Canvas c = new Canvas(objBitmap2);
                c.drawBitmap(objBitmap1, 0, 0, new Paint());
                for (int x = 0; x < objBitmap1.getWidth(); x++)
                    for (int y = 0; y < objBitmap1.getHeight(); y++)
                    {
                        int w = objBitmap1.getPixel(x, y);
                        int xg = (int)((Color.red(w) + Color.green(w) + Color.blue(w)) / 3);
                        int xbw = 0;
                        if (xg >= 128)
                            xbw = 255;
                        int new_w = Color.rgb(xbw, xbw, xbw);//java
                        //Color new_w = Color.FromArgb(xi, xi, xi);//C#
                        objBitmap2.setPixel(x, y, new_w);
                    }
                image.setImageBitmap(objBitmap2);
                objBitmaptemp=objBitmap2.copy(objBitmap2.getConfig(), true);
            }
        });
        btnBlackwhiterata.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap objBitmap2 = Bitmap.createBitmap(objBitmap1.getWidth(), objBitmap1.getHeight(), Config.RGB_565);
                Canvas c = new Canvas(objBitmap2);
                c.drawBitmap(objBitmap1, 0, 0, new Paint());
                int temp=0, total=0;
                for (int x = 0; x < objBitmap1.getWidth(); x++)
                    for (int y = 0; y < objBitmap1.getHeight(); y++)
                    {
                        int w = objBitmap1.getPixel(x, y);
                        int xg = (int)((Color.red(w) + Color.green(w) + Color.blue(w)) / 3);
                        total++;
                        temp = temp + xg;
                    }
                int tres = temp / total;

                for (int x = 0; x < objBitmap1.getWidth(); x++)
                    for (int y = 0; y < objBitmap1.getHeight(); y++)
                    {
                        int w = objBitmap1.getPixel(x, y);
                        int xg = (int)((Color.red(w) + Color.green(w) + Color.blue(w)) / 3);
                        int xbw = 0;
                        if (xg >= tres)
                            xbw = 255;
                        int new_w = Color.rgb(xbw, xbw, xbw);//java
                        //Color new_w = Color.FromArgb(xi, xi, xi);//C#
                        objBitmap2.setPixel(x, y, new_w);
                    }
                image.setImageBitmap(objBitmap2);
                objBitmaptemp=objBitmap2.copy(objBitmap2.getConfig(), true);
            }
        });

        //menu Blackwhiteuser
        btnBlackwhiteuser.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                menuEffecs.setVisibility(HorizontalScrollView.GONE);
                menuBlackwhiteuser.setVisibility(HorizontalScrollView.VISIBLE);
            }
        });
        btnBack5.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                menuBlackwhiteuser.setVisibility(HorizontalScrollView.GONE);
                menuEffecs.setVisibility(HorizontalScrollView.VISIBLE);
            }
        });
        btnGoblackwhiteuser.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap objBitmap2 = Bitmap.createBitmap(objBitmap1.getWidth(), objBitmap1.getHeight(), Config.RGB_565);
                Canvas c = new Canvas(objBitmap2);
                c.drawBitmap(objBitmap1, 0, 0, new Paint());
                int thres = Integer.parseInt(editBlackwhiteuser.getText().toString());
                for (int x = 0; x < objBitmap1.getWidth(); x++)
                    for (int y = 0; y < objBitmap1.getHeight(); y++)
                    {
                        int w = objBitmap1.getPixel(x, y);
                        int xg = (int)((Color.red(w) + Color.green(w) + Color.blue(w)) / 3);
                        int xbw = 0;
                        if (xg >= thres)
                            xbw = 255;
                        int new_w = Color.rgb(xbw, xbw, xbw);//java
                        //Color new_w = Color.FromArgb(xi, xi, xi);//C#
                        objBitmap2.setPixel(x, y, new_w);
                    }
                image.setImageBitmap(objBitmap2);
                objBitmaptemp=objBitmap2.copy(objBitmap2.getConfig(), true);
            }
        });
        //end menu Blackwhiteuser

        btnKuantisasi16.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap objBitmap2 = Bitmap.createBitmap(objBitmap1.getWidth(), objBitmap1.getHeight(), Config.RGB_565);
                Canvas c = new Canvas(objBitmap2);
                c.drawBitmap(objBitmap1, 0, 0, new Paint());
                for (int x = 0; x < objBitmap1.getWidth(); x++)
                    for (int y = 0; y < objBitmap1.getHeight(); y++)
                    {
                        int w = objBitmap1.getPixel(x, y);
                        int xg = (int)((Color.red(w) + Color.green(w) + Color.blue(w)) / 3);
                        int xk = 16 * (int)(xg / 16);
                        int new_w = Color.rgb(xk, xk, xk);//java
                        //Color new_w = Color.FromArgb(xi, xi, xi);//C#
                        objBitmap2.setPixel(x, y, new_w);
                    }
                image.setImageBitmap(objBitmap2);
                objBitmaptemp=objBitmap2.copy(objBitmap2.getConfig(), true);
            }
        });

        //menu Kuantisasiuser
        btnKuantisasiuser.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                menuEffecs.setVisibility(HorizontalScrollView.GONE);
                menuKuantisasiuser.setVisibility(HorizontalScrollView.VISIBLE);
            }
        });
        btnBack6.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                menuKuantisasiuser.setVisibility(HorizontalScrollView.GONE);
                menuEffecs.setVisibility(HorizontalScrollView.VISIBLE);
            }
        });
        btnGokuantisasiuser.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap objBitmap2 = Bitmap.createBitmap(objBitmap1.getWidth(), objBitmap1.getHeight(), Config.RGB_565);
                Canvas c = new Canvas(objBitmap2);
                c.drawBitmap(objBitmap1, 0, 0, new Paint());
                int kuantisasi = Integer.parseInt(editKuantisasiuser.getText().toString());
                for (int x = 0; x < objBitmap1.getWidth(); x++)
                    for (int y = 0; y < objBitmap1.getHeight(); y++)
                    {
                        int w = objBitmap1.getPixel(x, y);
                        int xg = (int)((Color.red(w) + Color.green(w) + Color.blue(w)) / 3);
                        int xk = kuantisasi * (int)(xg / kuantisasi);
                        int new_w = Color.rgb(xk, xk, xk);//java
                        //Color new_w = Color.FromArgb(xi, xi, xi);//C#
                        objBitmap2.setPixel(x, y, new_w);
                    }
                image.setImageBitmap(objBitmap2);
                objBitmaptemp=objBitmap2.copy(objBitmap2.getConfig(), true);
            }
        });
        //end menu Kuantisasi user

        btnSephia.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap objBitmap2 = Bitmap.createBitmap(objBitmap1.getWidth(), objBitmap1.getHeight(), Config.RGB_565);
                Canvas c = new Canvas(objBitmap2);
                c.drawBitmap(objBitmap1, 0, 0, new Paint());
                for (int x = 0; x < objBitmap1.getWidth(); x++)
                    for (int y = 0; y < objBitmap1.getHeight(); y++)
                    {
                        int w = objBitmap1.getPixel(x, y);
                        int xr = Color.blue(w);
                        int xg = (byte)(xr*0.95);
                        if(xg>255)
                            xg=255;
                        int xb = (byte)(xr*0.5);
                        if(xb>255)
                            xb=255;
                        int new_w = Color.rgb(xr, xg, xb);//java
                        //Color new_w = Color.FromArgb(xi, xi, xi);//C#
                        objBitmap2.setPixel(x, y, new_w);
                    }
                image.setImageBitmap(objBitmap2);
                objBitmaptemp=objBitmap2.copy(objBitmap2.getConfig(), true);
            }
        });
        //End Action Menu Effects


        //Action Menu Brightness belum
        btnBrightness.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                menuUtama.setVisibility(HorizontalScrollView.GONE);
                menuBrightness.setVisibility(HorizontalScrollView.VISIBLE);
            }
        });
        btnBack3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                menuBrightness.setVisibility(HorizontalScrollView.GONE);
                menuUtama.setVisibility(HorizontalScrollView.VISIBLE);
            }
        });
        btnGobrightness.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap objBitmap2 = Bitmap.createBitmap(objBitmap1.getWidth(), objBitmap1.getHeight(), Config.RGB_565);
                Canvas c = new Canvas(objBitmap2);
                c.drawBitmap(objBitmap1, 0, 0, new Paint());
                int level = Integer.parseInt(editBrightness.getText().toString());
                for (int x = 0; x < objBitmap1.getWidth(); x++)
                    for (int y = 0; y < objBitmap1.getHeight(); y++)
                    {
                        int w = objBitmap1.getPixel(x, y);
                        int xg = (int)((Color.red(w) + Color.green(w) + Color.blue(w)) / 3);
                        int xb = xg + level;
                        if (xb > 255)
                            xb = 255;
                        else if (xb < 0)
                            xb = 0;
                        int new_w = Color.rgb(xb, xb, xb);//java
                        //Color new_w = Color.FromArgb(xi, xi, xi);//C#
                        objBitmap2.setPixel(x, y, new_w);
                    }
                image.setImageBitmap(objBitmap2);
                objBitmaptemp=objBitmap2.copy(objBitmap2.getConfig(), true);
            }
        });
        //End Action Menu Brightness


        //Action Menu Contrast Belum
        btnContrast.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                menuUtama.setVisibility(HorizontalScrollView.GONE);
                menuContrast.setVisibility(HorizontalScrollView.VISIBLE);
            }
        });
        btnBack4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                menuContrast.setVisibility(HorizontalScrollView.GONE);
                menuUtama.setVisibility(HorizontalScrollView.VISIBLE);
            }
        });
        btnGocontrast.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap objBitmap2 = Bitmap.createBitmap(objBitmap1.getWidth(), objBitmap1.getHeight(), Config.RGB_565);
                Canvas c = new Canvas(objBitmap2);
                c.drawBitmap(objBitmap1, 0, 0, new Paint());
                float level = Float.parseFloat(editContrast.getText().toString());
                for (int x = 0; x < objBitmap1.getWidth(); x++)
                    for (int y = 0; y < objBitmap1.getHeight(); y++)
                    {
                        int w = objBitmap1.getPixel(x, y);
                        float xg = (Color.red(w) + Color.green(w) + Color.blue(w)) / 3;
                        int xc = (int)(xg * level);
                        if (xc > 255)
                            xc = 255;
                        else if (xc < 0)
                            xc = 0;
                        int new_w = Color.rgb(xc, xc, xc);//java
                        //Color new_w = Color.FromArgb(xi, xi, xi);//C#
                        objBitmap2.setPixel(x, y, new_w);
                    }
                image.setImageBitmap(objBitmap2);
                objBitmaptemp=objBitmap2.copy(objBitmap2.getConfig(), true);
            }
        });
        //END Action Menu Contrast


        //Action Menu Autolevel
        btnAutolevel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap objBitmap2 = Bitmap.createBitmap(objBitmap1.getWidth(), objBitmap1.getHeight(), Config.RGB_565);
                Canvas c = new Canvas(objBitmap2);
                c.drawBitmap(objBitmap1, 0, 0, new Paint());
                int xgmax = 0;
                int xgmin = 255;
                for (int x = 0; x < objBitmap1.getWidth(); x++)
                    for (int y = 0; y < objBitmap1.getHeight(); y++)
                    {
                        int w = objBitmap1.getPixel(x, y);
                        int xg = (int)((Color.red(w) + Color.green(w) + Color.blue(w)) / 3);
                        if(xg > xgmax) xgmax = xg;
                        if(xg < xgmin) xgmin = xg;
                    }
                for (int x = 0; x < objBitmap1.getWidth(); x++)
                    for (int y = 0; y < objBitmap1.getHeight(); y++)
                    {
                        int w = objBitmap1.getPixel(x, y);
                        int xg = (int)((Color.red(w) + Color.green(w) + Color.blue(w)) / 3);
                        int xb = (int)(255 * (xg - xgmin) / (xgmax - xgmin));
                        int new_w = Color.rgb(xb, xb, xb);//java
                        //Color new_w = Color.FromArgb(xi, xi, xi);//C#
                        objBitmap2.setPixel(x, y, new_w);
                    }
                image.setImageBitmap(objBitmap2);
                objBitmaptemp=objBitmap2.copy(objBitmap2.getConfig(), true);
            }
        });
        //End Action Menu Autolevel
    }



    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
            {
                if (resultCode == RESULT_OK)
                {
                    Uri photoUri = data.getData();
                    if (photoUri != null)
                    {
                        try {
                            String[] filePathColumn = {MediaStore.Images.Media.DATA};
                            Cursor cursor = getContentResolver().query(photoUri, filePathColumn, null, null, null);
                            cursor.moveToFirst();
                            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                            String filePath = cursor.getString(columnIndex);
                            cursor.close();
                            objBitmap1 = BitmapFactory.decodeFile(filePath);
                            image.setImageBitmap(objBitmap1);

                        }catch(Exception e)
                        {}
                    }
                }
            }
        }
    }

    public void onBackPressed() {
        if(isTaskRoot()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Want to exit this app?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            MainActivity.this.finish();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();

        } else {
            super.onBackPressed();
        }
    }

}
