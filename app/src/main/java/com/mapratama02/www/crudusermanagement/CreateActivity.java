package com.mapratama02.www.crudusermanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mapratama02.www.crudusermanagement.database.DaoHandler;
import com.mapratama02.www.crudusermanagement.database.DaoSession;
import com.mapratama02.www.crudusermanagement.database.TblUserManajement;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CreateActivity extends AppCompatActivity {

    @BindView(R.id.edt_name)
    EditText edt_name;
    @BindView(R.id.edt_email)
    EditText edt_email;
    @BindView(R.id.btn_save)
    Button btn_save;

    private Unbinder unbinder;
    private DaoSession daoSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Create new user");

        unbinder = ButterKnife.bind(this);
        daoSession = DaoHandler.getInstance(this);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edt_name.getText().toString();
                String email = edt_email.getText().toString();

                if (name.isEmpty() || email.isEmpty()) {
                    Toast.makeText(CreateActivity.this, "Opps! there is the blank field!", Toast.LENGTH_SHORT).show();
                } else {
                    TblUserManajement tblUserManajement = new TblUserManajement();
                    tblUserManajement.setName(name);
                    tblUserManajement.setEmail(email);
                    daoSession.getTblUserManajementDao().insert(tblUserManajement);

                    Toast.makeText(CreateActivity.this, "Data input successed", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(CreateActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    finish();
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
