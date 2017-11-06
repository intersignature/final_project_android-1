package kmitl.project.surasee2012.eatrightnow;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, RandomFragment.RandomFragmentListener {

    FoodDbAdapter foodDbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        foodDbAdapter = new FoodDbAdapter(this);

        Button randomFragBtn = (Button) findViewById(R.id.randomFragBtn);

        if(savedInstanceState == null) {
            initialFragment();
        }

        randomFragBtn.setOnClickListener(this);
    }

    private void initialFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, RandomFragment.newInstance(MainActivity.this)).commit();
    }

    @Override
    public void onClick(View view) {
        if (R.id.randomFragBtn == view.getId()) {
            viewFragment(RandomFragment.newInstance(MainActivity.this));
        }
    }

    private void viewFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void random() {
        String data = foodDbAdapter.getData();
        Message.message(this,data);
    }
}
