package com.dimitrisam.lesson_1;

import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.web.sugar.Web.onWebView;
import android.content.Intent;
import android.support.test.espresso.web.webdriver.DriverAtoms;
import android.support.test.espresso.web.webdriver.Locator;
import android.support.test.rule.ActivityTestRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<MainActivity>(MainActivity.class,
                    false, false){
                @Override
                protected void afterActivityLaunched() {
                    onWebView(withId(R.id.result_desc)).forceJavascriptEnabled();
                }
            };
    @Before
    public void setUp() {
        mActivityRule.launchActivity(new Intent());
    }
    @Test
    public void webViewTest() {
        onWebView(withId(R.id.result_desc))
                .withElement(DriverAtoms.findElement(Locator.XPATH,"//input[@name='q']"))
                .perform(DriverAtoms.webKeys("Наконец-то"))
                .withElement(DriverAtoms.findElement(Locator.XPATH,"//input[@name='btnK']"))
                .perform(DriverAtoms.webClick())
                .withElement(DriverAtoms.findElement(Locator.XPATH,"//div[@id='result-stats']"));
    }
}