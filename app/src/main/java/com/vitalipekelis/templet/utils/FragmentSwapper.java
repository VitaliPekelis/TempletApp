package com.vitalipekelis.templet.utils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import java.util.List;

public class FragmentSwapper
{
    private FragmentManager mFragmentManager = null;

    public FragmentSwapper(FragmentManager fragmentManager)
    {
        mFragmentManager = fragmentManager;
    }

    public Fragment swapToFragment(Class<? extends Fragment> fragmentClass, Bundle arguments, int containerResourceId, boolean isShouldaddToBackStack)
    {

        return swapToFragment(fragmentClass, arguments, containerResourceId, isShouldaddToBackStack, true);
    }

    public Fragment swapToFragmentWithoutAnimation(Class<? extends Fragment> fragmentClass, Bundle arguments, int containerResourceId, boolean isShouldaddToBackStack)
    {

        return swapToFragment(fragmentClass, arguments, containerResourceId, isShouldaddToBackStack, false);
    }

    public Fragment swapToFragment(Class<? extends Fragment> fragmentClass, Bundle arguments, int containerResourceId, boolean isShouldaddToBackStack, boolean isAnimationEnabled)
    {
        final String fragmentTag = fragmentClass.getSimpleName();

        // try to
        Fragment newFragment = mFragmentManager.findFragmentByTag(fragmentTag);
        FragmentTransaction mFt = mFragmentManager.beginTransaction();

        // if (newFragment == null)
        {
            try {
                newFragment = fragmentClass.newInstance();
                // pass the arguments to the fragment
                // note that you can't call setArguments if the fragment is
                // attached!
                // the last constrain might cause weird behavior in case we
                // would like to send new arguments. Be aware!
                newFragment.setArguments(arguments);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
		/*
		 * if (isAnimationEnabled) {
		 * mFt.setCustomAnimations(R.anim.tab_right_to_left_in_animation,
		 * R.anim.tab_right_to_left_out_animation,
		 * R.anim.tab_left_to_right_in_animation,
		 * R.anim.tab_left_to_right_out_animation); }
		 */

        if (isShouldaddToBackStack) {

            mFt.add(containerResourceId, newFragment, fragmentTag);
            mFt.addToBackStack(fragmentTag);
        }
        else
        {
            mFt.replace(containerResourceId, newFragment, fragmentTag);
        }

        mFt.commit();

        return newFragment;
    }

    public void printFragmentsStack()
    {
        List<Fragment> fragments = mFragmentManager.getFragments();

        if (fragments != null)
        {
            Log.i("fragments", "==========================");
            for(int i = 0 ; i < fragments.size() ; i++)
            {
                Log.d("fragments", i + " - " + (fragments.get(i) != null ? fragments.get(i).getClass().getSimpleName() : "null"));
            }
            Log.i("fragments", "==========================");
        }
    }

    public void refreshFragment(Fragment fragment)
    {
        //fragment.setArguments(bundle);
        mFragmentManager.beginTransaction().detach(fragment).attach(fragment).commit();
    }

    public void clearFragments()
    {
        if (mFragmentManager.getBackStackEntryCount() > 0)
        {
            FragmentManager.BackStackEntry first = mFragmentManager.getBackStackEntryAt(0);
            mFragmentManager.popBackStackImmediate(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

    public void clearFragmentsToPosition(int iPosition)
    {
        if (mFragmentManager.getBackStackEntryCount() > 0 && iPosition >= 0 && mFragmentManager.getBackStackEntryCount() > iPosition)
        {
            FragmentManager.BackStackEntry first = mFragmentManager.getBackStackEntryAt(iPosition);
            mFragmentManager.popBackStackImmediate(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

    public void popFragment()
    {

        mFragmentManager.popBackStack();
    }

    public Fragment findFragmentByTag(String tag)
    {

        return mFragmentManager.findFragmentByTag(tag);
    }

    public Fragment findFragmentById(int id)
    {

        return mFragmentManager.findFragmentById(id);
    }

    private static String getFragmentTag(Class<? extends Fragment> baseFragmentClass)
    {
        return baseFragmentClass.getSimpleName();
    }

    /**
     * Finds the top fragment that inherits from the given class in the fragments stack.
     *
     * @param i_class
     *
     * @return The position, or -1 if not found.
     */
    public int getTopFragmentExtendsClassPosition(Class<? extends Fragment> i_class)
    {
        int result = -1;

        List<Fragment> fragmentsInStack = mFragmentManager.getFragments();

        if (fragmentsInStack != null)
        {
            for (int i = fragmentsInStack.size() - 1 ; i >= 0 ; i--)
            {
                if (fragmentsInStack.get(i) != null && i_class.isAssignableFrom(fragmentsInStack.get(i).getClass()))
                {
                    result = i;
                    break;
                }
            }
        }

        return result;
    }

    /**
     * Finds if top fragment in stack is instance of given class.
     *
     * @param iFragmentClass
     *
     * @return true if top fragment in stack is instance of given class.
     */
    public boolean isTopFragmentIsInstanceOfClass(Class<? extends Fragment> iFragmentClass)
    {
        boolean result = false;

        List<Fragment> fragmentsInStack = mFragmentManager.getFragments();

        if (fragmentsInStack != null)
        {
            for (int i = fragmentsInStack.size() - 1 ; i >= 0 ; i--)
            {
                Fragment currentFragment = fragmentsInStack.get(i);

                if (currentFragment != null)
                {
                    result = iFragmentClass.isInstance(currentFragment);
                    break;
                }
            }
        }

        return result;
    }
}
