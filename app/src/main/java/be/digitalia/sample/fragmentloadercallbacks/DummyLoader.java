package be.digitalia.sample.fragmentloadercallbacks;

import android.content.Context;
import android.support.v4.content.Loader;

/**
 * A dummy Loader than immediately returns a result.
 */

class DummyLoader extends Loader<Result> {
	DummyLoader(Context context) {
		super(context);
	}

	@Override
	protected void onStartLoading() {
		deliverResult(new Result("Hello World!"));
	}
}
