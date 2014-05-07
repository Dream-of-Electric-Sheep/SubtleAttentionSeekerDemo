package com.dream_of_electric_sheep.android.attention_seeker;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.nineoldandroids.animation.Keyframe;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.PropertyValuesHolder;
import com.nineoldandroids.view.animation.AnimatorProxy;

/**
 * Based on <a href="https://plus.google.com/u/0/118417777153109946393/posts/FABaJhRMCuy">Cyril
 * Mottier's Google+ post</a>
 * 
 * @author Cyril Mottier (animation code for "Subtle Attention Seekers")
 * @author Dream of Electric Sheep (porting to NineOldAndroids)
 * 
 */
public class MainActivity extends SherlockFragmentActivity {

  private final Handler mHandler = new Handler(Looper.getMainLooper());
  private View menu_item_1;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    if (savedInstanceState == null) {
      getSupportFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment())
          .commit();
    }
  }

  public void clickTada(View v) {
    tada(menu_item_1).start();
  }

  public void clickNope(View v) {
    nope(menu_item_1).start();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    Log.d(getClass().getSimpleName(), "onCreateOptionsMenu()");
    super.onCreateOptionsMenu(menu);

    MenuItem mMenuItem1 = menu.add(Menu.NONE, R.id.menu_item_1, Menu.NONE,
        R.string.action_add_person);
    mMenuItem1.setIcon(R.drawable.ic_action_social_add_person);
    mMenuItem1.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

    new Handler().post(new Runnable() {
      @Override
      public void run() {
        menu_item_1 = findViewById(R.id.menu_item_1);
      }
    });

    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();
    if (id == R.id.menu_item_1) {
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  public static ObjectAnimator tada(View view) {
    return tada(view, 1f);
  }

  public static ObjectAnimator tada(View view, float shakeFactor) {

    PropertyValuesHolder pvhScaleX = PropertyValuesHolder.ofKeyframe("scaleX",
        Keyframe.ofFloat(0f, 1f), Keyframe.ofFloat(.1f, .9f), Keyframe.ofFloat(.2f, .9f),
        Keyframe.ofFloat(.3f, 1.1f), Keyframe.ofFloat(.4f, 1.1f), Keyframe.ofFloat(.5f, 1.1f),
        Keyframe.ofFloat(.6f, 1.1f), Keyframe.ofFloat(.7f, 1.1f), Keyframe.ofFloat(.8f, 1.1f),
        Keyframe.ofFloat(.9f, 1.1f), Keyframe.ofFloat(1f, 1f));

    PropertyValuesHolder pvhScaleY = PropertyValuesHolder.ofKeyframe("scaleY",
        Keyframe.ofFloat(0f, 1f), Keyframe.ofFloat(.1f, .9f), Keyframe.ofFloat(.2f, .9f),
        Keyframe.ofFloat(.3f, 1.1f), Keyframe.ofFloat(.4f, 1.1f), Keyframe.ofFloat(.5f, 1.1f),
        Keyframe.ofFloat(.6f, 1.1f), Keyframe.ofFloat(.7f, 1.1f), Keyframe.ofFloat(.8f, 1.1f),
        Keyframe.ofFloat(.9f, 1.1f), Keyframe.ofFloat(1f, 1f));

    PropertyValuesHolder pvhRotate = PropertyValuesHolder.ofKeyframe("rotation",
        Keyframe.ofFloat(0f, 0f), Keyframe.ofFloat(.1f, -3f * shakeFactor),
        Keyframe.ofFloat(.2f, -3f * shakeFactor), Keyframe.ofFloat(.3f, 3f * shakeFactor),
        Keyframe.ofFloat(.4f, -3f * shakeFactor), Keyframe.ofFloat(.5f, 3f * shakeFactor),
        Keyframe.ofFloat(.6f, -3f * shakeFactor), Keyframe.ofFloat(.7f, 3f * shakeFactor),
        Keyframe.ofFloat(.8f, -3f * shakeFactor), Keyframe.ofFloat(.9f, 3f * shakeFactor),
        Keyframe.ofFloat(1f, 0f));

    return ObjectAnimator.ofPropertyValuesHolder(
        AnimatorProxy.NEEDS_PROXY ? AnimatorProxy.wrap(view) : view, pvhScaleX, pvhScaleY,
        pvhRotate).setDuration(1000);
  }

  public static ObjectAnimator nope(View view) {
    float delta = view.getResources().getDimensionPixelOffset(R.dimen.spacing_medium);

    PropertyValuesHolder pvhTranslateX = PropertyValuesHolder.ofKeyframe("translationX",
        Keyframe.ofFloat(0f, 0f), Keyframe.ofFloat(.10f, -delta), Keyframe.ofFloat(.26f, delta),
        Keyframe.ofFloat(.42f, -delta), Keyframe.ofFloat(.58f, delta),
        Keyframe.ofFloat(.74f, -delta), Keyframe.ofFloat(.90f, delta), Keyframe.ofFloat(1f, 0f));

    return ObjectAnimator.ofPropertyValuesHolder(
        AnimatorProxy.NEEDS_PROXY ? AnimatorProxy.wrap(view) : view, pvhTranslateX)
        .setDuration(500);
  }

  /**
   * A placeholder fragment containing a simple view.
   */
  public static class PlaceholderFragment extends SherlockFragment {

    public PlaceholderFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      View rootView = inflater.inflate(R.layout.fragment_main, container, false);
      return rootView;
    }
  }

}
