package tylerjxzhangtexty.texty;

import android.app.PendingIntent;
import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.content.Intent;
import android.telephony.SmsManager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
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

public class MainActivity extends AppCompatActivity{

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    static public FloatingActionButton fab;
    static public int sectionId;
    static TextView textView;
    static EditText editText;
    static EditText editText2;
    static Spinner spinner;
    static Spinner spinner2;
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

        fab = (FloatingActionButton)findViewById(R.id.fab);
        sectionId = 0;
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
        private String[] titles = {"LANGUAGE", "CURRENCY", "WEATHER", "STOCK"};

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show total pages.
            return titles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if(titles[position] != null){
                return titles[position];
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

            sectionId = getArguments().getInt(ARG_SECTION_NUMBER) - 1;
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            textView = (TextView) rootView.findViewById(R.id.section_label);
            editText = (EditText) rootView.findViewById(R.id.editText);
            editText2 = (EditText) rootView.findViewById(R.id.editText2);
            spinner = (Spinner)rootView.findViewById(R.id.spinner);
            spinner2 = (Spinner)rootView.findViewById(R.id.spinner2);

            textView.setText(getSectionTitle(sectionId));
            Log.d("main", "Title created: " + textView.getText());
            editText.setHint(getHint(sectionId));
            editText2.setHint(getHint2(sectionId));

            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(rootView.getContext(),
                    getDropdownArray(sectionId), android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            Log.d("main", "spinner created: " + spinner.getSelectedItem());
            spinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());

            ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(rootView.getContext(),
                    getDropdownArray2(sectionId), android.R.layout.simple_spinner_dropdown_item);
            spinner2.setAdapter(adapter2);
            Log.d("main", "spinner2 created: " + spinner2.getSelectedItem());
            spinner2.setOnItemSelectedListener(new CustomOnItemSelectedListener());

            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int section = getArguments().getInt(ARG_SECTION_NUMBER) - 1;
                        Log.d("main", "view: " + view.getId());

                    EditText editTextT = (EditText)view.getRootView().findViewById(R.id.editText);
                    Spinner spinnerT = (Spinner)view.getRootView().findViewById(R.id.spinner);
                    Spinner spinner2T = (Spinner)view.getRootView().findViewById(R.id.spinner2);

                    if (editTextT.getText() != null && spinnerT.getSelectedItem() != null && spinner2T.getSelectedItem() != null) {
                        String[] stringData = {editTextT.getText().toString(),
                                spinnerT.getSelectedItem().toString(),
                                spinner2T.getSelectedItem().toString()};
                        sendSMS(section, stringData);
                        Snackbar.make(view, "Sending Request, Please Wait", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    } else {
                        if (editTextT.getText() == null) {
                            Log.d("Main", " editText null");
                        }
                        if (spinnerT.getSelectedItem() == null) {
                            Log.d("Main", " spinner 1 null");
                        }
                        if (spinner2T.getSelectedItem() == null) {
                            Log.d("Main", " spinner 2 null");
                        }
                        Snackbar.make(view, "Please Validate the Information", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }


                }
            });


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

        //---sends an SMS message to another device---
        private void sendSMS(int sectionId, String[] data)
        {
            SmsManager smsManager = SmsManager.getDefault();
            String msg = "";
            switch(sectionId - 1){
                case 0:
                    msg += "@translate ";
                    msg += "@"+ data[1] + " @" + data[2] + " @";
                    break;
                case 1:
                    msg += "@currency ";
                    msg += "@"+ data[1] + " @" + data[2] + " @";
                    break;
                case 2:
                    msg += "@temperature ";
                    msg += "@" + data[2] + " @";
                    break;
                case 3:
                    msg += "@stock @";
                    break;
            }

            msg += data[0];

            //smsManager.sendTextMessage("a12672336296", null, msg, null, null);
            Log.d("Main","Msg sent: " + msg);

        }
    }
}
