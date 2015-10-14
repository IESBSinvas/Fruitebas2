package aplicativo.milreuelima.iesb.com.br.futebas.Atividades.adapters;

/**
 * Created by edu on 12/10/15.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import aplicativo.milreuelima.iesb.com.br.futebas.Atividades.EquipesFragment;
import aplicativo.milreuelima.iesb.com.br.futebas.Atividades.JogadoresFragment;

public class TabsPageAdapter extends FragmentPagerAdapter {

    public TabsPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {

        switch (index) {
            case 0:
                // Top Rated fragment activity
                return new JogadoresFragment();
            case 1:
                // Games fragment activity
                return new EquipesFragment();
        }

        return null;
    }

    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 2;
    }

}