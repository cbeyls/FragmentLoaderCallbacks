package be.digitalia.sample.fragmentloadercallbacks;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;

public class MainActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager()
					.beginTransaction()
					.add(R.id.activity_main, new MainFragment())
					.commit();
		}

		findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				// Detach the fragment, if any
				Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.activity_main);
				if (fragment != null) {
					getSupportFragmentManager()
							.beginTransaction()
							.detach(fragment)
							.commit();
				}
			}
		});
	}
}
