package com.mapratama02.www.crudusermanagement;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mapratama02.www.crudusermanagement.adapter.UserManajementAdapter;
import com.mapratama02.www.crudusermanagement.database.DaoHandler;
import com.mapratama02.www.crudusermanagement.database.DaoSession;
import com.mapratama02.www.crudusermanagement.database.TblUserManajement;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity implements UserManajementAdapter.UserManajementAdapterCallback {

    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.recycler)
    RecyclerView recycler;

    private DaoSession daoSession;
    private UserManajementAdapter mAdapter;
    private List<TblUserManajement> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        daoSession = DaoHandler.getInstance(this);

        getSupportActionBar().setTitle("User Manajament");

        mList = daoSession.getTblUserManajementDao().queryBuilder().list();
        mAdapter = new UserManajementAdapter(mList, this);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setItemAnimator(new DefaultItemAnimator());
        recycler.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CreateActivity.class));
            }
        });
    }

    @Override
    public void onDelete(int position) {
        String name = mList.get(position).getName();
        showDialogDelete(position, name);
    }

    private void showDialogDelete(final int position, String name) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Delete user " + name + "?");
        builder.setCancelable(true);

        builder.setPositiveButton(
                "Iya",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        long idTbl = mList.get(position).getId();
                        daoSession.getTblUserManajementDao().deleteByKey(idTbl);

                        mList.remove(position);
                        mAdapter.notifyItemRemoved(position);
                        mAdapter.notifyItemRangeChanged(position, mList.size());

                        dialogInterface.dismiss();
                    }
                }
        );

        builder.setNegativeButton(
                "Tidak",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                }
        );
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
