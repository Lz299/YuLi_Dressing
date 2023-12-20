package com.xxxy.no2.yulidressing;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import java.io.File;
import java.io.IOException;
import java.util.UUID;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UploadAvtivity extends AppCompatActivity {
    public static final  int ALBUM_CODE = 0;

    private Button button1,button2;
    ImageView iv_image;
    File file = null;
    public static final  int UPLOAD_SUCCESS = 2;
    public static final  int UPLOAD_ERROR = 1;
    private myHandler myHandler;

    class myHandler extends Handler {

        @Override
        public void dispatchMessage(Message msg) {
            // TODO Auto-generated method stub
            super.dispatchMessage(msg);
            String result;
            switch(msg.what){
                case UPLOAD_ERROR:
                    if(msg.obj!=null){
                        result=(String)msg.obj;
                        Toast.makeText(UploadAvtivity.this, "失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case UPLOAD_SUCCESS:
                    if(msg.obj!=null){
                        result=(String)msg.obj;
                        Toast.makeText(UploadAvtivity.this, result, Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_avtivity);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        iv_image = findViewById(R.id.iv_image);

        myHandler = new myHandler();
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAlbum();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://192.168.184.116:8088/YuLi_war/UpServlet";
                String filename = file.getName();
                String filepath = file.getAbsolutePath();
                String mediaType = "image/*";
                try {
                    upload(url,filepath,filename,mediaType);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }
        });


    }

    private void openAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        //有返回值的活动调用
        startActivityForResult(intent, ALBUM_CODE);
    }

    //接收返回值
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ALBUM_CODE) {
            // 相册
            if (resultCode == RESULT_OK) {
                Uri imageUri = data.getData();
                // 处理uri,7.0以后的fileProvider 把URI 以content provider 方式 对外提供的解析方法
                file = getFileFromUri(imageUri, UploadAvtivity.this);
                if (file.exists()) {
                    // updateFile(file);
                    Toast.makeText(UploadAvtivity.this, file.getAbsolutePath(), Toast.LENGTH_LONG).show();
                    //把选择好的图片显示在界面上的ImageView上
                    iv_image.setImageURI(Uri.fromFile(file));
                }
            }
        }
    }

    public File getFileFromUri(Uri uri, Context context) {
        if (uri == null) {
            return null;
        }
        switch (uri.getScheme()) {
            case "content":
                return getFileFromContentUri(uri, context);
            case "file":
                return new File(uri.getPath());
            default:
                return null;
        }
    }

    /**
     * 通过内容解析中查询uri中的文件路径
     */
    @SuppressLint("Range")
    private File getFileFromContentUri(Uri contentUri, Context context) {
        if (contentUri == null) {
            return null;
        }
        File file = null;
        String filePath;
        String[] filePathColumn = { MediaStore.MediaColumns.DATA };
        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursor = contentResolver.query(contentUri, filePathColumn, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            filePath = cursor.getString(cursor.getColumnIndex(filePathColumn[0]));
            cursor.close();

            if (!TextUtils.isEmpty(filePath)) {
                file = new File(filePath);
            }
        }
        return file;
    }


    //上传文件到服务器
    private void upload(String url, String filePath, String fileName,String mediaType) throws Exception {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("imageurl", fileName,
                        RequestBody.create(MediaType.parse(mediaType), new File(filePath)))
                .build();

        Request request = new Request.Builder()
                .header("Authorization", "Client-ID " + UUID.randomUUID())
                .url(url)
                .post(requestBody)
                .build();
        //Response response = client.newCall(request).execute();
        Call call=client.newCall(request);
        call.enqueue(new Callback() {

            @Override
            public void onResponse(Call arg0, Response arg1) throws IOException {
                // TODO Auto-generated method stub
                String res=arg1.body().string();
                Message msg=new Message();
                msg.what=UPLOAD_SUCCESS;
                msg.obj=res;
                myHandler.sendMessage(msg);
            }

            @Override
            public void onFailure(Call arg0, IOException arg1) {
                // TODO Auto-generated method stub
                Message msg=new Message();
                msg.what=UPLOAD_SUCCESS;
                msg.obj=arg1.getMessage();
                myHandler.sendMessage(msg);
            }
        });
    }




}

