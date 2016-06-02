package com.example.llw.demo_contentprovider_insert5;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editUsername;
    private EditText editUsernumber;
    private Button btnInsert;

    private void assignViews() {
        editUsername = (EditText) findViewById(R.id.edit_username);
        editUsernumber = (EditText) findViewById(R.id.edit_usernumber);
        btnInsert = (Button) findViewById(R.id.btn_insert);
        btnInsert.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assignViews();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_insert:
                Insert_name_number(editUsername.getText().toString(), editUsernumber.getText().toString());
                break;
        }
    }


    public void Insert_name_number(String name, String number) {

        ContentResolver contentResolver = getContentResolver();
        ContentValues values = new ContentValues();
        Uri uri = contentResolver.insert(ContactsContract.RawContacts.CONTENT_URI, values);
        //解析uri
        long raw_contact_id = ContentUris.parseId(uri);
        values.clear();


//向手机添加联系人名字             向手机添加联系人名字                          向手机添加联系人名字
        values.put(ContactsContract.CommonDataKinds.StructuredName.RAW_CONTACT_ID, raw_contact_id);
        values.put(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, name);
        values.put(ContactsContract.CommonDataKinds.StructuredName.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
        uri = contentResolver.insert(ContactsContract.RawContacts.CONTENT_URI, values);
        // uri = contentResolver.insert(ContactsContract.Data.CONTENT_URI, values);
        values.clear();


//向手机添加联系人号码              向手机添加联系人号码                         向手机添加联系人号码
        values.put(ContactsContract.CommonDataKinds.Phone.RAW_CONTACT_ID, raw_contact_id);
        values.put(ContactsContract.CommonDataKinds.Phone.NUMBER, number);
        values.put(ContactsContract.CommonDataKinds.Phone.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
        // uri = contentResolver.insert(ContactsContract.Data.CONTENT_URI, values);
        uri = contentResolver.insert(ContactsContract.RawContacts.CONTENT_URI, values);
        // uri = contentResolver.insert(ContactsContract.Contacts.CONTENT_URI,values);
        values.clear();

//向邮箱库添加邮箱                    邮箱库添加邮箱                                 邮箱库添加邮箱
        values.put(ContactsContract.CommonDataKinds.Email.RAW_CONTACT_ID, raw_contact_id);
        values.put(ContactsContract.CommonDataKinds.Email.MIMETYPE, ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE);
        values.put(ContactsContract.CommonDataKinds.Email.DATA, "XERDPDLLW@163.COM");
        uri = contentResolver.insert(ContactsContract.Data.CONTENT_URI, values);
        values.clear();

//##############################################################################################################################################
//向SMS写入短信        //向SMS写入短信                        向SMS写入短信
        Uri sms_uri = Uri.parse("content://sms/");
        ContentValues contentValues = new ContentValues();
        contentValues.put("type", 1);
        values.put("date", System.currentTimeMillis());
        contentValues.put("address", "12343");
        contentValues.put("body", "您的尾号为9228的卡新转入1000,00元，余额为5000,000,0.00");
        getContentResolver().insert(sms_uri, contentValues);
        contentValues.clear();


        /*短信是手机常见的功能，本文就以实例形式讲述了Android实现将已发送的短信写入短信数据库的方法。分享给大家供大家参考之用。具体如下：

一般来说，把短信发送出去以后，需要把已发送的短信写入短信数据库。短信数据库有多个Uri，其中已发送的Uri是content://sms/sent。

具体功能代码如下：

// 把短信写入数据库  ContentValues.put个参数意义详解

    ContentValues values = new ContentValues();
    // 发送时间
    values.put("date", System.currentTimeMillis());
    // 阅读状态
    values.put("read", 0);
    // 类型：1为收，2为发
    values.put("type", 2);
    // 发送号码
    values.put("address",smsWidget.str_number);
    // 发送内容
    values.put("body", content);
    // 插入短信库
    getContentResolver().insert(Uri.parse("content://sms/sent"), values);

*/

//########################################################################################################################################

    }
}
