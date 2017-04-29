package be.digitalia.sample.fragmentloadercallbacks;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Fragment which populates a TextView with a message loaded using a Loader in a .
 */
public class MainFragment extends Fragment {

	private static final int LOADER_ID = 1;

	TextView textView;


	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_main, container, false);
		textView = (TextView) view.findViewById(R.id.text);
		return view;
	}

	@Override
	public void onDestroyView() {
		textView = null;
		super.onDestroyView();
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		getLoaderManager().initLoader(LOADER_ID, null, mLoaderCallbacks);
	}

	final LoaderManager.LoaderCallbacks<Result> mLoaderCallbacks = new LoaderManager.LoaderCallbacks<Result>() {
		@Override
		public Loader<Result> onCreateLoader(int id, Bundle args) {
			return new DummyLoader(getActivity());
		}

		@Override
		public void onLoadFinished(Loader<Result> loader, Result data) {
			if (data != null) {
				textView.setText(data.message);
			}
		}

		@Override
		public void onLoaderReset(Loader<Result> loader) {
		}
	};
}
