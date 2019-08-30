package com.mapratama02.www.crudusermanagement.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mapratama02.www.crudusermanagement.R;
import com.mapratama02.www.crudusermanagement.database.TblUserManajement;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserManajementAdapter extends RecyclerView.Adapter<UserManajementAdapter.ViewHolder> {
    private List<TblUserManajement> list;
    private UserManajementAdapterCallback mAdapterCallback;

    public UserManajementAdapter(List<TblUserManajement> list, UserManajementAdapterCallback mAdapterCallback) {
        this.list = list;
        this.mAdapterCallback = mAdapterCallback;
    }

    @NonNull
    @Override
    public UserManajementAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserManajementAdapter.ViewHolder holder, int position) {
        TblUserManajement item = list.get(position);

        holder.user_name.setText(item.getName());
        holder.user_email.setText(item.getEmail());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void clear() {
        int size = this.list.size();
        this.list.clear();
        notifyItemRangeChanged(0, size);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.user_name)
        TextView user_name;
        @BindView(R.id.user_email)
        TextView user_email;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    mAdapterCallback.onDelete(getAdapterPosition());
                    return true;
                }
            });
        }
    }

    public interface UserManajementAdapterCallback {
        void onDelete(int position);
    }
}
