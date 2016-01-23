package tylerjxzhangtexty.texty;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ArrayAdapter;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

      FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
      fab.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                      .setAction("Action", null).show();
          }
      });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 4 total pages.
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "LANGUAGE";
                case 1:
                    return "CURRENCY";
                case 2:
                    return "WEATHER";
                case 3:
                    return "STOCK";
            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getSectionTitle(getArguments().getInt(ARG_SECTION_NUMBER) - 1));
            EditText editText = (EditText) rootView.findViewById(R.id.editText);
            editText.setHint(getHint(getArguments().getInt(ARG_SECTION_NUMBER) - 1));
            EditText editText2 = (EditText) rootView.findViewById(R.id.editText2);
            editText2.setHint(getHint2(getArguments().getInt(ARG_SECTION_NUMBER) - 1));

            Spinner spinner = (Spinner)rootView.findViewById(R.id.spinner);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(rootView.getContext(),
                    getDropdownArray(getArguments().getInt(ARG_SECTION_NUMBER) - 1), android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(
                    new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View rootView,
                                                   int pos, long id) {
                            parent.getItemAtPosition(pos);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parents) {


                        }
                    }
            );

            Spinner spinner2 = (Spinner)rootView.findViewById(R.id.spinner2);
            ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(rootView.getContext(),
                    getDropdownArray2(getArguments().getInt(ARG_SECTION_NUMBER) - 1), android.R.layout.simple_spinner_item);
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner2.setAdapter(adapter2);

            return rootView;
        }

        public int getDropdownArray(int n){
            switch (n) {
                case 0:
                    return R.array.dropdown_array;
                case 1:
                    return R.array.dropdown1_array;
                case 2:
                    return R.array.dropdown2_array;
                case 3:
                    return R.array.dropdown3_array;
            }
            return -1;
        }

        public int getDropdownArray2(int n){
            switch (n) {
                case 0:
                    return R.array.dropdown_array2;
                case 1:
                    return R.array.dropdown1_array2;
                case 2:
                    return R.array.dropdown2_array2;
                case 3:
                    return R.array.dropdown3_array2;
            }
            return -1;
        }

        public String getSectionTitle(int n){
            switch (n) {
                case 0:
                    return "Language Translation";
                case 1:
                    return "Currency Conversion";
                case 2:
                    return "Current Weather";
                case 3:
                    return "Stock index";
            }
            return null;
        }

        public String getHint(int n){
            switch (n) {
                case 0:
                    return "Enter Text";
                case 1:
                    return "Enter Currency";
                case 2:
                    return "Enter City";
                case 3:
                    return "Enter Stock Name";
            }
            return null;
        }

        public String getHint2(int n){
            switch (n) {
                case 0:
                    return "Translation";
                case 1:
                    return "Converted";
                case 2:
                    return "Weather";
                case 3:
                    return "Stock index";
            }
            return null;
        }
    }
}
