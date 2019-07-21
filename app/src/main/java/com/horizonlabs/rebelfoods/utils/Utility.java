package com.horizonlabs.rebelfoods.utils;

import com.horizonlabs.rebelfoods.R;
import com.horizonlabs.rebelfoods.data.local.entity.UserEntity;

import java.util.Random;

/**
 * Created by Rajeev Ranjan -  ABPB on 20-07-2019.
 */
public class Utility {
    public static int getFixedBackground(int position) {
        Random rand = new Random();
        int n = rand.nextInt(2);
        switch (position % 3) {
            case 0:
                return R.drawable.circle_green;
            case 1:
                return R.drawable.circle_blue;
            case 2:
                return R.drawable.circle_red;
            default:
                return R.drawable.circle_green;
        }
    }

    public static String getShortName(String name) {
        String[] s = name.split("\\s+");
        String shortName = "";
        for (int i = 0; i < s.length; i++) {
            shortName = shortName + s[i].charAt(0);
        }
        return shortName;
    }

    public static String getAddress(UserEntity userEntity) {
        String address = "";
        address = "ADDRESS  : " + userEntity.getAddress().getStreet()
                + " , " + userEntity.getAddress().getSuite()
                + " , " + userEntity.getAddress().getCity()
                + " - " + userEntity.getAddress().getZipcode();

        return address;
    }
}
