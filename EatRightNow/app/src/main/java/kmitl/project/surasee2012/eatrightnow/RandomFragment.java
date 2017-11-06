package kmitl.project.surasee2012.eatrightnow;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RandomFragment extends Fragment {

    EditText editText;

    private RandomFragmentListener listener;

    public static final RandomFragment newInstance(RandomFragmentListener listener){
        RandomFragment fragment = new RandomFragment();
        fragment.setListener(listener);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.random_fragment, container, false);
        Button randomBtn = rootView.findViewById(R.id.randomBtn);

        randomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.random();

            }
        });
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editText = getView().findViewById(R.id.searchEditText);

    }

    public RandomFragmentListener getListener() {
        return listener;
    }

    public void setListener(RandomFragmentListener listener) {
        this.listener = listener;
    }

    public interface RandomFragmentListener{
        void random();
    }
}
