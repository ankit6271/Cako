package com.example.cako;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

class PgrAdapter extends FragmentStatePagerAdapter {
    int count;

    public PgrAdapter(@NonNull FragmentManager fm, int count) {
        super(fm);
        this.count = count;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new CakoOffers();
            case 1:
                return new PaymentOffers();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return count;
    }
}
