# LoaderCallbacks behavior change since Android Support Library 24.0.0

A sample project to demonstrate LoaderCallbacks behavior change in fragments since version 24.0.0 of the Android Support Library, leading to potential bugs and crashes.

## About the issue

Previously, Android Loaders used to be started only between ```Fragment.onStart()``` and ```Fragment.onStop()```. Since version 24.0.0 of the support library, that behavior has changed in an undocumented way. Loaders of **detached fragments** are now briefly started during a configuration change. Detached fragments are inactive and have no associated view hierarchy.

Consequently, one can not assume anymore that a fragment will be in the started state with an attached view hierarchy (for non-headless fragments) when ```LoaderCallbacks.onLoadFinished()``` is called with a new result. This requires to update the code to add tedious extra checks in every ```LoaderCallbacks``` to guarantee safe code.

This is a bug in my opinion. The LoaderManager should never start the Loaders, or at least never deliver new results, when a fragment is not in the started state, even less when it is invisible. There can be potentially many detached fragments in a single Activity at a given time and this behavior causes them all to start their loaders during configuration changes, which is also a waste of resources.

## Steps to reproduce

This sample project demonstrates the issue.

- Launch this project on any device
- Press the button to detach the fragment
- Rotate the screen to trigger a configuration change
- The app will crash because the code expects the view to be present when a new result is delivered..

If you change the support library version in build.gradle to an older version like 23.4.0, you will notice that no such crash will occur.
