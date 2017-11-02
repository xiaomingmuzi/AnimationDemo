package com.lixm.animationdemo.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lixm.animationdemo.R;
import com.lixm.animationdemo.application.MyApplication1;
import com.lixm.animationdemo.bean.User;
import com.lixm.animationdemo.db.MigrationHelper;
import com.lixm.animationdemo.db.UserDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

public class GreenDaoActivity extends BaseActivity {

    private EditText idEt, nameEt, sexEt;
    private Button mConfirm, mSearch, mDelete, mModify,mUpdateDB;
    private UserDao userDao;
    private TextView content;
    private StringBuffer msb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_dao);
        userDao = MyApplication1.getInstances().getDaoSession().getUserDao();
        idEt = (EditText) findViewById(R.id.user_id);
        nameEt = (EditText) findViewById(R.id.name);
        sexEt = (EditText) findViewById(R.id.sex);
        mConfirm = (Button) findViewById(R.id.button3);
        mConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setId(Long.parseLong(idEt.getText().toString().trim()));
                user.setName(nameEt.getText().toString().trim());
                user.setSex(sexEt.getText().toString().trim());
                userDao.insert(user);
                idEt.setText("");
                nameEt.setText("");
                sexEt.setText("");
                List<User> users = userDao.loadAll();
                showData(users);
            }
        });
        mSearch = (Button) findViewById(R.id.button4);
        mSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = idEt.getText().toString().trim();
                if (TextUtils.isEmpty(id)) {
                    List<User> users = userDao.loadAll();
                    showData(users);
                } else {
                    QueryBuilder<User> qb = userDao.queryBuilder();
                    qb.where(UserDao.Properties.Id.eq(id)).orderAsc(UserDao.Properties.Id);
                    List<User> list = qb.list();
                    Log.i(getClass().getName(),"=========查询出来的用户："+list.size());
                    showData(list);
                }
            }
        });
        msb = new StringBuffer("");
        content = (TextView) findViewById(R.id.textView);
        mDelete = (Button) findViewById(R.id.delete);
        mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = idEt.getText().toString().trim();
                if (TextUtils.isEmpty(id)) {
                    Toast.makeText(GreenDaoActivity.this, "请输入id", Toast.LENGTH_SHORT).show();
                    return;
                }
                userDao.deleteByKey(Long.valueOf(id));
                List<User> users = userDao.loadAll();
                showData(users);
            }
        });
        mModify = (Button) findViewById(R.id.modify);
        mModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setId(Long.parseLong(idEt.getText().toString().trim()));
                user.setName(nameEt.getText().toString().trim());
                user.setSex(sexEt.getText().toString().trim());
                userDao.update(user);
                idEt.setText("");
                nameEt.setText("");
                sexEt.setText("");
                List<User> users = userDao.loadAll();
                showData(users);
            }
        });
        mUpdateDB= (Button) findViewById(R.id.update_db);
        mUpdateDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MigrationHelper.migrate(MyApplication1.getInstances().getDb());
            }
        });

    }

    /**
     * 展示查询出来的用户
     * @param users
     */
    private void showData(List<User> users) {
        msb.delete(0, msb.length());
        for (int i = 0; i < users.size(); i++) {
            msb.append(users.get(i).toString());
            msb.append("\n");
        }
        content.setText(msb.toString());
    }
}
