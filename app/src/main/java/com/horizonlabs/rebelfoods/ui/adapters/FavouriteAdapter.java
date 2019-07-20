package com.horizonlabs.rebelfoods.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.horizonlabs.rebelfoods.R;
import com.horizonlabs.rebelfoods.data.local.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rajeev Ranjan -  ABPB on 20-07-2019.
 */
public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.UserHolder> {

    private ItemClick itemClick;
    List<UserEntity> userEntities = new ArrayList<>();

    public FavouriteAdapter() {
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_all_user, viewGroup, false);
        return new UserHolder(itView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int i) {
        UserEntity userEntity = userEntities.get(i);
        holder.tvName.setText(userEntity.getName() + "    " + userEntity.getIsBookmarked());


    }

    @Override
    public int getItemCount() {
        return userEntities.size();
    }

    public class UserHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvEmail;

        public UserHolder(@NonNull final View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemView != null && getAdapterPosition() != RecyclerView.NO_POSITION)
                        itemClick.onItemClick(userEntities.get(getAdapterPosition()));
                }
            });
        }
    }

    public void setUserEntities(List<UserEntity> userEntities) {
        this.userEntities = userEntities;
        notifyDataSetChanged();
    }

    public interface ItemClick {
        void onItemClick(UserEntity userEntity);
    }

    public void setOnItemClickListener(ItemClick listener) {
        this.itemClick = listener;
    }
}
